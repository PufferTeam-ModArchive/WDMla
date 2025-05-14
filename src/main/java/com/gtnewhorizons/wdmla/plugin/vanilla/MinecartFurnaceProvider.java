package com.gtnewhorizons.wdmla.plugin.vanilla;

import com.gtnewhorizons.wdmla.api.identifier.VanillaIDs;
import net.minecraft.entity.item.EntityMinecartFurnace;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import com.gtnewhorizons.wdmla.api.accessor.EntityAccessor;
import com.gtnewhorizons.wdmla.api.provider.IEntityComponentProvider;
import com.gtnewhorizons.wdmla.api.provider.IServerDataProvider;
import com.gtnewhorizons.wdmla.api.provider.ITimeFormatConfigurable;
import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.api.config.WDMlaConfig;
import com.gtnewhorizons.wdmla.api.format.TimeFormattingPattern;
import com.gtnewhorizons.wdmla.api.ui.helper.ThemeHelper;

public enum MinecartFurnaceProvider
        implements IEntityComponentProvider, IServerDataProvider<EntityAccessor>, ITimeFormatConfigurable {

    INSTANCE;

    @Override
    public void appendTooltip(IComponent tooltip, EntityAccessor accessor) {
        int fuel = accessor.getServerData().getInteger("Fuel");
        if (fuel > 0) {
            TimeFormattingPattern timePattern = WDMlaConfig.instance().getTimeFormatter(this);
            tooltip.child(
                    ThemeHelper.instance().value(
                            StatCollector.translateToLocal("hud.msg.wdmla.remaining.time"),
                            timePattern.tickFormatter.format(fuel)));
        }
    }

    @Override
    public void appendServerData(NBTTagCompound data, EntityAccessor accessor) {
        if (accessor.getEntity() instanceof EntityMinecartFurnace furnaceCart) {
            data.setInteger("Fuel", furnaceCart.fuel);
        }
    }

    @Override
    public ResourceLocation getUid() {
        return VanillaIDs.MINECART_FURNACE;
    }

    @Override
    public TimeFormattingPattern getDefaultTimeFormatter() {
        return TimeFormattingPattern.ALWAYS_SECOND;
    }
}
