package com.gtnewhorizons.wdmla.plugin.core;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import com.gtnewhorizons.wdmla.api.identifier.WDMlaIDs;
import com.gtnewhorizons.wdmla.api.TooltipPosition;
import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.IComponent;

public enum BlockFaceProvider implements IBlockComponentProvider {

    INSTANCE;

    private static final String[] SIDES = { "down", "up", "east", "west", "north", "south" };

    @Override
    public void appendTooltip(IComponent tooltip, BlockAccessor accessor) {
        int side = accessor.getHitResult().sideHit;
        IComponent itemNameRow = tooltip.getChildWithTag(WDMlaIDs.TARGET_NAME_ROW);
        if (side != -1 && itemNameRow != null) {
            itemNameRow.text(
                    String.format(
                            StatCollector.translateToLocal("hud.msg.wdmla.side.decorator"),
                            StatCollector.translateToLocal("hud.msg.wdmla.side." + SIDES[side])));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return WDMlaIDs.BLOCK_FACE;
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.DEFAULT_INFO + 1;
    }

    @Override
    public boolean isPriorityFixed() {
        return true;
    }

    @Override
    public boolean enabledByDefault() {
        return false;
    }
}
