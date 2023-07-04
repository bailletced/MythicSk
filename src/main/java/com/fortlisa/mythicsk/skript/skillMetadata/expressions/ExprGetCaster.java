package com.fortlisa.mythicsk.skript.skillMetadata.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.api.skills.SkillMetadata;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

@Name("caster")
@Description({
        "Get the caster from the skill data. Returns a skript entity."
})
@Examples({
        "function example_skill(skilldata: skilldata, location: location, entity: entity) :: boolean:",
            "\tset {_entity} to lanceur of {_skilldata}"
})
public class ExprGetCaster extends SimpleExpression<Entity> {
    static {
        Skript.registerExpression(ExprGetCaster.class, Entity.class, ExpressionType.SIMPLE,
                "caster [of|from] %skilldata%"
        );
    }
    Expression<SkillMetadata> caster;
    @Override
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, ParseResult parseResult) {
        caster = (Expression<SkillMetadata>) exprs[0];
        return true;
    }

    @Override
    public Class<? extends Entity> getReturnType() {
        return Entity.class;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean bool) {
        return "GetLanceur";
    }

    @Override
    protected Entity[] get(Event event) {
        SkillMetadata meta = caster.getSingle(event);
        if (meta == null) {
            Bukkit.getLogger().info("I am NULL");
        }
        return new Entity[0];
    }
}
