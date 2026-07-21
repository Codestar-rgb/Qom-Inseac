package com.dhanantry.scapeandrunparasites.entity;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import net.minecraft.entity.IEntityMultiPart;

public interface IHitboxedEntity extends IEntityMultiPart {
   EntityParasiteBase getParent();
}
