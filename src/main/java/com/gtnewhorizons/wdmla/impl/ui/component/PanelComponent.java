package com.gtnewhorizons.wdmla.impl.ui.component;

import java.util.ArrayList;

import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.api.ui.IDrawable;
import com.gtnewhorizons.wdmla.api.ui.style.IRectStyle;
import com.gtnewhorizons.wdmla.impl.ui.drawable.RectDrawable;
import com.gtnewhorizons.wdmla.impl.ui.style.RectStyle;
import org.jetbrains.annotations.NotNull;

import com.gtnewhorizons.wdmla.api.ui.style.IPanelStyle;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Padding;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Size;
import com.gtnewhorizons.wdmla.impl.ui.style.PanelStyle;

public abstract class PanelComponent extends TooltipComponent {

    public static final int DEFAULT_W = 100;
    public static final int DEFAULT_H = 100;

    protected @NotNull IPanelStyle style;
    protected IRectStyle bgStyle;

    protected PanelComponent() {
        super(new ArrayList<>(), new Padding(), new Size(DEFAULT_W, DEFAULT_H), new RectDrawable());
        this.style = new PanelStyle();
        this.bgStyle = new RectStyle();
    }

    public PanelComponent style(IPanelStyle style) {
        this.style = style;
        return this;
    }

    public PanelComponent bgStyle(IRectStyle rectStyle) {
        ((RectDrawable) this.foreground).style(rectStyle);
        this.bgStyle = rectStyle;
        return this;
    }
}
