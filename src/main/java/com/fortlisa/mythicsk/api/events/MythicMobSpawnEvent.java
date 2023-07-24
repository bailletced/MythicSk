package com.fortlisa.mythicsk.api.events;

import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class MythicMobSpawnEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private ActiveMob activeMob;

    public MythicMobSpawnEvent(ActiveMob activeMob) {
        this.activeMob = activeMob;
    }

    public ActiveMob getActiveMob() {
        return this.activeMob;
    }

    public Entity getEntity() {
        return this.activeMob.getEntity().getBukkitEntity();
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
