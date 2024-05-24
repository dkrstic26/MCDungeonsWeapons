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
import dev.timefall.mcdw.api.util.CleanlinessHelper;
import dev.timefall.mcdw.api.util.RarityHelper;
import dev.timefall.mcdw.bases.McdwHammerItem;
import dev.timefall.mcdw.configs.CompatibilityFlags;
import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import dev.timefall.mcdw.configs.stats.McdwWeaponStatsConfig;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class McdwHammerItemRegistry {
    public static final McdwHammerItem HAMMER_BONECLUB      = register("hammer_boneclub", McdwWeaponStatsConfig.CONFIG.getHammerItemStats().getHammerBoneclub());
    public static final McdwHammerItem HAMMER_BONE_CUDGEL   = register("hammer_bone_cudgel", McdwWeaponStatsConfig.CONFIG.getHammerItemStats().getHammerBoneCudgel());
    public static final McdwHammerItem HAMMER_FLAIL         = register("hammer_flail", McdwWeaponStatsConfig.CONFIG.getHammerItemStats().getHammerFlail());
    public static final McdwHammerItem HAMMER_GRAVITY       = register("hammer_gravity", McdwWeaponStatsConfig.CONFIG.getHammerItemStats().getHammerGravity()) ;
    public static final McdwHammerItem HAMMER_GREAT_HAMMER  = register("hammer_great_hammer", McdwWeaponStatsConfig.CONFIG.getHammerItemStats().getHammerGreatHammer());
    public static final McdwHammerItem HAMMER_MACE          = register("hammer_mace", McdwWeaponStatsConfig.CONFIG.getHammerItemStats().getHammerMace());
    public static final McdwHammerItem HAMMER_STORMLANDER   = register("hammer_stormlander", McdwWeaponStatsConfig.CONFIG.getHammerItemStats().getHammerStormlander());
    public static final McdwHammerItem HAMMER_SUNS_GRACE    = register("hammer_suns_grace", McdwWeaponStatsConfig.CONFIG.getHammerItemStats().getHammerSunsGrace());

    public static void register() {

    }

    private static McdwHammerItem register(String name, IMcdwWeaponStats.MeleeStats stats) {
        return Registry.register(Registries.ITEM, Mcdw.ID(name), makeWeapon(stats));
    }


    private static McdwHammerItem makeWeapon(IMcdwWeaponStats.MeleeStats stats) {
        if (CompatibilityFlags.isReachExtensionEnabled) {
            return new McdwHammerItem(
                    stats,
                    stats.material,
                    new Item.Settings()
                            .maxDamage(stats.material.getDurability())
                            .rarity(RarityHelper.fromToolMaterial(stats.material))
                            .maxCount(1)
                            .attributeModifiers(
                                    CleanlinessHelper.createBaseWithRangeAttributeModifiers(
                                            stats.material,
                                            stats.damage,
                                            stats.attackSpeed,
                                            stats.additionalAttackRange
                                    )
                            )
            );
        }
        return new McdwHammerItem(
                stats,
                stats.material,
                new Item.Settings()
                        .maxDamage(stats.material.getDurability())
                        .rarity(RarityHelper.fromToolMaterial(stats.material))
                        .maxCount(1)
                        .attributeModifiers(
                                CleanlinessHelper.createBaseAttributeModifiers(
                                        stats.material,
                                        stats.damage,
                                        stats.attackSpeed
                                )
                        )
        );
    }
}
