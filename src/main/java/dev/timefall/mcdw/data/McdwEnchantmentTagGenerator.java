/*
 * Timefall Development License 1.2
 * Copyright (c) 2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

package dev.timefall.mcdw.data;

import dev.timefall.mcdw.Mcdw;
import dev.timefall.mcdw.enchants.EnchantmentIds;
import dev.timefall.mcdw.registries.items.*;
import dev.timefall.mcdw.registries.tag.McdwEnchantmentTags;
import dev.timefall.mcdw.registries.tag.McdwItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class McdwEnchantmentTagGenerator extends FabricTagProvider.EnchantmentTagProvider {
    public McdwEnchantmentTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

        // ENCHANTMENT TAGS
        getOrCreateTagBuilder(McdwEnchantmentTags.EXPERIENCE_EXCLUSIVE)
                .setReplace(false)
                .addOptional(EnchantmentIds.ANIMA_CONDUIT)
                .addOptional(EnchantmentIds.SOUL_DEVOURER)
                .addOptional(EnchantmentIds.SOUL_SIPHON);

        getOrCreateTagBuilder(McdwEnchantmentTags.HEALING_EXCLUSIVE)
                .setReplace(false)
                .addOptional(EnchantmentIds.ANIMA_CONDUIT)
                .addOptional(EnchantmentIds.LEECHING)
                .addOptional(EnchantmentIds.RADIANCE);

    }

}