/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.entity.ai;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityAIBlockResidue
extends EntityAIBase {
    private final EntityParasiteBase parent;
    private int ticks = 0;
    private int range;
    private int blockBreakCounter;

    public EntityAIBlockResidue(EntityParasiteBase ghast, int range) {
        this.parent = ghast;
        this.range = range;
        this.blockBreakCounter = 160;
    }

    public boolean func_75250_a() {
        return this.parent.func_70638_az() == null && !this.parent.func_70090_H() && this.parent.getGeneMod(8);
    }

    public void func_75251_c() {
        this.parent.setParasiteStatus(0);
        this.blockBreakCounter = 160;
    }

    public void func_75246_d() {
        if (this.blockBreakCounter > 0) {
            if (this.parent.field_70170_p.field_73012_v.nextInt(5) == 0) {
                --this.blockBreakCounter;
            }
        } else {
            --this.blockBreakCounter;
            if (this.blockBreakCounter == -1) {
                this.parent.setParasiteStatus(25);
                this.parent.func_70661_as().func_75499_g();
                this.parent.func_184185_a(SRPSounds.ADAPTED_V, 2.0f, 1.0f);
            }
            if (this.blockBreakCounter == -40) {
                this.parent.func_184185_a(SRPSounds.ADAPTED_V, 2.0f, 1.0f);
            }
            this.parent.particleStatus((byte)13);
            if (this.blockBreakCounter == -60) {
                double i1 = MathHelper.func_76128_c((double)(this.parent.field_70163_u + 0.1));
                double l1 = this.parent.field_70165_t;
                double i2 = this.parent.field_70161_v;
                World world = this.parent.field_70170_p;
                if (this.blockBreakCounter == -63) {
                    // empty if block
                }
                for (int k2 = -1 * this.range; k2 <= 1 * this.range; ++k2) {
                    for (int l2 = -1 * this.range; l2 <= 1 * this.range; ++l2) {
                        double i3 = l1 + (double)k2;
                        double l = i2 + (double)l2;
                        BlockPos blockpos = new BlockPos(i3, i1, l);
                        Block block = this.parent.field_70170_p.func_180495_p(blockpos).func_177230_c();
                        Block blockDown = this.parent.field_70170_p.func_180495_p(blockpos.func_177977_b()).func_177230_c();
                        if (block != Blocks.field_150350_a || blockDown == Blocks.field_150350_a || !world.func_180495_p(blockpos.func_177977_b()).func_185913_b() || blockDown == SRPBlocks.InfestedStain || this.parent.field_70170_p.field_73012_v.nextInt(2) != 0) continue;
                        this.parent.field_70170_p.func_175656_a(blockpos, SRPBlocks.InfestRemain.func_176203_a(1));
                    }
                }
            }
            if (this.blockBreakCounter == -100) {
                this.parent.setParasiteStatus(0);
                this.blockBreakCounter = 200;
            }
        }
    }
}

