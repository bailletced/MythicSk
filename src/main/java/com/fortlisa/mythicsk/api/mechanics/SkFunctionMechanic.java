package com.fortlisa.mythicsk.api.mechanics;

import ch.njol.skript.lang.function.Function;
import ch.njol.skript.lang.function.Functions;
import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.adapters.AbstractLocation;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.*;
import io.lumine.mythic.core.skills.SkillMechanic;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class SkFunctionMechanic extends SkillMechanic implements INoTargetSkill, ITargetedEntitySkill, ITargetedLocationSkill {

    Function<?> function;
    Object[][]parameters;
    int dataPos,locationPos,entityPos;
    String name;

    public SkFunctionMechanic(String skill, MythicLineConfig mlc) {
        super(skill, mlc);

        name=mlc.getString("name","");
        function=Functions.getFunction(name);

        if(function!=null) {
            parameters=new Object[function.getParameters().length][];
            dataPos=locationPos=entityPos=-1;
            for(int i=0;i<function.getParameters().length;i++) {
                String type=function.getParameter(i).getType().getCodeName();
                switch(type) {
                    case "skilldata":
                        dataPos=i;
                        break;
                    case "location":
                        locationPos=i;
                        break;
                    case "entity":
                        entityPos=i;
                        break;
                }
            }
        } else {
            Bukkit.getLogger().warning("Cant find function "+name);
        }
    }

    @Override
    public boolean cast(SkillMetadata meta) {
        if(dataPos>-1) parameters[dataPos]=new SkillMetadata[] {meta};
        if(locationPos>-1) parameters[locationPos]=new Location[0];
        if(entityPos>-1) parameters[entityPos]=new Entity[0];
        function.execute(parameters);
        return true;
    }

    @Override
    public boolean castAtLocation(SkillMetadata meta, AbstractLocation aLocation) {
        if(dataPos>-1) parameters[dataPos]=new SkillMetadata[] {meta};
        if(locationPos>-1) parameters[locationPos]=new Location[] {BukkitAdapter.adapt(aLocation)};
        if(entityPos>-1) parameters[entityPos]=new Entity[0];
        function.execute(parameters);
        return true;
    }

    @Override
    public SkillResult cast(SkillMetadata skillMetadata) {
        return null;
    }

    @Override
    public SkillResult castAtEntity(SkillMetadata skillMetadata, AbstractEntity abstractEntity) {
        if(dataPos>-1) parameters[dataPos]=new SkillMetadata[] {skillMetadata};
        if(locationPos>-1) parameters[locationPos]=new Location[0];
        if(entityPos>-1) parameters[entityPos]=new Entity[] {abstractEntity.getBukkitEntity()};

        function.execute(parameters);

        return true;
    }

    @Override
    public SkillResult castAtLocation(SkillMetadata skillMetadata, AbstractLocation abstractLocation) {
        return null;
    }
}