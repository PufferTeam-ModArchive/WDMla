package com.gtnewhorizons.wdmla.plugin.core;

import static mcp.mobius.waila.api.SpecialChars.*;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

import com.gtnewhorizons.wdmla.api.identifier.WDMlaIDs;
import com.gtnewhorizons.wdmla.api.TooltipPosition;
import com.gtnewhorizons.wdmla.api.accessor.EntityAccessor;
import com.gtnewhorizons.wdmla.api.provider.IEntityComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.api.ui.MessageType;
import com.gtnewhorizons.wdmla.api.config.General;
import com.gtnewhorizons.wdmla.api.config.PluginsConfig;
import com.gtnewhorizons.wdmla.api.ui.helper.ThemeHelper;
import com.gtnewhorizons.wdmla.impl.ui.component.EntityComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.HPanelComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.TextComponent;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Padding;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Size;
import com.gtnewhorizons.wdmla.impl.ui.style.TextStyle;
import com.gtnewhorizons.wdmla.util.FormatUtil;

import mcp.mobius.waila.utils.ModIdentification;

public enum DefaultEntityInfoProvider implements IEntityComponentProvider {

    INSTANCE;

    @Override
    public ResourceLocation getUid() {
        return WDMlaIDs.DEFAULT_ENTITY;
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.DEFAULT_INFO;
    }

    @Override
    public boolean isPriorityFixed() {
        return true;
    }

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor accessor) {
        ITooltip row = tooltip.horizontal();
        if (PluginsConfig.core.defaultEntity.showEntity) {
            if (!PluginsConfig.core.defaultEntity.fancyRenderer && !(accessor.getEntity() instanceof EntityLiving)) {
                row.child(new HPanelComponent().tag(WDMlaIDs.ENTITY));
            } else {
                row.child(
                        new EntityComponent(accessor.getEntity()).padding(new Padding(6, 0, 10, 0))
                                .size(new Size(12, 12)).tag(WDMlaIDs.ENTITY));
            }
        }

        ITooltip row_vertical = row.vertical();
        if (PluginsConfig.core.defaultEntity.showEntityName) {
            String name;
            if (accessor.getEntity() instanceof EntityLiving living && living.hasCustomNameTag()
                    && General.customNameOverride) {
                name = FormatUtil.formatNameByPixelCount(living.getCustomNameTag());
            } else {
                name = FormatUtil.formatNameByPixelCount(accessor.getEntity().getCommandSenderName());
            }
            row_vertical.child(ThemeHelper.instance().title(name).tag(WDMlaIDs.ENTITY_NAME));
        }
        if (PluginsConfig.core.defaultEntity.showModName) {
            row_vertical.child(
                    new TextComponent(ITALIC + ModIdentification.nameFromEntity(accessor.getEntity()))
                            .style(new TextStyle().color(General.currentTheme.get().textColor(MessageType.MOD_NAME)))
                            .tag(WDMlaIDs.MOD_NAME));
        }
    }
}
