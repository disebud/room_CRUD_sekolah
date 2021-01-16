package com.stmikbanisaleh.room_crud_sekolah;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stmikbanisaleh.room_crud_sekolah.ui.orangtua.OrangtuaFragment;
import com.stmikbanisaleh.room_crud_sekolah.ui.sekolah.SekolahFragment;
import com.stmikbanisaleh.room_crud_sekolah.ui.siswa.SiswaFragment;

public class MainActivity extends AppCompatActivity {
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.nav_view);
        loadFragment(new SiswaFragment());
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_siswa, R.id.navigation_orangtua, R.id.navigation_sekolah)
//                .build();
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_siswa:
                        setTitle("DATA SISWA");
                        fragment = new SiswaFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_orangtua:
                        setTitle("DATA ORANG TUA");
                        fragment = new OrangtuaFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.navigation_sekolah:
                        setTitle("DATA SEKOLAH");
                        fragment = new SekolahFragment();
                        loadFragment(fragment);
                        return true;
                }
                return false;
            }
        });
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        // transaction.addToBackStack(false);
        transaction.commit();
    }


}