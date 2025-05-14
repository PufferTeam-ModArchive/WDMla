package com.gtnewhorizons.wdmla.plugin.core;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import com.gtnewhorizons.wdmla.api.identifier.WDMlaIDs;
import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.api.ui.helper.ThemeHelper;
import com.gtnewhorizons.wdmla.util.FormatUtil;

public enum EnchantmentPowerProvider implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(IComponent tooltip, BlockAccessor accessor) {
        float power = accessor.getBlock().getEnchantPowerBonus(accessor.getPlayer().worldObj, 0, 0, 0);
        if (power > 0) {
            tooltip.child(
                    ThemeHelper.instance().value(
                            StatCollector.translateToLocal("hud.msg.wdmla.enchantment.power"),
                            FormatUtil.STANDARD.format(power)).tag(WDMlaIDs.ENCHANTMENT_POWER));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return WDMlaIDs.ENCHANTMENT_POWER;
    }
}
