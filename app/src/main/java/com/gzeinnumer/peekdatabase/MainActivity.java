package com.gzeinnumer.peekdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.gzeinnumer.peekdatabase.exampleRoom.RoomExampleActivity;
import com.gzeinnumer.peekdatabase.exampleSQLite.SqliteExampleActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSqlite = findViewById(R.id.btn_sqlite);
        btnSqlite.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), SqliteExampleActivity.class));
        });

        Button btnRoom = findViewById(R.id.btn_room);
        btnRoom.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), RoomExampleActivity.class));
        });
    }
}