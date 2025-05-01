package com.gtnewhorizons.wdmla.plugin.harvestability.helpers;

import static com.gtnewhorizons.wdmla.plugin.harvestability.helpers.ToolHelper.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.config.PluginsConfig;
import com.gtnewhorizons.wdmla.impl.ui.ThemeHelper;
import com.gtnewhorizons.wdmla.plugin.harvestability.proxy.ProxyGregTech;

public class BlockHelper {

    public static IComponent getShearabilityString(EntityPlayer player, Block block, int meta,
            MovingObjectPosition position) {
        boolean isSneaking = player.isSneaking();
        boolean showShearability = PluginsConfig.harvestability.legacy.shearability
                && (!PluginsConfig.harvestability.legacy.shearabilitySneakingOnly || isSneaking);

        if (showShearability && (block instanceof IShearable || block == Blocks.deadbush
                || (block == Blocks.double_plant && block.getItemDropped(meta, new Random(), 0) == null))) {
            ItemStack itemHeld = player.getHeldItem();
            boolean isHoldingShears = itemHeld != null && itemHeld.getItem() instanceof ItemShears;
            boolean isShearable = isHoldingShears && ((IShearable) block)
                    .isShearable(itemHeld, player.worldObj, position.blockX, position.blockY, position.blockZ);
            String shearableString = PluginsConfig.harvestability.legacy.shearabilityString;
            return isShearable ? ThemeHelper.INSTANCE.success(shearableString)
                    : ThemeHelper.INSTANCE.failure(shearableString);
        }
        return null;
    }

    public static IComponent getSilkTouchabilityString(EntityPlayer player, Block block, int meta,
            MovingObjectPosition position) {
        boolean isSneaking = player.isSneaking();
        boolean showSilkTouchability = PluginsConfig.harvestability.legacy.silkTouchability
                && (!PluginsConfig.harvestability.legacy.silkTouchabilitySneakingOnly || isSneaking);

        if (showSilkTouchability && block
                .canSilkHarvest(player.worldObj, player, position.blockX, position.blockY, position.blockZ, meta)) {
            Item itemDropped = block.getItemDropped(meta, new Random(), 0);
            boolean silkTouchMatters = (itemDropped instanceof ItemBlock && itemDropped != Item.getItemFromBlock(block))
                    || block.quantityDropped(new Random()) <= 0;
            if (silkTouchMatters) {
                boolean hasSilkTouch = EnchantmentHelper.getSilkTouchModifier(player);
                String silkTouchString = PluginsConfig.harvestability.legacy.silkTouchabilityString;
                return hasSilkTouch ? ThemeHelper.INSTANCE.success(silkTouchString)
                        : ThemeHelper.INSTANCE.failure(silkTouchString);
            }
        }
        return null;
    }
}
