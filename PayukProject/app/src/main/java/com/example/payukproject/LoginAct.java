package com.example.payukproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.payukproject.Utils.DBHelper;

public class LoginAct extends AppCompatActivity {
    Button btn_login;
    EditText login_name, login_passw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        DBHelper dbh = new DBHelper(this);

        btn_login = findViewById(R.id.btn_login);
        login_name = findViewById(R.id.edit_user);
        login_passw = findViewById(R.id.edit_passw);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login_name.getText().toString().isEmpty()||login_passw.getText().toString().isEmpty()) {
                    Toast.makeText(LoginAct.this, "Имя и пароль обязательны!!!", Toast.LENGTH_SHORT).show();
                } else {
                    boolean result = dbh.checkUser(login_name.getText().toString(), login_passw.getText().toString());
                    if (result) {
                        if (dbh.getRole(login_name.getText().toString())==0){
                            login_passw.setText("");
                            Toast.makeText(LoginAct.this, "Авторизация успешно пройдена... Вы администратор!!!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginAct.this, AdminAct.class);
                            startActivity(intent);
                            dbh.close();
                        }
                        if (dbh.getRole(login_name.getText().toString())==1){
                            login_passw.setText("");
                            Toast.makeText(LoginAct.this, "Авторизация успешно пройдена...Ваша роль Call центр", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginAct.this, Role1Act.class);
                            startActivity(intent);
                            dbh.close();
                        }
                        if (dbh.getRole(login_name.getText().toString())==2){
                            login_passw.setText("");
                            Toast.makeText(LoginAct.this, "Авторизация успешно пройдена...Ваша роль Мастер приемщик", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(LoginAct.this, Role2Act.class);
//                            startActivity(intent);
                            dbh.close();
                        }
                        if (dbh.getRole(login_name.getText().toString())==3){
                            login_passw.setText("");
                            Toast.makeText(LoginAct.this, "Авторизация успешно пройдена...Ваша роль Мастер цеха ремонта", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(LoginAct.this, Role3Act.class);
//                            startActivity(intent);
                            dbh.close();
                        }
                    } else {
                        Toast.makeText(LoginAct.this, "Ошибка в имени пользоватея или пароле....", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}