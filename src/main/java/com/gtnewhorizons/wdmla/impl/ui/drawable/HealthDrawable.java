package com.gtnewhorizons.wdmla.impl.ui.drawable;

import com.gtnewhorizons.wdmla.impl.ui.sizer.Area;
import net.minecraft.util.MathHelper;

import com.gtnewhorizons.wdmla.api.ui.IDrawable;
import com.gtnewhorizons.wdmla.api.ui.sizer.IArea;
import com.gtnewhorizons.wdmla.overlay.GuiDraw;
import com.gtnewhorizons.wdmla.overlay.VanillaUIIcons;

public class HealthDrawable implements IDrawable {

    private final int maxHeartsPerLine; // max hearts fit in line
    private final float health; // number of hearts
    private final float maxHealth; // number of max hearts
    private final IconDrawable heartBG;
    private final IconDrawable heart;
    private final IconDrawable hHeart;
    private final IconDrawable eHeart;

    public HealthDrawable(int maxHeartsPerLine, float health, float maxHealth) {
        this.maxHeartsPerLine = maxHeartsPerLine;
        this.health = health;
        this.maxHealth = maxHealth;
        this.heartBG = new IconDrawable(VanillaUIIcons.HEART_BG, VanillaUIIcons.PATH);
        this.heart = new IconDrawable(VanillaUIIcons.HEART, VanillaUIIcons.PATH);
        this.hHeart = new IconDrawable(VanillaUIIcons.HHEART, VanillaUIIcons.PATH);
        this.eHeart = new IconDrawable(VanillaUIIcons.EHEART, VanillaUIIcons.PATH);
    }

    @Override
    public void draw(IArea area) {
        int nHearts = MathHelper.ceiling_float_int(maxHealth);
        int heartsPerLine = (int) (Math.min(maxHeartsPerLine, Math.ceil(maxHealth)));

        int offsetX = area.getX();
        int offsetY = area.getY();

        for (int iheart = 1; iheart <= nHearts; iheart++) {

            if (iheart <= MathHelper.floor_float(health)) {
                heartBG.draw(new Area(offsetX, offsetY, 8, 8));
                heart.draw(new Area(offsetX, offsetY, 8, 8));
                offsetX += 8;
            }

            if ((iheart > health) && (iheart <= health + 0.5f)) {
                heartBG.draw(new Area(offsetX, offsetY, 8, 8));
                hHeart.draw(new Area(offsetX, offsetY, 8, 8));
                offsetX += 8;
            }

            if (iheart > health + 0.5f) {
                eHeart.draw(new Area(offsetX, offsetY, 8, 8));
                offsetX += 8;
            }

            if (iheart % heartsPerLine == 0) {
                offsetY += 10;
                offsetX = area.getX();
            }
        }
    }
}
