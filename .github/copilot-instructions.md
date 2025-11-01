# AI记账小程序规则
language: java
jdk: 21
spring-boot: 3.2.x
spring-cloud: 2023.x

禁止出现 Lombok @Data，使用 @Getter @Setter @ToString 代替
所有 Controller 返回统一封装 Result<T>
所有 Feign 接口必须写 FallbackFactory 并记录日志
所有实体类实现 Serializable
所有时间字段使用 java.time.LocalDateTime
所有金额单位用“分”，避免浮点
所有外部配置前缀统一小写中划线，例：deepseek.api-key
单元测试覆盖率不低于 70%，使用 @Testcontainers 做集成测试