package com.example.payukproject;

public class DBTables {
    public static final String dbName = "project1.db";
    public static final String TABLE_NAME = "role1";

    public static final String COL_1 = "id";
    public static final String COL_2 = "zakaz_id";
    public static final String COL_3 = "car_num";
    public static final String COL_4 = "date_time";
    public static final String COL_5 = "zak_phone";
    public static final String COL_6 = "zak_car_model";
    public static final String COL_7 = "zak_note";
    public static final String COL_8 = "complete";

    public static final String Table1Create = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " INTEGER NOT NULL," + COL_3 + " TEXT NOT NULL, " + COL_4 + " INTEGER NOT NULL, "+COL_5+" TEXT NOT NULL,"+COL_6+" TEXT, "+COL_7+" TEXT, "+COL_8+" INTEGER);";
    public static final String Table1Drop = "DROP TABLE IF EXISTS " + TABLE_NAME + " ;";
    public static final String Table1GETMaxNum = "SELECT MAX("+COL_2+") FROM "+TABLE_NAME+" ;";


    public static final String TABLE2_NAME = "role2";
    public static final String T2_C_1 = "id";
    public static final String T2_C_2 = "id_nar";
    public static final String T2_C_3 = "car_num";
    public static final String T2_C_4 = "zak_phone";
    public static final String T2_C_5 = "zak_car_model";
    public static final String T2_C_6 = "zak_note";
    public static final String T2_C_7 = "diagnost";
    public static final String T2_C_8 = "result";
    public static final String T2_C_9 = "summa";
    public static final String T2_C_10 = "dat_begin";
    public static final String T2_C_11 = "dat_end";
    public static final String T2_C_12 = "complete";

    public static final String Table2Create = "CREATE TABLE IF NOT EXISTS " + TABLE2_NAME + " (" + T2_C_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + T2_C_2 + " INTEGER NOT NULL," + T2_C_3 + " TEXT NOT NULL, " + T2_C_4 + " TEXT NOT NULL,"+T2_C_5+" TEXT,"+T2_C_6+" TEXT, "+T2_C_7+" TEXT,"+T2_C_8+" TEXT,"+T2_C_9+" INTEGER,"+T2_C_10+" TEXT NOT NULL ,"+T2_C_11+" TEXT, "+T2_C_12+" INTEGER );";
    public static final String Table2Drop = "DROP TABLE IF EXISTS " + TABLE2_NAME + " ;";
    public static final String Table2GETMaxNum = "SELECT MAX("+T2_C_1+") FROM "+TABLE2_NAME+" ;";
}
