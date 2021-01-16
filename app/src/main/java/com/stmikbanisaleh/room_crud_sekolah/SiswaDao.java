package com.stmikbanisaleh.room_crud_sekolah;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SiswaDao {
    @Query("SELECT * FROM siswa")
    List<Siswa> getAll();

    @Insert
    void insertAll(Siswa ...siswa);

    @Insert
    long insert(Siswa siswa);

    @Update
    void update(Siswa siswa);

    @Delete
    void delete(Siswa siswa);

    @Delete
    void delete(Siswa ...siswa);
}