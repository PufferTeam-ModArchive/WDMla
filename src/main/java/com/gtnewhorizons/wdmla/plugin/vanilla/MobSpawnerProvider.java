package com.gtnewhorizons.wdmla.plugin.vanilla;

import com.gtnewhorizons.wdmla.api.identifier.VanillaIDs;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.provider.IServerDataProvider;
import com.gtnewhorizons.wdmla.api.provider.ITimeFormatConfigurable;
import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.api.config.WDMlaConfig;
import com.gtnewhorizons.wdmla.api.format.TimeFormattingPattern;
import com.gtnewhorizons.wdmla.api.ui.helper.ThemeHelper;

public enum MobSpawnerProvider
        implements IBlockComponentProvider, IServerDataProvider<BlockAccessor>, ITimeFormatConfigurable {

    INSTANCE;

    @Override
    public void appendTooltip(IComponent tooltip, BlockAccessor accessor) {
        if (accessor.getServerData().hasKey("delay")) {
            int delay = accessor.getServerData().getInteger("delay");
            TimeFormattingPattern timePattern = WDMlaConfig.instance().getTimeFormatter(this);
            tooltip.child(
                    ThemeHelper.instance().value(
                            StatCollector.translateToLocal("hud.msg.wdmla.delay"),
                            timePattern.tickFormatter.format(delay)).tag(VanillaIDs.MOB_SPAWNER));
        }
    }

    @Override
    public void appendServerData(NBTTagCompound data, BlockAccessor accessor) {
        if (accessor.getTileEntity() instanceof TileEntityMobSpawner spawner) {
            int delay = spawner.func_145881_a().spawnDelay;
            data.setInteger("delay", delay);
        }
    }

    @Override
    public ResourceLocation getUid() {
        return VanillaIDs.MOB_SPAWNER;
    }

    @Override
    public boolean canToggleInGui() {
        return false;
    }

    @Override
    public boolean enabledByDefault() {
        return false;
    }
}
