package com.example.vactracker;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.vactracker.DAO.ObjDAO;
import com.example.vactracker.ui.data.Obj;

@Database(entities = {Obj.class}, version = 1)
 @TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract ObjDAO objDAO();


}
