package com.example.payukproject.Model;

public class Role1Data {

    private int id;
    private int zakNum; //номер заказа
    private String zakCarNum; // гос номер автомобиля
    private String zakDateTime;// дата время визита на СТО
    private String zakPhone; // телефон владельца для связи
    private String zakCarModel; // модель автомобиля ?????
    private String zakNote; // комментарий о твладельца по необходимым работам
    private int completed; // признак исполнения - визит совершен - true

    public int getId() {
        return id;
    }

    public String getZakPhone() {
        return zakPhone;
    }

    public String getZakCarModel() {
        return zakCarModel;
    }

    public void setZakCarModel(String zakCarModel) {
        this.zakCarModel = zakCarModel;
    }

    public String getZakNote() {
        return zakNote;
    }

    public void setZakNote(String zakNote) {
        this.zakNote = zakNote;
    }

    public void setZakPhone(String zakPhone) {
        this.zakPhone = zakPhone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getZakNum() {
        return zakNum;
    }

    public void setZakNum(int zakNum) {
        this.zakNum = zakNum;
    }

    public String getZakCarNum() {
        return zakCarNum;
    }

    public void setZakCarNum(String zakCarNum) {
        this.zakCarNum = zakCarNum;
    }

    public String getZakDateTime() {
        return zakDateTime;
    }

    public void setZakDateTime(String zakDateTime) {
        this.zakDateTime = zakDateTime;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }
}
