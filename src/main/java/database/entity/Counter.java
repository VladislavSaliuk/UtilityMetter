package database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
@NoArgsConstructor
public class Counter {
    private ObjectId counterID;
    private String counterNumber;
    private double dayTariff;
    private double nightTariff;
}
