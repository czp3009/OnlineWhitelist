package com.hiczp.minecraft.onlinewhitelist.listener;

import com.hiczp.minecraft.onlinewhitelist.Config;
import com.hiczp.minecraft.onlinewhitelist.Main;
import com.hiczp.minecraft.onlinewhitelist.dao.OnlineWhiteListDao;
import com.hiczp.minecraft.onlinewhitelist.entity.OnlineWhiteListEntity;
import com.hiczp.minecraft.onlinewhitelist.task.ReckonByTimeTask;
import com.hiczp.minecraft.onlinewhitelist.task.TaskHolder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

/**
 * Created by czp on 17-3-6.
 */
public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent playerJoinEvent) {
        Player player = playerJoinEvent.getPlayer();
        if (player.isOp()) {
            return;
        }
        OnlineWhiteListEntity onlineWhiteListEntity = OnlineWhiteListDao.findByUsername(player.getName());
        if (onlineWhiteListEntity == null || onlineWhiteListEntity.getTime() <= 0) {
            player.kickPlayer(Config.getKickMessage());
            return;
        }
        if (Config.getReckonByTime()) {
            JavaPlugin javaPlugin = Main.getJavaPlugin();
            BukkitTask bukkitTask = javaPlugin.getServer().getScheduler().runTaskTimerAsynchronously(javaPlugin, new ReckonByTimeTask(player), 1200, 1200);
            TaskHolder.getTasks().put(player.getName(), bukkitTask);
        }
    }
}
