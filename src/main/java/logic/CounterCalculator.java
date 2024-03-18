package logic;

import database.entity.Counter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CounterCalculator {
    private double currentDayEnergyConsumption;
    private double currentNightEnergyConsumption;
    private double dayTariff;
    private double nightTariff;
    private Counter counter;

    public double calculateTotalBill() {
        if(currentDayEnergyConsumption < 0 || currentNightEnergyConsumption < 0){
            throw new NumberFormatException("Please enter numeric values for energy consumption!");
        }
        if(counter.getCurrentDayConsumptionValue() >= currentDayEnergyConsumption || counter.getCurrentNightConsumptionValue() >= currentNightEnergyConsumption){
            throw new IllegalArgumentException("The current counter readings cannot be less than or equal to the previous ones!!");
        }
        return (((currentDayEnergyConsumption - counter.getCurrentDayConsumptionValue()) * dayTariff) + ((currentNightEnergyConsumption - counter.getCurrentNightConsumptionValue()) * nightTariff));
    }

}
