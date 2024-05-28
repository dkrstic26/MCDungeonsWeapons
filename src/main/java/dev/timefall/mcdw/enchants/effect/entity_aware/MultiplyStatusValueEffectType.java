/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.enchants.effect.entity_aware;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.timefall.mcdw.enchants.effect.EntityAwareValueEffectType;
import net.minecraft.enchantment.EnchantmentLevelBasedValueType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.Optional;

public record MultiplyStatusValueEffectType(RegistryEntry<StatusEffect> statusEffect, EnchantmentLevelBasedValueType amount, boolean provideFlatBonus, Optional<EntityAwareValueEffectType.BoundedFloatUnaryOperator> bounds) implements EntityAwareValueEffectType {

    public static final MapCodec<MultiplyStatusValueEffectType> CODEC = RecordCodecBuilder.mapCodec(instance ->
        instance.group(
            Registries.STATUS_EFFECT.getEntryCodec().fieldOf("status_effect").forGetter(MultiplyStatusValueEffectType::statusEffect),
            EnchantmentLevelBasedValueType.CODEC.fieldOf("amount").forGetter(MultiplyStatusValueEffectType::amount),
            Codec.BOOL.optionalFieldOf("provide_flat_bonus", false).forGetter(MultiplyStatusValueEffectType::provideFlatBonus),
            BoundedFloatUnaryOperator.CODEC.optionalFieldOf("bounds").forGetter(MultiplyStatusValueEffectType::bounds)
        ).apply(instance, MultiplyStatusValueEffectType::new)
    );

    @Override
    public float apply(int level, float input, LivingEntity entity) {
        //get status
        StatusEffectInstance instance = entity.getStatusEffect(statusEffect);

        //if no status and we don't provide a flat bonus, short circuit back the input
        if (instance == null && !provideFlatBonus) return input;

        //get the amplifier, 0 if the instance is null (this would be a flat bonus)
        int amplifier = instance == null ? 0 : instance.getAmplifier();

        //calculate the multiplier, optionally bounding it
        float multiplier = (bounds.isPresent()
            ? this.bounds.get().apply(this.amount.getValue(level) * (amplifier + 1f))
            : this.amount.getValue(level) * (amplifier + 1f));

        //return the multiplied input
        return input * (1f + multiplier);
    }

    @Override
    public MapCodec<? extends EntityAwareValueEffectType> getCodec() {
        return CODEC;
    }
}