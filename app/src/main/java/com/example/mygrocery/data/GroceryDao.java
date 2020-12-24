package com.example.mygrocery.data;

import androidx.room.*;

import java.util.List;

@Dao
public interface GroceryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGrocery(Grocery... groceries);

    @Query("SELECT * FROM grocery_table ORDER by id ASC")
    List<Grocery> getGroceries();

    @Query("DELETE FROM grocery_table WHERE grocery_item_name = :groceryItemName")
    void deleteGrocery(String groceryItemName);
}
