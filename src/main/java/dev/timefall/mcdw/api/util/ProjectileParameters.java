package dev.timefall.mcdw.api.util;

public class ProjectileParameters {
    private final World world;
    private final LivingEntity attacker;
    private final LivingEntity target;
    private final float v1;
    private final float v2;

    public ProjectileParameters(World world, LivingEntity attacker, LivingEntity target, float v1, float v2) {
        this.world = world;
        this.attacker = attacker;
        this.target = target;
        this.v1 = v1;
        this.v2 = v2;
    }

    public World getWorld() {
        return world;
    }

    public LivingEntity getAttacker() {
        return attacker;
    }

    public LivingEntity getTarget() {
        return target;
    }

    public float getV1() {
        return v1;
    }

    public float getV2() {
        return v2;
    }
}
