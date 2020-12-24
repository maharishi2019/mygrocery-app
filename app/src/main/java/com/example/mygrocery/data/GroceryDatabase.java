package com.example.mygrocery.data;

import android.content.*;
import androidx.room.*;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Grocery.class}, version=4)
public abstract class GroceryDatabase extends RoomDatabase{

    public abstract GroceryDao groceryDao();
    private static GroceryDatabase INSTANCE;

    public static GroceryDatabase getDBInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), GroceryDatabase.class, "GroceryDB")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
