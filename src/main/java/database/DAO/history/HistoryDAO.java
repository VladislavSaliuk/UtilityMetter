package database.DAO.history;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import database.MongoConnection;
import database.entity.History;
import org.bson.Document;

public class HistoryDAO extends MongoConnection implements IHistoryDAO{

    private final String COLLECTION_NAME = "history";
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> historyCollection;
    public HistoryDAO() {
        mongoDatabase = provideConnectionToDatabase();
        historyCollection = mongoDatabase.getCollection(COLLECTION_NAME);
    }
    @Override
    public void add(History history) {
        Document historyDocument = new Document()
                .append("Meter number", history.getMeterNumber())
                .append("Bill",history.getBillValue())
                .append("Pay date", history.getPayDate());
        historyCollection.insertOne(historyDocument);
        System.out.println("Succesfully inserted!");
    }
}
