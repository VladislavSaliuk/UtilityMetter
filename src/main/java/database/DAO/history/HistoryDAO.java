package database.DAO.history;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import database.MongoConnection;
import database.entity.History;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class HistoryDAO extends MongoConnection implements IHistoryDAO {

    private final String COLLECTION_NAME = "history";
    private MongoCollection<Document> historyCollection;

    public HistoryDAO() {
        MongoDatabase mongoDatabase = getConnection();
        historyCollection = mongoDatabase.getCollection(COLLECTION_NAME);
    }

    @Override
    public void add(History history) {
        Document historyDocument = new Document("Counter number", history.getCounterNumber())
                .append("Current day consumption", history.getCurrentDayConsumingValue())
                .append("Current night consumption", history.getCurrentNightConsumingValue())
                .append("Total bill", history.getTotalBill())
                .append("Pay date", history.getPayDate());
        historyCollection.insertOne(historyDocument);
    }

    @Override
    public void clear() {
        DeleteResult deleteResult = historyCollection.deleteMany(new Document());
    }

    @Override
    public void delete(ObjectId historyId) {
        DeleteResult deleteResult = historyCollection.deleteOne(Filters.eq("_id", historyId));
    }

    @Override
    public List<History> getItems() {
        List<History> historyList = new ArrayList<>();
        FindIterable<Document> findIterable = historyCollection.find();
        for (Document document : findIterable) {
            History history = documentToHistory(document);
            historyList.add(history);
        }
        return historyList;
    }

    private History documentToHistory(Document document) {
        ObjectId historyID = document.getObjectId("_id");
        String counterNumber = document.getString("Counter number");
        double dayTariff = document.getDouble("Current day consumption");
        double nightTariff = document.getDouble("Current night consumption");
        double totalBill = document.getDouble("Total bill");
        String payDate = document.getString("Pay date");
        History history = new History();
        history.setHistoryID(historyID);
        history.setCounterNumber(counterNumber);
        history.setCurrentDayConsumingValue(dayTariff);
        history.setCurrentNightConsumingValue(nightTariff);
        history.setTotalBill(totalBill);
        history.setPayDate(payDate);
        return history;
    }
}
