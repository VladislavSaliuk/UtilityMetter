package logic;

public class BillCalculationLogic {
    private double previousIndicatorValue;
    private double currrentIndicatorValue;
    private double tariffValue;
    public BillCalculationLogic(){

    }
    public BillCalculationLogic(double previousIndicatorValue,double currrentIndicatorValue, double tariffValue) {
        this.previousIndicatorValue = previousIndicatorValue;
        this.currrentIndicatorValue = currrentIndicatorValue;
        this.tariffValue = tariffValue;
    }

    public double calculateBill(){
        return (this.currrentIndicatorValue - this.previousIndicatorValue) * this.tariffValue;
    }
    public double getPreviousIndicatorValue() {
        return previousIndicatorValue;
    }

    public void setPreviousIndicatorValue(double previousIndicatorValue) {
        this.previousIndicatorValue = previousIndicatorValue;
    }

    public double getCurrrentIndicatorValue() {
        return currrentIndicatorValue;
    }

    public void setCurrrentIndicatorValue(double currrentIndicatorValue) {
        this.currrentIndicatorValue = currrentIndicatorValue;
    }

    public double getTariffValue() {
        return tariffValue;
    }

    public void setTariffValue(double tariffValue) {
        this.tariffValue = tariffValue;
    }
}
