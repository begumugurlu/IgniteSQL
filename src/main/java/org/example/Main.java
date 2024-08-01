package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.ignite.IgniteJdbcThinDriver;

public class Main {
    private static String username = "";
    private static String password = "";

    public static void main(String[] args) throws SQLException {
        System.out.println("Classpath: " + System.getProperty("java.class.path"));

        try {
            Class.forName("org.apache.ignite.IgniteJdbcThinDriver");
            String jdbcUrl = "jdbc:ignite:thin://127.0.0.1:10800/";
            Connection connection = DriverManager.getConnection(jdbcUrl);
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM SUBSCRIBER";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int subscId = resultSet.getInt("SUBSC_ID");
                String subscName = resultSet.getString("SUBSC_NAME");
                String subscSurname = resultSet.getString("SUBSC_SURNAME");
                String msisdn = resultSet.getString("MSISDN");
                int tariffId = resultSet.getInt("TARIFF_ID");
                String startDate = resultSet.getString("START_DATE");

                System.out.println(subscId +" "+subscName+" "
                        +  subscSurname + " "+  msisdn
                        +" "+ tariffId + " "+startDate);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}