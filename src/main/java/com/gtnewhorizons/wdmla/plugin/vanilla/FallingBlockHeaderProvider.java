package com.gtnewhorizons.wdmla.plugin.vanilla;

import com.gtnewhorizons.wdmla.api.identifier.VanillaIDs;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import com.gtnewhorizons.wdmla.api.identifier.WDMlaIDs;
import com.gtnewhorizons.wdmla.api.TooltipPosition;
import com.gtnewhorizons.wdmla.api.accessor.EntityAccessor;
import com.gtnewhorizons.wdmla.api.provider.IEntityComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.api.ui.ThemeHelper;
import com.gtnewhorizons.wdmla.impl.ui.component.ItemComponent;

import mcp.mobius.waila.api.impl.ModuleRegistrar;
import mcp.mobius.waila.overlay.DisplayUtil;

// test command: summon FallingSand ~ ~10 ~ {TileID:137,Time:1}
public enum FallingBlockHeaderProvider implements IEntityComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor accessor) {
        if (accessor.getEntity() instanceof EntityFallingBlock falling) {
            // we don't have proper api to deal with non-raytraced block yet
            if (ModuleRegistrar.instance().hasStackProviders(falling.func_145805_f())) {
                return;
            }

            ItemStack itemForm = new ItemStack(falling.func_145805_f(), 1, falling.field_145814_a);
            tooltip.replaceChildWithTag(WDMlaIDs.ENTITY, new ItemComponent(itemForm).doDrawOverlay(false));
            ThemeHelper.instance().overrideEntityTooltipTitle(
                    tooltip,
                    String.format(
                            StatCollector.translateToLocal("hud.msg.wdmla.entity.falling"),
                            DisplayUtil.itemDisplayNameShortFormatted(itemForm)),
                    accessor.getEntity());
        }
    }

    @Override
    public ResourceLocation getUid() {
        return VanillaIDs.FALLING_BLOCK_HEADER;
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.HEAD;
    }
}
