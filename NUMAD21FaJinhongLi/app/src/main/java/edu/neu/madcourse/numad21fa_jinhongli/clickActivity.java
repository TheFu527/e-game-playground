package edu.neu.madcourse.numad21fa_jinhongli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class clickActivity extends AppCompatActivity {

    private Button A;
    private Button B;
    private Button C;
    private Button D;
    private Button E;
    private Button F;
    private TextView textView;
    private TextView text1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);
        textView = findViewById(R.id.textView);
        A = findViewById(R.id.btm_a);
        B = findViewById(R.id.btm_b);
        C = findViewById(R.id.btm_c);
        D = findViewById(R.id.btm_d);
        E = findViewById(R.id.btm_e);
        F = findViewById(R.id.btm_f);
        //A.setOnTouchListener();
    }
}