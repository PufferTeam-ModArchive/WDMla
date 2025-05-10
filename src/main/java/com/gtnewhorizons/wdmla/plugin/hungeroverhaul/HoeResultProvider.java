package com.gtnewhorizons.wdmla.plugin.hungeroverhaul;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.gtnewhorizons.wdmla.api.identifier.CommonPluginIDs;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import com.gtnewhorizons.wdmla.api.Mods;
import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.impl.ui.ThemeHelper;

import iguanaman.hungeroverhaul.config.IguanaConfig;
import iguanaman.hungeroverhaul.util.IguanaEventHook;
import mcp.mobius.waila.utils.WailaExceptionHandler;

/**
 * A small feature for helping new players to understand the hoe mechanic.
 */
// TODO: request seed drop chance to server
public enum HoeResultProvider implements IBlockComponentProvider {

    INSTANCE;

    private Class<?> mattockClass;
    private Method isWaterNearby;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor) {
        if (IguanaConfig.modifyHoeUse && isHoldingHoe(accessor.getPlayer().inventory.getCurrentItem())) {
            Block block = accessor.getBlock();
            if ((block == Blocks.dirt || block == Blocks.grass) && isWaterNearby(accessor)) {
                tooltip.horizontal().child(ThemeHelper.INSTANCE.smallItem(new ItemStack(Blocks.farmland)))
                        .text(StatCollector.translateToLocal("hud.msg.wdmla.near.water")).text(": ")
                        .child(ThemeHelper.INSTANCE.success(StatCollector.translateToLocal("hud.msg.wdmla.yes")));
            } else if (block == Blocks.grass && !isWaterNearby(accessor)) {
                tooltip.horizontal().child(ThemeHelper.INSTANCE.smallItem(new ItemStack(Items.wheat_seeds)))
                        .text(StatCollector.translateToLocal("hud.msg.wdmla.near.water")).text(": ")
                        .child(ThemeHelper.INSTANCE.failure(StatCollector.translateToLocal("hud.msg.wdmla.no")));
            } else {
                tooltip.horizontal().text(StatCollector.translateToLocal("hud.msg.wdmla.cannot.hoe"));
            }
        }
    }

    @Override
    public ResourceLocation getUid() {
        return CommonPluginIDs.HO_HOE_RESULT;
    }

    private boolean isHoldingHoe(ItemStack mainHand) {
        if (mainHand == null) {
            return false;
        }

        if (mainHand.getItem() instanceof ItemHoe) {
            return true;
        }

        if (Mods.TCONSTUCT.isLoaded()) {
            if (mattockClass == null) {
                try {
                    mattockClass = Class.forName("tconstruct.items.tools.Mattock");
                } catch (ClassNotFoundException e) {
                    WailaExceptionHandler.handleErr(e, getClass().getName(), null);
                }
            }

            return mattockClass.isInstance(mainHand.getItem());
        }

        return false;
    }

    private boolean isWaterNearby(BlockAccessor accessor) {
        try {
            if (isWaterNearby == null) {
                isWaterNearby = IguanaEventHook.class
                        .getDeclaredMethod("isWaterNearby", World.class, int.class, int.class, int.class);
                isWaterNearby.setAccessible(true);
            }
            return (boolean) isWaterNearby.invoke(
                    new IguanaEventHook(),
                    accessor.getWorld(),
                    accessor.getHitResult().blockX,
                    accessor.getHitResult().blockY,
                    accessor.getHitResult().blockZ);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException ignore) {
            return false;
        }
    }
}
