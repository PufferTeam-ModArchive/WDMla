package com.gtnewhorizons.wdmla.plugin.core;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import com.gtnewhorizons.wdmla.api.identifier.WDMlaIDs;
import com.gtnewhorizons.wdmla.api.TooltipPosition;
import com.gtnewhorizons.wdmla.api.accessor.EntityAccessor;
import com.gtnewhorizons.wdmla.api.provider.IEntityComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.ArmorComponent;

public enum EntityArmorProvider implements IEntityComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(IComponent tooltip, EntityAccessor accessor) {
        float armorValue = ((EntityLiving) accessor.getEntity()).getTotalArmorValue() / 2.0f;
        if (armorValue > 0) {
            if (accessor.getEntity() instanceof EntityPlayer) {
                tooltip.child(new ArmorComponent(armorValue, 10)).tag(WDMlaIDs.ARMOR);
            } else {
                // in vanilla Minecraft, armor point past than 20 has no effect
                // TODO:support https://github.com/GTNewHorizons/OverloadedArmorBar
                tooltip.child(new ArmorComponent(armorValue, Math.min(armorValue, 10))).tag(WDMlaIDs.ARMOR);
            }
        }
    }

    @Override
    public ResourceLocation getUid() {
        return WDMlaIDs.ARMOR;
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.HEAD;
    }
}
