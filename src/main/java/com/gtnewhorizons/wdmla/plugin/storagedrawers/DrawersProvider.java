package com.gtnewhorizons.wdmla.plugin.storagedrawers;

import com.gtnewhorizons.wdmla.api.Theme;
import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.ComponentAlignment;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.api.ui.MessageType;
import com.gtnewhorizons.wdmla.config.General;
import com.gtnewhorizons.wdmla.impl.ui.StatusHelper;
import com.gtnewhorizons.wdmla.impl.ui.ThemeHelper;
import com.gtnewhorizons.wdmla.impl.ui.component.HPanelComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.IconComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.RectComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.TextComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.VPanelComponent;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Size;
import com.gtnewhorizons.wdmla.impl.ui.style.PanelStyle;
import com.gtnewhorizons.wdmla.impl.ui.style.RectStyle;
import com.gtnewhorizons.wdmla.overlay.WDMlaUIIcons;
import com.jaquadro.minecraft.storagedrawers.api.storage.IDrawer;
import com.jaquadro.minecraft.storagedrawers.api.storage.IFractionalDrawer;
import com.jaquadro.minecraft.storagedrawers.api.storage.attribute.LockAttribute;
import com.jaquadro.minecraft.storagedrawers.block.tile.TileEntityDrawers;
import com.jaquadro.minecraft.storagedrawers.core.ModItems;
import com.jaquadro.minecraft.storagedrawers.security.SecurityManager;
import mcp.mobius.waila.overlay.DisplayUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import static com.gtnewhorizons.wdmla.impl.ui.StatusHelper.ICON_SIZE;

public enum DrawersProvider implements IBlockComponentProvider {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor) {
        if (!(accessor.getTileEntity() instanceof TileEntityDrawers drawers)) {
            return;
        }

        if (SecurityManager.hasAccess(accessor.getPlayer().getGameProfile(), drawers)) {
            appendDrawersContent(drawers, tooltip);
        }
        else {
            tooltip.child(ThemeHelper.INSTANCE.failure(StatCollector.translateToLocal("hud.msg.wdmla.no.access")));
        }

        appendDrawersProperty(drawers, tooltip, accessor);
    }

    @Override
    public ResourceLocation getUid() {
        return StorageDrawersPlugin.path("drawers");
    }

    private void appendDrawersContent(TileEntityDrawers drawerTile, ITooltip tooltip) {
        Theme theme = General.currentTheme.get();
        for (int i = 0; i < drawerTile.getDrawerCount(); i++) {
            if (!drawerTile.isDrawerEnabled(i)) {
                continue;
            }

            IDrawer drawer = drawerTile.getDrawer(i);
            ItemStack stack = drawer.getStoredItemPrototype();
            if (stack == null || stack.getItem() == null) {
                tooltip.child(
                        new HPanelComponent().style(new PanelStyle().alignment(ComponentAlignment.CENTER))
                                .child(new RectComponent()
                                        .style(new RectStyle().backgroundColor(theme.textColor(MessageType.NORMAL)))
                                        .size(new Size(25, 1)))
                                .child(new TextComponent(StatCollector.translateToLocal("hud.msg.wdmla.empty")).scale(0.6f))
                                .child(new RectComponent()
                                                .style(new RectStyle().backgroundColor(theme.textColor(MessageType.NORMAL)))
                                                .size(new Size(25, 1))));
                continue;
            }

            String displayName = DisplayUtil.stripSymbols(DisplayUtil.itemDisplayNameShortFormatted(stack));
            if (drawer.getStoredItemCount() == Integer.MAX_VALUE) {
                tooltip.horizontal().child(ThemeHelper.INSTANCE.smallItem(stack)).text(displayName + " [Inf]");
                continue;
            }
            //GTNH has no compact drawer
            if (drawer instanceof IFractionalDrawer
                    && ((IFractionalDrawer) drawer).getConversionRate() > 1) {
                String text = displayName + ((i == 0) ? " [" : " [+")
                        + ((IFractionalDrawer) drawer).getStoredItemRemainder()
                        + "]";
                tooltip.horizontal().child(ThemeHelper.INSTANCE.smallItem(stack)).text(text);
                continue;
            }

            String stackSizeLine = assembleStackSizeLine(drawer);
            // Grass [1x64 + 22]
            tooltip.horizontal().child(ThemeHelper.INSTANCE.smallItem(stack)).text(displayName + stackSizeLine);
        }

        if (drawerTile.isUnlimited() || drawerTile.isVending()) {
            tooltip.text(StatCollector.translateToLocal("hud.msg.wdmla.stack.limit") + ": Inf");
        }
        else {
            int limit = drawerTile.getDrawerCapacity() * drawerTile.getEffectiveStorageMultiplier();
            tooltip.horizontal().text(
                    StatCollector.translateToLocal(
                            "hud.msg.wdmla.stack.limit"))
                    .child(ThemeHelper.INSTANCE.info(String.format(": %d (x%d)",
                            limit, drawerTile.getEffectiveStorageMultiplier())));
        }
    }

    private String assembleStackSizeLine(IDrawer drawer) {
        int stacks = drawer.getStoredItemCount() / drawer.getStoredItemStackSize();
        int remainder = drawer.getStoredItemCount() - (stacks * drawer.getStoredItemStackSize());
        String stackSizeLine;
        if (stacks > 0 && remainder > 0) {
            stackSizeLine = " ["
                    + stacks
                    + "x"
                    + drawer.getStoredItemStackSize()
                    + " + "
                    + remainder
                    + "]";
        }
        else if (stacks > 0) {
            stackSizeLine = " [" + stacks + "x" + drawer.getStoredItemStackSize() + "]";
        }
        else {
            stackSizeLine = " [" + remainder + "]";
        }
        return stackSizeLine;
    }

    private void appendDrawersProperty(TileEntityDrawers drawers, ITooltip tooltip, BlockAccessor accessor) {
        ITooltip panel = accessor.showDetails() ?
                new VPanelComponent() : new HPanelComponent();
        if (drawers.isLocked(LockAttribute.LOCK_POPULATED)) {
            if (accessor.showDetails()) {
                panel.child(StatusHelper.INSTANCE.locked());
            }
            else {
                panel.child(new HPanelComponent().child(
                        new IconComponent(WDMlaUIIcons.LOCK, WDMlaUIIcons.LOCK.texPath).size(new Size(ICON_SIZE, ICON_SIZE))));
            }
        }
        if (drawers.isVoid()) {
            if (accessor.showDetails()) {
                panel.child(StatusHelper.INSTANCE.voidOverflow());
            }
            else {
                panel.child(new HPanelComponent().child(
                        new IconComponent(WDMlaUIIcons.VOID, WDMlaUIIcons.VOID.texPath).size(new Size(ICON_SIZE, ICON_SIZE))));
            }
        }
        if (drawers.getOwner() != null) {
            if (accessor.showDetails()) {
                panel.child(new HPanelComponent().child(
                                ThemeHelper.INSTANCE.smallItem(new ItemStack(ModItems.personalKey, 1)))
                        .child(ThemeHelper.INSTANCE.info(StatCollector.translateToLocal("hud.msg.wdmla.access.protected"))));
            }
            else {
                panel.child(new HPanelComponent().child(
                        ThemeHelper.INSTANCE.smallItem(new ItemStack(ModItems.personalKey, 1))));
            }
        }
        // unused?
        if (drawers.isSorting()) {
            panel.text("sorting");
        }

        if(panel.childrenSize() > 0) {
            tooltip.child(panel);
        }
    }
}
