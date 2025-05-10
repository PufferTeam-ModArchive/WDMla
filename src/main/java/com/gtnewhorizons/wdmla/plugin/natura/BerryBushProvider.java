package com.gtnewhorizons.wdmla.plugin.natura;

import com.gtnewhorizons.wdmla.api.identifier.CommonPluginIDs;
import net.minecraft.util.ResourceLocation;

import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.api.ui.ThemeHelper;

import mods.natura.blocks.crops.BerryBush;

public enum BerryBushProvider implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor) {
        if (accessor.getBlock() instanceof BerryBush) {
            float growthValue = (float) (accessor.getMetadata() / 4 + 1) / 4;
            tooltip.child(ThemeHelper.instance().growthValue(growthValue));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return CommonPluginIDs.NATURA_BERRY_BUSH;
    }
}
