package com.gtnewhorizons.wdmla.overlay;

import com.gtnewhorizons.wdmla.api.identifier.SpritePaths;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

public enum WDMlaUIIcons implements IIcon {

    FURNACE(4, 0, 28, 16, 256, 256, SpritePaths.FURNACE),
    FURNACE_BG(4, 16, 28, 16, 256, 256, SpritePaths.FURNACE),
    CANCEL(1, 1, 12, 12, 42, 42, SpritePaths.RS2_ICON),
    ERROR(15, 1, 12, 12, 42, 42, SpritePaths.RS2_ICON),
    RESET(29, 1, 12, 12, 42, 42, SpritePaths.RS2_ICON),
    IDLE(1, 15, 12, 12, 42, 42, SpritePaths.RS2_ICON),
    PAUSE(15, 15, 12, 12, 42, 42, SpritePaths.RS2_ICON),
    SET(29, 15, 12, 12, 42, 42, SpritePaths.RS2_ICON),
    START(2, 29, 10, 12, 42, 42, SpritePaths.RS2_ICON),
    WARNING(15, 29, 10, 10, 42, 42, SpritePaths.RS2_ICON),
    LOCK(0, 0, 10, 12, 10, 12, SpritePaths.LOCK),
    VOID(0, 0, 16, 16, 16, 16, SpritePaths.VOID)
    // spotless:off
    ;
    // spotless:on

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
