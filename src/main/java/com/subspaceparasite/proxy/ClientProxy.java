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
package com.subspaceparasite.proxy;

import com.subspaceparasite.block.BlockBiomePurifier;
import com.subspaceparasite.block.BlockParasiteSpreading;
import com.subspaceparasite.client.DerivedDistortionTextHandler;
import com.subspaceparasite.client.DistortedItemHighlightOverlayHandler;
import com.subspaceparasite.client.DistortionGuiSwapHandler;
import com.subspaceparasite.client.celestial.CelestialObjectRegistry;
import com.subspaceparasite.client.fog.SPFogHandler;
import com.subspaceparasite.client.gui.GuiGameOverEscape;
import com.subspaceparasite.client.renderer.RenderDistortedSign;
import com.subspaceparasite.client.renderer.RenderRelayController;
import com.subspaceparasite.client.renderer.RenderTrophyTESR;
import com.subspaceparasite.client.shader.BreatheShaderCommand;
import com.subspaceparasite.client.sky.CommandSkyFlashClient;
import com.subspaceparasite.entity.tile.TileEntityTrophy;
import com.subspaceparasite.init.SPMusic;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.network.msg.AbstractPacket;
import com.subspaceparasite.proxy.CommonProxy;
import com.subspaceparasite.tileentity.TileEntityRelayController;
import com.subspaceparasite.util.SPReference;
import com.subspaceparasite.util.config.SPConfigWorld;
import com.subspaceparasite.util.handlers.SPEventHandlerBus;
import com.subspaceparasite.util.handlers.SPRegistryHandlers;
import com.subspaceparasite.world.biome.BiomeParasiteBase;
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
        SPRegistryHandlers.initRenders();
        MinecraftForge.EVENT_BUS.register((Object)new GuiGameOverEscape.OpenHook());
        MinecraftForge.EVENT_BUS.register((Object)new DerivedDistortionTextHandler());
        MinecraftForge.EVENT_BUS.register((Object)new DistortionGuiSwapHandler());
        MinecraftForge.EVENT_BUS.register((Object)new DistortedItemHighlightOverlayHandler());
        BreatheShaderCommand.register();
        ClientProxy.initMusic();
        CelestialObjectRegistry.init();
        OBJLoader.INSTANCE.addDomain("subspaceparasite");
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRelayController.class, (TileEntitySpecialRenderer)new RenderRelayController());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySign.class, (TileEntitySpecialRenderer)new RenderDistortedSign());
    }

    public static void initMusic() {
        SPMusic.EVPHASE_1_MUSIC = EnumHelperClient.addMusicType((String)"srpphaseone", (SoundEvent)SPSounds.EVPHASE_1_M, (int)20, (int)200);
        SPMusic.EVPHASE_2_MUSIC = EnumHelperClient.addMusicType((String)"srpphasetwo", (SoundEvent)SPSounds.EVPHASE_2_M, (int)20, (int)200);
        SPMusic.EVPHASE_3_MUSIC = EnumHelperClient.addMusicType((String)"srpphasethree", (SoundEvent)SPSounds.EVPHASE_3_M, (int)20, (int)200);
        SPMusic.EVPHASE_4_MUSIC = EnumHelperClient.addMusicType((String)"srpphasefour", (SoundEvent)SPSounds.EVPHASE_4_M, (int)20, (int)200);
        SPMusic.EVPHASE_5_MUSIC = EnumHelperClient.addMusicType((String)"srpphasefive", (SoundEvent)SPSounds.EVPHASE_5_M, (int)20, (int)200);
        SPMusic.EVPHASE_6_MUSIC = EnumHelperClient.addMusicType((String)"srpphasesix", (SoundEvent)SPSounds.EVPHASE_6_M, (int)20, (int)200);
        SPMusic.EVPHASE_7_MUSIC = EnumHelperClient.addMusicType((String)"srpphaseseven", (SoundEvent)SPSounds.EVPHASE_7_M, (int)20, (int)200);
        SPMusic.EVPHASE_8_MUSIC = EnumHelperClient.addMusicType((String)"srpphaseeight", (SoundEvent)SPSounds.EVPHASE_8_M, (int)20, (int)200);
        SPMusic.EVPHASE_9_MUSIC = EnumHelperClient.addMusicType((String)"srpphasenine", (SoundEvent)SPSounds.EVPHASE_9_M, (int)20, (int)200);
        SPMusic.EVPHASE_10_MUSIC = EnumHelperClient.addMusicType((String)"srpphaseten", (SoundEvent)SPSounds.EVPHASE_10_M, (int)20, (int)200);
        SPMusic.BIOME_MUSIC = EnumHelperClient.addMusicType((String)"srpbiome", (SoundEvent)SPSounds.BIOME_M, (int)20, (int)200);
        SPMusic.SCENT_MUSIC = EnumHelperClient.addMusicType((String)"srpscent", (SoundEvent)SPSounds.SCENT_M, (int)20, (int)200);
        SPMusic.DISC1 = EnumHelperClient.addMusicType((String)"srpdiscone", (SoundEvent)SPSounds.DISC1, (int)20, (int)200);
        SPMusic.DISC2 = EnumHelperClient.addMusicType((String)"srpdisctwo", (SoundEvent)SPSounds.DISC2, (int)20, (int)200);
        SPMusic.DISC3 = EnumHelperClient.addMusicType((String)"srpdiscthree", (SoundEvent)SPSounds.DISC3, (int)20, (int)200);
    }

    @Override
    public void spreadBiome(BlockPos pos, boolean convert, int type) {
        if (Minecraft.func_71410_x().field_71441_e == null) {
            return;
        }
        Biome current = Minecraft.func_71410_x().field_71441_e.func_180494_b(pos);
        if (convert) {
            BiomeParasiteBase target = SPReference.getBiomeFromInt(type);
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
        SPFogHandler.register();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrophy.class, (TileEntitySpecialRenderer)new RenderTrophyTESR());
    }

    @Override
    public void playMovingSound(int sound, float v) {
        Random rand = new Random();
        switch (sound) {
            case -1: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.BHEART, (float)1.0f, (float)(v * SPConfigWorld.biomeHeartVol)));
                break;
            }
            case 1: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.EVPHASE_1, (float)1.0f, (float)0.75f));
                break;
            }
            case 2: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.EVPHASE_2, (float)1.0f, (float)0.75f));
                break;
            }
            case 3: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.EVPHASE_3, (float)1.0f, (float)0.75f));
                break;
            }
            case 4: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.EVPHASE_4, (float)1.0f, (float)0.75f));
                break;
            }
            case 5: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.EVPHASE_5, (float)1.0f, (float)0.75f));
                break;
            }
            case 6: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.EVPHASE_6, (float)1.0f, (float)0.75f));
                break;
            }
            case 7: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.EVPHASE_7, (float)1.0f, (float)0.75f));
                break;
            }
            case 8: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.EVPHASE_8, (float)1.0f, (float)0.75f));
                break;
            }
            case 9: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.EVPHASE_9, (float)1.0f, (float)0.75f));
                break;
            }
            case 10: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.EVPHASE_10, (float)1.0f, (float)0.75f));
                break;
            }
            case 100: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.NODE_1, (float)1.0f, (float)0.75f));
                break;
            }
            case 101: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.COLONY_1, (float)1.0f, (float)0.75f));
                break;
            }
            case 102: {
                if (SPEventHandlerBus.clientScent <= 0) {
                    Minecraft.func_71410_x().func_147118_V().func_189520_a("", SoundCategory.MUSIC);
                    Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_184370_a((SoundEvent)SPMusic.SCENT_MUSIC.func_188768_a()));
                }
                SPEventHandlerBus.musicTimer = 5000;
                SPEventHandlerBus.clientScent = 5000;
                break;
            }
            case 103: {
                SPEventHandlerBus.musicTimer = 1000;
                SPEventHandlerBus.clientScent = 140;
                break;
            }
            case 104: {
                SPEventHandlerBus.musicTimer = 1000;
                Minecraft.func_71410_x().func_147118_V().func_189520_a("", SoundCategory.MUSIC);
                break;
            }
            case 200: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_0, (float)1.0f, (float)0.45f));
                break;
            }
            case 201: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_1, (float)1.0f, (float)0.45f));
                break;
            }
            case 202: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_2, (float)1.0f, (float)0.45f));
                break;
            }
            case 203: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_3, (float)1.0f, (float)0.45f));
                break;
            }
            case 204: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_4, (float)1.0f, (float)0.45f));
                break;
            }
            case 205: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_5, (float)1.0f, (float)0.45f));
                break;
            }
            case 206: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_6, (float)1.0f, (float)0.45f));
                break;
            }
            case 207: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_7, (float)1.0f, (float)0.45f));
                break;
            }
            case 208: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_8, (float)1.0f, (float)0.45f));
                break;
            }
            case 209: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_9, (float)1.0f, (float)0.45f));
                break;
            }
            case 210: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_10, (float)1.0f, (float)0.45f));
                break;
            }
            case 211: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_11, (float)1.0f, (float)0.45f));
                break;
            }
            case 212: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_12, (float)1.0f, (float)0.45f));
                break;
            }
            case 213: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_13, (float)1.0f, (float)0.45f));
                break;
            }
            case 214: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_14, (float)1.0f, (float)0.45f));
                break;
            }
            case 215: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_15, (float)1.0f, (float)0.45f));
                break;
            }
            case 216: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_16, (float)1.0f, (float)0.45f));
                break;
            }
            case 217: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_17, (float)1.0f, (float)0.45f));
                break;
            }
            case 218: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_18, (float)1.0f, (float)0.45f));
                break;
            }
            case 219: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_19, (float)1.0f, (float)0.45f));
                break;
            }
            case 220: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_20, (float)1.0f, (float)0.45f));
                break;
            }
            case 221: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_21, (float)1.0f, (float)0.45f));
                break;
            }
            case 222: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_22, (float)1.0f, (float)0.45f));
                break;
            }
            case 223: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_23, (float)1.0f, (float)0.45f));
                break;
            }
            case 224: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_24, (float)1.0f, (float)0.45f));
                break;
            }
            case 225: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_25, (float)1.0f, (float)0.45f));
                break;
            }
            case 226: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_26, (float)1.0f, (float)0.45f));
                break;
            }
            case 227: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_27, (float)1.0f, (float)0.45f));
                break;
            }
            case 228: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_28, (float)1.0f, (float)0.45f));
                break;
            }
            case 229: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.DISLO_29, (float)1.0f, (float)0.45f));
                break;
            }
            case 400: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.ORIGINNEW, (float)((rand.nextFloat() - rand.nextFloat()) * 0.2f + 1.0f), (float)0.75f));
                break;
            }
            case 401: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.ORIGINOUTBREAK, (float)((rand.nextFloat() - rand.nextFloat()) * 0.2f + 1.0f), (float)0.75f));
                break;
            }
            case 402: {
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_194007_a((SoundEvent)SPSounds.ORIGINDELETED, (float)((rand.nextFloat() - rand.nextFloat()) * 0.2f + 1.0f), (float)0.75f));
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

