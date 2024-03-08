package database.DAO.meters;

import database.entity.Meter;

public interface IMeterDAO {
    public void add(Meter meter);
    public void delete(String meterID);
    public void clear();
}
