package com.gtnewhorizons.wdmla.example;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;

import com.gtnewhorizons.wdmla.api.identifier.WDMlaIDs;
import com.gtnewhorizons.wdmla.api.TooltipPosition;
import com.gtnewhorizons.wdmla.api.harvestability.EffectiveTool;
import com.gtnewhorizons.wdmla.api.harvestability.HarvestLevel;
import com.gtnewhorizons.wdmla.api.harvestability.HarvestabilityInfo;
import com.gtnewhorizons.wdmla.api.harvestability.HarvestabilityTestPhase;
import com.gtnewhorizons.wdmla.api.provider.HarvestHandler;

public enum ExampleHarvestHandler implements HarvestHandler {

    INSTANCE;

    public static final EffectiveTool EXAMPLE_TOOL = EffectiveTool.of("egg", Arrays.asList(new ItemStack(Items.egg)));

    @Override
    public boolean testHarvest(HarvestabilityInfo info, HarvestabilityTestPhase phase, EntityPlayer player, Block block,
            int meta, MovingObjectPosition position) {
        if (phase == HarvestabilityTestPhase.EFFECTIVE_TOOL_NAME) {
            info.setEffectiveTool(EXAMPLE_TOOL);
        } else if (phase == HarvestabilityTestPhase.HARVEST_LEVEL) {
            info.setHarvestLevel(HarvestLevel.fromNameRule(2, level -> "Very Hard"));
        } else if (phase == HarvestabilityTestPhase.CURRENTLY_HARVESTABLE) {
            info.setCurrentlyHarvestable(true);
        } else if (phase == HarvestabilityTestPhase.IS_HELD_TOOL_EFFECTIVE) {
            if (player.getHeldItem().getItem() == Items.egg) {
                info.setHeldToolEffective(true);
            }
        }
        return true;
    }

    @Override
    public ResourceLocation getUid() {
        return WDMlaIDs.EXAMPLE_HARVEST;
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.TAIL;
    }
}
