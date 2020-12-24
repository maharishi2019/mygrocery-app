package com.example.mygrocery.data;

import androidx.room.*;

@Entity(tableName="grocery_table", indices = @Index(value = {"grocery_item_name"}, unique=true))
public class Grocery {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name="grocery_item_name")
    public String groceryItemName;
}
