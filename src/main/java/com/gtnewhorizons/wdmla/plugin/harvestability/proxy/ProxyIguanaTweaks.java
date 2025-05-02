package com.gtnewhorizons.wdmla.plugin.harvestability.proxy;

import com.gtnewhorizons.wdmla.config.PluginsConfig;
import net.minecraft.item.ItemStack;

import java.lang.reflect.Method;

import static com.gtnewhorizons.wdmla.plugin.harvestability.proxy.ProxyTinkersConstruct.defaultPickaxes;

public class ProxyIguanaTweaks {

    private static Class<?> HarvestLevels = null;
    private static Method proxyGetHarvestLevelName;

    public static void init() {
        try {
            HarvestLevels = Class.forName("iguanaman.iguanatweakstconstruct.util.HarvestLevels");
            proxyGetHarvestLevelName = HarvestLevels.getDeclaredMethod("getHarvestLevelName", int.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getHarvestLevelName(int num) {
        String harvestLevelName = "<Unknown>";

        try {
            harvestLevelName = (String) proxyGetHarvestLevelName.invoke(null, num);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return harvestLevelName;
    }

    /**
     * Gets the icon of the effective Pickaxe from config.
     *
     * @see ProxyTinkersConstruct#getEffectivePickaxeIcon(int)
     */
    public static ItemStack getEffectivePickaxeIcon(int num) {
        PluginsConfig.Harvestability.IguanaTweaks iguanaConfig = PluginsConfig.harvestability.iguanaTweaks;
        return switch (num) {
            case 0 -> defaultPickaxes.get(iguanaConfig.harvestLevel0);
            case 1 -> defaultPickaxes.get(iguanaConfig.harvestLevel1);
            case 2 -> defaultPickaxes.get(iguanaConfig.harvestLevel2);
            case 3 -> defaultPickaxes.get(iguanaConfig.harvestLevel3);
            case 4 -> defaultPickaxes.get(iguanaConfig.harvestLevel4);
            case 5 -> defaultPickaxes.get(iguanaConfig.harvestLevel5);
            case 6 -> defaultPickaxes.get(iguanaConfig.harvestLevel6);
            case 7 -> defaultPickaxes.get(iguanaConfig.harvestLevel7);
            case 8 -> defaultPickaxes.get(iguanaConfig.harvestLevel8);
            case 9 -> defaultPickaxes.get(iguanaConfig.harvestLevel9);
            default -> null;
        };
    }
}
