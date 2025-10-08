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

import com.example.payukproject.AddNewRecRole2;
import com.example.payukproject.Model.Role2Data;
import com.example.payukproject.R;
import com.example.payukproject.Record2Detail;
import com.example.payukproject.Role2Act;
import com.example.payukproject.Role3Act;
import com.example.payukproject.Utils.Role1DBHelper;

import java.util.List;

public class Role3Adapter1 extends RecyclerView.Adapter<Role3Adapter1.Role3ViewHolder1>{
    private List<Role2Data> recList2;
    private Context context;
    private Role1DBHelper roleDB;
    private Role3Act activity;

    public Role3Adapter1(Role1DBHelper roleDB, Role3Act activity,  Context context) {
        this.context = context;
        this.roleDB = roleDB;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Role3Adapter1.Role3ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task2_record_item, parent, false);
        return new Role3Adapter1.Role3ViewHolder1(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Role3Adapter1.Role3ViewHolder1 holder, int position) {
        final Role2Data item = recList2.get(position);
        // Заполняем поля согласно task2_record_item...

////        holder.zakaz_id.setText(item.getZakNum());
        int id = item.getId();
        holder.item_id.setText(String.valueOf(item.getId()));
        holder.tel_zak.setText(String.valueOf(item.getRemNum()));
        holder.car_num.setText(item.getRemCarNum());
        holder.phone.setText(item.getRemPhone());
        holder.datB.setText(item.getRemDateBegin());
        holder.datE.setText(item.getRemDateEnd());
        holder.summa.setText(String.valueOf(item.getRemSumma()));
        if (item.getRemPlatComplete() == 0) {
            holder.compl.setChecked(false);
            holder.compl.setEnabled(false);

        } else {
            holder.compl.setChecked(true);
            holder.compl.setEnabled(false);
        }

////      добавить заполнение чекбокса в строке RV

        holder.itemView.setOnClickListener(new View.OnClickListener() { // было .itemView. стало relativeLayout
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Record2Detail.class);
                intent.putExtra("id", String.valueOf(id));
                intent.putExtra("role", String.valueOf(3));
                context.startActivity(intent);
            }
        });

    }

    public Context getContext() {
        return activity;
    }

    @Override
    public int getItemCount() {
        return recList2.size();
    }

    public void setUser(List<Role2Data> List) {
        this.recList2 = List;
        notifyDataSetChanged();
    }
//
//    public void deleteRecord(int position) {
//        Role2Data item = recList2.get(position);
//        roleDB.Role2deleteRecord(item.getId());
//        recList2.remove(position);
//        notifyItemRemoved(position);
//    }
//
//    public void editRecord(int position) {
//
//        Role2Data item = recList2.get(position);
//        Bundle bundle = new Bundle();
//
//        bundle.putString("bandleType", "2");
//        bundle.putInt("id", item.getId());
//        bundle.putInt("zakNum", item.getRemNum());
//        bundle.putString("zakCarNum", item.getRemCarNum());
////        bundle.putString("zakDateTime", item.getRemDateBegin());
//        bundle.putString("zakPhone", item.getRemPhone());
//        bundle.putString("zakCarModel", item.getRemCarModel());
//        bundle.putString("zakNote", item.getRemNote());
//        bundle.putString("diagnost", item.getRemDiagnost());
//        bundle.putString("result", item.getRemResult());
//        bundle.putInt("summa", item.getRemSumma());
//        bundle.putString("datBegin", item.getRemDateBegin());
//        bundle.putString("datEnd", item.getRemDateEnd());
//        bundle.putInt("completed", item.getRemComplete());
//        AddNewRecRole2 nRecord = new AddNewRecRole2();
//        nRecord.setArguments(bundle);
//        nRecord.show(activity.getSupportFragmentManager(), nRecord.getTag());
////          Toast.makeText(activity.getApplicationContext(), "EDIT!!!!!", Toast.LENGTH_SHORT).show();
////        notifyDataSetChanged();
//    }



    public static class Role3ViewHolder1 extends RecyclerView.ViewHolder {
        TextView item_id, tel_zak, car_num, phone, datB, datE, summa; // актуальную структуру!!!!!!!!
        CheckBox compl;

        public Role3ViewHolder1(@NonNull View itemView) {// ПРАВИТЬ!!!!!
            super(itemView);
            item_id = itemView.findViewById(R.id.item_id);
            tel_zak = itemView.findViewById(R.id.item_zak);
            car_num = itemView.findViewById(R.id.item_car_num);
            phone = itemView.findViewById(R.id.item_phone);
            datB = itemView.findViewById(R.id.item_dat_beg);
            datE = itemView.findViewById(R.id.item_dat_end);
            summa = itemView.findViewById(R.id.item_summa);
            compl = itemView.findViewById(R.id.item_cb);

            // добавить признак исполнения заявки
        }
    }

}
