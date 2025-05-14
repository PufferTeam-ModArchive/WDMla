package com.gtnewhorizons.wdmla.impl.ui.component;

import com.gtnewhorizons.wdmla.api.ui.IComponent;
import org.jetbrains.annotations.NotNull;

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
        foreground.draw(new Area(x + padding.getLeft(), y + padding.getTop(), getWidth(), getHeight()));

        float totWidth = this.getWidth();
        float cy = y + padding.getTop() + bgStyle.getBorderThickness();
        for (int i = 0; i < children.size(); i++) {
            float w = children.get(i).getWidth();
            float cx = x;
            switch (style.getAlignment()) {
                case TOPLEFT:
                    cx = x + padding.getLeft() + bgStyle.getBorderThickness();
                    break;
                case CENTER:
                    cx = x + (totWidth - w) / 2;
                    break;
                case BOTTOMRIGHT:
                    cx = x + totWidth - w - padding.getRight() - bgStyle.getBorderThickness();
                default:
                    break;
            }

            children.get(i).tick(cx, cy);

            if (i < children.size() - 1) {
                cy += children.get(i).getHeight() + style.getSpacing();
            }
        }
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

        return padding.getLeft() + w + padding.getRight() + bgStyle.getBorderThickness() * 2;
    }

    @Override
    public float getHeight() {
        float h = 0;
        for (IComponent child : children) {
            h += child.getHeight();
        }

        float totalSpacing = children.isEmpty() ? 0 : style.getSpacing() * (children.size() - 1);
        return padding.getTop() + h + totalSpacing + padding.getBottom() + bgStyle.getBorderThickness() * 2;
    }

    @Override
    public PanelComponent size(@NotNull ISize size) {
        throw new IllegalArgumentException("Vertical Panel is auto sized.");
    }
}
