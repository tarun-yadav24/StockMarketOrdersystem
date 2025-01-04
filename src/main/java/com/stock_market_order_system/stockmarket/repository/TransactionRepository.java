package com.stock_market_order_system.stockmarket.repository;

import com.stock_market_order_system.stockmarket.dto.TransactionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDto, Long> {
}

