package dev.timefall.mcdw.enchants.effect.entity;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValueType;
import net.minecraft.enchantment.effect.EnchantmentEntityEffectType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public record LeechMobEnchantmentEffectType(EnchantmentLevelBasedValueType amount)implements EnchantmentEntityEffectType
{

    public static final MapCodec<LeechMobEnchantmentEffectType> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        EnchantmentLevelBasedValueType.CODEC.fieldOf("amount").forGetter(LeechMobEnchantmentEffectType::amount)
                ).apply(instance, LeechMobEnchantmentEffectType::new)
            );

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (user instanceof LivingEntity victim){
            if (context.owner() != null){
                float healthRegained = this.amount.getValue(level) * victim.getMaxHealth();
                context.owner().heal(healthRegained);
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffectType> getCodec() {
        return CODEC;
    }
}