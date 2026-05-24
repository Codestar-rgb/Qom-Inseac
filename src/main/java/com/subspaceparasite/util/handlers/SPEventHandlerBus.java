/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.MusicTicker$MusicType
 *  net.minecraft.client.audio.PositionedSoundRecord
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$FogMode
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIAvoidEntity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.attributes.IAttribute
 *  net.minecraft.entity.item.EntityArmorStand
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.init.MobEffects
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.Style
 *  net.minecraft.util.text.TextComponentTranslation
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.GameRules
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraft.world.WorldType
 *  net.minecraftforge.client.event.EntityViewRenderEvent$FogColors
 *  net.minecraftforge.client.event.EntityViewRenderEvent$FogDensity
 *  net.minecraftforge.client.event.GuiOpenEvent
 *  net.minecraftforge.client.event.MouseEvent
 *  net.minecraftforge.client.event.TextureStitchEvent$Pre
 *  net.minecraftforge.client.event.sound.PlayStreamingSourceEvent
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 *  net.minecraftforge.event.entity.EntityMountEvent
 *  net.minecraftforge.event.entity.EntityStruckByLightningEvent
 *  net.minecraftforge.event.entity.living.LivingDamageEvent
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.event.entity.living.LivingDropsEvent
 *  net.minecraftforge.event.entity.living.LivingEntityUseItemEvent$Start
 *  net.minecraftforge.event.entity.living.LivingHealEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.event.entity.player.ItemFishedEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$EntityInteract
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$EntityInteractSpecific
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$RightClickBlock
 *  net.minecraftforge.event.entity.player.PlayerWakeUpEvent
 *  net.minecraftforge.event.world.BlockEvent$CropGrowEvent$Pre
 *  net.minecraftforge.event.world.BlockEvent$NeighborNotifyEvent
 *  net.minecraftforge.event.world.BlockEvent$PlaceEvent
 *  net.minecraftforge.event.world.WorldEvent$Unload
 *  net.minecraftforge.fluids.Fluid
 *  net.minecraftforge.fluids.FluidRegistry
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.eventhandler.Event$Result
 *  net.minecraftforge.fml.common.eventhandler.EventPriority
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ServerTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$WorldTickEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.registry.ForgeRegistries
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.apache.logging.log4j.Level
 *  org.lwjgl.input.Keyboard
 */
package com.subspaceparasite.util.handlers;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.block.BlockDiseasedSponge;
import com.subspaceparasite.block.BlockParasiteSpreading;
import com.subspaceparasite.client.particle.ParticleBiomass;
import com.subspaceparasite.client.particle.ParticleFog;
import com.subspaceparasite.client.particle.ParticleMultipleGore;
import com.subspaceparasite.client.particle.ParticleRHappy;
import com.subspaceparasite.client.particle.ParticleSpore;
import com.subspaceparasite.entity.ai.EntityAISkill;
import com.subspaceparasite.entity.ai.misc.EntityCanHaveBodies;
import com.subspaceparasite.entity.ai.misc.EntityPInfected;
import com.subspaceparasite.entity.ai.misc.EntityPMalleable;
import com.subspaceparasite.entity.ai.misc.EntityPStationary;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.entity.monster.crude.EntityInhooM;
import com.subspaceparasite.entity.monster.crude.EntityInhooS;
import com.subspaceparasite.entity.monster.hijacked.EntityHiGolem;
import com.subspaceparasite.entity.monster.infected.EntityInfPlayer;
import com.subspaceparasite.entity.monster.primitive.EntityNogla;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.init.SPMusic;
import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.item.tool.IHaveReach;
import com.subspaceparasite.item.tool.WeaponToolArmorBase;
import com.subspaceparasite.item.tool.WeaponToolMeleeBase;
import com.subspaceparasite.network.SPPacketFog;
import com.subspaceparasite.network.SPPacketMeleeRange;
import com.subspaceparasite.network.SPPacketMovingSound;
import com.subspaceparasite.network.SPPacketParticle;
import com.subspaceparasite.network.SPPacketRequestEvoPhaseClient;
import com.subspaceparasite.util.EIVUtil;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.ParasiteEventWorld;
import com.subspaceparasite.util.SPReference;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigMobs;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.util.config.SPConfigWorld;
import com.subspaceparasite.util.convert.BeckonBlockInfestation;
import com.subspaceparasite.util.spawn.ParasiteSummon;
import com.subspaceparasite.world.SPSaveData;
import com.subspaceparasite.world.SPWorldData;
import com.subspaceparasite.world.SPWorldParasiteSpawner;
import com.subspaceparasite.world.biome.BiomeParasiteBase;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldType;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.event.sound.PlayStreamingSourceEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;
import org.lwjgl.input.Keyboard;

public class SPEventHandlerBus {
    private static final Map<UUID, FogProperties> playerFogProperties = new HashMap<UUID, FogProperties>();
    private ArrayList<ISound> musicToRemove = new ArrayList();
    public static byte clientCurrentEvoPhase;
    public static int clientScent;
    public static int clientVector;
    public static int musicTimer;
    private byte lockedMu = 0;
    public static float fog;
    public static float fogRed;
    public static float fogGreen;
    public static float fogBlue;
    private static final Map<Integer, Long> RICARDO_DEATH_RULE_RESTORE;
    private static boolean srpSoakGuard;
    private boolean closeG = false;
    private int counerW = 0;
    private int blockInfestedCountCooldown;
    private int blockParasiteCountCooldown;
    private int moo = 0;
    private ArrayList<Integer> worldsChecked = new ArrayList();
    private int meteor;
    private int heart;

    @SubscribeEvent
    @SideOnly(value=Side.CLIENT)
    public void onClientTick(TickEvent.ClientTickEvent event) {
        Minecraft mc = Minecraft.func_71410_x();
        if (mc.field_71439_g == null) {
            return;
        }
        if (mc.field_71439_g.func_70644_a(SPPotions.INDEAF_E)) {
            int i = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();
            KeyBinding cfr_ignored_0 = mc.field_71474_y.field_74351_w;
            KeyBinding.func_74510_a((int)i, (boolean)false);
            KeyBinding cfr_ignored_1 = mc.field_71474_y.field_74368_y;
            KeyBinding.func_74510_a((int)i, (boolean)false);
            KeyBinding cfr_ignored_2 = mc.field_71474_y.field_74370_x;
            KeyBinding.func_74510_a((int)i, (boolean)false);
            KeyBinding cfr_ignored_3 = mc.field_71474_y.field_74366_z;
            KeyBinding.func_74510_a((int)i, (boolean)false);
            KeyBinding cfr_ignored_4 = mc.field_71474_y.field_74314_A;
            KeyBinding.func_74510_a((int)i, (boolean)false);
        }
    }

    @SubscribeEvent
    @SideOnly(value=Side.CLIENT)
    public void soundTwo(PlayStreamingSourceEvent event) {
        SPMain.logger.debug("phase: {} s: {} ticks: {}", (Object)clientCurrentEvoPhase, (Object)event.getName(), (Object)musicTimer);
        if (!SPConfig.musicTrue) {
            return;
        }
        if (!(event.getName().contains("subspaceparasite") || clientCurrentEvoPhase <= 0 && clientScent <= 0)) {
            if (SPConfigWorld.originActivated && clientScent <= 0 && clientVector <= 0) {
                return;
            }
            this.musicToRemove.add(event.getSound());
            SPMain.logger.debug("added {}", (Object)event.getName());
        }
    }

    public static void resetSouncTicker(int in) {
        int goo;
        if (in > 0) {
            if (musicTimer < in) {
                return;
            }
            musicTimer = in;
            return;
        }
        Random rand = new Random();
        int atm2 = SPConfig.musicMax - SPConfig.musicMin + 1;
        if (atm2 <= 0 || SPConfig.musicMax <= SPConfig.musicMin) {
            int n = SPConfig.musicMax + 1000;
        }
        if (musicTimer < (goo = rand.nextInt(atm2) + SPConfig.musicMin) && musicTimer > 0) {
            return;
        }
        musicTimer = goo;
    }

    @SubscribeEvent
    @SideOnly(value=Side.CLIENT)
    public void soundThree(TickEvent.PlayerTickEvent event) {
        if (!SPConfig.musicTrue) {
            return;
        }
        if (event.phase == TickEvent.Phase.END) {
            return;
        }
        if (event.side != Side.CLIENT) {
            return;
        }
        if (Minecraft.func_71410_x() == null) {
            return;
        }
        if (event.player.field_70173_aa % 20 == 0) {
            SPMain.network.sendToServer((IMessage)new SPPacketRequestEvoPhaseClient());
        }
        int prev = clientVector;
        if (clientVector > -10) {
            --clientVector;
        }
        if (SPConfigWorld.originActivated && clientVector <= 0) {
            if (prev > 0) {
                Minecraft.func_71410_x().func_147118_V().func_189520_a("", SoundCategory.MUSIC);
            }
            return;
        }
        if (!this.musicToRemove.isEmpty()) {
            for (int i = 0; i < this.musicToRemove.size(); ++i) {
                if (Minecraft.func_71410_x().func_147118_V().func_147692_c(this.musicToRemove.get(i))) {
                    Minecraft.func_71410_x().func_147118_V().func_147683_b(this.musicToRemove.get(i));
                    this.musicToRemove.remove(i);
                    --i;
                    continue;
                }
                this.lockedMu = (byte)(this.lockedMu + 1);
            }
            if (this.lockedMu >= 100) {
                this.musicToRemove = new ArrayList();
            }
        }
        if (musicTimer >= 0) {
            --musicTimer;
        }
        if (clientScent >= 0) {
            --clientScent;
        }
        if (clientScent == -1) {
            Minecraft.func_71410_x().func_147118_V().func_189520_a("", SoundCategory.MUSIC);
            clientScent = -10;
        }
        if (musicTimer <= 0 && this.musicToRemove.isEmpty() && clientCurrentEvoPhase > 0) {
            SPEventHandlerBus.resetSouncTicker(0);
            MusicTicker.MusicType evoPhaseMusic = this.getMusicPhase();
            if (evoPhaseMusic == null) {
                return;
            }
            if (clientScent <= 0) {
                Minecraft.func_71410_x().func_147118_V().func_189520_a("", SoundCategory.MUSIC);
                Minecraft.func_71410_x().func_147118_V().func_147682_a((ISound)PositionedSoundRecord.func_184370_a((SoundEvent)evoPhaseMusic.func_188768_a()));
            }
        } else {
            SPEventHandlerBus.resetSouncTicker(0);
        }
    }

    @SideOnly(value=Side.CLIENT)
    private MusicTicker.MusicType getMusicPhase() {
        EntityPlayerSP player = Minecraft.func_71410_x().field_71439_g;
        if (player.field_70170_p.func_180494_b(player.func_180425_c()) instanceof BiomeParasiteBase) {
            return SPMusic.BIOME_MUSIC;
        }
        switch (clientCurrentEvoPhase) {
            case 1: {
                return SPMusic.EVPHASE_1_MUSIC;
            }
            case 2: {
                return SPMusic.EVPHASE_2_MUSIC;
            }
            case 3: {
                return SPMusic.EVPHASE_3_MUSIC;
            }
            case 4: {
                return SPMusic.EVPHASE_4_MUSIC;
            }
            case 5: {
                return SPMusic.EVPHASE_5_MUSIC;
            }
            case 6: {
                return SPMusic.EVPHASE_6_MUSIC;
            }
            case 7: {
                return SPMusic.EVPHASE_7_MUSIC;
            }
            case 8: {
                return SPMusic.EVPHASE_8_MUSIC;
            }
            case 9: {
                return SPMusic.EVPHASE_9_MUSIC;
            }
            case 10: {
                return SPMusic.EVPHASE_10_MUSIC;
            }
        }
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void onWorldLoad(WorldEvent.Unload event) {
        clientCurrentEvoPhase = 0;
        clientScent = 0;
        fog = 0.0f;
        musicTimer = 1000;
        SPSaveData.falseLevel = 0;
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void onEvent(EntityViewRenderEvent.FogDensity event) {
        Entity entity = event.getEntity();
        if (entity.func_70055_a(SPBlocks.PARASITEBLOOD)) {
            GlStateManager.func_187430_a((GlStateManager.FogMode)GlStateManager.FogMode.EXP);
            event.setDensity(1.0f);
            event.setCanceled(true);
            return;
        }
        if (fog > 0.0f && !Minecraft.func_71410_x().field_71439_g.func_70644_a(MobEffects.field_76440_q)) {
            GlStateManager.func_187430_a((GlStateManager.FogMode)GlStateManager.FogMode.EXP);
            event.setDensity(fog);
            event.setCanceled(true);
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void onEvent(EntityViewRenderEvent.FogColors event) {
        Entity entity = event.getEntity();
        if (entity.func_70055_a(SPBlocks.PARASITEBLOOD)) {
            event.setRed(0.1f);
            event.setGreen(0.6f);
            event.setBlue(0.2f);
            return;
        }
        if ((double)fog > (double)SPConfigWorld.biomeFogDensity * 0.2 && !Minecraft.func_71410_x().field_71439_g.func_70644_a(MobEffects.field_76440_q)) {
            event.setRed(fogRed);
            event.setGreen(fogGreen);
            event.setBlue(fogBlue);
        }
    }

    @SubscribeEvent
    public void cropGrow(BlockEvent.CropGrowEvent.Pre event) {
        if (event.getResult() == Event.Result.DENY) {
            return;
        }
        if (!SPConfigWorld.nodesActivated && !SPConfigSystems.useEvolution) {
            return;
        }
        SPWorldData data = SPWorldData.get(event.getWorld());
        int nodeAge = data.nearestHeartAge(event.getPos(), false, 0);
        double[] nodeChance = new double[]{0.0, SPConfigWorld.nodeCropStopNodeOne, SPConfigWorld.nodeCropStopNodeTwo, SPConfigWorld.nodeCropStopNodeThree};
        if (nodeAge >= 1 && nodeAge <= 3 && event.getWorld().field_73012_v.nextDouble() < nodeChance[nodeAge]) {
            event.setResult(Event.Result.DENY);
            return;
        }
        byte phase = SPSaveData.get(event.getWorld(), 79).getEvolutionPhase(event.getWorld().field_73011_w.getDimension());
        double[] phaseChance = new double[]{0.0, SPConfigSystems.cropGrowStunnedOne, SPConfigSystems.cropGrowStunnedTwo, SPConfigSystems.cropGrowStunnedThree, SPConfigSystems.cropGrowStunnedFour, SPConfigSystems.cropGrowStunnedFive, SPConfigSystems.cropGrowStunnedSix, SPConfigSystems.cropGrowStunnedSeven, SPConfigSystems.cropGrowStunnedEight};
        if (phase >= 1 && phase < phaseChance.length && event.getWorld().field_73012_v.nextDouble() < phaseChance[phase]) {
            event.setResult(Event.Result.DENY);
        }
    }

    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof EntityPlayer)) {
            return;
        }
        Entity src = event.getSource().func_76346_g();
        if (!(src instanceof EntityNogla)) {
            return;
        }
        EntityNogla nogla = (EntityNogla)src;
        if (!nogla.isRicardoVariant()) {
            return;
        }
        EntityPlayer player = (EntityPlayer)event.getEntity();
        MinecraftServer server = player.func_184102_h();
        if (server == null) {
            return;
        }
        WorldServer ws = (WorldServer)player.field_70170_p;
        GameRules rules = ws.func_82736_K();
        boolean wasShowing = rules.func_82766_b("showDeathMessages");
        if (wasShowing) {
            rules.func_82764_b("showDeathMessages", "false");
            RICARDO_DEATH_RULE_RESTORE.put(ws.field_73011_w.getDimension(), ws.func_82737_E() + 1L);
        }
        server.func_184103_al().func_148539_a((ITextComponent)new TextComponentTranslation("death.attack.subspaceparasite.ricardo", new Object[]{player.func_145748_c_()}));
    }

    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent e) {
        if (e.phase != TickEvent.Phase.END || e.world.field_72995_K) {
            return;
        }
        Integer dim = e.world.field_73011_w.getDimension();
        Long when = RICARDO_DEATH_RULE_RESTORE.get(dim);
        if (when != null && e.world.func_82737_E() >= when) {
            ((WorldServer)e.world).func_82736_K().func_82764_b("showDeathMessages", "true");
            RICARDO_DEATH_RULE_RESTORE.remove(dim);
        }
    }

    private static Block getDeadBloodBlock() {
        Fluid f = FluidRegistry.getFluid((String)"deadblood");
        if (f == null) {
            return Blocks.field_150350_a;
        }
        Block b = f.getBlock();
        return b == null ? Blocks.field_150350_a : b;
    }

    private static Block getDiseasedSpongeBlock() {
        return SPBlocks.diseasedSponge;
    }

    private static boolean isSponge(Block b) {
        return b == Blocks.field_150360_v;
    }

    @SubscribeEvent
    public void onBlockPlaced(BlockEvent.PlaceEvent e) {
        if (e.getWorld().field_72995_K) {
            return;
        }
        SPEventHandlerBus.tryConvertSponge(e.getWorld(), e.getPos(), e.getPlacedBlock());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @SubscribeEvent
    public void onNeighborNotify(BlockEvent.NeighborNotifyEvent e) {
        block20: {
            if (e.getWorld().field_72995_K) {
                return;
            }
            World world = e.getWorld();
            BlockPos pos = e.getPos();
            Block deadBlood = SPEventHandlerBus.getDeadBloodBlock();
            Block changed = e.getState().func_177230_c();
            IBlockState changedState = e.getState();
            if (!(SPEventHandlerBus.isSponge(changed) || changed == deadBlood || SPEventHandlerBus.isWater(changedState) || SPEventHandlerBus.isLava(changedState))) {
                return;
            }
            if (srpSoakGuard) {
                return;
            }
            srpSoakGuard = true;
            try {
                if (changed == deadBlood) {
                    for (EnumFacing f : EnumFacing.values()) {
                        IBlockState rubble;
                        BlockPos n = pos.func_177972_a(f);
                        IBlockState st = world.func_180495_p(n);
                        if (SPEventHandlerBus.isSponge(st.func_177230_c())) {
                            SPEventHandlerBus.tryConvertSponge(world, n, st);
                            continue;
                        }
                        if (SPEventHandlerBus.isWater(st)) {
                            IBlockState stain = SPEventHandlerBus.getParasiteStainState();
                            if (stain.func_177230_c() == Blocks.field_150350_a) continue;
                            world.func_180501_a(n, stain, 2);
                            continue;
                        }
                        if (!SPEventHandlerBus.isLava(st) || (rubble = SPEventHandlerBus.getParasiteRubbleState()).func_177230_c() == Blocks.field_150350_a) continue;
                        world.func_180501_a(n, rubble, 2);
                    }
                    return;
                }
                if (SPEventHandlerBus.isWater(changedState) || SPEventHandlerBus.isLava(changedState)) {
                    boolean touchesDeadBlood = false;
                    for (EnumFacing f : EnumFacing.values()) {
                        if (world.func_180495_p(pos.func_177972_a(f)).func_177230_c() != deadBlood) continue;
                        touchesDeadBlood = true;
                        break;
                    }
                    if (touchesDeadBlood) {
                        if (SPEventHandlerBus.isWater(changedState)) {
                            IBlockState stain = SPEventHandlerBus.getParasiteStainState();
                            if (stain.func_177230_c() != Blocks.field_150350_a) {
                                world.func_180501_a(pos, stain, 2);
                            }
                        } else {
                            IBlockState rubble = SPEventHandlerBus.getParasiteRubbleState();
                            if (rubble.func_177230_c() != Blocks.field_150350_a) {
                                world.func_180501_a(pos, rubble, 2);
                            }
                        }
                    }
                    return;
                }
                if (!SPEventHandlerBus.isSponge(changed)) break block20;
                for (EnumFacing f : EnumFacing.values()) {
                    if (world.func_180495_p(pos.func_177972_a(f)).func_177230_c() != deadBlood) continue;
                    SPEventHandlerBus.tryConvertSponge(world, pos, changedState);
                    break;
                }
            }
            finally {
                srpSoakGuard = false;
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void tryConvertSponge(World world, BlockPos spongePos, IBlockState spongeState) {
        if (srpSoakGuard) {
            return;
        }
        if (!SPEventHandlerBus.isSponge(spongeState.func_177230_c())) {
            return;
        }
        Block deadBlood = SPEventHandlerBus.getDeadBloodBlock();
        Block diseased = SPEventHandlerBus.getDiseasedSpongeBlock();
        if (deadBlood == Blocks.field_150350_a || diseased == Blocks.field_150350_a) {
            return;
        }
        boolean touching = false;
        for (EnumFacing f : EnumFacing.values()) {
            if (world.func_180495_p(spongePos.func_177972_a(f)).func_177230_c() != deadBlood) continue;
            touching = true;
            break;
        }
        if (!touching) {
            return;
        }
        srpSoakGuard = true;
        try {
            boolean absorbed = BlockDiseasedSponge.absorbDeadBlood(world, spongePos, deadBlood);
            if (absorbed) {
                world.func_180501_a(spongePos, diseased.func_176223_P(), 2);
            }
        }
        finally {
            srpSoakGuard = false;
        }
    }

    private static IBlockState getParasiteStainState() {
        Block b = (Block)ForgeRegistries.BLOCKS.getValue(new ResourceLocation("subspaceparasite", "parasitestain"));
        if (b == null || b == Blocks.field_150350_a) {
            return Blocks.field_150350_a.func_176223_P();
        }
        return b.func_176203_a(1);
    }

    private static IBlockState getParasiteRubbleState() {
        Block b = (Block)ForgeRegistries.BLOCKS.getValue(new ResourceLocation("subspaceparasite", "parasiterubble"));
        if (b == null || b == Blocks.field_150350_a) {
            return Blocks.field_150350_a.func_176223_P();
        }
        return b.func_176203_a(7);
    }

    private static boolean isWater(IBlockState st) {
        Material m = st.func_185904_a();
        return m == Material.field_151586_h;
    }

    private static boolean isLava(IBlockState st) {
        Material m = st.func_185904_a();
        return m == Material.field_151587_i;
    }

    @SubscribeEvent
    public void entityHeal(LivingHealEvent event) {
        if (event.isCanceled()) {
            return;
        }
        EntityLivingBase entity = event.getEntityLiving();
        if (entity == null || entity.field_70170_p.field_72995_K) {
            return;
        }
        if (entity.field_70170_p.func_180494_b(entity.func_180425_c()) instanceof BiomeParasiteBase) {
            float penaltyH;
            switch (ParasiteEventWorld.canBiomeStillExistType(entity.field_70170_p, entity.func_180425_c(), true)) {
                case 2: {
                    penaltyH = SPConfigWorld.biomeTwoHealPenalty;
                    break;
                }
                case 3: {
                    penaltyH = SPConfigWorld.biomeThreeHealPenalty;
                    break;
                }
                case 4: {
                    penaltyH = SPConfigWorld.biomeFourHealPenalty;
                    break;
                }
                default: {
                    penaltyH = SPConfigWorld.biomeOneHealPenalty;
                }
            }
            if (entity instanceof EntityParasiteBase) {
                return;
            }
            if (entity instanceof EntityPlayer) {
                event.setAmount(event.getAmount() * penaltyH);
            } else {
                boolean flag = ParasiteEventEntity.checkName(EntityList.func_191301_a((Entity)entity).toString(), SPConfigWorld.biomeHealPenaltyBlackList, SPConfigWorld.biomeHealPenaltyBlackListWhite);
                if (flag) {
                    return;
                }
                event.setAmount(event.getAmount() * penaltyH);
            }
        }
        if (SPConfigSystems.useEvolution && SPSaveData.get(entity.field_70170_p, 80).getEvolutionPhase(entity.field_70170_p.field_73011_w.getDimension()) >= SPConfigSystems.evolutionNoParasiteHealing) {
            event.setAmount(event.getAmount() * SPConfigSystems.evolutionNoParasiteHealingValue);
        }
    }

    @SubscribeEvent
    public void entityHurt(LivingHurtEvent event) {
        EntityLivingBase mob;
        float damage;
        float amp;
        EntityLivingBase entity = event.getEntityLiving();
        if (entity == null || entity.field_70170_p.field_72995_K) {
            return;
        }
        if (entity.func_70644_a(SPPotions.VIRA_E) && SPConfigSystems.viralEnable) {
            amp = entity.func_70660_b(SPPotions.VIRA_E).func_76458_c() + 1;
            damage = event.getAmount();
            event.setAmount(damage + damage * (amp * SPConfigSystems.viralAmount));
        }
        if (entity.func_70644_a(SPPotions.OVERHEATING_E) && (event.getSource() == DamageSource.field_76370_b || event.getSource() == DamageSource.field_76372_a)) {
            amp = entity.func_70660_b(SPPotions.OVERHEATING_E).func_76458_c() + 1;
            damage = event.getAmount();
            event.getSource().func_151518_m();
            event.setAmount(damage + damage * (amp * 1.0f));
        }
        if (event.getSource().func_76346_g() instanceof EntityLivingBase && (mob = (EntityLivingBase)event.getSource().func_76346_g()).func_70644_a(SPPotions.MUSCLEOUT_E)) {
            float amp2 = (float)mob.func_70660_b(SPPotions.MUSCLEOUT_E).func_76458_c() + 1.0f;
            event.setAmount(event.getAmount() * (SPConfigSystems.muscleoutDamageOut * amp2));
        }
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            event.setAmount(this.playerArmor(player, event.getAmount(), event.getSource()));
        }
    }

    private float playerArmor(EntityPlayer player, float damageV, DamageSource source) {
        float red = 0.0f;
        String damage = "";
        byte type = 0;
        boolean needCheckP = true;
        boolean paraA = false;
        for (ItemStack itemstack : player.field_71071_by.field_70460_b) {
            int i;
            NBTTagList allResI;
            NBTTagList allResS;
            if (!(itemstack.func_77973_b() instanceof WeaponToolArmorBase)) continue;
            paraA = true;
            if (needCheckP && source.func_76364_f() != null) {
                if (source == DamageSource.field_76372_a || source == DamageSource.field_76370_b || player.func_70027_ad()) {
                    return damageV * SPConfig.firemultyplier;
                }
                if (source.func_76364_f() instanceof EntityPlayer) {
                    damage = source.func_76364_f().func_70005_c_();
                } else if (source.func_76364_f() instanceof EntityLivingBase) {
                    damage = EntityList.func_191301_a((Entity)source.func_76364_f()).toString();
                } else {
                    damage = source.field_76373_n;
                    type = 2;
                }
                needCheckP = false;
            }
            ArrayList<String> resistanceS = new ArrayList<String>();
            ArrayList<Integer> resistanceI = new ArrayList<Integer>();
            NBTTagCompound compound = itemstack.func_77978_p();
            boolean lag = ((WeaponToolArmorBase)itemstack.func_77973_b()).canCall();
            if (compound == null) {
                compound = new NBTTagCompound();
            }
            if (compound.func_150297_b("sprresistanceb", 99)) {
                lag = compound.func_74767_n("sprresistanceb");
            }
            if (compound.func_74764_b("sprresistances")) {
                allResS = compound.func_150295_c("sprresistances", 10);
                allResI = compound.func_150295_c("sprresistancei", 10);
                if (allResS.func_74745_c() != allResI.func_74745_c()) {
                    return damageV;
                }
                for (i = 0; i < allResS.func_74745_c(); ++i) {
                    NBTTagCompound resT = allResS.func_150305_b(i);
                    String res = resT.func_74779_i("resistance" + i);
                    resistanceS.add(i, res);
                    NBTTagCompound resU = allResI.func_150305_b(i);
                    int resi = resU.func_74762_e("resistance" + i);
                    resistanceI.add(i, resi);
                }
            }
            red += (float)this.hasResistance(damage, resistanceS, resistanceI, lag, player.field_70170_p.field_73012_v, type) * (lag ? SPConfig.sentientPointReduction : SPConfig.livingPointReduction);
            if (resistanceS.size() != resistanceI.size()) {
                return damageV;
            }
            allResS = new NBTTagList();
            allResI = new NBTTagList();
            for (i = 0; i < resistanceS.size(); ++i) {
                String res = resistanceS.get(i);
                NBTTagCompound resT = new NBTTagCompound();
                resT.func_74778_a("resistance" + i, res);
                allResS.func_74742_a((NBTBase)resT);
                int resi = resistanceI.get(i);
                NBTTagCompound resU = new NBTTagCompound();
                resU.func_74768_a("resistance" + i, resi);
                allResI.func_74742_a((NBTBase)resU);
            }
            compound.func_74782_a("sprresistances", (NBTBase)allResS);
            compound.func_74782_a("sprresistancei", (NBTBase)allResI);
            compound.func_74757_a("sprresistanceb", lag);
            if (compound.func_74764_b("srphits")) {
                int key = (int)((float)compound.func_74762_e("srphits") + damageV);
                compound.func_74768_a("srphits", key);
            } else {
                compound.func_74768_a("srphits", (int)damageV);
            }
            itemstack.func_77982_d(compound);
        }
        red *= damageV;
        damageV = Math.max(damageV - red, 0.0f);
        if (source.func_76364_f() instanceof EntityLivingBase && paraA && SPConfig.armorCoth) {
            SPPotions.applyStackPotion(SPPotions.COTH_E, (EntityLivingBase)source.func_76364_f(), 400, 2);
        }
        return damageV;
    }

    private int hasResistance(String damage, ArrayList<String> resistanceS, ArrayList<Integer> resistanceI, boolean stage, Random rand, byte type) {
        double getChanceLearn;
        if (this.checkList(damage, type)) {
            return 0;
        }
        double d = getChanceLearn = stage ? SPConfig.sentientChanceLe : SPConfig.livingChanceLe;
        if (rand.nextDouble() < getChanceLearn) {
            this.addResistance(damage, resistanceS, resistanceI, stage);
        }
        for (int i = 0; i < resistanceS.size(); ++i) {
            if (!resistanceS.get(i).equals(damage)) continue;
            int tage = SPConfig.livingPointCap;
            if (stage) {
                tage = SPConfig.sentientPointCap;
            }
            return Math.min(resistanceI.get(i), tage);
        }
        return 0;
    }

    private void addResistance(String damage, ArrayList<String> resistanceS, ArrayList<Integer> resistanceI, boolean stage) {
        boolean flag = true;
        for (int i = 0; i < resistanceS.size(); ++i) {
            if (!resistanceS.get(i).equals(damage)) continue;
            int iiii = resistanceI.get(i) + 1;
            resistanceI.set(i, iiii);
            flag = false;
            break;
        }
        if (flag) {
            int lim = SPConfig.livingDamageCap;
            if (stage) {
                lim = SPConfig.sentientDamageCap;
            }
            if (resistanceS.size() >= lim) {
                return;
            }
            resistanceS.add(damage);
            resistanceI.add(1);
        }
    }

    private boolean checkList(String damage, byte type) {
        switch (type) {
            case 0: {
                if (ParasiteEventEntity.checkName(damage, SPConfig.armorDamageTypeBlackListMob, SPConfig.armorDamageTypeBlackListWhite)) {
                    return true;
                }
            }
            case 2: {
                if (!ParasiteEventEntity.checkName(damage, SPConfig.armorDamageTypeBlackListElse, SPConfig.armorDamageTypeBlackListWhite)) break;
                return true;
            }
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void stitchEventPre(TextureStitchEvent.Pre event) {
        event.getMap().func_174942_a(ParticleSpore.PARTICLES_TEXTURE);
        event.getMap().func_174942_a(ParticleFog.PARTICLES_TEXTURE);
        event.getMap().func_174942_a(ParticleMultipleGore.PARTICLES_TEXTUREINFECTED);
        event.getMap().func_174942_a(ParticleMultipleGore.PARTICLES_TEXTURESPIDER);
        event.getMap().func_174942_a(ParticleMultipleGore.PARTICLES_TEXTUREPRIMITIVE);
        event.getMap().func_174942_a(ParticleMultipleGore.PARTICLES_TEXTUREADAPTED);
        event.getMap().func_174942_a(ParticleMultipleGore.PARTICLES_TEXTUREVOMIT);
        event.getMap().func_174942_a(ParticleRHappy.PARTICLES_TEXTURE);
        event.getMap().func_174942_a(ParticleBiomass.PARTICLES_TEXTURE);
    }

    @SubscribeEvent
    public void playerFishing(ItemFishedEvent event) {
        if (!event.getEntity().field_70170_p.field_72995_K && SPConfigSystems.useEvolution) {
            SPWorldData data = SPWorldData.get(event.getEntity().field_70170_p);
            if (SPSaveData.get(event.getEntity().field_70170_p, 81).getEvolutionPhase(event.getEntity().field_70170_p.field_73011_w.getDimension()) >= SPConfigSystems.evolutionStopFishing) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void itemEntity(PlayerInteractEvent.EntityInteract event) {
        if (event.getTarget() instanceof EntityLivingBase && !event.getWorld().field_72995_K) {
            ItemStack stack = event.getItemStack();
            String item = stack.func_77973_b().getRegistryName().toString();
            String[] atm = new String[3];
            for (int i = 0; i < SPConfigSystems.COTHItemPrevent.length; ++i) {
                atm = SPConfigSystems.COTHItemPrevent[i].split(";");
                if (!atm[0].equals(item)) continue;
                int dur = Integer.parseInt(atm[2]);
                if (!event.getEntityPlayer().field_71075_bZ.field_75098_d) {
                    stack.func_190918_g(1);
                    double chance = Double.parseDouble(atm[1]);
                    if (!(event.getWorld().field_73012_v.nextDouble() < chance)) continue;
                    ((EntityLivingBase)event.getTarget()).func_70690_d(new PotionEffect(SPPotions.EPEL_E, dur * 20, 0));
                    SPMain.network.sendToAll((IMessage)new SPPacketParticle(event.getTarget().field_70165_t, event.getTarget().field_70163_u, event.getTarget().field_70161_v, event.getTarget().field_70130_N, event.getTarget().field_70131_O, 3));
                    continue;
                }
                ((EntityLivingBase)event.getTarget()).func_70690_d(new PotionEffect(SPPotions.EPEL_E, dur * 20, 0));
                SPMain.network.sendToAll((IMessage)new SPPacketParticle(event.getTarget().field_70165_t, event.getTarget().field_70163_u, event.getTarget().field_70161_v, event.getTarget().field_70130_N, event.getTarget().field_70131_O, 3));
            }
        }
    }

    @SubscribeEvent
    public void itemPlayer(LivingEntityUseItemEvent.Start event) {
        if (!(event.getEntityLiving() instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer)event.getEntityLiving();
        if (player == null) {
            return;
        }
        if (player.func_70644_a(SPPotions.FEAR_E) && SPConfigSystems.fearActive) {
            if (player.func_70660_b(SPPotions.FEAR_E).func_76458_c() >= 1 && player.field_70170_p.field_73012_v.nextDouble() < (double)SPConfigSystems.fearItemChance && !ParasiteEventEntity.checkName(player.func_184582_a(EntityEquipmentSlot.MAINHAND).func_77973_b().getRegistryName().toString(), SPConfigSystems.fearItemBlackList, SPConfigSystems.fearItemBlackListWhite)) {
                player.func_146105_b(new TextComponentTranslation("message.subspaceparasite.fearitem", new Object[0]).func_150255_a(new Style().func_150238_a(TextFormatting.RED)), true);
                event.setDuration(-1);
                event.setCanceled(true);
                return;
            }
            if (event.getItem().func_77973_b() instanceof ItemBlock) {
                // empty if block
            }
        }
    }

    @SubscribeEvent
    public void blockPlayer(PlayerInteractEvent.RightClickBlock event) {
        EntityPlayer player = event.getEntityPlayer();
        if (player == null) {
            return;
        }
        if (player.func_70644_a(SPPotions.FEAR_E) && SPConfigSystems.fearActive) {
            if (player.func_70660_b(SPPotions.FEAR_E).func_76458_c() >= 2 && player.field_70170_p.field_73012_v.nextDouble() < (double)SPConfigSystems.fearBlockChance) {
                player.func_146105_b(new TextComponentTranslation("message.subspaceparasite.fearblock", new Object[0]).func_150255_a(new Style().func_150238_a(TextFormatting.RED)), true);
                event.setUseBlock(Event.Result.DENY);
            } else if (player.func_70660_b(SPPotions.FEAR_E).func_76458_c() >= 1 && player.field_70170_p.field_73012_v.nextDouble() < (double)SPConfigSystems.fearItemChance) {
                player.func_146105_b(new TextComponentTranslation("message.subspaceparasite.fearitem", new Object[0]).func_150255_a(new Style().func_150238_a(TextFormatting.RED)), true);
                event.setUseItem(Event.Result.DENY);
            }
        }
    }

    @SubscribeEvent
    public void entityPlayer(PlayerInteractEvent.EntityInteractSpecific event) {
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void guiPlayer(GuiOpenEvent event) {
        Minecraft mc = Minecraft.func_71410_x();
        EntityPlayerSP player = mc.field_71439_g;
        if (player == null || event.getGui() == null) {
            return;
        }
        if (player.func_184812_l_() || event.getGui().func_73868_f()) {
            return;
        }
        if (player.func_70644_a(SPPotions.FEAR_E) && SPConfigSystems.fearActive && !player.field_71075_bZ.field_75102_a && player.func_70660_b(SPPotions.FEAR_E).func_76458_c() >= 3 && player.field_70170_p.field_73012_v.nextDouble() < (double)SPConfigSystems.fearInvChance) {
            player.func_146105_b((ITextComponent)new TextComponentTranslation("message.subspaceparasite.feargui", new Object[0]), true);
            this.closeG = true;
        }
    }

    @SubscribeEvent
    public void mobFear(LivingDamageEvent event) {
        boolean trueAirborne;
        boolean inFluidOrSupport;
        EntityLivingBase in = event.getEntityLiving();
        if (in == null || !SPConfigSystems.fearActive) {
            return;
        }
        if (!in.func_70644_a(SPPotions.FEAR_E)) {
            return;
        }
        int amp = in.func_70660_b(SPPotions.FEAR_E).func_76458_c() + 1;
        if (event.getSource() == DamageSource.field_76379_h && SPConfigSystems.fearFallDamage != 0.0f) {
            event.setAmount(event.getAmount() * (SPConfigSystems.fearFallDamage * (float)amp));
        }
        if (SPConfigSystems.fearAirDamage == 0.0f) {
            return;
        }
        if (SPConfigSystems.fearUnfair) {
            if (!in.field_70122_E) {
                event.setAmount(event.getAmount() * (SPConfigSystems.fearAirDamage * (float)amp));
            }
            return;
        }
        boolean bl = inFluidOrSupport = in.func_70090_H() || in.func_180799_ab() || in.func_70617_f_() || in.func_184218_aH();
        if (inFluidOrSupport) {
            return;
        }
        boolean creativeFlying = false;
        boolean elytraFlying = false;
        if (in instanceof EntityPlayer) {
            EntityPlayer p = (EntityPlayer)in;
            creativeFlying = p.field_71075_bZ.field_75100_b;
            elytraFlying = p.func_184613_cA();
        }
        float MIN_FALL_DISTANCE = 2.0f;
        boolean descending = in.field_70181_x < 0.0;
        boolean bl2 = trueAirborne = creativeFlying || elytraFlying || !in.field_70122_E && descending && in.field_70143_R >= 2.0f;
        if (trueAirborne) {
            event.setAmount(event.getAmount() * (SPConfigSystems.fearAirDamage * (float)amp));
        }
    }

    @SubscribeEvent
    public void onEntitySpawn(EntityJoinWorldEvent event) {
        if (event.getEntity() == null) {
            return;
        }
        if (event.getEntity() instanceof EntityLivingBase && !event.getEntity().field_70170_p.field_72995_K) {
            String mobname;
            if (event.getEntity() instanceof EntityPlayer) {
                return;
            }
            try {
                mobname = EntityList.func_191301_a((Entity)event.getEntity()).toString();
            }
            catch (Exception e) {
                SPMain.logger.log(Level.ERROR, "Problem with spawning entity");
                return;
            }
            NBTTagCompound tags = event.getEntity().getEntityData();
            boolean parasite = event.getEntity() instanceof EntityParasiteBase;
            boolean flagNC = SPConfigWorld.nodesActivated || SPConfigWorld.coloniesActivated || SPConfigSystems.useEvolution;
            SPWorldData data = null;
            if (flagNC) {
                data = SPWorldData.get(event.getWorld());
            }
            if (SPConfigWorld.nodesActivated && SPConfigSystems.cothActive && data.nearestHeartAge(event.getEntity().func_180425_c(), false, 0) != -1) {
                ((EntityLivingBase)event.getEntity()).func_70690_d(new PotionEffect(SPPotions.COTH_E, 3600, 0, false, false));
            }
            if (!tags.func_74764_b("srpcothimmunity") && SPConfigSystems.cothActive && !parasite) {
                this.writeCOTHTag((EntityLivingBase)event.getEntity(), mobname, tags);
            }
            if (parasite) {
                this.setNewParasiteTask((EntityParasiteBase)event.getEntity(), mobname, flagNC, data);
            } else if (event.getEntity() instanceof EntityCreature) {
                this.setNewCreatureTask((EntityCreature)event.getEntity(), mobname);
            }
        }
    }

    private void writeCOTHTag(EntityLivingBase in, String mobname, NBTTagCompound tags) {
        if (in instanceof EntityArmorStand) {
            tags.func_74768_a("srpcothimmunity", 0);
        } else if (ParasiteEventEntity.checkName(mobname, SPConfigSystems.COTHImmuneList, SPConfigSystems.COTHImmuneListWhite)) {
            tags.func_74768_a("srpcothimmunity", 0);
        } else {
            tags.func_74768_a("srpcothimmunity", 1);
        }
        this.setCOTH(in, SPSaveData.get(in.field_70170_p, 84).getEvolutionPhase(in.field_70170_p.field_73011_w.getDimension()));
    }

    private void setNewParasiteTask(EntityParasiteBase entity, String mobname, boolean flagNC, SPWorldData data) {
        SPSaveData dataS = SPSaveData.get(entity.field_70170_p, 82);
        if (!entity.spawnedByColo) {
            EntityCanHaveBodies head;
            entity.spawnedByColo = true;
            switch (dataS.getChoice()) {
                case 2: {
                    this.changeAttribute(entity, SharedMonsterAttributes.field_111267_a, 3.0);
                    this.changeAttribute(entity, SharedMonsterAttributes.field_188791_g, 3.0);
                    this.changeAttribute(entity, SharedMonsterAttributes.field_111264_e, 3.0);
                    this.changeAttribute(entity, SharedMonsterAttributes.field_111266_c, 3.0);
                    break;
                }
                case 3: {
                    this.changeAttribute(entity, SharedMonsterAttributes.field_111267_a, 10.0);
                    this.changeAttribute(entity, SharedMonsterAttributes.field_188791_g, 10.0);
                    this.changeAttribute(entity, SharedMonsterAttributes.field_111264_e, 10.0);
                    this.changeAttribute(entity, SharedMonsterAttributes.field_111266_c, 10.0);
                }
            }
            entity.applyBonuses(dataS, entity.field_70170_p);
            this.applyDislo(entity, dataS, entity.field_70170_p.field_73011_w.getDimension());
            this.applyColony(entity, data.totalColonyPoints(0));
            this.applyNode(entity, data.totalNodePoints(0));
            if (entity instanceof EntityPMalleable) {
                EntityPMalleable uwu = (EntityPMalleable)entity;
                String damage = data.getMostCommonDamageS();
                if (damage != null) {
                    for (int times = data.getMostCommonDamageI(); times > 0; --times) {
                        uwu.addResistance(damage);
                    }
                    uwu.increaseDamageCap(1);
                    uwu.colonySpawned = true;
                }
            }
            if (entity instanceof EntityCanHaveBodies && (head = (EntityCanHaveBodies)((Object)entity)).getCanF()) {
                int len = head.getBodyLength();
                EntityCanHaveBodies current = head;
                for (int i = 0; i < len; ++i) {
                    EntityCanHaveBodies entityWithBodies = head.getAnotherBody(entity.field_70170_p);
                    entityWithBodies.setCanF(false);
                    entityWithBodies.setFollowing(current);
                    entityWithBodies.copyCopy(current);
                    entityWithBodies.onSpawn(entity.field_70170_p.func_175649_E(new BlockPos((Entity)entity)), null);
                    entity.field_70170_p.func_72838_d(entityWithBodies.getEntity());
                    entityWithBodies.setBodyNumber(i + 1);
                    if (len - 1 == i) {
                        entityWithBodies.setBodyTail(true);
                    }
                    current = entityWithBodies;
                }
            }
        }
        if (SPConfig.parasiteGriefing.length != 0) {
            String[] task = new String[4];
            for (int i = 0; i < SPConfig.parasiteGriefing.length; ++i) {
                if (SPConfig.parasiteGriefing[i] == null || !(task = SPConfig.parasiteGriefing[i].split(";"))[0].equals(mobname)) continue;
                if (entity instanceof EntityPStationary) {
                    entity.setSkillBreakBlocksValues(Float.parseFloat(task[1]), MathHelper.func_76123_f((float)entity.field_70131_O), Integer.parseInt(task[3]));
                    break;
                }
                entity.setSkillBreakBlocksValues(Float.parseFloat(task[1]), MathHelper.func_76123_f((float)entity.field_70131_O), Integer.parseInt(task[3]));
                entity.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAISkill(entity, Integer.parseInt(task[2]), 64, false, 13));
                break;
            }
        }
        if (entity instanceof EntityPInfected && SPConfigSystems.generationUse) {
            entity.func_70606_j(entity.func_110143_aJ() * this.getSimCOTHMod(SPSaveData.get(entity.field_70170_p, 83), entity.field_70170_p));
        }
    }

    private void applyDislo(EntityParasiteBase in, SPSaveData dataTwo, int id) {
        int[] killa = dataTwo.getDisloValues(id);
        if (SPConfigSystems.disloSummonByDeath && !in.disloNumberTwo && killa[2] > 0) {
            in.disloNumberTwo = true;
        }
        if (SPConfigSystems.disloPotiEff && !in.disloNumberThree && killa[3] > 0) {
            in.disloNumberThree = true;
            int amp = dataTwo.getCurrentCode(id, 3);
            String here = SPConfigSystems.disloPotiEffEffects[in.field_70170_p.field_73012_v.nextInt(SPConfigSystems.disloPotiEffEffects.length)];
            Potion potion = Potion.func_180142_b((String)here);
            if (potion != null) {
                in.func_70690_d(new PotionEffect(potion, dataTwo.getCurrentCodeDuration(id, 3) * 20 + 50, amp));
            }
        }
        if (SPConfigSystems.dislostats && !in.disloNumberFour && killa[4] > 0) {
            in.disloNumberFour = true;
            int multip = dataTwo.getCurrentCode(id, 4);
            in.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(in.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() * (double)(1 + multip));
            in.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(in.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111125_b() * (double)(1 + multip));
            in.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(in.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111125_b() * (double)(1 + multip));
        }
        if (SPConfigSystems.disloItemDura && !in.disloNumberSix && killa[6] > 0) {
            in.disloNumberSix = true;
        }
        if (SPConfigSystems.disloHealingDeath && !in.disloNumberSeven && killa[7] > 0) {
            in.disloNumberSeven = true;
        }
        if (SPConfigSystems.disloDamageDeath && !in.disloNumberEight && killa[8] > 0) {
            in.disloNumberEight = true;
        }
        if (SPConfigSystems.disloFoodDeath && !in.disloNumberNine && killa[9] > 0) {
            in.disloNumberNine = true;
        }
        if (SPConfigSystems.disloParasiteNoPotion && in.disloNumberEleven <= 0 && killa[11] > 0) {
            in.disloNumberEleven = dataTwo.getCurrentCodeDuration(id, 11) * 20 + 50;
        }
        if (SPConfigSystems.disloGrowlNoise && in.disloNumberFifteen <= 0 && killa[15] > 0) {
            in.disloNumberFifteen = dataTwo.getCurrentCodeDuration(id, 15) * 20 + 50;
            in.func_184212_Q().func_187227_b(EntityParasiteBase.DISLO15, (Object)true);
        }
        if (SPConfigSystems.disloWalkNoise && in.disloNumberSixteen <= 0 && killa[16] > 0) {
            in.disloNumberSixteen = dataTwo.getCurrentCodeDuration(id, 16) * 20 + 50;
        }
        if (SPConfigSystems.disloShieldFood && in.disloNumberSeventeen <= 0 && killa[17] > 0) {
            in.disloNumberSeventeen = dataTwo.getCurrentCodeDuration(id, 17) * 20 + 50;
        }
        if (SPConfigSystems.disloLootXpCanc && !in.disloNumberEighteen && killa[18] > 0) {
            in.disloNumberEighteen = true;
        }
        if (SPConfigSystems.disloKillcountInc && in.disloNumberNineteen <= 0 && killa[19] > 0) {
            in.disloNumberNineteen = dataTwo.getCurrentCodeDuration(id, 19) * 20 + 50;
            in.disloNumberNineteenValue = killa[19];
        }
        if (SPConfigSystems.disloGiveBodies && killa[20] > 0) {
            if (in instanceof EntityInhooM) {
                ((EntityInhooM)in).disloNumberTwenty = true;
            }
            if (in instanceof EntityInhooS) {
                ((EntityInhooS)in).disloNumberTwenty = true;
            }
        }
        if (SPConfigSystems.disloBurningDeath && !in.disloNumberTwentyone && killa[21] > 0) {
            in.disloNumberTwentyone = true;
        }
        if (SPConfigSystems.disloSameVersionDyeing && !in.disloNumberTwentytwo && killa[22] > 0) {
            in.disloNumberTwentytwo = true;
        }
    }

    private void applyColony(EntityParasiteBase in, int totalColonyPoints) {
        if (totalColonyPoints == 0) {
            return;
        }
        double bonus = (float)totalColonyPoints / SPConfigWorld.colonyExtraHealthPoint * SPConfigWorld.colonyExtraHealthValue;
        this.changeAttribute(in, SharedMonsterAttributes.field_111267_a, bonus);
        in.func_70606_j((float)in.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b());
        bonus = (float)totalColonyPoints / SPConfigWorld.colonyExtraArmorPoint * SPConfigWorld.colonyExtraArmorValue;
        this.changeAttribute(in, SharedMonsterAttributes.field_188791_g, bonus);
        bonus = (float)totalColonyPoints / SPConfigWorld.colonyExtraDamagePoint * SPConfigWorld.colonyExtraDamageValue;
        this.changeAttribute(in, SharedMonsterAttributes.field_111264_e, bonus);
        bonus = (float)totalColonyPoints / SPConfigWorld.colonyExtraKDResPoint * SPConfigWorld.colonyExtraKDResValue;
        this.changeAttribute(in, SharedMonsterAttributes.field_111266_c, bonus);
        bonus = (float)totalColonyPoints / SPConfigWorld.colonyDamageCapPoint * SPConfigWorld.colonyDamageCapValue;
        in.damageCap = (int)((double)in.damageCap + (double)in.damageCap * bonus);
    }

    private void changeAttribute(EntityParasiteBase in, IAttribute stat, double bonus) {
        double base = in.func_110148_a(stat).func_111125_b();
        in.func_110148_a(stat).func_111128_a(base + base * bonus);
    }

    private void applyNode(EntityParasiteBase in, int totalPoints) {
        if (totalPoints == 0) {
            return;
        }
        String[] here = new String[3];
        for (String i : SPConfigWorld.potionEffectForNodes) {
            here = i.split(";");
            int level = Integer.parseInt(here[0]);
            if (totalPoints < level) continue;
            int amp = Integer.parseInt(here[2]);
            Potion potion = Potion.func_180142_b((String)here[1]);
            if (potion == null) continue;
            in.func_70690_d(new PotionEffect(potion, 7777, amp));
        }
    }

    private float getSimCOTHMod(SPSaveData data, World world) {
        switch (data.getGeneration(world.field_73011_w.getDimension())) {
            case 0: {
                return SPConfigSystems.generationCOTH0;
            }
            case 1: {
                return SPConfigSystems.generationCOTH1;
            }
            case 2: {
                return SPConfigSystems.generationCOTH2;
            }
            case 3: {
                return SPConfigSystems.generationCOTH3;
            }
            case 4: {
                return SPConfigSystems.generationCOTH4;
            }
            case 5: {
                return SPConfigSystems.generationCOTH5;
            }
        }
        return 1.0f;
    }

    @SubscribeEvent
    public void onEntityMount(EntityMountEvent event) {
        if (event.getEntityBeingMounted() == null || event.getEntityMounting() == null) {
            return;
        }
        if (!event.getEntityBeingMounted().func_70089_S()) {
            return;
        }
        if (event.getEntityMounting() instanceof EntityPlayer && event.getEntityBeingMounted() instanceof EntityHiGolem) {
            EntityPlayer player = (EntityPlayer)event.getEntityMounting();
            if (!player.field_71075_bZ.field_75102_a && player.func_110143_aJ() > 0.0f && event.isDismounting()) {
                event.setCanceled(true);
            }
        }
    }

    private void setNewCreatureTask(EntityCreature entity, String mobname) {
        if (ParasiteEventEntity.checkName(mobname, SPConfig.entitiesWillAttack, SPConfig.entitiesWillAttackWhite)) {
            entity.field_70715_bh.func_75776_a(5, (EntityAIBase)new EntityAINearestAttackableTarget(entity, EntityParasiteBase.class, true));
            return;
        }
        if (ParasiteEventEntity.checkName(mobname, SPConfig.entitiesWillAvoid, SPConfig.entitiesWillAvoidWhite)) {
            entity.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIAvoidEntity(entity, EntityParasiteBase.class, 12.0f, 0.8, 0.8));
        }
    }

    private void setCOTH(EntityLivingBase target, byte evo) {
        if (target.func_70631_g_()) {
            return;
        }
        Random rand = new Random();
        switch (evo) {
            case 1: {
                if (!(rand.nextDouble() < SPConfigSystems.mobSpawningCOTHChanceOne)) break;
                target.func_70690_d(new PotionEffect(SPPotions.COTH_E, 3600, 0, false, false));
                break;
            }
            case 2: {
                if (!(rand.nextDouble() < SPConfigSystems.mobSpawningCOTHChanceTwo)) break;
                target.func_70690_d(new PotionEffect(SPPotions.COTH_E, 3600, 0, false, false));
                break;
            }
            case 3: {
                if (!(rand.nextDouble() < SPConfigSystems.mobSpawningCOTHChanceThree)) break;
                target.func_70690_d(new PotionEffect(SPPotions.COTH_E, 3600, 0, false, false));
                break;
            }
            case 4: {
                if (!(rand.nextDouble() < SPConfigSystems.mobSpawningCOTHChanceFour)) break;
                target.func_70690_d(new PotionEffect(SPPotions.COTH_E, 3600, 0, false, false));
                break;
            }
            case 5: {
                if (!(rand.nextDouble() < SPConfigSystems.mobSpawningCOTHChanceFive)) break;
                target.func_70690_d(new PotionEffect(SPPotions.COTH_E, 3600, 0, false, false));
                break;
            }
            case 6: {
                if (!(rand.nextDouble() < SPConfigSystems.mobSpawningCOTHChanceSix)) break;
                target.func_70690_d(new PotionEffect(SPPotions.COTH_E, 3600, 0, false, false));
                break;
            }
            case 7: {
                if (!(rand.nextDouble() < SPConfigSystems.mobSpawningCOTHChanceSeven)) break;
                target.func_70690_d(new PotionEffect(SPPotions.COTH_E, 3600, 0, false, false));
                break;
            }
            case 8: {
                if (!(rand.nextDouble() < SPConfigSystems.mobSpawningCOTHChanceEight)) break;
                target.func_70690_d(new PotionEffect(SPPotions.COTH_E, 3600, 0, false, false));
            }
        }
    }

    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void serverTick(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            return;
        }
        if (BeckonBlockInfestation.blockInfestedCount > SPConfig.BlockInfestedLimit) {
            ++this.blockInfestedCountCooldown;
            if (this.blockInfestedCountCooldown > SPConfig.BlockInfestedLimitCD) {
                this.blockInfestedCountCooldown = 0;
                BeckonBlockInfestation.blockInfestedCount = 0;
            }
        }
        if (BlockParasiteSpreading.blockParasiteCount > SPConfig.BlockParasiteLimit) {
            ++this.blockParasiteCountCooldown;
            if (this.blockParasiteCountCooldown > SPConfig.BlockParasiteLimitCD) {
                this.blockParasiteCountCooldown = 0;
                BlockParasiteSpreading.blockParasiteCount = 0;
            }
        }
        ++this.counerW;
        if (SPConfigWorld.originWorldCheckDebugSpeed > 0) {
            this.counerW += SPConfigWorld.originWorldCheckDebugSpeed;
        }
        --ParasiteEventWorld.disloCool;
        if (this.counerW > SPConfig.dayTickValue && (SPConfigWorld.nodesActivated || SPConfigWorld.coloniesActivated || SPConfigSystems.useEvolution || SPConfigWorld.originActivated)) {
            MinecraftServer ser = FMLCommonHandler.instance().getMinecraftServerInstance();
            if (ser.field_71305_c.length > 0) {
                SPSaveData dat = SPSaveData.get((World)ser.field_71305_c[0], 89);
                dat.addUpdateNumber(1);
            }
            this.worldsChecked = new ArrayList();
            this.counerW = -150;
        }
    }

    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void worldTick(TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            return;
        }
        if (!event.world.field_72995_K) {
            ++this.moo;
            if (SPConfigSystems.useEvolution && SPConfigSystems.phaseCustomSpawner) {
                if (event.world.func_73046_m() == null) {
                    return;
                }
                this.tickSpawn(event.world.func_73046_m().func_71218_a(event.world.field_73011_w.getDimension()));
            }
            if (this.moo >= 20 * SPConfigSystems.disloSeconds) {
                this.moo = 0;
                SPSaveData.get(event.world, 88).reduceCodesCooldown(event.world.field_73011_w.getDimension(), SPConfigSystems.disloSeconds, event.world);
            }
            ++this.meteor;
            if (this.meteor > SPConfigWorld.meteorTick) {
                SPWorldData data;
                this.meteor = 0;
                if (event.world.field_73012_v.nextDouble() < SPConfigWorld.meteorChance && SPConfig.spawnDays <= (int)event.world.func_82737_E() && (data = SPWorldData.get(event.world)).getTriggerMet() && SPSaveData.get(event.world, -549).getEvolutionPhase(event.world.field_73011_w.getDimension()) >= 0) {
                    if (SPConfigWorld.meteorVectorless) {
                        if (data.getorigins("x").isEmpty()) {
                            this.spawningMet(event.world);
                        }
                    } else {
                        this.spawningMet(event.world);
                    }
                }
            }
            if (this.counerW < 0 && (SPConfigWorld.nodesActivated || SPConfigWorld.coloniesActivated || SPConfigSystems.useEvolution || SPConfigWorld.originActivated)) {
                int id = event.world.field_73011_w.getDimension();
                for (int i : this.worldsChecked) {
                    if (i != id) continue;
                    return;
                }
                ParasiteEventWorld.checkColonyStatus(event.world);
                ParasiteEventWorld.checkNodeStatus(event.world);
                SPMain.logger.info("[EIV DEBUG] worldTick is calling createRandomOrigin. dim={} time={} counerW={} worldsChecked={} originActivated={} min={} max={}", (Object)event.world.field_73011_w.getDimension(), (Object)event.world.func_82737_E(), (Object)this.counerW, this.worldsChecked, (Object)SPConfigWorld.originActivated, (Object)SPConfigWorld.originCreatingDistanceMin, (Object)SPConfigWorld.originCreatingDistanceMax);
                EIVUtil.createRandomOrigin(event.world, SPConfigWorld.originCreatingDistanceMin, SPConfigWorld.originCreatingDistanceMax);
                this.worldsChecked.add(id);
            }
        }
    }

    private void spawningMet(World world) {
        block2: {
            EntityPlayer mob;
            ArrayList mobs = Lists.newArrayList();
            mobs.addAll(world.field_73010_i);
            boolean flag = true;
            if (mobs.size() == 0) break block2;
            Iterator iterator = mobs.iterator();
            while (iterator.hasNext()) {
                mob = (EntityPlayer)iterator.next();
                if (!world.func_175710_j(mob.func_180425_c())) continue;
                ParasiteSummon.spawnMeteor(mob.func_180425_c(), world.field_73012_v.nextInt(SPConfigWorld.meteorRadius), SPConfigWorld.meteorMinRadius, world);
                flag = false;
                break;
            }
            if (flag && (iterator = mobs.iterator()).hasNext()) {
                mob = (EntityPlayer)iterator.next();
                ParasiteSummon.spawnMeteor(mob.func_180425_c(), world.field_73012_v.nextInt(SPConfigWorld.meteorRadius), SPConfigWorld.meteorMinRadius, world);
            }
        }
    }

    private void tickSpawn(WorldServer server) {
        if (server.func_82736_K().func_82766_b("doMobSpawning") && server.func_72912_H().func_76067_t() != WorldType.field_180272_g) {
            SPWorldParasiteSpawner.findChunksForSpawning(server, true, false, server.func_72912_H().func_82573_f() % 400L == 0L);
        }
    }

    @SubscribeEvent
    public void setLoot(LivingDropsEvent event) {
        if (event.getEntityLiving() instanceof EntityParasiteBase) {
            if (!event.getEntityLiving().field_70170_p.func_82736_K().func_82766_b("doMobLoot")) {
                return;
            }
            if (event.getEntityLiving().func_70644_a(SPPotions.DEBAR_E)) {
                return;
            }
            EntityParasiteBase mob = (EntityParasiteBase)event.getEntityLiving();
            if (mob.disloNumberEighteen && SPSaveData.get(event.getEntityLiving().field_70170_p, 87).getCurrentCode(event.getEntityLiving().field_70170_p.field_73011_w.getDimension(), 18) > 0) {
                return;
            }
            switch (mob.getParasiteIDRegister()) {
                case 1: {
                    this.loot(event, SPConfigMobs.shycoLoot);
                    return;
                }
                case 51: {
                    this.loot(event, SPConfigMobs.shycoadaptedloot);
                    return;
                }
                case 10: {
                    this.loot(event, SPConfigMobs.noglaLoot);
                    return;
                }
                case 54: {
                    this.loot(event, SPConfigMobs.noglaadaptedloot);
                    return;
                }
                case 4: {
                    this.loot(event, SPConfigMobs.emanaLoot);
                    return;
                }
                case 55: {
                    this.loot(event, SPConfigMobs.emanaadaptedloot);
                    return;
                }
                case 7: {
                    this.loot(event, SPConfigMobs.hullLoot);
                    return;
                }
                case 52: {
                    this.loot(event, SPConfigMobs.hulladaptedloot);
                    return;
                }
                case 8: {
                    this.loot(event, SPConfigMobs.canraLoot);
                    return;
                }
                case 53: {
                    this.loot(event, SPConfigMobs.canraadaptedloot);
                    return;
                }
                case 38: {
                    this.loot(event, SPConfigMobs.arachnidaLoot);
                    return;
                }
                case 58: {
                    this.loot(event, SPConfigMobs.arachnidaadaptedloot);
                    return;
                }
                case 37: {
                    this.loot(event, SPConfigMobs.shycoLoot);
                    return;
                }
                case 57: {
                    this.loot(event, SPConfigMobs.shycoLoot);
                    return;
                }
                case 66: {
                    this.loot(event, SPConfigMobs.lumLoot);
                    return;
                }
                case 81: {
                    this.loot(event, SPConfigMobs.lumadaptedloot);
                    return;
                }
                case 17: {
                    this.loot(event, SPConfigMobs.zetmoLoot);
                    return;
                }
                case 56: {
                    this.loot(event, SPConfigMobs.zetmoadaptedloot);
                    return;
                }
                case 2: {
                    this.loot(event, SPConfigMobs.dorpaLoot);
                    return;
                }
                case 49: {
                    this.loot(event, SPConfigMobs.infbearLoot);
                    return;
                }
                case 13: {
                    this.loot(event, SPConfigMobs.infcowLoot);
                    return;
                }
                case 28: {
                    this.loot(event, SPConfigMobs.infcowheadLoot);
                    return;
                }
                case 64: {
                    this.loot(event, SPConfigMobs.infdragoneLoot);
                    return;
                }
                case 70: {
                    this.loot(event, SPConfigMobs.infdragoneheadLoot);
                    return;
                }
                case 59: {
                    this.loot(event, SPConfigMobs.infendermanLoot);
                    return;
                }
                case 69: {
                    this.loot(event, SPConfigMobs.infendermanheadLoot);
                    return;
                }
                case 44: {
                    this.loot(event, SPConfigMobs.infhorseLoot);
                    return;
                }
                case 45: {
                    this.loot(event, SPConfigMobs.infhorseheadLoot);
                    return;
                }
                case 6: {
                    this.loot(event, SPConfigMobs.infhumanLoot);
                    return;
                }
                case 46: {
                    this.loot(event, SPConfigMobs.infhumanheadLoot);
                    return;
                }
                case 26: {
                    this.loot(event, SPConfigMobs.infpigLoot);
                    return;
                }
                case 31: {
                    this.loot(event, SPConfigMobs.infpigheadLoot);
                    return;
                }
                case 40: {
                    this.loot(event, SPConfigMobs.infadventurerLoot);
                    return;
                }
                case 71: {
                    this.loot(event, SPConfigMobs.infadventurerheadLoot);
                    return;
                }
                case 14: {
                    this.loot(event, SPConfigMobs.infsheepLoot);
                    return;
                }
                case 22: {
                    this.loot(event, SPConfigMobs.infsheepheadLoot);
                    return;
                }
                case 27: {
                    this.loot(event, SPConfigMobs.infvillagerLoot);
                    return;
                }
                case 32: {
                    this.loot(event, SPConfigMobs.infvillagerheadLoot);
                    return;
                }
                case 15: {
                    this.loot(event, SPConfigMobs.infwolfLoot);
                    return;
                }
                case 21: {
                    this.loot(event, SPConfigMobs.infwolfheadLoot);
                    return;
                }
                case 306: {
                    this.loot(event, SPConfigMobs.ferbearLoot);
                    return;
                }
                case 93: {
                    this.loot(event, SPConfigMobs.fercowLoot);
                    return;
                }
                case 94: {
                    this.loot(event, SPConfigMobs.ferendermanLoot);
                    return;
                }
                case 95: {
                    this.loot(event, SPConfigMobs.ferhorseLoot);
                    return;
                }
                case 96: {
                    this.loot(event, SPConfigMobs.ferhumanLoot);
                    return;
                }
                case 97: {
                    this.loot(event, SPConfigMobs.ferpigLoot);
                    return;
                }
                case 98: {
                    this.loot(event, SPConfigMobs.fersheepLoot);
                    return;
                }
                case 99: {
                    this.loot(event, SPConfigMobs.fervillagerLoot);
                    return;
                }
                case 300: {
                    this.loot(event, SPConfigMobs.ferwolfLoot);
                    return;
                }
                case 324: {
                    this.loot(event, SPConfigMobs.marhumanLoot);
                    return;
                }
                case 330: {
                    this.loot(event, SPConfigMobs.marbearLoot);
                    return;
                }
                case 322: {
                    this.loot(event, SPConfigMobs.marcowLoot);
                    return;
                }
                case 329: {
                    this.loot(event, SPConfigMobs.marsheepLoot);
                    return;
                }
                case 321: {
                    this.loot(event, SPConfigMobs.marendermanLoot);
                    return;
                }
                case 323: {
                    this.loot(event, SPConfigMobs.marvillagerLoot);
                    return;
                }
                case 302: {
                    this.loot(event, SPConfigMobs.hiblazeLoot);
                    return;
                }
                case 301: {
                    this.loot(event, SPConfigMobs.higolemLoot);
                    return;
                }
                case 303: {
                    this.loot(event, SPConfigMobs.hiskeletonLoot);
                    return;
                }
                case 9: {
                    this.loot(event, SPConfigMobs.alafhaLoot);
                    return;
                }
                case 25: {
                    this.loot(event, SPConfigMobs.angedLoot);
                    return;
                }
                case 50: {
                    this.loot(event, SPConfigMobs.esorLoot);
                    return;
                }
                case 60: {
                    this.loot(event, SPConfigMobs.flogLoot);
                    return;
                }
                case 33: {
                    this.loot(event, SPConfigMobs.ganroLoot);
                    return;
                }
                case 47: {
                    this.loot(event, SPConfigMobs.ombooLoot);
                    return;
                }
                case 82: {
                    this.loot(event, SPConfigMobs.ombooLoot);
                    return;
                }
                case 65: {
                    this.loot(event, SPConfigMobs.jinjoLoot);
                    return;
                }
                case 85: {
                    this.loot(event, SPConfigMobs.elviaLoot);
                    return;
                }
                case 86: {
                    this.loot(event, SPConfigMobs.lenciaLoot);
                    return;
                }
                case 88: {
                    this.loot(event, SPConfigMobs.vestaLoot);
                    return;
                }
                case 87: {
                    this.loot(event, SPConfigMobs.pheonLoot);
                    return;
                }
                case 11: {
                    this.loot(event, SPConfigMobs.butholLoot);
                    return;
                }
                case 36: {
                    this.loot(event, SPConfigMobs.kolLoot);
                    return;
                }
                case 23: {
                    this.loot(event, SPConfigMobs.kolLoot);
                    return;
                }
                case 5: {
                    this.loot(event, SPConfigMobs.LodoLoot);
                    return;
                }
                case 12: {
                    this.loot(event, SPConfigMobs.mudoLoot);
                    return;
                }
                case 76: {
                    this.loot(event, SPConfigMobs.nuuhLoot);
                    return;
                }
                case 3: {
                    this.loot(event, SPConfigMobs.ratholLoot);
                    return;
                }
                case 74: {
                    this.loot(event, SPConfigMobs.ratholLoot);
                    return;
                }
                case 72: {
                    this.loot(event, SPConfigMobs.nakLoot);
                    return;
                }
                case 29: {
                    this.loot(event, SPConfigMobs.tonroLoot);
                    return;
                }
                case 30: {
                    this.loot(event, SPConfigMobs.unvoLoot);
                    return;
                }
                case 16: {
                    this.loot(event, SPConfigMobs.venkrolLoot);
                    return;
                }
                case 18: {
                    this.loot(event, SPConfigMobs.venkrolsiiLoot);
                    return;
                }
                case 19: {
                    this.loot(event, SPConfigMobs.venkrolsiiiLoot);
                    return;
                }
                case 41: {
                    this.loot(event, SPConfigMobs.venkrolsivLoot);
                    return;
                }
                case 73: {
                    this.loot(event, SPConfigMobs.dodsiLoot);
                    return;
                }
                case 77: {
                    this.loot(event, SPConfigMobs.dodsiiLoot);
                    return;
                }
                case 78: {
                    this.loot(event, SPConfigMobs.dodsiiiLoot);
                    return;
                }
                case 79: {
                    this.loot(event, SPConfigMobs.dodsivLoot);
                    return;
                }
                case 62: {
                    this.loot(event, SPConfigMobs.cruxaLoot);
                    return;
                }
                case 63: {
                    this.loot(event, SPConfigMobs.heedLoot);
                    return;
                }
                case 48: {
                    this.loot(event, SPConfigMobs.hostLoot);
                    return;
                }
                case 75: {
                    this.loot(event, SPConfigMobs.herdLoot);
                    return;
                }
                case 39: {
                    this.loot(event, SPConfigMobs.inhooSLoot);
                    return;
                }
                case 43: {
                    this.loot(event, SPConfigMobs.inhooMLoot);
                    return;
                }
                case 80: {
                    this.loot(event, SPConfigMobs.thrallLoot);
                    return;
                }
                case 24: {
                    this.loot(event, SPConfigMobs.oroncoLoot);
                    return;
                }
                case 20: {
                    this.loot(event, SPConfigMobs.terlaLoot);
                    return;
                }
                case 309: {
                    this.loot(event, SPConfigMobs.hebluLoot);
                    return;
                }
                case 67: {
                    this.loot(event, SPConfigMobs.kirinLoot);
                    return;
                }
                case 34: {
                    this.loot(event, SPConfigMobs.pod1Loot);
                }
            }
        } else if (event.getEntity() instanceof EntityLivingBase && !(event.getEntity() instanceof EntityPlayer) && SPConfigSystems.cothActive) {
            if (event.getSource().func_76346_g() instanceof EntityParasiteBase && SPConfig.mobsKilledDropLoot) {
                event.setCanceled(true);
                return;
            }
            if (((EntityLivingBase)event.getEntity()).func_70644_a(SPPotions.COTH_E)) {
                int key;
                NBTTagCompound tags;
                if (SPConfigSystems.useEvolution) {
                    int key2;
                    NBTTagCompound tags2;
                    if (SPSaveData.get(event.getEntity().field_70170_p, 86).getEvolutionPhase(event.getEntity().field_70170_p.field_73011_w.getDimension()) >= SPConfigSystems.evolutionCothStopLoot && (tags2 = event.getEntity().getEntityData()).func_74764_b("srpcothimmunity") && (key2 = tags2.func_74762_e("srpcothimmunity")) != 0) {
                        event.setCanceled(true);
                    }
                } else if (SPConfigSystems.cothLootDisable && (tags = event.getEntity().getEntityData()).func_74764_b("srpcothimmunity") && (key = tags.func_74762_e("srpcothimmunity")) != 0) {
                    event.setCanceled(true);
                }
            }
        }
    }

    private void loot(LivingDropsEvent event, String[] drop) {
        block8: {
            try {
                int realquantity;
                int rng;
                int chance;
                int quantity;
                if (drop.length == 0) break block8;
                String[] dropping = new String[4];
                String[] dropped = new String[drop.length];
                Random rand = new Random();
                int totalFalse = 0;
                for (String s : drop) {
                    dropping = s.split(";");
                    boolean always = Boolean.parseBoolean(dropping[3]);
                    quantity = Integer.parseInt(dropping[2]);
                    chance = Integer.parseInt(dropping[1]);
                    if (always) {
                        rng = rand.nextInt(100);
                        if (rng > chance - 1) continue;
                        Item item = Item.func_111206_d((String)dropping[0]);
                        realquantity = rand.nextInt(quantity);
                        for (int j = 0; j <= realquantity && item != null; ++j) {
                            BlockPos pos = event.getEntity().func_180425_c();
                            event.getDrops().add(new EntityItem(event.getEntity().func_130014_f_(), (double)pos.func_177958_n(), (double)pos.func_177956_o(), (double)pos.func_177952_p(), new ItemStack(item)));
                        }
                        continue;
                    }
                    dropped[totalFalse] = s;
                    ++totalFalse;
                }
                if (totalFalse != 0) {
                    int n = rand.nextInt(totalFalse);
                    String[] stringItem = dropped[n].split(";");
                    quantity = Integer.parseInt(stringItem[2]);
                    chance = Integer.parseInt(stringItem[1]);
                    rng = rand.nextInt(100);
                    if (rng <= chance - 1) {
                        Item item = Item.func_111206_d((String)stringItem[0]);
                        realquantity = rand.nextInt(quantity);
                        for (int j = 0; j <= realquantity && item != null; ++j) {
                            BlockPos pos = event.getEntity().func_180425_c();
                            event.getDrops().add(new EntityItem(event.getEntity().func_130014_f_(), (double)pos.func_177958_n(), (double)pos.func_177956_o(), (double)pos.func_177952_p(), new ItemStack(item)));
                        }
                    }
                }
            }
            catch (Exception e) {
                SPMain.logger.log(Level.ERROR, "Problem with loot event", (Throwable)e);
            }
        }
    }

    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void playerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            EntityPlayer thePlayer = event.player;
            if (thePlayer.field_70170_p.field_72995_K) {
                if (this.closeG) {
                    if (thePlayer.field_71070_bA != null) {
                        thePlayer.func_71053_j();
                    }
                    this.closeG = false;
                }
            } else {
                SPWorldData data;
                int age;
                boolean isParaBiome = thePlayer.field_70170_p.func_180494_b(thePlayer.func_180425_c()) instanceof BiomeParasiteBase;
                if (ParasiteEventWorld.canBiomeStillExist(thePlayer.field_70170_p, thePlayer.func_180425_c(), false) >= 1 || isParaBiome) {
                    BiomeParasiteBase biomeChecked = isParaBiome ? (BiomeParasiteBase)thePlayer.field_70170_p.func_180494_b(thePlayer.func_180425_c()) : SPReference.getBiomeFromInt(ParasiteEventWorld.canBiomeStillExistType(thePlayer.field_70170_p, thePlayer.func_180425_c(), false));
                    fog = Math.min(fog + 4.5E-4f, SPConfigWorld.biomeFogDensity);
                    fogRed = biomeChecked.getRedValue();
                    fogGreen = biomeChecked.getGreenValue();
                    fogBlue = biomeChecked.getBlueValue();
                    if (fog < SPConfigWorld.biomeFogDensity) {
                        SPMain.network.sendTo((IMessage)new SPPacketFog(fog, fogRed, fogGreen, fogBlue), (EntityPlayerMP)thePlayer);
                    }
                } else if (fog > 0.0f) {
                    fog = Math.max(fog - 8.0E-4f, 0.0f);
                    fogRed = 0.0f;
                    fogGreen = 0.0f;
                    fogBlue = 0.0f;
                    SPMain.network.sendTo((IMessage)new SPPacketFog(fog, fogRed, fogGreen, fogBlue), (EntityPlayerMP)thePlayer);
                }
                ++this.heart;
                if (this.heart < SPConfigWorld.biomeHeartFreq) {
                    return;
                }
                this.heart = 0;
                if (isParaBiome && (age = (data = SPWorldData.get(thePlayer.field_70170_p)).nearestHeartAge(thePlayer.func_180425_c(), true, 0)) > 0) {
                    int totalS = data.getDistanceSpreadByAge(age, true);
                    float vol = ((float)data.isInRangeOfHeart(thePlayer.func_180425_c(), totalS) / (float)totalS - 1.0f) * -1.0f;
                    SPMain.network.sendTo((IMessage)new SPPacketMovingSound(-1, vol), (EntityPlayerMP)thePlayer);
                }
            }
        }
    }

    @SubscribeEvent
    public void playerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)event.getEntity();
            if (SPConfigSystems.cothActive && SPConfigMobs.infadventurerEnabled && SPConfigMobs.infadventurerSpawnBy && player.func_70644_a(SPPotions.COTH_E)) {
                int amp = player.func_70660_b(SPPotions.COTH_E).func_76458_c();
                if (amp >= 2) {
                    EntityInfPlayer out = new EntityInfPlayer(player.field_70170_p);
                    ItemStack head = new ItemStack(player.func_184582_a(EntityEquipmentSlot.HEAD).func_77973_b());
                    ItemStack legs = new ItemStack(player.func_184582_a(EntityEquipmentSlot.LEGS).func_77973_b());
                    ItemStack feet = new ItemStack(player.func_184582_a(EntityEquipmentSlot.FEET).func_77973_b());
                    if (head.func_77973_b() != Items.field_190931_a) {
                        out.func_184201_a(EntityEquipmentSlot.HEAD, head);
                        out.setHelmetSlot(true);
                    }
                    out.func_184201_a(EntityEquipmentSlot.LEGS, legs);
                    out.func_184201_a(EntityEquipmentSlot.FEET, feet);
                    out.func_82149_j((Entity)player);
                    out.func_180482_a(player.field_70170_p.func_175649_E(new BlockPos((Entity)out)), null);
                    player.field_70170_p.func_72838_d((Entity)out);
                    player.field_70170_p.func_180498_a((EntityPlayer)null, 1026, new BlockPos((Entity)out), 0);
                    out.func_96094_a(player.func_70005_c_());
                    out.func_174805_g(true);
                    out.particleStatus((byte)7);
                    out.cannotDespawn(false);
                } else if (amp == 1) {
                    EntityInhooM out = new EntityInhooM(player.field_70170_p);
                    out.func_82149_j((Entity)player);
                    out.func_180482_a(player.field_70170_p.func_175649_E(new BlockPos((Entity)out)), null);
                    player.field_70170_p.func_72838_d((Entity)out);
                    player.field_70170_p.func_180498_a((EntityPlayer)null, 1026, new BlockPos((Entity)out), 0);
                    out.func_96094_a(player.func_70005_c_());
                    out.func_174805_g(true);
                    out.particleStatus((byte)7);
                    out.cannotDespawn(false);
                }
            }
        }
    }

    @SubscribeEvent
    public void playerUp(PlayerWakeUpEvent event) {
        if (!SPConfigSystems.useEvolution || event.getEntity().field_70170_p.field_72995_K) {
            return;
        }
        SPSaveData data = SPSaveData.get(event.getEntityPlayer().field_70170_p, 85);
        World world = event.getEntityPlayer().field_70170_p;
        if (event.getEntity().field_70170_p.func_72820_D() % (long)SPConfig.dayTickValue < 13000L) {
            int bonus = 1;
            if (data.getEvolutionPhase(world.field_73011_w.getDimension()) >= SPConfigSystems.evolutionSleepDenied) {
                bonus = 5;
            }
            data.setTotalKills(world.field_73011_w.getDimension(), this.getSleepPointP(data.getEvolutionPhase(world.field_73011_w.getDimension())) * bonus, true, world, true, true, 55);
        }
    }

    private int getSleepPointP(byte phase) {
        switch (phase) {
            case 0: {
                return SPConfigSystems.sleepPenaltyZero;
            }
            case 1: {
                return SPConfigSystems.sleepPenaltyOne;
            }
            case 2: {
                return SPConfigSystems.sleepPenaltyTwo;
            }
            case 3: {
                return SPConfigSystems.sleepPenaltyThree;
            }
            case 4: {
                return SPConfigSystems.sleepPenaltyFour;
            }
            case 5: {
                return SPConfigSystems.sleepPenaltyFive;
            }
            case 6: {
                return SPConfigSystems.sleepPenaltySix;
            }
            case 7: {
                return SPConfigSystems.sleepPenaltySeven;
            }
            case 8: {
                return SPConfigSystems.sleepPenaltyEight;
            }
            case 9: {
                return SPConfigSystems.sleepPenaltyNine;
            }
            case 10: {
                return SPConfigSystems.sleepPenaltyTen;
            }
        }
        return 0;
    }

    @SubscribeEvent
    public void light(EntityStruckByLightningEvent event) {
        if (event.getEntity() instanceof EntityParasiteBase) {
            ((EntityParasiteBase)event.getEntity()).setKillC(1000000.0);
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void onEvent(MouseEvent event) {
        if (SPConfig.weaponCancelPacket) {
            return;
        }
        if (event.getButton() == 0 && event.isButtonstate()) {
            float reach;
            RayTraceResult mov;
            IHaveReach ieri;
            ItemStack itemstack;
            Minecraft mc = Minecraft.func_71410_x();
            EntityPlayerSP thePlayer = mc.field_71439_g;
            if (thePlayer != null && (itemstack = thePlayer.func_184614_ca()) != null && (ieri = itemstack.func_77973_b() instanceof IHaveReach ? (IHaveReach)itemstack.func_77973_b() : null) != null && (mov = this.getMouseOverExtended(reach = ieri.getReach())) != null && mov.field_72308_g != null && mov.field_72308_g.field_70172_ad == 0 && mov.field_72308_g != thePlayer) {
                SPMain.network.sendToServer((IMessage)new SPPacketMeleeRange(mov.field_72308_g.func_145782_y()));
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void onMouse(MouseEvent event) {
        if (event.getButton() != 0 || !event.isButtonstate()) {
            return;
        }
        Minecraft mc = Minecraft.func_71410_x();
        EntityPlayerSP p = mc.field_71439_g;
        if (p == null) {
            return;
        }
        Item item = p.func_184614_ca().func_77973_b();
        if (!(item instanceof WeaponToolMeleeBase)) {
            return;
        }
        if (p.func_184811_cZ().func_185141_a(item)) {
            event.setCanceled(true);
        }
    }

    private RayTraceResult getMouseOverExtended(float dist) {
        Minecraft mc = FMLClientHandler.instance().getClient();
        Entity theRenderViewEntity = mc.func_175606_aa();
        assert (theRenderViewEntity != null);
        AxisAlignedBB theViewBoundingBox = new AxisAlignedBB(theRenderViewEntity.field_70165_t - 0.5, theRenderViewEntity.field_70163_u - 0.0, theRenderViewEntity.field_70161_v - 0.5, theRenderViewEntity.field_70165_t + 0.5, theRenderViewEntity.field_70163_u + 1.5, theRenderViewEntity.field_70161_v + 0.5);
        RayTraceResult returnMOP = null;
        if (mc.field_71441_e != null) {
            double var2 = dist;
            returnMOP = theRenderViewEntity.func_174822_a(var2, 0.0f);
            double calcdist = var2;
            Vec3d pos = theRenderViewEntity.func_174824_e(0.0f);
            var2 = calcdist;
            if (returnMOP != null) {
                calcdist = returnMOP.field_72307_f.func_72438_d(pos);
            }
            Vec3d lookvec = theRenderViewEntity.func_70676_i(0.0f);
            Vec3d var8 = pos.func_72441_c(lookvec.field_72450_a * var2, lookvec.field_72448_b * var2, lookvec.field_72449_c * var2);
            Entity pointedEntity = null;
            float var9 = 1.0f;
            List list = mc.field_71441_e.func_72839_b(theRenderViewEntity, theViewBoundingBox.func_72321_a(lookvec.field_72450_a * var2, lookvec.field_72448_b * var2, lookvec.field_72449_c * var2).func_72314_b((double)var9, (double)var9, (double)var9));
            double d = calcdist;
            for (Entity entity : list) {
                double d1;
                if (!entity.func_70067_L()) continue;
                float bordersize = entity.func_70111_Y();
                AxisAlignedBB aabb = new AxisAlignedBB(entity.field_70165_t - (double)(entity.field_70130_N / 2.0f), entity.field_70163_u, entity.field_70161_v - (double)(entity.field_70130_N / 2.0f), entity.field_70165_t + (double)(entity.field_70130_N / 2.0f), entity.field_70163_u + (double)entity.field_70131_O, entity.field_70161_v + (double)(entity.field_70130_N / 2.0f));
                aabb.func_72321_a((double)bordersize, (double)bordersize, (double)bordersize);
                RayTraceResult mop0 = aabb.func_72327_a(pos, var8);
                if (aabb.func_72318_a(pos)) {
                    if (!(0.0 < d) && d != 0.0) continue;
                    pointedEntity = entity;
                    d = 0.0;
                    continue;
                }
                if (mop0 == null || !((d1 = pos.func_72438_d(mop0.field_72307_f)) < d) && d != 0.0) continue;
                pointedEntity = entity;
                d = d1;
            }
            if (pointedEntity != null && (d < calcdist || returnMOP == null)) {
                returnMOP = new RayTraceResult(pointedEntity);
            }
        }
        return returnMOP;
    }

    static {
        clientScent = 0;
        clientVector = 0;
        musicTimer = 1000;
        fog = 0.0f;
        fogRed = 0.0f;
        fogGreen = 0.0f;
        fogBlue = 0.0f;
        RICARDO_DEATH_RULE_RESTORE = new HashMap<Integer, Long>();
        srpSoakGuard = false;
    }

    public static class FogProperties {
        public float density;
        public float red;
        public float green;
        public float blue = 0.0f;

        public FogProperties(float red, float green, float blue, float density) {
            this.density = density;
            this.red = red;
            this.green = green;
            this.blue = blue;
        }

        public FogProperties() {
        }
    }
}

