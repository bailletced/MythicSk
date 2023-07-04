package com.fortlisa.mythicsk.skript.skillMetadata.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.registrations.Classes;
import io.lumine.mythic.api.skills.SkillMetadata;

public class Types {
    static {
        Classes.registerClass(new ClassInfo<SkillMetadata>(SkillMetadata.class, "skilldata")
                .user("skilldata")
                .name("skilldata")
                .defaultExpression(new EventValueExpression<>(SkillMetadata.class))
                .parser(new Parser<SkillMetadata>() {
                    @Override
                    public String toString(SkillMetadata skillMetadata, int i) {
                        return skillMetadata.toString();
                    }

                    @Override
                    public String toVariableNameString(SkillMetadata skillMetadata) {
                        return skillMetadata.toString();
                    }
                })
        );
    }
}
