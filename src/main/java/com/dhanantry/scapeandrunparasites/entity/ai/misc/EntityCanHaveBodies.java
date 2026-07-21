package com.dhanantry.scapeandrunparasites.entity.ai.misc;

import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public interface EntityCanHaveBodies {
   void setFollowing(EntityCanHaveBodies var1);

   int getBodyLength();

   EntityCanHaveBodies getAnotherBody(World var1);

   void copyCopy(EntityCanHaveBodies var1);

   void onSpawn(DifficultyInstance var1, IEntityLivingData var2);

   Entity getEntity();

   void setCanF(boolean var1);

   boolean getCanF();

   byte getBodyNumber();

   void setBodyNumber(int var1);

   boolean getBodyTail();

   void setBodyTail(boolean var1);

   UUID getFollowing();

   boolean getDigging();

   void setDigging(boolean var1);

   void setTargetPos(BlockPos var1);

   BlockPos getTargetPos();

   int getBodiesT();

   void setBodiesT(int var1);

   float getDigModel();

   void bodyPartEffect();

   double getKillPoints();

   EntityParasiteBase getEvolution(World var1);

   EntityParasiteBase getHead();

   Entity getFather(UUID var1);

   void handleDigging();
}
