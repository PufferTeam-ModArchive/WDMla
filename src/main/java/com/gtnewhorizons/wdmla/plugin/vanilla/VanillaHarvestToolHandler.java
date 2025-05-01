package com.gtnewhorizons.wdmla.plugin.vanilla;

import com.gtnewhorizons.wdmla.api.HarvestabilityInfo;
import com.gtnewhorizons.wdmla.api.HarvestabilityTestPhase;
import com.gtnewhorizons.wdmla.api.provider.InteractionHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

import static com.gtnewhorizons.wdmla.plugin.harvestability.helpers.ToolHelper.*;

public enum VanillaHarvestToolHandler implements InteractionHandler {
    INSTANCE;

    private static final HashMap<String, ItemStack> testTools = new HashMap<>();
    static {
        testTools.put(TOOL_PICKAXE, new ItemStack(Items.wooden_pickaxe));
        testTools.put(TOOL_SHOVEL, new ItemStack(Items.wooden_shovel));
        testTools.put(TOOL_AXE, new ItemStack(Items.wooden_axe));
    }

    @Override
    public void testHarvest(HarvestabilityInfo info, HarvestabilityTestPhase phase,
                            EntityPlayer player, Block block, int meta, MovingObjectPosition position) {
        if( phase == HarvestabilityTestPhase.EFFECTIVE_TOOL) {
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
    }

    @Override
    public ResourceLocation getUid() {
        return VanillaIdentifiers.HARVEST_TOOL;
    }

    @Override
    public int getDefaultPriority() {
        return 1000;
    }
}
