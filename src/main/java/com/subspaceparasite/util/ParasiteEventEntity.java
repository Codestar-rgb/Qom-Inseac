/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBush
 *  net.minecraft.block.BlockLeaves
 *  net.minecraft.block.BlockLog
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.effect.EntityLightningBolt
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.MobEffects
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ClassInheritanceMultiMap
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.TextComponentTranslation
 *  net.minecraft.util.text.translation.I18n
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.World
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  org.apache.logging.log4j.Level
 */
package com.subspaceparasite.util;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.block.BlockGore;
import com.subspaceparasite.compatibility.ModCompatibility;
import com.subspaceparasite.entity.EntityParasiticScent;
import com.subspaceparasite.entity.ai.misc.EntityCanSummon;
import com.subspaceparasite.entity.ai.misc.EntityPBeckon;
import com.subspaceparasite.entity.ai.misc.EntityPCrude;
import com.subspaceparasite.entity.ai.misc.EntityPFeral;
import com.subspaceparasite.entity.ai.misc.EntityPHijacked;
import com.subspaceparasite.entity.ai.misc.EntityPInfected;
import com.subspaceparasite.entity.ai.misc.EntityPMalleable;
import com.subspaceparasite.entity.ai.misc.EntityPStationary;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.entity.monster.EntityBiomass;
import com.subspaceparasite.entity.monster.adapted.EntityBanoAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityCanraAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityEmanaAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityGimAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityHullAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityNoglaAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityRanracAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityShycoAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityWymoAdapted;
import com.subspaceparasite.entity.monster.adapted.EntityZaaAdapted;
import com.subspaceparasite.entity.monster.crude.EntityInhooM;
import com.subspaceparasite.entity.monster.crude.EntityInhooS;
import com.subspaceparasite.entity.monster.deterrent.EntityNak;
import com.subspaceparasite.entity.monster.deterrent.EntityRof;
import com.subspaceparasite.entity.monster.deterrent.EntityTonro;
import com.subspaceparasite.entity.monster.deterrent.EntityUnvo;
import com.subspaceparasite.entity.monster.feral.EntityFerBear;
import com.subspaceparasite.entity.monster.feral.EntityFerCow;
import com.subspaceparasite.entity.monster.feral.EntityFerEnderman;
import com.subspaceparasite.entity.monster.feral.EntityFerHorse;
import com.subspaceparasite.entity.monster.feral.EntityFerHuman;
import com.subspaceparasite.entity.monster.feral.EntityFerPig;
import com.subspaceparasite.entity.monster.feral.EntityFerSheep;
import com.subspaceparasite.entity.monster.feral.EntityFerVillager;
import com.subspaceparasite.entity.monster.feral.EntityFerWolf;
import com.subspaceparasite.entity.monster.inborn.EntityKol;
import com.subspaceparasite.entity.monster.infected.EntityInfBear;
import com.subspaceparasite.entity.monster.infected.EntityInfCow;
import com.subspaceparasite.entity.monster.infected.EntityInfEnderman;
import com.subspaceparasite.entity.monster.infected.EntityInfHorse;
import com.subspaceparasite.entity.monster.infected.EntityInfHuman;
import com.subspaceparasite.entity.monster.infected.EntityInfPig;
import com.subspaceparasite.entity.monster.infected.EntityInfSheep;
import com.subspaceparasite.entity.monster.infected.EntityInfVillager;
import com.subspaceparasite.entity.monster.infected.EntityInfWolf;
import com.subspaceparasite.entity.monster.infected.special.EntitySpeCow;
import com.subspaceparasite.entity.monster.infected.special.EntitySpeEnderman;
import com.subspaceparasite.entity.monster.infected.special.EntitySpeHuman;
import com.subspaceparasite.entity.monster.infected.special.EntitySpeVillager;
import com.subspaceparasite.entity.monster.primitive.EntityBano;
import com.subspaceparasite.entity.monster.primitive.EntityCanra;
import com.subspaceparasite.entity.monster.primitive.EntityEmana;
import com.subspaceparasite.entity.monster.primitive.EntityGim;
import com.subspaceparasite.entity.monster.primitive.EntityHull;
import com.subspaceparasite.entity.monster.primitive.EntityIki;
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
import com.subspaceparasite.entity.monster.pure.preeminent.EntityTenn;
import com.subspaceparasite.entity.projectile.EntityBomb;
import com.subspaceparasite.entity.projectile.EntityProjectileBiomass;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.network.SPPacketMovingSound;
import com.subspaceparasite.network.SPPacketParticle;
import com.subspaceparasite.util.ParasiteEventWorld;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigMobs;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.util.config.SPConfigWorld;
import com.subspaceparasite.util.spawn.ParasiteSummon;
import com.subspaceparasite.world.SPExplosion;
import com.subspaceparasite.world.SPSaveData;
import com.subspaceparasite.world.SPWorldData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ClassInheritanceMultiMap;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import org.apache.logging.log4j.Level;

public class ParasiteEventEntity {
    public static boolean canSpawnNext = true;

    public static int entityChunkCount(World world, BlockPos pos, Class<? extends EntityLivingBase> mobC) {
        ClassInheritanceMultiMap[] arrayE = world.func_175726_f(pos).func_177429_s();
        int v = 0;
        for (ClassInheritanceMultiMap entities : arrayE) {
            Object[] arrayO;
            if (entities == null) continue;
            for (Object o : arrayO = entities.toArray()) {
                if (o == null || !mobC.isInstance(o)) continue;
                ++v;
            }
        }
        return v;
    }

    public static boolean checkEntity(EntityLivingBase entity, String[] list, boolean inverted) {
        ResourceLocation enti = EntityList.func_191301_a((Entity)entity);
        if (enti != null) {
            return ParasiteEventEntity.checkName(enti.toString(), list, inverted);
        }
        return false;
    }

    public static boolean checkName(String potentialElement, String[] blacklist, boolean isWhitelist) {
        if (potentialElement == null) {
            return false;
        }
        return Arrays.stream(blacklist).anyMatch(potentialElement::contains) != isWhitelist;
    }

    public static void orbApplyEffects(EntityLivingBase target, EntityParasiteBase in, String[] effects, int mobs) {
        for (String i : effects) {
            String[] here = i.split(";");
            try {
                if (here[5] == null) {
                    return;
                }
            }
            catch (Exception e) {
                return;
            }
            Potion potionE = Potion.func_180142_b((String)here[3]);
            if (potionE == null) continue;
            int self = Integer.parseInt(here[0]);
            int duration = Integer.parseInt(here[1]) * 20;
            int amp = Integer.parseInt(here[2]);
            int enemiesA = Integer.parseInt(here[4]);
            int enemiesD = Integer.parseInt(here[5]);
            if (enemiesA != 0) {
                amp += mobs / enemiesA;
            }
            if (enemiesD != 0) {
                duration += mobs / enemiesD * 20;
            }
            if (self == 1) {
                in.func_70690_d(new PotionEffect(potionE, duration, amp, false, false));
                continue;
            }
            if (self == 2) {
                if (!(target instanceof EntityParasiteBase)) continue;
                SPPotions.applyStackPotion(potionE, target, duration, amp);
                continue;
            }
            if (target instanceof EntityParasiteBase) continue;
            SPPotions.applyStackPotion(potionE, target, duration, amp);
        }
    }

    public static void spawnNext(EntityParasiteBase entityin, EntityParasiteBase entityout, boolean effects, boolean thunder) {
        if (entityin.field_70128_L) {
            return;
        }
        if (entityout == null) {
            return;
        }
        boolean flag = entityin.func_70027_ad();
        entityin.func_70106_y();
        entityout.func_70012_b(entityin.field_70165_t, entityin.field_70163_u, entityin.field_70161_v, entityin.field_70177_z, entityin.field_70125_A);
        entityout.func_180482_a(entityin.field_70170_p.func_175649_E(new BlockPos((Entity)entityout)), null);
        entityout.cannotDespawn(entityin.func_70692_ba());
        if (entityin.func_145818_k_()) {
            entityout.func_96094_a(entityin.func_95999_t());
            entityout.func_174805_g(entityin.func_174833_aM());
        }
        entityin.field_70170_p.func_72838_d((Entity)entityout);
        if (entityin instanceof EntityPMalleable && entityout instanceof EntityPMalleable) {
            ((EntityPMalleable)entityout).copyResistancesFrom((EntityPMalleable)entityin);
        }
        if (effects) {
            entityout.particleStatus((byte)7);
        }
        if (thunder && SPConfig.thunderEnable) {
            entityout.field_70170_p.func_72942_c((Entity)new EntityLightningBolt(entityout.field_70170_p, entityout.field_70165_t, entityout.field_70163_u, entityout.field_70161_v, true));
        }
        if (flag) {
            entityout.func_70606_j(entityout.func_110138_aP() * 0.5f);
            entityout.func_70015_d(8);
        }
    }

    public static void spawnFromList(Entity entityin, String[] out, @Nullable EntityLivingBase target) {
        EntityLiving entityout = (EntityLiving)EntityList.func_188429_b((ResourceLocation)new ResourceLocation(out[entityin.field_70170_p.field_73012_v.nextInt(out.length)]), (World)entityin.field_70170_p);
        if (entityout == null) {
            return;
        }
        entityout.func_82149_j(entityin);
        entityout.func_180482_a(entityin.field_70170_p.func_175649_E(new BlockPos((Entity)entityout)), null);
        entityin.field_70170_p.func_72838_d((Entity)entityout);
        if (target != null) {
            entityout.func_70624_b(target);
        }
    }

    public static boolean spawnBiomassFromProjectile(EntityParasiteBase entityin, String[] out, @Nullable EntityLivingBase target) {
        if (!entityin.field_70170_p.field_72995_K) {
            Random rand = new Random();
            int index = rand.nextInt(out.length);
            int limit = 0;
            boolean flag = true;
            while (flag) {
                if (index >= out.length) {
                    index = 0;
                    ++limit;
                }
                if (limit == 2) {
                    return false;
                }
                if (out[index] != null) {
                    String[] entityC = out[index].split(";");
                    double chance = Double.parseDouble(entityC[1]);
                    if (rand.nextDouble() <= chance) {
                        EntityCanSummon father = (EntityCanSummon)((Object)entityin);
                        int points = Integer.parseInt(entityC[2]);
                        if (father.getTotalParasites() - father.getActualParasites() < points) {
                            ++index;
                            continue;
                        }
                        if (target == null) {
                            return false;
                        }
                        Vec3d vec3d = entityin.func_70676_i(1.0f);
                        double d2 = target.field_70165_t - (entityin.field_70165_t + vec3d.field_72450_a);
                        double d3 = target.func_174813_aQ().field_72338_b + (double)(target.field_70131_O / 2.0f) - (0.5 + entityin.field_70163_u + (double)(entityin.field_70131_O / 2.0f));
                        double d4 = target.field_70161_v - (entityin.field_70161_v + vec3d.field_72449_c);
                        EntityProjectileBiomass entityout = new EntityProjectileBiomass(entityin.field_70170_p, (EntityLivingBase)entityin, d2, d3, d4);
                        entityout.field_70165_t = entityin.field_70165_t + vec3d.field_72450_a;
                        entityout.field_70163_u = entityin.field_70163_u + (double)entityin.func_70047_e() - 0.2;
                        entityout.field_70161_v = entityin.field_70161_v + vec3d.field_72449_c;
                        entityout.setParasite(entityC[0], points, 4);
                        father.setActualParasites(points);
                        father.addID(entityout.func_145782_y(), points);
                        entityin.field_70170_p.func_72838_d((Entity)entityout);
                        flag = false;
                        return true;
                    }
                }
                ++index;
            }
        }
        return false;
    }

    public static boolean spawnBiomassFromVomit(EntityParasiteBase entityin, String[] out, @Nullable EntityLivingBase target) {
        if (!entityin.field_70170_p.field_72995_K) {
            Random rand = new Random();
            int index = rand.nextInt(out.length);
            int limit = 0;
            boolean flag = true;
            while (flag) {
                if (index >= out.length) {
                    index = 0;
                    ++limit;
                }
                if (limit == 2) {
                    return false;
                }
                if (out[index] != null) {
                    String[] entityC = out[index].split(";");
                    double chance = Double.parseDouble(entityC[1]);
                    if (rand.nextDouble() <= chance) {
                        EntityCanSummon father = (EntityCanSummon)((Object)entityin);
                        int points = Integer.parseInt(entityC[2]);
                        if (father.getTotalParasites() - father.getActualParasites() < points) {
                            ++index;
                            continue;
                        }
                        EntityBiomass entityout = new EntityBiomass(entityin.field_70170_p, entityin, target);
                        entityout.func_70012_b(entityin.field_70165_t, entityin.field_70163_u, entityin.field_70161_v, entityin.field_70177_z, entityin.field_70125_A);
                        float f19 = MathHelper.func_76126_a((float)(entityin.field_70177_z * ((float)Math.PI / 180) - entityin.field_70704_bt * 0.01f));
                        float f14 = 0.17453292f;
                        float f16 = MathHelper.func_76134_b((float)f14);
                        float f4 = MathHelper.func_76134_b((float)(entityin.field_70177_z * ((float)Math.PI / 180) - entityin.field_70704_bt * 0.01f));
                        entityout.field_70177_z = entityin.field_70177_z;
                        if (entityout.field_70170_p.func_180495_p(new BlockPos(entityin.field_70165_t + -1.0 * (double)(f19 * 3.0f * f16), entityin.field_70163_u + (double)entityin.func_70047_e(), entityin.field_70161_v - -1.0 * (double)(f4 * 3.0f * f16))).func_177230_c() != Blocks.field_150350_a) {
                            entityout.func_70106_y();
                            return false;
                        }
                        entityout.func_70107_b(entityin.field_70165_t + -1.0 * (double)(f19 * 3.0f * f16), entityin.field_70163_u + (double)entityin.func_70047_e(), entityin.field_70161_v - -1.0 * (double)(f4 * 3.0f * f16));
                        entityout.setFuse(80);
                        entityout.setParasite(entityC[0], points);
                        if (entityin instanceof EntityCanra) {
                            entityout.setSkin(5);
                        } else {
                            entityout.setSkin(6);
                        }
                        father.setActualParasites(points);
                        father.addID(entityout.func_145782_y(), points);
                        if (entityin.func_70027_ad()) {
                            entityout.func_70015_d(8);
                        }
                        entityin.field_70170_p.func_72838_d((Entity)entityout);
                        flag = false;
                        return true;
                    }
                }
                ++index;
            }
        }
        return false;
    }

    private static boolean getWorldBeckonSpawnLimit(EntityParasiteBase entityin) {
        int count = 0;
        List entities = entityin.func_130014_f_().field_72996_f;
        for (Entity entity : entities) {
            if (!(entity instanceof EntityParasiteBase)) continue;
            ++count;
        }
        int players = entityin.field_70170_p.field_73010_i.size();
        return count < SPConfig.worldMobCap + players * SPConfig.worldMobCapPlusPlayer + SPConfig.worldBeckonSpawnsCap;
    }

    public static boolean spawnBiomassFromBeckon(EntityParasiteBase entityin, int stage, EntityLivingBase target, boolean payfather, String[] ground, String[] air) {
        if (!entityin.field_70170_p.field_72995_K) {
            String[] mobListG = ground;
            if (entityin.field_70163_u + 3.0 <= target.field_70163_u) {
                if (stage == 1) {
                    return false;
                }
                mobListG = air;
            }
            Random rand = new Random();
            int index = rand.nextInt(mobListG.length);
            int limit = 0;
            boolean flag = true;
            while (flag) {
                if (index >= mobListG.length) {
                    index = 0;
                    ++limit;
                }
                if (limit == 2) {
                    return false;
                }
                if (mobListG[index] != null) {
                    double k;
                    double d7;
                    double d6;
                    double d5;
                    double d4;
                    double d3;
                    double d2;
                    double d1;
                    double d0;
                    String[] entityC = mobListG[index].split(";");
                    if (entityC.length != 3) {
                        SPMain.logger.error("Malformed string: " + mobListG[index].toString() + " in the beckon spawn pool configuration, safely exiting loop. Did you forget a semicolon?");
                        return false;
                    }
                    double chance = Double.parseDouble(entityC[1]);
                    EntityCanSummon father = (EntityCanSummon)((Object)entityin);
                    int points = Integer.parseInt(entityC[2]);
                    if (father.getTotalParasites() - father.getActualParasites() < points && payfather) {
                        ++index;
                    }
                    double b = 0.0;
                    if (stage == 3) {
                        b = 0.5;
                    }
                    if (!ParasiteEventEntity.getWorldBeckonSpawnLimit(entityin)) {
                        EntityBomb entityAlt = new EntityBomb(entityin.field_70170_p, entityin, false);
                        if (entityin.func_130014_f_().field_73012_v.nextInt() < 40) {
                            return false;
                        }
                        if (entityin.func_70638_az() != null) {
                            entityAlt.func_70012_b(entityin.field_70165_t, entityin.field_70163_u + ((double)entityin.func_70047_e() + b), entityin.field_70161_v, entityin.field_70177_z, entityin.field_70125_A);
                            d0 = (float)entityin.field_70165_t + entityin.field_70170_p.field_73012_v.nextFloat();
                            d1 = (float)entityin.field_70163_u + entityin.func_70047_e() + entityin.field_70170_p.field_73012_v.nextFloat();
                            d2 = (float)entityin.field_70161_v + entityin.field_70170_p.field_73012_v.nextFloat();
                            d3 = d0 - entityin.field_70165_t;
                            d4 = d1 - entityin.field_70163_u;
                            d5 = d2 - entityin.field_70161_v;
                            d6 = MathHelper.func_76133_a((double)(d3 * d3 + d4 * d4 + d5 * d5));
                            d3 /= d6;
                            d4 /= d6;
                            d5 /= d6;
                            d7 = 0.5 / (d6 / 4.0 + 0.1);
                            d7 *= (double)(entityin.field_70170_p.field_73012_v.nextFloat() * entityin.field_70170_p.field_73012_v.nextFloat() + 1.7f);
                            k = 3.0;
                            if (stage == 3) {
                                k = 5.0;
                            }
                            d5 = d7 * k;
                            entityAlt.setMotion(d3 *= d7 * k, d4 *= d7 * 2.0, d5, 0.4, 0.5);
                            entityAlt.setFuse(60);
                            entityAlt.setStren(0.0f);
                            entityAlt.setSkin(1);
                            entityAlt.setDamage((float)entityin.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111125_b(), 2);
                            entityAlt.field_70125_A -= -20.0f;
                            entityin.field_70170_p.func_72838_d((Entity)entityAlt);
                            entityAlt.updateSTR();
                            SPMain.network.sendToAll((IMessage)new SPPacketParticle(entityin.field_70165_t, entityin.field_70163_u, entityin.field_70161_v, 0.5f, 0.5f, 10));
                        }
                    } else {
                        EntityBiomass entityout = new EntityBiomass(entityin.field_70170_p, entityin, stage, target, payfather);
                        entityout.func_70012_b(entityin.field_70165_t, entityin.field_70163_u + ((double)entityin.func_70047_e() + b), entityin.field_70161_v, entityin.field_70177_z, entityin.field_70125_A);
                        d0 = (float)entityin.field_70165_t + entityin.field_70170_p.field_73012_v.nextFloat();
                        d1 = (float)entityin.field_70163_u + entityin.func_70047_e() + entityin.field_70170_p.field_73012_v.nextFloat();
                        d2 = (float)entityin.field_70161_v + entityin.field_70170_p.field_73012_v.nextFloat();
                        d3 = d0 - entityin.field_70165_t;
                        d4 = d1 - entityin.field_70163_u;
                        d5 = d2 - entityin.field_70161_v;
                        d6 = MathHelper.func_76133_a((double)(d3 * d3 + d4 * d4 + d5 * d5));
                        d3 /= d6;
                        d4 /= d6;
                        d5 /= d6;
                        d7 = 0.5 / (d6 / 4.0 + 0.1);
                        d7 *= (double)(entityin.field_70170_p.field_73012_v.nextFloat() * entityin.field_70170_p.field_73012_v.nextFloat() + 1.7f);
                        k = 3.0;
                        if (stage == 3) {
                            k = 5.0;
                        }
                        d5 = d7 * k;
                        entityout.setMotion(d3 *= d7 * k, d4 *= d7 * 2.0, d5, 0.4, 0.5);
                        entityout.setFuse(80);
                        entityout.setParasite(entityC[0], points);
                        entityout.setSkin(stage);
                        if (entityin.func_70027_ad()) {
                            entityout.func_70015_d(8);
                        }
                        entityin.field_70170_p.func_72838_d((Entity)entityout);
                        if (payfather) {
                            father.setActualParasites(points);
                            father.addID(entityout.func_145782_y(), points);
                        }
                        flag = false;
                        return true;
                    }
                }
                ++index;
            }
        }
        return false;
    }

    public static void convertEntity(EntityLivingBase entityin, NBTTagCompound tags, boolean ignoreKey, String[] list) {
        if (entityin == null) {
            return;
        }
        World world = entityin.field_70170_p;
        if (world.field_72995_K) {
            return;
        }
        if (SPSaveData.get(entityin.field_70170_p, 105).getEvolutionPhase(entityin.field_71093_bK) >= SPConfigSystems.evolutionFeralNoSim && ParasiteEventEntity.convertEntityFeral(entityin, tags, true, list)) {
            return;
        }
        if (tags.func_74764_b("srpcothimmunity")) {
            String mobname;
            int goo;
            int key = tags.func_74762_e("srpcothimmunity");
            if (key == 0 && !ignoreKey) {
                entityin.func_184596_c(SPPotions.COTH_E);
                return;
            }
            entityin.func_70690_d(new PotionEffect(MobEffects.field_76421_d, 100, 3, false, false));
            SPMain.network.sendToAll((IMessage)new SPPacketParticle(entityin.field_70165_t, entityin.field_70163_u, entityin.field_70161_v, entityin.field_70130_N, entityin.field_70131_O, 1));
            tags.func_74768_a("srpcothimmunity", ++key);
            if (key < 3 && !ignoreKey) {
                return;
            }
            SPSaveData dataLol = SPSaveData.get(entityin.field_70170_p, 103);
            int n = goo = SPConfigSystems.disloCOTHTiers ? dataLol.getCurrentCode(entityin.field_70170_p.field_73011_w.getDimension(), 1) : 0;
            if (goo != 0) {
                EntityParasiteBase halo = ParasiteEventEntity.getRandomFeral(entityin.field_70170_p);
                if (goo >= SPConfigSystems.disloCOTHTiersValue1) {
                    halo = ParasiteEventEntity.getRandomPrimitive(entityin.field_70170_p);
                }
                if (goo >= SPConfigSystems.disloCOTHTiersValue2) {
                    halo = ParasiteEventEntity.getRandomAdapted(entityin.field_70170_p);
                }
                if (goo >= SPConfigSystems.disloCOTHTiersValue3) {
                    halo = ParasiteEventEntity.getRandomPure(entityin.field_70170_p);
                }
                halo.func_82149_j((Entity)entityin);
                world.func_72900_e((Entity)entityin);
                halo.func_180482_a(world.func_175649_E(new BlockPos((Entity)halo)), null);
                if (entityin.func_145818_k_()) {
                    halo.func_96094_a(entityin.func_95999_t());
                    halo.func_174805_g(entityin.func_174833_aM());
                }
                world.func_72838_d((Entity)halo);
                world.func_180498_a(null, 1026, new BlockPos((Entity)halo), 0);
                halo.particleStatus((byte)7);
                halo.cannotDespawn(SPConfig.convertedDespawn);
                if (key >= 10) {
                    halo.func_70690_d(new PotionEffect(SPPotions.EPEL_E, 600, 0, false, false));
                }
                return;
            }
            try {
                mobname = EntityList.func_191301_a((Entity)entityin).toString();
            }
            catch (Exception e) {
                SPMain.logger.log(Level.ERROR, "Problem while converting entity", (Throwable)e);
                ParasiteEventEntity.spawnInsider(entityin, world, tags);
                return;
            }
            boolean flag = true;
            for (String s : list) {
                EntityPInfected entityout;
                String[] here = s.split(";");
                try {
                    if (here[0] == null || here[1] == null) {
                        ParasiteEventEntity.spawnInsider(entityin, world, tags);
                        return;
                    }
                }
                catch (Exception e) {
                    SPMain.logger.log(Level.ERROR, "Problem while converting entity", (Throwable)e);
                    ParasiteEventEntity.spawnInsider(entityin, world, tags);
                    return;
                }
                if (!here[0].equals(mobname)) continue;
                Entity outOne = EntityList.func_188429_b((ResourceLocation)new ResourceLocation(here[1]), (World)world);
                if (outOne == null) {
                    ParasiteEventEntity.spawnInsider(entityin, world, tags);
                    return;
                }
                if (outOne instanceof EntityPInfected) {
                    entityout = (EntityPInfected)outOne;
                    SPSaveData.get(world, 104).addNumberIDDataSpawn(entityout.getParasiteIDRegister());
                    entityout.func_82149_j((Entity)entityin);
                    world.func_72900_e((Entity)entityin);
                    entityout.setHost(mobname);
                    entityout.func_180482_a(world.func_175649_E(new BlockPos((Entity)entityout)), null);
                    if (entityin.func_145818_k_()) {
                        entityout.func_96094_a(entityin.func_95999_t());
                        entityout.func_174805_g(entityin.func_174833_aM());
                    }
                    world.func_72838_d((Entity)entityout);
                    world.func_180498_a(null, 1026, new BlockPos((Entity)entityout), 0);
                    if (SPConfigSystems.generationUse) {
                        entityout.func_70606_j(entityout.func_110143_aJ() * ParasiteEventEntity.getSimCOTHMod(dataLol, world));
                    }
                    entityout.particleStatus((byte)7);
                    entityout.cannotDespawn(SPConfig.convertedDespawn);
                    if (key >= 10) {
                        entityout.func_70690_d(new PotionEffect(SPPotions.EPEL_E, 600, 0, false, false));
                    }
                    AxisAlignedBB axisalignedbb = new AxisAlignedBB(entityout.func_180425_c()).func_186662_g(14.0);
                    List moblist = entityout.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
                    for (EntityLivingBase mob : moblist) {
                        if (!mob.func_70644_a(SPPotions.COTH_E) || !(tags = mob.getEntityData()).func_74764_b("srpcothimmunity") || (key = tags.func_74762_e("srpcothimmunity")) != 1 || mob.func_70660_b(SPPotions.COTH_E).func_76458_c() <= 1) continue;
                        tags.func_74768_a("srpcothimmunity", ++key);
                    }
                } else if (outOne instanceof EntityLiving) {
                    entityout = (EntityLiving)outOne;
                    entityout.func_82149_j((Entity)entityin);
                    world.func_72900_e((Entity)entityin);
                    entityout.func_180482_a(world.func_175649_E(new BlockPos((Entity)entityout)), null);
                    if (entityin.func_145818_k_()) {
                        entityout.func_96094_a(entityin.func_95999_t());
                        entityout.func_174805_g(entityin.func_174833_aM());
                    }
                    world.func_72838_d((Entity)entityout);
                    world.func_180498_a(null, 1026, new BlockPos((Entity)entityout), 0);
                }
                flag = false;
            }
            if (flag && !ignoreKey) {
                ParasiteEventEntity.spawnInsider(entityin, world, tags);
            }
        }
    }

    public static void spawnInsider(EntityLivingBase entity, World world, NBTTagCompound tags) {
        if (!SPConfigMobs.inhooSEnabled || !SPConfigMobs.inhooMEnabled) {
            return;
        }
        List serverList = world.field_72996_f;
        int count = 0;
        for (Entity value : serverList) {
            if (!(value instanceof EntityInhooM) && !(value instanceof EntityInhooS)) continue;
            ++count;
        }
        if (count > SPConfig.incompleteCap) {
            world.func_72900_e((Entity)entity);
            return;
        }
        if (tags.func_74764_b("srpcothimmunity")) {
            int key = tags.func_74762_e("srpcothimmunity");
            if (key == 0) {
                entity.func_184596_c(SPPotions.COTH_E);
                return;
            }
            entity.func_70690_d(new PotionEffect(MobEffects.field_76421_d, 100, 3, false, false));
            SPMain.network.sendToAll((IMessage)new SPPacketParticle(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, entity.field_70130_N, entity.field_70131_O, 1));
            tags.func_74768_a("srpcothimmunity", ++key);
            if (key < 3) {
                return;
            }
            EntityPCrude out = new EntityInhooS(world);
            float mass = ParasiteEventEntity.getEntityArea(entity);
            if ((double)mass > 0.517) {
                out = new EntityInhooM(world);
            }
            out.func_82149_j((Entity)entity);
            world.func_72900_e((Entity)entity);
            world.func_72838_d((Entity)out);
            world.func_180498_a(null, 1026, new BlockPos((Entity)out), 0);
            out.particleStatus((byte)7);
            out.cannotDespawn(SPConfig.convertedDespawn);
            if (SPConfigSystems.generationUse) {
                out.func_70606_j(out.func_110143_aJ() * ParasiteEventEntity.getSimCOTHMod(SPSaveData.get(world, 102), world));
            }
            int range = 1;
            double i1 = MathHelper.func_76128_c((double)(out.field_70163_u + 0.1));
            double l1 = out.field_70165_t;
            double i2 = out.field_70161_v;
            int counttt = 0;
            count = 2;
            for (int k2 = -1 * range; k2 <= 1 * range && SPConfig.paraGore; ++k2) {
                for (int l2 = -1 * range; l2 <= 1 * range; ++l2) {
                    double i3 = l1 + (double)k2;
                    double l = i2 + (double)l2;
                    BlockPos blockpos = new BlockPos(i3, i1, l);
                    Block block = out.field_70170_p.func_180495_p(blockpos).func_177230_c();
                    Block blockDown = out.field_70170_p.func_180495_p(blockpos.func_177977_b()).func_177230_c();
                    if (block != Blocks.field_150350_a || blockDown == Blocks.field_150350_a || !world.func_180495_p(blockpos.func_177977_b()).func_185913_b() || blockDown == SPBlocks.InfestedStain || out.field_70170_p.field_73012_v.nextInt(4) != 0) continue;
                    out.field_70170_p.func_175656_a(blockpos, SPBlocks.goreSim.func_176223_P().func_177226_a(BlockGore.VARIANT, (Comparable)((Object)BlockGore.EnumType.FLAT)));
                    if (++counttt < count) continue;
                    return;
                }
            }
        }
    }

    private static float getEntityArea(EntityLivingBase entity) {
        return entity.field_70130_N * entity.field_70130_N * entity.field_70131_O;
    }

    private static float getSimCOTHMod(SPSaveData data, World world) {
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

    public static boolean convertEntityFeral(EntityLivingBase entityin, NBTTagCompound tags, boolean ignoreKey, String[] list) {
        World world = entityin.field_70170_p;
        if (world.field_72995_K) {
            return false;
        }
        if (entityin == null) {
            return false;
        }
        if (tags.func_74764_b("srpcothimmunity")) {
            String mobname;
            int key = tags.func_74762_e("srpcothimmunity");
            if (key == 0 && !ignoreKey) {
                entityin.func_184596_c(SPPotions.COTH_E);
                return false;
            }
            entityin.func_70690_d(new PotionEffect(MobEffects.field_76421_d, 100, 3, false, false));
            SPMain.network.sendToAll((IMessage)new SPPacketParticle(entityin.field_70165_t, entityin.field_70163_u, entityin.field_70161_v, entityin.field_70130_N, entityin.field_70131_O, 1));
            tags.func_74768_a("srpcothimmunity", ++key);
            if (key < 3 && !ignoreKey) {
                return false;
            }
            try {
                mobname = EntityList.func_191301_a((Entity)entityin).toString();
            }
            catch (Exception e) {
                SPMain.logger.log(Level.ERROR, "Problem while converting entity", (Throwable)e);
                ParasiteEventEntity.spawnInsider(entityin, world, tags);
                return false;
            }
            boolean flag = true;
            for (String s : list) {
                String[] here = s.split(";");
                try {
                    if (here[0] == null || here[1] == null) {
                        ParasiteEventEntity.spawnInsider(entityin, world, tags);
                        return false;
                    }
                }
                catch (Exception e) {
                    SPMain.logger.log(Level.ERROR, "Problem while converting entity", (Throwable)e);
                    ParasiteEventEntity.spawnInsider(entityin, world, tags);
                    return false;
                }
                if (!here[0].equals(mobname)) continue;
                Entity outOne = EntityList.func_188429_b((ResourceLocation)new ResourceLocation(here[1]), (World)world);
                if (outOne == null) {
                    ParasiteEventEntity.spawnInsider(entityin, world, tags);
                    return false;
                }
                if (outOne instanceof EntityPInfected) {
                    EntityPFeral gaa = ((EntityPInfected)outOne).getFeral(world);
                    if (gaa != null) {
                        outOne.func_70106_y();
                        gaa.func_82149_j((Entity)entityin);
                        world.func_72900_e((Entity)entityin);
                        gaa.func_180482_a(world.func_175649_E(new BlockPos((Entity)gaa)), null);
                        if (entityin.func_145818_k_()) {
                            gaa.func_96094_a(entityin.func_95999_t());
                            gaa.func_174805_g(entityin.func_174833_aM());
                        }
                        world.func_72838_d((Entity)gaa);
                        world.func_180498_a(null, 1026, new BlockPos((Entity)gaa), 0);
                        gaa.particleStatus((byte)7);
                        gaa.cannotDespawn(SPConfig.convertedDespawn);
                    } else {
                        EntityPInfected entityout = (EntityPInfected)outOne;
                        SPSaveData.get(world, 101).addNumberIDDataSpawn(entityout.getParasiteIDRegister());
                        entityout.func_82149_j((Entity)entityin);
                        world.func_72900_e((Entity)entityin);
                        entityout.setHost(mobname);
                        entityout.func_180482_a(world.func_175649_E(new BlockPos((Entity)entityout)), null);
                        if (entityin.func_145818_k_()) {
                            entityout.func_96094_a(entityin.func_95999_t());
                            entityout.func_174805_g(entityin.func_174833_aM());
                        }
                        world.func_72838_d((Entity)entityout);
                        world.func_180498_a(null, 1026, new BlockPos((Entity)entityout), 0);
                        entityout.particleStatus((byte)7);
                        entityout.cannotDespawn(SPConfig.convertedDespawn);
                        if (key >= 10) {
                            entityout.func_70690_d(new PotionEffect(SPPotions.EPEL_E, 600, 0, false, false));
                        }
                        AxisAlignedBB axisalignedbb = new AxisAlignedBB(entityout.func_180425_c()).func_186662_g(14.0);
                        List moblist = entityout.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
                        for (EntityLivingBase mob : moblist) {
                            if (!mob.func_70644_a(SPPotions.COTH_E) || !(tags = mob.getEntityData()).func_74764_b("srpcothimmunity") || (key = tags.func_74762_e("srpcothimmunity")) != 1 || mob.func_70660_b(SPPotions.COTH_E).func_76458_c() <= 1) continue;
                            tags.func_74768_a("srpcothimmunity", ++key);
                        }
                    }
                    return true;
                }
                if (outOne instanceof EntityLiving) {
                    EntityLiving entityout = (EntityLiving)outOne;
                    entityout.func_82149_j((Entity)entityin);
                    world.func_72900_e((Entity)entityin);
                    entityout.func_180482_a(world.func_175649_E(new BlockPos((Entity)entityout)), null);
                    if (entityin.func_145818_k_()) {
                        entityout.func_96094_a(entityin.func_95999_t());
                        entityout.func_174805_g(entityin.func_174833_aM());
                    }
                    world.func_72838_d((Entity)entityout);
                    world.func_180498_a(null, 1026, new BlockPos((Entity)entityout), 0);
                    return true;
                }
                flag = false;
            }
            if (flag && !ignoreKey) {
                ParasiteEventEntity.spawnInsider(entityin, world, tags);
            }
        }
        return false;
    }

    public static boolean hijackEntity(EntityLivingBase entityin, String[] list) {
        String mobname;
        if (entityin == null) {
            return false;
        }
        World world = entityin.field_70170_p;
        if (world.field_72995_K) {
            return false;
        }
        try {
            mobname = EntityList.func_191301_a((Entity)entityin).toString();
        }
        catch (Exception e) {
            SPMain.logger.log(Level.ERROR, "Problem while converting entity", (Throwable)e);
            return false;
        }
        boolean flag = true;
        for (String s : list) {
            EntityPHijacked entityout;
            String[] here = s.split(";");
            try {
                if (here[0] == null || here[1] == null) {
                    return false;
                }
            }
            catch (Exception e) {
                SPMain.logger.log(Level.ERROR, "Problem while converting entity", (Throwable)e);
                return false;
            }
            if (!here[0].equals(mobname)) continue;
            Entity outOne = EntityList.func_188429_b((ResourceLocation)new ResourceLocation(here[1]), (World)world);
            if (outOne == null) {
                return false;
            }
            if (outOne instanceof EntityPHijacked) {
                entityout = (EntityPHijacked)outOne;
                SPSaveData.get(world, 98).addNumberIDDataSpawn(entityout.getParasiteIDRegister());
                entityout.func_82149_j((Entity)entityin);
                world.func_72900_e((Entity)entityin);
                entityout.func_180482_a(world.func_175649_E(new BlockPos((Entity)entityout)), null);
                if (entityin.func_145818_k_()) {
                    entityout.func_96094_a(entityin.func_95999_t());
                    entityout.func_174805_g(entityin.func_174833_aM());
                }
                world.func_72838_d((Entity)entityout);
                world.func_180498_a(null, 1026, new BlockPos((Entity)entityout), 0);
                entityout.particleStatus((byte)7);
                entityout.cannotDespawn(SPConfig.convertedDespawn);
            } else if (outOne instanceof EntityLiving) {
                entityout = (EntityLiving)outOne;
                entityout.func_82149_j((Entity)entityin);
                world.func_72900_e((Entity)entityin);
                entityout.func_180482_a(world.func_175649_E(new BlockPos((Entity)entityout)), null);
                if (entityin.func_145818_k_()) {
                    entityout.func_96094_a(entityin.func_95999_t());
                    entityout.func_174805_g(entityin.func_174833_aM());
                }
                world.func_72838_d((Entity)entityout);
                world.func_180498_a(null, 1026, new BlockPos((Entity)entityout), 0);
            }
            flag = false;
        }
        return false;
    }

    public static BlockPos getFloor(World worldIn, BlockPos pos, int loop) {
        if (loop <= 0) {
            return null;
        }
        --loop;
        if (worldIn.func_180495_p(pos).func_177230_c() == Blocks.field_150350_a) {
            if (worldIn.func_180495_p(pos.func_177977_b()).func_177230_c() != Blocks.field_150350_a) {
                return pos;
            }
            return ParasiteEventEntity.getFloor(worldIn, pos.func_177977_b(), loop);
        }
        return ParasiteEventEntity.getFloor(worldIn, pos.func_177984_a(), loop);
    }

    public static BlockPos getFloorBuilding(World worldIn, BlockPos pos, int loop) {
        if (loop <= 0) {
            return null;
        }
        --loop;
        if (!worldIn.func_180495_p(pos).func_185913_b()) {
            if (worldIn.func_180495_p(pos.func_177977_b()).func_185913_b() && !(worldIn.func_180495_p(pos.func_177977_b()).func_177230_c() instanceof BlockBush) && !(worldIn.func_180495_p(pos.func_177977_b()).func_177230_c() instanceof BlockLeaves) && !(worldIn.func_180495_p(pos.func_177977_b()).func_177230_c() instanceof BlockLog)) {
                return pos;
            }
            return ParasiteEventEntity.getFloorBuilding(worldIn, pos.func_177977_b(), loop);
        }
        if (worldIn.func_180495_p(pos).func_177230_c() instanceof BlockBush || worldIn.func_180495_p(pos).func_177230_c() instanceof BlockLeaves || worldIn.func_180495_p(pos).func_177230_c() instanceof BlockLog) {
            return ParasiteEventEntity.getFloorBuilding(worldIn, pos.func_177979_c(1), loop);
        }
        return ParasiteEventEntity.getFloorBuilding(worldIn, pos.func_177984_a(), loop);
    }

    public static boolean spawnTurrets(EntityLivingBase entityin, int range, byte type, int stage) {
        if (stage <= 2) {
            return false;
        }
        if (entityin.field_70170_p.func_175659_aa() == EnumDifficulty.PEACEFUL) {
            return false;
        }
        Random rand = new Random();
        World world = entityin.field_70170_p;
        double randomx = rand.nextInt(range);
        double randomz = rand.nextInt(range);
        double negative = rand.nextInt(2);
        if (negative == 0.0) {
            randomx *= -1.0;
        }
        if ((negative = (double)rand.nextInt(2)) == 0.0) {
            randomz *= -1.0;
        }
        int index = 5;
        int limit = 0;
        boolean flag = true;
        while (flag) {
            if (limit >= 5) {
                return false;
            }
            BlockPos floor = ParasiteEventEntity.getFloor(world, new BlockPos(entityin.field_70165_t + randomx, entityin.field_70163_u, entityin.field_70161_v + randomz), 5);
            if (floor != null && world.func_180495_p(floor.func_177977_b()).func_177230_c() == SPBlocks.InfestedStain) {
                int flag2 = 0;
                AxisAlignedBB axisalignedbb = new AxisAlignedBB((double)floor.func_177958_n(), (double)floor.func_177956_o(), (double)floor.func_177952_p(), (double)(floor.func_177958_n() + 1), (double)(floor.func_177956_o() + 1), (double)(floor.func_177952_p() + 1)).func_72314_b(42.0, 5.0, 42.0);
                List moblist = world.func_72872_a(EntityParasiteBase.class, axisalignedbb);
                for (EntityParasiteBase mob : moblist) {
                    if (!mob.func_70089_S() || mob.getParasiteType() != 40) continue;
                    ++flag2;
                }
                if (flag2 < 3) {
                    EntityPStationary out;
                    if (type == 1) {
                        if (!SPConfigMobs.unvoEnabled) {
                            return false;
                        }
                        out = new EntityUnvo(world);
                        out.func_70012_b(floor.func_177958_n(), floor.func_177956_o(), floor.func_177952_p(), 0.0f, 0.0f);
                        world.func_72838_d((Entity)out);
                        out.func_70624_b(entityin);
                        return true;
                    }
                    if (type == 2) {
                        if (!SPConfigMobs.tonroEnabled) {
                            return false;
                        }
                        out = new EntityTonro(world);
                        out.func_70012_b(floor.func_177958_n(), floor.func_177956_o(), floor.func_177952_p(), 0.0f, 0.0f);
                        world.func_72838_d((Entity)out);
                        out.func_70624_b(entityin);
                        return true;
                    }
                } else {
                    return false;
                }
            }
            randomx = rand.nextInt(range);
            randomz = rand.nextInt(range);
            negative = rand.nextInt(2);
            if (negative == 0.0) {
                randomx *= -1.0;
            }
            if ((negative = (double)rand.nextInt(2)) == 0.0) {
                randomz *= -1.0;
            }
            ++limit;
        }
        return false;
    }

    public static void alertAllPlayerDim(World worldIn, String message, int warning) {
        if (worldIn == null) {
            return;
        }
        List playerEntityList = worldIn.field_73010_i;
        SPMain.network.sendToDimension((IMessage)new SPPacketMovingSound(warning), worldIn.field_73011_w.getDimension());
        if (!message.equals("")) {
            for (EntityPlayer entityPlayer : playerEntityList) {
                entityPlayer.func_145747_a((ITextComponent)new TextComponentString(message));
            }
        }
        if (warning == -7 && message.equals("Phase decreased")) {
            List serverList = worldIn.field_72996_f;
            for (Entity entity : serverList) {
                if (!(entity instanceof EntityParasiteBase)) continue;
                ((EntityParasiteBase)entity).func_70690_d(new PotionEffect(SPPotions.RAGE_E, 2400, 1, false, false));
            }
        }
    }

    public static void alertAllPlayerSer(World w, String message) {
        if (w == null) {
            return;
        }
        List playerEntityList = w.func_73046_m().func_184103_al().func_181057_v();
        for (EntityPlayerMP entityPlayerMP : playerEntityList) {
            entityPlayerMP.func_145747_a((ITextComponent)new TextComponentString(message));
        }
    }

    public static void alertAllPlayerSer(World w, String message, int warning) {
        if (w == null) {
            return;
        }
        List playerEntityList = w.func_73046_m().func_184103_al().func_181057_v();
        SPMain.network.sendToServer((IMessage)new SPPacketMovingSound(warning));
        for (EntityPlayerMP entityPlayerMP : playerEntityList) {
            entityPlayerMP.func_145747_a((ITextComponent)new TextComponentString(message));
        }
    }

    public static boolean spawnFromBlock(World world, String[] out, int range, BlockPos pos) {
        if (!world.field_72995_K) {
            List serverList = world.field_72996_f;
            int count = 0;
            int tenn = 0;
            for (int x = 0; x < serverList.size(); ++x) {
                if (!(serverList.get(x) instanceof EntityParasiteBase)) continue;
                ++count;
                if (serverList.get(x) instanceof EntityTenn) {
                    ++tenn;
                }
                if (count > SPConfig.worldMobCap) {
                    return false;
                }
                if (tenn <= 5) continue;
                return false;
            }
            Random rand = new Random();
            double x = pos.func_177958_n();
            double y = pos.func_177956_o();
            double z = pos.func_177952_p();
            double randomx = rand.nextInt(range);
            double randomz = rand.nextInt(range);
            double negative = rand.nextInt(2);
            if (negative == 0.0) {
                randomx *= -1.0;
            }
            if ((negative = (double)rand.nextInt(2)) == 0.0) {
                randomz *= -1.0;
            }
            int index = rand.nextInt(out.length);
            int limit = 0;
            boolean flag = true;
            while (flag) {
                if (index >= out.length) {
                    index = 0;
                    ++limit;
                }
                if (limit == 2) {
                    return false;
                }
                if (out[index] != null) {
                    String[] entityC = out[index].split(";");
                    double chance = Double.parseDouble(entityC[1]);
                    if (rand.nextDouble() <= chance) {
                        BlockPos helper = ParasiteEventEntity.getFloor(world, new BlockPos(x + randomx, y, z + randomz), 3);
                        if (helper != null) {
                            EntityLiving entityout = (EntityLiving)EntityList.func_188429_b((ResourceLocation)new ResourceLocation(entityC[0]), (World)world);
                            if (entityout == null) {
                                return false;
                            }
                            entityout.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(16.0);
                            entityout.func_70012_b((double)helper.func_177958_n(), (double)helper.func_177956_o(), (double)helper.func_177952_p(), 0.0f, 0.0f);
                            entityout.func_180482_a(world.func_175649_E(helper), null);
                            if (entityout instanceof EntityKol) {
                                EntityKol kol = (EntityKol)entityout;
                                SPWorldData data = SPWorldData.get(world);
                                BlockPos origin = data.nearestColonyPosition(helper, false);
                                if (origin != null) {
                                    kol.setTask(origin, data.getColonyDistanceSpreadByPosition(origin, false));
                                } else {
                                    return false;
                                }
                            }
                            world.func_72838_d((Entity)entityout);
                            flag = false;
                            return true;
                        }
                        randomx = rand.nextInt(range);
                        randomz = rand.nextInt(range);
                        negative = rand.nextInt(2);
                        if (negative == 0.0) {
                            randomx *= -1.0;
                        }
                        if ((negative = (double)rand.nextInt(2)) == 0.0) {
                            randomz *= -1.0;
                        }
                    }
                }
                ++index;
            }
        }
        return false;
    }

    public static void spawnBeckon(World world, DamageSource cause, EntityParasiteBase in) {
        if (in.field_70130_N <= 1.0f && in.field_70131_O <= 1.0f) {
            return;
        }
        if (SPConfigSystems.rsEnabled) {
            List serverList = world.field_72996_f;
            int count = 0;
            for (Entity entity : serverList) {
                if (!(entity instanceof EntityPBeckon) || ++count <= SPConfig.nexusVenkrolCap && !(in.func_70068_e(entity) < (double)(SPConfig.nexusVenkrolDis * SPConfig.nexusVenkrolDis))) continue;
                return;
            }
            SPWorldData data = SPWorldData.get(world);
            if (SPConfigSystems.rsPlayer) {
                if (cause.func_76346_g() instanceof EntityPlayer) {
                    if (SPConfigSystems.useEvolution) {
                        ParasiteEventEntity.spawnBeckonE(data, world, in);
                    } else {
                        ParasiteEventEntity.spawnBeckonNE(data, SPConfigSystems.rschance, world, in);
                    }
                }
            } else if (SPConfigSystems.useEvolution) {
                ParasiteEventEntity.spawnBeckonE(data, world, in);
            } else {
                ParasiteEventEntity.spawnBeckonNE(data, SPConfigSystems.rschance, world, in);
            }
        }
    }

    public static void spawnBeckonNE(SPWorldData data, double chance, World world, EntityParasiteBase in) {
        long worldT = world.func_82737_E();
        long seconds = (worldT - SPAttributes.lastTimeD1) / 20L;
        Random rand = new Random();
        if (rand.nextDouble() < chance && (long)SPConfigSystems.rsCooldown < Math.abs(seconds)) {
            if (SPConfigWorld.originActivated && data.nearestInfectionValue(in.func_180425_c(), false) == -1) {
                return;
            }
            if (ParasiteSummon.SummonM((EntityLivingBase)in, new String[]{ParasiteEventEntity.getRSColony(data)}, 5, 10, in.func_70638_az())) {
                if (SPConfigSystems.rsSounds) {
                    if (SPConfigSystems.disloGrowlNoise) {
                        if (SPSaveData.get(world, 94).getCurrentCode(world.field_73011_w.getDimension(), 15) == 0) {
                            in.func_184185_a(SPSounds.VENKROLSI, 4.0f, 1.0f);
                        }
                    } else {
                        in.func_184185_a(SPSounds.VENKROLSI, 4.0f, 1.0f);
                    }
                }
                SPAttributes.lastTimeD1 = worldT;
            }
        }
    }

    public static String getRSColony(SPWorldData data) {
        if (SPConfigWorld.coloniesActivated) {
            int totalColonyPoints = data.totalColonyPoints(0);
            double bonus = (float)totalColonyPoints / SPConfigWorld.colonyExtraRSChancePoint * SPConfigWorld.colonyExtraRSChanceValue;
            if (bonus > 2.0) {
                return "subspaceparasite:beckon_siii;1;1";
            }
            if (bonus > 1.0) {
                return "subspaceparasite:beckon_sii;1;1";
            }
        }
        return "subspaceparasite:beckon_si;1;1";
    }

    public static void spawnBeckonE(SPWorldData data, World world, EntityParasiteBase in) {
        switch (SPSaveData.get(world, 93).getEvolutionPhase(world.field_73011_w.getDimension())) {
            case 1: {
                ParasiteEventEntity.spawnBeckonNE(data, SPConfigSystems.reinforcementSystemChanceOne, world, in);
                break;
            }
            case 2: {
                ParasiteEventEntity.spawnBeckonNE(data, SPConfigSystems.reinforcementSystemChanceTwo, world, in);
                break;
            }
            case 3: {
                ParasiteEventEntity.spawnBeckonNE(data, SPConfigSystems.reinforcementSystemChanceThree, world, in);
                break;
            }
            case 4: {
                ParasiteEventEntity.spawnBeckonNE(data, SPConfigSystems.reinforcementSystemChanceFour, world, in);
                break;
            }
            case 5: {
                ParasiteEventEntity.spawnBeckonNE(data, SPConfigSystems.reinforcementSystemChanceFive, world, in);
                break;
            }
            case 6: {
                ParasiteEventEntity.spawnBeckonNE(data, SPConfigSystems.reinforcementSystemChanceSix, world, in);
                break;
            }
            case 7: {
                ParasiteEventEntity.spawnBeckonNE(data, SPConfigSystems.reinforcementSystemChanceSeven, world, in);
                break;
            }
            case 8: {
                ParasiteEventEntity.spawnBeckonNE(data, SPConfigSystems.reinforcementSystemChanceEight, world, in);
                break;
            }
            case 9: {
                ParasiteEventEntity.spawnBeckonNE(data, SPConfigSystems.reinforcementSystemChanceNine, world, in);
                break;
            }
            case 10: {
                ParasiteEventEntity.spawnBeckonNE(data, SPConfigSystems.reinforcementSystemChanceTen, world, in);
            }
        }
    }

    public static double getRSchance(World world) {
        switch (SPSaveData.get(world, 92).getEvolutionPhase(world.field_73011_w.getDimension())) {
            case 1: {
                return SPConfigSystems.reinforcementSystemChanceOne;
            }
            case 2: {
                return SPConfigSystems.reinforcementSystemChanceTwo;
            }
            case 3: {
                return SPConfigSystems.reinforcementSystemChanceThree;
            }
            case 4: {
                return SPConfigSystems.reinforcementSystemChanceFour;
            }
            case 5: {
                return SPConfigSystems.reinforcementSystemChanceFive;
            }
            case 6: {
                return SPConfigSystems.reinforcementSystemChanceSix;
            }
            case 7: {
                return SPConfigSystems.reinforcementSystemChanceSeven;
            }
            case 8: {
                return SPConfigSystems.reinforcementSystemChanceEight;
            }
            case 9: {
                return SPConfigSystems.reinforcementSystemChanceNine;
            }
            case 10: {
                return SPConfigSystems.reinforcementSystemChanceTen;
            }
        }
        return 0.0;
    }

    public static boolean alertOthers(EntityParasiteBase pin, EntityLivingBase target, World world, int loop) {
        return false;
    }

    public static void leaveScent(World world, DamageSource cause, EntityParasiteBase in) {
        if (!SPConfigSystems.useScent) {
            return;
        }
        if (SPConfigSystems.scentPlayer ? !(cause.func_76346_g() instanceof EntityPlayer) : !(cause.func_76346_g() instanceof EntityLivingBase)) {
            return;
        }
        if (world.field_73012_v.nextDouble() < SPConfigSystems.scentDeathSpawning) {
            return;
        }
        if (SPConfigSystems.useEvolution && in.getPhaseCreated() < SPConfigSystems.evolutionOneMind && in.getLevelCreated() < SPConfigSystems.deveOnemindUse) {
            return;
        }
        List serverList = world.field_72996_f;
        int count = 0;
        for (Entity entity : serverList) {
            if (!(entity instanceof EntityParasiticScent)) continue;
            ++count;
        }
        if (count > SPConfigSystems.scentCap) {
            return;
        }
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(in.field_70165_t, in.field_70163_u, in.field_70161_v, in.field_70165_t + 1.0, in.field_70163_u + 1.0, in.field_70161_v + 1.0).func_186662_g(64.0);
        List moblist1 = world.func_72872_a(EntityParasiticScent.class, axisalignedbb);
        for (EntityParasiticScent mob : moblist1) {
            Entity source;
            if (!in.func_70685_l(mob)) continue;
            mob.increaseDanger(in.getCCDeathValue(), true);
            mob.increaseActivity(1, true);
            mob.setScentLife(mob.getScentLife() + 20 * SPConfigSystems.scentLifeDeath);
            mob.setScentReaction(ParasiteEventEntity.getScentReactionBonus(in.getPhaseCreated()), false);
            mob.setTargetToKill((EntityLivingBase)cause.func_76346_g(), true);
            if (cause.func_76346_g() instanceof EntityPlayer && (source = cause.func_76346_g()) instanceof EntityPlayer) {
                ((EntityPlayer)source).func_146105_b((ITextComponent)new TextComponentTranslation("srp.msg.scent.closest_notified", new Object[0]), true);
            }
            return;
        }
        axisalignedbb = new AxisAlignedBB(in.field_70165_t, in.field_70163_u, in.field_70161_v, in.field_70165_t + 1.0, in.field_70163_u + 1.0, in.field_70161_v + 1.0).func_186662_g(64.0);
        List moblist2 = world.func_72872_a(EntityParasiteBase.class, axisalignedbb);
        int dangerValue = in.getCCDeathValue() + ParasiteEventEntity.getScentBonus(in.getPhaseCreated());
        if (moblist2.size() <= 3 && in.getPhaseCreated() >= 0 && in.getCCDeathValue() > 2) {
            EntityParasiticScent nut = new EntityParasiticScent(world, 0, (EntityLivingBase)cause.func_76346_g());
            nut.func_82149_j(cause.func_76346_g());
            nut.setScentLife(SPConfigSystems.scentLifeObserver * 20);
            nut.increaseDanger(dangerValue, true);
            nut.setScentReaction(ParasiteEventEntity.getScentReactionBonus(in.getPhaseCreated()), false);
            world.func_72838_d((Entity)nut);
            nut.warnPlayers(I18n.func_74838_a((String)"srp.msg.scent.deployed_area"));
        }
    }

    public static int getScentBonus(byte in) {
        int q = 1;
        if (SPConfigSystems.useEvolution) {
            switch (in) {
                case 0: {
                    q = SPConfigSystems.phaseScentBonusZero;
                    break;
                }
                case 1: {
                    q = SPConfigSystems.phaseScentBonusOne;
                    break;
                }
                case 2: {
                    q = SPConfigSystems.phaseScentBonusTwo;
                    break;
                }
                case 3: {
                    q = SPConfigSystems.phaseScentBonusThree;
                    break;
                }
                case 4: {
                    q = SPConfigSystems.phaseScentBonusFour;
                    break;
                }
                case 5: {
                    q = SPConfigSystems.phaseScentBonusFive;
                    break;
                }
                case 6: {
                    q = SPConfigSystems.phaseScentBonusSix;
                    break;
                }
                case 7: {
                    q = SPConfigSystems.phaseScentBonusSeven;
                    break;
                }
                case 8: {
                    q = SPConfigSystems.phaseScentBonusEight;
                }
            }
        }
        return q;
    }

    public static byte getScentReactionBonus(byte in) {
        byte q = SPConfigSystems.scentGoActive;
        if (SPConfigSystems.useEvolution) {
            switch (in) {
                case 0: {
                    q = SPConfigSystems.phaseScentReactionZero;
                    break;
                }
                case 1: {
                    q = SPConfigSystems.phaseScentReactionOne;
                    break;
                }
                case 2: {
                    q = SPConfigSystems.phaseScentReactionTwo;
                    break;
                }
                case 3: {
                    q = SPConfigSystems.phaseScentReactionThree;
                    break;
                }
                case 4: {
                    q = SPConfigSystems.phaseScentReactionFour;
                    break;
                }
                case 5: {
                    q = SPConfigSystems.phaseScentReactionFive;
                    break;
                }
                case 6: {
                    q = SPConfigSystems.phaseScentReactionSix;
                    break;
                }
                case 7: {
                    q = SPConfigSystems.phaseScentReactionSeven;
                    break;
                }
                case 8: {
                    q = SPConfigSystems.phaseScentReactionEight;
                }
            }
        }
        return q;
    }

    public static void checkColony(World world, DamageSource cause, EntityPMalleable in) {
        if (in.func_70027_ad() || !SPConfigWorld.coloniesActivated) {
            return;
        }
        if (ParasiteEventWorld.numberofColonies(world) <= 0) {
            return;
        }
        double chance = 0.0;
        if (in.func_70644_a(SPPotions.LINK_E)) {
            chance = (double)(in.func_70660_b(SPPotions.LINK_E).func_76458_c() + 1) * SPConfigSystems.adapsChance;
        }
        if (ParasiteEventWorld.rangeOfColony(world, in.func_180425_c(), true) != null || world.field_73012_v.nextDouble() < chance) {
            String da;
            SPWorldData data = SPWorldData.get(world);
            if (in.colonySpawned) {
                in.removeCommonDamage(data.getMostCommonDamageS(), data.getMostCommonDamageI());
            }
            if ((da = in.getMostCommonDamage()) == null) {
                return;
            }
            data.addGlobalResistance(da);
            SPMain.network.sendToAll((IMessage)new SPPacketParticle(in.field_70165_t, in.field_70163_u, in.field_70161_v, in.field_70130_N, in.field_70131_O, 4));
        }
    }

    @Deprecated
    public static SPExplosion createExplotion(World worldIn, @Nullable Entity entityIn, double x, double y, double z, float strength, boolean isSmoking) {
        ModCompatibility.warnAddonDeprecatedFunction("createExplotion(World worldIn,@Nullable Entity entityIn, double x, double y, double z, float strength, boolean isSmoking)", "createExplosion(World worldIn,@Nullable Entity entityIn, double x, double y, double z, float strength, boolean isSmoking)");
        SPExplosion explosion = new SPExplosion(worldIn, entityIn, x, y, z, strength, false, isSmoking);
        if (ForgeEventFactory.onExplosionStart((World)worldIn, (Explosion)explosion)) {
            return explosion;
        }
        explosion.func_77278_a();
        explosion.func_77279_a(false);
        return explosion;
    }

    public static SPExplosion createExplosion(World worldIn, @Nullable Entity entityIn, double x, double y, double z, float strength, boolean isSmoking) {
        SPExplosion explosion = new SPExplosion(worldIn, entityIn, x, y, z, strength, false, isSmoking);
        if (ForgeEventFactory.onExplosionStart((World)worldIn, (Explosion)explosion)) {
            return explosion;
        }
        explosion.func_77278_a();
        explosion.func_77279_a(false);
        return explosion;
    }

    public static boolean teleportDigging(EntityParasiteBase in, float maxHardness, BlockPos posIn, int range, int mini) {
        if (!in.func_70644_a(MobEffects.field_76421_d)) {
            in.func_70690_d(new PotionEffect(MobEffects.field_76421_d, 80, 4, false, false));
            return false;
        }
        Random rand = new Random();
        double x = posIn.func_177958_n();
        double y = posIn.func_177956_o();
        double z = posIn.func_177952_p();
        double randomx = rand.nextInt(range) + mini;
        double randomz = rand.nextInt(range) + mini;
        double negative = rand.nextInt(2);
        if (negative == 0.0) {
            randomx *= -1.0;
        }
        if ((negative = (double)rand.nextInt(2)) == 0.0) {
            randomz *= -1.0;
        }
        int limit = 0;
        boolean flag = true;
        while (flag) {
            if (limit >= 5) {
                return false;
            }
            BlockPos pos = new BlockPos(x + randomx, y, z + randomz);
            if ((pos = ParasiteEventEntity.getFloor(in.field_70170_p, pos, 5)) != null && in.field_70170_p.func_180495_p(pos.func_177977_b()).func_185917_h()) {
                float bHard = 0.0f;
                for (int i = 1; i < 4; ++i) {
                    IBlockState state = in.field_70170_p.func_180495_p(pos.func_177979_c(i));
                    float atm = state.func_185887_b(in.field_70170_p, pos.func_177979_c(i));
                    if (atm <= 0.0f) {
                        return false;
                    }
                    bHard += atm;
                }
                if (bHard >= maxHardness) {
                    return false;
                }
                AxisAlignedBB axisalignedbb = new AxisAlignedBB((double)pos.func_177958_n(), (double)pos.func_177956_o(), (double)pos.func_177952_p(), (double)(pos.func_177958_n() + 1), (double)(pos.func_177956_o() + 1), (double)(pos.func_177952_p() + 1)).func_186662_g(1.0);
                List moblist = in.field_70170_p.func_72872_a(EntityNak.class, axisalignedbb);
                if (moblist.isEmpty()) {
                    in.func_70012_b((double)pos.func_177958_n() + 0.5, pos.func_177956_o(), (double)pos.func_177952_p() + 0.5, in.field_70177_z, in.field_70125_A);
                    flag = false;
                    return true;
                }
            }
            randomx = rand.nextInt(range) + mini;
            randomz = rand.nextInt(range) + mini;
            negative = rand.nextInt(2);
            if (negative == 0.0) {
                randomx *= -1.0;
            }
            if ((negative = (double)rand.nextInt(2)) == 0.0) {
                randomz *= -1.0;
            }
            ++limit;
        }
        return false;
    }

    public static EntityParasiteBase getRandomAssimilated(World world) {
        switch (world.field_73012_v.nextInt(9)) {
            case 0: {
                if (!SPConfigMobs.infbearEnabled) break;
                return new EntityInfBear(world);
            }
            case 1: {
                if (!SPConfigMobs.infcowEnabled) break;
                return new EntityInfCow(world);
            }
            case 2: {
                if (!SPConfigMobs.infendermanEnabled) break;
                return new EntityInfEnderman(world);
            }
            case 3: {
                if (!SPConfigMobs.infhorseEnabled) break;
                return new EntityInfHorse(world);
            }
            case 4: {
                if (!SPConfigMobs.infhumanEnabled) break;
                return new EntityInfHuman(world);
            }
            case 5: {
                if (!SPConfigMobs.infpigEnabled) break;
                return new EntityInfPig(world);
            }
            case 6: {
                if (!SPConfigMobs.infsheepEnabled) break;
                return new EntityInfSheep(world);
            }
            case 7: {
                if (!SPConfigMobs.infvillagerEnabled) break;
                return new EntityInfVillager(world);
            }
            case 8: {
                if (!SPConfigMobs.infwolfEnabled) break;
                return new EntityInfWolf(world);
            }
        }
        return null;
    }

    public static EntityParasiteBase getRandomFeral(World world) {
        switch (world.field_73012_v.nextInt(9)) {
            case 0: {
                if (!SPConfigMobs.ferbearEnabled) break;
                return new EntityFerBear(world);
            }
            case 1: {
                if (!SPConfigMobs.fercowEnabled) break;
                return new EntityFerCow(world);
            }
            case 2: {
                if (!SPConfigMobs.ferendermanEnabled) break;
                return new EntityFerEnderman(world);
            }
            case 3: {
                if (!SPConfigMobs.ferhorseEnabled) break;
                return new EntityFerHorse(world);
            }
            case 4: {
                if (!SPConfigMobs.ferhumanEnabled) break;
                return new EntityFerHuman(world);
            }
            case 5: {
                if (!SPConfigMobs.ferpigEnabled) break;
                return new EntityFerPig(world);
            }
            case 6: {
                if (!SPConfigMobs.fersheepEnabled) break;
                return new EntityFerSheep(world);
            }
            case 7: {
                if (!SPConfigMobs.fervillagerEnabled) break;
                return new EntityFerVillager(world);
            }
            case 8: {
                if (!SPConfigMobs.ferwolfEnabled) break;
                return new EntityFerWolf(world);
            }
        }
        return null;
    }

    public static EntityParasiteBase getRandomAssimara(World world) {
        switch (world.field_73012_v.nextInt(9)) {
            case 1: {
                if (!SPConfigMobs.fercowEnabled) break;
                return new EntitySpeCow(world);
            }
            case 2: {
                if (!SPConfigMobs.ferendermanEnabled) break;
                return new EntitySpeEnderman(world);
            }
            case 4: {
                if (!SPConfigMobs.ferhumanEnabled) break;
                return new EntitySpeHuman(world);
            }
            case 7: {
                if (!SPConfigMobs.fervillagerEnabled) break;
                return new EntitySpeVillager(world);
            }
        }
        return null;
    }

    public static EntityParasiteBase getRandomPrimitive(World world) {
        switch (world.field_73012_v.nextInt(11)) {
            case 0: {
                if (!SPConfigMobs.emanaEnabled) break;
                return new EntityEmana(world);
            }
            case 1: {
                if (!SPConfigMobs.canraEnabled) break;
                return new EntityCanra(world);
            }
            case 2: {
                if (!SPConfigMobs.zetmoEnabled) break;
                return new EntityBano(world);
            }
            case 3: {
                if (!SPConfigMobs.shycoEnabled) break;
                return new EntityShyco(world);
            }
            case 4: {
                if (!SPConfigMobs.arachnidaEnabled) break;
                return new EntityRanrac(world);
            }
            case 5: {
                if (!SPConfigMobs.noglaEnabled) break;
                return new EntityNogla(world);
            }
            case 6: {
                if (!SPConfigMobs.hullEnabled) break;
                return new EntityHull(world);
            }
            case 7: {
                if (!SPConfigMobs.ikiEnabled) break;
                return new EntityIki(world);
            }
            case 8: {
                if (!SPConfigMobs.wymoEnabled) break;
                return new EntityWymo(world);
            }
            case 9: {
                if (!SPConfigMobs.zaaEnabled) break;
                return new EntityZaa(world);
            }
            case 10: {
                if (!SPConfigMobs.gimEnabled) break;
                return new EntityGim(world);
            }
        }
        return null;
    }

    public static EntityParasiteBase getRandomAdapted(World world) {
        switch (world.field_73012_v.nextInt(10)) {
            case 0: {
                if (!SPConfigMobs.emanaEnabled) break;
                return new EntityEmanaAdapted(world);
            }
            case 1: {
                if (!SPConfigMobs.canraEnabled) break;
                return new EntityCanraAdapted(world);
            }
            case 2: {
                if (!SPConfigMobs.zetmoEnabled) break;
                return new EntityBanoAdapted(world);
            }
            case 3: {
                if (!SPConfigMobs.shycoEnabled) break;
                return new EntityShycoAdapted(world);
            }
            case 4: {
                if (!SPConfigMobs.arachnidaEnabled) break;
                return new EntityRanracAdapted(world);
            }
            case 5: {
                if (!SPConfigMobs.noglaEnabled) break;
                return new EntityNoglaAdapted(world);
            }
            case 6: {
                if (!SPConfigMobs.hullEnabled) break;
                return new EntityHullAdapted(world);
            }
            case 7: {
                if (!SPConfigMobs.wymoEnabled) break;
                return new EntityWymoAdapted(world);
            }
            case 8: {
                if (!SPConfigMobs.zaaEnabled) break;
                return new EntityZaaAdapted(world);
            }
            case 9: {
                if (!SPConfigMobs.gimEnabled) break;
                return new EntityGimAdapted(world);
            }
        }
        return null;
    }

    public static EntityParasiteBase getRandomPure(World world) {
        switch (world.field_73012_v.nextInt(7)) {
            case 0: {
                if (!SPConfigMobs.alafhaEnabled) break;
                return new EntityAlafha(world);
            }
            case 1: {
                if (!SPConfigMobs.angedEnabled) break;
                return new EntityAnged(world);
            }
            case 2: {
                if (!SPConfigMobs.esorEnabled) break;
                return new EntityEsor(world);
            }
            case 3: {
                if (!SPConfigMobs.flogEnabled) break;
                return new EntityFlog(world);
            }
            case 4: {
                if (!SPConfigMobs.ganroEnabled) break;
                return new EntityGanro(world);
            }
            case 5: {
                if (!SPConfigMobs.ombooEnabled) break;
                return new EntityOmboo(world);
            }
            case 6: {
                if (!SPConfigMobs.orchEnabled) break;
                return new EntityOrch(world);
            }
        }
        return null;
    }

    public static boolean spawnUnitFromRof(World world, EntityLivingBase target, BlockPos poss, String[] out, int min, int max) {
        if ((poss = ParasiteEventEntity.getFloor(world, poss, 10)) == null) {
            return false;
        }
        EntityRof samuel = new EntityRof(world);
        samuel.func_70107_b((double)poss.func_177958_n() + 0.5, poss.func_177956_o(), (double)poss.func_177952_p() + 0.5);
        if (!samuel.field_70170_p.func_184144_a((Entity)samuel, samuel.func_174813_aQ().func_72321_a(1.0, 7.0, 1.0)).isEmpty()) {
            samuel.func_70106_y();
            return false;
        }
        samuel.setMob(out);
        samuel.maxmob = max;
        samuel.minmob = min;
        samuel.setPeek(true);
        samuel.setBuried();
        samuel.func_70624_b(target);
        world.func_72838_d((Entity)samuel);
        world.func_184133_a(null, samuel.func_180425_c(), SPSounds.ROF_EMERGE, SoundCategory.HOSTILE, 2.0f, 1.0f);
        world.func_72960_a((Entity)samuel, (byte)50);
        samuel.targetScent = target;
        if (target == null) {
            samuel.targetScent = samuel;
        }
        return true;
    }

    public static boolean disloNumber2(World world) {
        List serverList = world.field_72996_f;
        int count = 0;
        EntityLivingBase in = null;
        for (int i = 0; i < serverList.size(); ++i) {
            int atm;
            if (!(serverList.get(i) instanceof EntityLivingBase) || !((EntityLivingBase)serverList.get(i)).func_70644_a(SPPotions.JUGG_E) || count > (atm = ((EntityLivingBase)serverList.get(i)).func_70660_b(SPPotions.JUGG_E).func_76458_c())) continue;
            count = atm;
            in = (EntityLivingBase)serverList.get(i);
        }
        if (in != null && count >= SPConfigSystems.disloSummonByDeathKilling) {
            Random rand = new Random();
            int count2 = SPSaveData.get(world, 99).getCurrentCode(world.field_73011_w.getDimension(), 2);
            for (int loop = 0; loop < 10; ++loop) {
                double randomx = rand.nextInt(4);
                double randomz = rand.nextInt(4);
                if (rand.nextBoolean()) {
                    randomx *= -1.0;
                }
                if (rand.nextBoolean()) {
                    randomz *= -1.0;
                }
                if (!ParasiteEventEntity.spawnUnitFromRof(world, in, new BlockPos(in.field_70165_t + randomx, in.field_70163_u, in.field_70161_v + randomz), new String[]{ParasiteEventEntity.disloNumber2A(count2, world.field_73012_v)}, 0, 0)) continue;
                SPSaveData.get(world, 91).setCurrentCode(world.field_73011_w.getDimension(), 2, 0, 0, world, false, 0);
                return true;
            }
        }
        return false;
    }

    private static String disloNumber2A(int dama, Random rand) {
        int i;
        String[] here;
        int cc = 0;
        for (int i2 = 0; i2 < SPConfigSystems.disloSummonByDeathMobs.length; ++i2) {
            int min;
            if (SPConfigSystems.disloSummonByDeathMobs[i2] == null || (min = Integer.parseInt((here = SPConfigSystems.disloSummonByDeathMobs[i2].split(";"))[0])) >= dama || min <= cc) continue;
            cc = min;
        }
        ArrayList<String> mobList = new ArrayList<String>();
        for (i = 0; i < SPConfigSystems.disloSummonByDeathMobs.length; ++i) {
            if (SPConfigSystems.disloSummonByDeathMobs[i] == null || cc != Integer.parseInt((here = SPConfigSystems.disloSummonByDeathMobs[i].split(";"))[0])) continue;
            mobList.add(here[1]);
        }
        if (mobList.isEmpty()) {
            for (i = 0; i < SPConfigSystems.disloSummonByDeathMobs.length; ++i) {
                if (SPConfigSystems.disloSummonByDeathMobs[i] == null || (here = SPConfigSystems.disloSummonByDeathMobs[i].split(";")).length <= 1) continue;
                return here[1];
            }
            return "subspaceparasite:warden";
        }
        return (String)mobList.get(rand.nextInt(mobList.size()));
    }

    public static boolean disloNumber5(EntityLivingBase target, World world) {
        return false;
    }
}

