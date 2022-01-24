package com.gzeinnumer.peekdatabase.exampleRoom.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sample_table")
public class SampleTable {

    public SampleTable(int id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "name")
    public String name;
}
