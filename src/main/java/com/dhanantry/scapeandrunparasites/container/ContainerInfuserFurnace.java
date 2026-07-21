package com.dhanantry.scapeandrunparasites.container;

import com.dhanantry.scapeandrunparasites.tileentity.TileEntityInfuserFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceOutput;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerInfuserFurnace extends Container {
   private final TileEntityInfuserFurnace te;
   private int burnTime;
   private int currentBurnTime;
   private int cookSmelt;
   private int cookInfuse;

   public ContainerInfuserFurnace(InventoryPlayer playerInv, TileEntityInfuserFurnace te, EntityPlayer player) {
      this.te = te;
      this.func_75146_a(new Slot(te, 0, 56, 17));
      this.func_75146_a(new Slot(te, 1, 56, 53) {
         public boolean func_75214_a(ItemStack stack) {
            return TileEntityFurnace.func_145954_b(stack);
         }
      });
      this.func_75146_a(new Slot(te, 3, 30, 35));
      this.func_75146_a(new SlotFurnaceOutput(player, te, 4, 140, 53));
      this.func_75146_a(new SlotFurnaceOutput(player, te, 5, 116, 35));
      this.func_75146_a(new SlotFurnaceOutput(player, te, 2, 140, 17));

      for (int row = 0; row < 3; row++) {
         for (int col = 0; col < 9; col++) {
            this.func_75146_a(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
         }
      }

      for (int col = 0; col < 9; col++) {
         this.func_75146_a(new Slot(playerInv, col, 8 + col * 18, 142));
      }
   }

   public void func_75132_a(IContainerListener listener) {
      super.func_75132_a(listener);
      listener.func_175173_a(this, this.te);
   }

   public void func_75142_b() {
      super.func_75142_b();

      for (IContainerListener l : this.field_75149_d) {
         if (this.burnTime != this.te.func_174887_a_(0)) {
            l.func_71112_a(this, 0, this.te.func_174887_a_(0));
         }

         if (this.currentBurnTime != this.te.func_174887_a_(1)) {
            l.func_71112_a(this, 1, this.te.func_174887_a_(1));
         }

         if (this.cookSmelt != this.te.func_174887_a_(2)) {
            l.func_71112_a(this, 2, this.te.func_174887_a_(2));
         }

         if (this.cookInfuse != this.te.func_174887_a_(3)) {
            l.func_71112_a(this, 3, this.te.func_174887_a_(3));
         }
      }

      this.burnTime = this.te.func_174887_a_(0);
      this.currentBurnTime = this.te.func_174887_a_(1);
      this.cookSmelt = this.te.func_174887_a_(2);
      this.cookInfuse = this.te.func_174887_a_(3);
   }

   public void func_75137_b(int id, int data) {
      this.te.func_174885_b(id, data);
   }

   public boolean func_75145_c(EntityPlayer playerIn) {
      return this.te.func_70300_a(playerIn);
   }

   public ItemStack func_82846_b(EntityPlayer playerIn, int index) {
      ItemStack empty = ItemStack.field_190927_a;
      Slot slot = (Slot)this.field_75151_b.get(index);
      if (slot != null && slot.func_75216_d()) {
         ItemStack stack = slot.func_75211_c();
         ItemStack copy = stack.func_77946_l();
         int TE_SLOTS = 6;
         int INV_START = 6;
         int INV_END = this.field_75151_b.size();
         if (index < 6) {
            if (!this.func_75135_a(stack, 6, INV_END, true)) {
               return empty;
            }
         } else if (TileEntityFurnace.func_145954_b(stack)) {
            if (!this.func_75135_a(stack, 1, 2, false)) {
               return empty;
            }
         } else if (!this.func_75135_a(stack, 0, 1, false) && !this.func_75135_a(stack, 3, 4, false)) {
            return empty;
         }

         if (stack.func_190926_b()) {
            slot.func_75215_d(ItemStack.field_190927_a);
         } else {
            slot.func_75218_e();
         }

         return copy;
      } else {
         return empty;
      }
   }
}
