package com.xika.iocdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xika.iocdemo.ico.OnClick;
import com.xika.iocdemo.ico.ViewById;
import com.xika.iocdemo.ico.ViewUtils;

public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.tv_hint)
    private TextView tv_hint;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mContext = this;
        ViewUtils.inject(MainActivity.this);
        tv_hint.setText("IOC测试");
    }

    @OnClick({R.id.btn_ioc_test, R.id.btn_ioc_test_again})
    private void onclick(View view) {
        switch (view.getId()) {
            case R.id.btn_ioc_test:
                Toast.makeText(mContext, "IOC按钮第一次测试", Toast.LENGTH_LONG).show();
                break;
            case R.id.btn_ioc_test_again:
                Toast.makeText(mContext, "IOC按钮第二次测试", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
