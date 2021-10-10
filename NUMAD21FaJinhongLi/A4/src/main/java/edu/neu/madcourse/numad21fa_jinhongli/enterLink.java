package edu.neu.madcourse.numad21fa_jinhongli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class enterLink extends AppCompatActivity {

    EditText URL;
    EditText Name;

    //public static final int RESULT_OK = -1;
    public static final String EXTRA_NAME =
            "numad21fa_jinhongli_name";
    public static final String EXTRA_LINK =
            "numad21fa_jinhongli_link";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterlink);
        URL = findViewById(R.id.URL_enter);
        Name = findViewById(R.id.name_enter);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close_icon);
        setTitle("Name & URL");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_link, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_link:
                saveLink();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveLink() {
        String name = Name.getText().toString();
        String link = URL.getText().toString();

        if (name.trim().isEmpty() || link.trim().isEmpty()) {
            Toast.makeText(this, "Insert a name and link", Toast.LENGTH_SHORT).show();
            return;
        }
// 将数据返回给唤起这个activity的main activity
        Intent data = new Intent();
        data.putExtra(EXTRA_NAME,name);
        data.putExtra(EXTRA_LINK,link);
        //data.putExtra(EXTRA_PRIORITY, priority);

        setResult(RESULT_OK, data);
        finish();
    }
}