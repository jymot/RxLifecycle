package im.wangchao.rxlifecycleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TestV4FragmentActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_v4_fragment);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new TestV4Fragment(), "v4fragment")
                .commit();
    }
}
