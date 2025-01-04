package com.stock_market_order_system.stockmarket.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private double totalAmount;
    private int stocksOwned;

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public int getStocksOwned() {
        return stocksOwned;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setStocksOwned(int stocksOwned) {
        this.stocksOwned = stocksOwned;
    }
}
