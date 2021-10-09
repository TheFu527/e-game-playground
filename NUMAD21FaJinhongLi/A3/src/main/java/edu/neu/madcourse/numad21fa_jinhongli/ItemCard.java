package edu.neu.madcourse.numad21fa_jinhongli;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemCard implements ItemClickListener{
    // 储存数据
    private final String itemName;
    private final String itemLink;
    //private boolean isChecked;

    //Constructor
    public ItemCard(String itemName, String itemLink) {
        this.itemName = itemName;
        this.itemLink = itemLink;
        // 是否点击
        //this.isChecked = isChecked;
    }

    public String getItemLink() {
        return itemLink;
    }

    public String getItemName() {
        return itemName;
    }

//    public boolean getStatus() {
//        return isChecked;
//    }

    public void onItemClick(int position) {
        //isChecked = !isChecked;
        // 打开网址
    }

}