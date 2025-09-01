package com.example.payukproject;

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

import com.example.payukproject.Utils.Crpt;

public class Role1Act extends AppCompatActivity {
    Button btn;
    EditText mesText;
    TextView tv1,tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role1);
        mesText= findViewById(R.id.mesText);
        btn = findViewById(R.id.test);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = Crpt.Encrypte(mesText.getText().toString());
                String detxt = Crpt.Decrypt(txt);
//                Toast.makeText(Role1Act.this, txt, Toast.LENGTH_SHORT).show();
                tv1.setText(txt);
                tv2.setText(detxt);
            }
        });


    }
}