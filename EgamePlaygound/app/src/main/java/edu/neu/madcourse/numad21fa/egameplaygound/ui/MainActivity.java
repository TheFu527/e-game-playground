package edu.neu.madcourse.numad21fa.egameplaygound.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import java.net.URI;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.net.URI;
import java.net.URISyntaxException;

import edu.neu.madcourse.numad21fa.egameplaygound.R;
import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserGenderEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserLevelEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManager;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManagerImpl;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.TeamUpCardDTO;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.UserInfoDTO;
import edu.neu.madcourse.numad21fa.egameplaygound.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This is for example!!!! NOT production
//        DatabaseManager dbManager = DatabaseManagerImpl.getInstance();
//        UserInfoDTO user = new UserInfoDTO().setAvatarURI("AvatarURI")
//                .setEmail("a@example.com")
//                .setGender(UserGenderEnum.MALE)
//                .setLevel(UserLevelEnum.MASTER)
//                .setLocation("Beijing")
//                .setUuid("uuid-uuid")
//                .setName("Hao Fu");
//        dbManager.insertUser(user);
//        TeamUpCardDTO teamUpCardDTO = new TeamUpCardDTO().setDescription("abcde")
//                .setTimestamp("time")
//                .setCreatorUser(user)
//                .setLocation("beijing")
//                .setUuid("uuid-uuid");
//        dbManager.insertTeamUpCard(teamUpCardDTO);


        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);



    }
}