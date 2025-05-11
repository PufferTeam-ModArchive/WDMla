package com.gtnewhorizons.wdmla.impl.ui;

import com.gtnewhorizons.wdmla.api.ui.ComponentHelper;
import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.impl.ui.component.ProgressComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.TextComponent;

public class ComponentHelperImpl implements ComponentHelper {

    public static final ComponentHelper _instance = new ComponentHelperImpl();

    private ComponentHelperImpl() {

    }

    @Override
    public IComponent text(String text) {
        return null;
    }

    @Override
    public ITooltip vertical() {
        return null;
    }

    @Override
    public ITooltip horizontal() {
        return null;
    }

    @Override
    public ITooltip progress(long current, long max, String progressText) {
        return new ProgressComponent(current, max)
                .child(new TextComponent(progressText).padding(DEFAULT_PROGRESS_DESCRIPTION_PADDING));
    }
}
