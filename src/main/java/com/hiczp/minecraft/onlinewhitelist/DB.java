package com.hiczp.minecraft.onlinewhitelist;

import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by czp on 17-3-6.
 */
public class DB {
    private static Logger logger = Main.getJavaPlugin().getLogger();
    private static Connection connection;

    public static Connection connect(String driverClassName, String url, String username, String password) {
        if (connection == null) {
            try {
                Class.forName(driverClassName);
                connection = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException e) {
                logger.severe("Load database driver failed!");
                e.printStackTrace();
            } catch (SQLException e) {
                logger.severe("Connect to database server failed!");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void close() {
        if (connection != null) {
            try {
                DbUtils.close(connection);
            } catch (SQLException e) {
                logger.severe("Close database connection failed!");
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        DB.connection = connection;
    }
}
