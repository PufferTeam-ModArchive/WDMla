package com.gtnewhorizons.wdmla.plugin.vanilla;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.provider.IServerDataProvider;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.impl.ui.StatusHelper;
import com.gtnewhorizons.wdmla.impl.ui.ThemeHelper;

public enum BeaconProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor) {
        if (!accessor.getServerData().hasKey("Formed")) {
            return;
        }

        if (!accessor.getServerData().getBoolean("Formed")) {
            tooltip.child(StatusHelper.INSTANCE.structureIncomplete());
            return;
        }

        int levels = accessor.getServerData().getInteger("Levels");
        tooltip.child(
                ThemeHelper.INSTANCE
                        .value(StatCollector.translateToLocal("hud.msg.wdmla.level"), String.valueOf(levels)));

        int primary = accessor.getServerData().getInteger("Primary");
        if (primary > 0) {
            tooltip.child(
                    ThemeHelper.INSTANCE.value(
                            StatCollector.translateToLocal("hud.msg.wdmla.primary"),
                            StatCollector.translateToLocal(Potion.potionTypes[primary].getName())));
        }

        int secondary = accessor.getServerData().getInteger("Secondary");
        if (secondary > 0) {
            tooltip.child(
                    ThemeHelper.INSTANCE.value(
                            StatCollector.translateToLocal("hud.msg.wdmla.secondary"),
                            StatCollector.translateToLocal(Potion.potionTypes[secondary].getName())));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return VanillaIdentifiers.BEACON;
    }

    @Override
    public void appendServerData(NBTTagCompound data, BlockAccessor accessor) {
        if (accessor.getTileEntity() instanceof TileEntityBeacon beacon) {
            data.setBoolean("Formed", beacon.func_146002_i() > 0.0f);
            data.setInteger("Levels", beacon.getLevels());
            data.setInteger("Primary", beacon.getPrimaryEffect());
            data.setInteger("Secondary", beacon.getSecondaryEffect());
        }
    }
}
