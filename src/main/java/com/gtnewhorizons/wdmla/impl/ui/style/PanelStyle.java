package com.gtnewhorizons.wdmla.impl.ui.style;

import com.gtnewhorizons.wdmla.api.ui.ComponentAlignment;
import com.gtnewhorizons.wdmla.api.ui.style.IPanelStyle;
import com.gtnewhorizons.wdmla.config.General;

public class PanelStyle implements IPanelStyle {

    private ComponentAlignment alignment;
    private float spacing;

    public PanelStyle(int spacing) {
        this.alignment = ComponentAlignment.TOPLEFT;
        this.spacing = spacing;
    }

    public PanelStyle() {
        IPanelStyle theme = General.currentTheme.get().panelStyle;
        this.alignment = ComponentAlignment.TOPLEFT;
        this.spacing = theme.getSpacing();
    }

    public PanelStyle spacing(float spacing) {
        this.spacing = spacing;
        return this;
    }

    public PanelStyle alignment(ComponentAlignment alignment) {
        this.alignment = alignment;
        return this;
    }

    @Override
    public ComponentAlignment getAlignment() {
        return alignment;
    }

    @Override
    public float getSpacing() {
        return spacing;
    }
}
