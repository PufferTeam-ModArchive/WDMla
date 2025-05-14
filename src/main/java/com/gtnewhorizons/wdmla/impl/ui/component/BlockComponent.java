package com.gtnewhorizons.wdmla.impl.ui.component;

import com.gtnewhorizons.wdmla.impl.ui.drawable.BlockDrawable;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Padding;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Size;

import java.util.ArrayList;

public class BlockComponent extends TooltipComponent {

    public BlockComponent(int blockX, int blockY, int blockZ) {
        super(new ArrayList<>(), new Padding(), new Size(16, 16), new BlockDrawable(blockX, blockY, blockZ));
    }
}
