package com.gtnewhorizons.wdmla.plugin.hungeroverhaul;

import com.gtnewhorizons.wdmla.api.identifier.CommonPluginIDs;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import com.gtnewhorizons.wdmla.api.accessor.EntityAccessor;
import com.gtnewhorizons.wdmla.api.provider.IEntityComponentProvider;
import com.gtnewhorizons.wdmla.api.provider.IServerDataProvider;
import com.gtnewhorizons.wdmla.api.provider.ITimeFormatConfigurable;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.api.config.WDMlaConfig;
import com.gtnewhorizons.wdmla.api.format.TimeFormattingPattern;
import com.gtnewhorizons.wdmla.api.ui.ThemeHelper;

import iguanaman.hungeroverhaul.config.IguanaConfig;

public enum MilkCooldownProvider
        implements IEntityComponentProvider, IServerDataProvider<EntityAccessor>, ITimeFormatConfigurable {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor accessor) {
        TimeFormattingPattern formatter = WDMlaConfig.instance().getTimeFormatter(this);
        if (IguanaConfig.milkedTimeout > 1 && accessor.getServerData().hasKey("Milked")
                && accessor.getEntity() != null) {
            tooltip.child(
                    ThemeHelper.instance().value(
                            StatCollector.translateToLocal("hud.msg.wdmla.milk.cooldown"),
                            formatter.tickFormatter.format(
                                    accessor.getServerData().getInteger("Milked") * 20
                                            + (int) (20 - accessor.getEntity().worldObj.getTotalWorldTime() % 20))));
        }
    }

    @Override
    public void appendServerData(NBTTagCompound data, EntityAccessor accessor) {
        if (IguanaConfig.milkedTimeout > 1 && accessor.getEntity().getEntityData().hasKey("Milked")) {
            data.setInteger("Milked", accessor.getEntity().getEntityData().getInteger("Milked"));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return CommonPluginIDs.HO_MILK_COOLDOWN;
    }

    @Override
    public TimeFormattingPattern getDefaultTimeFormatter() {
        return TimeFormattingPattern.HOUR_MIN_SEC;
    }
}
