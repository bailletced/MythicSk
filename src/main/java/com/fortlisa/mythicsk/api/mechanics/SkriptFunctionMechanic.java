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
    private static final int SKILL_DATA_POS=0;
    private static final int ENTITY_POS=1;
    private static final int LOCATION_POS=2;
    Function<?> function;
    String name;

    public SkriptFunctionMechanic(SkillExecutor se, String skill, MythicLineConfig mlc) {
        super(se, skill, mlc);
        Bukkit.getLogger().info("Mechanic passe : "+se.getTargeters().toString());
        this.name=mlc.getString("name","");
        this.function=Functions.getFunction(name);

        if(function==null) {
            Bukkit.getLogger().warning("Cant find function "+name);
        }
    }

    @Override
    public SkillResult cast(SkillMetadata skillMetadata) {
        Bukkit.getLogger().info("entityTargets"+skillMetadata.getEntityTargets().toString());
        this.function.execute(new Object[][] {
                {skillMetadata},
        });
        return SkillResult.SUCCESS;
    }

    @Override
    public SkillResult castAtEntity(SkillMetadata skillMetadata, AbstractEntity abstractEntity) {
        this.function.execute(new Object[][] {
                {skillMetadata},
                {abstractEntity.getBukkitEntity()}
        });

        return SkillResult.SUCCESS;
    }

    @Override
    public SkillResult castAtLocation(SkillMetadata skillMetadata, AbstractLocation abstractLocation) {
        this.function.execute(new Object[][] {
                {skillMetadata},
                {BukkitAdapter.adapt(abstractLocation)}
        });

        return SkillResult.SUCCESS;
    }
}