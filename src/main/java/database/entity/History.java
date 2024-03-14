package database.entity;

import lombok.*;
import org.bson.types.ObjectId;


@Getter
@Setter
@NoArgsConstructor
public class History {
    private ObjectId historyID;
    private String counterNumber;
    private double dayTariff;
    private double nightTariff;
    private int markup;
    private double totalBill;
    private String payDate;
}
