package logic;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import database.MongoConnection;
import org.bson.Document;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CountersComboBoxInitializer extends MongoConnection  {
    private final String COLLECTION_NAME = "counters";
    private MongoDatabase database;
    private MongoCollection<Document> metersCollection;
    public CountersComboBoxInitializer() {
        database = getConnection();
        metersCollection = database.getCollection(COLLECTION_NAME);
    }

    public List<String> getMeterNumbers(){
        List<String> counterNumbersList = new LinkedList<>();
        FindIterable<Document> findIterable  = metersCollection.find();
        Iterator<Document> iterator = findIterable.iterator();
        while(iterator.hasNext()){
            Document document = iterator.next();
            String counterNumber = document.getString("Counter number");
            counterNumbersList.add(counterNumber);
        }
        return counterNumbersList;
    }

}
