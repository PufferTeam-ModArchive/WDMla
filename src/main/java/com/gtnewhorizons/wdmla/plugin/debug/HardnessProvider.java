package com.gtnewhorizons.wdmla.plugin.debug;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import com.gtnewhorizons.wdmla.api.identifier.WDMlaIDs;
import com.gtnewhorizons.wdmla.api.TooltipPosition;
import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.api.ui.helper.ThemeHelper;
import com.gtnewhorizons.wdmla.util.FormatUtil;

public enum HardnessProvider implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(IComponent tooltip, BlockAccessor accessor) {
        float hardness = accessor.getBlock().getBlockHardness(
                accessor.getWorld(),
                accessor.getHitResult().blockX,
                accessor.getHitResult().blockY,
                accessor.getHitResult().blockZ);
        tooltip.child(
                ThemeHelper.instance().value(
                        StatCollector.translateToLocal("hud.msg.wdmla.hardness"),
                        FormatUtil.STANDARD.format(hardness)));
    }

    @Override
    public ResourceLocation getUid() {
        return WDMlaIDs.HARDNESS;
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.TAIL;
    }

    @Override
    public boolean enabledByDefault() {
        return false;
    }
}
