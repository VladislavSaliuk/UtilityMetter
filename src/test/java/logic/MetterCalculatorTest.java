package logic;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MetterCalculatorTest {

    @Test
    void calculateTotalBill_shouldReturnBillValueinDouble_whenInputVariablesAreOnlyPositiveValues(){
        MetterCalculator metterCalculator = new MetterCalculator(100,0.15,50,0.1);
        assertEquals(20,metterCalculator.calculateTotalBill());
    }

    @Test
    void calculateTotalBill_shouldReturnZero_whenInputVariablesAreZeros(){
        MetterCalculator metterCalculator = new MetterCalculator(0,0,0,0);
        assertEquals(0, metterCalculator.calculateTotalBill());
    }

}
