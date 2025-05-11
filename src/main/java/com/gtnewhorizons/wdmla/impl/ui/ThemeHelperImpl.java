package com.gtnewhorizons.wdmla.impl.ui;

import static mcp.mobius.waila.api.SpecialChars.ITALIC;

import java.util.List;

import com.gtnewhorizons.wdmla.api.ui.ThemeHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import com.gtnewhorizons.wdmla.api.identifier.WDMlaIDs;
import com.gtnewhorizons.wdmla.api.theme.Theme;
import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.api.ui.MessageType;
import com.gtnewhorizons.wdmla.api.config.General;
import com.gtnewhorizons.wdmla.api.config.PluginsConfig;
import com.gtnewhorizons.wdmla.api.format.TimeFormattingPattern;
import com.gtnewhorizons.wdmla.impl.ui.component.EntityComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.HPanelComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.IconComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.ItemComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.TextComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.VPanelComponent;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Padding;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Size;
import com.gtnewhorizons.wdmla.impl.ui.style.TextStyle;
import com.gtnewhorizons.wdmla.overlay.WDMlaUIIcons;
import com.gtnewhorizons.wdmla.api.identifier.VanillaIDs;
import com.gtnewhorizons.wdmla.util.FormatUtil;

import mcp.mobius.waila.overlay.DisplayUtil;
import mcp.mobius.waila.utils.ModIdentification;

/**
 * Use this class to unify common layout settings
 */
public class ThemeHelperImpl implements ThemeHelper {

    private static final int ITEM_SIZE = Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;

    public static final ThemeHelper _instance = new ThemeHelperImpl();

    private ThemeHelperImpl() {}

    @Deprecated
    @Override
    public void overrideTooltipIcon(ITooltip root, ItemStack newItemStack) {
        overrideTooltipIcon(root, newItemStack, false);
    }

    @Override
    public void overrideTooltipIcon(ITooltip root, ItemStack newItemStack, boolean overrideFancyRenderer) {
        if (!overrideFancyRenderer
                && PluginsConfig.core.defaultBlock.fancyRenderer == PluginsConfig.Core.fancyRendererMode.ALL) {
            return;
        }

        root.replaceChildWithTag(
                WDMlaIDs.ITEM_ICON,
                new ItemComponent(newItemStack).doDrawOverlay(false).tag(WDMlaIDs.ITEM_ICON));
    }

    @Override
    public void overrideTooltipTitle(ITooltip root, ItemStack newItemStack) {
        String strippedName = DisplayUtil.itemDisplayNameShortFormatted(newItemStack);
        overrideTooltipTitle(root, strippedName);
    }

    @Override
    public void overrideTooltipTitle(ITooltip root, String formattedNewName) {
        Theme theme = General.currentTheme.get();
        IComponent replacedName = new HPanelComponent().child(
                new TextComponent(formattedNewName).style(new TextStyle().color(theme.textColor(MessageType.TITLE))))
                .tag(WDMlaIDs.ITEM_NAME);
        root.replaceChildWithTag(WDMlaIDs.ITEM_NAME, replacedName);
    }

    @Override
    public void overrideEntityTooltipTitle(ITooltip root, String newName, @Nullable Entity entityMayHaveCustomName) {
        Theme theme = General.currentTheme.get();
        if (entityMayHaveCustomName instanceof EntityLiving living && living.hasCustomNameTag()) {
            newName = FormatUtil.formatNameByPixelCount(living.getCustomNameTag());
        } else {
            newName = FormatUtil.formatNameByPixelCount(newName);
        }
        IComponent replacedName = new HPanelComponent()
                .child(new TextComponent(newName).style(new TextStyle().color(theme.textColor(MessageType.TITLE))))
                .tag(WDMlaIDs.ENTITY_NAME);
        root.replaceChildWithTag(WDMlaIDs.ENTITY_NAME, replacedName);
    }

    @Override
    public void overrideEntityTooltipIcon(ITooltip root, @Nullable Entity newEntity) {
        if (PluginsConfig.core.defaultEntity.showEntity) {
            if (!PluginsConfig.core.defaultEntity.fancyRenderer && !(newEntity instanceof EntityLiving)) {
                root.replaceChildWithTag(WDMlaIDs.ENTITY, new HPanelComponent().tag(WDMlaIDs.ENTITY));
            } else {
                root.replaceChildWithTag(
                        WDMlaIDs.ENTITY,
                        new EntityComponent(newEntity).padding(new Padding(6, 0, 10, 0)).size(new Size(12, 12))
                                .tag(WDMlaIDs.ENTITY));
            }
        }
    }

    @Override
    public void overrideTooltipModName(ITooltip root, ItemStack newItemStack) {
        overrideTooltipModName(root, ModIdentification.nameFromStack(newItemStack));
    }

    @Override
    public void overrideTooltipModName(ITooltip root, String newName) {
        Theme theme = General.currentTheme.get();
        IComponent replacedModName = new TextComponent(ITALIC + newName)
                .style(new TextStyle().color(theme.textColor(MessageType.MOD_NAME))).tag(WDMlaIDs.MOD_NAME);
        root.replaceChildWithTag(WDMlaIDs.MOD_NAME, replacedModName);
    }

    @Override
    public void overrideTooltipHeader(ITooltip root, ItemStack newItemStack) {
        overrideTooltipIcon(root, newItemStack, false);
        overrideTooltipTitle(root, newItemStack);
        overrideTooltipModName(root, newItemStack);
    }

    @Override
    public IComponent info(String content) {
        return color(content, MessageType.INFO);
    }

    @Override
    public IComponent title(String content) {
        return color(content, MessageType.TITLE);
    }

    @Override
    public IComponent success(String content) {
        return color(content, MessageType.SUCCESS);
    }

    @Override
    public IComponent warning(String content) {
        return color(content, MessageType.WARNING);
    }

    @Override
    public IComponent danger(String content) {
        return color(content, MessageType.DANGER);
    }

    @Override
    public IComponent failure(String content) {
        return color(content, MessageType.FAILURE);
    }

    @Override
    public IComponent color(String content, MessageType type) {
        Theme theme = General.currentTheme.get();
        return new TextComponent(content).style(new TextStyle().color(theme.textColor(type)));
    }

    @Override
    public IComponent furnaceLikeProgress(List<ItemStack> input, List<ItemStack> output, int currentProgress,
                                          int maxProgress, boolean showDetails) {
        return furnaceLikeProgress(input, output, currentProgress, maxProgress, showDetails, null);
    }

    @Override
    public IComponent furnaceLikeProgress(List<ItemStack> input, List<ItemStack> output, int currentProgress,
                                          int maxProgress, boolean showDetails, @Nullable IComponent legacyProcessText) {
        if (!General.forceLegacy) {
            HPanelComponent hPanel = new HPanelComponent();
            for (ItemStack inputStack : input) {
                if (inputStack != null) {
                    hPanel.child(new ItemComponent(inputStack));
                }
            }
            float ratio = (float) currentProgress / maxProgress;
            hPanel.padding(new Padding().horizontal(2)).child(
                    new IconComponent(WDMlaUIIcons.FURNACE_BG, WDMlaUIIcons.FURNACE_BG.texPath).padding(new Padding())
                            .child(
                                    new IconComponent(WDMlaUIIcons.FURNACE, WDMlaUIIcons.FURNACE.texPath)
                                            .clip(0f, 0f, ratio, 1f).padding(new Padding())));
            for (ItemStack outputStack : output) {
                if (outputStack != null) {
                    hPanel.child(new ItemComponent(outputStack));
                }
            }
            return hPanel;
        } else {
            ITooltip vPanel = new VPanelComponent();
            if (showDetails) {
                for (ItemStack inputStack : input) {
                    if (inputStack != null) {
                        vPanel.horizontal()
                                .text(String.format("%s: ", StatCollector.translateToLocal("hud.msg.wdmla.in"))).child(
                                        ThemeHelper.instance().info(
                                                String.format(
                                                        "%dx %s",
                                                        inputStack.stackSize,
                                                        DisplayUtil.itemDisplayNameShortFormatted(inputStack))));
                    }
                }
                for (ItemStack outputStack : output) {
                    if (outputStack != null) {
                        vPanel.horizontal()
                                .text(String.format("%s: ", StatCollector.translateToLocal("hud.msg.wdmla.out"))).child(
                                        ThemeHelper.instance().info(
                                                String.format(
                                                        "%dx %s",
                                                        outputStack.stackSize,
                                                        DisplayUtil.itemDisplayNameShortFormatted(outputStack))));
                    }
                }
            }

            if (currentProgress != 0 && maxProgress != 0 && legacyProcessText == null) {
                legacyProcessText = ThemeHelper.instance().value(
                        StatCollector.translateToLocal("hud.msg.wdmla.progress"),
                        TimeFormattingPattern.ALWAYS_TICK.tickFormatter.format(currentProgress) + " / "
                                + TimeFormattingPattern.ALWAYS_TICK.tickFormatter.format(maxProgress));
            }

            if (legacyProcessText != null) {
                vPanel.child(legacyProcessText);
            }

            if (vPanel.childrenSize() != 0) {
                return vPanel;
            } else {
                return null;
            }
        }
    }

    @Override
    public IComponent value(String entry, String value) {
        return new HPanelComponent().text(String.format("%s: ", entry)).child(info(value));
    }

    @Override
    public ITooltip smallItem(ItemStack itemStack) {
        return new ItemComponent(itemStack).doDrawOverlay(false).size(new Size(ITEM_SIZE, ITEM_SIZE));
    }

    @Override
    public IComponent itemStackFullLine(ItemStack stack) {
        String strippedName = DisplayUtil.stripSymbols(DisplayUtil.itemDisplayNameShortFormatted(stack));
        TextComponent name = new TextComponent(strippedName);
        ITooltip hPanel = new HPanelComponent().child(smallItem(stack));
        String s = String.valueOf(stack.stackSize); // TODO: unit format
        return hPanel.text(s).text(StatCollector.translateToLocal("hud.msg.wdmla.item.count") + StringUtils.EMPTY)
                .child(name);
    }

    @Override
    public IComponent growthValue(float growthValue) {
        if (growthValue < 1) {
            return ThemeHelper.instance().value(
                    StatCollector.translateToLocal("hud.msg.wdmla.growth"),
                    FormatUtil.PERCENTAGE_STANDARD.format(growthValue)).tag(VanillaIDs.GROWTH_RATE);
        } else {
            return new HPanelComponent()
                    .text(String.format("%s: ", StatCollector.translateToLocal("hud.msg.wdmla.growth")))
                    .child(
                            ThemeHelper.instance().success(
                                    String.format("%s", StatCollector.translateToLocal("hud.msg.wdmla.mature"))))
                    .tag(VanillaIDs.GROWTH_RATE);
        }
    }
}
