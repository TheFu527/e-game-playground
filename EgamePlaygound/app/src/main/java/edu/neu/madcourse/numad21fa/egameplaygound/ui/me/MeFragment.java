package edu.neu.madcourse.numad21fa.egameplaygound.ui.me;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.fragment.NavHostFragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.neu.madcourse.numad21fa.egameplaygound.R;
import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserLevelEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.databinding.FragmentMeBinding;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.authentication.Authentication;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.authentication.AuthenticationImpl;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManager;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManagerImpl;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseViewModel;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.storage.StorageManager;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.storage.StorageManagerImpl;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.TeamUpCardDTO;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.UserInfoDTO;
import edu.neu.madcourse.numad21fa.egameplaygound.ui.MainActivity;
import edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup.TeamUpCard;
import edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup.TeamUpFragment;

public class MeFragment extends Fragment {

    private MeViewModel meViewModel;
    private FragmentMeBinding binding;
    private Button setlevel;
    private  TextView teamupdesc;
    private String uname;
    String uuid;
    private ImageView meImage;
    private Spinner reset_level_spinner;
    private FirebaseAuth mAuth;
    final UserLevelEnum[] levelSelected = {UserLevelEnum.SILVER};
    private File currentImageFile = null;
    int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE;
    private DatabaseManager databaseManager;



    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        StorageManager storageManager = StorageManagerImpl.getInstance();
        databaseManager = DatabaseManagerImpl.getInstance();

        meViewModel =
                new ViewModelProvider(this).get(MeViewModel.class);

        binding = FragmentMeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        //get userinfo
        Authentication auth = AuthenticationImpl.getInstance();
        String email = auth.getUser().getEmail();
        uname = "";
        if (email!=null){
            uname  = email.split("@")[0];
        }
        Log.i("uname:",uname);
        uuid = auth.getUserID();
        Log.i("uuid:",uuid);

        //set username
        final TextView user_name = binding.usernameMe;
        meViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                user_name.setText(uname);
            }
        });


        //get level
        reset_level_spinner = (Spinner) binding.resetLevelSpinner;
        List<UserLevelEnum> levelList = Arrays.asList(UserLevelEnum.MASTER,UserLevelEnum.GOLD,UserLevelEnum.SILVER,UserLevelEnum.UNKNOWN);

        ArrayAdapter<UserLevelEnum> resetLevelAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item,UserLevelEnum.values());
        reset_level_spinner.setAdapter(resetLevelAdapter);

        reset_level_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                levelSelected[0] = levelList.get(i);
                //set level
                setlevel = (Button) binding.resetLevel;
                setlevel.setOnClickListener(new View.OnClickListener(){
                    //为找到的button设置监听
                    @Override
                    //重写onClick函数
                    public void onClick(View v){
                        DatabaseReference USERS_REF = FirebaseDatabase.getInstance().getReference("/users");
                        Log.i("level got:",USERS_REF.child(uuid).child("level").getKey());
                        USERS_REF.child(uuid).child("level").setValue(levelSelected[0]);
//                        Map<String,Object> childUpdates = new HashMap<>();
//                        Map<String, UserLevelEnum> postValues;
//                        postValues = new HashMap<>();
//                        postValues.put("level", levelSelected[0]);
//                        childUpdates.put(uuid,childUpdates);
//                        USERS_REF.updateChildren(childUpdates);

                        Log.i("reset level succeed!","wow~");
                        Toast.makeText(getActivity(),"reset level successfully",Toast.LENGTH_SHORT).show();

                    }
                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        Log.i("selected level:",levelSelected[0].toString());


        //read newest teamup
        databaseManager.getTeamUpCardList(this,uuid).observe(getViewLifecycleOwner(), new Observer<List<TeamUpCardDTO>>() {
            @Override
            public void onChanged(List<TeamUpCardDTO> teamUpCardDTOS) {
                String max_time = "2010-03-01 00:00:00";
                String Latest_des = "";
                int flag = 0;
                for (TeamUpCardDTO t : teamUpCardDTOS) {
                    String tmp = t.getTimestamp();
                    flag = 1;
                    if (max_time.compareTo(tmp)<0) {
                        max_time = tmp;
                        Latest_des = t.getDescription();
                    }
                }

                if(flag==1){
                    teamupdesc = (TextView) binding.teamupDesc;
                    teamupdesc.setText(" Description: "+Latest_des+"\n"+"Publish time:   "+max_time);
                    //

                    teamupdesc.setOnClickListener(new View.OnClickListener() {
                        //为找到的button设置监听
                        @Override
                        //重写onClick函数
                        public void onClick(View v) {
                            Bundle myCardBundle = new Bundle();
                            myCardBundle.putString("uuid", uuid);
                            NavHostFragment.findNavController(MeFragment.this).navigate(R.id.navigation_user_teamup, myCardBundle);
                        }
                    });
                }
            }
        });








        //reset picture
        //for camera picture taken
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            builder.detectFileUriExposure();
        }

        meImage = (ImageView) binding.imageViewme;
        meImage.setOnClickListener(new View.OnClickListener(){
            //为找到的button设置监听
            @Override
            //重写onClick函数
            public void onClick(View v){
                //如果你想在外存储上放公共文件你可以使用getExternalStoragePublicDirectory()
                //but！如果你的api 版本低于8，那么不能使用getExternalStoragePublicDirectory()，
                //而是使用Environment.getExternalStorageDirectory(),不带参数，不能自己创建一个目录，只是返回外部存储的根路径。
                File dir = new File(Environment.getExternalStorageDirectory(),"pictures");
                //函数原型：File newFile=new File(directory, filename)
                //创建了一个文件夹，名字是dir，路径是外部存储的根路径，名字是"pictures"。
                if(dir.exists())
                {
                    dir.mkdirs();//在根路径下建子目录，子目录名是"pictures"
                }

                //命名临时图片的文件名
                currentImageFile = new File(dir,System.currentTimeMillis() + ".jpg");
                if(!currentImageFile.exists())
                {
                    try
                    {
                        currentImageFile.createNewFile();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }

                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
                    // Permission is not granted
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        // Show an explanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.

                    } else {
                        // No explanation needed; request the permission
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE);
                    }
                }
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED) {


                    Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//通过intent调用照相机照相
                    it.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(currentImageFile));

                    //存到外部存储的临时文件currentImageFile的路径下
                    startActivityForResult(it, Activity.DEFAULT_KEYS_DIALER);
                    //如果想在Activity中得到新打开Activity 关闭后返回的数据，
                    // 需要使用系统提供的startActivityForResult(Intent intent, int requestCode)方法
                    //打开新的Activity，新的Activity 关闭后会向前面的Activity传回数据，为了得到传回的数据，
                    //必须在前面的Activity中重写onActivityResult(int requestCode, int resultCode, Intent data)方法。

                }


            }
        });

        return root;
    }

    //重写onActivityResult(int requestCode, int resultCode, Intent data)方法
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == Activity.DEFAULT_KEYS_DIALER)
        {
            meImage.setImageURI(Uri.fromFile(currentImageFile));
            Log.i("image uri:",Uri.fromFile(currentImageFile).toString());
            StorageManager storageManager = StorageManagerImpl.getInstance();
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();

            //upload image to storage
            String StorageChild = String.valueOf(System.currentTimeMillis());
            Log.i("storage child:",StorageChild);
            storageManager.uploadImage( storageRef.child("user_image").child(StorageChild), String.valueOf(Uri.fromFile(currentImageFile)));

            //update avatar uri to realtime database/users
            DatabaseReference USERS_REF = FirebaseDatabase.getInstance().getReference("/users");
            USERS_REF.child(uuid).child("avatarURI").setValue(storageRef.child("user_image").child(StorageChild).toString());
            Log.i("reset user image succeed!","wow~");
            Toast.makeText(getActivity(),"reset user image successfully",Toast.LENGTH_SHORT).show();


        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}