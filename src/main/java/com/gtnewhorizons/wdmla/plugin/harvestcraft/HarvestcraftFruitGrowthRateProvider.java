package com.gtnewhorizons.wdmla.plugin.harvestcraft;

import com.gtnewhorizons.wdmla.api.identifier.CommonPluginIDs;
import net.minecraft.util.ResourceLocation;

import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.api.ui.helper.ThemeHelper;

public enum HarvestcraftFruitGrowthRateProvider implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(IComponent tooltip, BlockAccessor accessor) {
        float growthValue = accessor.getMetadata() / 2.0F;
        tooltip.child(ThemeHelper.instance().growthValue(growthValue));
    }

    @Override
    public ResourceLocation getUid() {
        return CommonPluginIDs.HC_GROWTH_RATE;
    }
}
