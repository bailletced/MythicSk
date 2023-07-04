package com.fortlisa.mythicsk.skript.skillMetadata.types;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.registrations.Classes;
import io.lumine.mythic.core.skills.SkillMetadataImpl;

public class Types {
    static {
        Classes.registerClass(new ClassInfo<SkillMetadataImpl>(SkillMetadataImpl.class, "skilldata")
                .user("skilldata")
                .name("skilldata")
                .defaultExpression(new EventValueExpression<>(SkillMetadataImpl.class))
                .parser(new Parser<SkillMetadataImpl>() {
                    @Override
                    public String toString(SkillMetadataImpl skillMetadata, int i) {
                        return skillMetadata.toString();
                    }

                    @Override
                    public String toVariableNameString(SkillMetadataImpl skillMetadata) {
                        return skillMetadata.toString();
                    }
                })
        );
    }
}
