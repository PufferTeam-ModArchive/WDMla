package com.gtnewhorizons.wdmla.impl.ui.sizer;

import com.gtnewhorizons.wdmla.api.ui.sizer.ISize;

public class Size implements ISize {

    protected final float width;
    protected final float height;

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
        verify();
    }

    public Size(float width, float height) {
        this.width = width;
        this.height = height;
        verify();
    }

    private void verify() {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Size must not below zero");
        }
    }

    @Override
    public float getW() {
        return width;
    }

    @Override
    public float getH() {
        return height;
    }
}
