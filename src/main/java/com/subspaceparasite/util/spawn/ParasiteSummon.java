/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.EntitySelectors
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.subspaceparasite.util.spawn;

import com.subspaceparasite.entity.ai.misc.EntityCanSummon;
import com.subspaceparasite.entity.ai.misc.EntityPBeckon;
import com.subspaceparasite.entity.ai.misc.EntityPStationaryArchitect;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityVenkrol;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityVenkrolSII;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityVenkrolSIII;
import com.subspaceparasite.entity.projectile.EntityDropPod;
import com.subspaceparasite.entity.projectile.EntityMeteor;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.network.MsgQlipShake;
import com.subspaceparasite.network.SPNetwork;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.util.config.SPConfigWorld;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class ParasiteSummon {
    public static void spawnM(EntityParasiteBase entityin, String[] out, int particle, boolean cannotDespawn, String name) {
        if (!entityin.field_70170_p.field_72995_K) {
            Random rand = new Random();
            double x = entityin.field_70165_t;
            double y = entityin.field_70163_u;
            double z = entityin.field_70161_v;
            for (String s : out) {
                if (s == null) continue;
                String[] entityC = s.split(";");
                int max = Integer.parseInt(entityC[1]);
                int min = Integer.parseInt(entityC[2]);
                int total = min == max ? min : rand.nextInt(max - min + 1) + min;
                ResourceLocation entityR = new ResourceLocation(entityC[0]);
                for (int count = 0; count < total; ++count) {
                    EntityLiving entityout = (EntityLiving)EntityList.func_188429_b((ResourceLocation)entityR, (World)entityin.field_70170_p);
                    if (entityout == null) {
                        return;
                    }
                    entityout.func_70012_b(x, y + (double)(entityin.field_70131_O / 2.0f) + 0.5, z, entityin.field_70177_z, entityin.field_70125_A);
                    entityout.func_180482_a(entityin.field_70170_p.func_175649_E(new BlockPos((Entity)entityout)), null);
                    if (entityout instanceof EntityParasiteBase && cannotDespawn) {
                        EntityParasiteBase parasite = (EntityParasiteBase)entityout;
                        parasite.cannotDespawn(SPConfig.convertedDespawn);
                        entityin.field_70170_p.func_72838_d((Entity)parasite);
                        continue;
                    }
                    entityin.field_70170_p.func_72838_d((Entity)entityout);
                }
            }
        }
    }

    public static boolean SummonM(EntityLivingBase entityin, String[] out, int minRange, int maxRange, @Nullable EntityLivingBase target) {
        if (!entityin.field_70170_p.field_72995_K) {
            Random rand = new Random();
            double x = entityin.field_70165_t;
            double y = entityin.field_70163_u;
            double z = entityin.field_70161_v;
            double randomx = rand.nextInt(maxRange - minRange + 1) + minRange;
            double randomz = rand.nextInt(maxRange - minRange + 1) + minRange;
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
                        randomx = rand.nextInt(maxRange - minRange + 1) + minRange;
                        randomz = rand.nextInt(maxRange - minRange + 1) + minRange;
                        if (rand.nextBoolean()) {
                            randomx *= -1.0;
                        }
                        if (rand.nextBoolean()) {
                            randomz *= -1.0;
                        }
                        if (entityin.field_70170_p.func_180495_p(new BlockPos(x + randomx, y, z + randomz)).func_177230_c() == Blocks.field_150350_a && entityin.field_70170_p.func_180495_p(new BlockPos(x + randomx, y - 1.0, z + randomz)).func_177230_c() != Blocks.field_150350_a) {
                            EntityLiving entityout = (EntityLiving)EntityList.func_188429_b((ResourceLocation)new ResourceLocation(entityC[0]), (World)entityin.field_70170_p);
                            if (entityout == null) {
                                return false;
                            }
                            entityout.func_70012_b(x + randomx, y, z + randomz, entityin.field_70177_z, entityin.field_70125_A);
                            if (!entityin.field_70170_p.func_184144_a((Entity)entityout, entityout.func_174813_aQ()).isEmpty()) {
                                entityout.func_70106_y();
                                ++index;
                                continue;
                            }
                            if (!entityin.field_70170_p.func_175674_a((Entity)entityout, entityout.func_174813_aQ().func_186662_g(0.25), EntitySelectors.field_94557_a).isEmpty()) {
                                entityout.func_70106_y();
                                ++index;
                                continue;
                            }
                            entityout.func_180482_a(entityin.field_70170_p.func_175649_E(new BlockPos((Entity)entityout)), null);
                            if (entityin instanceof EntityCanSummon) {
                                EntityCanSummon father = (EntityCanSummon)entityin;
                                int points = Integer.parseInt(entityC[2]);
                                if (father.getTotalParasites() - father.getActualParasites() < points) {
                                    entityout.func_70106_y();
                                    ++index;
                                    continue;
                                }
                                father.setActualParasites(points);
                                father.addID(entityout.func_145782_y(), points);
                            }
                            if (entityout instanceof EntityPStationaryArchitect) {
                                if (SPConfigSystems.rsSky && !entityout.field_70170_p.func_175678_i(new BlockPos(entityout.field_70165_t, entityout.field_70163_u + (double)entityout.func_70047_e(), entityout.field_70161_v))) {
                                    entityout.func_70106_y();
                                    ++index;
                                    continue;
                                }
                                switch (((EntityPStationaryArchitect)entityout).getParasiteIDRegister()) {
                                    case 16: {
                                        EntityVenkrol vOut = (EntityVenkrol)entityout;
                                        entityin.field_70170_p.func_72838_d((Entity)vOut);
                                        if (target != null) {
                                            vOut.func_70624_b(target);
                                        }
                                        entityin.field_70170_p.func_72960_a((Entity)vOut, (byte)50);
                                        break;
                                    }
                                    case 18: {
                                        EntityVenkrolSII vOutii = (EntityVenkrolSII)entityout;
                                        entityin.field_70170_p.func_72838_d((Entity)vOutii);
                                        if (target != null) {
                                            vOutii.func_70624_b(target);
                                        }
                                        entityin.field_70170_p.func_72960_a((Entity)vOutii, (byte)50);
                                        break;
                                    }
                                    case 19: {
                                        EntityVenkrolSIII vOutiii = (EntityVenkrolSIII)entityout;
                                        entityin.field_70170_p.func_72838_d((Entity)vOutiii);
                                        if (target != null) {
                                            vOutiii.func_70624_b(target);
                                        }
                                        entityin.field_70170_p.func_72960_a((Entity)vOutiii, (byte)50);
                                        break;
                                    }
                                    default: {
                                        entityin.field_70170_p.func_72838_d((Entity)entityout);
                                        if (target == null) break;
                                        entityout.func_70624_b(target);
                                    }
                                }
                                return true;
                            }
                            entityin.field_70170_p.func_72838_d((Entity)entityout);
                            if (target != null) {
                                entityout.func_70624_b(target);
                            }
                            flag = false;
                            return true;
                        }
                    }
                }
                ++index;
            }
        }
        return false;
    }

    public static boolean SummonM(EntityParasiteBase entityin, String[] out, int range, double tx, double ty, double tz, @Nullable EntityLivingBase target, boolean pod) {
        if (!entityin.field_70170_p.field_72995_K) {
            Random rand = new Random();
            double randomx = rand.nextInt(range);
            double randomz = rand.nextInt(range);
            if (rand.nextBoolean()) {
                randomx *= -1.0;
            }
            if (rand.nextBoolean()) {
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
                        if (entityin.field_70170_p.func_180495_p(new BlockPos(tx + randomx, ty, tz + randomz)).func_177230_c() == Blocks.field_150350_a && entityin.field_70170_p.func_180495_p(new BlockPos(tx + randomx, ty - 1.0, tz + randomz)).func_177230_c() != Blocks.field_150350_a || pod) {
                            if (pod) {
                                EntityDropPod entityout2 = new EntityDropPod(entityin.field_70170_p);
                                entityout2.func_70012_b(tx + randomx, ty, tz + randomz, entityin.field_70177_z, entityin.field_70125_A);
                                entityout2.setOwner(entityin.getParasiteType());
                                entityin.field_70170_p.func_72838_d((Entity)entityout2);
                                flag = false;
                                return true;
                            }
                            EntityLiving entityout = (EntityLiving)EntityList.func_188429_b((ResourceLocation)new ResourceLocation(entityC[0]), (World)entityin.field_70170_p);
                            if (entityout == null) {
                                return false;
                            }
                            entityout.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(16.0);
                            entityout.func_70012_b(tx + randomx, ty, tz + randomz, entityin.field_70177_z, entityin.field_70125_A);
                            entityout.func_180482_a(entityin.field_70170_p.func_175649_E(new BlockPos((Entity)entityout)), null);
                            if (entityin instanceof EntityCanSummon) {
                                EntityCanSummon father = (EntityCanSummon)((Object)entityin);
                                int points = Integer.parseInt(entityC[2]);
                                if (father.getTotalParasites() - father.getActualParasites() < points) {
                                    ++index;
                                    continue;
                                }
                                father.setActualParasites(points);
                                father.addID(entityout.func_145782_y(), points);
                            }
                            if (entityin.getParasiteIDRegister() == 41) {
                                if (entityout instanceof EntityPBeckon) {
                                    EntityPBeckon out2 = (EntityPBeckon)entityout;
                                    out2.setCanGrowTo(false);
                                    out2.setLifeB(300);
                                    out2.field_70170_p.func_72960_a((Entity)out2, (byte)50);
                                }
                            } else if (entityout instanceof EntityVenkrol) {
                                Block looking = entityin.field_70170_p.func_180495_p(entityout.func_180425_c().func_177977_b()).func_177230_c();
                                if (looking == SPBlocks.InfestedStain) {
                                    return false;
                                }
                                entityout.field_70170_p.func_72960_a((Entity)entityout, (byte)50);
                            }
                            entityin.field_70170_p.func_72838_d((Entity)entityout);
                            if (target != null) {
                                entityout.func_70624_b(target);
                            }
                            flag = false;
                            if (entityout instanceof EntityParasiteBase) {
                                EntityParasiteBase eOut = (EntityParasiteBase)entityout;
                                eOut.setParasiteToFollow(entityin);
                            }
                            return true;
                        }
                        randomx = rand.nextInt(range);
                        randomz = rand.nextInt(range);
                        if (rand.nextBoolean()) {
                            randomx *= -1.0;
                        }
                        if (rand.nextBoolean()) {
                            randomz *= -1.0;
                        }
                    }
                }
                ++index;
            }
        }
        return false;
    }

    public static void spawnMeteor(BlockPos pos, int rad, int minRad, World world) {
        ParasiteSummon.spawnMeteor(pos.func_177958_n(), pos.func_177956_o(), pos.func_177952_p(), rad, minRad, world);
    }

    public static void spawnMeteor(int oX, int oY, int oZ, int rad, int minRad, World world) {
        if (rad > SPConfigWorld.meteorRadius) {
            rad = SPConfigWorld.meteorRadius;
        }
        if (minRad < SPConfigWorld.meteorMinRadius) {
            minRad = SPConfigWorld.meteorMinRadius;
        }
        if (rad < minRad) {
            rad = minRad + 1;
        }
        int min = minRad;
        int max = rad;
        int origenX = world.field_73012_v.nextInt(max - min + 1) + min;
        if (world.field_73012_v.nextBoolean()) {
            origenX *= -1;
        }
        int origenY = world.func_72940_L();
        int origenZ = world.field_73012_v.nextInt(max - min + 1) + min;
        if (world.field_73012_v.nextBoolean()) {
            origenZ *= -1;
        }
        int targetX = world.field_73012_v.nextInt(max - min + 1) + min;
        if (world.field_73012_v.nextBoolean()) {
            targetX *= -1;
        }
        int targetZ = world.field_73012_v.nextInt(max - min + 1) + min;
        if (world.field_73012_v.nextBoolean()) {
            targetZ *= -1;
        }
        ParasiteSummon.spawnMeteor(oX + origenX, origenY, oZ + origenZ, oX + targetX, oY, oZ + targetZ, world);
    }

    public static void spawnMeteor(int oX, int oY, int oZ, int tX, int tY, int tZ, World world) {
        double tarX = tX;
        double tarY = tY;
        double tarZ = tZ;
        double dx = tarX - (double)oX;
        double dy = tarY - (double)oY;
        double dz = tarZ - (double)oZ;
        double len = Math.sqrt(dx * dx + dy * dy + dz * dz);
        double speed = 10.5;
        double motionX = (dx /= len) * speed;
        double motionY = (dy /= len) * speed;
        double motionZ = (dz /= len) * speed;
        EntityMeteor meteor = new EntityMeteor(world, oX, oY, oZ, motionX, motionY, motionZ);
        world.func_72838_d((Entity)meteor);
        for (EntityPlayer mob : world.field_73010_i) {
            SPNetwork.CHANNEL.sendTo((IMessage)new MsgQlipShake(0, 0, true, false, 0.0f), (EntityPlayerMP)mob);
        }
    }
}

