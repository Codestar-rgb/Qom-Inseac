package com.dhanantry.scapeandrunparasites.util.handlers;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.block.BlockDiseasedSponge;
import com.dhanantry.scapeandrunparasites.block.BlockParasiteSpreading;
import com.dhanantry.scapeandrunparasites.client.particle.ParticleBiomass;
import com.dhanantry.scapeandrunparasites.client.particle.ParticleBlood;
import com.dhanantry.scapeandrunparasites.client.particle.ParticleCoolerFog;
import com.dhanantry.scapeandrunparasites.client.particle.ParticleDot;
import com.dhanantry.scapeandrunparasites.client.particle.ParticleEen;
import com.dhanantry.scapeandrunparasites.client.particle.ParticleFlash;
import com.dhanantry.scapeandrunparasites.client.particle.ParticleFog;
import com.dhanantry.scapeandrunparasites.client.particle.ParticleMultipleGore;
import com.dhanantry.scapeandrunparasites.client.particle.ParticleRHappy;
import com.dhanantry.scapeandrunparasites.client.particle.ParticleRage;
import com.dhanantry.scapeandrunparasites.client.particle.ParticleSpore;
import com.dhanantry.scapeandrunparasites.client.particle.ParticleWind;
import com.dhanantry.scapeandrunparasites.entity.ai.EntityAISkill;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityCanHaveBodies;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPInfected;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPMalleable;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPStationary;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityInhooM;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityInhooS;
import com.dhanantry.scapeandrunparasites.entity.monster.hijacked.EntityHiGolem;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfPlayer;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityNogla;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPMusic;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.item.tool.IHaveReach;
import com.dhanantry.scapeandrunparasites.item.tool.WeaponToolArmorBase;
import com.dhanantry.scapeandrunparasites.item.tool.WeaponToolMeleeBase;
import com.dhanantry.scapeandrunparasites.network.SRPPacketFog;
import com.dhanantry.scapeandrunparasites.network.SRPPacketMeleeRange;
import com.dhanantry.scapeandrunparasites.network.SRPPacketMovingSound;
import com.dhanantry.scapeandrunparasites.network.SRPPacketParticle;
import com.dhanantry.scapeandrunparasites.network.SRPPacketRequestEvoPhaseClient;
import com.dhanantry.scapeandrunparasites.util.EIVUtil;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.SRPReference;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.util.convert.BeckonBlockInfestation;
import com.dhanantry.scapeandrunparasites.util.spawn.ParasiteSummon;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import com.dhanantry.scapeandrunparasites.world.SRPWorldData;
import com.dhanantry.scapeandrunparasites.world.SRPWorldParasiteSpawner;
import com.dhanantry.scapeandrunparasites.world.biome.BiomeParasiteBase;
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
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.MusicTicker.MusicType;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.FogMode;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
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
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldType;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogDensity;
import net.minecraftforge.client.event.sound.PlayStreamingSourceEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent.Start;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteractSpecific;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.world.BlockEvent.NeighborNotifyEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import net.minecraftforge.event.world.BlockEvent.CropGrowEvent.Pre;
import net.minecraftforge.event.world.WorldEvent.Unload;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;
import org.lwjgl.input.Keyboard;

public class SRPEventHandlerBus {
   private static final Map<UUID, SRPEventHandlerBus.FogProperties> playerFogProperties = new HashMap<>();
   private ArrayList<ISound> musicToRemove = new ArrayList<>();
   public static byte clientCurrentEvoPhase;
   public static int clientScent = 0;
   public static int clientVector = 0;
   public static int musicTimer = 1000;
   private byte lockedMu = 0;
   public static float fog = 0.0F;
   public static float fogRed = 0.0F;
   public static float fogGreen = 0.0F;
   public static float fogBlue = 0.0F;
   private static final Map<Integer, Long> RICARDO_DEATH_RULE_RESTORE = new HashMap<>();
   private static boolean srpSoakGuard = false;
   private boolean closeG = false;
   private int counerW = 0;
   private int blockInfestedCountCooldown;
   private int blockParasiteCountCooldown;
   private int moo = 0;
   private ArrayList<Integer> worldsChecked = new ArrayList<>();
   private int meteor;
   private int heart;

   @SubscribeEvent
   @SideOnly(Side.CLIENT)
   public void onClientTick(ClientTickEvent event) {
      Minecraft mc = Minecraft.func_71410_x();
      if (mc.field_71439_g != null) {
         if (mc.field_71439_g.func_70644_a(SRPPotions.INDEAF_E)) {
            int i = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();
            KeyBinding.func_74510_a(i, false);
            KeyBinding.func_74510_a(i, false);
            KeyBinding.func_74510_a(i, false);
            KeyBinding.func_74510_a(i, false);
            KeyBinding.func_74510_a(i, false);
         }
      }
   }

   @SubscribeEvent
   @SideOnly(Side.CLIENT)
   public void soundTwo(PlayStreamingSourceEvent event) {
      SRPMain.logger.debug("phase: {} s: {} ticks: {}", clientCurrentEvoPhase, event.getName(), musicTimer);
      if (SRPConfig.musicTrue) {
         if (!event.getName().contains("srparasites") && (clientCurrentEvoPhase > 0 || clientScent > 0)) {
            if (SRPConfigWorld.originActivated && clientScent <= 0 && clientVector <= 0) {
               return;
            }

            this.musicToRemove.add(event.getSound());
            SRPMain.logger.debug("added {}", event.getName());
         }
      }
   }

   public static void resetSouncTicker(int in) {
      if (in > 0) {
         if (musicTimer >= in) {
            musicTimer = in;
         }
      } else {
         Random rand = new Random();
         int atm2 = SRPConfig.musicMax - SRPConfig.musicMin + 1;
         if (atm2 <= 0 || SRPConfig.musicMax <= SRPConfig.musicMin) {
            int goo = SRPConfig.musicMax + 1000;
         }

         int goo = rand.nextInt(atm2) + SRPConfig.musicMin;
         if (musicTimer >= goo || musicTimer <= 0) {
            musicTimer = goo;
         }
      }
   }

   @SubscribeEvent
   @SideOnly(Side.CLIENT)
   public void soundThree(PlayerTickEvent event) {
      if (SRPConfig.musicTrue) {
         if (event.phase != Phase.END) {
            if (event.side == Side.CLIENT) {
               if (Minecraft.func_71410_x() != null) {
                  if (event.player.field_70173_aa % 20 == 0) {
                     SRPMain.network.sendToServer(new SRPPacketRequestEvoPhaseClient());
                  }

                  int prev = clientVector;
                  if (clientVector > -10) {
                     clientVector--;
                  }

                  if (SRPConfigWorld.originActivated && clientVector <= 0) {
                     if (prev > 0) {
                        Minecraft.func_71410_x().func_147118_V().func_189520_a("", SoundCategory.MUSIC);
                     }
                  } else {
                     if (!this.musicToRemove.isEmpty()) {
                        for (int i = 0; i < this.musicToRemove.size(); i++) {
                           if (Minecraft.func_71410_x().func_147118_V().func_147692_c(this.musicToRemove.get(i))) {
                              Minecraft.func_71410_x().func_147118_V().func_147683_b(this.musicToRemove.get(i));
                              this.musicToRemove.remove(i);
                              i--;
                           } else {
                              this.lockedMu++;
                           }
                        }

                        if (this.lockedMu >= 100) {
                           this.musicToRemove = new ArrayList<>();
                        }
                     }

                     if (musicTimer >= 0) {
                        musicTimer--;
                     }

                     if (clientScent >= 0) {
                        clientScent--;
                     }

                     if (clientScent == -1) {
                        Minecraft.func_71410_x().func_147118_V().func_189520_a("", SoundCategory.MUSIC);
                        clientScent = -10;
                     }

                     if (musicTimer <= 0 && this.musicToRemove.isEmpty() && clientCurrentEvoPhase > 0) {
                        resetSouncTicker(0);
                        MusicType evoPhaseMusic = this.getMusicPhase();
                        if (evoPhaseMusic == null) {
                           return;
                        }

                        if (clientScent <= 0) {
                           Minecraft.func_71410_x().func_147118_V().func_189520_a("", SoundCategory.MUSIC);
                           Minecraft.func_71410_x().func_147118_V().func_147682_a(PositionedSoundRecord.func_184370_a(evoPhaseMusic.func_188768_a()));
                        }
                     } else {
                        resetSouncTicker(0);
                     }
                  }
               }
            }
         }
      }
   }

   @SideOnly(Side.CLIENT)
   private MusicType getMusicPhase() {
      EntityPlayerSP player = Minecraft.func_71410_x().field_71439_g;
      if (player.field_70170_p.func_180494_b(player.func_180425_c()) instanceof BiomeParasiteBase) {
         return SRPMusic.BIOME_MUSIC;
      } else {
         switch (clientCurrentEvoPhase) {
            case 1:
               return SRPMusic.EVPHASE_1_MUSIC;
            case 2:
               return SRPMusic.EVPHASE_2_MUSIC;
            case 3:
               return SRPMusic.EVPHASE_3_MUSIC;
            case 4:
               return SRPMusic.EVPHASE_4_MUSIC;
            case 5:
               return SRPMusic.EVPHASE_5_MUSIC;
            case 6:
               return SRPMusic.EVPHASE_6_MUSIC;
            case 7:
               return SRPMusic.EVPHASE_7_MUSIC;
            case 8:
               return SRPMusic.EVPHASE_8_MUSIC;
            case 9:
               return SRPMusic.EVPHASE_9_MUSIC;
            case 10:
               return SRPMusic.EVPHASE_10_MUSIC;
            default:
               return null;
         }
      }
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
   public void onWorldLoad(Unload event) {
      clientCurrentEvoPhase = 0;
      clientScent = 0;
      fog = 0.0F;
      musicTimer = 1000;
      SRPSaveData.falseLevel = 0;
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
   public void onEvent(FogDensity event) {
      Entity entity = event.getEntity();
      if (entity.func_70055_a(SRPBlocks.PARASITEBLOOD)) {
         GlStateManager.func_187430_a(FogMode.EXP);
         event.setDensity(1.0F);
         event.setCanceled(true);
      } else {
         if (fog > 0.0F && !Minecraft.func_71410_x().field_71439_g.func_70644_a(MobEffects.field_76440_q)) {
            GlStateManager.func_187430_a(FogMode.EXP);
            event.setDensity(fog);
            event.setCanceled(true);
         }
      }
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
   public void onEvent(FogColors event) {
      Entity entity = event.getEntity();
      if (entity.func_70055_a(SRPBlocks.PARASITEBLOOD)) {
         event.setRed(0.1F);
         event.setGreen(0.6F);
         event.setBlue(0.2F);
      } else {
         if (fog > SRPConfigWorld.biomeFogDensity * 0.2 && !Minecraft.func_71410_x().field_71439_g.func_70644_a(MobEffects.field_76440_q)) {
            event.setRed(fogRed);
            event.setGreen(fogGreen);
            event.setBlue(fogBlue);
         }
      }
   }

   @SubscribeEvent
   public void cropGrow(Pre event) {
      if (event.getResult() != Result.DENY) {
         if (SRPConfigWorld.nodesActivated || SRPConfigSystems.useEvolution) {
            SRPWorldData data = SRPWorldData.get(event.getWorld());
            int nodeAge = data.nearestHeartAge(event.getPos(), false, 0);
            double[] nodeChance = new double[]{
               0.0, SRPConfigWorld.nodeCropStopNodeOne, SRPConfigWorld.nodeCropStopNodeTwo, SRPConfigWorld.nodeCropStopNodeThree
            };
            if (nodeAge >= 1 && nodeAge <= 3 && event.getWorld().field_73012_v.nextDouble() < nodeChance[nodeAge]) {
               event.setResult(Result.DENY);
            } else {
               int phase = SRPSaveData.get(event.getWorld(), 79).getEvolutionPhase(event.getWorld().field_73011_w.getDimension());
               double[] phaseChance = new double[]{
                  0.0,
                  SRPConfigSystems.cropGrowStunnedOne,
                  SRPConfigSystems.cropGrowStunnedTwo,
                  SRPConfigSystems.cropGrowStunnedThree,
                  SRPConfigSystems.cropGrowStunnedFour,
                  SRPConfigSystems.cropGrowStunnedFive,
                  SRPConfigSystems.cropGrowStunnedSix,
                  SRPConfigSystems.cropGrowStunnedSeven,
                  SRPConfigSystems.cropGrowStunnedEight
               };
               if (phase >= 1 && phase < phaseChance.length && event.getWorld().field_73012_v.nextDouble() < phaseChance[phase]) {
                  event.setResult(Result.DENY);
               }
            }
         }
      }
   }

   @SubscribeEvent
   public void onLivingDeath(LivingDeathEvent event) {
      if (event.getEntity() instanceof EntityPlayer) {
         Entity src = event.getSource().func_76346_g();
         if (src instanceof EntityNogla) {
            EntityNogla nogla = (EntityNogla)src;
            if (nogla.isRicardoVariant()) {
               EntityPlayer player = (EntityPlayer)event.getEntity();
               MinecraftServer server = player.func_184102_h();
               if (server != null) {
                  WorldServer ws = (WorldServer)player.field_70170_p;
                  GameRules rules = ws.func_82736_K();
                  boolean wasShowing = rules.func_82766_b("showDeathMessages");
                  if (wasShowing) {
                     rules.func_82764_b("showDeathMessages", "false");
                     RICARDO_DEATH_RULE_RESTORE.put(ws.field_73011_w.getDimension(), ws.func_82737_E() + 1L);
                  }

                  server.func_184103_al()
                     .func_148539_a(new TextComponentTranslation("death.attack.srparasites.ricardo", new Object[]{player.func_145748_c_()}));
               }
            }
         }
      }
   }

   @SubscribeEvent
   public void onWorldTick(WorldTickEvent e) {
      if (e.phase == Phase.END && !e.world.field_72995_K) {
         Integer dim = e.world.field_73011_w.getDimension();
         Long when = RICARDO_DEATH_RULE_RESTORE.get(dim);
         if (when != null && e.world.func_82737_E() >= when) {
            ((WorldServer)e.world).func_82736_K().func_82764_b("showDeathMessages", "true");
            RICARDO_DEATH_RULE_RESTORE.remove(dim);
         }
      }
   }

   private static Block getDeadBloodBlock() {
      Fluid f = FluidRegistry.getFluid("deadblood");
      if (f == null) {
         return Blocks.field_150350_a;
      } else {
         Block b = f.getBlock();
         return b == null ? Blocks.field_150350_a : b;
      }
   }

   private static Block getDiseasedSpongeBlock() {
      return SRPBlocks.diseasedSponge;
   }

   private static boolean isSponge(Block b) {
      return b == Blocks.field_150360_v;
   }

   @SubscribeEvent
   public void onBlockPlaced(PlaceEvent e) {
      if (!e.getWorld().field_72995_K) {
         tryConvertSponge(e.getWorld(), e.getPos(), e.getPlacedBlock());
      }
   }

   @SubscribeEvent
   public void onNeighborNotify(NeighborNotifyEvent e) {
      if (!e.getWorld().field_72995_K) {
         World world = e.getWorld();
         BlockPos pos = e.getPos();
         Block deadBlood = getDeadBloodBlock();
         Block changed = e.getState().func_177230_c();
         IBlockState changedState = e.getState();
         if (isSponge(changed) || changed == deadBlood || isWater(changedState) || isLava(changedState)) {
            if (!srpSoakGuard) {
               srpSoakGuard = true;

               try {
                  if (changed != deadBlood) {
                     if (!isWater(changedState) && !isLava(changedState)) {
                        if (isSponge(changed)) {
                           for (EnumFacing f : EnumFacing.values()) {
                              if (world.func_180495_p(pos.func_177972_a(f)).func_177230_c() == deadBlood) {
                                 tryConvertSponge(world, pos, changedState);
                                 return;
                              }
                           }
                        }

                        return;
                     }

                     boolean touchesDeadBlood = false;

                     for (EnumFacing fx : EnumFacing.values()) {
                        if (world.func_180495_p(pos.func_177972_a(fx)).func_177230_c() == deadBlood) {
                           touchesDeadBlood = true;
                           break;
                        }
                     }

                     if (touchesDeadBlood) {
                        if (isWater(changedState)) {
                           IBlockState stain = getParasiteStainState();
                           if (stain.func_177230_c() != Blocks.field_150350_a) {
                              world.func_180501_a(pos, stain, 2);
                              return;
                           }
                        } else {
                           IBlockState rubble = getParasiteRubbleState();
                           if (rubble.func_177230_c() != Blocks.field_150350_a) {
                              world.func_180501_a(pos, rubble, 2);
                              return;
                           }
                        }

                        return;
                     }

                     return;
                  }

                  for (EnumFacing fxx : EnumFacing.values()) {
                     BlockPos n = pos.func_177972_a(fxx);
                     IBlockState st = world.func_180495_p(n);
                     if (isSponge(st.func_177230_c())) {
                        tryConvertSponge(world, n, st);
                     } else if (isWater(st)) {
                        IBlockState stain = getParasiteStainState();
                        if (stain.func_177230_c() != Blocks.field_150350_a) {
                           world.func_180501_a(n, stain, 2);
                        }
                     } else if (isLava(st)) {
                        IBlockState rubble = getParasiteRubbleState();
                        if (rubble.func_177230_c() != Blocks.field_150350_a) {
                           world.func_180501_a(n, rubble, 2);
                        }
                     }
                  }
               } finally {
                  srpSoakGuard = false;
               }
            }
         }
      }
   }

   private static void tryConvertSponge(World world, BlockPos spongePos, IBlockState spongeState) {
      if (!srpSoakGuard) {
         if (isSponge(spongeState.func_177230_c())) {
            Block deadBlood = getDeadBloodBlock();
            Block diseased = getDiseasedSpongeBlock();
            if (deadBlood != Blocks.field_150350_a && diseased != Blocks.field_150350_a) {
               boolean touching = false;

               for (EnumFacing f : EnumFacing.values()) {
                  if (world.func_180495_p(spongePos.func_177972_a(f)).func_177230_c() == deadBlood) {
                     touching = true;
                     break;
                  }
               }

               if (touching) {
                  srpSoakGuard = true;

                  try {
                     boolean absorbed = BlockDiseasedSponge.absorbDeadBlood(world, spongePos, deadBlood);
                     if (absorbed) {
                        world.func_180501_a(spongePos, diseased.func_176223_P(), 2);
                     }
                  } finally {
                     srpSoakGuard = false;
                  }
               }
            }
         }
      }
   }

   private static IBlockState getParasiteStainState() {
      Block b = (Block)ForgeRegistries.BLOCKS.getValue(new ResourceLocation("srparasites", "parasitestain"));
      return b != null && b != Blocks.field_150350_a ? b.func_176203_a(1) : Blocks.field_150350_a.func_176223_P();
   }

   private static IBlockState getParasiteRubbleState() {
      Block b = (Block)ForgeRegistries.BLOCKS.getValue(new ResourceLocation("srparasites", "parasiterubble"));
      return b != null && b != Blocks.field_150350_a ? b.func_176203_a(7) : Blocks.field_150350_a.func_176223_P();
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
      if (!event.isCanceled()) {
         EntityLivingBase entity = event.getEntityLiving();
         if (entity != null && !entity.field_70170_p.field_72995_K) {
            if (entity.field_70170_p.func_180494_b(entity.func_180425_c()) instanceof BiomeParasiteBase) {
               float penaltyH;
               switch (ParasiteEventWorld.canBiomeStillExistType(entity.field_70170_p, entity.func_180425_c(), true)) {
                  case 2:
                     penaltyH = SRPConfigWorld.biomeTwoHealPenalty;
                     break;
                  case 3:
                     penaltyH = SRPConfigWorld.biomeThreeHealPenalty;
                     break;
                  case 4:
                     penaltyH = SRPConfigWorld.biomeFourHealPenalty;
                     break;
                  default:
                     penaltyH = SRPConfigWorld.biomeOneHealPenalty;
               }

               if (entity instanceof EntityParasiteBase) {
                  return;
               }

               if (entity instanceof EntityPlayer) {
                  event.setAmount(event.getAmount() * penaltyH);
               } else {
                  boolean flag = ParasiteEventEntity.checkName(
                     EntityList.func_191301_a(entity).toString(), SRPConfigWorld.biomeHealPenaltyBlackList, SRPConfigWorld.biomeHealPenaltyBlackListWhite
                  );
                  if (flag) {
                     return;
                  }

                  event.setAmount(event.getAmount() * penaltyH);
               }
            }

            if (SRPConfigSystems.useEvolution
               && SRPSaveData.get(entity.field_70170_p, 80).getEvolutionPhase(entity.field_70170_p.field_73011_w.getDimension())
                  >= SRPConfigSystems.evolutionNoParasiteHealing) {
               event.setAmount(event.getAmount() * SRPConfigSystems.evolutionNoParasiteHealingValue);
            }
         }
      }
   }

   @SubscribeEvent
   public void entityHurt(LivingHurtEvent event) {
      EntityLivingBase entity = event.getEntityLiving();
      if (entity != null && !entity.field_70170_p.field_72995_K) {
         if (entity.func_70644_a(SRPPotions.VIRA_E) && SRPConfigSystems.viralEnable) {
            float amp = entity.func_70660_b(SRPPotions.VIRA_E).func_76458_c() + 1;
            float damage = event.getAmount();
            event.setAmount(damage + damage * (amp * SRPConfigSystems.viralAmount));
         }

         if (entity.func_70644_a(SRPPotions.OVERHEATING_E)
            && (event.getSource() == DamageSource.field_76370_b || event.getSource() == DamageSource.field_76372_a)) {
            float amp = entity.func_70660_b(SRPPotions.OVERHEATING_E).func_76458_c() + 1;
            float damage = event.getAmount();
            event.getSource().func_151518_m();
            event.setAmount(damage + damage * (amp * 1.0F));
         }

         if (event.getSource().func_76346_g() instanceof EntityLivingBase) {
            EntityLivingBase mob = (EntityLivingBase)event.getSource().func_76346_g();
            if (mob.func_70644_a(SRPPotions.MUSCLEOUT_E)) {
               float amp = mob.func_70660_b(SRPPotions.MUSCLEOUT_E).func_76458_c() + 1.0F;
               event.setAmount(event.getAmount() * (SRPConfigSystems.muscleoutDamageOut * amp));
            }
         }

         if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            event.setAmount(this.playerArmor(player, event.getAmount(), event.getSource()));
         }
      }
   }

   private float playerArmor(EntityPlayer player, float damageV, DamageSource source) {
      float red = 0.0F;
      String damage = "";
      byte type = 0;
      boolean needCheckP = true;
      boolean paraA = false;

      for (ItemStack itemstack : player.field_71071_by.field_70460_b) {
         if (itemstack.func_77973_b() instanceof WeaponToolArmorBase) {
            paraA = true;
            if (needCheckP && source.func_76364_f() != null) {
               if (source == DamageSource.field_76372_a || source == DamageSource.field_76370_b || player.func_70027_ad()) {
                  return damageV * SRPConfig.firemultyplier;
               }

               if (source.func_76364_f() instanceof EntityPlayer) {
                  damage = source.func_76364_f().func_70005_c_();
               } else if (source.func_76364_f() instanceof EntityLivingBase) {
                  damage = EntityList.func_191301_a(source.func_76364_f()).toString();
               } else {
                  damage = source.field_76373_n;
                  type = 2;
               }

               needCheckP = false;
            }

            ArrayList<String> resistanceS = new ArrayList<>();
            ArrayList<Integer> resistanceI = new ArrayList<>();
            NBTTagCompound compound = itemstack.func_77978_p();
            boolean lag = ((WeaponToolArmorBase)itemstack.func_77973_b()).canCall();
            if (compound == null) {
               compound = new NBTTagCompound();
            }

            if (compound.func_150297_b("sprresistanceb", 99)) {
               lag = compound.func_74767_n("sprresistanceb");
            }

            if (compound.func_74764_b("sprresistances")) {
               NBTTagList allResS = compound.func_150295_c("sprresistances", 10);
               NBTTagList allResI = compound.func_150295_c("sprresistancei", 10);
               if (allResS.func_74745_c() != allResI.func_74745_c()) {
                  return damageV;
               }

               for (int i = 0; i < allResS.func_74745_c(); i++) {
                  NBTTagCompound resT = allResS.func_150305_b(i);
                  String res = resT.func_74779_i("resistance" + i);
                  resistanceS.add(i, res);
                  NBTTagCompound resU = allResI.func_150305_b(i);
                  int resi = resU.func_74762_e("resistance" + i);
                  resistanceI.add(i, resi);
               }
            }

            red += this.hasResistance(damage, resistanceS, resistanceI, lag, player.field_70170_p.field_73012_v, type)
               * (lag ? SRPConfig.sentientPointReduction : SRPConfig.livingPointReduction);
            if (resistanceS.size() != resistanceI.size()) {
               return damageV;
            }

            NBTTagList allResS = new NBTTagList();
            NBTTagList allResI = new NBTTagList();

            for (int i = 0; i < resistanceS.size(); i++) {
               String res = resistanceS.get(i);
               NBTTagCompound resT = new NBTTagCompound();
               resT.func_74778_a("resistance" + i, res);
               allResS.func_74742_a(resT);
               int resi = resistanceI.get(i);
               NBTTagCompound resU = new NBTTagCompound();
               resU.func_74768_a("resistance" + i, resi);
               allResI.func_74742_a(resU);
            }

            compound.func_74782_a("sprresistances", allResS);
            compound.func_74782_a("sprresistancei", allResI);
            compound.func_74757_a("sprresistanceb", lag);
            if (compound.func_74764_b("srphits")) {
               int key = (int)(compound.func_74762_e("srphits") + damageV);
               compound.func_74768_a("srphits", key);
            } else {
               compound.func_74768_a("srphits", (int)damageV);
            }

            itemstack.func_77982_d(compound);
         }
      }

      red *= damageV;
      damageV = Math.max(damageV - red, 0.0F);
      if (source.func_76364_f() instanceof EntityLivingBase && paraA && SRPConfig.armorCoth) {
         SRPPotions.applyStackPotion(SRPPotions.COTH_E, (EntityLivingBase)source.func_76364_f(), 400, 2);
      }

      return damageV;
   }

   private int hasResistance(String damage, ArrayList<String> resistanceS, ArrayList<Integer> resistanceI, boolean stage, Random rand, byte type) {
      if (this.checkList(damage, type)) {
         return 0;
      } else {
         double getChanceLearn = stage ? SRPConfig.sentientChanceLe : SRPConfig.livingChanceLe;
         if (rand.nextDouble() < getChanceLearn) {
            this.addResistance(damage, resistanceS, resistanceI, stage);
         }

         for (int i = 0; i < resistanceS.size(); i++) {
            if (resistanceS.get(i).equals(damage)) {
               int tage = SRPConfig.livingPointCap;
               if (stage) {
                  tage = SRPConfig.sentientPointCap;
               }

               return Math.min(resistanceI.get(i), tage);
            }
         }

         return 0;
      }
   }

   private void addResistance(String damage, ArrayList<String> resistanceS, ArrayList<Integer> resistanceI, boolean stage) {
      boolean flag = true;

      for (int i = 0; i < resistanceS.size(); i++) {
         if (resistanceS.get(i).equals(damage)) {
            int iiii = resistanceI.get(i) + 1;
            resistanceI.set(i, iiii);
            flag = false;
            break;
         }
      }

      if (flag) {
         int lim = SRPConfig.livingDamageCap;
         if (stage) {
            lim = SRPConfig.sentientDamageCap;
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
         case 0:
            if (ParasiteEventEntity.checkName(damage, SRPConfig.armorDamageTypeBlackListMob, SRPConfig.armorDamageTypeBlackListWhite)) {
               return true;
            }
         case 2:
            if (ParasiteEventEntity.checkName(damage, SRPConfig.armorDamageTypeBlackListElse, SRPConfig.armorDamageTypeBlackListWhite)) {
               return true;
            }
         default:
            return false;
      }
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void stitchEventPre(net.minecraftforge.client.event.TextureStitchEvent.Pre event) {
      event.getMap().func_174942_a(ParticleFog.PARTICLES_TEXTURE);
      event.getMap().func_174942_a(ParticleEen.RED);
      event.getMap().func_174942_a(ParticleDot.DOT);
      event.getMap().func_174942_a(ParticleRHappy.REDHAPPY);
      event.getMap().func_174942_a(ParticleFlash.FLASH);
      event.getMap().func_174942_a(ParticleBlood.BLOOD1);
      event.getMap().func_174942_a(ParticleBlood.BLOOD2);
      event.getMap().func_174942_a(ParticleBlood.BLOOD_LAND);
      event.getMap().func_174942_a(ParticleSpore.WHITE1);
      event.getMap().func_174942_a(ParticleSpore.WHITE2);
      event.getMap().func_174942_a(ParticleSpore.WHITE3);
      event.getMap().func_174942_a(ParticleSpore.WHITE4);
      event.getMap().func_174942_a(ParticleMultipleGore.ASSIMILATED1);
      event.getMap().func_174942_a(ParticleMultipleGore.ASSIMILATED2);
      event.getMap().func_174942_a(ParticleMultipleGore.ASSIMILATED3);
      event.getMap().func_174942_a(ParticleMultipleGore.PURE1);
      event.getMap().func_174942_a(ParticleMultipleGore.PURE2);
      event.getMap().func_174942_a(ParticleMultipleGore.PURE3);
      event.getMap().func_174942_a(ParticleMultipleGore.PRIMITIVE1);
      event.getMap().func_174942_a(ParticleMultipleGore.PRIMITIVE2);
      event.getMap().func_174942_a(ParticleMultipleGore.PRIMITIVE3);
      event.getMap().func_174942_a(ParticleMultipleGore.ADAPTED1);
      event.getMap().func_174942_a(ParticleMultipleGore.ADAPTED2);
      event.getMap().func_174942_a(ParticleMultipleGore.ADAPTED3);
      event.getMap().func_174942_a(ParticleMultipleGore.VOMIT1);
      event.getMap().func_174942_a(ParticleMultipleGore.VOMIT2);
      event.getMap().func_174942_a(ParticleMultipleGore.VOMIT3);
      event.getMap().func_174942_a(ParticleMultipleGore.VOMIT4);
      event.getMap().func_174942_a(ParticleMultipleGore.VOMIT5);
      event.getMap().func_174942_a(ParticleMultipleGore.VOMIT6);
      event.getMap().func_174942_a(ParticleBiomass.BIOMASS1);
      event.getMap().func_174942_a(ParticleBiomass.BIOMASS2);
      event.getMap().func_174942_a(ParticleBiomass.BIOMASS3);
      event.getMap().func_174942_a(ParticleBiomass.BIOMASS4);
      event.getMap().func_174942_a(ParticleWind.WIND1);
      event.getMap().func_174942_a(ParticleWind.WIND2);
      event.getMap().func_174942_a(ParticleWind.WIND3);
      event.getMap().func_174942_a(ParticleWind.WIND4);
      event.getMap().func_174942_a(ParticleWind.WIND5);
      event.getMap().func_174942_a(ParticleWind.WIND6);
      event.getMap().func_174942_a(ParticleWind.WIND7);
      event.getMap().func_174942_a(ParticleWind.WIND8);
      event.getMap().func_174942_a(ParticleWind.WIND9);
      event.getMap().func_174942_a(ParticleWind.WIND10);
      event.getMap().func_174942_a(ParticleWind.WIND11);
      event.getMap().func_174942_a(ParticleWind.WIND12);
      event.getMap().func_174942_a(ParticleWind.WIND13);
      event.getMap().func_174942_a(ParticleCoolerFog.FOG_INTRO1);
      event.getMap().func_174942_a(ParticleCoolerFog.FOG_INTRO2);
      event.getMap().func_174942_a(ParticleCoolerFog.FOG_INTRO3);
      event.getMap().func_174942_a(ParticleCoolerFog.FOG_INTRO4);
      event.getMap().func_174942_a(ParticleCoolerFog.FOG_INTRO5);
      event.getMap().func_174942_a(ParticleCoolerFog.FOG1);
      event.getMap().func_174942_a(ParticleCoolerFog.FOG2);
      event.getMap().func_174942_a(ParticleCoolerFog.FOG3);
      event.getMap().func_174942_a(ParticleCoolerFog.FOG4);
      event.getMap().func_174942_a(ParticleRage.RAGE1);
      event.getMap().func_174942_a(ParticleRage.RAGE2);
      event.getMap().func_174942_a(ParticleRage.RAGE3);
   }

   @SubscribeEvent
   public void playerFishing(ItemFishedEvent event) {
      if (!event.getEntity().field_70170_p.field_72995_K && SRPConfigSystems.useEvolution) {
         SRPWorldData data = SRPWorldData.get(event.getEntity().field_70170_p);
         if (SRPSaveData.get(event.getEntity().field_70170_p, 81).getEvolutionPhase(event.getEntity().field_70170_p.field_73011_w.getDimension())
            >= SRPConfigSystems.evolutionStopFishing) {
            event.setCanceled(true);
         }
      }
   }

   @SubscribeEvent
   public void itemEntity(EntityInteract event) {
      if (event.getTarget() instanceof EntityLivingBase && !event.getWorld().field_72995_K) {
         ItemStack stack = event.getItemStack();
         String item = stack.func_77973_b().getRegistryName().toString();
         String[] atm = new String[3];

         for (int i = 0; i < SRPConfigSystems.COTHItemPrevent.length; i++) {
            atm = SRPConfigSystems.COTHItemPrevent[i].split(";");
            if (atm[0].equals(item)) {
               int dur = Integer.parseInt(atm[2]);
               if (!event.getEntityPlayer().field_71075_bZ.field_75098_d) {
                  stack.func_190918_g(1);
                  double chance = Double.parseDouble(atm[1]);
                  if (event.getWorld().field_73012_v.nextDouble() < chance) {
                     ((EntityLivingBase)event.getTarget()).func_70690_d(new PotionEffect(SRPPotions.EPEL_E, dur * 20, 0));
                     SRPMain.network
                        .sendToAll(
                           new SRPPacketParticle(
                              event.getTarget().field_70165_t,
                              event.getTarget().field_70163_u,
                              event.getTarget().field_70161_v,
                              event.getTarget().field_70130_N,
                              event.getTarget().field_70131_O,
                              (byte)3
                           )
                        );
                  }
               } else {
                  ((EntityLivingBase)event.getTarget()).func_70690_d(new PotionEffect(SRPPotions.EPEL_E, dur * 20, 0));
                  SRPMain.network
                     .sendToAll(
                        new SRPPacketParticle(
                           event.getTarget().field_70165_t,
                           event.getTarget().field_70163_u,
                           event.getTarget().field_70161_v,
                           event.getTarget().field_70130_N,
                           event.getTarget().field_70131_O,
                           (byte)3
                        )
                     );
               }
            }
         }
      }
   }

   @SubscribeEvent
   public void itemPlayer(Start event) {
      if (event.getEntityLiving() instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)event.getEntityLiving();
         if (player != null) {
            if (player.func_70644_a(SRPPotions.FEAR_E) && SRPConfigSystems.fearActive) {
               if (player.func_70660_b(SRPPotions.FEAR_E).func_76458_c() >= 1
                  && player.field_70170_p.field_73012_v.nextDouble() < SRPConfigSystems.fearItemChance
                  && !ParasiteEventEntity.checkName(
                     player.func_184582_a(EntityEquipmentSlot.MAINHAND).func_77973_b().getRegistryName().toString(),
                     SRPConfigSystems.fearItemBlackList,
                     SRPConfigSystems.fearItemBlackListWhite
                  )) {
                  player.func_146105_b(
                     new TextComponentTranslation("message.srparasites.fearitem", new Object[0]).func_150255_a(new Style().func_150238_a(TextFormatting.RED)),
                     true
                  );
                  event.setDuration(-1);
                  event.setCanceled(true);
                  return;
               }

               if (event.getItem().func_77973_b() instanceof ItemBlock) {
               }
            }
         }
      }
   }

   @SubscribeEvent
   public void blockPlayer(RightClickBlock event) {
      EntityPlayer player = event.getEntityPlayer();
      if (player != null) {
         if (player.func_70644_a(SRPPotions.FEAR_E) && SRPConfigSystems.fearActive) {
            if (player.func_70660_b(SRPPotions.FEAR_E).func_76458_c() >= 2
               && player.field_70170_p.field_73012_v.nextDouble() < SRPConfigSystems.fearBlockChance) {
               player.func_146105_b(
                  new TextComponentTranslation("message.srparasites.fearblock", new Object[0]).func_150255_a(new Style().func_150238_a(TextFormatting.RED)),
                  true
               );
               event.setUseBlock(Result.DENY);
            } else if (player.func_70660_b(SRPPotions.FEAR_E).func_76458_c() >= 1
               && player.field_70170_p.field_73012_v.nextDouble() < SRPConfigSystems.fearItemChance) {
               player.func_146105_b(
                  new TextComponentTranslation("message.srparasites.fearitem", new Object[0]).func_150255_a(new Style().func_150238_a(TextFormatting.RED)),
                  true
               );
               event.setUseItem(Result.DENY);
            }
         }
      }
   }

   @SubscribeEvent
   public void entityPlayer(EntityInteractSpecific event) {
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
   public void guiPlayer(GuiOpenEvent event) {
      Minecraft mc = Minecraft.func_71410_x();
      EntityPlayer player = mc.field_71439_g;
      if (player != null && event.getGui() != null) {
         if (!player.func_184812_l_() && !event.getGui().func_73868_f()) {
            if (player.func_70644_a(SRPPotions.FEAR_E)
               && SRPConfigSystems.fearActive
               && !player.field_71075_bZ.field_75102_a
               && player.func_70660_b(SRPPotions.FEAR_E).func_76458_c() >= 3
               && player.field_70170_p.field_73012_v.nextDouble() < SRPConfigSystems.fearInvChance) {
               player.func_146105_b(new TextComponentTranslation("message.srparasites.feargui", new Object[0]), true);
               this.closeG = true;
            }
         }
      }
   }

   @SubscribeEvent
   public void mobFear(LivingDamageEvent event) {
      EntityLivingBase in = event.getEntityLiving();
      if (in != null && SRPConfigSystems.fearActive) {
         if (in.func_70644_a(SRPPotions.FEAR_E)) {
            int amp = in.func_70660_b(SRPPotions.FEAR_E).func_76458_c() + 1;
            if (event.getSource() == DamageSource.field_76379_h && SRPConfigSystems.fearFallDamage != 0.0F) {
               event.setAmount(event.getAmount() * (SRPConfigSystems.fearFallDamage * amp));
            }

            if (SRPConfigSystems.fearAirDamage != 0.0F) {
               if (SRPConfigSystems.fearUnfair) {
                  if (!in.field_70122_E) {
                     event.setAmount(event.getAmount() * (SRPConfigSystems.fearAirDamage * amp));
                  }
               } else {
                  boolean inFluidOrSupport = in.func_70090_H() || in.func_180799_ab() || in.func_70617_f_() || in.func_184218_aH();
                  if (!inFluidOrSupport) {
                     boolean creativeFlying = false;
                     boolean elytraFlying = false;
                     if (in instanceof EntityPlayer) {
                        EntityPlayer p = (EntityPlayer)in;
                        creativeFlying = p.field_71075_bZ.field_75100_b;
                        elytraFlying = p.func_184613_cA();
                     }

                     float MIN_FALL_DISTANCE = 2.0F;
                     boolean descending = in.field_70181_x < 0.0;
                     boolean trueAirborne = creativeFlying || elytraFlying || !in.field_70122_E && descending && in.field_70143_R >= 2.0F;
                     if (trueAirborne) {
                        event.setAmount(event.getAmount() * (SRPConfigSystems.fearAirDamage * amp));
                     }
                  }
               }
            }
         }
      }
   }

   @SubscribeEvent
   public void onEntitySpawn(EntityJoinWorldEvent event) {
      if (event.getEntity() != null) {
         if (event.getEntity() instanceof EntityLivingBase && !event.getEntity().field_70170_p.field_72995_K) {
            if (event.getEntity() instanceof EntityPlayer) {
               return;
            }

            String mobname;
            try {
               mobname = EntityList.func_191301_a(event.getEntity()).toString();
            } catch (Exception var7) {
               SRPMain.logger.log(Level.ERROR, "Problem with spawning entity");
               return;
            }

            NBTTagCompound tags = event.getEntity().getEntityData();
            boolean parasite = event.getEntity() instanceof EntityParasiteBase;
            boolean flagNC = SRPConfigWorld.nodesActivated || SRPConfigWorld.coloniesActivated || SRPConfigSystems.useEvolution;
            SRPWorldData data = null;
            if (flagNC) {
               data = SRPWorldData.get(event.getWorld());
            }

            if (SRPConfigWorld.nodesActivated && SRPConfigSystems.cothActive && data.nearestHeartAge(event.getEntity().func_180425_c(), false, 0) != -1) {
               ((EntityLivingBase)event.getEntity()).func_70690_d(new PotionEffect(SRPPotions.COTH_E, 3600, 0, false, false));
            }

            if (!tags.func_74764_b("srpcothimmunity") && SRPConfigSystems.cothActive && !parasite) {
               this.writeCOTHTag((EntityLivingBase)event.getEntity(), mobname, tags);
            }

            if (parasite) {
               this.setNewParasiteTask((EntityParasiteBase)event.getEntity(), mobname, flagNC, data);
            } else if (event.getEntity() instanceof EntityCreature) {
               this.setNewCreatureTask((EntityCreature)event.getEntity(), mobname);
            }
         }
      }
   }

   private void writeCOTHTag(EntityLivingBase in, String mobname, NBTTagCompound tags) {
      if (in instanceof EntityArmorStand) {
         tags.func_74768_a("srpcothimmunity", 0);
      } else if (ParasiteEventEntity.checkName(mobname, SRPConfigSystems.COTHImmuneList, SRPConfigSystems.COTHImmuneListWhite)) {
         tags.func_74768_a("srpcothimmunity", 0);
      } else {
         tags.func_74768_a("srpcothimmunity", 1);
      }

      this.setCOTH(in, SRPSaveData.get(in.field_70170_p, 84).getEvolutionPhase(in.field_70170_p.field_73011_w.getDimension()));
   }

   private void setNewParasiteTask(EntityParasiteBase entity, String mobname, boolean flagNC, SRPWorldData data) {
      SRPSaveData dataS = SRPSaveData.get(entity.field_70170_p, 82);
      if (!entity.spawnedByColo) {
         entity.spawnedByColo = true;
         switch (dataS.getChoice()) {
            case 2:
               this.changeAttribute(entity, SharedMonsterAttributes.field_111267_a, 3.0);
               this.changeAttribute(entity, SharedMonsterAttributes.field_188791_g, 3.0);
               this.changeAttribute(entity, SharedMonsterAttributes.field_111264_e, 3.0);
               this.changeAttribute(entity, SharedMonsterAttributes.field_111266_c, 3.0);
               break;
            case 3:
               this.changeAttribute(entity, SharedMonsterAttributes.field_111267_a, 10.0);
               this.changeAttribute(entity, SharedMonsterAttributes.field_188791_g, 10.0);
               this.changeAttribute(entity, SharedMonsterAttributes.field_111264_e, 10.0);
               this.changeAttribute(entity, SharedMonsterAttributes.field_111266_c, 10.0);
         }

         entity.applyBonuses(dataS, entity.field_70170_p);
         this.applyDislo(entity, dataS, entity.field_70170_p.field_73011_w.getDimension());
         this.applyColony(entity, data.totalColonyPoints(0));
         this.applyNode(entity, data.totalNodePoints(0));
         if (entity instanceof EntityPMalleable) {
            EntityPMalleable uwu = (EntityPMalleable)entity;
            String damage = data.getMostCommonDamageS();
            if (damage != null) {
               int times = data.getMostCommonDamageI();

               while (times > 0) {
                  times--;
                  uwu.addResistance(damage);
               }

               uwu.increaseDamageCap(1);
               uwu.colonySpawned = true;
            }
         }

         if (entity instanceof EntityCanHaveBodies) {
            EntityCanHaveBodies head = (EntityCanHaveBodies)entity;
            if (head.getCanF()) {
               int len = head.getBodyLength();
               EntityCanHaveBodies current = head;

               for (int i = 0; i < len; i++) {
                  EntityCanHaveBodies entityWithBodies = head.getAnotherBody(entity.field_70170_p);
                  entityWithBodies.setCanF(false);
                  entityWithBodies.setFollowing(current);
                  entityWithBodies.copyCopy(current);
                  entityWithBodies.onSpawn(entity.field_70170_p.func_175649_E(new BlockPos(entity)), null);
                  entity.field_70170_p.func_72838_d(entityWithBodies.getEntity());
                  entityWithBodies.setBodyNumber(i + 1);
                  if (len - 1 == i) {
                     entityWithBodies.setBodyTail(true);
                  }

                  current = entityWithBodies;
               }
            }
         }
      }

      if (SRPConfig.parasiteGriefing.length != 0) {
         String[] task = new String[4];

         for (int i = 0; i < SRPConfig.parasiteGriefing.length; i++) {
            if (SRPConfig.parasiteGriefing[i] != null) {
               task = SRPConfig.parasiteGriefing[i].split(";");
               if (task[0].equals(mobname)) {
                  if (entity instanceof EntityPStationary) {
                     entity.setSkillBreakBlocksValues(Float.parseFloat(task[1]), MathHelper.func_76123_f(entity.field_70131_O), Integer.parseInt(task[3]));
                  } else {
                     entity.setSkillBreakBlocksValues(Float.parseFloat(task[1]), MathHelper.func_76123_f(entity.field_70131_O), Integer.parseInt(task[3]));
                     entity.field_70714_bg.func_75776_a(9, new EntityAISkill(entity, Integer.parseInt(task[2]), 64, false, 13));
                  }
                  break;
               }
            }
         }
      }

      if (entity instanceof EntityPInfected && SRPConfigSystems.generationUse) {
         entity.func_70606_j(entity.func_110143_aJ() * this.getSimCOTHMod(SRPSaveData.get(entity.field_70170_p, 83), entity.field_70170_p));
      }
   }

   private void applyDislo(EntityParasiteBase in, SRPSaveData dataTwo, int id) {
      int[] killa = dataTwo.getDisloValues(id);
      if (SRPConfigSystems.disloSummonByDeath && !in.disloNumberTwo && killa[2] > 0) {
         in.disloNumberTwo = true;
      }

      if (SRPConfigSystems.disloPotiEff && !in.disloNumberThree && killa[3] > 0) {
         in.disloNumberThree = true;
         int amp = dataTwo.getCurrentCode(id, 3);
         String here = SRPConfigSystems.disloPotiEffEffects[in.field_70170_p.field_73012_v.nextInt(SRPConfigSystems.disloPotiEffEffects.length)];
         Potion potion = Potion.func_180142_b(here);
         if (potion != null) {
            in.func_70690_d(new PotionEffect(potion, dataTwo.getCurrentCodeDuration(id, 3) * 20 + 50, amp));
         }
      }

      if (SRPConfigSystems.dislostats && !in.disloNumberFour && killa[4] > 0) {
         in.disloNumberFour = true;
         int multip = dataTwo.getCurrentCode(id, 4);
         in.func_110148_a(SharedMonsterAttributes.field_111267_a)
            .func_111128_a(in.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() * (1 + multip));
         in.func_110148_a(SharedMonsterAttributes.field_188791_g)
            .func_111128_a(in.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111125_b() * (1 + multip));
         in.func_110148_a(SharedMonsterAttributes.field_111264_e)
            .func_111128_a(in.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111125_b() * (1 + multip));
      }

      if (SRPConfigSystems.disloItemDura && !in.disloNumberSix && killa[6] > 0) {
         in.disloNumberSix = true;
      }

      if (SRPConfigSystems.disloHealingDeath && !in.disloNumberSeven && killa[7] > 0) {
         in.disloNumberSeven = true;
      }

      if (SRPConfigSystems.disloDamageDeath && !in.disloNumberEight && killa[8] > 0) {
         in.disloNumberEight = true;
      }

      if (SRPConfigSystems.disloFoodDeath && !in.disloNumberNine && killa[9] > 0) {
         in.disloNumberNine = true;
      }

      if (SRPConfigSystems.disloParasiteNoPotion && in.disloNumberEleven <= 0 && killa[11] > 0) {
         in.disloNumberEleven = dataTwo.getCurrentCodeDuration(id, 11) * 20 + 50;
      }

      if (SRPConfigSystems.disloGrowlNoise && in.disloNumberFifteen <= 0 && killa[15] > 0) {
         in.disloNumberFifteen = dataTwo.getCurrentCodeDuration(id, 15) * 20 + 50;
         in.func_184212_Q().func_187227_b(EntityParasiteBase.DISLO15, true);
      }

      if (SRPConfigSystems.disloWalkNoise && in.disloNumberSixteen <= 0 && killa[16] > 0) {
         in.disloNumberSixteen = dataTwo.getCurrentCodeDuration(id, 16) * 20 + 50;
      }

      if (SRPConfigSystems.disloShieldFood && in.disloNumberSeventeen <= 0 && killa[17] > 0) {
         in.disloNumberSeventeen = dataTwo.getCurrentCodeDuration(id, 17) * 20 + 50;
      }

      if (SRPConfigSystems.disloLootXpCanc && !in.disloNumberEighteen && killa[18] > 0) {
         in.disloNumberEighteen = true;
      }

      if (SRPConfigSystems.disloKillcountInc && in.disloNumberNineteen <= 0 && killa[19] > 0) {
         in.disloNumberNineteen = dataTwo.getCurrentCodeDuration(id, 19) * 20 + 50;
         in.disloNumberNineteenValue = killa[19];
      }

      if (SRPConfigSystems.disloGiveBodies && killa[20] > 0) {
         if (in instanceof EntityInhooM) {
            ((EntityInhooM)in).disloNumberTwenty = true;
         }

         if (in instanceof EntityInhooS) {
            ((EntityInhooS)in).disloNumberTwenty = true;
         }
      }

      if (SRPConfigSystems.disloBurningDeath && !in.disloNumberTwentyone && killa[21] > 0) {
         in.disloNumberTwentyone = true;
      }

      if (SRPConfigSystems.disloSameVersionDyeing && !in.disloNumberTwentytwo && killa[22] > 0) {
         in.disloNumberTwentytwo = true;
      }
   }

   private void applyColony(EntityParasiteBase in, int totalColonyPoints) {
      if (totalColonyPoints != 0) {
         double bonus = totalColonyPoints / SRPConfigWorld.colonyExtraHealthPoint * SRPConfigWorld.colonyExtraHealthValue;
         this.changeAttribute(in, SharedMonsterAttributes.field_111267_a, bonus);
         in.func_70606_j((float)in.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b());
         bonus = totalColonyPoints / SRPConfigWorld.colonyExtraArmorPoint * SRPConfigWorld.colonyExtraArmorValue;
         this.changeAttribute(in, SharedMonsterAttributes.field_188791_g, bonus);
         bonus = totalColonyPoints / SRPConfigWorld.colonyExtraDamagePoint * SRPConfigWorld.colonyExtraDamageValue;
         this.changeAttribute(in, SharedMonsterAttributes.field_111264_e, bonus);
         bonus = totalColonyPoints / SRPConfigWorld.colonyExtraKDResPoint * SRPConfigWorld.colonyExtraKDResValue;
         this.changeAttribute(in, SharedMonsterAttributes.field_111266_c, bonus);
         bonus = totalColonyPoints / SRPConfigWorld.colonyDamageCapPoint * SRPConfigWorld.colonyDamageCapValue;
         in.damageCap = (int)(in.damageCap + in.damageCap * bonus);
      }
   }

   private void changeAttribute(EntityParasiteBase in, IAttribute stat, double bonus) {
      double base = in.func_110148_a(stat).func_111125_b();
      in.func_110148_a(stat).func_111128_a(base + base * bonus);
   }

   private void applyNode(EntityParasiteBase in, int totalPoints) {
      if (totalPoints != 0) {
         String[] here = new String[3];

         for (String i : SRPConfigWorld.potionEffectForNodes) {
            here = i.split(";");
            int level = Integer.parseInt(here[0]);
            if (totalPoints >= level) {
               int amp = Integer.parseInt(here[2]);
               Potion potion = Potion.func_180142_b(here[1]);
               if (potion != null) {
                  in.func_70690_d(new PotionEffect(potion, 7777, amp));
               }
            }
         }
      }
   }

   private float getSimCOTHMod(SRPSaveData data, World world) {
      switch (data.getGeneration(world.field_73011_w.getDimension())) {
         case 0:
            return SRPConfigSystems.generationCOTH0;
         case 1:
            return SRPConfigSystems.generationCOTH1;
         case 2:
            return SRPConfigSystems.generationCOTH2;
         case 3:
            return SRPConfigSystems.generationCOTH3;
         case 4:
            return SRPConfigSystems.generationCOTH4;
         case 5:
            return SRPConfigSystems.generationCOTH5;
         default:
            return 1.0F;
      }
   }

   @SubscribeEvent
   public void onEntityMount(EntityMountEvent event) {
      if (event.getEntityBeingMounted() != null && event.getEntityMounting() != null) {
         if (event.getEntityBeingMounted().func_70089_S()) {
            if (event.getEntityMounting() instanceof EntityPlayer && event.getEntityBeingMounted() instanceof EntityHiGolem) {
               EntityPlayer player = (EntityPlayer)event.getEntityMounting();
               if (!player.field_71075_bZ.field_75102_a && player.func_110143_aJ() > 0.0F && event.isDismounting()) {
                  event.setCanceled(true);
               }
            }
         }
      }
   }

   private void setNewCreatureTask(EntityCreature entity, String mobname) {
      if (ParasiteEventEntity.checkName(mobname, SRPConfig.entitiesWillAttack, SRPConfig.entitiesWillAttackWhite)) {
         entity.field_70715_bh.func_75776_a(5, new EntityAINearestAttackableTarget(entity, EntityParasiteBase.class, true));
      } else {
         if (ParasiteEventEntity.checkName(mobname, SRPConfig.entitiesWillAvoid, SRPConfig.entitiesWillAvoidWhite)) {
            entity.field_70714_bg.func_75776_a(5, new EntityAIAvoidEntity(entity, EntityParasiteBase.class, 12.0F, 0.8, 0.8));
         }
      }
   }

   private void setCOTH(EntityLivingBase target, byte evo) {
      if (!target.func_70631_g_()) {
         Random rand = new Random();
         switch (evo) {
            case 1:
               if (rand.nextDouble() < SRPConfigSystems.mobSpawningCOTHChanceOne) {
                  target.func_70690_d(new PotionEffect(SRPPotions.COTH_E, 3600, 0, false, false));
               }
               break;
            case 2:
               if (rand.nextDouble() < SRPConfigSystems.mobSpawningCOTHChanceTwo) {
                  target.func_70690_d(new PotionEffect(SRPPotions.COTH_E, 3600, 0, false, false));
               }
               break;
            case 3:
               if (rand.nextDouble() < SRPConfigSystems.mobSpawningCOTHChanceThree) {
                  target.func_70690_d(new PotionEffect(SRPPotions.COTH_E, 3600, 0, false, false));
               }
               break;
            case 4:
               if (rand.nextDouble() < SRPConfigSystems.mobSpawningCOTHChanceFour) {
                  target.func_70690_d(new PotionEffect(SRPPotions.COTH_E, 3600, 0, false, false));
               }
               break;
            case 5:
               if (rand.nextDouble() < SRPConfigSystems.mobSpawningCOTHChanceFive) {
                  target.func_70690_d(new PotionEffect(SRPPotions.COTH_E, 3600, 0, false, false));
               }
               break;
            case 6:
               if (rand.nextDouble() < SRPConfigSystems.mobSpawningCOTHChanceSix) {
                  target.func_70690_d(new PotionEffect(SRPPotions.COTH_E, 3600, 0, false, false));
               }
               break;
            case 7:
               if (rand.nextDouble() < SRPConfigSystems.mobSpawningCOTHChanceSeven) {
                  target.func_70690_d(new PotionEffect(SRPPotions.COTH_E, 3600, 0, false, false));
               }
               break;
            case 8:
               if (rand.nextDouble() < SRPConfigSystems.mobSpawningCOTHChanceEight) {
                  target.func_70690_d(new PotionEffect(SRPPotions.COTH_E, 3600, 0, false, false));
               }
         }
      }
   }

   @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
   public void serverTick(ServerTickEvent event) {
      if (event.phase != Phase.END) {
         if (BeckonBlockInfestation.blockInfestedCount > SRPConfig.BlockInfestedLimit) {
            this.blockInfestedCountCooldown++;
            if (this.blockInfestedCountCooldown > SRPConfig.BlockInfestedLimitCD) {
               this.blockInfestedCountCooldown = 0;
               BeckonBlockInfestation.blockInfestedCount = 0;
            }
         }

         if (BlockParasiteSpreading.blockParasiteCount > SRPConfig.BlockParasiteLimit) {
            this.blockParasiteCountCooldown++;
            if (this.blockParasiteCountCooldown > SRPConfig.BlockParasiteLimitCD) {
               this.blockParasiteCountCooldown = 0;
               BlockParasiteSpreading.blockParasiteCount = 0;
            }
         }

         this.counerW++;
         if (SRPConfigWorld.originWorldCheckDebugSpeed > 0) {
            this.counerW = this.counerW + SRPConfigWorld.originWorldCheckDebugSpeed;
         }

         ParasiteEventWorld.disloCool--;
         if (this.counerW > SRPConfig.dayTickValue
            && (SRPConfigWorld.nodesActivated || SRPConfigWorld.coloniesActivated || SRPConfigSystems.useEvolution || SRPConfigWorld.originActivated)) {
            MinecraftServer ser = FMLCommonHandler.instance().getMinecraftServerInstance();
            if (ser.field_71305_c.length > 0) {
               SRPSaveData dat = SRPSaveData.get(ser.field_71305_c[0], 89);
               dat.addUpdateNumber(1);
            }

            this.worldsChecked = new ArrayList<>();
            this.counerW = -150;
         }
      }
   }

   @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
   public void worldTick(WorldTickEvent event) {
      if (event.phase != Phase.END) {
         if (!event.world.field_72995_K) {
            this.moo++;
            if (SRPConfigSystems.useEvolution && SRPConfigSystems.phaseCustomSpawner) {
               if (event.world.func_73046_m() == null) {
                  return;
               }

               this.tickSpawn(event.world.func_73046_m().func_71218_a(event.world.field_73011_w.getDimension()));
            }

            if (this.moo >= 20 * SRPConfigSystems.disloSeconds) {
               this.moo = 0;
               SRPSaveData.get(event.world, 88).reduceCodesCooldown(event.world.field_73011_w.getDimension(), SRPConfigSystems.disloSeconds, event.world);
            }

            this.meteor++;
            if (this.meteor > SRPConfigWorld.meteorTick) {
               this.meteor = 0;
               if (event.world.field_73012_v.nextDouble() < SRPConfigWorld.meteorChance && SRPConfig.spawnDays <= (int)event.world.func_82737_E()) {
                  SRPWorldData data = SRPWorldData.get(event.world);
                  if (data.getTriggerMet() && SRPSaveData.get(event.world, -549).getEvolutionPhase(event.world.field_73011_w.getDimension()) >= 0) {
                     if (SRPConfigWorld.meteorVectorless) {
                        if (data.getorigins("x").isEmpty()) {
                           this.spawningMet(event.world);
                        }
                     } else {
                        this.spawningMet(event.world);
                     }
                  }
               }
            }

            if (this.counerW < 0
               && (SRPConfigWorld.nodesActivated || SRPConfigWorld.coloniesActivated || SRPConfigSystems.useEvolution || SRPConfigWorld.originActivated)) {
               int id = event.world.field_73011_w.getDimension();

               for (int i : this.worldsChecked) {
                  if (i == id) {
                     return;
                  }
               }

               ParasiteEventWorld.checkColonyStatus(event.world);
               ParasiteEventWorld.checkNodeStatus(event.world);
               SRPMain.logger
                  .debug(
                     "[EIV DEBUG] worldTick is calling createRandomOrigin. dim={} time={} counerW={} worldsChecked={} originActivated={} min={} max={}",
                     event.world.field_73011_w.getDimension(),
                     event.world.func_82737_E(),
                     this.counerW,
                     this.worldsChecked,
                     SRPConfigWorld.originActivated,
                     SRPConfigWorld.originCreatingDistanceMin,
                     SRPConfigWorld.originCreatingDistanceMax
                  );
               EIVUtil.createRandomOrigin(event.world, SRPConfigWorld.originCreatingDistanceMin, SRPConfigWorld.originCreatingDistanceMax);
               this.worldsChecked.add(id);
            }
         }
      }
   }

   private void spawningMet(World world) {
      List<EntityPlayer> mobs = Lists.newArrayList();
      mobs.addAll(world.field_73010_i);
      boolean flag = true;
      if (mobs.size() != 0) {
         for (EntityPlayer mob : mobs) {
            if (world.func_175710_j(mob.func_180425_c())) {
               ParasiteSummon.spawnMeteor(mob.func_180425_c(), world.field_73012_v.nextInt(SRPConfigWorld.meteorRadius), SRPConfigWorld.meteorMinRadius, world);
               flag = false;
               break;
            }
         }

         if (flag) {
            Iterator var6 = mobs.iterator();
            if (var6.hasNext()) {
               EntityPlayer mobx = (EntityPlayer)var6.next();
               ParasiteSummon.spawnMeteor(mobx.func_180425_c(), world.field_73012_v.nextInt(SRPConfigWorld.meteorRadius), SRPConfigWorld.meteorMinRadius, world);
            }
         }
      }
   }

   private void tickSpawn(WorldServer server) {
      if (server.func_82736_K().func_82766_b("doMobSpawning") && server.func_72912_H().func_76067_t() != WorldType.field_180272_g) {
         SRPWorldParasiteSpawner.findChunksForSpawning(server, true, false, server.func_72912_H().func_82573_f() % 400L == 0L);
      }
   }

   @SubscribeEvent
   public void setLoot(LivingDropsEvent event) {
      if (event.getEntityLiving() instanceof EntityParasiteBase) {
         if (!event.getEntityLiving().field_70170_p.func_82736_K().func_82766_b("doMobLoot")) {
            return;
         }

         if (event.getEntityLiving().func_70644_a(SRPPotions.DEBAR_E)) {
            return;
         }

         EntityParasiteBase mob = (EntityParasiteBase)event.getEntityLiving();
         if (mob.disloNumberEighteen
            && SRPSaveData.get(event.getEntityLiving().field_70170_p, 87)
                  .getCurrentCode(event.getEntityLiving().field_70170_p.field_73011_w.getDimension(), 18)
               > 0) {
            return;
         }

         switch (mob.getParasiteIDRegister()) {
            case 1:
               this.loot(event, SRPConfigMobs.shycoLoot);
               return;
            case 2:
               this.loot(event, SRPConfigMobs.dorpaLoot);
               return;
            case 3:
               this.loot(event, SRPConfigMobs.ratholLoot);
               return;
            case 4:
               this.loot(event, SRPConfigMobs.emanaLoot);
               return;
            case 5:
               this.loot(event, SRPConfigMobs.LodoLoot);
               return;
            case 6:
               this.loot(event, SRPConfigMobs.infhumanLoot);
               return;
            case 7:
               this.loot(event, SRPConfigMobs.hullLoot);
               return;
            case 8:
               this.loot(event, SRPConfigMobs.canraLoot);
               return;
            case 9:
               this.loot(event, SRPConfigMobs.alafhaLoot);
               return;
            case 10:
               this.loot(event, SRPConfigMobs.noglaLoot);
               return;
            case 11:
               this.loot(event, SRPConfigMobs.butholLoot);
               return;
            case 12:
               this.loot(event, SRPConfigMobs.mudoLoot);
               return;
            case 13:
               this.loot(event, SRPConfigMobs.infcowLoot);
               return;
            case 14:
               this.loot(event, SRPConfigMobs.infsheepLoot);
               return;
            case 15:
               this.loot(event, SRPConfigMobs.infwolfLoot);
               return;
            case 16:
               this.loot(event, SRPConfigMobs.venkrolLoot);
               return;
            case 17:
               this.loot(event, SRPConfigMobs.zetmoLoot);
               return;
            case 18:
               this.loot(event, SRPConfigMobs.venkrolsiiLoot);
               return;
            case 19:
               this.loot(event, SRPConfigMobs.venkrolsiiiLoot);
               return;
            case 20:
               this.loot(event, SRPConfigMobs.terlaLoot);
               return;
            case 21:
               this.loot(event, SRPConfigMobs.infwolfheadLoot);
               return;
            case 22:
               this.loot(event, SRPConfigMobs.infsheepheadLoot);
               return;
            case 23:
               this.loot(event, SRPConfigMobs.kolLoot);
               return;
            case 24:
               this.loot(event, SRPConfigMobs.oroncoLoot);
               return;
            case 25:
               this.loot(event, SRPConfigMobs.angedLoot);
               return;
            case 26:
               this.loot(event, SRPConfigMobs.infpigLoot);
               return;
            case 27:
               this.loot(event, SRPConfigMobs.infvillagerLoot);
               return;
            case 28:
               this.loot(event, SRPConfigMobs.infcowheadLoot);
               return;
            case 29:
               this.loot(event, SRPConfigMobs.tonroLoot);
               return;
            case 30:
               this.loot(event, SRPConfigMobs.unvoLoot);
               return;
            case 31:
               this.loot(event, SRPConfigMobs.infpigheadLoot);
               return;
            case 32:
               this.loot(event, SRPConfigMobs.infvillagerheadLoot);
               return;
            case 33:
               this.loot(event, SRPConfigMobs.ganroLoot);
               return;
            case 34:
               this.loot(event, SRPConfigMobs.pod1Loot);
            case 35:
            case 42:
            case 61:
            case 68:
            case 83:
            case 84:
            case 89:
            case 90:
            case 91:
            case 92:
            case 100:
            case 101:
            case 102:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case 108:
            case 109:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
            case 115:
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 131:
            case 132:
            case 133:
            case 134:
            case 135:
            case 136:
            case 137:
            case 138:
            case 139:
            case 140:
            case 141:
            case 142:
            case 143:
            case 144:
            case 145:
            case 146:
            case 147:
            case 148:
            case 149:
            case 150:
            case 151:
            case 152:
            case 153:
            case 154:
            case 155:
            case 156:
            case 157:
            case 158:
            case 159:
            case 160:
            case 161:
            case 162:
            case 163:
            case 164:
            case 165:
            case 166:
            case 167:
            case 168:
            case 169:
            case 170:
            case 171:
            case 172:
            case 173:
            case 174:
            case 175:
            case 176:
            case 177:
            case 178:
            case 179:
            case 180:
            case 181:
            case 182:
            case 183:
            case 184:
            case 185:
            case 186:
            case 187:
            case 188:
            case 189:
            case 190:
            case 191:
            case 192:
            case 193:
            case 194:
            case 195:
            case 196:
            case 197:
            case 198:
            case 199:
            case 200:
            case 201:
            case 202:
            case 203:
            case 204:
            case 205:
            case 206:
            case 207:
            case 208:
            case 209:
            case 210:
            case 211:
            case 212:
            case 213:
            case 214:
            case 215:
            case 216:
            case 217:
            case 218:
            case 219:
            case 220:
            case 221:
            case 222:
            case 223:
            case 224:
            case 225:
            case 226:
            case 227:
            case 228:
            case 229:
            case 230:
            case 231:
            case 232:
            case 233:
            case 234:
            case 235:
            case 236:
            case 237:
            case 238:
            case 239:
            case 240:
            case 241:
            case 242:
            case 243:
            case 244:
            case 245:
            case 246:
            case 247:
            case 248:
            case 249:
            case 250:
            case 251:
            case 252:
            case 253:
            case 254:
            case 255:
            case 256:
            case 257:
            case 258:
            case 259:
            case 260:
            case 261:
            case 262:
            case 263:
            case 264:
            case 265:
            case 266:
            case 267:
            case 268:
            case 269:
            case 270:
            case 271:
            case 272:
            case 273:
            case 274:
            case 275:
            case 276:
            case 277:
            case 278:
            case 279:
            case 280:
            case 281:
            case 282:
            case 283:
            case 284:
            case 285:
            case 286:
            case 287:
            case 288:
            case 289:
            case 290:
            case 291:
            case 292:
            case 293:
            case 294:
            case 295:
            case 296:
            case 297:
            case 298:
            case 299:
            case 304:
            case 305:
            case 307:
            case 308:
            case 310:
            case 311:
            case 312:
            case 313:
            case 314:
            case 315:
            case 316:
            case 317:
            case 318:
            case 319:
            case 320:
            case 325:
            case 326:
            case 327:
            case 328:
            default:
               break;
            case 36:
               this.loot(event, SRPConfigMobs.kolLoot);
               return;
            case 37:
               this.loot(event, SRPConfigMobs.shycoLoot);
               return;
            case 38:
               this.loot(event, SRPConfigMobs.arachnidaLoot);
               return;
            case 39:
               this.loot(event, SRPConfigMobs.inhooSLoot);
               return;
            case 40:
               this.loot(event, SRPConfigMobs.infadventurerLoot);
               return;
            case 41:
               this.loot(event, SRPConfigMobs.venkrolsivLoot);
               return;
            case 43:
               this.loot(event, SRPConfigMobs.inhooMLoot);
               return;
            case 44:
               this.loot(event, SRPConfigMobs.infhorseLoot);
               return;
            case 45:
               this.loot(event, SRPConfigMobs.infhorseheadLoot);
               return;
            case 46:
               this.loot(event, SRPConfigMobs.infhumanheadLoot);
               return;
            case 47:
               this.loot(event, SRPConfigMobs.ombooLoot);
               return;
            case 48:
               this.loot(event, SRPConfigMobs.hostLoot);
               return;
            case 49:
               this.loot(event, SRPConfigMobs.infbearLoot);
               return;
            case 50:
               this.loot(event, SRPConfigMobs.esorLoot);
               return;
            case 51:
               this.loot(event, SRPConfigMobs.shycoadaptedloot);
               return;
            case 52:
               this.loot(event, SRPConfigMobs.hulladaptedloot);
               return;
            case 53:
               this.loot(event, SRPConfigMobs.canraadaptedloot);
               return;
            case 54:
               this.loot(event, SRPConfigMobs.noglaadaptedloot);
               return;
            case 55:
               this.loot(event, SRPConfigMobs.emanaadaptedloot);
               return;
            case 56:
               this.loot(event, SRPConfigMobs.zetmoadaptedloot);
               return;
            case 57:
               this.loot(event, SRPConfigMobs.shycoLoot);
               return;
            case 58:
               this.loot(event, SRPConfigMobs.arachnidaadaptedloot);
               return;
            case 59:
               this.loot(event, SRPConfigMobs.infendermanLoot);
               return;
            case 60:
               this.loot(event, SRPConfigMobs.flogLoot);
               return;
            case 62:
               this.loot(event, SRPConfigMobs.cruxaLoot);
               return;
            case 63:
               this.loot(event, SRPConfigMobs.heedLoot);
               return;
            case 64:
               this.loot(event, SRPConfigMobs.infdragoneLoot);
               return;
            case 65:
               this.loot(event, SRPConfigMobs.jinjoLoot);
               return;
            case 66:
               this.loot(event, SRPConfigMobs.lumLoot);
               return;
            case 67:
               this.loot(event, SRPConfigMobs.kirinLoot);
               return;
            case 69:
               this.loot(event, SRPConfigMobs.infendermanheadLoot);
               return;
            case 70:
               this.loot(event, SRPConfigMobs.infdragoneheadLoot);
               return;
            case 71:
               this.loot(event, SRPConfigMobs.infadventurerheadLoot);
               return;
            case 72:
               this.loot(event, SRPConfigMobs.nakLoot);
               return;
            case 73:
               this.loot(event, SRPConfigMobs.dodsiLoot);
               return;
            case 74:
               this.loot(event, SRPConfigMobs.ratholLoot);
               return;
            case 75:
               this.loot(event, SRPConfigMobs.herdLoot);
               return;
            case 76:
               this.loot(event, SRPConfigMobs.nuuhLoot);
               return;
            case 77:
               this.loot(event, SRPConfigMobs.dodsiiLoot);
               return;
            case 78:
               this.loot(event, SRPConfigMobs.dodsiiiLoot);
               return;
            case 79:
               this.loot(event, SRPConfigMobs.dodsivLoot);
               return;
            case 80:
               this.loot(event, SRPConfigMobs.thrallLoot);
               return;
            case 81:
               this.loot(event, SRPConfigMobs.lumadaptedloot);
               return;
            case 82:
               this.loot(event, SRPConfigMobs.ombooLoot);
               return;
            case 85:
               this.loot(event, SRPConfigMobs.elviaLoot);
               return;
            case 86:
               this.loot(event, SRPConfigMobs.lenciaLoot);
               return;
            case 87:
               this.loot(event, SRPConfigMobs.pheonLoot);
               return;
            case 88:
               this.loot(event, SRPConfigMobs.vestaLoot);
               return;
            case 93:
               this.loot(event, SRPConfigMobs.fercowLoot);
               return;
            case 94:
               this.loot(event, SRPConfigMobs.ferendermanLoot);
               return;
            case 95:
               this.loot(event, SRPConfigMobs.ferhorseLoot);
               return;
            case 96:
               this.loot(event, SRPConfigMobs.ferhumanLoot);
               return;
            case 97:
               this.loot(event, SRPConfigMobs.ferpigLoot);
               return;
            case 98:
               this.loot(event, SRPConfigMobs.fersheepLoot);
               return;
            case 99:
               this.loot(event, SRPConfigMobs.fervillagerLoot);
               return;
            case 300:
               this.loot(event, SRPConfigMobs.ferwolfLoot);
               return;
            case 301:
               this.loot(event, SRPConfigMobs.higolemLoot);
               return;
            case 302:
               this.loot(event, SRPConfigMobs.hiblazeLoot);
               return;
            case 303:
               this.loot(event, SRPConfigMobs.hiskeletonLoot);
               return;
            case 306:
               this.loot(event, SRPConfigMobs.ferbearLoot);
               return;
            case 309:
               this.loot(event, SRPConfigMobs.hebluLoot);
               return;
            case 321:
               this.loot(event, SRPConfigMobs.marendermanLoot);
               return;
            case 322:
               this.loot(event, SRPConfigMobs.marcowLoot);
               return;
            case 323:
               this.loot(event, SRPConfigMobs.marvillagerLoot);
               return;
            case 324:
               this.loot(event, SRPConfigMobs.marhumanLoot);
               return;
            case 329:
               this.loot(event, SRPConfigMobs.marsheepLoot);
               return;
            case 330:
               this.loot(event, SRPConfigMobs.marbearLoot);
               return;
         }
      } else if (event.getEntity() instanceof EntityLivingBase && !(event.getEntity() instanceof EntityPlayer) && SRPConfigSystems.cothActive) {
         if (event.getSource().func_76346_g() instanceof EntityParasiteBase && SRPConfig.mobsKilledDropLoot) {
            event.setCanceled(true);
            return;
         }

         if (((EntityLivingBase)event.getEntity()).func_70644_a(SRPPotions.COTH_E)) {
            if (SRPConfigSystems.useEvolution) {
               if (SRPSaveData.get(event.getEntity().field_70170_p, 86).getEvolutionPhase(event.getEntity().field_70170_p.field_73011_w.getDimension())
                  >= SRPConfigSystems.evolutionCothStopLoot) {
                  NBTTagCompound tags = event.getEntity().getEntityData();
                  if (tags.func_74764_b("srpcothimmunity")) {
                     int key = tags.func_74762_e("srpcothimmunity");
                     if (key != 0) {
                        event.setCanceled(true);
                     }
                  }
               }
            } else if (SRPConfigSystems.cothLootDisable) {
               NBTTagCompound tags = event.getEntity().getEntityData();
               if (tags.func_74764_b("srpcothimmunity")) {
                  int key = tags.func_74762_e("srpcothimmunity");
                  if (key != 0) {
                     event.setCanceled(true);
                  }
               }
            }
         }
      }
   }

   private void loot(LivingDropsEvent event, String[] drop) {
      try {
         if (drop.length != 0) {
            String[] dropping = new String[4];
            String[] dropped = new String[drop.length];
            Random rand = new Random();
            int totalFalse = 0;

            for (String s : drop) {
               dropping = s.split(";");
               boolean always = Boolean.parseBoolean(dropping[3]);
               int quantity = Integer.parseInt(dropping[2]);
               int chance = Integer.parseInt(dropping[1]);
               if (always) {
                  int rng = rand.nextInt(100);
                  if (rng <= chance - 1) {
                     Item item = Item.func_111206_d(dropping[0]);
                     int realquantity = rand.nextInt(quantity);

                     for (int j = 0; j <= realquantity && item != null; j++) {
                        BlockPos pos = event.getEntity().func_180425_c();
                        event.getDrops()
                           .add(
                              new EntityItem(
                                 event.getEntity().func_130014_f_(), pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), new ItemStack(item)
                              )
                           );
                     }
                  }
               } else {
                  dropped[totalFalse] = s;
                  totalFalse++;
               }
            }

            if (totalFalse != 0) {
               int n = rand.nextInt(totalFalse);
               String[] stringItem = dropped[n].split(";");
               int quantity = Integer.parseInt(stringItem[2]);
               int chance = Integer.parseInt(stringItem[1]);
               int rng = rand.nextInt(100);
               if (rng <= chance - 1) {
                  Item item = Item.func_111206_d(stringItem[0]);
                  int realquantity = rand.nextInt(quantity);

                  for (int j = 0; j <= realquantity && item != null; j++) {
                     BlockPos pos = event.getEntity().func_180425_c();
                     event.getDrops()
                        .add(
                           new EntityItem(
                              event.getEntity().func_130014_f_(), pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), new ItemStack(item)
                           )
                        );
                  }
               }
            }
         }
      } catch (Exception var19) {
         SRPMain.logger.log(Level.ERROR, "Problem with loot event", var19);
      }
   }

   @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
   public void playerTick(PlayerTickEvent event) {
      if (event.phase == Phase.START) {
         EntityPlayer thePlayer = event.player;
         if (thePlayer.field_70170_p.field_72995_K) {
            if (this.closeG) {
               if (thePlayer.field_71070_bA != null) {
                  thePlayer.func_71053_j();
               }

               this.closeG = false;
            }
         } else {
            boolean isParaBiome = thePlayer.field_70170_p.func_180494_b(thePlayer.func_180425_c()) instanceof BiomeParasiteBase;
            if (ParasiteEventWorld.canBiomeStillExist(thePlayer.field_70170_p, thePlayer.func_180425_c(), false) < 1 && !isParaBiome) {
               if (fog > 0.0F) {
                  fog = Math.max(fog - 8.0E-4F, 0.0F);
                  fogRed = 0.0F;
                  fogGreen = 0.0F;
                  fogBlue = 0.0F;
                  SRPMain.network.sendTo(new SRPPacketFog(fog, fogRed, fogGreen, fogBlue), (EntityPlayerMP)thePlayer);
               }
            } else {
               BiomeParasiteBase biomeChecked;
               if (isParaBiome) {
                  biomeChecked = (BiomeParasiteBase)thePlayer.field_70170_p.func_180494_b(thePlayer.func_180425_c());
               } else {
                  biomeChecked = SRPReference.getBiomeFromInt(
                     ParasiteEventWorld.canBiomeStillExistType(thePlayer.field_70170_p, thePlayer.func_180425_c(), false)
                  );
               }

               fog = Math.min(fog + 4.5E-4F, SRPConfigWorld.biomeFogDensity);
               fogRed = biomeChecked.getRedValue();
               fogGreen = biomeChecked.getGreenValue();
               fogBlue = biomeChecked.getBlueValue();
               if (fog < SRPConfigWorld.biomeFogDensity) {
                  SRPMain.network.sendTo(new SRPPacketFog(fog, fogRed, fogGreen, fogBlue), (EntityPlayerMP)thePlayer);
               }
            }

            this.heart++;
            if (this.heart < SRPConfigWorld.biomeHeartFreq) {
               return;
            }

            this.heart = 0;
            if (isParaBiome) {
               SRPWorldData data = SRPWorldData.get(thePlayer.field_70170_p);
               int age = data.nearestHeartAge(thePlayer.func_180425_c(), true, 0);
               if (age > 0) {
                  int totalS = data.getDistanceSpreadByAge(age, true);
                  float vol = ((float)data.isInRangeOfHeart(thePlayer.func_180425_c(), totalS) / totalS - 1.0F) * -1.0F;
                  SRPMain.network.sendTo(new SRPPacketMovingSound(-1, vol), (EntityPlayerMP)thePlayer);
               }
            }
         }
      }
   }

   @SubscribeEvent
   public void playerDeath(LivingDeathEvent event) {
      if (event.getEntity() instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)event.getEntity();
         if (SRPConfigSystems.cothActive && SRPConfigMobs.infadventurerEnabled && SRPConfigMobs.infadventurerSpawnBy && player.func_70644_a(SRPPotions.COTH_E)) {
            int amp = player.func_70660_b(SRPPotions.COTH_E).func_76458_c();
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
               out.func_82149_j(player);
               out.func_180482_a(player.field_70170_p.func_175649_E(new BlockPos(out)), (IEntityLivingData)null);
               player.field_70170_p.func_72838_d(out);
               player.field_70170_p.func_180498_a((EntityPlayer)null, 1026, new BlockPos(out), 0);
               out.func_96094_a(player.func_70005_c_());
               out.func_174805_g(true);
               out.particleStatus((byte)7);
               out.cannotDespawn(false);
            } else if (amp == 1) {
               EntityInhooM out = new EntityInhooM(player.field_70170_p);
               out.func_82149_j(player);
               out.func_180482_a(player.field_70170_p.func_175649_E(new BlockPos(out)), (IEntityLivingData)null);
               player.field_70170_p.func_72838_d(out);
               player.field_70170_p.func_180498_a((EntityPlayer)null, 1026, new BlockPos(out), 0);
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
      if (SRPConfigSystems.useEvolution && !event.getEntity().field_70170_p.field_72995_K) {
         SRPSaveData data = SRPSaveData.get(event.getEntityPlayer().field_70170_p, 85);
         World world = event.getEntityPlayer().field_70170_p;
         if (event.getEntity().field_70170_p.func_72820_D() % SRPConfig.dayTickValue < 13000L) {
            int bonus = 1;
            if (data.getEvolutionPhase(world.field_73011_w.getDimension()) >= SRPConfigSystems.evolutionSleepDenied) {
               bonus = 5;
            }

            data.setTotalKills(
               world.field_73011_w.getDimension(),
               this.getSleepPointP(data.getEvolutionPhase(world.field_73011_w.getDimension())) * bonus,
               true,
               world,
               true,
               true,
               55
            );
         }
      }
   }

   private int getSleepPointP(byte phase) {
      switch (phase) {
         case 0:
            return SRPConfigSystems.sleepPenaltyZero;
         case 1:
            return SRPConfigSystems.sleepPenaltyOne;
         case 2:
            return SRPConfigSystems.sleepPenaltyTwo;
         case 3:
            return SRPConfigSystems.sleepPenaltyThree;
         case 4:
            return SRPConfigSystems.sleepPenaltyFour;
         case 5:
            return SRPConfigSystems.sleepPenaltyFive;
         case 6:
            return SRPConfigSystems.sleepPenaltySix;
         case 7:
            return SRPConfigSystems.sleepPenaltySeven;
         case 8:
            return SRPConfigSystems.sleepPenaltyEight;
         case 9:
            return SRPConfigSystems.sleepPenaltyNine;
         case 10:
            return SRPConfigSystems.sleepPenaltyTen;
         default:
            return 0;
      }
   }

   @SubscribeEvent
   public void light(EntityStruckByLightningEvent event) {
      if (event.getEntity() instanceof EntityParasiteBase) {
         ((EntityParasiteBase)event.getEntity()).setKillC(1000000.0);
      }
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
   public void onEvent(MouseEvent event) {
      if (!SRPConfig.weaponCancelPacket) {
         if (event.getButton() == 0 && event.isButtonstate()) {
            Minecraft mc = Minecraft.func_71410_x();
            EntityPlayer thePlayer = mc.field_71439_g;
            if (thePlayer != null) {
               ItemStack itemstack = thePlayer.func_184614_ca();
               if (itemstack != null) {
                  IHaveReach ieri;
                  if (itemstack.func_77973_b() instanceof IHaveReach) {
                     ieri = (IHaveReach)itemstack.func_77973_b();
                  } else {
                     ieri = null;
                  }

                  if (ieri != null) {
                     float reach = ieri.getReach();
                     RayTraceResult mov = this.getMouseOverExtended(reach);
                     if (mov != null && mov.field_72308_g != null && mov.field_72308_g.field_70172_ad == 0 && mov.field_72308_g != thePlayer) {
                        SRPMain.network.sendToServer(new SRPPacketMeleeRange(mov.field_72308_g.func_145782_y()));
                     }
                  }
               }
            }
         }
      }
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public void onMouse(MouseEvent event) {
      if (event.getButton() == 0 && event.isButtonstate()) {
         Minecraft mc = Minecraft.func_71410_x();
         EntityPlayer p = mc.field_71439_g;
         if (p != null) {
            Item item = p.func_184614_ca().func_77973_b();
            if (item instanceof WeaponToolMeleeBase) {
               if (p.func_184811_cZ().func_185141_a(item)) {
                  event.setCanceled(true);
               }
            }
         }
      }
   }

   private RayTraceResult getMouseOverExtended(float dist) {
      Minecraft mc = FMLClientHandler.instance().getClient();
      Entity theRenderViewEntity = mc.func_175606_aa();

      assert theRenderViewEntity != null;

      AxisAlignedBB theViewBoundingBox = new AxisAlignedBB(
         theRenderViewEntity.field_70165_t - 0.5,
         theRenderViewEntity.field_70163_u - 0.0,
         theRenderViewEntity.field_70161_v - 0.5,
         theRenderViewEntity.field_70165_t + 0.5,
         theRenderViewEntity.field_70163_u + 1.5,
         theRenderViewEntity.field_70161_v + 0.5
      );
      RayTraceResult returnMOP = null;
      if (mc.field_71441_e != null) {
         double var2 = dist;
         returnMOP = theRenderViewEntity.func_174822_a(var2, 0.0F);
         double calcdist = var2;
         Vec3d pos = theRenderViewEntity.func_174824_e(0.0F);
         if (returnMOP != null) {
            calcdist = returnMOP.field_72307_f.func_72438_d(pos);
         }

         Vec3d lookvec = theRenderViewEntity.func_70676_i(0.0F);
         Vec3d var8 = pos.func_72441_c(lookvec.field_72450_a * var2, lookvec.field_72448_b * var2, lookvec.field_72449_c * var2);
         Entity pointedEntity = null;
         float var9 = 1.0F;
         List<Entity> list = mc.field_71441_e
            .func_72839_b(
               theRenderViewEntity,
               theViewBoundingBox.func_72321_a(lookvec.field_72450_a * var2, lookvec.field_72448_b * var2, lookvec.field_72449_c * var2)
                  .func_72314_b(var9, var9, var9)
            );
         double d = calcdist;

         for (Entity entity : list) {
            if (entity.func_70067_L()) {
               float bordersize = entity.func_70111_Y();
               AxisAlignedBB aabb = new AxisAlignedBB(
                  entity.field_70165_t - entity.field_70130_N / 2.0F,
                  entity.field_70163_u,
                  entity.field_70161_v - entity.field_70130_N / 2.0F,
                  entity.field_70165_t + entity.field_70130_N / 2.0F,
                  entity.field_70163_u + entity.field_70131_O,
                  entity.field_70161_v + entity.field_70130_N / 2.0F
               );
               aabb.func_72321_a(bordersize, bordersize, bordersize);
               RayTraceResult mop0 = aabb.func_72327_a(pos, var8);
               if (aabb.func_72318_a(pos)) {
                  if (0.0 < d || d == 0.0) {
                     pointedEntity = entity;
                     d = 0.0;
                  }
               } else if (mop0 != null) {
                  double d1 = pos.func_72438_d(mop0.field_72307_f);
                  if (d1 < d || d == 0.0) {
                     pointedEntity = entity;
                     d = d1;
                  }
               }
            }
         }

         if (pointedEntity != null && (d < calcdist || returnMOP == null)) {
            returnMOP = new RayTraceResult(pointedEntity);
         }
      }

      return returnMOP;
   }

   public static class FogProperties {
      public float density;
      public float red;
      public float green;
      public float blue = 0.0F;

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
