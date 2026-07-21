package com.dhanantry.scapeandrunparasites.tileentity;

import com.dhanantry.scapeandrunparasites.block.BlockInfestedFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IInteractionObject;

public class TileEntityInfestedFurnace extends TileEntity implements ISidedInventory, ITickable, IInteractionObject {
   public static boolean DEBUG = false;
   private static final int[] SLOTS_TOP = new int[]{0};
   private static final int[] SLOTS_BOTTOM = new int[]{2, 1};
   private static final int[] SLOTS_SIDES = new int[]{1};
   private NonNullList<ItemStack> furnaceItemStacks = NonNullList.func_191197_a(3, ItemStack.field_190927_a);
   private int furnaceBurnTime;
   private int currentItemBurnTime;
   private int cookTime;
   private int totalCookTime = 200;
   private String customName;

   public void func_73660_a() {
      boolean wasBurning = this.isBurning();
      boolean dirty = false;
      if (this.isBurning()) {
         this.furnaceBurnTime--;
      }

      if (!this.field_145850_b.field_72995_K) {
         ItemStack fuel = (ItemStack)this.furnaceItemStacks.get(1);
         ItemStack input = (ItemStack)this.furnaceItemStacks.get(0);
         if (this.isBurning() || !fuel.func_190926_b() && !input.func_190926_b()) {
            if (!this.isBurning() && this.canSmelt()) {
               this.furnaceBurnTime = getItemBurnTime(fuel);
               this.currentItemBurnTime = this.furnaceBurnTime;
               if (this.isBurning()) {
                  dirty = true;
                  if (!fuel.func_190926_b()) {
                     ItemStack container = fuel.func_77973_b().getContainerItem(fuel);
                     fuel.func_190918_g(1);
                     if (fuel.func_190926_b()) {
                        this.furnaceItemStacks.set(1, container);
                     }
                  }
               }
            }

            if (this.isBurning() && this.canSmelt()) {
               this.cookTime++;
               if (this.cookTime >= this.totalCookTime) {
                  this.cookTime = 0;
                  this.totalCookTime = 200;
                  this.smeltItem();
                  dirty = true;
               }
            } else {
               this.cookTime = 0;
            }
         } else if (!this.isBurning() && this.cookTime > 0) {
            this.cookTime = 0;
         }

         if (wasBurning != this.isBurning()) {
            if (DEBUG) {
               System.out.println("Infested Furnace: burnStateChange pos=" + this.field_174879_c + " " + wasBurning + " -> " + this.isBurning());
            }

            BlockInfestedFurnace.setLitState(this.isBurning(), this.field_145850_b, this.field_174879_c);
            dirty = true;
         }
      }

      if (dirty) {
         this.func_70296_d();
      }
   }

   public boolean isBurning() {
      return this.furnaceBurnTime > 0;
   }

   private boolean canSmelt() {
      ItemStack input = (ItemStack)this.furnaceItemStacks.get(0);
      if (input.func_190926_b()) {
         return false;
      } else {
         ItemStack result = FurnaceRecipes.func_77602_a().func_151395_a(input);
         if (result.func_190926_b()) {
            return false;
         } else {
            ItemStack output = (ItemStack)this.furnaceItemStacks.get(2);
            if (output.func_190926_b()) {
               return true;
            } else if (!output.func_77969_a(result)) {
               return false;
            } else {
               int res = output.func_190916_E() + result.func_190916_E();
               return res <= this.func_70297_j_() && res <= output.func_77976_d();
            }
         }
      }
   }

   private void smeltItem() {
      if (this.canSmelt()) {
         ItemStack input = (ItemStack)this.furnaceItemStacks.get(0);
         ItemStack result = FurnaceRecipes.func_77602_a().func_151395_a(input);
         ItemStack output = (ItemStack)this.furnaceItemStacks.get(2);
         if (output.func_190926_b()) {
            this.furnaceItemStacks.set(2, result.func_77946_l());
         } else if (output.func_77973_b() == result.func_77973_b()) {
            output.func_190917_f(result.func_190916_E());
         }

         input.func_190918_g(1);
      }
   }

   public static int getItemBurnTime(ItemStack stack) {
      return TileEntityFurnace.func_145952_a(stack);
   }

   public int func_70302_i_() {
      return this.furnaceItemStacks.size();
   }

   public boolean func_191420_l() {
      for (ItemStack itemstack : this.furnaceItemStacks) {
         if (!itemstack.func_190926_b()) {
            return false;
         }
      }

      return true;
   }

   public ItemStack func_70301_a(int index) {
      return (ItemStack)this.furnaceItemStacks.get(index);
   }

   public ItemStack func_70298_a(int index, int count) {
      return ItemStackHelper.func_188382_a(this.furnaceItemStacks, index, count);
   }

   public ItemStack func_70304_b(int index) {
      return ItemStackHelper.func_188383_a(this.furnaceItemStacks, index);
   }

   public void func_70299_a(int index, ItemStack stack) {
      ItemStack prev = (ItemStack)this.furnaceItemStacks.get(index);
      boolean same = !stack.func_190926_b() && stack.func_77969_a(prev) && ItemStack.func_77970_a(stack, prev);
      this.furnaceItemStacks.set(index, stack);
      if (stack.func_190916_E() > this.func_70297_j_()) {
         stack.func_190920_e(this.func_70297_j_());
      }

      if (index == 0 && !same) {
         this.totalCookTime = 200;
         this.cookTime = 0;
         this.func_70296_d();
      }
   }

   public String func_70005_c_() {
      return this.func_145818_k_() ? this.customName : "tile.srparasites.infested_furnace.name";
   }

   public boolean func_145818_k_() {
      return this.customName != null && !this.customName.isEmpty();
   }

   public void setCustomInventoryName(String name) {
      this.customName = name;
   }

   public ITextComponent func_145748_c_() {
      return (ITextComponent)(this.func_145818_k_()
         ? new TextComponentString(this.customName)
         : new TextComponentTranslation("tile.srparasites.infested_furnace.name", new Object[0]));
   }

   public int func_70297_j_() {
      return 64;
   }

   public boolean func_70300_a(EntityPlayer player) {
      TileEntity te = this.field_145850_b.func_175625_s(this.field_174879_c);
      if (te != this) {
         if (DEBUG) {
            System.out
               .println(
                  "Infested Furnace: isUsableByPlayer FAILED pos="
                     + this.field_174879_c
                     + " worldTE="
                     + (te == null ? "null" : te.getClass().getName())
                     + " this="
                     + this.getClass().getName()
               );
         }

         return false;
      } else {
         return player.func_70092_e(
               this.field_174879_c.func_177958_n() + 0.5, this.field_174879_c.func_177956_o() + 0.5, this.field_174879_c.func_177952_p() + 0.5
            )
            <= 64.0;
      }
   }

   public void func_174889_b(EntityPlayer player) {
      if (DEBUG) {
         System.out.println("Infested Furnace: openInventory pos=" + this.field_174879_c + " player=" + player.func_70005_c_());
      }
   }

   public void func_174886_c(EntityPlayer player) {
      if (DEBUG) {
         System.out.println("Infested Furnace: closeInventory pos=" + this.field_174879_c + " player=" + player.func_70005_c_());
      }
   }

   public boolean func_94041_b(int index, ItemStack stack) {
      if (index == 2) {
         return false;
      } else {
         return index != 1 ? true : TileEntityFurnace.func_145954_b(stack);
      }
   }

   public int[] func_180463_a(EnumFacing side) {
      return side == EnumFacing.DOWN ? SLOTS_BOTTOM : (side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES);
   }

   public boolean func_180462_a(int index, ItemStack itemStackIn, EnumFacing direction) {
      return this.func_94041_b(index, itemStackIn);
   }

   public boolean func_180461_b(int index, ItemStack stack, EnumFacing direction) {
      return direction == EnumFacing.DOWN && index == 1 ? stack.func_77973_b() == Items.field_151133_ar : true;
   }

   public int func_174887_a_(int id) {
      switch (id) {
         case 0:
            return this.furnaceBurnTime;
         case 1:
            return this.currentItemBurnTime;
         case 2:
            return this.cookTime;
         case 3:
            return this.totalCookTime;
         default:
            return 0;
      }
   }

   public void func_174885_b(int id, int value) {
      switch (id) {
         case 0:
            this.furnaceBurnTime = value;
            break;
         case 1:
            this.currentItemBurnTime = value;
            break;
         case 2:
            this.cookTime = value;
            break;
         case 3:
            this.totalCookTime = value;
      }
   }

   public int func_174890_g() {
      return 4;
   }

   public void func_174888_l() {
      for (int i = 0; i < this.furnaceItemStacks.size(); i++) {
         this.furnaceItemStacks.set(i, ItemStack.field_190927_a);
      }
   }

   public String func_174875_k() {
      return "minecraft:furnace";
   }

   public Container func_174876_a(InventoryPlayer playerInventory, EntityPlayer playerIn) {
      if (DEBUG) {
         System.out.println("Infested Furnace: createContainer pos=" + this.field_174879_c + " player=" + playerIn.func_70005_c_());
      }

      return new ContainerFurnace(playerInventory, this);
   }

   public NBTTagCompound func_189515_b(NBTTagCompound compound) {
      super.func_189515_b(compound);
      ItemStackHelper.func_191282_a(compound, this.furnaceItemStacks);
      compound.func_74768_a("BurnTime", this.furnaceBurnTime);
      compound.func_74768_a("CookTime", this.cookTime);
      compound.func_74768_a("CookTimeTotal", this.totalCookTime);
      compound.func_74768_a("CurrentBurnTime", this.currentItemBurnTime);
      if (this.func_145818_k_()) {
         compound.func_74778_a("CustomName", this.customName);
      }

      return compound;
   }

   public void func_145839_a(NBTTagCompound compound) {
      super.func_145839_a(compound);
      this.furnaceItemStacks = NonNullList.func_191197_a(3, ItemStack.field_190927_a);
      ItemStackHelper.func_191283_b(compound, this.furnaceItemStacks);
      this.furnaceBurnTime = compound.func_74762_e("BurnTime");
      this.cookTime = compound.func_74762_e("CookTime");
      this.totalCookTime = compound.func_74762_e("CookTimeTotal");
      this.currentItemBurnTime = compound.func_74762_e("CurrentBurnTime");
      if (compound.func_150297_b("CustomName", 8)) {
         this.customName = compound.func_74779_i("CustomName");
      }
   }
}
