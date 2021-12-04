package edu.neu.madcourse.numad21fa.egameplaygound.manager.storage;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.StorageReference;

public class StorageManagerImpl implements StorageManager {

    @Override
    public void loadImageIntoImageView(Context context, StorageReference imageRef, ImageView imageView) {
        Glide.with(context)
                .load(imageRef)
                .into(imageView);
    }
}
