package com.stock_market_order_system.stockmarket.service;

import com.stock_market_order_system.stockmarket.model.Stock;
import com.stock_market_order_system.stockmarket.model.Transaction;
import com.stock_market_order_system.stockmarket.model.User;
import com.stock_market_order_system.stockmarket.repository.StockRepository;
import com.stock_market_order_system.stockmarket.repository.TransactionRepository;
import com.stock_market_order_system.stockmarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    //Business Logic goes in here.

    @Autowired
    UserRepository userRepository;
    @Autowired
    StockRepository stockRepository;
    @Autowired
    TransactionRepository transactionRepository;

    public void buyStock(long BuyerId,long StockId,int Quantity){
        User Buyer = userRepository.findById(BuyerId).orElseThrow();
        Stock Stock = stockRepository.findById(StockId).orElseThrow();
        double totalStockPrice = Stock.getPrice() * Quantity;

        if(Buyer.getTotalAmount()<totalStockPrice){
            throw new RuntimeException("Insufficient funds to purchase stocks.");
        }

        Buyer.setTotalAmount(Buyer.getTotalAmount()-totalStockPrice);
        Buyer.setStocksOwned(Buyer.getStocksOwned()+Quantity);
        userRepository.save(Buyer);

        Transaction transaction = new Transaction();
        transaction.setBuyerId(BuyerId);
        transaction.setStockId(StockId);
        transaction.setQuantity(Quantity);
        transactionRepository.save(transaction);

    }

    public void sellStock(long SellerId,long StockId,int Quantity){
        User Seller = userRepository.findById(SellerId).orElseThrow();
        Stock stock = stockRepository.findById(StockId).orElseThrow();
        double totalStockPrice = stock.getPrice() * Quantity;

        if(Seller.getStocksOwned()<Quantity){
            throw new RuntimeException("Insufficient Stocks to Sell.");
        }

        Seller.setTotalAmount(Seller.getTotalAmount()+totalStockPrice);
        Seller.setStocksOwned(Seller.getStocksOwned()-Quantity);
        userRepository.save(Seller);

        Transaction transaction = new Transaction();
        transaction.setBuyerId(SellerId);
        transaction.setStockId(StockId);
        transaction.setQuantity(Quantity);
        transactionRepository.save(transaction);
    }

    public User getUserDashboard(long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return user;
    }

}

//Sell -> Low to High
//Buy -> High to Low.

//id : 1
//username : aditya
//totalAmount = 10,000rs
//
//id : 2
//username : Tarun
//        totalAmount = 3000rs
//
//
//Stocks
//
//id : 1
//name : tata motors
//price : 1000rs
//
//
//initially Empty account
//
//#1 Buy : 5 stocks at rate of 1000rs = 5000rs ( Totalamount = buy - totalInitialAmount ); //Dashboard -> show everything
//
//#2 Buy : 3 Stocks at rate of 1000rs = 3000rs I requested // Depending on number of Users registered anyone can buy or sell. ( market related)
//#3 Aditya sell 3 stocks to tarun. ( tarun and aditya ke id ka update totalamount same like above)
//
//
//Market related : Anybody can buy or sell
//
//
//Dashboard Extra features