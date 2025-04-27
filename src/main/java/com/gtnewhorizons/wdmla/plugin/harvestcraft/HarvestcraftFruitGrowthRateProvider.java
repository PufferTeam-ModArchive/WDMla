package com.gtnewhorizons.wdmla.plugin.harvestcraft;

import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.plugin.vanilla.GrowthRateProvider;
import net.minecraft.util.ResourceLocation;

public enum HarvestcraftFruitGrowthRateProvider implements IBlockComponentProvider {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor) {
        float growthValue = (accessor.getMetadata() / 2.0F) * 100.0F;
        GrowthRateProvider.INSTANCE.appendGrowthRate(tooltip, growthValue);
    }

    @Override
    public ResourceLocation getUid() {
        return HarvestcraftPlugin.path("fruit_growth_rate");
    }
}
