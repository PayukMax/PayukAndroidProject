package com.example.payukproject.Model;

public class Role3Data {

    private int id;
    private int remNum; //номер заказа - из таблицы записи 2
    private String remCarNum; // гос номер автомобиля
    private String remPhone; //  телефон владельца для связи
    private int remSumma; // общая сумма внесенной оплаты
    private String remPlatComplete;// дата и время внесения оплаты
    private String remNote; // комментарий о твладельца по оплате


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

    public int getRemSumma() {
        return remSumma;
    }

    public void setRemSumma(int remSumma) {
        this.remSumma = remSumma;
    }

    public String getRemPlatComplete() {
        return remPlatComplete;
    }

    public void setRemPlatComplete(String remPlatComplete) {
        this.remPlatComplete = remPlatComplete;
    }

    public String getRemNote() {
        return remNote;
    }

    public void setRemNote(String remNote) {
        this.remNote = remNote;
    }
}
