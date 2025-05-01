package com.gtnewhorizons.wdmla.plugin.harvestability;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.gtnewhorizons.wdmla.api.HarvestabilityInfo;
import com.gtnewhorizons.wdmla.api.HarvestabilityTestPhase;
import com.gtnewhorizons.wdmla.api.provider.InteractionHandler;
import com.gtnewhorizons.wdmla.impl.WDMlaClientRegistration;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.gtnewhorizons.wdmla.api.Identifiers;
import com.gtnewhorizons.wdmla.api.TooltipPosition;
import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.config.General;
import com.gtnewhorizons.wdmla.config.PluginsConfig;
import com.gtnewhorizons.wdmla.impl.ui.ThemeHelper;
import com.gtnewhorizons.wdmla.impl.ui.component.HPanelComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.ItemComponent;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Padding;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Size;
import com.gtnewhorizons.wdmla.plugin.harvestability.helpers.StringHelper;
import com.gtnewhorizons.wdmla.plugin.harvestability.proxy.ProxyCreativeBlocks;

public enum HarvestToolProvider implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public ResourceLocation getUid() {
        return HarvestabilityIdentifiers.HARVESTABILITY;
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.HARVESTABILITY_OVERRIDE;
    }

    @Override
    public boolean isPriorityFixed() {
        return true;
    }

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor) {
        if (General.forceLegacy) {
            return;
        }

        if (ProxyCreativeBlocks.isCreativeBlock(accessor.getBlock(), accessor.getMetadata())) {
            return;
        }

        List<InteractionHandler> handlers = WDMlaClientRegistration.instance().getInterationHandlers(
                accessor.getBlock(),
                interactionHandler -> true);

        Block effectiveBlock = handlers
                .stream().map(
                        handler -> handler.getEffectiveBlock(
                                accessor.getBlock(), accessor.getItemForm(), accessor.getMetadata()))
                .filter(Objects::nonNull).findFirst().orElse(accessor.getBlock());
        int effectiveMeta = handlers
                .stream().map(
                        handler -> handler.getEffectiveMeta(
                                accessor.getBlock(), accessor.getItemForm(), accessor.getMetadata()))
                .filter(Objects::nonNull).findFirst().orElse(accessor.getMetadata());

        List<IComponent> harvestableDisplay = getHarvestability(
                accessor.getPlayer(),
                effectiveBlock,
                effectiveMeta,
                accessor.getHitResult(),
                handlers);

        updateTooltip(tooltip, harvestableDisplay);
    }

    private void updateTooltip(ITooltip tooltip, List<IComponent> harvestableDisplay) {
        IComponent itemNameRow = tooltip.getChildWithTag(Identifiers.ITEM_NAME_ROW);
        if (!(itemNameRow instanceof ITooltip)) {
            return;
        }

        ((ITooltip) itemNameRow).child(harvestableDisplay.get(0));
        if (harvestableDisplay.size() > 1 && harvestableDisplay.get(1) != null
                && PluginsConfig.harvestability.modern.modernHarvestLevelNum) {
            tooltip.child(harvestableDisplay.get(1));
        }
    }

    /**
     * @return element1: harvest tool icon to append after item name
     *         <p>
     *         element2: harvestability String if the harvest level is greater than 0
     */
    public List<IComponent> getHarvestability(EntityPlayer player, Block block, int meta,
            MovingObjectPosition position, List<InteractionHandler> handlers) {
        // needed to stop array index out of bounds exceptions on mob spawners
        // block.getHarvestLevel/getHarvestTool are only 16 elements big
        if (meta >= 16) meta = 0;

        HarvestabilityInfo info = new HarvestabilityInfo();
        if (!fireHarvestTest(HarvestabilityTestPhase.EFFECTIVE_TOOL,
                player, block, meta, position, handlers, info)) {
            return info.result;
        }

        if (!fireHarvestTest(HarvestabilityTestPhase.HARVEST_LEVEL,
                player, block, meta, position, handlers, info)) {
            return info.result;
        }

        if (!fireHarvestTest(HarvestabilityTestPhase.EFFECTIVE_TOOL_ICON,
                player, block, meta, position, handlers, info)) {
            return info.result;
        }

        if (info.effectiveToolIcon == null) {
            info.effectiveToolIcon = new ItemStack(Blocks.iron_bars);
        }

        if (!fireHarvestTest(HarvestabilityTestPhase.ADDITIONAL_TOOLS_ICON,
                player, block, meta, position, handlers, info)) {
            return info.result;
        }

        if (!fireHarvestTest(HarvestabilityTestPhase.CURRENTLY_HARVESTABLE,
                player, block, meta, position, handlers, info)) {
            return info.result;
        }

        // remove durability bar from tool icon
        ITooltip effectiveToolIconComponent = new ItemComponent(info.effectiveToolIcon).doDrawOverlay(false)
                .size(new Size(10, 10));

        ITooltip harvestabilityIcon = assembleHarvestabilityIcon(
                effectiveToolIconComponent,
                info.canHarvest);
        IComponent harvestLevelText = assembleHarvestLevelText(info.harvestLevel, info.canHarvest);
        return Arrays.asList(harvestabilityIcon, harvestLevelText);
    }

    private static boolean fireHarvestTest(HarvestabilityTestPhase phase, EntityPlayer player, Block block, int meta,
                                           MovingObjectPosition position, List<InteractionHandler> handlers, HarvestabilityInfo info) {
        for (InteractionHandler handler : handlers) {
            if(info.stopFurtherTesting) {
                break;
            }
            handler.testHarvest(info, phase, player, block, meta, position);
        }

        return !info.stopFurtherTesting;
    }

    private static @NotNull ITooltip assembleHarvestabilityIcon(ITooltip effectiveToolIconComponent,
            boolean isCurrentlyHarvestable) {
        ITooltip harvestabilityComponent = new HPanelComponent().tag(HarvestabilityIdentifiers.HARVESTABILITY_ICON);
        // TODO: resize CHECK text
        IComponent currentlyHarvestableIcon = (isCurrentlyHarvestable
                ? ThemeHelper.INSTANCE.success(PluginsConfig.harvestability.modern.currentlyHarvestableString)
                : ThemeHelper.INSTANCE.failure(PluginsConfig.harvestability.modern.notCurrentlyHarvestableString));

        if (PluginsConfig.harvestability.modern.modernCurrentlyHarvestableIcon) {
            if (effectiveToolIconComponent != null && PluginsConfig.harvestability.modern.modernEffectiveToolIcon) {
                effectiveToolIconComponent.child(
                        new HPanelComponent().padding(new Padding().left(5).top(6)).child(currentlyHarvestableIcon));
                harvestabilityComponent.child(effectiveToolIconComponent);
            } else {
                harvestabilityComponent.child(currentlyHarvestableIcon);
            }
        }
        return harvestabilityComponent;
    }

    private static @Nullable IComponent assembleHarvestLevelText(int harvestLevel, boolean isCurrentlyHarvestable) {
        IComponent harvestLevelText = null;
        if (harvestLevel >= 1) {
            String harvestLevelString = StringHelper.stripFormatting(StringHelper.getHarvestLevelName(harvestLevel));
            harvestLevelText = new HPanelComponent().tag(HarvestabilityIdentifiers.HARVESTABILITY_TEXT)
                    .text(String.format("%s: ", StatCollector.translateToLocal("hud.msg.wdmla.harvestlevel"))).child(
                            isCurrentlyHarvestable ? ThemeHelper.INSTANCE.success(harvestLevelString)
                                    : ThemeHelper.INSTANCE.failure(harvestLevelString));
        }
        return harvestLevelText;
    }
}
