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
import dev.timefall.mcdw.bases.McdwDaggerItem;
import dev.timefall.mcdw.configs.CompatibilityFlags;
import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import dev.timefall.mcdw.configs.stats.McdwWeaponStatsConfig;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class McdwDaggerItemRegistry {

    public static final McdwDaggerItem DAGGER_BACKSTABBER              = register("dagger_backstabber_", McdwWeaponStatsConfig.CONFIG.getDaggerItemStats().getDaggerBackstabber());
    public static final McdwDaggerItem DAGGER_DAGGER                   = register("dagger_dagger", McdwWeaponStatsConfig.CONFIG.getDaggerItemStats().getDaggerDagger());
    public static final McdwDaggerItem DAGGER_CHILL_GALE_KNIFE         = register("dagger_chill_gale_knife", McdwWeaponStatsConfig.CONFIG.getDaggerItemStats().getDaggerChillGaleKnife());
    public static final McdwDaggerItem DAGGER_FANGS_OF_FROST           = register("dagger_fangs_of_frost", McdwWeaponStatsConfig.CONFIG.getDaggerItemStats().getDaggerFangsOfFrost());
    public static final McdwDaggerItem DAGGER_MOON                     = register("dagger_moon", McdwWeaponStatsConfig.CONFIG.getDaggerItemStats().getDaggerMoon());
    public static final McdwDaggerItem DAGGER_RESOLUTE_TEMPEST_KNIFE   = register("dagger_resolute_tempest_knife", McdwWeaponStatsConfig.CONFIG.getDaggerItemStats().getDaggerResoluteTempestKnife());
    public static final McdwDaggerItem DAGGER_SHEAR_DAGGER             = register("dagger_shear_dagger", McdwWeaponStatsConfig.CONFIG.getDaggerItemStats().getDaggerShearDagger());
    public static final McdwDaggerItem DAGGER_SWIFT_STRIKER            = register("dagger_swift_striker", McdwWeaponStatsConfig.CONFIG.getDaggerItemStats().getDaggerSwiftStriker());
    public static final McdwDaggerItem DAGGER_TEMPEST_KNIFE            = register("dagger_tempest_knife", McdwWeaponStatsConfig.CONFIG.getDaggerItemStats().getDaggerTempestKnife());
    public static final McdwDaggerItem DAGGER_THE_BEGINNING            = register("dagger_the_beginning", McdwWeaponStatsConfig.CONFIG.getDaggerItemStats().getDaggerTheBeginning());
    public static final McdwDaggerItem DAGGER_THE_END                  = register("dagger_the_end", McdwWeaponStatsConfig.CONFIG.getDaggerItemStats().getDaggerTheEnd());
    public static final McdwDaggerItem DAGGER_VOID_TOUCHED_BLADE       = register("dagger_void_touched_blade", McdwWeaponStatsConfig.CONFIG.getDaggerItemStats().getDaggerVoidTouchedBlade());

    public static void register() {

    }

    private static McdwDaggerItem register(String name, IMcdwWeaponStats.MeleeStats stats) {
        return Registry.register(Registries.ITEM, Mcdw.ID(name), makeWeapon(stats));
    }

    private static McdwDaggerItem makeWeapon(IMcdwWeaponStats.MeleeStats stats) {
        if (CompatibilityFlags.isReachExtensionEnabled) {
            return new McdwDaggerItem(
                    stats,
                    stats.material,
                    new Item.Settings()
                            .maxDamage(stats.material.getDurability())
                            .rarity(RarityHelper.fromToolMaterial(stats.material))
                            .maxCount(1)
                            .attributeModifiers(
                                    CleanlinessHelper.createDualWieldWithRangeAttributeModifiers(
                                            stats.material,
                                            stats.damage,
                                            stats.attackSpeed,
                                            stats.additionalAttackRange
                                    )
                            )
            );
        }
        return new McdwDaggerItem(
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
