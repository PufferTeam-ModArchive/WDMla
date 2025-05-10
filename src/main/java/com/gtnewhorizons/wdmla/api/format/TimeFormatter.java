package com.gtnewhorizons.wdmla.api.format;

@FunctionalInterface
public interface TimeFormatter {
    String format(int ticks);
}