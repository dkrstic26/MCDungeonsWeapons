/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.enchants;

import dev.timefall.mcdw.Mcdw;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class EnchantmentIds {

    public static final List<Identifier> ENCHANTMENT_IDS       = new ArrayList<>();

    /////////////////

    public static final RegistryKey<Enchantment> ACCELERATE    = EnchantmentIds.of("accelerate");

    public static final RegistryKey<Enchantment> ANIMA_CONDUIT = EnchantmentIds.of("anima_conduit");

    public static final RegistryKey<Enchantment> DYNAMO = EnchantmentIds.of("dynamo");
    public static final RegistryKey<Enchantment> ECHO    = EnchantmentIds.of("echo");

    public static final RegistryKey<Enchantment> LEECHING      = EnchantmentIds.of("leeching");

    public static final RegistryKey<Enchantment> PAIN_CYCLE    = EnchantmentIds.of("pain_cycle");

    public static final RegistryKey<Enchantment> RADIANCE      = EnchantmentIds.of("radiance");

    public static final RegistryKey<Enchantment> SOUL_DEVOURER = EnchantmentIds.of("soul_devourer");
    public static final RegistryKey<Enchantment> SOUL_SIPHON   = EnchantmentIds.of("soul_siphon");

    public static void init(){}

    private static RegistryKey<Enchantment> of(String path) {
        Identifier id = Mcdw.ID(path);
        ENCHANTMENT_IDS.add(id);
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, id);
    }
}