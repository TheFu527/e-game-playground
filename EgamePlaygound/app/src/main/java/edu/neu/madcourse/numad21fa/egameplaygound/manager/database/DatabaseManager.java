package edu.neu.madcourse.numad21fa.egameplaygound.manager.database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelStoreOwner;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.PiazzaCardDTO;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.TeamUpCardDTO;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.UserInfoDTO;

public interface DatabaseManager {
    DatabaseReference getUsersRef();
    DatabaseReference getTeamUpCardsRef();
    DatabaseReference getPiazzaCardsRef();
    void insertUser(UserInfoDTO userInfo);
    void insertTeamUpCard(TeamUpCardDTO teamUpCard);
    void insertPiazzaCard(PiazzaCardDTO piazzaCardDTO);
    LiveData<List<TeamUpCardDTO>> getTeamUpCardList(ViewModelStoreOwner owner);
    LiveData<List<PiazzaCardDTO>> getPiazzaCardList(ViewModelStoreOwner owner);
    LiveData<List<TeamUpCardDTO>> getTeamUpCardList(ViewModelStoreOwner owner, String uuid);
    LiveData<UserInfoDTO> getUserInfo(ViewModelStoreOwner owner, String uuid);

}
