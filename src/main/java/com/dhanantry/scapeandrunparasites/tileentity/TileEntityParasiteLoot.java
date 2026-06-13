/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.ItemStackHelper
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntityLockable
 *  net.minecraft.util.NonNullList
 */
package com.dhanantry.scapeandrunparasites.tileentity;

import com.dhanantry.scapeandrunparasites.container.ContainerParasiteLoot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.NonNullList;

public class TileEntityParasiteLoot
extends TileEntityLockable
implements IInventory {
    private static final int INV_SIZE = 27;
    private NonNullList<ItemStack> items = NonNullList.func_191197_a((int)27, (Object)ItemStack.field_190927_a);

    public float getFullness() {
        int total = this.getTotalSlots();
        if (total <= 0) {
            return 0.0f;
        }
        return (float)this.getUsedSlotCount() / (float)total;
    }

    public int getFullnessField() {
        return (int)Math.round((double)this.getFullness() * 1000.0);
    }

    public Container func_174876_a(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerParasiteLoot(playerInventory, this, playerIn);
    }

    public String func_174875_k() {
        return "srparasites:parasite_loot";
    }

    public int func_70302_i_() {
        return this.items.size();
    }

    public boolean func_191420_l() {
        for (ItemStack s : this.items) {
            if (s.func_190926_b()) continue;
            return false;
        }
        return true;
    }

    public ItemStack func_70301_a(int index) {
        return (ItemStack)this.items.get(index);
    }

    public ItemStack func_70298_a(int index, int count) {
        ItemStack s = ItemStackHelper.func_188382_a(this.items, (int)index, (int)count);
        this.func_70296_d();
        return s;
    }

    public ItemStack func_70304_b(int index) {
        ItemStack s = ItemStackHelper.func_188383_a(this.items, (int)index);
        this.func_70296_d();
        return s;
    }

    public void func_70299_a(int index, ItemStack stack) {
        this.items.set(index, (Object)stack);
        if (!stack.func_190926_b() && stack.func_190916_E() > this.func_70297_j_()) {
            stack.func_190920_e(this.func_70297_j_());
        }
        this.func_70296_d();
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer player) {
        if (this.field_145850_b.func_175625_s(this.field_174879_c) != this) {
            return false;
        }
        return player.func_70092_e((double)this.field_174879_c.func_177958_n() + 0.5, (double)this.field_174879_c.func_177956_o() + 0.5, (double)this.field_174879_c.func_177952_p() + 0.5) <= 64.0;
    }

    public void func_174889_b(EntityPlayer player) {
    }

    public void func_174886_c(EntityPlayer player) {
    }

    public boolean func_94041_b(int index, ItemStack stack) {
        return true;
    }

    public int func_174887_a_(int id) {
        return id == 0 ? this.getFullnessField() : 0;
    }

    public void func_174885_b(int id, int value) {
    }

    public int func_174890_g() {
        return 1;
    }

    public void func_174888_l() {
        this.items.clear();
    }

    public String func_70005_c_() {
        return "container.srparasites.parasite_loot";
    }

    public boolean func_145818_k_() {
        return false;
    }

    public NBTTagCompound func_189515_b(NBTTagCompound tag) {
        super.func_189515_b(tag);
        ItemStackHelper.func_191282_a((NBTTagCompound)tag, this.items);
        return tag;
    }

    public void func_145839_a(NBTTagCompound tag) {
        super.func_145839_a(tag);
        this.items = NonNullList.func_191197_a((int)27, (Object)ItemStack.field_190927_a);
        ItemStackHelper.func_191283_b((NBTTagCompound)tag, this.items);
    }

    public int getTotalSlots() {
        return this.func_70302_i_();
    }

    public int getFreeSlotCount() {
        int free = 0;
        for (int i = 0; i < this.items.size(); ++i) {
            if (!((ItemStack)this.items.get(i)).func_190926_b()) continue;
            ++free;
        }
        return free;
    }

    public int getUsedSlotCount() {
        return this.getTotalSlots() - this.getFreeSlotCount();
    }
}

