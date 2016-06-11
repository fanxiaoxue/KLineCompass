package com.example.thinking.klinecompass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class WelComActivity extends AppCompatActivity {

    private TextView oneTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏

        setContentView(R.layout.activity_wel_com);

        oneTv = (TextView)findViewById(R.id.oneTv);
        oneTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelComActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
