package database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;


public abstract class ConnectionToMongoDB {
    private final String LOCALHOST = "localhost";
    private final int PORT = 27017;
    private final String DATABASE_NAME = "UtilityMetterDB";
    protected MongoDatabase provideConnectionToDatabase(){
        MongoClient mongoClient = new MongoClient(LOCALHOST, PORT);
        MongoDatabase mongoDatabase = mongoClient.getDatabase(DATABASE_NAME);
        return mongoDatabase;
    }

}
