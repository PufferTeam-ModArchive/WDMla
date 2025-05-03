package com.gtnewhorizons.wdmla.impl.ui;

import net.minecraft.client.Minecraft;
import net.minecraft.util.StatCollector;

import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.HPanelComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.IconComponent;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Size;
import com.gtnewhorizons.wdmla.overlay.WDMlaUIIcons;

/**
 * Use this class to unify common object status
 */
public class StatusHelper {

    public static final StatusHelper INSTANCE = new StatusHelper();

    private static final int ICON_SIZE = Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;

    private StatusHelper() {

    }

    public IComponent structureIncomplete() {
        return new HPanelComponent().child(
                new IconComponent(WDMlaUIIcons.ERROR, WDMlaUIIcons.ERROR.texPath).size(new Size(ICON_SIZE, ICON_SIZE)))
                .child(
                        ThemeHelper.INSTANCE
                                .failure(StatCollector.translateToLocal("hud.msg.wdmla.incomplete.structure")));
    }

    public IComponent hasProblem() {
        return new HPanelComponent().child(
                new IconComponent(WDMlaUIIcons.ERROR, WDMlaUIIcons.ERROR.texPath).size(new Size(ICON_SIZE, ICON_SIZE)))
                .child(ThemeHelper.INSTANCE.failure(StatCollector.translateToLocal("hud.msg.wdmla.has.problem")));
    }

    public IComponent runningFine() {
        return new HPanelComponent().child(
                new IconComponent(WDMlaUIIcons.START, WDMlaUIIcons.START.texPath).size(new Size(ICON_SIZE, ICON_SIZE)))
                .child(ThemeHelper.INSTANCE.success(StatCollector.translateToLocal("hud.msg.wdmla.running.fine")));
    }

    public IComponent idle() {
        return new HPanelComponent().child(
                new IconComponent(WDMlaUIIcons.IDLE, WDMlaUIIcons.IDLE.texPath).size(new Size(ICON_SIZE, ICON_SIZE)))
                .child(ThemeHelper.INSTANCE.info(StatCollector.translateToLocal("hud.msg.wdmla.idle")));
    }

    public IComponent workingDisabled() {
        return new HPanelComponent().child(
                new IconComponent(WDMlaUIIcons.PAUSE, WDMlaUIIcons.PAUSE.texPath).size(new Size(ICON_SIZE, ICON_SIZE)))
                .child(ThemeHelper.INSTANCE.info(StatCollector.translateToLocal("hud.msg.wdmla.working.disabled")));
    }

    public IComponent insufficientEnergy() {
        return new HPanelComponent().child(
                new IconComponent(WDMlaUIIcons.WARNING, WDMlaUIIcons.WARNING.texPath)
                        .size(new Size(ICON_SIZE, ICON_SIZE)))
                .child(
                        ThemeHelper.INSTANCE
                                .warning(StatCollector.translateToLocal("hud.msg.wdmla.insufficient.energy")));
    }

    public IComponent insufficientFuel() {
        return new HPanelComponent()
                .child(
                        new IconComponent(WDMlaUIIcons.WARNING, WDMlaUIIcons.WARNING.texPath)
                                .size(new Size(ICON_SIZE, ICON_SIZE)))
                .child(ThemeHelper.INSTANCE.warning(StatCollector.translateToLocal("hud.msg.wdmla.insufficient.fuel")));
    }

    public IComponent locked() {
        return new HPanelComponent().child(
                        new IconComponent(WDMlaUIIcons.LOCK, WDMlaUIIcons.LOCK.texPath).size(new Size(ICON_SIZE / 1.2f, ICON_SIZE)))
                .child(ThemeHelper.INSTANCE.info(StatCollector.translateToLocal("hud.msg.wdmla.locked")));
    }
}
