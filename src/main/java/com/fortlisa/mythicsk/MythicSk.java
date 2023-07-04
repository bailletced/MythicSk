package com.fortlisa.mythicsk;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import com.fortlisa.mythicsk.api.MythicListener;
import com.fortlisa.mythicsk.skript.skillMetadata.types.Types;
import io.lumine.mythic.bukkit.events.MythicMechanicLoadEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class MythicSk extends JavaPlugin {
    public static MythicSk instance;
    SkriptAddon addon;

    public void onEnable() {
         // Register Addon
        instance = this;
        addon = Skript.registerAddon(this);
        MythicListener.register();

        try {
            addon.loadClasses("com.fortlisa.mythicsk.skript");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
