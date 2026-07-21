package com.dhanantry.scapeandrunparasites.client;

import com.dhanantry.scapeandrunparasites.client.particle.ParticleKirinWarning;
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

@EventBusSubscriber(Side.CLIENT)
public final class SRPClientParticles {
   public static final TextureAtlasSprite[] INFESTED_LEAF = new TextureAtlasSprite[4];
   public static TextureAtlasSprite KIRIN_WARNING;

   private SRPClientParticles() {
   }

   @SubscribeEvent
   public static void onTextureStitch(Pre e) {
      TextureMap map = e.getMap();
      INFESTED_LEAF[0] = map.func_174942_a(new ResourceLocation("srparasites:particle/infested_leaves"));
      INFESTED_LEAF[1] = map.func_174942_a(new ResourceLocation("srparasites:particle/infested_leaves2"));
      INFESTED_LEAF[2] = map.func_174942_a(new ResourceLocation("srparasites:particle/infested_leaves3"));
      INFESTED_LEAF[3] = map.func_174942_a(new ResourceLocation("srparasites:particle/infested_leaves4"));
      KIRIN_WARNING = map.func_174942_a(new ResourceLocation("srparasites:particle/kirin_warning"));
   }

   public static TextureAtlasSprite randomInfestedLeaf(Random rand) {
      return INFESTED_LEAF[rand.nextInt(INFESTED_LEAF.length)];
   }

   public static void spawnKirinWarning(World world, double x, double y, double z, float sizeBlocks, float yawRad, int maxAgeTicks) {
      if (world.field_72995_K) {
         Particle p = new ParticleKirinWarning(world, x, y, z, sizeBlocks, yawRad, maxAgeTicks);
         Minecraft.func_71410_x().field_71452_i.func_78873_a(p);
      }
   }
}
