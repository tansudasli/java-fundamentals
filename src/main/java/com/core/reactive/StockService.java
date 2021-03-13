package com.core.reactive;

public interface StockService {
    public static Stock fetch(String symbol, double price) {

        return new Stock(symbol, price);
    }

}
