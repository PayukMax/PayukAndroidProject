package com.example.payukproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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

import java.util.Objects;

public class Record2Detail extends AppCompatActivity {
    TextView tv_id, id_nar, car_num, phone, car_model, note, diagnost, result, summa, dat_B, dat_E;
    Role1DBHelper dbHelper;
    int id = 0;
    int role = 0;
    Button btn_back, btn_closeNar, btn_platNar;

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
        btn_closeNar = findViewById(R.id.btn_close_naryad);
        btn_platNar = findViewById(R.id.btn_plat_naryad);
        role = 0;
        Intent intent = getIntent();
        id = Integer.parseInt(Objects.requireNonNull(intent.getStringExtra("id")));
        role = Integer.parseInt(Objects.requireNonNull(intent.getStringExtra("role")));

        tv_id.setText(String.valueOf(id));

        btn_closeNar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (summa.getText().toString().isEmpty()) {
                    Toast.makeText(Record2Detail.this, "Сумма не может быть пустой...", Toast.LENGTH_SHORT).show();
                } else {
                    if (setComplete(id)) {
                        Toast.makeText(Record2Detail.this, "Complete - OK", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Record2Detail.this, Role2Act.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // подчищаем историю чтобы не переместился кнопкой НАЗАД
                        startActivity(intent);
                        // переход на основное активити роли 2 с зачисткой истории переходов
                    }
                }
            }
        });

        btn_platNar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (setPlat(id)) {
//                    Toast.makeText(Record2Detail.this, "Complete - OK", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Record2Detail.this, Role3Act.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // подчищаем историю чтобы не переместился кнопкой НАЗАД
                    startActivity(intent);
                    // переход на основное активити роли 2 с зачисткой истории переходов
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent1;
                if (role==2){
                    intent1 = new Intent(Record2Detail.this, Role2Act.class);
                } else if (role==3){
                    intent1 = new Intent(Record2Detail.this, Role3Act.class);
                } else intent1 = new Intent(Record2Detail.this, LoginAct.class);
                startActivity(intent1);
            }
        });

        loadData2ById();

    }

    private boolean setComplete(int id) {
        if (dbHelper.Role2SetComplete(id, true)) {
            Toast.makeText(this, "Наряд успешно закрыт...", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Закрыть наряд не удалось!!!!!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private boolean setPlat(int id) {
        if (dbHelper.Role2SetPlat(id, true)) {
            Toast.makeText(this, "Оплата успешно зафиксирована...", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Зафиксировать оплату не удалось!!!!!", Toast.LENGTH_SHORT).show();
            return false;
        }
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
                if (role == 2) {
                    if (cursor.getInt(cursor.getColumnIndexOrThrow(DBTables.T2_C_12)) == 1) { // проверяем зактыт ло наряд
                        btn_closeNar.setEnabled(false);
                        btn_closeNar.setVisibility(View.GONE);

                    } else {
                        btn_closeNar.setEnabled(true);
                        btn_closeNar.setVisibility(View.VISIBLE);
                    }
                } else {
                    btn_closeNar.setEnabled(false);
                    btn_closeNar.setVisibility(View.GONE);
                }

                if (role == 3) {
                    if (cursor.getInt(cursor.getColumnIndexOrThrow(DBTables.T2_C_13)) == 1) { // проверяем оплачие ли наряд
                        btn_platNar.setEnabled(false);
                        btn_platNar.setVisibility(View.GONE);
                    } else {
                        btn_platNar.setEnabled(true);
                        btn_platNar.setVisibility(View.VISIBLE);
                    }
                } else {
                    btn_platNar.setEnabled(false);
                    btn_platNar.setVisibility(View.GONE);
                }

            } while (cursor.moveToNext());
        }
        db.close();
    }


}