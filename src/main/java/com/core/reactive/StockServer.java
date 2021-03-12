package com.core.reactive;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.disposables.Disposable;;import java.util.Arrays;

public class StockServer {

//    public static Observable<Stock> getFeed(String[] symbols) {
//
//        System.out.println("created...");
//
//        return Observable.create(StockServer::emitPrice);
//    }
//
//    private static void emitPrice(ObservableEmitter<Stock> emitter) {
//        System.out.println("ready to emit...");
//
//    }

    public static void main(String[] args) {

        var symbols = Arrays.asList("GOOG", "AMZN", "TESLA");

        System.out.println("server started...");

        symbols.forEach(symbol -> {
                //create Observable
                //emit the price
                //subscribe to catch the price
            Observable.<Stock>create(emitter-> {
                                System.out.println("created...");

                                //emit the price
                                emitter.onNext(new Stock(symbol, 10.0));  //fetch from an API
                                emitter.onComplete();

                          })
                          .subscribe(stock -> System.out.println("got...: " + stock.toString()))
                          .dispose(); //callbacks to the emitted data..

        });
//
//        Observable<Stock> feed = StockServer.getFeed(symbols);
//
//        System.out.println("got observable..");
//
//        feed.subscribe(stock -> System.out.println(stock));

    }
}
