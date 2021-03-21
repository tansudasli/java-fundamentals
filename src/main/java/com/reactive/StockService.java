package com.reactive;

public interface StockService {
    static Stock fetch(String symbol) {

        //fetch via api
        return new Stock(symbol, 50.0);
    }

}
