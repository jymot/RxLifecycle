package im.wangchao.rxlifecycle;

import org.reactivestreams.Publisher;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.MaybeTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;

/**
 * <p>Description  : LifecycleTransformer.</p>
 * <p>Author       : wangchao.</p>
 * <p>Date         : 2017/11/28.</p>
 * <p>Time         : 下午12:50.</p>
 */
public class LifecycleTransformer<STREAM> implements ObservableTransformer<STREAM, STREAM>,
        FlowableTransformer<STREAM, STREAM>, SingleTransformer<STREAM, STREAM>,
        CompletableTransformer, MaybeTransformer<STREAM, STREAM> {

    private final LifecycleObservable mObservable;
    private final int mDisposeEvent;

    LifecycleTransformer(LifecycleObservable observable, @RxLifecycle.Event int disposeEvent){
        mObservable = observable;
        mDisposeEvent = disposeEvent;
    }

    @Override public ObservableSource<STREAM> apply(Observable<STREAM> upstream) {
        return upstream.takeUntil(mObservable.get().skipWhile(this::filter));
    }

    @Override public CompletableSource apply(Completable upstream) {
        return Completable.ambArray(upstream);
    }

    @Override public Publisher<STREAM> apply(Flowable<STREAM> upstream) {
        return upstream.takeUntil(mObservable.get().toFlowable(BackpressureStrategy.LATEST));
    }

    @Override public MaybeSource<STREAM> apply(Maybe<STREAM> upstream) {
        return upstream.takeUntil(mObservable.get().firstElement());
    }

    @Override public SingleSource<STREAM> apply(Single<STREAM> upstream) {
        return upstream.takeUntil(mObservable.get().firstOrError());
    }

    private boolean filter(int event){
        return event != mDisposeEvent;
    }
}
