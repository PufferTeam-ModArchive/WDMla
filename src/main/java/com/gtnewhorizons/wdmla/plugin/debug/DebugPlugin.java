package com.gtnewhorizons.wdmla.plugin.debug;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.config.Configuration;

import com.gtnewhorizons.wdmla.api.IWDMlaClientRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaPlugin;
import com.gtnewhorizons.wdmla.api.identifier.WDMlaIDs;
import com.gtnewhorizons.wdmla.api.WDMlaPlugin;
import com.gtnewhorizons.wdmla.api.config.WDMlaConfig;

/**
 * The base plugin that registers providers which provide less useful information to very wide range objects.
 */
@SuppressWarnings("unused")
@WDMlaPlugin(uid = WDMlaIDs.NAMESPACE_DEBUG)
public class DebugPlugin implements IWDMlaPlugin {

    @Override
    public void registerClient(IWDMlaClientRegistration registration) {
        registration.registerBlockComponent(HardnessProvider.INSTANCE, Block.class);
        registration.registerBlockComponent(BlastResistanceProvider.INSTANCE, Block.class);
        registration.registerBlockComponent(RegistryDataProvider.getBlock(), Block.class);
        registration.registerBlockComponent(CoordinatesProvider.getBlock(), Block.class);

        registration.registerEntityComponent(RegistryDataProvider.getEntity(), Entity.class);
        registration.registerEntityComponent(CoordinatesProvider.getEntity(), Entity.class);

        WDMlaConfig.instance().setCategoryLangKey(
                WDMlaIDs.CONFIG_AUTOGEN + Configuration.CATEGORY_SPLITTER + WDMlaIDs.NAMESPACE_DEBUG,
                "provider.wdmla.debug.category");
    }
}
