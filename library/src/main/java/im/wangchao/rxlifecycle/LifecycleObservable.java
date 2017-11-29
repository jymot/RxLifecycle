package im.wangchao.rxlifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;

import io.reactivex.subjects.BehaviorSubject;

/**
 * <p>Description  : LifecycleObservable.</p>
 * <p>Author       : wangchao.</p>
 * <p>Date         : 2017/11/28.</p>
 * <p>Time         : 下午4:26.</p>
 */
/*package*/ class LifecycleObservable implements LifecycleObserver{

    private BehaviorSubject<Integer> mSubject = BehaviorSubject.create();
    private Lifecycle mLifecycle;

    LifecycleObservable(){

    }

    LifecycleObservable(Lifecycle lifecycle){
        mLifecycle = lifecycle;
        mLifecycle.addObserver(this);
    }

    BehaviorSubject<Integer> get(){
        return mSubject;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE) void onCreate(){
        mSubject.onNext(RxLifecycle.EVENT_ON_CREATE);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START) void onStart() {
        mSubject.onNext(RxLifecycle.EVENT_ON_START);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME) void onResume() {
        mSubject.onNext(RxLifecycle.EVENT_ON_RESUME);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE) void onPause() {
        mSubject.onNext(RxLifecycle.EVENT_ON_PAUSE);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP) void onStop() {
        mSubject.onNext(RxLifecycle.EVENT_ON_STOP);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY) void onDestroy() {
        mSubject.onNext(RxLifecycle.EVENT_ON_DESTROY);
        mSubject.onComplete();
        if (mLifecycle != null){
            mLifecycle.removeObserver(this);
        }
    }
}
