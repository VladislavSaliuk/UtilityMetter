package database.entity;

import org.bson.types.ObjectId;

public class Meter {
    private ObjectId meterID;
    private String meterNumber;
    private double dayTariffValue;
    private double nightTariffValue;
    public Meter() {

    }
    public Meter(ObjectId meterID, String meterNumber, double dayTariffValue, double nightTariffValue) {
        this.meterID = meterID;
        this.meterNumber = meterNumber;
        this.dayTariffValue = dayTariffValue;
        this.nightTariffValue = nightTariffValue;
    }

    @Override
    public String toString() {
        return meterNumber;
    }

    public ObjectId getMeterID() {
        return meterID;
    }

    public void setMeterID(ObjectId meterID) {
        this.meterID = meterID;
    }

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public double getDayTariffValue() {
        return dayTariffValue;
    }

    public void setDayTariffValue(double dayTariffValue) {
        this.dayTariffValue = dayTariffValue;
    }

    public double getNightTariffValue() {
        return nightTariffValue;
    }

    public void setNightTariffValue(double nightTariffValue) {
        this.nightTariffValue = nightTariffValue;
    }
}
