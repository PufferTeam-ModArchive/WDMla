package com.gtnewhorizons.wdmla.plugin.vanilla;

import java.util.Arrays;
import java.util.List;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.api.view.ClientViewGroup;
import com.gtnewhorizons.wdmla.api.view.ItemView;
import com.gtnewhorizons.wdmla.plugin.universal.ItemStorageProvider;

public enum SnowLayerProvider implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor) {
        ItemView itemView = new ItemView(new ItemStack(Blocks.snow_layer, (accessor.getMetadata() & 7) + 1));
        List<ClientViewGroup<ItemView>> viewList = Arrays.asList(new ClientViewGroup<>(Arrays.asList(itemView)));
        ItemStorageProvider.append(tooltip, accessor, viewList);
    }

    @Override
    public ResourceLocation getUid() {
        return VanillaIdentifiers.SNOW_LAYER;
    }
}
