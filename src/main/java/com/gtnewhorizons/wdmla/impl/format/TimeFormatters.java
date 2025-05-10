package com.gtnewhorizons.wdmla.impl.format;

import com.gtnewhorizons.wdmla.api.Ticks;
import com.gtnewhorizons.wdmla.util.FormatUtil;
import net.minecraft.util.StatCollector;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class TimeFormatters {
    private TimeFormatters() {}

    public static String alwaysTick(int tick) {
        return FormatUtil.STANDARD.format(tick) + StatCollector.translateToLocal("hud.msg.wdmla.ticks");
    }

    public static String alwaysSecond(int tick) {
        return FormatUtil.STANDARD.format(Duration.of(tick, Ticks.INSTANCE).get(ChronoUnit.SECONDS))
                + StatCollector.translateToLocal("hud.msg.wdmla.seconds");
    }

    public static String hourMinSec(int tick) {
        Duration duration = Duration.of(tick, Ticks.INSTANCE);
        if (duration.toMinutes() < 1) {
            return FormatUtil.STANDARD.format(duration.get(ChronoUnit.SECONDS))
                    + StatCollector.translateToLocal("hud.msg.wdmla.seconds");
        } else if (duration.toHours() < 1) {
            return FormatUtil.STANDARD.format(duration.toMinutes())
                    + StatCollector.translateToLocal("hud.msg.wdmla.minutes")
                    + FormatUtil.TIME_PART.format(duration.minusMinutes(duration.toMinutes()).get(ChronoUnit.SECONDS))
                    + StatCollector.translateToLocal("hud.msg.wdmla.seconds");
        } else {
            return FormatUtil.STANDARD.format(duration.toHours())
                    + StatCollector.translateToLocal("hud.msg.wdmla.hours")
                    + FormatUtil.TIME_PART.format(duration.minusHours(duration.toHours()).toMinutes())
                    + StatCollector.translateToLocal("hud.msg.wdmla.minutes")
                    + FormatUtil.TIME_PART.format(duration.minusMinutes(duration.toMinutes()).get(ChronoUnit.SECONDS))
                    + StatCollector.translateToLocal("hud.msg.wdmla.seconds");
        }
    }
}
