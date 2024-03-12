package logic;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import database.MongoConnection;
import org.bson.Document;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TariffsOperator extends MongoConnection {
    private final String COLLECTION_NAME = "meters";
    private MongoDatabase database;
    private MongoCollection<Document> metersCollection;
    public TariffsOperator() {
        database = provideConnectionToDatabase();
        metersCollection = database.getCollection(COLLECTION_NAME);
    }
    public double getNightTariffValue(String meterNumber){
        Document document = metersCollection.find(new Document("Meter number",meterNumber)).first();
        double nightTariffValue = document.getDouble("Night tariff");
        return nightTariffValue;
    }
    public double getDayTariffValue(String meterNumber){
        Document document = metersCollection.find(new Document("Meter number",meterNumber)).first();
        double dayTariffValue = document.getDouble("Day tariff");
        return dayTariffValue;
    }

}
