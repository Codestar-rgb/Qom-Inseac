/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.items.CapabilityItemHandler
 *  net.minecraftforge.items.IItemHandler
 *  net.minecraftforge.items.IItemHandlerModifiable
 *  net.minecraftforge.items.SlotItemHandler
 */
package com.dhanantry.scapeandrunparasites.container;

import com.dhanantry.scapeandrunparasites.item.ItemModule;
import com.dhanantry.scapeandrunparasites.tileentity.TileEntityRelayController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.SlotItemHandler;

public class ScannerContainer
extends Container {
    private final TileEntityRelayController te;
    private final boolean clientFallback;

    public ScannerContainer(InventoryPlayer playerInv, TileEntityRelayController te) {
        boolean fallback;
        this.te = te;
        Object handler = (IItemHandler)te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        this.clientFallback = fallback = handler == null;
        if (fallback) {
            handler = new ReadonlyClientHandler();
        }
        IItemHandler finalHandler = handler;
        this.func_75146_a((Slot)new SlotItemHandler(finalHandler, 0, 80, 35){

            public boolean func_75214_a(ItemStack stack) {
                if (ScannerContainer.this.clientFallback) {
                    return false;
                }
                return stack != null && !stack.func_190926_b() && stack.func_77973_b() instanceof ItemModule;
            }

            public int func_75219_a() {
                return 1;
            }

            public boolean func_82869_a(EntityPlayer playerIn) {
                return !ScannerContainer.this.clientFallback && super.func_82869_a(playerIn);
            }
        });
        int xStart = 8;
        int yStart = 84;
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.func_75146_a(new Slot((IInventory)playerInv, col + row * 9 + 9, xStart + col * 18, yStart + row * 18));
            }
        }
        for (int i = 0; i < 9; ++i) {
            this.func_75146_a(new Slot((IInventory)playerInv, i, xStart + i * 18, yStart + 58));
        }
    }

    public boolean func_75145_c(EntityPlayer playerIn) {
        double cz;
        double cy;
        if (this.te.func_145831_w() == null || this.te.func_145831_w().func_175625_s(this.te.func_174877_v()) != this.te) {
            return false;
        }
        double cx = (double)this.te.func_174877_v().func_177958_n() + 0.5;
        return playerIn.func_70092_e(cx, cy = (double)this.te.func_174877_v().func_177956_o() + 0.5, cz = (double)this.te.func_174877_v().func_177952_p() + 0.5) <= 64.0;
    }

    public ItemStack func_82846_b(EntityPlayer playerIn, int index) {
        if (this.clientFallback) {
            return ItemStack.field_190927_a;
        }
        ItemStack ret = ItemStack.field_190927_a;
        Slot slot = (Slot)this.field_75151_b.get(index);
        if (slot != null && slot.func_75216_d()) {
            ItemStack in = slot.func_75211_c();
            ret = in.func_77946_l();
            if (index == 0) {
                if (!this.func_75135_a(in, 1, this.field_75151_b.size(), true)) {
                    return ItemStack.field_190927_a;
                }
            } else if (in.func_77973_b() instanceof ItemModule) {
                if (!this.func_75135_a(in, 0, 1, false)) {
                    return ItemStack.field_190927_a;
                }
            } else {
                return ItemStack.field_190927_a;
            }
            if (in.func_190926_b()) {
                slot.func_75215_d(ItemStack.field_190927_a);
            } else {
                slot.func_75218_e();
            }
        }
        return ret;
    }

    private static final class ReadonlyClientHandler
    implements IItemHandlerModifiable {
        private final ItemStack[] slots = new ItemStack[]{ItemStack.field_190927_a};

        private ReadonlyClientHandler() {
        }

        public int getSlots() {
            return 1;
        }

        public ItemStack getStackInSlot(int slot) {
            return this.slots[0];
        }

        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            return stack;
        }

        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            return ItemStack.field_190927_a;
        }

        public int getSlotLimit(int slot) {
            return 1;
        }

        public boolean isItemValid(int slot, ItemStack stack) {
            return stack != null && !stack.func_190926_b() && stack.func_77973_b() instanceof ItemModule;
        }

        public void setStackInSlot(int slot, ItemStack stack) {
            this.slots[0] = stack;
        }
    }
}

