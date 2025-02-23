/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.enchants.effect.entity;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValueType;
import net.minecraft.enchantment.effect.EnchantmentEntityEffectType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public record LeechMobEnchantmentEntityEffectType(EnchantmentLevelBasedValueType amount)implements EnchantmentEntityEffectType
{

    public static final MapCodec<LeechMobEnchantmentEntityEffectType> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        EnchantmentLevelBasedValueType.CODEC.fieldOf("amount").forGetter(LeechMobEnchantmentEntityEffectType::amount)
                ).apply(instance, LeechMobEnchantmentEntityEffectType::new)
            );

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (user instanceof LivingEntity victim){
            if (context.owner() != null){
                float healthRegained = this.amount.getValue(level) * victim.getMaxHealth();
                context.owner().heal(healthRegained);
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffectType> getCodec() {
        return CODEC;
    }
}