package database.DAO.history;

import database.entity.History;
import org.bson.types.ObjectId;

import java.util.List;

public interface IHistoryDAO {
    public void add(History history);
    public void clear();
    public void delete(ObjectId historyId);
    public List<History> getItems();
}
