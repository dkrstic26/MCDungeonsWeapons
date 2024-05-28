/*
 * Timefall Development License 1.2
 * Copyright (c) 2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

package dev.timefall.mcdw.component;

import dev.timefall.mcdw.Mcdw;
import dev.timefall.mcdw.enchants.effect.EntityAwareValueEffectType;
import net.minecraft.component.ComponentType;
import net.minecraft.enchantment.effect.EnchantmentEffectEntry;
import net.minecraft.enchantment.effect.EnchantmentEntityEffectType;
import net.minecraft.enchantment.effect.EnchantmentValueEffectType;
import net.minecraft.enchantment.effect.TargetedEnchantmentEffectType;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import java.util.List;
import java.util.function.UnaryOperator;

public class McdwEffectComponentTypes {

    public static final ComponentType<EntityAwareValueEffectType> ACCELERATE_CHARGE_TIME = register("accelerate_charge_time", builder -> builder.codec(EntityAwareValueEffectType.CODEC));
    public static final ComponentType<List<EnchantmentEffectEntry<EntityAwareValueEffectType>>> ENTITY_AWARE_DAMAGE = register("entity_aware_damage", builder -> builder.codec(EnchantmentEffectEntry.createCodec(EntityAwareValueEffectType.CODEC, LootContextTypes.ENCHANTED_DAMAGE).listOf()));
    public static final ComponentType<List<EnchantmentEffectEntry<EnchantmentEntityEffectType>>> ON_JUMP = register("on_jump", builder -> builder.codec(EnchantmentEffectEntry.createCodec(EnchantmentEntityEffectType.CODEC, LootContextTypes.ENCHANTED_ENTITY).listOf()));
    public static final ComponentType<List<TargetedEnchantmentEffectType<EnchantmentEntityEffectType>>> POST_DEATH = register("post_death", builder -> builder.codec(TargetedEnchantmentEffectType.createPostAttackCodec(EnchantmentEntityEffectType.CODEC, LootContextTypes.ENCHANTED_DAMAGE).listOf()));
    public static final ComponentType<List<EnchantmentEffectEntry<EnchantmentValueEffectType>>> XP_REPAIR_PLAYER = register("xp_repair_player", builder -> builder.codec(EnchantmentEffectEntry.createCodec(EnchantmentValueEffectType.CODEC, LootContextTypes.ENCHANTED_ENTITY).listOf()));


    private static <T> ComponentType<T> register(String id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE, Mcdw.ID(id), (builderOperator.apply(ComponentType.builder())).build());
    }
}