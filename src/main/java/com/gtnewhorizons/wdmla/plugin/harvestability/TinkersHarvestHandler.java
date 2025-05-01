package com.gtnewhorizons.wdmla.plugin.harvestability;

import com.gtnewhorizons.wdmla.api.HarvestabilityInfo;
import com.gtnewhorizons.wdmla.api.HarvestabilityTestPhase;
import com.gtnewhorizons.wdmla.api.Mods;
import com.gtnewhorizons.wdmla.api.provider.InteractionHandler;
import com.gtnewhorizons.wdmla.plugin.harvestability.proxy.ProxyTinkersConstruct;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;

import static com.gtnewhorizons.wdmla.plugin.vanilla.VanillaHarvestToolHandler.TOOL_PICKAXE;

public enum TinkersHarvestHandler implements InteractionHandler {
    INSTANCE;

    @Override
    public void testHarvest(HarvestabilityInfo info, HarvestabilityTestPhase phase, EntityPlayer player, Block block, int meta, MovingObjectPosition position) {
        if(phase == HarvestabilityTestPhase.EFFECTIVE_TOOL_ICON) {
            if (Mods.TCONSTUCT.isLoaded() && info.harvestLevel != -1 && TOOL_PICKAXE.equals(info.effectiveTool)) {
                //override vanilla pickaxe icon
                info.effectiveToolIcon = ProxyTinkersConstruct.getEffectivePickaxeIcon(info.harvestLevel);
            }
        }
    }

    @Override
    public ResourceLocation getUid() {
        return HarvestabilityIdentifiers.TINKERSCONSTRUCT;
    }

    @Override
    public int getDefaultPriority() {
        return 1000;
    }
}
