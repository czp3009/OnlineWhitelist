package com.hiczp.minecraft.onlinewhitelist;

import com.hiczp.minecraft.onlinewhitelist.listener.PlayerJoinListener;
import com.hiczp.minecraft.onlinewhitelist.listener.PlayerKickListener;
import com.hiczp.minecraft.onlinewhitelist.listener.PlayerQuitListener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by czp on 17-3-5.
 */
public class Main extends JavaPlugin {
    private static JavaPlugin javaPlugin;

    public static JavaPlugin getJavaPlugin() {
        return javaPlugin;
    }

    public static void setJavaPlugin(JavaPlugin javaPlugin) {
        Main.javaPlugin = javaPlugin;
    }

    @Override
    public void onEnable() {
        javaPlugin = this;
        if (!Config.load()) {
            DB.close();
            getLogger().severe("Load Configuration failed, plugin will not work!");
            return;
        }
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerKickListener(), this);
        getLogger().info(String.format("%s enabled", getName()));
    }

    @Override
    public void onDisable() {
        DB.close();
        getLogger().info(String.format("%s disabled", getName()));
    }
}
