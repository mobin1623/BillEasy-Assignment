package com.example.billeasy_assignment.DB;

import com.example.billeasy_assignment.Utils.Constants;


import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


@Dao
public interface MovieDao {

    @Query("SELECT * FROM " + Constants.TABLE_NAME)
    List<Movies> getMovies();

    @Insert
    long insertMovies(Movies movies);
}
