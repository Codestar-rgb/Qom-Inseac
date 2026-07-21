package com.dhanantry.scapeandrunparasites.client;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent.Pre;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@EventBusSubscriber(modid = "srparasites", value = Side.CLIENT)
public final class SRPTextureStitch {
   private SRPTextureStitch() {
   }

   @SubscribeEvent
   public static void onTextureStitchPre(Pre event) {
      TextureMap map = event.getMap();
      map.func_174942_a(new ResourceLocation("srparasites", "blocks/arraytowertexture"));
      map.func_174942_a(new ResourceLocation("srparasites", "blocks/arraytowertextureon"));
   }
}
