package com.gzeinnumer.peekdatabase.exampleSQLite.entity;

import android.database.sqlite.SQLiteDatabase;

import com.gzeinnumer.esc.SQLiteLIB;
import com.gzeinnumer.esc.struck.SQLiteTable;
import com.gzeinnumer.esc.typeData.DecimalTypeData;
import com.gzeinnumer.esc.typeData.IntegerTypeData;
import com.gzeinnumer.esc.typeData.PrimaryKeyTypeData;
import com.gzeinnumer.esc.typeData.TextTypeData;
import com.gzeinnumer.esc.typeData.TimeStampTypeData;
import com.gzeinnumer.esc.typeData.VarcharTypeData;
import com.gzeinnumer.sb.struct.CreateTableQuery;

@CreateTableQuery(
        query = "CREATE TABLE table2 (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, rating REAL, desc TEXT, flag_active INTEGER, created_at TEXT)"
)
@SQLiteTable(tableName = "table2")
public class Table2 extends SQLiteLIB<Table2> {

    @PrimaryKeyTypeData
    private int id;
    @VarcharTypeData
    private String name;
    @DecimalTypeData
    private double rating;
    @TextTypeData
    private String desc;
    @IntegerTypeData
    private int flag_active;
    @TimeStampTypeData
    private String created_at;

    private SQLiteDatabase sqLiteDatabase;

    public Table2() {
    }

    public Table2(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

}
