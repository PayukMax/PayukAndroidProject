package com.example.payukproject;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.payukproject.Utils.DBHelper;
import com.example.payukproject.Utils.Role1DBHelper;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddNewRecRole1 extends BottomSheetDialogFragment {
    public static final String TAG = "AddNewRecRole1";

    private EditText numRec, carNum, datTime, phone, carModel, note;
    private Button rSaveButton;
    private Role1DBHelper myDB;

    public static AddNewRecRole1 newInstance() {
        return new AddNewRecRole1();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_new_rec_role1, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        numRec = view.findViewById(R.id.et_num_rec);
        carNum = view.findViewById(R.id.et_car_num);
        datTime = view.findViewById(R.id.et_date_time);
        phone = view.findViewById(R.id.et_phone);
        carModel = view.findViewById(R.id.et_car_model);
        note = view.findViewById(R.id.et_note);
        rSaveButton = view.findViewById(R.id.add_rec_btn);

        myDB = new Role1DBHelper(getActivity());
        boolean isUpdate = false;
        Bundle bundle = getArguments();
        if (bundle != null) {
            isUpdate = true; // если прилетел свиток то это не новая запись а обновление старой, потому ведем себя иначе
//            int t1 = bundle.getInt("id");
//            String t2 = bundle.getString("user");
//            String t3 = bundle.getString("passw");
//            int t4 = bundle.getInt("role");

//            mUser.setText(t2);
//            mPasw.setText(t3);
//            mRole.setText(String.valueOf(t4));
//            if (t2.length() > 0) {
//                mSaveButton.setEnabled(false);
//            }
        }
        carNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    rSaveButton.setEnabled(false);
                    rSaveButton.setBackgroundColor(Color.GRAY);
                } else {
                    rSaveButton.setEnabled(true);
                    rSaveButton.setBackgroundColor(Color.BLUE);
                }
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (s.toString().equals("")) {
                    rSaveButton.setEnabled(false);
                    rSaveButton.setBackgroundColor(Color.GRAY);
                } else {
                    rSaveButton.setEnabled(true);
                    rSaveButton.setBackgroundColor(Color.BLUE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        boolean finalIsUpdate = isUpdate;
        rSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int zakN = Integer.parseInt(numRec.getText().toString());
                String carN = carNum.getText().toString();
                String datN = datTime.getText().toString();
                String phoneN = phone.getText().toString();
                String modelN = carModel.getText().toString();
                String noteN = note.getText().toString();
                int complN = 0;

                if (finalIsUpdate) {
//                    myDB.updateUser(bundle.getInt("id"), tUser, tPass, tRole);
                } else {
//                    if (myDB.checkUserName(tUser)) {
//                        Toast.makeText(getContext(), "Такой пользователь уже существует. Укажите другое имя...", Toast.LENGTH_SHORT).show();
//                    } else {
                        myDB.addRecord(zakN, carN, datN, phoneN, modelN, noteN, complN);
//                    }
                }
                dismiss();
            }
        });
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        Activity activity = getActivity();
        if (activity instanceof OnDialogCloseListener) {
            ((OnDialogCloseListener) activity).onDialogClose(dialog);
        }
    }

}
