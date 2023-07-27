package com.fortlisa.mythicsk.skript.activemob.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import io.lumine.mythic.core.mobs.ActiveMob;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

@Name("caster")
@Description({
        "Get the entity from the activemob. Returns a skript entity."
})
@Examples({
        "set {_activeMob} to spawn mythicmob \"Gouliath\" at location of player",
        "\nset {_mob} to entity of {_activeMob}"
})
public class ExprGetEntity extends SimplePropertyExpression<ActiveMob, Entity> {
    static {
        register(ExprGetEntity.class, Entity.class, "entity", "activemob");
    }

    @Override
    public Entity convert(ActiveMob activeMob) {
        return activeMob.getEntity().getBukkitEntity();
    }

    @Override
    protected String getPropertyName() {
        return "entity";
    }

    @Override
    public Class<? extends Entity> getReturnType() {
        return Entity.class;
    }
}
