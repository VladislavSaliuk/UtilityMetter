package database.entity;

import lombok.*;
import org.bson.types.ObjectId;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class History {
    private ObjectId historyID;
    private String counterNumber;
    private double currentDayConsumingValue;
    private double currentNightConsumingValue;
    private double totalBill;
    private String payDate;
}
