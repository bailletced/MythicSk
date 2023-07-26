package com.fortlisa.mythicsk.skript.mob.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.doc.Since;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.util.Task;
import ch.njol.util.Kleenean;
import io.lumine.mythic.api.adapters.AbstractLocation;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.mobs.ActiveMob;
import jdk.jfr.Description;
import jdk.jfr.Name;
import org.bukkit.Location;
import org.bukkit.event.Event;
import javax.annotation.Nullable;


@Name("MythicMob - summon")
@Description("Summon a mythic mob.")
@Examples("set {_gouliath} to spawn mythicmob 'Gouliath' at location of player with level 3")
@Since("0.0.2")

public class SpawnMythicMob extends SimpleExpression<ActiveMob> {
    static {
        Skript.registerExpression(
                SpawnMythicMob.class,
                ActiveMob.class,
                ExpressionType.SIMPLE,
                "spawn mythicmob %string% at %location% [with level %number%]"
        );
    }
    private Expression<String> mobName;
    private Expression<Location> location;
    private Expression<Integer> level;

    @SuppressWarnings({"unchecked", "NullableProblems"})
    @Override
    @Nullable
    public boolean init(Expression<?>[] exprs, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        this.mobName = (Expression<String>) exprs[0];
        this.location = (Expression<Location>) exprs[1];
        if (exprs.length > 1) {
            this.level = (Expression<Integer>) exprs[2];
        }
        return true;
    }
    @Override
    @Nullable
    protected ActiveMob[] get(Event event) {
        String mobtype = mobName.getSingle(event);
        AbstractLocation finalLoc = BukkitAdapter.adapt(location.getSingle(event));
        double mobLevel = level.getSingle(event).doubleValue();

        if (finalLoc==null) return null;

        ActiveMob am = Task.callSync(() -> MythicBukkit.inst().getMobManager().spawnMob(mobtype, finalLoc, mobLevel));
        if (am == null) {
            Skript.warning("Mob "+this.mobName+" is not registered by MythicMob");
        }
        return new ActiveMob[]{am};
    }

    @Override
    public boolean isSingle() {
        return true;
    }
    @Override
    public String toString(@Nullable Event var1, boolean var2) {
        return null;
    }
    @Override
    public Class<? extends ActiveMob> getReturnType() {
        return ActiveMob.class;
    }
}
