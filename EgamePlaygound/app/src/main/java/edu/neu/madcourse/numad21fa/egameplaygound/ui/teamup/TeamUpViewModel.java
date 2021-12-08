package edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserGenderEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserLevelEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.manager.database.DatabaseManagerImpl;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.TeamUpCardDTO;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.UserInfoDTO;

public class TeamUpViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<List<TeamUpCard>> teamUpCardLiveData;
    private final List<TeamUpCard> teamUpCardList;

    public TeamUpViewModel() {
        mText = new MutableLiveData<>();
        teamUpCardLiveData = new MutableLiveData<>();
        teamUpCardList = new ArrayList<>();
        mText.setValue("This is team up fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<TeamUpCard>> getTeamUpCard() {
        return teamUpCardLiveData;
    }

    public void updateTeamUpCardList(List<TeamUpCardDTO> teamUpCardDTOList) {
        for (TeamUpCardDTO t : teamUpCardDTOList) {
            teamUpCardList.add(new TeamUpCard()
                    .setDescription(t.getDescription())
                    .setTimestamp(t.getTimestamp())
                    .setUserInfo(t.getCreatorUser())
                    .setLocation(t.getLocation())
                    .setUuid(t.getUuid()));
        }
        teamUpCardLiveData.setValue(teamUpCardList);
    }
}