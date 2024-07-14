package dev.timefall.mcdw.api.util;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;

public class KnockbackHandler {

    public static int applyKnockback(PlayerEntity playerEntity, ItemStack offhandStack, boolean isMostlyCharged) {
        int knockbackLevel = EnchantmentHelper.getLevel(Enchantments.KNOCKBACK, offhandStack);
        if (playerEntity.isSprinting() && isMostlyCharged) {
            CleanlinessHelper.playCenteredSound(playerEntity, SoundEvents.ENTITY_PLAYER_ATTACK_KNOCKBACK, 1.0f, 1.0f);
            ++knockbackLevel;
        }
        return knockbackLevel;
    }

    public static void handleKnockbackAndVelocity(Entity target, int knockbackLevel, double positionOne, double positionTwo, PlayerEntity playerEntity) {
        if (knockbackLevel > 0) {
            if (target instanceof LivingEntity livingTarget) {
                livingTarget.takeKnockback((float) knockbackLevel * 0.5f, -positionOne, -positionTwo);
            } else {
                target.addVelocity(positionOne * (float) knockbackLevel * 0.5f, 0.1, positionTwo * (float) knockbackLevel * 0.5f);
            }
            playerEntity.setVelocity(playerEntity.getVelocity().multiply(0.6, 1.0, 0.6));
            playerEntity.setSprinting(false);
        }
    }
}
