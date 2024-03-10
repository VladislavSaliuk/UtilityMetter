package logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MetterCalculatorTest {

    @Test
    void calculateTotalBill_shouldReturnCorrectBillValue_whenInputWithNoZeros(){
        MetterCalculator metterCalculator = new MetterCalculator(100,0.15,50,0.1);
        assertEquals(20,metterCalculator.calculateTotalBill(0));
    }

    @Test
    void calculateTotalBill_shouldReturnZero_whenInputContainsOnlyZeros(){
        MetterCalculator metterCalculator = new MetterCalculator(0,0,0,0);
        assertEquals(0, metterCalculator.calculateTotalBill(0));
    }

    @Test
    void calculateTotalBill_shouldReturnCorrectBillValue_whenInputAndMarkupWithNoZeros(){
        MetterCalculator metterCalculator = new MetterCalculator(100,0.15,50,0.10);
        assertEquals(22, metterCalculator.calculateTotalBill(10));
    }

    @Test
    void calculateTotalBill_shouldReturnSameCorrectBillValue_whenInputWithNoZeroValuesButMarkupValueIsZero(){
        MetterCalculator metterCalculator= new MetterCalculator(100,0.15,50,0.1);
        boolean isBillEqual = metterCalculator.calculateTotalBill(0) == metterCalculator.calculateTotalBill(0);
        assertTrue(isBillEqual);
    }

    @Test
    void calculateTotalBill_shouldReturnZero_whenInputWithZerosButMarkupisNoZeroValue(){
        MetterCalculator metterCalculator = new MetterCalculator(0,0,0,0);
        assertEquals(0, metterCalculator.calculateTotalBill(50));
    }

    @Test
    void calculateTotalBill_shouldReturnZero_whenTariffsAreZeros(){
        MetterCalculator metterCalculator = new MetterCalculator(100,0,50,0);
        assertEquals(0, metterCalculator.calculateTotalBill(0));
    }

}
