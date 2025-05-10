package com.gtnewhorizons.wdmla.plugin.natura;

import com.gtnewhorizons.wdmla.api.identifier.CommonPluginIDs;
import net.minecraft.util.ResourceLocation;

import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.impl.ui.ThemeHelper;

import mods.natura.blocks.crops.CropBlock;

public enum NaturaGrowthRateProvider implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor) {
        if (accessor.getBlock() instanceof CropBlock cropBlock) {
            float startMeta = cropBlock.getStartGrowth(accessor.getMetadata());
            float maxMeta = cropBlock.getMaxGrowth(accessor.getMetadata()) - startMeta;
            float growthValue = ((accessor.getMetadata() - startMeta) / maxMeta);
            tooltip.child(ThemeHelper.INSTANCE.growthValue(growthValue));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return CommonPluginIDs.NATURA_GROWTH_RATE;
    }
}
