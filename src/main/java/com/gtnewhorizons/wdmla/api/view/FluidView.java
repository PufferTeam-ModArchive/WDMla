package com.gtnewhorizons.wdmla.api.view;

import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

import org.jetbrains.annotations.Nullable;

/**
 * This is a Fluid Tank wrapper which is used in client Waila fluid storage view.<br>
 */
public class FluidView {

    /**
     * This is only used to render Fluid Stack icon on tank. <br>
     * Do not expect the other states of fluid is correct here.
     */
    @Nullable
    public FluidStack overlay;
    public long current;
    public long max;
    @Nullable
    public String fluidName;
    /**
     * the override text or any component to be displayed instead of fluid name
     */
    @Nullable
    public ITooltip description;
    /**
     * If true, the progress bar will have vertical stripes
     */
    public boolean hasScale;

    public FluidView(@Nullable FluidStack overlay) {
        this.overlay = overlay;
    }

    @Nullable
    public static FluidView readDefault(Data tank) {
        if (tank.capacity <= 0) {
            return null;
        }
        FluidStack fluidObject = tank.fluid;
        FluidView fluidView = new FluidView(fluidObject);
        long amount;
        if (fluidObject == null) {
            amount = 0;
            fluidView.fluidName = null;
        } else {
            amount = fluidObject.amount;
            fluidView.fluidName = fluidObject.getLocalizedName();
        }
        fluidView.current = amount;
        fluidView.max = tank.capacity;
        fluidView.hasScale = false;
        return fluidView;
    }

    /**
     * The Fluid Tank data representation for synchronization
     */
    public static class Data {

        public final FluidStack fluid;
        public final long capacity;

        public Data(FluidStack fluid, long capacity) {
            this.fluid = fluid;
            this.capacity = capacity;
        }

        public static NBTTagCompound encode(Data data) {
            NBTTagCompound encoded = new NBTTagCompound();
            if (data.fluid != null) {
                data.fluid.writeToNBT(encoded);
            }
            encoded.setLong("capacity", data.capacity);
            return encoded;
        }

        public static Data decode(NBTTagCompound nbt) {
            return new Data(FluidStack.loadFluidStackFromNBT(nbt), nbt.getLong("capacity"));
        }
    }
}
