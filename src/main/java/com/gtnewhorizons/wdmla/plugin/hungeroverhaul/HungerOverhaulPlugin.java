package com.gtnewhorizons.wdmla.plugin.hungeroverhaul;

import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;

import com.gtnewhorizons.wdmla.api.IWDMlaClientRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaCommonRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaPlugin;
import com.gtnewhorizons.wdmla.api.Identifiers;
import com.gtnewhorizons.wdmla.api.WDMlaPlugin;
import com.gtnewhorizons.wdmla.config.WDMlaConfig;

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

        registration.registerEntityComponent(SlowChickenProvider.INSTANCE, EntityChicken.class);
        registration.registerEntityComponent(SlowAnimalProvider.INSTANCE, EntityAnimal.class);
        registration.registerEntityComponent(MilkCooldownProvider.INSTANCE, EntityCow.class);

        WDMlaConfig.instance()
                .getCategory(Identifiers.CONFIG_AUTOGEN + Configuration.CATEGORY_SPLITTER + "hungeroverhaul")
                .setLanguageKey("provider.wdmla.hungeroverhaul.category");
    }

    public static ResourceLocation path(String path) {
        return new ResourceLocation("hungeroverhaul", path);
    }
}
