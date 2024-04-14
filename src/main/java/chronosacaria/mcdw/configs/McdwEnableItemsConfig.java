package chronosacaria.mcdw.configs;

import chronosacaria.mcdw.enums.ItemsID;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.LinkedHashMap;

@Config(name = "mcdw_items_registry")
public class McdwEnableItemsConfig implements ConfigData {
    public final LinkedHashMap<ItemsID, Boolean> ITEMS_ENABLED = new LinkedHashMap<>();

    public McdwEnableItemsConfig() {
        for (ItemsID itemsID : ItemsID.values())
            ITEMS_ENABLED.put(itemsID, true);
    }
}
