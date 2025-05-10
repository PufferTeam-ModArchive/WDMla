package com.gtnewhorizons.wdmla.plugin.vanilla;

import com.gtnewhorizons.wdmla.api.identifier.VanillaIDs;
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

public enum PrimedTNTProvider
        implements IEntityComponentProvider, IServerDataProvider<EntityAccessor>, ITimeFormatConfigurable {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor accessor) {
        TimeFormattingPattern timePattern = WDMlaConfig.instance().getTimeFormatter(this);
        tooltip.child(
                ThemeHelper.instance()
                        .value(
                                StatCollector.translateToLocal("hud.msg.wdmla.fuse"),
                                timePattern.tickFormatter.format((int) accessor.getServerData().getByte("Fuse")))
                        .tag(VanillaIDs.PRIMED_TNT));
    }

    @Override
    public void appendServerData(NBTTagCompound data, EntityAccessor accessor) {
        accessor.getEntity().writeToNBT(data);
    }

    @Override
    public ResourceLocation getUid() {
        return VanillaIDs.PRIMED_TNT;
    }
}
