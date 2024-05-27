/*
 * Timefall Development License 1.2
 * Copyright (c) 2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */

package dev.timefall.mcdw.data;

import dev.timefall.mcdw.component.McdwEffectComponentTypes;
import dev.timefall.mcdw.enchants.EnchantmentIds;
import dev.timefall.mcdw.enchants.effect.entity.LeechMobEnchantmentEffectType;
import dev.timefall.mcdw.registries.tag.McdwEnchantmentTags;
import net.minecraft.block.Block;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValueType;
import net.minecraft.enchantment.effect.EnchantmentEffectTarget;
import net.minecraft.enchantment.effect.value.AddEnchantmentEffectType;
import net.minecraft.enchantment.effect.value.MultiplyEnchantmentEffectType;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.item.Item;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.ItemTags;

public class McdwEnchantmentGenerator {
    public static void generate(Registerable<Enchantment> enchantment) {

        RegistryEntryLookup<DamageType> damageTypeLookup = enchantment.getRegistryLookup(RegistryKeys.DAMAGE_TYPE);
        RegistryEntryLookup<Enchantment> enchantmentLookup = enchantment.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        RegistryEntryLookup<Item> itemLookup = enchantment.getRegistryLookup(RegistryKeys.ITEM);
        RegistryEntryLookup<Block> blockLookup = enchantment.getRegistryLookup(RegistryKeys.BLOCK);

        // LEECHING
        register(
                enchantment,
                EnchantmentIds.LEECHING,
                Enchantment.builder(
                        Enchantment.definition(
                                itemLookup.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                1,
                                3,
                                Enchantment.leveledCost(1, 10),
                                Enchantment.leveledCost(16, 10),
                                2,
                                AttributeModifierSlot.HAND
                        )
                ).exclusiveSet(
                        enchantmentLookup.getOrThrow(McdwEnchantmentTags.HEALING_EXCLUSIVE)
                ).addEffect(
                        McdwEffectComponentTypes.POST_DEATH,
                        EnchantmentEffectTarget.ATTACKER,
                        EnchantmentEffectTarget.VICTIM,
                        new LeechMobEnchantmentEffectType(EnchantmentLevelBasedValueType.linear(0.4f, 0.2f))
                )
        );


        // SOUL_DEVOURER
        register(
            // the enchantment registerable
            enchantment,
            // the RegistryKey of SOUL_DEVOURER, kept in the Ids class
            EnchantmentIds.SOUL_DEVOURER,
            //the enchantment builder itself
            Enchantment.builder(
                // first, the definitions. This is the "basic stats" of the enchantment.
                Enchantment.definition(
                    // tag of valid items. I used WEAPON, but another could be used, or a custom tag made. Old way uses Sword, Axe, or MCDW Custom, so maybe needs to be custom.
                    // As is, this would become enchantable on tridents, maces, etc. as well as swords/axes.
                    itemLookup.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                    // weight of the enchantment, previously the "Rarity". Used the old "VERY_RARE".
                    1,
                    // max level, self-explanatory
                    3,
                    //minimum enchanting cost. old style did not customize this, so I used the "default" values for these
                    Enchantment.leveledCost(1, 10),
                    //maximum enchanting cost. defaults used again
                    Enchantment.leveledCost(16, 10),
                    //Anvil Cost. I set this middle of the road, but not really sure what to do with it tbh, this is a new thing.
                    2,
                    //valid slots the weapon can be in. I used "HAND", which enables off-hand holding. Might need to play with.
                    AttributeModifierSlot.HAND
                )
            // next we add the exclusions. I made new tag holder class, and this is exclusive with the other "Experience" enchants. The tag includes this, by design.
            ).exclusiveSet(
                enchantmentLookup.getOrThrow(McdwEnchantmentTags.EXPERIENCE_EXCLUSIVE)
            //Finally, the effect.
            ).addEffect(
                //the Mob Experience modification hook.
                EnchantmentEffectComponentTypes.MOB_EXPERIENCE,
                //we will multiply the drops by 1 + (level / 3.0), which is the same as "1.33333 + 0.33333 for each level beyond the first".
                new MultiplyEnchantmentEffectType(EnchantmentLevelBasedValueType.linear(1.33333f, 0.33333f))
            )
        );

        // SOUL_SIPHON
        register(
                enchantment,
                EnchantmentIds.SOUL_SIPHON,
                Enchantment.builder(
                        Enchantment.definition(
                                itemLookup.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                                1,
                                3,
                                Enchantment.leveledCost(1, 10),
                                Enchantment.leveledCost(16, 10),
                                2,
                                AttributeModifierSlot.MAINHAND
                        )
                ).exclusiveSet(
                        enchantmentLookup.getOrThrow(McdwEnchantmentTags.EXPERIENCE_EXCLUSIVE)
                ).addEffect(
                        EnchantmentEffectComponentTypes.MOB_EXPERIENCE,
                        new AddEnchantmentEffectType(EnchantmentLevelBasedValueType.linear(3f)),
                        //this addition happens 10% of the time
                        RandomChanceLootCondition.builder(0.1f)
                )
        );


        // Thundering Enchantment
    }

    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }
}