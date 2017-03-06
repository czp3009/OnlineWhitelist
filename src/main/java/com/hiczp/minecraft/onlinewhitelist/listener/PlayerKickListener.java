package com.hiczp.minecraft.onlinewhitelist.listener;

import com.hiczp.minecraft.onlinewhitelist.task.TaskHolder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

/**
 * Created by czp on 17-3-6.
 */
public class PlayerKickListener implements Listener {
    @EventHandler
    public void onPlayerKick(PlayerKickEvent playerKickEvent) {
        TaskHolder.cancel(playerKickEvent.getPlayer().getName());
    }
}

