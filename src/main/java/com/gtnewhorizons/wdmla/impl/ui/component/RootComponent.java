package com.gtnewhorizons.wdmla.impl.ui.component;

import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;

import com.gtnewhorizons.wdmla.api.Identifiers;
import com.gtnewhorizons.wdmla.api.ui.IDrawable;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.impl.ui.drawable.BackgroundDrawable;
import com.gtnewhorizons.wdmla.impl.ui.drawable.BreakProgressDrawable;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Area;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Size;
import com.gtnewhorizons.wdmla.impl.ui.value.HUDRenderArea;
import com.gtnewhorizons.wdmla.util.GLStateHelper;

import mcp.mobius.waila.utils.WailaExceptionHandler;

public final class RootComponent extends VPanelComponent {

    private final @NotNull IDrawable background = new BackgroundDrawable();
    private final @NotNull IDrawable breakProgress = BreakProgressDrawable.INSTANCE;

    public RootComponent() {
        super();
        tagInternal();
    }

    public void renderHUD() {

        GLStateHelper.prepareBGDraw();

        Area bgArea = new HUDRenderArea(new Size(getWidth(), getHeight())).computeBackground();
        background.draw(bgArea);
        breakProgress.draw(bgArea);

        GLStateHelper.prepareFGDraw();

        Area fgArea = new HUDRenderArea(new Size(getWidth(), getHeight())).computeForeground();
        tick(fgArea.getX(), fgArea.getY());

        GLStateHelper.endDraw();
    }

    @Override
    public ITooltip tag(ResourceLocation tag) {
        WailaExceptionHandler.handleErr(
                new IllegalArgumentException("RootComponent tag cannot be replaced!"),
                RootComponent.class.getName(),
                null);
        return null;
    }

    private void tagInternal() {
        super.tag(Identifiers.ROOT);
    }
}
