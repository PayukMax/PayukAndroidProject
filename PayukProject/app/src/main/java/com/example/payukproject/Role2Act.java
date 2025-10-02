package com.example.payukproject;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payukproject.Adaptor.Role1Adapter;
import com.example.payukproject.Adaptor.Role2Adapter1;
import com.example.payukproject.Adaptor.Role2Adapter2;
import com.example.payukproject.Model.Role1Data;
import com.example.payukproject.Model.Role2Data;
import com.example.payukproject.Utils.Role1DBHelper;

import java.util.ArrayList;
import java.util.List;

public class Role2Act extends AppCompatActivity implements OnDialogCloseListener  {
    RecyclerView rv1, rv2;
    Role1DBHelper role1DB;
    private List<Role1Data> rList;
    private List<Role2Data> r2List;
    private Role2Adapter1 rAdapter;
    private Role2Adapter2 r2Adapter;
    Button addRecBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role2);

        addRecBtn = findViewById(R.id.add_naryad);

        rv1 = findViewById(R.id.role2_rv1);
        rList = new ArrayList<>();
        role1DB = new Role1DBHelper(Role2Act.this);
        rAdapter = new Role2Adapter1(role1DB, Role2Act.this, this);
        rv1.setHasFixedSize(true);
        rv1.setLayoutManager(new LinearLayoutManager(this));
        rv1.setAdapter(rAdapter);

        rList = role1DB.Role1getAllRecords(false);
        rAdapter.setUser(rList);


        rv2 = findViewById(R.id.role2_rv2);
        r2List = new ArrayList<>();
        r2Adapter = new Role2Adapter2(role1DB, Role2Act.this, this);
        rv2.setHasFixedSize(true);
        rv2.setLayoutManager(new LinearLayoutManager(this));
        rv2.setAdapter(r2Adapter);
        r2List = role1DB.Role2getAllRecords();
        r2Adapter.setUser(r2List);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RVTHelperRole2(r2Adapter));
        itemTouchHelper.attachToRecyclerView(rv2);


        // ручное добавление нового заказ-наряда
        addRecBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewRecRole2.newInstance().show(getSupportFragmentManager(),AddNewRecRole2.TAG);

            }
        });

    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        r2List = role1DB.Role2getAllRecords();
        r2Adapter.setUser(r2List);
        r2Adapter.notifyDataSetChanged();
    }
}