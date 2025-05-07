package com.gtnewhorizons.wdmla.plugin.storagedrawers;

import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import com.gtnewhorizons.wdmla.api.ui.ComponentAlignment;
import com.gtnewhorizons.wdmla.api.ui.HighlightTracker;
import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.config.General;
import com.gtnewhorizons.wdmla.impl.ui.component.HPanelComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.RectComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.TextComponent;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Size;
import com.gtnewhorizons.wdmla.impl.ui.style.PanelStyle;
import com.gtnewhorizons.wdmla.impl.ui.style.RectStyle;
import com.gtnewhorizons.wdmla.impl.ui.style.TextStyle;
import com.gtnewhorizons.wdmla.util.Color;
import com.gtnewhorizons.wdmla.util.FormatUtil;
import com.jaquadro.minecraft.storagedrawers.api.storage.IDrawer;

import mcp.mobius.waila.overlay.DisplayUtil;

// Tracks "Grass [1x64 + 22]"
public class DrawerInfoHighlighter {

    private final HighlightTracker<Integer> stackCountTracker;
    private final HighlightTracker<Integer> remainderTracker;
    private final HighlightTracker.ItemStack stackTracker;

    public DrawerInfoHighlighter(IDrawer drawer) {
        ItemStack stack = drawer.getStoredItemPrototype();
        if (stack == null || stack.getItem() == null) {
            stackCountTracker = new HighlightTracker<>(0);
            remainderTracker = new HighlightTracker<>(0);
        } else {
            int stackCount = drawer.getStoredItemCount() / drawer.getStoredItemStackSize();
            int remainder = drawer.getStoredItemCount() - (stackCount * drawer.getStoredItemStackSize());
            stackCountTracker = new HighlightTracker<>(stackCount);
            remainderTracker = new HighlightTracker<>(remainder);
        }
        stackTracker = new HighlightTracker.ItemStack(stack);
    }

    public IComponent update(IDrawer drawer) {
        ItemStack stack = drawer.getStoredItemPrototype();
        if (stack == null || stack.getItem() == null) {
            return updateEmpty();
        }

        int stackCount = drawer.getStoredItemCount() / drawer.getStoredItemStackSize();
        int remainder = drawer.getStoredItemCount() - (stackCount * drawer.getStoredItemStackSize());

        boolean highlightStacks = stackTracker.update(stack);
        boolean highlightStackCount = stackCountTracker.update(stackCount) || highlightStacks;
        boolean highlightRemainder = remainderTracker.update(remainder) || highlightStacks;

        String stackCountStr = FormatUtil.STANDARD.format(stackCount);
        String remainderStr = FormatUtil.STANDARD.format(remainder);
        String displayName = DisplayUtil
                .stripSymbols(DisplayUtil.itemDisplayNameShortFormatted(drawer.getStoredItemPrototype()));

        float stackInterpolation = stackTracker.getInterpolation();

        IComponent displayNameComponent = getHighlightComponent(displayName, highlightStacks, stackInterpolation);
        IComponent stackCountComponent = getHighlightComponent(
                stackCountStr,
                highlightStackCount,
                stackCountTracker.getInterpolation());
        IComponent remainderComponent = getHighlightComponent(
                remainderStr,
                highlightRemainder,
                remainderTracker.getInterpolation());

        ITooltip itemLine = new HPanelComponent();
        itemLine.child(displayNameComponent);
        itemLine.child(getHighlightComponent(" [", highlightStacks, stackInterpolation));

        if (stackCount > 0 && remainder > 0) {
            itemLine.child(stackCountComponent);
            itemLine.child(
                    getHighlightComponent(
                            "x" + drawer.getStoredItemStackSize() + " + ",
                            highlightStacks,
                            stackInterpolation));
            itemLine.child(remainderComponent);
            itemLine.child(getHighlightComponent("]", highlightStacks, stackInterpolation));
        } else if (stackCount > 0) {
            itemLine.child(stackCountComponent);
            itemLine.child(
                    getHighlightComponent(
                            "x" + drawer.getStoredItemStackSize() + "]",
                            highlightStacks,
                            stackInterpolation));
        } else {
            itemLine.child(remainderComponent);
            itemLine.child(getHighlightComponent("]", highlightStacks, stackInterpolation));
        }
        return itemLine;
    }

    private IComponent updateEmpty() {
        boolean highlightStacks = stackTracker.update(null);
        stackCountTracker.update(0);
        remainderTracker.update(0);
        float stackInterpolation = stackTracker.getInterpolation();
        return new HPanelComponent().style(new PanelStyle().alignment(ComponentAlignment.CENTER)).child(
                new RectComponent().style(new RectStyle().backgroundColor(getInterpolationColor(stackInterpolation)))
                        .size(new Size(25, 1)))
                .child(
                        ((TextComponent) getHighlightComponent(
                                StatCollector.translateToLocal("hud.msg.wdmla.empty"),
                                highlightStacks,
                                stackInterpolation)).scale(0.6f))
                .child(
                        new RectComponent()
                                .style(new RectStyle().backgroundColor(getInterpolationColor(stackInterpolation)))
                                .size(new Size(25, 1)));
    }

    private IComponent getHighlightComponent(String text, boolean doHighlight, float interpolation) {
        return new TextComponent(text).style(
                doHighlight ? new TextStyle().color(getInterpolationColor(interpolation))
                        : new TextStyle().color(General.currentTheme.get().textColors._default));
    }

    private int getInterpolationColor(float interpolation) {
        return Color.setInterporation(
                General.currentTheme.get().textColors.info,
                General.currentTheme.get().textColors._default,
                interpolation > 0.75f ? (interpolation - 0.75f) * 4 : 0f);
    }
}
