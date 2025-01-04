package com.stock_market_order_system.stockmarket.controller;

import com.stock_market_order_system.stockmarket.dto.StockDto;
import com.stock_market_order_system.stockmarket.dto.UserDto;
import com.stock_market_order_system.stockmarket.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockService stockService;


    //API HTTP Requests
    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome to the Stock Market Order System API. Use /api/stocks/registerUser to register a user and /api/stocks/registerStock to register a stock.");
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buyStock(@RequestParam long BuyerId, @RequestParam long StockId, @RequestParam int Quantity){
      stockService.buyStock(BuyerId, StockId, Quantity);
      return ResponseEntity.ok("Stock bought successfully");
    }

    @PostMapping("/sell")
    public ResponseEntity<String> sellStock(@RequestParam long SellerId, @RequestParam long StockId, @RequestParam int Quantity){
        stockService.sellStock(SellerId, StockId, Quantity);
        return ResponseEntity.ok("Stock Sold successfully");
    }

    @GetMapping("/dashboard/{userId}")
    public ResponseEntity<String> getUserDashboard(@RequestParam long userId){
        stockService.getUserDashboard(userId);
        return ResponseEntity.ok("Stock Dashboard");
    }

    @PostMapping("/registerUser")
    public ResponseEntity<String> registerUser(@RequestParam UserDto userDto){
        stockService.registerUser(userDto);
        return ResponseEntity.ok("User registered");
    }

    @PostMapping("/registerStock")
    public ResponseEntity<String> registerStock(@RequestParam StockDto stockDto){
        stockService.registerStock(stockDto);
        return ResponseEntity.ok("Stock registered");
    }
}
