package com.gtnewhorizons.wdmla.impl.ui.style;

import com.gtnewhorizons.wdmla.api.ui.ColorPalette;
import com.gtnewhorizons.wdmla.api.ui.style.IRectStyle;

public class RectStyle implements IRectStyle {

    private int backgroundColor1;
    private int backgroundColor2;
    private int borderColor;
    private float borderThickness;

    public RectStyle() {
        this.backgroundColor1 = ColorPalette.TRANSPARENT;
        this.backgroundColor2 = ColorPalette.TRANSPARENT;
        this.borderColor = ColorPalette.TRANSPARENT;
        this.borderThickness = 1;
    }

    public RectStyle borderColor(int borderColor) {
        this.borderColor = borderColor;
        return this;
    }

    public RectStyle backgroundColor(int backgroundColor) {
        this.backgroundColor1 = backgroundColor;
        this.backgroundColor2 = backgroundColor;
        return this;
    }

    public RectStyle borderThickness(float borderThickness) {
        this.borderThickness = borderThickness;
        return this;
    }

    @Override
    public int getBackgroundColor1() {
        return backgroundColor1;
    }

    @Override
    public int getBackgroundColor2() {
        return backgroundColor2;
    }

    @Override
    public int getBorderColor() {
        return borderColor;
    }

    @Override
    public float getBorderThickness() {
        return borderColor == ColorPalette.TRANSPARENT ? 0 : borderThickness;
    }
}
