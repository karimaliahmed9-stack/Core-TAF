package ConfigureTest.DBTests;

import Core.DataBaseManager.DataBaseConnection;
import Core.LogManager.LogManager;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Connection;

public class TestDBConnection {
    DataBaseConnection DBConnection;

    @Test
    public void testDatabaseConnection() {

        Connection connection = null;

        try {

            connection = DBConnection.getConnection();

            Assert.assertNotNull(connection, "Database connection is null");

            Assert.assertFalse(connection.isClosed(), "Database connection is closed");

            LogManager.Info("Database Connection Successful!");

        } catch (Exception e) {

            Assert.fail("Database Connection Failed: " + e.getMessage());

        } finally {

            DBConnection.closeConnection(connection);
        }
    }

}

