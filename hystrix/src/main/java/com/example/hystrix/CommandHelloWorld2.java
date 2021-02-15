package com.example.hystrix;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Schedulers;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class CommandHelloWorld2 extends HystrixObservableCommand<String> {
    private final String name;

    public CommandHelloWorld2(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected Observable<String> construct() {
        return Observable.create(new Observable.OnSubscribe<String>() {
            public void call(Subscriber<? super String> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        observer.onNext("Hello " + name + "!");
                        //observer.onNext(name);
                        //observer.onNext("!");
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        }).subscribeOn(Schedulers.io());
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Observable<String> observable = new CommandHelloWorld2("Chaos").construct();
        Future<String> string = observable.toBlocking().toFuture();
        System.out.println(string.get());
    }
}
