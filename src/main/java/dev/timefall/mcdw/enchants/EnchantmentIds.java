package dev.timefall.mcdw.enchants;

import dev.timefall.mcdw.Mcdw;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class EnchantmentIds {

    public static final RegistryKey<Enchantment> ACCELERATE    = EnchantmentIds.of("accelerate");

    public static final RegistryKey<Enchantment> ANIMA_CONDUIT = EnchantmentIds.of("anima_conduit");

    public static final RegistryKey<Enchantment> LEECHING      = EnchantmentIds.of("leeching");

    public static final RegistryKey<Enchantment> RADIANCE      = EnchantmentIds.of("radiance");

    public static final RegistryKey<Enchantment> SOUL_DEVOURER = EnchantmentIds.of("soul_devourer");
    public static final RegistryKey<Enchantment> SOUL_SIPHON   = EnchantmentIds.of("soul_siphon");


    private static RegistryKey<Enchantment> of(String id) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, Mcdw.ID(id));
    }
}