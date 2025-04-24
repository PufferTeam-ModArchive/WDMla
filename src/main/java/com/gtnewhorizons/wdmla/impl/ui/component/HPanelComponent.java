package com.gtnewhorizons.wdmla.impl.ui.component;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.api.ui.sizer.ISize;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Area;

/**
 * A Horizontal layout panel. Size depends on children.
 */
public class HPanelComponent extends PanelComponent {

    public HPanelComponent() {
        super();
    }

    @Override
    public void tick(float x, float y) {
        foreground.draw(new Area(x + padding.getLeft(), y + padding.getTop(), getWidth(), getHeight()));

        float totHeight = this.getHeight();
        float cx = x + padding.getLeft() + bgStyle.getBorderThickness();
        for (int i = 0; i < children.size(); i++) {
            float h = children.get(i).getHeight();
            float cy = y;
            switch (style.getAlignment()) {
                case TOPLEFT:
                    cy = y + padding.getTop() + bgStyle.getBorderThickness();
                    break;
                case CENTER:
                    cy = y + (totHeight - h) / 2;
                    break;
                case BOTTOMRIGHT:
                    cy = y + totHeight - h - padding.getBottom() - bgStyle.getBorderThickness();
                default:
                    break;
            }

            children.get(i).tick(cx, cy);

            if (i < children.size() - 1) {
                cx += children.get(i).getWidth() + style.getSpacing();
            }
        }
    }

    @Override
    public float getWidth() {
        float w = 0;
        for (IComponent child : children) {
            w += child.getWidth();
        }

        float totalSpacing = children.isEmpty() ? 0 : style.getSpacing() * (children.size() - 1);
        return padding.getLeft() + w + totalSpacing + padding.getRight() + bgStyle.getBorderThickness() * 2;
    }

    @Override
    public float getHeight() {
        float h = 0;
        for (IComponent child : children) {
            float ww = child.getHeight();
            if (ww > h) {
                h = ww;
            }
        }

        return padding.getTop() + h + padding.getBottom() + bgStyle.getBorderThickness() * 2;
    }

    @Override
    public PanelComponent size(@NotNull ISize size) {
        throw new IllegalArgumentException("Horizontal Panel is auto sized.");
    }
}
