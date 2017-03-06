package com.hiczp.minecraft.onlinewhitelist.task;

import com.hiczp.minecraft.onlinewhitelist.Config;
import com.hiczp.minecraft.onlinewhitelist.Main;
import com.hiczp.minecraft.onlinewhitelist.dao.OnlineWhiteListDao;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by czp on 17-3-6.
 */
public class ReckonByTimeTask implements Runnable {
    private Player player;

    public ReckonByTimeTask(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        Integer restTime = OnlineWhiteListDao.reduceTime(player.getName(), 1);
        if (restTime <= 0) {
            JavaPlugin javaPlugin = Main.getJavaPlugin();
            javaPlugin.getServer().getScheduler().runTask(javaPlugin,
                    () -> player.kickPlayer(Config.getKickMessage()));
        } else if (restTime < 5) {
            player.sendMessage(String.format("Your rest available gaming time less than %d min(s)", restTime));
        }
    }
}
