package com.gzeinnumer.peekdatabase.exampleSQLite.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.gzeinnumer.peekdatabase.exampleSQLite.entity.Table1;
import com.gzeinnumer.peekdatabase.exampleSQLite.entity.Table2;
import com.gzeinnumer.sb.SQLiteBuilder;
import com.gzeinnumer.sb.struct.SQLiteDatabaseEntity;

@SQLiteDatabaseEntity(entities = {
        Table1.class,
        Table2.class
})
public class DBInstance extends SQLiteBuilder {

    private static SQLiteDatabase sqLiteDatabase;
    public static String DB_NAME = "databaselocalmodulesqlite.db";

    public static SQLiteDatabase getDataBase(Context context) {
        sqLiteDatabase = SQLiteBuilder.builder(DBInstance.class, context)
                .setDatabaseName(DB_NAME)
                .setDatabaseVersion(3)
                .build();
        return sqLiteDatabase;
    }

}
