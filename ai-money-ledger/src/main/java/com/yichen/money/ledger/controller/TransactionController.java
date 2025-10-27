package com.yichen.money.ledger.controller;

import com.yichen.money.common.ApiResponse;
import com.yichen.money.ledger.dto.CreateTransactionRequest;
import com.yichen.money.ledger.dto.TransactionDTO;
import com.yichen.money.ledger.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/ledger/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {
    private final TransactionService service;
    private static final String USER_HEADER = "X-User-Name";

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiResponse<TransactionDTO>> create(
            @RequestHeader(name = USER_HEADER, required = false) String userName,
            @RequestHeader(name = "X-User-Id", required = false) String userId,
            @Valid @RequestBody CreateTransactionRequest req) {
        String uid = userName != null ? userName : userId; // 兼容旧头
        return service.create(uid, req)
                      .map(TransactionDTO::from)
                      .map(ApiResponse::ok);
    }

    @GetMapping
    public Flux<TransactionDTO> list(
            @RequestHeader(name = USER_HEADER, required = false) String userName,
            @RequestHeader(name = "X-User-Id", required = false) String userId
    ) {
        String uid = userName != null ? userName : userId;
        return service.listByUser(uid).map(TransactionDTO::from);
    }
}
