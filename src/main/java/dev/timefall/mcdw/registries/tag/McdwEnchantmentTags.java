package dev.timefall.mcdw.registries.tag;

import dev.timefall.mcdw.Mcdw;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class McdwEnchantmentTags {

    public static final TagKey<Enchantment> EXPERIENCE_EXCLUSIVE = TagKey.of(RegistryKeys.ENCHANTMENT, Mcdw.ID("experience_exclusive"));
    public static final TagKey<Enchantment> HEALING_EXCLUSIVE    = TagKey.of(RegistryKeys.ENCHANTMENT, Mcdw.ID("healing_exclusive"));
}