/*
 * Timefall Development License 1.2
 * Copyright (c) 2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

package dev.timefall.mcdw.configs;

import dev.timefall.mcdw.Mcdw;
import me.fzzyhmstrs.fzzy_config.api.ConfigApi;
import me.fzzyhmstrs.fzzy_config.config.Config;

import java.util.function.Supplier;

public class McdwEnchantmentSettingsConfig extends Config {
    public static final McdwEnchantmentSettingsConfig CONFIG = ConfigApi.registerAndLoadConfig((Supplier<McdwEnchantmentSettingsConfig>) McdwEnchantmentSettingsConfig::new);

    public McdwEnchantmentSettingsConfig() {
        super(Mcdw.ID("mcdw_enchantment_settings_config"));
    }

}
