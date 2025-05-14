package com.gtnewhorizons.wdmla.plugin.vanilla;

import com.gtnewhorizons.wdmla.api.identifier.VanillaIDs;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import com.gtnewhorizons.wdmla.api.TooltipPosition;
import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.api.ui.helper.ThemeHelper;

// "report to mod author" name is not fun to see
public enum EndPortalHeaderProvider implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(IComponent tooltip, BlockAccessor accessor) {
        ThemeHelper.instance().overrideTooltipTitle(tooltip, StatCollector.translateToLocal("block.EndPortal.name"));
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.HEAD;
    }

    @Override
    public ResourceLocation getUid() {
        return VanillaIDs.END_PORTAL_HEADER;
    }
}
