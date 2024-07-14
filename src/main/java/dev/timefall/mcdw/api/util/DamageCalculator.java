package dev.timefall.mcdw.api.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.attribute.EntityAttributes;

public class DamageCalculator {

    public static float calculateAttackDamage(PlayerEntity playerEntity, float cooldownProgress) {
        float attackDamage = (float) playerEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        return attackDamage * (float) (0.2f + Math.pow(cooldownProgress, 2) * 0.8f);
    }
}
