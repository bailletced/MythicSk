package com.fortlisa.mythicsk.api;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.function.Function;
import com.fortlisa.mythicsk.MythicSk;
import com.fortlisa.mythicsk.api.mechanics.SkriptFunctionMechanic;
import com.fortlisa.mythicsk.api.targets.SkriptFunctionEntityTargeter;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.SkillManager;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.bukkit.events.MythicMechanicLoadEvent;
import io.lumine.mythic.bukkit.events.MythicTargeterLoadEvent;
import io.lumine.mythic.core.skills.SkillExecutor;
import io.lumine.mythic.core.skills.targeters.LocationTargeter;
import org.bukkit.entity.Entity;
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

    @EventHandler
    public void onMythicMobsCustomTargeterLoad(MythicTargeterLoadEvent e) {
        switch(e.getTargeterName().toLowerCase()) {
            case "skfunction":
            case "skriptfunction":
                MythicLineConfig mlc = e.getConfig();
                String name = mlc.getString("name","");
                SkillExecutor se = MythicBukkit.inst().getSkillManager();
                Function<?> function = ch.njol.skript.lang.function.Functions.getFunction(name);
                if(function!=null) {
                    String returnType=function.getReturnType().getCodeName();
                    if(returnType.equals("location")) {
//                        e.register(new LocationTargeter(se, mlc, function));
                    } else if(returnType.equals("entity")) {
                        e.register(new SkriptFunctionEntityTargeter(se, mlc, function));
                    } else {
                        Skript.error("Expected return type for skript targeter "+name+" has to be a entity or location list but is "+returnType);
                    }
                } else {
                    Skript.error("Cant find function "+name);
                }
                break;
        }

    }
}