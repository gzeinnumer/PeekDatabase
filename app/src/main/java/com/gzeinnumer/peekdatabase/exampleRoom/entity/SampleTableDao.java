package com.gzeinnumer.peekdatabase.exampleRoom.entity;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface  SampleTableDao {
    @Query("SELECT * FROM sample_table")
    List<SampleTable> getAll();

    @Insert
    void insertAll(SampleTable... sampleTables);

    @Delete
    void delete(SampleTable sampleTable);
}
