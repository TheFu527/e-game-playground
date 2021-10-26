package edu.neu.madcourse.numad21fa_jinhongli;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import edu.neu.madcourse.numad21fa_jinhongli.ItemCard;
import edu.neu.madcourse.numad21fa_jinhongli.RviewAdapter;

public class linkCollector extends AppCompatActivity {
    //Creating the essential parts needed for a Recycler view to work: RecyclerView, Adapter, LayoutManager
    private ArrayList<ItemCard> itemList = new ArrayList<>();

    private RecyclerView recyclerView;
    private RviewAdapter rviewAdapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private FloatingActionButton addButton;

    private static final String KEY_OF_INSTANCE = "MatchingKey";
    private static final String NUMBER_OF_ITEMS = "Link NUMBERS";

    public static final int RequestCode= 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link);

        init(savedInstanceState);

        addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Enterlink = new Intent(linkCollector.this,enterLink.class);
                startActivityForResult(Enterlink,RequestCode);
            }
        });
    }

    // 用于接收enterLink输入的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RequestCode && resultCode == RESULT_OK) {
            String name = data.getStringExtra(enterLink.EXTRA_NAME);
            String link = data.getStringExtra(enterLink.EXTRA_LINK);
            ItemCard newCard = new ItemCard(name, link);
//            itemList.add(0, newCard);
//            rviewAdapter.notifyItemInserted(0);
            if (isValidURL(link) == false) {
                Snackbar.make(addButton,"Invalid URL, please re-enter",Snackbar.LENGTH_SHORT).show();
            } else {
                itemList.add(0, newCard);
                rviewAdapter.notifyItemInserted(0);
                Snackbar.make(addButton, "Add succeeded", Snackbar.LENGTH_SHORT).show();
            }
            //Toast.makeText(this, "Link saved", Toast.LENGTH_SHORT).show();
        } else {
            Snackbar.make(addButton,"Add failed",Snackbar.LENGTH_SHORT).show();
        }
    }

    public boolean isValidURL(String url) {
        try {
            new URL(url).toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            return false;
        }

        return true;
    }

    // Handling Orientation Changes on Android
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        int size = itemList == null ? 0 : itemList.size();
        outState.putInt(NUMBER_OF_ITEMS, size);

        // Need to generate unique key for each item
        // This is only a possible way to do, please find your own way to generate the key
        for (int i = 0; i < size; i++) {
            // put name information into instance
            outState.putString(KEY_OF_INSTANCE + i + "a", itemList.get(i).getItemName());
            // put link information into instance
            outState.putString(KEY_OF_INSTANCE + i + "b", itemList.get(i).getItemLink());
        }
        super.onSaveInstanceState(outState);
    }

    private void init(Bundle savedInstanceState) {

        initialItemData(savedInstanceState);
        createRecyclerView();
    }

    private void initialItemData(Bundle savedInstanceState) {

        // Not the first time to open this Activity
        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_ITEMS)) {
            if (itemList == null || itemList.size() == 0) {
                int size = savedInstanceState.getInt(NUMBER_OF_ITEMS);
                // Retrieve keys we stored in the instance
                for (int i = 0; i < size; i++) {
                    String itemName = savedInstanceState.getString(KEY_OF_INSTANCE + i + "a");
                    String itemLink = savedInstanceState.getString(KEY_OF_INSTANCE + i + "b");

                    ItemCard itemCard = new ItemCard(itemName, itemLink);
                    itemList.add(itemCard);
                }
            }
        }
    }

    private void createRecyclerView() {

        rLayoutManger = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        rviewAdapter = new RviewAdapter(itemList);
        //ItemCard itemClickListener = new ItemCard() {
        // assign clicklistener to adapter
        ItemClickListener itemClickListener = new ItemClickListener() {
            public void onItemClick(int position) {
                // 对列表中对应的行项进行点击操作
                //itemList.get(position).onItemClick(position);
                Intent Link = new Intent(Intent.ACTION_VIEW, Uri.parse(itemList.get(position).getItemLink()));
                startActivity(Link);
                //rviewAdapter.notifyItemChanged(position);
            }

        };
        rviewAdapter.setOnItemClickListener(itemClickListener);

        recyclerView.setAdapter(rviewAdapter);
        recyclerView.setLayoutManager(rLayoutManger);
    }

}