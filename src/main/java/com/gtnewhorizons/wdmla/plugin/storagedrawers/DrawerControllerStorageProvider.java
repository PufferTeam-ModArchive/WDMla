package com.gtnewhorizons.wdmla.plugin.storagedrawers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gtnewhorizons.wdmla.api.identifier.CommonPluginIDs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.Nullable;

import com.gtnewhorizons.wdmla.api.accessor.Accessor;
import com.gtnewhorizons.wdmla.api.provider.IClientExtensionProvider;
import com.gtnewhorizons.wdmla.api.provider.IServerExtensionProvider;
import com.gtnewhorizons.wdmla.api.view.ClientViewGroup;
import com.gtnewhorizons.wdmla.api.view.ItemView;
import com.gtnewhorizons.wdmla.api.view.ViewGroup;
import com.jaquadro.minecraft.storagedrawers.api.storage.IDrawer;
import com.jaquadro.minecraft.storagedrawers.block.tile.TileEntityController;

public enum DrawerControllerStorageProvider
        implements IServerExtensionProvider<ItemStack>, IClientExtensionProvider<ItemStack, ItemView> {

    INSTANCE;

    @Override
    public List<ClientViewGroup<ItemView>> getClientGroups(Accessor accessor, List<ViewGroup<ItemStack>> groups) {
        return ClientViewGroup.map(groups, ItemView::new, null);
    }

    @Override
    public @Nullable List<ViewGroup<ItemStack>> getGroups(Accessor accessor) {
        if (accessor.getTarget() instanceof TileEntityController controller) {
            List<ItemStack> list = new ArrayList<>();
            for (int i = 0, n = controller.getDrawerCount(); i < n; i++) {
                IDrawer drawer = controller.getDrawer(i);
                if (drawer == null || !controller.isDrawerEnabled(i)) {
                    continue;
                }

                ItemStack stack = drawer.getStoredItemCopy();
                if (stack != null && stack.getItem() != null) {
                    list.add(stack);
                }
            }
            return Arrays.asList(new ViewGroup<>(list, "content", null));
        }
        return null;
    }

    @Override
    public ResourceLocation getUid() {
        return CommonPluginIDs.DRAWERS_CONTROLLER_STORAGE;
    }
}
