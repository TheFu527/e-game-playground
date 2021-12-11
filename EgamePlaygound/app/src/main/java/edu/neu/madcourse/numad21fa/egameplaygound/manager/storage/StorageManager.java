package edu.neu.madcourse.numad21fa.egameplaygound.manager.storage;

import android.content.Context;
import android.widget.ImageView;

import com.google.firebase.storage.StorageReference;

public interface StorageManager {
    void loadImageIntoImageView(Context context,
                                StorageReference imageRef,
                                ImageView imageView);
    void loadImageIntoImageView(Context context,
                                String imageRef,
                                ImageView imageView);
    void uploadImage(
                    StorageReference imageRef,
                    String file_name);
}

