package com.example.firstapp.roomDB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntityClass.class}, version = 1)
public abstract class DatabaseClass extends RoomDatabase {

    public abstract Dao myDao();

}
