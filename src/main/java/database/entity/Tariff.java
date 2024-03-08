package database.entity;

public class Tariff {
    private String tariffID;
    private int dayTariffValue;
    private int nightTariffValue;

    public Tariff() {

    }

    public Tariff(int dayTariffValue, int nightTariffValue) {
        this.dayTariffValue = dayTariffValue;
        this.nightTariffValue = nightTariffValue;
    }

    public Tariff(String tariffID, int dayTariffValue, int nightTariffValue) {
        this.tariffID = tariffID;
        this.dayTariffValue = dayTariffValue;
        this.nightTariffValue = nightTariffValue;
    }

    @Override
    public String toString() {
        return "Day tariff: " + dayTariffValue + "\tNight tariff: " + nightTariffValue;
    }

    public String getTariffID() {
        return tariffID;
    }
    public void setTariffID(String tariffID) {
        this.tariffID = tariffID;
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
