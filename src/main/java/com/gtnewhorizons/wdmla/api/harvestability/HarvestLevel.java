package com.gtnewhorizons.wdmla.api.harvestability;

import java.util.List;
import java.util.function.Function;

import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.Nullable;

import com.gtnewhorizons.wdmla.impl.harvestability.HarvestLevelImpl;

public interface HarvestLevel {

    HarvestLevel NO_TOOL = HarvestLevel.of(-1);

    static HarvestLevel of(int level) {
        return fromNameRule(level, null);
    }

    static HarvestLevel fromNameRule(int level, @Nullable Function<Integer, String> nameSupplier) {
        return new HarvestLevelImpl(level, nameSupplier);
    }

    static HarvestLevel newNameRule(HarvestLevel prevLevel, Function<Integer, String> nameSupplier) {
        return new HarvestLevelImpl(prevLevel, nameSupplier);
    }

    boolean isToolRequired();

    String getDisplayNum();

    String getName();

    ItemStack getIconFromList(List<ItemStack> iconList);

    boolean doesSatisfy(HarvestLevel otherHarvestLevel);
}
