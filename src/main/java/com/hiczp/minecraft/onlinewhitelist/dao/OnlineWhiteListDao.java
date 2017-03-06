package com.hiczp.minecraft.onlinewhitelist.dao;

import com.hiczp.minecraft.onlinewhitelist.Config;
import com.hiczp.minecraft.onlinewhitelist.DB;
import com.hiczp.minecraft.onlinewhitelist.Main;
import com.hiczp.minecraft.onlinewhitelist.entity.OnlineWhiteListEntity;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.stream.Collectors;

/**
 * Created by czp on 17-3-6.
 */
public class OnlineWhiteListDao {
    public static void createTable(String table) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(Main.class.getResourceAsStream("/data.sql"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String sql = bufferedReader.lines().collect(Collectors.joining("\n"));
            sql = String.format(sql, table);
            new QueryRunner().update(DB.getConnection()
                    , sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static OnlineWhiteListEntity findByUsername(String username) {
        try {
            return new QueryRunner().query(DB.getConnection(),
                    String.format("SELECT *  FROM `%s` WHERE `username` = '%s'", Config.getTable(), username),
                    new BeanHandler<>(OnlineWhiteListEntity.class));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Integer reduceTime(String username, int reduceCount) {
        try {
            OnlineWhiteListEntity onlineWhiteListEntity = new QueryRunner().query(DB.getConnection(),
                    String.format("SELECT *  FROM `%s` WHERE `username` = '%s'", Config.getTable(), username),
                    new BeanHandler<>(OnlineWhiteListEntity.class));
            onlineWhiteListEntity.setTime(onlineWhiteListEntity.getTime() - reduceCount);
            new QueryRunner().update(DB.getConnection(),
                    String.format("UPDATE `%s` SET `time` = %d WHERE `username` = '%s'",
                            Config.getTable(),
                            onlineWhiteListEntity.getTime(),
                            onlineWhiteListEntity.getUsername()));
            return onlineWhiteListEntity.getTime();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
