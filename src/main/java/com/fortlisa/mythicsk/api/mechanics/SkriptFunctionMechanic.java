package com.fortlisa.mythicsk.api.mechanics;

import ch.njol.skript.lang.function.Function;
import ch.njol.skript.lang.function.Functions;
import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.adapters.AbstractLocation;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.*;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.skills.SkillExecutor;
import io.lumine.mythic.core.skills.SkillMechanic;
import org.bukkit.Bukkit;

public class SkriptFunctionMechanic extends SkillMechanic implements INoTargetSkill, ITargetedEntitySkill, ITargetedLocationSkill {
    Function<?> function;
    String name;
    MythicLineConfig mythicLineConfig;

    public SkriptFunctionMechanic(SkillExecutor se, String skill, MythicLineConfig mlc) {
        super(se, skill, mlc);
        this.name=mlc.getString("name","");
        this.function=Functions.getFunction(name);
        this.mythicLineConfig = mlc;

        if(function==null) {
            Bukkit.getLogger().warning("\nCant find function "+name);
        }
    }

    @Override
    public SkillResult cast(SkillMetadata skillMetadata) {
        skillMetadata.setMetadata("mlc", mythicLineConfig);
        this.function.execute(new Object[][] {
                {skillMetadata},
        });
        return SkillResult.SUCCESS;
    }

    @Override
    public SkillResult castAtEntity(SkillMetadata skillMetadata, AbstractEntity abstractEntity) {
        skillMetadata.setMetadata("mlc", mythicLineConfig);
        this.function.execute(new Object[][] {
                {skillMetadata},
                {abstractEntity.getBukkitEntity()}
        });

        return SkillResult.SUCCESS;
    }

    @Override
    public SkillResult castAtLocation(SkillMetadata skillMetadata, AbstractLocation abstractLocation) {
        skillMetadata.setMetadata("mlc", mythicLineConfig);
        this.function.execute(new Object[][] {
                {skillMetadata},
                {BukkitAdapter.adapt(abstractLocation)}
        });

        return SkillResult.SUCCESS;
    }
}