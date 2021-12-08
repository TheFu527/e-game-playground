package edu.neu.madcourse.numad21fa.egameplaygound.manager.storage;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

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

    public void uploadImage(StorageReference imageRef,String file_name) {
        UploadTask uploadTask = imageRef.putFile(Uri.parse(file_name));
        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Log.i("=========", "upload image to storage failure!");
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                Log.i("=========", "upload image to storage success!");
            }
        });
    }
}




