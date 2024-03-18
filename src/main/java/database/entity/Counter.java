package database.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Counter {

    private ObjectId counterID;

    private String counterNumber;

    private double currentDayConsumptionValue;

    private double currentNightConsumptionValue;

    @Override
    public String toString() {
        return counterNumber;
    }
}
