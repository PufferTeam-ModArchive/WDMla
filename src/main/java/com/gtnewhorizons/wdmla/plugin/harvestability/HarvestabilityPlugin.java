package com.gtnewhorizons.wdmla.plugin.harvestability;

import com.gtnewhorizons.wdmla.api.Mods;
import net.minecraft.block.Block;
import net.minecraftforge.common.config.Configuration;

import com.gtnewhorizons.wdmla.api.IWDMlaClientRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaPlugin;
import com.gtnewhorizons.wdmla.api.Identifiers;
import com.gtnewhorizons.wdmla.api.WDMlaPlugin;
import com.gtnewhorizons.wdmla.config.WDMlaConfig;

/**
 * the plugin dedicated to register harvest tool and harvestability info providers.
 */
@SuppressWarnings("unused")
@WDMlaPlugin(uid = HarvestabilityIdentifiers.NAMESPACE_HARVESTABILITY)
public class HarvestabilityPlugin implements IWDMlaPlugin {

    @Override
    public void registerClient(IWDMlaClientRegistration registration) {
        registration.registerBlockComponent(HarvestToolProvider.INSTANCE, Block.class);
//        registration.registerBlockComponent(LegacyHarvestToolProvider.INSTANCE, Block.class);
        registration.registerInteraction(BaseHarvestLogicHandler.INSTANCE, Block.class);
        if (Mods.TCONSTUCT.isLoaded()) {
            registration.registerInteraction(TinkersHarvestHandler.INSTANCE, Block.class);
        }
        if (Mods.GREGTECH.isLoaded()) {
            registration.registerInteraction(GregTechHarvestHandler.INSTANCE, Block.class);
        }

        WDMlaConfig.instance()
                .getCategory(
                        Identifiers.CONFIG_AUTOGEN + Configuration.CATEGORY_SPLITTER
                                + HarvestabilityIdentifiers.NAMESPACE_HARVESTABILITY)
                .setLanguageKey("provider.wdmla.harvestability.category");
    }
}
