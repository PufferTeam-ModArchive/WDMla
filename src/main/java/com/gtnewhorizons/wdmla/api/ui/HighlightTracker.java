package com.gtnewhorizons.wdmla.api.ui;

import net.minecraft.util.MathHelper;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

public class HighlightTracker<T> {

    private T currentValue;
    private Instant lastUpdate = Instant.now().minus(DETAILED_DURATION);
    private static final Duration DETAILED_DURATION = Duration.ofMillis(1500);

    public HighlightTracker(T initValue) {
        currentValue = initValue;
    }

    public void resetTimer() {
        lastUpdate = Instant.now();
    }

    public boolean isRecentlyUpdated() {
        return Duration.between(lastUpdate, Instant.now()).compareTo(DETAILED_DURATION) < 0;
    }

    /**
     * updates the current value with the new value
     * @return does the value require highlight?
     */
    public boolean update(T newValue) {
        if (isSame(currentValue, newValue)) {
            return isRecentlyUpdated();
        }
        else {
            currentValue = newValue;
            resetTimer();
            return true;
        }
    }

    public boolean isSame(T object1, T object2) {
        return Objects.equals(object1, object2);
    }

    public float getInterpolation() {
        long elapsedMillis = Duration.between(lastUpdate, Instant.now()).toMillis();
        long totalMillis = DETAILED_DURATION.toMillis();
        return MathHelper.clamp_float((float)elapsedMillis / totalMillis, 0f, 1f);
    }

    public static class ItemStack extends HighlightTracker<net.minecraft.item.ItemStack> {

        public ItemStack(net.minecraft.item.ItemStack initValue) {
            super(initValue);
        }

        @Override
        public boolean isSame(net.minecraft.item.ItemStack object1, net.minecraft.item.ItemStack object2) {
            return net.minecraft.item.ItemStack.areItemStacksEqual(object1, object2);
        }
    }
}
