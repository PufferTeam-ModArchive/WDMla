package com.gtnewhorizons.wdmla.example;

public enum TestMode {
    /**
     * Disable test
     */
    NONE,
    /**
     * Test WDMla component is functioning properly(see command block, pig)
     */
    WDMla,
    /**
     * Enable packet logger for packets WDMla client receives from server
     */
    PACKET,
    /**
     * Test tooltip provided by Waila Legacy api is working properly on WDMla env(see furnace)
     */
    Waila
}
