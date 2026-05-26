/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.IEntityMultiPart
 *  net.minecraft.entity.MultiPartEntityPart
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.MobEffects
 *  net.minecraft.init.SoundEvents
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.SPacketEntityVelocity
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.stats.StatList
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumParticleTypes
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.ForgeHooks
 *  net.minecraftforge.event.ForgeEventFactory
 *  net.minecraftforge.event.entity.player.CriticalHitEvent
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package com.subspaceparasite.network;

import com.subspaceparasite.network.msg.AbstractPacket;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class HitboxHit
extends AbstractPacket<HitboxHit> {
    protected int eID;
    public float dmg;
    public boolean isAttack;

    public HitboxHit() {
    }

    public HitboxHit(EntityLiving entity, float damage, boolean isAttack) {
        this.eID = entity.func_145782_y();
        this.dmg = damage;
        this.isAttack = isAttack;
    }

    @Override
    public void clientSide(Minecraft client, HitboxHit message, EntityPlayer player, MessageContext messageContext) {
        Entity entity;
        if (player.field_70170_p != null && (entity = player.field_70170_p.func_73045_a(message.eID)) instanceof EntityLivingBase) {
            double dist = player.func_70032_d(entity);
            EntityLivingBase mob = (EntityLivingBase)entity;
            if (dist < 100.0) {
                if (message.dmg > 0.0f) {
                    this.attackTargetEntityWithCurrentItem((Entity)mob, player, this.dmg);
                } else {
                    mob.func_184230_a(player, EnumHand.MAIN_HAND);
                }
            }
        }
    }

    @Override
    public void serverSide(MinecraftServer minecraftServerInstance, HitboxHit message, EntityPlayerMP player, MessageContext messageContext) {
        Entity entity;
        if (player.field_70170_p != null && (entity = player.field_70170_p.func_73045_a(message.eID)) instanceof EntityLivingBase) {
            double dist = player.func_70032_d(entity);
            EntityLivingBase mob = (EntityLivingBase)entity;
            if (dist < 100.0) {
                if (message.dmg > 0.0f) {
                    this.attackTargetEntityWithCurrentItem((Entity)mob, (EntityPlayer)player, this.dmg);
                } else {
                    mob.func_184230_a((EntityPlayer)player, EnumHand.MAIN_HAND);
                }
            }
        }
    }

    public void fromBytes(ByteBuf buf) {
        this.eID = buf.readInt();
        this.dmg = buf.readFloat();
        this.isAttack = buf.readBoolean();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.eID);
        buf.writeFloat(this.dmg);
        buf.writeBoolean(this.isAttack);
    }

    public void attackTargetEntityWithCurrentItem(Entity targetEntity, EntityPlayer player, float dmg) {
        if (!ForgeHooks.onPlayerAttackTarget((EntityPlayer)player, (Entity)targetEntity)) {
            return;
        }
        if (targetEntity.func_70075_an() && !targetEntity.func_85031_j((Entity)player)) {
            float f = (float)player.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e() * dmg;
            float f1 = targetEntity instanceof EntityLivingBase ? EnchantmentHelper.func_152377_a((ItemStack)player.func_184614_ca(), (EnumCreatureAttribute)((EntityLivingBase)targetEntity).func_70668_bt()) : EnchantmentHelper.func_152377_a((ItemStack)player.func_184614_ca(), (EnumCreatureAttribute)EnumCreatureAttribute.UNDEFINED);
            float f2 = player.func_184825_o(0.5f);
            f1 *= f2;
            player.func_184821_cY();
            if ((f *= 0.2f + f2 * f2 * 0.8f) > 0.0f || f1 > 0.0f) {
                ItemStack itemstack;
                boolean flag = f2 > 0.9f;
                boolean flag1 = false;
                int i = 0;
                i += EnchantmentHelper.func_77501_a((EntityLivingBase)player);
                if (player.func_70051_ag() && flag) {
                    player.field_70170_p.func_184148_a(null, player.field_70165_t, player.field_70163_u, player.field_70161_v, SoundEvents.field_187721_dT, player.func_184176_by(), 1.0f, 1.0f);
                    ++i;
                    flag1 = true;
                }
                boolean flag2 = flag && player.field_70143_R > 0.0f && !player.field_70122_E && !player.func_70617_f_() && !player.func_70090_H() && !player.func_70644_a(MobEffects.field_76440_q) && !player.func_184218_aH() && targetEntity instanceof EntityLivingBase;
                flag2 = flag2 && !player.func_70051_ag();
                CriticalHitEvent hitResult = ForgeHooks.getCriticalHit((EntityPlayer)player, (Entity)targetEntity, (boolean)flag2, (float)(flag2 ? 1.5f : 1.0f));
                boolean bl = flag2 = hitResult != null;
                if (flag2) {
                    f *= hitResult.getDamageModifier();
                }
                f += f1;
                boolean flag3 = false;
                double d0 = player.field_70140_Q - player.field_70141_P;
                if (flag && !flag2 && !flag1 && player.field_70122_E && d0 < (double)player.func_70689_ay() && (itemstack = player.func_184586_b(EnumHand.MAIN_HAND)).func_77973_b() instanceof ItemSword) {
                    flag3 = true;
                }
                float f4 = 0.0f;
                boolean flag4 = false;
                int j = EnchantmentHelper.func_90036_a((EntityLivingBase)player);
                if (targetEntity instanceof EntityLivingBase) {
                    f4 = ((EntityLivingBase)targetEntity).func_110143_aJ();
                    if (j > 0 && !targetEntity.func_70027_ad()) {
                        flag4 = true;
                        targetEntity.func_70015_d(1);
                    }
                }
                double d1 = targetEntity.field_70159_w;
                double d2 = targetEntity.field_70181_x;
                double d3 = targetEntity.field_70179_y;
                boolean flag5 = targetEntity.func_70097_a(DamageSource.func_76365_a((EntityPlayer)player), f);
                if (flag5) {
                    IEntityMultiPart ientitymultipart;
                    if (i > 0) {
                        if (targetEntity instanceof EntityLivingBase) {
                            ((EntityLivingBase)targetEntity).func_70653_a((Entity)player, (float)i * 0.5f, (double)MathHelper.func_76126_a((float)(player.field_70177_z * ((float)Math.PI / 180))), (double)(-MathHelper.func_76134_b((float)(player.field_70177_z * ((float)Math.PI / 180)))));
                        } else {
                            targetEntity.func_70024_g((double)(-MathHelper.func_76126_a((float)(player.field_70177_z * ((float)Math.PI / 180))) * (float)i * 0.5f), 0.1, (double)(MathHelper.func_76134_b((float)(player.field_70177_z * ((float)Math.PI / 180))) * (float)i * 0.5f));
                        }
                        player.field_70159_w *= 0.6;
                        player.field_70179_y *= 0.6;
                        player.func_70031_b(false);
                    }
                    if (flag3) {
                        float f3 = 1.0f + EnchantmentHelper.func_191527_a((EntityLivingBase)player) * f;
                        for (EntityLivingBase entitylivingbase : player.field_70170_p.func_72872_a(EntityLivingBase.class, targetEntity.func_174813_aQ().func_72314_b(1.0, 0.25, 1.0))) {
                            if (entitylivingbase == player || entitylivingbase == targetEntity || player.func_184191_r((Entity)entitylivingbase) || !(player.func_70068_e((Entity)entitylivingbase) < 9.0)) continue;
                            entitylivingbase.func_70653_a((Entity)player, 0.4f, (double)MathHelper.func_76126_a((float)(player.field_70177_z * ((float)Math.PI / 180))), (double)(-MathHelper.func_76134_b((float)(player.field_70177_z * ((float)Math.PI / 180)))));
                            entitylivingbase.func_70097_a(DamageSource.func_76365_a((EntityPlayer)player), f3);
                        }
                        player.field_70170_p.func_184148_a(null, player.field_70165_t, player.field_70163_u, player.field_70161_v, SoundEvents.field_187730_dW, player.func_184176_by(), 1.0f, 1.0f);
                        player.func_184810_cG();
                    }
                    if (targetEntity instanceof EntityPlayerMP && targetEntity.field_70133_I) {
                        ((EntityPlayerMP)targetEntity).field_71135_a.func_147359_a((Packet)new SPacketEntityVelocity(targetEntity));
                        targetEntity.field_70133_I = false;
                        targetEntity.field_70159_w = d1;
                        targetEntity.field_70181_x = d2;
                        targetEntity.field_70179_y = d3;
                    }
                    if (flag2) {
                        player.field_70170_p.func_184148_a(null, player.field_70165_t, player.field_70163_u, player.field_70161_v, SoundEvents.field_187718_dS, player.func_184176_by(), 1.0f, 1.0f);
                        player.func_71009_b(targetEntity);
                    }
                    if (!flag2 && !flag3) {
                        if (flag) {
                            player.field_70170_p.func_184148_a(null, player.field_70165_t, player.field_70163_u, player.field_70161_v, SoundEvents.field_187727_dV, player.func_184176_by(), 1.0f, 1.0f);
                        } else {
                            player.field_70170_p.func_184148_a(null, player.field_70165_t, player.field_70163_u, player.field_70161_v, SoundEvents.field_187733_dX, player.func_184176_by(), 1.0f, 1.0f);
                        }
                    }
                    if (f1 > 0.0f) {
                        player.func_71047_c(targetEntity);
                    }
                    player.func_130011_c(targetEntity);
                    if (targetEntity instanceof EntityLivingBase) {
                        EnchantmentHelper.func_151384_a((EntityLivingBase)((EntityLivingBase)targetEntity), (Entity)player);
                    }
                    EnchantmentHelper.func_151385_b((EntityLivingBase)player, (Entity)targetEntity);
                    ItemStack itemstack1 = player.func_184614_ca();
                    Entity entity = targetEntity;
                    if (targetEntity instanceof MultiPartEntityPart && (ientitymultipart = ((MultiPartEntityPart)targetEntity).field_70259_a) instanceof EntityLivingBase) {
                        entity = (EntityLivingBase)ientitymultipart;
                    }
                    if (!itemstack1.func_190926_b() && entity instanceof EntityLivingBase) {
                        ItemStack beforeHitCopy = itemstack1.func_77946_l();
                        itemstack1.func_77961_a((EntityLivingBase)entity, player);
                        if (itemstack1.func_190926_b()) {
                            ForgeEventFactory.onPlayerDestroyItem((EntityPlayer)player, (ItemStack)beforeHitCopy, (EnumHand)EnumHand.MAIN_HAND);
                            player.func_184611_a(EnumHand.MAIN_HAND, ItemStack.field_190927_a);
                        }
                    }
                    if (targetEntity instanceof EntityLivingBase) {
                        float f5 = f4 - ((EntityLivingBase)targetEntity).func_110143_aJ();
                        player.func_71064_a(StatList.field_188111_y, Math.round(f5 * 10.0f));
                        if (j > 0) {
                            targetEntity.func_70015_d(j * 4);
                        }
                        if (player.field_70170_p instanceof WorldServer && f5 > 2.0f) {
                            int k = (int)((double)f5 * 0.5);
                            ((WorldServer)player.field_70170_p).func_175739_a(EnumParticleTypes.DAMAGE_INDICATOR, targetEntity.field_70165_t, targetEntity.field_70163_u + (double)(targetEntity.field_70131_O * 0.5f), targetEntity.field_70161_v, k, 0.1, 0.0, 0.1, 0.2, new int[0]);
                        }
                    }
                    player.func_71020_j(0.1f);
                } else {
                    player.field_70170_p.func_184148_a(null, player.field_70165_t, player.field_70163_u, player.field_70161_v, SoundEvents.field_187724_dU, player.func_184176_by(), 1.0f, 1.0f);
                    if (flag4) {
                        targetEntity.func_70066_B();
                    }
                }
            }
        }
    }
}

