package com.mydiploma.autohelper.ui.card;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CardViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LiveData<String> getText() {
        return mText;
    }
}