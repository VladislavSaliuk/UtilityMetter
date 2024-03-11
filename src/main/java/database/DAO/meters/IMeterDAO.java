package database.DAO.meters;

import database.entity.Meter;

import java.util.List;

public interface IMeterDAO {
    public void add(Meter meter);
    public void delete(String meterID);
    public void clear();
    public List<Meter> getItems();
}
