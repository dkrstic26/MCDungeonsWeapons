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
import dev.timefall.mcdw.bases.McdwCrossbowItem;
import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import dev.timefall.mcdw.configs.stats.McdwWeaponStatsConfig;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class McdwCrossbowItemRegistry {
    public static final McdwCrossbowItem CROSSBOW_AUTO_CROSSBOW = register("crossbow_auto_crossbow", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowAutoCrossbow());
    public static final McdwCrossbowItem CROSSBOW_AZURE_SEEKER = register("crossbow_azure_seeker", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowAzureSeeker());
    public static final McdwCrossbowItem CROSSBOW_BABY_CROSSBOW = register("crossbow_baby_crossbow", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowBabyCrossbow());
    public static final McdwCrossbowItem CROSSBOW_BURST_CROSSBOW = register("crossbow_burst_crossbow", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowBurstCrossbow());
    public static final McdwCrossbowItem CROSSBOW_BUTTERFLY_CROSSBOW = register("crossbow_butterfly_crossbow", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowButterflyCrossbow());
    public static final McdwCrossbowItem CROSSBOW_COG_CROSSBOW = register("crossbow_cog_crossbow", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowCogCrossbow());
    public static final McdwCrossbowItem CROSSBOW_CORRUPTED_CROSSBOW = register("crossbow_corrupted_crossbow", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowCorruptedCrossbow());
    public static final McdwCrossbowItem CROSSBOW_DOOM_CROSSBOW = register("crossbow_doom_crossbow", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowDoomCrossbow());
    public static final McdwCrossbowItem CROSSBOW_DUAL_CROSSBOW = register("crossbow_dual_crossbow", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowDualCrossbow());
    public static final McdwCrossbowItem CROSSBOW_EXPLODING_CROSSBOW = register("crossbow_exploding_crossbow", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowExplodingCrossbow());
    public static final McdwCrossbowItem CROSSBOW_FERAL_SOUL_CROSSBOW = register("crossbow_feral_soul_crossbow", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowFeralSoulCrossbow());
    public static final McdwCrossbowItem CROSSBOW_FIREBOLT_THROWER = register("crossbow_firebolt_thrower", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowFireboltThrower());
    public static final McdwCrossbowItem CROSSBOW_HARPOON_CROSSBOW = register("crossbow_harpoon_crossbow", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowHarpoonCrossbow());
    public static final McdwCrossbowItem CROSSBOW_HARP_CROSSBOW = register("crossbow_harp_crossbow", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowHarpCrossbow());
    public static final McdwCrossbowItem CROSSBOW_HEAVY_CROSSBOW = register("crossbow_heavy_crossbow", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowHeavyCrossbow());
    public static final McdwCrossbowItem CROSSBOW_IMPLODING_CROSSBOW = register("crossbow_imploding_crossbow", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowImplodingCrossbow());
    public static final McdwCrossbowItem CROSSBOW_LIGHTNING_HARP_CROSSBOW = register("crossbow_lightning_harp_crossbow", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowLightningHarpCrossbow());
    public static final McdwCrossbowItem CROSSBOW_NAUTICAL_CROSSBOW = register("crossbow_nautical_crossbow", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowNauticalCrossbow());
    public static final McdwCrossbowItem CROSSBOW_PRIDE_OF_THE_PIGLINS = register("crossbow_pride_of_the_piglins", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowPrideOfThePiglins());
    public static final McdwCrossbowItem CROSSBOW_RAPID_CROSSBOW = register("crossbow_rapid_crossbow", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowRapidCrossbow());
    public static final McdwCrossbowItem CROSSBOW_SCATTER_CROSSBOW = register("crossbow_scatter_crossbow", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowScatterCrossbow());
    public static final McdwCrossbowItem CROSSBOW_SHADOW_CROSSBOW = register("crossbow_shadow_crossbow", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowShadowCrossbow());
    public static final McdwCrossbowItem CROSSBOW_SLAYER_CROSSBOW = register("crossbow_slayer_crossbow", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowSlayerCrossbow());
    public static final McdwCrossbowItem CROSSBOW_SOUL_CROSSBOW = register("crossbow_soul_crossbow", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowSoulCrossbow());
    public static final McdwCrossbowItem CROSSBOW_SOUL_HUNTER_CROSSBOW = register("crossbow_soul_hunter_crossbow", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowSoulHunterCrossbow());
    public static final McdwCrossbowItem CROSSBOW_SPELLBOUND_CROSSBOW = register("crossbow_spellbound_crossbow", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowSpellboundCrossbow());
    public static final McdwCrossbowItem CROSSBOW_THE_SLICER = register("crossbow_the_slicer", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowTheSlicer());
    public static final McdwCrossbowItem CROSSBOW_VEILED_CROSSBOW = register("crossbow_veiled_crossbow", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowVeiledCrossbow());
    public static final McdwCrossbowItem CROSSBOW_VOIDCALLER_CROSSBOW = register("crossbow_voidcaller_crossbow", McdwWeaponStatsConfig.CONFIG.getCrossbowItemStats().getCrossbowVoidcallerCrossbow());


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

    private static McdwCrossbowItem register(String name, IMcdwWeaponStats.RangedStats stats) {
        return Registry.register(Registries.ITEM, Mcdw.ID(name), makeWeapon(stats));
    }

    private static McdwCrossbowItem makeWeapon(IMcdwWeaponStats.RangedStats stats) {
        return new McdwCrossbowItem(
                stats,
                stats.material,
                new Item.Settings()
                        .maxDamage(100 + stats.material.getDurability())
                        .rarity(RarityHelper.fromToolMaterial(stats.material))
                        .maxCount(1),
                stats.drawSpeed,
                stats.range
        );
    }
}