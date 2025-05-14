package com.gtnewhorizons.wdmla.api.ui.helper;

import com.gtnewhorizons.wdmla.api.ui.HighlightState;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.impl.ui.helper.StatusHelperImpl;
import net.minecraft.client.Minecraft;

public interface StatusHelper {

    static StatusHelper instance() {
        return StatusHelperImpl._instance;
    }

    int ICON_SIZE = Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;

    ITooltip structureIncomplete();

    ITooltip hasProblem();

    ITooltip runningFine();

    ITooltip idle();

    ITooltip workingDisabled();

    ITooltip insufficientEnergy();

    ITooltip insufficientFuel();

    ITooltip locked();

    ITooltip locked(HighlightState highlightState);

    /**
     * Indicates the input item/fluid is voided on overflow. This concept is shared between multiple storage mods
     * (drawers, barrels, super chests...)
     */
    ITooltip voidOverflow();

    ITooltip voidOverflow(HighlightState highlightState);
}
