package com.gtnewhorizons.wdmla.api.harvestability;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import com.gtnewhorizons.wdmla.impl.harvestability.EffectiveToolImpl;

public interface EffectiveTool {

    EffectiveTool NO_TOOL = EffectiveTool.of(null, null);
    EffectiveTool CANNOT_HARVEST = EffectiveTool.of("unbreakable", null);

    static EffectiveTool of(String toolType, List<ItemStack> iconList) {
        return new EffectiveToolImpl(toolType, iconList);
    }

    boolean isValid();

    HarvestLevel getHarvestLevel(Block block, int meta);

    boolean isToolInstance(ItemStack tool);

    boolean isSameTool(EffectiveTool anotherTool);

    boolean hasIconRegistered();

    ItemStack getIcon(HarvestLevel harvestLevel);

    String getLocalizedName();
}
