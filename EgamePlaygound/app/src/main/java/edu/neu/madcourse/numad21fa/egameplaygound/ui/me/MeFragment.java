package edu.neu.madcourse.numad21fa.egameplaygound.ui.me;

import static java.lang.Thread.sleep;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.neu.madcourse.numad21fa.egameplaygound.R;
import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserGenderEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserLevelEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.databinding.FragmentMeBinding;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.authentication.Authentication;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.authentication.AuthenticationImpl;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManager;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManagerImpl;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseViewModel;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.storage.StorageManager;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.storage.StorageManagerImpl;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.PiazzaCardDTO;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.TeamUpCardDTO;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.UserInfoDTO;
import edu.neu.madcourse.numad21fa.egameplaygound.ui.MainActivity;
import edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup.TeamUpCard;
import edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup.TeamUpFragment;

public class MeFragment extends Fragment {

    private MeViewModel meViewModel;
    private FragmentMeBinding binding;
    private  TextView teamupdesc;
    private TextView piazzadesc;
    private String uname;
    private UserInfoDTO user;
    private ImageView gender_icon;
    private ImageView level_icon;
    private TextView location;
    private  TextView level;
    String uuid;
    private ImageView meImage;

    private FirebaseAuth mAuth;
    private File currentImageFile = null;
    int MY_PERMISSIONS_WRITE_EXTERNAL_STORAGE;
    private DatabaseManager databaseManager;
    long[] time = {0};
    private StorageReference storageRef;
    private StorageManager storageManager;



    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        databaseManager = DatabaseManagerImpl.getInstance();
        storageManager = StorageManagerImpl.getInstance();


        meViewModel =
                new ViewModelProvider(this).get(MeViewModel.class);

        binding = FragmentMeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //get base time
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd H:m:s");
        Date max_time = null;
        try {
            max_time = format.parse("2010-03-01 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(max_time);
        time[0] = cal.getTimeInMillis();
        System.out.println(time[0]);


        //get uuid
        Authentication auth = AuthenticationImpl.getInstance();
        try {
            uuid = requireArguments().getString("uuid");
            Log.i("get arguments successsfully","wow");

        } catch(Exception e){
            uuid = auth.getUserID();
            Log.i("get arguments failed","wow");

        }


        //update user info
        meImage = (ImageView) binding.imageViewme;
        final TextView user_name = binding.usernameMe;
        gender_icon = binding.genderIcon;
        level = binding.userlevel;
        level_icon = binding.userLevelIcon;
        location = binding.location;
        databaseManager.getUserInfo(this,uuid).observe(getViewLifecycleOwner(), new Observer<UserInfoDTO>() {
            @Override
            public void onChanged(UserInfoDTO userInfoDTO) {
                String imageUri = userInfoDTO.getAvatarURI();
                Log.i("user image uri:",imageUri);
                String location_value = "Boston";
                location_value = userInfoDTO.getLocation();
                UserLevelEnum level_value = UserLevelEnum.UNKNOWN;
                level_value = userInfoDTO.getLevel();
                UserGenderEnum gender_value = UserGenderEnum.UNKNOWN;
                gender_value = userInfoDTO.getGender();
                String username = userInfoDTO.getName();

                StorageManager storageManager = StorageManagerImpl.getInstance();
                storageManager.loadImageIntoImageView(getContext(),imageUri,meImage);

                if(location_value != null) {
                    location.setText(location_value);
                    level.setText(level_value.toString());
                    level_icon.setImageResource(level_value.getIcon());
                    level_icon.setColorFilter(level_value.getColor());
                    gender_icon.setImageResource(gender_value.getIcon());
                    gender_icon.setColorFilter(gender_value.getColor());
                    user_name.setText(username);
                }

            }
        });

        //level image click
        level.setOnClickListener(v -> {
            if(uuid==auth.getUserID()){
                Bundle myCardBundle = new Bundle();
                myCardBundle.putString("uuid", uuid);
                NavHostFragment.findNavController(MeFragment.this)
                        .navigate(R.id.navigation_resetlevel, myCardBundle);
            }
            else
            {
                Toast.makeText(getContext(),"You can only change your own level",Toast.LENGTH_SHORT).show();
            }

        });

        //read newest teamup
        teamupdesc = (TextView) binding.teamupDesc;
        databaseManager.getTeamUpCardList(this,uuid).observe(getViewLifecycleOwner(), new Observer<List<TeamUpCardDTO>>() {
            @Override
            public void onChanged(List<TeamUpCardDTO> teamUpCardDTOS) {
                String Latest_des = "";
                int flag[] = {0};
                for (TeamUpCardDTO t : teamUpCardDTOS) {
                    Long tmp = t.getTimestamp();
                    flag[0] = 1;
                    if (time[0]<tmp) {
                        time[0] = tmp;
                        Latest_des = t.getDescription();
                    }
                }

                if(flag[0]==1){
                    teamupdesc.setText("Description: "+Latest_des+"\n"+"Publish timestamp:   "+transferLongToDate("yyyy-MM-dd HH:mm:ss",time[0]));

                    teamupdesc.setOnClickListener(new View.OnClickListener() {
                        //????????????button????????????
                        @Override
                        //??????onClick??????
                        public void onClick(View v) {
                            if(flag[0]==1){
                                Log.i("flag value:",String.valueOf(flag[0]));
                                Bundle myCardBundle = new Bundle();
                                myCardBundle.putString("uuid", uuid);
                                NavHostFragment.findNavController(MeFragment.this).navigate(R.id.navigation_user_teamup, myCardBundle);
                            }
                            else {
                                Toast.makeText(getContext(),"No teamup card till now!",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });
        teamupdesc.setOnClickListener(new View.OnClickListener() {
            //????????????button????????????
            @Override
            //??????onClick??????
            public void onClick(View v) {
                }
        });


        //read newest piazza
        piazzadesc = (TextView) binding.piazzaDesc;
        databaseManager.getPiazzaCardList(this,uuid).observe(getViewLifecycleOwner(), new Observer<List<PiazzaCardDTO>>() {
            @Override
            public void onChanged(List<PiazzaCardDTO> PiazzaCardDTOS) {
                String Latest_des = "";
                int flag[] = {0};
                for (PiazzaCardDTO t : PiazzaCardDTOS) {
                    Long tmp = t.getTimestamp();
                    flag[0] = 1;
                    if (time[0]<tmp) {
                        time[0] = tmp;
                        Latest_des = t.getContent();
                    }
                }

                if(flag[0]==1){
                    piazzadesc.setText("Context: "+Latest_des+"\n"+"Publish timestamp:   "+transferLongToDate("yyyy-MM-dd HH:mm:ss",time[0]));

                    piazzadesc.setOnClickListener(new View.OnClickListener() {
                        //????????????button????????????
                        @Override
                        //??????onClick??????
                        public void onClick(View v) {
                            if(flag[0]==1){
                                Log.i("flag value:",String.valueOf(flag[0]));
                                Bundle myCardBundle = new Bundle();
                                myCardBundle.putString("uuid", uuid);
                                NavHostFragment.findNavController(MeFragment.this).navigate(R.id.navigation_user_piazza, myCardBundle);
                            }
                            else {
                                Toast.makeText(getContext(),"No teamup card till now!",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });
        piazzadesc.setOnClickListener(new View.OnClickListener() {
            //????????????button????????????
            @Override
            //??????onClick??????
            public void onClick(View v) {
            }
        });


        //reset picture
        //for camera picture taken
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            builder.detectFileUriExposure();
        }

        meImage.setOnClickListener(new View.OnClickListener(){
            //????????????button????????????
            @Override
            //??????onClick??????
            public void onClick(View v){
                //?????????????????????????????????????????????????????????getExternalStoragePublicDirectory()
                //but???????????????api ????????????8?????????????????????getExternalStoragePublicDirectory()???
                //????????????Environment.getExternalStorageDirectory(),???????????????????????????????????????????????????????????????????????????????????????
                File dir = new File(Environment.getExternalStorageDirectory(),"pictures");
                //???????????????File newFile=new File(directory, filename)
                //????????????????????????????????????dir????????????????????????????????????????????????"pictures"???
                if(dir.exists())
                {
                    dir.mkdirs();//?????????????????????????????????????????????"pictures"
                }

                //??????????????????????????????
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


                    Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//??????intent?????????????????????
                    it.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(currentImageFile));

                    //?????????????????????????????????currentImageFile????????????
                    startActivityForResult(it, Activity.DEFAULT_KEYS_DIALER);
                    //????????????Activity??????????????????Activity ???????????????????????????
                    // ???????????????????????????startActivityForResult(Intent intent, int requestCode)??????
                    //????????????Activity?????????Activity ????????????????????????Activity?????????????????????????????????????????????
                    //??????????????????Activity?????????onActivityResult(int requestCode, int resultCode, Intent data)?????????

                }


            }
        });

        return root;
    }

    //??????onActivityResult(int requestCode, int resultCode, Intent data)??????
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == Activity.DEFAULT_KEYS_DIALER)
        {
            meImage.setImageURI(Uri.fromFile(currentImageFile));
            Log.i("image uri:",Uri.fromFile(currentImageFile).toString());

            //upload image to storage
            String StorageChild = String.valueOf(System.currentTimeMillis());
            Log.i("storage child:",StorageChild);
            storageManager.uploadImage( storageRef.child("user_image").child(StorageChild), String.valueOf(Uri.fromFile(currentImageFile)));

            //update avatar uri to realtime database/users
            DatabaseReference USERS_REF = FirebaseDatabase.getInstance().getReference("/users");
            USERS_REF.child(uuid).child("avatarURI").setValue(storageRef.child("user_image").child(StorageChild).toString());
            Log.i("reset user image succeed!","wow~");
            Toast.makeText(getActivity(),"reset user image successfully",Toast.LENGTH_SHORT).show();

            //reload image from realtime database
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            databaseManager.getUserInfo(this,uuid).observe(getViewLifecycleOwner(), new Observer<UserInfoDTO>() {
                @Override
                public void onChanged(UserInfoDTO userInfoDTO) {
                    String imageUri = userInfoDTO.getAvatarURI();
                    Log.i("user image uri:",imageUri);
                    storageManager.loadImageIntoImageView(getContext(),imageUri,meImage);
                }
            });
        }
    }

    public String transferLongToDate(String dateFormat, Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(millSec);
        return sdf.format(date);
        }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}