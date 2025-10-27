package com.yichen.money.ledger.repository;

import com.yichen.money.ledger.model.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {
    Flux<Transaction> findByUserIdOrderByTimestampDesc(String userId);
    Flux<Transaction> findByUserId(String userId, Pageable pageable);
}

