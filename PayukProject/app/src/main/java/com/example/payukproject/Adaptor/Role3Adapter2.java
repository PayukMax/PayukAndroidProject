package com.example.payukproject.Adaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payukproject.Model.Role2Data;
import com.example.payukproject.Model.Role3Data;
import com.example.payukproject.R;
import com.example.payukproject.Record2Detail;
import com.example.payukproject.Role3Act;
import com.example.payukproject.Utils.Role1DBHelper;

import java.util.List;

public class Role3Adapter2  extends RecyclerView.Adapter<Role3Adapter2.Role3ViewHolder2> {
    private List<Role3Data> recList3;
    private Context context;
    private Role1DBHelper roleDB;
    private Role3Act activity;

    public Role3Adapter2(Role1DBHelper roleDB, Role3Act activity,  Context context) {
        this.context = context;
        this.roleDB = roleDB;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Role3Adapter2.Role3ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task3_record_item, parent, false);
        return new Role3Adapter2.Role3ViewHolder2(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Role3ViewHolder2 holder, int position) {
        final Role3Data item = recList3.get(position);
        int id = item.getId();
        holder.item_id.setText(String.valueOf(item.getId()));
        holder.car_num.setText(item.getRemCarNum());
        holder.phone.setText(item.getRemPhone());
        holder.summa.setText(String.valueOf(item.getRemSumma()));
        holder.data.setText(item.getRemPlatComplete());

////      добавить заполнение чекбокса в строке RV

        holder.itemView.setOnClickListener(new View.OnClickListener() { // было .itemView. стало relativeLayout
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, Record3Detail.class);
//                intent.putExtra("id", String.valueOf(id));
//                intent.putExtra("role", String.valueOf(3));
//                context.startActivity(intent);
            }
        });
    }

    public Context getContext() {
        return activity;
    }

    @Override
    public int getItemCount() {
        return recList3.size();
    }

    public void setUser(List<Role3Data> List) {
        this.recList3 = List;
        notifyDataSetChanged();
    }


    public static class Role3ViewHolder2 extends RecyclerView.ViewHolder {
        TextView item_id, car_num, phone, summa, data; // актуальную структуру!!!!!!!!
//        CheckBox compl;

        public Role3ViewHolder2(@NonNull View itemView) {// ПРАВИТЬ!!!!!
            super(itemView);
            item_id = itemView.findViewById(R.id.t3_nar);
            car_num = itemView.findViewById(R.id.t3_car_num);
            phone = itemView.findViewById(R.id.t3_phone);
            summa = itemView.findViewById(R.id.t3_summa);
            data = itemView.findViewById(R.id.t3_dat);


            // добавить признак исполнения заявки
        }
    }

}
