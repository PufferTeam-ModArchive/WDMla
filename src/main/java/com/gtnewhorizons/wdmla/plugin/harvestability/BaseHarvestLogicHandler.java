package com.gtnewhorizons.wdmla.plugin.harvestability;

import com.gtnewhorizons.wdmla.api.HarvestabilityInfo;
import com.gtnewhorizons.wdmla.api.HarvestabilityTestPhase;
import com.gtnewhorizons.wdmla.api.TooltipPosition;
import com.gtnewhorizons.wdmla.api.provider.InteractionHandler;
import com.gtnewhorizons.wdmla.config.PluginsConfig;
import com.gtnewhorizons.wdmla.impl.ui.ThemeHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.Arrays;

public enum BaseHarvestLogicHandler implements InteractionHandler {
    INSTANCE;

    @Override
    public void testHarvest(HarvestabilityInfo info, HarvestabilityTestPhase phase, EntityPlayer player, Block block, int meta, MovingObjectPosition position) {
        if (phase == HarvestabilityTestPhase.EFFECTIVE_TOOL) {
            if (!player.isCurrentToolAdventureModeExempt(position.blockX, position.blockY, position.blockZ)
                    || isBlockUnbreakable(block, player.worldObj, position.blockX, position.blockY, position.blockZ)) {
                info.result = Arrays.asList(
                        ThemeHelper.INSTANCE.failure(PluginsConfig.harvestability.modern.notCurrentlyHarvestableString));
                info.stopFurtherTesting = true;
            }

            info.effectiveTool = block.getHarvestTool(meta);
        }
        else if (phase == HarvestabilityTestPhase.HARVEST_LEVEL) {
            info.harvestLevel = getHarvestLevel(block, meta, info.effectiveTool);
        }
    }

    @Override
    public ResourceLocation getUid() {
        return HarvestabilityIdentifiers.BASE_LOGIC;
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.DEFAULT_INFO;
    }

    public boolean isBlockUnbreakable(Block block, World world, int x, int y, int z) {
        return block.getBlockHardness(world, x, y, z) == -1.0f;
    }

    public int getHarvestLevel(Block block, int meta, String effectiveTool) {
        int harvestLevel = block.getHarvestLevel(meta);
        if (effectiveTool != null && harvestLevel < 0) harvestLevel = 0;
        return harvestLevel;
    }
}
