package com.fortlisa.mythicsk.api.mechanics;

import ch.njol.skript.lang.function.Function;
import ch.njol.skript.lang.function.Functions;
import ch.njol.skript.lang.function.Parameter;
import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.adapters.AbstractLocation;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.*;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.skills.SkillExecutor;
import io.lumine.mythic.core.skills.SkillMechanic;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class SkriptFunctionMechanic extends SkillMechanic implements INoTargetSkill, ITargetedEntitySkill, ITargetedLocationSkill {
    private static final int SKILL_DATA_POS=0;
    private static final int ENTITY_POS=1;
    private static final int LOCATION_POS=2;
    Function<?> function;
    Object[][]parameters;
    String name;

    public SkriptFunctionMechanic(SkillExecutor se, String skill, MythicLineConfig mlc) {
        super(se, skill, mlc);
        this.name=mlc.getString("name","");
        this.function=Functions.getFunction(name);

        if(this.function!=null) {
            this.parameters= new Parameter[][]{function.getParameters()};
        } else {
            Bukkit.getLogger().warning("Cant find function "+name);
        }
    }

    @Override
    public SkillResult cast(SkillMetadata skillMetadata) {
        this.parameters[SKILL_DATA_POS]=new SkillMetadata[] {skillMetadata};
        this.function.execute(parameters);

        return SkillResult.SUCCESS;
    }

    @Override
    public SkillResult castAtEntity(SkillMetadata skillMetadata, AbstractEntity abstractEntity) {
        this.parameters[SKILL_DATA_POS]=new SkillMetadata[] {skillMetadata};
        this.parameters[ENTITY_POS]=new Entity[] {abstractEntity.getBukkitEntity()};
        this.function.execute(parameters);

        return SkillResult.SUCCESS;
    }

    @Override
    public SkillResult castAtLocation(SkillMetadata skillMetadata, AbstractLocation abstractLocation) {
        this.function.execute(new Object[][] {
                {new SkillMetadata[] {skillMetadata}},
                {new Location[] {BukkitAdapter.adapt(abstractLocation)}}
        });

        return SkillResult.SUCCESS;
    }
}