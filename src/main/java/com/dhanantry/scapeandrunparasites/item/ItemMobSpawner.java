package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.client.IModelSRP;
import com.dhanantry.scapeandrunparasites.entity.monster.abomination.EntityAboBodies;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityBanoAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityCanraAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityEmanaAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityGimAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityHullAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityIkiAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityLumAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityNoglaAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityRanracAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.adapted.EntityShycoAdapted;
import com.dhanantry.scapeandrunparasites.entity.monster.ancient.EntityOronco;
import com.dhanantry.scapeandrunparasites.entity.monster.ancient.EntityTerla;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityCruxA;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityCruxB;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityDone;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityHeed;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityHost;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityHostII;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityInhooM;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityInhooS;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityLeer;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityLesh;
import com.dhanantry.scapeandrunparasites.entity.monster.crude.EntityMes;
import com.dhanantry.scapeandrunparasites.entity.monster.derived.EntityHeblu;
import com.dhanantry.scapeandrunparasites.entity.monster.derived.EntityKirin;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityNak;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityTonro;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.EntityUnvo;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityDod;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityDodSII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityDodSIII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityDodSIV;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityLeem;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityLeemSII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityLeemSIII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityLeemSIV;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityVenkrol;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityVenkrolSII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityVenkrolSIII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityVenkrolSIV;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityVenkrolSV;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerBear;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerCow;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerEnderman;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerHorse;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerHuman;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerPig;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerSheep;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerVillager;
import com.dhanantry.scapeandrunparasites.entity.monster.feral.EntityFerWolf;
import com.dhanantry.scapeandrunparasites.entity.monster.hijacked.EntityHiBlaze;
import com.dhanantry.scapeandrunparasites.entity.monster.hijacked.EntityHiGolem;
import com.dhanantry.scapeandrunparasites.entity.monster.hijacked.EntityHiSkeleton;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityAta;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityButhol;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityGothol;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityLodo;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityMudo;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityNuuh;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityRathol;
import com.dhanantry.scapeandrunparasites.entity.monster.inborn.EntityViin;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityDorpa;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfBear;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfCow;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfDragonE;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfEnderman;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfHorse;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfHuman;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfPig;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfPlayer;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfSheep;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfSquid;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfVillager;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.EntityInfWolf;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfCowHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfDragonEHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfEndermanHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfHorseHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfHumanHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfPigHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfPlayerHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfSheepHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfVillagerHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.head.EntityInfWolfHead;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.special.EntitySpeBear;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.special.EntitySpeCow;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.special.EntitySpeEnderman;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.special.EntitySpeHuman;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.special.EntitySpeSheep;
import com.dhanantry.scapeandrunparasites.entity.monster.infected.special.EntitySpeVillager;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityBano;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityCanra;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityEmana;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityGim;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityHull;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityIki;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityLum;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityNogla;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityRanrac;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityShyco;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityWymo;
import com.dhanantry.scapeandrunparasites.entity.monster.primitive.EntityZaa;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityAlafha;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityAnged;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityEsor;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityFlog;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityGanro;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityOmboo;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.EntityOrch;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityElvia;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityJinjo;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityLencia;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityPheon;
import com.dhanantry.scapeandrunparasites.entity.monster.pure.preeminent.EntityVesta;
import com.dhanantry.scapeandrunparasites.entity.projectile.EntityDropPod;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigMobs;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMobSpawner extends ItemBase implements IModelSRP {
   private String name;

   public ItemMobSpawner(String name) {
      super("itemmobspawner_" + name, 64, (byte)0);
      this.name = name;
      SRPItems.SRP_ITEMS.add(this);
   }

   public EnumActionResult func_180614_a(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      ItemStack itemstack = player.func_184586_b(hand);
      if (worldIn.field_72995_K) {
         return EnumActionResult.SUCCESS;
      } else if (!player.func_175151_a(pos.func_177972_a(facing), facing, itemstack)) {
         return EnumActionResult.FAIL;
      } else {
         IBlockState iblockstate = worldIn.func_180495_p(pos);
         Block block = iblockstate.func_177230_c();
         if (block == Blocks.field_150474_ac) {
            TileEntity blockpos = worldIn.func_175625_s(pos);
         }

         BlockPos blockpos = pos.func_177972_a(facing);
         double d0 = this.getYOffset(worldIn, blockpos);
         Entity entity = this.spawnEntity(worldIn, blockpos.func_177958_n() + 0.5, blockpos.func_177956_o() + d0, blockpos.func_177952_p() + 0.5, player);
         if (entity != null) {
            if (entity instanceof EntityLivingBase && itemstack.func_82837_s()) {
               entity.func_96094_a(itemstack.func_82833_r());
            }

            applyItemEntityDataToEntity(worldIn, player, itemstack, entity);
            if (!player.field_71075_bZ.field_75098_d) {
               itemstack.func_190918_g(1);
            }
         }

         return EnumActionResult.SUCCESS;
      }
   }

   protected double getYOffset(World p_190909_1_, BlockPos p_190909_2_) {
      AxisAlignedBB axisalignedbb = new AxisAlignedBB(p_190909_2_).func_72321_a(0.0, -1.0, 0.0);
      List<AxisAlignedBB> list = p_190909_1_.func_184144_a((Entity)null, axisalignedbb);
      if (list.isEmpty()) {
         return 0.0;
      } else {
         double d0 = axisalignedbb.field_72338_b;

         for (AxisAlignedBB axisalignedbb1 : list) {
            d0 = Math.max(axisalignedbb1.field_72337_e, d0);
         }

         return d0 - p_190909_2_.func_177956_o();
      }
   }

   public static void applyItemEntityDataToEntity(World entityWorld, @Nullable EntityPlayer player, ItemStack stack, @Nullable Entity targetEntity) {
      MinecraftServer minecraftserver = entityWorld.func_73046_m();
      if (minecraftserver != null && targetEntity != null) {
         NBTTagCompound nbttagcompound = stack.func_77978_p();
         if (nbttagcompound != null && nbttagcompound.func_150297_b("EntityTag", 10)) {
            if (!entityWorld.field_72995_K
               && targetEntity.func_184213_bq()
               && (player == null || !minecraftserver.func_184103_al().func_152596_g(player.func_146103_bH()))) {
               return;
            }

            NBTTagCompound nbttagcompound1 = targetEntity.func_189511_e(new NBTTagCompound());
            UUID uuid = targetEntity.func_110124_au();
            nbttagcompound1.func_179237_a(nbttagcompound.func_74775_l("EntityTag"));
            targetEntity.func_184221_a(uuid);
            targetEntity.func_70020_e(nbttagcompound1);
         }
      }
   }

   public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
      ItemStack itemstack = playerIn.func_184586_b(handIn);
      if (worldIn.field_72995_K) {
         return new ActionResult(EnumActionResult.PASS, itemstack);
      } else {
         RayTraceResult raytraceresult = this.func_77621_a(worldIn, playerIn, true);
         if (raytraceresult != null && raytraceresult.field_72313_a == Type.BLOCK) {
            BlockPos blockpos = raytraceresult.func_178782_a();
            if (!(worldIn.func_180495_p(blockpos).func_177230_c() instanceof BlockLiquid)) {
               return new ActionResult(EnumActionResult.PASS, itemstack);
            } else if (worldIn.func_175660_a(playerIn, blockpos) && playerIn.func_175151_a(blockpos, raytraceresult.field_178784_b, itemstack)) {
               Entity entity = this.spawnEntity(
                  worldIn, blockpos.func_177958_n() + 0.5, blockpos.func_177956_o() + 0.5, blockpos.func_177952_p() + 0.5, playerIn
               );
               if (entity == null) {
                  return new ActionResult(EnumActionResult.PASS, itemstack);
               } else {
                  if (entity instanceof EntityLivingBase && itemstack.func_82837_s()) {
                     entity.func_96094_a(itemstack.func_82833_r());
                  }

                  applyItemEntityDataToEntity(worldIn, playerIn, itemstack, entity);
                  if (!playerIn.field_71075_bZ.field_75098_d) {
                     itemstack.func_190918_g(1);
                  }

                  playerIn.func_71029_a(StatList.func_188057_b(this));
                  return new ActionResult(EnumActionResult.SUCCESS, itemstack);
               }
            } else {
               return new ActionResult(EnumActionResult.FAIL, itemstack);
            }
         } else {
            return new ActionResult(EnumActionResult.PASS, itemstack);
         }
      }
   }

   private Entity spawnEntity(World worldIn, double x, double y, double z, EntityPlayer playerIn) {
      EntityLiving entity = new EntityZombie(worldIn);
      if (!SRPConfig.allowMobs) {
         return entity;
      } else {
         if (this.name.equals("pod")) {
            entity = new EntityDropPod(worldIn);
            y += 25.0;
         } else {
            if (this.name.equals("mes") && SRPConfigMobs.cruxaEnabled) {
               return this.spawnPlayer2(worldIn, x, y, z, playerIn);
            }

            if (this.name.equals("cruxa") && SRPConfigMobs.cruxaEnabled) {
               entity = new EntityCruxA(worldIn);
            } else if (this.name.equals("cruxb") && SRPConfigMobs.cruxaEnabled) {
               entity = new EntityCruxB(worldIn);
            } else if (this.name.equals("heed") && SRPConfigMobs.heedEnabled) {
               entity = new EntityHeed(worldIn);
            } else if (this.name.equals("done") && SRPConfigMobs.heedEnabled) {
               entity = new EntityDone(worldIn);
            } else if (this.name.equals("jinjo") && SRPConfigMobs.jinjoEnabled) {
               entity = new EntityJinjo(worldIn);
            } else if (this.name.equals("elvia") && SRPConfigMobs.jinjoEnabled) {
               entity = new EntityElvia(worldIn);
            } else if (this.name.equals("pheon") && SRPConfigMobs.jinjoEnabled) {
               entity = new EntityPheon(worldIn);
            } else if (this.name.equals("lencia") && SRPConfigMobs.jinjoEnabled) {
               entity = new EntityLencia(worldIn);
            } else if (this.name.equals("vesta") && SRPConfigMobs.jinjoEnabled) {
               entity = new EntityVesta(worldIn);
            } else if (this.name.equals("rathol") && SRPConfigMobs.ratholEnabled) {
               entity = new EntityRathol(worldIn);
            } else if (this.name.equals("gothol") && SRPConfigMobs.ratholEnabled) {
               entity = new EntityGothol(worldIn);
            } else if (this.name.equals("lodo") && SRPConfigMobs.lodoEnabled) {
               entity = new EntityLodo(worldIn);
            } else if (this.name.equals("mudo") && SRPConfigMobs.mudoEnabled) {
               entity = new EntityMudo(worldIn);
            } else if (this.name.equals("nuuh") && SRPConfigMobs.mudoEnabled) {
               entity = new EntityNuuh(worldIn);
            } else if (this.name.equals("ata")) {
               entity = new EntityAta(worldIn);
            } else if (this.name.equals("viin")) {
               entity = new EntityViin(worldIn);
            } else if (this.name.equals("venkrol") && SRPConfigSystems.rsEnabled) {
               entity = new EntityVenkrol(worldIn);
            } else if (this.name.equals("venkrolsii") && SRPConfigSystems.rsEnabled) {
               entity = new EntityVenkrolSII(worldIn);
            } else if (this.name.equals("venkrolsiii") && SRPConfigSystems.rsEnabled) {
               entity = new EntityVenkrolSIII(worldIn);
            } else if (this.name.equals("venkrolsiv") && SRPConfigSystems.rsEnabled) {
               entity = new EntityVenkrolSIV(worldIn);
            } else if (this.name.equals("venkrolsv") && SRPConfigSystems.rsEnabled) {
               entity = new EntityVenkrolSV(worldIn);
            } else if (this.name.equals("nak") && SRPConfigSystems.rsEnabled) {
               entity = new EntityNak(worldIn);
            } else if (this.name.equals("leem") && SRPConfigSystems.rsEnabled) {
               entity = new EntityLeem(worldIn);
            } else if (this.name.equals("leemsii") && SRPConfigSystems.rsEnabled) {
               entity = new EntityLeemSII(worldIn);
            } else if (this.name.equals("leemsiii") && SRPConfigSystems.rsEnabled) {
               entity = new EntityLeemSIII(worldIn);
            } else if (this.name.equals("leemsiv") && SRPConfigSystems.rsEnabled) {
               entity = new EntityLeemSIV(worldIn);
            } else if (this.name.equals("dod") && SRPConfigSystems.rsEnabled) {
               entity = new EntityDod(worldIn);
            } else if (this.name.equals("dodsii") && SRPConfigSystems.rsEnabled) {
               entity = new EntityDodSII(worldIn);
            } else if (this.name.equals("dodsiii") && SRPConfigSystems.rsEnabled) {
               entity = new EntityDodSIII(worldIn);
            } else if (this.name.equals("dodsiv") && SRPConfigSystems.rsEnabled) {
               entity = new EntityDodSIV(worldIn);
            } else if (this.name.equals("dorpa") && SRPConfigMobs.dorpaEnabled) {
               entity = new EntityDorpa(worldIn);
            } else if (this.name.equals("infhuman") && SRPConfigMobs.infhumanEnabled) {
               entity = new EntityInfHuman(worldIn);
            } else if (this.name.equals("infhumanhead") && SRPConfigMobs.infhumanEnabled) {
               entity = new EntityInfHumanHead(worldIn);
            } else if (this.name.equals("infcow") && SRPConfigMobs.infcowEnabled) {
               entity = new EntityInfCow(worldIn);
            } else if (this.name.equals("infcowhead") && SRPConfigMobs.infcowEnabled) {
               entity = new EntityInfCowHead(worldIn);
            } else if (this.name.equals("infsheep") && SRPConfigMobs.infsheepEnabled) {
               entity = new EntityInfSheep(worldIn);
            } else if (this.name.equals("infsquid") && SRPConfigMobs.infsquidEnabled) {
               entity = new EntityInfSquid(worldIn);
            } else if (this.name.equals("infsheephead") && SRPConfigMobs.infsheepEnabled) {
               entity = new EntityInfSheepHead(worldIn);
            } else if (this.name.equals("infwolf") && SRPConfigMobs.infwolfEnabled) {
               entity = new EntityInfWolf(worldIn);
            } else if (this.name.equals("infwolfhead") && SRPConfigMobs.infwolfEnabled) {
               entity = new EntityInfWolfHead(worldIn);
            } else if (this.name.equals("infpig") && SRPConfigMobs.infpigEnabled) {
               entity = new EntityInfPig(worldIn);
            } else if (this.name.equals("infpighead") && SRPConfigMobs.infpigEnabled) {
               entity = new EntityInfPigHead(worldIn);
            } else if (this.name.equals("infvillager") && SRPConfigMobs.infvillagerEnabled) {
               entity = new EntityInfVillager(worldIn);
            } else if (this.name.equals("infvillagerhead") && SRPConfigMobs.infvillagerEnabled) {
               entity = new EntityInfVillagerHead(worldIn);
            } else {
               if (this.name.equals("infplayer") && SRPConfigMobs.infadventurerEnabled) {
                  return this.spawnPlayer(worldIn, x, y, z, playerIn);
               }

               if (this.name.equals("infplayerhead") && SRPConfigMobs.infadventurerEnabled) {
                  return this.spawnPlayerHead(worldIn, x, y, z, playerIn);
               }

               if (this.name.equals("inhoos")) {
                  entity = new EntityInhooS(worldIn);
               } else if (this.name.equals("inhoom")) {
                  entity = new EntityInhooM(worldIn);
               } else if (this.name.equals("infhorse") && SRPConfigMobs.infhorseEnabled) {
                  entity = new EntityInfHorse(worldIn);
               } else if (this.name.equals("infhorsehead") && SRPConfigMobs.infhorseEnabled) {
                  entity = new EntityInfHorseHead(worldIn);
               } else if (this.name.equals("infenderman") && SRPConfigMobs.infendermanEnabled) {
                  entity = new EntityInfEnderman(worldIn);
               } else if (this.name.equals("infendermanhead") && SRPConfigSystems.rsEnabled) {
                  entity = new EntityInfEndermanHead(worldIn);
               } else if (this.name.equals("infbear") && SRPConfigMobs.infbearEnabled) {
                  entity = new EntityInfBear(worldIn);
               } else if (this.name.equals("infdragone")) {
                  entity = new EntityInfDragonE(worldIn);
               } else if (this.name.equals("infdragonehead") && SRPConfigSystems.rsEnabled) {
                  entity = new EntityInfDragonEHead(worldIn);
               } else if (this.name.equals("host") && SRPConfigMobs.hostEnabled) {
                  entity = new EntityHost(worldIn);
               } else if (this.name.equals("hostii") && SRPConfigMobs.hostEnabled) {
                  entity = new EntityHostII(worldIn);
               } else if (this.name.equals("hull") && SRPConfigMobs.hullEnabled) {
                  entity = new EntityHull(worldIn);
               } else if (this.name.equals("hulladapted") && SRPConfigMobs.hullEnabled) {
                  entity = new EntityHullAdapted(worldIn);
               } else if (this.name.equals("canra") && SRPConfigMobs.canraEnabled) {
                  entity = new EntityCanra(worldIn);
               } else if (this.name.equals("canraadapted") && SRPConfigMobs.canraEnabled) {
                  entity = new EntityCanraAdapted(worldIn);
               } else if (this.name.equals("nogla") && SRPConfigMobs.noglaEnabled) {
                  entity = new EntityNogla(worldIn);
               } else if (this.name.equals("noglaadapted") && SRPConfigMobs.noglaEnabled) {
                  entity = new EntityNoglaAdapted(worldIn);
               } else if (this.name.equals("gim") && SRPConfigMobs.gimEnabled) {
                  entity = new EntityGim(worldIn);
               } else if (this.name.equals("gimadapted") && SRPConfigMobs.gimEnabled) {
                  entity = new EntityGimAdapted(worldIn);
               } else if (this.name.equals("zaa") && SRPConfigMobs.zaaEnabled) {
                  entity = new EntityZaa(worldIn);
               } else if (this.name.equals("bano") && SRPConfigMobs.zetmoEnabled) {
                  entity = new EntityBano(worldIn);
               } else if (this.name.equals("banoadapted") && SRPConfigMobs.zetmoEnabled) {
                  entity = new EntityBanoAdapted(worldIn);
               } else if (this.name.equals("ranrac") && SRPConfigMobs.arachnidaEnabled) {
                  entity = new EntityRanrac(worldIn);
               } else if (this.name.equals("ranracadapted") && SRPConfigMobs.arachnidaEnabled) {
                  entity = new EntityRanracAdapted(worldIn);
               } else if (this.name.equals("lum") && SRPConfigMobs.lumEnabled) {
                  entity = new EntityLum(worldIn);
               } else if (this.name.equals("lumadapted") && SRPConfigMobs.lumEnabled) {
                  entity = new EntityLumAdapted(worldIn);
               } else if (this.name.equals("shyco") && SRPConfigMobs.shycoEnabled) {
                  entity = new EntityShyco(worldIn);
               } else if (this.name.equals("shycoadapted") && SRPConfigMobs.shycoEnabled) {
                  entity = new EntityShycoAdapted(worldIn);
               } else if (this.name.equals("emana") && SRPConfigMobs.emanaEnabled) {
                  entity = new EntityEmana(worldIn);
               } else if (this.name.equals("emanaadapted") && SRPConfigMobs.emanaEnabled) {
                  entity = new EntityEmanaAdapted(worldIn);
               } else if (this.name.equals("iki")) {
                  entity = new EntityIki(worldIn);
               } else if (this.name.equals("ikiadapted")) {
                  entity = new EntityIkiAdapted(worldIn);
               } else if (this.name.equals("wymo")) {
                  entity = new EntityWymo(worldIn);
               } else if (this.name.equals("buthol") && SRPConfigMobs.butholEnabled) {
                  entity = new EntityButhol(worldIn);
               } else if (this.name.equals("alafha") && SRPConfigMobs.alafhaEnabled) {
                  entity = new EntityAlafha(worldIn);
               } else if (this.name.equals("oronco") && SRPConfigMobs.oroncoEnabled) {
                  entity = new EntityOronco(worldIn);
               } else if (this.name.equals("terla") && SRPConfigMobs.terlaEnabled) {
                  entity = new EntityTerla(worldIn);
               } else if (this.name.equals("anged") && SRPConfigMobs.angedEnabled) {
                  entity = new EntityAnged(worldIn);
               } else if (this.name.equals("lesh")) {
                  entity = new EntityLesh(worldIn);
               } else if (this.name.equals("leer")) {
                  entity = new EntityLeer(worldIn);
               } else if (this.name.equals("tonro") && SRPConfigMobs.tonroEnabled) {
                  entity = new EntityTonro(worldIn);
               } else if (this.name.equals("unvo") && SRPConfigMobs.unvoEnabled) {
                  entity = new EntityUnvo(worldIn);
               } else if (this.name.equals("ganro") && SRPConfigMobs.ganroEnabled) {
                  entity = new EntityGanro(worldIn);
               } else if (this.name.equals("omboo") && SRPConfigMobs.ombooEnabled) {
                  entity = new EntityOmboo(worldIn);
               } else if (this.name.equals("esor") && SRPConfigMobs.esorEnabled) {
                  entity = new EntityEsor(worldIn);
               } else if (this.name.equals("orch") && SRPConfigMobs.esorEnabled) {
                  entity = new EntityOrch(worldIn);
               } else if (this.name.equals("flog") && SRPConfigMobs.flogEnabled) {
                  entity = new EntityFlog(worldIn);
               } else if (this.name.equals("ferbear") && SRPConfigMobs.ferbearEnabled) {
                  entity = new EntityFerBear(worldIn);
               } else if (this.name.equals("fercow") && SRPConfigMobs.fercowEnabled) {
                  entity = new EntityFerCow(worldIn);
               } else if (this.name.equals("ferenderman") && SRPConfigMobs.ferendermanEnabled) {
                  entity = new EntityFerEnderman(worldIn);
               } else if (this.name.equals("ferhuman") && SRPConfigMobs.ferhumanEnabled) {
                  entity = new EntityFerHuman(worldIn);
               } else if (this.name.equals("ferhorse") && SRPConfigMobs.ferhorseEnabled) {
                  entity = new EntityFerHorse(worldIn);
               } else if (this.name.equals("fersheep") && SRPConfigMobs.fersheepEnabled) {
                  entity = new EntityFerSheep(worldIn);
               } else if (this.name.equals("ferpig") && SRPConfigMobs.ferpigEnabled) {
                  entity = new EntityFerPig(worldIn);
               } else if (this.name.equals("ferwolf")) {
                  entity = new EntityFerWolf(worldIn);
               } else if (this.name.equals("fervillager") && SRPConfigMobs.fervillagerEnabled) {
                  entity = new EntityFerVillager(worldIn);
               } else if (this.name.equals("marcow")) {
                  entity = new EntitySpeCow(worldIn);
               } else if (this.name.equals("marenderman")) {
                  entity = new EntitySpeEnderman(worldIn);
               } else if (this.name.equals("marvillager")) {
                  entity = new EntitySpeVillager(worldIn);
               } else if (this.name.equals("marhuman")) {
                  entity = new EntitySpeHuman(worldIn);
               } else if (this.name.equals("marsheep")) {
                  entity = new EntitySpeSheep(worldIn);
               } else if (this.name.equals("marbear")) {
                  entity = new EntitySpeBear(worldIn);
               } else if (this.name.equals("abobodies")) {
                  entity = new EntityAboBodies(worldIn);
               } else if (this.name.equals("higolem") && SRPConfigMobs.higolemEnabled) {
                  entity = new EntityHiGolem(worldIn);
               } else if (this.name.equals("hiblaze")) {
                  entity = new EntityHiBlaze(worldIn);
               } else if (this.name.equals("hiskeleton")) {
                  entity = new EntityHiSkeleton(worldIn);
               } else if (this.name.equals("kirin")) {
                  entity = new EntityKirin(worldIn);
               } else if (this.name.equals("heblu") && SRPConfigMobs.hebluEnabled) {
                  entity = new EntityHeblu(worldIn);
               }
            }
         }

         entity.func_70012_b(x, y, z, MathHelper.func_76142_g(worldIn.field_73012_v.nextFloat() * 360.0F), 0.0F);
         entity.field_70759_as = entity.field_70177_z;
         entity.field_70761_aq = entity.field_70177_z;
         entity.func_180482_a(worldIn.func_175649_E(new BlockPos(entity)), (IEntityLivingData)null);
         worldIn.func_72838_d(entity);
         return entity;
      }
   }

   private Entity spawnPlayer(World worldIn, double x, double y, double z, EntityPlayer playerIn) {
      EntityInfPlayer entity = new EntityInfPlayer(worldIn);
      ItemStack head = new ItemStack(playerIn.func_184582_a(EntityEquipmentSlot.HEAD).func_77973_b());
      ItemStack legs = new ItemStack(playerIn.func_184582_a(EntityEquipmentSlot.LEGS).func_77973_b());
      ItemStack feet = new ItemStack(playerIn.func_184582_a(EntityEquipmentSlot.FEET).func_77973_b());
      if (head.func_77973_b() != Items.field_190931_a) {
         entity.func_184201_a(EntityEquipmentSlot.HEAD, head);
         entity.setHelmetSlot(true);
      }

      entity.func_184201_a(EntityEquipmentSlot.LEGS, legs);
      entity.func_184201_a(EntityEquipmentSlot.FEET, feet);
      entity.func_70012_b(x, y, z, MathHelper.func_76142_g(worldIn.field_73012_v.nextFloat() * 360.0F), 0.0F);
      entity.field_70759_as = entity.field_70177_z;
      entity.field_70761_aq = entity.field_70177_z;
      entity.func_180482_a(worldIn.func_175649_E(new BlockPos(entity)), (IEntityLivingData)null);
      entity.func_96094_a(playerIn.func_70005_c_());
      entity.func_174805_g(true);
      worldIn.func_72838_d(entity);
      return entity;
   }

   private Entity spawnPlayer2(World worldIn, double x, double y, double z, EntityPlayer playerIn) {
      EntityMes entity = new EntityMes(worldIn);
      entity.func_70012_b(x, y, z, MathHelper.func_76142_g(worldIn.field_73012_v.nextFloat() * 360.0F), 0.0F);
      entity.field_70759_as = entity.field_70177_z;
      entity.field_70761_aq = entity.field_70177_z;
      entity.func_180482_a(worldIn.func_175649_E(new BlockPos(entity)), (IEntityLivingData)null);
      entity.func_96094_a(playerIn.func_70005_c_());
      entity.func_174805_g(true);
      worldIn.func_72838_d(entity);
      return entity;
   }

   private Entity spawnPlayerHead(World worldIn, double x, double y, double z, EntityPlayer playerIn) {
      EntityInfPlayerHead entity = new EntityInfPlayerHead(worldIn);
      entity.func_70012_b(x, y, z, MathHelper.func_76142_g(worldIn.field_73012_v.nextFloat() * 360.0F), 0.0F);
      entity.field_70759_as = entity.field_70177_z;
      entity.field_70761_aq = entity.field_70177_z;
      entity.func_180482_a(worldIn.func_175649_E(new BlockPos(entity)), (IEntityLivingData)null);
      entity.func_96094_a(playerIn.func_70005_c_());
      entity.func_174805_g(true);
      worldIn.func_72838_d(entity);
      return entity;
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
   }

   @Override
   public void registerModels() {
      SRPMain.proxy.modelReg(this, 0, "inventory");
   }
}
