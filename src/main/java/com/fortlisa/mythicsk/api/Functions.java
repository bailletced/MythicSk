package com.fortlisa.mythicsk.api;

import com.fortlisa.mythicsk.MythicSk;
import io.lumine.mythic.bukkit.events.MythicMechanicLoadEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Functions implements Listener {
    public static void register() {
        new Functions();
    }

    public Functions() {
        MythicSk.instance.getServer().getPluginManager().registerEvents(this, MythicSk.instance);
    }

    @EventHandler
    public void onMythicMobsCustomMechanicsLoad(MythicMechanicLoadEvent e) {

        switch(e.getMechanicName().toLowerCase()) {
            case "skriptfunction":
            case "skfunction":
                e.register(new SkriptfunctionMechanic(e.getContainer().getConfigLine(),e.getConfig()));
                break;
        }
    }
}