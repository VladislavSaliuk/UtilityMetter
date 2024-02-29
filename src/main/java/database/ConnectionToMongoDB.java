package database;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.internal.MongoDatabaseImpl;

public abstract class ConnectionToMongoDB {
    private final String LOCALHOST = "localhost";
    private final int PORT = 27017;
    private final String ROOT = "root";
    private final String PASSWORD = "1234";
    private final String DATABASE_NAME = "UtilityMetterDB";
    protected void provideConnectionToMongoDB(){
        try(MongoClient mongoClient = new MongoClient(LOCALHOST, PORT)){
            MongoCredential mongoCredential = MongoCredential.createCredential(ROOT, DATABASE_NAME,PASSWORD.toCharArray());
            MongoDatabase mongoDatabase = mongoClient.getDatabase("DATABASE_NAME");
            System.out.println("mongoCredential = " + mongoCredential);
            System.out.println("Database name = " + mongoDatabase);
            System.out.println("Connected to the database successfully!");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
