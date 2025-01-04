package com.stock_market_order_system.stockmarket.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TransactionDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long BuyerId;
    private long SellerId;
    private long StockId;
    private int Quantity;
    private String type;

    public long getId() {
        return id;
    }

    public long getBuyerId() {
        return BuyerId;
    }

    public long getSellerId() {
        return SellerId;
    }

    public long getStockId() {
        return StockId;
    }

    public int getQuantity() {
        return Quantity;
    }
    public String getType() {
        return type;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBuyerId(long buyerId) {
        BuyerId = buyerId;
    }

    public void setSellerId(long sellerId) {
        SellerId = sellerId;
    }

    public void setStockId(long stockId) {
        StockId = stockId;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public void setType(String type) {
        this.type = type;
    }
}
