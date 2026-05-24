/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ContainerChest
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.ItemStackHelper
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntityLockableLoot
 *  net.minecraft.util.ITickable
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.AxisAlignedBB
 */
package com.subspaceparasite.block;

import com.subspaceparasite.init.SPSounds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;

public class TileEntityDermoidCyst
extends TileEntityLockableLoot
implements ITickable {
    private NonNullList<ItemStack> items = NonNullList.func_191197_a((int)27, (Object)ItemStack.field_190927_a);
    private String customName;
    private int numPlayersUsing = 0;
    private int ticksSinceSync = 0;

    protected NonNullList<ItemStack> func_190576_q() {
        return this.items;
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
        ItemStack stack = ItemStackHelper.func_188382_a(this.items, (int)index, (int)count);
        if (!stack.func_190926_b()) {
            this.func_70296_d();
        }
        return stack;
    }

    public ItemStack func_70304_b(int index) {
        return ItemStackHelper.func_188383_a(this.items, (int)index);
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

    public String func_70005_c_() {
        return this.func_145818_k_() ? this.customName : "container.dermoid_cyst";
    }

    public boolean func_145818_k_() {
        return this.customName != null && !this.customName.isEmpty();
    }

    public void func_190575_a(String name) {
        this.customName = name;
        this.markForRenderUpdate();
    }

    public String func_174875_k() {
        return "minecraft:chest";
    }

    public Container func_174876_a(InventoryPlayer inv, EntityPlayer player) {
        return new ContainerChest((IInventory)inv, (IInventory)this, player);
    }

    public boolean isNamedBoris() {
        if (this.customName == null) {
            return false;
        }
        String n = this.customName.trim();
        return "Boris".equalsIgnoreCase(n) || "Borris".equalsIgnoreCase(n);
    }

    public int getNumPlayersUsing() {
        return this.numPlayersUsing;
    }

    public void func_174889_b(EntityPlayer player) {
        if (player.func_175149_v()) {
            return;
        }
        ++this.numPlayersUsing;
        if (!this.field_145850_b.field_72995_K) {
            this.field_145850_b.func_184133_a(null, this.field_174879_c, SPSounds.FLESH_GROW, SoundCategory.BLOCKS, 10.0f, 1.0f);
            this.field_145850_b.func_175641_c(this.field_174879_c, this.func_145838_q(), 1, this.numPlayersUsing);
        }
        this.markForRenderUpdate();
    }

    public void func_174886_c(EntityPlayer player) {
        if (player.func_175149_v()) {
            return;
        }
        this.numPlayersUsing = Math.max(0, this.numPlayersUsing - 1);
        if (!this.field_145850_b.field_72995_K) {
            this.field_145850_b.func_184133_a(null, this.field_174879_c, SPSounds.FLESH_GROW, SoundCategory.BLOCKS, 10.0f, 1.0f);
            this.field_145850_b.func_175641_c(this.field_174879_c, this.func_145838_q(), 1, this.numPlayersUsing);
        }
        this.markForRenderUpdate();
    }

    public boolean func_145842_c(int id, int param) {
        if (id == 1) {
            this.numPlayersUsing = param;
            if (this.field_145850_b != null) {
                this.field_145850_b.func_175704_b(this.field_174879_c, this.field_174879_c);
            }
            return true;
        }
        return super.func_145842_c(id, param);
    }

    public void func_73660_a() {
        if (this.field_145850_b == null || this.field_145850_b.field_72995_K) {
            return;
        }
        if (++this.ticksSinceSync % 80 == 0) {
            int old = this.numPlayersUsing;
            this.numPlayersUsing = 0;
            double r = 5.0;
            AxisAlignedBB box = new AxisAlignedBB(this.field_174879_c).func_186662_g(r);
            for (EntityPlayer p : this.field_145850_b.func_72872_a(EntityPlayer.class, box)) {
                IInventory lower;
                if (!(p.field_71070_bA instanceof ContainerChest) || (lower = ((ContainerChest)p.field_71070_bA).func_85151_d()) != this) continue;
                ++this.numPlayersUsing;
            }
            if (this.numPlayersUsing != old) {
                this.field_145850_b.func_175641_c(this.field_174879_c, this.func_145838_q(), 1, this.numPlayersUsing);
                this.field_145850_b.func_175704_b(this.field_174879_c, this.field_174879_c);
            }
        }
    }

    public void func_145839_a(NBTTagCompound nbt) {
        super.func_145839_a(nbt);
        this.items = NonNullList.func_191197_a((int)this.func_70302_i_(), (Object)ItemStack.field_190927_a);
        if (!this.func_184283_b(nbt)) {
            ItemStackHelper.func_191283_b((NBTTagCompound)nbt, this.items);
        }
        if (nbt.func_150297_b("CustomName", 8)) {
            this.customName = nbt.func_74779_i("CustomName");
        }
    }

    public NBTTagCompound func_189515_b(NBTTagCompound nbt) {
        super.func_189515_b(nbt);
        if (!this.func_184282_c(nbt)) {
            ItemStackHelper.func_191282_a((NBTTagCompound)nbt, this.items);
        }
        if (this.func_145818_k_()) {
            nbt.func_74778_a("CustomName", this.customName);
        }
        return nbt;
    }

    private void markForRenderUpdate() {
        if (this.field_145850_b != null) {
            this.field_145850_b.func_175704_b(this.field_174879_c, this.field_174879_c);
            if (!this.field_145850_b.field_72995_K) {
                this.field_145850_b.func_175685_c(this.field_174879_c, this.func_145838_q(), false);
            }
        }
        this.func_70296_d();
    }
}

