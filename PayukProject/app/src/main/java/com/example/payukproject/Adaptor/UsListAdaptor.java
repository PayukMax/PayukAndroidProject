package com.example.payukproject.Adaptor;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payukproject.AddNewUser;
import com.example.payukproject.AdminAct;
import com.example.payukproject.Model.UserData;
import com.example.payukproject.R;
import com.example.payukproject.Utils.DBHelper;

import java.util.List;

public class UsListAdaptor extends RecyclerView.Adapter<UsListAdaptor.MyViewHolder>{
    private List<UserData> datList;
    private AdminAct activity;
    private DBHelper myDB;

    public UsListAdaptor(DBHelper myDB, AdminAct activity) {
        this.myDB = myDB;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_layout, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UsListAdaptor.MyViewHolder holder, int position) {
        final UserData item = datList.get(position);
        String tmp = item.getId()+" - "+item.getName()+" - "+item.getPassw()+" - "+item.getRole();
        holder.user_line.setText(tmp); //
    }

    public Context getContext(){
        return activity;
    }

    public void setUser (List<UserData> mList){
        this.datList = mList;
        notifyDataSetChanged();
    }

    public void deleteUser(int position){
        UserData item = datList.get(position);
        myDB.deleteUser(item.getId());
        datList.remove(position);
        notifyItemRemoved(position);
    }

    public void editUser (int position){
        // код при редактировании записи юзера, аналог editItems
        UserData item = datList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("user", item.getName());
        bundle.putString("passw", item.getPassw());
        bundle.putInt("role", item.getRole());
        AddNewUser nUser = new AddNewUser();
        nUser.setArguments(bundle);
        nUser.show(activity.getSupportFragmentManager(), nUser.getTag());

    }

    @Override
    public int getItemCount() {
        return datList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView user_line;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            user_line = itemView.findViewById(R.id.user_line);
        }
    }
}
