package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.client.ClientQlipShake;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import java.lang.reflect.Field;
import java.util.List;
import net.minecraft.advancements.Advancement;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBough extends ItemBase {
   private static final int USE_TICKS = 20;
   private static final int COOLDOWN_TICKS = 60;
   private static final float VOL_START = 3.5F;
   private static final float VOL_HURT = 3.0F;
   private static final float VOL_MID = 3.5F;
   private static final float VOL_DEATH = 3.5F;
   private static final String NBT_ACTIVE = "srp_bough_active";
   private static final String NBT_PREV_NODMG = "srp_bough_prev_nodmg";
   private static final String NBT_ADAPT_PLAYED = "srp_bough_adapt_played";
   private static final DamageSource SEPEKU_SRC = new DamageSource("sepeku").func_76348_h().func_151518_m().func_76359_i();
   private static final Field F_LAST_DAMAGE = findLastDamageField();

   public ItemBough(String name, int maxStack, byte id) {
      super(name, maxStack, id);
      this.func_185043_a(new ResourceLocation("stab"), new IItemPropertyGetter() {
         public float func_185085_a(ItemStack stack, World worldIn, EntityLivingBase entityIn) {
            if (entityIn == null) {
               return 0.0F;
            } else {
               return entityIn.func_184587_cr() && entityIn.func_184607_cu() == stack ? 1.0F : 0.0F;
            }
         }
      });
   }

   public EnumAction func_77661_b(ItemStack stack) {
      return EnumAction.BLOCK;
   }

   public int func_77626_a(ItemStack stack) {
      return 20;
   }

   public ActionResult<ItemStack> func_77659_a(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
      ItemStack stack = playerIn.func_184586_b(handIn);
      if (playerIn.func_184811_cZ().func_185141_a(this)) {
         return new ActionResult(EnumActionResult.FAIL, stack);
      } else {
         playerIn.func_184598_c(handIn);
         playerIn.func_184811_cZ().func_185145_a(this, 60);
         playerIn.getEntityData().func_74757_a("srp_bough_adapt_played", false);
         playerIn.func_184185_a(SRPSounds.ADAPTED_V, 3.5F, 1.0F);
         worldIn.func_184148_a(
            null, playerIn.field_70165_t, playerIn.field_70163_u, playerIn.field_70161_v, SoundEvents.field_187800_eb, SoundCategory.PLAYERS, 3.0F, 1.0F
         );
         if (worldIn.field_72995_K) {
            triggerShakeClient();
         }

         if (!worldIn.field_72995_K && playerIn instanceof EntityPlayerMP) {
            setHoldInvuln((EntityPlayerMP)playerIn, true);
         }

         return new ActionResult(EnumActionResult.SUCCESS, stack);
      }
   }

   public void onUsingTick(ItemStack stack, EntityLivingBase living, int count) {
      if (living instanceof EntityPlayer) {
         EntityPlayer player = (EntityPlayer)living;
         if (player.field_70170_p.field_72995_K) {
            if ((count & 1) == 0) {
               triggerShakeClient();
            }

            player.field_70159_w = 0.0;
            player.field_70179_y = 0.0;
         } else {
            player.func_70690_d(new PotionEffect(MobEffects.field_76421_d, 6, 255, false, false));
            player.field_70159_w = 0.0;
            player.field_70181_x = 0.0;
            player.field_70179_y = 0.0;
            player.field_70133_I = true;
            if (SRPPotions.RAGE_E != null) {
               player.func_70690_d(new PotionEffect(SRPPotions.RAGE_E, 6, 0, false, false));
            }

            if (count == 1 && !player.getEntityData().func_74767_n("srp_bough_adapt_played")) {
               player.func_184185_a(SRPSounds.ADAPTATION_P, 3.5F, 1.0F);
               player.getEntityData().func_74757_a("srp_bough_adapt_played", true);
            }

            if (player.field_70170_p instanceof WorldServer && (count & 1) == 0) {
               spawnBloodSpray((WorldServer)player.field_70170_p, player);
            }
         }
      }
   }

   public void func_77615_a(ItemStack stack, World worldIn, EntityLivingBase living, int timeLeft) {
      if (!worldIn.field_72995_K && living instanceof EntityPlayerMP) {
         EntityPlayerMP p = (EntityPlayerMP)living;
         setHoldInvuln(p, false);
         p.func_184589_d(MobEffects.field_76421_d);
         if (SRPPotions.RAGE_E != null) {
            p.func_184589_d(SRPPotions.RAGE_E);
         }
      }
   }

   public ItemStack func_77654_b(ItemStack stack, World worldIn, EntityLivingBase living) {
      if (worldIn.field_72995_K) {
         return stack;
      } else if (!(living instanceof EntityPlayerMP)) {
         return stack;
      } else {
         EntityPlayerMP p = (EntityPlayerMP)living;
         p.func_184589_d(MobEffects.field_76421_d);
         if (SRPPotions.RAGE_E != null) {
            p.func_184589_d(SRPPotions.RAGE_E);
         }

         p.func_184185_a(SRPSounds.ALAFHA_SHOOTING, 3.5F, 1.0F);
         p.func_184185_a(SRPSounds.ALAFHA_HURT, 3.5F, 1.0F);
         spawnReplacement(worldIn, p);
         if (worldIn instanceof WorldServer) {
            spawnBloodBurst((WorldServer)worldIn, p);
         }

         grantSepekuAdvancement(p);
         setHoldInvuln(p, false);
         if (!p.field_71075_bZ.field_75098_d) {
            stack.func_190918_g(1);
         }

         killPlayerSepeku(p);
         return stack;
      }
   }

   public void func_77663_a(ItemStack stack, World worldIn, Entity entityIn, int slot, boolean isSelected) {
      if (!worldIn.field_72995_K) {
         if (entityIn instanceof EntityPlayerMP) {
            EntityPlayerMP p = (EntityPlayerMP)entityIn;
            if (p.getEntityData().func_74767_n("srp_bough_active")) {
               boolean stillUsing = p.func_184587_cr() && !p.func_184607_cu().func_190926_b() && p.func_184607_cu().func_77973_b() == this;
               if (!stillUsing) {
                  setHoldInvuln(p, false);
                  p.func_184589_d(MobEffects.field_76421_d);
                  if (SRPPotions.RAGE_E != null) {
                     p.func_184589_d(SRPPotions.RAGE_E);
                  }
               }
            }
         }
      }
   }

   @SideOnly(Side.CLIENT)
   private static void triggerShakeClient() {
      ClientQlipShake.INSTANCE.triggerDelayed(20, 0, true, true, 4.0F);
   }

   private static void setHoldInvuln(EntityPlayerMP p, boolean enable) {
      if (enable) {
         p.getEntityData().func_74757_a("srp_bough_active", true);
         p.getEntityData().func_74757_a("srp_bough_prev_nodmg", p.field_71075_bZ.field_75102_a);
         p.field_71075_bZ.field_75102_a = true;
         p.func_71016_p();
      } else {
         if (!p.getEntityData().func_74767_n("srp_bough_active")) {
            return;
         }

         p.getEntityData().func_74757_a("srp_bough_active", false);
         boolean prev = p.getEntityData().func_74767_n("srp_bough_prev_nodmg");
         p.field_71075_bZ.field_75102_a = prev;
         p.func_71016_p();
      }
   }

   private static void spawnReplacement(World world, EntityPlayer player) {
      if (!world.field_72995_K) {
         if (SRPConfigWorld.boughSpawnReplacement) {
            String raw = SRPConfigWorld.boughReplacementMobId;
            if (raw != null) {
               raw = raw.trim();
               if (!raw.isEmpty() && !"none".equalsIgnoreCase(raw)) {
                  ResourceLocation id;
                  try {
                     if (raw.indexOf(58) >= 0) {
                        id = new ResourceLocation(raw);
                     } else {
                        id = new ResourceLocation("srparasites", raw);
                     }
                  } catch (Exception var5) {
                     id = new ResourceLocation("srparasites", "sim_adventurer");
                  }

                  Entity ent = EntityList.func_188429_b(id, world);
                  if (ent == null) {
                     id = new ResourceLocation("srparasites", "sim_adventurer");
                     ent = EntityList.func_188429_b(id, world);
                     if (ent == null) {
                        return;
                     }
                  }

                  ent.func_70012_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, player.field_70177_z, player.field_70125_A);
                  world.func_72838_d(ent);
               }
            }
         }
      }
   }

   private static void grantSepekuAdvancement(EntityPlayerMP p) {
      MinecraftServer srv = p.func_184102_h();
      if (srv != null) {
         ResourceLocation id = new ResourceLocation("srparasites", "sepeku");
         Advancement adv = srv.func_191949_aK().func_192778_a(id);
         if (adv != null) {
            p.func_192039_O().func_192750_a(adv, "done");
         }
      }
   }

   private static void spawnBloodSpray(WorldServer world, EntityPlayer player) {
      IBlockState red = Blocks.field_150451_bX.func_176223_P();
      int stateId = Block.func_176210_f(red);
      world.func_175739_a(
         EnumParticleTypes.BLOCK_CRACK,
         player.field_70165_t,
         player.field_70163_u + player.field_70131_O * 0.55,
         player.field_70161_v,
         12,
         0.25,
         0.2,
         0.25,
         0.18,
         new int[]{stateId}
      );
   }

   private static void spawnBloodBurst(WorldServer world, EntityPlayer player) {
      IBlockState red = Blocks.field_150451_bX.func_176223_P();
      int stateId = Block.func_176210_f(red);
      world.func_175739_a(
         EnumParticleTypes.BLOCK_CRACK,
         player.field_70165_t,
         player.field_70163_u + player.field_70131_O * 0.6,
         player.field_70161_v,
         90,
         0.35,
         0.35,
         0.35,
         0.35,
         new int[]{stateId}
      );
      world.func_175739_a(
         EnumParticleTypes.DAMAGE_INDICATOR,
         player.field_70165_t,
         player.field_70163_u + player.field_70131_O * 0.7,
         player.field_70161_v,
         25,
         0.25,
         0.25,
         0.25,
         0.1,
         new int[0]
      );
   }

   private static void killPlayerSepeku(EntityPlayerMP p) {
      boolean wasCreative = p.field_71075_bZ.field_75098_d;
      boolean wasNoDmg = p.field_71075_bZ.field_75102_a;
      p.field_71075_bZ.field_75098_d = false;
      p.field_71075_bZ.field_75102_a = false;
      p.func_71016_p();
      p.field_70172_ad = 0;
      p.field_70737_aN = 0;
      p.field_70738_aO = 0;
      resetLastDamage(p);
      p.func_110142_aN().func_94549_h();
      boolean applied = p.func_70097_a(SEPEKU_SRC, Float.MAX_VALUE);
      if (!applied || !p.field_70128_L && p.func_110143_aJ() > 0.0F) {
         try {
            float hpBefore = p.func_110143_aJ();
            p.func_110142_aN().func_94547_a(SEPEKU_SRC, Float.MAX_VALUE, hpBefore);
         } catch (Throwable var5) {
         }

         p.func_70606_j(0.0F);
         p.func_70645_a(SEPEKU_SRC);
         p.func_70106_y();
      }

      if (!p.field_70128_L && p.func_110143_aJ() > 0.0F) {
         p.field_71075_bZ.field_75098_d = wasCreative;
         p.field_71075_bZ.field_75102_a = wasNoDmg;
         p.func_71016_p();
      }
   }

   private static Field findLastDamageField() {
      try {
         Field f = ObfuscationReflectionHelper.findField(EntityLivingBase.class, "field_110153_bc");
         f.setAccessible(true);
         return f;
      } catch (Exception var2) {
         try {
            Field fx = ObfuscationReflectionHelper.findField(EntityLivingBase.class, "lastDamage");
            fx.setAccessible(true);
            return fx;
         } catch (Exception var1) {
            return null;
         }
      }
   }

   private static void resetLastDamage(EntityLivingBase e) {
      if (F_LAST_DAMAGE != null) {
         try {
            F_LAST_DAMAGE.setFloat(e, 0.0F);
         } catch (Exception var2) {
         }
      }
   }

   @SideOnly(Side.CLIENT)
   @Override
   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
      tooltip.add(TextFormatting.YELLOW + I18n.func_135052_a("tooltip.srparasites.bough.line1", new Object[0]));
      tooltip.add(TextFormatting.GOLD + I18n.func_135052_a("tooltip.srparasites.bough.line2", new Object[0]));
   }
}
