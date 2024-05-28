/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.enchants.effect.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValueType;
import net.minecraft.enchantment.effect.EnchantmentEntityEffectType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;

import java.util.Optional;

public record ApplyStackingMobEffectEnchantmentEntityEffectType(
        RegistryEntryList<StatusEffect> toApply,
        EnchantmentLevelBasedValueType duration,
        EnchantmentLevelBasedValueType startingAmplifier,
        EnchantmentLevelBasedValueType maxAmplifier,
        boolean popIfMaxed)implements EnchantmentEntityEffectType
{

    public ApplyStackingMobEffectEnchantmentEntityEffectType(
            RegistryEntry<StatusEffect> toApply,
            EnchantmentLevelBasedValueType duration,
            EnchantmentLevelBasedValueType maxAmplifier)
    {
        this(RegistryEntryList.of(toApply), duration, EnchantmentLevelBasedValueType.constant(0f), maxAmplifier, false);
    }

    public static final MapCodec<ApplyStackingMobEffectEnchantmentEntityEffectType> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        RegistryCodecs.entryList(RegistryKeys.STATUS_EFFECT).fieldOf("to_apply").forGetter(ApplyStackingMobEffectEnchantmentEntityEffectType::toApply),
                        EnchantmentLevelBasedValueType.CODEC.fieldOf("duration").forGetter(ApplyStackingMobEffectEnchantmentEntityEffectType::duration),
                        EnchantmentLevelBasedValueType.CODEC.optionalFieldOf("starting_amplifier", new EnchantmentLevelBasedValueType.Constant(0f)).forGetter(ApplyStackingMobEffectEnchantmentEntityEffectType::startingAmplifier),
                        EnchantmentLevelBasedValueType.CODEC.fieldOf("max_amplifier").forGetter(ApplyStackingMobEffectEnchantmentEntityEffectType::maxAmplifier),
                        Codec.BOOL.optionalFieldOf("pop_if_maxed",false).forGetter(ApplyStackingMobEffectEnchantmentEntityEffectType::popIfMaxed)
                ).apply(instance, ApplyStackingMobEffectEnchantmentEntityEffectType::new)
            );

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (user instanceof LivingEntity livingEntity){
            Random random = user.getRandom();
            Optional<RegistryEntry<StatusEffect>> optional;
            if((optional = this.toApply.getRandom(random)).isPresent()){
                int durationValue = (int) this.duration.getValue(level);
                StatusEffectInstance instance = livingEntity.getStatusEffect(optional.get());
                int amplifierValue = instance != null
                        ? Math.max((int) this.maxAmplifier.getValue(level), instance.getAmplifier() + 1)
                        : (int) this.startingAmplifier.getValue(level);
                if (popIfMaxed && instance != null && instance.getAmplifier() == this.maxAmplifier.getValue(level))
                    livingEntity.removeStatusEffect(optional.get());
                else
                    livingEntity.addStatusEffect(new StatusEffectInstance(optional.get(), durationValue, amplifierValue));
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffectType> getCodec() {
        return CODEC;
    }
}