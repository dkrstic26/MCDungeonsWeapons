/*
 * Timefall Development License 1.2
 * Copyright (c) 2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.registries.items;

import dev.timefall.mcdw.Mcdw;
import dev.timefall.mcdw.api.util.RarityHelper;
import dev.timefall.mcdw.bases.McdwShortbowItem;
import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import dev.timefall.mcdw.configs.stats.McdwWeaponStatsConfig;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class McdwShortbowItemRegistry {
    public static final McdwShortbowItem SHORTBOW_LOVE_SPELL_BOW       = register("shortbow_love_spell_bow", McdwWeaponStatsConfig.CONFIG.getShortbowItemStats().getShortbowLoveSpellBow());
    public static final McdwShortbowItem SHORTBOW_MECHANICAL_SHORTBOW  = register("shortbow_mechanical_shortbow", McdwWeaponStatsConfig.CONFIG.getShortbowItemStats().getShortbowMechanicalShortbow());
    public static final McdwShortbowItem SHORTBOW_PURPLE_STORM         = register("shortbow_purple_storm", McdwWeaponStatsConfig.CONFIG.getShortbowItemStats().getShortbowPurpleStorm());
    public static final McdwShortbowItem SHORTBOW_SHORTBOW             = register("shortbow_shortbow", McdwWeaponStatsConfig.CONFIG.getShortbowItemStats().getShortbowShortbow());

    //@Override
    //public double getProjectileDamage() {
    //    if (FabricLoader.getInstance().isModLoaded("ranged_weapon_api")) {
    //        return projectileDamage;
    //    } else {
    //        return 0;
    //    }
    //}

    public static void register() {

    }

    private static McdwShortbowItem register(String name, IMcdwWeaponStats.RangedStats stats) {
        return Registry.register(Registries.ITEM, Mcdw.ID(name), makeWeapon(stats));
    }

    private static McdwShortbowItem makeWeapon(IMcdwWeaponStats.RangedStats stats) {
        return new McdwShortbowItem(
                stats,
                stats.material,
                new Item.Settings()
                        .maxDamage(50 + stats.material.getDurability())
                        .rarity(RarityHelper.fromToolMaterial(stats.material))
                        .maxCount(1),
                stats.drawSpeed,
                stats.range
        );
    }
}