package logic;

public class MetterCalculator {
    private double dayEnergyConsumption;
    private double dayTariff;
    private double nightEnergyConsumption;
    private double nightTariff;

    public MetterCalculator() {

    }

    public MetterCalculator(double dayEnergyConsumption, double dayTariff, double nightEnergyConsumption, double nightTariff) {
        this.dayEnergyConsumption = dayEnergyConsumption;
        this.dayTariff = dayTariff;
        this.nightEnergyConsumption = nightEnergyConsumption;
        this.nightTariff = nightTariff;
    }

    public double calculateTotalBill(int markupPercentage) {
        if (dayEnergyConsumption < 0 || dayTariff < 0 || nightEnergyConsumption < 0 || nightTariff < 0) {
            throw new IllegalArgumentException("Energy consumption and tariff values must be non-negative.");
        }
        double totalBillWithoutMarkup = (dayEnergyConsumption * dayTariff) + (nightEnergyConsumption * nightTariff);
        double markup = (double) markupPercentage / 100;
        double markupValue = totalBillWithoutMarkup * markup;
        return totalBillWithoutMarkup + markupValue;
    }

    public double getDayEnergyConsumption() {
        return dayEnergyConsumption;
    }

    public void setDayEnergyConsumption(double dayEnergyConsumption) {
        this.dayEnergyConsumption = dayEnergyConsumption;
    }

    public double getDayTariff() {
        return dayTariff;
    }

    public void setDayTariff(double dayTariff) {
        this.dayTariff = dayTariff;
    }

    public double getNightEnergyConsumption() {
        return nightEnergyConsumption;
    }

    public void setNightEnergyConsumption(double nightEnergyConsumption) {
        this.nightEnergyConsumption = nightEnergyConsumption;
    }

    public double getNightTariff() {
        return nightTariff;
    }

    public void setNightTariff(double nightTariff) {
        this.nightTariff = nightTariff;
    }
}
