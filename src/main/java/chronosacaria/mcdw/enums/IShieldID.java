package chronosacaria.mcdw.enums;

import chronosacaria.mcdw.configs.McdwNewStatsConfig;
import net.minecraft.item.ToolMaterial;

import java.util.HashMap;

public interface IShieldID extends IMcdwWeaponID {

    static IShieldID[] values() {
        return IMcdwWeaponID.shieldValues();
    }


    HashMap<ShieldsID, ShieldStats> getWeaponStats(McdwNewStatsConfig mcdwNewStatsConfig);

    ShieldStats getWeaponItemStats();

    ShieldStats getWeaponItemStats(McdwNewStatsConfig mcdwNewStatsConfig);

    boolean getIsEnabled();

    ToolMaterial getMaterial();

    String[] getRepairIngredient();

    ShieldStats getShieldStats();

    class ShieldStats {
        boolean isEnabled;
        String material;
        String[] repairIngredient;

        public ShieldStats shieldStats(boolean isEnabled, String material, String[] repairIngredient) {
            this.isEnabled = isEnabled;
            this.material = material;
            this.repairIngredient = repairIngredient;
            return this;
        }
    }
}
