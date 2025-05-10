package com.gtnewhorizons.wdmla.impl.harvestability;

import java.util.List;
import java.util.Objects;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import org.jetbrains.annotations.Nullable;

import com.gtnewhorizons.wdmla.api.harvestability.EffectiveTool;
import com.gtnewhorizons.wdmla.api.harvestability.HarvestLevel;

import joptsimple.internal.Strings;

public class EffectiveToolImpl implements EffectiveTool {

    protected final String value;
    /**
     * icons of the tool indexed by harvest level (0~)
     */
    private final @Nullable List<ItemStack> iconList;

    public EffectiveToolImpl(String name, @Nullable List<ItemStack> iconList) {
        this.value = name;
        this.iconList = iconList;
    }

    @Override
    public boolean isValid() {
        return !Strings.isNullOrEmpty(value) && value.length() > 1;
    }

    @Override
    public HarvestLevel getHarvestLevel(Block block, int meta) {
        int rawLevel = block.getHarvestLevel(meta);
        if (isValid() && rawLevel < 0) rawLevel = 0;
        return HarvestLevel.of(rawLevel);
    }

    @Override
    public boolean isToolInstance(ItemStack tool) {
        return tool.getItem().getToolClasses(tool).contains(value);
    }

    @Override
    public boolean isSameTool(EffectiveTool anotherTool) {
        return Objects.equals(value, ((EffectiveToolImpl) anotherTool).value);
    }

    @Override
    public boolean hasIconRegistered() {
        return iconList != null;
    }

    @Override
    public ItemStack getIcon(HarvestLevel harvestLevel) {
        if (!harvestLevel.isToolRequired() || !isValid()) {
            return null;
        } else {
            ItemStack icon = harvestLevel.getIconFromList(iconList);
            if (icon == null && !isSameTool(CANNOT_HARVEST)) {
                icon = new ItemStack(Blocks.iron_bars);
            }
            return icon;
        }
    }

    @Override
    public String getLocalizedName() {
        if (StatCollector.canTranslate("hud.msg.wdmla.toolclass." + value)) {
            return StatCollector.translateToLocal("hud.msg.wdmla.toolclass." + value);
        } else if (isValid()) {
            return value.substring(0, 1).toUpperCase() + value.substring(1);
        } else {
            return null;
        }
    }
}
