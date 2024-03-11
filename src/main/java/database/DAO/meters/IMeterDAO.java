package database.DAO.meters;

import database.entity.Meter;
import org.bson.types.ObjectId;

import java.util.List;

public interface IMeterDAO {
    public void add(Meter meter);
    public void delete(ObjectId meterID);
    public void clear();
    public List<Meter> getItems();
    public void edit(Meter meter);
}
