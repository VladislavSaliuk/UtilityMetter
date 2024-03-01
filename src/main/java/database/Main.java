package database;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;
import database.DAO.meters.MeterDAO;
import database.DAO.tariffs.TariffDAO;
import database.entity.Meter;
import database.entity.Tariff;

public class Main {
    public static void main(String[] args) {
        MeterDAO meterDAO = new MeterDAO();
        Tariff tariff = new Tariff();
        tariff.setDayTariffValue(13);
        tariff.setNightTariffValue(3453);
        Meter meter = new Meter();
        meter.setMeterNumber("001");
        meter.setTariff(tariff);
        meterDAO.add(meter);
    }
}
