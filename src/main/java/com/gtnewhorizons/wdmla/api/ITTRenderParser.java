package com.gtnewhorizons.wdmla.api;

import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import org.jetbrains.annotations.ApiStatus;

/**
 * Waila compatibility interface which converts encoded strings into component.<br>
 * In most cases you don't want to bother with it.
 */
@ApiStatus.Internal
public interface ITTRenderParser {

    /**
     * Parse renderer builder provided by custom regex which is part of legacy Waila api
     *
     * @param args string begins with custom regex
     * @return corresponding WDMla component
     */
    ITooltip parse(String[] args);
}
