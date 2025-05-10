package com.gtnewhorizons.wdmla.plugin.architecturecraft;

import com.gtnewhorizons.wdmla.api.identifier.CommonPluginIDs;
import net.minecraftforge.common.config.Configuration;

import com.gtnewhorizons.wdmla.api.IWDMlaClientRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaCommonRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaPlugin;
import com.gtnewhorizons.wdmla.api.identifier.WDMlaIDs;
import com.gtnewhorizons.wdmla.api.WDMlaPlugin;
import com.gtnewhorizons.wdmla.api.config.WDMlaConfig;
import com.gtnewhorizons.wdmla.plugin.universal.ItemStorageProvider;

import gcewing.architecture.common.block.BlockSawbench;
import gcewing.architecture.common.block.BlockShape;

// TODO: display chisel and hammer interaction
@WDMlaPlugin(uid = "architecturecraft", dependencies = "ArchitectureCraft")
public class ArchitectureCraftPlugin implements IWDMlaPlugin {

    @Override
    public void register(IWDMlaCommonRegistration registration) {
        registration.registerItemStorage(ItemStorageProvider.Extension.INSTANCE, BlockSawbench.class);
    }

    @Override
    public void registerClient(IWDMlaClientRegistration registration) {
        registration.registerBlockComponent(BlockShapeProvider.INSTANCE, BlockShape.class);
        registration.registerHarvest(ShapeHarvestHandler.INSTANCE, BlockShape.class);

        WDMlaConfig.instance().setCategoryLangKey(
                WDMlaIDs.CONFIG_AUTOGEN + Configuration.CATEGORY_SPLITTER + CommonPluginIDs.NAMESPACE_ARCHITECTURECRAFT,
                "provider.wdmla.architecturecraft.category");
    }
}
