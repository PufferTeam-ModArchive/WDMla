package com.gtnewhorizons.wdmla.plugin.vanilla;

import com.gtnewhorizons.wdmla.api.identifier.VanillaIDs;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import com.gtnewhorizons.wdmla.api.TooltipPosition;
import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.api.ui.helper.ThemeHelper;

import mcp.mobius.waila.overlay.DisplayUtil;

public enum FlowerPotHeaderProvider implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor) {
        String formattedName = String.format(
                StatCollector.translateToLocal("hud.msg.wdmla.flower.pot"),
                DisplayUtil.itemDisplayNameShortFormatted(accessor.getItemForm()));
        ThemeHelper.instance().overrideTooltipTitle(tooltip, formattedName);
    }

    @Override
    public ResourceLocation getUid() {
        return VanillaIDs.FLOWER_POT_HEADER;
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.HEAD;
    }
}
