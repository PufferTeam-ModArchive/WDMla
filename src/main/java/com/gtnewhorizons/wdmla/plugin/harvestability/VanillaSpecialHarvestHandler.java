package com.gtnewhorizons.wdmla.plugin.harvestability;

import com.gtnewhorizons.wdmla.api.TooltipPosition;
import com.gtnewhorizons.wdmla.api.harvestability.EffectiveTool;
import com.gtnewhorizons.wdmla.api.harvestability.HarvestabilityInfo;
import com.gtnewhorizons.wdmla.api.harvestability.HarvestabilityTestPhase;
import com.gtnewhorizons.wdmla.api.provider.HarvestHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;

import java.util.Arrays;

public enum VanillaSpecialHarvestHandler implements HarvestHandler {

    INSTANCE;

    @Override
    public boolean testHarvest(HarvestabilityInfo info, HarvestabilityTestPhase phase, EntityPlayer player,
                               Block block, int meta, MovingObjectPosition position) {
        if (phase == HarvestabilityTestPhase.EFFECTIVE_TOOL_NAME) {
            if (block.equals(Blocks.dragon_egg)) {
                info.setEffectiveTool(EffectiveTool.CANNOT_HARVEST);
            }
            if (block.equals(Blocks.web)) {
                info.setEffectiveTool(VanillaHarvestToolHandler.TOOL_SWORD);
            }
        }
        else if (phase == HarvestabilityTestPhase.CURRENTLY_HARVESTABLE) {
            if (block.equals(Blocks.snow) || block.equals(Blocks.snow_layer)) {
                info.setCurrentlyHarvestable(true);
            }
        }
        return true;
    }

    @Override
    public ResourceLocation getUid() {
        return HarvestabilityIdentifiers.VANILLA_SPECIAL;
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.TAIL - 100;
    }
}
