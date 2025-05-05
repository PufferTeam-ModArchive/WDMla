package com.gtnewhorizons.wdmla.plugin.storagedrawers;

import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.HighlightState;
import com.gtnewhorizons.wdmla.api.ui.HighlightTracker;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.impl.ObjectDataCenter;
import com.gtnewhorizons.wdmla.impl.ui.StatusHelper;
import com.gtnewhorizons.wdmla.impl.ui.ThemeHelper;
import com.gtnewhorizons.wdmla.impl.ui.component.HPanelComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.IconComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.VPanelComponent;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Padding;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Size;
import com.gtnewhorizons.wdmla.overlay.WDMlaUIIcons;
import com.jaquadro.minecraft.storagedrawers.api.storage.attribute.LockAttribute;
import com.jaquadro.minecraft.storagedrawers.block.tile.TileEntityDrawers;
import com.jaquadro.minecraft.storagedrawers.core.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import static com.gtnewhorizons.wdmla.impl.ui.StatusHelper.ICON_SIZE;

public enum DrawersPropertyProvider implements IBlockComponentProvider {
    INSTANCE;

    private HighlightTracker<Boolean> isLockedTracker;
    private HighlightTracker<Boolean> isVoidTracker;
    private HighlightTracker<Boolean> hasOwnerTracker;
    private MovingObjectPosition lastPos;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor) {
        if (!(accessor.getTileEntity() instanceof TileEntityDrawers drawers)) {
            return;
        }

        boolean isLocked = drawers.isLocked(LockAttribute.LOCK_POPULATED);
        boolean isVoid = drawers.isVoid();
        boolean hasOwner = drawers.getOwner() != null;

        if (!ObjectDataCenter.equals(accessor.getHitResult(), lastPos)) {
            isLockedTracker = new HighlightTracker<>(isLocked);
            isVoidTracker = new HighlightTracker<>(isVoid);
            hasOwnerTracker = new HighlightTracker<>(hasOwner);
        }
        lastPos = accessor.getHitResult();

        ITooltip panel = new HPanelComponent();
        ITooltip detailedPanel = new VPanelComponent();
        if (isLockedTracker.update(isLocked)) {
            if (isLocked) {
                detailedPanel.child(StatusHelper.INSTANCE.locked(HighlightState.ACTIVATING));
            }
            else {
                detailedPanel.child(StatusHelper.INSTANCE.locked(HighlightState.DEACTIVATING));
            }
        }
        else if (isLocked){
            panel.child(new HPanelComponent().child(
                    new IconComponent(WDMlaUIIcons.LOCK, WDMlaUIIcons.LOCK.texPath).size(new Size(ICON_SIZE, ICON_SIZE))));
        }

        if (isVoidTracker.update(isVoid)) {
            if (isVoid) {
                detailedPanel.child(StatusHelper.INSTANCE.voidOverflow(HighlightState.ACTIVATING));
            }
            else {
                detailedPanel.child(StatusHelper.INSTANCE.voidOverflow(HighlightState.DEACTIVATING));
            }
        }
        else if (isVoid){
            panel.child(new HPanelComponent().child(
                    new IconComponent(WDMlaUIIcons.VOID, WDMlaUIIcons.VOID.texPath).size(new Size(ICON_SIZE, ICON_SIZE))));
        }

        if (hasOwnerTracker.update(hasOwner)) {
            if (hasOwner) {
                detailedPanel.child(new HPanelComponent().child(
                                ThemeHelper.INSTANCE.smallItem(new ItemStack(ModItems.personalKey, 1)))
                        .child(ThemeHelper.INSTANCE.success(StatCollector.translateToLocal("hud.msg.wdmla.access.protected") + "!")));
            }
            else {
                detailedPanel.child(new HPanelComponent().child(
                                ThemeHelper.INSTANCE.smallItem(new ItemStack(ModItems.personalKey, 1))
                                        .child(new HPanelComponent().padding(new Padding(2,0,1.5f,0))
                                                .child(ThemeHelper.INSTANCE.failure("✕"))))
                        .child(ThemeHelper.INSTANCE.failure(StatCollector.translateToLocal("hud.msg.wdmla.access.protected"))));
            }
        }
        else if (hasOwner){
            panel.child(new HPanelComponent().child(
                    ThemeHelper.INSTANCE.smallItem(new ItemStack(ModItems.personalKey, 1))));
        }

        if (panel.childrenSize() > 0) {
            tooltip.child(panel);
        }
        if (detailedPanel.childrenSize() > 0) {
            tooltip.child(detailedPanel);
        }

        // unused?
        if (drawers.isSorting()) {
            tooltip.text("sorting");
        }
    }

    @Override
    public ResourceLocation getUid() {
        return StorageDrawersPlugin.path("property");
    }

    @Override
    public int getDefaultPriority() {
        return DrawersContentProvider.INSTANCE.getDefaultPriority() + 100;
    }
}
