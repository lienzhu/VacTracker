package com.example.vactracker.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.vactracker.ui.data.Obj;

import java.util.List;

@Dao
public interface ObjDAO {
    @Query("SELECT * FROM obj")
    List<Obj> getObjs();


    @Query("SELECT * FROM obj WHERE id == :objID")
    Obj getObj(String objID);

    @Insert
    void insertAll(Obj... objs);

    @Delete
    void deleteAll(Obj... objs);
}
