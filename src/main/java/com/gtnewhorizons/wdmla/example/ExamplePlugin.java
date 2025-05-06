package com.gtnewhorizons.wdmla.example;

import net.minecraft.block.BlockCommandBlock;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockHay;
import net.minecraft.block.BlockSign;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;

import com.gtnewhorizons.wdmla.api.IWDMlaClientRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaCommonRegistration;
import com.gtnewhorizons.wdmla.api.IWDMlaPlugin;

public class ExamplePlugin implements IWDMlaPlugin {

    @Override
    public void register(IWDMlaCommonRegistration registration) {
        registration.registerBlockDataProvider(ExampleNBTBlockProvider.INSTANCE, BlockCommandBlock.class);
        registration.registerEntityDataProvider(ExampleEntityProvider.INSTANCE, EntityPig.class);
        registration.registerItemStorage(ExampleItemStorageProvider.INSTANCE, BlockDispenser.class);
        registration.registerFluidStorage(ExampleFluidStorageProvider.INSTANCE, EntitySheep.class);
        registration.registerProgress(ExampleProgressProvider.INSTANCE, BlockSign.class);
    }

    @Override
    public void registerClient(IWDMlaClientRegistration registration) {
        registration.registerBlockComponent(ExampleHeaderProvider.INSTANCE, BlockCommandBlock.class);
        registration.registerBlockComponent(ExampleNBTBlockProvider.INSTANCE, BlockCommandBlock.class);
        registration.registerBlockComponent(ExampleThemeBlockProvider.INSTANCE, BlockStoneSlab.class);
        registration.registerBlockComponent(ExampleThemeBlockProvider.INSTANCE, BlockSkull.class);
        registration.registerEntityComponent(ExampleEntityProvider.INSTANCE, EntityPig.class);
        registration.registerItemStorageClient(ExampleItemStorageProvider.INSTANCE);
        registration.registerFluidStorageClient(ExampleFluidStorageProvider.INSTANCE);
        registration.registerProgressClient(ExampleProgressProvider.INSTANCE);
        registration.registerHarvest(ExampleHarvestHandler.INSTANCE, BlockHay.class);
    }
}
