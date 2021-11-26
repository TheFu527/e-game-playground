package edu.neu.madcourse.numad21fa.egameplaygound.ui.teamup;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TeamUpViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TeamUpViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is team up fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}