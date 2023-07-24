package com.fortlisa.mythicsk.api.targets;

import ch.njol.skript.lang.function.Function;
import com.fortlisa.mythicsk.MythicSk;
import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.SkillMetadata;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.skills.SkillExecutor;
import io.lumine.mythic.core.skills.targeters.IEntitySelector;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;

public class SkriptFunctionEntityTargeter extends IEntitySelector {
    Function<Entity[]> function;

    public SkriptFunctionEntityTargeter(SkillExecutor se, MythicLineConfig mlc, Function skriptFunction) {
        super(se, mlc);
        function=skriptFunction;
    }

    @Override
    public HashSet<AbstractEntity> getEntities(SkillMetadata skillMetadata) {
        HashSet<AbstractEntity> targets = new HashSet<>();
        try {
            return Bukkit.getScheduler().callSyncMethod(
                    MythicSk.instance,
                    () -> {
                        Object[] result = function.execute(new Object[][] {
                                {skillMetadata},
                        });
                        if(result!=null) {
                            for(int i=0;i<result.length;i++) {
                                targets.add(BukkitAdapter.adapt((Entity) result[i]));
                            }
                        }
                        return targets;
                    }
            ).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
