package com.gtnewhorizons.wdmla.plugin.forestry;

import com.gtnewhorizons.wdmla.api.identifier.CommonPluginIDs;
import net.minecraftforge.common.config.Configuration;

import com.gtnewhorizons.wdmla.api.IWDMlaClientRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaPlugin;
import com.gtnewhorizons.wdmla.api.identifier.WDMlaIDs;
import com.gtnewhorizons.wdmla.api.WDMlaPlugin;
import com.gtnewhorizons.wdmla.api.config.WDMlaConfig;

import forestry.apiculture.blocks.BlockBeehives;

// TODO: finish supporting rest of tile entities
@WDMlaPlugin(uid = "forestry", dependencies = "Forestry")
public class ForestryPlugin implements IWDMlaPlugin {

    @Override
    public void registerClient(IWDMlaClientRegistration registration) {
        registration.registerHarvest(ForestryToolHarvestHandler.INSTANCE, BlockBeehives.class);

        WDMlaConfig.instance().setCategoryLangKey(
                WDMlaIDs.CONFIG_AUTOGEN + Configuration.CATEGORY_SPLITTER + CommonPluginIDs.NAMESPACE_FORESTRY,
                "provider.wdmla.forestry.category");
    }
}
