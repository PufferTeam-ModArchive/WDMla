package com.gtnewhorizons.wdmla.plugin.natura;

import com.gtnewhorizons.wdmla.api.IWDMlaClientRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaCommonRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaPlugin;
import com.gtnewhorizons.wdmla.api.WDMlaPlugin;
import mods.natura.blocks.crops.CropBlock;
import mods.natura.blocks.trees.NLeaves;
import mods.natura.blocks.trees.NLeavesDark;
import mods.natura.blocks.trees.NLeavesNocolor;
import mods.natura.blocks.trees.OverworldLeaves;
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
        registration.registerBlockComponent(NaturaLeavesProvider.INSTANCE, NLeaves.class);
        registration.registerBlockComponent(NaturaLeavesProvider.INSTANCE, NLeavesNocolor.class);
        registration.registerBlockComponent(NaturaLeavesProvider.INSTANCE, NLeavesDark.class);
        registration.registerBlockComponent(NaturaLeavesProvider.INSTANCE, OverworldLeaves.class);
    }

    public static ResourceLocation path(String path) {
        return new ResourceLocation("natura", path);
    }
}
