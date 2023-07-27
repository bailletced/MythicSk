package com.fortlisa.mythicsk.skript.skilldata.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.api.skills.SkillMetadata;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.ArrayList;

@Name("caster")
@Description({
        "Get the target entities from the skill data. Returns skript entities."
})
@Examples({
        "function example_skill(skilldata: skilldata, location: location, entity: entity) :: boolean:",
            "\tset {_entities::*} to target entities of {_skilldata}"
})
public class ExprGetEntityTargets extends SimpleExpression<Entity> {
    static {
        Skript.registerExpression(ExprGetEntityTargets.class, Entity.class, ExpressionType.SIMPLE,
                "entitytargets (of|from) %skilldata%");
    }
    Expression<SkillMetadata> metadata;
    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Entity> getReturnType() {
        return Entity.class;
    }

    @Override
    @Nullable
    protected Entity[] get(Event event) {
        SkillMetadata meta=metadata.getSingle(event);
        if(meta!=null&&meta.getEntityTargets()!=null) {
            ArrayList<Entity> targets=new ArrayList<>();
            meta.getEntityTargets().forEach( aEntity -> {
                targets.add(aEntity.getBukkitEntity());
            });
            return targets.toArray(new Entity[0]);
        }
        return new Entity[0];
    }

    @Override
    public String toString(@Nullable Event event, boolean bool) {
        return "EntityTargets@"+event.getEventName();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expression, int var1, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        return (metadata=(Expression<SkillMetadata>)expression[0])!=null;
    }
}
