package edu.neu.madcourse.numad21fa.egameplaygound.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import edu.neu.madcourse.numad21fa.egameplaygound.R;
import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserGenderEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserLevelEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManager;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManagerImpl;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.TeamUpCardDTO;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.UserInfoDTO;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This is for example!!!! NOT production
        DatabaseManager dbManager = DatabaseManagerImpl.getInstance();
        UserInfoDTO user = new UserInfoDTO().setAvatarURI("AvatarURI")
                .setEmail("a@example.com")
                .setGender(UserGenderEnum.MALE)
                .setLevel(UserLevelEnum.MASTER)
                .setLocation("Beijing")
                .setUuid("uuid-uuid")
                .setName("Hao Fu");
        dbManager.insertUser(user);

        dbManager.insertTeamUpCard(new TeamUpCardDTO().setDescription("abcde")
                .setTimestamp("time")
                .setCreatorUser(user)
                .setLocation("beijing")
                .setUuid("uuid-uuid"));
        dbManager.insertTeamUpCard(new TeamUpCardDTO().setDescription("that's cool")
                .setTimestamp("time")
                .setCreatorUser(user)
                .setLocation("Jingbei")
                .setUuid("uuid-uuid-uuid"));

        Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
        startActivity(intent);

    }
}