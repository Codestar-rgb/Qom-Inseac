/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.util.ITooltipFlag
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.stats.StatList
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ActionResult
 *  net.minecraft.util.EnumActionResult
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.item;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.client.IModelSRP;
import com.subspaceparasite.entity.monster.abomination.EntityAboBodies;
import com.subspaceparasite.entity.monster.adapted.EntityBanoAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityCanraAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityEmanaAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityGimAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityHullAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityIkiAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityLumAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityNoglaAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityRanracAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityShycoAdapted;
import com.subspaceparasite.entity.monster.ancient.EntityOronco;
import com.subspaceparasite.entity.monster.ancient.EntityTerla;
import com.subspaceparasite.entity.monster.crude.EntityCruxA;
import com.subspaceparasite.entity.monster.crude.EntityCruxB;
import com.subspaceparasite.entity.monster.crude.EntityDone;
import com.subspaceparasite.entity.monster.crude.EntityHeed;
import com.subspaceparasite.entity.monster.crude.EntityHost;
import com.subspaceparasite.entity.monster.crude.EntityHostII;
import com.subspaceparasite.entity.monster.crude.EntityInhooM;
import com.subspaceparasite.entity.monster.crude.EntityInhooS;
import com.subspaceparasite.entity.monster.crude.EntityLeer;
import com.subspaceparasite.entity.monster.crude.EntityLesh;
import com.subspaceparasite.entity.monster.crude.EntityMes;
import com.subspaceparasite.entity.monster.derived.EntityHeblu;
import com.subspaceparasite.entity.monster.derived.EntityKirin;
import com.subspaceparasite.entity.monster.deterrent.EntityNak;
import com.subspaceparasite.entity.monster.deterrent.EntityTonro;
import com.subspaceparasite.entity.monster.deterrent.EntityUnvo;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityDod;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityDodSII;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityDodSIII;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityDodSIV;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityLeem;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityLeemSII;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityLeemSIII;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityLeemSIV;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityVenkrol;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityVenkrolSII;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityVenkrolSIII;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityVenkrolSIV;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityVenkrolSV;
import com.subspaceparasite.entity.monster.feral.EntityFerBear;
import com.subspaceparasite.entity.monster.feral.EntityFerCow;
import com.subspaceparasite.entity.monster.feral.EntityFerEnderman;
import com.subspaceparasite.entity.monster.feral.EntityFerHorse;
import com.subspaceparasite.entity.monster.feral.EntityFerHuman;
import com.subspaceparasite.entity.monster.feral.EntityFerPig;
import com.subspaceparasite.entity.monster.feral.EntityFerSheep;
import com.subspaceparasite.entity.monster.feral.EntityFerVillager;
import com.subspaceparasite.entity.monster.feral.EntityFerWolf;
import com.subspaceparasite.entity.monster.hijacked.EntityHiBlaze;
import com.subspaceparasite.entity.monster.hijacked.EntityHiGolem;
import com.subspaceparasite.entity.monster.hijacked.EntityHiSkeleton;
import com.subspaceparasite.entity.monster.inborn.EntityAta;
import com.subspaceparasite.entity.monster.inborn.EntityButhol;
import com.subspaceparasite.entity.monster.inborn.EntityGothol;
import com.subspaceparasite.entity.monster.inborn.EntityLodo;
import com.subspaceparasite.entity.monster.inborn.EntityMudo;
import com.subspaceparasite.entity.monster.inborn.EntityNuuh;
import com.subspaceparasite.entity.monster.inborn.EntityRathol;
import com.subspaceparasite.entity.monster.inborn.EntityViin;
import com.subspaceparasite.entity.monster.infected.EntityDorpa;
import com.subspaceparasite.entity.monster.infected.EntityInfBear;
import com.subspaceparasite.entity.monster.infected.EntityInfCow;
import com.subspaceparasite.entity.monster.infected.EntityInfDragonE;
import com.subspaceparasite.entity.monster.infected.EntityInfEnderman;
import com.subspaceparasite.entity.monster.infected.EntityInfHorse;
import com.subspaceparasite.entity.monster.infected.EntityInfHuman;
import com.subspaceparasite.entity.monster.infected.EntityInfPig;
import com.subspaceparasite.entity.monster.infected.EntityInfPlayer;
import com.subspaceparasite.entity.monster.infected.EntityInfSheep;
import com.subspaceparasite.entity.monster.infected.EntityInfSquid;
import com.subspaceparasite.entity.monster.infected.EntityInfVillager;
import com.subspaceparasite.entity.monster.infected.EntityInfWolf;
import com.subspaceparasite.entity.monster.infected.head.EntityInfCowHead;
import com.subspaceparasite.entity.monster.infected.head.EntityInfDragonEHead;
import com.subspaceparasite.entity.monster.infected.head.EntityInfEndermanHead;
import com.subspaceparasite.entity.monster.infected.head.EntityInfHorseHead;
import com.subspaceparasite.entity.monster.infected.head.EntityInfHumanHead;
import com.subspaceparasite.entity.monster.infected.head.EntityInfPigHead;
import com.subspaceparasite.entity.monster.infected.head.EntityInfPlayerHead;
import com.subspaceparasite.entity.monster.infected.head.EntityInfSheepHead;
import com.subspaceparasite.entity.monster.infected.head.EntityInfVillagerHead;
import com.subspaceparasite.entity.monster.infected.head.EntityInfWolfHead;
import com.subspaceparasite.entity.monster.infected.special.EntitySpeBear;
import com.subspaceparasite.entity.monster.infected.special.EntitySpeCow;
import com.subspaceparasite.entity.monster.infected.special.EntitySpeEnderman;
import com.subspaceparasite.entity.monster.infected.special.EntitySpeHuman;
import com.subspaceparasite.entity.monster.infected.special.EntitySpeSheep;
import com.subspaceparasite.entity.monster.infected.special.EntitySpeVillager;
import com.subspaceparasite.entity.monster.primitive.EntityBano;
import com.subspaceparasite.entity.monster.primitive.EntityCanra;
import com.subspaceparasite.entity.monster.primitive.EntityEmana;
import com.subspaceparasite.entity.monster.primitive.EntityGim;
import com.subspaceparasite.entity.monster.primitive.EntityHull;
import com.subspaceparasite.entity.monster.primitive.EntityIki;
import com.subspaceparasite.entity.monster.primitive.EntityLum;
import com.subspaceparasite.entity.monster.primitive.EntityNogla;
import com.subspaceparasite.entity.monster.primitive.EntityRanrac;
import com.subspaceparasite.entity.monster.primitive.EntityShyco;
import com.subspaceparasite.entity.monster.primitive.EntityWymo;
import com.subspaceparasite.entity.monster.primitive.EntityZaa;
import com.subspaceparasite.entity.monster.pure.EntityAlafha;
import com.subspaceparasite.entity.monster.pure.EntityAnged;
import com.subspaceparasite.entity.monster.pure.EntityEsor;
import com.subspaceparasite.entity.monster.pure.EntityFlog;
import com.subspaceparasite.entity.monster.pure.EntityGanro;
import com.subspaceparasite.entity.monster.pure.EntityOmboo;
import com.subspaceparasite.entity.monster.pure.EntityOrch;
import com.subspaceparasite.entity.monster.pure.preeminent.EntityElvia;
import com.subspaceparasite.entity.monster.pure.preeminent.EntityJinjo;
import com.subspaceparasite.entity.monster.pure.preeminent.EntityLencia;
import com.subspaceparasite.entity.monster.pure.preeminent.EntityPheon;
import com.subspaceparasite.entity.monster.pure.preeminent.EntityVesta;
import com.subspaceparasite.entity.projectile.EntityDropPod;
import com.subspaceparasite.init.SPItems;
import com.subspaceparasite.item.ItemBase;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigMobs;
import com.subspaceparasite.util.config.SPConfigSystems;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
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
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMobSpawner
extends ItemBase
implements IModelSRP {
    private String name;

    public ItemMobSpawner(String name) {
        super("itemmobspawner_" + name, 64, (byte)0);
        this.name = name;
        SPItems.SP_ITEMS.add(this);
    }

    public EnumActionResult func_180614_a(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack itemstack = player.func_184586_b(hand);
        if (worldIn.field_72995_K) {
            return EnumActionResult.SUCCESS;
        }
        if (!player.func_175151_a(pos.func_177972_a(facing), facing, itemstack)) {
            return EnumActionResult.FAIL;
        }
        IBlockState iblockstate = worldIn.func_180495_p(pos);
        Block block = iblockstate.func_177230_c();
        if (block == Blocks.field_150474_ac) {
            TileEntity tileEntity = worldIn.func_175625_s(pos);
        }
        BlockPos blockpos = pos.func_177972_a(facing);
        double d0 = this.getYOffset(worldIn, blockpos);
        Entity entity = this.spawnEntity(worldIn, (double)blockpos.func_177958_n() + 0.5, (double)blockpos.func_177956_o() + d0, (double)blockpos.func_177952_p() + 0.5, player);
        if (entity != null) {
            if (entity instanceof EntityLivingBase && itemstack.func_82837_s()) {
                entity.func_96094_a(itemstack.func_82833_r());
            }
            ItemMobSpawner.applyItemEntityDataToEntity(worldIn, player, itemstack, entity);
            if (!player.field_71075_bZ.field_75098_d) {
                itemstack.func_190918_g(1);
            }
        }
        return EnumActionResult.SUCCESS;
    }

    protected double getYOffset(World p_190909_1_, BlockPos p_190909_2_) {
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(p_190909_2_).func_72321_a(0.0, -1.0, 0.0);
        List list = p_190909_1_.func_184144_a((Entity)null, axisalignedbb);
        if (list.isEmpty()) {
            return 0.0;
        }
        double d0 = axisalignedbb.field_72338_b;
        for (AxisAlignedBB axisalignedbb1 : list) {
            d0 = Math.max(axisalignedbb1.field_72337_e, d0);
        }
        return d0 - (double)p_190909_2_.func_177956_o();
    }

    public static void applyItemEntityDataToEntity(World entityWorld, @Nullable EntityPlayer player, ItemStack stack, @Nullable Entity targetEntity) {
        NBTTagCompound nbttagcompound;
        MinecraftServer minecraftserver = entityWorld.func_73046_m();
        if (minecraftserver != null && targetEntity != null && (nbttagcompound = stack.func_77978_p()) != null && nbttagcompound.func_150297_b("EntityTag", 10)) {
            if (!(entityWorld.field_72995_K || !targetEntity.func_184213_bq() || player != null && minecraftserver.func_184103_al().func_152596_g(player.func_146103_bH()))) {
                return;
            }
            NBTTagCompound nbttagcompound1 = targetEntity.func_189511_e(new NBTTagCompound());
            UUID uuid = targetEntity.func_110124_au();
            nbttagcompound1.func_179237_a(nbttagcompound.func_74775_l("EntityTag"));
            targetEntity.func_184221_a(uuid);
            targetEntity.func_70020_e(nbttagcompound1);
        }
    }

    public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemstack = playerIn.func_184586_b(handIn);
        if (worldIn.field_72995_K) {
            return new ActionResult(EnumActionResult.PASS, (Object)itemstack);
        }
        RayTraceResult raytraceresult = this.func_77621_a(worldIn, playerIn, true);
        if (raytraceresult != null && raytraceresult.field_72313_a == RayTraceResult.Type.BLOCK) {
            BlockPos blockpos = raytraceresult.func_178782_a();
            if (!(worldIn.func_180495_p(blockpos).func_177230_c() instanceof BlockLiquid)) {
                return new ActionResult(EnumActionResult.PASS, (Object)itemstack);
            }
            if (worldIn.func_175660_a(playerIn, blockpos) && playerIn.func_175151_a(blockpos, raytraceresult.field_178784_b, itemstack)) {
                Entity entity = this.spawnEntity(worldIn, (double)blockpos.func_177958_n() + 0.5, (double)blockpos.func_177956_o() + 0.5, (double)blockpos.func_177952_p() + 0.5, playerIn);
                if (entity == null) {
                    return new ActionResult(EnumActionResult.PASS, (Object)itemstack);
                }
                if (entity instanceof EntityLivingBase && itemstack.func_82837_s()) {
                    entity.func_96094_a(itemstack.func_82833_r());
                }
                ItemMobSpawner.applyItemEntityDataToEntity(worldIn, playerIn, itemstack, entity);
                if (!playerIn.field_71075_bZ.field_75098_d) {
                    itemstack.func_190918_g(1);
                }
                playerIn.func_71029_a(StatList.func_188057_b((Item)this));
                return new ActionResult(EnumActionResult.SUCCESS, (Object)itemstack);
            }
            return new ActionResult(EnumActionResult.FAIL, (Object)itemstack);
        }
        return new ActionResult(EnumActionResult.PASS, (Object)itemstack);
    }

    private Entity spawnEntity(World worldIn, double x, double y, double z, EntityPlayer playerIn) {
        Object entity = new EntityZombie(worldIn);
        if (!SPConfig.allowMobs) {
            return entity;
        }
        if (this.name.equals("pod")) {
            entity = new EntityDropPod(worldIn);
            y += 25.0;
        } else {
            if (this.name.equals("mes") && SPConfigMobs.cruxaEnabled) {
                return this.spawnPlayer2(worldIn, x, y, z, playerIn);
            }
            if (this.name.equals("cruxa") && SPConfigMobs.cruxaEnabled) {
                entity = new EntityCruxA(worldIn);
            } else if (this.name.equals("cruxb") && SPConfigMobs.cruxaEnabled) {
                entity = new EntityCruxB(worldIn);
            } else if (this.name.equals("heed") && SPConfigMobs.heedEnabled) {
                entity = new EntityHeed(worldIn);
            } else if (this.name.equals("done") && SPConfigMobs.heedEnabled) {
                entity = new EntityDone(worldIn);
            } else if (this.name.equals("jinjo") && SPConfigMobs.jinjoEnabled) {
                entity = new EntityJinjo(worldIn);
            } else if (this.name.equals("elvia") && SPConfigMobs.jinjoEnabled) {
                entity = new EntityElvia(worldIn);
            } else if (this.name.equals("pheon") && SPConfigMobs.jinjoEnabled) {
                entity = new EntityPheon(worldIn);
            } else if (this.name.equals("lencia") && SPConfigMobs.jinjoEnabled) {
                entity = new EntityLencia(worldIn);
            } else if (this.name.equals("vesta") && SPConfigMobs.jinjoEnabled) {
                entity = new EntityVesta(worldIn);
            } else if (this.name.equals("rathol") && SPConfigMobs.ratholEnabled) {
                entity = new EntityRathol(worldIn);
            } else if (this.name.equals("gothol") && SPConfigMobs.ratholEnabled) {
                entity = new EntityGothol(worldIn);
            } else if (this.name.equals("lodo") && SPConfigMobs.lodoEnabled) {
                entity = new EntityLodo(worldIn);
            } else if (this.name.equals("mudo") && SPConfigMobs.mudoEnabled) {
                entity = new EntityMudo(worldIn);
            } else if (this.name.equals("nuuh") && SPConfigMobs.mudoEnabled) {
                entity = new EntityNuuh(worldIn);
            } else if (this.name.equals("ata")) {
                entity = new EntityAta(worldIn);
            } else if (this.name.equals("viin")) {
                entity = new EntityViin(worldIn);
            } else if (this.name.equals("venkrol") && SPConfigSystems.rsEnabled) {
                entity = new EntityVenkrol(worldIn);
            } else if (this.name.equals("venkrolsii") && SPConfigSystems.rsEnabled) {
                entity = new EntityVenkrolSII(worldIn);
            } else if (this.name.equals("venkrolsiii") && SPConfigSystems.rsEnabled) {
                entity = new EntityVenkrolSIII(worldIn);
            } else if (this.name.equals("venkrolsiv") && SPConfigSystems.rsEnabled) {
                entity = new EntityVenkrolSIV(worldIn);
            } else if (this.name.equals("venkrolsv") && SPConfigSystems.rsEnabled) {
                entity = new EntityVenkrolSV(worldIn);
            } else if (this.name.equals("nak") && SPConfigSystems.rsEnabled) {
                entity = new EntityNak(worldIn);
            } else if (this.name.equals("leem") && SPConfigSystems.rsEnabled) {
                entity = new EntityLeem(worldIn);
            } else if (this.name.equals("leemsii") && SPConfigSystems.rsEnabled) {
                entity = new EntityLeemSII(worldIn);
            } else if (this.name.equals("leemsiii") && SPConfigSystems.rsEnabled) {
                entity = new EntityLeemSIII(worldIn);
            } else if (this.name.equals("leemsiv") && SPConfigSystems.rsEnabled) {
                entity = new EntityLeemSIV(worldIn);
            } else if (this.name.equals("dod") && SPConfigSystems.rsEnabled) {
                entity = new EntityDod(worldIn);
            } else if (this.name.equals("dodsii") && SPConfigSystems.rsEnabled) {
                entity = new EntityDodSII(worldIn);
            } else if (this.name.equals("dodsiii") && SPConfigSystems.rsEnabled) {
                entity = new EntityDodSIII(worldIn);
            } else if (this.name.equals("dodsiv") && SPConfigSystems.rsEnabled) {
                entity = new EntityDodSIV(worldIn);
            } else if (this.name.equals("dorpa") && SPConfigMobs.dorpaEnabled) {
                entity = new EntityDorpa(worldIn);
            } else if (this.name.equals("infhuman") && SPConfigMobs.infhumanEnabled) {
                entity = new EntityInfHuman(worldIn);
            } else if (this.name.equals("infhumanhead") && SPConfigMobs.infhumanEnabled) {
                entity = new EntityInfHumanHead(worldIn);
            } else if (this.name.equals("infcow") && SPConfigMobs.infcowEnabled) {
                entity = new EntityInfCow(worldIn);
            } else if (this.name.equals("infcowhead") && SPConfigMobs.infcowEnabled) {
                entity = new EntityInfCowHead(worldIn);
            } else if (this.name.equals("infsheep") && SPConfigMobs.infsheepEnabled) {
                entity = new EntityInfSheep(worldIn);
            } else if (this.name.equals("infsquid") && SPConfigMobs.infsquidEnabled) {
                entity = new EntityInfSquid(worldIn);
            } else if (this.name.equals("infsheephead") && SPConfigMobs.infsheepEnabled) {
                entity = new EntityInfSheepHead(worldIn);
            } else if (this.name.equals("infwolf") && SPConfigMobs.infwolfEnabled) {
                entity = new EntityInfWolf(worldIn);
            } else if (this.name.equals("infwolfhead") && SPConfigMobs.infwolfEnabled) {
                entity = new EntityInfWolfHead(worldIn);
            } else if (this.name.equals("infpig") && SPConfigMobs.infpigEnabled) {
                entity = new EntityInfPig(worldIn);
            } else if (this.name.equals("infpighead") && SPConfigMobs.infpigEnabled) {
                entity = new EntityInfPigHead(worldIn);
            } else if (this.name.equals("infvillager") && SPConfigMobs.infvillagerEnabled) {
                entity = new EntityInfVillager(worldIn);
            } else if (this.name.equals("infvillagerhead") && SPConfigMobs.infvillagerEnabled) {
                entity = new EntityInfVillagerHead(worldIn);
            } else {
                if (this.name.equals("infplayer") && SPConfigMobs.infadventurerEnabled) {
                    return this.spawnPlayer(worldIn, x, y, z, playerIn);
                }
                if (this.name.equals("infplayerhead") && SPConfigMobs.infadventurerEnabled) {
                    return this.spawnPlayerHead(worldIn, x, y, z, playerIn);
                }
                if (this.name.equals("inhoos")) {
                    entity = new EntityInhooS(worldIn);
                } else if (this.name.equals("inhoom")) {
                    entity = new EntityInhooM(worldIn);
                } else if (this.name.equals("infhorse") && SPConfigMobs.infhorseEnabled) {
                    entity = new EntityInfHorse(worldIn);
                } else if (this.name.equals("infhorsehead") && SPConfigMobs.infhorseEnabled) {
                    entity = new EntityInfHorseHead(worldIn);
                } else if (this.name.equals("infenderman") && SPConfigMobs.infendermanEnabled) {
                    entity = new EntityInfEnderman(worldIn);
                } else if (this.name.equals("infendermanhead") && SPConfigSystems.rsEnabled) {
                    entity = new EntityInfEndermanHead(worldIn);
                } else if (this.name.equals("infbear") && SPConfigMobs.infbearEnabled) {
                    entity = new EntityInfBear(worldIn);
                } else if (this.name.equals("infdragone")) {
                    entity = new EntityInfDragonE(worldIn);
                } else if (this.name.equals("infdragonehead") && SPConfigSystems.rsEnabled) {
                    entity = new EntityInfDragonEHead(worldIn);
                } else if (this.name.equals("host") && SPConfigMobs.hostEnabled) {
                    entity = new EntityHost(worldIn);
                } else if (this.name.equals("hostii") && SPConfigMobs.hostEnabled) {
                    entity = new EntityHostII(worldIn);
                } else if (this.name.equals("hull") && SPConfigMobs.hullEnabled) {
                    entity = new EntityHull(worldIn);
                } else if (this.name.equals("hulladapted") && SPConfigMobs.hullEnabled) {
                    entity = new EntityHullAdapted(worldIn);
                } else if (this.name.equals("canra") && SPConfigMobs.canraEnabled) {
                    entity = new EntityCanra(worldIn);
                } else if (this.name.equals("canraadapted") && SPConfigMobs.canraEnabled) {
                    entity = new EntityCanraAdapted(worldIn);
                } else if (this.name.equals("nogla") && SPConfigMobs.noglaEnabled) {
                    entity = new EntityNogla(worldIn);
                } else if (this.name.equals("noglaadapted") && SPConfigMobs.noglaEnabled) {
                    entity = new EntityNoglaAdapted(worldIn);
                } else if (this.name.equals("gim") && SPConfigMobs.gimEnabled) {
                    entity = new EntityGim(worldIn);
                } else if (this.name.equals("gimadapted") && SPConfigMobs.gimEnabled) {
                    entity = new EntityGimAdapted(worldIn);
                } else if (this.name.equals("zaa") && SPConfigMobs.zaaEnabled) {
                    entity = new EntityZaa(worldIn);
                } else if (this.name.equals("bano") && SPConfigMobs.zetmoEnabled) {
                    entity = new EntityBano(worldIn);
                } else if (this.name.equals("banoadapted") && SPConfigMobs.zetmoEnabled) {
                    entity = new EntityBanoAdapted(worldIn);
                } else if (this.name.equals("ranrac") && SPConfigMobs.arachnidaEnabled) {
                    entity = new EntityRanrac(worldIn);
                } else if (this.name.equals("ranracadapted") && SPConfigMobs.arachnidaEnabled) {
                    entity = new EntityRanracAdapted(worldIn);
                } else if (this.name.equals("lum") && SPConfigMobs.lumEnabled) {
                    entity = new EntityLum(worldIn);
                } else if (this.name.equals("lumadapted") && SPConfigMobs.lumEnabled) {
                    entity = new EntityLumAdapted(worldIn);
                } else if (this.name.equals("shyco") && SPConfigMobs.shycoEnabled) {
                    entity = new EntityShyco(worldIn);
                } else if (this.name.equals("shycoadapted") && SPConfigMobs.shycoEnabled) {
                    entity = new EntityShycoAdapted(worldIn);
                } else if (this.name.equals("emana") && SPConfigMobs.emanaEnabled) {
                    entity = new EntityEmana(worldIn);
                } else if (this.name.equals("emanaadapted") && SPConfigMobs.emanaEnabled) {
                    entity = new EntityEmanaAdapted(worldIn);
                } else if (this.name.equals("iki")) {
                    entity = new EntityIki(worldIn);
                } else if (this.name.equals("ikiadapted")) {
                    entity = new EntityIkiAdapted(worldIn);
                } else if (this.name.equals("wymo")) {
                    entity = new EntityWymo(worldIn);
                } else if (this.name.equals("buthol") && SPConfigMobs.butholEnabled) {
                    entity = new EntityButhol(worldIn);
                } else if (this.name.equals("alafha") && SPConfigMobs.alafhaEnabled) {
                    entity = new EntityAlafha(worldIn);
                } else if (this.name.equals("oronco") && SPConfigMobs.oroncoEnabled) {
                    entity = new EntityOronco(worldIn);
                } else if (this.name.equals("terla") && SPConfigMobs.terlaEnabled) {
                    entity = new EntityTerla(worldIn);
                } else if (this.name.equals("anged") && SPConfigMobs.angedEnabled) {
                    entity = new EntityAnged(worldIn);
                } else if (this.name.equals("lesh")) {
                    entity = new EntityLesh(worldIn);
                } else if (this.name.equals("leer")) {
                    entity = new EntityLeer(worldIn);
                } else if (this.name.equals("tonro") && SPConfigMobs.tonroEnabled) {
                    entity = new EntityTonro(worldIn);
                } else if (this.name.equals("unvo") && SPConfigMobs.unvoEnabled) {
                    entity = new EntityUnvo(worldIn);
                } else if (this.name.equals("ganro") && SPConfigMobs.ganroEnabled) {
                    entity = new EntityGanro(worldIn);
                } else if (this.name.equals("omboo") && SPConfigMobs.ombooEnabled) {
                    entity = new EntityOmboo(worldIn);
                } else if (this.name.equals("esor") && SPConfigMobs.esorEnabled) {
                    entity = new EntityEsor(worldIn);
                } else if (this.name.equals("orch") && SPConfigMobs.esorEnabled) {
                    entity = new EntityOrch(worldIn);
                } else if (this.name.equals("flog") && SPConfigMobs.flogEnabled) {
                    entity = new EntityFlog(worldIn);
                } else if (this.name.equals("ferbear") && SPConfigMobs.ferbearEnabled) {
                    entity = new EntityFerBear(worldIn);
                } else if (this.name.equals("fercow") && SPConfigMobs.fercowEnabled) {
                    entity = new EntityFerCow(worldIn);
                } else if (this.name.equals("ferenderman") && SPConfigMobs.ferendermanEnabled) {
                    entity = new EntityFerEnderman(worldIn);
                } else if (this.name.equals("ferhuman") && SPConfigMobs.ferhumanEnabled) {
                    entity = new EntityFerHuman(worldIn);
                } else if (this.name.equals("ferhorse") && SPConfigMobs.ferhorseEnabled) {
                    entity = new EntityFerHorse(worldIn);
                } else if (this.name.equals("fersheep") && SPConfigMobs.fersheepEnabled) {
                    entity = new EntityFerSheep(worldIn);
                } else if (this.name.equals("ferpig") && SPConfigMobs.ferpigEnabled) {
                    entity = new EntityFerPig(worldIn);
                } else if (this.name.equals("ferwolf")) {
                    entity = new EntityFerWolf(worldIn);
                } else if (this.name.equals("fervillager") && SPConfigMobs.fervillagerEnabled) {
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
                } else if (this.name.equals("higolem") && SPConfigMobs.higolemEnabled) {
                    entity = new EntityHiGolem(worldIn);
                } else if (this.name.equals("hiblaze")) {
                    entity = new EntityHiBlaze(worldIn);
                } else if (this.name.equals("hiskeleton")) {
                    entity = new EntityHiSkeleton(worldIn);
                } else if (this.name.equals("kirin")) {
                    entity = new EntityKirin(worldIn);
                } else if (this.name.equals("heblu") && SPConfigMobs.hebluEnabled) {
                    entity = new EntityHeblu(worldIn);
                }
            }
        }
        entity.func_70012_b(x, y, z, MathHelper.func_76142_g((float)(worldIn.field_73012_v.nextFloat() * 360.0f)), 0.0f);
        entity.field_70759_as = entity.field_70177_z;
        entity.field_70761_aq = entity.field_70177_z;
        entity.func_180482_a(worldIn.func_175649_E(new BlockPos((Entity)entity)), (IEntityLivingData)null);
        worldIn.func_72838_d((Entity)entity);
        return entity;
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
        entity.func_70012_b(x, y, z, MathHelper.func_76142_g((float)(worldIn.field_73012_v.nextFloat() * 360.0f)), 0.0f);
        entity.field_70759_as = entity.field_70177_z;
        entity.field_70761_aq = entity.field_70177_z;
        entity.func_180482_a(worldIn.func_175649_E(new BlockPos((Entity)entity)), null);
        entity.func_96094_a(playerIn.func_70005_c_());
        entity.func_174805_g(true);
        worldIn.func_72838_d((Entity)entity);
        return entity;
    }

    private Entity spawnPlayer2(World worldIn, double x, double y, double z, EntityPlayer playerIn) {
        EntityMes entity = new EntityMes(worldIn);
        entity.func_70012_b(x, y, z, MathHelper.func_76142_g((float)(worldIn.field_73012_v.nextFloat() * 360.0f)), 0.0f);
        entity.field_70759_as = entity.field_70177_z;
        entity.field_70761_aq = entity.field_70177_z;
        entity.func_180482_a(worldIn.func_175649_E(new BlockPos((Entity)entity)), null);
        entity.func_96094_a(playerIn.func_70005_c_());
        entity.func_174805_g(true);
        worldIn.func_72838_d((Entity)entity);
        return entity;
    }

    private Entity spawnPlayerHead(World worldIn, double x, double y, double z, EntityPlayer playerIn) {
        EntityInfPlayerHead entity = new EntityInfPlayerHead(worldIn);
        entity.func_70012_b(x, y, z, MathHelper.func_76142_g((float)(worldIn.field_73012_v.nextFloat() * 360.0f)), 0.0f);
        entity.field_70759_as = entity.field_70177_z;
        entity.field_70761_aq = entity.field_70177_z;
        entity.func_180482_a(worldIn.func_175649_E(new BlockPos((Entity)entity)), null);
        entity.func_96094_a(playerIn.func_70005_c_());
        entity.func_174805_g(true);
        worldIn.func_72838_d((Entity)entity);
        return entity;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
    }

    @Override
    public void registerModels() {
        SPMain.proxy.modelReg(this, 0, "inventory");
    }
}

