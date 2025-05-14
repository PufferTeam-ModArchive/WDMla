package com.gtnewhorizons.wdmla.plugin.vanilla;

import com.gtnewhorizons.wdmla.api.identifier.VanillaIDs;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.util.ResourceLocation;

import com.gtnewhorizons.wdmla.api.accessor.EntityAccessor;
import com.gtnewhorizons.wdmla.api.provider.IEntityComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.IComponent;

public enum PaintingProvider implements IEntityComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(IComponent tooltip, EntityAccessor accessor) {
        if (accessor.getEntity() instanceof EntityPainting painting) {
            tooltip.text(painting.art.title); // Hope nobody wants translation of this...
        }
    }

    @Override
    public ResourceLocation getUid() {
        return VanillaIDs.PAINTING;
    }
}
