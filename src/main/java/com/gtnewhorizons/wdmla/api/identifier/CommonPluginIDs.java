package com.gtnewhorizons.wdmla.api.identifier;

import net.minecraft.util.ResourceLocation;

public final class CommonPluginIDs {

    public static final ResourceLocation AC_BLOCK_SHAPE = ARCHITECTURE("block_shape");
    public static final ResourceLocation AC_SHAPE_HARVEST = ARCHITECTURE("shape_harvest");

    public static final ResourceLocation FORESTRY_HARVEST = FORESTRY("tool_harvest");

    public static final ResourceLocation HC_ANIMAL_TRAP = HARVEST("animal_trap");
    public static final ResourceLocation HC_FISH_TRAP = HARVEST("fish_trap");
    public static final ResourceLocation HC_GROWTH_RATE = HARVEST("fruit_growth_rate");

    public static final ResourceLocation HO_HOE_RESULT = HO("hoe_result");
    public static final ResourceLocation HO_MILK_COOLDOWN = HO("milk_cooldown");
    public static final ResourceLocation HO_SLOWER_ANIMAL = HO("slow_animal");
    public static final ResourceLocation HO_SLOWER_CHICKEN = HO("slow_chicken");

    public static final ResourceLocation NATURA_BERRY_BUSH = NATURA("berry_bush");
    public static final ResourceLocation NATURA_CROP_HEADER = NATURA("crop_header");
    public static final ResourceLocation NATURA_GROWTH_RATE = NATURA("growth_rate");
    public static final ResourceLocation NATURA_LEAVES = NATURA("leaves");

    public static final ResourceLocation DRAWERS_CONTROLLER_STORAGE = DRAWERS("controller_storage");
    public static final ResourceLocation DRAWERS_CONTENT = DRAWERS("content");
    public static final ResourceLocation DRAWERS_PROPERTY = DRAWERS("property");

    public static final String NAMESPACE_ARCHITECTURECRAFT = "architecturecraft";
    public static final String NAMESPACE_FORESTRY = "forestry";
    public static final String NAMESPACE_HARVESTCRAFT = "harvestcraft";
    public static final String NAMESPACE_HUNGEROVERHAUL = "hungeroverhaul";
    public static final String NAMESPACE_NATURA = "natura";
    public static final String NAMESPACE_STORAGEDRAWERS = "storagedrawers";

    public static ResourceLocation ARCHITECTURE(String path) {
        return new ResourceLocation(NAMESPACE_ARCHITECTURECRAFT, path);
    }

    public static ResourceLocation FORESTRY(String path) {
        return new ResourceLocation(NAMESPACE_FORESTRY, path);
    }

    public static ResourceLocation HARVEST(String path) {
        return new ResourceLocation(NAMESPACE_HARVESTCRAFT, path);
    }

    public static ResourceLocation HO(String path) {
        return new ResourceLocation(NAMESPACE_HUNGEROVERHAUL, path);
    }

    public static ResourceLocation NATURA(String path) {
        return new ResourceLocation(NAMESPACE_NATURA, path);
    }

    public static ResourceLocation DRAWERS(String path) {
        return new ResourceLocation(NAMESPACE_STORAGEDRAWERS, path);
    }
}
