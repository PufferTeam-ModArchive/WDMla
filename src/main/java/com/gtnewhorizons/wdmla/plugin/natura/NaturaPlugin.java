package com.gtnewhorizons.wdmla.plugin.natura;

import com.gtnewhorizons.wdmla.api.IWDMlaClientRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaCommonRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaPlugin;
import com.gtnewhorizons.wdmla.api.WDMlaPlugin;
import mods.natura.blocks.crops.CropBlock;
import net.minecraft.util.ResourceLocation;

@WDMlaPlugin(uid = "Natura", dependencies = "Natura")
public class NaturaPlugin implements IWDMlaPlugin {

    @Override
    public void register(IWDMlaCommonRegistration registration) {

    }

    @Override
    public void registerClient(IWDMlaClientRegistration registration) {
        registration.registerBlockComponent(NaturaCropHeaderProvider.INSTANCE, CropBlock.class);
        registration.registerBlockComponent(NaturaGrowthRateProvider.INSTANCE, CropBlock.class);
    }

    public static ResourceLocation path(String path) {
        return new ResourceLocation("natura", path);
    }
}
