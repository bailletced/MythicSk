package com.fortlisa.mythicsk.skript;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import com.fortlisa.mythicsk.api.enums.SkilldataType;
import io.lumine.mythic.api.skills.SkillMetadata;
import io.lumine.mythic.core.mobs.ActiveMob;

import javax.annotation.Nullable;

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

                    @Override
                    public SkillMetadata parse(String string, ParseContext context) {
                        return null;
                    }
                })
        );

        Classes.registerClass(new ClassInfo<ActiveMob>(ActiveMob.class, "activemob")
                .user("activemob")
                .name("activemob")
                .defaultExpression(new EventValueExpression<>(ActiveMob.class))
                .parser(new Parser<ActiveMob>() {
                    @Override
                    public String toString(ActiveMob activeMob, int i) {
                        return activeMob.getType().getInternalName();
                    }

                    @Override
                    public String toVariableNameString(ActiveMob activeMob) {
                        return activeMob.getUniqueId().toString();
                    }

                    @Override
                    public ActiveMob parse(String string, ParseContext context) {
                        return null;
                    }
                })
        );

        Classes.registerClass(new ClassInfo<>(SkilldataType.class, "skilldatatype")
            .user("skilldata ?types?")
            .name("Skilldata - Data Type")
            .description("Represents a type of Skilldata property.")
            .usage(SkilldataType.getNames())
            .examples("set {_level} to int data \"level\" of {_data}")
            .since("0.0.2")
            .parser(new Parser<SkilldataType>() {
                @Nullable
                @Override
                public SkilldataType parse(String s, ParseContext context) {
                    return SkilldataType.fromName(s);
                }

                @Override
                public String toString(SkilldataType nbtCustomType, int i) {
                    return nbtCustomType.getName();
                }

                @Override
                public String toVariableNameString(SkilldataType nbtCustomType) {
                    return toString(nbtCustomType, 0);
                }
            }));
    }
}
