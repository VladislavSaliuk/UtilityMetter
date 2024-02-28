package logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BillCalculationLogicTest {

    @Test
    void calculateBill_shouldReturnDoubleCalculationResult_whenInputContainsThreeDoubleValues(){
        BillCalculationLogic billCalculationLogic = new BillCalculationLogic(1,11,10);
        assertEquals(100,billCalculationLogic.calculateBill());
    }

}
