package com.dhanantry.scapeandrunparasites.client.particle;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.event.TextureStitchEvent.Pre;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber(modid = "srparasites", value = Side.CLIENT)
public class SRPParticleRegistry {
   private static TextureAtlasSprite PURE1;
   private static TextureAtlasSprite PURE2;

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public static void onTextureStitch(Pre e) {
      TextureMap map = e.getMap();
      PURE1 = map.func_174942_a(new ResourceLocation("srparasites", "particle/pure1"));
      PURE2 = map.func_174942_a(new ResourceLocation("srparasites", "particle/pure2"));
   }

   @SideOnly(Side.CLIENT)
   public static TextureAtlasSprite randPureSprite(Random r) {
      return r.nextBoolean() ? PURE1 : PURE2;
   }

   @SideOnly(Side.CLIENT)
   public static void spawnPureBurst(World world, double x, double y, double z, int count, int kind) {
      if (PURE1 != null && PURE2 != null) {
         Random r = world.field_73012_v;

         for (int i = 0; i < count; i++) {
            TextureAtlasSprite sprite = (i & 1) == 0 ? PURE1 : PURE2;
            Particle p = new ParticlePure(world, x, y, z, kind, sprite);
            Minecraft.func_71410_x().field_71452_i.func_78873_a(p);
         }
      }
   }
}
