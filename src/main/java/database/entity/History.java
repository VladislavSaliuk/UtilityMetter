package database.entity;

import org.bson.types.ObjectId;

import java.util.Date;

public class History {
    private ObjectId historyID;
    private String meterNumber;
    private double dayTariff;
    private double nightTariff;
    private int markup;
    private double billValue;
    private String payDate;
    public History() {

    }
    public History(String meterNumber, double dayTariff, double nightTariff, int markup, double billValue, String payDate) {
        this.meterNumber = meterNumber;
        this.dayTariff = dayTariff;
        this.nightTariff = nightTariff;
        this.markup = markup;
        this.billValue = billValue;
        this.payDate = payDate;
    }

    public History(ObjectId historyID, String meterNumber, double dayTariff, double nightTariff, int markup, double billValue, String payDate) {
        this.historyID = historyID;
        this.meterNumber = meterNumber;
        this.dayTariff = dayTariff;
        this.nightTariff = nightTariff;
        this.markup = markup;
        this.billValue = billValue;
        this.payDate = payDate;
    }

    public ObjectId getHistoryID() {
        return historyID;
    }

    public void setHistoryID(ObjectId historyID) {
        this.historyID = historyID;
    }

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public double getDayTariff() {
        return dayTariff;
    }

    public void setDayTariff(double dayTariff) {
        this.dayTariff = dayTariff;
    }

    public double getNightTariff() {
        return nightTariff;
    }

    public void setNightTariff(double nightTariff) {
        this.nightTariff = nightTariff;
    }

    public int getMarkup() {
        return markup;
    }

    public void setMarkup(int markup) {
        this.markup = markup;
    }

    public double getBillValue() {
        return billValue;
    }

    public void setBillValue(double billValue) {
        this.billValue = billValue;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }
}
