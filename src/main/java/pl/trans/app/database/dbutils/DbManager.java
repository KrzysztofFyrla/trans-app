package pl.trans.app.database.dbutils;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import pl.trans.app.database.models.SemiTrailer;
import pl.trans.app.database.models.Truck;
import pl.trans.app.database.models.Worker;

import java.io.IOException;
import java.sql.SQLException;

public class DbManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(DbManager.class);

    private static final String JDBC_DRIVER_HD = "jdbc:h2:./libraryDB";
    private static final String USER = "admin";
    private static final String PASS = "admin";

    private static ConnectionSource connectionSource;

    public static void initDatabase(){
        createConnectionSource();
        dropTable(); //zakomentuj, żeby nie kasować za każym razem tabel w bazie
        createTable();
        closeConnectionSource();
    }

    private static void createConnectionSource(){
        try {
            connectionSource = new JdbcConnectionSource(JDBC_DRIVER_HD,USER, PASS);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    public static ConnectionSource getConnectionSource(){
        if(connectionSource == null){
            createConnectionSource();
        }
        return connectionSource;
    }

    public static void closeConnectionSource(){
        if(connectionSource!=null){
            try {
                connectionSource.close();
            } catch (IOException e) {
                LOGGER.warn(e.getMessage());
            }
        }
    }

    private static void createTable(){
        try {
            TableUtils.createTableIfNotExists(connectionSource, Worker.class);
            TableUtils.createTableIfNotExists(connectionSource, Truck.class);
            TableUtils.createTableIfNotExists(connectionSource, SemiTrailer.class);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }

    private  static  void  dropTable(){
        try {
            TableUtils.dropTable(connectionSource, Worker.class, true);
            TableUtils.dropTable(connectionSource, Truck.class, true);
            TableUtils.dropTable(connectionSource, SemiTrailer.class, true);
        } catch (SQLException e) {
            LOGGER.warn(e.getMessage());
        }
    }
}
