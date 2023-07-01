package com.fortlisa.mythicsk;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class MythicSk extends JavaPlugin {
    public static MythicSk instance;
    SkriptAddon addon;
    public static FileConfiguration config;

    public void onEnable() {
        // Register Addon
        instance = this;
        addon = Skript.registerAddon(this);
        try {
            addon.loadClasses("com.fortlisa.mythicsk.skript");
        } catch (IOException e) {
            e.printStackTrace();
        }

        config = getConfig();
    }
}
