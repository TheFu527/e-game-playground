package edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserGenderEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserLevelEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.UserInfoDTO;

public class TeamUpViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<TeamUpCard>> teamUpCardLiveData;
    private List<TeamUpCard> teamUpCardList;

    public TeamUpViewModel() {
        mText = new MutableLiveData<>();
        teamUpCardLiveData = new MutableLiveData<>();
        init();
        mText.setValue("This is team up fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<TeamUpCard>> getTeamUpCard() {
        return teamUpCardLiveData;
    }

    private void init() {
        mockList();
        teamUpCardLiveData.setValue(teamUpCardList);
    }

    private void mockList() {
        UserInfoDTO user = new UserInfoDTO("uuid", "name",
                "url", UserGenderEnum.MALE, UserLevelEnum.GOLD, "a@example.com");
        TeamUpCard card = new TeamUpCard("uuid", user, "description", "timestamp");
        teamUpCardList = new ArrayList<>();
        teamUpCardList.add(card);
    }




}