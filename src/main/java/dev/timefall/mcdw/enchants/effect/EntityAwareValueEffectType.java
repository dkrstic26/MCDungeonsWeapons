/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.enchants.effect;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.timefall.mcdw.Mcdw;
import dev.timefall.mcdw.enchants.effect.entity_aware.ExperienceLevelValueEffectType;
import dev.timefall.mcdw.enchants.effect.entity_aware.MultiplyStatusValueEffectType;
import dev.timefall.mcdw.registries.McdwRegistries;
import it.unimi.dsi.fastutil.floats.FloatUnaryOperator;
import net.minecraft.entity.LivingEntity;
import net.minecraft.registry.Registry;
import net.minecraft.util.math.MathHelper;

import java.util.function.Function;

public interface EntityAwareValueEffectType {

    Codec<EntityAwareValueEffectType> CODEC = McdwRegistries.ENTITY_AWARE_VALUE_EFFECT_TYPES.getCodec().dispatch(EntityAwareValueEffectType::getCodec, Function.identity());

    float apply(int level, float input, LivingEntity entity);

    MapCodec<? extends EntityAwareValueEffectType> getCodec();

    static void register(Registry<MapCodec<? extends EntityAwareValueEffectType>> registry) {
        Registry.register(registry, Mcdw.ID("unit"), Unit.CODEC);
        Registry.register(registry, Mcdw.ID("multiply_status"), MultiplyStatusValueEffectType.CODEC);
        Registry.register(registry, Mcdw.ID("experience_level"), ExperienceLevelValueEffectType.CODEC);
    }

    /////////////

    class Unit implements EntityAwareValueEffectType {

        public static final MapCodec<Unit> CODEC = MapCodec.unit(new Unit());

        @Override
        public float apply(int level, float input, LivingEntity entity) {
            return input;
        }

        @Override
        public MapCodec<? extends EntityAwareValueEffectType> getCodec() {
            return CODEC;
        }
    }

    // a UnaryOperator that clamps a float input to the provided bounds
    record BoundedFloatUnaryOperator(float min, float max) implements FloatUnaryOperator {
        public static final Codec<BoundedFloatUnaryOperator> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                Codec.FLOAT.fieldOf("min").forGetter(BoundedFloatUnaryOperator::min),
                Codec.FLOAT.fieldOf("max").forGetter(BoundedFloatUnaryOperator::max)
            ).apply(instance, BoundedFloatUnaryOperator::new)
        );

        @Override
        public float apply(float x) {
            return MathHelper.clamp(x, min, max);
        }
    }
}