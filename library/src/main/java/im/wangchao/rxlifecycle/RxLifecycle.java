package im.wangchao.rxlifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.support.annotation.IntDef;
import android.support.annotation.RequiresApi;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * <p>Description  : RxLifecycle.</p>
 * <p>Author       : wangchao.</p>
 * <p>Date         : 2017/11/8.</p>
 * <p>Time         : 下午2:13.</p>
 */
public class RxLifecycle {
    public static final int EVENT_ON_CREATE = 1;
    public static final int EVENT_ON_START = EVENT_ON_CREATE + 1;
    public static final int EVENT_ON_RESUME = EVENT_ON_START + 1;
    public static final int EVENT_ON_PAUSE = EVENT_ON_RESUME + 1;
    public static final int EVENT_ON_STOP = EVENT_ON_PAUSE + 1;
    public static final int EVENT_ON_DESTROY = EVENT_ON_STOP + 1;

    @IntDef({
            EVENT_ON_CREATE,
            EVENT_ON_START,
            EVENT_ON_RESUME,
            EVENT_ON_PAUSE,
            EVENT_ON_STOP,
            EVENT_ON_DESTROY
    })
    public @interface Event{}

    private LifecycleObservable mObservable;

    private RxLifecycle(LifecycleObservable observable){
        mObservable = observable;
    }

    public static RxLifecycle with(Activity activity){
        return with(activity.getFragmentManager());
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static RxLifecycle with(Fragment fragment){
        return with(fragment.getChildFragmentManager());
    }

    public static RxLifecycle with(android.support.v4.app.Fragment fragment) {
        return new RxLifecycle(new LifecycleObservable(fragment.getLifecycle()));
    }

    public static RxLifecycle with(FragmentManager manager){
        return new RxLifecycle(LifecycleFragment.get(manager).getObservable());
    }

    public Observable<Integer> toObservable(){
        return mObservable.get();
    }

    public Flowable<Integer> toFlowable(BackpressureStrategy strategy){
        return mObservable.get().toFlowable(strategy);
    }

    public <T> LifecycleTransformer<T> bindUntilDestroy(){
        return bindUntilEvent(EVENT_ON_DESTROY);
    }

    public <T> LifecycleTransformer<T> bindUntilEvent(@Event int event){
        return new LifecycleTransformer<>(mObservable, event);
    }

}
