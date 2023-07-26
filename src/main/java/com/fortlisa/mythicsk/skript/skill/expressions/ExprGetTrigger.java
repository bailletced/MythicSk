package com.fortlisa.mythicsk.skript.skill.expressions;

import ch.njol.skript.doc.Description;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Name;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import io.lumine.mythic.api.skills.SkillMetadata;
import org.bukkit.entity.Entity;

@Name("trigger")
@Description({
        "Get the trigger from the skill data. Returns a skript entity."
})
@Examples({
        "function example_skill(skilldata: skilldata):",
            "\tset {_entity} to trigger of {_skilldata}"
})
public class ExprGetTrigger extends SimplePropertyExpression<SkillMetadata, Entity> {
    static {
        register(ExprGetTrigger.class, Entity.class, "trigger", "skilldata");
    }

    @Override
    public Entity convert(SkillMetadata skillmetadata) {
        return skillmetadata.getTrigger().getBukkitEntity();
    }

    @Override
    protected String getPropertyName() {
        return "trigger";
    }

    @Override
    public Class<? extends Entity> getReturnType() {
        return Entity.class;
    }
}
