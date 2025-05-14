package com.gtnewhorizons.wdmla.impl.ui.component;

import java.util.List;

import com.gtnewhorizons.wdmla.api.ui.helper.ComponentHelper;
import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizons.wdmla.api.ui.IDrawable;
import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.api.ui.sizer.IPadding;
import com.gtnewhorizons.wdmla.api.ui.sizer.ISize;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Area;

public class TooltipComponent implements IComponent {

    // settings
    protected IPadding padding;
    protected ISize size;

    // render
    protected IDrawable foreground;

    protected ResourceLocation tag;

    protected List<IComponent> children;

    protected TooltipComponent(List<IComponent> children, IPadding padding, ISize size, IDrawable foreground) {
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

        for (IComponent child : children) {
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
    public IComponent child(@NotNull IComponent child) {
        this.children.add(child);
        return this;
    }

    @Override
    public int childrenSize() {
        return this.children.size();
    }

    @Override
    public IComponent clear() {
        this.children.clear();
        return this;
    }

    @Override
    public IComponent tag(ResourceLocation tag) {
        this.tag = tag;
        return this;
    }

    @Override
    public ResourceLocation getTag() {
        return tag;
    }

    @Override
    public IComponent getChildWithTag(ResourceLocation tag) {
        for (int i = 0; i < children.size(); i++) {
            IComponent child = children.get(i);
            if (tag.equals(child.getTag())) {
                return child;
            } else {
                IComponent nestedChild = child.getChildWithTag(tag);
                if (nestedChild != null) {
                    return nestedChild;
                }
            }
        }

        return null;
    }

    @Override
    public boolean replaceChildWithTag(@NotNull ResourceLocation tag, IComponent newChild) {
        for (int i = 0; i < children.size(); i++) {
            IComponent child = children.get(i);
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
    public IComponent text(String text) {
        IComponent c = ComponentHelper.instance().text(text);
        this.children.add(c);
        return this;
    }

    @Override
    public IComponent vertical() {
        IComponent c = ComponentHelper.instance().vertical();
        this.children.add(c);
        return c;
    }

    @Override
    public IComponent horizontal() {
        IComponent c = ComponentHelper.instance().horizontal();
        this.children.add(c);
        return c;
    }
}
