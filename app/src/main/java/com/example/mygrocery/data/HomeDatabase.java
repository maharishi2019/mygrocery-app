package com.example.mygrocery.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Home.class}, version=3)
public abstract class HomeDatabase extends RoomDatabase {

    public abstract HomeDao homeDao();
    private static HomeDatabase INSTANCE;

    public static HomeDatabase getDBInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), HomeDatabase.class, "HomeDB")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}