package edu.neu.madcourse.numad21fa.egameplaygound.manager.storage;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class StorageManagerImpl implements StorageManager {
    private static final StorageManagerImpl instance = new StorageManagerImpl();
    private final FirebaseStorage storage;

    private StorageManagerImpl() {
        storage = FirebaseStorage.getInstance();
    }

    public static StorageManagerImpl getInstance() {
        return instance;
    }


    @Override
    public void loadImageIntoImageView(Context context, StorageReference imageRef, ImageView imageView) {
        Glide.with(context)
                .load(imageRef)
                .into(imageView);
    }

    @Override
    public void loadImageIntoImageView(Context context, String imageRef, ImageView imageView) {
        Glide.with(context)
                .load(imageRef)
                .into(imageView);
    }
}
