package com.gtnewhorizons.wdmla.plugin.harvestcraft;

import com.gtnewhorizons.wdmla.impl.ui.ThemeHelper;
import net.minecraft.util.ResourceLocation;

import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;

public enum HarvestcraftFruitGrowthRateProvider implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor) {
        float growthValue = accessor.getMetadata() / 2.0F;
        tooltip.child(ThemeHelper.INSTANCE.growthValue(growthValue));
    }

    @Override
    public ResourceLocation getUid() {
        return HarvestcraftPlugin.path("fruit_growth_rate");
    }
}
