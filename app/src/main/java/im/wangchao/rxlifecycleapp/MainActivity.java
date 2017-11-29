package im.wangchao.rxlifecycleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import im.wangchao.rxlifecycle.RxLifecycle;

public class MainActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.fragment).setOnClickListener(this::onClick);
        findViewById(R.id.v4fragment).setOnClickListener(this::onClick);

        RxLifecycle.with(this).toObservable().subscribe(integer -> Log.e("wcwcwc", "lifecycle: " + integer));
    }

    private void onClick(View v){
        final int id = v.getId();

        switch (id){
            case R.id.fragment:
                startActivity(new Intent(this, TestFragmentActivity.class));
                break;
            case R.id.v4fragment:
                startActivity(new Intent(this, TestV4FragmentActivity.class));
                break;
        }
    }
}
