package com.gtnewhorizons.wdmla.plugin.storagedrawers;

import com.gtnewhorizons.wdmla.api.identifier.CommonPluginIDs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import com.gtnewhorizons.wdmla.api.accessor.Accessor;
import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.impl.ObjectDataCenter;
import com.gtnewhorizons.wdmla.api.ui.helper.ThemeHelper;
import com.jaquadro.minecraft.storagedrawers.api.storage.IDrawer;
import com.jaquadro.minecraft.storagedrawers.api.storage.IFractionalDrawer;
import com.jaquadro.minecraft.storagedrawers.block.tile.TileEntityDrawers;
import com.jaquadro.minecraft.storagedrawers.security.SecurityManager;

import mcp.mobius.waila.overlay.DisplayUtil;

public enum DrawersContentProvider implements IBlockComponentProvider {

    INSTANCE;

    private final DrawerInfoHighlighter[] tracker = new DrawerInfoHighlighter[4];
    private MovingObjectPosition lastPos;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor) {
        if (!(accessor.getTileEntity() instanceof TileEntityDrawers drawers)) {
            return;
        }

        if (SecurityManager.hasAccess(accessor.getPlayer().getGameProfile(), drawers)) {
            appendDrawersContent(accessor, drawers, tooltip);
        } else {
            tooltip.child(ThemeHelper.instance().failure(StatCollector.translateToLocal("hud.msg.wdmla.no.access")));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return CommonPluginIDs.DRAWERS_CONTENT;
    }

    private void appendDrawersContent(Accessor accessor, TileEntityDrawers drawerTile, ITooltip tooltip) {
        for (int i = 0; i < drawerTile.getDrawerCount(); i++) {
            if (!drawerTile.isDrawerEnabled(i)) {
                continue;
            }

            IDrawer drawer = drawerTile.getDrawer(i);
            if (!ObjectDataCenter.equals(accessor.getHitResult(), lastPos) || tracker[i] == null) {
                tracker[i] = new DrawerInfoHighlighter(drawer);
            }

            ItemStack stack = drawer.getStoredItemPrototype();
            if (stack == null || stack.getItem() == null) {
                tooltip.child(tracker[i].update(drawer));
                continue;
            }

            String displayName = DisplayUtil.stripSymbols(DisplayUtil.itemDisplayNameShortFormatted(stack));
            if (drawer.getStoredItemCount() == Integer.MAX_VALUE) {
                tooltip.horizontal().child(ThemeHelper.instance().smallItem(stack)).text(displayName + " [Inf]");
                continue;
            }
            // GTNH has no compact drawer
            if (drawer instanceof IFractionalDrawer && ((IFractionalDrawer) drawer).getConversionRate() > 1) {
                String text = displayName + ((i == 0) ? " [" : " [+")
                        + ((IFractionalDrawer) drawer).getStoredItemRemainder()
                        + "]";
                tooltip.horizontal().child(ThemeHelper.instance().smallItem(stack)).text(text);
                continue;
            }

            ITooltip itemLine = tracker[i].update(drawer);
            tooltip.horizontal().child(ThemeHelper.instance().smallItem(stack)).child(itemLine);
        }

        if (drawerTile.isUnlimited() || drawerTile.isVending()) {
            tooltip.text(StatCollector.translateToLocal("hud.msg.wdmla.stack.limit") + ": Inf");
        } else {
            int limit = drawerTile.getDrawerCapacity() * drawerTile.getEffectiveStorageMultiplier();
            tooltip.horizontal().text(StatCollector.translateToLocal("hud.msg.wdmla.stack.limit")).child(
                    ThemeHelper.instance()
                            .info(String.format(": %d (x%d)", limit, drawerTile.getEffectiveStorageMultiplier())));
        }

        lastPos = accessor.getHitResult();
    }
}
