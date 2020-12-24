package com.example.mygrocery.data;

import androidx.room.*;

import java.util.Date;

@Entity(tableName="home_table", indices = @Index(value = {"home_item_name"}, unique=true))
public class Home {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name="home_item_name")
    public String homeItemName;
    @ColumnInfo(name="expire_date")
    public String expireDate;
}