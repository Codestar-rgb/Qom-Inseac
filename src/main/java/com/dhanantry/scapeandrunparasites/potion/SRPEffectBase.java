/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.dhanantry.scapeandrunparasites.potion;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.client.particle.ParticleSpawner;
import com.dhanantry.scapeandrunparasites.client.particle.SRPEnumParticle;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.item.ItemInfestedBonemeal;
import com.dhanantry.scapeandrunparasites.network.SRPPacketParticle;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventWorld;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SRPEffectBase
extends Potion {
    private ResourceLocation icon;

    public SRPEffectBase(String name, boolean isBadEffectIn, int liquidColorIn, int IconIndexX, int IconIndexY) {
        super(isBadEffectIn, liquidColorIn);
        this.setRegistryName("srparasites:" + name);
        this.func_76390_b("mob_effect." + this.getRegistryName());
        this.func_76399_b(IconIndexX, IconIndexY);
        this.icon = new ResourceLocation("srparasites:textures/gui/potion_" + name + ".png");
    }

    public boolean func_76400_d() {
        return false;
    }

    public List<ItemStack> getCurativeItems() {
        return new ArrayList<ItemStack>();
    }

    public boolean func_76397_a(int duration, int amplifier) {
        if (this == SRPPotions.BLEED_E || this == SRPPotions.EPEL_E || this == SRPPotions.DLER_E || this == SRPPotions.CORRO_E || this == SRPPotions.FOSTER_E || this == SRPPotions.CONTA_E) {
            int j = 25 >> amplifier;
            if (j > 0) {
                return duration % j == 0;
            }
            return true;
        }
        return true;
    }

    public void func_76394_a(EntityLivingBase entity, int amplifier) {
        if (entity.field_70170_p.field_72995_K) {
            if (this == SRPPotions.RAGE_E) {
                for (int i = 0; i <= 3; ++i) {
                    this.spawnParticles(SRPEnumParticle.GCLOUD, new Random(), entity);
                }
            }
        } else if (this == SRPPotions.COTH_E) {
            this.effectCOTH(entity, amplifier);
        } else if (this == SRPPotions.INDEAF_E) {
            entity.field_70159_w = 0.0;
            entity.field_70179_y = 0.0;
            if (entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer)entity;
                player.field_191988_bg = 0.0f;
                player.field_70702_br = 0.0f;
            }
        } else if (this == SRPPotions.EFFECTPOS_E) {
            this.effectEffectPos(entity, amplifier);
        } else if (this == SRPPotions.EFFECTNEG_E) {
            this.effectEffectNeg(entity, amplifier);
        }
    }

    private void spawnParticles(SRPEnumParticle particleType, Random rand, EntityLivingBase entity) {
        double d0 = rand.nextGaussian() * 0.02;
        double d1 = rand.nextGaussian() * 0.02;
        double d2 = rand.nextGaussian() * 0.02;
        ParticleSpawner.spawnParticle(particleType, entity.field_70165_t + (double)(rand.nextFloat() * entity.field_70130_N * 2.0f) - (double)entity.field_70130_N, entity.field_70163_u + 0.5 + (double)(rand.nextFloat() * entity.field_70131_O), entity.field_70161_v + (double)(rand.nextFloat() * entity.field_70130_N * 2.0f) - (double)entity.field_70130_N, d0, d1, d2, 0, 0, 0);
    }

    private void InfectNearby(EntityLivingBase entity, int range) {
        if (SRPConfigSystems.cothAura == 0) {
            return;
        }
        if (entity.func_70644_a(SRPPotions.EPEL_E)) {
            return;
        }
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            if (player.field_71075_bZ.field_75098_d) {
                return;
            }
        }
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, entity.field_70165_t + 1.0, entity.field_70163_u + 1.0, entity.field_70161_v + 1.0).func_186662_g((double)range);
        List moblist = entity.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
        for (EntityLivingBase mob : moblist) {
            if (mob == entity || mob.func_70644_a(SRPPotions.COTH_E) || mob.func_70644_a(SRPPotions.EPEL_E)) continue;
            mob.func_70690_d(new PotionEffect(SRPPotions.COTH_E, 4800, 0, false, false));
        }
    }

    private void effectCOTH(EntityLivingBase entity, int amplifier) {
        boolean flagPr = entity instanceof EntityPlayer;
        if (!SRPConfigSystems.cothPlayer && flagPr) {
            return;
        }
        boolean flag = !entity.field_70170_p.field_72995_K;
        boolean particle = false;
        int dur = entity.func_70660_b(SRPPotions.COTH_E).func_76459_b();
        if (SRPConfigSystems.disloCOTHIgnoreAmp && amplifier <= 1 && entity.field_70173_aa % 20 == 0 && SRPSaveData.get(entity.field_70170_p, 106).getCurrentCode(entity.field_70170_p.field_73011_w.getDimension(), 0) > 0) {
            entity.func_70690_d(new PotionEffect(SRPPotions.COTH_E, 6666, 10));
            NBTTagCompound tags = entity.getEntityData();
            if (tags.func_74764_b("srpcothimmunity") && tags.func_74762_e("srpcothimmunity") != 0) {
                ParasiteEventEntity.convertEntity(entity, tags, false, SRPConfigSystems.COTHVictimParasite);
            }
        }
        if (SRPConfigSystems.COTHPopping && amplifier >= 2) {
            EntityPlayer player;
            if (entity instanceof EntityPlayer && !((player = (EntityPlayer)entity).func_110143_aJ() > 0.0f)) {
                BlockPos pos = new BlockPos(player.field_70165_t, player.field_70163_u - 1.0, player.field_70161_v);
                if (player.field_70170_p.func_175623_d(pos)) {
                    return;
                }
                ItemInfestedBonemeal.boneMealEffect(player.field_70170_p, pos, EnumFacing.DOWN);
                player.field_70170_p.func_184133_a(null, pos, SoundEvents.field_187929_hc, SoundCategory.BLOCKS, 1.0f, 1.0f);
                player.func_184596_c(SRPPotions.COTH_E);
            }
            return;
        }
        boolean tickFlag = entity.field_70173_aa % 20 == 0;
        switch (amplifier) {
            case 0: {
                if (!flag) break;
                this.effectCOTHextendDuration(dur, 200, entity, amplifier, flagPr, true, true);
                break;
            }
            case 1: {
                if (!flag) break;
                if (tickFlag) {
                    this.InfectNearby(entity, SRPConfigSystems.cothAura);
                    this.effectCOTHextendDuration(dur, 200, entity, amplifier, flagPr, true, true);
                }
                particle = this.effectCOTHTransform(entity, dur, tickFlag, flagPr, true);
                break;
            }
            default: {
                if (!flag) break;
                if (tickFlag) {
                    this.InfectNearby(entity, SRPConfigSystems.cothAura);
                    this.effectCOTHextendDuration(dur, 200, entity, amplifier, flagPr, true, true);
                }
                particle = this.effectCOTHTransform(entity, dur, tickFlag, flagPr, false);
            }
        }
        if (particle) {
            SRPMain.network.sendToAll((IMessage)new SRPPacketParticle(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, entity.field_70130_N, entity.field_70131_O, 1));
        }
    }

    private void effectCOTHextendDuration(int duration, int durationCheck, EntityLivingBase entity, int amplifier, boolean flagPr, boolean origin, boolean evoPoints) {
        if (duration < durationCheck) {
            int cothDur = 3600;
            amplifier = Math.min(amplifier + 1, 2);
            NBTTagCompound tags = entity.getEntityData();
            if (flagPr) {
                entity.func_70690_d(new PotionEffect(SRPPotions.COTH_E, cothDur, amplifier, false, false));
            } else if (tags.func_74764_b("srpcothimmunity")) {
                if (tags.func_74762_e("srpcothimmunity") != 0) {
                    entity.func_70690_d(new PotionEffect(SRPPotions.COTH_E, cothDur, amplifier, false, false));
                    if (SRPConfigWorld.originActivated && origin) {
                        ParasiteEventWorld.setOriginInHealth(entity.field_70170_p, entity.func_180425_c(), (int)((double)entity.func_110138_aP() * SRPConfigWorld.originCOTHMultiplier), true);
                    }
                    if (SRPConfigSystems.useEvolution) {
                        SRPSaveData data = SRPSaveData.get(entity.field_70170_p, 107);
                        if (evoPoints) {
                            data.setTotalKills(entity.field_70170_p.field_73011_w.getDimension(), SRPConfigSystems.valueCOTH, true, entity.field_70170_p, true, 52);
                        }
                        if (data.getEvolutionPhase(entity.field_70170_p.field_73011_w.getDimension()) >= SRPConfigSystems.evolutionAssimilatedDehiding) {
                            int key = tags.func_74762_e("srpcothimmunity");
                            tags.func_74768_a("srpcothimmunity", ++key);
                        }
                    }
                } else {
                    entity.func_184596_c(SRPPotions.COTH_E);
                }
            } else {
                entity.func_184596_c(SRPPotions.COTH_E);
            }
        }
    }

    private boolean effectCOTHTransform(EntityLivingBase entity, int duration, boolean tickFlag, boolean flagPr, boolean insider) {
        if (flagPr) {
            return false;
        }
        boolean flagReturn = false;
        NBTTagCompound tags = entity.getEntityData();
        if (tags.func_74764_b("srpcothimmunity")) {
            int key = tags.func_74762_e("srpcothimmunity");
            if (entity.func_110138_aP() * SRPConfigSystems.cothUnhide > entity.func_110143_aJ() && entity.func_110143_aJ() > 0.0f || key > 1) {
                flagReturn = true;
                if (insider && tickFlag) {
                    ParasiteEventEntity.spawnInsider(entity, entity.field_70170_p, tags);
                } else if (tickFlag) {
                    ParasiteEventEntity.convertEntity(entity, tags, false, SRPConfigSystems.COTHVictimParasite);
                }
            }
        } else {
            entity.func_184596_c(SRPPotions.COTH_E);
        }
        return flagReturn;
    }

    protected BlockPos getRandomPosInCircle(Random rand, BlockPos center, double radius) {
        double theta = rand.nextDouble() * 2.0 * Math.PI;
        double r = Math.sqrt(rand.nextDouble()) * radius;
        int dx = (int)Math.floor(Math.cos(theta) * r);
        int dz = (int)Math.floor(Math.sin(theta) * r);
        return center.func_177982_a(dx, 0, dz);
    }

    private void effectEffectPos(EntityLivingBase entity, int amplifier) {
        if (entity.field_70173_aa % 20 != 0) {
            return;
        }
        for (PotionEffect pe : entity.func_70651_bq()) {
            Potion p = pe.func_188419_a();
            if (p.func_76398_f()) continue;
            entity.func_70097_a(DamageSource.field_82727_n, 0.5f * (float)(pe.func_76458_c() + 1));
        }
    }

    private void effectEffectNeg(EntityLivingBase entity, int amplifier) {
        if (entity.field_70173_aa % 20 != 0) {
            return;
        }
        for (PotionEffect pe : entity.func_70651_bq()) {
            Potion p = pe.func_188419_a();
            if (!p.func_76398_f()) continue;
            SRPPotions.applyStackPotion(p, entity, 20, amplifier);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
        if (mc.field_71462_r != null) {
            mc.func_110434_K().func_110577_a(this.icon);
            Gui.func_146110_a((int)(x + 6), (int)(y + 7), (float)0.0f, (float)0.0f, (int)18, (int)18, (float)18.0f, (float)18.0f);
        }
    }

    public void renderHUDEffect(int x, int y, PotionEffect effect, Minecraft mc, float alpha) {
        mc.func_110434_K().func_110577_a(this.icon);
        Gui.func_146110_a((int)(x + 3), (int)(y + 3), (float)0.0f, (float)0.0f, (int)18, (int)18, (float)18.0f, (float)18.0f);
    }
}

