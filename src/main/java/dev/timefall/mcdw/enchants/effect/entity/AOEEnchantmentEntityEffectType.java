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
import dev.timefall.mcdw.mixin.LivingEntityAccessor;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValueType;
import net.minecraft.enchantment.effect.EnchantmentEntityEffectType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public record AOEEnchantmentEntityEffectType(EnchantmentLevelBasedValueType radius, EnchantmentEntityEffectType effect, Optional<EnchantmentLevelBasedValueType> targetLimit, boolean ignoreTargetEntity)implements EnchantmentEntityEffectType {

    public AOEEnchantmentEntityEffectType(EnchantmentLevelBasedValueType radius, EnchantmentEntityEffectType effect){
        this(radius, effect, Optional.empty(), true);
    }

    public static final MapCodec<AOEEnchantmentEntityEffectType> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                    EnchantmentLevelBasedValueType.CODEC.fieldOf("radius").forGetter(AOEEnchantmentEntityEffectType::radius),
                    EnchantmentEntityEffectType.CODEC.fieldOf("effect").forGetter(AOEEnchantmentEntityEffectType::effect),
                    EnchantmentLevelBasedValueType.CODEC.optionalFieldOf("target_limit").forGetter(AOEEnchantmentEntityEffectType::targetLimit),
                    Codec.BOOL.optionalFieldOf("ignore_target_entity", true).forGetter(AOEEnchantmentEntityEffectType::ignoreTargetEntity)
                ).apply(instance, AOEEnchantmentEntityEffectType::new)
            );

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        double d = (double) this.radius.getValue(level) * 2.0;
        Predicate<Entity> aoePredicate = e -> true;
        Predicate<Entity> predicate = e -> (!this.ignoreTargetEntity || e != user) && aoePredicate.test(e);
        List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, Box.of(pos, d, d, d), predicate);
        if (effect instanceof RelativeEnchantmentEntityEffectType relativeEffect) {
            float damage = user instanceof LivingEntity ? ((LivingEntityAccessor)user).getLastDamageTaken() : 0f;
            if (this.targetLimit.isPresent()) {
                Collections.shuffle(entities);
                int limit = (int) this.targetLimit.get().getValue(level);
                for (LivingEntity livingEntity : entities) {
                    relativeEffect.applyRelative(world, level, context, livingEntity, pos, user);
                    --limit;
                    if (limit <= 0) break;
                }
            } else {
                for (LivingEntity livingEntity : entities) {
                    relativeEffect.applyRelative(world, level, context, livingEntity, pos, user);
                }
            }
        } else {
            if (this.targetLimit.isPresent()) {
                Collections.shuffle(entities);
                int limit = (int) this.targetLimit.get().getValue(level);
                for (LivingEntity livingEntity : entities) {
                    effect.apply(world, level, context, livingEntity, pos);
                    --limit;
                    if (limit <= 0) break;
                }
            } else {
                for (LivingEntity livingEntity : entities) {
                    effect.apply(world, level, context, livingEntity, pos);
                }
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffectType> getCodec() {
        return CODEC;
    }
}