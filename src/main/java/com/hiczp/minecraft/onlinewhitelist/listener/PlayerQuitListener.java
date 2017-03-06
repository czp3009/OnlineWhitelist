package com.hiczp.minecraft.onlinewhitelist.listener;

import com.hiczp.minecraft.onlinewhitelist.task.TaskHolder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by czp on 17-3-6.
 */
public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent playerQuitEvent) {
        TaskHolder.cancel(playerQuitEvent.getPlayer().getName());
    }
}
