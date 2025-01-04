package com.stock_market_order_system.stockmarket.service;

import com.stock_market_order_system.stockmarket.dto.StockDto;
import com.stock_market_order_system.stockmarket.dto.TransactionDto;
import com.stock_market_order_system.stockmarket.dto.UserDto;
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
        UserDto Buyer = userRepository.findById(BuyerId).orElseThrow();
        StockDto StockDto = stockRepository.findById(StockId).orElseThrow();
        double totalStockPrice = StockDto.getPrice() * Quantity;

        if(Buyer.getTotalAmount()<totalStockPrice){
            throw new RuntimeException("Insufficient funds to purchase stocks.");
        }

        Buyer.setTotalAmount(Buyer.getTotalAmount()-totalStockPrice);
        Buyer.setStocksOwned(Buyer.getStocksOwned()+Quantity);
        userRepository.save(Buyer);

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setBuyerId(BuyerId);
        transactionDto.setStockId(StockId);
        transactionDto.setQuantity(Quantity);
        transactionRepository.save(transactionDto);

    }

    public void sellStock(long SellerId,long StockId,int Quantity){
        UserDto Seller = userRepository.findById(SellerId).orElseThrow();
        StockDto stockDto = stockRepository.findById(StockId).orElseThrow();
        double totalStockPrice = stockDto.getPrice() * Quantity;

        if(Seller.getStocksOwned()<Quantity){
            throw new RuntimeException("Insufficient Stocks to Sell.");
        }

        Seller.setTotalAmount(Seller.getTotalAmount()+totalStockPrice);
        Seller.setStocksOwned(Seller.getStocksOwned()-Quantity);
        userRepository.save(Seller);

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setBuyerId(SellerId);
        transactionDto.setStockId(StockId);
        transactionDto.setQuantity(Quantity);
        transactionRepository.save(transactionDto);
    }

    public UserDto getUserDashboard(long userId) {
        UserDto userDto = userRepository.findById(userId).orElseThrow();
        return userDto;
    }

    public void registerUser(UserDto userDto) {
        userDto.setUsername(userDto.getUsername());

        userRepository.save(userDto);
    }
    public void registerStock(StockDto stockDto) {
        stockDto.setName(stockDto.getName());
        stockRepository.save(stockDto);
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