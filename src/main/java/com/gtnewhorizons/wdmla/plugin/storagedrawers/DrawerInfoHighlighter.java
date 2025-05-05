package com.gtnewhorizons.wdmla.plugin.storagedrawers;

import com.gtnewhorizons.wdmla.api.Theme;
import com.gtnewhorizons.wdmla.api.ui.ComponentAlignment;
import com.gtnewhorizons.wdmla.api.ui.HighlightTracker;
import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.api.ui.MessageType;
import com.gtnewhorizons.wdmla.config.General;
import com.gtnewhorizons.wdmla.impl.ui.ThemeHelper;
import com.gtnewhorizons.wdmla.impl.ui.component.HPanelComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.RectComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.TextComponent;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Size;
import com.gtnewhorizons.wdmla.impl.ui.style.PanelStyle;
import com.gtnewhorizons.wdmla.impl.ui.style.RectStyle;
import com.gtnewhorizons.wdmla.impl.ui.style.TextStyle;
import com.jaquadro.minecraft.storagedrawers.api.storage.IDrawer;
import mcp.mobius.waila.overlay.DisplayUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

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
        }
        else {
            int stackCount = drawer.getStoredItemCount() / drawer.getStoredItemStackSize();
            int remainder = drawer.getStoredItemCount() - (stackCount * drawer.getStoredItemStackSize());
            stackCountTracker = new HighlightTracker<>(stackCount);
            remainderTracker = new HighlightTracker<>(remainder);
        }
        stackTracker = new HighlightTracker.ItemStack(stack);
    }

    //TODO:item amount format
    //TODO:time based highlight fade
    public IComponent update(IDrawer drawer) {
        ItemStack stack = drawer.getStoredItemPrototype();
        if (stack == null || stack.getItem() == null) {
            return updateEmpty();
        }

        boolean highlightStacks = stackTracker.update(stack);
        int stackCount = drawer.getStoredItemCount() / drawer.getStoredItemStackSize();
        int remainder = drawer.getStoredItemCount() - (stackCount * drawer.getStoredItemStackSize());
        boolean highlightStackCount = stackCountTracker.update(stackCount) || highlightStacks;
        boolean highlightRemainder = remainderTracker.update(remainder) || highlightStacks;

        String displayName = DisplayUtil.stripSymbols(
                DisplayUtil.itemDisplayNameShortFormatted(drawer.getStoredItemPrototype()));
        ITooltip itemLine = new HPanelComponent();
        if (highlightStacks) {
            itemLine.child(ThemeHelper.INSTANCE.info(displayName + " ["));
        }
        else {
            itemLine.text(displayName + " [");
        }

        if (stackCount > 0 && remainder > 0) {
            if (highlightStackCount) {
                itemLine.child(ThemeHelper.INSTANCE.info(String.valueOf(stackCount)));
            }
            else {
                itemLine.text(String.valueOf(stackCount));
            }
            if (highlightStacks) {
                itemLine.child(ThemeHelper.INSTANCE.info("x" + drawer.getStoredItemStackSize() + " + "));
            }
            else {
                itemLine.text("x" + drawer.getStoredItemStackSize() + " + ");
            }
            if (highlightRemainder) {
                itemLine.child(ThemeHelper.INSTANCE.info(String.valueOf(remainder)));
            }
            else {
                itemLine.text(String.valueOf(remainder));
            }
            if (highlightStacks) {
                itemLine.child(ThemeHelper.INSTANCE.info("]"));
            }
            else {
                itemLine.text("]");
            }
        }
        else if (stackCount > 0) {
            if (highlightStackCount) {
                itemLine.child(ThemeHelper.INSTANCE.info(String.valueOf(stackCount)));
            }
            else {
                itemLine.text(String.valueOf(stackCount));
            }
            if (highlightStacks) {
                itemLine.child(ThemeHelper.INSTANCE.info("x" + drawer.getStoredItemStackSize() + "]"));
            }
            else {
                itemLine.text("x" + drawer.getStoredItemStackSize() + "]");
            }
        }
        else {
            if (highlightRemainder) {
                itemLine.child(ThemeHelper.INSTANCE.info(String.valueOf(remainder)));
            }
            else {
                itemLine.text(String.valueOf(remainder));
            }
            if (highlightStacks) {
                itemLine.child(ThemeHelper.INSTANCE.info("]"));
            }
            else {
                itemLine.text("]");
            }
        }
        return itemLine;
    }

    private IComponent updateEmpty() {
        boolean highlightStacks = stackTracker.update(null);
        stackCountTracker.update(0);
        remainderTracker.update(0);
        Theme theme = General.currentTheme.get();
        return new HPanelComponent().style(new PanelStyle().alignment(ComponentAlignment.CENTER))
                .child(new RectComponent()
                        .style(new RectStyle().backgroundColor(highlightStacks ?
                                theme.textColor(MessageType.INFO) : theme.textColor(MessageType.NORMAL)))
                        .size(new Size(25, 1)))
                .child(new TextComponent(StatCollector.translateToLocal("hud.msg.wdmla.empty")).scale(0.6f)
                        .style(new TextStyle().color(highlightStacks ?
                                theme.textColor(MessageType.INFO) : theme.textColor(MessageType.NORMAL))))
                .child(new RectComponent()
                        .style(new RectStyle().backgroundColor(highlightStacks ?
                                theme.textColor(MessageType.INFO) : theme.textColor(MessageType.NORMAL)))
                        .size(new Size(25, 1)));
    }
}
