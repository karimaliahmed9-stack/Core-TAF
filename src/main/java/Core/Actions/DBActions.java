package Core.Actions;

import Core.LogManager.LogManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBActions {
    private final Connection connection;

    public DBActions(Connection connection) {
        this.connection = connection;
    }


    // SELECT
    public ResultSet executeQuery(String query) {

        try {
            LogManager.Info("Executing DB Query: " + query);

            Statement statement = connection.createStatement();

            return statement.executeQuery(query);

        } catch (SQLException e) {

            LogManager.Error("Failed to execute DB Query: " + e.getMessage());

            throw new RuntimeException("Failed to execute DB Query", e);
        }
    }

    // INSERT / UPDATE / DELETE
    public int executeUpdate(String query) {

        try {
            LogManager.Info("Executing DB Update: " + query);

            Statement statement = connection.createStatement();

            return statement.executeUpdate(query);

        } catch (SQLException e) {

            LogManager.Error("Failed to execute DB Update: " + e.getMessage());

            throw new RuntimeException("Failed to execute DB Update", e);
        }
    }


    // Get Single Value

    public Object getSingleValue(String query) {

        try {

            LogManager.Info("Getting single value from DB: " + query);

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                if (resultSet.next()) {
                    return resultSet.getObject(1);
                }

                return null;
            }

        } catch (SQLException e) {

            LogManager.Error("Failed to get single value: " + e.getMessage());

            throw new RuntimeException("Failed to get single value", e);
        }
    }


    // Get String Value

    public String getStringValue(String query) {

        Object value = getSingleValue(query);

        return value != null ? value.toString() : null;
    }


    // Get Integer Value

    public int getIntValue(String query) {

        Object value = getSingleValue(query);

        if (value == null) {
            LogManager.Warn("No value found for query: " + query + ". Returning 0 as default.");
            return 0;
        }

        return ((Number) value).intValue();
    }


    // Check Record Exists

    public boolean isRecordExists(String query) {

        try {

            LogManager.Info("Checking record existence: " + query);

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                return resultSet.next();
            }

        } catch (SQLException e) {

            LogManager.Error("Failed to check record existence: " + e.getMessage());

            throw new RuntimeException("Failed to check record existence", e);
        }
    }


    // Get Row Count

    public int getRowCount(String query) {

        try {

            LogManager.Info("Getting row count: " + query);

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                int count = 0;

                while (resultSet.next()) {
                    count++;
                }

                return count;
            }

        } catch (SQLException e) {

            LogManager.Error("Failed to get row count: " + e.getMessage());

            throw new RuntimeException("Failed to get row count", e);
        }
    }


    // Close Connection
    public void closeConnection() {

        try {

            if (connection != null && !connection.isClosed()) {

                connection.close();

                LogManager.Info("Database connection closed successfully.");
            }

        } catch (SQLException e) {

            LogManager.Error("Failed to close database connection: " + e.getMessage());

            throw new RuntimeException("Failed to close database connection", e);
        }
    }
}


