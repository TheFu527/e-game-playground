package edu.neu.madcourse.numad21fa.egameplaygound.ui.piazza;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PiazzaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PiazzaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is piazza fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}