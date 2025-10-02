package com.example.payukproject.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payukproject.AddNewRecRole1;
import com.example.payukproject.Model.Role1Data;
import com.example.payukproject.R;
import com.example.payukproject.RecordDetail;
import com.example.payukproject.Role1Act;
import com.example.payukproject.Utils.Role1DBHelper;

import java.util.List;

public class Role1Adapter extends RecyclerView.Adapter<Role1Adapter.Role1ViewHolder> {

    private List<Role1Data> recList;
    private Context context;
    private Role1DBHelper roleDB;
    private Role1Act activity;

    public Role1Adapter(Role1DBHelper roleDB, Role1Act activity, Context context) {
        this.roleDB = roleDB;
        this.activity = activity;
        this.context = context;
    }

    @NonNull
    @Override
    public Role1ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task1_record_item, parent, false);
        return new Role1ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Role1Adapter.Role1ViewHolder holder, int position) {
        final Role1Data item = recList.get(position);
//        holder.zakaz_id.setText(item.getZakNum());
        int id = item.getId();
        holder.zakaz_id.setText(String.valueOf(item.getZakNum()));
        holder.car_num.setText(item.getZakCarNum());
        holder.dat_time.setText(item.getZakDateTime());
//        if (item.getCompleted()==0) holder.compl.setEnabled(false); else holder.compl.setEnabled(true);
        if (item.getCompleted() == 0) {
            holder.compl.setChecked(false);
            holder.compl.setEnabled(false);

        } else {
            holder.compl.setChecked(true);
            holder.compl.setEnabled(false);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() { // было .itemView. стало relativeLayout
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecordDetail.class);
                intent.putExtra("id", String.valueOf(id));
                intent.putExtra("role", String.valueOf(1));
                context.startActivity(intent);
            }
        });


    }

    public Context getContext() {
        return activity;
    }

    @Override
    public int getItemCount() {
        return recList.size();
    }

    public void setUser(List<Role1Data> List) {
        this.recList = List;
        notifyDataSetChanged();
    }

    public void deleteRecord(int position) {
        Role1Data item = recList.get(position);
        roleDB.Role1deleteRecord(item.getId());
        recList.remove(position);
        notifyItemRemoved(position);
    }

    public void editRecord(int position) {

        Role1Data item = recList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putInt("zakNum", item.getZakNum());
        bundle.putString("zakCarNum", item.getZakCarNum());
        bundle.putString("zakDateTime", item.getZakDateTime());
        bundle.putString("zakPhone", item.getZakPhone());
        bundle.putString("zakCarModel", item.getZakCarModel());
        bundle.putString("zakNote", item.getZakNote());
        bundle.putInt("completed", item.getCompleted());
        AddNewRecRole1 nRecord = new AddNewRecRole1();
        nRecord.setArguments(bundle);
        nRecord.show(activity.getSupportFragmentManager(), nRecord.getTag());
//          Toast.makeText(activity.getApplicationContext(), "EDIT!!!!!", Toast.LENGTH_SHORT).show();
//        notifyDataSetChanged();
    }

    public static class Role1ViewHolder extends RecyclerView.ViewHolder {
        TextView zakaz_id, car_num, dat_time;
        CheckBox compl; // добавить признак исполнения заявки

        public Role1ViewHolder(@NonNull View itemView) {
            super(itemView);
            zakaz_id = itemView.findViewById(R.id.tv_num);
            car_num = itemView.findViewById(R.id.tv_CarNum);
            dat_time = itemView.findViewById(R.id.tv_dat);
            compl = itemView.findViewById(R.id.tv_complete);
            // добавить признак исполнения заявки
        }
    }
}
