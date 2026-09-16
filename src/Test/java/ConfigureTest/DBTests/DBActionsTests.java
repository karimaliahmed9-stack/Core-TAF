package ConfigureTest.DBTests;

import Core.Actions.DBActions;
import Core.DataBaseManager.DataBaseConnection;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.Connection;

public class DBActionsTests {
    private Connection connection;
    private DBActions dbActions;

    @BeforeClass
    public void setUp() {
        // Open Connection
        DataBaseConnection dbConnection = new DataBaseConnection();
        connection = dbConnection.getConnection();

        dbActions = new DBActions(connection);

        // Create Test Table
        dbActions.executeUpdate("""
                     CREATE TABLE Users (
                    ID INT PRIMARY KEY,
                    Name VARCHAR(100),
                    Age INT
                )
                """);
    }

    @AfterClass
    public void tearDown() {
        // Close Database Connection
        dbActions.closeConnection();
    }


    @Test
    public void testInsertData() {

        int result = dbActions.executeUpdate("""
                INSERT INTO Users (ID, Name, Age)
                VALUES (1, 'Karim', 25)
                """);

        Assert.assertEquals(result, 1);
    }


    @Test(dependsOnMethods = "testInsertData")
    public void testGetStringValue() {

        String name = dbActions.getStringValue(
                "SELECT Name FROM Users WHERE ID = 1"
        );

        Assert.assertEquals(name, "Karim");
    }


    @Test(dependsOnMethods = "testInsertData")
    public void testGetIntValue() {

        int age = dbActions.getIntValue(
                "SELECT Age FROM Users WHERE ID = 1"
        );

        Assert.assertEquals(age, 25);
    }


    @Test(dependsOnMethods = "testInsertData")
    public void testRecordExists() {

        boolean exists = dbActions.isRecordExists(
                "SELECT * FROM Users WHERE ID = 1"
        );

        Assert.assertTrue(exists);
    }


    @Test(dependsOnMethods = "testInsertData")
    public void testRowCount() {

        int count = dbActions.getRowCount(
                "SELECT * FROM Users"
        );

        Assert.assertEquals(count, 1);
    }


    @Test(dependsOnMethods = "testInsertData")
    public void testUpdateData() {

        int result = dbActions.executeUpdate("""
                UPDATE Users
                SET Name = 'Ahmed'
                WHERE ID = 1
                """);

        Assert.assertEquals(result, 1);

        String name = dbActions.getStringValue(
                "SELECT Name FROM Users WHERE ID = 1"
        );

        Assert.assertEquals(name, "Ahmed");
    }


    @Test(dependsOnMethods = "testUpdateData")
    public void testDeleteData() {

        int result = dbActions.executeUpdate(
                "DELETE FROM Users WHERE ID = 1"
        );

        Assert.assertEquals(result, 1);

        boolean exists = dbActions.isRecordExists(
                "SELECT * FROM Users WHERE ID = 1"
        );

        Assert.assertFalse(exists);
    }


}
