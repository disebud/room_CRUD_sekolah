package com.stmikbanisaleh.room_crud_sekolah;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Siswa.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SiswaDao dosenDao();

    public static AppDatabase db;

    public static AppDatabase getDb(Context context) {
        if (db == null) {
            db = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "db_biodata")
                    .enableMultiInstanceInvalidation()
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
            Log.i("Database Initialized", "Database Initialized");
        }
        return db;
    }
}