package com.gtnewhorizons.wdmla.api.provider;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import com.gtnewhorizons.wdmla.api.accessor.Accessor;
import com.gtnewhorizons.wdmla.api.view.ClientViewGroup;
import com.gtnewhorizons.wdmla.api.view.ViewGroup;

/**
 * Implement this to display custom content on pre-defined format on WDMla tooltips.<br>
 * Unlike normal providers, this kind of provider does not write nbt data directly.<br>
 * Instead, it converts raw content to "view"s for {@link IClientExtensionProvider} to use.<br>
 * {@link ClientViewGroup} will then send the mapped views to special providers for tooltips.<br>
 * You can see the example code on its derivative classes.<br>
 *
 * @param <T> The type of data this provider sends to client
 */
public interface IServerExtensionProvider<T> extends IWDMlaProvider {

    @Nullable
    List<ViewGroup<T>> getGroups(Accessor accessor);

    /**
     * should this provider on the client side send the packet for target data request?
     * 
     * @param accessor current target info
     */
    default boolean shouldRequestData(Accessor accessor) {
        return true;
    }
}
