package com.gtnewhorizons.wdmla.plugin.vanilla;

import com.gtnewhorizons.wdmla.api.TooltipPosition;
import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.impl.ui.ThemeHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

//"report to mod author" name is not fun to see
public enum EndPortalHeaderProvider implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor) {
        ThemeHelper.INSTANCE.overrideTooltipTitle(
                tooltip, StatCollector.translateToLocal("block.EndPortal.name"));
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.HEAD;
    }

    @Override
    public ResourceLocation getUid() {
        return VanillaIdentifiers.END_PORTAL_HEADER;
    }
}
