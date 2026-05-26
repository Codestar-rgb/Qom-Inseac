package com.srp.draconite.config;

/**
 * SRP-style difficulty system for Draconite.
 * 
 * SRP has 4 difficulty levels that dynamically modify entity attributes:
 * - EASY (0): Base values, reduced aggression
 * - NORMAL (1): Standard challenge, default in original SRP  
 * - HARD (2): Enhanced stats, more aggressive behavior
 * - IMPOSSIBLE (3): Maximum challenge
 * 
 * The difficulty affects: health, armor, attack damage, knockback resistance,
 * adaptation rate, regen fraction, shadow clone behavior, cosmic hacking frequency,
 * damage cap, minimum damage, and skill cooldowns.
 */
public enum DraconiteDifficulty {
    EASY(0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
    NORMAL(1, 1.5, 1.3, 1.2, 1.1, 1.2, 1.3, 0.9, 1.5, 1.3),
    HARD(2, 2.0, 1.6, 1.5, 1.2, 1.5, 1.6, 0.8, 2.0, 1.6),
    IMPOSSIBLE(3, 2.5, 2.0, 2.0, 1.3, 2.0, 2.0, 0.7, 2.5, 2.0);

    private final int id;
    private final double healthMultiplier;
    private final double damageMultiplier;
    private final double armorMultiplier;
    private final double knockbackMultiplier;
    private final double adaptationRateMultiplier;
    private final double regenMultiplier;
    private final double skillCooldownMultiplier;
    private final double minDamageMultiplier;
    private final double damageCapMultiplier;

    DraconiteDifficulty(int id, double healthMult, double damageMult, double armorMult,
                        double knockbackMult, double adaptationMult, double regenMult,
                        double skillCooldownMult, double minDamageMult, double damageCapMult) {
        this.id = id;
        this.healthMultiplier = healthMult;
        this.damageMultiplier = damageMult;
        this.armorMultiplier = armorMult;
        this.knockbackMultiplier = knockbackMult;
        this.adaptationRateMultiplier = adaptationMult;
        this.regenMultiplier = regenMult;
        this.skillCooldownMultiplier = skillCooldownMult;
        this.minDamageMultiplier = minDamageMult;
        this.damageCapMultiplier = damageCapMult;
    }

    public int getId() { return id; }

    public double getHealthMultiplier() { return healthMultiplier; }
    public double getDamageMultiplier() { return damageMultiplier; }
    public double getArmorMultiplier() { return armorMultiplier; }
    public double getKnockbackMultiplier() { return knockbackMultiplier; }
    public double getAdaptationRateMultiplier() { return adaptationRateMultiplier; }
    public double getRegenMultiplier() { return regenMultiplier; }
    public double getSkillCooldownMultiplier() { return skillCooldownMultiplier; }
    public double getMinDamageMultiplier() { return minDamageMultiplier; }
    public double getDamageCapMultiplier() { return damageCapMultiplier; }

    public static DraconiteDifficulty fromId(int id) {
        for (DraconiteDifficulty d : values()) {
            if (d.id == id) return d;
        }
        return NORMAL; // Default to normal
    }

    /**
     * Get the scaled maximum health.
     * Base (easy): 525.0
     */
    public double getScaledMaxHealth() {
        return 525.0 * healthMultiplier;
    }

    /**
     * Get the scaled attack damage.
     * Base (easy): 210.0
     */
    public double getScaledAttackDamage() {
        return 210.0 * damageMultiplier;
    }

    /**
     * Get the scaled armor.
     * Base (easy): 30.0
     */
    public double getScaledArmor() {
        return 30.0 * armorMultiplier;
    }

    /**
     * Get the scaled knockback resistance.
     * Base (easy): 1.0
     */
    public double getScaledKnockbackResistance() {
        return Math.min(1.0, 1.0 * knockbackMultiplier);
    }

    /**
     * Get the scaled minimum damage.
     * Base (easy): 14.0
     */
    public float getScaledMinDamage() {
        return (float)(14.0 * minDamageMultiplier);
    }

    /**
     * Get the scaled damage cap.
     * Base (easy): 21.0
     */
    public float getScaledDamageCap() {
        return (float)(21.0 * damageCapMultiplier);
    }

    /**
     * Get the scaled regen fraction.
     * Base (easy): 0.0025
     */
    public double getScaledRegenFraction() {
        return 0.0025 * regenMultiplier;
    }

    /**
     * Get the adaptation learn chance.
     * Base (easy): 0.5
     * Higher difficulty = faster adaptation
     */
    public double getAdaptationLearnChance() {
        return Math.min(1.0, 0.5 * adaptationRateMultiplier);
    }

    /**
     * Get skill cooldown multiplier.
     * Lower = more frequent skills (harder)
     */
    public double getSkillCooldownMultiplier() {
        return skillCooldownMultiplier;
    }

    /**
     * Whether cosmic hacking is enabled at this difficulty.
     * Only on Normal and above.
     */
    public boolean hasCosmicHacking() {
        return this.ordinal() >= NORMAL.ordinal();
    }

    /**
     * Whether shadow clone system is active.
     * Active on all difficulties but more aggressive on harder ones.
     */
    public boolean hasShadowClones() {
        return true;
    }

    /**
     * Maximum number of adaptation damage types.
     */
    public int getAdaptationDamageTypeCap() {
        switch (this) {
            case EASY: return 3;
            case NORMAL: return 5;
            case HARD: return 7;
            case IMPOSSIBLE: return 10;
            default: return 5;
        }
    }

    /**
     * Maximum adaptation points per damage type.
     */
    public int getAdaptationPointCap() {
        switch (this) {
            case EASY: return 7;
            case NORMAL: return 10;
            case HARD: return 13;
            case IMPOSSIBLE: return 16;
            default: return 10;
        }
    }

    /**
     * Hack interval in ticks (lower = more frequent).
     */
    public int getHackInterval() {
        switch (this) {
            case EASY: return 40;
            case NORMAL: return 20;
            case HARD: return 15;
            case IMPOSSIBLE: return 10;
            default: return 20;
        }
    }

    /**
     * Maximum hack targets per cycle.
     */
    public int getHackMaxTargets() {
        switch (this) {
            case EASY: return 3;
            case NORMAL: return 5;
            case HARD: return 7;
            case IMPOSSIBLE: return 10;
            default: return 5;
        }
    }

    /**
     * Hack cooldown in ticks.
     */
    public int getHackCooldown() {
        return (int)(300 * skillCooldownMultiplier);
    }

    /**
     * Maximum hack cycles before cooldown.
     */
    public int getHackMaxCycles() {
        switch (this) {
            case EASY: return 3;
            case NORMAL: return 7;
            case HARD: return 10;
            case IMPOSSIBLE: return 15;
            default: return 7;
        }
    }
}
