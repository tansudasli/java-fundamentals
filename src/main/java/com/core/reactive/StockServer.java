package com.core.reactive;

import io.reactivex.rxjava3.core.Observable;
import java.util.Arrays;

import static com.core.reactive.StockService.fetch;

public class StockServer {


    public static void main(String[] args) {

        var symbols = Arrays.asList("GOOG", "AMZN", "TESLA");

        System.out.println("server started...");

        symbols.forEach(symbol -> {
                //create Observable
                //emit - do something and put it
                //subscribe - to read data
            Observable.<Stock>create(emitter-> {
                                System.out.println("created...");

                                //emit the price
                                emitter.onNext(fetch(symbol, 10.0));  //fetch from an API

//                                emitter.onError(new Throwable("error"));

                                emitter.onComplete();

                          })
                          .subscribe(stock -> System.out.println("got...: " + stock.toString()))
                          .dispose(); //callbacks to the emitted data..

        });

    }
}
