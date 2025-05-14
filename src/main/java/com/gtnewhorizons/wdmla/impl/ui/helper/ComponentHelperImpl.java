package com.gtnewhorizons.wdmla.impl.ui.helper;

import com.gtnewhorizons.wdmla.api.ui.helper.ComponentHelper;
import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.ArmorComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.BlockComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.EntityComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.FluidComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.HPanelComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.HealthComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.IconComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.ItemComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.ProgressComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.RectComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.TextComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.VPanelComponent;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

public class ComponentHelperImpl implements ComponentHelper {

    public static final ComponentHelper _instance = new ComponentHelperImpl();

    private ComponentHelperImpl() {

    }

    @Override
    public IComponent text(String text) {
        return new TextComponent(text);
    }

    @Override
    public IComponent vertical() {
        return new VPanelComponent();
    }

    @Override
    public IComponent horizontal() {
        return new HPanelComponent();
    }

    @Override
    public IComponent armor(float armor, float maxArmor) {
        return new ArmorComponent(armor, maxArmor);
    }

    @Override
    public IComponent health(float health, float maxHealth) {
        return new HealthComponent(health, maxHealth);
    }

    @Override
    public IComponent block(int blockX, int blockY, int blockZ) {
        return new BlockComponent(blockX, blockY, blockZ);
    }

    @Override
    public IComponent entity(Entity entity) {
        return new EntityComponent(entity);
    }

    @Override
    public IComponent fluid(FluidStack fluid) {
        return new FluidComponent(fluid);
    }

    @Override
    public IComponent progress(long current, long max, String progressText) {
        return new ProgressComponent(current, max)
                .child(new TextComponent(progressText).padding(DEFAULT_PROGRESS_DESCRIPTION_PADDING));
    }

    @Override
    public IComponent icon(IIcon icon, ResourceLocation path) {
        return new IconComponent(icon, path);
    }

    @Override
    public IComponent item(ItemStack itemStack) {
        return new ItemComponent(itemStack);
    }

    @Override
    public IComponent progress(long current, long max) {
        return new ProgressComponent(current, max);
    }

    @Override
    public IComponent progress(float ratio) {
        return new ProgressComponent(ratio);
    }

    @Override
    public IComponent rect() {
        return new RectComponent();
    }
}
