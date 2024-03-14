package database.DAO.meters;

import database.entity.Counter;
import org.bson.types.ObjectId;

import java.util.List;

public interface ICounterDAO {
    public void add(Counter counter);
    public void delete(ObjectId counterID);
    public void clear();
    public List<Counter> getItems();
    public void edit(Counter counter);
}
