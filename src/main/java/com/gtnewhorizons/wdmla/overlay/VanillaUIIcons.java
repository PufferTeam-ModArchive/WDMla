package com.gtnewhorizons.wdmla.overlay;

import net.minecraft.client.gui.Gui;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

/**
 * Vanilla ui icons from {@code Gui.icons} sprite sheet location.
 */
public enum VanillaUIIcons implements IIcon {

    HEART(52, 0, 9, 9),
    HEART_BG(52, 9, 9, 9),
    HHEART(61, 0, 9, 9),
    EHEART(52, 9, 9, 9),
    ARMOR(34, 9, 9, 9),
    ARMOR_BG(16, 9, 9, 9),
    HARMOR(25, 9, 9, 9),
    EARMOR(16, 9, 9, 9),
    BUBBLEEXP(25, 18, 9, 9);

    public static final ResourceLocation PATH = Gui.icons;
    public static final int TEX_WIDTH = 256;
    public static final int TEX_HEIGHT = 256;

    /**
     * u, v, sizeU, sizeV
     */
    public final int u, v, su, sv;

    VanillaUIIcons(int u, int v, int su, int sv) {
        this.u = u;
        this.v = v;
        this.su = su;
        this.sv = sv;
    }

    @Override
    public int getIconWidth() {
        return su;
    }

    @Override
    public int getIconHeight() {
        return sv;
    }

    @Override
    public float getMinU() {
        return (float) u / TEX_WIDTH;
    }

    @Override
    public float getMaxU() {
        return (float) (u + su) / TEX_WIDTH;
    }

    @Override
    public float getInterpolatedU(double interpolation) {
        return (u + su * (float) interpolation) / TEX_WIDTH;
    }

    @Override
    public float getMinV() {
        return (float) v / TEX_HEIGHT;
    }

    @Override
    public float getMaxV() {
        return (float) (v + sv) / TEX_HEIGHT;
    }

    @Override
    public float getInterpolatedV(double interpolation) {
        return (v + sv * (float) interpolation) / TEX_HEIGHT;
    }

    @Override
    public String getIconName() {
        return this.name();
    }
}
