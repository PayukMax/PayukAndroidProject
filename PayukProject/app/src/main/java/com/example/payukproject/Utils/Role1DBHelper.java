package com.example.payukproject.Utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.payukproject.Model.Role1Data;
import com.example.payukproject.Model.UserData;
import com.example.payukproject.Role1Act;

import java.util.ArrayList;
import java.util.List;

public class Role1DBHelper extends SQLiteOpenHelper {

    private static final String dbName = "project1.db";
    private static final String TABLE_NAME = "role1";//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private static final String COL_1 = "id";
    private static final String COL_2 = "zakaz_id";
    private static final String COL_3 = "car_num";
    private static final String COL_4 = "date_time";
    private static final String COL_5 = "zak_phone";
    private static final String COL_6 = "zak_car_model";
    private static final String COL_7 = "zak_note";
    private static final String COL_8 = "complete";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " INTEGER NOT NULL," + COL_3 + " TEXT NOT NULL, " + COL_4 + " INTEGER NOT NULL, "+COL_5+" TEXT NOT NULL,"+COL_6+" TEXT, "+COL_7+" TEXT, "+COL_8+" INTEGER);";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME + " ;";

    public Role1DBHelper(@Nullable Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
        } catch (Exception ignored) {

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public boolean addRecord(int zakazId, String carNum, String datTime, String zakPhone,String zakModel, String zakNote, int complete) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2, zakazId);
        cv.put(COL_3, carNum);
        cv.put(COL_4, datTime);
        cv.put(COL_5, zakPhone);
        cv.put(COL_6, zakModel);
        cv.put(COL_7, zakNote);
        cv.put(COL_8, complete);

        long result = db.insert(TABLE_NAME, null, cv);
        return result != -1;
    }

    @SuppressLint("Range")
    public List<Role1Data> getAllRecords() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        List<Role1Data> recordList = new ArrayList<>();

        db.beginTransaction();
        try {
            cursor = db.query(TABLE_NAME,null,null,null,null,null,null,null);
            if (cursor != null){
                if (cursor.moveToFirst()){
                    do {
                        Role1Data usData = new Role1Data();
                        usData.setId(cursor.getInt(cursor.getColumnIndex(COL_1)));
                        usData.setZakNum(cursor.getInt(cursor.getColumnIndex(COL_2)));
                        usData.setZakCarNum(cursor.getString(cursor.getColumnIndex(COL_3)));
                        usData.setZakDateTime(cursor.getString(cursor.getColumnIndex(COL_4)));
                        usData.setZakPhone(cursor.getString(cursor.getColumnIndex(COL_5)));                        // добавить занесение признака выполнения работ
                        usData.setZakCarModel(cursor.getString(cursor.getColumnIndex(COL_6)));
                        usData.setZakNote(cursor.getString(cursor.getColumnIndex(COL_7)));
                        usData.setCompleted(cursor.getInt(cursor.getColumnIndex(COL_8)));
                        recordList.add(usData);
                    } while (cursor.moveToNext());
                }
            }

        } finally {
            db.endTransaction();
            cursor.close();
        }
        return recordList;
    }

    public void updateUser(int id, String name, String passw, int role) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2, name);
        cv.put(COL_3, Crpt.Encrypte(passw)); // вот тут надо приправить пароль солью и сохранить хеш
        cv.put(COL_4, role);
        db.update(TABLE_NAME, cv, "ID=?", new String[]{String.valueOf(id)});
    }

    public void deleteUser(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id=?", new String[]{String.valueOf(id)});
    }

}
