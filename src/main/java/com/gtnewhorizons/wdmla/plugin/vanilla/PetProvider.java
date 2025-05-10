package com.gtnewhorizons.wdmla.plugin.vanilla;

import java.util.UUID;

import com.gtnewhorizons.wdmla.api.identifier.VanillaIDs;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.UsernameCache;

import com.google.common.base.Objects;
import com.gtnewhorizons.wdmla.api.accessor.EntityAccessor;
import com.gtnewhorizons.wdmla.api.provider.IEntityComponentProvider;
import com.gtnewhorizons.wdmla.api.provider.IServerDataProvider;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.api.config.PluginsConfig;
import com.gtnewhorizons.wdmla.api.ui.ThemeHelper;

import joptsimple.internal.Strings;

public enum PetProvider implements IEntityComponentProvider, IServerDataProvider<EntityAccessor> {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, EntityAccessor accessor) {
        if (accessor.getEntity() instanceof EntityTameable pet && pet.isTamed()
                && PluginsConfig.vanilla.pet.showPetSit) {
            tooltip.child(
                    ThemeHelper.instance()
                            .value(
                                    StatCollector.translateToLocal("hud.msg.wdmla.sitting"),
                                    pet.isSitting() ? StatCollector.translateToLocal("hud.msg.wdmla.yes")
                                            : StatCollector.translateToLocal("hud.msg.wdmla.no"))
                            .tag(VanillaIDs.PET));
        }

        String ownerUUID = accessor.getServerData().getString("OwnerUUID");
        if (Objects.equal(ownerUUID, Strings.EMPTY) || !PluginsConfig.vanilla.pet.showPetOwner) {
            return;
        }

        String ownerString = UsernameCache.getLastKnownUsername(UUID.fromString(ownerUUID));
        if (Objects.equal(ownerString, Strings.EMPTY)) {
            return;
        }

        tooltip.child(
                ThemeHelper.instance().value(StatCollector.translateToLocal("hud.msg.wdmla.owner"), ownerString)
                        .tag(VanillaIDs.PET));
    }

    @Override
    public ResourceLocation getUid() {
        return VanillaIDs.PET;
    }

    @Override
    public void appendServerData(NBTTagCompound data, EntityAccessor accessor) {
        accessor.getEntity().writeToNBT(data);
    }
}
