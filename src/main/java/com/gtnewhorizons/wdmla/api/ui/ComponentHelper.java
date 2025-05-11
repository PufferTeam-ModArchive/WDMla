package com.gtnewhorizons.wdmla.api.ui;

import com.gtnewhorizons.wdmla.api.ui.sizer.IPadding;
import com.gtnewhorizons.wdmla.impl.ui.ComponentHelperImpl;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Padding;

public interface ComponentHelper {

    IPadding DEFAULT_PROGRESS_DESCRIPTION_PADDING = new Padding(2, 1, 2, 3);

    static ComponentHelper instance() {
        return ComponentHelperImpl._instance;
    }

    IComponent text(String text);

    ITooltip vertical();

    ITooltip horizontal();

    ITooltip progress(long current, long max, String progressText);
}
