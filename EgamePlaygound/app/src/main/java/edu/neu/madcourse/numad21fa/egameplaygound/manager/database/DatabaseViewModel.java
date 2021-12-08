package edu.neu.madcourse.numad21fa.egameplaygound.manager.database;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.TeamUpCardDTO;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.UserInfoDTO;

public class DatabaseViewModel extends ViewModel {
    private final LiveData<List<UserInfoDTO>> usersLiveData;
    private final LiveData<List<TeamUpCardDTO>> teamUpCardsLiveData;
    private final FirebaseQueryLiveData usersQueryLiveData;

    public DatabaseViewModel() {
        super();
        usersQueryLiveData = new FirebaseQueryLiveData(DatabaseManagerImpl.getInstance().getUsersRef());
        usersLiveData = Transformations.map(
                usersQueryLiveData,
                new UsersDeserializer());
        teamUpCardsLiveData = Transformations.map(
                new FirebaseQueryLiveData(DatabaseManagerImpl.getInstance().getTeamUpCardsRef()),
                new TeamUpCardsDeserializer());

    }

    @NonNull
    public LiveData<List<UserInfoDTO>> getUsersLiveData() {
        return usersLiveData;
    }

    @NonNull
    public LiveData<List<TeamUpCardDTO>> getTeamUpCardsLiveData() {
        return teamUpCardsLiveData;
    }

    @NonNull
    public LiveData<List<TeamUpCardDTO>> getTeamUpCardsLiveData(String uuid) {
        return Transformations.map(
                new FirebaseQueryLiveData(DatabaseManagerImpl.getInstance()
                        .getTeamUpCardsRef()
                        .orderByChild("creatorUser/uuid")
                        .equalTo(uuid)),
                new TeamUpCardsDeserializer());
    }

    @NonNull
    public LiveData<UserInfoDTO> getUserInfoLiveData(String uuid) {
        return Transformations.map(
                usersQueryLiveData,
                new UserInfoDeserializer(uuid));
    }

    private static class UsersDeserializer implements Function<DataSnapshot, List<UserInfoDTO>> {
        @Override
        public List<UserInfoDTO> apply(DataSnapshot dataSnapshot) {
            List<UserInfoDTO> modelList= new ArrayList<>();
            for(DataSnapshot ds : dataSnapshot.getChildren()) {
                modelList.add(ds.getValue(UserInfoDTO.class));
            }
            return modelList;
        }
    }

    private static class UserInfoDeserializer implements Function<DataSnapshot, UserInfoDTO> {

        private final String uuid;

        UserInfoDeserializer(String uuid) {
            this.uuid = uuid;
        }

        @Override
        public UserInfoDTO apply(DataSnapshot dataSnapshot) {
            return dataSnapshot.child(uuid).getValue(UserInfoDTO.class);
        }
    }

    private static class TeamUpCardsDeserializer implements Function<DataSnapshot, List<TeamUpCardDTO>> {
        @Override
        public List<TeamUpCardDTO> apply(DataSnapshot dataSnapshot) {
            List<TeamUpCardDTO> modelList= new ArrayList<>();
            for(DataSnapshot ds : dataSnapshot.getChildren()) {
                modelList.add(ds.getValue(TeamUpCardDTO.class));
            }
            return modelList;
        }
    }
}
