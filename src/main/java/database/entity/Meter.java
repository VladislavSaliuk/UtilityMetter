package database.entity;

public class Meter {
    private String meterID;
    private String meterNumber;
    private int dayTariffValue;
    private int nightTariffValue;
    public Meter() {

    }
    public Meter(String meterNumber, int dayTariffValue, int nightTariffValue) {
        this.meterNumber = meterNumber;
        this.dayTariffValue = dayTariffValue;
        this.nightTariffValue = nightTariffValue;
    }
    public Meter(String meterID, String meterNumber, int dayTariffValue, int nightTariffValue) {
        this.meterID = meterID;
        this.meterNumber = meterNumber;
        this.dayTariffValue = dayTariffValue;
        this.nightTariffValue = nightTariffValue;
    }
    @Override
    public String toString() {
        return meterNumber;
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

    public int getDayTariffValue() {
        return dayTariffValue;
    }

    public void setDayTariffValue(int dayTariffValue) {
        this.dayTariffValue = dayTariffValue;
    }

    public int getNightTariffValue() {
        return nightTariffValue;
    }

    public void setNightTariffValue(int nightTariffValue) {
        this.nightTariffValue = nightTariffValue;
    }
}
