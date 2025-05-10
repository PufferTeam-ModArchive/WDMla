package com.gtnewhorizons.wdmla.impl.harvestability;

import java.util.List;
import java.util.function.Function;

import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import org.jetbrains.annotations.Nullable;

import com.gtnewhorizons.wdmla.api.harvestability.HarvestLevel;

public class HarvestLevelImpl implements HarvestLevel {

    protected final int value;
    protected @Nullable final Function<Integer, String> nameSupplier;

    public HarvestLevelImpl(int level, @Nullable Function<Integer, String> nameSupplier) {
        this.value = level;
        this.nameSupplier = nameSupplier;
    }

    public HarvestLevelImpl(HarvestLevel prevLevel, @Nullable Function<Integer, String> nameSupplier) {
        this.value = ((HarvestLevelImpl) prevLevel).value;
        this.nameSupplier = nameSupplier;
    }

    @Override
    public boolean isToolRequired() {
        return value != -1;
    }

    @Override
    public String getDisplayNum() {
        return isToolRequired() ? String.valueOf(value) : null;
    }

    @Override
    public String getName() {
        if (nameSupplier != null) {
            return nameSupplier.apply(value);
        }

        String unlocalized = "hud.msg.wdmla.harvestlevel" + (value + 1);

        if (StatCollector.canTranslate(unlocalized)) {
            return StatCollector.translateToLocal(unlocalized);
        } else {
            return String.valueOf(value);
        }
    }

    @Override
    public ItemStack getIconFromList(List<ItemStack> iconList) {
        if (iconList == null || iconList.isEmpty()) {
            return null;
        }
        if (iconList.size() <= value) {
            return iconList.get(iconList.size() - 1);
        }
        return iconList.get(value);
    }

    @Override
    public boolean doesSatisfy(HarvestLevel otherHarvestLevel) {
        return value >= ((HarvestLevelImpl) otherHarvestLevel).value;
    }
}
