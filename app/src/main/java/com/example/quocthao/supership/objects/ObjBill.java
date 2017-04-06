package com.example.quocthao.supership.objects;

/**
 * Created by Quoc Thao on 4/6/2017.
 */

public class ObjBill {
    private String addressSend;
    private String addressReceive;
    private String timeOut;
    private String category;
    private String weight;
    private String moneyItem;
    private String moneyShip;
    private String phoneSender;
    private String phoneReceiver;
    private String note;

    public ObjBill() {
    }

    public ObjBill(String addressSend, String addressReceive, String timeOut,
                   String category, String weight, String moneyItem, String moneyShip,
                   String phoneSender, String phoneReceiver, String note) {
        this.addressSend = addressSend;
        this.addressReceive = addressReceive;
        this.timeOut = timeOut;
        this.category = category;
        this.weight = weight;
        this.moneyItem = moneyItem;
        this.moneyShip = moneyShip;
        this.phoneSender = phoneSender;
        this.phoneReceiver = phoneReceiver;
        this.note = note;
    }

    public String getAddressSend() {
        return addressSend;
    }

    public void setAddressSend(String addressSend) {
        this.addressSend = addressSend;
    }

    public String getAddressReceive() {
        return addressReceive;
    }

    public void setAddressReceive(String addressReceive) {
        this.addressReceive = addressReceive;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getMoneyItem() {
        return moneyItem;
    }

    public void setMoneyItem(String moneyItem) {
        this.moneyItem = moneyItem;
    }

    public String getMoneyShip() {
        return moneyShip;
    }

    public void setMoneyShip(String moneyShip) {
        this.moneyShip = moneyShip;
    }

    public String getPhoneSender() {
        return phoneSender;
    }

    public void setPhoneSender(String phoneSender) {
        this.phoneSender = phoneSender;
    }

    public String getPhoneReceiver() {
        return phoneReceiver;
    }

    public void setPhoneReceiver(String phoneReceiver) {
        this.phoneReceiver = phoneReceiver;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
