package com.gtnewhorizons.wdmla.overlay;

import static com.gtnewhorizons.wdmla.api.Identifiers.FURNACE_PATH;
import static com.gtnewhorizons.wdmla.api.Identifiers.RS2_ICON_PATH;

import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public enum WDMlaUIIcons implements IIcon {

    FURNACE(4, 0, 28, 16, 256, 256, FURNACE_PATH),
    FURNACE_BG(4, 16, 28, 16, 256, 256, FURNACE_PATH),
    CANCEL(1, 1, 12, 12, 42, 42, RS2_ICON_PATH),
    ERROR(15, 1, 12, 12, 42, 42, RS2_ICON_PATH),
    RESET(29, 1, 12, 12, 42, 42, RS2_ICON_PATH),
    IDLE(1, 15, 12, 12, 42, 42, RS2_ICON_PATH),
    PAUSE(15, 15, 12, 12, 42, 42, RS2_ICON_PATH),
    SET(29, 15, 12, 12, 42, 42, RS2_ICON_PATH),
    START(2, 29, 10, 12, 42, 42, RS2_ICON_PATH),
    WARNING(15, 29, 10, 10, 42, 42, RS2_ICON_PATH),;

    public final int texWidth;
    public final int texHeight;
    public final ResourceLocation texPath;

    /**
     * u, v, sizeU, sizeV
     */
    public final int u, v, su, sv;

    WDMlaUIIcons(int u, int v, int su, int sv, int texWidth, int texHeight, ResourceLocation texPath) {
        this.u = u;
        this.v = v;
        this.su = su;
        this.sv = sv;
        this.texWidth = texWidth;
        this.texHeight = texHeight;
        this.texPath = texPath;
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
        return (float) u / texWidth;
    }

    @Override
    public float getMaxU() {
        return (float) (u + su) / texWidth;
    }

    @Override
    public float getInterpolatedU(double interpolation) {
        return (u + su * (float) interpolation) / texWidth;
    }

    @Override
    public float getMinV() {
        return (float) v / texHeight;
    }

    @Override
    public float getMaxV() {
        return (float) (v + sv) / texHeight;
    }

    @Override
    public float getInterpolatedV(double interpolation) {
        return (v + sv * (float) interpolation) / texHeight;
    }

    @Override
    public String getIconName() {
        return this.name();
    }
}
