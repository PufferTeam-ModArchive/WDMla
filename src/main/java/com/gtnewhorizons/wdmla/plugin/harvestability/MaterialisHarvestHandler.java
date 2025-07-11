package com.gtnewhorizons.wdmla.plugin.harvestability;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;

import net.pufferlab.materialis.Utils;

import com.gtnewhorizons.wdmla.api.TooltipPosition;
import com.gtnewhorizons.wdmla.api.harvestability.EffectiveTool;
import com.gtnewhorizons.wdmla.api.harvestability.HarvestabilityInfo;
import com.gtnewhorizons.wdmla.api.harvestability.HarvestabilityTestPhase;
import com.gtnewhorizons.wdmla.api.provider.HarvestHandler;

public enum MaterialisHarvestHandler implements HarvestHandler {

    INSTANCE;

    @Override
    public boolean testHarvest(HarvestabilityInfo info, HarvestabilityTestPhase phase, EntityPlayer player, Block block,
            int meta, MovingObjectPosition position) {
        if (phase == HarvestabilityTestPhase.CURRENTLY_HARVESTABLE) {
            if(Utils.containsOreDict(block, "logWood")) {
                info.setCurrentlyHarvestable(false);
                ItemStack heldItem = player.inventory.getCurrentItem();
                if(Utils.containsOreDict(heldItem, "toolAxe")) {
                    info.setCurrentlyHarvestable(true);
                }
            }
        }
        return true;
    }

    @Override
    public ResourceLocation getUid() {
        return HarvestabilityIdentifiers.MATERIALIS;
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.TAIL - 100;
    }
}
