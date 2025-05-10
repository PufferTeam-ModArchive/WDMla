package com.gtnewhorizons.wdmla.plugin.core;

import net.minecraft.util.ResourceLocation;

import com.gtnewhorizons.wdmla.api.identifier.WDMlaIDs;
import com.gtnewhorizons.wdmla.api.TooltipPosition;
import com.gtnewhorizons.wdmla.api.accessor.Accessor;
import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.accessor.EntityAccessor;
import com.gtnewhorizons.wdmla.api.provider.IComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.impl.ui.ThemeHelper;

public class HideFancyIconProvider<T extends Accessor> implements IComponentProvider<T> {

    public static ForBlock getBlock() {
        return ForBlock.INSTANCE;
    }

    public static ForEntity getEntity() {
        return ForEntity.INSTANCE;
    }

    @Override
    public void appendTooltip(ITooltip tooltip, T accessor) {
        if (accessor instanceof BlockAccessor blockAccessor) {
            ThemeHelper.INSTANCE.overrideTooltipIcon(tooltip, blockAccessor.getItemForm(), true);
        } else if (accessor instanceof EntityAccessor) {
            ThemeHelper.INSTANCE.overrideEntityTooltipIcon(tooltip, null);
        }
    }

    @Override
    public ResourceLocation getUid() {
        return WDMlaIDs.HIDE_FANCY_ICON;
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.HEAD - 100;
    }

    public static class ForBlock extends HideFancyIconProvider<BlockAccessor> {

        private static final ForBlock INSTANCE = new ForBlock();
    }

    public static class ForEntity extends HideFancyIconProvider<EntityAccessor> {

        private static final ForEntity INSTANCE = new ForEntity();

    }
}
