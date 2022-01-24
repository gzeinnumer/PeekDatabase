package com.gzeinnumer.peekdatabase.exampleRoom.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.gzeinnumer.peekdatabase.exampleRoom.entity.SampleTable;
import com.gzeinnumer.peekdatabase.exampleRoom.entity.SampleTableDao;

@Database(entities = {SampleTable.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract SampleTableDao noteDao();

    public static String dbName = "databaselocalmoduleroom.db";
    private static volatile AppDatabase noteRoomDatabase;

    public static AppDatabase getDatabase(final Context context){
        if (noteRoomDatabase == null){
            synchronized (AppDatabase.class){
                if (noteRoomDatabase == null){
                    noteRoomDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, dbName).allowMainThreadQueries().build();
                }
            }
        }
        return noteRoomDatabase;
    }

    public SQLiteDatabase getSQLiteDB(Context context) {
        return SQLiteDatabase.openDatabase(context.getDatabasePath(dbName).getPath(),null,SQLiteDatabase.OPEN_READWRITE);
    }
}