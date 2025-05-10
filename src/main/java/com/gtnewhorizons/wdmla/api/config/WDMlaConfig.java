package com.gtnewhorizons.wdmla.api.config;

import com.gtnewhorizons.wdmla.api.provider.IComponentProvider;
import com.gtnewhorizons.wdmla.api.provider.ITimeFormatConfigurable;
import com.gtnewhorizons.wdmla.config.WDMlaConfigImpl;
import com.gtnewhorizons.wdmla.impl.format.TimeFormattingPattern;

public interface WDMlaConfig {

    static WDMlaConfig instance() {
        return WDMlaConfigImpl._instance;
    }

    void save();

    void reloadConfig();

    boolean isProviderEnabled(IComponentProvider<?> provider);

    int getProviderPriority(IComponentProvider<?> provider);

    TimeFormattingPattern getTimeFormatter(ITimeFormatConfigurable provider);

    void setCategoryLangKey(String category, String languageKey);
}
