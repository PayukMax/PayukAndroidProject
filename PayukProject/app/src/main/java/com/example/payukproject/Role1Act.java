package com.example.payukproject;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payukproject.Adaptor.Role1Adapter;
import com.example.payukproject.Model.Role1Data;
import com.example.payukproject.Utils.Role1DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Role1Act extends AppCompatActivity implements OnDialogCloseListener{
    RecyclerView rv;
    FloatingActionButton addRecord;
    Switch sw;
    boolean flagRecords;
    Role1DBHelper role1DB;
    private List<Role1Data> rList;
    private Role1Adapter rAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role1);

        rv = findViewById(R.id.recordRV);
        addRecord = findViewById(R.id.role1FAB);
        sw = findViewById(R.id.switch1);
        flagRecords=sw.isChecked();

        role1DB = new Role1DBHelper(Role1Act.this);
        rList = new ArrayList<>();
        rAdapter = new Role1Adapter(role1DB, Role1Act.this, this);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(rAdapter);

        rList = role1DB.Role1getAllRecords(flagRecords);
        rAdapter.setUser(rList);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RVTHelperRole1(rAdapter));
        itemTouchHelper.attachToRecyclerView(rv);

        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagRecords=sw.isChecked();
                rList = role1DB.Role1getAllRecords(flagRecords);
                rAdapter.setUser(rList);
                rAdapter.notifyDataSetChanged();
            }
        });
        addRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Role1Act.this, "!!!!!!!", Toast.LENGTH_SHORT).show();
                AddNewRecRole1.newInstance().show(getSupportFragmentManager(),AddNewRecRole1.TAG);

            }
        });

    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        rList = role1DB.Role1getAllRecords(flagRecords);
        rAdapter.setUser(rList);
        rAdapter.notifyDataSetChanged();
    }
}