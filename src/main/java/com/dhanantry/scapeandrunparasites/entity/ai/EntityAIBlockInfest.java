package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.block.BlockParasiteSpreading;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.world.World;

public class EntityAIBlockInfest extends EntityAIBase {
   private final EntityParasiteBase parentEntity;
   private int ticks = 0;
   private int stage;

   public EntityAIBlockInfest(EntityParasiteBase ghast, int stage) {
      this.parentEntity = ghast;
      this.stage = stage;
   }

   public boolean func_75250_a() {
      return true;
   }

   public void func_75249_e() {
   }

   public void func_75251_c() {
   }

   public void func_75246_d() {
      this.ticks++;
      if (this.ticks > 200) {
         this.ticks = 0;
         World world = this.parentEntity.field_70170_p;
         Block block = world.func_180495_p(this.parentEntity.func_180425_c()).func_177230_c();
         BlockParasiteSpreading.canInfestBlock(world, this.parentEntity.func_180425_c(), new Random(), this.stage, true);
      }
   }
}
