/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.DifficultyInstance
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.entity.ai.misc;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public interface EntityCanHaveBodies {
    public void setFollowing(EntityCanHaveBodies var1);

    public int getBodyLength();

    public EntityCanHaveBodies getAnotherBody(World var1);

    public void copyCopy(EntityCanHaveBodies var1);

    public void onSpawn(DifficultyInstance var1, IEntityLivingData var2);

    public Entity getEntity();

    public void setCanF(boolean var1);

    public boolean getCanF();

    public byte getBodyNumber();

    public void setBodyNumber(int var1);

    public boolean getBodyTail();

    public void setBodyTail(boolean var1);

    public UUID getFollowing();

    public boolean getDigging();

    public void setDigging(boolean var1);

    public void setTargetPos(BlockPos var1);

    public BlockPos getTargetPos();

    public int getBodiesT();

    public void setBodiesT(int var1);

    public float getDigModel();

    public void bodyPartEffect();

    public double getKillPoints();

    public EntityParasiteBase getEvolution(World var1);

    public EntityParasiteBase getHead();

    public Entity getFather(UUID var1);

    public void handleDigging();
}

