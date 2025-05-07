package com.gtnewhorizons.wdmla.plugin.natura;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.impl.ui.ThemeHelper;

import mods.natura.common.NContent;

public enum NaturaLeavesProvider implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor) {
        if ((accessor.getBlock() == NContent.floraLeaves || accessor.getBlock() == NContent.floraLeavesNoColor
                || accessor.getBlock() == NContent.rareLeaves
                || accessor.getBlock() == NContent.darkLeaves) && accessor.getMetadata() > 3) {
            ThemeHelper.INSTANCE
                    .overrideTooltipTitle(tooltip, new ItemStack(accessor.getBlock(), 1, accessor.getMetadata() % 4));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return NaturaPlugin.path("leaves");
    }
}
