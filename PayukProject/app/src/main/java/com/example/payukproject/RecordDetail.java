package com.example.payukproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    Button btn_zak_nar;
    Role1DBHelper dbHelper;
    int id, role;

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


        btn_zak_nar = findViewById(R.id.btn_new_naryad);
        Intent intent = getIntent();
        id = Integer.parseInt(Objects.requireNonNull(intent.getStringExtra("id")));
// здесь добавим чтение признака что из 2й роли - значит делаем видимой кнопку ОТКРЫТЬ З/Н - при ее нажатии переходим на активити нового заказ наряда с пред заполеннными полями
        role=0;
        role = Integer.parseInt(Objects.requireNonNull(intent.getStringExtra("role")));
        if (role==2){
            btn_zak_nar.setEnabled(true);
        } else btn_zak_nar.setEnabled(false);
        loadDataById();

        btn_zak_nar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                finish();
                Bundle bundle = new Bundle();
                bundle.putInt("zakNum", Integer.parseInt(zakNum.getText().toString()));
                bundle.putString("zakCarNum", zakCarNum.getText().toString());
                bundle.putString("zakPhone", zakPhone.getText().toString());
                bundle.putString("zakCarModel", zakCarModel.getText().toString());
                bundle.putString("zakNote", zakNote.getText().toString());
                bundle.putString("bandleType","1"); // тип пересылки - 1 создано на основании предв записи, 2 - редактирование созлданной ранее записи


//                Intent intent1 = new Intent(RecordDetail.this, Record2Detail.class);
//                intent1.putExtra("zakNum",Integer.parseInt(zakNum.getText().toString()));
//                intent1.putExtra("zakCarNum",zakCarNum.getText().toString());
//                intent1.putExtra("zakPhone",zakPhone.getText().toString());
//                startActivity(intent1);

                AddNewRecRole2 newRec2 = new AddNewRecRole2();
                newRec2.setArguments(bundle);
//                newRec2.show(newRec2.requireActivity().getSupportFragmentManager(), newRec2.getTag());
                newRec2.show(getSupportFragmentManager(), newRec2.getTag());
//                AddNewRecRole2.newInstance().show(getSupportFragmentManager(),AddNewRecRole2.TAG);

//                finish();
                 }
        });

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