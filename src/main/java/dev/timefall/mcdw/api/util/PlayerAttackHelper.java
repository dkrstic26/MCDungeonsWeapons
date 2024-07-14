package dev.timefall.mcdw.api.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class PlayerAttackHelper {

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean mcdw$isLikelyNotMeleeDamage(DamageSource damageSource){
        return damageSource.isOf(DamageTypes.ON_FIRE)
                || damageSource.isOf(DamageTypes.EXPLOSION)
                || damageSource.isOf(DamageTypes.MAGIC)
                || damageSource.isOf(DamageTypes.ARROW)
                || !mcdw$isDirectDamage(damageSource);
    }

    private static boolean mcdw$isDirectDamage(DamageSource damageSource){
        return damageSource.isOf(DamageTypes.MOB_ATTACK)
                || damageSource.isOf(DamageTypes.PLAYER_ATTACK);
    }

    public static void mcdw$offhandAttack(PlayerEntity playerEntity, Entity target) {
        if (CompatibilityFlags.noOffhandConflicts) {
            if (!target.isAttackable())
                if (target.handleAttack(playerEntity))
                    return;

            ItemStack offhandStack = playerEntity.getOffHandStack();
            float cooldownProgress = ((IDualWielding) playerEntity).mcdw$getOffhandAttackCooldownProgress(0.5F);
            float attackDamage = DamageCalculator.calculateAttackDamage(playerEntity, cooldownProgress);

            ((IDualWielding) playerEntity).mcdw$resetLastAttackedOffhandTicks();

            if (attackDamage > 0.0f) {
                boolean isMostlyCharged = cooldownProgress > 0.9f;
                int knockbackLevel = KnockbackHandler.applyKnockback(playerEntity, offhandStack, isMostlyCharged);
                boolean playerShouldCrit = isMostlyCharged && AbilityHelper.entityCanCrit(playerEntity) && target instanceof LivingEntity;

                if (playerShouldCrit && !playerEntity.isSprinting()) {
                    attackDamage *= 1.5f;
                }

                boolean playerShouldSweep = isMostlyCharged && !playerShouldCrit && !playerEntity.isSprinting() && playerEntity.isOnGround() && playerEntity.horizontalSpeed - playerEntity.prevHorizontalSpeed < (double) playerEntity.getMovementSpeed() && offhandStack.getItem() instanceof IOffhandAttack;

                float targetHealth = 0.0f;
                boolean bl5 = FireAspectHandler.applyFireAspect(target, offhandStack);

                Vec3d targetVelocity = target.getVelocity();
                if (target.damage(target.getWorld().getDamageSources().playerAttack(playerEntity), attackDamage)) {
                    double positionOne = -MathHelper.sin(playerEntity.getYaw() * ((float) Math.PI / 180));
                    double positionTwo = MathHelper.cos(playerEntity.getYaw() * ((float) Math.PI / 180));
                    KnockbackHandler.handleKnockbackAndVelocity(target, knockbackLevel, positionOne, positionTwo, playerEntity);

                    CritAndSweepHandler.handleCritAndSweep(playerEntity, target, attackDamage, playerShouldCrit, playerShouldSweep, isMostlyCharged, offhandStack, positionOne, positionTwo);

                    DamageEffectsHandler.handleDamageEffects(playerEntity, target, offhandStack, targetHealth, bl5, targetVelocity);
                }
            }
        }
    }

    public static boolean mixAndMatchWeapons(PlayerEntity playerEntity) {
        return (playerEntity.getOffHandStack().isOf(playerEntity.getMainHandStack().getItem())
                || (playerEntity.getMainHandStack().isOf(McdwDaggerItemRegistry.DAGGER_THE_BEGINNING) && playerEntity.getOffHandStack().isOf(McdwDaggerItemRegistry.DAGGER_THE_END))
                || (playerEntity.getMainHandStack().isOf(McdwDaggerItemRegistry.DAGGER_THE_END) && playerEntity.getOffHandStack().isOf(McdwDaggerItemRegistry.DAGGER_THE_BEGINNING))
                || (playerEntity.getMainHandStack().isOf(McdwSickleItemRegistry.SICKLE_LAST_LAUGH_GOLD) && playerEntity.getOffHandStack().isOf(McdwSickleItemRegistry.SICKLE_LAST_LAUGH_SILVER))
                || (playerEntity.getMainHandStack().isOf(McdwSickleItemRegistry.SICKLE_LAST_LAUGH_SILVER) && playerEntity.getOffHandStack().isOf(McdwSickleItemRegistry.SICKLE_LAST_LAUGH_GOLD)));
    }
}
