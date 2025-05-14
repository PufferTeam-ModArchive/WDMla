package com.gtnewhorizons.wdmla.plugin.core;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import com.gtnewhorizons.wdmla.api.identifier.WDMlaIDs;
import com.gtnewhorizons.wdmla.api.TooltipPosition;
import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.api.config.General;
import com.gtnewhorizons.wdmla.api.ui.helper.ThemeHelper;
import com.gtnewhorizons.wdmla.util.FormatUtil;

public enum BreakProgressTextProvider implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(IComponent tooltip, BlockAccessor accessor) {
        float breakProgress = Minecraft.getMinecraft().playerController.curBlockDamageMP;
        if (breakProgress == 0 || General.breakProgress.mode != General.BreakProgress.Mode.TEXT) {
            return;
        }

        tooltip.child(
                ThemeHelper.instance().value(
                        StatCollector.translateToLocal("hud.msg.wdmla.progress"),
                        FormatUtil.PERCENTAGE_STANDARD.format(breakProgress)));
    }

    @Override
    public ResourceLocation getUid() {
        return WDMlaIDs.BREAK_PROGRESS_TEXT;
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.DEFAULT_INFO + 2;
    }

    @Override
    public boolean isPriorityFixed() {
        return true;
    }

    @Override
    public boolean canToggleInGui() {
        return false;
    }
}
