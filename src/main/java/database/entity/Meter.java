package database.entity;

public class Meter {
    private String meterID;
    private String meterNumber;
    private double dayTariffValue;
    private double nightTariffValue;
    public Meter() {

    }
    public Meter(String meterNumber, double dayTariffValue, double nightTariffValue) {
        this.meterNumber = meterNumber;
        this.dayTariffValue = dayTariffValue;
        this.nightTariffValue = nightTariffValue;
    }

    @Override
    public String toString() {
        return meterNumber;
    }

    public Meter(String meterID, String meterNumber, double dayTariffValue, double nightTariffValue) {
        this.meterID = meterID;
        this.meterNumber = meterNumber;
        this.dayTariffValue = dayTariffValue;
        this.nightTariffValue = nightTariffValue;
    }

    public String getMeterID() {
        return meterID;
    }

    public void setMeterID(String meterID) {
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
