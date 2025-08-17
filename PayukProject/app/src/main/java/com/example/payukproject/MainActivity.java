package com.example.payukproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.payukproject.Utils.DBHelper;

public class MainActivity extends AppCompatActivity {
    Button btn_save;
    EditText name, passw;

    int role=10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbh = new DBHelper(this);
        if (dbh.checkRoot()) {
            Intent intent = new Intent(MainActivity.this, LoginAct.class);
            startActivity(intent);
            dbh.close();
        }

        name = findViewById(R.id.editName);
        passw = findViewById(R.id.editPassw);
        btn_save = findViewById(R.id.btnSave);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().isEmpty() || passw.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Поля должны быть заполнены!!!", Toast.LENGTH_SHORT).show();
                } else {
                    // Сохраняем в базу учетку с ролью 0
                    boolean result = dbh.addUser(name.getText().toString(),passw.getText().toString(),0);
                    if (result) {Toast.makeText(MainActivity.this, "Пользователь добавлен!!!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, LoginAct.class);
                        startActivity(intent);} else
                        Toast.makeText(MainActivity.this, "Ошибка добавления пользователя!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}