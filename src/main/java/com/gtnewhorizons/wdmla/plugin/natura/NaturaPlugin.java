package com.gtnewhorizons.wdmla.plugin.natura;

import com.gtnewhorizons.wdmla.api.IWDMlaClientRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaCommonRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaPlugin;
import com.gtnewhorizons.wdmla.api.Identifiers;
import com.gtnewhorizons.wdmla.api.WDMlaPlugin;
import com.gtnewhorizons.wdmla.config.WDMlaConfig;
import com.gtnewhorizons.wdmla.plugin.vanilla.FurnaceProvider;
import mods.natura.blocks.crops.BerryBush;
import mods.natura.blocks.crops.CropBlock;
import mods.natura.blocks.tech.NetherrackFurnaceBlock;
import mods.natura.blocks.trees.NLeaves;
import mods.natura.blocks.trees.NLeavesDark;
import mods.natura.blocks.trees.NLeavesNocolor;
import mods.natura.blocks.trees.OverworldLeaves;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;

@WDMlaPlugin(uid = "Natura", dependencies = "Natura",
        overridingRegistrationMethodName = "mods.natura.plugins.waila.WailaRegistrar.wailaCallback")
public class NaturaPlugin implements IWDMlaPlugin {

    @Override
    public void register(IWDMlaCommonRegistration registration) {
        registration.registerBlockDataProvider(FurnaceProvider.INSTANCE, NetherrackFurnaceBlock.class);
    }

    @Override
    public void registerClient(IWDMlaClientRegistration registration) {
        registration.registerBlockComponent(NaturaCropHeaderProvider.INSTANCE, CropBlock.class);
        registration.registerBlockComponent(NaturaGrowthRateProvider.INSTANCE, CropBlock.class);
        registration.registerBlockComponent(NaturaLeavesProvider.INSTANCE, NLeaves.class);
        registration.registerBlockComponent(NaturaLeavesProvider.INSTANCE, NLeavesNocolor.class);
        registration.registerBlockComponent(NaturaLeavesProvider.INSTANCE, NLeavesDark.class);
        registration.registerBlockComponent(NaturaLeavesProvider.INSTANCE, OverworldLeaves.class);
        registration.registerBlockComponent(FurnaceProvider.INSTANCE, NetherrackFurnaceBlock.class);
        registration.registerBlockComponent(BerryBushProvider.INSTANCE, BerryBush.class);

        WDMlaConfig.instance().getCategory(
                        Identifiers.CONFIG_AUTOGEN + Configuration.CATEGORY_SPLITTER + "natura")
                .setLanguageKey("provider.wdmla.natura.category");
    }

    public static ResourceLocation path(String path) {
        return new ResourceLocation("natura", path);
    }
}
