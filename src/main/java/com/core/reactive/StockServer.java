package com.core.reactive;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;

import java.util.Arrays;
import java.util.List;


public class StockServer {

    private static void process(ObservableEmitter<Stock> emitter, List<String> symbols) {

        //emit the Stock
        //symbols.forEach(symbol -> emitter.onNext(fetch(symbol)));
        symbols.stream()
               .map(StockService::fetch)
               .forEach(emitter::onNext);

        emitter.onComplete();  //send complete signal
    }

    public static void main(String[] args) {

        var symbols = Arrays.asList("GOOG", "AMZN", "TESLA");

        System.out.println("server started...");

        //create Observable, emit - do something, till it finishes, subscribe - to read data
        Observable.<Stock>create(emitter -> process(emitter, symbols))
                  .subscribe(stock -> {
                                //intercept and do some other staff, such as buy the stock
                                if (stock.getPrice() < 35) {
                                    //unsubscribe, then buy, to do that we need to use anonymous new Observer<Stock>() { }
                                    //and, override methods!!! or another way ??
                                    System.out.println("BUY...");
                                }

                                //do something
                                System.out.println("got...: " + stock.toString());
                             }, //onNext
                             err -> System.out.println("ERROR..."),  //onError channel
                             () -> System.out.println("DONE..."))             //onComplete channel
                  .dispose();  //unsubscribe

    }
}
