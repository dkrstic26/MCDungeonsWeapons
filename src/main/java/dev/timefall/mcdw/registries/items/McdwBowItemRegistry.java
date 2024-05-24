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
import dev.timefall.mcdw.bases.McdwBowItem;
import dev.timefall.mcdw.configs.stats.IMcdwWeaponStats;
import dev.timefall.mcdw.configs.stats.McdwWeaponStatsConfig;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class McdwBowItemRegistry {
    public static final McdwBowItem BOW_ANCIENT_BOW         = register("bow_ancient_bow", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowAncientBow());
    public static final McdwBowItem BOW_BONEBOW             = register("bow_bonebow", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowBonebow());
    public static final McdwBowItem BOW_BUBBLE_BOW          = register("bow_bubble_bow", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowBubbleBow());
    public static final McdwBowItem BOW_BUBBLE_BURSTER      = register("bow_bubble_burster", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowBubbleBurster());
    public static final McdwBowItem BOW_BURST_GALE_BOW      = register("bow_burst_gale_bow", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowBurstGaleBow());
    public static final McdwBowItem BOW_CALL_OF_THE_VOID    = register("bow_call_of_the_void", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowCallOfTheVoid());
    public static final McdwBowItem BOW_ECHO_OF_THE_VALLEY  = register("bow_echo_of_the_valley", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowEchoOfTheValley());
    public static final McdwBowItem BOW_ELITE_POWER_BOW     = register("bow_elite_power_bow", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowElitePowerBow());
    public static final McdwBowItem BOW_GREEN_MENACE        = register("bow_green_menace", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowGreenMenace());
    public static final McdwBowItem BOW_HAUNTED_BOW         = register("bow_haunted_bow", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowHauntedBow());
    public static final McdwBowItem BOW_HUNTERS_PROMISE     = register("bow_hunters_promise", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowHuntersPromise());
    public static final McdwBowItem BOW_HUNTING_BOW         = register("bow_hunting_bow", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowHuntingBow());
    public static final McdwBowItem BOW_LOST_SOULS          = register("bow_lost_souls", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowLostSouls());
    public static final McdwBowItem BOW_MASTERS_BOW         = register("bow_masters_bow", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowMastersBow());
    public static final McdwBowItem BOW_NOCTURNAL_BOW       = register("bow_nocturnal_bow", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowNocturnalBow());
    public static final McdwBowItem BOW_PHANTOM_BOW         = register("bow_phantom_bow", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowPhantomBow());
    public static final McdwBowItem BOW_PINK_SCOUNDREL      = register("bow_pink_scoundrel", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowPinkScoundrel());
    public static final McdwBowItem BOW_POWER_BOW           = register("bow_power_bow", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowPowerBow());
    public static final McdwBowItem BOW_SABREWING           = register("bow_sabrewing", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowSabrewing());
    public static final McdwBowItem BOW_SHIVERING_BOW       = register("bow_shivering_bow", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowShiveringBow());
    public static final McdwBowItem BOW_SNOW_BOW            = register("bow_snow_bow", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowSnowBow());
    public static final McdwBowItem BOW_SOUL_BOW            = register("bow_soul_bow", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowSoulBow());
    public static final McdwBowItem BOW_TRICKBOW            = register("bow_trickbow", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowTrickbow());
    public static final McdwBowItem BOW_TWIN_BOW            = register("bow_twin_bow", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowTwinBow());
    public static final McdwBowItem BOW_TWISTING_VINE_BOW   = register("bow_twisting_vine_bow", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowTwistingVineBow());
    public static final McdwBowItem BOW_VOID_BOW            = register("bow_void_bow", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowVoidBow());
    public static final McdwBowItem BOW_WEB_BOW             = register("bow_web_bow", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowWebBow());
    public static final McdwBowItem BOW_WEEPING_VINE_BOW    = register("bow_weeping_vine_bow", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowWeepingVineBow());
    public static final McdwBowItem BOW_WIND_BOW            = register("bow_wind_bow", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowWindBow());
    public static final McdwBowItem BOW_WINTERS_TOUCH       = register("bow_winters_touch", McdwWeaponStatsConfig.CONFIG.getBowItemStats().getBowWintersTouch());

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

    private static McdwBowItem register(String name, IMcdwWeaponStats.RangedStats stats) {
        return Registry.register(Registries.ITEM, Mcdw.ID(name), makeWeapon(stats));
    }

    private static McdwBowItem makeWeapon(IMcdwWeaponStats.RangedStats stats) {
        return new McdwBowItem(
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