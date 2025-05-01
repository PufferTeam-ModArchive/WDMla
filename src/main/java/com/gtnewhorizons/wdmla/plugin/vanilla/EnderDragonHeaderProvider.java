package com.gtnewhorizons.wdmla.plugin.vanilla;

import com.gtnewhorizons.wdmla.api.TooltipPosition;
import com.gtnewhorizons.wdmla.api.accessor.EntityAccessor;
import com.gtnewhorizons.wdmla.api.provider.IEntityComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.impl.ui.ThemeHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.util.ResourceLocation;

public enum EnderDragonHeaderProvider implements IEntityComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor accessor) {
        if (accessor.getEntity() instanceof EntityDragonPart dragonPart) {
            Entity dragon = (Entity) dragonPart.entityDragonObj;
            ThemeHelper.INSTANCE.overrideEntityTooltipTitle(tooltip, dragon.getCommandSenderName(), dragon);
            ThemeHelper.INSTANCE.overrideEntityTooltipIcon(tooltip, dragon);
        }
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.HEAD;
    }

    @Override
    public ResourceLocation getUid() {
        return VanillaIdentifiers.ENDER_DRAGON_HEADER;
    }
}
