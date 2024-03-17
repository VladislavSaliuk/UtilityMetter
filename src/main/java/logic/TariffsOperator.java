package logic;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import database.MongoConnection;
import org.bson.Document;
import org.bson.conversions.Bson;

public class TariffsOperator extends MongoConnection {
    private final String COLLECTION_NAME = "counters";
    private MongoDatabase database;
    private MongoCollection<Document> countersCollection;
    public TariffsOperator() {
        database = getConnection();
        countersCollection = database.getCollection(COLLECTION_NAME);
    }
    public double getNightTariffValue(String counterNumber){
        Document document = countersCollection.find(new Document("Counter number",counterNumber)).first();
        double nightTariffValue = document.getDouble("Night tariff");
        return nightTariffValue;
    }
    public double getDayTariffValue(String counterNumber){
        Document document = countersCollection.find(new Document("Counter number",counterNumber)).first();
        double dayTariffValue = document.getDouble("Day tariff");
        return dayTariffValue;
    }


}
