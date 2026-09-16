package Core.DataBaseManager;

import Core.DataReaderManager.PropertyReader;
import Core.LogManager.LogManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    public DataBaseConnection() {
        // Private constructor to prevent instantiation
    }
    //فتح اتصال بالـ Database

    public  Connection getConnection() {

        try {
            Class.forName(PropertyReader.getProperty("DB_DRIVER"));
            return DriverManager.getConnection(
                    PropertyReader.getProperty("DB_URL"),
                    PropertyReader.getProperty("DB_USERNAME"),
                    PropertyReader.getProperty("DB_PASSWORD")
            );

        } catch (ClassNotFoundException | SQLException e) {

            throw new RuntimeException(
                    "Failed to create database connection", e
            );
        }
    }
    //إغلاق الاتصال

    public static void closeConnection(Connection connection) {

        if (connection != null) {

            try {
                connection.close();

            } catch (SQLException e) {

                throw new RuntimeException(
                        "Failed to close database connection", e
                );
            }
        }
    }
}

