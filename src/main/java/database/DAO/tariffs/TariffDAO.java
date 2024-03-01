package database.DAO.tariffs;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import database.MongoConnection;
import database.entity.Tariff;
import org.bson.Document;
import org.bson.types.ObjectId;

public class TariffDAO extends MongoConnection implements ITariffDAO {
    private final String COLLECTION_NAME = "tariffs";
    private MongoDatabase database;
    private MongoCollection<Document> tariffCollection;
    public TariffDAO() {
        database = provideConnectionToDatabase();
        tariffCollection = database.getCollection(COLLECTION_NAME);
    }
    @Override
    public void add(Tariff tariff) {
        Document tariffDocument = new Document()
                .append("Day tariff" ,tariff.getDayTariffValue())
                .append("Night tariff", tariff.getNightTariffValue());
        tariffCollection.insertOne(tariffDocument);
        System.out.println("Succesfully inserted!");
    }
    @Override
    public void delete(String tariffID) {
        DeleteResult deleteResult = tariffCollection.deleteOne(Filters.eq("_id",new ObjectId(tariffID)));
        System.out.println("Succesfully deleted " + deleteResult.getDeletedCount() + "documents!");
    }

    @Override
    public void clear() {
        DeleteResult deleteResult = tariffCollection.deleteMany(new Document());
        System.out.println("Succesfully deleted all " + deleteResult.getDeletedCount() + " documents!");
    }
}
