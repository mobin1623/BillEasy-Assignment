package com.example.billeasy_assignment.DB;


import android.content.Context;

import com.example.billeasy_assignment.Utils.Constants;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = { Movies.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    public abstract MovieDao getMovieDao();

    private static MovieDatabase movieDatabase;

    public static MovieDatabase getInstance(Context context){
        if (null == movieDatabase){
            movieDatabase = buildDatabaseInstance(context);
        }
        return movieDatabase;

    }

    private static MovieDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,MovieDatabase.class, Constants.DB_NAME)
                .allowMainThreadQueries().build();
    }


    public void cleanUp(){
        movieDatabase = null;
    }


    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }


}
