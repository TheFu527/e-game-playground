package edu.neu.madcourse.numad21fa_jinhongli;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class ItemCard implements ItemClickListener{
    // 储存数据
    private final String itemName;
    private final String itemLink;
    private Context context; //运行上下文

    //Constructor
    public ItemCard(String itemName, String itemLink) {
        this.itemName = itemName;
        this.itemLink = itemLink;
    }

    public String getItemLink() {
        return itemLink;
    }

    public String getItemName() {
        return itemName;
    }

    @Override
    public void onItemClick(int position) {
        // isChecked = !isChecked;
        Intent Link = new Intent(Intent.ACTION_VIEW, Uri.parse(itemLink));
        context.startActivity(Link);
    }


}