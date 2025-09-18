package com.example.payukproject.Model;

public class Role2Data {
    private int id;
    private int remNum; //номер заказа - из таблицы записи
    private String remCarNum; // гос номер автомобиля
    private String remPhone; //  телефон владельца для связи
    private String remCarModel; // модель автомобиля ?????
    private String remNote; // комментарий о твладельца по необходимым работам
    private String remDiagnost; // коментарий диагноста по выявленным дефектам
    private String remResult; // произведенные работы
    private int remSumma; // общая сумма выполненных работ для оплаты
    private String remDateBegin;// дата время визита на СТО
    private String remDateEnd;// дата время окончания визита на СТО
    private int remComplete; // признак исполнения - оплата завершена+заказ закрыт - true


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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRemNum() {
        return remNum;
    }

    public void setRemNum(int remNum) {
        this.remNum = remNum;
    }

    public String getRemCarNum() {
        return remCarNum;
    }

    public void setRemCarNum(String remCarNum) {
        this.remCarNum = remCarNum;
    }

    public String getRemPhone() {
        return remPhone;
    }

    public void setRemPhone(String remPhone) {
        this.remPhone = remPhone;
    }

    public String getRemCarModel() {
        return remCarModel;
    }

    public void setRemCarModel(String remCarModel) {
        this.remCarModel = remCarModel;
    }

    public String getRemNote() {
        return remNote;
    }

    public void setRemNote(String remNote) {
        this.remNote = remNote;
    }

    public String getRemDiagnost() {
        return remDiagnost;
    }

    public void setRemDiagnost(String remDiagnost) {
        this.remDiagnost = remDiagnost;
    }

    public String getRemResult() {
        return remResult;
    }

    public void setRemResult(String remResult) {
        this.remResult = remResult;
    }

    public int getRemSumma() {
        return remSumma;
    }

    public void setRemSumma(int remSumma) {
        this.remSumma = remSumma;
    }

    public String getRemDateBegin() {
        return remDateBegin;
    }

    public void setRemDateBegin(String remDateBegin) {
        this.remDateBegin = remDateBegin;
    }

    public String getRemDateEnd() {
        return remDateEnd;
    }

    public void setRemDateEnd(String remDateEnd) {
        this.remDateEnd = remDateEnd;
    }

    public int getRemComplete() {
        return remComplete;
    }

    public void setRemComplete(int remComplete) {
        this.remComplete = remComplete;
    }
}
