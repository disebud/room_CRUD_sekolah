package com.stmikbanisaleh.room_crud_sekolah.ui.siswa;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SiswaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SiswaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is siswa fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
