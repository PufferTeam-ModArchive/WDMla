package com.gtnewhorizons.wdmla.api.ui.sizer;

/**
 * The two-dimensional position and size information.
 * 
 * @see java.awt.Point
 */
public interface IArea extends ISize {

    float getX();

    float getY();

    default float getEX() {
        return getX() + getW();
    }

    default float getEY() {
        return getY() + getH();
    }
}
