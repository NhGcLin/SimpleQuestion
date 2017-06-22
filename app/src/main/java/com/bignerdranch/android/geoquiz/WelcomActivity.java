package com.bignerdranch.android.geoquiz;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2017/4/24.
 */

public class WelcomActivity extends AppCompatActivity {
    private Button mStart;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mStart=(Button)findViewById(R.id.button_Start);
        Log.i("123", "onCreate: "+(mStart==null));
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomActivity.this,QuizActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
