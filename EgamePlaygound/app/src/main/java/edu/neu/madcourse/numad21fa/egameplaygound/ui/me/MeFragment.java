package edu.neu.madcourse.numad21fa.egameplaygound.ui.me;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import edu.neu.madcourse.numad21fa.egameplaygound.R;
import edu.neu.madcourse.numad21fa.egameplaygound.databinding.FragmentMeBinding;

public class MeFragment extends Fragment {

    private MeViewModel meViewModel;
    private FragmentMeBinding binding;
    private Button setlevel;
    private FirebaseAuth mAuth;






    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        meViewModel =
                new ViewModelProvider(this).get(MeViewModel.class);

        binding = FragmentMeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView user_name = binding.usernameMe;
        meViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                user_name.setText(s);
            }
        });

//        ivHead = (ImageView) binding.imageViewme;
        setlevel = (Button) binding.resetLevel;

        //        File path = Environment.getExternalStorageDirectory();
//        String path = "/Downloads/";//sd路径
//
//        Log.e("path:",path.toString());
//
//        Bitmap bt = BitmapFactory.decodeFile(path+"赛事.png");
//        if(bt!=null){
//            ivHead.setImageBitmap(bt);
//            Log.i("file path right!","check");
//
//        }
//        else{
//            Log.i("file path wrong!","check");
//        }




        return root;
    }






    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}