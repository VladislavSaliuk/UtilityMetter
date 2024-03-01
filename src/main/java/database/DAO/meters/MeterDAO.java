package database.DAO.meters;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import database.MongoConnection;
import database.entity.Meter;
import org.bson.Document;
import org.bson.types.ObjectId;

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
}
