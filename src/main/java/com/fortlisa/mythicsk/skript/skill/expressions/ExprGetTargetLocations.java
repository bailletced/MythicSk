package com.fortlisa.mythicsk.skript.skill.expressions;

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
import io.lumine.mythic.bukkit.BukkitAdapter;
import org.bukkit.Location;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.ArrayList;

@Name("caster")
@Description({
        "Get the target locations from the skill data. Returns a skript locations."
})
@Examples({
        "function example_skill(skilldata: skilldata, location: location, entity: entity) :: boolean:",
            "\tset {_entities::*} to targets of {_skilldata}"
})
public class ExprGetTargetLocations extends SimpleExpression<Location> {
    static {
        Skript.registerExpression(ExprGetTargetLocations.class, Location.class, ExpressionType.SIMPLE,
                "targetlocations (of|from) %skilldata%");
    }
    Expression<SkillMetadata> metadata;
    @Override
    public boolean isSingle() {
        return false;
    }

    @Override
    public Class<? extends Location> getReturnType() {
        return Location.class;
    }

    @Override
    @Nullable
    protected Location[] get(Event event) {
        SkillMetadata meta=metadata.getSingle(event);
        if(meta!=null&&meta.getLocationTargets()!=null) {
            ArrayList<Location>locations=new ArrayList<>();
            meta.getLocationTargets().forEach( aLocation -> {
                locations.add(BukkitAdapter.adapt(aLocation));
            });
            return locations.toArray(new Location[0]);
        }
        return new Location[0];
    }

    @Override
    public String toString(@Nullable Event event, boolean bool) {
        return "EntityLocations@"+event.getEventName();
    }

    @Override
    public boolean init(Expression<?>[] expression, int var1, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        return (metadata=(Expression<SkillMetadata>)expression[0])!=null;
    }
}
