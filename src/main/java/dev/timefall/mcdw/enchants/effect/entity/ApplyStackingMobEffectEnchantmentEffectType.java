package dev.timefall.mcdw.enchants.effect.entity;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.EnchantmentLevelBasedValueType;
import net.minecraft.enchantment.effect.EnchantmentEntityEffectType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;

import java.util.Optional;

public record ApplyStackingMobEffectEnchantmentEffectType(
        RegistryEntryList<StatusEffect> toApply,
        EnchantmentLevelBasedValueType duration,
        EnchantmentLevelBasedValueType startingAmplifier,
        EnchantmentLevelBasedValueType maxAmplifier)implements EnchantmentEntityEffectType
{

    public static final MapCodec<ApplyStackingMobEffectEnchantmentEffectType> CODEC = RecordCodecBuilder.mapCodec(instance ->
                instance.group(
                        RegistryCodecs.entryList(RegistryKeys.STATUS_EFFECT).fieldOf("to_apply").forGetter(ApplyStackingMobEffectEnchantmentEffectType::toApply),
                        EnchantmentLevelBasedValueType.CODEC.fieldOf("duration").forGetter(ApplyStackingMobEffectEnchantmentEffectType::duration),
                        EnchantmentLevelBasedValueType.CODEC.optionalFieldOf("starting_amplifier", new EnchantmentLevelBasedValueType.Constant(0f)).forGetter(ApplyStackingMobEffectEnchantmentEffectType::startingAmplifier),
                        EnchantmentLevelBasedValueType.CODEC.fieldOf("max_amplifier").forGetter(ApplyStackingMobEffectEnchantmentEffectType::maxAmplifier)
                ).apply(instance, ApplyStackingMobEffectEnchantmentEffectType::new)
            );

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (user instanceof LivingEntity livingEntity){
            Random random = user.getRandom();
            Optional<RegistryEntry<StatusEffect>> optional;
            if((optional = this.toApply.getRandom(random)).isPresent()){
                int durationValue = (int) this.duration.getValue(level);
                StatusEffectInstance instance = livingEntity.getStatusEffect(optional.get());
                int amplifierValue = instance != null
                        ? Math.max((int) this.maxAmplifier.getValue(level), instance.getAmplifier() + 1)
                        : (int) this.startingAmplifier.getValue(level);
                livingEntity.addStatusEffect(new StatusEffectInstance(optional.get(), durationValue, amplifierValue));
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffectType> getCodec() {
        return CODEC;
    }
}