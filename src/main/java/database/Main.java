package database;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;
import database.DAO.tariffs.TariffDAO;
import database.entity.Tariff;

public class Main {
    public static void main(String[] args) {
        TariffDAO tariffDAO = new TariffDAO();
        tariffDAO.clear();
    }
}
