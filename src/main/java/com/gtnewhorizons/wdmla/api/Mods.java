package com.gtnewhorizons.wdmla.api;

import java.util.function.Predicate;

import org.jetbrains.annotations.ApiStatus;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.versioning.ArtifactVersion;
import cpw.mods.fml.common.versioning.DefaultArtifactVersion;
import mcp.mobius.waila.Waila;

/**
 * Mods list that WDMla has integration
 */
@ApiStatus.Internal
public enum Mods {

    CREATIVEBLOCKS("CreativeBlocks", null),
    GREGTECH("gregtech", version -> !Loader.isModLoaded("gregapi")), // ProcessedVersion of GregTech is always
                                                                     // "MC1.7.10"
    IGUANATWEAKS("IguanaTweaksTConstruct", null),
    TCONSTUCT("TConstruct", null),
    NOTENOUGHITEMS("NotEnoughItems",
            version -> new DefaultArtifactVersion("2.7.29-GTNH").compareTo(version) <= 0),
    FORGEMULTIPARTS("ForgeMultipart", null),
    NATURA("Natura", null, "mods.natura.plugins.waila.WailaRegistrar.wailaCallback")

    //spotless: off
    ;
    //spotless: on

    public final String modID;

    /**
     * WDMla works without any external content mod. <br>
     * Not loading old version of mods can solve class name issue, will help greatly at outside GTNH
     */
    public final Predicate<ArtifactVersion> otherRequirement;
    private Boolean loaded;
    /**
     * If the config is enabled, WDMla will disable this registration method provided by IMC message from the mod,<br>
     * to avoid duplicated tooltips implementation registered.
     */
    public final String blacklistRegistrationMethodName;

    Mods(String modID, Predicate<ArtifactVersion> otherRequirement) {
        this.modID = modID;
        this.otherRequirement = otherRequirement;
        this.blacklistRegistrationMethodName = null;
    }

    Mods(String modID, Predicate<ArtifactVersion> otherRequirement, String blacklistRegistrationMethodName) {
        this.modID = modID;
        this.otherRequirement = otherRequirement;
        this.blacklistRegistrationMethodName = blacklistRegistrationMethodName;
    }

    /**
     * @return the mod is active with version requirement satisfied or not
     */
    public boolean isLoaded() {
        if (this.loaded == null) {
            if (!Loader.isModLoaded(modID)) {
                this.loaded = false;
            } else {
                if (otherRequirement != null) {
                    ArtifactVersion version = Loader.instance().getIndexedModList().get(modID).getProcessedVersion();
                    if (otherRequirement.test(version)) {
                        this.loaded = true;
                    } else {
                        Waila.log.info(
                                String.format(
                                        "Skipped loading %s Compatibility classes due to incompatibility. Loaded version: %s",
                                        modID,
                                        version.getVersionString()));
                        this.loaded = false;
                    }
                } else {
                    this.loaded = true;
                }
            }
        }

        return this.loaded;
    }
}
