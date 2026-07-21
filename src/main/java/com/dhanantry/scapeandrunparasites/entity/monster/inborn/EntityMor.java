package com.dhanantry.scapeandrunparasites.entity.monster.inborn;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.util.SRPAttributes;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityMor extends EntityParasiteBase {
   public EntityMor(World worldIn) {
      super(worldIn);
      this.func_70105_a(0.85F, 1.0F);
      this.field_70728_aV = SRPAttributes.XP_LiTTLE;
      this.field_70714_bg.func_85156_a(this.folow);
      this.type = 7;
      this.killcount = -10.0;
   }

   @Override
   public int getParasiteIDRegister() {
      return 305;
   }

   protected void func_184651_r() {
   }

   protected void func_110147_ax() {
      super.func_110147_ax();
      this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(SRPAttributes.LODO_HEALTH);
      this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(SRPAttributes.LODO_ARMOR);
      this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3);
      this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(SRPAttributes.LODO_ATTACK_DAMAGE);
      this.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111128_a(SRPAttributes.LODO_KD_RESISTANCE);
      this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(16.0);
   }

   @Override
   public void func_70636_d() {
      super.func_70636_d();
   }

   public float func_70047_e() {
      return 0.8F;
   }

   @Override
   public void func_70014_b(NBTTagCompound compound) {
      super.func_70014_b(compound);
   }

   @Override
   public void func_70037_a(NBTTagCompound compound) {
      super.func_70037_a(compound);
   }
}
