package com.hiczp.minecraft.onlinewhitelist.task;

import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by czp on 17-3-6.
 */
public class TaskHolder {
    private static Map<String, BukkitTask> tasks = new HashMap<>();

    public static void cancel(String playerName) {
        BukkitTask bukkitTask = tasks.get(playerName);
        if (bukkitTask == null) {
            return;
        }
        bukkitTask.cancel();
    }

    public static Map<String, BukkitTask> getTasks() {
        return tasks;
    }
}
