package com.gtnewhorizons.wdmla.plugin.vanilla;

import com.gtnewhorizons.wdmla.api.identifier.VanillaIDs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockRedstoneComparator;
import net.minecraft.block.BlockRedstoneRepeater;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.api.config.PluginsConfig;
import com.gtnewhorizons.wdmla.api.format.TimeFormattingPattern;
import com.gtnewhorizons.wdmla.api.ui.helper.ThemeHelper;
import com.gtnewhorizons.wdmla.impl.ui.component.HPanelComponent;

public enum RedstoneStateProvider implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor) {
        Block block = accessor.getBlock();

        if (block instanceof BlockLever && PluginsConfig.vanilla.redstoneState.showLeverState) {
            IComponent redstoneOn = (accessor.getMetadata() & 8) == 0
                    ? ThemeHelper.instance().failure(StatCollector.translateToLocal("hud.msg.wdmla.off"))
                    : ThemeHelper.instance().success(StatCollector.translateToLocal("hud.msg.wdmla.on"));
            tooltip.child(
                    new HPanelComponent()
                            .text(String.format("%s: ", StatCollector.translateToLocal("hud.msg.wdmla.state")))
                            .child(redstoneOn).tag(VanillaIDs.REDSTONE_STATE));
        } else if (block instanceof BlockRedstoneRepeater && PluginsConfig.vanilla.redstoneState.showRepeaterDelay) {
            int tick = (accessor.getMetadata() >> 2) + 1;
            tooltip.child(
                    ThemeHelper.instance()
                            .value(
                                    StatCollector.translateToLocal("hud.msg.wdmla.delay"),
                                    TimeFormattingPattern.ALWAYS_TICK.tickFormatter.format(tick))
                            .tag(VanillaIDs.REDSTONE_STATE));
        } else
            if ((block instanceof BlockRedstoneComparator) && PluginsConfig.vanilla.redstoneState.showComparatorMode) {
                String mode = ((accessor.getMetadata() >> 2) & 1) == 0
                        ? StatCollector.translateToLocal("hud.msg.wdmla.comparator")
                        : StatCollector.translateToLocal("hud.msg.wdmla.substractor");
                tooltip.child(
                        ThemeHelper.instance().value(StatCollector.translateToLocal("hud.msg.wdmla.mode"), mode)
                                .tag(VanillaIDs.REDSTONE_STATE));
            }
    }

    @Override
    public ResourceLocation getUid() {
        return VanillaIDs.REDSTONE_STATE;
    }
}
