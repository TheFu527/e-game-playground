package edu.neu.madcourse.numad21fa_jinhongli;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 先对控件进行声明
    private Button btm_about;
    private Button btm_clicky;
    private TextView textView;
    private TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //启动程序
        super.onCreate(savedInstanceState);
        //初始化界面,使用activity_main.xml 布局
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
//        textView.setText("Hello");
//        text1 = new TextView(this);
//        text1.setText("world");
//        text1.setId(R.id.text1 );
//        ConstraintLayout myCL = new ConstraintLayout(this);
//        setContentView(myCL);
        btm_about = findViewById(R.id.btm_about1);
        //按钮按下触发操作
        btm_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Jinhong Li, li.jinh@northeastern.edu", Toast.LENGTH_LONG).show();
            }
        });
        btm_clicky = findViewById(R.id.btm_clicky);
        //按钮按下触发操作
        btm_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clicker = new Intent(MainActivity.this,clickActivity.class);
                startActivity(clicker);
            }
        });
    }
}