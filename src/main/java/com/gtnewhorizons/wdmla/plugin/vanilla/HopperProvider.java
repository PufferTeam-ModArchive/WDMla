package com.gtnewhorizons.wdmla.plugin.vanilla;

import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.provider.IServerDataProvider;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.impl.ui.StatusHelper;
import net.minecraft.block.BlockHopper;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

//TODO: facing
public enum HopperProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor) {
        if (accessor.getBlock() instanceof BlockHopper
        && accessor.getServerData().getBoolean("Locked")) {
            tooltip.child(StatusHelper.INSTANCE.locked());
        }
    }

    @Override
    public ResourceLocation getUid() {
        return VanillaIdentifiers.HOPPER;
    }

    @Override
    public void appendServerData(NBTTagCompound data, BlockAccessor accessor) {
        if (accessor.getTileEntity() != null) {
            data.setBoolean("Locked", !BlockHopper.func_149917_c(accessor.getTileEntity().getBlockMetadata()));
        }
    }
}
