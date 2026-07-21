package com.dhanantry.scapeandrunparasites.proxy;

import com.dhanantry.scapeandrunparasites.block.BlockBiomePurifier;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteSpreading;
import com.dhanantry.scapeandrunparasites.client.DerivedDistortionTextHandler;
import com.dhanantry.scapeandrunparasites.client.DistortedItemHighlightOverlayHandler;
import com.dhanantry.scapeandrunparasites.client.DistortionGuiSwapHandler;
import com.dhanantry.scapeandrunparasites.client.celestial.CelestialObjectRegistry;
import com.dhanantry.scapeandrunparasites.client.fog.SRPFogHandler;
import com.dhanantry.scapeandrunparasites.client.gui.GuiGameOverEscape;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderDistortedSign;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderRelayController;
import com.dhanantry.scapeandrunparasites.client.renderer.RenderTrophyTESR;
import com.dhanantry.scapeandrunparasites.client.shader.BlackSkyShaderManager;
import com.dhanantry.scapeandrunparasites.client.shader.BreatheShaderCommand;
import com.dhanantry.scapeandrunparasites.client.sky.CommandSkyFlashClient;
import com.dhanantry.scapeandrunparasites.entity.tile.TileEntityTrophy;
import com.dhanantry.scapeandrunparasites.init.SRPMusic;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.network.msg.AbstractPacket;
import com.dhanantry.scapeandrunparasites.tileentity.TileEntityRelayController;
import com.dhanantry.scapeandrunparasites.util.SRPReference;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.util.handlers.SRPEventHandlerBus;
import com.dhanantry.scapeandrunparasites.util.handlers.SRPRegistryHandlers;
import com.dhanantry.scapeandrunparasites.world.biome.BiomeParasiteBase;
import java.util.Objects;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ClientProxy extends CommonProxy {
   public static final Minecraft MC = Minecraft.func_71410_x();

   @Override
   public void preInit(FMLPreInitializationEvent e) {
      super.preInit(e);
      SRPRegistryHandlers.initRenders();
      MinecraftForge.EVENT_BUS.register(new GuiGameOverEscape.OpenHook());
      MinecraftForge.EVENT_BUS.register(new DerivedDistortionTextHandler());
      MinecraftForge.EVENT_BUS.register(new DistortionGuiSwapHandler());
      MinecraftForge.EVENT_BUS.register(new DistortedItemHighlightOverlayHandler());
      BreatheShaderCommand.register();
      BlackSkyShaderManager.get().register();
      initMusic();
      CelestialObjectRegistry.init();
      OBJLoader.INSTANCE.addDomain("srparasites");
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRelayController.class, new RenderRelayController());
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySign.class, new RenderDistortedSign());
   }

   public static void initMusic() {
      SRPMusic.EVPHASE_1_MUSIC = EnumHelperClient.addMusicType("srpphaseone", SRPSounds.EVPHASE_1_M, 20, 200);
      SRPMusic.EVPHASE_2_MUSIC = EnumHelperClient.addMusicType("srpphasetwo", SRPSounds.EVPHASE_2_M, 20, 200);
      SRPMusic.EVPHASE_3_MUSIC = EnumHelperClient.addMusicType("srpphasethree", SRPSounds.EVPHASE_3_M, 20, 200);
      SRPMusic.EVPHASE_4_MUSIC = EnumHelperClient.addMusicType("srpphasefour", SRPSounds.EVPHASE_4_M, 20, 200);
      SRPMusic.EVPHASE_5_MUSIC = EnumHelperClient.addMusicType("srpphasefive", SRPSounds.EVPHASE_5_M, 20, 200);
      SRPMusic.EVPHASE_6_MUSIC = EnumHelperClient.addMusicType("srpphasesix", SRPSounds.EVPHASE_6_M, 20, 200);
      SRPMusic.EVPHASE_7_MUSIC = EnumHelperClient.addMusicType("srpphaseseven", SRPSounds.EVPHASE_7_M, 20, 200);
      SRPMusic.EVPHASE_8_MUSIC = EnumHelperClient.addMusicType("srpphaseeight", SRPSounds.EVPHASE_8_M, 20, 200);
      SRPMusic.EVPHASE_9_MUSIC = EnumHelperClient.addMusicType("srpphasenine", SRPSounds.EVPHASE_9_M, 20, 200);
      SRPMusic.EVPHASE_10_MUSIC = EnumHelperClient.addMusicType("srpphaseten", SRPSounds.EVPHASE_10_M, 20, 200);
      SRPMusic.BIOME_MUSIC = EnumHelperClient.addMusicType("srpbiome", SRPSounds.BIOME_M, 20, 200);
      SRPMusic.SCENT_MUSIC = EnumHelperClient.addMusicType("srpscent", SRPSounds.SCENT_M, 20, 200);
      SRPMusic.DISC1 = EnumHelperClient.addMusicType("srpdiscone", SRPSounds.DISC1, 20, 200);
      SRPMusic.DISC2 = EnumHelperClient.addMusicType("srpdisctwo", SRPSounds.DISC2, 20, 200);
      SRPMusic.DISC3 = EnumHelperClient.addMusicType("srpdiscthree", SRPSounds.DISC3, 20, 200);
   }

   @Override
   public void spreadBiome(BlockPos pos, boolean convert, int type) {
      if (Minecraft.func_71410_x().field_71441_e != null) {
         Biome current = Minecraft.func_71410_x().field_71441_e.func_180494_b(pos);
         if (convert) {
            Biome target = SRPReference.getBiomeFromInt(type);
            if (target != null && current == target) {
               return;
            }

            BlockParasiteSpreading.positionToParasiteBiome(Minecraft.func_71410_x().field_71441_e, pos, type);
         } else {
            if (!(current instanceof BiomeParasiteBase)) {
               return;
            }

            BlockBiomePurifier.positionToBiome(Minecraft.func_71410_x().field_71441_e, pos, type);
         }
      }
   }

   @Override
   public void init(FMLInitializationEvent e) {
      super.init(e);
      ClientCommandHandler.instance.func_71560_a(new CommandSkyFlashClient());
      SRPFogHandler.register();
      ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrophy.class, new RenderTrophyTESR());
   }

   @Override
   public void playMovingSound(int sound, float v) {
      Random rand = new Random();
      switch (sound) {
         case -1:
            Minecraft.func_71410_x()
               .func_147118_V()
               .func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.BHEART, 1.0F, v * SRPConfigWorld.biomeHeartVol));
            break;
         case 1:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.EVPHASE_1, 1.0F, 0.75F));
            break;
         case 2:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.EVPHASE_2, 1.0F, 0.75F));
            break;
         case 3:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.EVPHASE_3, 1.0F, 0.75F));
            break;
         case 4:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.EVPHASE_4, 1.0F, 0.75F));
            break;
         case 5:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.EVPHASE_5, 1.0F, 0.75F));
            break;
         case 6:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.EVPHASE_6, 1.0F, 0.75F));
            break;
         case 7:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.EVPHASE_7, 1.0F, 0.75F));
            break;
         case 8:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.EVPHASE_8, 1.0F, 0.75F));
            break;
         case 9:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.EVPHASE_9, 1.0F, 0.75F));
            break;
         case 10:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.EVPHASE_10, 1.0F, 0.75F));
            break;
         case 100:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.NODE_1, 1.0F, 0.75F));
            break;
         case 101:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.COLONY_1, 1.0F, 0.75F));
            break;
         case 102:
            if (SRPEventHandlerBus.clientScent <= 0) {
               Minecraft.func_71410_x().func_147118_V().func_189520_a("", SoundCategory.MUSIC);
               Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_184370_a(SRPMusic.SCENT_MUSIC.func_188768_a()));
            }

            SRPEventHandlerBus.musicTimer = 5000;
            SRPEventHandlerBus.clientScent = 5000;
            break;
         case 103:
            SRPEventHandlerBus.musicTimer = 1000;
            SRPEventHandlerBus.clientScent = 140;
            break;
         case 104:
            SRPEventHandlerBus.musicTimer = 1000;
            Minecraft.func_71410_x().func_147118_V().func_189520_a("", SoundCategory.MUSIC);
            break;
         case 200:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_0, 1.0F, 0.45F));
            break;
         case 201:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_1, 1.0F, 0.45F));
            break;
         case 202:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_2, 1.0F, 0.45F));
            break;
         case 203:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_3, 1.0F, 0.45F));
            break;
         case 204:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_4, 1.0F, 0.45F));
            break;
         case 205:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_5, 1.0F, 0.45F));
            break;
         case 206:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_6, 1.0F, 0.45F));
            break;
         case 207:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_7, 1.0F, 0.45F));
            break;
         case 208:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_8, 1.0F, 0.45F));
            break;
         case 209:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_9, 1.0F, 0.45F));
            break;
         case 210:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_10, 1.0F, 0.45F));
            break;
         case 211:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_11, 1.0F, 0.45F));
            break;
         case 212:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_12, 1.0F, 0.45F));
            break;
         case 213:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_13, 1.0F, 0.45F));
            break;
         case 214:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_14, 1.0F, 0.45F));
            break;
         case 215:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_15, 1.0F, 0.45F));
            break;
         case 216:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_16, 1.0F, 0.45F));
            break;
         case 217:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_17, 1.0F, 0.45F));
            break;
         case 218:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_18, 1.0F, 0.45F));
            break;
         case 219:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_19, 1.0F, 0.45F));
            break;
         case 220:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_20, 1.0F, 0.45F));
            break;
         case 221:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_21, 1.0F, 0.45F));
            break;
         case 222:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_22, 1.0F, 0.45F));
            break;
         case 223:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_23, 1.0F, 0.45F));
            break;
         case 224:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_24, 1.0F, 0.45F));
            break;
         case 225:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_25, 1.0F, 0.45F));
            break;
         case 226:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_26, 1.0F, 0.45F));
            break;
         case 227:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_27, 1.0F, 0.45F));
            break;
         case 228:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_28, 1.0F, 0.45F));
            break;
         case 229:
            Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.DISLO_29, 1.0F, 0.45F));
            break;
         case 400:
            Minecraft.func_71410_x()
               .func_147118_V()
               .func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.ORIGINNEW, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F, 0.75F));
            break;
         case 401:
            Minecraft.func_71410_x()
               .func_147118_V()
               .func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.ORIGINOUTBREAK, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F, 0.75F));
            break;
         case 402:
            Minecraft.func_71410_x()
               .func_147118_V()
               .func_147682_a(PositionedSoundRecord.func_194007_a(SRPSounds.ORIGINDELETED, (rand.nextFloat() - rand.nextFloat()) * 0.2F + 1.0F, 0.75F));
      }
   }

   @Override
   public void modelReg(Item item, int meta, String id) {
      if (item != null) {
         ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), id));
      }
   }

   @Override
   public <T extends AbstractPacket<T>> void networkMessage(T message, MessageContext messageContext) {
      if (messageContext.side.isServer()) {
         super.networkMessage(message, messageContext);
      } else {
         MC.func_152344_a(() -> message.clientSide(MC, message, MC.field_71439_g, messageContext));
      }
   }
}
