package com.example.payukproject.Adaptor;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payukproject.R;

public class Role1ViewHolder extends RecyclerView.ViewHolder{

    TextView zakaz_id, car_num, dat_time; // добавить признак исполнения заявки

    public Role1ViewHolder(@NonNull View itemView) {
        super(itemView);
        zakaz_id = itemView.findViewById(R.id.tv_num);
        car_num = itemView.findViewById(R.id.tv_CarNum);
        dat_time = itemView.findViewById(R.id.tv_dat);
        // добавить признак исполнения заявки
    }
}
