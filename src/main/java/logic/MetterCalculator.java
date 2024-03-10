package logic;

public class MetterCalculator {
    private double consumedEnergyDuringDayPeriod;
    private double dayTariffValue;
    private double consumedEnergyDuringNightPeriod;
    private double nightTariffValue;
    public MetterCalculator() {

    }
    public MetterCalculator(double consumedEnergyDuringDayPeriod, double dayTariffValue, double consumedEnergyDuringNightPeriod, double nightTariffValue) {
        this.consumedEnergyDuringDayPeriod = consumedEnergyDuringDayPeriod;
        this.dayTariffValue = dayTariffValue;
        this.consumedEnergyDuringNightPeriod = consumedEnergyDuringNightPeriod;
        this.nightTariffValue = nightTariffValue;
    }

    public double calculateTotalBill(int markup){
        double totalBillValueWithoutMarkup = consumedEnergyDuringDayPeriod * dayTariffValue + consumedEnergyDuringNightPeriod * nightTariffValue;
        double markupValue = totalBillValueWithoutMarkup * markup * 0.01;
        double totaBillValueWithMarkup = totalBillValueWithoutMarkup + markupValue;
        return totaBillValueWithMarkup;
    }
    public double getConsumedEnergyDuringDayPeriod() {
        return consumedEnergyDuringDayPeriod;
    }

    public void setConsumedEnergyDuringDayPeriod(double consumedEnergyDuringDayPeriod) {
        this.consumedEnergyDuringDayPeriod = consumedEnergyDuringDayPeriod;
    }

    public double getDayTariffValue() {
        return dayTariffValue;
    }

    public void setDayTariffValue(double dayTariffValue) {
        this.dayTariffValue = dayTariffValue;
    }

    public double getConsumedEnergyDuringNightPeriod() {
        return consumedEnergyDuringNightPeriod;
    }

    public void setConsumedEnergyDuringNightPeriod(double consumedEnergyDuringNightPeriod) {
        this.consumedEnergyDuringNightPeriod = consumedEnergyDuringNightPeriod;
    }

    public double getNightTariffValue() {
        return nightTariffValue;
    }

    public void setNightTariffValue(double nightTariffValue) {
        this.nightTariffValue = nightTariffValue;
    }
}
