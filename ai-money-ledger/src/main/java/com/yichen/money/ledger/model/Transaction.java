package com.yichen.money.ledger.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("transactions")
public class Transaction {
    @Id
    private String id;

    @Indexed
    private String userId;

    private BigDecimal amount;
    private String currency;
    private String category;
    private String aiCategory;
    private Double aiConfidence;

    private String merchant;
    private String description;
    private Instant timestamp;

    private List<String> tags;
}

