package dev.timefall.mcdw.api.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class CritAndSweepHandler {

    public static void handleCritAndSweep(PlayerEntity playerEntity, Entity target, float attackDamage, boolean playerShouldCrit, boolean playerShouldSweep, boolean isMostlyCharged, ItemStack offhandStack, double positionOne, double positionTwo) {
        if (playerShouldCrit) {
            CleanlinessHelper.playCenteredSound(playerEntity, SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, 1.0f, 1.0f);
            playerEntity.addCritParticles(target);
        } else if (!playerShouldSweep) {
            CleanlinessHelper.playCenteredSound(playerEntity, isMostlyCharged ? SoundEvents.ENTITY_PLAYER_ATTACK_STRONG : SoundEvents.ENTITY_PLAYER_ATTACK_WEAK, 1.0f, 1.0f);
        }

        if (playerShouldSweep) {
            float sweepingEdgeMultiplierTimesDamage = 1.0f + EnchantmentHelper.getSweepingMultiplier(EnchantmentHelper.getLevel(Enchantments.SWEEPING_EDGE, offhandStack)) * attackDamage;
            playerEntity.getWorld().getNonSpectatingEntities(LivingEntity.class, target.getBoundingBox().expand(1.0, 0.25, 1.0)).forEach(sweptEntity -> {
                if (AOEHelper.satisfySweepConditions(playerEntity, target, sweptEntity, 3.0f)) {
                    sweptEntity.takeKnockback(0.4f, -positionOne, -positionTwo);
                    sweptEntity.damage(sweptEntity.getWorld().getDamageSources().playerAttack(playerEntity), sweepingEdgeMultiplierTimesDamage);
                }
            });
            CleanlinessHelper.playCenteredSound(playerEntity, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, 1.0f, 1.0f);
            if (playerEntity.getWorld() instanceof ServerWorld serverWorld) {
                //serverWorld.spawnParticles(ParticlesRegistry.OFFHAND_SWEEP_PARTICLE, playerEntity.getX() + positionOne, playerEntity.getBodyY(0.5D), playerEntity.getZ() + positionTwo, 0, positionOne, 0.0D, positionTwo, 0.0D);
            }
        }
    }
}
