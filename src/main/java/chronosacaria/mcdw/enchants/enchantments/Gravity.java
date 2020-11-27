package chronosacaria.mcdw.enchants.enchantments;

import chronosacaria.mcdw.Mcdw;
import chronosacaria.mcdw.enchants.util.AOEHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Gravity extends Enchantment {
    public Gravity (Rarity weight, EnchantmentTarget type, EquipmentSlot[] slotTypes) {
        super(weight, type, slotTypes);
        Registry.register(Registry.ENCHANTMENT,new Identifier(Mcdw.MOD_ID, "gravity"),this);
    }

    @Override
    public int getMaxLevel(){
        return 3;
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level){


        if (!(target instanceof LivingEntity)) return;
        float chance = user.getRandom().nextFloat();
        if (chance <= 0.3) {
            AOEHelper.pullInNearbyEntities(
                    user,
                    target,
                    level * 3);
        }
    }
}
