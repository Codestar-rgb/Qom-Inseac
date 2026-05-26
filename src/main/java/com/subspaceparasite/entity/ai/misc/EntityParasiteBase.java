/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  net.minecraft.advancements.CriteriaTriggers
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockEndGateway
 *  net.minecraft.block.BlockEndPortalFrame
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.BlockPortal
 *  net.minecraft.block.SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.MultiPartEntityPart
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.item.EntityBoat
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.item.EntityMinecart
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.init.MobEffects
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemShield
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.datasync.DataParameter
 *  net.minecraft.network.datasync.DataSerializer
 *  net.minecraft.network.datasync.DataSerializers
 *  net.minecraft.network.datasync.EntityDataManager
 *  net.minecraft.pathfinding.PathNavigateFlying
 *  net.minecraft.pathfinding.PathNavigateGround
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.stats.StatList
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.EnumSkyBlock
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.Biome
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.fml.common.eventhandler.Event$Result
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.entity.ai.misc;

import com.subspaceparasite.block.IMetaName;
import com.subspaceparasite.client.particle.ParticleSpawner;
import com.subspaceparasite.client.particle.SPEnumParticle;
import com.subspaceparasite.entity.EntityHitbox;
import com.subspaceparasite.entity.EntityToxicCloud;
import com.subspaceparasite.entity.IHitboxedEntity;
import com.subspaceparasite.entity.ai.EntityAINearestAttackableTargetStatus;
import com.subspaceparasite.entity.ai.EntityAIParasiteFollow;
import com.subspaceparasite.entity.ai.EntityAIWanderStatus;
import com.subspaceparasite.entity.ai.misc.EntityPDispatcher;
import com.subspaceparasite.entity.projectile.EntityGore;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.init.SPItems;
import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.init.SPSounds;
import com.subspaceparasite.item.tool.WeaponToolMeleeBase;
import com.subspaceparasite.item.tool.WeaponToolRangeBase;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.ParasiteEventWorld;
import com.subspaceparasite.util.SPAttributes;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.util.config.SPConfigWorld;
import com.subspaceparasite.world.SPSaveData;
import com.subspaceparasite.world.SPWorldData;
import com.subspaceparasite.world.biome.BiomeParasiteBase;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEndGateway;
import net.minecraft.block.BlockEndPortalFrame;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemShield;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class EntityParasiteBase
extends EntityMob
implements IHitboxedEntity {
    protected int attackSpeedT;
    protected double killcount = 0.0;
    protected boolean canD = true;
    private int waitInt;
    private boolean canWorkTask;
    private EntityParasiteBase owner;
    private EntityParasiteBase notOwner;
    public boolean canChangeVariant;
    protected float scentHPMultiplier = 2.5f;
    protected int foodRootNumber;
    protected double foodRott;
    public boolean disloNumberTwo = false;
    public boolean disloNumberThree = false;
    public boolean disloNumberFour = false;
    public boolean disloNumberSix = false;
    public boolean disloNumberSeven = false;
    public boolean disloNumberEight = false;
    public boolean disloNumberNine = false;
    public int disloNumberEleven = 0;
    public int disloNumberFifteen = 0;
    public static final DataParameter<Boolean> DISLO15 = EntityDataManager.func_187226_a(EntityParasiteBase.class, (DataSerializer)DataSerializers.field_187198_h);
    public int disloNumberSixteen = 0;
    public float distanceWalkedOnStepModifiedDislo;
    public int disloNumberSeventeen = 0;
    public boolean disloNumberEighteen = false;
    public int disloNumberNineteen = 0;
    public double disloNumberNineteenValue = 0.0;
    public boolean disloNumberTwentyone = false;
    public boolean disloNumberTwentytwo = false;
    protected boolean geneMindam = true;
    protected boolean geneDamcap = true;
    protected boolean geneLookwall = true;
    protected boolean geneSprinting = true;
    protected boolean geneWaterleap = true;
    protected boolean geneSpecialmove = true;
    protected float genePoisonHealing = 2.5f;
    protected float geneMobHealing = 3.0f;
    protected float geneAttackSpeed = 0.5f;
    private static final DataParameter<Byte> SPECIAL = EntityDataManager.func_187226_a(EntityParasiteBase.class, (DataSerializer)DataSerializers.field_187191_a);
    private static final DataParameter<Integer> SELFE = EntityDataManager.func_187226_a(EntityParasiteBase.class, (DataSerializer)DataSerializers.field_187192_b);
    private static final DataParameter<Byte> SKIN = EntityDataManager.func_187226_a(EntityParasiteBase.class, (DataSerializer)DataSerializers.field_187191_a);
    private static final DataParameter<Boolean> COLD_L = EntityDataManager.func_187226_a(EntityParasiteBase.class, (DataSerializer)DataSerializers.field_187198_h);
    public int srpTicks;
    protected int lastActiveTime;
    protected int timeSinceIgnited;
    protected int fuseTime = 40;
    protected byte canModRender = 0;
    protected int attackCooldown;
    protected byte madeRng = (byte)-1;
    protected byte paraGeneration = 0;
    public boolean spawnedByColo;
    protected byte type = 0;
    public int damageCap = 1;
    protected float MiniDamage = 0.0f;
    protected float miniCap;
    protected boolean miniCapA = false;
    protected byte phaseCreated;
    protected byte levelCreated;
    protected EntityAIJumping jumpT = new EntityAIJumping((EntityLiving)this);
    protected EntityAIWait wait = new EntityAIWait();
    public EntityAIParasiteFollow folow = new EntityAIParasiteFollow(this, 1.3, 16.0, 6.0, true);
    public EntityAIWanderStatus aiWander = new EntityAIWanderStatus(this, 1.0, 120, 0.001f, true);
    protected int oneMindDeathValue;
    protected float foodSteal;
    private float aniticks;
    private boolean still;
    private int stillTicks;
    private int canBeStored;
    private int dodFatherID = 0;
    public boolean canSpawnSpawn;
    protected int valueEvDeath;
    protected int liquidLeap;
    protected float cothSpread;
    private EntityLivingBase last;
    public int targetNewCool;
    protected float blockH;
    protected int BGheight;
    protected int BGrange;
    protected boolean SkillBGflag;
    private ArrayList<String> inbBlockName = new ArrayList();
    private ArrayList<Integer> inbBlockNumber = new ArrayList();
    protected int attacking;
    protected double targetX;
    protected double targetZ;
    protected float leapMotionY;
    protected double jumpSpeed;
    protected int jumpR;
    protected boolean SkillLeapFlag;
    public EntityHitbox[] hitboxes;
    public float prevHitboxScale;

    public EntityParasiteBase(World worldIn) {
        super(worldIn);
        this.field_70714_bg.func_75776_a(0, (EntityAIBase)this.wait);
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)this.aiWander);
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)this.folow);
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)this.jumpT);
        this.canBeStored = 0;
        this.attackCooldown = 0;
        this.canWorkTask = true;
        this.srpTicks = 0;
        this.madeRng = (byte)-1;
        this.foodSteal = 0.001f;
        this.spawnedByColo = false;
        this.SkillBGflag = false;
        this.SkillLeapFlag = false;
        this.canChangeVariant = false;
        this.oneMindDeathValue = 1;
        this.foodRootNumber = 0;
        this.foodRott = 0.0;
        this.attackSpeedT = 20;
        this.canSpawnSpawn = true;
        this.still = false;
        this.miniCap = SPConfig.miniminiCap;
        this.setScentHPMultiplier(2.5f);
    }

    public void setCreatedPhase(int in, int ud) {
        this.phaseCreated = (byte)in;
        this.levelCreated = (byte)ud;
    }

    public void setScentHPMultiplier(float scentHPMultiplier) {
        this.scentHPMultiplier = scentHPMultiplier;
    }

    public float getScentHPMultiplier() {
        return this.scentHPMultiplier;
    }

    public double scentHostHPCalculation() {
        return this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111126_e() * (double)this.getScentHPMultiplier();
    }

    public abstract int getParasiteIDRegister();

    public int getAttackSpeed() {
        return (int)((float)this.attackSpeedT * this.geneAttackSpeed);
    }

    public void applyBonuses(SPSaveData sabe, World world) {
        this.setCreatedPhase(sabe.getEvolutionPhase(world.field_73011_w.getDimension()), sabe.getDeveLevel());
        int id = world.field_73011_w.getDimension();
        this.applyGene(sabe.getGeneModi(id), sabe.getGeneModi2(id));
        if (!(SPConfigWorld.nodesActivated || SPConfigWorld.coloniesActivated || SPConfigSystems.useEvolution)) {
            return;
        }
        this.phaseCreated = sabe.getEvolutionPhase(world.field_73011_w.getDimension());
        if (this.phaseCreated >= SPConfigSystems.evolutionAssimilatedDehiding) {
            this.field_70715_bh.func_75776_a(5, new EntityAINearestAttackableTargetStatus<EntityVillager>(this, EntityVillager.class, true));
        }
        if (this.phaseCreated >= SPConfigSystems.evolutionTotalKill) {
            this.field_70715_bh.func_75776_a(4, new EntityAINearestAttackableTargetStatus<EntityLivingBase>(this, EntityLivingBase.class, 0, false, false, entity -> !(entity instanceof EntityPlayer) && !ParasiteEventEntity.checkEntity(entity, SPConfig.mobattackingBlackList, SPConfig.mobattackingBlackListWhite), SPConfig.adaptedSneakPen, SPConfig.adaptedInviPen));
        }
    }

    public void applyGene(boolean[] kool, float[] goon) {
        if (!SPConfigSystems.generationUse) {
            return;
        }
        this.geneMindam = kool[0];
        this.geneDamcap = kool[1];
        this.geneLookwall = kool[2];
        this.geneSprinting = kool[3];
        this.geneWaterleap = kool[4];
        this.geneSpecialmove = kool[5];
        this.genePoisonHealing = goon[0];
        this.geneMobHealing = goon[1];
        this.geneAttackSpeed = goon[2];
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_187214_a(SPECIAL, (Object)0);
        this.field_70180_af.func_187214_a(SELFE, (Object)-1);
        this.field_70180_af.func_187214_a(SKIN, (Object)0);
        this.field_70180_af.func_187214_a(COLD_L, (Object)false);
        this.field_70180_af.func_187214_a(DISLO15, (Object)false);
    }

    public void cannotDespawn(boolean in) {
        this.canD = in;
    }

    public boolean func_70692_ba() {
        return this.canD;
    }

    public void setApplyColonyB(boolean in) {
        this.spawnedByColo = in;
    }

    public boolean getApplyColonyB() {
        return this.spawnedByColo;
    }

    public void func_70636_d() {
        ++this.srpTicks;
        if (this.func_175446_cd()) {
            return;
        }
        if (this.getWait() > 0) {
            int j1 = this.getWait() - 1;
            if (j1 == 0) {
                this.setParasiteStatus(0);
            }
            this.setWait(j1);
        } else {
            super.func_70636_d();
        }
        if (this.hitboxes != null && this.hitboxes.length > 0) {
            this.updateHitboxes();
            this.resetHitboxes(this.func_70603_bj());
        }
        if (this.field_70170_p.field_72995_K) {
            if (this.func_70644_a(SPPotions.PARATE_E)) {
                if (this.field_70170_p.field_73012_v.nextInt(3) != 0) {
                    return;
                }
                for (int i = 0; i <= 1; ++i) {
                    this.spawnParticles(SPEnumParticle.RHAPPY, 0, 0, 0);
                }
            }
            if (this.srpTicks > 20) {
                this.srpTicks = 0;
            }
        } else {
            EntityLivingBase tgt;
            if (this.targetNewCool >= 0) {
                --this.targetNewCool;
            }
            if ((tgt = this.func_70638_az()) instanceof EntityPlayer && tgt.func_70644_a(SPPotions.THE_SIGN_E)) {
                super.func_70624_b(null);
                this.func_70604_c(null);
                this.func_130011_c(null);
                this.func_70661_as().func_75499_g();
            }
            if (this.disloNumberEleven >= 0) {
                --this.disloNumberEleven;
            }
            if (this.disloNumberFifteen >= 0) {
                --this.disloNumberFifteen;
                if (this.disloNumberFifteen <= 0) {
                    this.field_70180_af.func_187227_b(DISLO15, (Object)false);
                }
            }
            if (this.disloNumberSixteen >= 0) {
                this.field_82151_R = -10.0f;
                --this.disloNumberSixteen;
                if (this.disloNumberSixteen <= 0) {
                    this.field_82151_R = this.distanceWalkedOnStepModifiedDislo + 500.0f;
                }
            }
            if (this.disloNumberSeventeen >= 0) {
                --this.disloNumberSeventeen;
            }
            if (this.disloNumberNineteen >= 0) {
                --this.disloNumberNineteen;
            }
            this.handleParasiteStatus();
            if (this.srpTicks == 1) {
                this.handleWater(true);
                if (this.disloNumberNineteen > 0) {
                    this.killcount += this.disloNumberNineteenValue;
                }
            }
            if (this.srpTicks > 20) {
                this.srpTicks = 0;
                if (!this.inbBlockName.isEmpty() && this.field_70146_Z.nextInt(50) == 0) {
                    this.spawnCyst();
                }
                if (this.func_70638_az() instanceof EntityPlayer) {
                    this.fearPlayer(this.func_70638_az());
                }
                if (SPConfigSystems.useEvolution && this.killcount >= 0.0) {
                    switch (this.phaseCreated) {
                        case 1: {
                            this.killcount += SPConfigSystems.phaseKillCountPlusOne;
                            break;
                        }
                        case 2: {
                            this.killcount += SPConfigSystems.phaseKillCountPlusTwo;
                            break;
                        }
                        case 3: {
                            this.killcount += SPConfigSystems.phaseKillCountPlusThree;
                            break;
                        }
                        case 4: {
                            this.killcount += SPConfigSystems.phaseKillCountPlusFour;
                            break;
                        }
                        case 5: {
                            this.killcount += SPConfigSystems.phaseKillCountPlusFive;
                            break;
                        }
                        case 6: {
                            this.killcount += SPConfigSystems.phaseKillCountPlusSix;
                            break;
                        }
                        case 7: {
                            this.killcount += SPConfigSystems.phaseKillCountPlusSeven;
                            break;
                        }
                        case 8: {
                            this.killcount += SPConfigSystems.phaseKillCountPlusEight;
                            break;
                        }
                        case 9: {
                            this.killcount += SPConfigSystems.phaseKillCountPlusNine;
                            break;
                        }
                        case 10: {
                            this.killcount += SPConfigSystems.phaseKillCountPlusTen;
                        }
                    }
                } else if (this.field_70170_p.func_175659_aa() == EnumDifficulty.HARD && this.killcount >= 0.0) {
                    this.killcount += SPConfig.killcountplus;
                }
                this.placeNidus();
            }
            this.handleWater(false);
        }
        if (this.field_70165_t == this.field_70169_q && this.field_70161_v == this.field_70166_s) {
            ++this.stillTicks;
        } else {
            this.canBeStored = 55;
            this.stillTicks = 0;
            this.still = false;
        }
        if (this.stillTicks > 25) {
            this.still = true;
        }
        this.aniticks = this.still ? (this.aniticks += 0.0012f) : 0.0f;
        if (this.canBeStored > 0) {
            --this.canBeStored;
        }
    }

    protected void handleWater(boolean check) {
        if (check && this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c() instanceof BlockLiquid && this.func_70638_az() != null) {
            ++this.liquidLeap;
            if (this.liquidLeap > 4) {
                this.liquidLeap = 4;
            }
        }
        if (this.liquidLeap >= 1) {
            if (this.geneWaterleap) {
                double h = 0.1;
                double str = 0.5;
                this.func_70661_as().func_75499_g();
                EntityLivingBase entitylivingbase = this.func_70638_az();
                if (entitylivingbase != null) {
                    Block bl = this.field_70170_p.func_180495_p(this.func_180425_c()).func_177230_c();
                    if (!bl.func_176205_b((IBlockAccess)this.field_70170_p, this.func_180425_c())) {
                        h = 0.3;
                        str = 1.0;
                    }
                    --this.liquidLeap;
                    double dd0 = entitylivingbase.field_70165_t - this.field_70165_t;
                    double dd1 = entitylivingbase.field_70161_v - this.field_70161_v;
                    float f = MathHelper.func_76133_a((double)(dd0 * dd0 + dd1 * dd1));
                    this.field_70159_w += dd0 / (double)f * str * (double)0.8f + this.field_70159_w * (double)0.2f;
                    this.field_70179_y += dd1 / (double)f * str * (double)0.8f + this.field_70179_y * (double)0.2f;
                    this.field_70181_x = h;
                    this.lookAt((Entity)entitylivingbase);
                }
            } else {
                --this.liquidLeap;
            }
        }
    }

    protected void handleParasiteStatus() {
        byte k = this.getParasiteStatus();
        if (this.getAttackCooldownAni() != 0 || k == 1 || k == 2) {
            if (this.getAttackCooldownAni() != 0) {
                int i = this.getAttackCooldownAni() - 1;
                this.setAttackCooldownAni(i);
            }
            if (k == 1 || k == 2) {
                if (this.func_70638_az() != null) {
                    if (!this.func_70638_az().func_70089_S()) {
                        this.func_70624_b(null);
                        this.setParasiteStatus(0);
                    } else if (this.srpTicks == 10 && this.field_70146_Z.nextInt(10) == 0) {
                        double follo = this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111126_e();
                        if (this.func_70068_e((Entity)this.func_70638_az()) > follo * follo) {
                            this.func_70624_b(null);
                            this.setParasiteStatus(0);
                        }
                    }
                } else {
                    this.setParasiteStatus(0);
                    this.func_70624_b(null);
                }
            }
        }
    }

    protected void fearPlayer(EntityLivingBase player) {
    }

    public boolean checkPositionWander(double x, double y, double z) {
        return true;
    }

    protected void placeNidus() {
        if (SPConfigSystems.evolutionNests < this.getPhaseCreated() && SPConfigSystems.deveNestsUse < this.getLevelCreated()) {
            return;
        }
        if (SPConfigSystems.useEvolution && this.phaseCreated <= 0) {
            return;
        }
        if (this.field_70170_p.field_73012_v.nextInt(350) != 0 || this.type < 11) {
            return;
        }
        if (this.killcount < 10.0) {
            return;
        }
        AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_186662_g(SPConfig.nexussivFollow);
        List moblist = this.field_70170_p.func_72872_a(EntityParasiteBase.class, axisalignedbb);
        int collec = 0;
        for (EntityParasiteBase mob : moblist) {
            int idP = mob.getParasiteIDRegister();
            if (idP == 73 || idP == 77 || idP == 78 || idP == 79) {
                return;
            }
            collec += (int)mob.getKillC();
        }
        if (collec < SPConfig.reinforcerNidusStart) {
            return;
        }
        double d0 = (float)this.field_70165_t + this.field_70170_p.field_73012_v.nextFloat();
        double d1 = (float)this.field_70163_u + this.field_70170_p.field_73012_v.nextFloat();
        double d2 = (float)this.field_70161_v + this.field_70170_p.field_73012_v.nextFloat();
        double d3 = d0 - this.field_70165_t;
        double d4 = d1 - this.field_70163_u;
        double d5 = d2 - this.field_70161_v;
        double d6 = MathHelper.func_76133_a((double)(d3 * d3 + d4 * d4 + d5 * d5));
        d3 /= d6;
        d4 /= d6;
        d5 /= d6;
        double d7 = 0.5 / (d6 / 4.0 + 0.1);
        d4 = d4 * d7 * 2.0;
        EntityGore bomb = new EntityGore(this.field_70170_p);
        bomb.setType((byte)11);
        bomb.func_82149_j((Entity)this);
        bomb.setMotion(d3 *= (d7 *= (double)(this.field_70170_p.field_73012_v.nextFloat() * this.field_70170_p.field_73012_v.nextFloat() + 0.3f)), d4, d5 *= d7, 0.1, 0.5);
        this.field_70170_p.func_72838_d((Entity)bomb);
    }

    public void setDodFatherID(int in) {
        this.dodFatherID = in;
    }

    public boolean getGeneMod(int id) {
        switch (id) {
            case 0: {
                return this.geneMindam;
            }
            case 1: {
                return this.geneDamcap;
            }
            case 2: {
                return this.geneLookwall;
            }
            case 3: {
                return this.geneSprinting;
            }
            case 4: {
                return this.geneWaterleap;
            }
            case 5: {
                return this.geneSpecialmove;
            }
        }
        return false;
    }

    protected void func_70619_bc() {
        super.func_70619_bc();
        if (this.getWait() > 0) {
            int j1 = this.getWait() - 1;
            if (j1 == 0) {
                this.setParasiteStatus(0);
            }
            this.setWait(j1);
        }
    }

    public boolean func_70097_a(@Nonnull DamageSource source, float amount) {
        if (this.field_70170_p.field_72995_K) {
            return false;
        }
        this.canBeStored = 55;
        if (this.madeRng == -1) {
            this.madeRng = (byte)this.field_70146_Z.nextInt(2);
            if (this.madeRng == 0) {
                this.field_70170_p.func_72960_a((Entity)this, (byte)40);
            }
        }
        if (source == DamageSource.field_76376_m && this.func_70644_a(MobEffects.field_76436_u) && amount == 1.0f) {
            this.func_70691_i(1.0f * this.genePoisonHealing);
            return false;
        }
        if (this.disloNumberTwentyone && !this.func_70027_ad() && source != DamageSource.field_76380_i) {
            return super.func_70097_a(source, 0.0f);
        }
        if (source == DamageSource.field_76380_i) {
            return super.func_70097_a(source, amount);
        }
        if (source == DamageSource.field_76368_d) {
            this.skillBreakBlocks();
        }
        if (this.disloNumberSeven && this.field_70146_Z.nextInt(5) == 0) {
            this.particleStatus((byte)8);
        }
        if (this.disloNumberEight && this.field_70146_Z.nextInt(5) == 0) {
            this.particleStatus((byte)11);
        }
        if (this.disloNumberNine && this.field_70146_Z.nextInt(5) == 0) {
            this.particleStatus((byte)13);
        }
        if (this.field_70146_Z.nextDouble() < 0.1 && this.func_110143_aJ() > 0.0f) {
            this.attackEntityFromEffects(1, 1);
        }
        if (source.func_76364_f() != null && source.func_76364_f() instanceof EntityPlayer) {
            EntityPlayer playerIn = (EntityPlayer)source.func_76364_f();
            if (this.disloNumberSix) {
                SPSaveData data = SPSaveData.get(this.field_70170_p, 32);
                int count = data.getCurrentCode(this.field_70170_p.field_73011_w.getDimension(), 6);
                if (count >= 1) {
                    playerIn.func_184582_a(EntityEquipmentSlot.MAINHAND).func_77972_a(count, (EntityLivingBase)playerIn);
                    playerIn.func_184582_a(EntityEquipmentSlot.OFFHAND).func_77972_a(count, (EntityLivingBase)playerIn);
                } else {
                    this.disloNumberSix = false;
                }
            }
            if (playerIn.func_184582_a(EntityEquipmentSlot.MAINHAND).func_77973_b() instanceof WeaponToolMeleeBase) {
                return super.func_70097_a(source, amount);
            }
        }
        if (source == DamageSource.field_76372_a || source == DamageSource.field_76370_b) {
            if (!this.field_70178_ae) {
                if (this.field_70146_Z.nextInt(5) == 0 && !this.func_70644_a(SPPotions.RAGE_E)) {
                    this.func_70690_d(new PotionEffect(SPPotions.RAGE_E, 200, 1, false, false));
                }
                return super.func_70097_a(source, this.getSkin() == 120 ? amount * (SPConfig.firemultyplier - SPConfig.firemultyplier / 2.0f) : amount * SPConfig.firemultyplier);
            }
            return false;
        }
        if (this.notOwner != null) {
            if (this.notOwner.func_70089_S()) {
                if (this.func_70644_a(SPPotions.PIVOT_E)) {
                    this.notOwner.func_70097_a(source, amount * (float)(Objects.requireNonNull(this.func_70660_b(SPPotions.PIVOT_E)).func_76458_c() + 1) * SPConfigSystems.pivotDamageRHost);
                    return super.func_70097_a(source, amount * SPConfigSystems.pivotDamageRNotHost);
                }
                this.func_184589_d(SPPotions.PIVOT_E);
                this.notOwner = null;
            } else {
                this.notOwner = null;
            }
        }
        boolean flagWeak = SPConfig.parasiteWeakToMobs.length > 0 || SPConfig.parasiteWeakToItems.length > 0 || SPConfig.parasiteWeakToElse.length > 0;
        boolean flagCap = this.damageCap > 1 && this.geneDamcap;
        String damageName = "";
        int naniDesu = 0;
        if (flagWeak || flagCap) {
            if (source.func_76364_f() instanceof EntityPlayer) {
                damageName = ((EntityPlayer)Objects.requireNonNull(source.func_76346_g())).func_184582_a(EntityEquipmentSlot.MAINHAND).func_77973_b().getRegistryName().toString();
                naniDesu = 1;
            } else if (source.func_76364_f() instanceof EntityLivingBase) {
                damageName = EntityList.func_191301_a((Entity)source.func_76364_f()).toString();
                naniDesu = 2;
            } else {
                damageName = source.field_76373_n;
                naniDesu = 3;
            }
        }
        if (flagWeak) {
            switch (naniDesu) {
                case 1: {
                    int i;
                    for (i = 0; i < SPConfig.parasiteWeakToItems.length; ++i) {
                        String[] here;
                        if (SPConfig.parasiteWeakToItems[i] == null || !(here = SPConfig.parasiteWeakToItems[i].split(";"))[0].equals(damageName)) continue;
                        amount *= Float.parseFloat(here[1]);
                    }
                    break;
                }
                case 2: {
                    int i;
                    for (i = 0; i < SPConfig.parasiteWeakToMobs.length; ++i) {
                        String[] here;
                        if (SPConfig.parasiteWeakToMobs[i] == null || !(here = SPConfig.parasiteWeakToMobs[i].split(";"))[0].equals(damageName)) continue;
                        amount *= Float.parseFloat(here[1]);
                    }
                    break;
                }
                case 3: {
                    int i;
                    for (i = 0; i < SPConfig.parasiteWeakToElse.length; ++i) {
                        String[] here;
                        if (SPConfig.parasiteWeakToElse[i] == null || !(here = SPConfig.parasiteWeakToElse[i].split(";"))[0].equals(damageName)) continue;
                        amount *= Float.parseFloat(here[1]);
                    }
                    break;
                }
            }
        }
        if (flagCap) {
            switch (naniDesu) {
                case 1: {
                    if (!ParasiteEventEntity.checkName(damageName, SPConfig.damageCapBlackListItem, SPConfig.damageCapBlackListWhite)) break;
                    return super.func_70097_a(source, amount);
                }
                case 2: {
                    if (!ParasiteEventEntity.checkName(damageName, SPConfig.damageCapBlackListMob, SPConfig.damageCapBlackListWhite)) break;
                    return super.func_70097_a(source, amount);
                }
                case 3: {
                    if (!ParasiteEventEntity.checkName(damageName, SPConfig.damageCapBlackListElse, SPConfig.damageCapBlackListWhite)) break;
                    return super.func_70097_a(source, amount);
                }
            }
            float damage = this.func_110138_aP() / (float)this.damageCap + this.func_110138_aP() % (float)this.damageCap * 0.5f;
            if (amount >= damage) {
                source.func_76348_h();
                if (!this.func_70644_a(SPPotions.RAGE_E)) {
                    this.func_70690_d(new PotionEffect(SPPotions.RAGE_E, 200, 1, false, false));
                }
                if (this.field_70146_Z.nextDouble() < 0.3 && this.func_110143_aJ() > 0.0f) {
                    this.attackEntityFromEffects(1, 1);
                    this.attackEntityFromCap(1);
                }
            }
            return super.func_70097_a(source, Math.min(amount, damage));
        }
        return super.func_70097_a(source, amount);
    }

    protected void attackEntityFromEffects(int range, int count) {
    }

    protected void attackEntityFromCap(int go) {
    }

    public boolean func_70652_k(@Nonnull Entity entityIn) {
        boolean flag;
        if (this.field_70170_p.field_72995_K) {
            return false;
        }
        this.canBeStored = 55;
        boolean green = entityIn instanceof EntityLivingBase;
        if (green) {
            this.attackEntityAsMobMinimum((EntityLivingBase)entityIn, this.MiniDamage);
        }
        if ((flag = super.func_70652_k(entityIn)) && green) {
            EntityLivingBase target;
            if (this.getSkin() == 120) {
                SPPotions.applyStackPotion(MobEffects.field_76421_d, (EntityLivingBase)entityIn, 100, 1);
            }
            this.setAttackCooldownAni(100);
            if (this.disloNumberSix && entityIn instanceof EntityPlayer) {
                EntityPlayer playerIn = (EntityPlayer)entityIn;
                SPSaveData data = SPSaveData.get(this.field_70170_p, 33);
                int count = data.getCurrentCode(this.field_70170_p.field_73011_w.getDimension(), 6);
                if (count >= 1) {
                    for (ItemStack itemstack : playerIn.field_71071_by.field_70460_b) {
                        itemstack.func_77972_a(count, (EntityLivingBase)playerIn);
                    }
                } else {
                    this.disloNumberSix = false;
                }
            }
            if (this.field_70146_Z.nextDouble() < (double)this.cothSpread && !(target = (EntityLivingBase)entityIn).func_70644_a(SPPotions.COTH_E) && !target.func_70644_a(SPPotions.EPEL_E)) {
                target.func_70690_d(new PotionEffect(SPPotions.COTH_E, 3600, 0, false, false));
            }
            this.attackEntityAsMobFood(entityIn, true, this.foodRootNumber, this.foodRott);
        }
        return flag;
    }

    public boolean attackEntityAsMobMinimum(EntityLivingBase target, float MinimumDamage) {
        if (MinimumDamage <= 0.0f) {
            return false;
        }
        if (!this.geneMindam) {
            return false;
        }
        float f1 = target.func_110143_aJ();
        if (f1 <= 0.0f || f1 <= this.miniCap && this.miniCapA) {
            return false;
        }
        if (target instanceof EntityPlayer) {
            ItemStack itemstack1;
            EntityPlayer entityplayer = (EntityPlayer)target;
            if (entityplayer.field_71075_bZ.field_75098_d) {
                return false;
            }
            entityplayer.func_71020_j(this.foodSteal);
            ItemStack itemStack = itemstack1 = entityplayer.func_184587_cr() ? entityplayer.func_184607_cu() : ItemStack.field_190927_a;
            if (itemstack1.func_77973_b() instanceof ItemShield && entityplayer.func_184585_cz() && !entityplayer.func_184811_cZ().func_185141_a(itemstack1.func_77973_b())) {
                float f12 = 0.25f + (float)EnchantmentHelper.func_185293_e((EntityLivingBase)this) * 0.05f;
                if (this.field_70146_Z.nextFloat() < f12 && this.type >= 31 || this.disloNumberSeventeen > 0) {
                    entityplayer.func_184811_cZ().func_185145_a(itemstack1.func_77973_b(), 100);
                    this.field_70170_p.func_72960_a((Entity)entityplayer, (byte)30);
                }
                this.func_184590_k(MinimumDamage);
                return true;
            }
        }
        DamageSource s = DamageSource.func_76358_a((EntityLivingBase)this);
        float damage = 0.0f;
        if (target.func_70644_a(SPPotions.VIRA_E)) {
            damage = MinimumDamage * (float)(target.func_70660_b(SPPotions.VIRA_E).func_76458_c() + 1);
        }
        damage += MinimumDamage;
        try {
            if (target.func_110142_aN() != null && s != null) {
                target.func_110142_aN().func_94547_a(s, f1, damage);
            }
        }
        catch (Exception f12) {
            // empty catch block
        }
        if (target.func_110139_bj() > 0.0f) {
            target.func_70606_j(f1 - damage / 2.0f);
            target.func_110149_m(target.func_110139_bj() - damage / 2.0f);
        } else {
            target.func_70606_j(f1 - damage);
        }
        this.field_70170_p.func_72960_a((Entity)target, (byte)2);
        if (target.func_110143_aJ() <= 0.0f) {
            ItemStack itemstack = null;
            for (EnumHand enumhand : EnumHand.values()) {
                ItemStack itemstack1 = target.func_184586_b(enumhand);
                if (itemstack1.func_77973_b() != Items.field_190929_cY) continue;
                itemstack = itemstack1.func_77946_l();
                itemstack1.func_190918_g(1);
                break;
            }
            if (itemstack != null) {
                if (target instanceof EntityPlayerMP) {
                    EntityPlayerMP entityplayermp = (EntityPlayerMP)target;
                    entityplayermp.func_71029_a(StatList.func_188057_b((Item)Items.field_190929_cY));
                    CriteriaTriggers.field_193130_A.func_193187_a(entityplayermp, itemstack);
                }
                target.func_70606_j(1.0f);
                target.func_70674_bp();
                target.func_70690_d(new PotionEffect(MobEffects.field_76428_l, 900, 1));
                target.func_70690_d(new PotionEffect(MobEffects.field_76444_x, 100, 1));
                target.field_70170_p.func_72960_a((Entity)target, (byte)35);
            } else {
                target.func_70645_a(s);
                this.killcount += 1.0;
            }
        }
        return true;
    }

    public void func_70604_c(@Nullable EntityLivingBase livingBase) {
        if (livingBase instanceof EntityPlayer && livingBase.func_70644_a(SPPotions.THE_SIGN_E)) {
            super.func_70604_c(null);
            return;
        }
        super.func_70604_c(livingBase);
    }

    public boolean attackEntityAsMobFood(Entity target, boolean hit, int foodnumber, double foodchance) {
        if (this.disloNumberSeventeen > 0) {
            foodnumber = 1;
            foodchance = 1.0;
        }
        if (this.field_70146_Z.nextDouble() >= foodchance && hit) {
            return false;
        }
        if (!(target instanceof EntityPlayer)) {
            return false;
        }
        if (foodnumber <= 0) {
            return false;
        }
        EntityPlayer player = (EntityPlayer)target;
        if (player.field_71075_bZ.field_75098_d) {
            return false;
        }
        for (int i = 0; i < player.field_71071_by.field_70462_a.size(); ++i) {
            if (!(((ItemStack)player.field_71071_by.field_70462_a.get(i)).func_77973_b() instanceof ItemFood)) continue;
            int amm = this.field_70146_Z.nextInt(foodnumber) + 1;
            ((ItemStack)player.field_71071_by.field_70462_a.get(i)).func_190918_g(amm);
            ItemStack stack = new ItemStack(SPItems.infected_drop, amm);
            EntityItem entityitem = new EntityItem(this.field_70170_p, target.field_70165_t, target.field_70163_u, target.field_70161_v, stack);
            entityitem.func_174869_p();
            this.field_70170_p.func_72838_d((Entity)entityitem);
            return true;
        }
        if (player.func_184582_a(EntityEquipmentSlot.OFFHAND).func_77973_b() instanceof ItemFood) {
            int amm = this.field_70146_Z.nextInt(foodnumber) + 1;
            player.func_184582_a(EntityEquipmentSlot.OFFHAND).func_190918_g(amm);
            ItemStack stack = new ItemStack(SPItems.infected_drop, amm);
            EntityItem entityitem = new EntityItem(this.field_70170_p, target.field_70165_t, target.field_70163_u, target.field_70161_v, stack);
            entityitem.func_174869_p();
            this.field_70170_p.func_72838_d((Entity)entityitem);
            return true;
        }
        return false;
    }

    public void func_70074_a(EntityLivingBase entityLivingIn) {
        if (this.field_70170_p.field_72995_K) {
            return;
        }
        if (this.killcount >= 0.0) {
            if (this.getSkin() != 120) {
                this.killcount += 1.0;
            } else if (this.field_70146_Z.nextInt(3) == 0) {
                this.killcount += 1.0;
            }
        }
        if (SPConfigSystems.useEvolution) {
            if (this.func_70644_a(SPPotions.PIVOT_E)) {
                int amp = this.func_70660_b(SPPotions.PIVOT_E).func_76458_c();
                SPSaveData.get(this.field_70170_p, 35).setTotalKills(this.field_70170_p.field_73011_w.getDimension(), SPConfigSystems.valueKill * (SPConfigSystems.pivotPointMultiplier * (amp + 1)), true, this.field_70170_p, true, 17);
            } else {
                SPSaveData.get(this.field_70170_p, 34).setTotalKills(this.field_70170_p.field_73011_w.getDimension(), SPConfigSystems.valueKill, true, this.field_70170_p, true, 16);
            }
        }
        if (SPConfigWorld.originActivated) {
            ParasiteEventWorld.setOriginInHealth(this.field_70170_p, this.func_180425_c(), (int)((double)entityLivingIn.func_110138_aP() * SPConfigWorld.originKillMultiplier), true);
            if (SPConfigWorld.originTriggerKill > this.field_70170_p.field_73012_v.nextDouble()) {
                ParasiteEventWorld.placeOriginInWorld(this.field_70170_p, new BlockPos(this.field_70165_t, this.field_70163_u, this.field_70161_v), SPConfigWorld.originHealth, SPConfigWorld.originRadius);
            }
        }
        if (entityLivingIn.func_70644_a(SPPotions.COTH_E) && this.field_70146_Z.nextDouble() < (double)SPConfigSystems.cothConvert) {
            ParasiteEventEntity.convertEntity(entityLivingIn, entityLivingIn.getEntityData(), true, SPConfigSystems.COTHVictimParasite);
        }
        if (this.func_70644_a(SPPotions.PARATE_E)) {
            int bonuss = this.func_70660_b(SPPotions.PARATE_E).func_76458_c() + 1;
            if (entityLivingIn.func_110148_a(SharedMonsterAttributes.field_111267_a) != null) {
                this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() + entityLivingIn.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() * (SPConfigSystems.parateMuch * (double)bonuss));
            }
            if (entityLivingIn.func_110148_a(SharedMonsterAttributes.field_188791_g) != null) {
                this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111125_b() + entityLivingIn.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111125_b() * (SPConfigSystems.parateMuch * (double)bonuss));
            }
            if (entityLivingIn.func_110148_a(SharedMonsterAttributes.field_111264_e) != null) {
                this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111125_b() + entityLivingIn.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111125_b() * (SPConfigSystems.parateMuch * (double)bonuss));
            }
        }
        this.func_70691_i(entityLivingIn.func_110143_aJ() * this.geneMobHealing);
        this.setWait(10);
    }

    public boolean func_70687_e(PotionEffect potioneffectIn) {
        if (this.disloNumberEleven > 0 && potioneffectIn.func_188419_a().func_76398_f()) {
            return false;
        }
        Potion potion = potioneffectIn.func_188419_a();
        if (potion == SPPotions.COTH_E || potion == SPPotions.VIRA_E || potion == SPPotions.CORRO_E || potion == SPPotions.DLER_E) {
            return false;
        }
        return super.func_70687_e(potioneffectIn);
    }

    protected boolean func_184228_n(Entity entityIn) {
        if (entityIn instanceof EntityBoat || entityIn instanceof EntityMinecart) {
            return false;
        }
        return super.func_184228_n(entityIn);
    }

    public boolean func_70686_a(Class<? extends EntityLivingBase> cls) {
        if (cls == EntityPlayer.class || cls == EntityPlayerMP.class) {
            return true;
        }
        String name = null;
        try {
            name = EntityList.func_191306_a(cls).toString();
        }
        catch (Exception e) {
            return true;
        }
        if (name == null) {
            return true;
        }
        if (name.contains("subspaceparasite")) {
            return false;
        }
        return !SPConfig.mobAttackingFull || !ParasiteEventEntity.checkName(name, SPConfig.mobattackingBlackList, SPConfig.mobattackingBlackListWhite);
    }

    public boolean isCreatureType(EnumCreatureType type, boolean forSpawnCount) {
        if (forSpawnCount) {
            return false;
        }
        if (this instanceof EntityLiving && this.func_104002_bU()) {
            return false;
        }
        return type.func_75598_a().isAssignableFrom(this.getClass());
    }

    protected boolean func_184645_a(EntityPlayer player, EnumHand hand) {
        if (hand != EnumHand.MAIN_HAND || this.field_70170_p.field_72995_K) {
            return super.func_184645_a(player, hand);
        }
        Item wea = player.func_184582_a(EntityEquipmentSlot.MAINHAND).func_77973_b();
        if (wea == SPItems.itemEvolve) {
            this.killcount += 1.0E9;
            this.killcount += 1.0E9;
            this.killcount += 1.0E9;
        } else if (wea == SPItems.itemDevolve) {
            this.canChangeVariant = true;
            this.func_70645_a(DamageSource.field_76377_j);
            this.func_70106_y();
        } else if (wea == SPItems.itemVariant) {
            this.canChangeVariant = true;
            this.func_180482_a(this.field_70170_p.func_175649_E(new BlockPos((Entity)this)), null);
            this.canChangeVariant = false;
        }
        return super.func_184645_a(player, hand);
    }

    public void func_70624_b(@Nullable EntityLivingBase entitylivingbaseIn) {
        if (entitylivingbaseIn instanceof EntityPlayer && entitylivingbaseIn.func_70644_a(SPPotions.THE_SIGN_E)) {
            super.func_70624_b(null);
            return;
        }
        if (this.targetNewCool > 0 && entitylivingbaseIn != null) {
            return;
        }
        super.func_70624_b(entitylivingbaseIn);
        if (entitylivingbaseIn != null && entitylivingbaseIn != this.last) {
            this.doLast(entitylivingbaseIn);
            this.last = entitylivingbaseIn;
        }
    }

    public void setAttackTarget(@Nullable EntityLivingBase entitylivingbaseIn, int negative) {
        this.func_70624_b(entitylivingbaseIn);
        this.targetNewCool = negative;
    }

    protected void doLast(EntityLivingBase entitylivingbaseIn) {
        if (entitylivingbaseIn == null) {
            return;
        }
        SPWorldData data = SPWorldData.get(this.field_70170_p);
        if (data == null) {
            return;
        }
        if (data.nearestInfectionPosition(false, this.func_180425_c()) == null) {
            return;
        }
        entitylivingbaseIn.func_70690_d(new PotionEffect(SPPotions.SPOT_E, 1200, 0, false, false));
        if (SPConfigSystems.useOneMind) {
            ParasiteEventEntity.alertOthers(this, entitylivingbaseIn, this.field_70170_p, 7);
        }
    }

    public int canBeStored() {
        return this.canBeStored;
    }

    public int getSelfeState() {
        return (Integer)this.field_70180_af.func_187225_a(SELFE);
    }

    public void setSelfeState(int state) {
        this.field_70180_af.func_187227_b(SELFE, (Object)state);
    }

    public float getMiniDamage() {
        return this.MiniDamage;
    }

    public int getLLeap() {
        return this.liquidLeap;
    }

    public void setWorkTask(boolean in) {
        this.canWorkTask = in;
    }

    public boolean shouldWorkTask() {
        return this.canWorkTask;
    }

    public void setParasiteToFollow(@Nullable EntityParasiteBase in) {
        this.owner = in;
    }

    public EntityParasiteBase getParasiteFollowing() {
        if (this.owner != null) {
            if (this.owner.func_70089_S()) {
                return this.owner;
            }
            this.setParasiteToFollow(null);
        }
        return null;
    }

    public void SetRooter(@Nullable EntityParasiteBase in) {
        this.notOwner = in;
    }

    public byte getParasiteStatus() {
        return (Byte)this.field_70180_af.func_187225_a(SPECIAL);
    }

    public void setParasiteStatus(int state) {
        this.field_70180_af.func_187227_b(SPECIAL, (Object)((byte)state));
    }

    public void setWait(int in) {
        this.waitInt = in;
    }

    public int getWait() {
        return this.waitInt;
    }

    public void resetIdleTime() {
        this.field_70708_bq = 0;
    }

    public byte getPhaseCreated() {
        return this.phaseCreated;
    }

    public byte getLevelCreated() {
        return this.levelCreated;
    }

    public int getSkin() {
        return ((Byte)this.field_70180_af.func_187225_a(SKIN)).intValue();
    }

    public boolean getColdL() {
        return (Boolean)this.field_70180_af.func_187225_a(COLD_L);
    }

    public boolean getDislo15() {
        return (Boolean)this.field_70180_af.func_187225_a(DISLO15);
    }

    public void setDislo15(boolean in) {
        this.field_70180_af.func_187227_b(DISLO15, (Object)in);
    }

    public void setSkin(int texture) {
        if (this.getSkin() == 120 && !this.canChangeVariant) {
            return;
        }
        this.field_70180_af.func_187227_b(SKIN, (Object)((byte)texture));
    }

    public int getCCDeathValue() {
        return this.oneMindDeathValue;
    }

    public void setAttackCooldownAni(int i) {
        this.attackCooldown = i;
    }

    public int getAttackCooldownAni() {
        return this.attackCooldown;
    }

    public void setKillC(double i) {
        this.killcount = i;
    }

    public double getKillC() {
        return this.killcount;
    }

    public byte getParasiteType() {
        return this.type;
    }

    public void func_70642_aH() {
        if (((Boolean)this.field_70180_af.func_187225_a(DISLO15)).booleanValue()) {
            return;
        }
        super.func_70642_aH();
    }

    public void func_70645_a(DamageSource cause) {
        super.func_70645_a(cause);
        if (!this.field_70170_p.field_72995_K) {
            if (this.onDeathDislo(cause)) {
                return;
            }
            this.spawnCyst();
            ParasiteEventEntity.spawnBeckon(this.field_70170_p, cause, this);
            ParasiteEventEntity.leaveScent(this.field_70170_p, cause, this);
            ParasiteEventWorld.setOriginInHealth(this.field_70170_p, this.func_180425_c(), (int)(-((double)this.func_110138_aP() * SPConfigWorld.originParasiteDeath)), true);
            if (SPConfigSystems.useEvolution && !this.func_70644_a(SPPotions.DEBAR_E)) {
                SPSaveData.get(this.field_70170_p, 39).setTotalKills(this.field_70170_p.field_73011_w.getDimension(), -this.valueEvDeath, true, this.field_70170_p, false, 18);
            }
            if (cause.func_76346_g() != null && cause.func_76346_g() instanceof EntityPlayer) {
                this.checkWeapon((EntityPlayer)cause.func_76346_g());
            }
        }
    }

    protected boolean onDeathDislo(DamageSource cause) {
        List moblist;
        int count;
        ParasiteEventWorld.setDisloWorldPhase(this.field_70170_p, SPAttributes.EVENTPARADEATH, SPConfigSystems.chanceEventParaDeath, 0, null);
        SPSaveData data = SPSaveData.get(this.field_70170_p, 38);
        if (this.disloNumberTwo && (count = data.getCurrentCode(this.field_70170_p.field_73011_w.getDimension(), 2)) >= 1 && cause.func_76346_g() instanceof EntityLivingBase) {
            count = (int)((float)count + this.func_110138_aP());
            int furaa = data.getCurrentCodeDuration(this.field_70170_p.field_73011_w.getDimension(), 2);
            SPPotions.applyStackPotion(SPPotions.JUGG_E, (EntityLivingBase)cause.func_76346_g(), furaa * 20 + 50, 1);
            data.setCurrentCode(this.field_70170_p.field_73011_w.getDimension(), 2, count, furaa, null, false, 0);
        }
        if (this.disloNumberSeven && (count = data.getCurrentCode(this.field_70170_p.field_73011_w.getDimension(), 7)) >= 1) {
            AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_186662_g(7.0);
            moblist = this.field_70170_p.func_72872_a(EntityParasiteBase.class, axisalignedbb);
            for (EntityParasiteBase mob : moblist) {
                mob.func_70691_i(count);
            }
            this.func_184185_a(SPSounds.RATHOL_BOOM, 0.2f, 0.7f);
            this.particleStatus((byte)7);
        }
        if (this.disloNumberEight) {
            count = data.getCurrentCode(this.field_70170_p.field_73011_w.getDimension(), 8);
            if (count >= 1) {
                AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_186662_g(7.0);
                moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
                for (EntityParasiteBase mob : moblist) {
                    if (mob instanceof EntityParasiteBase) continue;
                    mob.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), count);
                }
            }
            this.func_184185_a(SPSounds.RATHOL_BOOM, 0.2f, 0.7f);
            this.particleStatus((byte)7);
        }
        if (this.disloNumberNine) {
            count = data.getCurrentCode(this.field_70170_p.field_73011_w.getDimension(), 9);
            if (count >= 1) {
                AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_186662_g(7.0);
                moblist = this.field_70170_p.func_72872_a(EntityPlayer.class, axisalignedbb);
                for (EntityParasiteBase mob : moblist) {
                    mob.func_71020_j(count);
                }
            }
            this.func_184185_a(SPSounds.RATHOL_BOOM, 0.2f, 0.7f);
            this.particleStatus((byte)7);
        }
        if (this.disloNumberEighteen && data.getCurrentCode(this.field_70170_p.field_73011_w.getDimension(), 18) >= 1) {
            this.field_70728_aV = 0;
        }
        return false;
    }

    private void checkWeapon(EntityPlayer playerIn) {
        if (!SPConfigSystems.useScent) {
            return;
        }
        if (this.getLevelCreated() < SPConfigSystems.deveScentUse) {
            return;
        }
        Item ga = playerIn.func_184582_a(EntityEquipmentSlot.MAINHAND).func_77973_b();
        if ((ga instanceof WeaponToolMeleeBase || ga instanceof WeaponToolRangeBase) && this.field_70146_Z.nextInt(10) == 0) {
            playerIn.func_70690_d(new PotionEffect(SPPotions.PREY_E, 600, 0, false, false));
        }
    }

    protected void func_70609_aI() {
        if (this.canModRender == 1) {
            if (this.madeRng == 0) {
                this.setSelfeState(1);
                this.setParasiteStatus(6);
                this.dyingBurst(true, 1);
            } else {
                this.onDeathUpdateOG();
            }
        } else if (this.canModRender == 2) {
            if (this.madeRng == 0) {
                this.selfExplode();
                this.OnDeathHelper();
            } else {
                this.onDeathUpdateOG();
            }
        } else {
            this.onDeathUpdateOG();
        }
    }

    protected void onDeathUpdateOG() {
        ++this.field_70725_aQ;
        if (this.field_70725_aQ == 20) {
            if (!this.field_70170_p.field_72995_K && (this.func_70684_aJ() || this.field_70718_bc > 0 && this.func_146066_aG() && this.field_70170_p.func_82736_K().func_82766_b("doMobLoot")) && !this.func_70644_a(SPPotions.DEBAR_E)) {
                int j;
                int i;
                if (SPConfigSystems.useEvolution) {
                    if (SPSaveData.get(this.field_70170_p, 37).getEvolutionPhase(this.field_70170_p.field_73011_w.getDimension()) < SPConfigSystems.evolutionPArasitesWithoutXP) {
                        i = this.func_70693_a(this.field_70717_bb);
                        for (i = ForgeEventFactory.getExperienceDrop((EntityLivingBase)this, (EntityPlayer)this.field_70717_bb, (int)i); i > 0; i -= j) {
                            j = EntityXPOrb.func_70527_a((int)i);
                            this.field_70170_p.func_72838_d((Entity)new EntityXPOrb(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, j));
                        }
                    }
                } else {
                    i = this.func_70693_a(this.field_70717_bb);
                    for (i = ForgeEventFactory.getExperienceDrop((EntityLivingBase)this, (EntityPlayer)this.field_70717_bb, (int)i); i > 0; i -= j) {
                        j = EntityXPOrb.func_70527_a((int)i);
                        this.field_70170_p.func_72838_d((Entity)new EntityXPOrb(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, j));
                    }
                }
            }
            this.func_70106_y();
            if (this.field_70170_p.field_72995_K) {
                for (int k = 0; k < 20; ++k) {
                    this.spawnParticles(SPEnumParticle.GCLOUD, 127, 0, 0);
                }
            }
        }
    }

    protected void dyingBurst(boolean fromDeath, int value) {
        int i = this.getSelfeState();
        if (i <= 0 || this.timeSinceIgnited == 0) {
            // empty if block
        }
        this.timeSinceIgnited += i * value;
        if (this.timeSinceIgnited < 0) {
            this.timeSinceIgnited = 0;
        }
        if (this.timeSinceIgnited >= this.fuseTime) {
            this.timeSinceIgnited = this.fuseTime;
            this.selfExplode();
            if (fromDeath) {
                this.OnDeathHelper();
            }
        }
    }

    public void OnDeathHelper() {
        if (!this.field_70170_p.field_72995_K && (this.func_70684_aJ() || this.field_70718_bc > 0 && this.func_146066_aG() && this.field_70170_p.func_82736_K().func_82766_b("doMobLoot"))) {
            int j;
            int i = this.func_70693_a(this.field_70717_bb);
            for (i = ForgeEventFactory.getExperienceDrop((EntityLivingBase)this, (EntityPlayer)this.field_70717_bb, (int)i); i > 0; i -= j) {
                j = EntityXPOrb.func_70527_a((int)i);
                this.field_70170_p.func_72838_d((Entity)new EntityXPOrb(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, j));
            }
        }
    }

    protected void selfExplode() {
        if (this.field_70170_p.field_72995_K) {
            this.spawnEffectsGore();
        }
        if (!this.field_70170_p.field_72995_K) {
            this.spawnGore();
            this.func_184185_a(SPSounds.MOBEXPLOTION, 1.0f, 1.0f);
            this.field_70729_aU = true;
            this.func_70106_y();
            EntityToxicCloud entityareaeffectcloud = new EntityToxicCloud(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v);
            entityareaeffectcloud.setRadius(this.field_70130_N * 1.5f, 0.5f);
            entityareaeffectcloud.setWaitTime(10);
            entityareaeffectcloud.setDuration(entityareaeffectcloud.getDuration() / 2);
            entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / (float)entityareaeffectcloud.getDuration());
            entityareaeffectcloud.addEffect(new PotionEffect(MobEffects.field_76436_u, 300, 0));
            entityareaeffectcloud.addEffect(new PotionEffect(SPPotions.COTH_E, 3600, 0, false, false));
            this.field_70170_p.func_72838_d((Entity)entityareaeffectcloud);
        }
    }

    protected void spawnGore() {
    }

    protected void func_82167_n(Entity entityIn) {
        super.func_82167_n(entityIn);
    }

    public boolean func_70601_bi() {
        if (SPConfigSystems.useEvolution) {
            if (this.phaseCreated >= SPConfigSystems.evolutionSpawningIgnoreSunlight || this.phaseCreated == -1 && SPConfigSystems.phaseLightlessMinusOne) {
                IBlockState iblockstate = this.field_70170_p.func_180495_p(new BlockPos((Entity)this).func_177977_b());
                return iblockstate.func_189884_a((Entity)this) && this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL && this.isValidLightLevelTwo() && SPConfig.spawnDays <= (int)this.field_70170_p.func_82737_E();
            }
            IBlockState iblockstate = this.field_70170_p.func_180495_p(new BlockPos((Entity)this).func_177977_b());
            return iblockstate.func_189884_a((Entity)this) && this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL && this.isValidLightLevelOne() && SPConfig.spawnDays <= (int)this.field_70170_p.func_82737_E();
        }
        if (SPConfig.ignoreL) {
            IBlockState iblockstate = this.field_70170_p.func_180495_p(new BlockPos((Entity)this).func_177977_b());
            return iblockstate.func_189884_a((Entity)this) && this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL && this.isValidLightLevelTwo() && SPConfig.spawnDays <= (int)this.field_70170_p.func_82737_E();
        }
        IBlockState iblockstate = this.field_70170_p.func_180495_p(new BlockPos((Entity)this).func_177977_b());
        return iblockstate.func_189884_a((Entity)this) && this.field_70170_p.func_175659_aa() != EnumDifficulty.PEACEFUL && this.isValidLightLevelOne() && SPConfig.spawnDays <= (int)this.field_70170_p.func_82737_E();
    }

    protected void func_70623_bb() {
        Event.Result result = null;
        if (this.func_104002_bU()) {
            this.field_70708_bq = 0;
        } else if ((this.field_70708_bq & 0x1F) == 31 && (result = ForgeEventFactory.canEntityDespawn((EntityLiving)this)) != Event.Result.DEFAULT) {
            if (result == Event.Result.DENY) {
                this.field_70708_bq = 0;
            } else {
                this.spawnCyst();
                this.storeBefDes();
                this.func_70106_y();
            }
        } else {
            EntityPlayer entity = this.field_70170_p.func_72890_a((Entity)this, -1.0);
            if (entity != null) {
                double d0 = entity.field_70165_t - this.field_70165_t;
                double d1 = entity.field_70163_u - this.field_70163_u;
                double d2 = entity.field_70161_v - this.field_70161_v;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if (this.func_70692_ba() && d3 > 16384.0) {
                    this.spawnCyst();
                    this.storeBefDes();
                    this.func_70106_y();
                }
                if (this.field_70708_bq > 600 && this.field_70146_Z.nextInt(800) == 0 && d3 > 1024.0 && this.func_70692_ba()) {
                    this.spawnCyst();
                    this.storeBefDes();
                    this.func_70106_y();
                } else if (d3 < 1024.0) {
                    this.field_70708_bq = 0;
                }
            }
        }
    }

    public void reduceIdleChance(int chance, int amount) {
        if (this.field_70146_Z.nextInt(chance) == 0) {
            this.field_70708_bq -= amount;
        }
    }

    protected void storeBefDes() {
        Entity dod;
        if (this.dodFatherID != 0 && (dod = this.field_70170_p.func_73045_a(this.dodFatherID)) != null && dod.func_70089_S() && dod instanceof EntityPDispatcher) {
            ((EntityPDispatcher)dod).storeParasite(this);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    protected boolean isValidLightLevelOne() {
        if (this.field_70170_p.func_180494_b(this.func_180425_c()) instanceof BiomeParasiteBase) {
            return this.isValidLightLevelTwo();
        }
        BlockPos blockpos = new BlockPos(this.field_70165_t, this.func_174813_aQ().field_72338_b, this.field_70161_v);
        if (this.field_70170_p.func_175642_b(EnumSkyBlock.SKY, blockpos) > this.field_70146_Z.nextInt(32)) {
            return false;
        }
        int i = this.field_70170_p.func_175671_l(blockpos);
        if (this.field_70170_p.func_72911_I()) {
            int j = this.field_70170_p.func_175657_ab();
            this.field_70170_p.func_175692_b(10);
            i = this.field_70170_p.func_175671_l(blockpos);
            this.field_70170_p.func_175692_b(j);
        }
        if (i > this.field_70146_Z.nextInt(8)) return false;
        BlockPos blockPos = new BlockPos(this.field_70165_t, this.func_174813_aQ().field_72338_b, this.field_70161_v);
        if (!(this.func_180484_a(blockPos) >= 0.0f)) return false;
        return true;
    }

    protected boolean isValidLightLevelTwo() {
        BlockPos blockpos = new BlockPos(this.field_70165_t, this.func_174813_aQ().field_72338_b, this.field_70161_v);
        int light = this.field_70170_p.func_175642_b(EnumSkyBlock.BLOCK, blockpos);
        if (light > this.field_70146_Z.nextInt(1000) || light > 7) {
            return false;
        }
        return this.field_70146_Z.nextInt(8) == 0;
    }

    public void lookAt(Entity in) {
        this.lookAt(in.field_70165_t, in.field_70163_u, in.field_70161_v);
    }

    public void lookAt(double x, double y, double z) {
        double dx = x - this.field_70165_t;
        double dy = y - (this.field_70163_u + (double)this.func_70047_e());
        double dz = z - this.field_70161_v;
        double yaw = Math.atan2(dz, dx) * 57.29577951308232 - 90.0;
        double distance = Math.sqrt(dx * dx + dz * dz);
        double pitch = -Math.atan2(dy, distance) * 57.29577951308232;
        this.field_70177_z = (float)yaw;
        this.field_70125_A = (float)pitch;
    }

    private boolean isInParasiteBiome() {
        return this.field_70146_Z.nextInt(8) == 0 && this.field_70170_p.func_180494_b(this.func_180425_c()) instanceof BiomeParasiteBase;
    }

    public IEntityLivingData func_180482_a(DifficultyInstance difficulty, IEntityLivingData livingdata) {
        IEntityLivingData floo = super.func_180482_a(difficulty, livingdata);
        if (SPConfigSystems.useEvolution && !this.field_70170_p.field_72995_K) {
            this.phaseCreated = SPSaveData.get(this.field_70170_p, 36).getEvolutionPhase(this.field_70170_p.field_73011_w.getDimension());
            if (this.phaseCreated >= SPConfigSystems.evolutionParasiteStatIncrease) {
                this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() * (double)(1.0f + SPConfigSystems.evolutionParasiteStatIncreaseValue));
                this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111128_a(this.func_110148_a(SharedMonsterAttributes.field_188791_g).func_111125_b() * (double)(1.0f + SPConfigSystems.evolutionParasiteStatIncreaseValue));
                this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111125_b() * (double)(1.0f + SPConfigSystems.evolutionParasiteStatIncreaseValue));
            }
        }
        return floo;
    }

    protected boolean applyColdBiome() {
        Biome c = this.field_70170_p.func_180494_b(this.func_180425_c());
        if (c == null) {
            return false;
        }
        if (this.isBiomeBlacklistedForFrozen(c)) {
            return false;
        }
        if (c.func_180626_a(this.func_180425_c()) <= (float)SPConfigWorld.frozenVariantTempThreshold || c.func_150559_j() || this.canChangeVariant) {
            this.setSkin(120);
            this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111125_b() * 0.6);
            this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111125_b() * 1.4);
            this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111125_b() * 1.3);
            return true;
        }
        return false;
    }

    private boolean isBiomeBlacklistedForFrozen(Biome biome) {
        if (biome == null || biome.getRegistryName() == null) {
            return false;
        }
        String biomeKey = biome.getRegistryName().toString();
        for (String raw : SPConfigWorld.frozenVariantBiomeBlacklist) {
            if (raw == null || raw.isEmpty() || !biomeKey.equalsIgnoreCase(raw.trim())) continue;
            return true;
        }
        return false;
    }

    public void func_70014_b(NBTTagCompound compound) {
        super.func_70014_b(compound);
        compound.func_74757_a("dsltwo", this.disloNumberTwo);
        compound.func_74757_a("dslthree", this.disloNumberThree);
        compound.func_74757_a("dslfour", this.disloNumberFour);
        compound.func_74757_a("dslsix", this.disloNumberSix);
        compound.func_74757_a("dslseven", this.disloNumberSeven);
        compound.func_74757_a("dsleight", this.disloNumberEight);
        compound.func_74757_a("dslnine", this.disloNumberNine);
        compound.func_74768_a("dsleleven", this.disloNumberEleven);
        compound.func_74768_a("dslfifteen", this.disloNumberFifteen);
        compound.func_74757_a("dslfifteenb", ((Boolean)this.field_70180_af.func_187225_a(DISLO15)).booleanValue());
        compound.func_74768_a("dslsixteen", this.disloNumberSixteen);
        compound.func_74768_a("dslseventeen", this.disloNumberSeventeen);
        compound.func_74757_a("dsleighteen", this.disloNumberEighteen);
        compound.func_74768_a("dslnineteen", this.disloNumberNineteen);
        compound.func_74780_a("dslnineteenv", this.disloNumberNineteenValue);
        compound.func_74757_a("dsltyone", this.disloNumberTwentyone);
        compound.func_74757_a("dsltytwo", this.disloNumberTwentytwo);
        compound.func_74768_a("parasitetype", this.getSkin());
        compound.func_74757_a("parasitedespawn", this.func_70692_ba());
        compound.func_74776_a("parasitekills", (float)this.getKillC());
        compound.func_74757_a("parasitecolob", this.getApplyColonyB());
        compound.func_74757_a("parasitecoldl", ((Boolean)this.field_70180_af.func_187225_a(COLD_L)).booleanValue());
        compound.func_74774_a("phasecreat", this.phaseCreated);
        compound.func_74774_a("levelcreat", this.levelCreated);
        compound.func_74774_a("paragener", this.paraGeneration);
        NBTTagList allResS = new NBTTagList();
        NBTTagList allResI = new NBTTagList();
        if (this.inbBlockName.size() != this.inbBlockNumber.size()) {
            return;
        }
        for (int i = 0; i < this.inbBlockName.size(); ++i) {
            String res = this.inbBlockName.get(i);
            NBTTagCompound resT = new NBTTagCompound();
            resT.func_74778_a("blocks" + i, res);
            allResS.func_74742_a((NBTBase)resT);
            int resi = this.inbBlockNumber.get(i);
            NBTTagCompound resU = new NBTTagCompound();
            resU.func_74768_a("blocki" + i, resi);
            allResI.func_74742_a((NBTBase)resU);
        }
        compound.func_74782_a("srpinvblocksname", (NBTBase)allResS);
        compound.func_74782_a("srpinvblocksnumber", (NBTBase)allResI);
    }

    public void func_70037_a(NBTTagCompound compound) {
        super.func_70037_a(compound);
        if (compound.func_150297_b("dsltwo", 99)) {
            this.disloNumberTwo = compound.func_74767_n("dsltwo");
        }
        if (compound.func_150297_b("dslthree", 99)) {
            this.disloNumberThree = compound.func_74767_n("dslthree");
        }
        if (compound.func_150297_b("dslfour", 99)) {
            this.disloNumberFour = compound.func_74767_n("dslfour");
        }
        if (compound.func_150297_b("dslsix", 99)) {
            this.disloNumberSix = compound.func_74767_n("dslsix");
        }
        if (compound.func_150297_b("dslseven", 99)) {
            this.disloNumberSeven = compound.func_74767_n("dslseven");
        }
        if (compound.func_150297_b("dsleight", 99)) {
            this.disloNumberEight = compound.func_74767_n("dsleight");
        }
        if (compound.func_150297_b("dslnine", 99)) {
            this.disloNumberNine = compound.func_74767_n("dslnine");
        }
        if (compound.func_150297_b("dsleleven", 99)) {
            this.disloNumberEleven = compound.func_74762_e("dsleleven");
        }
        if (compound.func_150297_b("dslfifteen", 99)) {
            this.disloNumberFifteen = compound.func_74762_e("dslfifteen");
        }
        if (compound.func_150297_b("dslfifteenb", 99)) {
            this.field_70180_af.func_187227_b(DISLO15, (Object)compound.func_74767_n("dslfifteenb"));
        }
        if (compound.func_150297_b("dslsixteen", 99)) {
            this.disloNumberSixteen = compound.func_74762_e("dslsixteen");
        }
        if (compound.func_150297_b("dslseventeen", 99)) {
            this.disloNumberSeventeen = compound.func_74762_e("dslseventeen");
        }
        if (compound.func_150297_b("dsleighteen", 99)) {
            this.disloNumberEighteen = compound.func_74767_n("dsleighteen");
        }
        if (compound.func_150297_b("dslnineteen", 99)) {
            this.disloNumberNineteen = compound.func_74762_e("dslnineteen");
        }
        if (compound.func_150297_b("dslnineteenv", 99)) {
            this.disloNumberNineteenValue = compound.func_74769_h("dslnineteenv");
        }
        if (compound.func_150297_b("dsltyone", 99)) {
            this.disloNumberTwentyone = compound.func_74767_n("dsltyone");
        }
        if (compound.func_150297_b("dsltytwo", 99)) {
            this.disloNumberTwentytwo = compound.func_74767_n("dsltytwo");
        }
        if (compound.func_150297_b("parasitetype", 99)) {
            this.setSkin(compound.func_74762_e("parasitetype"));
        }
        if (compound.func_150297_b("parasitedespawn", 99)) {
            this.cannotDespawn(compound.func_74767_n("parasitedespawn"));
        }
        if (compound.func_150297_b("parasitekills", 99)) {
            this.setKillC(compound.func_74760_g("parasitekills"));
        }
        if (compound.func_150297_b("parasitecolob", 99)) {
            this.setApplyColonyB(compound.func_74767_n("parasitecolob"));
        }
        if (compound.func_150297_b("parasitecoldl", 99)) {
            this.field_70180_af.func_187227_b(COLD_L, (Object)compound.func_74767_n("parasitecoldl"));
        }
        if (compound.func_150297_b("phasecreat", 99)) {
            this.phaseCreated = compound.func_74771_c("phasecreat");
        }
        if (compound.func_150297_b("levelcreat", 99)) {
            this.levelCreated = compound.func_74771_c("levelcreat");
        }
        if (compound.func_150297_b("paragener", 99)) {
            this.paraGeneration = compound.func_74771_c("paragener");
        }
        if (compound.func_74764_b("srpinvblocksname")) {
            NBTTagList allResS = compound.func_150295_c("srpinvblocksname", 10);
            NBTTagList allResI = compound.func_150295_c("srpinvblocksnumber", 10);
            if (allResS.func_74745_c() != allResI.func_74745_c()) {
                return;
            }
            for (int i = 0; i < allResS.func_74745_c(); ++i) {
                NBTTagCompound resT = allResS.func_150305_b(i);
                String res = resT.func_74779_i("blocks" + i);
                this.inbBlockName.add(i, res);
                NBTTagCompound resU = allResI.func_150305_b(i);
                int resi = resU.func_74762_e("blocki" + i);
                this.inbBlockNumber.add(i, resi);
            }
        }
    }

    public void particleStatus(byte id) {
        this.field_70170_p.func_72960_a((Entity)this, id);
    }

    @SideOnly(value=Side.CLIENT)
    protected void spawnEffectsGore() {
    }

    @SideOnly(value=Side.CLIENT)
    public void func_70103_a(byte id) {
        switch (id) {
            case 5: {
                for (int i = 0; i <= 10; ++i) {
                    this.spawnParticles(SPEnumParticle.RHAPPY, 0, 0, 0);
                }
                break;
            }
            case 6: {
                for (int i = 0; i <= 10; ++i) {
                    this.spawnParticles(SPEnumParticle.GCLOUD, 127, 0, 0);
                }
                break;
            }
            case 7: {
                for (int i = 0; i <= 10; ++i) {
                    this.spawnParticles(EnumParticleTypes.EXPLOSION_LARGE);
                }
                break;
            }
            case 8: {
                for (int i = 0; i <= 10; ++i) {
                    this.spawnParticles(SPEnumParticle.BIOMASS, 0, 0, 0);
                }
                break;
            }
            case 10: {
                for (int i = 0; i <= 40; ++i) {
                    this.spawnParticles(EnumParticleTypes.CRIT_MAGIC);
                }
                break;
            }
            case 11: {
                for (int i = 0; i <= 10; ++i) {
                    this.spawnParticles(SPEnumParticle.GCLOUD, 0, 0, 0);
                }
                break;
            }
            case 12: {
                for (int i = 0; i <= 10; ++i) {
                    this.spawnParticles(SPEnumParticle.GCLOUD, 0, 0, 0);
                }
                break;
            }
            case 13: {
                for (int i = 0; i <= 10; ++i) {
                    this.spawnParticles(SPEnumParticle.GSPLASH, 4, -1, -1);
                }
                break;
            }
            case 40: {
                this.madeRng = 0;
                break;
            }
            case 43: {
                int iddd = Block.func_176210_f((IBlockState)this.field_70170_p.func_180495_p(new BlockPos(this.field_70165_t, this.field_70163_u, this.field_70161_v).func_177977_b()));
                for (int i = 0; i <= 10; ++i) {
                    this.field_70170_p.func_175688_a(EnumParticleTypes.BLOCK_CRACK, this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70163_u, this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70146_Z.nextGaussian() * 0.02, this.field_70146_Z.nextGaussian() * 0.02, this.field_70146_Z.nextGaussian() * 0.02, new int[]{iddd});
                }
                break;
            }
            case 51: {
                for (int i = 0; i <= 60; ++i) {
                    if (i % 4 == 0) {
                        this.spawnParticles(SPEnumParticle.GCLOUD, 150, 0, 0);
                    }
                    if (i % 5 != 0) continue;
                    this.spawnParticles(SPEnumParticle.GSPLASH, 2, -1, -1);
                }
                break;
            }
            case 52: {
                for (int i = 0; i <= 80; ++i) {
                    if (i % 3 == 0) {
                        this.spawnParticles(SPEnumParticle.GCLOUD, 200, 200, 0);
                    }
                    if (i % 5 != 0) continue;
                    this.spawnParticles(SPEnumParticle.GSPLASH, 3, -1, -1);
                }
                break;
            }
            default: {
                super.func_70103_a(id);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void spawnParticles(EnumParticleTypes particleType) {
        double d0 = this.field_70146_Z.nextGaussian() * 0.02;
        double d1 = this.field_70146_Z.nextGaussian() * 0.02;
        double d2 = this.field_70146_Z.nextGaussian() * 0.02;
        this.field_70170_p.func_175688_a(particleType, this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70163_u + 0.5 + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, d0, d1, d2, new int[0]);
    }

    @SideOnly(value=Side.CLIENT)
    public void spawnParticles(SPEnumParticle particleType, int r, int g, int b) {
        double d0 = this.field_70146_Z.nextGaussian() * 0.02;
        double d1 = this.field_70146_Z.nextGaussian() * 0.02;
        double d2 = this.field_70146_Z.nextGaussian() * 0.02;
        ParticleSpawner.spawnParticle(particleType, this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70163_u + 0.5 + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, d0, d1, d2, r, g, b);
    }

    @SideOnly(value=Side.CLIENT)
    public void spawnParticlesGore(SPEnumParticle particleType, int r, int g, int b) {
        this.spawnParticlesGore(particleType, r, g, b, 1.0, 4.0);
    }

    @SideOnly(value=Side.CLIENT)
    public void spawnParticlesGore(SPEnumParticle particleType, int r, int g, int b, double xF, double yF) {
        double d0 = (float)this.field_70165_t + this.field_70146_Z.nextFloat();
        double d1 = (float)this.field_70163_u + this.field_70146_Z.nextFloat();
        double d2 = (float)this.field_70161_v + this.field_70146_Z.nextFloat();
        double d3 = d0 - this.field_70165_t;
        double d4 = d1 - this.field_70163_u;
        double d5 = d2 - this.field_70161_v;
        double d6 = MathHelper.func_76133_a((double)(d3 * d3 + d4 * d4 + d5 * d5));
        d3 /= d6;
        d4 /= d6;
        d5 /= d6;
        double d7 = 0.5 / (d6 / 4.0 + 0.1);
        d3 = d3 * (d7 *= (double)(this.field_70146_Z.nextFloat() * this.field_70146_Z.nextFloat() + 0.3f)) * xF;
        d4 = d4 * d7 * yF;
        d5 = d5 * d7 * xF;
        d3 = Math.min(d3, 0.2) * (Math.random() * 2.0 - 1.0);
        d4 = Math.min(d4, 0.6) * (Math.random() * 2.0 - 1.0);
        d5 = Math.min(d5, 0.2) * (Math.random() * 2.0 - 1.0);
        ParticleSpawner.spawnParticle(particleType, this.field_70165_t, this.field_70163_u + 0.2 + (double)this.field_70131_O, this.field_70161_v, d3, d4, d5, r, g, b);
    }

    @SideOnly(value=Side.CLIENT)
    public void spawnParticlesGoreMouth(SPEnumParticle particleType, int r, int g, int b, double xF, double yF) {
        double d0 = (float)this.field_70165_t + this.field_70146_Z.nextFloat();
        double d1 = (float)this.field_70163_u + this.field_70146_Z.nextFloat();
        double d2 = (float)this.field_70161_v + this.field_70146_Z.nextFloat();
        double d3 = d0 - this.field_70165_t;
        double d4 = d1 - this.field_70163_u;
        double d5 = d2 - this.field_70161_v;
        double d6 = MathHelper.func_76133_a((double)(d3 * d3 + d4 * d4 + d5 * d5));
        d3 /= d6;
        d4 /= d6;
        d5 /= d6;
        double d7 = 0.5 / (d6 / 4.0 + 0.1);
        d3 = d3 * (d7 *= (double)(this.field_70146_Z.nextFloat() * this.field_70146_Z.nextFloat() + 0.3f)) * xF;
        d4 = d4 * d7 * yF;
        d5 = d5 * d7 * xF;
        d3 = Math.min(d3, 0.2) * (Math.random() * 2.0 - 1.0);
        d4 = Math.min(d4, 0.6) * (Math.random() * 2.0 - 1.0);
        d5 = Math.min(d5, 0.2) * (Math.random() * 2.0 - 1.0);
        ParticleSpawner.spawnParticle(particleType, this.field_70165_t, this.field_70163_u, this.field_70161_v, d3, d4, d5, r, g, b);
    }

    @SideOnly(value=Side.CLIENT)
    public void spawnParticlesGoreBox(SPEnumParticle particleType, int r, int g, int b, double xF, double yF) {
        double d0 = (float)this.field_70165_t + this.field_70146_Z.nextFloat();
        double d1 = (float)this.field_70163_u + this.field_70146_Z.nextFloat();
        double d2 = (float)this.field_70161_v + this.field_70146_Z.nextFloat();
        double d3 = d0 - this.field_70165_t;
        double d4 = d1 - this.field_70163_u;
        double d5 = d2 - this.field_70161_v;
        double d6 = MathHelper.func_76133_a((double)(d3 * d3 + d4 * d4 + d5 * d5));
        d3 /= d6;
        d4 /= d6;
        d5 /= d6;
        double d7 = 0.5 / (d6 / 4.0 + 0.1);
        d3 = d3 * (d7 *= (double)(this.field_70146_Z.nextFloat() * this.field_70146_Z.nextFloat() + 0.3f)) * xF;
        d4 = d4 * d7 * yF;
        d5 = d5 * d7 * xF;
        d3 = Math.min(d3, 0.2) * (Math.random() * 2.0 - 1.0);
        d4 = Math.min(d4, 0.6) * (Math.random() * 2.0 - 1.0);
        d5 = Math.min(d5, 0.2) * (Math.random() * 2.0 - 1.0);
        ParticleSpawner.spawnParticle(particleType, this.field_70165_t + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, this.field_70163_u + 0.5 + (double)(this.field_70146_Z.nextFloat() * this.field_70131_O), this.field_70161_v + (double)(this.field_70146_Z.nextFloat() * this.field_70130_N * 2.0f) - (double)this.field_70130_N, d3, d4, d5, r, g, b);
    }

    @SideOnly(value=Side.CLIENT)
    public void spawnParticles(EnumParticleTypes particleType, double d0, double d1, double d2, double d3, double d4, double d5) {
        this.field_70170_p.func_175688_a(particleType, d0, d1, d2, d3, d4, d5, new int[0]);
    }

    @SideOnly(value=Side.CLIENT)
    public void spawnParticles(SPEnumParticle particleType, int r, int g, int b, double d0, double d1, double d2, double d3, double d4, double d5) {
        ParticleSpawner.spawnParticle(particleType, d0, d1, d2, d3, d4, d5, r, g, b);
    }

    @SideOnly(value=Side.CLIENT)
    public float getSelfeFlashIntensity(float p_70831_1_) {
        return ((float)this.lastActiveTime + (float)(this.timeSinceIgnited - this.lastActiveTime) * p_70831_1_) / (float)(this.fuseTime - 2);
    }

    @SideOnly(value=Side.CLIENT)
    public boolean isSwingingArms() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public float getscale(float p_70831_1_) {
        return 0.0f;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean getStillAni() {
        return this.still;
    }

    @SideOnly(value=Side.CLIENT)
    public float getAniTick() {
        return this.aniticks;
    }

    public void doSpecialSkill(byte id) {
        switch (id) {
            case 13: {
                this.skillBreakBlocks();
                return;
            }
            case 14: {
                this.skillLeap();
                return;
            }
        }
    }

    public boolean getFinished(byte attID) {
        switch (attID) {
            case 13: {
                return this.SkillBGflag;
            }
            case 14: {
                return this.SkillLeapFlag;
            }
        }
        return false;
    }

    public void setFinished(byte attID, boolean in) {
        switch (attID) {
            case 13: {
                this.SkillBGflag = in;
            }
            case 14: {
                this.SkillLeapFlag = in;
            }
        }
    }

    public void setSkillBreakBlocksValues(float hardness, int heightIn, int rangeIn) {
        this.blockH = hardness;
        this.BGheight = heightIn;
        this.BGrange = rangeIn;
    }

    public float getBlockH() {
        if (this.getSkin() == 120) {
            return this.blockH * 1.5f;
        }
        if (this.getSkin() == 7) {
            return this.blockH * 2.0f;
        }
        return this.blockH;
    }

    public void skillBreakBlocks() {
        EntityLivingBase target;
        if (this.field_70128_L) {
            return;
        }
        if (this.getBlockH() == 0.0f) {
            return;
        }
        int blocksbroke = 0;
        if (!ForgeEventFactory.getMobGriefingEvent((World)this.field_70170_p, (Entity)this)) {
            return;
        }
        int i1 = MathHelper.func_76128_c((double)(this.field_70163_u + 0.1));
        double l1 = this.field_70165_t;
        double i2 = this.field_70161_v;
        boolean flag = false;
        int Brangeatm = this.BGrange;
        int offsetT = 0;
        if (this.func_70638_az() != null && (target = this.func_70638_az()).func_70092_e(this.field_70165_t, target.field_70163_u, this.field_70161_v) < 9.0) {
            if (target.field_70163_u - this.field_70163_u < -1.0) {
                offsetT -= 2;
                if (!this.field_70122_E) {
                    --offsetT;
                }
            } else if (target.field_70163_u - this.field_70163_u > 2.0) {
                ++offsetT;
                this.BGrange = 0;
            }
        }
        for (int k2 = -1 * this.BGrange; k2 <= this.BGrange; ++k2) {
            for (int l2 = -1 * this.BGrange; l2 <= this.BGrange; ++l2) {
                for (int j = 1 + offsetT; j <= this.BGheight + offsetT; ++j) {
                    String name;
                    double i3 = l1 + (double)k2;
                    double k = i1 + j;
                    double l = i2 + (double)l2;
                    BlockPos blockpos = new BlockPos(i3, k, l);
                    IBlockState iblockstate = this.field_70170_p.func_180495_p(blockpos);
                    Block block = iblockstate.func_177230_c();
                    float bHard = iblockstate.func_185887_b(this.field_70170_p, blockpos);
                    if (!(bHard <= this.getBlockH()) || !(bHard >= 0.0f) || block instanceof IMetaName && block != SPBlocks.ParasiteCanister || block == SPBlocks.BiomeHeart || block == SPBlocks.ColonyHeart || block == SPBlocks.ParasiteRubbleDense || block == SPBlocks.ParasiteCanisterActive || block == SPBlocks.dodN || block instanceof BlockLiquid || block instanceof BlockPortal || block instanceof BlockEndGateway || block instanceof BlockEndPortalFrame || iblockstate.func_185904_a() == Material.field_151567_E || this.blockException(name = block.getRegistryName().toString()) || block == Blocks.field_150350_a || !block.canEntityDestroy(iblockstate, (IBlockAccess)this.field_70170_p, blockpos, (Entity)this) || !ForgeEventFactory.onEntityDestroyBlock((EntityLivingBase)this, (BlockPos)blockpos, (IBlockState)iblockstate)) continue;
                    if (SPConfig.cystActive) {
                        int meta = block.func_176201_c(iblockstate);
                        boolean bl = flag = this.destroyBlockPos(blockpos, false) || flag;
                        if (SPConfig.doTileDrops) {
                            this.addToBlockInv(name + ";" + meta);
                        }
                    } else {
                        this.destroyBlockPos(blockpos, SPConfig.doTileDrops);
                    }
                    ++blocksbroke;
                }
            }
        }
        this.BGrange = Brangeatm;
        this.SkillBGflag = true;
    }

    protected boolean destroyBlockPos(BlockPos pos, boolean dropBlock) {
        IBlockState iblockstate = this.field_70170_p.func_180495_p(pos);
        Block block = iblockstate.func_177230_c();
        if (block.isAir(iblockstate, (IBlockAccess)this.field_70170_p, pos)) {
            return false;
        }
        if (this.field_70146_Z.nextDouble() < SPConfig.blockParticleChance) {
            this.field_70170_p.func_175718_b(2001, pos, Block.func_176210_f((IBlockState)iblockstate));
        }
        if (this.field_70146_Z.nextDouble() < SPConfig.blockSoundChance) {
            SoundType soundtype = block.getSoundType(Block.func_176220_d((int)Block.func_176210_f((IBlockState)iblockstate)), this.field_70170_p, pos, null);
            this.field_70170_p.func_184133_a((EntityPlayer)null, pos, soundtype.func_185845_c(), SoundCategory.BLOCKS, (soundtype.func_185843_a() + 1.0f) / 2.0f, soundtype.func_185847_b() * 0.8f);
        }
        if (dropBlock && this.field_70146_Z.nextDouble() < SPConfig.blockDropChance) {
            block.func_176226_b(this.field_70170_p, pos, iblockstate, 0);
        }
        return this.field_70170_p.func_180501_a(pos, Blocks.field_150350_a.func_176223_P(), 3);
    }

    protected boolean blockException(String name) {
        return ParasiteEventEntity.checkName(name, SPConfig.parasiteGriefingBlackList, SPConfig.parasiteGriefingWhite);
    }

    public void addToBlockInv(String name) {
        boolean flag = true;
        for (int i = 0; i < this.inbBlockName.size(); ++i) {
            if (!this.inbBlockName.get(i).equals(name) || this.inbBlockNumber.get(i) > 64) continue;
            int iiii = this.inbBlockNumber.get(i) + 1;
            this.inbBlockNumber.set(i, iiii);
            flag = false;
            break;
        }
        if (flag) {
            if (this.inbBlockName.size() >= 22) {
                this.spawnCyst();
            }
            this.inbBlockName.add(name);
            this.inbBlockNumber.add(1);
        }
    }

    protected void spawnCyst() {
        if (this.inbBlockName.isEmpty()) {
            return;
        }
        double d0 = (float)this.field_70165_t + this.field_70170_p.field_73012_v.nextFloat();
        double d1 = (float)this.field_70163_u + this.field_70170_p.field_73012_v.nextFloat();
        double d2 = (float)this.field_70161_v + this.field_70170_p.field_73012_v.nextFloat();
        double d3 = d0 - this.field_70165_t;
        double d4 = d1 - this.field_70163_u;
        double d5 = d2 - this.field_70161_v;
        double d6 = MathHelper.func_76133_a((double)(d3 * d3 + d4 * d4 + d5 * d5));
        d3 /= d6;
        d4 /= d6;
        d5 /= d6;
        double d7 = 0.5 / (d6 / 4.0 + 0.1);
        d4 = d4 * d7 * 2.0;
        EntityGore bomb = new EntityGore(this.field_70170_p);
        bomb.setType((byte)10);
        bomb.func_82149_j((Entity)this);
        bomb.setMotion(d3 *= (d7 *= (double)(this.field_70170_p.field_73012_v.nextFloat() * this.field_70170_p.field_73012_v.nextFloat() + 0.3f)), d4, d5 *= d7, 0.25, 0.75);
        this.field_70170_p.func_72838_d((Entity)bomb);
        bomb.inbBlockName = new ArrayList<String>(this.inbBlockName);
        bomb.inbBlockNumber = new ArrayList<Integer>(this.inbBlockNumber);
        this.inbBlockName = new ArrayList();
        this.inbBlockNumber = new ArrayList();
    }

    public void setskillLeapValues(float leapY, double leapSpeed, int jumpRad) {
        this.leapMotionY = leapY;
        this.jumpSpeed = leapSpeed;
        this.jumpR = jumpRad;
    }

    protected void skillLeap() {
        if (this.leapMotionY == 0.0f) {
            return;
        }
        if (this.func_70638_az() != null && this.shouldWorkTask() && !this.func_70644_a(MobEffects.field_76421_d) && this.getParasiteStatus() <= 2) {
            EntityLivingBase entitylivingbase = this.func_70638_az();
            if (this.attacking == 0) {
                ++this.attacking;
                this.targetX = entitylivingbase.field_70165_t;
                this.targetZ = entitylivingbase.field_70161_v;
            }
        }
        if (this.attacking >= 1) {
            ++this.attacking;
            this.skillBreakBlocks();
            if (this.attacking == 2 && this.field_70122_E) {
                this.setParasiteStatus(10);
                this.func_70661_as().func_75499_g();
                double d0 = this.targetX - this.field_70165_t;
                double d1 = this.targetZ - this.field_70161_v;
                double f = MathHelper.func_76133_a((double)(d0 * d0 + d1 * d1));
                this.field_70181_x = this.leapMotionY;
                this.field_70159_w += d0 / f * this.jumpSpeed * 0.9 + this.field_70159_w * 0.3;
                this.field_70179_y += d1 / f * this.jumpSpeed * 0.9 + this.field_70179_y * 0.3;
            }
            if (this.attacking > 2 && this.field_70122_E) {
                if (this.jumpR != 0) {
                    float damage = (float)this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111125_b();
                    AxisAlignedBB axisalignedbb = new AxisAlignedBB(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70165_t + 1.0, this.field_70163_u + 1.0, this.field_70161_v + 1.0).func_72314_b((double)this.jumpR, 2.0, (double)this.jumpR);
                    List moblist = this.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb);
                    for (EntityLivingBase mob : moblist) {
                        if (mob == this || mob instanceof EntityParasiteBase) continue;
                        mob.func_70653_a((Entity)mob, 2.5f, this.field_70165_t - mob.field_70165_t, this.field_70161_v - mob.field_70161_v);
                        this.func_70652_k((Entity)mob);
                    }
                }
                this.setParasiteStatus(0);
                this.attacking = 0;
                if (this.type >= 31 && this.field_70131_O > 2.0f) {
                    this.func_184185_a(SPSounds.HITGROUND, 15.0f, 1.0f);
                }
                this.SkillLeapFlag = true;
            }
        }
    }

    public boolean func_70965_a(MultiPartEntityPart dragonPart, DamageSource source, float damage) {
        return damage > 0.0f;
    }

    @Override
    public EntityParasiteBase getParent() {
        return this;
    }

    public void updateHitboxes() {
        for (EntityHitbox hb : this.hitboxes) {
            if (hb == null) continue;
            hb.func_70071_h_();
        }
    }

    public void resetHitboxes(float scale) {
        if (scale > this.prevHitboxScale) {
            this.prevHitboxScale = scale;
            for (EntityHitbox hb : this.hitboxes) {
                if (hb == null) continue;
                hb.resize(scale);
            }
        }
    }

    private void clearHitboxes() {
        for (EntityHitbox hb : this.hitboxes) {
            if (hb == null) continue;
            hb.func_70106_y();
            this.field_70170_p.func_72973_f((Entity)hb);
        }
    }

    public void func_70106_y() {
        if (this.hitboxes != null) {
            this.clearHitboxes();
        }
        super.func_70106_y();
    }

    @Nonnull
    public World func_82194_d() {
        return this.field_70170_p;
    }

    public static class EntityAIJumping
    extends EntityAIBase {
        private final EntityLiving parent;
        private int secs;

        public EntityAIJumping(EntityLiving parentIn) {
            this.parent = parentIn;
            this.func_75248_a(4);
            if (parentIn.func_70661_as() instanceof PathNavigateGround) {
                ((PathNavigateGround)parentIn.func_70661_as()).func_179693_d(true);
            } else if (parentIn.func_70661_as() instanceof PathNavigateFlying) {
                ((PathNavigateFlying)parentIn.func_70661_as()).func_192877_c(true);
            }
        }

        public boolean func_75250_a() {
            ++this.secs;
            if (this.secs < 10) {
                return false;
            }
            this.secs = 0;
            EntityLivingBase target = this.parent.func_70638_az();
            if (target == null) {
                return false;
            }
            if (target.func_70092_e(this.parent.field_70165_t, target.field_70163_u, this.parent.field_70161_v) < 4.0 && target.field_70163_u - (this.parent.field_70163_u + (double)this.parent.func_70047_e()) > 1.0 && this.parent.field_70122_E) {
                this.parent.func_70661_as().func_75499_g();
                double dd0 = target.field_70165_t - this.parent.field_70165_t;
                double dd1 = target.field_70161_v - this.parent.field_70161_v;
                float f = MathHelper.func_76133_a((double)(dd0 * dd0 + dd1 * dd1));
                this.parent.field_70159_w += dd0 / (double)f * 0.5 * (double)0.8f + this.parent.field_70159_w * (double)0.2f;
                this.parent.field_70179_y += dd1 / (double)f * 0.5 * (double)0.8f + this.parent.field_70179_y * (double)0.2f;
                this.parent.field_70181_x = 0.2 + (double)this.parent.field_70131_O * 0.15;
            }
            return false;
        }

        public void func_75246_d() {
            if (this.parent.func_70681_au().nextFloat() < 0.8f) {
                this.parent.func_70683_ar().func_75660_a();
            }
        }
    }

    class EntityAIWait
    extends EntityAIBase {
        public EntityAIWait() {
            this.func_75248_a(7);
        }

        public boolean func_75250_a() {
            return EntityParasiteBase.this.getWait() > 0;
        }

        public void func_75246_d() {
        }
    }
}

