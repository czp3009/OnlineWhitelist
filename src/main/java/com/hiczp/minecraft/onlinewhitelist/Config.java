package com.hiczp.minecraft.onlinewhitelist;

import com.hiczp.minecraft.onlinewhitelist.dao.OnlineWhiteListDao;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;

/**
 * Created by czp on 17-3-6.
 */
public class Config {
    private static JavaPlugin javaPlugin = Main.getJavaPlugin();
    private static String table;
    private static Boolean reckonByTime;
    private static String kickMessage;

    public static boolean load() {
        javaPlugin.saveDefaultConfig();
        FileConfiguration fileConfiguration = javaPlugin.getConfig();

        ConfigurationSection datasourceConfigurationSection = fileConfiguration.getConfigurationSection("datasource");
        Connection connection = DB.connect(datasourceConfigurationSection.getString("driverClassName"),
                datasourceConfigurationSection.getString("url"),
                datasourceConfigurationSection.getString("username"),
                datasourceConfigurationSection.getString("password"));

        table = datasourceConfigurationSection.getString("table");
        OnlineWhiteListDao.createTable(table);

        reckonByTime = fileConfiguration.getBoolean("reckon-by-time");

        kickMessage = fileConfiguration.getString("kick-message");

        return connection != null;
    }

    public static String getTable() {
        return table;
    }

    public static Boolean getReckonByTime() {
        return reckonByTime;
    }

    public static String getKickMessage() {
        return kickMessage;
    }
}
