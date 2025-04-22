package com.gtnewhorizons.wdmla.impl.ui.drawable;

import com.gtnewhorizons.wdmla.impl.ui.sizer.Area;
import net.minecraft.util.MathHelper;

import com.gtnewhorizons.wdmla.api.ui.IDrawable;
import com.gtnewhorizons.wdmla.api.ui.sizer.IArea;
import com.gtnewhorizons.wdmla.overlay.GuiDraw;
import com.gtnewhorizons.wdmla.overlay.VanillaUIIcons;

public class ArmorDrawable implements IDrawable {

    private final int maxArmorsPerLine; // max armors fit in line
    private final float armor; // number of armors
    private final float maxArmor; // number of max armors
    private final IconDrawable armorBG;
    private final IconDrawable armorIcon;
    private final IconDrawable hArmor;
    private final IconDrawable eArmor;

    public ArmorDrawable(int maxArmorsPerLine, float armor, float maxArmor) {
        this.maxArmorsPerLine = maxArmorsPerLine;
        this.armor = armor;
        this.maxArmor = maxArmor;
        this.armorBG = new IconDrawable(VanillaUIIcons.ARMOR_BG, VanillaUIIcons.PATH);
        this.armorIcon = new IconDrawable(VanillaUIIcons.ARMOR, VanillaUIIcons.PATH);
        this.hArmor = new IconDrawable(VanillaUIIcons.HARMOR, VanillaUIIcons.PATH);
        this.eArmor = new IconDrawable(VanillaUIIcons.EARMOR, VanillaUIIcons.PATH);
    }

    @Override
    public void draw(IArea area) {
        int nArmor = MathHelper.ceiling_float_int(maxArmor);
        int nArmorsPerLine = (int) (Math.min(maxArmorsPerLine, Math.ceil(maxArmor)));

        int offsetX = area.getX();
        int offsetY = area.getY();

        for (int iArmor = 1; iArmor <= nArmor; iArmor++) {

            if (iArmor <= MathHelper.floor_float(armor)) {
                armorBG.draw(new Area(offsetX, offsetY, 8, 8));
                armorIcon.draw(new Area(offsetX, offsetY, 8, 8));
                offsetX += 8;
            }

            if ((iArmor > armor) && (iArmor <= armor + 0.5f)) {
                armorBG.draw(new Area(offsetX, offsetY, 8, 8));
                hArmor.draw(new Area(offsetX, offsetY, 8, 8));
                offsetX += 8;
            }

            if (iArmor > armor + 0.5f) {
                eArmor.draw(new Area(offsetX, offsetY, 8, 8));
                offsetX += 8;
            }

            if (iArmor % nArmorsPerLine == 0) {
                offsetY += 10;
                offsetX = area.getX();
            }
        }
    }
}
