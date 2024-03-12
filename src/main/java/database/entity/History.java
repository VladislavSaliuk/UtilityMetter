package database.entity;

import org.bson.types.ObjectId;

import java.util.Date;

public class History {
    private ObjectId historyID;
    private String meterNumber;
    private double billValue;
    private String payDate;

    public History() {

    }

    public History(String meterNumber, double billValue, String payDate) {
        this.meterNumber = meterNumber;
        this.billValue = billValue;
        this.payDate = payDate;
    }

    public History(ObjectId historyID, String meterNumber, double billValue, String payDate) {
        this.historyID = historyID;
        this.meterNumber = meterNumber;
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
