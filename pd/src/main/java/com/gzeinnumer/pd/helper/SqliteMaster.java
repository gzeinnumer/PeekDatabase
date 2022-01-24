package com.gzeinnumer.pd.helper;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gzeinnumer.esc.SQLiteLIB;
import com.gzeinnumer.esc.struck.SQLiteTable;
import com.gzeinnumer.esc.typeData.VarcharTypeData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SQLiteTable(tableName = "sqlite_master")
public class SqliteMaster extends SQLiteLIB<SqliteMaster> {

    private final SQLiteDatabase sqLiteDatabase;

    @VarcharTypeData
    private String name;

    public SqliteMaster(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public List<String> query(String[] tableInclude) {
        String[] tableExclude = {"android_metadata","sqlite_sequence","room_master_table"};
        StringBuilder sbEx = new StringBuilder();
        for(int i = 0; i < tableExclude.length; i++) {
            sbEx.append("'").append(tableExclude[i]).append("',");
        }
        String strExclude = sbEx.substring(0, sbEx.lastIndexOf(","));

        String query;
        if (tableInclude!=null){
            StringBuilder sbIn = new StringBuilder();
            for (String s : tableInclude) {
                sbIn.append("'").append(s).append("',");
            }
            String strInclude = sbIn.substring(0, sbIn.lastIndexOf(","));
            query = "SELECT name FROM sqlite_master WHERE type='table' and name not in ("+strExclude+") and name in ("+strInclude+") order by name asc;";
        } else {
            query = "SELECT name FROM sqlite_master WHERE type='table' and name not in ("+strExclude+") order by name asc;";
        }

        List<SqliteMaster> temp = queryData(SqliteMaster.class, sqLiteDatabase, query);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            list.add(temp.get(i).getName());
        }
        return list;
    }

    public List<String> getColumns(String tableName) {
        List<String> ar = null;
        try (Cursor c = sqLiteDatabase.rawQuery("select * from " + tableName + " limit 1", null)) {
            if (c != null) {
                ar = new ArrayList<String>(Arrays.asList(c.getColumnNames()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ar;
    }

    @SuppressLint("Range,Recycle")
    public List<String> getData(String table) {
        List<String> list = getColumns(table);
        String query = "SELECT *, ROW_NUMBER() OVER() as LineNo  FROM " + table + " order by LineNo DESC;";

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        List<String> temp = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String msg = "";
                for (int i = 0; i < list.size(); i++) {
                    String data = cursor.getString(cursor.getColumnIndex(list.get(i)));
                    String build = list.get(i) + " => " +  data + ",\n";
                    msg = msg + build;
                }
                msg = msg.substring(0, msg.lastIndexOf(","));
                temp.add(msg);
            }
        }
        return temp;
    }

    public int countData(String name) {
        String query = "SELECT COUNT(*) FROM "+name+";";
        return queryCount(SqliteMaster.class, sqLiteDatabase, query);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
