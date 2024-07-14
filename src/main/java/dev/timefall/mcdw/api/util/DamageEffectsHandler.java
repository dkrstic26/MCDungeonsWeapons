package dev.timefall.mcdw.api.util;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DamageEffectsHandler {

    public static void handleDamageEffects(PlayerEntity playerEntity, Entity target, ItemStack offhandStack, float targetHealth, boolean bl5, Vec3d targetVelocity) {
        if (target instanceof ServerPlayerEntity && target.velocityModified) {
            ((ServerPlayerEntity) target).networkHandler.sendPacket(new EntityVelocityUpdateS2CPacket(target));
            target.velocityModified = false;
            target.setVelocity(targetVelocity);
        }

        if (target.damage(target.getWorld().getDamageSources().playerAttack(playerEntity), attackDamage)) {
            playerEntity.onAttacking(target);
            if (target instanceof LivingEntity livingTarget) {
                EnchantmentHelper.onUserDamaged(livingTarget, playerEntity);
            }

            EnchantmentHelper.onTargetDamaged(playerEntity, target);
            Entity targetedEntity = target;
            if (target instanceof EnderDragonPart enderDragonPartTarget) {
                targetedEntity = enderDragonPartTarget.owner;
            }

            if (!playerEntity.getWorld().isClient && !offhandStack.isEmpty() && targetedEntity instanceof LivingEntity livingTarget) {
                offhandStack.postHit(livingTarget, playerEntity);
                if (offhandStack.isEmpty()) {
                    playerEntity.setStackInHand(Hand.OFF_HAND, ItemStack.EMPTY);
                }
            }

            if (target instanceof LivingEntity livingTarget) {
                float targetCurrentHealth = targetHealth - livingTarget.getHealth();
                playerEntity.increaseStat(Stats.DAMAGE_DEALT, Math.round(targetCurrentHealth * 10.0f));
                if (fireAspectLevel > 0) {
                    target.setOnFireFor(fireAspectLevel * 4);
                }

                if (playerEntity.getWorld() instanceof ServerWorld playerServerWorld && targetCurrentHealth > 2.0f) {
                    int particleCount = (int) ((double) targetCurrentHealth * 0.5);
                    playerServerWorld.spawnParticles(ParticleTypes.DAMAGE_INDICATOR, target.getX(), target.getBodyY(0.5), target.getZ(), particleCount, 0.1, 0.0, 0.1, 0.2);
                }
            }
            playerEntity.addExhaustion(0.1f);
        } else {
            CleanlinessHelper.playCenteredSound(playerEntity, SoundEvents.ENTITY_PLAYER_ATTACK_NODAMAGE, 1.0f, 1.0f);
            if (bl5) {
                target.extinguish();
            }
        }
    }
}
