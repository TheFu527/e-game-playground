package edu.neu.madcourse.numad21fa.egameplaygound.manager.database;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.PiazzaCardDTO;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.TeamUpCardDTO;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.EventCardDTO;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.UserInfoDTO;

public class DatabaseViewModel extends ViewModel {
    private final LiveData<List<UserInfoDTO>> usersLiveData;
    private final LiveData<List<EventCardDTO>> eventCardsLiveData;
    private final LiveData<List<TeamUpCardDTO>> teamUpCardsLiveData;
    private final LiveData<List<PiazzaCardDTO>> piazzaCardsLiveData;
    private final FirebaseQueryLiveData usersQueryLiveData;

    public DatabaseViewModel() {
        super();
        usersQueryLiveData = new FirebaseQueryLiveData(DatabaseManagerImpl.getInstance().getUsersRef());
        usersLiveData = Transformations.map(
                usersQueryLiveData,
                new UsersDeserializer());
        teamUpCardsLiveData = Transformations.map(
                new FirebaseQueryLiveData(DatabaseManagerImpl.getInstance()
                        .getTeamUpCardsRef()
                        .orderByChild("timestamp")),
                new TeamUpCardsDeserializer());
        piazzaCardsLiveData = Transformations.map(
                new FirebaseQueryLiveData(DatabaseManagerImpl.getInstance().getPiazzaCardsRef()),
                new PiazzaCardsDeserializer());
        eventCardsLiveData = Transformations.map(
                new FirebaseQueryLiveData(DatabaseManagerImpl.getInstance().getEventCardsRef()),
                new EventCardsDeserializer());
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
    public LiveData<List<PiazzaCardDTO>> getPiazzaCardsLiveData() {
        return piazzaCardsLiveData;
    }

    @NonNull
    public LiveData<List<EventCardDTO>> getEventCardsLiveData() {
        return eventCardsLiveData;
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
    public LiveData<List<PiazzaCardDTO>> getPiazzaCardsLiveData(String uuid) {
        return Transformations.map(
                new FirebaseQueryLiveData(DatabaseManagerImpl.getInstance()
                        .getPiazzaCardsRef()
                        .orderByChild("creatorUser/uuid")
                        .equalTo(uuid)),
                new PiazzaCardsDeserializer());
    }

    @NonNull
    public LiveData<List<EventCardDTO>> getEventCardsLiveData(String uuid) {
        return Transformations.map(
                new FirebaseQueryLiveData(DatabaseManagerImpl.getInstance()
                        .getEventCardsRef()
                        .orderByChild("creatorUser/uuid")
                        .equalTo(uuid)),
                new EventCardsDeserializer());    }

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
            Collections.reverse(modelList);
            return modelList;
        }
    }

    private static class PiazzaCardsDeserializer implements  Function<DataSnapshot, List<PiazzaCardDTO>> {
        @Override
        public List<PiazzaCardDTO> apply(DataSnapshot dataSnapshot) {
            List<PiazzaCardDTO> modelList= new ArrayList<>();
            for(DataSnapshot ds : dataSnapshot.getChildren()) {
                modelList.add(ds.getValue(PiazzaCardDTO.class));
            }
            Collections.reverse(modelList);
            return modelList;
        }
    }

    private static class EventCardsDeserializer implements  Function<DataSnapshot, List<EventCardDTO>> {
        @Override
        public List<EventCardDTO> apply(DataSnapshot dataSnapshot) {
            List<EventCardDTO> modelList= new ArrayList<>();
            for(DataSnapshot ds : dataSnapshot.getChildren()) {
                modelList.add(ds.getValue(EventCardDTO.class));
            }
            Collections.reverse(modelList);
            return modelList;
        }
    }
}
