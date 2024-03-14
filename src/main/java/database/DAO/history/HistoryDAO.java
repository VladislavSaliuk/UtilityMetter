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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HistoryDAO extends MongoConnection implements IHistoryDAO{

    private final String COLLECTION_NAME = "history";
    private MongoDatabase mongoDatabase;
    private MongoCollection<Document> historyCollection;
    public HistoryDAO() {
        mongoDatabase = getConnection();
        historyCollection = mongoDatabase.getCollection(COLLECTION_NAME);
    }
    @Override
    public void add(History history) {
        Document historyDocument = new Document()
                .append("Counter number", history.getCounterNumber())
                .append("Day tariff" , history.getDayTariff())
                .append("Night tariff", history.getNightTariff())
                .append("Markup", history.getMarkup())
                .append("Bill",history.getTotalBill())
                .append("Pay date", history.getPayDate());
        historyCollection.insertOne(historyDocument);
    }

    @Override
    public void clear() {
        DeleteResult deleteResult = historyCollection.deleteMany(new Document());
    }

    @Override
    public void delete(ObjectId historyId) {
        DeleteResult deleteResult = historyCollection.deleteOne(Filters.eq("_id",historyId));
    }

    @Override
    public List<History> getItems() {
        List<History> historyList = new LinkedList<>();
        FindIterable<Document> findIterable  = historyCollection.find();
        Iterator<Document> iterator = findIterable.iterator();
        while(iterator.hasNext()){
            Document document = iterator.next();
            ObjectId historyID = document.getObjectId("_id");
            String counterNumber = document.getString("Counter number");
            double dayTariff = document.getDouble("Day tariff");
            double nightTariff = document.getDouble("Night tariff");
            int markup = document.getInteger("Markup");
            double totalBill = document.getDouble("Bill");
            String payDate = document.getString("Pay date");
            History history = new History();
            history.setHistoryID(historyID);
            history.setCounterNumber(counterNumber);
            history.setDayTariff(dayTariff);
            history.setNightTariff(nightTariff);
            history.setMarkup(markup);
            history.setTotalBill(totalBill);
            history.setPayDate(payDate);
            historyList.add(history);
        }
        return historyList;
    }
}
