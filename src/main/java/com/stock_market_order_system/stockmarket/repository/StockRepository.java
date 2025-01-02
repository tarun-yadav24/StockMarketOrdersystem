package com.stock_market_order_system.stockmarket.repository;

import com.stock_market_order_system.stockmarket.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
