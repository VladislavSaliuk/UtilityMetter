package logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CounterCalculatorTest {

    @Test
    void calculateTotalBill_shouldReturnCorrectBillValue_whenInputWithNoZeros(){
        CounterCalculator counterCalculator = new CounterCalculator(100,0.15,50,0.1);
        assertEquals(20, counterCalculator.calculateTotalBill(0));
    }

    @Test
    void calculateTotalBill_shouldReturnZero_whenInputContainsOnlyZeros(){
        CounterCalculator counterCalculator = new CounterCalculator(0,0,0,0);
        assertEquals(0, counterCalculator.calculateTotalBill(0));
    }

    @Test
    void calculateTotalBill_shouldReturnCorrectBillValue_whenInputAndMarkupWithNoZeros(){
        CounterCalculator counterCalculator = new CounterCalculator(100,0.15,50,0.10);
        assertEquals(22, counterCalculator.calculateTotalBill(10));
    }

    @Test
    void calculateTotalBill_shouldReturnSameCorrectBillValue_whenInputWithNoZeroValuesButMarkupValueIsZero(){
        CounterCalculator counterCalculator = new CounterCalculator(100,0.15,50,0.1);
        boolean isBillEqual = counterCalculator.calculateTotalBill(0) == counterCalculator.calculateTotalBill(0);
        assertTrue(isBillEqual);
    }

    @Test
    void calculateTotalBill_shouldReturnZero_whenInputWithZerosButMarkupisNoZeroValue(){
        CounterCalculator counterCalculator = new CounterCalculator(0,0,0,0);
        assertEquals(0, counterCalculator.calculateTotalBill(50));
    }

    @Test
    void calculateTotalBill_shouldReturnZero_whenTariffsAreZeros(){
        CounterCalculator counterCalculator = new CounterCalculator(100,0,50,0);
        assertEquals(0, counterCalculator.calculateTotalBill(0));
    }

}
