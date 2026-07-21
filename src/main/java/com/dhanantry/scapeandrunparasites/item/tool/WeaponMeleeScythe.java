package com.dhanantry.scapeandrunparasites.item.tool;

import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import java.util.List;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WeaponMeleeScythe extends WeaponToolMeleeBase {
   public WeaponMeleeScythe(ToolMaterial material, String name, double attackspeed, float range, float attackD, boolean fear, byte id) {
      super(material, name, attackspeed, range, attackD, fear, id);
   }

   @Override
   public void func_77622_d(ItemStack stack, World worldIn, EntityPlayer playerIn) {
      super.func_77622_d(stack, worldIn, playerIn);
   }

   @Override
   public Item getNext() {
      return this == SRPItems.weapon_scythe ? SRPItems.weapon_scytheSentient : null;
   }

   @Override
   public boolean func_77644_a(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
      boolean flag = super.func_77644_a(stack, target, attacker);
      if (flag) {
         int aoe = 4;
         EntityPlayer player = (EntityPlayer)attacker;
         NBTTagCompound compound = stack.func_77978_p();
         if (compound == null) {
            compound = new NBTTagCompound();
         }

         if (this.calling) {
            aoe = 8;
            AxisAlignedBB axisalignedbb = new AxisAlignedBB(
                  player.field_70165_t,
                  player.field_70163_u,
                  player.field_70161_v,
                  player.field_70165_t + 1.0,
                  player.field_70163_u + 1.0,
                  player.field_70161_v + 1.0
               )
               .func_186662_g(aoe);

            for (EntityLivingBase mob : target.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb)) {
               if (mob != target && mob != player) {
                  this.hitTarget(stack, mob, player);
                  if (mob.func_110143_aJ() <= 0.0F) {
                     if (compound.func_74764_b("srpkills")) {
                        int key = (int)(compound.func_74762_e("srpkills") + target.func_110138_aP());
                        compound.func_74768_a("srpkills", key);
                     } else {
                        compound.func_74768_a("srpkills", (int)target.func_110138_aP());
                     }
                  }
               }
            }
         }

         AxisAlignedBB axisalignedbb = new AxisAlignedBB(
               target.field_70165_t,
               target.field_70163_u,
               target.field_70161_v,
               target.field_70165_t + 1.0,
               target.field_70163_u + 1.0,
               target.field_70161_v + 1.0
            )
            .func_186662_g(aoe);

         for (EntityLivingBase mobx : target.field_70170_p.func_72872_a(EntityLivingBase.class, axisalignedbb)) {
            if (mobx != target && mobx != player) {
               this.hitTarget(stack, mobx, player);
               if (mobx.func_110143_aJ() <= 0.0F) {
                  if (compound.func_74764_b("srpkills")) {
                     int key = (int)(compound.func_74762_e("srpkills") + target.func_110138_aP());
                     compound.func_74768_a("srpkills", key);
                  } else {
                     compound.func_74768_a("srpkills", (int)target.func_110138_aP());
                  }
               }
            }
         }
      }

      return flag;
   }

   private void hitTarget(ItemStack stack, Entity targetEntity, EntityPlayer attacker) {
      if (ForgeHooks.onPlayerAttackTarget(attacker, targetEntity)) {
         if (targetEntity.func_70075_an() && !targetEntity.func_85031_j(attacker)) {
            float f = (float)attacker.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
            float f1;
            if (targetEntity instanceof EntityLivingBase) {
               f1 = EnchantmentHelper.func_152377_a(attacker.func_184614_ca(), ((EntityLivingBase)targetEntity).func_70668_bt());
            } else {
               f1 = EnchantmentHelper.func_152377_a(attacker.func_184614_ca(), EnumCreatureAttribute.UNDEFINED);
            }

            float f2 = attacker.func_184825_o(0.5F);
            f *= 0.2F + f2 * f2 * 0.8F;
            f1 *= f2;
            attacker.func_184821_cY();
            if (f > 0.0F || f1 > 0.0F) {
               boolean flag = f2 > 0.9F;
               boolean flag1 = false;
               int i = 0;
               i += EnchantmentHelper.func_77501_a(attacker);
               if (attacker.func_70051_ag() && flag) {
                  attacker.field_70170_p
                     .func_184148_a(
                        null,
                        attacker.field_70165_t,
                        attacker.field_70163_u,
                        attacker.field_70161_v,
                        SoundEvents.field_187721_dT,
                        attacker.func_184176_by(),
                        1.0F,
                        1.0F
                     );
                  i++;
                  flag1 = true;
               }

               boolean flag2 = flag
                  && attacker.field_70143_R > 0.0F
                  && !attacker.field_70122_E
                  && !attacker.func_70617_f_()
                  && !attacker.func_70090_H()
                  && !attacker.func_70644_a(MobEffects.field_76440_q)
                  && !attacker.func_184218_aH()
                  && targetEntity instanceof EntityLivingBase;
               flag2 = flag2 && !attacker.func_70051_ag();
               CriticalHitEvent hitResult = ForgeHooks.getCriticalHit(attacker, targetEntity, flag2, flag2 ? 1.5F : 1.0F);
               flag2 = hitResult != null;
               if (flag2) {
                  f *= hitResult.getDamageModifier();
               }

               f += f1;
               boolean flag3 = false;
               double d0 = attacker.field_70140_Q - attacker.field_70141_P;
               if (flag && !flag2 && !flag1 && attacker.field_70122_E && d0 < attacker.func_70689_ay()) {
                  ItemStack itemstack = attacker.func_184586_b(EnumHand.MAIN_HAND);
                  flag3 = itemstack.func_77973_b() instanceof ItemSword;
               }

               float f4 = 0.0F;
               boolean flag4 = false;
               int j = EnchantmentHelper.func_90036_a(attacker);
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
               boolean flag5 = targetEntity.func_70097_a(DamageSource.func_76365_a(attacker), f);
               if (flag5) {
                  if (i > 0) {
                     if (targetEntity instanceof EntityLivingBase) {
                        ((EntityLivingBase)targetEntity)
                           .func_70653_a(
                              attacker,
                              i * 0.5F,
                              MathHelper.func_76126_a(attacker.field_70177_z * (float) (Math.PI / 180.0)),
                              -MathHelper.func_76134_b(attacker.field_70177_z * (float) (Math.PI / 180.0))
                           );
                     } else {
                        targetEntity.func_70024_g(
                           -MathHelper.func_76126_a(attacker.field_70177_z * (float) (Math.PI / 180.0)) * i * 0.5F,
                           0.1,
                           MathHelper.func_76134_b(attacker.field_70177_z * (float) (Math.PI / 180.0)) * i * 0.5F
                        );
                     }

                     attacker.field_70159_w *= 0.6;
                     attacker.field_70179_y *= 0.6;
                     attacker.func_70031_b(false);
                  }

                  if (flag3) {
                     float f3 = 1.0F + EnchantmentHelper.func_191527_a(attacker) * f;

                     for (EntityLivingBase entitylivingbase : attacker.field_70170_p
                        .func_72872_a(EntityLivingBase.class, targetEntity.func_174813_aQ().func_72314_b(1.0, 0.25, 1.0))) {
                        if (entitylivingbase != attacker
                           && entitylivingbase != targetEntity
                           && !attacker.func_184191_r(entitylivingbase)
                           && attacker.func_70068_e(entitylivingbase) < 9.0) {
                           entitylivingbase.func_70653_a(
                              attacker,
                              0.4F,
                              MathHelper.func_76126_a(attacker.field_70177_z * (float) (Math.PI / 180.0)),
                              -MathHelper.func_76134_b(attacker.field_70177_z * (float) (Math.PI / 180.0))
                           );
                           entitylivingbase.func_70097_a(DamageSource.func_76365_a(attacker), f3);
                        }
                     }

                     attacker.field_70170_p
                        .func_184148_a(
                           null,
                           attacker.field_70165_t,
                           attacker.field_70163_u,
                           attacker.field_70161_v,
                           SoundEvents.field_187730_dW,
                           attacker.func_184176_by(),
                           1.0F,
                           1.0F
                        );
                     attacker.func_184810_cG();
                  }

                  if (targetEntity instanceof EntityPlayerMP && targetEntity.field_70133_I) {
                     ((EntityPlayerMP)targetEntity).field_71135_a.func_147359_a(new SPacketEntityVelocity(targetEntity));
                     targetEntity.field_70133_I = false;
                     targetEntity.field_70159_w = d1;
                     targetEntity.field_70181_x = d2;
                     targetEntity.field_70179_y = d3;
                  }

                  if (flag2) {
                     attacker.field_70170_p
                        .func_184148_a(
                           null,
                           attacker.field_70165_t,
                           attacker.field_70163_u,
                           attacker.field_70161_v,
                           SoundEvents.field_187718_dS,
                           attacker.func_184176_by(),
                           1.0F,
                           1.0F
                        );
                     attacker.func_71009_b(targetEntity);
                  }

                  if (!flag2 && !flag3) {
                     if (flag) {
                        attacker.field_70170_p
                           .func_184148_a(
                              null,
                              attacker.field_70165_t,
                              attacker.field_70163_u,
                              attacker.field_70161_v,
                              SoundEvents.field_187727_dV,
                              attacker.func_184176_by(),
                              1.0F,
                              1.0F
                           );
                     } else {
                        attacker.field_70170_p
                           .func_184148_a(
                              null,
                              attacker.field_70165_t,
                              attacker.field_70163_u,
                              attacker.field_70161_v,
                              SoundEvents.field_187733_dX,
                              attacker.func_184176_by(),
                              1.0F,
                              1.0F
                           );
                     }
                  }

                  if (f1 > 0.0F) {
                     attacker.func_71047_c(targetEntity);
                  }

                  attacker.func_130011_c(targetEntity);
                  if (targetEntity instanceof EntityLivingBase) {
                     EnchantmentHelper.func_151384_a((EntityLivingBase)targetEntity, attacker);
                  }

                  EnchantmentHelper.func_151385_b(attacker, targetEntity);
                  ItemStack itemstack1 = attacker.func_184614_ca();
                  Entity entity = targetEntity;
                  if (targetEntity instanceof MultiPartEntityPart) {
                     IEntityMultiPart ientitymultipart = ((MultiPartEntityPart)targetEntity).field_70259_a;
                     if (ientitymultipart instanceof EntityLivingBase) {
                        entity = (EntityLivingBase)ientitymultipart;
                     }
                  }

                  if (!itemstack1.func_190926_b() && entity instanceof EntityLivingBase) {
                     ItemStack beforeHitCopy = itemstack1.func_77946_l();
                     stack.func_77972_a(1, attacker);
                     if (itemstack1.func_190926_b()) {
                        ForgeEventFactory.onPlayerDestroyItem(attacker, beforeHitCopy, EnumHand.MAIN_HAND);
                        attacker.func_184611_a(EnumHand.MAIN_HAND, ItemStack.field_190927_a);
                     }
                  }

                  if (targetEntity instanceof EntityLivingBase) {
                     float f5 = f4 - ((EntityLivingBase)targetEntity).func_110143_aJ();
                     attacker.func_71064_a(StatList.field_188111_y, Math.round(f5 * 10.0F));
                     if (j > 0) {
                        targetEntity.func_70015_d(j * 4);
                     }

                     if (attacker.field_70170_p instanceof WorldServer && f5 > 2.0F) {
                        int k = (int)(f5 * 0.5);
                        ((WorldServer)attacker.field_70170_p)
                           .func_175739_a(
                              EnumParticleTypes.DAMAGE_INDICATOR,
                              targetEntity.field_70165_t,
                              targetEntity.field_70163_u + targetEntity.field_70131_O * 0.5F,
                              targetEntity.field_70161_v,
                              k,
                              0.1,
                              0.0,
                              0.1,
                              0.2,
                              new int[0]
                           );
                     }
                  }

                  attacker.func_71020_j(0.1F);
               } else {
                  attacker.field_70170_p
                     .func_184148_a(
                        null,
                        attacker.field_70165_t,
                        attacker.field_70163_u,
                        attacker.field_70161_v,
                        SoundEvents.field_187724_dU,
                        attacker.func_184176_by(),
                        1.0F,
                        1.0F
                     );
                  if (flag4) {
                     targetEntity.func_70066_B();
                  }
               }
            }
         }
      }
   }

   @Override
   public EnumRarity func_77613_e(ItemStack stack) {
      return super.func_77613_e(stack);
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
      super.func_77624_a(stack, worldIn, tooltip, flagIn);
      tooltip.add(TextFormatting.YELLOW + I18n.func_135052_a("tootip.srparasites.weaponm." + this.idTool, new Object[0]));
      tooltip.add(TextFormatting.RED + I18n.func_135052_a("tootip.srparasites.weaponm." + this.idTool * 10, new Object[0]));
      if (this.calling && SRPConfigSystems.useScent) {
         tooltip.add(TextFormatting.BLACK + I18n.func_135052_a("tootip.srparasites.weaponm." + this.idTool * 100, new Object[0]));
      }
   }
}
