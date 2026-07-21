package com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus;

import com.dhanantry.scapeandrunparasites.entity.ai.EntityAIBlockInfest;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPBeckon;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import java.util.Arrays;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.world.World;

public class EntityVenkrolSV extends EntityPBeckon {
   private EntityAIBlockInfest infestation = new EntityAIBlockInfest(this, 2);

   public EntityVenkrolSV(World worldIn) {
      super(worldIn);
      this.func_70105_a(1.0F, 8.8F);
      this.field_70158_ak = true;
      this.totalP = SRPConfigMobs.venkrolTotalActiveMobs;
      this.mobID = new int[this.totalP + SRPConfigMobs.venkrollimit];
      this.mobPT = new int[this.totalP + SRPConfigMobs.venkrollimit];
      this.stage = 3;
      this.field_70728_aV = SRPAttributes.XP_ADAPTED * 2;
      if (SRPAttributes.rsBlockI) {
         this.field_70714_bg.func_75776_a(3, this.infestation);
      }

      Arrays.fill(this.mobID, -777);
      this.setBODY(1.0F);
   }

   @Override
   public int getParasiteIDRegister() {
      return 42;
   }

   protected void func_184651_r() {
      this.field_70715_bh.func_75776_a(1, new EntityAIHurtByTarget(this, true, new Class[0]));
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.VENKROLSII_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.VENKROLSII_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.0);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.VENKROLSII_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(1.0);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(24.0);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
      if (this.getParasiteStatus() == 0) {
         this.setBODY(0.04F);
      } else {
         this.setBODY(-0.04F);
      }
   }

   public float func_70047_e() {
      return 8.5F;
   }

   public void setBODY(float in) {
      in += this.getBODY();
      if (in > 0.5F) {
         in = 0.5F;
      }

      if (in < 0.0F) {
         in = 0.0F;
      }

      this.body = in;
   }

   @Override
   public float getBombDamage() {
      return (float)SRPAttributes.VENKROLSIV_ATTACK_DAMAGE;
   }
}
