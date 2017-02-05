package com.qzj.facial.api;

import android.util.Log;

import com.orhanobut.hawk.Hawk;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxHelper {

    private static final Observable.Transformer schedulersTransformer = new  Observable.Transformer() {
        @Override public Object call(Object observable) {
            return ((Observable)  observable).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    @SuppressWarnings("unchecked")
    public static <T> Observable.Transformer<T, T> schedulers() {
        return (Observable.Transformer<T, T>) schedulersTransformer;
    }

    // 未解决区分缓存和网络数据问题
    @SuppressWarnings("unchecked")
    public static <T> Observable.Transformer<T, T> cache(final String cacheKey){
        return new Observable.Transformer<T,T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                Observable fromNetwork = (Observable)tObservable;
                fromNetwork = fromNetwork
                        .map(new Func1<Object, Object>() {
                            @Override
                            public Object call(Object result) {
                                Hawk.put(cacheKey, result);
                                return result;
                            }
                        });

                // 加载缓存数据
                Observable<T> fromCache = Observable.create(new Observable.OnSubscribe<T>() {
                    @Override
                    public void call(Subscriber<? super T> subscriber) {
                        T cache = Hawk.get(cacheKey);
                        if (cache != null) {
                            Log.e("cache", cache.toString());
                            subscriber.onNext(cache);
                        } else {
                            Log.e("cache", "null");
                            subscriber.onCompleted();
                        }
                    }
                });

                // 合并访问
                return Observable.concat(fromCache,fromNetwork)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
