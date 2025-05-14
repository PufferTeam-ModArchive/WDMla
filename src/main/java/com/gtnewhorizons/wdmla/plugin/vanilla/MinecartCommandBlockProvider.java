package com.gtnewhorizons.wdmla.plugin.vanilla;

import com.gtnewhorizons.wdmla.api.identifier.VanillaIDs;
import net.minecraft.entity.EntityMinecartCommandBlock;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;

import org.apache.commons.lang3.StringUtils;

import com.gtnewhorizons.wdmla.api.accessor.EntityAccessor;
import com.gtnewhorizons.wdmla.api.provider.IEntityComponentProvider;
import com.gtnewhorizons.wdmla.api.provider.IServerDataProvider;
import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.api.config.PluginsConfig;

public enum MinecartCommandBlockProvider implements IEntityComponentProvider, IServerDataProvider<EntityAccessor> {

    INSTANCE;

    @Override
    public void appendTooltip(IComponent tooltip, EntityAccessor accessor) {
        if (accessor.getEntity() instanceof EntityMinecartCommandBlock) {
            String command = accessor.getServerData().getString("command");
            if (StringUtils.isBlank(command)) {
                return;
            }

            tooltip.text("> " + command);
        }
    }

    @Override
    public void appendServerData(NBTTagCompound data, EntityAccessor accessor) {
        if (accessor.getEntity() instanceof EntityMinecartCommandBlock commandBlock
                && MinecraftServer.getServer().isCommandBlockEnabled()
                && accessor.getPlayer().canCommandSenderUseCommand(2, "")) {
            String command = commandBlock.func_145822_e().func_145753_i();
            int maxLength = PluginsConfig.vanilla.commandBlock.maxCommandLength;
            if (command.length() > maxLength) {
                command = command.substring(0, maxLength - 3) + "...";
            }
            data.setString("command", command);
        }
    }

    @Override
    public ResourceLocation getUid() {
        return VanillaIDs.MINECART_COMMAND_BLOCK;
    }
}
