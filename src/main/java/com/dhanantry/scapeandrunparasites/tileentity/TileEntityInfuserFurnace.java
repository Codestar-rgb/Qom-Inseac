package com.dhanantry.scapeandrunparasites.tileentity;

import com.dhanantry.scapeandrunparasites.container.ContainerInfuserFurnace;
import com.dhanantry.scapeandrunparasites.recipes.InfuserFurnaceRecipe;
import com.dhanantry.scapeandrunparasites.recipes.InfuserFurnaceRecipes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityInfuserFurnace extends TileEntityLockable implements ITickable, ISidedInventory {
   public static final int SLOT_SMELT_IN = 0;
   public static final int SLOT_FUEL = 1;
   public static final int SLOT_SMELT_OUT = 2;
   public static final int SLOT_INFUSE_IN = 3;
   public static final int SLOT_INFUSE_OUT = 4;
   public static final int SLOT_BOTTLE_OUT = 5;
   private NonNullList<ItemStack> items = NonNullList.func_191197_a(6, ItemStack.field_190927_a);
   private int burnTime;
   private int currentBurnTime;
   private int cookTimeSmelt;
   private int cookTimeInfuse;
   private static final int COOK_TIME_TOTAL = 200;
   private static final int[] TOP = new int[]{0};
   private static final int[] BOTTOM = new int[]{2, 4, 5};
   private static final int[] SIDES = new int[]{1, 3};

   private InfuserFurnaceRecipe getCurrentRecipe() {
      ItemStack smelt = this.func_70301_a(0);
      ItemStack infuse = this.func_70301_a(3);
      return InfuserFurnaceRecipes.find(smelt, infuse);
   }

   public void func_73660_a() {
      boolean wasBurning = this.isBurning();
      boolean dirty = false;
      if (this.isBurning()) {
         this.burnTime--;
      }

      InfuserFurnaceRecipe r = this.getCurrentRecipe();
      if (!this.isBurning() && (this.canSmelt() || this.canInfuse(r))) {
         ItemStack fuel = (ItemStack)this.items.get(1);
         int fuelBurn = TileEntityFurnace.func_145952_a(fuel);
         if (fuelBurn > 0) {
            this.burnTime = fuelBurn;
            this.currentBurnTime = fuelBurn;
            dirty = true;
            Item fuelItem = fuel.func_77973_b();
            fuel.func_190918_g(1);
            if (fuel.func_190926_b()) {
               Item container = fuelItem.func_77668_q();
               this.items.set(1, container != null ? new ItemStack(container) : ItemStack.field_190927_a);
            }
         }
      }

      if (this.isBurning() && this.canSmelt()) {
         this.cookTimeSmelt++;
         if (this.cookTimeSmelt >= 200) {
            this.cookTimeSmelt = 0;
            this.doSmelt();
            dirty = true;
         }
      } else {
         this.cookTimeSmelt = 0;
      }

      if (this.isBurning() && this.canInfuse(r)) {
         this.cookTimeInfuse++;
         if (this.cookTimeInfuse >= (r != null ? r.cookTime : 200)) {
            this.cookTimeInfuse = 0;
            this.doInfuse(r);
            dirty = true;
         }
      } else {
         this.cookTimeInfuse = 0;
      }

      if (wasBurning != this.isBurning()) {
         dirty = true;
      }

      if (dirty) {
         this.func_70296_d();
      }
   }

   public boolean isBurning() {
      return this.burnTime > 0;
   }

   private boolean canSmelt() {
      ItemStack in = (ItemStack)this.items.get(0);
      if (in.func_190926_b()) {
         return false;
      } else {
         ItemStack result = FurnaceRecipes.func_77602_a().func_151395_a(in);
         if (result.func_190926_b()) {
            return false;
         } else {
            ItemStack out = (ItemStack)this.items.get(2);
            return this.canOutputStack(out, result);
         }
      }
   }

   private void doSmelt() {
      if (this.canSmelt()) {
         ItemStack in = (ItemStack)this.items.get(0);
         ItemStack result = FurnaceRecipes.func_77602_a().func_151395_a(in).func_77946_l();
         ItemStack out = (ItemStack)this.items.get(2);
         this.items.set(2, this.pushToOutput(out, result));
         in.func_190918_g(1);
      }
   }

   private boolean canInfuse(InfuserFurnaceRecipe r) {
      if (r == null) {
         return false;
      } else {
         return !canMerge(this.func_70301_a(4), r.infusedOut) ? false : canMerge(this.func_70301_a(5), r.bottleOut);
      }
   }

   private void doInfuse(InfuserFurnaceRecipe r) {
      if (this.canInfuse(r)) {
         this.func_70301_a(0).func_190918_g(1);
         this.func_70301_a(3).func_190918_g(1);
         this.mergeInto(4, r.infusedOut);
         this.mergeInto(5, r.bottleOut);
         this.func_70296_d();
      }
   }

   private boolean canOutputStack(ItemStack existing, ItemStack toAdd) {
      if (toAdd.func_190926_b()) {
         return false;
      } else if (existing.func_190926_b()) {
         return true;
      } else if (!existing.func_77969_a(toAdd)) {
         return false;
      } else {
         int total = existing.func_190916_E() + toAdd.func_190916_E();
         return total <= existing.func_77976_d() && total <= this.func_70297_j_();
      }
   }

   private ItemStack pushToOutput(ItemStack existing, ItemStack toAdd) {
      if (existing.func_190926_b()) {
         return toAdd;
      } else {
         existing.func_190917_f(toAdd.func_190916_E());
         return existing;
      }
   }

   private static boolean canMerge(ItemStack slotStack, ItemStack add) {
      if (add.func_190926_b()) {
         return true;
      } else if (slotStack.func_190926_b()) {
         return true;
      } else if (!ItemStack.func_179545_c(slotStack, add)) {
         return false;
      } else {
         return !ItemStack.func_77970_a(slotStack, add) ? false : slotStack.func_190916_E() + add.func_190916_E() <= slotStack.func_77976_d();
      }
   }

   private void mergeInto(int slot, ItemStack add) {
      if (!add.func_190926_b()) {
         ItemStack cur = this.func_70301_a(slot);
         if (cur.func_190926_b()) {
            this.func_70299_a(slot, add.func_77946_l());
         } else {
            cur.func_190917_f(add.func_190916_E());
            this.func_70299_a(slot, cur);
         }
      }
   }

   public String func_174875_k() {
      return "srparasites:infuser_furnace";
   }

   public Container func_174876_a(InventoryPlayer playerInventory, EntityPlayer playerIn) {
      return new ContainerInfuserFurnace(playerInventory, this, playerIn);
   }

   public String func_70005_c_() {
      return "container.infuser_furnace";
   }

   public boolean func_145818_k_() {
      return false;
   }

   public ITextComponent func_145748_c_() {
      return new TextComponentTranslation(this.func_70005_c_(), new Object[0]);
   }

   public int func_70302_i_() {
      return this.items.size();
   }

   public boolean func_191420_l() {
      for (ItemStack s : this.items) {
         if (!s.func_190926_b()) {
            return false;
         }
      }

      return true;
   }

   public ItemStack func_70301_a(int index) {
      return (ItemStack)this.items.get(index);
   }

   public ItemStack func_70298_a(int index, int count) {
      return ItemStackHelper.func_188382_a(this.items, index, count);
   }

   public ItemStack func_70304_b(int index) {
      return ItemStackHelper.func_188383_a(this.items, index);
   }

   public void func_70299_a(int index, ItemStack stack) {
      this.items.set(index, stack);
      if (!stack.func_190926_b() && stack.func_190916_E() > this.func_70297_j_()) {
         stack.func_190920_e(this.func_70297_j_());
      }

      this.func_70296_d();
   }

   public int func_70297_j_() {
      return 64;
   }

   public boolean func_70300_a(EntityPlayer player) {
      return this.field_145850_b.func_175625_s(this.field_174879_c) == this
         && player.func_70092_e(this.field_174879_c.func_177958_n() + 0.5, this.field_174879_c.func_177956_o() + 0.5, this.field_174879_c.func_177952_p() + 0.5)
            <= 64.0;
   }

   public void func_174889_b(EntityPlayer player) {
   }

   public void func_174886_c(EntityPlayer player) {
   }

   public boolean func_94041_b(int index, ItemStack stack) {
      if (index == 2 || index == 4 || index == 5) {
         return false;
      } else {
         return index == 1 ? TileEntityFurnace.func_145954_b(stack) : true;
      }
   }

   public int func_174887_a_(int id) {
      switch (id) {
         case 0:
            return this.burnTime;
         case 1:
            return this.currentBurnTime;
         case 2:
            return this.cookTimeSmelt;
         case 3:
            return this.cookTimeInfuse;
         default:
            return 0;
      }
   }

   public void func_174885_b(int id, int value) {
      switch (id) {
         case 0:
            this.burnTime = value;
            break;
         case 1:
            this.currentBurnTime = value;
            break;
         case 2:
            this.cookTimeSmelt = value;
            break;
         case 3:
            this.cookTimeInfuse = value;
      }
   }

   public int func_174890_g() {
      return 4;
   }

   public void func_174888_l() {
      this.items.clear();
   }

   public int[] func_180463_a(EnumFacing side) {
      if (side == EnumFacing.DOWN) {
         return BOTTOM;
      } else {
         return side == EnumFacing.UP ? TOP : SIDES;
      }
   }

   public boolean func_180462_a(int index, ItemStack itemStackIn, EnumFacing direction) {
      return this.func_94041_b(index, itemStackIn);
   }

   public boolean func_180461_b(int index, ItemStack stack, EnumFacing direction) {
      return index == 2 || index == 4 || index == 5;
   }

   public NBTTagCompound func_189515_b(NBTTagCompound compound) {
      super.func_189515_b(compound);
      NBTTagList list = new NBTTagList();

      for (int i = 0; i < this.items.size(); i++) {
         ItemStack s = (ItemStack)this.items.get(i);
         if (!s.func_190926_b()) {
            NBTTagCompound c = new NBTTagCompound();
            c.func_74774_a("Slot", (byte)i);
            s.func_77955_b(c);
            list.func_74742_a(c);
         }
      }

      compound.func_74782_a("Items", list);
      compound.func_74768_a("BurnTime", this.burnTime);
      compound.func_74768_a("CurrentBurnTime", this.currentBurnTime);
      compound.func_74768_a("CookSmelt", this.cookTimeSmelt);
      compound.func_74768_a("CookInfuse", this.cookTimeInfuse);
      return compound;
   }

   public void func_145839_a(NBTTagCompound compound) {
      super.func_145839_a(compound);
      this.items = NonNullList.func_191197_a(6, ItemStack.field_190927_a);
      NBTTagList list = compound.func_150295_c("Items", 10);

      for (int i = 0; i < list.func_74745_c(); i++) {
         NBTTagCompound c = list.func_150305_b(i);
         int slot = c.func_74771_c("Slot") & 255;
         if (slot >= 0 && slot < this.items.size()) {
            this.items.set(slot, new ItemStack(c));
         }
      }

      this.burnTime = compound.func_74762_e("BurnTime");
      this.currentBurnTime = compound.func_74762_e("CurrentBurnTime");
      this.cookTimeSmelt = compound.func_74762_e("CookSmelt");
      this.cookTimeInfuse = compound.func_74762_e("CookInfuse");
   }
}
