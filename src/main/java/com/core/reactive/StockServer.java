package com.core.reactive;

import io.reactivex.rxjava3.core.Observable;
import java.util.Arrays;

import static com.core.reactive.StockService.fetch;

public class StockServer {


    public static void main(String[] args) {

        var symbols = Arrays.asList("GOOG", "AMZN", "TESLA");

        System.out.println("server started...");


        //create Observable
        //emit - do something and put it, till it finishes
        //subscribe - to read data
        Observable.<Stock>create(emitter-> {
                            System.out.println("created...");

                            //emit the Stock
                            symbols.forEach(symbol -> emitter
                                   .onNext(fetch(symbol, 10.0)));


                      })
                      .subscribe(stock -> System.out.println("got...: " + stock.toString()))
                      .dispose();



    }
}
