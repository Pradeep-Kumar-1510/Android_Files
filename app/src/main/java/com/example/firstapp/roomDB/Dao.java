package com.example.firstapp.roomDB;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Insert
    public void addUser(UserEntityClass user);

    @Query("select *  from userTable")
    public List<UserEntityClass> getUsers();

    @Delete
    public void deleteUser(UserEntityClass user);

    @Update
    public void updateUser(UserEntityClass user);

}
