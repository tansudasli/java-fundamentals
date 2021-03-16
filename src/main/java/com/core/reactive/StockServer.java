package com.core.reactive;

import io.reactivex.rxjava3.core.*;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;


public class StockServer {

    private static void emitThings(ObservableEmitter<Stock> emitter, List<String> symbols) {

        //emit the Stock
        //symbols.forEach(symbol -> emitter.onNext(fetch(symbol)));
        int counter = 0;
        while (counter++ < 10) {
            symbols.stream()
                    .map(StockService::fetch)
                    .forEach(emitter::onNext);
        }

        emitter.onComplete();  //send complete signal
    }

    private static void subscribeThings(Stock stock) {
        //intercept and do some other staff, such as buy the stock
        if (stock.getPrice() < 35) {
            //unsubscribe, then buy, to do that we need to use anonymous new Observer<Stock>() { }
            //and, override methods!!! or another way ??
            System.out.println("BUY...");
        }

        //do something
        System.out.println("got...: " + stock.toString() + " " + Thread.currentThread());
    }


    private static void handleError(Throwable err) {
        System.out.println("ERROR..." + err);
    }

//    private static Observable<Stock> callBackup(List<String> symbols, Throwable throwable) {
//        System.out.println(throwable);
//
//        return symbols.stream().map(StockService::fetch);
//
//    }

    public static void main(String[] args) throws InterruptedException {

        var symbols = Arrays.asList("GOOG", "AMZN", "TESLA");

        System.out.println("server started...");

        //create Observable<Stock), emit - do something,
        //then, subscribe - to read data
        Observable.<Stock>create(emitter -> emitThings(emitter, symbols))
                  .subscribeOn(Schedulers.io())         //multi-thread capability
//todo: go on errors                 .onErrorResumeNext(throwable -> callBackup(symbols, throwable))
//todo: backpressure
                  .subscribe(StockServer::subscribeThings,                 //onNext
                             StockServer::handleError,  //onError channel
                             () -> System.out.println("DONE..."));         //onComplete channel


        Thread.sleep(10000);
    }




}
