package com.example.mygrocery.data;

import androidx.room.*;

import java.util.List;

@Dao()
public interface HomeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertHomeItem(Home... homeItem);

    @Query("SELECT * FROM home_table ORDER by id ASC")
    List<Home> getHomeItems();

    @Query("DELETE FROM home_table WHERE home_item_name = :homeItemName")
    void deleteHomeItem(String homeItemName);
}
