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
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddNewUser extends BottomSheetDialogFragment {
    public static final String TAG = "AddNewUser";

    private EditText mUser, mPasw, mRole;
    private Button mSaveButton;
    private DBHelper myDB;

    public static AddNewUser newInstance() {
        return new AddNewUser();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_new_user, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mUser = view.findViewById(R.id.n_name);
        mPasw = view.findViewById(R.id.n_passw);
        mRole = view.findViewById(R.id.n_role);
        mSaveButton = view.findViewById(R.id.btn_Save);
//        role_selector = view.findViewById(R.id.roleSelector);

        myDB = new DBHelper(getActivity());
        boolean isUpdate = false;
        Bundle bundle = getArguments();
        if (bundle != null) {
            isUpdate = true;
            int t1 = bundle.getInt("id");
            String t2 = bundle.getString("user");
            String t3 = bundle.getString("passw");
            int t4 = bundle.getInt("role");

            mUser.setText(t2);
            mPasw.setText(t3);
            mRole.setText(String.valueOf(t4));
            if (t2.length() > 0) {
                mSaveButton.setEnabled(false);
            }
        }
        mUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")) {
                    mSaveButton.setEnabled(false);
                    mSaveButton.setBackgroundColor(Color.GRAY);
                } else {
                    mSaveButton.setEnabled(true);
                    mSaveButton.setBackgroundColor(Color.BLUE);
                }
            }


            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPasw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if (s.toString().equals("")) {
                    mSaveButton.setEnabled(false);
                    mSaveButton.setBackgroundColor(Color.GRAY);
                } else {
                    mSaveButton.setEnabled(true);
                    mSaveButton.setBackgroundColor(Color.BLUE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        boolean finalIsUpdate = isUpdate;
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tUser = mUser.getText().toString();
                String tPass = mPasw.getText().toString();
                int tRole = Integer.parseInt(mRole.getText().toString());

                if (finalIsUpdate) {
                    myDB.updateUser(bundle.getInt("id"), tUser, tPass, tRole);
                } else {
                    if (myDB.checkUserName(tUser)) {
                        Toast.makeText(getContext(), "Такой пользователь уже существует. Укажите другое имя...", Toast.LENGTH_SHORT).show();
                    } else {
                        myDB.addUser(tUser, tPass, tRole);
                    }
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
