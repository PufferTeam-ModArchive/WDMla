package com.gtnewhorizons.wdmla.plugin.vanilla;

import com.gtnewhorizons.wdmla.api.identifier.VanillaIDs;
import net.minecraft.block.BlockCrops;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;

import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.api.ui.helper.ThemeHelper;

public enum GrowthRateProvider implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(IComponent tooltip, BlockAccessor accessor) {
        boolean iscrop = BlockCrops.class.isInstance(accessor.getBlock()); // Done to cover all inheriting mods
        if (iscrop || accessor.getBlock() == Blocks.melon_stem || accessor.getBlock() == Blocks.pumpkin_stem) {
            float growthValue = (accessor.getMetadata() / 7.0F);
            tooltip.child(ThemeHelper.instance().growthValue(growthValue));
        } else if (accessor.getBlock() == Blocks.cocoa) {
            float growthValue = ((accessor.getMetadata() >> 2) / 2.0F);
            tooltip.child(ThemeHelper.instance().growthValue(growthValue));
        } else if (accessor.getBlock() == Blocks.nether_wart) {
            float growthValue = (accessor.getMetadata() / 3.0F);
            tooltip.child(ThemeHelper.instance().growthValue(growthValue));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return VanillaIDs.GROWTH_RATE;
    }
}
