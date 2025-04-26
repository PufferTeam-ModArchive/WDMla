package com.gtnewhorizons.wdmla.plugin.hungeroverhaul;

import com.gtnewhorizons.wdmla.api.accessor.EntityAccessor;
import com.gtnewhorizons.wdmla.api.provider.IEntityComponentProvider;
import com.gtnewhorizons.wdmla.api.provider.IServerDataProvider;
import com.gtnewhorizons.wdmla.api.provider.ITimeFormatConfigurable;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.config.WDMlaConfig;
import com.gtnewhorizons.wdmla.impl.format.TimeFormattingPattern;
import com.gtnewhorizons.wdmla.impl.ui.ThemeHelper;
import iguanaman.hungeroverhaul.config.IguanaConfig;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public enum MilkCooldownProvider implements IEntityComponentProvider, IServerDataProvider<EntityAccessor>, ITimeFormatConfigurable {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor accessor) {
        TimeFormattingPattern formatter = WDMlaConfig.instance().getTimeFormatter(this);
        if(IguanaConfig.milkedTimeout > 1 && accessor.getServerData().hasKey("Milked") && accessor.getEntity() != null) {
            tooltip.child(
                    ThemeHelper.INSTANCE.value(StatCollector.translateToLocal("hud.msg.wdmla.milk.cooldown"),
                    formatter.tickFormatter.apply(
                            accessor.getServerData().getInteger("Milked") * 20 + (int) (20 - accessor.getEntity().worldObj.getTotalWorldTime() % 20))));
        }
    }

    @Override
    public void appendServerData(NBTTagCompound data, EntityAccessor accessor) {
        if(IguanaConfig.milkedTimeout > 1 && accessor.getEntity().getEntityData().hasKey("Milked")) {
            data.setInteger("Milked", accessor.getEntity().getEntityData().getInteger("Milked"));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return HungerOverhaulPlugin.path("milk_cooldown");
    }

    @Override
    public TimeFormattingPattern getDefaultTimeFormatter() {
        return TimeFormattingPattern.HOUR_MIN_SEC;
    }
}
