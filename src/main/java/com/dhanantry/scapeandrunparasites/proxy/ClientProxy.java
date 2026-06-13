/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.client.renderer.block.model.ModelResourceLocation
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.command.ICommand
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.tileentity.TileEntitySign
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.Biome
 *  net.minecraftforge.client.ClientCommandHandler
 *  net.minecraftforge.client.EnumHelperClient
 *  net.minecraftforge.client.model.ModelLoader
 *  net.minecraftforge.client.model.obj.OBJLoader
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.client.registry.ClientRegistry
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
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
import com.dhanantry.scapeandrunparasites.client.shader.BreatheShaderCommand;
import com.dhanantry.scapeandrunparasites.client.sky.CommandSkyFlashClient;
import com.dhanantry.scapeandrunparasites.entity.tile.TileEntityTrophy;
import com.dhanantry.scapeandrunparasites.init.SRPMusic;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.network.msg.AbstractPacket;
import com.dhanantry.scapeandrunparasites.proxy.CommonProxy;
import com.dhanantry.scapeandrunparasites.tileentity.TileEntityRelayController;
import com.dhanantry.scapeandrunparasites.util.SRPReference;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.util.handlers.SRPEventHandlerBus;
import com.dhanantry.scapeandrunparasites.util.handlers.SRPRegistryHandlers;
import com.dhanantry.scapeandrunparasites.world.biome.BiomeParasiteBase;
import java.util.Objects;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.command.ICommand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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

public class ClientProxy
extends CommonProxy {
    public static final Minecraft MC = Minecraft.func_71410_x();

    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        SRPRegistryHandlers.initRenders();
        MinecraftForge.EVENT_BUS.register((Object)new GuiGameOverEscape.OpenHook());
        MinecraftForge.EVENT_BUS.register((Object)new DerivedDistortionTextHandler());
        MinecraftForge.EVENT_BUS.register((Object)new DistortionGuiSwapHandler());
        MinecraftForge.EVENT_BUS.register((Object)new DistortedItemHighlightOverlayHandler());
        BreatheShaderCommand.register();
        ClientProxy.initMusic();
        CelestialObjectRegistry.init();
        OBJLoader.INSTANCE.addDomain("srparasites");
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRelayController.class, (TileEntitySpecialRenderer)new RenderRelayController());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySign.class, (TileEntitySpecialRenderer)new RenderDistortedSign());
    }

    public static void initMusic() {
        SRPMusic.EVPHASE_1_MUSIC = EnumHelperClient.addMusicType((String)"srpphaseone", (SoundEvent)SRPSounds.EVPHASE_1_M, (int)20, (int)200);
        SRPMusic.EVPHASE_2_MUSIC = EnumHelperClient.addMusicType((String)"srpphasetwo", (SoundEvent)SRPSounds.EVPHASE_2_M, (int)20, (int)200);
        SRPMusic.EVPHASE_3_MUSIC = EnumHelperClient.addMusicType((String)"srpphasethree", (SoundEvent)SRPSounds.EVPHASE_3_M, (int)20, (int)200);
        SRPMusic.EVPHASE_4_MUSIC = EnumHelperClient.addMusicType((String)"srpphasefour", (SoundEvent)SRPSounds.EVPHASE_4_M, (int)20, (int)200);
        SRPMusic.EVPHASE_5_MUSIC = EnumHelperClient.addMusicType((String)"srpphasefive", (SoundEvent)SRPSounds.EVPHASE_5_M, (int)20, (int)200);
        SRPMusic.EVPHASE_6_MUSIC = EnumHelperClient.addMusicType((String)"srpphasesix", (SoundEvent)SRPSounds.EVPHASE_6_M, (int)20, (int)200);
        SRPMusic.EVPHASE_7_MUSIC = EnumHelperClient.addMusicType((String)"srpphaseseven", (SoundEvent)SRPSounds.EVPHASE_7_M, (int)20, (int)200);
        SRPMusic.EVPHASE_8_MUSIC = EnumHelperClient.addMusicType((String)"srpphaseeight", (SoundEvent)SRPSounds.EVPHASE_8_M, (int)20, (int)200);
        SRPMusic.EVPHASE_9_MUSIC = EnumHelperClient.addMusicType((String)"srpphasenine", (SoundEvent)SRPSounds.EVPHASE_9_M, (int)20, (int)200);
        SRPMusic.EVPHASE_10_MUSIC = EnumHelperClient.addMusicType((String)"srpphaseten", (SoundEvent)SRPSounds.EVPHASE_10_M, (int)20, (int)200);
        SRPMusic.BIOME_MUSIC = EnumHelperClient.addMusicType((String)"srpbiome", (SoundEvent)SRPSounds.BIOME_M, (int)20, (int)200);
        SRPMusic.SCENT_MUSIC = EnumHelperClient.addMusicType((String)"srpscent", (SoundEvent)SRPSounds.SCENT_M, (int)20, (int)200);
        SRPMusic.DISC1 = EnumHelperClient.addMusicType((String)"srpdiscone", (SoundEvent)SRPSounds.DISC1, (int)20, (int)200);
        SRPMusic.DISC2 = EnumHelperClient.addMusicType((String)"srpdisctwo", (SoundEvent)SRPSounds.DISC2, (int)20, (int)200);
        SRPMusic.DISC3 = EnumHelperClient.addMusicType((String)"srpdiscthree", (SoundEvent)SRPSounds.DISC3, (int)20, (int)200);
    }

    @Override
    public void spreadBiome(BlockPos pos, boolean convert, int type) {
        if (Minecraft.func_71410_x().field_71441_e == null) {
            return;
        }
        Biome current = Minecraft.func_71410_x().field_71441_e.func_180494_b(pos);
        if (convert) {
            BiomeParasiteBase target = SRPReference.getBiomeFromInt(type);
            if (target != null && current == target) {
                return;
            }
            BlockParasiteSpreading.positionToParasiteBiome((World)Minecraft.func_71410_x().field_71441_e, pos, type);
        } else {
            if (!(current instanceof BiomeParasiteBase)) {
                return;
            }
            BlockBiomePurifier.positionToBiome((World)Minecraft.func_71410_x().field_71441_e, pos, type);
        }
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        ClientCommandHandler.instance.func_71560_a((ICommand)new CommandSkyFlashClient());
        SRPFogHandler.register();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrophy.class, (TileEntitySpecialRenderer)new RenderTrophyTESR());
    }

    @Override
    public void playMovingSound(int sound, float v) {
        Random rand = new Random();
        switch (sound) {
            case -1: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.BHEART, (float)1.0f, (float)(v * SRPConfigWorld.biomeHeartVol)));
                break;
            }
            case 1: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.EVPHASE_1, (float)1.0f, (float)0.75f));
                break;
            }
            case 2: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.EVPHASE_2, (float)1.0f, (float)0.75f));
                break;
            }
            case 3: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.EVPHASE_3, (float)1.0f, (float)0.75f));
                break;
            }
            case 4: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.EVPHASE_4, (float)1.0f, (float)0.75f));
                break;
            }
            case 5: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.EVPHASE_5, (float)1.0f, (float)0.75f));
                break;
            }
            case 6: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.EVPHASE_6, (float)1.0f, (float)0.75f));
                break;
            }
            case 7: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.EVPHASE_7, (float)1.0f, (float)0.75f));
                break;
            }
            case 8: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.EVPHASE_8, (float)1.0f, (float)0.75f));
                break;
            }
            case 9: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.EVPHASE_9, (float)1.0f, (float)0.75f));
                break;
            }
            case 10: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.EVPHASE_10, (float)1.0f, (float)0.75f));
                break;
            }
            case 100: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.NODE_1, (float)1.0f, (float)0.75f));
                break;
            }
            case 101: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.COLONY_1, (float)1.0f, (float)0.75f));
                break;
            }
            case 102: {
                if (SRPEventHandlerBus.clientScent <= 0) {
                    Minecraft.func_71410_x().func_147118_V().func_189520_a("", SoundCategory.MUSIC);
                    Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_184370_a((SoundEvent)SRPMusic.SCENT_MUSIC.func_188768_a()));
                }
                SRPEventHandlerBus.musicTimer = 5000;
                SRPEventHandlerBus.clientScent = 5000;
                break;
            }
            case 103: {
                SRPEventHandlerBus.musicTimer = 1000;
                SRPEventHandlerBus.clientScent = 140;
                break;
            }
            case 104: {
                SRPEventHandlerBus.musicTimer = 1000;
                Minecraft.func_71410_x().func_147118_V().func_189520_a("", SoundCategory.MUSIC);
                break;
            }
            case 200: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_0, (float)1.0f, (float)0.45f));
                break;
            }
            case 201: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_1, (float)1.0f, (float)0.45f));
                break;
            }
            case 202: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_2, (float)1.0f, (float)0.45f));
                break;
            }
            case 203: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_3, (float)1.0f, (float)0.45f));
                break;
            }
            case 204: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_4, (float)1.0f, (float)0.45f));
                break;
            }
            case 205: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_5, (float)1.0f, (float)0.45f));
                break;
            }
            case 206: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_6, (float)1.0f, (float)0.45f));
                break;
            }
            case 207: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_7, (float)1.0f, (float)0.45f));
                break;
            }
            case 208: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_8, (float)1.0f, (float)0.45f));
                break;
            }
            case 209: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_9, (float)1.0f, (float)0.45f));
                break;
            }
            case 210: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_10, (float)1.0f, (float)0.45f));
                break;
            }
            case 211: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_11, (float)1.0f, (float)0.45f));
                break;
            }
            case 212: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_12, (float)1.0f, (float)0.45f));
                break;
            }
            case 213: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_13, (float)1.0f, (float)0.45f));
                break;
            }
            case 214: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_14, (float)1.0f, (float)0.45f));
                break;
            }
            case 215: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_15, (float)1.0f, (float)0.45f));
                break;
            }
            case 216: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_16, (float)1.0f, (float)0.45f));
                break;
            }
            case 217: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_17, (float)1.0f, (float)0.45f));
                break;
            }
            case 218: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_18, (float)1.0f, (float)0.45f));
                break;
            }
            case 219: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_19, (float)1.0f, (float)0.45f));
                break;
            }
            case 220: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_20, (float)1.0f, (float)0.45f));
                break;
            }
            case 221: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_21, (float)1.0f, (float)0.45f));
                break;
            }
            case 222: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_22, (float)1.0f, (float)0.45f));
                break;
            }
            case 223: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_23, (float)1.0f, (float)0.45f));
                break;
            }
            case 224: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_24, (float)1.0f, (float)0.45f));
                break;
            }
            case 225: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_25, (float)1.0f, (float)0.45f));
                break;
            }
            case 226: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_26, (float)1.0f, (float)0.45f));
                break;
            }
            case 227: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_27, (float)1.0f, (float)0.45f));
                break;
            }
            case 228: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_28, (float)1.0f, (float)0.45f));
                break;
            }
            case 229: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.DISLO_29, (float)1.0f, (float)0.45f));
                break;
            }
            case 400: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.ORIGINNEW, (float)((rand.nextFloat() - rand.nextFloat()) * 0.2f + 1.0f), (float)0.75f));
                break;
            }
            case 401: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.ORIGINOUTBREAK, (float)((rand.nextFloat() - rand.nextFloat()) * 0.2f + 1.0f), (float)0.75f));
                break;
            }
            case 402: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SRPSounds.ORIGINDELETED, (float)((rand.nextFloat() - rand.nextFloat()) * 0.2f + 1.0f), (float)0.75f));
            }
        }
    }

    @Override
    public void modelReg(Item item, int meta, String id) {
        if (item != null) {
            ModelLoader.setCustomModelResourceLocation((Item)item, (int)meta, (ModelResourceLocation)new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), id));
        }
    }

    @Override
    public <T extends AbstractPacket<T>> void networkMessage(T message, MessageContext messageContext) {
        if (messageContext.side.isServer()) {
            super.networkMessage(message, messageContext);
        } else {
            MC.func_152344_a(() -> message.clientSide(MC, message, (EntityPlayer)ClientProxy.MC.field_71439_g, messageContext));
        }
    }
}

