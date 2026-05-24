/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItemFrame
 *  net.minecraft.item.IItemPropertyGetter
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.item;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.network.SPPacketCompass;
import com.subspaceparasite.world.SPWorldData;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ItemCompass
extends Item {
    protected byte type;

    public ItemCompass(String n, int t) {
        this.setRegistryName(n);
        this.func_77655_b("subspaceparasite." + n);
        this.func_77637_a(SPMain.SP_CREATIVETAB);
        this.type = (byte)t;
        this.func_185043_a(new ResourceLocation("angle"), new IItemPropertyGetter(){
            @SideOnly(value=Side.CLIENT)
            double rotation;
            @SideOnly(value=Side.CLIENT)
            double rota;
            @SideOnly(value=Side.CLIENT)
            long lastUpdateTick;

            @SideOnly(value=Side.CLIENT)
            public float func_185085_a(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                double d0;
                EntityLivingBase entity;
                if (entityIn == null && !stack.func_82839_y()) {
                    return 0.0f;
                }
                boolean flag = entityIn != null;
                Object object = entity = flag ? entityIn : stack.func_82836_z();
                if (worldIn == null) {
                    worldIn = entity.field_70170_p;
                }
                if (worldIn.field_73011_w.func_76569_d()) {
                    double d1 = flag ? (double)entity.field_70177_z : this.getFrameRotation((EntityItemFrame)entity);
                    d1 = MathHelper.func_191273_b((double)(d1 / 360.0), (double)1.0);
                    double d2 = this.getSpawnToAngle(worldIn, (Entity)entity) / (Math.PI * 2);
                    d0 = 0.5 - (d1 - 0.25 - d2);
                } else {
                    d0 = Math.random();
                }
                if (flag) {
                    d0 = this.wobble(worldIn, d0);
                }
                return MathHelper.func_188207_b((float)((float)d0), (float)1.0f);
            }

            @SideOnly(value=Side.CLIENT)
            private double wobble(World worldIn, double ddd) {
                if (worldIn.func_82737_E() != this.lastUpdateTick) {
                    this.lastUpdateTick = worldIn.func_82737_E();
                    double d0 = ddd - this.rotation;
                    d0 = MathHelper.func_191273_b((double)(d0 + 0.5), (double)1.0) - 0.5;
                    this.rota += d0 * 0.1;
                    this.rota *= 0.8;
                    this.rotation = MathHelper.func_191273_b((double)(this.rotation + this.rota), (double)1.0);
                }
                return this.rotation;
            }

            @SideOnly(value=Side.CLIENT)
            private double getFrameRotation(EntityItemFrame en) {
                return MathHelper.func_188209_b((int)(180 + en.field_174860_b.func_176736_b() * 90));
            }

            @SideOnly(value=Side.CLIENT)
            private double getSpawnToAngle(World world, Entity en) {
                if (ItemCompass.this.getOri() == null) {
                    return world.field_73012_v.nextDouble();
                }
                return Math.atan2((double)ItemCompass.this.getOri().func_177952_p() - en.field_70161_v, (double)ItemCompass.this.getOri().func_177958_n() - en.field_70165_t);
            }
        });
    }

    public void func_77663_a(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!worldIn.field_72995_K && entityIn.field_70173_aa % 20 == 0) {
            SPWorldData aaa = SPWorldData.get(worldIn);
            BlockPos posss = this.getOrigin(entityIn, worldIn);
            if (posss != null) {
                SPMain.network.sendToAll((IMessage)new SPPacketCompass(posss.func_177958_n(), posss.func_177956_o(), posss.func_177952_p(), this.type));
            } else {
                SPMain.network.sendToAll((IMessage)new SPPacketCompass(worldIn.field_73012_v.nextInt(), worldIn.field_73012_v.nextInt(), worldIn.field_73012_v.nextInt(), this.type));
            }
        }
    }

    public abstract BlockPos getOrigin(Entity var1, World var2);

    public abstract BlockPos getOri();
}

