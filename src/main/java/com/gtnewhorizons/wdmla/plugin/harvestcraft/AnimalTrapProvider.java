package com.gtnewhorizons.wdmla.plugin.harvestcraft;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.provider.IServerDataProvider;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.impl.ui.StatusHelper;
import com.pam.harvestcraft.ItemRegistry;
import com.pam.harvestcraft.TileEntityPamAnimalTrap;

public enum AnimalTrapProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor) {
        if (!accessor.getServerData().hasKey("GoodEnvironment")) {
            return;
        }

        if (!accessor.getServerData().getBoolean("GoodEnvironment")) {
            tooltip.child(StatusHelper.INSTANCE.structureIncomplete());
            String material = StatCollector.translateToLocal("hud.msg.wdmla.dirt.or.grass");
            tooltip.text(StatCollector.translateToLocalFormatted("hud.msg.wdmla.must.surround", material));
        } else if (!accessor.getServerData().getBoolean("IngredientValid")) {
            tooltip.child(StatusHelper.INSTANCE.idle());
        } else {
            tooltip.child(StatusHelper.INSTANCE.runningFine());
        }
    }

    @Override
    public void appendServerData(NBTTagCompound data, BlockAccessor accessor) {
        if (accessor.getTileEntity() instanceof TileEntityPamAnimalTrap trap) {
            data.setBoolean("GoodEnvironment", trap.countFlowers() >= 5);
            data.setBoolean("IngredientValid", isIngredientValid(trap.getStackInSlot(18)));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return HarvestcraftPlugin.path("animal_trap");
    }

    private boolean isIngredientValid(ItemStack baitSlotItemStack) {
        if (baitSlotItemStack == null) {
            return false;
        } else {
            return (baitSlotItemStack.getItem() == ItemRegistry.grainbaitItem
                    || baitSlotItemStack.getItem() == ItemRegistry.fruitbaitItem
                    || baitSlotItemStack.getItem() == ItemRegistry.veggiebaitItem);
        }
    }
}
