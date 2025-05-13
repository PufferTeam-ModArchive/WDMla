package com.gtnewhorizons.wdmla.impl.ui.helper;

import com.gtnewhorizons.wdmla.api.ui.helper.StatusHelper;
import com.gtnewhorizons.wdmla.api.ui.helper.ThemeHelper;
import net.minecraft.util.StatCollector;

import com.gtnewhorizons.wdmla.api.ui.HighlightState;
import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.HPanelComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.IconComponent;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Padding;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Size;
import com.gtnewhorizons.wdmla.overlay.WDMlaUIIcons;

/**
 * Use this class to unify common object status
 */
public class StatusHelperImpl implements StatusHelper {

    public static final StatusHelper _instance = new StatusHelperImpl();

    private StatusHelperImpl() {

    }

    @Override
    public IComponent structureIncomplete() {
        return new HPanelComponent().child(
                new IconComponent(WDMlaUIIcons.ERROR, WDMlaUIIcons.ERROR.texPath).size(new Size(ICON_SIZE, ICON_SIZE)))
                .child(
                        ThemeHelper.instance()
                                .failure(StatCollector.translateToLocal("hud.msg.wdmla.incomplete.structure")));
    }

    @Override
    public IComponent hasProblem() {
        return new HPanelComponent().child(
                new IconComponent(WDMlaUIIcons.ERROR, WDMlaUIIcons.ERROR.texPath).size(new Size(ICON_SIZE, ICON_SIZE)))
                .child(ThemeHelper.instance().failure(StatCollector.translateToLocal("hud.msg.wdmla.has.problem")));
    }

    @Override
    public IComponent runningFine() {
        return new HPanelComponent().child(
                new IconComponent(WDMlaUIIcons.START, WDMlaUIIcons.START.texPath).size(new Size(ICON_SIZE, ICON_SIZE)))
                .child(ThemeHelper.instance().success(StatCollector.translateToLocal("hud.msg.wdmla.running.fine")));
    }

    @Override
    public IComponent idle() {
        return new HPanelComponent().child(
                new IconComponent(WDMlaUIIcons.IDLE, WDMlaUIIcons.IDLE.texPath).size(new Size(ICON_SIZE, ICON_SIZE)))
                .child(ThemeHelper.instance().info(StatCollector.translateToLocal("hud.msg.wdmla.idle")));
    }

    @Override
    public IComponent workingDisabled() {
        return new HPanelComponent().child(
                new IconComponent(WDMlaUIIcons.PAUSE, WDMlaUIIcons.PAUSE.texPath).size(new Size(ICON_SIZE, ICON_SIZE)))
                .child(ThemeHelper.instance().info(StatCollector.translateToLocal("hud.msg.wdmla.working.disabled")));
    }

    @Override
    public IComponent insufficientEnergy() {
        return new HPanelComponent().child(
                new IconComponent(WDMlaUIIcons.WARNING, WDMlaUIIcons.WARNING.texPath)
                        .size(new Size(ICON_SIZE, ICON_SIZE)))
                .child(
                        ThemeHelper.instance()
                                .warning(StatCollector.translateToLocal("hud.msg.wdmla.insufficient.energy")));
    }

    @Override
    public IComponent insufficientFuel() {
        return new HPanelComponent()
                .child(
                        new IconComponent(WDMlaUIIcons.WARNING, WDMlaUIIcons.WARNING.texPath)
                                .size(new Size(ICON_SIZE, ICON_SIZE)))
                .child(ThemeHelper.instance().warning(StatCollector.translateToLocal("hud.msg.wdmla.insufficient.fuel")));
    }

    @Override
    public IComponent locked() {
        return locked(HighlightState.ACTIVE);
    }

    @Override
    public IComponent locked(HighlightState highlightState) {
        switch (highlightState) {
            case ACTIVATING -> {
                return new HPanelComponent().child(
                        new IconComponent(WDMlaUIIcons.LOCK, WDMlaUIIcons.LOCK.texPath)
                                .size(new Size(ICON_SIZE, ICON_SIZE)))
                        .child(
                                ThemeHelper.instance()
                                        .success(StatCollector.translateToLocal("hud.msg.wdmla.locked") + "!"));
            }
            case ACTIVE -> {
                return new HPanelComponent()
                        .child(
                                new IconComponent(WDMlaUIIcons.LOCK, WDMlaUIIcons.LOCK.texPath)
                                        .size(new Size(ICON_SIZE, ICON_SIZE)))
                        .child(ThemeHelper.instance().info(StatCollector.translateToLocal("hud.msg.wdmla.locked")));
            }
            case DEACTIVATING -> {
                return new HPanelComponent()
                        .child(
                                new IconComponent(WDMlaUIIcons.LOCK, WDMlaUIIcons.LOCK.texPath)
                                        .size(new Size(ICON_SIZE, ICON_SIZE)).child(
                                                new HPanelComponent().padding(new Padding(2, 0, 1.5f, 0))
                                                        .child(ThemeHelper.instance().failure("✕"))))
                        .child(ThemeHelper.instance().failure(StatCollector.translateToLocal("hud.msg.wdmla.locked")));
            }
            default -> {
                return new HPanelComponent();
            }
        }
    }

    @Override
    public IComponent voidOverflow() {
        return voidOverflow(HighlightState.ACTIVE);
    }

    @Override
    public IComponent voidOverflow(HighlightState highlightState) {
        switch (highlightState) {
            case ACTIVATING -> {
                return new HPanelComponent()
                        .child(
                                new IconComponent(WDMlaUIIcons.VOID, WDMlaUIIcons.VOID.texPath)
                                        .size(new Size(ICON_SIZE, ICON_SIZE)))
                        .child(
                                ThemeHelper.instance()
                                        .success(StatCollector.translateToLocal("hud.msg.wdmla.void.overflow") + "!"));
            }
            case ACTIVE -> {
                return new HPanelComponent().child(
                        new IconComponent(WDMlaUIIcons.VOID, WDMlaUIIcons.VOID.texPath)
                                .size(new Size(ICON_SIZE, ICON_SIZE)))
                        .child(
                                ThemeHelper.instance()
                                        .info(StatCollector.translateToLocal("hud.msg.wdmla.void.overflow")));
            }
            case DEACTIVATING -> {
                return new HPanelComponent()
                        .child(
                                new IconComponent(WDMlaUIIcons.VOID, WDMlaUIIcons.VOID.texPath)
                                        .size(new Size(ICON_SIZE, ICON_SIZE)).child(
                                                new HPanelComponent().padding(new Padding(2, 0, 1.5f, 0))
                                                        .child(ThemeHelper.instance().failure("✕"))))
                        .child(
                                ThemeHelper.instance()
                                        .failure(StatCollector.translateToLocal("hud.msg.wdmla.void.overflow")));
            }
            default -> {
                return new HPanelComponent();
            }
        }
    }
}
