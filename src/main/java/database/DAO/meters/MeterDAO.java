package database.DAO.meters;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import database.MongoConnection;
import database.entity.Meter;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MeterDAO extends MongoConnection implements IMeterDAO {
    private final String COLLECTION_NAME = "meters";
    private MongoDatabase database;
    private MongoCollection<Document> metersCollection;
    public MeterDAO() {
        database = provideConnectionToDatabase();
        metersCollection = database.getCollection(COLLECTION_NAME);
    }
    @Override
    public void add(Meter meter) {
        Document tariffDocument = new Document()
                .append("Meter number" , meter.getMeterNumber())
                .append("Day tariff" ,meter.getDayTariffValue())
                .append("Night tariff", meter.getNightTariffValue());
        metersCollection.insertOne(tariffDocument);
        System.out.println("Succesfully inserted!");
    }

    @Override
    public void delete(String meterID) {
        DeleteResult deleteResult = metersCollection.deleteOne(Filters.eq("_id",new ObjectId(meterID)));
        System.out.println("Succesfully deleted " + deleteResult.getDeletedCount() + "documents!");
    }

    @Override
    public void clear() {
        DeleteResult deleteResult = metersCollection.deleteMany(new Document());
        System.out.println("Succesfully deleted all " + deleteResult.getDeletedCount() + " documents!");
    }

    @Override
    public List<Meter> getItems() {
        List<Meter> meterList = new LinkedList<>();
        FindIterable<Document> findIterable  = metersCollection.find();
        Iterator<Document> iterator = findIterable.iterator();
        while(iterator.hasNext()){
            Document document = iterator.next();
            ObjectId meterID = document.getObjectId("_id");
            String meterNumber = document.getString("Meter number");
            double dayTariffValue = document.getDouble("Day tariff");
            double nightTariffValue = document.getDouble("Night tariff");
            Meter meter = new Meter(meterID, meterNumber, dayTariffValue, nightTariffValue);
            meterList.add(meter);
        }
        return meterList;
    }
}
