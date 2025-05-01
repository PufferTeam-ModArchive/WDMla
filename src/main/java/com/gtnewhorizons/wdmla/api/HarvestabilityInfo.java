package com.gtnewhorizons.wdmla.api;

import com.gtnewhorizons.wdmla.api.ui.IComponent;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HarvestabilityInfo {
    @NotNull
    public Block block = Blocks.air;
    public int meta = 0;

    public boolean canHarvest = false;
    public String effectiveTool = null;
    public int harvestLevel = -1;
    public ItemStack effectiveToolIcon = null;

    public boolean stopFurtherTesting = false;

    public List<IComponent> result;
}
