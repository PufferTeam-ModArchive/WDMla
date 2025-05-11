package com.gtnewhorizons.wdmla.api.ui;

import com.gtnewhorizons.wdmla.impl.ui.StatusHelperImpl;
import net.minecraft.client.Minecraft;

public interface StatusHelper {

    static StatusHelper instance() {
        return StatusHelperImpl._instance;
    }

    int ICON_SIZE = Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;

    IComponent structureIncomplete();

    IComponent hasProblem();

    IComponent runningFine();

    IComponent idle();

    IComponent workingDisabled();

    IComponent insufficientEnergy();

    IComponent insufficientFuel();

    IComponent locked();

    IComponent locked(HighlightState highlightState);

    /**
     * Indicates the input item/fluid is voided on overflow. This concept is shared between multiple storage mods
     * (drawers, barrels, super chests...)
     */
    IComponent voidOverflow();

    IComponent voidOverflow(HighlightState highlightState);
}
