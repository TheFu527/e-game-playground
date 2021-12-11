package edu.neu.madcourse.numad21fa.egameplaygound.ui.me;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import edu.neu.madcourse.numad21fa.egameplaygound.constant.enums.user.UserLevelEnum;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.TeamUpCardDTO;
import edu.neu.madcourse.numad21fa.egameplaygound.model.dto.UserInfoDTO;
import edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup.TeamUpCard;

public class MeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private final MutableLiveData<UserInfoDTO> userInfoDTOLiveData;


    public MeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is me fragment");
        userInfoDTOLiveData = new MutableLiveData<>();

    }

    public LiveData<String> getText() {
        return mText;
    }

    public void updateUserLevel(UserInfoDTO userInfoDTO, UserLevelEnum value) {

        userInfoDTO.setLevel(value);
        userInfoDTOLiveData.setValue(userInfoDTO);
    }


}