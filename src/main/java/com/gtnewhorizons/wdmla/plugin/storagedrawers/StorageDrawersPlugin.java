package com.gtnewhorizons.wdmla.plugin.storagedrawers;

import com.gtnewhorizons.wdmla.api.identifier.CommonPluginIDs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;

import com.gtnewhorizons.wdmla.api.IWDMlaClientRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaCommonRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaPlugin;
import com.gtnewhorizons.wdmla.api.identifier.WDMlaIDs;
import com.gtnewhorizons.wdmla.api.WDMlaPlugin;
import com.gtnewhorizons.wdmla.api.config.WDMlaConfig;
import com.gtnewhorizons.wdmla.plugin.core.HideFancyIconProvider;
import com.gtnewhorizons.wdmla.plugin.universal.ItemStorageProvider;
import com.jaquadro.minecraft.storagedrawers.block.BlockController;
import com.jaquadro.minecraft.storagedrawers.block.BlockDrawers;
import com.jaquadro.minecraft.storagedrawers.block.BlockDrawersCustom;
import com.jaquadro.minecraft.storagedrawers.block.BlockFramingTable;

@SuppressWarnings("unused")
@WDMlaPlugin(
        uid = "StorageDrawers",
        dependencies = "StorageDrawers",
        overridingRegistrationMethodName = "com.jaquadro.minecraft.storagedrawers.integration.Waila.registerProvider")
public class StorageDrawersPlugin implements IWDMlaPlugin {

    @Override
    public void register(IWDMlaCommonRegistration registration) {
        registration.registerItemStorage(ItemStorageProvider.Extension.INSTANCE, BlockFramingTable.class);
        registration.registerItemStorage(DrawerControllerStorageProvider.INSTANCE, BlockController.class);
    }

    @Override
    public void registerClient(IWDMlaClientRegistration registration) {
        registration.registerBlockComponent(DrawersContentProvider.INSTANCE, BlockDrawers.class);
        registration.registerBlockComponent(DrawersPropertyProvider.INSTANCE, BlockDrawers.class);
        registration.registerBlockComponent(HideFancyIconProvider.getBlock(), BlockDrawersCustom.class);
        registration.registerBlockComponent(HideFancyIconProvider.getBlock(), BlockFramingTable.class);

        registration.registerItemStorageClient(DrawerControllerStorageProvider.INSTANCE);

        WDMlaConfig.instance().setCategoryLangKey(
                WDMlaIDs.CONFIG_AUTOGEN + Configuration.CATEGORY_SPLITTER + CommonPluginIDs.NAMESPACE_STORAGEDRAWERS,
                "provider.wdmla.storagedrawers.category");
    }
}
