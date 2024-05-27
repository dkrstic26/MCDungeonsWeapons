package dev.timefall.mcdw.registries.tag;

import dev.timefall.mcdw.Mcdw;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class McdwItemTags {

    public static final TagKey<Item> COMMON_LOOT_POOL_ITEMS = TagKey.of(RegistryKeys.ITEM, Mcdw.ID("common_loot_pool_items"));
    public static final TagKey<Item> UNCOMMON_LOOT_POOL_ITEMS = TagKey.of(RegistryKeys.ITEM, Mcdw.ID("uncommon_loot_pool_items"));
    public static final TagKey<Item> RARE_LOOT_POOL_ITEMS = TagKey.of(RegistryKeys.ITEM, Mcdw.ID("rare_loot_pool_items"));
    public static final TagKey<Item> EPIC_LOOT_POOL_ITEMS = TagKey.of(RegistryKeys.ITEM, Mcdw.ID("epic_loot_pool_items"));

}