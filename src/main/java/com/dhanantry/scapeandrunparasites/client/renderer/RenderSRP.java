package com.dhanantry.scapeandrunparasites.client.renderer;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;

public abstract class RenderSRP<T extends EntityParasiteBase> extends RenderLiving<T> {
   private int ticklag;
   private boolean tickflag;

   public RenderSRP(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
      super(rendermanagerIn, modelbaseIn, shadowsizeIn);
   }

   public boolean shouldRender(T livingEntity, ICamera camera, double camX, double camY, double camZ) {
      this.ticklag++;
      if (this.ticklag > 20) {
         this.ticklag = 0;
         this.tickflag = Minecraft.func_71410_x().field_71439_g.func_70644_a(SRPPotions.BRAINING_E);
      }

      return this.tickflag ? false : super.func_177071_a(livingEntity, camera, camX, camY, camZ);
   }
}
