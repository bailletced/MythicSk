package com.fortlisa.mythicsk.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.fortlisa.mythicsk.api.events.MythicMobSpawnEvent;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.entity.Entity;

import javax.annotation.Nullable;

public class SimpleEvents {
    static {
        //MythicMob spawn event
        Skript.registerEvent("MythicMob spawn", SimpleEvent.class, MythicMobSpawnEvent.class,
                        "mythicmob spawn")
                .description("Called when a mythicMob spawn.")
                .examples("");
        EventValues.registerEventValue(MythicMobSpawnEvent.class, ActiveMob.class, new Getter<ActiveMob, MythicMobSpawnEvent>() {
            @Override
            public @Nullable ActiveMob get(MythicMobSpawnEvent event) {
                return event.getActiveMob();
            }
        }, 0);

        EventValues.registerEventValue(MythicMobSpawnEvent.class, Entity.class, new Getter<Entity, MythicMobSpawnEvent>() {
            @Override
            @Nullable
            public Entity get(MythicMobSpawnEvent e) {
                return e.getEntity();
            }}, 0);
    }
}
