package com.gtnewhorizons.wdmla.plugin.hungeroverhaul;

import com.gtnewhorizons.wdmla.api.IWDMlaClientRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaCommonRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaPlugin;
import com.gtnewhorizons.wdmla.api.WDMlaPlugin;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.ResourceLocation;

@WDMlaPlugin(uid = "hunger_overhaul", dependencies = "HungerOverhaul")
public class HungerOverhaulPlugin implements IWDMlaPlugin {

    @Override
    public void register(IWDMlaCommonRegistration registration) {

    }

    @Override
    public void registerClient(IWDMlaClientRegistration registration) {
        registration.registerEntityComponent(SlowChickenProvider.INSTANCE, EntityChicken.class);
        registration.registerEntityComponent(SlowAnimalProvider.INSTANCE, EntityAnimal.class);
    }

    public static ResourceLocation path(String path) {
        return new ResourceLocation("hungeroverhaul", path);
    }
}
