package com.gtnewhorizons.wdmla.plugin.harvestability;

import com.gtnewhorizons.wdmla.api.HarvestabilityInfo;
import com.gtnewhorizons.wdmla.api.HarvestabilityTestPhase;
import com.gtnewhorizons.wdmla.api.provider.InteractionHandler;
import com.gtnewhorizons.wdmla.plugin.harvestability.proxy.ProxyIguanaTweaks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;

import static com.gtnewhorizons.wdmla.plugin.harvestability.VanillaHarvestToolHandler.TOOL_PICKAXE;

public enum IguanaHarvestHandler implements InteractionHandler {
    INSTANCE;

    @Override
    public void testHarvest(HarvestabilityInfo info, HarvestabilityTestPhase phase, EntityPlayer player, Block block, int meta, MovingObjectPosition position) {
        if (phase == HarvestabilityTestPhase.HARVEST_LEVEL_NAME) {
            info.harvestLevelName = ProxyIguanaTweaks.getHarvestLevelName(info.harvestLevel);
        }
        else if (phase == HarvestabilityTestPhase.EFFECTIVE_TOOL_ICON) {
            if (info.harvestLevel != -1 && TOOL_PICKAXE.equals(info.effectiveTool)) {
                //override tic pickaxe icon
                info.effectiveToolIcon = ProxyIguanaTweaks.getEffectivePickaxeIcon(info.harvestLevel);
            }
        }
    }

    @Override
    public ResourceLocation getUid() {
        return HarvestabilityIdentifiers.IGUANATWEAKS;
    }

    @Override
    public int getDefaultPriority() {
        return TinkersHarvestHandler.INSTANCE.getDefaultPriority() + 500;
    }
}
