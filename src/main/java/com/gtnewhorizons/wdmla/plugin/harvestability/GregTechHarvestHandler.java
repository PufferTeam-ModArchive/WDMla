package com.gtnewhorizons.wdmla.plugin.harvestability;

import com.gtnewhorizons.wdmla.api.HarvestabilityInfo;
import com.gtnewhorizons.wdmla.api.HarvestabilityTestPhase;
import com.gtnewhorizons.wdmla.api.Mods;
import com.gtnewhorizons.wdmla.api.provider.InteractionHandler;
import com.gtnewhorizons.wdmla.plugin.harvestability.proxy.ProxyGregTech;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;

public enum GregTechHarvestHandler implements InteractionHandler {
    INSTANCE;

    @Override
    public void testHarvest(HarvestabilityInfo info, HarvestabilityTestPhase phase,
                            EntityPlayer player, Block block, int meta, MovingObjectPosition position) {
        if(phase == HarvestabilityTestPhase.EFFECTIVE_TOOL_ICON) {
            if (info.harvestLevel != -1 && info.effectiveTool != null && Mods.GREGTECH.isLoaded()) {
                info.effectiveToolIcon = ProxyGregTech.getEffectiveGregToolIcon(info.effectiveTool, info.harvestLevel);
            }
        }
    }

    @Override
    public ResourceLocation getUid() {
        return HarvestabilityIdentifiers.GREGTECH;
    }

    @Override
    public Block getEffectiveBlock(Block block, ItemStack itemForm, int meta) {
        return isDisguised(block, itemForm, meta) ? Block.getBlockFromItem(itemForm.getItem()) : null;
    }

    @Override
    public Integer getEffectiveMeta(Block block, ItemStack itemForm, int meta) {
        return isDisguised(block, itemForm, meta) ? itemForm.getItemDamage() : null;
    }

    @Override
    public int getDefaultPriority() {
        return 2000;
    }

    public boolean isDisguised(Block block, ItemStack itemForm, int meta) {
        return itemForm.getItem() instanceof ItemBlock && !ProxyGregTech.isOreBlock(block, meta)
                && !ProxyGregTech.isCasing(block)
                && !ProxyGregTech.isMachine(block);
    }
}
