package com.gtnewhorizons.wdmla.plugin.core;

import static mcp.mobius.waila.api.SpecialChars.*;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

import com.gtnewhorizons.wdmla.api.Identifiers;
import com.gtnewhorizons.wdmla.api.TooltipPosition;
import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.theme.Theme;
import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.api.ui.MessageType;
import com.gtnewhorizons.wdmla.api.config.General;
import com.gtnewhorizons.wdmla.api.config.PluginsConfig;
import com.gtnewhorizons.wdmla.impl.ui.ThemeHelper;
import com.gtnewhorizons.wdmla.impl.ui.component.BlockComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.HPanelComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.ItemComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.TextComponent;
import com.gtnewhorizons.wdmla.impl.ui.style.TextStyle;
import com.gtnewhorizons.wdmla.util.FormatUtil;
import com.gtnewhorizons.wdmla.wailacompat.RayTracingCompat;

import mcp.mobius.waila.overlay.DisplayUtil;
import mcp.mobius.waila.utils.ModIdentification;

public enum DefaultBlockInfoProvider implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public ResourceLocation getUid() {
        return Identifiers.DEFAULT_BLOCK;
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.DEFAULT_INFO;
    }

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor) {
        // step 1: check whether waila has custom Wailastack or not
        ItemStack overrideStack = RayTracingCompat.INSTANCE.getWailaStack(accessor.getHitResult());

        // step 2: construct an actual icon
        ITooltip row = tooltip.horizontal();
        ItemStack itemStack = overrideStack != null ? overrideStack : accessor.getItemForm();
        if (PluginsConfig.core.defaultBlock.showIcon) {
            if (PluginsConfig.core.defaultBlock.fancyRenderer == PluginsConfig.Core.fancyRendererMode.ALL
                    || (PluginsConfig.core.defaultBlock.fancyRenderer == PluginsConfig.Core.fancyRendererMode.FALLBACK
                            && itemStack.getItem() == null)) {
                row.child(
                        new BlockComponent(
                                accessor.getHitResult().blockX,
                                accessor.getHitResult().blockY,
                                accessor.getHitResult().blockZ).tag(Identifiers.ITEM_ICON));
            } else {
                row.child(new ItemComponent(itemStack).doDrawOverlay(false).tag(Identifiers.ITEM_ICON));
            }
        }

        ITooltip row_vertical = row.vertical();
        if (PluginsConfig.core.defaultBlock.showBlockName) {
            String itemName;
            if (accessor.getServerData().hasKey("CustomName")) {
                String rawName = accessor.getServerData().getString("CustomName");
                itemName = EnumChatFormatting.ITALIC + FormatUtil.formatNameByPixelCount(rawName);
            } else {
                itemName = DisplayUtil.itemDisplayNameShortFormatted(itemStack);
            }
            ITooltip title = row_vertical.horizontal();
            IComponent nameComponent = ThemeHelper.INSTANCE.title(itemName).tag(Identifiers.ITEM_NAME);
            title.child(nameComponent).child(new HPanelComponent() {

                @Override
                public void tick(float x, float y) {
                    if (General.alignIconRightTop) {
                        IComponent icon = row.getChildWithTag(Identifiers.ITEM_ICON);
                        IComponent name = title.getChildWithTag(Identifiers.ITEM_NAME);
                        // align right
                        x += Math.max(
                                tooltip.getWidth() - (icon != null ? icon.getWidth() : 0)
                                        - (name != null ? name.getWidth() : 0)
                                        - getWidth()
                                        - General.currentTheme.get().defaultSpacing * 2,
                                0);
                    }
                    super.tick(x, y);
                }
            }.tag(Identifiers.TARGET_NAME_ROW));
        }
        String modName = ModIdentification.nameFromStack(itemStack);
        if (PluginsConfig.core.defaultBlock.showModName) {
            Theme theme = General.currentTheme.get();
            if (modName != null) {
                row_vertical.child(
                        new TextComponent(ITALIC + modName)
                                .style(new TextStyle().color(theme.textColor(MessageType.MOD_NAME)))
                                .tag(Identifiers.MOD_NAME));
            } else {
                // reserve for replacement
                row_vertical.child(
                        new TextComponent("").style(new TextStyle().color(theme.textColor(MessageType.MOD_NAME)))
                                .tag(Identifiers.MOD_NAME));
            }
        }
    }

    @Override
    public boolean isPriorityFixed() {
        return true;
    }
}
