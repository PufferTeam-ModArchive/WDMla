package com.gtnewhorizons.wdmla.api.theme;

import com.gtnewhorizons.wdmla.api.TextColors;
import com.gtnewhorizons.wdmla.api.ui.ColorPalette;

public enum DefaultThemes {

    CUSTOM(0, 0, 0,
            new TextColors(
                    0,
                    ColorPalette.INFO,
                    ColorPalette.TITLE,
                    ColorPalette.SUCCESS,
                    ColorPalette.WARNING,
                    ColorPalette.DANGER,
                    ColorPalette.FAILURE,
                    ColorPalette.MOD_NAME),
            2),
    JADE(ColorPalette.BG_COLOR_JADE, ColorPalette.BG_GRADIENT1_JADE, ColorPalette.BG_GRADIENT2_JADE,
            new TextColors(
                    ColorPalette.DEFAULT,
                    ColorPalette.INFO,
                    ColorPalette.TITLE,
                    ColorPalette.SUCCESS_JADE,
                    ColorPalette.WARNING,
                    ColorPalette.DANGER,
                    ColorPalette.FAILURE,
                    ColorPalette.MOD_NAME),
            1), // Jade has tight spacing than others
    TOP(ColorPalette.BG_COLOR_TOP, ColorPalette.BG_GRADIENT1_TOP, ColorPalette.BG_GRADIENT2_TOP,
            new TextColors(
                    ColorPalette.DEFAULT,
                    ColorPalette.INFO,
                    ColorPalette.TITLE,
                    ColorPalette.SUCCESS_TOP,
                    ColorPalette.WARNING_TOP,
                    ColorPalette.DANGER,
                    ColorPalette.FAILURE,
                    ColorPalette.MOD_NAME),
            2);

    private final Theme theme;

    DefaultThemes(int bgColor, int gradient1, int gradient2, TextColors textColors, int defaultSpacing) {
        this.theme = new Theme(bgColor, gradient1, gradient2, textColors, defaultSpacing);
    }

    public Theme get() {
        return theme;
    }
}
