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
import dev.timefall.mcdw.bases.McdwSwordItem;
import dev.timefall.mcdw.configs.CompatibilityFlags;
import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import dev.timefall.mcdw.configs.stats.McdwWeaponStatsConfig;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class McdwSwordItemRegistry {
    public static final McdwSwordItem SWORD_BEE_STINGER           = register("sword_bee_stinger", McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordBeeStinger());
    public static final McdwSwordItem SWORD_BROADSWORD            = register("sword_broadsword", McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordBroadsword());
    public static final McdwSwordItem SWORD_BROKEN_SAWBLADE       = register("sword_broken_sawblade", McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordBrokenSawblade());
    public static final McdwSwordItem SWORD_CLAYMORE              = register("sword_claymore", McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordClaymore());
    public static final McdwSwordItem SWORD_CORAL_BLADE           = register("sword_coral_blade", McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordCoralBlade());
    public static final McdwSwordItem SWORD_CUTLASS               = register("sword_cutlass", McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordCutlass());
    public static final McdwSwordItem SWORD_DANCERS_SWORD         = register("sword_dancers_sword", McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordDancersSword());
    public static final McdwSwordItem SWORD_DARK_KATANA           = register("sword_dark_katana", McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordDarkKatana());
    public static final McdwSwordItem SWORD_DIAMOND_SWORD_VAR     = register("sword_diamond_sword_var", McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordDiamondSwordVar());
    public static final McdwSwordItem SWORD_FREEZING_FOIL         = register("sword_freezing_foil", McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordFreezingFoil());
    public static final McdwSwordItem SWORD_FROST_SLAYER          = register("sword_frost_slayer", McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordFrostSlayer());
    public static final McdwSwordItem SWORD_GREAT_AXEBLADE        = register("sword_great_axeblade", McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordGreatAxeblade());
    public static final McdwSwordItem SWORD_HAWKBRAND             = register("sword_hawkbrand", McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordHawkbrand());
    public static final McdwSwordItem SWORD_HEARTSTEALER          = register("sword_heartstealer", McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordHeartstealer());
    public static final McdwSwordItem SWORD_IRON_SWORD_VAR        = register("sword_iron_sword_var", McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordIronSwordVar());
    public static final McdwSwordItem SWORD_KATANA                = register("sword_katana", McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordKatana());
    public static final McdwSwordItem SWORD_MASTERS_KATANA        = register("sword_masters_katana", McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordMastersKatana());
    public static final McdwSwordItem SWORD_MECHANIZED_SAWBLADE   = register("sword_mechanized_sawblade", McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordMechanizedSawblade());
    public static final McdwSwordItem SWORD_NAMELESS_BLADE        = register("sword_nameless_blade", McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordNamelessBlade());
    public static final McdwSwordItem SWORD_OBSIDIAN_CLAYMORE     = register("sword_obsidian_claymore", McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordObsidianClaymore());
    public static final McdwSwordItem SWORD_RAPIER                = register("sword_rapier", McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordRapier());
    public static final McdwSwordItem SWORD_SINISTER_SWORD        = register("sword_sinister_sword", McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordSinisterSword());
    public static final McdwSwordItem SWORD_SPONGE_STRIKER        = register("sword_sponge_striker", McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordSpongeStriker());
    public static final McdwSwordItem SWORD_THE_STARLESS_NIGHT    = register("sword_the_starless_night", McdwWeaponStatsConfig.CONFIG.getSwordItemStats().getSwordTheStarlessNight());

    public static void register() {

    }

    private static McdwSwordItem register(String name, IMcdwWeaponStats.MeleeStats stats) {
        return Registry.register(Registries.ITEM, Mcdw.ID(name), makeWeapon(stats));
    }

    private static McdwSwordItem makeWeapon(IMcdwWeaponStats.MeleeStats stats) {
        if (CompatibilityFlags.isReachExtensionEnabled) {
            return new McdwSwordItem(
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
        return new McdwSwordItem(
                stats,
                stats.material,
                new Item.Settings()
                        .maxDamage(stats.material.getDurability())
                        .rarity(RarityHelper.fromToolMaterial(stats.material))
                        .maxCount(1)
                        .attributeModifiers(
                                SwordItem.createAttributeModifiers(
                                        stats.material,
                                        stats.damage,
                                        stats.attackSpeed
                                )
                        )
        );
    }
}
