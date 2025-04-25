package com.gtnewhorizons.wdmla.impl.ui.drawable;

import net.minecraft.client.Minecraft;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;

import com.gtnewhorizons.wdmla.api.ui.IDrawable;
import com.gtnewhorizons.wdmla.api.ui.sizer.IArea;
import com.gtnewhorizons.wdmla.overlay.GuiDraw;

public class IconDrawable implements IDrawable {

    private final IIcon icon;
    private final ResourceLocation path;
    private float suRatio = 0, svRatio = 0, twRatio = 1, thRatio = 1;

    public IconDrawable(IIcon icon, ResourceLocation path) {
        this.icon = icon;
        this.path = path;
    }

    public void clip(float suRatio, float svRatio, float twRatio, float thRatio) {
        this.suRatio = suRatio;
        this.svRatio = svRatio;
        this.twRatio = twRatio;
        this.thRatio = thRatio;
    }

    @Override
    public void draw(IArea area) {
        if (icon == null) {
            return;
        }

        float x = area.getX() + area.getW() * suRatio;
        float y = area.getY() + area.getH() * svRatio;
        float w = area.getW() * twRatio;
        float h = area.getH() * thRatio;

        float fullU = icon.getMaxU() - icon.getMinU();
        float fullV = icon.getMaxV() - icon.getMinV();

        float u0 = icon.getMinU() + fullU * suRatio;
        float v0 = icon.getMinV() + fullV * svRatio;

        float tw = fullU * w / area.getW();
        float th = fullV * h / area.getH();

        Minecraft.getMinecraft().getTextureManager().bindTexture(path);
        GuiDraw.drawTexturedModelRect(x, y, u0, v0, w, h, tw, th);
    }
}
