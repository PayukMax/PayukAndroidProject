package com.example.payukproject;

import android.app.AlertDialog;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.payukproject.Adaptor.UsListAdaptor;

public class RecyclerViewTouchHelper extends ItemTouchHelper.SimpleCallback{
    UsListAdaptor adaptor;

    public RecyclerViewTouchHelper(UsListAdaptor adaptor) {
        super(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT);
        this.adaptor = adaptor;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        final int position = viewHolder.getAdapterPosition();
        if (direction==ItemTouchHelper.RIGHT){
            AlertDialog.Builder builder = new AlertDialog.Builder(adaptor.getContext());
            builder.setTitle("Удалить пользователя?");
            builder.setMessage("Уверены?");
            builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    adaptor.deleteUser(position);
                }
            });
            builder.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    adaptor.notifyItemChanged(position);
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            adaptor.editUser(position);
        }
    }
}
