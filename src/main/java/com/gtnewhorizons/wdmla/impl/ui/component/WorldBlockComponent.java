package com.gtnewhorizons.wdmla.impl.ui.component;

import com.gtnewhorizons.wdmla.impl.ui.drawable.WorldBlockDrawable;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Padding;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Size;

public class WorldBlockComponent extends Component {

    public WorldBlockComponent(int blockX, int blockY, int blockZ) {
        super(new Padding(), new Size(16, 16), new WorldBlockDrawable(blockX, blockY, blockZ));
    }
}
