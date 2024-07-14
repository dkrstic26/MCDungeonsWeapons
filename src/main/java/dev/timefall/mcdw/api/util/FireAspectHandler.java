package dev.timefall.mcdw.api.util;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class FireAspectHandler {

    public static boolean applyFireAspect(Entity target, ItemStack offhandStack) {
        int fireAspectLevel = EnchantmentHelper.getLevel(Enchantments.FIRE_ASPECT, offhandStack);
        if (target instanceof LivingEntity livingTarget && fireAspectLevel > 0 && !livingTarget.isOnFire()) {
            livingTarget.setOnFireFor(1);
            return true;
        }
        return false;
    }
}
