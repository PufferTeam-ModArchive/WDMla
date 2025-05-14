package com.gtnewhorizons.wdmla.example;

import static mcp.mobius.waila.api.SpecialChars.*;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import com.gtnewhorizons.wdmla.api.identifier.WDMlaIDs;
import com.gtnewhorizons.wdmla.api.TooltipPosition;
import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.api.ui.helper.ThemeHelper;
import com.gtnewhorizons.wdmla.impl.ui.component.TextComponent;

public enum ExampleHeaderProvider implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public ResourceLocation getUid() {
        return WDMlaIDs.EXAMPLE_HEAD;
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.HEAD;
    }

    @Override
    public void appendTooltip(IComponent tooltip, BlockAccessor accessor) {
        ThemeHelper.instance().overrideTooltipIcon(tooltip, new ItemStack(Blocks.lit_furnace), true);
        ThemeHelper.instance().overrideTooltipTitle(tooltip, "Furnace");
        tooltip.replaceChildWithTag(
                WDMlaIDs.MOD_NAME,
                new TextComponent(BLUE + ITALIC + "WDMla").tag(WDMlaIDs.MOD_NAME));
    }
}
