package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.entity.ai.misc.EntityParasiteBase;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.init.SRPSoundTypes;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockParasiteMouth extends BlockBase {
   private static final float DAMAGE_PER_HIT = 1.0F;
   private static final int APPLY_COOLDOWN_TICKS = 10;
   private static final String NBT_KEY_NEXT_APPLY = "srp_mouth_next_apply";
   private static final DamageSource SRC_PARASITE_MOUTH = new DamageSource("parasite_mouth").func_76348_h();

   public BlockParasiteMouth(Material material, String name, float hardness, boolean creative, boolean tickRandom) {
      super(material, name, hardness, creative, tickRandom);
      this.func_180632_j(this.field_176227_L.func_177621_b());
      this.func_149672_a(SRPSoundTypes.FLESH);
   }

   public boolean func_149662_c(IBlockState state) {
      return false;
   }

   public boolean func_149686_d(IBlockState state) {
      return false;
   }

   public int func_180651_a(IBlockState state) {
      return this.func_176201_c(state);
   }

   @SideOnly(Side.CLIENT)
   public BlockRenderLayer func_180664_k() {
      return BlockRenderLayer.TRANSLUCENT;
   }

   public void func_176199_a(World world, BlockPos pos, Entity entity) {
      if (!world.field_72995_K && this.isOnTop(pos, entity)) {
         this.tryAffect(world, pos, entity);
      }

      super.func_176199_a(world, pos, entity);
   }

   public void func_180634_a(World world, BlockPos pos, IBlockState state, Entity entity) {
      if (!world.field_72995_K && this.isOnTop(pos, entity)) {
         this.tryAffect(world, pos, entity);
      }

      super.func_180634_a(world, pos, state, entity);
   }

   private boolean isOnTop(BlockPos pos, Entity e) {
      return e.func_174813_aQ().field_72338_b >= pos.func_177956_o() + 0.999
         && e.field_70165_t >= pos.func_177958_n()
         && e.field_70165_t < pos.func_177958_n() + 1
         && e.field_70161_v >= pos.func_177952_p()
         && e.field_70161_v < pos.func_177952_p() + 1;
   }

   private void tryAffect(World world, BlockPos pos, Entity entity) {
      if (entity instanceof EntityLivingBase) {
         if (!(entity instanceof EntityParasiteBase)) {
            if (!(entity instanceof EntityPlayer) || !((EntityPlayer)entity).field_71075_bZ.field_75098_d) {
               EntityLivingBase mob = (EntityLivingBase)entity;
               long now = world.func_82737_E();
               long nextAllowed = mob.getEntityData().func_74763_f("srp_mouth_next_apply");
               if (now >= nextAllowed) {
                  mob.getEntityData().func_74772_a("srp_mouth_next_apply", now + 10L);
                  mob.func_70097_a(SRC_PARASITE_MOUTH, 1.0F);
                  mob.func_70690_d(new PotionEffect(SRPPotions.CORRO_E, 100, 0, false, false));
                  SRPPotions.applyStackPotion(SRPPotions.VIRA_E, mob, 200, 1);
               }
            }
         }
      }
   }
}
