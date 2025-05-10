package com.gtnewhorizons.wdmla.api.ui;

import com.gtnewhorizons.wdmla.impl.ui.ThemeHelperImpl;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface ThemeHelper {

    static ThemeHelper instance() {
        return ThemeHelperImpl._instance;
    }

    @Deprecated
    void overrideTooltipIcon(ITooltip root, ItemStack newItemStack);

    void overrideTooltipIcon(ITooltip root, ItemStack newItemStack, boolean overrideFancyRenderer);

    void overrideTooltipTitle(ITooltip root, ItemStack newItemStack);

    void overrideTooltipTitle(ITooltip root, String formattedNewName);

    void overrideEntityTooltipTitle(ITooltip root, String newName, @Nullable Entity entityMayHaveCustomName);

    void overrideEntityTooltipIcon(ITooltip root, @Nullable Entity newEntity);

    void overrideTooltipModName(ITooltip root, ItemStack newItemStack);

    void overrideTooltipModName(ITooltip root, String newName);

    void overrideTooltipHeader(ITooltip root, ItemStack newItemStack);

    IComponent info(String content);

    IComponent title(String content);

    IComponent success(String content);

    IComponent warning(String content);

    IComponent danger(String content);

    IComponent failure(String content);

    IComponent color(String content, MessageType type);

    IComponent furnaceLikeProgress(List<ItemStack> input, List<ItemStack> output, int currentProgress,
                                   int maxProgress, boolean showDetails);

    /**
     * Provides Minecraft furnace progress arrow and item display.
     *
     * @param input             the items on the left side of arrow
     * @param output            the items on the right side of arrow
     * @param currentProgress   ticks elapsed
     * @param maxProgress       the length of ticks to fill the arrow
     * @param showDetails       is Show Details button pressed or not (for controlling legacy text)
     * @param legacyProcessText The text displayed instead of arrow and ItemStacks in legacy mode. If null, it will be
     *                          auto generated.
     * @return built component
     */
    IComponent furnaceLikeProgress(List<ItemStack> input, List<ItemStack> output, int currentProgress,
                                   int maxProgress, boolean showDetails, @Nullable IComponent legacyProcessText);

    IComponent value(String entry, String value);

    /**
     * Provides an ItemComponent with size of default text height
     *
     * @param itemStack Base ItemStack to display
     */
    ITooltip smallItem(ItemStack itemStack);

    /**
     * Constructs a component to display an ItemStack in "(icon) 3x Apple" format
     */
    IComponent itemStackFullLine(ItemStack stack);

    /**
     * display any crop's growth value with percentage
     *
     * @param growthValue growth value (0 ~ 1)
     */
    IComponent growthValue(float growthValue);
}
