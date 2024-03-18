package logic;

import database.entity.Counter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CounterCalculatorTest {
    private static Counter counter;
    private static CounterCalculator counterCalculator;
    @BeforeAll
    static void init(){
        counter = new Counter();
        counter.setCurrentDayConsumptionValue(10050);
        counter.setCurrentNightConsumptionValue(6000);
        counterCalculator = new CounterCalculator();
        counterCalculator.setCounter(counter);
        counterCalculator.setDayTariff(0.15);
        counterCalculator.setNightTariff(0.1);
    }
    @Test
    void calculateTotalBill_shouldReturnCorrectTotalBillValue_whenInputContainsTrueData(){
        counterCalculator.setCurrentDayEnergyConsumption(12000);
        counterCalculator.setCurrentNightEnergyConsumption(7000);
        assertEquals(392.5, counterCalculator.calculateTotalBill());
    }

    @Test
    void calculateTotalBill_shouldReturnNumberFormatException_whenInputContainsNegativeConsumptionValues(){
        counterCalculator.setCurrentNightEnergyConsumption(-12000);
        counterCalculator.setCurrentNightEnergyConsumption(-7000);
        NumberFormatException exception = assertThrows(NumberFormatException.class,counterCalculator::calculateTotalBill);
        assertEquals("Please enter numeric values for energy consumption!",exception.getMessage());
    }

    @Test
    void calculateTotalBill_shouldReturnIllegalArgumentException_whenInputContainsFalseData(){
        counterCalculator.setCurrentDayEnergyConsumption(9000);
        counterCalculator.setCurrentNightEnergyConsumption(5000);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,counterCalculator::calculateTotalBill);
        assertEquals("The current counter readings cannot be less than or equal to the previous ones!!",exception.getMessage());
    }

    @Test
    void calculateTotalBill_shouldReturnIlligalArgumentException_whenInputContainsFalseCurrentDayEnergyConsumption(){
        counterCalculator.setCurrentDayEnergyConsumption(9000);
        counterCalculator.setCurrentNightEnergyConsumption(7000);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,counterCalculator::calculateTotalBill);
        assertEquals("The current counter readings cannot be less than or equal to the previous ones!!", exception.getMessage());
    }

    @Test
    void calculateTotalBill_shouldReturnIlligalArgumentException_whenInputContainsFalseCurrentNightEnergyConsumption(){
        counterCalculator.setCurrentDayEnergyConsumption(12000);
        counterCalculator.setCurrentNightEnergyConsumption(5000);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,counterCalculator::calculateTotalBill);
        assertEquals("The current counter readings cannot be less than or equal to the previous ones!!", exception.getMessage());
    }
}
