package com.gtnewhorizons.wdmla.impl.ui.component;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.api.ui.sizer.ISize;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Area;

/**
 * A Vertical layout panel. Size depends on children.
 */
public class VPanelComponent extends PanelComponent {

    public VPanelComponent() {
        super();
    }

    @Override
    public void tick(float x, float y) {
        float totWidth = this.getWidth();
        float cy = y + padding.getTop() + style.getBorderThickness();
        for (int i = 0; i < children.size(); i++) {
            float w = children.get(i).getWidth();
            float cx = x;
            switch (style.getAlignment()) {
                case TOPLEFT:
                    cx = x + padding.getLeft() + style.getBorderThickness();
                    break;
                case CENTER:
                    cx = x + (totWidth - w) / 2;
                    break;
                case BOTTOMRIGHT:
                    cx = x + totWidth - w - padding.getRight() - style.getBorderThickness();
                default:
                    break;
            }

            children.get(i).tick(cx, cy);

            if (i < children.size() - 1) {
                cy += children.get(i).getHeight() + style.getSpacing();
            }
        }

        foreground.draw(new Area(x + padding.getLeft(), y + padding.getTop(), getWidth(), getHeight()));
    }

    @Override
    public float getWidth() {
        float w = 0;
        for (IComponent child : children) {
            float ww = child.getWidth();
            if (ww > w) {
                w = ww;
            }
        }

        return padding.getLeft() + w + padding.getRight() + style.getBorderThickness() * 2;
    }

    @Override
    public float getHeight() {
        int h = 0;
        for (IComponent child : children) {
            h += child.getHeight();
        }

        int totalSpacing = children.isEmpty() ? 0 : style.getSpacing() * (children.size() - 1);
        return padding.getTop() + h + totalSpacing + padding.getBottom() + style.getBorderThickness() * 2;
    }

    @Override
    public PanelComponent size(@NotNull ISize size) {
        throw new IllegalArgumentException("Vertical Panel is auto sized.");
    }
}
