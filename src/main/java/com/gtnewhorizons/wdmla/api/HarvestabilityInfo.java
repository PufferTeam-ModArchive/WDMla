package com.gtnewhorizons.wdmla.api;

import com.gtnewhorizons.wdmla.api.ui.IComponent;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HarvestabilityInfo {
    @NotNull
    public Block block = Blocks.air;
    public int meta = 0;

    public boolean canHarvest = false;
    public String effectiveTool = null;
    public int harvestLevel = -1;
    /**
     * the primary harvest tool (pickaxe for hopper, shovel for dirt...)
     */
    public ItemStack effectiveToolIcon = null;
    /**
     * the secondary harvest tool (wrench for hopper, shears for leaves)
     */
    public List<ItemStack> secondaryToolsIcon = new ArrayList<>();

    public boolean stopFurtherTesting = false;

    public List<IComponent> result;
}
