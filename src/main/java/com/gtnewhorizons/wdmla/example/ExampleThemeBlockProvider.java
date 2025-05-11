package com.gtnewhorizons.wdmla.example;

import static com.gtnewhorizons.wdmla.impl.ui.component.TooltipComponent.DEFAULT_PROGRESS_DESCRIPTION_PADDING;

import java.util.Arrays;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.ResourceLocation;

import com.gtnewhorizons.wdmla.api.identifier.WDMlaIDs;
import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.ui.ColorPalette;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.api.ui.StatusHelper;
import com.gtnewhorizons.wdmla.api.ui.ThemeHelper;
import com.gtnewhorizons.wdmla.impl.ui.component.HPanelComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.ItemComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.ProgressComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.TextComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.TooltipComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.VPanelComponent;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Padding;
import com.gtnewhorizons.wdmla.impl.ui.style.ProgressStyle;

public enum ExampleThemeBlockProvider implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public ResourceLocation getUid() {
        return WDMlaIDs.EXAMPLE_THEME_BLOCK;
    }

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor) {
        if (accessor.getTileEntity() instanceof TileEntitySkull skull && skull.func_145904_a() == 1) {
            tooltip.child(StatusHelper.instance().structureIncomplete());
            tooltip.child(StatusHelper.instance().hasProblem());
            tooltip.child(StatusHelper.instance().runningFine());
            tooltip.child(StatusHelper.instance().idle());
            tooltip.child(StatusHelper.instance().workingDisabled());
            tooltip.child(StatusHelper.instance().insufficientEnergy());
            tooltip.child(StatusHelper.instance().insufficientFuel());

            return;
        }

        if (!accessor.showDetails()) {
            tooltip.text("normal");
            tooltip.child(ThemeHelper.instance().info("This is info"));
            tooltip.child(ThemeHelper.instance().title("This is title"));
            tooltip.child(ThemeHelper.instance().success("This is success"));
            tooltip.child(ThemeHelper.instance().warning("This is warning"));
            tooltip.child(ThemeHelper.instance().danger("This is danger"));
            tooltip.child(ThemeHelper.instance().failure("This is failure"));
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

            tooltip.child(ThemeHelper.instance().value("The answer", "42"));
            tooltip.child(
                    ThemeHelper.instance().furnaceLikeProgress(
                            Arrays.asList(new ItemStack(Items.egg)),
                            Arrays.asList(new ItemStack(Items.chicken)),
                            1,
                            8,
                            accessor.showDetails(),
                            new TextComponent("1 / 8")));
        }
    }
}
