package com.stmikbanisaleh.room_crud_sekolah.ui.sekolah;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SekolahViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SekolahViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is sekolah fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}