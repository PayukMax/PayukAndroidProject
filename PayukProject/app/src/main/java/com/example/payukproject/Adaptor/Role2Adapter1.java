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
import com.example.payukproject.R;
import com.example.payukproject.RecordDetail;
import com.example.payukproject.Role1Act;
import com.example.payukproject.Role2Act;
import com.example.payukproject.Utils.Role1DBHelper;

import java.util.List;

public class Role2Adapter1 extends RecyclerView.Adapter<Role2Adapter1.Role2ViewHolder1>{
    private List<Role1Data> recList;
    private Context context;
    private Role1DBHelper roleDB;
    private Role2Act activity;

    public Role2Adapter1(Role1DBHelper roleDB, Role2Act activity, Context context) {
        this.roleDB = roleDB;
        this.activity = activity;
        this.context = context;
    }

    @NonNull
    @Override
    public Role2Adapter1.Role2ViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task1_record_item, parent, false);
        return new Role2Adapter1.Role2ViewHolder1(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Role2Adapter1.Role2ViewHolder1 holder, int position) {
        final Role1Data item = recList.get(position);
//        holder.zakaz_id.setText(item.getZakNum());
        int id = item.getId();
        holder.zakaz_id.setText(String.valueOf(item.getZakNum()));
        holder.car_num.setText(item.getZakCarNum());
        holder.dat_time.setText(item.getZakDateTime());
//      добавить заполнение чекбокса в строке RV

        holder.itemView.setOnClickListener(new View.OnClickListener() { // было .itemView. стало relativeLayout
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RecordDetail.class);
                intent.putExtra("id", String.valueOf(id));
                intent.putExtra("role", String.valueOf(2));
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



    public static class Role2ViewHolder1 extends RecyclerView.ViewHolder {
        TextView zakaz_id, car_num, dat_time; // добавить признак исполнения заявки

        public Role2ViewHolder1(@NonNull View itemView) {
            super(itemView);
            zakaz_id = itemView.findViewById(R.id.tv_num);
            car_num = itemView.findViewById(R.id.tv_CarNum);
            dat_time = itemView.findViewById(R.id.tv_dat);
            // добавить признак исполнения заявки
        }
    }
}
