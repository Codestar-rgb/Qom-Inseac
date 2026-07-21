package com.dhanantry.scapeandrunparasites.client.renderer;

import com.dhanantry.scapeandrunparasites.block.BlockEntityTrophy;
import com.dhanantry.scapeandrunparasites.entity.tile.TileEntityTrophy;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RenderTrophyTESR extends TileEntitySpecialRenderer<TileEntityTrophy> {
   public void render(TileEntityTrophy te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
      World world = te.func_145831_w();
      if (world != null) {
         BlockPos pos = te.func_174877_v();
         IBlockState state = world.func_180495_p(pos);
         if (state.func_177230_c() instanceof BlockEntityTrophy) {
            BlockEntityTrophy trophy = (BlockEntityTrophy)state.func_177230_c();
            Entity entity = this.getOrCreateEntity(te, trophy.getMobId());
            if (entity != null) {
               entity.func_174810_b(true);
               entity.func_189654_d(true);
               if (!trophy.isAnimate()) {
                  entity.field_70173_aa = 0;
               } else {
                  long t = Minecraft.func_71410_x().field_71441_e.func_82737_E();
                  entity.field_70173_aa = (int)(t % 100000L);
               }

               float yaw = Minecraft.func_71410_x().field_71439_g != null ? Minecraft.func_71410_x().field_71439_g.field_70177_z : 0.0F;
               entity.field_70177_z = -yaw;
               entity.field_70126_B = -yaw;
               GlStateManager.func_179094_E();
               GlStateManager.func_179109_b((float)x + 0.5F, (float)y + 1.0F + trophy.getYOffset(), (float)z + 0.5F);
               float s = trophy.getScale();
               GlStateManager.func_179152_a(s, s, s);
               if (trophy.getTextureMode() == BlockEntityTrophy.TrophyTextureMode.STONE) {
                  GlStateManager.func_179131_c(0.75F, 0.75F, 0.75F, 1.0F);
               } else {
                  GlStateManager.func_179131_c(1.0F, 1.0F, 1.0F, 1.0F);
               }

               Minecraft.func_71410_x().func_175598_ae().func_188391_a(entity, 0.0, 0.0, 0.0, 0.0F, partialTicks, false);
               GlStateManager.func_179121_F();
            }
         }
      }
   }

   private Entity getOrCreateEntity(TileEntityTrophy te, ResourceLocation mobId) {
      if (te.cachedRenderEntity != null) {
         return te.cachedRenderEntity;
      } else {
         World w = Minecraft.func_71410_x().field_71441_e;
         if (w == null) {
            return null;
         } else {
            Entity e = EntityList.func_188429_b(mobId, w);
            if (e == null) {
               return null;
            } else {
               if (e instanceof EntityLiving) {
                  ((EntityLiving)e).func_94061_f(true);
               }

               te.cachedRenderEntity = e;
               return e;
            }
         }
      }
   }
}
