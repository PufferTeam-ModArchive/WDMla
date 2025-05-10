package com.gtnewhorizons.wdmla.api.format;

import java.time.temporal.ChronoUnit;

import com.gtnewhorizons.wdmla.impl.format.TimeFormatters;
import com.gtnewhorizons.wdmla.util.FormatUtil;

/**
 * Tries to format time unit into specified time unit with the help of {@link ChronoUnit} and {@link FormatUtil}
 */
public enum TimeFormattingPattern {

    /**
     * 123,456t
     */
    ALWAYS_TICK(TimeFormatters::alwaysTick),

    /**
     * 7,890s
     */
    ALWAYS_SECOND(TimeFormatters::alwaysSecond),

    /**
     * 1h02m03s
     */
    HOUR_MIN_SEC(TimeFormatters::hourMinSec);

    public final TimeFormatter tickFormatter;

    TimeFormattingPattern(TimeFormatter tickFormatter) {
        this.tickFormatter = tickFormatter;
    }
}
