package com.srp.draconite.config;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;

/**
 * World-saved configuration for Draconite difficulty.
 * Persists the difficulty setting across server restarts.
 * Default difficulty is NORMAL (matching original SRP default).
 */
public class DraconiteConfig extends SavedData {
    private static final String DATA_NAME = "srpdraconite_config";
    private DraconiteDifficulty difficulty = DraconiteDifficulty.NORMAL;

    public DraconiteConfig() {
    }

    public static DraconiteConfig load(CompoundTag tag) {
        DraconiteConfig config = new DraconiteConfig();
        if (tag.contains("difficulty", 3)) { // Int tag type
            config.difficulty = DraconiteDifficulty.fromId(tag.getInt("difficulty"));
        }
        return config;
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.putInt("difficulty", difficulty.getId());
        return tag;
    }

    public DraconiteDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DraconiteDifficulty difficulty) {
        this.difficulty = difficulty;
        this.setDirty();
    }

    /**
     * Get the current difficulty - used by EntityDraconite to scale attributes.
     * Can also be accessed statically for spawn-time attribute registration.
     */
    public static DraconiteDifficulty getCurrentDifficulty() {
        // Default to NORMAL difficulty when world data is not yet available
        return DraconiteDifficulty.NORMAL;
    }
}
