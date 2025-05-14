package com.gtnewhorizons.wdmla.plugin.hungeroverhaul;

import com.gtnewhorizons.wdmla.api.identifier.CommonPluginIDs;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import com.gtnewhorizons.wdmla.api.TooltipPosition;
import com.gtnewhorizons.wdmla.api.accessor.EntityAccessor;
import com.gtnewhorizons.wdmla.api.provider.IEntityComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.api.ui.helper.ThemeHelper;
import com.gtnewhorizons.wdmla.api.identifier.VanillaIDs;
import com.gtnewhorizons.wdmla.util.FormatUtil;

import iguanaman.hungeroverhaul.config.IguanaConfig;

public enum SlowerAnimalProvider implements IEntityComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor accessor) {
        if (IguanaConfig.breedingTimeoutMultiplier > 1) {
            ITooltip breedComponent = tooltip.getChildWithTag(VanillaIDs.ANIMAL_BREED);
            if (breedComponent != null) {
                tooltip.replaceChildWithTag(
                        VanillaIDs.ANIMAL_BREED,
                        breedComponent.child(
                                ThemeHelper.instance().warning(
                                        StatCollector.translateToLocalFormatted(
                                                "hud.msg.wdmla.rng.multiplier",
                                                FormatUtil.STANDARD.format(IguanaConfig.breedingTimeoutMultiplier)))));
            }
        }

        if (IguanaConfig.childDurationMultiplier > 1) {
            ITooltip growthComponent = tooltip.getChildWithTag(VanillaIDs.ANIMAL_GROWTH);
            if (growthComponent != null) {
                tooltip.replaceChildWithTag(
                        VanillaIDs.ANIMAL_GROWTH,
                        growthComponent.child(
                                ThemeHelper.instance().warning(
                                        StatCollector.translateToLocalFormatted(
                                                "hud.msg.wdmla.rng.multiplier",
                                                FormatUtil.STANDARD.format(IguanaConfig.childDurationMultiplier)))));
            }
        }
    }

    @Override
    public ResourceLocation getUid() {
        return CommonPluginIDs.HO_SLOWER_ANIMAL;
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.BODY + 100;
    }
}
