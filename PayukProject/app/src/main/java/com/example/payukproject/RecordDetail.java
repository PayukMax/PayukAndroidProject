package com.example.payukproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.payukproject.Utils.Role1DBHelper;

import java.util.Locale;
import java.util.Objects;

public class RecordDetail extends AppCompatActivity {
    TextView zakNum, zakCarNum, zakDateTime, zakPhone, zakCarModel, zakNote;
//    Button btn;
    Role1DBHelper dbHelper;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_detail);

        dbHelper = new Role1DBHelper(this);

        zakNum = findViewById(R.id.tv_zakNum);
        zakCarNum = findViewById(R.id.tv_zakCarNum);
        zakDateTime = findViewById(R.id.tv_zakDateTime);
        zakPhone = findViewById(R.id.tv_zakPhone);
        zakCarModel = findViewById(R.id.tv_zakCarModel);
        zakNote = findViewById(R.id.tv_zakNote);


//        btn = findViewById(R.id.btn_back);
        Intent intent = getIntent();
        id = Integer.parseInt(Objects.requireNonNull(intent.getStringExtra("id")));

        loadDataById();

    }

    @SuppressLint("SetTextI18n")
    private void loadDataById() {
        String selectQuery = "SELECT * FROM " + DBTables.TABLE_NAME + " WHERE " + DBTables.COL_1 + " = \"" + id + "\"";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                zakNum.setText("" + cursor.getString(cursor.getColumnIndexOrThrow(DBTables.COL_2)));
                zakCarNum.setText("" + cursor.getString(cursor.getColumnIndexOrThrow(DBTables.COL_3)));
                zakDateTime.setText("" + cursor.getString(cursor.getColumnIndexOrThrow(DBTables.COL_4)));
                zakPhone.setText("" + cursor.getString(cursor.getColumnIndexOrThrow(DBTables.COL_5)));
                zakCarModel.setText("" + cursor.getString(cursor.getColumnIndexOrThrow(DBTables.COL_6)));
                zakNote.setText("" + cursor.getString(cursor.getColumnIndexOrThrow(DBTables.COL_7)));

            } while (cursor.moveToNext());
        }
        db.close();
    }
}