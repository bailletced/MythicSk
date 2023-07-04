package com.fortlisa.mythicsk.api;

import com.fortlisa.mythicsk.MythicSk;
import com.fortlisa.mythicsk.api.mechanics.SkriptFunctionMechanic;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.bukkit.events.MythicMechanicLoadEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MythicListener implements Listener {
    public static void register() {
        new MythicListener();
    }

    public MythicListener() {
        MythicSk.instance.getServer().getPluginManager().registerEvents(this, MythicSk.instance);
    }

    @EventHandler
    public void onMythicMobsCustomMechanicsLoad(MythicMechanicLoadEvent e) {
        switch(e.getMechanicName().toLowerCase()) {
            case "skriptfunction":
            case "skfunction":
                Bukkit.getLogger().info(e.getMechanicName());
                Bukkit.getLogger().info(MythicBukkit.inst().getSkillManager().toString());
                Bukkit.getLogger().info(e.getConfig().toString());
                String name = e.getMechanicName() != null ? e.getMechanicName()  : "";
                e.register(
                        new SkriptFunctionMechanic(
                                MythicBukkit.inst().getSkillManager(),
                                e.getMechanicName(),
                                e.getConfig()
                        )
                );
                break;
        }
    }
}