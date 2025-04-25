package com.gtnewhorizons.wdmla.api.provider;

import java.util.List;

import org.jetbrains.annotations.ApiStatus;

import com.gtnewhorizons.wdmla.api.accessor.Accessor;
import com.gtnewhorizons.wdmla.api.view.ClientViewGroup;
import com.gtnewhorizons.wdmla.api.view.ViewGroup;

/**
 * Implement this to display custom content on pre-defined format on WDMla tooltips.<br>
 * Unlike normal providers, this kind of provider does not append content to tooltips directly.<br>
 * Instead, it maps the {@link ViewGroup}s which comes from {@link IServerExtensionProvider} to {@link ClientViewGroup}s.<br>
 * The mapped {@link ClientViewGroup} will then be processed in special providers for tooltips.<br>
 * You can see the example code on its derivative classes.<br>
 *
 * @param <IN> the view class that ViewGroup holds
 * @param <OUT> the view class that ClientViewGroup will hold
 */
public interface IClientExtensionProvider<IN, OUT> extends IWDMlaProvider {

    List<ClientViewGroup<OUT>> getClientGroups(Accessor accessor, List<ViewGroup<IN>> groups);

}
