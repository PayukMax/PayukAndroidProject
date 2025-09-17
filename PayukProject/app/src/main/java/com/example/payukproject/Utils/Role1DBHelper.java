package com.example.payukproject.Utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.payukproject.DBTables;
import com.example.payukproject.Model.Role1Data;

import java.util.ArrayList;
import java.util.List;

public class Role1DBHelper extends SQLiteOpenHelper {

    private static final String dbName = DBTables.dbName;
    private static final String TABLE_NAME = DBTables.TABLE_NAME;

//    private static final String COL_1 = DBTables.COL_1;
//    private static final String COL_2 = DBTables.COL_2;
//    private static final String COL_3 = DBTables.COL_3;
//    private static final String COL_4 = DBTables.COL_4;
//    private static final String COL_5 = DBTables.COL_5;
//    private static final String COL_6 = DBTables.COL_6;
//    private static final String COL_7 = DBTables.COL_7;
//    private static final String COL_8 = DBTables.COL_8;
    private static final String CREATE_TABLE = DBTables.Table1Create;
            //"CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " INTEGER NOT NULL," + COL_3 + " TEXT NOT NULL, " + COL_4 + " INTEGER NOT NULL, "+COL_5+" TEXT NOT NULL,"+COL_6+" TEXT, "+COL_7+" TEXT, "+COL_8+" INTEGER);";
    private static final String DROP_TABLE = DBTables.Table1Drop;
                    //"DROP TABLE IF EXISTS " + TABLE_NAME + " ;";

    private static final String GETMAXNUM = DBTables.Table1GETMaxNum;
            //"SELECT MAX("+COL_2+") FROM "+TABLE_NAME+" ;";

    private static final String TABLE_NAME2 = DBTables.TABLE2_NAME;
//    private static final String T2_COL_1 = DBTables.T2_C_1;
//    private static final String T2_COL_2 = DBTables.T2_C_2;
//    private static final String T2_COL_3 = DBTables.T2_C_3;
//    private static final String T2_COL_4 = DBTables.T2_C_4;
//    private static final String T2_COL_5 = DBTables.T2_C_5;
//    private static final String T2_COL_6 = DBTables.T2_C_6;
//    private static final String T2_COL_7 = DBTables.T2_C_7;
//    private static final String T2_COL_8 = DBTables.T2_C_8;
//    private static final String T2_COL_9 = DBTables.T2_C_9;
//    private static final String T2_COL_10 = DBTables.T2_C_10;
//    private static final String T2_COL_11 = DBTables.T2_C_11;
//    private static final String T2_COL_12 = DBTables.T2_C_12;

    private static final String CREATE_TABLE2 = DBTables.Table2Create;
    private static final String DROP_TABLE2 = DBTables.Table2Drop;


    public Role1DBHelper(@Nullable Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
            db.execSQL(CREATE_TABLE2);
        } catch (Exception ignored) {

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        db.execSQL(DROP_TABLE2);
        onCreate(db);
    }

    public boolean Role1addRecord(int zakazId, String carNum, String datTime, String zakPhone, String zakModel, String zakNote, int complete) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBTables.COL_2, zakazId);
        cv.put(DBTables.COL_3, carNum);
        cv.put(DBTables.COL_4, datTime);
        cv.put(DBTables.COL_5, zakPhone);
        cv.put(DBTables.COL_6, zakModel);
        cv.put(DBTables.COL_7, zakNote);
        cv.put(DBTables.COL_8, complete);
        long result = db.insert(TABLE_NAME, null, cv);

//        ContentValues cv2 = new ContentValues();
//        cv2.put(COL_2, zakazId);
//        cv2.put(COL_3, carNum);
//        cv2.put(COL_4, datTime);
//        long result2 = db.insert(TABLE_NAME2, null, cv2);


        return result != -1;
    }

    @SuppressLint("Range")
    public List<Role1Data> Role1getAllRecords() {
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
                        usData.setId(cursor.getInt(cursor.getColumnIndex(DBTables.COL_1)));
                        usData.setZakNum(cursor.getInt(cursor.getColumnIndex(DBTables.COL_2)));
                        usData.setZakCarNum(cursor.getString(cursor.getColumnIndex(DBTables.COL_3)));
                        usData.setZakDateTime(cursor.getString(cursor.getColumnIndex(DBTables.COL_4)));
                        usData.setZakPhone(cursor.getString(cursor.getColumnIndex(DBTables.COL_5)));                        // добавить занесение признака выполнения работ
                        usData.setZakCarModel(cursor.getString(cursor.getColumnIndex(DBTables.COL_6)));
                        usData.setZakNote(cursor.getString(cursor.getColumnIndex(DBTables.COL_7)));
                        usData.setCompleted(cursor.getInt(cursor.getColumnIndex(DBTables.COL_8)));
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

    public void Role1updateRecord(int id, int zakazId, String carNum, String datTime, String zakPhone, String zakModel, String zakNote, int complete) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBTables.COL_2, zakazId);
        cv.put(DBTables.COL_3, carNum);
        cv.put(DBTables.COL_4, datTime);
        cv.put(DBTables.COL_5, zakPhone);
        cv.put(DBTables.COL_6, zakModel);
        cv.put(DBTables.COL_7, zakNote);
        cv.put(DBTables.COL_8, complete);

        db.update(TABLE_NAME, cv, "ID=?", new String[]{String.valueOf(id)});
    }

    public int Role1getMaxNum(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cv = db.rawQuery(GETMAXNUM,null);
        int tmp = 0;
        if (cv != null) {
            try {
                if (cv.moveToFirst()) {
                    tmp = cv.getInt(0);
                }
            } finally {
                cv.close();
            }
        }
        return tmp;
    }


    public void Role1deleteRecord(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id=?", new String[]{String.valueOf(id)});
    }

    public boolean Role1checkNumRecord(int zakN) {// проверяем записи на предмет совпадения кода переданного снаружи и кода заказа в имеющихся записях. Есть совпадение - true
        List<Role1Data> list = Role1getAllRecords();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getZakNum() == zakN) return true;
        }
        return false;
    }

    // методы для роли 2

//    public static final String T2_C_1 = "id";
//    public static final String T2_C_2 = "id_nar";
//    public static final String T2_C_3 = "car_num";
//    public static final String T2_C_4 = "zak_phone";
//    public static final String T2_C_5 = "zak_car_model";
//    public static final String T2_C_6 = "zak_note";
//    public static final String T2_C_7 = "diagnost";
//    public static final String T2_C_8 = "result";
//    public static final String T2_C_9 = "summa";
//    public static final String T2_C_10 = "dat_begin";
//    public static final String T2_C_11 = "dat_end";
//    public static final String T2_C_12 = "complete";



    public boolean Role2AddRecord(int zakId, String remCarNum, String remPhone, String remCarModel, String zakNote, String remDiagnost, String remResult, int remSumma , String remDatBegin, String remDatEnd, int complete) {// ПАРВИТЬ!!!!
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBTables.T2_C_2, zakId);
        cv.put(DBTables.T2_C_3, remCarNum);
        cv.put(DBTables.T2_C_4, remPhone);
        cv.put(DBTables.T2_C_5, remCarModel);
        cv.put(DBTables.T2_C_6, zakNote);
        cv.put(DBTables.T2_C_7, remDiagnost);
        cv.put(DBTables.T2_C_8, remResult);
        cv.put(DBTables.T2_C_9, remSumma);
        cv.put(DBTables.T2_C_10, remDatBegin);
        cv.put(DBTables.T2_C_11, remDatEnd);
        cv.put(DBTables.T2_C_12, complete);

        long result = db.insert(DBTables.TABLE2_NAME, null, cv);
        return result != -1;
    }

    public void Role2deleteRecord(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DBTables.TABLE2_NAME, "id=?", new String[]{String.valueOf(id)});
    }

    public int Role2GetMaxNum(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cv = db.rawQuery(DBTables.Table2GETMaxNum,null);
        int tmp = 0;
        if (cv != null) {
            try {
                if (cv.moveToFirst()) {
                    tmp = cv.getInt(0);
                }
            } finally {
                cv.close();
            }
        }
        return tmp;
    }



}
