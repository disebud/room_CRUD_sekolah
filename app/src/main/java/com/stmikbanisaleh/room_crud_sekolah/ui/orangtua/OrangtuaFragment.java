package com.stmikbanisaleh.room_crud_sekolah.ui.orangtua;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.stmikbanisaleh.room_crud_sekolah.R;


public class OrangtuaFragment extends Fragment {

    private OrangtuaViewModel orangtuaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        orangtuaViewModel =
                ViewModelProviders.of(this).get(OrangtuaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_orangtua, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        orangtuaViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}