package com.srp.draconite.entity;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import com.srp.draconite.config.DraconiteDifficulty;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.player.Player;
import com.srp.draconite.init.ModEntityTypes;
import com.srp.draconite.init.ModEffects;
import com.srp.draconite.init.ModSounds;

public class EntityDraconite
extends PathfinderMob
implements GeoEntity,
RangedAttackMob {
    private static final EntityDataAccessor<Boolean> DATA_FLYING = SynchedEntityData.defineId(EntityDraconite.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_ATTACKING = SynchedEntityData.defineId(EntityDraconite.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> DATA_ATTACK_STATE = SynchedEntityData.defineId(EntityDraconite.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> DATA_SHADOW = SynchedEntityData.defineId(EntityDraconite.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DATA_CLONE = SynchedEntityData.defineId(EntityDraconite.class, EntityDataSerializers.BOOLEAN);
    // Base (easy) attribute values
    private static final double BASE_MAX_HEALTH = 525.0;
    private static final double BASE_ARMOR = 30.0;
    private static final double BASE_ATTACK_DAMAGE = 210.0;
    private static final double BASE_KNOCKBACK_RESISTANCE = 1.0;
    private static final double MOVEMENT_SPEED = 0.27;
    private static final double FOLLOW_RANGE = 80.0;

    // Current difficulty - default NORMAL per SRP standard
    private DraconiteDifficulty difficulty = DraconiteDifficulty.NORMAL;

    // Difficulty-scaled attributes (computed from difficulty)
    private double MAX_HEALTH;
    private double ARMOR;
    private double ATTACK_DAMAGE;
    private double KNOCKBACK_RESISTANCE;
    private float MINIMUM_DAMAGE;
    private float DAMAGE_CAP;
    private static final double TENDRIL_HEALTH_FRACTION = 0.2;
    private static final double HEAD_HEALTH_FRACTION = 0.15;
    private static final int RAINING_FIREBALL_COUNT = 19;
    private static final double RAINING_FIREBALL_RADIUS = 10.0;
    private static final double RAINING_FIREBALL_HEIGHT = 20.0;
    public static final int ATTACK_NONE = 0;
    public static final int ATTACK_MELEE = 1;
    public static final int ATTACK_FIREBALL = 2;
    public static final int ATTACK_FLAME_SKILL = 3;
    public static final int ATTACK_TOXIC = 4;
    public static final int ATTACK_ORB = 5;
    private byte hitStatus = 0;
    private static final float BASE_MINIMUM_DAMAGE = 14.0f;
    private static final float BASE_DAMAGE_CAP = 21.0f;
    private static final float FIRE_DAMAGE_MULTIPLIER = 4.0f;
    private static final double ADAPTATION_POINT_REDUCTION = 0.1;
    private static final double ADAPTATION_CAP = 1.0;
    private static final int ADAPTATION_NEW_DAMAGE_COOLDOWN = 40;
    private static final int ON_FIRE_ADAPT_DISABLE_DURATION = 10;
    private static final double ON_FIRE_ADAPT_DISABLE_CHANCE = 0.1;
    private double REGEN_FRACTION;
    private static final int REGEN_EFF = 10;
    private static final int REGEN_IDLE_DELAY = 80;
    private static final int KILLCOUNT_START = -10;
    private static final int KILLCOUNT_MAX = 25;
    private static final double SHADOW_BREAK_FRACTION = 0.1;
    private static final int SHADOW_DAMAGE_RESET_TICKS = 100;
    private static final int SHADOW_BREAK_RESET_TICKS = 200;
    private static final int CLONE_LIFESPAN = 440;
    private static final int SHADOW_LIGHT_THRESHOLD = 7;
    private static final float SHADOW_SKY_LIGHT_THRESHOLD = 0.467f;
    private static final int CLONE_SPAWN_COOLDOWN = 10;
    private static final int HACK_INTERVAL = 20;
    private static final int HACK_MAX_TARGETS = 5;
    private static final double HACK_RADIUS = 24.0;
    private static final int HACK_MAX_CYCLES = 7;
    private static final int HACK_COOLDOWN_TICKS = 300;
    private static final int CUT_RESIST_STUN_DURATION = 40;
    private static final int CUT_RESIST_SLOWNESS_AMPLIFIER = 100;
    private static final int CUT_RESIST_SLOWNESS_DURATION = 40;
    private static final int CUT_RESIST_RAGE_DURATION = 200;
    private static final int CUT_RESIST_RAGE_AMPLIFIER = 1;
    private static final int CUT_RESIST_BLEED_DURATION = 100;
    private static final int CUT_RESIST_BLEED_AMPLIFIER = 0;
    private static final int BLOCK_DESTROY_COOLDOWN = 40;
    private static final float BLOCK_DESTROY_HARDNESS = 27.0f;
    private static final int LIGHT_DESTROY_RADIUS = 6;
    private static final int LIGHT_DESTROY_INTERVAL = 160;
    private static final int ANIM_DURATION_TENDRIL = 20;
    private static final int ANIM_DURATION_SPIT_FIRE = 92;
    private static final int ANIM_DURATION_SPIT_BALL = 24;
    private static final int ANIM_DURATION_LANDING = 97;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private static final RawAnimation ANIM_FLY_QUICK = RawAnimation.begin().then("fly_quick", Animation.LoopType.LOOP);
    private static final RawAnimation ANIM_FLY_SLOW = RawAnimation.begin().then("fly_slow", Animation.LoopType.LOOP);
    private static final RawAnimation ANIM_FLY_REST = RawAnimation.begin().then("fly_rest", Animation.LoopType.LOOP);
    private static final RawAnimation ANIM_BALL_DOWN = RawAnimation.begin().then("ball_down", Animation.LoopType.PLAY_ONCE);
    private static final RawAnimation ANIM_REST1 = RawAnimation.begin().then("rest1", Animation.LoopType.LOOP);
    private static final RawAnimation ANIM_REST2 = RawAnimation.begin().then("rest2", Animation.LoopType.LOOP);
    private static final RawAnimation ANIM_TENDRIL1 = RawAnimation.begin().then("animation.\u884d\u751f.\u89e6\u624b\u653b\u51fb1", Animation.LoopType.PLAY_ONCE);
    private static final RawAnimation ANIM_TENDRIL2 = RawAnimation.begin().then("animation.\u884d\u751f.\u89e6\u624b\u653b\u51fb2", Animation.LoopType.PLAY_ONCE);
    private static final RawAnimation ANIM_SPIT_FIRE = RawAnimation.begin().then("spit_fire", Animation.LoopType.PLAY_ONCE);
    private static final RawAnimation ANIM_SPIT_BALL = RawAnimation.begin().then("spit_ball", Animation.LoopType.PLAY_ONCE);
    private int idleHoverTimer = 0;
    private int idleHoverVariant = 0;
    private static final int IDLE_HOVER_CYCLE_TICKS = 120;
    private int adaptationVisual = 0;
    private int adaptationVisualCooldown = 0;
    private EntityDraconiteBodyPart leftTendril;
    private EntityDraconiteBodyPart rightTendril;
    private EntityDraconiteBodyPart head;
    private float leftTendrilHealth;
    private float rightTendrilHealth;
    private float headHealth;
    private float wingFlapSoundCounter = 0.0f;
    private int randomFlightCooldown;
    private boolean isRainingFireballs;
    private int rainingFireballTicks;
    private boolean isRainingOrbs;
    private int rainingOrbTicks;
    private int hurtSoundCooldown;
    private int ambientSoundCooldown;
    private int stepSoundCooldown;
    private int shootSoundCooldown;
    public int attackAnimationTimer;
    public int hurtAnimationTimer;
    private int landingTimer;
    private boolean wasFlying;
    private PathNavigation groundNavigation;
    private PathNavigation flyingNavigation;
    private final LinkedHashMap<String, Integer> adaptationMap = new LinkedHashMap<>();
    private int newDamageCooldown = 0;
    private int onFireAdaptDisableTimer = 0;
    private float damageAmountReduced = 0.0f;
    private int adaptationDamageTypeCap = 5;
    private float accumulatedShadowDamage;
    private int shadowDamageTick;
    private int cloneEntityId = 0;
    private int originalEntityId = 0;
    private int cloneLifeTimer = 0;
    private int cloneSpawnCooldown = 0;
    public float shadowDamageR = 0.0f;
    public int shadowDamageRCooldown = 0;
    public int shakeTimer = 0;
    public int showC = 0;
    @Deprecated
    public int shakeee = 0;
    private int hackTimer = 0;
    private int hackCooldown = 0;
    private int hackCyclesCompleted = 0;
    private int stunTimer = 0;
    private int parasiteStatus = 1;
    private int killcount = -10;
    private int regenUse = 10;
    private int idleTime = 0;
    private int blockDestroyCooldown = 0;
    private int lightDestroyTimer = 0;
    private int flightSoundTimer = 0;
    private int flyingTickCount = 0;
    private int vomitTimer = 0;

    public EntityDraconite(EntityType<? extends PathfinderMob> entityType, Level level) {
        super(entityType, level);
        this.applyDifficultyScaling(DraconiteDifficulty.NORMAL);
        this.xpReward = 80;
        this.setCanPickUpLoot(false);
        this.moveControl = new DraconiteMoveControl(this);
        this.randomFlightCooldown = 20;
        this.hurtSoundCooldown = 0;
        this.ambientSoundCooldown = 0;
        this.stepSoundCooldown = 0;
        this.shootSoundCooldown = 0;
        this.wasFlying = false;
        this.landingTimer = 0;
        this.groundNavigation = new GroundPathNavigation(this, level);
        this.flyingNavigation = new FlyingPathNavigation(this, level);
        this.flyingNavigation.setCanFloat(true);
        this.accumulatedShadowDamage = 0.0f;
        this.shadowDamageTick = 0;
        this.navigation = this.flyingNavigation;
        this.setNoGravity(true);
        try {
            java.lang.reflect.Field eyeHeightField = Entity.class.getDeclaredField("eyeHeight");
            eyeHeightField.setAccessible(true);
            eyeHeightField.setFloat(this, 1.75f);
        } catch (Exception exception) {
            // eyeHeight is already set by default
        }
    }

    /**
     * Apply difficulty scaling to compute all derived attribute values.
     */
    public void applyDifficultyScaling(DraconiteDifficulty diff) {
        this.difficulty = diff;
        this.MAX_HEALTH = BASE_MAX_HEALTH * diff.getHealthMultiplier();
        this.ARMOR = BASE_ARMOR * diff.getArmorMultiplier();
        this.ATTACK_DAMAGE = BASE_ATTACK_DAMAGE * diff.getDamageMultiplier();
        this.KNOCKBACK_RESISTANCE = Math.min(1.0, BASE_KNOCKBACK_RESISTANCE * diff.getKnockbackMultiplier());
        this.MINIMUM_DAMAGE = BASE_MINIMUM_DAMAGE * diff.getMinDamageMultiplier();
        this.DAMAGE_CAP = BASE_DAMAGE_CAP * diff.getDamageCapMultiplier();
        this.REGEN_FRACTION = 0.0025 * diff.getRegenMultiplier();
        this.adaptationDamageTypeCap = diff.getAdaptationDamageTypeCap();
    }

    public DraconiteDifficulty getDifficulty() {
        return this.difficulty;
    }

    public static AttributeSupplier setAttributes() {
        // Use NORMAL difficulty values as default for entity registration
        DraconiteDifficulty diff = DraconiteDifficulty.NORMAL;
        return PathfinderMob.createMobAttributes()
            .add(Attributes.MAX_HEALTH, BASE_MAX_HEALTH * diff.getHealthMultiplier())
            .add(Attributes.ARMOR, BASE_ARMOR * diff.getArmorMultiplier())
            .add(Attributes.ATTACK_DAMAGE, BASE_ATTACK_DAMAGE * diff.getDamageMultiplier())
            .add(Attributes.KNOCKBACK_RESISTANCE, Math.min(1.0, BASE_KNOCKBACK_RESISTANCE * diff.getKnockbackMultiplier()))
            .add(Attributes.MOVEMENT_SPEED, 0.27)
            .add(Attributes.FOLLOW_RANGE, 80.0)
            .add(Attributes.FLYING_SPEED, 0.6)
            .build();
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_FLYING, true);
        this.entityData.define(DATA_ATTACKING, false);
        this.entityData.define(DATA_ATTACK_STATE, 0);
        this.entityData.define(DATA_SHADOW, true);
        this.entityData.define(DATA_CLONE, false);
    }

    public boolean isFlying() {
        return (Boolean)this.entityData.get(DATA_FLYING);
    }

    public void setFlying(boolean flying) {
        boolean wasFlyingBefore = this.isFlying();
        this.entityData.set(DATA_FLYING, flying);
        if (flying) {
            this.navigation = this.flyingNavigation;
            this.setNoGravity(true);
            if (!wasFlyingBefore && this.areTendrilsAlive()) {
                this.parasiteStatus = 3;
                this.setDeltaMovement(this.getDeltaMovement().add(0.0, 0.5, 0.0));
                this.wingFlapSoundCounter = 19.85f;
            }
        }
    }

    public boolean isAttacking() {
        return (Boolean)this.entityData.get(DATA_ATTACKING);
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(DATA_ATTACKING, attacking);
    }

    public int getAttackState() {
        return this.entityData.get(DATA_ATTACK_STATE);
    }

    public void setAttackState(int state) {
        this.entityData.set(DATA_ATTACK_STATE, state);
    }

    public boolean isShadow() {
        return (Boolean)this.entityData.get(DATA_SHADOW);
    }

    public void setShadow(boolean shadow) {
        this.entityData.set(DATA_SHADOW, shadow);
    }

    public boolean isClone() {
        return (Boolean)this.entityData.get(DATA_CLONE);
    }

    public void setClone(boolean clone) {
        this.entityData.set(DATA_CLONE, clone);
    }

    public byte getHitStatus() {
        return this.hitStatus;
    }

    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation nav = new FlyingPathNavigation(this, level);
        nav.setCanFloat(true);
        nav.setCanOpenDoors(false);
        nav.setCanWalkOverFences(false);
        return nav;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new DraconiteDiveGoal(this));
        this.goalSelector.addGoal(1, new DraconiteFlameSkillGoal(this, 160, 100));
        this.goalSelector.addGoal(2, new DraconiteMeleeAOEGoal(this, 1.3, 8.0f, 9.0f));
        this.goalSelector.addGoal(3, new DraconiteFlightAttackGoal(this));
        this.goalSelector.addGoal(4, new DraconiteFireballAttackGoal(this));
        this.goalSelector.addGoal(5, new DraconiteRangedAttackGoal(this, 1.3, 100, 40.0f));
        this.goalSelector.addGoal(5, new DraconiteOrbAttackGoal(this));
        this.goalSelector.addGoal(6, new DraconiteFlightMoveGoal(this));
        this.goalSelector.addGoal(6, new DraconiteMeleeRangeSwitchGoal(this, 12.0));
        this.goalSelector.addGoal(7, new DraconiteBlockLightGoal(this));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 24.0f, 0.6f));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this, new Class[0]));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, Villager.class, true));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, IronGolem.class, true));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal(this, LivingEntity.class, 10, true, false, entity -> entity != this && !(entity instanceof EntityDraconite) && entity.isAlive()));
    }

    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController(this, "bodyController", 10, this::bodyAnimPredicate);
        controllers.add(new AnimationController(this, "attackController", 5, this::attackAnimPredicate);
    }

    private PlayState bodyAnimPredicate(AnimationState<EntityDraconite> event) {
        int attackState = this.getAttackState();
        if (attackState != 0 && this.attackAnimationTimer > 0) {
            return PlayState.CONTINUE;
        }
        if (this.stunTimer > 0) {
            event.getController().setAnimation(ANIM_FLY_REST);
            return PlayState.CONTINUE;
        }
        double horizontalSpeed = this.getDeltaMovement().horizontalDistance();
        LivingEntity target = this.getTarget();
        if (target != null || this.isAttacking()) {
            this.idleHoverTimer = 0;
            event.getController().setAnimation(ANIM_FLY_QUICK);
        } else if (horizontalSpeed > 0.1) {
            this.idleHoverTimer = 0;
            event.getController().setAnimation(ANIM_FLY_SLOW);
        } else {
            ++this.idleHoverTimer;
            if (this.idleHoverTimer >= 120) {
                this.idleHoverTimer = 0;
                this.idleHoverVariant = (this.idleHoverVariant + 1) % 3;
            }
            switch (this.idleHoverVariant) {
                case 1: {
                    event.getController().setAnimation(ANIM_REST1);
                    break;
                }
                case 2: {
                    event.getController().setAnimation(ANIM_REST2);
                    break;
                }
                default: {
                    event.getController().setAnimation(ANIM_FLY_REST);
                }
            }
        }
        return PlayState.CONTINUE;
    }

    private PlayState attackAnimPredicate(AnimationState<EntityDraconite> event) {
        int attackState = this.getAttackState();
        if (attackState == 0 || this.attackAnimationTimer <= 0) {
            return PlayState.STOP;
        }
        switch (attackState) {
            case 1: {
                if (this.random.nextBoolean()) {
                    event.getController().setAnimation(ANIM_TENDRIL1);
                    break;
                }
                event.getController().setAnimation(ANIM_TENDRIL2);
                break;
            }
            case 2: {
                event.getController().setAnimation(ANIM_SPIT_FIRE);
                break;
            }
            case 3: {
                event.getController().setAnimation(ANIM_BALL_DOWN);
                break;
            }
            case 4: 
            case 5: {
                event.getController().setAnimation(ANIM_SPIT_BALL);
                break;
            }
            default: {
                event.getController().setAnimation(ANIM_TENDRIL2);
            }
        }
        return PlayState.CONTINUE;
    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        if (this.parasiteStatus != 0) {
            return ModSounds.MOB_SILENCE.get();
        }
        return ModSounds.HEBLU_GROWL.get();
    }

    public int getAmbientSoundInterval() {
        return this.random.nextInt(120) + 120;
    }

    @Nullable
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.HEBLU_HURT.get();
    }

    protected float getSoundVolume() {
        return 5.0f;
    }

    protected void playStepSound(BlockPos pos, BlockState state) {
        if (!this.isFlying()) {
            this.playSound(ModSounds.HEBLU_STEP.get(), 0.15f, 1.0f);
        }
    }

    public void playHurtSound(DamageSource source) {
        if (this.hurtSoundCooldown <= 0) {
            float pitch = (this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1.0f;
            this.playSound(ModSounds.HEBLU_HURT.get(), this.getSoundVolume(), pitch);
            this.hurtSoundCooldown = 20;
        }
    }

    public void playShootSound() {
        if (this.shootSoundCooldown <= 0) {
            float pitch = (this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1.0f;
            this.playSound(ModSounds.HEBLU_SHOOT.get(), 10.0f, pitch);
            this.shootSoundCooldown = 10;
        }
    }

    public void playRoarSound() {
        this.playSound(ModSounds.HEBLU_GROWL.get(), this.getSoundVolume(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1.0f);
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return ModSounds.HEBLU_DEATH.get();
    }

    public boolean causeFallDamage(float distance, float multiplier, DamageSource source) {
        return false;
    }

    public int getExperienceReward() {
        return Integer.MAX_VALUE;
    }

    public void initBodyParts() {
        if (this.level().isClientSide) {
            return;
        }
        this.removeBodyParts();
        float maxHealth = this.getMaxHealth();
        this.leftTendril = new EntityDraconiteBodyPart(ModEntityTypes.DRACONITE_BODY_PART.get(), this.level());
        this.leftTendrilHealth = (float)((double)maxHealth * 0.2);
        this.leftTendril.setHealth(this.leftTendrilHealth);
        this.leftTendril.setOwner(this);
        this.leftTendril.setPartName("leftTendril");
        this.level().addFreshEntity(this.leftTendril);
        this.rightTendril = new EntityDraconiteBodyPart(ModEntityTypes.DRACONITE_BODY_PART.get(), this.level());
        this.rightTendrilHealth = (float)((double)maxHealth * 0.2);
        this.rightTendril.setHealth(this.rightTendrilHealth);
        this.rightTendril.setOwner(this);
        this.rightTendril.setPartName("rightTendril");
        this.level().addFreshEntity(this.rightTendril);
        this.head = new EntityDraconiteBodyPart(ModEntityTypes.DRACONITE_BODY_PART.get(), this.level());
        this.headHealth = (float)((double)maxHealth * 0.15);
        this.head.setHealth(this.headHealth);
        this.head.setOwner(this);
        this.head.setPartName("head");
        this.level().addFreshEntity(this.head);
    }

    private void removeBodyParts() {
        if (this.leftTendril != null) {
            this.leftTendril.discard();
            this.leftTendril = null;
        }
        if (this.rightTendril != null) {
            this.rightTendril.discard();
            this.rightTendril = null;
        }
        if (this.head != null) {
            this.head.discard();
            this.head = null;
        }
    }

    public void updateBodyParts() {
        boolean headWasAlive;
        if (this.level().isClientSide) {
            return;
        }
        boolean leftWasAlive = this.leftTendril != null && this.leftTendril.isAlive();
        boolean rightWasAlive = this.rightTendril != null && this.rightTendril.isAlive();
        boolean bl = headWasAlive = this.head != null && this.head.isAlive();
        if (this.leftTendril == null || !this.leftTendril.isAlive()) {
            this.leftTendril = null;
            this.leftTendrilHealth = 0.0f;
        }
        if (this.rightTendril == null || !this.rightTendril.isAlive()) {
            this.rightTendril = null;
            this.rightTendrilHealth = 0.0f;
        }
        if (this.head == null || !this.head.isAlive()) {
            this.head = null;
            this.headHealth = 0.0f;
        }
        if (leftWasAlive && this.leftTendril == null) {
            this.cutResistances();
        }
        if (rightWasAlive && this.rightTendril == null) {
            this.cutResistances();
        }
        if (headWasAlive && this.head == null) {
            this.cutResistances();
        }
        float yaw = this.getYRot() * ((float)Math.PI / 180);
        if (this.leftTendril != null && this.leftTendril.isAlive()) {
            double lx = this.getX() + (double)Mth.cos((float)(yaw + 1.5707964f)) * 3.3;
            double lz = this.getZ() - (double)Mth.sin((float)(yaw + 1.5707964f)) * 3.3;
            this.leftTendril.moveTo(lx, this.getY() + 1.0, lz, this.getYRot(), this.getXRot());
            this.leftTendrilHealth = this.leftTendril.getHealth();
        }
        if (this.rightTendril != null && this.rightTendril.isAlive()) {
            double rx = this.getX() + (double)Mth.cos((float)(yaw - 1.5707964f)) * 3.3;
            double rz = this.getZ() - (double)Mth.sin((float)(yaw - 1.5707964f)) * 3.3;
            this.rightTendril.moveTo(rx, this.getY() + 1.0, rz, this.getYRot(), this.getXRot());
            this.rightTendrilHealth = this.rightTendril.getHealth();
        }
        if (this.head != null && this.head.isAlive()) {
            double hx = this.getX() - (double)Mth.cos((float)yaw) * 2.2;
            double hz = this.getZ() + (double)Mth.sin((float)yaw) * 2.2;
            this.head.moveTo(hx, this.getY() + 4.1, hz, this.getYRot(), this.getXRot());
            this.headHealth = this.head.getHealth();
        }
    }

    public boolean areTendrilsAlive() {
        return this.leftTendril != null && this.leftTendril.isAlive() && this.rightTendril != null && this.rightTendril.isAlive();
    }

    public boolean isHeadAlive() {
        return this.head != null && this.head.isAlive();
    }

    @Nullable
    public EntityDraconiteBodyPart getLeftTendril() {
        return this.leftTendril;
    }

    @Nullable
    public EntityDraconiteBodyPart getRightTendril() {
        return this.rightTendril;
    }

    @Nullable
    public EntityDraconiteBodyPart getHead() {
        return this.head;
    }

    private void tryLearnDamageType(String damageType) {
        if (this.onFireAdaptDisableTimer > 0) {
            return;
        }
        if (this.newDamageCooldown > 0) {
            return;
        }
        if (this.adaptationMap.containsKey(damageType)) {
            int currentPoints = this.adaptationMap.get(damageType);
            if (currentPoints < this.difficulty.getAdaptationPointCap() && this.random.nextDouble() < this.difficulty.getAdaptationLearnChance()) {
                this.adaptationMap.put(damageType, currentPoints + 1);
            }
        } else if (this.adaptationMap.size() < this.adaptationDamageTypeCap && this.random.nextDouble() < this.difficulty.getAdaptationLearnChance()) {
            this.adaptationMap.put(damageType, 1);
            this.newDamageCooldown = 40;
        }
    }

    private float getAdaptationReduction(String damageType, float amount) {
        Integer points = this.adaptationMap.get(damageType);
        if (points == null || points <= 0) {
            return 0.0f;
        }
        double reduction = 0.1 * (double)points.intValue() * (double)amount;
        double maxReduction = 1.0 * (double)amount;
        return (float)Math.min(reduction, maxReduction);
    }

    private String getDamageTypeKey(DamageSource source) {
        return source.type().msgId();
    }

    private void cutResistances() {
        Iterator<String> it;
        this.stunTimer = 40;
        this.setAttackState(0);
        this.setAttacking(false);
        this.attackAnimationTimer = 0;
        this.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 100));
        this.addEffect(new MobEffectInstance(ModEffects.RAGE.get(), 200, 1));
        this.addEffect(new MobEffectInstance(ModEffects.BLEED.get(), 100, 0));
        this.playSound(ModSounds.TENDRIL_BREAK.get(), 3.0f, 1.0f);
        if (!this.adaptationMap.isEmpty() && (it = this.adaptationMap.keySet().iterator()).hasNext()) {
            it.next();
            it.remove();
        }
        this.adaptationDamageTypeCap = Math.max(0, this.adaptationDamageTypeCap - 1);
    }

    private boolean isInDarkness() {
        BlockPos pos = this.blockPosition();
        int blockLight = this.level().getBrightness(LightLayer.BLOCK, pos);
        int skyLight = this.level().getBrightness(LightLayer.SKY, pos);
        return blockLight <= 7 && skyLight <= 7;
    }

    private boolean isInLight() {
        return !this.isInDarkness();
    }

    private void spawnShadowClone() {
        if (this.level().isClientSide) {
            return;
        }
        if (!(this.level() instanceof ServerLevel)) {
            return;
        }
        EntityDraconite clone = new EntityDraconite(ModEntityTypes.DRACONITE.get(), this.level());
        clone.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
        clone.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.35910000000000003);
        clone.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(105.0);
        clone.setHealth(clone.getMaxHealth());
        clone.initBodyParts();
        clone.setClone(true);
        clone.setShadow(false);
        clone.cloneLifeTimer = 440;
        clone.originalEntityId = this.getId();
        clone.parasiteStatus = 0;
        if (this.hasCustomName()) {
            clone.setCustomName(Component.literal("--" + this.getCustomName().getString() + "--"));
            clone.setPersistenceRequired();
        }
        if (this.isFlying()) {
            clone.setFlying(true);
        }
        ((ServerLevel)this.level()).addFreshEntity(clone);
        this.cloneEntityId = clone.getId();
        this.setShadow(false);
        this.cloneSpawnCooldown = 10;
        this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENDER_DRAGON_GROWL, SoundSource.HOSTILE, 1.5f, 0.5f);
    }

    private void killClone() {
        if (this.cloneEntityId != 0 && !this.level().isClientSide) {
            EntityDraconite cloneEntity;
            Entity entity = this.level().getEntity(this.cloneEntityId);
            if (entity instanceof EntityDraconite && (cloneEntity = (EntityDraconite)entity).isClone()) {
                cloneEntity.discard();
            }
            this.cloneEntityId = 0;
        }
    }

    private void tickShadowSystem() {
        if (this.level().isClientSide) {
            return;
        }
        if (this.isClone()) {
            ++this.cloneLifeTimer;
            if (this.cloneLifeTimer > 22) {
                Entity entity;
                if (this.originalEntityId != 0 && (entity = this.level().getEntity(this.originalEntityId)) instanceof EntityDraconite) {
                    EntityDraconite original = (EntityDraconite)entity;
                    original.setShadow(true);
                    original.cloneEntityId = 0;
                    original.shadowDamageTick = 10;
                }
                this.discard();
                return;
            }
        }
        if (this.isShadow()) {
            if (this.cloneSpawnCooldown <= 0 && this.random.nextInt(100) == 0 && this.getTarget() != null) {
                this.spawnShadowClone();
            }
        } else if (!this.isClone()) {
            Entity cloneEntity;
            Entity entity = cloneEntity = this.cloneEntityId != 0 ? this.level().getEntity(this.cloneEntityId) : null;
            if (cloneEntity == null || !cloneEntity.isAlive()) {
                this.cloneEntityId = 0;
                if (this.shadowDamageTick <= 0) {
                    this.setShadow(true);
                    this.accumulatedShadowDamage = 0.0f;
                }
            } else if (this.getTarget() != null && cloneEntity instanceof EntityDraconite) {
                EntityDraconite cloneDraconite = (EntityDraconite)cloneEntity;
                cloneDraconite.setTarget(this.getTarget());
            }
        }
    }

    private boolean isOriginalAlive() {
        EntityDraconite original;
        if (this.originalEntityId == 0) {
            return true;
        }
        Entity entity = this.level().getEntity(this.originalEntityId);
        return entity instanceof EntityDraconite && (original = (EntityDraconite)entity).isAlive();
    }

    private void tickCosmicHacking() {
        if (this.level().isClientSide) {
            return;
        }
        // Cosmic hacking only activates on Normal difficulty and above
        if (!this.difficulty.hasCosmicHacking()) {
            this.hackTimer = 0;
            this.hackCyclesCompleted = 0;
            return;
        }
        if (!this.isShadow() || !this.isOnGround() || this.isClone()) {
            this.hackTimer = 0;
            this.hackCyclesCompleted = 0;
            return;
        }
        if (this.hackCooldown > 0) {
            --this.hackCooldown;
            return;
        }
        ++this.hackTimer;
        if (this.hackTimer < this.difficulty.getHackInterval()) {
            return;
        }
        this.hackTimer = 0;
        ++this.hackCyclesCompleted;
        if (this.hackCyclesCompleted < 3) {
            this.shadowDamageR = 0.6f;
            return;
        }
        AABB searchBox = this.getBoundingBox().inflate(24.0);
        List<LivingEntity> targets = this.level().getEntitiesOfClass(LivingEntity.class, searchBox, entity -> entity != this && !(entity instanceof EntityDraconite) && entity.isAlive());
        int maxTargets = this.difficulty.getHackMaxTargets();
        if (targets.size() > maxTargets) {
            targets = targets.subList(0, maxTargets);
        }
        for (LivingEntity target : targets) {
            int removedCount = 0;
            ArrayList<MobEffectInstance> effectsToRemove = new ArrayList<>();
            for (MobEffectInstance effectInstance : target.getActiveEffects()) {
                if (effectInstance.getEffect().getCategory() == MobEffectCategory.HARMFUL) continue;
                effectsToRemove.add(effectInstance);
            }
            for (MobEffectInstance effectInstance : effectsToRemove) {
                target.removeEffect(effectInstance.getEffect());
                float healAmount = (float)((double)this.getMaxHealth() * 0.01 * (double)(effectInstance.getAmplifier() + 1));
                this.heal(healAmount);
                ++removedCount;
            }
            int harmfulToApply = Math.max(1, removedCount);
            for (int i = 0; i < harmfulToApply; ++i) {
                this.applyRandomHarmfulEffect(target);
            }
        }
        if (this.hackCyclesCompleted >= this.difficulty.getHackMaxCycles()) {
            this.hackCyclesCompleted = 0;
            this.hackCooldown = this.difficulty.getHackCooldown();
        }
    }

    private void applyRandomHarmfulEffect(LivingEntity target) {
        int effectType = this.random.nextInt(6);
        int duration = 100 + this.random.nextInt(100);
        int amplifier = this.random.nextInt(2);
        switch (effectType) {
            case 0: {
                target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, duration, amplifier));
                break;
            }
            case 1: {
                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, duration, amplifier));
                break;
            }
            case 2: {
                target.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, duration, amplifier));
                break;
            }
            case 3: {
                target.addEffect(new MobEffectInstance(MobEffects.POISON, duration, amplifier));
                break;
            }
            case 4: {
                target.addEffect(new MobEffectInstance(MobEffects.WITHER, duration, amplifier));
                break;
            }
            case 5: {
                target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, duration, amplifier));
            }
        }
    }

    public boolean hurt(DamageSource source, float amount) {
        Entity skyLight2;
        boolean result;
        float adaptationReduction;
        if (this.level().isClientSide) {
            return false;
        }
        if (source.is(DamageTypeTags.IS_PROJECTILE)) {
            return super.hurt(source, amount);
        }
        if (source.is(DamageTypeTags.IS_FIRE)) {
            amount *= 4.0f;
        }
        if (this.isOnFire() && this.random.nextDouble() < 0.1) {
            this.onFireAdaptDisableTimer = 10;
        }
        amount = Math.min(amount, this.DAMAGE_CAP);
        String damageTypeKey = this.getDamageTypeKey(source);
        this.tryLearnDamageType(damageTypeKey);
        this.damageAmountReduced = adaptationReduction = this.getAdaptationReduction(damageTypeKey, amount);
        if (this.isShadow() || this.isClone()) {
            BlockPos pos = this.blockPosition();
            int blockLight = this.level().getBrightness(LightLayer.BLOCK, pos);
            int skyLight = this.level().getBrightness(LightLayer.SKY, pos);
            if (blockLight <= 7 && skyLight <= 7) {
                this.shakeTimer = 15;
                this.shakeee = 15;
                this.shadowDamageR = 0.6f;
                this.showC = 15;
                this.hitStatus = (byte)3;
                this.playSound(ModSounds.ADAPTATION_IMMUNE.get(), this.getSoundVolume(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1.0f);
                this.sendEntityEvent((byte)41);
                boolean flag = super.hurt(source, 0.0f);
                return flag;
            }
            this.accumulatedShadowDamage += amount;
            this.shadowDamageTick = 100;
            if ((double)this.accumulatedShadowDamage >= (double)this.getMaxHealth() * 0.1 && !this.isClone()) {
                this.setShadow(false);
                this.spawnShadowClone();
                this.shadowDamageTick = 200;
                this.accumulatedShadowDamage = 0.0f;
            }
            this.shakeTimer = 15;
            this.shakeee = 15;
            this.shadowDamageR = 0.6f;
            this.showC = 15;
            this.hitStatus = (byte)3;
            this.playSound(ModSounds.ADAPTATION_IMMUNE.get(), this.getSoundVolume(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1.0f);
            this.sendEntityEvent((byte)41);
            boolean flag = super.hurt(source, 0.0f);
            return flag;
        }
        this.hitStatus = 0;
        if (adaptationReduction > 0.0f) {
            Integer points = this.adaptationMap.get(damageTypeKey);
            if (points != null && points >= 10) {
                this.hitStatus = (byte)2;
                this.adaptationVisual = 2;
                this.adaptationVisualCooldown = 40;
                this.playSound(ModSounds.ADAPTATION_FULL.get(), this.getSoundVolume(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1.0f);
                if (!this.level().isClientSide) {
                    this.spawnAdaptationParticles(true);
                }
            } else {
                this.hitStatus = 1;
                this.adaptationVisual = 1;
                this.adaptationVisualCooldown = 30;
                this.playSound(ModSounds.ADAPTATION_PARTIAL.get(), this.getSoundVolume(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1.0f);
                if (!this.level().isClientSide) {
                    this.spawnAdaptationParticles(false);
                }
            }
        }
        if (!this.level().isClientSide && amount > 0.0f) {
            this.playSound(ModSounds.MOB_HITGROUND.get(), 2.0f, 0.8f + this.random.nextFloat() * 0.4f);
            this.spawnHitParticles();
        }
        if ((result = super.hurt(source, amount = Math.max(0.0f, amount - adaptationReduction))) && this.damageAmountReduced > 0.0f && (skyLight2 = source.getEntity()) instanceof LivingEntity) {
            LivingEntity attacker = skyLight2;
            float counterDamage = this.damageAmountReduced / 2.0f + 7.0f;
            attacker.hurt(this.damageSources().mobAttack(this), counterDamage);
        }
        if (result) {
            this.idleTime = 0;
            if (!this.isFlying() && this.areTendrilsAlive() && this.random.nextInt(12) == 0) {
                this.setFlying(true);
            }
            this.hurtAnimationTimer = 10;
        }
        return result;
    }

    public float attackEntityAsMobMinimum(LivingEntity target, float baseDamage) {
        float damage = Math.max(baseDamage, this.MINIMUM_DAMAGE);
        target.hurt(this.damageSources().generic(), damage);
        return damage;
    }

    public boolean doHurtTarget(Entity target) {
        if (this.stunTimer > 0) {
            return false;
        }
        float baseDamage = (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
        float damage = Math.max(baseDamage, this.MINIMUM_DAMAGE);
        if (target instanceof LivingEntity) {
            LivingEntity livingTarget = target;
            livingTarget.hurt(this.damageSources().mobAttack(this), damage);
            livingTarget.hurt(this.damageSources().generic(), this.MINIMUM_DAMAGE);
            double kbX = livingTarget.getX() - this.getX();
            double kbZ = livingTarget.getZ() - this.getZ();
            double kbDist = Math.sqrt(kbX * kbX + kbZ * kbZ);
            if (kbDist > 0.01) {
                livingTarget.knockback(2.0, -kbX / kbDist, -kbZ / kbDist);
            }
        }
        return true;
    }

    public void awardKillScore(Entity entity, int score, DamageSource source) {
        super.awardKillScore(entity, score, source);
        if (this.killcount < 25) {
            ++this.killcount;
        }
    }

    private void tickRegen() {
        if (this.level().isClientSide) {
            return;
        }
        if (this.isOnFire()) {
            return;
        }
        if (this.killcount <= 1) {
            return;
        }
        if (this.getHealth() >= this.getMaxHealth()) {
            return;
        }
        if (this.idleTime < 80) {
            return;
        }
        float healAmount = (float)((double)this.getMaxHealth() * this.REGEN_FRACTION);
        this.heal(healAmount);
        --this.regenUse;
        if (this.regenUse <= 0) {
            --this.killcount;
            this.regenUse = 10;
        }
    }

    private void destroyPathBlockingBlocks() {
        boolean wantsToMove;
        if (this.level().isClientSide) {
            return;
        }
        if (this.blockDestroyCooldown > 0) {
            return;
        }
        if (!ForgeEventFactory.getMobGriefingEvent((Level)this.level(), this)) {
            return;
        }
        LivingEntity target = this.getTarget();
        boolean bl = wantsToMove = target != null || this.getMoveControl().hasWanted();
        if (!wantsToMove && !this.isAggressive()) {
            return;
        }
        Vec3 lookVec = this.getLookAngle();
        BlockPos frontPos = this.blockPosition().offset((int)Math.round(lookVec.x * 3.0), 0, (int)Math.round(lookVec.z * 3.0));
        boolean destroyed = false;
        for (int dx = -1; dx <= 1 && !destroyed; ++dx) {
            for (int dy = 0; dy <= 3 && !destroyed; ++dy) {
                for (int dz = -1; dz <= 1 && !destroyed; ++dz) {
                    float hardness;
                    BlockPos pos = frontPos.offset(dx, dy, dz);
                    BlockState state = this.level().getBlockState(pos);
                    if (state.isAir() || state.is(BlockTags.DRAGON_IMMUNE) || !((hardness = state.getDestroySpeed(this.level(), pos)) >= 0.0f) || !(hardness <= 27.0f)) continue;
                    this.level().destroyBlock(pos, false);
                    destroyed = true;
                }
            }
        }
        if (destroyed) {
            this.blockDestroyCooldown = 40;
        }
    }

    private void tickLightSourceDestruction() {
        if (this.level().isClientSide) {
            return;
        }
        ++this.lightDestroyTimer;
        if (this.lightDestroyTimer < 160) {
            return;
        }
        this.lightDestroyTimer = 0;
        if (!ForgeEventFactory.getMobGriefingEvent((Level)this.level(), this)) {
            return;
        }
        BlockPos center = this.blockPosition();
        int radius = 6;
        for (int dx = -radius; dx <= radius; ++dx) {
            for (int dy = -radius; dy <= radius; ++dy) {
                for (int dz = -radius; dz <= radius; ++dz) {
                    float hardness;
                    if (dx * dx + dy * dy + dz * dz > radius * radius) continue;
                    BlockPos targetPos = center.offset(dx, dy, dz);
                    BlockState state = this.level().getBlockState(targetPos);
                    if (state.isAir() || state.getLightEmission(this.level(), targetPos) <= 0 || !((hardness = state.getDestroySpeed(this.level(), targetPos)) >= 0.0f) || !(hardness <= 27.0f)) continue;
                    this.level().destroyBlock(targetPos, false);
                }
            }
        }
    }

    public void tick() {
        super.tick();
        if (this.tickCount == 1) this.killcount = -10;
        if (this.hurtSoundCooldown > 0) {
            --this.hurtSoundCooldown;
        }
        if (this.stepSoundCooldown > 0) {
            --this.stepSoundCooldown;
        }
        if (this.shootSoundCooldown > 0) {
            --this.shootSoundCooldown;
        }
        if (this.ambientSoundCooldown > 0) {
            --this.ambientSoundCooldown;
        }
        if (this.newDamageCooldown > 0) {
            --this.newDamageCooldown;
        }
        if (this.adaptationVisualCooldown > 0) {
            --this.adaptationVisualCooldown;
            if (this.adaptationVisualCooldown <= 0) {
                this.adaptationVisual = 0;
            }
        }
        if (this.onFireAdaptDisableTimer > 0) {
            --this.onFireAdaptDisableTimer;
        }
        if (this.stunTimer > 0) {
            --this.stunTimer;
            if (this.stunTimer > 0) {
                this.setAttackState(0);
                this.setAttacking(false);
            }
        }
        if (this.shakeTimer > 0) {
            --this.shakeTimer;
        }
        if (this.shakeee > 0) {
            --this.shakeee;
        }
        if (this.shadowDamageRCooldown > 0) {
            --this.shadowDamageRCooldown;
        }
        if (this.shadowDamageR > 0.0f && this.shadowDamageRCooldown == 0) {
            this.shadowDamageR -= 0.01f;
            if (this.shadowDamageR < 0.0f) {
                this.shadowDamageR = 0.0f;
            }
        }
        if (this.showC > -10) {
            --this.showC;
        }
        if (this.random.nextInt(10) == 0 && this.showC <= -10) {
            this.showC = this.random.nextInt(1) * 60;
        }
        if (this.hurtAnimationTimer > 0) {
            --this.hurtAnimationTimer;
        }
        if (this.attackAnimationTimer > 0) {
            --this.attackAnimationTimer;
            if (this.attackAnimationTimer <= 0 && this.getAttackState() != 0) {
                this.setAttackState(0);
                this.setAttacking(false);
            }
        }
        if (!(this.level().isClientSide || this.leftTendril != null && this.rightTendril != null && this.head != null || this.tickCount <= 20)) {
            this.initBodyParts();
        }
        this.updateBodyParts();
        if (this.isFlying()) {
            this.wingFlapSoundCounter += 0.782f;
            if (this.wingFlapSoundCounter >= 24.0f) {
                this.playSound(SoundEvents.PHANTOM_FLAP, 5.0f, 0.8f + this.random.nextFloat() * 0.3f);
                this.wingFlapSoundCounter = 0.0f;
            }
        } else {
            this.wingFlapSoundCounter = 0.0f;
        }
        if (!this.isFlying() && this.areTendrilsAlive()) {
            this.setFlying(true);
        }
        if (this.vomitTimer > 0) {
            --this.vomitTimer;
        }
        this.flyingTickCount = this.isFlying() ? ++this.flyingTickCount : 0;
        if (this.isFlying() && !this.level().isClientSide) {
            BlockPos below1 = this.blockPosition().below();
            BlockPos below2 = this.blockPosition().below(2);
            if (!(this.level().getBlockState(below1).isAir() && this.level().getBlockState(below2).isAir() || this.random.nextInt(3) != 0)) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0, 0.5, 0.0));
            }
        }
        if (this.isRainingFireballs && this.rainingFireballTicks > 0) {
            --this.rainingFireballTicks;
            if (this.rainingFireballTicks <= 15) {
                this.spawnRainingFireball();
            }
            if (this.rainingFireballTicks <= 0) {
                this.isRainingFireballs = false;
            }
        }
        if (this.isRainingOrbs && this.rainingOrbTicks > 0) {
            --this.rainingOrbTicks;
            if (this.rainingOrbTicks % 10 == 0) {
                this.spawnRainingOrb();
            }
            if (this.rainingOrbTicks <= 0) {
                this.isRainingOrbs = false;
            }
        }
        if (!this.level().isClientSide) {
            if (this.shadowDamageTick > 0) {
                --this.shadowDamageTick;
            }
            if (this.shadowDamageTick <= 0) {
                this.accumulatedShadowDamage = 0.0f;
            }
            if (this.cloneSpawnCooldown > 0) {
                --this.cloneSpawnCooldown;
            }
            if (this.tickCount % 20 == 0) {
                this.tickShadowSystem();
            }
            if (this.isClone() && !this.isOriginalAlive()) {
                this.discard();
                return;
            }
            this.idleTime = this.getTarget() == null && this.noActionTime <= 0 ? ++this.idleTime : 0;
            this.tickRegen();
            if (this.blockDestroyCooldown > 0) {
                --this.blockDestroyCooldown;
            }
            if (this.isAggressive() || this.getTarget() != null && this.getMoveControl().hasWanted()) {
                this.destroyPathBlockingBlocks();
            }
            this.tickLightSourceDestruction();
            this.tickCosmicHacking();
        }
        if (this.level().isClientSide) {
            this.spawnAmbientParticles();
        }
        this.wasFlying = this.isFlying();
    }

    private void spawnAmbientParticles() {
        int attackState;
        double pz;
        double py;
        double px;
        if (this.random.nextInt(3) == 0) {
            px = this.getX() + (this.random.nextDouble() - 0.5) * 3.0;
            py = this.getY() + this.random.nextDouble() * 3.8;
            pz = this.getZ() + (this.random.nextDouble() - 0.5) * 3.0;
            this.level().addParticle(ParticleTypes.PORTAL, px, py, pz, 0.0, 0.02, 0.0);
        }
        if (this.isAttacking() && this.random.nextInt(2) == 0) {
            px = this.getX() + (this.random.nextDouble() - 0.5) * 2.0;
            py = this.getY() + 1.5 + this.random.nextDouble() * 1.5;
            pz = this.getZ() + (this.random.nextDouble() - 0.5) * 2.0;
            this.level().addParticle(ParticleTypes.FLAME, px, py, pz, (this.random.nextDouble() - 0.5) * 0.1, 0.1, (this.random.nextDouble() - 0.5) * 0.1);
        }
        if (this.isAttacking() && this.random.nextInt(3) == 0 && ((attackState = this.getAttackState()) == 2 || attackState == 3 || attackState == 4)) {
            Vec3 look = this.getLookAngle();
            double mouthX = this.getX() + look.x * 3.0;
            double mouthY = this.getY() + 3.5 + look.y * 1.0;
            double mouthZ = this.getZ() + look.z * 3.0;
            for (int i = 0; i < 3; ++i) {
                this.level().addParticle(ParticleTypes.FLAME, mouthX + (this.random.nextDouble() - 0.5) * 0.5, mouthY + (this.random.nextDouble() - 0.5) * 0.5, mouthZ + (this.random.nextDouble() - 0.5) * 0.5, look.x * 0.3 + (this.random.nextDouble() - 0.5) * 0.1, look.y * 0.3 + (this.random.nextDouble() - 0.5) * 0.1, look.z * 0.3 + (this.random.nextDouble() - 0.5) * 0.1);
            }
        }
        if (this.vomitTimer > 0 && this.level().isClientSide) {
            Vec3 look = this.getLookAngle();
            for (int i = 0; i < 6; ++i) {
                double px2 = this.getX() + look.x * 3.0 + (this.random.nextDouble() - 0.5) * 1.5;
                double py2 = this.getY() + 3.5 + (this.random.nextDouble() - 0.5) * 1.0;
                double pz2 = this.getZ() + look.z * 3.0 + (this.random.nextDouble() - 0.5) * 1.5;
                double motionY = this.isRainingFireballs ? 0.3 : -0.1;
                this.level().addParticle(ParticleTypes.DRAGON_BREATH, px2, py2, pz2, look.x * 0.2 + (this.random.nextDouble() - 0.5) * 0.1, motionY + this.random.nextDouble() * 0.1, look.z * 0.2 + (this.random.nextDouble() - 0.5) * 0.1);
            }
            if (this.random.nextInt(2) == 0) {
                double px3 = this.getX() + look.x * 3.5 + (this.random.nextDouble() - 0.5) * 1.0;
                double py3 = this.getY() + 3.5 + (this.random.nextDouble() - 0.5) * 0.5;
                double pz3 = this.getZ() + look.z * 3.5 + (this.random.nextDouble() - 0.5) * 1.0;
                this.level().addParticle(ParticleTypes.FLAME, px3, py3, pz3, look.x * 0.3, 0.05, look.z * 0.3);
            }
        }
    }

    private void spawnAdaptationParticles(boolean isFull) {
        if (this.level().isClientSide) {
            return;
        }
        ServerLevel serverLevel = (ServerLevel)this.level();
        SimpleParticleType particleType = isFull ? ParticleTypes.DRAGON_BREATH : ParticleTypes.ENCHANT;
        for (int i = 0; i < 12; ++i) {
            double px = this.getX() + (this.random.nextDouble() - 0.5) * 3.0;
            double py = this.getY() + this.random.nextDouble() * 3.8;
            double pz = this.getZ() + (this.random.nextDouble() - 0.5) * 3.0;
            serverLevel.sendParticles(particleType, px, py, pz, 1, (this.random.nextDouble() - 0.5) * 0.3, 0.2, (this.random.nextDouble() - 0.5) * 0.3, 0.02);
        }
    }

    private void spawnHitParticles() {
        if (this.level().isClientSide) {
            return;
        }
        ServerLevel serverLevel = (ServerLevel)this.level();
        for (int i = 0; i < 8; ++i) {
            double px = this.getX() + (this.random.nextDouble() - 0.5) * 2.4;
            double py = this.getY() + 0.5 + this.random.nextDouble() * 3.0;
            double pz = this.getZ() + (this.random.nextDouble() - 0.5) * 2.4;
            serverLevel.sendParticles(ParticleTypes.DAMAGE_INDICATOR, px, py, pz, 1, (this.random.nextDouble() - 0.5) * 0.2, 0.1, (this.random.nextDouble() - 0.5) * 0.2, 0.0);
        }
    }

    public void handleEntityEvent(byte id) {
        if (id == 100) {
            this.vomitTimer = 40;
            this.isRainingFireballs = true;
        } else if (id == 101) {
            this.vomitTimer = 40;
        } else if (id == 41) {
            this.shakeee = 15;
            this.shadowDamageR = 0.6f;
            this.shadowDamageRCooldown = 40;
            this.showC = 15;
        } else {
            super.handleEntityEvent(id);
        }
    }

    public void sendEntityEvent(byte id) {
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, id);
        }
    }

    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide) {
            this.updateBodyParts();
        }
    }

    public void performRangedAttack(LivingEntity target, float velocity) {
        if (this.stunTimer > 0) {
            return;
        }
        this.idleTime = 0;
        this.vomitTimer = 40;
        if (target.getY() > this.getY() + 5.0 || target.getY() < this.getY()) {
            this.shootAlafhaBall(target);
            return;
        }
        if (this.random.nextBoolean()) {
            this.isRainingFireballs = true;
            this.rainingFireballTicks = 19;
            this.playShootSound();
            this.sendEntityEvent((byte)100);
        } else {
            this.setAttackState(4);
            this.setAttacking(true);
            this.attackAnimationTimer = 24;
            Vec3 look = this.getLookAngle();
            double bon = 12.5;
            float rad = 2.0f;
            for (int i = 0; i < 3; ++i) {
                double cx = this.getX() + look.x * bon;
                double cy = Math.max(this.getY(), target.getY());
                double cz = this.getZ() + look.z * bon;
                this.spawnToxicCloud(cx, cy, cz, rad + 1.0f);
                if (i == 1) {
                    bon += 4.0;
                }
                bon += 7.5 + (double)i;
                rad += 2.0f;
            }
            this.playShootSound();
            this.sendEntityEvent((byte)101);
        }
    }

    private void shootAlafhaBall(LivingEntity target) {
        if (this.level().isClientSide) {
            return;
        }
        if (this.stunTimer > 0) {
            return;
        }
        this.setAttackState(2);
        this.setAttacking(true);
        this.attackAnimationTimer = 92;
        Vec3 vec3d = this.getLookAngle();
        double d2 = target.getX() - (this.getX() + vec3d.x * 4.0);
        double d3 = target.getY() + (double)target.getBbHeight() / 2.0 - (0.5 + this.getY() + (double)this.getBbHeight() / 2.0);
        double d4 = target.getZ() - (this.getZ() + vec3d.z * 4.0);
        EntityDraconiteFireball fireball = new EntityDraconiteFireball((EntityType<? extends Fireball>)(ModEntityTypes.DRACONITE_FIREBALL.get()), this.level(), this, d2, d3, d4);
        fireball.setPos(this.getX() + vec3d.x * 4.0, this.getY() + (double)this.getBbHeight() / 2.0 + 0.5, this.getZ() + vec3d.z * 4.0);
        this.level().addFreshEntity(fireball);
        this.playShootSound();
    }

    private void spawnToxicCloud(double x, double y, double z, float radius) {
        EntityToxicCloud cloud = new EntityToxicCloud(ModEntityTypes.TOXIC_CLOUD.get(), this.level());
        cloud.setPos(x, y, z);
        cloud.setOwner(this);
        cloud.setDuration(100);
        cloud.setRadius(radius);
        cloud.setEffect(ModEffects.COTH.get(), 300, 0);
        if (!this.level().isClientSide) {
            this.level().addFreshEntity(cloud);
        }
    }

    public void shootFireballAt(LivingEntity target) {
        if (this.level().isClientSide) {
            return;
        }
        if (this.stunTimer > 0) {
            return;
        }
        this.setAttackState(2);
        this.setAttacking(true);
        this.attackAnimationTimer = 92;
        double distance = this.distanceTo(target);
        double flightTime = distance / 6.0;
        double dx = target.getX() + target.getDeltaMovement().x * flightTime - this.getX();
        double dy = target.getEyeHeight() + target.getDeltaMovement().y * flightTime - this.getEyeHeight();
        double dz = target.getZ() + target.getDeltaMovement().z * flightTime - this.getZ();
        EntityDraconiteFireball fireball = new EntityDraconiteFireball((EntityType<? extends Fireball>)(ModEntityTypes.DRACONITE_FIREBALL.get()), this.level(), this, dx, dy, dz);
        fireball.setPos(this.getX(), this.getEyeHeight(), this.getZ());
        this.level().addFreshEntity(fireball);
        this.playShootSound();
    }

    public void triggerRainingFireballs(LivingEntity target) {
        this.isRainingFireballs = true;
        this.rainingFireballTicks = 19;
        this.playRoarSound();
    }

    private void spawnRainingFireball() {
        if (this.level().isClientSide) {
            return;
        }
        double radius = 10.0;
        double x = this.getX() + (this.random.nextDouble() * 2.0 - 1.0) * radius;
        double y = this.getY() + 20.0;
        double z = this.getZ() + (this.random.nextDouble() * 2.0 - 1.0) * radius;
        LivingEntity target = this.getTarget();
        if (target != null && this.random.nextBoolean()) {
            x = target.getX() + (this.random.nextDouble() * 2.0 - 1.0) * radius;
            z = target.getZ() + (this.random.nextDouble() * 2.0 - 1.0) * radius;
        }
        EntityDraconiteFireball fireball = new EntityDraconiteFireball((EntityType<? extends Fireball>)(ModEntityTypes.DRACONITE_FIREBALL.get()), this.level(), this, 0.0, -10.0, 0.0);
        fireball.setPos(x, y, z);
        this.level().addFreshEntity(fireball);
    }

    public boolean isRainingFireballs() {
        return this.isRainingFireballs;
    }

    public void setRainingFireballs(boolean raining) {
        this.isRainingFireballs = raining;
    }

    public boolean isRainingOrbs() {
        return this.isRainingOrbs;
    }

    public void setRainingOrbs(boolean raining) {
        this.isRainingOrbs = raining;
        if (raining) {
            this.rainingOrbTicks = 40;
        }
    }

    public void takeFlight() {
        if (this.areTendrilsAlive() && !this.isFlying()) {
            this.setFlying(true);
        }
    }

    public void land() {
        if (this.isFlying()) {
            this.setFlying(false);
        }
    }

    public void spawnScorbOrb(double x, double y, double z) {
        if (this.level().isClientSide) {
            return;
        }
        EntityOrbScary orb = new EntityOrbScary(ModEntityTypes.ORB_SCARY.get(), this.level());
        orb.setPos(x, y, z);
        orb.setOwner(this);
        this.level().addFreshEntity(orb);
    }

    public void spawnConsecutiveScorbOrbs(LivingEntity target) {
        if (this.level().isClientSide || target == null) {
            return;
        }
        for (int i = 0; i < 2; ++i) {
            double offsetX = (this.random.nextDouble() - 0.5) * 2.0;
            double offsetZ = (this.random.nextDouble() - 0.5) * 2.0;
            this.spawnScorbOrb(target.getX() + offsetX, target.getY() + 1.0 + this.random.nextDouble() * 2.0, target.getZ() + offsetZ);
        }
    }

    public void spawnBoomOrb(LivingEntity target) {
        if (this.level().isClientSide) {
            return;
        }
        double dx = target.getX() - this.getX();
        double dy = target.getEyeHeight() - (this.getY() + 2.0);
        double dz = target.getZ() - this.getZ();
        EntityOrbBoom orb = new EntityOrbBoom(ModEntityTypes.ORB_BOOM.get(), this.level());
        orb.setPos(this.getX(), this.getY() + 2.0, this.getZ());
        orb.setOwner(this);
        orb.setTargetDirection(new Vec3(dx, dy, dz));
        this.level().addFreshEntity(orb);
        this.playShootSound();
    }

    private void spawnRainingOrb() {
        if (this.level().isClientSide || this.getTarget() == null) {
            return;
        }
        LivingEntity target = this.getTarget();
        double offsetX = (this.random.nextDouble() - 0.5) * 10.0;
        double offsetZ = (this.random.nextDouble() - 0.5) * 10.0;
        EntityOrbScary orb = new EntityOrbScary(ModEntityTypes.ORB_SCARY.get(), this.level());
        orb.setPos(target.getX() + offsetX, target.getY() + 15.0 + this.random.nextDouble() * 5.0, target.getZ() + offsetZ);
        orb.setOwner(this);
        this.level().addFreshEntity(orb);
    }

    public boolean isStunned() {
        return this.stunTimer > 0;
    }

    public int getAdaptationDamageTypeCap() {
        return this.adaptationDamageTypeCap;
    }

    public int getParasiteStatus() {
        return this.parasiteStatus;
    }

    public float getShadowDamageR() {
        return this.shadowDamageR;
    }

    public int getShakeee() {
        return this.shakeee;
    }

    public int getShakeTimer() {
        return this.shakeTimer;
    }

    public int getShowC() {
        return this.showC;
    }

    public int getAdaptationVisual() {
        return this.adaptationVisual;
    }

    public float getAdaptationVisualIntensity() {
        if (this.adaptationVisualCooldown <= 0) {
            return 0.0f;
        }
        return Math.min(1.0f, (float)this.adaptationVisualCooldown / 30.0f);
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setFlying(tag.getBoolean("Flying"));
        this.setAttacking(tag.getBoolean("Attacking"));
        this.setRainingFireballs(tag.getBoolean("RainingFireballs"));
        this.isRainingOrbs = tag.getBoolean("RainingOrbs");
        this.rainingOrbTicks = tag.getInt("RainingOrbTicks");
        this.leftTendrilHealth = tag.getFloat("LeftTendrilHealth");
        this.rightTendrilHealth = tag.getFloat("RightTendrilHealth");
        this.headHealth = tag.getFloat("HeadHealth");
        this.setShadow(tag.getBoolean("Shadow"));
        this.accumulatedShadowDamage = tag.getFloat("AccumulatedShadowDamage");
        this.shadowDamageTick = tag.getInt("ShadowDamageTick");
        this.cloneEntityId = tag.getInt("CloneEntityId");
        this.originalEntityId = tag.getInt("OriginalEntityId");
        this.cloneLifeTimer = tag.getInt("CloneLifeTimer");
        this.setClone(tag.getBoolean("IsClone"));
        this.cloneSpawnCooldown = tag.getInt("CloneSpawnCooldown");
        this.killcount = tag.getInt("KillCount");
        this.regenUse = tag.getInt("RegenUse");
        this.idleTime = tag.getInt("IdleTime");
        this.adaptationMap.clear();
        ListTag adaptationList = tag.getList("AdaptationData", 10);
        for (int i = 0; i < adaptationList.size(); ++i) {
            CompoundTag entryTag = adaptationList.getCompound(i);
            this.adaptationMap.put(entryTag.getString("DamageType"), entryTag.getInt("Points"));
        }
        this.newDamageCooldown = tag.getInt("NewDamageCooldown");
        this.onFireAdaptDisableTimer = tag.getInt("OnFireAdaptDisableTimer");
        this.damageAmountReduced = tag.getFloat("DamageAmountReduced");
        this.adaptationDamageTypeCap = tag.getInt("AdaptationDamageTypeCap");
        if (this.adaptationDamageTypeCap <= 0 && tag.contains("AdaptationDamageTypeCap")) {
            this.adaptationDamageTypeCap = 0;
        }
        this.stunTimer = tag.getInt("StunTimer");
        this.hackTimer = tag.getInt("HackTimer");
        this.hackCooldown = tag.getInt("HackCooldown");
        this.hackCyclesCompleted = tag.getInt("HackCyclesCompleted");
        this.parasiteStatus = tag.getInt("ParasiteStatus");
        this.blockDestroyCooldown = tag.getInt("BlockDestroyCooldown");
        this.lightDestroyTimer = tag.getInt("LightDestroyTimer");
        this.wingFlapSoundCounter = tag.getFloat("WingFlapSoundCounter");
        this.idleHoverTimer = tag.getInt("IdleHoverTimer");
        this.idleHoverVariant = tag.getInt("IdleHoverVariant");
        this.adaptationVisual = tag.getInt("AdaptationVisual");
        this.adaptationVisualCooldown = tag.getInt("AdaptationVisualCooldown");
        this.hitStatus = tag.getByte("HitStatus");
        this.shadowDamageR = tag.getFloat("ShadowDamageR");
        this.shadowDamageRCooldown = tag.getInt("ShadowDamageRCooldown");
        this.shakeTimer = tag.getInt("ShakeTimer");
        this.shakeee = tag.getInt("Shakeee");
        this.showC = tag.getInt("ShowC");
        this.vomitTimer = tag.getInt("VomitTimer");
        this.flyingTickCount = tag.getInt("FlyingTickCount");
        if (this.isFlying()) {
            this.setNoGravity(true);
        }
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("Flying", this.isFlying());
        tag.putBoolean("Attacking", this.isAttacking());
        tag.putBoolean("RainingFireballs", this.isRainingFireballs);
        tag.putBoolean("RainingOrbs", this.isRainingOrbs);
        tag.putInt("RainingOrbTicks", this.rainingOrbTicks);
        tag.putFloat("LeftTendrilHealth", this.leftTendrilHealth);
        tag.putFloat("RightTendrilHealth", this.rightTendrilHealth);
        tag.putFloat("HeadHealth", this.headHealth);
        tag.putBoolean("Shadow", this.isShadow());
        tag.putFloat("AccumulatedShadowDamage", this.accumulatedShadowDamage);
        tag.putInt("ShadowDamageTick", this.shadowDamageTick);
        tag.putInt("CloneEntityId", this.cloneEntityId);
        tag.putInt("OriginalEntityId", this.originalEntityId);
        tag.putInt("CloneLifeTimer", this.cloneLifeTimer);
        tag.putBoolean("IsClone", this.isClone());
        tag.putInt("CloneSpawnCooldown", this.cloneSpawnCooldown);
        tag.putInt("KillCount", this.killcount);
        tag.putInt("RegenUse", this.regenUse);
        tag.putInt("IdleTime", this.idleTime);
        ListTag adaptationList = new ListTag();
        for (Map.Entry<String, Integer> entry : this.adaptationMap.entrySet()) {
            CompoundTag entryTag = new CompoundTag();
            entryTag.putString("DamageType", entry.getKey());
            entryTag.putInt("Points", entry.getValue().intValue());
            adaptationList.add(entryTag);
        }
        tag.put("AdaptationData", adaptationList);
        tag.putInt("NewDamageCooldown", this.newDamageCooldown);
        tag.putInt("OnFireAdaptDisableTimer", this.onFireAdaptDisableTimer);
        tag.putFloat("DamageAmountReduced", this.damageAmountReduced);
        tag.putInt("AdaptationDamageTypeCap", this.adaptationDamageTypeCap);
        tag.putInt("StunTimer", this.stunTimer);
        tag.putInt("HackTimer", this.hackTimer);
        tag.putInt("HackCooldown", this.hackCooldown);
        tag.putInt("HackCyclesCompleted", this.hackCyclesCompleted);
        tag.putInt("ParasiteStatus", this.parasiteStatus);
        tag.putInt("BlockDestroyCooldown", this.blockDestroyCooldown);
        tag.putInt("LightDestroyTimer", this.lightDestroyTimer);
        tag.putFloat("WingFlapSoundCounter", this.wingFlapSoundCounter);
        tag.putInt("IdleHoverTimer", this.idleHoverTimer);
        tag.putInt("IdleHoverVariant", this.idleHoverVariant);
        tag.putInt("AdaptationVisual", this.adaptationVisual);
        tag.putInt("AdaptationVisualCooldown", this.adaptationVisualCooldown);
        tag.putByte("HitStatus", this.hitStatus);
        tag.putFloat("ShadowDamageR", this.shadowDamageR);
        tag.putInt("ShadowDamageRCooldown", this.shadowDamageRCooldown);
        tag.putInt("ShakeTimer", this.shakeTimer);
        tag.putInt("Shakeee", this.shakeee);
        tag.putInt("ShowC", this.showC);
        tag.putInt("VomitTimer", this.vomitTimer);
        tag.putInt("FlyingTickCount", this.flyingTickCount);
    }

    public void die(DamageSource source) {
        Entity entity;
        if (!this.isClone() && this.cloneEntityId != 0) {
            entity = this.level().getEntity(this.cloneEntityId);
            if (entity instanceof EntityDraconite) {
                EntityDraconite cloneEntity = (EntityDraconite)entity;
                cloneEntity.discard();
            }
            this.cloneEntityId = 0;
        }
        if (this.isClone() && this.originalEntityId != 0 && (entity = this.level().getEntity(this.originalEntityId)) instanceof EntityDraconite) {
            EntityDraconite original = (EntityDraconite)entity;
            original.cloneEntityId = 0;
        }
        this.removeBodyParts();
        this.playSound(ModSounds.HEBLU_DEATH.get(), this.getSoundVolume(), 0.8f + this.random.nextFloat() * 0.4f);
        super.die(source);
    }

    public void setRemoved(Entity.RemovalReason reason) {
        this.removeBodyParts();
        if (!this.isClone()) {
            this.killClone();
        }
        super.setRemoved(reason);
    }

    public boolean isPickable() {
        return true;
    }

    public boolean canBeCollidedWith() {
        return false;
    }

    protected void push(Entity entity) {
    }

    public boolean canCollideWith() {
        return false;
    }

    public float getEyeHeight(Pose pose, EntityDimensions dimensions) {
        return 1.75f;
    }

    public static boolean checkDraconiteSpawnRules(EntityType<? extends EntityDraconite> type, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        return true;
    }

    static class DraconiteDiveGoal
    extends Goal {
        private final EntityDraconite draconite;

        public DraconiteDiveGoal(EntityDraconite draconite) {
            this.draconite = draconite;
            this.setFlags(EnumSet.of(Goal.Flag.JUMP));
        }

        public boolean canUse() {
            return this.draconite.isInWater() || this.draconite.isInLava();
        }

        public void tick() {
            if ((this.draconite.isInWater() || this.draconite.isInLava()) && this.draconite.getRandom().nextFloat() < 0.8f) {
                this.draconite.getJumpControl().jumpFromGround();
            }
        }
    }

    static class DraconiteFlightAttackGoal
    extends Goal {
        private final EntityDraconite draconite;
        private int phaseTimer;
        private int phaseType;
        private double waypointX;
        private double waypointY;
        private double waypointZ;
        private int attackCooldown;
        private static final int FIREBALL_COOLDOWN = 40;

        public DraconiteFlightAttackGoal(EntityDraconite draconite) {
            this.draconite = draconite;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean canUse() {
            return this.draconite.isFlying() && this.draconite.getTarget() != null && !this.draconite.isStunned();
        }

        public boolean canContinueToUse() {
            return this.canUse() && this.draconite.getTarget() != null;
        }

        public void start() {
            this.phaseTimer = 0;
            this.phaseType = 0;
            this.attackCooldown = 10;
        }

        public void tick() {
            double dz;
            LivingEntity target = this.draconite.getTarget();
            if (target == null) {
                return;
            }
            double distance = this.draconite.distanceToSqr(target);
            ++this.phaseTimer;
            int phase1 = 40;
            int phase2 = 30;
            int phase3 = 40;
            int phase4 = 30;
            int phase5 = 40;
            int totalCycle = phase1 + phase2 + phase3 + phase4 + phase5;
            int cyclePos = this.phaseTimer % totalCycle;
            this.phaseType = cyclePos < phase1 ? 0 : (cyclePos < phase1 + phase2 ? 1 : (cyclePos < phase1 + phase2 + phase3 ? 2 : (cyclePos < phase1 + phase2 + phase3 + phase4 ? 3 : 4)));
            double dx = target.getX() - this.draconite.getX();
            double dist = Math.sqrt(dx * dx + (dz = target.getZ() - this.draconite.getZ()) * dz);
            if (dist < 0.01) {
                dist = 0.01;
            }
            double ndx = dx / dist;
            double ndz = dz / dist;
            double hoverY = target.getY() + 8.0 + Math.sin((double)this.draconite.tickCount * 0.05) * 2.0;
            switch (this.phaseType) {
                case 0: {
                    this.waypointX = target.getX();
                    this.waypointY = hoverY;
                    this.waypointZ = target.getZ();
                    break;
                }
                case 1: {
                    this.waypointX = target.getX() + ndz * 8.0;
                    this.waypointY = hoverY;
                    this.waypointZ = target.getZ() - ndx * 8.0;
                    break;
                }
                case 2: {
                    this.waypointX = target.getX() + Math.sin((double)this.draconite.tickCount * 0.1) * 3.0;
                    this.waypointY = hoverY;
                    this.waypointZ = target.getZ() + Math.cos((double)this.draconite.tickCount * 0.1) * 3.0;
                    break;
                }
                case 3: {
                    this.waypointX = target.getX() - ndz * 8.0;
                    this.waypointY = hoverY;
                    this.waypointZ = target.getZ() + ndx * 8.0;
                    break;
                }
                case 4: {
                    this.waypointX = target.getX();
                    this.waypointY = target.getY() + 3.0;
                    this.waypointZ = target.getZ();
                }
            }
            double speed = this.phaseType == 4 ? 1.5 : (this.phaseType == 2 ? 0.5 : 1.0);
            this.draconite.getMoveControl().setWantedPosition(this.waypointX, this.waypointY, this.waypointZ, speed);
            this.draconite.getLookControl().setLookAt(target, 30.0f, 30.0f);
            if (this.draconite.isHeadAlive() && this.draconite.getSensing().hasLineOfSight(target)) {
                --this.attackCooldown;
                if (this.draconite.hasEffect(ModEffects.RAGE.get())) {
                    --this.attackCooldown;
                }
                if (this.attackCooldown <= 0) {
                    double distToTarget = this.draconite.distanceTo(target);
                    if (distToTarget <= 64.0) {
                        if (target.isOnGround() && this.draconite.getRandom().nextInt(3) == 0) {
                            this.draconite.triggerRainingFireballs(target);
                            this.draconite.playShootSound();
                            this.attackCooldown = 55;
                        } else {
                            this.draconite.shootFireballAt(target);
                            this.attackCooldown = 40;
                        }
                        this.draconite.setAttacking(true);
                        this.draconite.setAttackState(2);
                        this.draconite.attackAnimationTimer = 20;
                    } else {
                        this.attackCooldown = 10;
                    }
                }
            }
        }

        public void stop() {
            this.draconite.setAttacking(false);
            this.draconite.setAttackState(0);
        }

        public boolean canBeUsedWhileContinuing() {
            return true;
        }
    }

    static class DraconiteRangedAttackGoal
    extends Goal {
        private final EntityDraconite draconite;
        private final double speedModifier;
        private final int attackInterval;
        private final float attackRadius;
        private int attackTime;

        public DraconiteRangedAttackGoal(EntityDraconite draconite, double speedModifier, int attackInterval, float attackRadius) {
            this.draconite = draconite;
            this.speedModifier = speedModifier;
            this.attackInterval = attackInterval;
            this.attackRadius = attackRadius;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean canUse() {
            LivingEntity target = this.draconite.getTarget();
            if (target == null || !target.isAlive()) {
                return false;
            }
            if (this.draconite.isStunned()) {
                return false;
            }
            double distance = this.draconite.distanceTo(target);
            return distance <= (double)this.attackRadius && distance > 9.0;
        }

        public boolean canContinueToUse() {
            return this.canUse();
        }

        public void start() {
            this.attackTime = this.attackInterval / 2;
        }

        public void tick() {
            LivingEntity target = this.draconite.getTarget();
            if (target == null) {
                return;
            }
            double distance = this.draconite.distanceTo(target);
            this.draconite.getLookControl().setLookAt(target, 30.0f, 30.0f);
            if (this.draconite.isFlying()) {
                double hoverY = target.getY() + 6.0;
                this.draconite.getMoveControl().setWantedPosition(target.getX(), hoverY, target.getZ(), 0.5);
            } else if (distance > (double)this.attackRadius * 0.8) {
                this.draconite.getNavigation().moveTo(target, this.speedModifier);
            } else {
                this.draconite.getNavigation().stop();
            }
            --this.attackTime;
            if (this.attackTime <= 0 && distance <= (double)this.attackRadius) {
                this.draconite.performRangedAttack(target, 1.0f);
                this.attackTime = this.attackInterval;
            }
        }

        public void stop() {
            this.draconite.getNavigation().stop();
        }

        public boolean canBeUsedWhileContinuing() {
            return true;
        }
    }

    static class DraconiteMeleeRangeSwitchGoal
    extends Goal {
        private final EntityDraconite draconite;
        private final double range;
        private int pathRecalcTimer;

        public DraconiteMeleeRangeSwitchGoal(EntityDraconite draconite, double range) {
            this.draconite = draconite;
            this.range = range;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean canUse() {
            return !this.draconite.isFlying() && this.draconite.getTarget() != null && (double)this.draconite.distanceTo(this.draconite.getTarget()) > 4.0 && !this.draconite.isStunned();
        }

        public boolean canContinueToUse() {
            return !this.draconite.isFlying() && this.draconite.getTarget() != null && (double)this.draconite.distanceTo(this.draconite.getTarget()) > 4.0 && !this.draconite.isStunned();
        }

        public void tick() {
            LivingEntity target = this.draconite.getTarget();
            if (target == null) {
                return;
            }
            this.draconite.getLookControl().setLookAt(target, 30.0f, 30.0f);
            --this.pathRecalcTimer;
            if (this.pathRecalcTimer <= 0) {
                this.draconite.getNavigation().moveTo(target, 1.3);
                this.pathRecalcTimer = 5;
            }
        }

        public void stop() {
            this.draconite.getNavigation().stop();
        }

        public boolean canBeUsedWhileContinuing() {
            return true;
        }
    }

    static class DraconiteBlockLightGoal
    extends Goal {
        private final EntityDraconite draconite;
        private BlockPos targetPos;
        private int tryTicks;

        public DraconiteBlockLightGoal(EntityDraconite draconite) {
            this.draconite = draconite;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean canUse() {
            if (this.draconite.isFlying()) {
                return false;
            }
            if (this.draconite.getTarget() != null) {
                return false;
            }
            if (this.draconite.getRandom().nextInt(40) != 0) {
                return false;
            }
            BlockPos entityPos = this.draconite.blockPosition();
            int searchRadius = 16;
            BlockPos nearest = null;
            double nearestDist = Double.MAX_VALUE;
            for (int dx = -searchRadius; dx <= searchRadius; ++dx) {
                for (int dy = -4; dy <= 8; ++dy) {
                    for (int dz = -searchRadius; dz <= searchRadius; ++dz) {
                        double dist;
                        BlockPos pos = entityPos.offset(dx, dy, dz);
                        BlockState state = this.draconite.level().getBlockState(pos);
                        if (!this.isLightOrRedstone(state) || !((dist = this.draconite.blockPosition().distSqr((Vec3i)pos)) < nearestDist)) continue;
                        nearestDist = dist;
                        nearest = pos;
                    }
                }
            }
            if (nearest != null) {
                this.targetPos = nearest;
                return true;
            }
            return false;
        }

        private boolean isLightOrRedstone(BlockState state) {
            if (state.getLightEmission(this.draconite.level(), BlockPos.ZERO) > 0) {
                return true;
            }
            return state.is(BlockTags.FEATURES);
        }

        public boolean canContinueToUse() {
            return this.targetPos != null && this.draconite.getTarget() == null && !this.draconite.isFlying();
        }

        public void tick() {
            if (this.targetPos == null) {
                return;
            }
            double dist = this.draconite.distanceToSqr((double)this.targetPos.getX() + 0.5, (double)this.targetPos.getY() + 0.5, (double)this.targetPos.getZ() + 0.5);
            if (dist > 9.0) {
                this.draconite.getNavigation().moveTo((double)this.targetPos.getX() + 0.5, (double)this.targetPos.getY(), (double)this.targetPos.getZ() + 0.5, 1.0);
                ++this.tryTicks;
                if (this.tryTicks > 200) {
                    this.targetPos = null;
                }
            } else {
                BlockState state;
                if (ForgeEventFactory.getMobGriefingEvent((Level)this.draconite.level(), this.draconite) && !(state = this.draconite.level().getBlockState(this.targetPos)).isAir()) {
                    this.draconite.level().destroyBlock(this.targetPos, false);
                }
                this.targetPos = null;
            }
        }

        public void stop() {
            this.targetPos = null;
            this.tryTicks = 0;
            this.draconite.getNavigation().stop();
        }

        public boolean canBeUsedWhileContinuing() {
            return true;
        }
    }

}
