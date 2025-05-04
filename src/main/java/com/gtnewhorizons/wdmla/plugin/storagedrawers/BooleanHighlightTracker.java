package com.gtnewhorizons.wdmla.plugin.storagedrawers;

import java.time.Duration;
import java.time.Instant;

//prototype for future implementation
//TODO:generic
public class BooleanHighlightTracker {

    private boolean currentValue;
    private Instant lastUpdate = Instant.MIN;
    private static final Duration DETAILED_DURATION = Duration.ofMillis(1500);

    public BooleanHighlightTracker(boolean initValue) {
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
    public boolean update(boolean newValue) {
        if (currentValue == newValue) {
            return isRecentlyUpdated();
        }
        else {
            currentValue = newValue;
            resetTimer();
            return true;
        }
    }
}
