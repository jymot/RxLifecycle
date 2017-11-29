package im.wangchao.rxlifecycleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TestFragmentActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fragment);

        getFragmentManager().beginTransaction()
                .replace(R.id.container, new TestFragment(), "fragment")
                .commit();
    }
}
