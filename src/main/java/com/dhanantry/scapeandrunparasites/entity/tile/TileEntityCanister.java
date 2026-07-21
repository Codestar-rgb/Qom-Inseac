package com.dhanantry.scapeandrunparasites.entity.tile;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteCanister;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteCanisterC;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.network.SRPPacketParticle;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nullable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class TileEntityCanister extends TileEntityLockableLoot implements ITickable {
   private NonNullList<ItemStack> chestContents = NonNullList.func_191197_a(40, ItemStack.field_190927_a);
   public int numPlayersUsing;
   private int ticksSinceSync;

   public int func_70302_i_() {
      return 40;
   }

   public boolean func_191420_l() {
      for (ItemStack itemstack : this.chestContents) {
         if (!itemstack.func_190926_b()) {
            return false;
         }
      }

      return true;
   }

   public String func_70005_c_() {
      return this.func_145818_k_() ? this.field_190577_o : "container.chest";
   }

   public static void registerFixesChest(DataFixer fixer) {
      fixer.func_188258_a(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileEntityCanister.class, new String[]{"Items"}));
   }

   public void func_145839_a(NBTTagCompound compound) {
      super.func_145839_a(compound);
      this.chestContents = NonNullList.func_191197_a(this.func_70302_i_(), ItemStack.field_190927_a);
      if (!this.func_184283_b(compound)) {
         ItemStackHelper.func_191283_b(compound, this.chestContents);
      }

      if (compound.func_150297_b("CustomName", 8)) {
         this.field_190577_o = compound.func_74779_i("CustomName");
      }
   }

   public NBTTagCompound func_189515_b(NBTTagCompound compound) {
      super.func_189515_b(compound);
      if (!this.func_184282_c(compound)) {
         ItemStackHelper.func_191282_a(compound, this.chestContents);
      }

      if (this.func_145818_k_()) {
         compound.func_74778_a("CustomName", this.field_190577_o);
      }

      return compound;
   }

   public int func_70297_j_() {
      return 100;
   }

   public void func_145836_u() {
      super.func_145836_u();
   }

   public void func_73660_a() {
      int i = this.field_174879_c.func_177958_n();
      int j = this.field_174879_c.func_177956_o();
      int k = this.field_174879_c.func_177952_p();
      this.ticksSinceSync++;
      if (!this.field_145850_b.field_72995_K && this.numPlayersUsing != 0 && (this.ticksSinceSync + i + j + k) % 200 == 0) {
         this.numPlayersUsing = 0;

         for (EntityPlayer entityplayer : this.field_145850_b
            .func_72872_a(EntityPlayer.class, new AxisAlignedBB(i - 5.0F, j - 5.0F, k - 5.0F, i + 1 + 5.0F, j + 1 + 5.0F, k + 1 + 5.0F))) {
            if (entityplayer.field_71070_bA instanceof ContainerChest) {
               IInventory iinventory = ((ContainerChest)entityplayer.field_71070_bA).func_85151_d();
               if (iinventory == this || iinventory instanceof InventoryLargeChest && ((InventoryLargeChest)iinventory).func_90010_a(this)) {
                  this.numPlayersUsing++;
               }
            }
         }
      }

      if (!this.field_145850_b.field_72995_K && this.ticksSinceSync % 20 == 0 && this.ticksSinceSync > 20 * SRPConfig.cystDelay) {
         int ccc = 0;

         for (int in = 0; in < this.func_70302_i_(); in++) {
            if (Objects.requireNonNull(this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)).getStackInSlot(in).func_77973_b()
               != Items.field_190931_a) {
               ItemStack stack = Objects.requireNonNull(this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)).getStackInSlot(in);
               if (!ParasiteEventEntity.checkName(Objects.requireNonNull(stack.func_77973_b().getRegistryName()).toString(), SRPConfig.cystItemBlackList, false)
                  )
                {
                  if (stack.func_77984_f()) {
                     if (stack.func_96631_a(5, this.field_145850_b.field_73012_v, null)) {
                        stack.func_190918_g(1);
                        if (SRPConfigSystems.useEvolution) {
                           SRPSaveData data = SRPSaveData.get(this.field_145850_b, 45);
                           data.setTotalKills(this.field_145850_b.field_73011_w.getDimension(), SRPConfig.cystConsumePoint, true, this.field_145850_b, true, 43);
                        }
                     }
                  } else {
                     stack.func_190918_g(1);
                     if (SRPConfigSystems.useEvolution) {
                        SRPSaveData data = SRPSaveData.get(this.field_145850_b, 46);
                        data.setTotalKills(this.field_145850_b.field_73011_w.getDimension(), SRPConfig.cystConsumePoint, true, this.field_145850_b, true, 44);
                     }
                  }

                  if (this.field_145850_b.field_73012_v.nextBoolean()) {
                     this.field_145850_b
                        .func_184148_a(
                           null,
                           this.field_174879_c.func_177958_n(),
                           this.field_174879_c.func_177956_o(),
                           this.field_174879_c.func_177952_p(),
                           SRPSounds.CYST_EATING,
                           SoundCategory.HOSTILE,
                           0.25F,
                           1.0F
                        );
                  }

                  SRPMain.network
                     .sendToAll(
                        new SRPPacketParticle(
                           this.field_174879_c.func_177958_n() + 0.5,
                           this.field_174879_c.func_177956_o() + 0.7,
                           this.field_174879_c.func_177952_p() + 0.5,
                           0.5F,
                           0.5F,
                           (byte)2
                        )
                     );
                  return;
               }
            } else {
               ccc++;
            }
         }

         if (ccc == this.func_70302_i_()) {
            if (this.field_145850_b.func_180495_p(this.field_174879_c.func_177977_b()).func_177230_c() != Blocks.field_150350_a) {
               this.field_145850_b
                  .func_175656_a(
                     this.field_174879_c,
                     SRPBlocks.ParasiteCanister.func_176223_P().func_177226_a(BlockParasiteCanister.VARIANT, BlockParasiteCanister.EnumType.CYST)
                  );
            } else {
               this.field_145850_b.func_175656_a(this.field_174879_c, Blocks.field_150350_a.func_176223_P());
            }
         }
      }
   }

   public boolean addStack(List<ItemStack> moblist2) {
      for (ItemStack mob : moblist2) {
         if (!mob.func_190926_b()) {
            for (int in = 0; in <= 26; in++) {
               ItemStack slot = Objects.requireNonNull(this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)).getStackInSlot(in);
               if (ItemStack.func_179545_c(slot, mob)) {
                  mob = Objects.requireNonNull(this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)).insertItem(in, mob, false);
               } else if (slot.func_77973_b() == Items.field_190931_a) {
                  mob = Objects.requireNonNull(this.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)).insertItem(in, mob, false);
               }
            }
         }
      }

      return false;
   }

   public boolean func_145842_c(int id, int type) {
      if (id == 1) {
         this.numPlayersUsing = type;
         return true;
      } else {
         return super.func_145842_c(id, type);
      }
   }

   public void func_174889_b(EntityPlayer player) {
      if (player.func_184812_l_()) {
         if (this.numPlayersUsing < 0) {
            this.numPlayersUsing = 0;
         }

         this.numPlayersUsing++;
         this.field_145850_b.func_175641_c(this.field_174879_c, this.func_145838_q(), 1, this.numPlayersUsing);
         this.field_145850_b.func_175685_c(this.field_174879_c, this.func_145838_q(), false);
      }
   }

   public void func_174886_c(EntityPlayer player) {
      if (player.func_184812_l_() && this.func_145838_q() instanceof BlockParasiteCanisterC) {
         this.numPlayersUsing--;
         this.field_145850_b.func_175641_c(this.field_174879_c, this.func_145838_q(), 1, this.numPlayersUsing);
         this.field_145850_b.func_175685_c(this.field_174879_c, this.func_145838_q(), false);
      }
   }

   @Nullable
   public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
      return (T)super.getCapability(capability, facing);
   }

   public IItemHandler getSingleChestHandler() {
      return (IItemHandler)super.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
   }

   public void func_145843_s() {
      super.func_145843_s();
      this.func_145836_u();
   }

   public String func_174875_k() {
      return "srparasites:canister";
   }

   public Container func_174876_a(InventoryPlayer playerInventory, EntityPlayer playerIn) {
      this.func_184281_d(playerIn);
      return new ContainerChest(playerInventory, this, playerIn);
   }

   protected NonNullList<ItemStack> func_190576_q() {
      return this.chestContents;
   }
}
