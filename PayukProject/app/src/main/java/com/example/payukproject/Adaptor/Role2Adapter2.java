package com.example.payukproject.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payukproject.Model.Role1Data;
import com.example.payukproject.Model.Role2Data;
import com.example.payukproject.R;
import com.example.payukproject.Record2Detail;
import com.example.payukproject.RecordDetail;
import com.example.payukproject.Role2Act;
import com.example.payukproject.Utils.Role1DBHelper;

import java.util.List;

public class Role2Adapter2 extends RecyclerView.Adapter<Role2Adapter2.Role2ViewHolder2> {
    private List<Role2Data> recList2;
    private Context context;
    private Role1DBHelper roleDB;
    private Role2Act activity;

    public Role2Adapter2(Role1DBHelper roleDB, Role2Act activity,  Context context) {
        this.context = context;
        this.roleDB = roleDB;
        this.activity = activity;
    }


    @NonNull
    @Override
    public Role2Adapter2.Role2ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task2_record_item, parent, false);
        return new Role2Adapter2.Role2ViewHolder2(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Role2Adapter2.Role2ViewHolder2 holder, int position) {
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
////      добавить заполнение чекбокса в строке RV

        holder.itemView.setOnClickListener(new View.OnClickListener() { // было .itemView. стало relativeLayout
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Record2Detail.class);
                intent.putExtra("id", String.valueOf(id));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recList2.size();
    }

    public void setUser(List<Role2Data> List) {
        this.recList2 = List;
        notifyDataSetChanged();
    }

    public static class Role2ViewHolder2 extends RecyclerView.ViewHolder {
        TextView item_id, tel_zak, car_num, phone, datB, datE, summa; // актуальную структуру!!!!!!!!

        public Role2ViewHolder2(@NonNull View itemView) {
            super(itemView);
            item_id = itemView.findViewById(R.id.item_id);
            tel_zak = itemView.findViewById(R.id.item_zak);
            car_num = itemView.findViewById(R.id.item_car_num);
            phone = itemView.findViewById(R.id.item_phone);
            datB = itemView.findViewById(R.id.item_dat_beg);
            datE = itemView.findViewById(R.id.item_dat_end);
            summa = itemView.findViewById(R.id.item_summa);

            // добавить признак исполнения заявки
        }
    }

}
