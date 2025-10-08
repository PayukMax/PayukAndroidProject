package com.example.payukproject.Utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.payukproject.DBTables;
import com.example.payukproject.Model.Role1Data;
import com.example.payukproject.Model.Role2Data;
import com.example.payukproject.Model.Role3Data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
//    private static final String CREATE_TABLE = DBTables.Table1Create;
    //"CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " INTEGER NOT NULL," + COL_3 + " TEXT NOT NULL, " + COL_4 + " INTEGER NOT NULL, "+COL_5+" TEXT NOT NULL,"+COL_6+" TEXT, "+COL_7+" TEXT, "+COL_8+" INTEGER);";
//    private static final String DROP_TABLE = DBTables.Table1Drop;
    //"DROP TABLE IF EXISTS " + TABLE_NAME + " ;";

    private static final String GETMAXNUM = DBTables.Table1GETMaxNum;
    private static final String GETNOTCOMPLETE1 = DBTables.Table1GETNoComplete;

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

//    private static final String CREATE_TABLE2 = DBTables.Table2Create;
//    private static final String DROP_TABLE2 = DBTables.Table2Drop;
//    private static final String GETNOTCOMPLETE2 = DBTables.Table2GETNoComplete;

    public Role1DBHelper(@Nullable Context context) {
        super(context, DBTables.dbName, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(DBTables.Table1Create);
            db.execSQL(DBTables.Table2Create);
            db.execSQL(DBTables.Table3Create);

        } catch (Exception ignored) {

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion>oldVersion){
            db.execSQL(DBTables.Table1Drop);
            db.execSQL(DBTables.Table2Drop);
            db.execSQL(DBTables.Table3Drop);
            onCreate(db);
        }

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
        long result = db.insert(DBTables.TABLE_NAME, null, cv);

//        ContentValues cv2 = new ContentValues();
//        cv2.put(COL_2, zakazId);
//        cv2.put(COL_3, carNum);
//        cv2.put(COL_4, datTime);
//        long result2 = db.insert(TABLE_NAME2, null, cv2);


        return result != -1;
    }

    @SuppressLint("Range")
    public List<Role1Data> Role1getAllRecords(boolean flag) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        List<Role1Data> recordList = new ArrayList<>();

        db.beginTransaction();
        try {
            if (flag) {
                cursor = db.query(TABLE_NAME, null, null, null, null, null, null, null);
            } else {
                cursor = db.rawQuery(GETNOTCOMPLETE1, null);
            }

            if (cursor != null) {
                if (cursor.moveToFirst()) {
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

    public int Role1getMaxNum() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cv = db.rawQuery(GETMAXNUM, null);
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


    public void Role1deleteRecord(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id=?", new String[]{String.valueOf(id)});
    }

    public boolean Role1checkNumRecord(int zakN) {// проверяем записи на предмет совпадения кода переданного снаружи и кода заказа в имеющихся записях. Есть совпадение - true
        List<Role1Data> list = Role1getAllRecords(false);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getZakNum() == zakN) return true;
        }
        return false;
    }


    public boolean Role1SetComplete(int zakazId, boolean b) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
//        cv.put(DBTables.COL_2, zakazId);
        if (b) cv.put(DBTables.COL_8, 1);
        else cv.put(DBTables.COL_8, 0);

        int x = db.update(TABLE_NAME, cv, "zakaz_id=?", new String[]{String.valueOf(zakazId)});
        if (x <= 0) return false;
        else return true;
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


    @SuppressLint("Range")
    public List<Role2Data> Role2getAllRecords(boolean flag) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        List<Role2Data> recordList = new ArrayList<>();

        db.beginTransaction();
        try {
            if (flag) {
                cursor = db.query(DBTables.TABLE2_NAME, null, null, null, null, null, null, null);
            } else {
                cursor = db.rawQuery(DBTables.Table2GETNoComplete, null);
            }

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Role2Data usData = new Role2Data();
                        usData.setId(cursor.getInt(cursor.getColumnIndex(DBTables.T2_C_1)));
                        usData.setRemNum(cursor.getInt(cursor.getColumnIndex(DBTables.T2_C_2)));
                        usData.setRemCarNum(cursor.getString(cursor.getColumnIndex(DBTables.T2_C_3)));
                        usData.setRemPhone(cursor.getString(cursor.getColumnIndex(DBTables.T2_C_4)));
                        usData.setRemCarModel(cursor.getString(cursor.getColumnIndex(DBTables.T2_C_5)));                        // добавить занесение признака выполнения работ
                        usData.setRemNote(cursor.getString(cursor.getColumnIndex(DBTables.T2_C_6)));
                        usData.setRemDiagnost(cursor.getString(cursor.getColumnIndex(DBTables.T2_C_7)));
                        usData.setRemResult(cursor.getString(cursor.getColumnIndex(DBTables.T2_C_8)));
                        usData.setRemSumma(cursor.getInt(cursor.getColumnIndex(DBTables.T2_C_9)));
                        usData.setRemDateBegin(cursor.getString(cursor.getColumnIndex(DBTables.T2_C_10)));
                        usData.setRemDateEnd(cursor.getString(cursor.getColumnIndex(DBTables.T2_C_11)));
                        usData.setRemComplete(cursor.getInt(cursor.getColumnIndex(DBTables.T2_C_12)));
                        usData.setRemPlatComplete(cursor.getInt(cursor.getColumnIndex(DBTables.T2_C_13)));
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

    @SuppressLint("Range")
    public List<Role2Data> Role2getAllClosedRecordsPlat(boolean flag) { // если false то complete=1 plat=0 , если true то complete=1 plat=1
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        List<Role2Data> recordList = new ArrayList<>();

        db.beginTransaction();
        try {
            if (flag) {
//                cursor = db.query(DBTables.TABLE2_NAME, null, null, null, null, null, null, null);
                cursor = db.rawQuery(DBTables.Table2GETCompleteAndPlat, null);
            } else {
                cursor = db.rawQuery(DBTables.Table2GETCompleteNotPlat, null);
            }

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Role2Data usData = new Role2Data();
                        usData.setId(cursor.getInt(cursor.getColumnIndex(DBTables.T2_C_1)));
                        usData.setRemNum(cursor.getInt(cursor.getColumnIndex(DBTables.T2_C_2)));
                        usData.setRemCarNum(cursor.getString(cursor.getColumnIndex(DBTables.T2_C_3)));
                        usData.setRemPhone(cursor.getString(cursor.getColumnIndex(DBTables.T2_C_4)));
                        usData.setRemCarModel(cursor.getString(cursor.getColumnIndex(DBTables.T2_C_5)));                        // добавить занесение признака выполнения работ
                        usData.setRemNote(cursor.getString(cursor.getColumnIndex(DBTables.T2_C_6)));
                        usData.setRemDiagnost(cursor.getString(cursor.getColumnIndex(DBTables.T2_C_7)));
                        usData.setRemResult(cursor.getString(cursor.getColumnIndex(DBTables.T2_C_8)));
                        usData.setRemSumma(cursor.getInt(cursor.getColumnIndex(DBTables.T2_C_9)));
                        usData.setRemDateBegin(cursor.getString(cursor.getColumnIndex(DBTables.T2_C_10)));
                        usData.setRemDateEnd(cursor.getString(cursor.getColumnIndex(DBTables.T2_C_11)));
                        usData.setRemComplete(cursor.getInt(cursor.getColumnIndex(DBTables.T2_C_12)));
                        usData.setRemPlatComplete(cursor.getInt(cursor.getColumnIndex(DBTables.T2_C_13)));
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


    public boolean Role2AddRecord(int zakId, String remCarNum, String remPhone, String remCarModel, String zakNote, String remDiagnost, String remResult, int remSumma, String remDatBegin, String remDatEnd, int complete, int plat) {// ПАРВИТЬ!!!!
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
        cv.put(DBTables.T2_C_13, plat);

        long result = db.insert(DBTables.TABLE2_NAME, null, cv);
        return result != -1;
    }

    public void Role2updateRecord(int id, int zakazId, String carNum, String zakPhone, String zakModel, String zakNote, String diagnost, String result, int summa, String datBeg, String datEnd, int complete, int plat) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBTables.T2_C_2, zakazId);
        cv.put(DBTables.T2_C_3, carNum);
        cv.put(DBTables.T2_C_4, zakPhone);
        cv.put(DBTables.T2_C_5, zakModel);
        cv.put(DBTables.T2_C_6, zakNote);
        cv.put(DBTables.T2_C_7, diagnost);
        cv.put(DBTables.T2_C_8, result);
        cv.put(DBTables.T2_C_9, summa);
        cv.put(DBTables.T2_C_10, datBeg);
        cv.put(DBTables.T2_C_11, datEnd);
        cv.put(DBTables.T2_C_12, complete);
        cv.put(DBTables.T2_C_13, plat);

        db.update(DBTables.TABLE2_NAME, cv, "ID=?", new String[]{String.valueOf(id)});
    }

    public void Role2deleteRecord(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DBTables.TABLE2_NAME, "id=?", new String[]{String.valueOf(id)});
    }

    public int Role2GetMaxNum() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cv = db.rawQuery(DBTables.Table2GETMaxNum, null);
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

    public boolean Role2SetComplete(int zakazId, boolean b) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
//        cv.put(DBTables.COL_2, zakazId);
        if (b) {
            long timestampMillis = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            String formattedDate = sdf.format(new Date(timestampMillis));
            cv.put(DBTables.T2_C_11, formattedDate);
            cv.put(DBTables.T2_C_12, 1);
        } else {
            cv.put(DBTables.T2_C_11, "XXXX-XX-XX yy:yy:yy");
            cv.put(DBTables.T2_C_12, 0);
        }

        int x = db.update(TABLE_NAME2, cv, "id=?", new String[]{String.valueOf(zakazId)});
        if (x <= 0) return false;
        else return true;
    }

    public boolean Role2SetPlat(int id, boolean b) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (b) {
            cv.put(DBTables.T2_C_13, 1);
        } else {
            cv.put(DBTables.T2_C_13, 0);
        }
        int x = db.update(TABLE_NAME2, cv, "id=?", new String[]{String.valueOf(id)});

        if(b) {
            String selectQuery = "SELECT * FROM " + DBTables.TABLE2_NAME + " WHERE " + DBTables.T2_C_1 + " = \"" + id + "\"";
            Cursor cursor = db.rawQuery(selectQuery, null);
            String car_num="", phone="", note="", platDat="";
            int summa=0;
            if (cursor.moveToFirst()) {
                do {
                    car_num = cursor.getString(cursor.getColumnIndexOrThrow(DBTables.T2_C_3));
                    phone = cursor.getString(cursor.getColumnIndexOrThrow(DBTables.T2_C_4));
                    summa = Integer.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(DBTables.T2_C_9)));
                    long timestampMillis = System.currentTimeMillis();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    platDat = sdf.format(new Date(timestampMillis));
                } while (cursor.moveToNext());
            }
            db.close();
// !!!!!!! Не очень красиво сделано, надо сделать изящнее!!!!!!!!
            Role3AddRecord(id, car_num, phone, summa , note, platDat);
        }
        if (x <= 0) return false;
        else return true;
    }


//    public boolean Role2checkNumRecord(int zakN) {// проверяем записи на предмет совпадения кода переданного снаружи и кода заказа в имеющихся записях. Есть совпадение - true
//        List<Role2Data> list = Role2getAllRecords();
//
//        for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).getId() == zakN) return true;
//        }
//        return false;
//    }

    // методы для роли 3

    @SuppressLint("Range")
    public List<Role3Data> Role3getAllRecords() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        List<Role3Data> recordList = new ArrayList<>();

        db.beginTransaction();
        try {
            cursor = db.query(DBTables.TABLE3_NAME, null, null, null, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Role3Data usData = new Role3Data();
                        usData.setId(cursor.getInt(cursor.getColumnIndex(DBTables.T3_C_1)));
                        usData.setRemNum(cursor.getInt(cursor.getColumnIndex(DBTables.T3_C_2)));
                        usData.setRemCarNum(cursor.getString(cursor.getColumnIndex(DBTables.T3_C_3)));
                        usData.setRemPhone(cursor.getString(cursor.getColumnIndex(DBTables.T3_C_4)));
                        usData.setRemSumma(cursor.getInt(cursor.getColumnIndex(DBTables.T3_C_5)));
                        usData.setRemPlatComplete(cursor.getString(cursor.getColumnIndex(DBTables.T3_C_6)));
                        usData.setRemNote(cursor.getString(cursor.getColumnIndex(DBTables.T3_C_7)));
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

    public boolean Role3AddRecord(int narId, String platCarNum, String platPhone, int platSumma, String platNote, String platDat) {// ПАРВИТЬ!!!!
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBTables.T3_C_2, narId);
        cv.put(DBTables.T3_C_3, platCarNum);
        cv.put(DBTables.T3_C_4, platPhone);
        cv.put(DBTables.T3_C_5, platSumma);
        cv.put(DBTables.T3_C_6, platDat);
        cv.put(DBTables.T3_C_7, platNote);

        long result = db.insert(DBTables.TABLE3_NAME, null, cv);
        return result != -1;
    }

    public void Role3updateRecord(int id, int narId, String carNum, String Phone, int summa, String datPlat, String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(DBTables.T3_C_2, narId);
        cv.put(DBTables.T3_C_3, carNum);
        cv.put(DBTables.T3_C_4, Phone);
        cv.put(DBTables.T3_C_5, summa);
        cv.put(DBTables.T3_C_6, datPlat);
        cv.put(DBTables.T3_C_7, note);

        db.update(DBTables.TABLE3_NAME, cv, "ID=?", new String[]{String.valueOf(id)});
    }

    public void Role3deleteRecord(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DBTables.TABLE3_NAME, "id=?", new String[]{String.valueOf(id)});
    }



}
