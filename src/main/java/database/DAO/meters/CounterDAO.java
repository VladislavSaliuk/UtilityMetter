package database.DAO.meters;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import database.MongoConnection;
import database.entity.Counter;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class CounterDAO extends MongoConnection implements ICounterDAO {

    private final String COLLECTION_NAME = "counters";
    private MongoCollection<Document> countersCollection;

    public CounterDAO() {
        MongoDatabase database = getConnection();
        countersCollection = database.getCollection(COLLECTION_NAME);
    }

    @Override
    public void add(String counterNumber) {
        Document tariffDocument = new Document("Counter number", counterNumber)
                .append("Current day consumption", 0.0)
                .append("Current night consumption", 0.0);
        countersCollection.insertOne(tariffDocument);
    }

    @Override
    public void delete(ObjectId counterID) {
        DeleteResult deleteResult = countersCollection.deleteOne(Filters.eq("_id", counterID));
    }

    @Override
    public void clear() {
        DeleteResult deleteResult = countersCollection.deleteMany(new Document());
    }

    @Override
    public List<Counter> getItems() {
        List<Counter> counterList = new ArrayList<>();
        FindIterable<Document> findIterable = countersCollection.find();
        for (Document document : findIterable) {
            Counter counter = documentToCounter(document);
            counterList.add(counter);
        }
        return counterList;
    }

    @Override
    public void edit(Counter counter) {
        countersCollection.updateOne(Filters.eq("_id", counter.getCounterID()), Updates.set("Counter number", counter.getCounterNumber()));
        countersCollection.updateOne(Filters.eq("_id", counter.getCounterID()), Updates.set("Current day consumption", counter.getCurrentDayConsumptionValue()));
        countersCollection.updateOne(Filters.eq("_id", counter.getCounterID()), Updates.set("Current night consumption", counter.getCurrentNightConsumptionValue()));
    }

    private Counter documentToCounter(Document document) {
        ObjectId counterID = document.getObjectId("_id");
        String counterNumber = document.getString("Counter number");
        double currentDayConsumption = document.getDouble("Current day consumption");
        double currentNightConsumption = document.getDouble("Current night consumption");
        Counter counter = new Counter();
        counter.setCounterID(counterID);
        counter.setCounterNumber(counterNumber);
        counter.setCurrentDayConsumptionValue(currentDayConsumption);
        counter.setCurrentNightConsumptionValue(currentNightConsumption);
        return counter;
    }
}
