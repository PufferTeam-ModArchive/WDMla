package com.gtnewhorizons.wdmla.plugin.storagedrawers;

import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.impl.ui.StatusHelper;
import com.gtnewhorizons.wdmla.impl.ui.ThemeHelper;
import com.gtnewhorizons.wdmla.impl.ui.component.HPanelComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.IconComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.ItemComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.VPanelComponent;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Size;
import com.gtnewhorizons.wdmla.impl.ui.style.PanelStyle;
import com.gtnewhorizons.wdmla.impl.ui.style.RectStyle;
import com.gtnewhorizons.wdmla.overlay.WDMlaUIIcons;
import com.jaquadro.minecraft.storagedrawers.api.storage.attribute.LockAttribute;
import com.jaquadro.minecraft.storagedrawers.block.tile.TileEntityDrawers;
import com.jaquadro.minecraft.storagedrawers.core.ModItems;
import com.jaquadro.minecraft.storagedrawers.security.SecurityManager;
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
            appendDrawersContent(tooltip, accessor);
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

    private void appendDrawersContent(ITooltip tooltip, BlockAccessor accessor) {
        //the tedious part
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
