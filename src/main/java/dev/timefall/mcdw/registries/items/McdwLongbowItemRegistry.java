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
import dev.timefall.mcdw.bases.McdwLongbowItem;
import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import dev.timefall.mcdw.configs.stats.McdwWeaponStatsConfig;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class McdwLongbowItemRegistry {
    public static final McdwLongbowItem LONGBOW_GUARDIAN_BOW    = register("longbow_guardian_bow", McdwWeaponStatsConfig.CONFIG.getLongbowItemStats().getLongbowGuardianBow());
    public static final McdwLongbowItem LONGBOW_LONGBOW         = register("longbow_longbow", McdwWeaponStatsConfig.CONFIG.getLongbowItemStats().getLongbowLongbow());
    public static final McdwLongbowItem LONGBOW_RED_SNAKE       = register("longbow_red_snake", McdwWeaponStatsConfig.CONFIG.getLongbowItemStats().getLongbowRedSnake());

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

    private static McdwLongbowItem register(String name, IMcdwWeaponStats.RangedStats stats) {
        return Registry.register(Registries.ITEM, Mcdw.ID(name), makeWeapon(stats));
    }

    private static McdwLongbowItem makeWeapon(IMcdwWeaponStats.RangedStats stats) {
        return new McdwLongbowItem(
                stats,
                stats.material,
                new Item.Settings()
                        .maxDamage(150 + stats.material.getDurability())
                        .rarity(RarityHelper.fromToolMaterial(stats.material))
                        .maxCount(1),
                stats.drawSpeed,
                stats.range
        );
    }
}