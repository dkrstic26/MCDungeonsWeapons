/*
 * Timefall Development License 1.2
 * Copyright (c) 2020-2024. Chronosacaria, Kluzzio, Timefall Development. All Rights Reserved.
 *
 * This software's content is licensed under the Timefall Development License 1.2. You can find this license information here: https://github.com/Timefall-Development/Timefall-Development-Licence/blob/main/TimefallDevelopmentLicense1.2.txt
 */
package dev.timefall.mcdw.api.interfaces;

import dev.timefall.mcdw.api.util.PlayerAttackHelper;
import dev.timefall.mcdw.configs.CompatibilityFlags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public interface IOffhandAttack {
    default TypedActionResult<ItemStack> useOffhand(World world, PlayerEntity player, Hand hand) {
        if (CompatibilityFlags.noOffhandConflicts) {
            if (isOffHand(hand) && isClientWorld(world) && hasOffHandAttack(player) && canMixAndMatchWeapons(player)) {
                //OffhandAttackChecker.checkForOffhandAttack();
                ItemStack offhand = player.getStackInHand(hand);
                return new TypedActionResult<>(ActionResult.SUCCESS, offhand);
            }
        }
        return new TypedActionResult<>(ActionResult.PASS, player.getStackInHand(hand));
    }

    private boolean isOffHand(Hand hand) {
        return hand == Hand.OFF_HAND;
    }

    private boolean isClientWorld(World world) {
        return world.isClient;
    }

    private boolean hasOffHandAttack(PlayerEntity player) {
        return player.getOffHandStack().getItem() instanceof IOffhandAttack;
    }

    private boolean canMixAndMatchWeapons(PlayerEntity player) {
        return PlayerAttackHelper.mixAndMatchWeapons(player);
    }
}

