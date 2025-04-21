package com.gtnewhorizons.wdmla.overlay;

import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public enum WDMlaUIIcons implements IIcon {
    FURNACE(4, 0, 28, 16),
    FURNACE_BG(4, 16, 28, 16),
    ;

    public static final ResourceLocation FURNACE_PATH = new ResourceLocation("waila", "textures/sprites.png");
    public static final int TEX_WIDTH = 256;
    public static final int TEX_HEIGHT = 256;

    /**
     * u, v, sizeU, sizeV
     */
    public final int u, v, su, sv;

    WDMlaUIIcons(int u, int v, int su, int sv) {
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
