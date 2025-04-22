package com.gtnewhorizons.wdmla.overlay;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

/**
 * Drawable potion sprite IIcon data calculated from potion effect class.
 * 
 * @see net.minecraft.client.renderer.InventoryEffectRenderer
 */
public class PotionIcon implements IIcon {

    public static final ResourceLocation PATH = new ResourceLocation("textures/gui/container/inventory.png");;

    private final int iconIndex;
    public static final int TEX_WIDTH = 256;
    public static final int TEX_HEIGHT = 256;

    public PotionIcon(PotionEffect potionEffect) {
        this.iconIndex = Potion.potionTypes[potionEffect.getPotionID()].getStatusIconIndex();
    }

    @Override
    public float getMinU() {
        return (float) (iconIndex % 8 * 18) / TEX_WIDTH;
    }

    @Override
    public float getMaxU() {
        return (float)(iconIndex % 8 * 18 + 18) / TEX_WIDTH;
    }

    @Override
    public float getInterpolatedU(double interpolation) {
        return (iconIndex % 8 * 18 + 18 * (float) interpolation) / TEX_WIDTH;
    }

    @Override
    public float getMinV() {
        return (float)(198 + iconIndex / 8 * 18) / TEX_HEIGHT;
    }

    @Override
    public float getMaxV() {
        return (float)(198 + iconIndex / 8 * 18 + 18) / TEX_HEIGHT;
    }

    @Override
    public float getInterpolatedV(double interpolation) {
        return (198 + (int)(iconIndex / 8) * 18 + 18 * (float) interpolation) / TEX_HEIGHT;
    }

    @Override
    public String getIconName() {
        return "potion_" + iconIndex;
    }

    @Override
    public int getIconWidth() {
        return 18;
    }

    @Override
    public int getIconHeight() {
        return 18;
    }
}
