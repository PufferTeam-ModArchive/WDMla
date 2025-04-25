package com.gtnewhorizons.wdmla.api.view;

import net.minecraft.item.ItemStack;

import org.jetbrains.annotations.Nullable;

import com.gtnewhorizons.wdmla.api.ui.IComponent;

/**
 * This is an ItemStack wrapper which is used in client Waila storage view.<br>
 * It allows ItemStack to be linked with custom amount like "1k" or custom description line.<br>
 */
public class ItemView {

    public ItemStack item;

    /**
     * Any text that should be placed at the right bottom of ItemStack
     */
    @Nullable
    public String amountText;
    /**
     * A full line description about this ItemStack
     */
    @Nullable
    public IComponent description;

    public ItemView(ItemStack item) {
        this.item = item;
    }

    public ItemView amountText(String amountText) {
        this.amountText = amountText;
        return this;
    }

    public ItemView description(IComponent description) {
        this.description = description;
        return this;
    }
}
