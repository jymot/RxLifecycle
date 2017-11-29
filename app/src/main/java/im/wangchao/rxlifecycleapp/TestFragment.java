package im.wangchao.rxlifecycleapp;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import im.wangchao.rxlifecycle.RxLifecycle;
import io.reactivex.Observable;

/**
 * <p>Description  : TestFragment.</p>
 * <p>Author       : wangchao.</p>
 * <p>Date         : 2017/11/29.</p>
 * <p>Time         : 下午1:17.</p>
 */
public class TestFragment extends Fragment {

    @Nullable
    @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(inflater.getContext());
        textView.setText("Test Fragment");
        return textView;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Observable.interval(1, TimeUnit.SECONDS)
                .compose(RxLifecycle.with(this).bindUntilDestroy())
                .subscribe(i -> Log.e("wcwcwc", "interval: " + i));
    }
}
