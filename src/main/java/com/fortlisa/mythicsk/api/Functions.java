package com.fortlisa.mythicsk.api;

import com.fortlisa.mythicsk.MythicSk;
import com.fortlisa.mythicsk.api.mechanics.SkFunctionMechanic;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.bukkit.events.MythicMechanicLoadEvent;
import io.lumine.mythic.utils.plugin.LuminePlugin;
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
                e.register(new SkFunctionMechanic(MythicBukkit.inst().getSkillManager(), e.getContainer().getConfigLine(),e.getConfig()));
                break;
        }
    }

//    @EventHandler
//    public void onMythicMobsCustomMechanicsLoad(MythicMechanicLoadEvent e) {
//
//        switch(e.getMechanicName().toLowerCase()) {
//            case "skriptfunction":
//            case "skfunction":
//                e.register(new SkFunctionMechanic(e.getContainer().getConfigLine(),e.getConfig()));
//                break;
//        }
//    }
}