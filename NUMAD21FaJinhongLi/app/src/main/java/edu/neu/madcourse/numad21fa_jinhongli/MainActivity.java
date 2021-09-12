package edu.neu.madcourse.numad21fa_jinhongli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btm_about;
// 先进行声明
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        btm_about = findViewById(R.id.btm_about);
        //按钮按下触发操作
        btm_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Jinhong Li, li.jinh@northeastern.edu", Toast.LENGTH_LONG).show();
            }
        });
    }
}