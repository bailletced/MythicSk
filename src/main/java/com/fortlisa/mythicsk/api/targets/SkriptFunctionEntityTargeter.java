package com.fortlisa.mythicsk.api.targets;

import ch.njol.skript.lang.function.Function;
import ch.njol.skript.util.Task;
import com.fortlisa.mythicsk.MythicSk;
import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.SkillMetadata;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.skills.SkillExecutor;
import io.lumine.mythic.core.skills.targeters.IEntitySelector;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;

public class SkriptFunctionEntityTargeter extends IEntitySelector {
    Function<Entity[]> function;
    Object[][]parameters;

    private static BukkitScheduler scheduler;

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
                                Bukkit.getLogger().info("Addin 1 tareget");
                                targets.add(BukkitAdapter.adapt((Entity) result[i]));
                            }
                        }
                        Bukkit.getLogger().info("result"+targets.toString());
                        Bukkit.getLogger().info("PASSE BEFORE");
                        return targets;
                    }
            ).get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

//        Task.callSync(() -> function.execute(new Object[][] {
//                {skillMetadata},
//        }), MythicSk.instance);

//        Object[] result =
//                Task.callSync(() -> function.execute(new Object[][] {
//                {skillMetadata},
//        }));

//        HashSet<AbstractEntity> targets = new HashSet<>();
//        if(result!=null) {
//            for(int i=0;i<result.length;i++) {
//                Bukkit.getLogger().info("Addin 1 tareget");
//                targets.add(BukkitAdapter.adapt((Entity) result[i]));
//            }
//        }

//        Bukkit.getLogger().info("result"+targets.toString());
//
//        Bukkit.getLogger().info("PASSE AFTER");
//        return targets;
    }
}
