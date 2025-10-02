package com.example.payukproject;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
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

public class AddNewRecRole2 extends BottomSheetDialogFragment {
    public static final String TAG = "AddNewRecRole2";

    private EditText numRec, carNum, phone, carModel, note, diagnost, result, summa, datBeg, datEnd; // переменные ло полей которые будем сохранять - то есть всех
    private Button rSaveButton;
    private Role1DBHelper myDB;
    private TextView tv, id;

    String bandType = "0";

    public static AddNewRecRole2 newInstance() {
        return new AddNewRecRole2();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_new_rec_role2, container, false); // создать лайоут!!!!!!!!!
        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv = view.findViewById(R.id.tv_r2_tw);
        id = view.findViewById(R.id.tv_r2_id);
        numRec = view.findViewById(R.id.tv_r2_rec1);
        carNum = view.findViewById(R.id.tv_r2_car_num);
        phone = view.findViewById(R.id.tv_r2_phone);
        carModel = view.findViewById(R.id.tv_r2_car_model);
        note = view.findViewById(R.id.tv_r2_zak_note);
        diagnost = view.findViewById(R.id.tv_r2_diagnost);
        result = view.findViewById(R.id.tv_r2_result);
        summa = view.findViewById(R.id.tv_r2_summ);
        datBeg = view.findViewById(R.id.tv_r2_dat_b);
        datEnd = view.findViewById(R.id.tv_r2_dat_e);

        rSaveButton = view.findViewById(R.id.add_rec2);
        rSaveButton.setBackgroundColor(Color.BLUE);
//        rSaveButton.setBackgroundColor(Color.GRAY);


        // ставим текущую дате в поле дата/тайм - если это создание нового, если обновление не трогаем поле
        long timestampMillis = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String formattedDate = sdf.format(new Date(timestampMillis));
        datBeg.setText(formattedDate);


        myDB = new Role1DBHelper(getActivity());
        id.setText(String.valueOf(myDB.Role2GetMaxNum() + 1));// создаем новый заказ-наряд номер максимум из имеющихся+1


        boolean isUpdate = false;
        Bundle bundle = getArguments();
        if (bundle != null) {
//            isUpdate = true; // если прилетел свиток то это не новая запись а обновление старой, потому ведем себя иначе
            // Если прилетел список то поле numRec принимаем и делаем закрытым для редактирования!!!!!!

            numRec.setText(String.valueOf(bundle.getInt("zakNum")));
            carNum.setText(bundle.getString("zakCarNum"));
            phone.setText(bundle.getString("zakPhone"));
            carModel.setText(bundle.getString("zakCarModel"));
            note.setText(bundle.getString("zakNote"));
            bandType = bundle.getString("bandleType");// тип пересылки - 1 создано на основании предв записи, 2 - редактирование созлданной ранее записи

            if (bandType.equals("1")) {
                isUpdate = false;
                rSaveButton.setEnabled(true);
                rSaveButton.setBackgroundColor(Color.BLUE);
                tv.setText("Создание наряда на основании предварительной записи");

            } else {
                isUpdate = true;
                tv.setText("Редактирование существующей записи");
                id.setText(String.valueOf(bundle.getInt("id")));
//                numRec.setText(bundle.getString("zakNum"));
//                carNum.setText(bundle.getString("zakCarNum"));
//                phone.setText(bundle.getString("zakPhone"));
//                carModel.setText(bundle.getString("zakCarModel"));
//                note.setText(bundle.getString("zakNote"));
                diagnost.setText(bundle.getString("diagnost"));
                result.setText(bundle.getString("result"));
                summa.setText(String.valueOf(bundle.getInt("summa")));
                datBeg.setText(bundle.getString("datBegin"));
                datEnd.setText(bundle.getString("datEnd"));

// добавить признак complete !!!!
                rSaveButton.setEnabled(true);
                rSaveButton.setBackgroundColor(Color.BLUE);

            }
        }
        if (isUpdate) {
            numRec.setFocusable(false);
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


        // СОХРАНЯЕМ
        boolean finalIsUpdate = isUpdate;
        rSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // проверяем нет ли записи с тактим же номером задачи
//                int zakN = 0;
//                if (!numRec.getText().toString().isEmpty()) {
//                    zakN = Integer.parseInt(id.getText().toString());
//                }
                if (numRec.getText().toString().isEmpty()) {
                    numRec.setText("0");
                }
                int nRec = Integer.parseInt(numRec.getText().toString());
                String carN = carNum.getText().toString();
                String phoneN = phone.getText().toString();
                String modelN = carModel.getText().toString();
                String noteN = note.getText().toString();
                String diagn = diagnost.getText().toString();
                String resul = result.getText().toString();
                if (summa.getText().toString().isEmpty()) summa.setText("0");
                int sum = Integer.parseInt(summa.getText().toString());
                String dat1 = datBeg.getText().toString();
                String dat2 = datEnd.getText().toString();

                int complN = 0; //
                if (!carN.isEmpty() && !phoneN.isEmpty() && !numRec.getText().toString().isEmpty()) {
                    if (finalIsUpdate) {
                        myDB.Role2updateRecord(bundle.getInt("id"), nRec, carN, phoneN, modelN, noteN, diagn, resul, sum, dat1, dat2, complN);
                        dismiss();
                    } else {
//                        if (!myDB.Role2checkNumRecord(nRec)){
                        boolean tmp = myDB.Role2AddRecord(nRec, carN, phoneN, modelN, noteN, diagn, resul, sum, dat1, dat2, complN);
                        if (!tmp)
                            Toast.makeText(getContext(), "Ошибка БД. Запись не добавлена!!!", Toast.LENGTH_SHORT).show();
                        dismiss();
                        if (bandType.equals("1")) {
                            // здесь добавляем установку в первой таблице в записи nRec поле complete в значение 1
                            tmp = myDB.Role1SetComplete(nRec,true);
                            if (!tmp) Toast.makeText(getContext(), "In 1 table not complete set!!!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), Role2Act.class);
                            startActivity(intent);
                        }
//                        } else Toast.makeText(getContext(), "Запись с таким номером уже существует. Укажите другой номер...", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getContext(), "Для сохранения поля в рамке должны быть заполнены!!!!", Toast.LENGTH_LONG).show();
                }

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
