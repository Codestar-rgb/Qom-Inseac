package com.dhanantry.scapeandrunparasites.client.fx;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent.Pre;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(modid = "srparasites", value = Side.CLIENT)
public final class ClientSRPParticles {
   public static TextureAtlasSprite INFESTED_LEAF_SPRITE;
   public static final TextureAtlasSprite[] INFESTED_LEAF_SPRITES = new TextureAtlasSprite[4];
   private static int spawnedThisTick = 0;
   private static final int MAX_PER_TICK = 68;

   public static boolean canSpawn() {
      return spawnedThisTick < 68;
   }

   public static void onSpawn() {
      spawnedThisTick++;
   }

   public static ParticleManager fx() {
      return Minecraft.func_71410_x().field_71452_i;
   }

   @SubscribeEvent
   public static void onClientTick(ClientTickEvent e) {
      if (e.phase == Phase.END) {
         spawnedThisTick = 0;
      }
   }

   @SubscribeEvent
   public static void onTextureStitch(Pre e) {
      INFESTED_LEAF_SPRITE = e.getMap().func_174942_a(new ResourceLocation("srparasites:particle/infested_leaves"));
      INFESTED_LEAF_SPRITES[0] = INFESTED_LEAF_SPRITE;
      INFESTED_LEAF_SPRITES[1] = e.getMap().func_174942_a(new ResourceLocation("srparasites:particle/infested_leaves2"));
      INFESTED_LEAF_SPRITES[2] = e.getMap().func_174942_a(new ResourceLocation("srparasites:particle/infested_leaves3"));
      INFESTED_LEAF_SPRITES[3] = e.getMap().func_174942_a(new ResourceLocation("srparasites:particle/infested_leaves4"));
   }

   public static TextureAtlasSprite randomInfestedLeaf(Random rand) {
      return INFESTED_LEAF_SPRITES[rand.nextInt(INFESTED_LEAF_SPRITES.length)];
   }
}
