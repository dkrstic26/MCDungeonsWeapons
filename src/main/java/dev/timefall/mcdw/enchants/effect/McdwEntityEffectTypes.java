/*
 * Timefall Development License 1.2
 * Copyright (c) 2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

package dev.timefall.mcdw.enchants.effect;

import com.mojang.serialization.MapCodec;
import dev.timefall.mcdw.Mcdw;
import dev.timefall.mcdw.enchants.effect.entity.AOEEnchantmentEntityEffectType;
import dev.timefall.mcdw.enchants.effect.entity.ApplyStackingMobEffectEnchantmentEntityEffectType;
import dev.timefall.mcdw.enchants.effect.entity.LeechMobEnchantmentEntityEffectType;
import dev.timefall.mcdw.enchants.effect.entity.RemoveMobEffectEnchantmentEntityEffectType;
import net.minecraft.enchantment.effect.EnchantmentEntityEffectType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class McdwEntityEffectTypes {

    public static MapCodec<AOEEnchantmentEntityEffectType> AOE_EFFECT = register("aoe_effect", AOEEnchantmentEntityEffectType.CODEC);
    public static MapCodec<ApplyStackingMobEffectEnchantmentEntityEffectType> APPLY_STACKING_MOB_EFFECT = register("apply_stacking_mob_effect", ApplyStackingMobEffectEnchantmentEntityEffectType.CODEC);
    public static MapCodec<LeechMobEnchantmentEntityEffectType> LEECH_MOB_EFFECT = register("leech_mob_effect", LeechMobEnchantmentEntityEffectType.CODEC);
    public static MapCodec<RemoveMobEffectEnchantmentEntityEffectType> REMOVE_MOB_EFFECT = register("remove_mob_effect", RemoveMobEffectEnchantmentEntityEffectType.CODEC);


    private static <T extends EnchantmentEntityEffectType> MapCodec<T> register(String id, MapCodec<T> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Mcdw.ID(id), codec);
    }

}