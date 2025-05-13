package com.gtnewhorizons.wdmla.plugin.vanilla;

import com.gtnewhorizons.wdmla.api.identifier.VanillaIDs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import com.gtnewhorizons.wdmla.api.TooltipPosition;
import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.api.ui.helper.ThemeHelper;

// includes crops and stems
public enum GrowableHeaderProvider implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor) {
        if (accessor.getBlock().equals(Blocks.wheat)) {
            ThemeHelper.instance()
                    .overrideTooltipTitle(tooltip, StatCollector.translateToLocal("hud.msg.wdmla.wheatcrop"));
        } else if (accessor.getBlock().equals(Blocks.carrots)) {
            ThemeHelper.instance().overrideTooltipIcon(tooltip, new ItemStack(Items.carrot), false);
        } else if (accessor.getBlock().equals(Blocks.potatoes)) {
            ThemeHelper.instance().overrideTooltipIcon(tooltip, new ItemStack(Items.potato), false);
        } else if (accessor.getBlock().equals(Blocks.pumpkin_stem)) {
            ThemeHelper.instance()
                    .overrideTooltipTitle(tooltip, StatCollector.translateToLocal("hud.msg.wdmla.pumpkinstem"));
        } else if (accessor.getBlock().equals(Blocks.melon_stem)) {
            ThemeHelper.instance()
                    .overrideTooltipTitle(tooltip, StatCollector.translateToLocal("hud.msg.wdmla.melonstem"));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return VanillaIDs.GROWABLE_HEADER;
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.HEAD;
    }
}
