package com.gtnewhorizons.wdmla.plugin.storagedrawers;

import com.gtnewhorizons.wdmla.api.IWDMlaClientRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaPlugin;
import com.gtnewhorizons.wdmla.api.WDMlaPlugin;
import com.gtnewhorizons.wdmla.plugin.core.HideFancyIconProvider;
import com.jaquadro.minecraft.storagedrawers.block.BlockDrawers;
import com.jaquadro.minecraft.storagedrawers.block.BlockDrawersCustom;
import com.jaquadro.minecraft.storagedrawers.block.BlockFramingTable;
import net.minecraft.util.ResourceLocation;

@WDMlaPlugin(uid = "StorageDrawers", dependencies = "StorageDrawers",
        overridingRegistrationMethodName = "com.jaquadro.minecraft.storagedrawers.integration.Waila.registerProvider")
public class StorageDrawersPlugin  implements IWDMlaPlugin {

    @Override
    public void registerClient(IWDMlaClientRegistration registration) {
        registration.registerBlockComponent(DrawersContentProvider.INSTANCE, BlockDrawers.class);
        registration.registerBlockComponent(DrawersPropertyProvider.INSTANCE, BlockDrawers.class);
        registration.registerBlockComponent(HideFancyIconProvider.getBlock(), BlockDrawersCustom.class);
        registration.registerBlockComponent(HideFancyIconProvider.getBlock(), BlockFramingTable.class);
    }

    public static ResourceLocation path(String path) {
        return new ResourceLocation("storagedrawers", path);
    }
}
