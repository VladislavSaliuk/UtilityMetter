package logic;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import database.MongoConnection;
import database.entity.Meter;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MetersComboBoxInitializer extends MongoConnection  {
    private final String COLLECTION_NAME = "meters";
    private MongoDatabase database;
    private MongoCollection<Document> metersCollection;
    public MetersComboBoxInitializer() {
        database = provideConnectionToDatabase();
        metersCollection = database.getCollection(COLLECTION_NAME);
    }

    public List<String> getMeterNumbers(){
        List<String> meterNumbersList = new LinkedList<>();
        FindIterable<Document> findIterable  = metersCollection.find();
        Iterator<Document> iterator = findIterable.iterator();
        while(iterator.hasNext()){
            Document document = iterator.next();
            String meterNumber = document.getString("Meter number");
            meterNumbersList.add(meterNumber);
        }
        return meterNumbersList;
    }

}
