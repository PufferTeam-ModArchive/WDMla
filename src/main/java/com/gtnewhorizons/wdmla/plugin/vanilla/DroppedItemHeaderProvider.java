package com.gtnewhorizons.wdmla.plugin.vanilla;

import com.gtnewhorizons.wdmla.api.identifier.VanillaIDs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import com.gtnewhorizons.wdmla.api.TooltipPosition;
import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.api.ui.helper.ThemeHelper;

public enum DroppedItemHeaderProvider implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(IComponent tooltip, BlockAccessor accessor) {
        ItemStack newStack = new ItemStack(
                accessor.getBlock(),
                1,
                accessor.getBlock().damageDropped(accessor.getMetadata()));
        ThemeHelper.instance().overrideTooltipIcon(tooltip, newStack, false);
        ThemeHelper.instance().overrideTooltipTitle(tooltip, newStack);
    }

    @Override
    public ResourceLocation getUid() {
        return VanillaIDs.DROPPED_ITEM_HEADER;
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.HEAD;
    }
}
