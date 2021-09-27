package edu.neu.madcourse.numad21fa_jinhongli;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
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
        ConstraintLayout clickLayout = findViewById(R.id.clickact);
        A.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        handleTouch(motionEvent,"A");
                        return true;
                    }
                }
        );
        B.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        handleTouch(motionEvent,"B");
                        return true;
                    }
                }
        );
        C.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        handleTouch(motionEvent,"C");
                        return true;
                    }
                }
        );
        D.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        handleTouch(motionEvent,"D");
                        return true;
                    }
                }
        );
        E.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        handleTouch(motionEvent,"E");
                        return true;
                    }
                }
        );
        F.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        handleTouch(motionEvent,"F");
                        return true;
                    }
                }
        );
    }

    void handleTouch(MotionEvent m,String buttom) {
        int pointerCount = m.getPointerCount();
        String actionString;
        int action = m.getActionMasked();
        switch (action)
        {
            case MotionEvent.ACTION_DOWN:
                actionString = "DOWN";
                textView.setText("Pressed: "+ buttom);
                break;
            case MotionEvent.ACTION_UP:
                actionString = "UP";
                textView.setText("Pressed: ");
                break;}
    }
}