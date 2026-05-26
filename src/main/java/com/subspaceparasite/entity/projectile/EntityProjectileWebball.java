/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.MobEffects
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.RayTraceResult$Type
 *  net.minecraft.world.World
 *  net.minecraftforge.event.ForgeEventFactory
 */
package com.subspaceparasite.entity.projectile;

import com.subspaceparasite.block.BlockWebBase;
import com.subspaceparasite.entity.projectile.EntitySPProjectile;
import com.subspaceparasite.init.SPBlocks;
import java.util.function.Consumer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

public class EntityProjectileWebball
extends EntitySPProjectile {
    private static final float WEB_BLIND_CHANCE = 0.3f;
    private static final int WEB_BLIND_TICKS = 60;
    private static final int WEB_BLIND_AMPLIFIER = 0;
    private byte type;

    public EntityProjectileWebball(World worldIn) {
        super(worldIn);
        this.func_70105_a(0.3f, 0.3f);
        this.type = 1;
    }

    public EntityProjectileWebball(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
        super(worldIn, shooter, accelX, accelY, accelZ);
        this.func_70105_a(0.3f, 0.3f);
        this.type = 1;
    }

    public EntityProjectileWebball(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ, byte t) {
        super(worldIn, shooter, accelX, accelY, accelZ);
        this.func_70105_a(0.3f, 0.3f);
        this.type = t;
    }

    protected EnumParticleTypes func_184563_j() {
        return EnumParticleTypes.EXPLOSION_NORMAL;
    }

    protected void func_70227_a(RayTraceResult result) {
        if (this.field_70170_p.field_72995_K) {
            return;
        }
        boolean griefing = ForgeEventFactory.getMobGriefingEvent((World)this.field_70170_p, (Entity)this);
        Consumer<BlockPos> tryPlaceWeb = pos -> {
            if (griefing && this.field_70170_p.func_175623_d(pos)) {
                this.field_70170_p.func_180501_a(pos, Blocks.field_150321_G.func_176223_P(), 3);
            }
        };
        if (result.field_72308_g instanceof EntityLivingBase) {
            EntityLivingBase target = (EntityLivingBase)result.field_72308_g;
            if (target instanceof EntityPlayer && !target.func_70644_a(MobEffects.field_76440_q) && this.field_70146_Z.nextFloat() < 0.3f) {
                target.func_70690_d(new PotionEffect(MobEffects.field_76440_q, 60, 0, false, true));
            }
            BlockPos feet = new BlockPos(target.field_70165_t, Math.floor(target.func_174813_aQ().field_72338_b), target.field_70161_v);
            tryPlaceWeb.accept(feet);
        } else if (result.field_72313_a == RayTraceResult.Type.BLOCK) {
            BlockPos placePos = result.func_178782_a().func_177972_a(result.field_178784_b);
            tryPlaceWeb.accept(placePos);
        }
        this.field_70170_p.func_72960_a((Entity)this, (byte)3);
        this.func_70106_y();
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70173_aa > 60) {
            this.setWebsAround();
            this.func_70106_y();
        }
    }

    private void setWebsAround() {
        int totalWebs = this.field_70146_Z.nextInt(3) + 1;
        int[] positionss = new int[]{-1, 0, 1};
        block5: for (int i = 1; i <= totalWebs; ++i) {
            int poz;
            int poy;
            int pox = positionss[this.field_70146_Z.nextInt(3)];
            if (this.field_70170_p.func_180495_p(new BlockPos(this.field_70165_t + (double)pox, this.field_70163_u + (double)(poy = positionss[this.field_70146_Z.nextInt(3)]), this.field_70161_v + (double)(poz = positionss[this.field_70146_Z.nextInt(3)]))).func_177230_c() != Blocks.field_150350_a) continue;
            switch (this.type) {
                case 1: {
                    this.field_70170_p.func_175656_a(new BlockPos(this.field_70165_t + (double)pox, this.field_70163_u + (double)poy, this.field_70161_v + (double)poz), SPBlocks.SPWeb.func_176223_P());
                    continue block5;
                }
                case 2: {
                    this.field_70170_p.func_175656_a(new BlockPos(this.field_70165_t + (double)pox, this.field_70163_u + (double)poy, this.field_70161_v + (double)poz), SPBlocks.SPWeb.func_176223_P().func_177226_a(BlockWebBase.VARIANT, (Comparable)((Object)BlockWebBase.EnumType.TWO)));
                    continue block5;
                }
                case 3: {
                    this.field_70170_p.func_175656_a(new BlockPos(this.field_70165_t + (double)pox, this.field_70163_u + (double)poy, this.field_70161_v + (double)poz), SPBlocks.SPWeb.func_176223_P().func_177226_a(BlockWebBase.VARIANT, (Comparable)((Object)BlockWebBase.EnumType.THREE)));
                }
            }
        }
    }
}

