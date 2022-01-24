package com.gzeinnumer.pd.helper;

import android.database.sqlite.SQLiteDatabase;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.gzeinnumer.pd.DialogTable;

public class DialogTableBuilder {

    private final FragmentActivity fragmentActivity;
    public SQLiteDatabase db;
    private String[] table;

    public static DialogTableBuilder newInstanse(FragmentActivity fragmentActivity, SQLiteDatabase sqLiteDatabase) {
        return new DialogTableBuilder(fragmentActivity, sqLiteDatabase);
    }

    public DialogTableBuilder(FragmentActivity fragmentActivity, SQLiteDatabase sqLiteDatabase) {
        this.fragmentActivity = fragmentActivity;
        this.db = sqLiteDatabase;
    }

    public void build() {
        FragmentTransaction transaction = fragmentActivity.getSupportFragmentManager().beginTransaction();
        Fragment previous = fragmentActivity.getSupportFragmentManager().findFragmentByTag(DialogTable.TAG);
        if(previous != null){
            transaction.remove(previous);
        }
        DialogTable dialog = DialogTable.newInstance(db, table);
        dialog.show(transaction, DialogTable.TAG);
    }

    public DialogTableBuilder addTable(String... table) {
        this.table = table;
        return this;
    }
}
