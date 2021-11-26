package edu.neu.madcourse.numad21fa.egameplaygound.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import edu.neu.madcourse.numad21fa.egameplaygound.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
        startActivity(intent);

    }
}