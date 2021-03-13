package com.core.reactive;

public interface StockService {
    static Stock fetch(String symbol, double price) {

        return new Stock(symbol, price);
    }

}
