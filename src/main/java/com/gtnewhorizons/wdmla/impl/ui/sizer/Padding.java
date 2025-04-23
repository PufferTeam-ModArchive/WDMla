package com.gtnewhorizons.wdmla.impl.ui.sizer;

import com.gtnewhorizons.wdmla.api.ui.sizer.IPadding;

public class Padding implements IPadding {

    private float top;
    private float bottom;
    private float left;
    private float right;

    public Padding() {
        this.top = 0;
        this.bottom = 0;
        this.left = 0;
        this.right = 0;
    }

    public Padding(int top, int bottom, int left, int right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    public Padding(float top, float bottom, float left, float right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    public IPadding top(int top) {
        this.top = top;
        return this;
    }

    @Override
    public IPadding bottom(int bottom) {
        this.bottom = bottom;
        return this;
    }

    @Override
    public IPadding left(int left) {
        this.left = left;
        return this;
    }

    @Override
    public IPadding right(int right) {
        this.right = right;
        return this;
    }

    @Override
    public IPadding vertical(int vertical) {
        top(vertical);
        bottom(vertical);
        return this;
    }

    @Override
    public IPadding horizontal(int horizontal) {
        left(horizontal);
        right(horizontal);
        return this;
    }

    @Override
    public float getTop() {
        return top;
    }

    @Override
    public float getBottom() {
        return bottom;
    }

    @Override
    public float getLeft() {
        return left;
    }

    @Override
    public float getRight() {
        return right;
    }
}
