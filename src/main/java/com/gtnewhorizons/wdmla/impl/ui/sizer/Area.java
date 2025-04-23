package com.gtnewhorizons.wdmla.impl.ui.sizer;

import com.gtnewhorizons.wdmla.api.ui.sizer.IArea;

public class Area extends Size implements IArea {

    private final float x;
    private final float y;

    public Area(int x, int y, int width, int height) {
        super(width, height);
        this.x = x;
        this.y = y;
    }

    public Area(float x, float y, float width, float height) {
        super(width, height);
        this.x = x;
        this.y = y;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }
}
