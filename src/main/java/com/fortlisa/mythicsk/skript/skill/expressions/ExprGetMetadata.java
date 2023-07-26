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
import com.fortlisa.mythicsk.api.enums.SkilldataType;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.SkillMetadata;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

@Name("caster")
@Description({
        "Get some data declared in the configuration of a skill"
})
@Examples({
        "function example_function(skilldata: skilldata):",
            "\tset {_id} to data 'id' of {_skilldata}",
            "\t\nset {_level} to int data 'level' of {_skilldata}"
})
public class ExprGetMetadata extends SimpleExpression<Object> {
    static {
        Skript.registerExpression(
                ExprGetMetadata.class,
                Object.class, ExpressionType.SIMPLE,
                "%skilldatatype% (data|prop|arg) %string% (of|from) %skilldata%",
                "(data|prop|arg) %string% (of|from) %skilldata%"
        );
    }
    Expression<SkilldataType> type;
    Expression<String> key;
    Expression<SkillMetadata> metadata;

    @Override
    @Nullable
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if (exprs.length == 3) {
            this.type = (Expression<SkilldataType>) exprs[0];
            this.key = (Expression<String>) exprs[1];
            this.metadata = (Expression<SkillMetadata>) exprs[2];
        }
        else {
            this.key = (Expression<String>) exprs[0];
            this.metadata = (Expression<SkillMetadata>) exprs[1];
        }

        return true;
    }
    @Override
    public boolean isSingle() {
        return true;
    }
    @Override
    public Class<? extends Object> getReturnType() {
        return Object.class;
    }
    @Override
    public String toString(@Nullable Event var1, boolean var2) {
        return null;
    }
    @Override
    @Nullable
    protected Object[] get(Event event) {
        SkillMetadata meta = metadata.getSingle(event);
        String prop = key.getSingle(event);
        SkilldataType castToType = SkilldataType.fromName("string");
        MythicLineConfig mlc = (MythicLineConfig) (meta.getMetadata("mlc")).get();

        if (type != null) {
            castToType = type.getSingle(event);
        }

        if (castToType.getName().equals("float")) {
            return new Object[]{mlc.getFloat(prop)};
        } else if (castToType.getName().equals("int")) {
            return new Object[]{mlc.getInteger(prop)};
        }
        return new Object[]{mlc.getString(prop)};
    }
}
