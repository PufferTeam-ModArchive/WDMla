package com.gtnewhorizons.wdmla.plugin.vanilla;

import com.gtnewhorizons.wdmla.api.HarvestabilityInfo;
import com.gtnewhorizons.wdmla.api.HarvestabilityTestPhase;
import com.gtnewhorizons.wdmla.api.provider.InteractionHandler;
import com.gtnewhorizons.wdmla.config.PluginsConfig;
import com.gtnewhorizons.wdmla.plugin.harvestability.helpers.BlockHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public enum VanillaHarvestToolHandler implements InteractionHandler {
    INSTANCE;

    private static final HashMap<String, ItemStack> testTools = new HashMap<>();

    public static final String TOOL_PICKAXE = "pickaxe";
    public static final String TOOL_SHOVEL = "shovel";
    public static final String TOOL_AXE = "axe";
    public static final String TOOL_SWORD = "sword";
    static {
        testTools.put(TOOL_PICKAXE, new ItemStack(Items.wooden_pickaxe));
        testTools.put(TOOL_SHOVEL, new ItemStack(Items.wooden_shovel));
        testTools.put(TOOL_AXE, new ItemStack(Items.wooden_axe));
    }

    @Override
    public void testHarvest(HarvestabilityInfo info, HarvestabilityTestPhase phase,
                            EntityPlayer player, Block block, int meta, MovingObjectPosition position) {
        if(phase == HarvestabilityTestPhase.EFFECTIVE_TOOL) {
            if (info.effectiveTool == null) {
                float hardness = block.getBlockHardness(player.worldObj, position.blockX, position.blockY, position.blockZ);
                if (hardness > 0f) {
                    for (Map.Entry<String, ItemStack> testToolEntry : testTools.entrySet()) {
                        ItemStack testTool = testToolEntry.getValue();
                        if (testTool != null && testTool.getItem() instanceof ItemTool
                                && testTool.func_150997_a(block) >= ((ItemTool) testTool.getItem()).func_150913_i()
                                .getEfficiencyOnProperMaterial()) {
                            info.effectiveTool = testToolEntry.getKey();
                            break;
                        }
                    }
                }
            }
        }
        else if (phase == HarvestabilityTestPhase.EFFECTIVE_TOOL_ICON) {
            if (info.harvestLevel != -1 && info.effectiveTool != null) {
                info.effectiveToolIcon = getEffectiveToolIcon(info.effectiveTool, info.harvestLevel);
            }
        }
        else if (phase == HarvestabilityTestPhase.ADDITIONAL_TOOLS_ICON) {
            if (PluginsConfig.harvestability.modern.modernShowShearabilityIcon) {
                Map.Entry<ItemStack, Boolean> canShear = BlockHelper.getShearability(player, block, meta, position);
                if (canShear != null) {
                    info.additionalToolsIcon.add(canShear);
                }
            }
            if (PluginsConfig.harvestability.modern.modernShowSilkTouchabilityIcon) {
                Map.Entry<ItemStack, Boolean> canSilktouch = BlockHelper.getSilktouchAbility(player, block, meta, position);
                if (canSilktouch != null) {
                    info.additionalToolsIcon.add(canSilktouch);
                }
            }
        }
    }

    @Override
    public ResourceLocation getUid() {
        return VanillaIdentifiers.HARVEST_TOOL;
    }

    @Override
    public int getDefaultPriority() {
        return 1000;
    }

    public ItemStack getEffectiveToolIcon(String effectiveTool, int harvestLevel) {
        return switch (effectiveTool) {
            case TOOL_PICKAXE -> getVanillaEffectivePickaxeIcon(harvestLevel);
            case TOOL_SHOVEL -> new ItemStack(Items.wooden_shovel);
            case TOOL_AXE -> new ItemStack(Items.wooden_axe);
            case TOOL_SWORD -> new ItemStack(Items.wooden_sword);
            default -> null;
        };
    }

    private static ItemStack getVanillaEffectivePickaxeIcon(int harvestLevel) {
        return switch (harvestLevel) {
            case 0 -> new ItemStack(Items.wooden_pickaxe);
            case 1 -> new ItemStack(Items.stone_pickaxe);
            case 2 -> new ItemStack(Items.iron_pickaxe);
            case 3 -> new ItemStack(Items.diamond_pickaxe);
            default -> null;
        };
    }
}
