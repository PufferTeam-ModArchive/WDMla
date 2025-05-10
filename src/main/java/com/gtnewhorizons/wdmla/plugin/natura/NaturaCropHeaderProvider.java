package com.gtnewhorizons.wdmla.plugin.natura;

import com.gtnewhorizons.wdmla.api.identifier.CommonPluginIDs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.impl.ui.ThemeHelper;

import mods.natura.blocks.crops.CropBlock;

public enum NaturaCropHeaderProvider implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor) {
        if (accessor.getBlock() instanceof CropBlock cropBlock) {
            ThemeHelper.INSTANCE.overrideTooltipHeader(
                    tooltip,
                    new ItemStack(
                            cropBlock.getCropItem(accessor.getMetadata()),
                            1,
                            cropBlock.damageDropped(accessor.getMetadata())));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return CommonPluginIDs.NATURA_CROP_HEADER;
    }
}
