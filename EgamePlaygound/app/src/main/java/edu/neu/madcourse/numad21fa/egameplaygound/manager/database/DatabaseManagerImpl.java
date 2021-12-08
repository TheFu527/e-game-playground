package edu.neu.madcourse.numad21fa.egameplaygound.manager.database;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.TeamUpCardDTO;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.UserInfoDTO;

public class DatabaseManagerImpl implements DatabaseManager {
    private static final DatabaseManagerImpl instance = new DatabaseManagerImpl();
    private static final DatabaseReference USERS_REF =
            FirebaseDatabase.getInstance().getReference("/users");
    private static final DatabaseReference TEAM_UP_CARDS_REF =
            FirebaseDatabase.getInstance().getReference("/teamUpCards");

    private final FirebaseDatabase database;

    private DatabaseManagerImpl() {
        database = FirebaseDatabase.getInstance();
    }

    public static DatabaseManagerImpl getInstance() {
        return instance;
    }

    @Override
    public DatabaseReference getUsersRef() {
        return USERS_REF;
    }

    @Override
    public DatabaseReference getTeamUpCardsRef() {
        return TEAM_UP_CARDS_REF;
    }

    @Override
    public void insertUser(UserInfoDTO userInfo) {
        USERS_REF.child(userInfo.getUuid()).setValue(userInfo);
    }

    @Override
    public void insertTeamUpCard(TeamUpCardDTO teamUpCard) {
        TEAM_UP_CARDS_REF.child(teamUpCard.getUuid()).setValue(teamUpCard);
    }

    @Override
    public LiveData<List<TeamUpCardDTO>> getTeamUpCardList(ViewModelStoreOwner owner) {
        return new ViewModelProvider(owner).get(DatabaseViewModel.class).getTeamUpCardsLiveData();
    }

    @Override
    public LiveData<List<TeamUpCardDTO>> getTeamUpCardList(ViewModelStoreOwner owner, String uuid) {
        return new ViewModelProvider(owner).get(DatabaseViewModel.class).getTeamUpCardsLiveData(uuid);
    }

    @Override
    public LiveData<UserInfoDTO> getUserInfo(ViewModelStoreOwner owner, String uuid) {
        return new ViewModelProvider(owner).get(DatabaseViewModel.class).getUserInfoLiveData(uuid);
    }
}
