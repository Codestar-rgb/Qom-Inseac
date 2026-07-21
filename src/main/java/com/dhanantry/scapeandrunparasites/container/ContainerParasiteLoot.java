package com.dhanantry.scapeandrunparasites.container;

import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.tileentity.TileEntityParasiteLoot;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;

public class ContainerParasiteLoot extends Container {
   private final TileEntityParasiteLoot te;
   private int interactionPulse = 0;
   public int clientPulse = 0;
   private static final float MIN_DAMAGE = 0.5F;
   private static final float MAX_DAMAGE = 8.0F;
   private static final DamageSource PARASITIC = DamageSource.field_76376_m;

   public ContainerParasiteLoot(InventoryPlayer playerInv, final TileEntityParasiteLoot te, EntityPlayer player) {
      this.te = te;
      int idx = 0;

      for (int r = 0; r < 3; r++) {
         for (int c = 0; c < 9; c++) {
            this.func_75146_a(new Slot(te, idx++, 8 + c * 18, 30 + r * 18) {
               public boolean func_75214_a(ItemStack stack) {
                  return TileEntityParasiteLoot.isValidParasiteLootItem(stack);
               }

               public ItemStack func_190901_a(EntityPlayer p, ItemStack stack) {
                  float f = te.getFullness();
                  float dmg = 0.5F + 7.5F * (1.0F - f);
                  if (!p.field_70170_p.field_72995_K) {
                     ContainerParasiteLoot.this.interactionPulse = ContainerParasiteLoot.this.interactionPulse + 1 & 32767;
                     if (SRPConfigWorld.parasiteLootDamageOnTake && dmg > 0.0F) {
                        p.func_70097_a(ContainerParasiteLoot.PARASITIC, dmg);
                     }

                     ContainerParasiteLoot.this.applyParasiteEffects(p, f);
                  }

                  return super.func_190901_a(p, stack);
               }
            });
         }
      }

      for (int r = 0; r < 3; r++) {
         for (int c = 0; c < 9; c++) {
            this.func_75146_a(new Slot(playerInv, c + r * 9 + 9, 8 + c * 18, 88 + r * 18));
         }
      }

      for (int i = 0; i < 9; i++) {
         this.func_75146_a(new Slot(playerInv, i, 8 + i * 18, 146));
      }
   }

   public boolean func_75145_c(EntityPlayer playerIn) {
      return this.te.func_70300_a(playerIn);
   }

   public ItemStack func_82846_b(EntityPlayer playerIn, int index) {
      ItemStack ret = ItemStack.field_190927_a;
      Slot slot = (Slot)this.field_75151_b.get(index);
      if (slot != null && slot.func_75216_d()) {
         ItemStack in = slot.func_75211_c();
         ret = in.func_77946_l();
         int teStart = 0;
         int teEnd = 27;
         int invStart = 27;
         int invEnd = 54;
         int hotStart = 54;
         int hotEnd = 63;
         if (index < 27) {
            float fBefore = this.te.getFullness();
            if (!this.func_75135_a(in, 27, 63, true)) {
               return ItemStack.field_190927_a;
            }

            if (!playerIn.field_70170_p.field_72995_K) {
               if (SRPConfigWorld.parasiteLootDamageOnTake) {
                  float dmg = 0.5F + 7.5F * (1.0F - fBefore);
                  if (dmg > 0.0F) {
                     playerIn.func_70097_a(PARASITIC, dmg);
                  }
               }

               if (fBefore < 1.0F) {
                  int corrosDur;
                  int viralAmp;
                  int viralDur;
                  int corrosAmp;
                  if (fBefore < 0.25F) {
                     corrosAmp = 1;
                     corrosDur = 400;
                     viralAmp = 3;
                     viralDur = 600;
                  } else if (fBefore < 0.5F) {
                     corrosAmp = 1;
                     corrosDur = 200;
                     viralAmp = 2;
                     viralDur = 400;
                  } else if (fBefore < 0.75F) {
                     corrosAmp = 0;
                     corrosDur = 200;
                     viralAmp = 1;
                     viralDur = 200;
                  } else {
                     corrosAmp = 0;
                     corrosDur = 100;
                     viralAmp = 1;
                     viralDur = 100;
                  }

                  playerIn.func_70690_d(new PotionEffect(SRPPotions.CORRO_E, corrosDur, corrosAmp, false, false));
                  SRPPotions.applyStackPotion(SRPPotions.VIRA_E, playerIn, viralDur, viralAmp);
               }

               this.interactionPulse = this.interactionPulse + 1 & 32767;
            }
         } else if (index < 54) {
            if (!this.func_75135_a(in, 0, 27, false)) {
               if (!this.func_75135_a(in, 54, 63, false)) {
                  return ItemStack.field_190927_a;
               }
            } else if (!playerIn.field_70170_p.field_72995_K) {
               this.interactionPulse = this.interactionPulse + 1 & 32767;
            }
         } else if (index < 63) {
            if (!this.func_75135_a(in, 0, 27, false)) {
               if (!this.func_75135_a(in, 27, 54, false)) {
                  return ItemStack.field_190927_a;
               }
            } else if (!playerIn.field_70170_p.field_72995_K) {
               this.interactionPulse = this.interactionPulse + 1 & 32767;
            }
         }

         if (in.func_190926_b()) {
            slot.func_75215_d(ItemStack.field_190927_a);
         } else {
            slot.func_75218_e();
         }

         if (in.func_190916_E() == ret.func_190916_E()) {
            return ItemStack.field_190927_a;
         }

         slot.func_190901_a(playerIn, in);
      }

      return ret;
   }

   private void applyParasiteEffects(EntityPlayer p, float fullness) {
      if (!p.field_70170_p.field_72995_K) {
         int corrosAmp;
         int corrosDur;
         int viralAmp;
         int viralDur;
         if (fullness < 0.25F) {
            corrosAmp = 1;
            corrosDur = 400;
            viralAmp = 3;
            viralDur = 600;
         } else if (fullness < 0.5F) {
            corrosAmp = 1;
            corrosDur = 200;
            viralAmp = 2;
            viralDur = 400;
         } else if (fullness < 0.75F) {
            corrosAmp = 0;
            corrosDur = 200;
            viralAmp = 1;
            viralDur = 200;
         } else {
            if (!(fullness < 1.0F)) {
               return;
            }

            corrosAmp = 0;
            corrosDur = 100;
            viralAmp = 1;
            viralDur = 100;
         }

         p.func_70690_d(new PotionEffect(SRPPotions.CORRO_E, corrosDur, corrosAmp, false, false));
         SRPPotions.applyStackPotion(SRPPotions.VIRA_E, p, viralDur, viralAmp);
      }
   }

   public void func_75142_b() {
      super.func_75142_b();

      for (IContainerListener l : this.field_75149_d) {
         l.func_71112_a(this, 0, this.te.getFullnessField());
         l.func_71112_a(this, 1, this.interactionPulse);
      }
   }

   public void func_75137_b(int id, int data) {
      if (id == 1) {
         this.clientPulse = data;
      }
   }

   public void func_75132_a(IContainerListener l) {
      super.func_75132_a(l);
      l.func_71112_a(this, 0, this.te.getFullnessField());
      l.func_71112_a(this, 1, this.interactionPulse);
   }
}
