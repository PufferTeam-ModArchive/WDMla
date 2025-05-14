package com.gtnewhorizons.wdmla.impl.ui.component;

import java.util.List;

import com.gtnewhorizons.wdmla.api.ui.helper.ComponentHelper;
import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizons.wdmla.api.ui.IDrawable;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.api.ui.sizer.IPadding;
import com.gtnewhorizons.wdmla.api.ui.sizer.ISize;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Area;

public class TooltipComponent implements ITooltip {

    // settings
    protected IPadding padding;
    protected ISize size;

    // render
    protected IDrawable foreground;

    protected ResourceLocation tag;

    protected List<ITooltip> children;

    protected TooltipComponent(List<ITooltip> children, IPadding padding, ISize size, IDrawable foreground) {
        this.padding = padding;
        this.size = size;
        this.foreground = foreground;
        this.children = children;
    }

    public TooltipComponent padding(@NotNull IPadding padding) {
        this.padding = padding;
        return this;
    }

    public TooltipComponent size(@NotNull ISize size) {
        this.size = size;
        return this;
    }

    @Override
    public void tick(float x, float y) {
        foreground.draw(new Area(x + padding.getLeft(), y + padding.getTop(), size.getW(), size.getH()));

        for (ITooltip child : children) {
            child.tick(x + padding.getLeft(), y + padding.getTop());
        }
    }

    @Override
    public float getWidth() {
        return padding.getLeft() + size.getW() + padding.getRight();
    }

    @Override
    public float getHeight() {
        return padding.getTop() + size.getH() + padding.getBottom();
    }

    @Override
    public ITooltip child(@NotNull ITooltip child) {
        this.children.add(child);
        return this;
    }

    @Override
    public int childrenSize() {
        return this.children.size();
    }

    @Override
    public ITooltip clear() {
        this.children.clear();
        return this;
    }

    @Override
    public ITooltip tag(ResourceLocation tag) {
        this.tag = tag;
        return this;
    }

    @Override
    public ResourceLocation getTag() {
        return tag;
    }

    @Override
    public ITooltip getChildWithTag(ResourceLocation tag) {
        for (int i = 0; i < children.size(); i++) {
            ITooltip child = children.get(i);
            if (tag.equals(child.getTag())) {
                return child;
            } else {
                ITooltip nestedChild = child.getChildWithTag(tag);
                if (nestedChild != null) {
                    return nestedChild;
                }
            }
        }

        return null;
    }

    @Override
    public boolean replaceChildWithTag(@NotNull ResourceLocation tag, ITooltip newChild) {
        for (int i = 0; i < children.size(); i++) {
            ITooltip child = children.get(i);
            if (tag.equals(child.getTag())) {
                children.set(i, newChild);
                return true;
            } else {
                boolean isReplaced = child.replaceChildWithTag(tag, newChild);
                if (isReplaced) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public ITooltip text(String text) {
        ITooltip c = ComponentHelper.instance().text(text);
        this.children.add(c);
        return this;
    }

    @Override
    public ITooltip vertical() {
        ITooltip c = ComponentHelper.instance().vertical();
        this.children.add(c);
        return c;
    }

    @Override
    public ITooltip horizontal() {
        ITooltip c = ComponentHelper.instance().horizontal();
        this.children.add(c);
        return c;
    }
}
