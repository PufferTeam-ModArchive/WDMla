package com.gtnewhorizons.wdmla.api.ui;

import net.minecraft.util.ResourceLocation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// spotless:off
/**
 * Base UI Component.<br>
 * How to implement a simple component without considering child element:<br>
 * <ul>
 * <li>Prepare the custom style class like {@link com.gtnewhorizons.wdmla.api.ui.style.ITextStyle} to decide what parameter is required to construct ui element</li>
 * <li>Prepare the {@link IDrawable} class.
 * This class will call rendering class to render the ui, based on provided style and {@link com.gtnewhorizons.wdmla.api.ui.sizer.IArea} information</li>
 * <li>Implement {@link com.gtnewhorizons.wdmla.impl.ui.component.TooltipComponent} class. Call the foreground(drawable) with provided condition in tick method.</li>
 * </ul>
 * Full Example code:<br>
 * <pre>
 * {@code
 * public class SimpleTextComponent extends Component {
 *
 *     protected ITextStyle style;
 *
 *     public SimpleTextComponent(String text) {
 *         super(new Padding(), new TextSize(text), new TextDrawable(text));
 *         this.style = new TextStyle();
 *     }
 *
 *     public SimpleTextComponent style(ITextStyle style) {
 *         ((TextDrawable) this.foreground).style(style);
 *         this.style = style;
 *         return this;
 *     }
 *
 *     @Override
 *     public SimpleTextComponent size(@NotNull ISize size) {
 *         throw new IllegalArgumentException("You can't set the size of TextComponent!");
 *     }
 *
 *     @Override
 *     public void tick(int x, int y) {
 *         foreground.draw(new Area(x + padding.getLeft(), y + padding.getTop(), width, height));
 *     }
 * }
 * }
 * </pre>
 */
// spotless:on
public interface IComponent {

    /**
     * Client render tick event. Call everything that consists UI component.
     *
     * @param x x position in the minecraft screen coordinate
     * @param y y position in the minecraft screen coordinate
     */
    void tick(float x, float y);

    /**
     * Must return positive value for rendering. If you want a negative sized element for layout, use
     * {@link com.gtnewhorizons.wdmla.api.ui.sizer.IPadding}.
     *
     * @return the ui component width.
     */
    float getWidth();

    /**
     * Must return positive value for rendering. If you want a negative sized element for layout, use
     * {@link com.gtnewhorizons.wdmla.api.ui.sizer.IPadding}.
     *
     * @return the ui component height.
     */
    float getHeight();

    /**
     * Applies custom identifier of resource location to component for later lookup purpose.<br>
     * If any component has potential to be used or replaced by other providers, appending a new tag is the best
     * option.<br>
     * Only one tag can be applied to component for now.
     *
     * @param tag unique identifier of new or existing tag
     * @return this component object
     */
    IComponent tag(ResourceLocation tag);

    /**
     * get the tag of this component.
     *
     * @return the unique tag.
     */
    ResourceLocation getTag();

    // spotless:off
    /**
     * Appends any component to this tooltip as child.<br>
     * Child component behaves same as normal component, except it can be searched from parent with
     * {@link IComponent#getChildWithTag(ResourceLocation)}.<br>
     * Its position might be affected by the parent position and setting.<br>
     * Any tooltip is (mostly) the child of {@link com.gtnewhorizons.wdmla.impl.ui.component.RootComponent}. <br>
     * It can be chained like this:
     * 
     * <pre>
     * {@code
     *             tooltip.child(new HPanelComponent().text("example1").item(new ItemStack(Items.apple)))
     *                     .child(new ProgressComponent(10, 100))
     *                     .child(new ArmorComponent(10));
     * }
     * </pre>
     * 
     * @param child The component which will be the child of this component
     * @return this tooltip instance
     */
    //spotless:on
    IComponent child(@NotNull IComponent child);

    /**
     * @return the count of children object appended to this tooltip
     */
    int childrenSize();

    /**
     * Clears all children appended to this tooltip.
     */
    IComponent clear();

    /**
     * Search children inside this component with a tag. The target must have a tag applied with
     * {@link IComponent#tag(ResourceLocation)}.
     * 
     * @param tag the identifier to find a child
     * @return the first component found
     */
    @Nullable
    IComponent getChildWithTag(ResourceLocation tag);

    /**
     * Replace a child inside this component with a specific tag.
     * 
     * @param tag      the identifier to find a child
     * @param newChild the new child to replace. I strongly recommend give it the same tag with previous one
     * @return The replacement was succeeded or not. If the child doesn't exist it will always return false
     */
    boolean replaceChildWithTag(ResourceLocation tag, IComponent newChild);

    //shortcuts for frequently used components
    IComponent text(String text);

    IComponent vertical();

    IComponent horizontal();
}
