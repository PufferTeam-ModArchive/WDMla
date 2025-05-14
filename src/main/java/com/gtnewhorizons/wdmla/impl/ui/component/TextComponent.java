package com.gtnewhorizons.wdmla.impl.ui.component;

import com.gtnewhorizons.wdmla.api.ui.IComponent;
import org.jetbrains.annotations.NotNull;

import com.gtnewhorizons.wdmla.api.ui.sizer.ISize;
import com.gtnewhorizons.wdmla.api.ui.style.ITextStyle;
import com.gtnewhorizons.wdmla.impl.ui.drawable.TextDrawable;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Area;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Padding;
import com.gtnewhorizons.wdmla.impl.ui.sizer.TextSize;
import com.gtnewhorizons.wdmla.impl.ui.style.TextStyle;

import mcp.mobius.waila.utils.WailaExceptionHandler;

import java.util.ArrayList;

public class TextComponent extends TooltipComponent {

    protected ITextStyle style;

    public TextComponent(String text) {
        super(new ArrayList<>(), new Padding(), new TextSize(text), new TextDrawable(text));
        this.style = new TextStyle();
    }

    public TextComponent style(ITextStyle style) {
        ((TextDrawable) this.foreground).style(style);
        this.style = style;
        return this;
    }

    public TextComponent scale(float scale) {
        ((TextSize) size).scale(scale);
        ((TextDrawable) this.foreground).scale(scale);
        return this;
    }

    @Override
    public TextComponent size(@NotNull ISize size) {
        throw new UnsupportedOperationException("You can't set the size of TextComponent!");
    }

    @Override
    public void tick(float x, float y) {
        float width = size.getW();
        float height = size.getH();
        switch (style.getAlignment()) {
            case BOTTOMRIGHT -> foreground
                    .draw(new Area((x + width) + padding.getLeft(), y + padding.getTop(), width, height));
            case CENTER -> foreground
                    .draw(new Area((x + (width / 2)) + padding.getLeft(), y + padding.getTop(), width, height));
            case TOPLEFT -> foreground.draw(new Area(x + padding.getLeft(), y + padding.getTop(), width, height));
            default -> WailaExceptionHandler
                    .handleErr(new IllegalArgumentException("invalid text alignment"), this.getClass().getName(), null);
        }
    }

    @Override
    public IComponent child(@NotNull IComponent child) {
        throw new UnsupportedOperationException("You can't set children for TextComponent!");
    }
}
