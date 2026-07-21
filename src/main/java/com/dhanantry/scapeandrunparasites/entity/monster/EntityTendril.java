package com.dhanantry.scapeandrunparasites.entity.monster;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import net.minecraft.world.World;

public class EntityTendril extends EntityParasiteBase {
   public EntityTendril(World worldIn) {
      super(worldIn);
      this.func_70105_a(1.0F, 1.0F);
      this.field_70714_bg.func_85156_a(this.aiWander);
      this.field_70714_bg.func_85156_a(this.folow);
      this.killcount = -10.0;
   }

   @Override
   public int getParasiteIDRegister() {
      return 202;
   }

   public EntityTendril(World world, float width, float height) {
      super(world);
      this.func_70105_a(width, height);
   }

   public void func_70071_h_() {
      super.func_70071_h_();
   }

   @Override
   public void setSkin(int texture) {
      super.setSkin(texture);
   }

   @Override
   public int getSkin() {
      return super.getSkin();
   }
}
