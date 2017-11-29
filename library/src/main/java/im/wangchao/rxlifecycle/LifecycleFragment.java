package im.wangchao.rxlifecycle;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * <p>Description  : LifecycleFragment.</p>
 * <p>Author       : wangchao.</p>
 * <p>Date         : 2017/11/8.</p>
 * <p>Time         : 下午2:14.</p>
 */
public class LifecycleFragment extends Fragment {

    private static final String LIFECYCLE_FRAGMENT_TAG = "RxLifecycle.LifecycleFragment.Tag";

    private LifecycleObservable mObservable = new LifecycleObservable();

    static LifecycleFragment get(FragmentManager fragmentManager){
        LifecycleFragment fragment = (LifecycleFragment) fragmentManager.findFragmentByTag(LIFECYCLE_FRAGMENT_TAG);
        if (fragment == null){
            fragmentManager.beginTransaction().add(fragment = new LifecycleFragment(), LIFECYCLE_FRAGMENT_TAG).commit();
            fragmentManager.executePendingTransactions();
        } else if (fragment.isDetached()){
            fragmentManager.beginTransaction().attach(fragment).commit();
        }

        return fragment;
    }

    LifecycleObservable getObservable(){
        return mObservable;
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        mObservable.onCreate();
        super.onActivityCreated(savedInstanceState);
    }

    @Override public void onStart() {
        mObservable.onStart();
        super.onStart();
    }

    @Override public void onResume() {
        mObservable.onResume();
        super.onResume();
    }

    @Override public void onPause() {
        mObservable.onPause();
        super.onPause();
    }

    @Override public void onStop() {
        mObservable.onStop();
        super.onStop();
    }

    @Override public void onDestroy() {
        mObservable.onDestroy();
        super.onDestroy();
    }
}
