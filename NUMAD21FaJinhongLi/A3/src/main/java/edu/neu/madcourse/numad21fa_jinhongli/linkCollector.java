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
//                int pos = 0;
//                addItem(pos);
            }
        });

        //Specify what action a specific gesture performs, in this case swiping right or left deletes the entry
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//                Toast.makeText(linkCollector.this, "Delete an item", Toast.LENGTH_SHORT).show();
//                int position = viewHolder.getLayoutPosition();
//                itemList.remove(position);
//
//                rviewAdapter.notifyItemRemoved(position);
//
//            }
//        });
//        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RequestCode && resultCode == RESULT_OK) {
            String name = data.getStringExtra(enterLink.EXTRA_NAME);
            String link = data.getStringExtra(enterLink.EXTRA_LINK);

            ItemCard newCard = new ItemCard(name, link);
            itemList.add(0, newCard);

            Toast.makeText(this, "Link saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Link not saved", Toast.LENGTH_SHORT).show();
        }
    }
    // Handling Orientation Changes on Android
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        int size = itemList == null ? 0 : itemList.size();
        outState.putInt(NUMBER_OF_ITEMS, size);

        // Need to generate unique key for each item
        // This is only a possible way to do, please find your own way to generate the key
        for (int i = 0; i < size; i++) {
            // put image information id into instance
            // outState.putInt(KEY_OF_INSTANCE + i + "0", itemList.get(i).getImageSource());
            // put itemName information into instance
            outState.putString(KEY_OF_INSTANCE + i + "a", itemList.get(i).getItemName());
            // put itemDesc information into instance
            outState.putString(KEY_OF_INSTANCE + i + "b", itemList.get(i).getItemLink());
            // put isChecked information into instance
            //outState.putBoolean(KEY_OF_INSTANCE + i + "3", itemList.get(i).getStatus());
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
                    // Integer imgId = savedInstanceState.getInt(KEY_OF_INSTANCE + i + "0");
                    String itemName = savedInstanceState.getString(KEY_OF_INSTANCE + i + "a");
                    String itemLink = savedInstanceState.getString(KEY_OF_INSTANCE + i + "b");
                    //boolean isChecked = savedInstanceState.getBoolean(KEY_OF_INSTANCE + i + "3");

                    // We need to make sure names such as "XXX(checked)" will not duplicate
                    // Use a tricky way to solve this problem, not the best though
//                    if (isChecked) {
//                        itemName = itemName.substring(0, itemName.lastIndexOf("("));
//                    }
                    ItemCard itemCard = new ItemCard(itemName, itemLink);

                    itemList.add(itemCard);
                }
            }
        }
        // The first time to opne this Activity
//        else {
//            ItemCard item1 = new ItemCard(R.drawable.pic_gmail_01, "Gmail", "Example description", false);
//            ItemCard item2 = new ItemCard(R.drawable.pic_google_01, "Google", "Example description", false);
//            ItemCard item3 = new ItemCard(R.drawable.pic_youtube_01, "Youtube", "Example description", false);
//            itemList.add(item1);
//            itemList.add(item2);
//            itemList.add(item3);
//        }

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
                //attributions bond to the item has been changed
                itemList.get(position).onItemClick(position);
                Intent Link = new Intent(Intent.ACTION_VIEW, Uri.parse(itemList.get(position).getItemLink()));
                startActivity(Link);
                rviewAdapter.notifyItemChanged(position);
            }

        };
        rviewAdapter.setOnItemClickListener(itemClickListener);

        recyclerView.setAdapter(rviewAdapter);
        recyclerView.setLayoutManager(rLayoutManger);
    }

//    private void addItem(int position) {
//        itemList.add(position, new ItemCard("Name：", "URL: " + "Tap to enter"));
//        Toast.makeText(linkCollector.this, "Add an link", Toast.LENGTH_SHORT).show();
//
//        rviewAdapter.notifyItemInserted(position);
//    }
}