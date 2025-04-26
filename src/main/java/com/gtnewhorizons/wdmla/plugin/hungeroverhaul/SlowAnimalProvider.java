package com.gtnewhorizons.wdmla.plugin.hungeroverhaul;

import com.gtnewhorizons.wdmla.api.TooltipPosition;
import com.gtnewhorizons.wdmla.api.accessor.EntityAccessor;
import com.gtnewhorizons.wdmla.api.provider.IEntityComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.impl.ui.ThemeHelper;
import com.gtnewhorizons.wdmla.plugin.vanilla.VanillaIdentifiers;
import com.gtnewhorizons.wdmla.util.FormatUtil;
import iguanaman.hungeroverhaul.config.IguanaConfig;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public enum SlowAnimalProvider implements IEntityComponentProvider {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor accessor) {
        if(IguanaConfig.breedingTimeoutMultiplier > 1) {
            IComponent breedComponent = tooltip.getChildWithTag(VanillaIdentifiers.ANIMAL_BREED);
            if(breedComponent instanceof ITooltip breed) {
                tooltip.replaceChildWithTag(VanillaIdentifiers.ANIMAL_BREED,
                        breed.child(
                                ThemeHelper.INSTANCE.warning(
                                        StatCollector.translateToLocalFormatted("hud.msg.wdmla.rng.multiplier",
                                                FormatUtil.STANDARD.format(IguanaConfig.breedingTimeoutMultiplier)))));
            }
        }

        if(IguanaConfig.childDurationMultiplier > 1) {
            IComponent growthComponent = tooltip.getChildWithTag(VanillaIdentifiers.ANIMAL_GROWTH);
            if(growthComponent instanceof ITooltip growth) {
                tooltip.replaceChildWithTag(VanillaIdentifiers.ANIMAL_GROWTH,
                        growth.child(
                                ThemeHelper.INSTANCE.warning(
                                        StatCollector.translateToLocalFormatted("hud.msg.wdmla.rng.multiplier",
                                                FormatUtil.STANDARD.format(IguanaConfig.childDurationMultiplier)))));
            }
        }
    }

    @Override
    public ResourceLocation getUid() {
        return HungerOverhaulPlugin.path("slow_animal");
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.BODY + 100;
    }
}
