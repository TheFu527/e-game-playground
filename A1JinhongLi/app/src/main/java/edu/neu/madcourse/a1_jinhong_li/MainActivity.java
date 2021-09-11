package edu.neu.madcourse.a1_jinhong_li;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void myClick(View view)
    {
        Toast.makeText(MainActivity.this,"Jinhong li.jinh@neu.edu",Toast.LENGTH_SHORT).show();
    }
}