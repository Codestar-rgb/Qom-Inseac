/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.passive.EntityWaterMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.MobEffects
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.util.text.translation.I18n
 *  net.minecraft.world.BossInfo$Color
 *  net.minecraft.world.BossInfo$Overlay
 *  net.minecraft.world.BossInfoServer
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.EnumSkyBlock
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 */
package com.dhanantry.scapeandrunparasites.entity;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityPStationary;
import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.entity.monster.EntityBiomass;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.network.SRPPacketMovingSound;
import com.dhanantry.scapeandrunparasites.util.ParasiteEventEntity;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class EntityParasiticScent
extends Entity {
    private final BossInfoServer bossInfo = (BossInfoServer)new BossInfoServer(this.func_145748_c_(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS).func_186741_a(false);
    private boolean phaseI = true;
    private byte scentState;
    private int lifeTicks = 600;
    private int currentL;
    private int dangerToUs = 100;
    private byte active;
    private int delay;
    private int timerTick;
    private byte scentLevel;
    private byte scentReaction;
    private int minwave;
    private int maxwave;
    private int minmob;
    private int maxmob;
    private boolean followTargetScent = false;
    private boolean dieAfterKilling;
    private boolean hasCheckedForOthers;
    private byte loopLife = (byte)103;
    private byte failing;
    private static final String SCENT_HOST_TAG = "SRPScentBuffed";
    private double originalHostMaxHealth = -1.0;
    private boolean hostBuffApplied = false;
    private EntityLivingBase targetScent;
    private EntityLivingBase hostLiving;

    public void func_96094_a(String name) {
        super.func_96094_a(name);
        this.bossInfo.func_186739_a(this.func_145748_c_());
    }

    public void func_184178_b(EntityPlayerMP player) {
        super.func_184178_b(player);
        this.bossInfo.func_186760_a(player);
        List playerEntityList = this.field_70170_p.field_73010_i;
        for (EntityPlayer entityPlayer : playerEntityList) {
            SRPMain.network.sendToDimension((IMessage)new SRPPacketMovingSound(102), this.field_70170_p.field_73011_w.getDimension());
        }
    }

    public void func_184203_c(EntityPlayerMP player) {
        super.func_184203_c(player);
        this.bossInfo.func_186761_b(player);
        List playerEntityList = this.field_70170_p.field_73010_i;
        for (EntityPlayer entityPlayer : playerEntityList) {
            SRPMain.network.sendToDimension((IMessage)new SRPPacketMovingSound(103), this.field_70170_p.field_73011_w.getDimension());
        }
    }

    public EntityParasiticScent(World worldIn) {
        super(worldIn);
    }

    public EntityParasiticScent(World worldIn, int status) {
        this(worldIn);
        this.scentState = (byte)status;
    }

    public EntityParasiticScent(World worldIn, int status, EntityLivingBase tar) {
        this(worldIn, status);
        this.setTargetToKill(tar, false);
    }

    protected void func_70088_a() {
    }

    public void func_70071_h_() {
        EntityPlayer pa;
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            return;
        }
        if (this.phaseI) {
            if (this.currentL != 600) {
                ++this.currentL;
            }
            if (this.hostLiving == null) {
                AxisAlignedBB axisalignedbb;
                List moblist;
                if (this.currentL % 20 == 0 && this.currentL >= 300 && !(moblist = this.field_70170_p.func_72872_a(EntityParasiteBase.class, axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_186662_g(80.0))).isEmpty()) {
                    EntityParasiteBase pickedHost = (EntityParasiteBase)moblist.get(this.field_70170_p.field_73012_v.nextInt(moblist.size()));
                    if (pickedHost.getEntityData().func_74767_n(SCENT_HOST_TAG)) {
                        return;
                    }
                    this.hostLiving = pickedHost;
                    this.hostLiving.func_70690_d(new PotionEffect(MobEffects.field_188423_x, this.currentL, 3, false, true));
                    if (this.hostLiving.func_110148_a(SharedMonsterAttributes.field_111267_a) != null) {
                        this.originalHostMaxHealth = this.hostLiving.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b();
                        double newMax = this.originalHostMaxHealth * 10.0;
                        this.hostLiving.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(newMax);
                        this.hostLiving.func_70606_j((float)newMax);
                        this.hostLiving.getEntityData().func_74757_a(SCENT_HOST_TAG, true);
                        this.hostBuffApplied = true;
                    }
                }
            } else if (!this.hostLiving.func_70089_S()) {
                this.func_70106_y();
                return;
            }
            this.bossInfo.func_186735_a(((float)this.currentL + 1.0f) / (float)this.lifeTicks);
            if (this.currentL == 600) {
                --this.dangerToUs;
                if (this.dangerToUs == 0) {
                    this.phaseI = false;
                }
            }
            return;
        }
        float hea = ((float)this.currentL + 1.0f) / (float)this.lifeTicks;
        this.bossInfo.func_186735_a(hea);
        if (this.scentState >= 5) {
            this.currentL -= 20;
        }
        if (this.currentL <= 0 || this.field_70170_p.func_175659_aa() == EnumDifficulty.PEACEFUL || this.loopLife < 0 || this.hostLiving == null) {
            this.func_70106_y();
            return;
        }
        if (!this.hostLiving.func_70089_S()) {
            this.func_70106_y();
            return;
        }
        if (this.getTargetToKill() == null) {
            if (this.scentState > 1) {
                this.scentState = 1;
                return;
            }
        } else if (this.getTargetToKill().func_70068_e((Entity)this) > 4096.0) {
            if (this.dieAfterKilling) {
                this.func_70106_y();
                return;
            }
            this.setTargetToKill(null, false);
            this.scentState = 1;
            return;
        }
        if (this.delay > 0) {
            --this.delay;
            return;
        }
        this.scentFollower();
        if (this.getTargetToKill() instanceof EntityPlayer && ((pa = (EntityPlayer)this.getTargetToKill()).func_175149_v() || pa.func_184812_l_())) {
            this.setTargetToKill(null, false);
            return;
        }
        switch (this.scentState) {
            case 0: {
                if (this.field_70146_Z.nextInt(3) == 0) {
                    this.scentObserver();
                }
                this.scentListener();
                break;
            }
            case 1: {
                this.scentObserver();
                this.scentListener();
                break;
            }
            case 4: {
                this.scentTactical();
                break;
            }
            case 5: {
                this.scentAttacker();
                break;
            }
            case 6: {
                this.scentBuilder();
            }
        }
    }

    private void scentListener() {
        if (this.active >= this.scentReaction && this.getTargetToKill() != null) {
            this.scentState = (byte)4;
            this.active = (byte)2;
            this.warnPlayers(I18n.func_74838_a((String)"srp.msg.scent.active"));
        }
    }

    private void scentObserver() {
        if (this.field_70173_aa % 20 != 0) {
            return;
        }
        if (this.followTargetScent) {
            return;
        }
        if (this.getTargetToKill() != null) {
            this.active = (byte)(this.active + 1);
            AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_186662_g(80.0);
            List moblist = this.field_70170_p.func_72872_a(EntityParasiteBase.class, axisalignedbb);
            for (EntityParasiteBase mob : moblist) {
                double ra;
                if (!mob.func_70089_S()) continue;
                if (mob.func_70638_az() == null) {
                    ra = mob.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111126_e();
                    if (!(mob.func_70068_e((Entity)this.getTargetToKill()) < ra * ra)) continue;
                    mob.func_70624_b(this.getTargetToKill());
                    continue;
                }
                if (mob.func_70638_az().func_70089_S()) continue;
                ra = mob.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111126_e();
                if (!(mob.func_70068_e((Entity)this.getTargetToKill()) < ra * ra)) continue;
                mob.func_70624_b(this.getTargetToKill());
            }
            return;
        }
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_186662_g(80.0);
        List moblist = this.field_70170_p.func_72872_a(EntityParasiteBase.class, axisalignedbb);
        int parasites = moblist.size();
        if (parasites < (moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb)).size()) {
            EntityLivingBase target = null;
            double dis = 40000.0;
            for (EntityLivingBase mob : moblist) {
                EntityPlayer qqq;
                double atm;
                if (mob instanceof EntityParasiteBase || mob instanceof EntityWaterMob || mob instanceof EntityCreeper || (atm = mob.func_70068_e((Entity)this)) > 4096.0 || !this.checkAttri(mob) || mob instanceof EntityPlayer && ((qqq = (EntityPlayer)mob).func_184812_l_() || qqq.func_175149_v()) || !(atm < dis)) continue;
                dis = atm;
                target = mob;
            }
            this.setTargetToKill(target, false);
        }
    }

    private void scentTactical() {
        if (this.active >= SRPConfigSystems.scentSpawnWaves) {
            this.scentState = (byte)5;
            this.active = (byte)(this.active - 5);
            return;
        }
        if (this.getTargetToKill() != null) {
            this.active = (byte)(this.active + 1);
        } else if (this.field_70173_aa % 80 == 0) {
            this.active = (byte)(this.active - 1);
            if (this.active <= 0) {
                this.scentState = 1;
                return;
            }
        }
    }

    private void scentAttacker() {
        if (this.checkNearby() <= 6) {
            this.scentState = (byte)6;
        } else {
            this.delay = 100;
            this.scentState = (byte)4;
        }
    }

    private void scentBuilder() {
        this.field_70170_p.func_184148_a(null, this.field_70165_t, this.field_70163_u, this.field_70161_v, SRPSounds.SCENTWAVE, this.func_184176_by(), 10.0f, 1.0f);
        this.field_70170_p.func_184148_a(null, this.getTargetToKill().field_70165_t, this.getTargetToKill().field_70163_u, this.getTargetToKill().field_70161_v, SRPSounds.SCENTWAVE, this.getTargetToKill().func_184176_by(), 10.0f, 1.0f);
        this.loopLife = (byte)(this.loopLife - 1);
        int a = 0;
        int horde = this.maxMinInt(this.minwave, this.maxwave);
        for (int limit = 0; limit < 10; ++limit) {
            if ((a += this.placeWaves(SRPConfigSystems.scentMiniDis, SRPConfigSystems.scentMaxDis)) < horde) continue;
            this.delay = 100 + a * 20;
            this.scentState = (byte)4;
            return;
        }
        this.delay = 200;
        if (a <= 0) {
            this.delay = 100;
        }
    }

    private void scentFollower() {
        if (this.followTargetScent) {
            if (this.getTargetToKill() != null) {
                if (this.getTargetToKill().func_70068_e((Entity)this) > 144.0 && this.getTargetToKill().func_70644_a(SRPPotions.PREY_E)) {
                    this.func_82149_j((Entity)this.getTargetToKill());
                }
            } else {
                this.setTargetToKill(null, false);
                this.followTargetScent = false;
                return;
            }
        }
    }

    public boolean getCanFollow() {
        return this.followTargetScent;
    }

    public void setCanFollow(boolean in) {
        this.followTargetScent = in;
    }

    public boolean getDieToE() {
        return this.dieAfterKilling;
    }

    public void setDieToE(boolean in) {
        this.dieAfterKilling = in;
    }

    public void setScentState(int in) {
        this.scentState = (byte)in;
    }

    public byte getScentState() {
        return this.scentState;
    }

    public void setScentLife(int in) {
        this.lifeTicks = in;
    }

    public int getScentLife() {
        return this.lifeTicks;
    }

    public boolean setTargetToKill(EntityLivingBase in, boolean checkATT) {
        if (this.followTargetScent) {
            return false;
        }
        if (in == null) {
            return false;
        }
        if (!in.func_70089_S()) {
            return false;
        }
        if (checkATT) {
            if (this.checkAttri(in)) {
                this.targetScent = in;
                return true;
            }
            return false;
        }
        this.targetScent = in;
        return true;
    }

    private boolean checkAttri(EntityLivingBase in) {
        if (in == null) {
            return false;
        }
        int cond = 0;
        if (in.func_110148_a(SharedMonsterAttributes.field_111267_a) != null && in.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111126_e() >= SRPConfigSystems.minAttriHealth) {
            ++cond;
        }
        if (in.func_110148_a(SharedMonsterAttributes.field_188791_g) != null && in.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111126_e() >= SRPConfigSystems.minAttriArmor) {
            ++cond;
        }
        if (in.func_110148_a(SharedMonsterAttributes.field_111264_e) != null && in.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e() >= SRPConfigSystems.minAttriDamage) {
            ++cond;
        }
        return cond >= SRPConfigSystems.minAttriFailCount || cond == 0;
    }

    public EntityLivingBase getTargetToKill() {
        if (this.targetScent == null) {
            return null;
        }
        if (!this.targetScent.func_70089_S()) {
            this.targetScent = null;
            return null;
        }
        return this.targetScent;
    }

    public void increaseDanger(int in, boolean plus) {
        this.dangerToUs = plus ? (this.dangerToUs += in) : in;
        this.updateScentOLevel();
    }

    private void updateScentOLevel() {
        if (this.dangerToUs >= SRPConfigSystems.scentLevelPointsEight) {
            this.scentLevel = (byte)8;
            this.maxmob = SRPConfigSystems.scentWaveMaxMobWaveEight;
            this.minmob = SRPConfigSystems.scentWaveMinMobWaveEight;
            this.maxwave = SRPConfigSystems.scentWaveMaximumEight;
            this.minwave = SRPConfigSystems.scentWaveMinimumEight;
        } else if (this.dangerToUs >= SRPConfigSystems.scentLevelPointsSeven) {
            this.scentLevel = (byte)7;
            this.maxmob = SRPConfigSystems.scentWaveMaxMobWaveSeven;
            this.minmob = SRPConfigSystems.scentWaveMinMobWaveSeven;
            this.maxwave = SRPConfigSystems.scentWaveMaximumSeven;
            this.minwave = SRPConfigSystems.scentWaveMinimumSeven;
        } else if (this.dangerToUs >= SRPConfigSystems.scentLevelPointsSix) {
            this.scentLevel = (byte)6;
            this.maxmob = SRPConfigSystems.scentWaveMaxMobWaveSix;
            this.minmob = SRPConfigSystems.scentWaveMinMobWaveSix;
            this.maxwave = SRPConfigSystems.scentWaveMaximumSix;
            this.minwave = SRPConfigSystems.scentWaveMinimumSix;
        } else if (this.dangerToUs >= SRPConfigSystems.scentLevelPointsFive) {
            this.scentLevel = (byte)5;
            this.maxmob = SRPConfigSystems.scentWaveMaxMobWaveFive;
            this.minmob = SRPConfigSystems.scentWaveMinMobWaveFive;
            this.maxwave = SRPConfigSystems.scentWaveMaximumFive;
            this.minwave = SRPConfigSystems.scentWaveMinimumFive;
        } else if (this.dangerToUs >= SRPConfigSystems.scentLevelPointsFour) {
            this.scentLevel = (byte)4;
            this.maxmob = SRPConfigSystems.scentWaveMaxMobWaveFour;
            this.minmob = SRPConfigSystems.scentWaveMinMobWaveFour;
            this.maxwave = SRPConfigSystems.scentWaveMaximumFour;
            this.minwave = SRPConfigSystems.scentWaveMinimumFour;
        } else if (this.dangerToUs >= SRPConfigSystems.scentLevelPointsThree) {
            this.scentLevel = (byte)3;
            this.maxmob = SRPConfigSystems.scentWaveMaxMobWaveThree;
            this.minmob = SRPConfigSystems.scentWaveMinMobWaveThree;
            this.maxwave = SRPConfigSystems.scentWaveMaximumThree;
            this.minwave = SRPConfigSystems.scentWaveMinimumThree;
        } else if (this.dangerToUs >= SRPConfigSystems.scentLevelPointsTwo) {
            this.scentLevel = (byte)2;
            this.maxmob = SRPConfigSystems.scentWaveMaxMobWaveTwo;
            this.minmob = SRPConfigSystems.scentWaveMinMobWaveTwo;
            this.maxwave = SRPConfigSystems.scentWaveMaximumTwo;
            this.minwave = SRPConfigSystems.scentWaveMinimumTwo;
        } else if (this.dangerToUs >= SRPConfigSystems.scentLevelPointsOne) {
            this.scentLevel = 1;
            this.maxmob = SRPConfigSystems.scentWaveMaxMobWaveOne;
            this.minmob = SRPConfigSystems.scentWaveMinMobWaveOne;
            this.maxwave = SRPConfigSystems.scentWaveMaximumOne;
            this.minwave = SRPConfigSystems.scentWaveMinimumOne;
        } else {
            this.maxmob = SRPConfigSystems.scentWaveMaxMobWaveZero;
            this.minmob = SRPConfigSystems.scentWaveMinMobWaveZero;
            this.maxwave = SRPConfigSystems.scentWaveMaximumZero;
            this.minwave = SRPConfigSystems.scentWaveMinimumZero;
        }
    }

    public int getDanger() {
        return this.dangerToUs;
    }

    public void increaseActivity(int in, boolean plus) {
        if (in > 100) {
            return;
        }
        this.active = plus ? (byte)(this.active + (byte)in) : (byte)in;
    }

    private int checkNearby() {
        int i = 0;
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_186662_g(80.0);
        List moblist = this.field_70170_p.func_72872_a(EntityParasiteBase.class, axisalignedbb);
        for (EntityParasiteBase mob : moblist) {
            if (mob.field_70159_w == 0.0 || mob.field_70181_x == 0.0 || mob.field_70179_y == 0.0 || mob.func_70638_az() != null || !mob.func_70089_S() || this.field_70170_p.func_175642_b(EnumSkyBlock.BLOCK, this.func_180425_c()) >= 5 || mob instanceof EntityPStationary || mob instanceof EntityBiomass || mob.field_70737_aN > 0 || !this.moveMobToLoc(mob)) continue;
            mob.func_70624_b(this.getTargetToKill());
            ++i;
        }
        List serverList = this.field_70170_p.field_72996_f;
        int count = 0;
        for (Entity entity : serverList) {
            if (!(entity instanceof EntityParasiteBase)) continue;
            ++count;
        }
        int players = this.field_70170_p.field_73010_i.size();
        if (count > SRPConfig.worldMobCap + (players *= SRPConfig.worldMobCapPlusPlayer)) {
            return 20;
        }
        return i;
    }

    private boolean moveMobToLoc(EntityParasiteBase in) {
        int minDist = SRPConfigSystems.scentMiniDis;
        int maxDist = SRPConfigSystems.scentMaxDis;
        int loop = 0;
        if (loop < 7) {
            ++loop;
            int range = maxDist * 2;
            int tryX = (int)Math.floor(this.getTargetToKill().field_70165_t - (double)range / 2.0 + (double)this.field_70146_Z.nextInt(range));
            int tryY = (int)this.getTargetToKill().field_70163_u;
            int tryZ = (int)Math.floor(this.getTargetToKill().field_70161_v - (double)range / 2.0 + (double)this.field_70146_Z.nextInt(range));
            BlockPos poss = new BlockPos(tryX, tryY, tryZ);
            if ((poss = ParasiteEventEntity.getFloor(this.field_70170_p, poss, 10)) == null) {
                return false;
            }
            if (this.field_70170_p.func_175642_b(EnumSkyBlock.BLOCK, poss) > 4) {
                return false;
            }
            AxisAlignedBB axisalignedbb = new AxisAlignedBB((double)tryX, (double)tryY, (double)tryZ, (double)(tryX + 1), (double)(tryY + 1), (double)(tryZ + 1)).func_72314_b((double)minDist, 5.0, (double)minDist);
            List moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
            for (EntityLivingBase mob : moblist) {
                if (mob instanceof EntityParasiteBase) continue;
                return false;
            }
            if (this.func_70011_f(poss.func_177958_n(), poss.func_177956_o(), poss.func_177952_p()) < (double)minDist || this.func_70011_f(poss.func_177958_n(), poss.func_177956_o(), poss.func_177952_p()) > (double)maxDist) {
                return false;
            }
            in.func_70107_b((double)poss.func_177958_n() + 0.5, poss.func_177956_o(), (double)poss.func_177952_p() + 0.5);
            return true;
        }
        return false;
    }

    public int placeWaves(int minDist, int maxDist) {
        if (this.getTargetToKill() == null) {
            return 0;
        }
        if (!SRPConfigSystems.useScent) {
            this.func_70106_y();
            return 0;
        }
        int range = maxDist * 2;
        int tryX = (int)Math.floor(this.getTargetToKill().field_70165_t - (double)range / 2.0 + (double)this.field_70146_Z.nextInt(range));
        int tryY = (int)this.getTargetToKill().field_70163_u;
        int tryZ = (int)Math.floor(this.getTargetToKill().field_70161_v - (double)range / 2.0 + (double)this.field_70146_Z.nextInt(range));
        BlockPos poss = new BlockPos(tryX, tryY, tryZ);
        if ((poss = ParasiteEventEntity.getFloor(this.field_70170_p, poss, 10)) == null) {
            return 0;
        }
        if (this.func_70011_f(poss.func_177958_n(), poss.func_177956_o(), poss.func_177952_p()) < (double)minDist || this.func_70011_f(poss.func_177958_n(), poss.func_177956_o(), poss.func_177952_p()) > (double)SRPConfigSystems.oneMinRangeCap) {
            return 0;
        }
        AxisAlignedBB axisalignedbb = new AxisAlignedBB((double)poss.func_177958_n(), (double)poss.func_177956_o(), (double)poss.func_177952_p(), (double)(poss.func_177958_n() + 1), (double)(poss.func_177956_o() + 1), (double)(poss.func_177952_p() + 1)).func_72314_b((double)maxDist, 16.0, (double)maxDist);
        List moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
        int living = moblist.size();
        moblist = this.field_70170_p.func_72872_a(EntityParasiteBase.class, axisalignedbb);
        List serverList = this.field_70170_p.field_72996_f;
        int count = 0;
        for (Entity entity : serverList) {
            if (!(entity instanceof EntityParasiteBase)) continue;
            ++count;
        }
        int players = this.field_70170_p.field_73010_i.size();
        if (count > SRPConfig.worldMobCap + (players *= SRPConfig.worldMobCapPlusPlayer)) {
            return 0;
        }
        if (living == moblist.size()) {
            return 0;
        }
        axisalignedbb = new AxisAlignedBB((double)poss.func_177958_n(), (double)poss.func_177956_o(), (double)poss.func_177952_p(), (double)(poss.func_177958_n() + 1), (double)(poss.func_177956_o() + 1), (double)(poss.func_177952_p() + 1)).func_72314_b((double)minDist, 5.0, (double)minDist);
        moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
        this.updateScentOLevel();
        ParasiteEventEntity.spawnUnitFromRof(this.field_70170_p, this.getTargetToKill(), poss, this.getMob(), this.minmob, this.maxmob);
        return 1;
    }

    private String[] getMob() {
        String[] mob = new String[]{"minecraft:zombie"};
        switch (this.getareaValue()) {
            case 0: {
                mob = SRPConfigSystems.scentLevelZero;
                break;
            }
            case 1: {
                mob = SRPConfigSystems.scentLevelOne;
                break;
            }
            case 2: {
                mob = SRPConfigSystems.scentLevelTwo;
                break;
            }
            case 3: {
                mob = SRPConfigSystems.scentLevelThree;
                break;
            }
            case 4: {
                mob = SRPConfigSystems.scentLevelFour;
                break;
            }
            case 5: {
                mob = SRPConfigSystems.scentLevelFive;
                break;
            }
            case 6: {
                mob = SRPConfigSystems.scentLevelSix;
                break;
            }
            case 7: {
                mob = SRPConfigSystems.scentLevelSeven;
                break;
            }
            case 8: {
                mob = SRPConfigSystems.scentLevelEight;
            }
        }
        return mob;
    }

    private byte getareaValue() {
        byte i = this.scentLevel;
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_186662_g(80.0);
        List moblist1 = this.field_70170_p.func_72872_a(EntityParasiticScent.class, axisalignedbb);
        for (EntityParasiticScent mob : moblist1) {
            if (mob.getScentLevel() <= i) continue;
            i = mob.getScentLevel();
        }
        return i;
    }

    public void setScentReaction(byte in, boolean override) {
        if (override) {
            this.scentReaction = in;
        } else if (in > this.scentReaction) {
            this.scentReaction = in;
        }
    }

    public byte getScentLevel() {
        return this.scentLevel;
    }

    public void setScentLevel(int in) {
        this.scentLevel = (byte)in;
    }

    public void warnPlayers(String in) {
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_186662_g(80.0);
        List moblist1 = this.field_70170_p.func_72872_a(EntityPlayer.class, axisalignedbb);
        for (EntityPlayer mob : moblist1) {
            mob.func_146105_b((ITextComponent)new TextComponentString(in), true);
        }
    }

    private int maxMinInt(int min, int max) {
        int atm = max - min + 1;
        return this.field_70146_Z.nextInt(atm) + min;
    }

    public boolean checkIfScentAlone(EntityParasiticScent scent) {
        ArrayList<BlockPos> scentsLoadedPositions = new ArrayList<BlockPos>();
        List serverList = this.field_70170_p.field_72996_f;
        for (Entity entity : serverList) {
            if (!(entity instanceof EntityParasiticScent) || entity == scent) continue;
            scentsLoadedPositions.add(entity.func_180425_c());
        }
        for (BlockPos s : scentsLoadedPositions) {
            if (!(scent.func_70011_f(s.func_177958_n(), s.func_177956_o(), s.func_177952_p()) <= (double)SRPConfigSystems.scentSpacing)) continue;
            return false;
        }
        return true;
    }

    public void func_70108_f(Entity entityIn) {
        super.func_70108_f(entityIn);
    }

    public void func_70100_b_(EntityPlayer entityIn) {
        if (this.field_70173_aa % 20 == 0 && !entityIn.field_70170_p.field_72995_K) {
            System.out.println("\n scent state " + this.scentState + "\n active " + this.active + "\n reaction " + this.scentReaction + "\n danger " + this.dangerToUs + "\n delay " + this.delay + "\n total life " + this.lifeTicks + "\n current life " + this.currentL + "\n targetScent is null " + (this.getTargetToKill() == null) + "\n level " + this.scentLevel + "\n targetScent name " + (this.getTargetToKill() != null ? this.getTargetToKill().getClass().toString() : " b ") + "\n min max mob " + this.minmob + " " + this.maxmob + "\n loop " + this.loopLife + "\n fail " + this.failing);
        }
        super.func_70100_b_(entityIn);
    }

    protected void func_70037_a(NBTTagCompound compound) {
        if (compound.func_150297_b("parasitehostbuffapplied", 1)) {
            this.hostBuffApplied = compound.func_74767_n("parasitehostbuffapplied");
        }
        if (compound.func_150297_b("parasitehostoriginalmax", 6)) {
            this.originalHostMaxHealth = compound.func_74769_h("parasitehostoriginalmax");
        }
        if (compound.func_150297_b("parasitescenttype", 99)) {
            this.scentState = compound.func_74771_c("parasitescenttype");
        }
        if (compound.func_150297_b("parasitescentactive", 99)) {
            this.active = compound.func_74771_c("parasitescentactive");
        }
        if (compound.func_150297_b("parasitescentlevel", 99)) {
            this.scentLevel = compound.func_74771_c("parasitescentlevel");
        }
        if (compound.func_150297_b("parasitelifespan", 99)) {
            this.lifeTicks = compound.func_74762_e("parasitelifespan");
        }
        if (compound.func_150297_b("parasitelifecurrent", 99)) {
            this.currentL = compound.func_74762_e("parasitelifecurrent");
        }
        if (compound.func_150297_b("parasitedangerous", 99)) {
            this.dangerToUs = compound.func_74762_e("parasitedangerous");
        }
        if (compound.func_150297_b("parasitedelay", 99)) {
            this.delay = compound.func_74762_e("parasitedelay");
        }
        if (compound.func_150297_b("parasitescentreaction", 99)) {
            this.scentReaction = compound.func_74771_c("parasitescentreaction");
        }
        if (compound.func_150297_b("parasitescentloopf", 99)) {
            this.loopLife = compound.func_74771_c("parasitescentloopf");
        }
        if (compound.func_150297_b("parasitescentfailing", 99)) {
            this.failing = compound.func_74771_c("parasitescentfailing");
        }
        if (compound.func_150297_b("parasitescentdyeing", 99)) {
            this.dieAfterKilling = compound.func_74767_n("parasitescentdyeing");
        }
        if (compound.func_150297_b("parasitescentfollowing", 99)) {
            this.followTargetScent = compound.func_74767_n("parasitescentfollowing");
        }
        if (compound.func_150297_b("parasitescentphase", 99)) {
            this.phaseI = compound.func_74767_n("parasitescentphase");
        }
        if (compound.func_150297_b("parasitehost", 99)) {
            this.hostLiving = (EntityLivingBase)this.field_70170_p.func_73045_a(compound.func_74762_e("parasitehost"));
        }
    }

    protected void func_70014_b(NBTTagCompound compound) {
        compound.func_74757_a("parasitehostbuffapplied", this.hostBuffApplied);
        compound.func_74780_a("parasitehostoriginalmax", this.originalHostMaxHealth);
        compound.func_74774_a("parasitescenttype", this.scentState);
        compound.func_74774_a("parasitescentactive", this.active);
        compound.func_74774_a("parasitescentlevel", this.scentLevel);
        compound.func_74768_a("parasitelifespan", this.lifeTicks);
        compound.func_74768_a("parasitelifecurrent", this.currentL);
        compound.func_74768_a("parasitedangerous", this.dangerToUs);
        compound.func_74768_a("parasitedelay", this.delay);
        compound.func_74774_a("parasitescentreaction", this.scentReaction);
        compound.func_74774_a("parasitescentloopf", this.loopLife);
        compound.func_74774_a("parasitescentfailing", this.failing);
        compound.func_74757_a("parasitescentdyeing", this.dieAfterKilling);
        compound.func_74757_a("parasitescentfollowing", this.followTargetScent);
        compound.func_74757_a("parasitescentphase", this.phaseI);
        if (this.hostLiving != null) {
            compound.func_74768_a("parasitehost", this.hostLiving.func_145782_y());
        }
    }

    private void cleanupHostBuff() {
        if (!this.hostBuffApplied || this.hostLiving == null) {
            return;
        }
        if (this.hostLiving.func_110148_a(SharedMonsterAttributes.field_111267_a) != null && this.hostLiving.func_70089_S()) {
            double restore = this.originalHostMaxHealth > 0.0 ? this.originalHostMaxHealth : this.hostLiving.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() / 10.0;
            this.hostLiving.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(restore);
            if ((double)this.hostLiving.func_110143_aJ() > restore) {
                this.hostLiving.func_70606_j((float)restore);
            }
        }
        this.hostLiving.getEntityData().func_82580_o(SCENT_HOST_TAG);
        this.hostBuffApplied = false;
        this.originalHostMaxHealth = -1.0;
    }

    public void func_70106_y() {
        if (!this.field_70170_p.field_72995_K) {
            this.cleanupHostBuff();
        }
        super.func_70106_y();
    }
}

