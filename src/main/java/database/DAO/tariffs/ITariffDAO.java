package database.DAO.tariffs;

import database.entity.Tariff;

public interface ITariffDAO {
    public void add(Tariff tariff);
    public void delete(String tariffID);
    public void clear();

}
