package database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public abstract class MongoConnection {
    private static final String LOCALHOST = "localhost";
    private static final int PORT = 27017;
    private static final String DATABASE_NAME = "UtilityMeterDB";

    protected MongoDatabase getConnection() {
        MongoClient mongoClient = new MongoClient(LOCALHOST, PORT);
        return mongoClient.getDatabase(DATABASE_NAME);
    }
}
