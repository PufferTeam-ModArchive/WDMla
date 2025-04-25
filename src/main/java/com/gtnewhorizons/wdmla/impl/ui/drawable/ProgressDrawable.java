package com.gtnewhorizons.wdmla.impl.ui.drawable;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizons.wdmla.api.ui.IDrawable;
import com.gtnewhorizons.wdmla.api.ui.IFilledProgress;
import com.gtnewhorizons.wdmla.api.ui.sizer.IArea;
import com.gtnewhorizons.wdmla.api.ui.style.IProgressStyle;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Area;
import com.gtnewhorizons.wdmla.impl.ui.style.ProgressStyle;
import com.gtnewhorizons.wdmla.overlay.GuiDraw;
import com.gtnewhorizons.wdmla.util.Color;

public class ProgressDrawable implements IDrawable {

    private final @NotNull IFilledProgress progress;
    private @NotNull IProgressStyle style;

    public ProgressDrawable(@NotNull IFilledProgress progress) {
        this.progress = progress;
        this.style = new ProgressStyle();
    }

    public ProgressDrawable style(IProgressStyle style) {
        this.style = style;
        return this;
    }

    @Override
    public void draw(IArea area) {
        long current = progress.getCurrent();
        long max = progress.getMax();
        if (current <= 0L || max <= 0L) {
            return;
        }

        float dx = Math.min(current * area.getW() / max, area.getW());
        if (dx > 0) {
            if (style.getOverlay() != null) {
                style.getOverlay().draw(new Area(area.getX(), area.getY(), dx, area.getH()));
            } else {
                fillWithGradient(area, dx);
            }
        }
        if (style.getFilledColor() != style.getAlternateFilledColor()) {
            for (int xx = (int) area.getX(); xx <= area.getX() + dx; ++xx) {
                if ((xx & 1) == 0) {
                    continue;
                }
                GuiDraw.drawVerticalLine(xx, area.getY(), area.getH(), style.getAlternateFilledColor());
            }
        }
    }

    private void fillWithGradient(IArea area, float dx) {
        int darker = Color.setLightness(style.getFilledColor(), 0.7f);
        float half = area.getH() / 2;
        GuiDraw.drawStraightGradientRect(
                new Area(area.getX(), area.getY(), dx, half),
                darker,
                style.getFilledColor(),
                false);
        GuiDraw.drawStraightGradientRect(
                new Area(area.getX(), area.getY() + half, dx, area.getH() - half),
                style.getFilledColor(),
                darker,
                false);
    }
}
