package com.fortlisa.mythicsk.skript.skill.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import io.lumine.mythic.api.skills.SkillMetadata;
import org.bukkit.entity.Entity;

@Name("caster")
@Description({
        "Get the caster from the skill data. Returns a skript entity."
})
@Examples({
        "function example_skill(skilldata: skilldata, location: location, entity: entity) :: boolean:",
            "\tset {_entity} to caster of {_skilldata}"
})
public class ExprGetCaster extends SimplePropertyExpression<SkillMetadata, Entity> {
    static {
        register(ExprGetCaster.class, Entity.class, "caster", "skilldata");
    }

    @Override
    public Entity convert(SkillMetadata skillmetadata) {
        return skillmetadata.getCaster().getEntity().getBukkitEntity();
    }

    @Override
    protected String getPropertyName() {
        return "caster";
    }

    @Override
    public Class<? extends Entity> getReturnType() {
        return Entity.class;
    }
}
