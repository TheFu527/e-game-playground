package edu.neu.madcourse.numad21fa.egameplaygound.ui.events;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EventsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EventsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is event news fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}