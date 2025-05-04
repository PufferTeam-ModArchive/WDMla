package com.gtnewhorizons.wdmla.plugin.natura;

import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.impl.ui.ThemeHelper;
import mods.natura.blocks.crops.BerryBush;
import net.minecraft.util.ResourceLocation;

public enum BerryBushProvider implements IBlockComponentProvider {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor) {
        if (accessor.getBlock() instanceof BerryBush) {
            float growthValue = (float) (accessor.getMetadata() / 4 + 1) / 4;
            tooltip.child(ThemeHelper.INSTANCE.growthValue(growthValue));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return NaturaPlugin.path("berry_bush");
    }
}
