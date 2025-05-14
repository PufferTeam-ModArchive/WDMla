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

public enum SlowerChickenProvider implements IEntityComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor accessor) {
        if (IguanaConfig.eggTimeoutMultiplier > 1) {
            ITooltip chickenComponent = tooltip.getChildWithTag(VanillaIDs.CHICKEN);
            if (chickenComponent != null) {
                tooltip.replaceChildWithTag(
                        VanillaIDs.CHICKEN,
                        chickenComponent.child(
                                ThemeHelper.instance().warning(
                                        StatCollector.translateToLocalFormatted(
                                                "hud.msg.wdmla.rng.multiplier",
                                                FormatUtil.STANDARD.format(IguanaConfig.eggTimeoutMultiplier)))));
            }
        }
    }

    @Override
    public ResourceLocation getUid() {
        return CommonPluginIDs.HO_SLOWER_CHICKEN;
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.BODY + 100;
    }
}
