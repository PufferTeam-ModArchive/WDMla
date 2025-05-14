package com.gtnewhorizons.wdmla.api.ui.helper;

import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.api.ui.sizer.IPadding;
import com.gtnewhorizons.wdmla.impl.ui.helper.ComponentHelperImpl;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Padding;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

/**
 * Allows accessing raw component from api package
 */
public interface ComponentHelper {

    IPadding DEFAULT_PROGRESS_DESCRIPTION_PADDING = new Padding(2, 1, 2, 3);

    static ComponentHelper instance() {
        return ComponentHelperImpl._instance;
    }

    ITooltip text(String text);

    ITooltip vertical();

    ITooltip horizontal();

    ITooltip armor(float armor, float maxArmor);

    ITooltip health(float health, float maxHealth);

    ITooltip block(int blockX, int blockY, int blockZ);

    ITooltip entity(Entity entity);

    ITooltip fluid(FluidStack fluid);

    ITooltip progress(long current, long max, String progressText);

    ITooltip icon(IIcon icon, ResourceLocation path);

    ITooltip item(ItemStack itemStack);

    ITooltip progress(long current, long max);

    ITooltip progress(float ratio);

    ITooltip rect();
}
