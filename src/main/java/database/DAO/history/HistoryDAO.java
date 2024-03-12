package database.DAO.history;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import database.MongoConnection;
import database.entity.History;
import database.entity.Meter;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
                .append("Day tariff" , history.getDayTariff())
                .append("Night tariff", history.getNightTariff())
                .append("Markup", history.getMarkup())
                .append("Bill",history.getBillValue())
                .append("Pay date", history.getPayDate());
        historyCollection.insertOne(historyDocument);
        System.out.println("Succesfully inserted!");
    }

    @Override
    public void clear() {
        DeleteResult deleteResult = historyCollection.deleteMany(new Document());
        System.out.println("Succesfully deleted all " + deleteResult.getDeletedCount() + " documents!");
    }

    @Override
    public void delete(ObjectId historyId) {
        DeleteResult deleteResult = historyCollection.deleteOne(Filters.eq("_id",historyId));
        System.out.println("Succesfully deleted " + deleteResult.getDeletedCount() + "documents!");
    }

    @Override
    public List<History> getItems() {
        List<History> historyList = new LinkedList<>();
        FindIterable<Document> findIterable  = historyCollection.find();
        Iterator<Document> iterator = findIterable.iterator();
        while(iterator.hasNext()){
            Document document = iterator.next();
            ObjectId historyID = document.getObjectId("_id");
            String meterNumber = document.getString("Meter number");
            double dayTariffValue = document.getDouble("Day tariff");
            double nightTariffValue = document.getDouble("Night tariff");
            int markup = document.getInteger("Markup");
            double billValue = document.getDouble("Bill");
            String payDateValue = document.getString("Pay date");
            History history = new History(historyID,meterNumber,dayTariffValue,nightTariffValue,markup,billValue,payDateValue);
            historyList.add(history);
        }
        return historyList;
    }
}
