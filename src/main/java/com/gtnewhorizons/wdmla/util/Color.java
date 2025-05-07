package com.gtnewhorizons.wdmla.util;

import net.minecraft.util.MathHelper;

public class Color {

    public static int setLightness(int color, float multiplier) {
        int a = (color >> 24) & 0xFF;
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;

        r = Math.min(255, (int) (r * multiplier));
        g = Math.min(255, (int) (g * multiplier));
        b = Math.min(255, (int) (b * multiplier));

        return (a << 24) | (r << 16) | (g << 8) | b;
    }

    public static int setInterporation(int colorBegin, int colorEnd, float interporation) {
        int a1 = (colorBegin >> 24) & 0xFF;
        int r1 = (colorBegin >> 16) & 0xFF;
        int g1 = (colorBegin >> 8) & 0xFF;
        int b1 = colorBegin & 0xFF;

        int a2 = (colorEnd >> 24) & 0xFF;
        int r2 = (colorEnd >> 16) & 0xFF;
        int g2 = (colorEnd >> 8) & 0xFF;
        int b2 = colorEnd & 0xFF;

        int a = MathHelper.clamp_int(a1 + (int) ((a2 - a1) * interporation), 0, 255);
        int r = MathHelper.clamp_int(r1 + (int) ((r2 - r1) * interporation), 0, 255);
        int g = MathHelper.clamp_int(g1 + (int) ((g2 - g1) * interporation), 0, 255);
        int b = MathHelper.clamp_int(b1 + (int) ((b2 - b1) * interporation), 0, 255);

        return (a << 24) | (r << 16) | (g << 8) | b;
    }
}
