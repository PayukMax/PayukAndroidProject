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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.payukproject.Utils.Role1DBHelper;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddNewRecRole1 extends BottomSheetDialogFragment {
    public static final String TAG = "AddNewRecRole1";

    private EditText numRec, carNum, datTime, phone, carModel, note;
    private Button rSaveButton;
    private Role1DBHelper myDB;
    private TextView tv;

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

        tv = view.findViewById(R.id.textView6);
        numRec = view.findViewById(R.id.et_num_rec);
        carNum = view.findViewById(R.id.et_car_num);
        datTime = view.findViewById(R.id.et_date_time);
        phone = view.findViewById(R.id.et_phone);
        carModel = view.findViewById(R.id.et_car_model);
        note = view.findViewById(R.id.et_note);
        rSaveButton = view.findViewById(R.id.add_rec_btn);
        rSaveButton.setBackgroundColor(Color.GRAY);
        // ставим текущую дате в поле дата/тайм
        long timestampMillis = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String formattedDate = sdf.format(new Date(timestampMillis));
        datTime.setText(formattedDate);


        myDB = new Role1DBHelper(getActivity());
        numRec.setText(String.valueOf(myDB.Role1getMaxNum() + 1));

        boolean isUpdate = false;
        Bundle bundle = getArguments();
        if (bundle != null) {
            isUpdate = true; // если прилетел свиток то это не новая запись а обновление старой, потому ведем себя иначе
            numRec.setText(String.valueOf(bundle.getInt("zakNum")));
            carNum.setText(bundle.getString("zakCarNum"));
            datTime.setText(bundle.getString("zakDateTime"));
            phone.setText(bundle.getString("zakPhone"));
            carModel.setText(bundle.getString("zakCarModel"));
            note.setText(bundle.getString("zakNote"));
            rSaveButton.setBackgroundColor(Color.BLUE);
            tv.setText("Редактирование существующей записи");
//
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
                if (s.toString().equals("") || phone.getText().toString().equals("")) {
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
                if (s.toString().equals("") || carNum.getText().toString().equals("")) {
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
                // проверяем нет ли записи с тактим же номером задачи
                int zakN = 0;
                if (!numRec.getText().toString().isEmpty()) {
                    zakN = Integer.parseInt(numRec.getText().toString());
                }
                if (!myDB.Role1checkNumRecord(zakN)) {
                    String carN = carNum.getText().toString();
                    String datN = datTime.getText().toString();
                    String phoneN = phone.getText().toString();
                    String modelN = carModel.getText().toString();
                    String noteN = note.getText().toString();
                    int complN = 0;
                    if (!carN.isEmpty() && !phoneN.isEmpty() && !numRec.getText().toString().isEmpty()) {
                        if (finalIsUpdate) {
                            myDB.Role1updateRecord(bundle.getInt("id"), zakN, carN, datN, phoneN, modelN, noteN, complN);
                        } else {
                           boolean tmp= myDB.Role1addRecord(zakN, carN, datN, phoneN, modelN, noteN, complN);
                           if (!tmp) Toast.makeText(getContext(), "Ошибка БД. Запись не добавлена!!!", Toast.LENGTH_SHORT).show();
                        }
                        dismiss();
                    } else {
                        Toast.makeText(getContext(), "Для сохранения поля в рамке должны быть заполнены!!!!", Toast.LENGTH_LONG).show();
                    }
                } else {Toast.makeText(getContext(), "Запись с таким номером уже существует. Укажите другой номер...", Toast.LENGTH_SHORT).show();}
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
