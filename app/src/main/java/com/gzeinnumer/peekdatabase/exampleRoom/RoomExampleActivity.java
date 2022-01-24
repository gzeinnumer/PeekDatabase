package com.gzeinnumer.peekdatabase.exampleRoom;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.gzeinnumer.pd.helper.DialogTableBuilder;
import com.gzeinnumer.peekdatabase.R;
import com.gzeinnumer.peekdatabase.exampleRoom.data.AppDatabase;
import com.gzeinnumer.peekdatabase.exampleRoom.entity.SampleTable;
import com.nambimobile.widgets.efab.FabOption;

public class RoomExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_example);

        ((TextView) findViewById(R.id.tv)).setText("" +
                "1. Make database instance to ROOM\n" +
                "2. Make some table\n" +
                "3. Input some data\n" +
                "4. Click FloatActionButton <>\n" +
                "5. Easy debug");

        setTitle("Room");

        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());

        //insert data
//        db.noteDao().insertAll(new SampleTable(1, "data"));
//        db.noteDao().insertAll(new SampleTable(2, "data"));
//        db.noteDao().insertAll(new SampleTable(3, "data"));
//        db.noteDao().insertAll(new SampleTable(4, "data"));
//        db.noteDao().insertAll(new SampleTable(5, "data"));

        SQLiteDatabase appDatabase = AppDatabase.getDatabase(getApplicationContext()).getSQLiteDB(getApplicationContext());

        FabOption fo1 = findViewById(R.id.fo_1);
        fo1.setOnClickListener(view -> {
            DialogTableBuilder.newInstanse(this, appDatabase).build();
        });

        FabOption fo2 = findViewById(R.id.fo_2);
        fo2.setOnClickListener(view -> {
            DialogTableBuilder.newInstanse(this, appDatabase).addTable("sample_table").build();
        });
    }
}