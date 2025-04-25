package com.gtnewhorizons.wdmla.test;

import static com.gtnewhorizons.wdmla.impl.ui.component.TooltipComponent.DEFAULT_PROGRESS_DESCRIPTION_PADDING;

import java.util.Arrays;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.ResourceLocation;

import com.gtnewhorizons.wdmla.api.Identifiers;
import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.ColorPalette;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.impl.ui.StatusHelper;
import com.gtnewhorizons.wdmla.impl.ui.ThemeHelper;
import com.gtnewhorizons.wdmla.impl.ui.component.HPanelComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.ItemComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.ProgressComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.TextComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.TooltipComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.VPanelComponent;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Padding;
import com.gtnewhorizons.wdmla.impl.ui.style.ProgressStyle;

public enum TestThemeBlockProvider implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public ResourceLocation getUid() {
        return Identifiers.TEST_THEME_BLOCK;
    }

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor) {
        if (accessor.getTileEntity() instanceof TileEntitySkull skull && skull.func_145904_a() == 1) {
            tooltip.child(StatusHelper.INSTANCE.structureIncomplete());
            tooltip.child(StatusHelper.INSTANCE.hasProblem());
            tooltip.child(StatusHelper.INSTANCE.runningFine());
            tooltip.child(StatusHelper.INSTANCE.idle());
            tooltip.child(StatusHelper.INSTANCE.workingDisabled());
            tooltip.child(StatusHelper.INSTANCE.insufficientEnergy());
            tooltip.child(StatusHelper.INSTANCE.insufficientFuel());

            return;
        }

        if (!accessor.showDetails()) {
            tooltip.text("normal");
            tooltip.child(ThemeHelper.INSTANCE.info("This is info"));
            tooltip.child(ThemeHelper.INSTANCE.title("This is title"));
            tooltip.child(ThemeHelper.INSTANCE.success("This is success"));
            tooltip.child(ThemeHelper.INSTANCE.warning("This is warning"));
            tooltip.child(ThemeHelper.INSTANCE.danger("This is danger"));
            tooltip.child(ThemeHelper.INSTANCE.failure("This is failure"));
        } else {
            tooltip.child(new TextComponent("This is 0.5 pixel off text.").padding(new Padding(-1f, 0, 0.5f, 0)));
            for (float i = 0; i < 1; i += 0.2f) {
                TooltipComponent panel = new HPanelComponent().padding(new Padding(0, 0, i, 0));
                for (int j = 0; j < 10; j++) {
                    panel.child(new ItemComponent(new ItemStack(Blocks.redstone_ore)));
                }
                tooltip.child(panel);
            }
            tooltip.child(
                    new ProgressComponent(8, 10).style(
                            new ProgressStyle().color(ColorPalette.ENERGY_FILLED, ColorPalette.ENERGY_FILLED_ALTERNATE))
                            .child(
                                    new VPanelComponent().padding(DEFAULT_PROGRESS_DESCRIPTION_PADDING)
                                            .child(new TextComponent("Test Energy: 8μI / 10μI"))));

            tooltip.child(ThemeHelper.INSTANCE.value("The answer", "42"));
            tooltip.child(
                    ThemeHelper.INSTANCE.furnaceLikeProgress(
                            Arrays.asList(new ItemStack(Items.egg)),
                            Arrays.asList(new ItemStack(Items.chicken)),
                            1,
                            8,
                            accessor.showDetails(),
                            new TextComponent("1 / 8")));
        }
    }
}
