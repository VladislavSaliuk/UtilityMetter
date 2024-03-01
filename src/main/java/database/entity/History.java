package database.entity;

import java.util.Date;

public class History {
    private String historyID;
    private String meterNumber;
    private int billValue;
    private Date payDate;

    public History() {

    }
    public History(String meterNumber, int billValue, Date payDate) {
        this.meterNumber = meterNumber;
        this.billValue = billValue;
        this.payDate = payDate;
    }
    public History(String historyID, String meterNumber, int billValue, Date payDate) {
        this.historyID = historyID;
        this.meterNumber = meterNumber;
        this.billValue = billValue;
        this.payDate = payDate;
    }

    public String getHistoryID() {
        return historyID;
    }

    public void setHistoryID(String historyID) {
        this.historyID = historyID;
    }

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public int getBillValue() {
        return billValue;
    }

    public void setBillValue(int billValue) {
        this.billValue = billValue;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }
}
