package im.wangchao.rxlifecycleapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import im.wangchao.rxlifecycle.RxLifecycle;
import io.reactivex.Observable;

/**
 * <p>Description  : TestV4Fragment.</p>
 * <p>Author       : wangchao.</p>
 * <p>Date         : 2017/11/29.</p>
 * <p>Time         : 下午1:17.</p>
 */
public class TestV4Fragment extends Fragment {

    @Nullable
    @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(inflater.getContext());
        textView.setText("Test V4 Fragment");
        return textView;
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Observable.interval(1, TimeUnit.SECONDS)
                .compose(RxLifecycle.with(this).bindUntilDestroy())
                .subscribe(i -> Log.e("wcwcwc", "v4 interval: " + i));
    }
}
