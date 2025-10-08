package com.example.payukproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payukproject.Adaptor.Role2Adapter2;
import com.example.payukproject.Adaptor.Role3Adapter1;
import com.example.payukproject.Adaptor.Role3Adapter2;
import com.example.payukproject.Model.Role2Data;
import com.example.payukproject.Model.Role3Data;
import com.example.payukproject.Utils.Role1DBHelper;

import java.util.ArrayList;
import java.util.List;

public class Role3Act extends AppCompatActivity {
    RecyclerView rv1, rv2;
    Switch aSwitch;
    Role1DBHelper role1DB;
    private List<Role2Data> r2List;
    private List<Role3Data> r3List;
    private Role3Adapter1 r3Adapter1;
    private Role3Adapter2 r3Adapter2;
    private boolean flagRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role3);

        rv1 = findViewById(R.id.role3_rv1);
        rv2 = findViewById(R.id.role3_rv2);
        aSwitch = findViewById(R.id.role3_sw);
        flagRecords=aSwitch.isChecked();


        r2List = new ArrayList<>();
        role1DB = new Role1DBHelper(Role3Act.this);
        r3Adapter1 = new Role3Adapter1(role1DB, Role3Act.this, this);
        rv1.setHasFixedSize(true);
        rv1.setLayoutManager(new LinearLayoutManager(this));
        rv1.setAdapter(r3Adapter1);
        r2List = role1DB.Role2getAllClosedRecordsPlat(flagRecords);
        r3Adapter1.setUser(r2List);

        r3List = new ArrayList<>();
        r3Adapter2 = new Role3Adapter2(role1DB,Role3Act.this,this);
        rv2.setHasFixedSize(true);
        rv2.setLayoutManager(new LinearLayoutManager(this));
        rv2.setAdapter(r3Adapter2);
        r3List = role1DB.Role3getAllRecords();
        r3Adapter2.setUser(r3List);



        aSwitch.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                flagRecords=aSwitch.isChecked();
                r2List = role1DB.Role2getAllClosedRecordsPlat(flagRecords);
                r3Adapter1.setUser(r2List);
                r3Adapter1.notifyDataSetChanged();
            }
        });

    }
}