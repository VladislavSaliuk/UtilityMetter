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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CounterDAO extends MongoConnection implements ICounterDAO {
    private final String COLLECTION_NAME = "counters";
    private MongoDatabase database;
    private MongoCollection<Document> countersCollection;
    public CounterDAO() {
        database = getConnection();
        countersCollection = database.getCollection(COLLECTION_NAME);
    }
    @Override
    public void add(Counter counter) {
        Document tariffDocument = new Document()
                .append("Counter number" , counter.getCounterNumber())
                .append("Day tariff" , counter.getDayTariff())
                .append("Night tariff", counter.getNightTariff());
        countersCollection.insertOne(tariffDocument);
    }

    @Override
    public void delete(ObjectId counterID) {
        DeleteResult deleteResult = countersCollection.deleteOne(Filters.eq("_id",counterID));
    }

    @Override
    public void clear() {
        DeleteResult deleteResult = countersCollection.deleteMany(new Document());
    }

    @Override
    public List<Counter> getItems() {
        List<Counter> counterList = new LinkedList<>();
        FindIterable<Document> findIterable  = countersCollection.find();
        Iterator<Document> iterator = findIterable.iterator();
        while(iterator.hasNext()){
            Document document = iterator.next();
            ObjectId meterID = document.getObjectId("_id");
            String counterNumber = document.getString("Counter number");
            double dayTariffValue = document.getDouble("Day tariff");
            double nightTariffValue = document.getDouble("Night tariff");
            Counter counter = new Counter();
            counter.setCounterID(meterID);
            counter.setCounterNumber(counterNumber);
            counter.setDayTariff(dayTariffValue);
            counter.setNightTariff(nightTariffValue);
            counterList.add(counter);
        }
        return counterList;
    }

    @Override
    public void edit(Counter counter) {
        countersCollection.updateOne(Filters.eq("_id", counter.getCounterID()), Updates.set("Counter number", counter.getCounterNumber()));
        countersCollection.updateOne(Filters.eq("_id", counter.getCounterID()), Updates.set("Day tariff", counter.getDayTariff()));
        countersCollection.updateOne(Filters.eq("_id", counter.getCounterID()), Updates.set("Night tariff", counter.getNightTariff()));
    }

}
