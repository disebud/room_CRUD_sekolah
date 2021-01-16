package com.stmikbanisaleh.room_crud_sekolah.ui.orangtua;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OrangtuaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public OrangtuaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is orangtua fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}