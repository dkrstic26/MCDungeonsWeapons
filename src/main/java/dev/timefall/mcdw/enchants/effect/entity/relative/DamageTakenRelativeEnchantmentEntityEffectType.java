/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.enchants.effect.entity.relative;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.timefall.mcdw.enchants.effect.entity.RelativeEnchantmentEntityEffectType;
import dev.timefall.mcdw.mixin.LivingEntityAccessor;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValueType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public record DamageTakenRelativeEnchantmentEntityEffectType(EnchantmentLevelBasedValueType factor, RegistryEntry<DamageType> damageType) implements RelativeEnchantmentEntityEffectType
{

    public static final MapCodec<DamageTakenRelativeEnchantmentEntityEffectType> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                    EnchantmentLevelBasedValueType.CODEC.fieldOf("factor").forGetter(DamageTakenRelativeEnchantmentEntityEffectType::factor),
                    DamageType.ENTRY_CODEC.fieldOf("damage_type").forGetter(DamageTakenRelativeEnchantmentEntityEffectType::damageType)
                ).apply(instance, DamageTakenRelativeEnchantmentEntityEffectType::new)
            );

    @Override
    public void applyRelative(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos, Entity relativeTo) {
        if (!(relativeTo instanceof LivingEntity livingEntity)) return;
        float damage = ((LivingEntityAccessor)livingEntity).getLastDamageTaken();
        float f = damage * factor.getValue(level);
        user.damage(new DamageSource(this.damageType, context.owner()), f);
    }

    @Override
    public MapCodec<? extends RelativeEnchantmentEntityEffectType> getCodec() {
        return CODEC;
    }
}