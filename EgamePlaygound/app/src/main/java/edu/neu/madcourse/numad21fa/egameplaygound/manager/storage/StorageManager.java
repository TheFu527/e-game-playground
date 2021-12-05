package edu.neu.madcourse.numad21fa.egameplaygound.manager.storage;

import android.content.Context;
import android.widget.ImageView;

import com.google.firebase.storage.StorageReference;

public interface StorageManager {
    void loadImageIntoImageView(Context context,
                                  StorageReference imageRef,
                                  ImageView imageView);
}