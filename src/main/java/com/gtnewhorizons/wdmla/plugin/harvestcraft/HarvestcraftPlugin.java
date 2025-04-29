package com.gtnewhorizons.wdmla.plugin.harvestcraft;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;

import com.gtnewhorizons.wdmla.api.IWDMlaClientRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaCommonRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaPlugin;
import com.gtnewhorizons.wdmla.api.Identifiers;
import com.gtnewhorizons.wdmla.api.WDMlaPlugin;
import com.gtnewhorizons.wdmla.config.WDMlaConfig;
import com.pam.harvestcraft.BlockPamFruit;

@WDMlaPlugin(uid = "harvestcraft", dependencies = "harvestcraft")
public class HarvestcraftPlugin implements IWDMlaPlugin {

    @Override
    public void register(IWDMlaCommonRegistration registration) {

    }

    @Override
    public void registerClient(IWDMlaClientRegistration registration) {
        registration.registerBlockComponent(HarvestcraftFruitGrowthRateProvider.INSTANCE, BlockPamFruit.class);

        WDMlaConfig.instance()
                .getCategory(Identifiers.CONFIG_AUTOGEN + Configuration.CATEGORY_SPLITTER + "harvestcraft")
                .setLanguageKey("provider.wdmla.harvestcraft.category");
    }

    public static ResourceLocation path(String path) {
        return new ResourceLocation("harvestcraft", path);
    }
}
