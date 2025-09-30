package com.example.payukproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.payukproject.Utils.Role1DBHelper;

import java.util.Objects;

public class Record2Detail extends AppCompatActivity {
    TextView tv_id, id_nar, car_num, phone, car_model, note, diagnost, result, summa, dat_B, dat_E;
    Role1DBHelper dbHelper;
    int id = 0;
    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record2_detail);

        dbHelper = new Role1DBHelper(this);

        tv_id = findViewById(R.id.tv2_r2_id);
        id_nar = findViewById(R.id.tv2_r2_rec1);
        car_num = findViewById(R.id.tv2_r2_car_num);
        phone = findViewById(R.id.tv2_r2_phone);
        car_model = findViewById(R.id.tv2_r2_car_model);
        note = findViewById(R.id.tv2_r2_zak_note);
        diagnost = findViewById(R.id.tv2_r2_diagnost);
        result = findViewById(R.id.tv2_r2_result);
        summa = findViewById(R.id.tv2_r2_summ);
        dat_B = findViewById(R.id.tv2_r2_dat_b);
        dat_E = findViewById(R.id.tv2_r2_dat_e);
        btn_back = findViewById(R.id.back);

        Intent intent = getIntent();
        id = Integer.parseInt(Objects.requireNonNull(intent.getStringExtra("id")));
        tv_id.setText(String.valueOf(id));

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent1 = new Intent(Record2Detail.this, Role2Act.class);
                startActivity(intent1);
            }
        });

        loadData2ById();

    }

    @SuppressLint("SetTextI18n")
    private void loadData2ById() {
        String selectQuery = "SELECT * FROM " + DBTables.TABLE2_NAME + " WHERE " + DBTables.T2_C_1 + " = \"" + id + "\"";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                id_nar.setText("" + cursor.getString(cursor.getColumnIndexOrThrow(DBTables.T2_C_2)));
                car_num.setText("" + cursor.getString(cursor.getColumnIndexOrThrow(DBTables.T2_C_3)));
                phone.setText("" + cursor.getString(cursor.getColumnIndexOrThrow(DBTables.T2_C_4)));
                car_model.setText("" + cursor.getString(cursor.getColumnIndexOrThrow(DBTables.T2_C_5)));
                note.setText("" + cursor.getString(cursor.getColumnIndexOrThrow(DBTables.T2_C_6)));
                diagnost.setText("" + cursor.getString(cursor.getColumnIndexOrThrow(DBTables.T2_C_7)));
                result.setText("" + cursor.getString(cursor.getColumnIndexOrThrow(DBTables.T2_C_8)));
                summa.setText("" + cursor.getString(cursor.getColumnIndexOrThrow(DBTables.T2_C_9)));
                dat_B.setText("" + cursor.getString(cursor.getColumnIndexOrThrow(DBTables.T2_C_10)));
                dat_E.setText("" + cursor.getString(cursor.getColumnIndexOrThrow(DBTables.T2_C_11)));

            } while (cursor.moveToNext());
        }
        db.close();
    }
}