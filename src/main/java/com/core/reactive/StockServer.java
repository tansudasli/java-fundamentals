package com.core.reactive;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;


public class StockServer {

    private static void emitThings(ObservableEmitter<Stock> emitter, List<String> symbols) {

        //emit the Stock
        //symbols.forEach(symbol -> emitter.onNext(fetch(symbol)));
        symbols.stream()
               .map(StockService::fetch)
               .forEach(emitter::onNext);

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

    public static void main(String[] args) throws InterruptedException {

        var symbols = Arrays.asList("GOOG", "AMZN", "TESLA");

        System.out.println("server started...");

        //create Observable<Stock), emit - do something,
        //then, subscribe - to read data
        Observable.<Stock>create(emitter -> emitThings(emitter, symbols))
                  .subscribeOn(Schedulers.io())         //multi-thread capability
                  .subscribe(StockServer::subscribeThings,                 //onNext
                             err -> System.out.println("ERROR..." + err),  //onError channel
                             () -> System.out.println("DONE..."));         //onComplete channel


        Thread.sleep(10000);
    }

}
