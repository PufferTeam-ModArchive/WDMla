package com.gtnewhorizons.wdmla.plugin.hungeroverhaul;

import com.gtnewhorizons.wdmla.api.identifier.CommonPluginIDs;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraftforge.common.config.Configuration;

import com.gtnewhorizons.wdmla.api.IWDMlaClientRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaCommonRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaPlugin;
import com.gtnewhorizons.wdmla.api.identifier.WDMlaIDs;
import com.gtnewhorizons.wdmla.api.WDMlaPlugin;
import com.gtnewhorizons.wdmla.api.config.WDMlaConfig;

@SuppressWarnings("unused")
@WDMlaPlugin(uid = "hungeroverhaul", dependencies = "HungerOverhaul")
public class HungerOverhaulPlugin implements IWDMlaPlugin {

    @Override
    public void register(IWDMlaCommonRegistration registration) {
        registration.registerEntityDataProvider(MilkCooldownProvider.INSTANCE, EntityCow.class);
    }

    @Override
    public void registerClient(IWDMlaClientRegistration registration) {
        registration.registerBlockComponent(HoeResultProvider.INSTANCE, BlockGrass.class);
        registration.registerBlockComponent(HoeResultProvider.INSTANCE, BlockDirt.class);

        registration.registerEntityComponent(SlowerChickenProvider.INSTANCE, EntityChicken.class);
        registration.registerEntityComponent(SlowerAnimalProvider.INSTANCE, EntityAnimal.class);
        registration.registerEntityComponent(MilkCooldownProvider.INSTANCE, EntityCow.class);

        WDMlaConfig.instance().setCategoryLangKey(
                WDMlaIDs.CONFIG_AUTOGEN + Configuration.CATEGORY_SPLITTER + CommonPluginIDs.NAMESPACE_HUNGEROVERHAUL,
                "provider.wdmla.hungeroverhaul.category");
    }
}
