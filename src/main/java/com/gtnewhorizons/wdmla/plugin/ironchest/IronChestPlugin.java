package com.gtnewhorizons.wdmla.plugin.ironchest;

import com.gtnewhorizons.wdmla.api.IWDMlaCommonRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaPlugin;
import com.gtnewhorizons.wdmla.api.WDMlaPlugin;
import com.gtnewhorizons.wdmla.plugin.universal.ItemStorageProvider;

import cpw.mods.ironchest.BlockIronChest;

@WDMlaPlugin(uid = "ironchest")
public class IronChestPlugin implements IWDMlaPlugin {

    @Override
    public void register(IWDMlaCommonRegistration registration) {
        registration.registerItemStorage(ItemStorageProvider.Extension.INSTANCE, BlockIronChest.class);
    }
}