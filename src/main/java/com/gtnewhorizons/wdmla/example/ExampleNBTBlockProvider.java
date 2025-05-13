package com.gtnewhorizons.wdmla.example;

import static com.gtnewhorizons.wdmla.api.ui.helper.ComponentHelper.DEFAULT_PROGRESS_DESCRIPTION_PADDING;

import java.util.Random;

import com.gtnewhorizons.wdmla.impl.ui.component.ItemComponent;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ResourceLocation;

import com.gtnewhorizons.wdmla.api.identifier.WDMlaIDs;
import com.gtnewhorizons.wdmla.api.TooltipPosition;
import com.gtnewhorizons.wdmla.api.accessor.BlockAccessor;
import com.gtnewhorizons.wdmla.api.provider.IBlockComponentProvider;
import com.gtnewhorizons.wdmla.api.provider.IServerDataProvider;
import com.gtnewhorizons.wdmla.api.ui.ITooltip;
import com.gtnewhorizons.wdmla.api.ui.sizer.IPadding;
import com.gtnewhorizons.wdmla.impl.ui.component.ProgressComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.TextComponent;
import com.gtnewhorizons.wdmla.impl.ui.component.VPanelComponent;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Padding;
import com.gtnewhorizons.wdmla.impl.ui.sizer.Size;
import com.gtnewhorizons.wdmla.impl.ui.style.RectStyle;

import mcp.mobius.waila.overlay.DisplayUtil;

public enum ExampleNBTBlockProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {

    INSTANCE;

    @Override
    public ResourceLocation getUid() {
        return WDMlaIDs.EXAMPLE_NBT_BLOCK;
    }

    @Override
    public int getDefaultPriority() {
        return TooltipPosition.BODY + 10;
    }

    //TODO: rewrite. This is very outdated version of example
    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor) {
        int cookTime = accessor.getServerData().getShort("CookTime");
        cookTime = Math.round(cookTime / 20.0f);

        ItemStack[] items = new ItemStack[3];
        NBTTagList itemsTag = accessor.getServerData().getTagList("Items", 10);

        boolean allEmpty = true;
        for (int i = 0; i < itemsTag.tagCount(); i++) {
            NBTTagCompound itemTag = itemsTag.getCompoundTagAt(i);
            byte slot = itemTag.getByte("Slot");

            if (slot >= 0 && slot < items.length) {
                items[slot] = ItemStack.loadItemStackFromNBT(itemTag);
                if (items[slot] != null) {
                    allEmpty = false;
                }
            }
        }

        if (!accessor.showDetails()) {
            return;
        }

        int random = accessor.getServerData().getInteger("random");
        tooltip.child(new TextComponent("Recieved Server Data: " + random));

        if (cookTime != 0) {
            ITooltip amountTooltip = new ProgressComponent(cookTime, 10).child(
                    new VPanelComponent().padding(DEFAULT_PROGRESS_DESCRIPTION_PADDING)
                            .child(new TextComponent("Smelting: " + cookTime + " / 10 s")));
            tooltip.child(amountTooltip);
        }

        int burnTime = accessor.getServerData().getInteger("BurnTime") / 20;
        if (burnTime > 0) {
            tooltip.horizontal().text("Burn").child(new ItemComponent(new ItemStack(Blocks.fire)).size(new Size(8, 8)))
                    .text(": " + burnTime + " " + "Seconds Remaining");
        }

        if (!allEmpty) {
            IPadding itemPadding = new Padding().vertical(2);
            ITooltip itemSection = new VPanelComponent()
                    .bgStyle(new RectStyle().backgroundColor(0x8000ffff).borderColor(0xff00ffff));
            if (items[0] != null) {
                itemSection.horizontal().child(new TextComponent("In: ").padding(itemPadding)).child(new ItemComponent(items[0]).size(new Size(10, 10)))
                        .child(new TextComponent(" " + DisplayUtil.itemDisplayNameShortFormatted(items[0])).padding(itemPadding));
            }

            if (items[2] != null) {
                itemSection.horizontal().child(new TextComponent("Out: ").padding(itemPadding)).child(new ItemComponent(items[2]).size(new Size(10, 10)))
                        .child(new TextComponent(" " + DisplayUtil.itemDisplayNameShortFormatted(items[2])).padding(itemPadding));
            }

            if (items[1] != null) {
                itemSection.horizontal().child(new TextComponent("Fuel: ").padding(itemPadding)).child(new ItemComponent(items[1]).size(new Size(10, 10)))
                        .child(new TextComponent(" " + DisplayUtil.itemDisplayNameShortFormatted(items[1])).padding(itemPadding));
            }
            tooltip.child(itemSection);
        }
    }

    @Override
    public void appendServerData(NBTTagCompound data, BlockAccessor accessor) {
        ItemStack[] dummyItemStacks = { new ItemStack(Items.potato), new ItemStack(Items.coal),
                new ItemStack(Items.baked_potato) };
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < 3; ++i) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setByte("Slot", (byte) i);
            dummyItemStacks[i].writeToNBT(nbttagcompound1);
            nbttaglist.appendTag(nbttagcompound1);
        }
        data.setTag("Items", nbttaglist);

        data.setShort("CookTime", (short) 100);
        data.setInteger("BurnTime", 5);
        data.setInteger("random", new Random().nextInt(11));
    }
}
