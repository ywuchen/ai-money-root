package com.yichen.money.ledger.dto;

import com.yichen.money.ledger.model.Transaction;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class TransactionDTO {
    private String id;
    private BigDecimal amount;
    private String currency;
    private String category;
    private String aiCategory;
    private Double aiConfidence;
    private String merchant;
    private String description;
    private Instant timestamp;
    private List<String> tags;

    public static TransactionDTO from(Transaction t) {
        TransactionDTO dto = new TransactionDTO();
        dto.id = t.getId();
        dto.amount = t.getAmount();
        dto.currency = t.getCurrency();
        dto.category = t.getCategory();
        dto.aiCategory = t.getAiCategory();
        dto.aiConfidence = t.getAiConfidence();
        dto.merchant = t.getMerchant();
        dto.description = t.getDescription();
        dto.timestamp = t.getTimestamp();
        dto.tags = t.getTags();
        return dto;
    }

    public String getId() { return id; }
    public BigDecimal getAmount() { return amount; }
    public String getCurrency() { return currency; }
    public String getCategory() { return category; }
    public String getAiCategory() { return aiCategory; }
    public Double getAiConfidence() { return aiConfidence; }
    public String getMerchant() { return merchant; }
    public String getDescription() { return description; }
    public Instant getTimestamp() { return timestamp; }
    public List<String> getTags() { return tags; }

    public void setId(String id) { this.id = id; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public void setCurrency(String currency) { this.currency = currency; }
    public void setCategory(String category) { this.category = category; }
    public void setAiCategory(String aiCategory) { this.aiCategory = aiCategory; }
    public void setAiConfidence(Double aiConfidence) { this.aiConfidence = aiConfidence; }
    public void setMerchant(String merchant) { this.merchant = merchant; }
    public void setDescription(String description) { this.description = description; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
    public void setTags(List<String> tags) { this.tags = tags; }
}

