/*
 * Timefall Development License 1.2
 * Copyright (c) 2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

package dev.timefall.mcdw.enchants.effect;

import com.mojang.serialization.MapCodec;
import dev.timefall.mcdw.Mcdw;
import dev.timefall.mcdw.enchants.effect.entity.ApplyStackingMobEffectEnchantmentEffectType;
import dev.timefall.mcdw.enchants.effect.entity.LeechMobEnchantmentEffectType;
import net.minecraft.enchantment.effect.EnchantmentEntityEffectType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class McdwEntityEffectTypes {

    public static MapCodec<ApplyStackingMobEffectEnchantmentEffectType> APPLY_STACKING_MOB_EFFECT = register("apply_stacking_mob_effect", ApplyStackingMobEffectEnchantmentEffectType.CODEC);
    public static MapCodec<LeechMobEnchantmentEffectType> LEECH_MOB_EFFECT = register("leech_mob_effect", LeechMobEnchantmentEffectType.CODEC);


    private static <T extends EnchantmentEntityEffectType> MapCodec<T> register(String id, MapCodec<T> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Mcdw.ID(id), codec);
    }

}