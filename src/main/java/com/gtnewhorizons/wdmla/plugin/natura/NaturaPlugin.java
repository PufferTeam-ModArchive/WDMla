package com.gtnewhorizons.wdmla.plugin.natura;

import com.gtnewhorizons.wdmla.api.identifier.CommonPluginIDs;
import net.minecraftforge.common.config.Configuration;

import com.gtnewhorizons.wdmla.api.IWDMlaClientRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaCommonRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaPlugin;
import com.gtnewhorizons.wdmla.api.identifier.WDMlaIDs;
import com.gtnewhorizons.wdmla.api.WDMlaPlugin;
import com.gtnewhorizons.wdmla.api.config.WDMlaConfig;
import com.gtnewhorizons.wdmla.plugin.vanilla.FurnaceProvider;

import mods.natura.blocks.crops.BerryBush;
import mods.natura.blocks.crops.CropBlock;
import mods.natura.blocks.tech.NetherrackFurnaceBlock;
import mods.natura.blocks.trees.NLeaves;
import mods.natura.blocks.trees.NLeavesDark;
import mods.natura.blocks.trees.NLeavesNocolor;
import mods.natura.blocks.trees.OverworldLeaves;

@WDMlaPlugin(
        uid = "Natura",
        dependencies = "Natura",
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

        WDMlaConfig.instance().setCategoryLangKey(
                WDMlaIDs.CONFIG_AUTOGEN + Configuration.CATEGORY_SPLITTER + CommonPluginIDs.NAMESPACE_NATURA,
                "provider.wdmla.natura.category");
    }
}
