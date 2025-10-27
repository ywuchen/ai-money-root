package com.yichen.money.ledger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LedgerApp {
    public static void main(String[] args) {
        SpringApplication.run(LedgerApp.class, args);
    }
}
