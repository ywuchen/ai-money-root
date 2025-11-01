-- AI 记账小程序 PostgreSQL 初始化脚本
-- Author: GitHub Copilot
-- Since: 2025-11-01
-- Usage:
--   1) 先创建数据库（若未创建）：
--      > psql -U postgres -c "CREATE DATABASE ai_account;"
--   2) 执行本脚本：
--      > psql -U postgres -d ai_account -f db-init.sql

-- ================================================
-- 通用：update_time 自动更新时间触发器函数
-- ================================================
CREATE OR REPLACE FUNCTION trg_set_update_time()
RETURNS trigger AS $$
BEGIN
  NEW.update_time := now();
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- ================================================
-- 用户域（user-service）
-- ================================================
-- 用户表
CREATE TABLE IF NOT EXISTS t_user (
    id            BIGSERIAL PRIMARY KEY,
    username      VARCHAR(50)  NOT NULL,
    email         VARCHAR(100),
    phone         VARCHAR(30),
    password_hash VARCHAR(200) NOT NULL,
    create_time   TIMESTAMP    NOT NULL DEFAULT now(),
    update_time   TIMESTAMP    NOT NULL DEFAULT now()
);

-- 唯一约束/索引
CREATE UNIQUE INDEX IF NOT EXISTS uq_user_username ON t_user (username);
CREATE UNIQUE INDEX IF NOT EXISTS uq_user_email    ON t_user (email);
CREATE UNIQUE INDEX IF NOT EXISTS uq_user_phone    ON t_user (phone);

-- 角色表
CREATE TABLE IF NOT EXISTS t_role (
    id          BIGSERIAL PRIMARY KEY,
    role_code   VARCHAR(50)  NOT NULL,
    role_name   VARCHAR(100) NOT NULL,
    create_time TIMESTAMP    NOT NULL DEFAULT now(),
    update_time TIMESTAMP    NOT NULL DEFAULT now()
);
CREATE UNIQUE INDEX IF NOT EXISTS uq_role_code ON t_role (role_code);

-- 用户-角色关联表
CREATE TABLE IF NOT EXISTS t_user_role (
    id          BIGSERIAL PRIMARY KEY,
    user_id     BIGINT      NOT NULL REFERENCES t_user(id) ON DELETE CASCADE,
    role_id     BIGINT      NOT NULL REFERENCES t_role(id) ON DELETE CASCADE,
    create_time TIMESTAMP   NOT NULL DEFAULT now()
);
CREATE UNIQUE INDEX IF NOT EXISTS uq_user_role ON t_user_role (user_id, role_id);

-- 第三方 OAuth 绑定（微信/等）
CREATE TABLE IF NOT EXISTS t_oauth_third (
    id              BIGSERIAL PRIMARY KEY,
    user_id         BIGINT      NOT NULL REFERENCES t_user(id) ON DELETE CASCADE,
    provider        VARCHAR(50) NOT NULL,           -- wechat / github / ...
    open_id         VARCHAR(100) NOT NULL,
    union_id        VARCHAR(100),
    create_time     TIMESTAMP   NOT NULL DEFAULT now(),
    update_time     TIMESTAMP   NOT NULL DEFAULT now()
);
CREATE UNIQUE INDEX IF NOT EXISTS uq_oauth_provider_openid ON t_oauth_third (provider, open_id);

-- 触发器：自动维护 update_time
DO $$
BEGIN
  IF NOT EXISTS (SELECT 1 FROM pg_trigger WHERE tgname = 'trg_user_update_time') THEN
    CREATE TRIGGER trg_user_update_time
      BEFORE UPDATE ON t_user
      FOR EACH ROW
      EXECUTE FUNCTION trg_set_update_time();
  END IF;

  IF NOT EXISTS (SELECT 1 FROM pg_trigger WHERE tgname = 'trg_role_update_time') THEN
    CREATE TRIGGER trg_role_update_time
      BEFORE UPDATE ON t_role
      FOR EACH ROW
      EXECUTE FUNCTION trg_set_update_time();
  END IF;

  IF NOT EXISTS (SELECT 1 FROM pg_trigger WHERE tgname = 'trg_oauth_update_time') THEN
    CREATE TRIGGER trg_oauth_update_time
      BEFORE UPDATE ON t_oauth_third
      FOR EACH ROW
      EXECUTE FUNCTION trg_set_update_time();
  END IF;
END $$;

-- ================================================
-- 账单域（record-service）
-- ================================================
-- 账单记录表
CREATE TABLE IF NOT EXISTS t_record (
    id           BIGSERIAL PRIMARY KEY,
    user_id      BIGINT       NOT NULL,
    amount       INT          NOT NULL,            -- 金额（分）
    category     VARCHAR(20)  NOT NULL,           -- 餐饮|交通|购物|娱乐|其他
    note         VARCHAR(200),
    spend_time   TIMESTAMP    NOT NULL,           -- 消费时间
    create_time  TIMESTAMP    NOT NULL DEFAULT now(),
    update_time  TIMESTAMP    NOT NULL DEFAULT now()
);

-- 索引：BRIN（范围查询高效）。如需高选择性点查，可另加 BTREE 索引（见注释）。
CREATE INDEX IF NOT EXISTS idx_record_user_time_brin
  ON t_record USING BRIN (user_id, spend_time);
-- 建议按需启用 BTREE：
-- CREATE INDEX IF NOT EXISTS idx_record_user_time ON t_record (user_id, spend_time);

-- 分类表
CREATE TABLE IF NOT EXISTS t_category (
    id           BIGSERIAL PRIMARY KEY,
    user_id      BIGINT       NOT NULL,           -- 0/NULL 可表示公共分类（按需）
    name         VARCHAR(50)  NOT NULL,
    parent_id    BIGINT,
    create_time  TIMESTAMP    NOT NULL DEFAULT now(),
    update_time  TIMESTAMP    NOT NULL DEFAULT now()
);
CREATE UNIQUE INDEX IF NOT EXISTS uq_category_user_name ON t_category (user_id, name);

-- 预算表（按用户 + 月份 + 分类名称）
CREATE TABLE IF NOT EXISTS t_budget (
    id             BIGSERIAL PRIMARY KEY,
    user_id        BIGINT       NOT NULL,
    month          VARCHAR(7)   NOT NULL,         -- 'YYYY-MM'
    category_name  VARCHAR(50),                   -- NULL 表示总预算
    amount_limit   INT          NOT NULL,         -- 预算上限（分）
    create_time    TIMESTAMP    NOT NULL DEFAULT now(),
    update_time    TIMESTAMP    NOT NULL DEFAULT now()
);
CREATE UNIQUE INDEX IF NOT EXISTS uq_budget_user_month_cat
  ON t_budget (user_id, month, category_name);

-- 触发器：自动维护 update_time
DO $$
BEGIN
  IF NOT EXISTS (SELECT 1 FROM pg_trigger WHERE tgname = 'trg_record_update_time') THEN
    CREATE TRIGGER trg_record_update_time
      BEFORE UPDATE ON t_record
      FOR EACH ROW
      EXECUTE FUNCTION trg_set_update_time();
  END IF;

  IF NOT EXISTS (SELECT 1 FROM pg_trigger WHERE tgname = 'trg_category_update_time') THEN
    CREATE TRIGGER trg_category_update_time
      BEFORE UPDATE ON t_category
      FOR EACH ROW
      EXECUTE FUNCTION trg_set_update_time();
  END IF;

  IF NOT EXISTS (SELECT 1 FROM pg_trigger WHERE tgname = 'trg_budget_update_time') THEN
    CREATE TRIGGER trg_budget_update_time
      BEFORE UPDATE ON t_budget
      FOR EACH ROW
      EXECUTE FUNCTION trg_set_update_time();
  END IF;
END $$;

-- ================================================
-- 可选：t_record 分表（ShardingSphere-JDBC 按 user_id 哈希或取模）
-- 说明：如果启用分表，请在应用侧配置逻辑表 t_record -> t_record_00..t_record_15 的分片规则。
-- 以下脚本默认创建 16 张分表及其 BRIN 索引与 update_time 触发器。
-- 如不需要，注释掉本段。
-- ================================================
DO $$
DECLARE
  i INT;
  tbl TEXT;
  idx TEXT;
BEGIN
  FOR i IN 0..15 LOOP
    tbl := format('t_record_%02s', to_char(i, 'FM00'));
    idx := format('idx_%s_user_time_brin', tbl);

    EXECUTE format($f$
      CREATE TABLE IF NOT EXISTS %I (
        id           BIGSERIAL PRIMARY KEY,
        user_id      BIGINT       NOT NULL,
        amount       INT          NOT NULL,
        category     VARCHAR(20)  NOT NULL,
        note         VARCHAR(200),
        spend_time   TIMESTAMP    NOT NULL,
        create_time  TIMESTAMP    NOT NULL DEFAULT now(),
        update_time  TIMESTAMP    NOT NULL DEFAULT now()
      )$f$, tbl);

    EXECUTE format('CREATE INDEX IF NOT EXISTS %I ON %I USING BRIN (user_id, spend_time);', idx, tbl);

    -- 每张分表的 update_time 触发器
    EXECUTE format($f$
      DO $d$
      BEGIN
        IF NOT EXISTS (SELECT 1 FROM pg_trigger WHERE tgname = 'trg_%s_update_time') THEN
          EXECUTE format('CREATE TRIGGER trg_%s_update_time BEFORE UPDATE ON %s FOR EACH ROW EXECUTE FUNCTION trg_set_update_time();', '%1$s', '%1$s', '%1$s');
        END IF;
      END $d$;
    $f$, tbl);
  END LOOP;
END $$;

