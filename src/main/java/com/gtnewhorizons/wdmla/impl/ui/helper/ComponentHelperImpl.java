package com.gtnewhorizons.wdmla.impl.ui.helper;

import com.gtnewhorizons.wdmla.api.ui.helper.ComponentHelper;
import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.impl.ui.component.HPanelComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.ProgressComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.TextComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.VPanelComponent;

public class ComponentHelperImpl implements ComponentHelper {

    public static final ComponentHelper _instance = new ComponentHelperImpl();

    private ComponentHelperImpl() {

    }

    @Override
    public IComponent text(String text) {
        return new TextComponent(text);
    }

    @Override
    public ITooltip vertical() {
        return new VPanelComponent();
    }

    @Override
    public ITooltip horizontal() {
        return new HPanelComponent();
    }

    @Override
    public ITooltip progress(long current, long max, String progressText) {
        return new ProgressComponent(current, max)
                .child(new TextComponent(progressText).padding(DEFAULT_PROGRESS_DESCRIPTION_PADDING));
    }
}
