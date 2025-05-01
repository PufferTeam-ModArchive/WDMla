package com.gtnewhorizons.wdmla.plugin.harvestability.proxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.gtnewhorizons.wdmla.plugin.harvestability.BaseHarvestLogicHandler;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeHooks;

import com.gtnewhorizons.wdmla.api.Mods;
import com.gtnewhorizons.wdmla.config.PluginsConfig;

import cpw.mods.fml.common.registry.GameRegistry;
import tconstruct.library.TConstructRegistry;
import tconstruct.library.crafting.ToolBuilder;
import tconstruct.library.tools.DualHarvestTool;
import tconstruct.library.tools.HarvestTool;
import tconstruct.library.util.HarvestLevels;
import tconstruct.tools.TinkerTools;

public class ProxyTinkersConstruct {

    private static Method getHarvestType = null;
    private static Method getSecondHarvestType = null;
    public static final List<ItemStack> defaultPickaxes = new ArrayList<>();

    public static void init() {
        try {
            getHarvestType = HarvestTool.class.getDeclaredMethod("getHarvestType");
            getSecondHarvestType = DualHarvestTool.class.getDeclaredMethod("getSecondHarvestType");
            getHarvestType.setAccessible(true);
            getSecondHarvestType.setAccessible(true);
            for (int materialID : TConstructRegistry.toolMaterials.keySet()) {
                defaultPickaxes.add(buildDefaultPickaxe(materialID));
            }
        } catch (Exception ignore) {}
    }

    public static boolean hasToolTag(ItemStack itemStack) {
        return itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("InfiTool");
    }

    public static NBTTagCompound getToolTag(ItemStack tool) {
        NBTTagCompound tag = null;
        if (tool.hasTagCompound()) tag = tool.getTagCompound().getCompoundTag("InfiTool");
        return tag;
    }

    public static int getPrimaryHarvestLevel(NBTTagCompound toolTag) {
        return toolTag.getInteger("HarvestLevel");
    }

    public static int getSecondaryHarvestLevel(NBTTagCompound toolTag) {
        return toolTag.getInteger("HarvestLevel2");
    }

    public static boolean isToolEffectiveAgainst(ItemStack tool, Block block, int metadata, String effectiveToolClass) {
        if (Mods.TCONSTUCT.isLoaded() && tool.getItem() instanceof HarvestTool harvestTool) {
            List<String> harvestTypes = new ArrayList<String>();
            try {
                harvestTypes.add((String) getHarvestType.invoke(harvestTool));
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (harvestTool instanceof DualHarvestTool) {
                try {
                    harvestTypes.add((String) getSecondHarvestType.invoke(harvestTool));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return harvestTypes.contains(effectiveToolClass);
        }
        return BaseHarvestLogicHandler.isToolEffectiveAgainst(tool, block, metadata, effectiveToolClass);
    }

    public static boolean canToolHarvestLevel(ItemStack tool, Block block, int metadata, int harvestLevel) {
        boolean canTinkersToolHarvestBlock = false;

        NBTTagCompound toolTag = getToolTag(tool);
        if (toolTag != null) {
            int toolHarvestLevel = Math.max(getPrimaryHarvestLevel(toolTag), getSecondaryHarvestLevel(toolTag));
            canTinkersToolHarvestBlock = toolHarvestLevel >= harvestLevel;
        }

        return canTinkersToolHarvestBlock || ForgeHooks.canToolHarvestBlock(block, metadata, tool);
    }

    public static String getTicHarvestLevelName(int num) {
        return HarvestLevels.getHarvestLevelName(num);
    }

    /**
     * Gets the icon of the effective Pickaxe from config. Important note: the default config value is tuned for Iguana
     * Tweaks with vanilla mode disabled. You have to edit the config in order to play with vanilla mode or TiC alone
     * See: <a href=
     * "https://github.com/GTNewHorizons/TinkersConstruct/blob/master/src/main/java/tconstruct/tools/TinkerTools.java#L1771">...</a>
     */
    public static ItemStack getEffectivePickaxeIcon(int num) {
        PluginsConfig.Harvestability.Modern.TinkersConstruct tiCConfig = PluginsConfig.harvestability.modern.tinkersConstruct;
        return switch (num) {
            case 0 -> defaultPickaxes.get(tiCConfig.harvestLevel0);
            case 1 -> defaultPickaxes.get(tiCConfig.harvestLevel1);
            case 2 -> defaultPickaxes.get(tiCConfig.harvestLevel2);
            case 3 -> defaultPickaxes.get(tiCConfig.harvestLevel3);
            case 4 -> defaultPickaxes.get(tiCConfig.harvestLevel4);
            case 5 -> defaultPickaxes.get(tiCConfig.harvestLevel5);
            case 6 -> defaultPickaxes.get(tiCConfig.harvestLevel6);
            case 7 -> defaultPickaxes.get(tiCConfig.harvestLevel7);
            case 8 -> defaultPickaxes.get(tiCConfig.harvestLevel8);
            case 9 -> defaultPickaxes.get(tiCConfig.harvestLevel9);
            default -> null;
        };
    }

    /**
     * build TCon Pickaxe for WDMla harvest tool display
     * @param materialID id of pickaxe head and binding material
     * @return a pickaxe with wood tool rod
     */
    public static ItemStack buildDefaultPickaxe(int materialID) {
        ItemStack tool = ToolBuilder.instance.buildTool(
                new ItemStack(TinkerTools.pickaxeHead, 1, materialID),
                new ItemStack(TinkerTools.toolRod, 1, 0),
                new ItemStack(TinkerTools.binding, 1, materialID),
                null,
                "");

        if (tool != null) {
            tool.getTagCompound().getCompoundTag("InfiTool").setBoolean("Built", true);
        }

        return tool;
    }
}
