package com.gtnewhorizons.wdmla.plugin.harvestcraft;

import com.gtnewhorizons.wdmla.api.identifier.CommonPluginIDs;
import net.minecraftforge.common.config.Configuration;

import com.gtnewhorizons.wdmla.api.IWDMlaClientRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaCommonRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaPlugin;
import com.gtnewhorizons.wdmla.api.identifier.WDMlaIDs;
import com.gtnewhorizons.wdmla.api.WDMlaPlugin;
import com.gtnewhorizons.wdmla.api.config.WDMlaConfig;
import com.gtnewhorizons.wdmla.plugin.universal.ItemStorageProvider;
import com.pam.harvestcraft.BlockPamAnimalTrap;
import com.pam.harvestcraft.BlockPamFishTrap;
import com.pam.harvestcraft.BlockPamFruit;

// GTNH Harvestcraft is in private repo so we maintain the plugin here
@WDMlaPlugin(uid = "harvestcraft", dependencies = "harvestcraft")
public class HarvestcraftPlugin implements IWDMlaPlugin {

    @Override
    public void register(IWDMlaCommonRegistration registration) {
        registration.registerBlockDataProvider(AnimalTrapProvider.INSTANCE, BlockPamAnimalTrap.class);
        registration.registerBlockDataProvider(FishTrapProvider.INSTANCE, BlockPamFishTrap.class);
        registration.registerItemStorage(ItemStorageProvider.Extension.INSTANCE, BlockPamAnimalTrap.class);
        registration.registerItemStorage(ItemStorageProvider.Extension.INSTANCE, BlockPamFishTrap.class);
    }

    @Override
    public void registerClient(IWDMlaClientRegistration registration) {
        registration.registerBlockComponent(HarvestcraftFruitGrowthRateProvider.INSTANCE, BlockPamFruit.class);
        registration.registerBlockComponent(AnimalTrapProvider.INSTANCE, BlockPamAnimalTrap.class);
        registration.registerBlockComponent(FishTrapProvider.INSTANCE, BlockPamFishTrap.class);

        WDMlaConfig.instance().setCategoryLangKey(
                WDMlaIDs.CONFIG_AUTOGEN + Configuration.CATEGORY_SPLITTER + CommonPluginIDs.NAMESPACE_HARVESTCRAFT,
                "provider.wdmla.harvestcraft.category");
    }
}
