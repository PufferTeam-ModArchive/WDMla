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

public enum SlowChickenProvider implements IEntityComponentProvider {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor accessor) {
        if(IguanaConfig.eggTimeoutMultiplier > 1) {
            IComponent chickenComponent = tooltip.getChildWithTag(VanillaIdentifiers.CHICKEN);
            if(chickenComponent instanceof ITooltip chicken) {
                tooltip.replaceChildWithTag(VanillaIdentifiers.CHICKEN,
                        chicken.child(
                                ThemeHelper.INSTANCE.warning(
                                        StatCollector.translateToLocalFormatted("hud.msg.wdmla.rng.multiplier",
                                                FormatUtil.STANDARD.format(IguanaConfig.eggTimeoutMultiplier)))));
            }
        }
    }

    @Override
    public ResourceLocation getUid() {
        return HungerOverhaulPlugin.path("slow_chicken");
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.BODY + 100;
    }
}
