package com.gtnewhorizons.wdmla.api.view;

import static com.gtnewhorizons.wdmla.api.ui.helper.ComponentHelper.DEFAULT_PROGRESS_DESCRIPTION_PADDING;

import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StringUtils;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import com.gtnewhorizons.wdmla.api.theme.Theme;
import com.gtnewhorizons.wdmla.api.ui.ComponentAlignment;
import com.gtnewhorizons.wdmla.api.ui.IComponent;
import com.gtnewhorizons.wdmla.api.ui.MessageType;
import com.gtnewhorizons.wdmla.api.config.General;
import com.gtnewhorizons.wdmla.impl.ui.component.HPanelComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.ProgressComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.RectComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.TextComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.VPanelComponent;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Size;
import com.gtnewhorizons.wdmla.impl.ui.style.PanelStyle;
import com.gtnewhorizons.wdmla.impl.ui.style.ProgressStyle;
import com.gtnewhorizons.wdmla.impl.ui.style.RectStyle;

/**
 * The client side collections of views that is processed from ViewGroup.
 */
public class ClientViewGroup<T> {

    public final List<T> views;
    /**
     * The title of the view group which is rendered on the top section of the group.
     */
    @Nullable
    public String title;
    /**
     * Used to decide the color of a progress bar across the view group.
     */
    @ApiStatus.Experimental
    public MessageType messageType = MessageType.NORMAL;
    /**
     * Used to render a progress bar across the view group (0~1).
     */
    @ApiStatus.Experimental
    public float boxProgress;
    @Nullable
    public NBTTagCompound extraData;

    public ClientViewGroup(List<T> views) {
        this.views = views;
    }

    public static <IN, OUT> List<ClientViewGroup<OUT>> map(List<ViewGroup<IN>> groups, Function<IN, OUT> itemFactory,
            @Nullable BiConsumer<ViewGroup<IN>, ClientViewGroup<OUT>> clientGroupDecorator) {
        return groups.stream().map($ -> {
            ClientViewGroup<OUT> group = new ClientViewGroup<>(
                    $.views.stream().map(itemFactory).filter(Objects::nonNull).collect(Collectors.toList()));
            NBTTagCompound data = $.extraData;
            if (data != null) {
                group.boxProgress = data.getFloat("Progress");
                String messageTypeString = data.getString("MessageType");
                group.messageType = !StringUtils.isNullOrEmpty(messageTypeString)
                        ? MessageType.valueOf(messageTypeString)
                        : MessageType.NORMAL;
            }
            if (clientGroupDecorator != null) {
                clientGroupDecorator.accept($, group);
            }
            group.extraData = data;
            return group;
        }).collect(Collectors.toList());
    }

    public static <T> void tooltip(IComponent tooltip, List<ClientViewGroup<T>> groups, boolean renderGroup,
                                   BiConsumer<IComponent, ClientViewGroup<T>> consumer) {
        for (ClientViewGroup<T> group : groups) {
            consumer.accept(tooltip, group);
            if (renderGroup && group.boxProgress > 0 && group.boxProgress < 1) {
                // TODO:overlap progress bar with item group
                IComponent content = new TextComponent(String.format("%d%%", (int) (group.boxProgress * 100)));
                tooltip.child(
                        new ProgressComponent(group.boxProgress)
                                .style(
                                        new ProgressStyle()
                                                .singleColor(General.currentTheme.get().textColor(group.messageType)))
                                .child(
                                        new VPanelComponent().padding(DEFAULT_PROGRESS_DESCRIPTION_PADDING)
                                                .child(content)));
            }
        }
    }

    public boolean shouldRenderGroup() {
        return title != null || boxProgress > 0;
    }

    public void renderHeader(IComponent tooltip) {
        if (title != null) {
            Theme theme = General.currentTheme.get();
            IComponent hPanel = new HPanelComponent().style(new PanelStyle().alignment(ComponentAlignment.CENTER));
            hPanel.child(
                    new RectComponent().style(new RectStyle().backgroundColor(theme.textColor(MessageType.NORMAL)))
                            .size(new Size(20, 1)));
            hPanel.child(new TextComponent(title).scale(0.6f));
            hPanel.child(
                    new RectComponent().style(new RectStyle().backgroundColor(theme.textColor(MessageType.NORMAL)))
                            .size(new Size(30, 1)));
            tooltip.child(hPanel);
        }
    }
}
