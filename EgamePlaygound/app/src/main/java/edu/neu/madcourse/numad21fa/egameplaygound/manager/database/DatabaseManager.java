package edu.neu.madcourse.numad21fa.egameplaygound.manager.database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelStoreOwner;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.TeamUpCardDTO;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.UserInfoDTO;

public interface DatabaseManager {
    DatabaseReference getUsersRef();
    DatabaseReference getTeamUpCardsRef();
    void insertUser(UserInfoDTO userInfo);
    void insertTeamUpCard(TeamUpCardDTO teamUpCard);
    LiveData<List<TeamUpCardDTO>> getTeamUpCardList(ViewModelStoreOwner owner);
    LiveData<List<TeamUpCardDTO>> getTeamUpCardList(ViewModelStoreOwner owner, String uuid);
    LiveData<UserInfoDTO> getUserInfo(ViewModelStoreOwner owner, String uuid);

}
