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

    @Query("SELECT * FROM obj WHERE stageOfDevelopment == 'Pre-clinical' OR stageOfDevelopment == 'Pre-Clinical' OR stageOfDevelopment == 'Pre-clincial' OR stageOfDevelopment == 'Pre-Clincial'")
    List<Obj> getPreclinicalObjs();

    @Query("SELECT * FROM obj WHERE stageOfDevelopment != 'Pre-clinical' AND stageOfDevelopment != 'Pre-Clinical' AND stageOfDevelopment != 'Pre-clincial' AND stageOfDevelopment != 'Pre-Clincial'")
    List<Obj> getClinicalObjs();

    @Query("SELECT * FROM obj WHERE id == :objID")
    Obj getObj(String objID);

    @Insert
    void insertAll(Obj... objs);

    @Delete
    void deleteAll(Obj... objs);
}
