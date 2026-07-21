package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.entity.EntityParasiticScent;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPSoundTypes;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.item.ItemBlockVariant;
import com.dhanantry.scapeandrunparasites.network.SRPPacketParticle;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class BlockEvolutionLure extends BlockBase implements IMetaName {
   public static final PropertyEnum<BlockEvolutionLure.EnumType> VARIANT = PropertyEnum.func_177709_a("variant", BlockEvolutionLure.EnumType.class);

   public BlockEvolutionLure(Material material, String name, float hardness, boolean creative, boolean tickRandom) {
      super(material, name, hardness, creative, tickRandom);
      this.func_149672_a(SRPSoundTypes.LURE);
      this.func_149715_a(0.1F);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(VARIANT, BlockEvolutionLure.EnumType.ONE));
   }

   public boolean func_180639_a(
      World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ
   ) {
      if (!worldIn.field_72995_K && SRPConfigSystems.useEvolution) {
         ItemStack head = new ItemStack(playerIn.func_184582_a(EntityEquipmentSlot.MAINHAND).func_77973_b());
         if (head.func_77973_b() != Items.field_190931_a) {
            playerIn.func_146105_b(new TextComponentTranslation("message.srparasites.lure_empty_hand", new Object[0]), true);
            return false;
         }

         if (this.checkBlocks(worldIn, pos, (BlockEvolutionLure.EnumType)state.func_177229_b(VARIANT))) {
            if (SRPSaveData.get(worldIn, 2).getCooldown(worldIn, worldIn.field_73011_w.getDimension()) > 0) {
               playerIn.func_146105_b(new TextComponentTranslation("message.srparasites.lure_cooldown", new Object[0]), true);
               return false;
            }

            worldIn.func_184133_a(null, pos, SRPSounds.LURE_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            worldIn.func_175656_a(pos.func_177964_d(3).func_177965_g(3), Blocks.field_150350_a.func_176223_P());
            worldIn.func_175656_a(pos.func_177964_d(3).func_177985_f(3), Blocks.field_150350_a.func_176223_P());
            worldIn.func_175656_a(pos.func_177970_e(3).func_177965_g(3), Blocks.field_150350_a.func_176223_P());
            worldIn.func_175656_a(pos.func_177970_e(3).func_177985_f(3), Blocks.field_150350_a.func_176223_P());
            SRPMain.network
               .sendToAll(
                  new SRPPacketParticle(
                     pos.func_177964_d(3).func_177965_g(3).func_177958_n() + 0.5,
                     pos.func_177956_o(),
                     pos.func_177964_d(3).func_177965_g(3).func_177952_p() + 0.5,
                     0.5F,
                     0.5F,
                     (byte)2
                  )
               );
            SRPMain.network
               .sendToAll(
                  new SRPPacketParticle(
                     pos.func_177964_d(3).func_177985_f(3).func_177958_n() + 0.5,
                     pos.func_177956_o(),
                     pos.func_177964_d(3).func_177985_f(3).func_177952_p() + 0.5,
                     0.5F,
                     0.5F,
                     (byte)2
                  )
               );
            SRPMain.network
               .sendToAll(
                  new SRPPacketParticle(
                     pos.func_177970_e(3).func_177965_g(3).func_177958_n() + 0.5,
                     pos.func_177956_o(),
                     pos.func_177970_e(3).func_177965_g(3).func_177952_p() + 0.5,
                     0.5F,
                     0.5F,
                     (byte)2
                  )
               );
            SRPMain.network
               .sendToAll(
                  new SRPPacketParticle(
                     pos.func_177970_e(3).func_177985_f(3).func_177958_n() + 0.5,
                     pos.func_177956_o(),
                     pos.func_177970_e(3).func_177985_f(3).func_177952_p() + 0.5,
                     0.5F,
                     0.5F,
                     (byte)2
                  )
               );
            playerIn.func_146105_b(new TextComponentTranslation("message.srparasites.lurec", new Object[0]), true);
            SRPSaveData data = SRPSaveData.get(worldIn, 3);
            worldIn.func_184133_a(null, pos, SRPSounds.CARCASS_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            switch ((BlockEvolutionLure.EnumType)state.func_177229_b(VARIANT)) {
               case EIGHT:
                  data.setTotalKills(worldIn.field_73011_w.getDimension(), -SRPConfigSystems.luredValueEightCool, true, worldIn, true, 23);
                  break;
               case FIVE:
                  data.setTotalKills(worldIn.field_73011_w.getDimension(), -SRPConfigSystems.luredValueFiveCool, true, worldIn, true, 24);
                  break;
               case FOUR:
                  data.setTotalKills(worldIn.field_73011_w.getDimension(), -SRPConfigSystems.luredValueFourCool, true, worldIn, true, 25);
                  break;
               case ONE:
                  data.setTotalKills(worldIn.field_73011_w.getDimension(), -SRPConfigSystems.luredValueOneCool, true, worldIn, true, 26);
                  break;
               case SEVEN:
                  data.setTotalKills(worldIn.field_73011_w.getDimension(), -SRPConfigSystems.luredValueSevenCool, true, worldIn, true, 27);
                  break;
               case SIX:
                  data.setTotalKills(worldIn.field_73011_w.getDimension(), -SRPConfigSystems.luredValueSixCool, true, worldIn, true, 28);
                  break;
               case THREE:
                  data.setTotalKills(worldIn.field_73011_w.getDimension(), -SRPConfigSystems.luredValueThreeCool, true, worldIn, true, 29);
                  break;
               case TWO:
                  data.setTotalKills(worldIn.field_73011_w.getDimension(), -SRPConfigSystems.luredValueTwoCool, true, worldIn, true, 30);
                  break;
               case NINE:
                  data.setTotalKills(worldIn.field_73011_w.getDimension(), -SRPConfigSystems.luredValueNineCool, true, worldIn, true, 31);
                  break;
               case TEN:
                  data.setTotalKills(worldIn.field_73011_w.getDimension(), -SRPConfigSystems.luredValueTenCool, true, worldIn, true, 32);
            }

            if (!playerIn.field_71075_bZ.field_75102_a && SRPConfigSystems.useScent) {
               EntityParasiticScent sss = new EntityParasiticScent(worldIn);
               sss.setScentLevel(this.getLevelByPhase(state));
               sss.func_82149_j(playerIn);
               sss.setTargetToKill(playerIn, false);
               sss.setDieToE(true);
               sss.setCanFollow(true);
               worldIn.func_72838_d(sss);
            }

            worldIn.func_72942_c(new EntityLightningBolt(worldIn, pos.func_177958_n() + 0.5, pos.func_177956_o(), pos.func_177952_p() + 0.5, true));
         } else {
            playerIn.func_146105_b(new TextComponentTranslation("message.srparasites.lure_structure_fail", new Object[0]), true);
            SRPSaveData data = SRPSaveData.get(worldIn, 4);
            if (data.getEvolutionPhase(worldIn.field_73011_w.getDimension()) <= -1) {
               return false;
            }

            switch ((BlockEvolutionLure.EnumType)state.func_177229_b(VARIANT)) {
               case EIGHT:
                  data.setCooldown(SRPConfigSystems.luredValueEight, worldIn, worldIn.field_73011_w.getDimension(), true);
                  playerIn.func_146105_b(new TextComponentTranslation("message.srparasites.lureb", new Object[0]), true);
                  break;
               case FIVE:
                  data.setCooldown(SRPConfigSystems.luredValueFive, worldIn, worldIn.field_73011_w.getDimension(), true);
                  playerIn.func_146105_b(new TextComponentTranslation("message.srparasites.lureb", new Object[0]), true);
                  break;
               case FOUR:
                  data.setCooldown(SRPConfigSystems.luredValueFour, worldIn, worldIn.field_73011_w.getDimension(), true);
                  playerIn.func_146105_b(new TextComponentTranslation("message.srparasites.lureb", new Object[0]), true);
                  break;
               case ONE:
                  data.setCooldown(SRPConfigSystems.luredValueOne, worldIn, worldIn.field_73011_w.getDimension(), true);
                  playerIn.func_146105_b(new TextComponentTranslation("message.srparasites.lureb", new Object[0]), true);
                  break;
               case SEVEN:
                  data.setCooldown(SRPConfigSystems.luredValueSeven, worldIn, worldIn.field_73011_w.getDimension(), true);
                  playerIn.func_146105_b(new TextComponentTranslation("message.srparasites.lureb", new Object[0]), true);
                  break;
               case SIX:
                  data.setCooldown(SRPConfigSystems.luredValueSix, worldIn, worldIn.field_73011_w.getDimension(), true);
                  playerIn.func_146105_b(new TextComponentTranslation("message.srparasites.lureb", new Object[0]), true);
                  break;
               case THREE:
                  data.setCooldown(SRPConfigSystems.luredValueThree, worldIn, worldIn.field_73011_w.getDimension(), true);
                  playerIn.func_146105_b(new TextComponentTranslation("message.srparasites.lureb", new Object[0]), true);
                  break;
               case TWO:
                  data.setCooldown(SRPConfigSystems.luredValueTwo, worldIn, worldIn.field_73011_w.getDimension(), true);
                  playerIn.func_146105_b(new TextComponentTranslation("message.srparasites.lureb", new Object[0]), true);
                  break;
               case NINE:
                  data.setCooldown(SRPConfigSystems.luredValueNine, worldIn, worldIn.field_73011_w.getDimension(), true);
                  playerIn.func_146105_b(new TextComponentTranslation("message.srparasites.lureb", new Object[0]), true);
                  break;
               case TEN:
                  data.setCooldown(SRPConfigSystems.luredValueTen, worldIn, worldIn.field_73011_w.getDimension(), true);
                  playerIn.func_146105_b(new TextComponentTranslation("message.srparasites.lureb", new Object[0]), true);
            }
         }

         worldIn.func_175656_a(pos, Blocks.field_150350_a.func_176223_P());

         for (int i = 0; i <= 3; i++) {
            SRPMain.network.sendToAll(new SRPPacketParticle(pos.func_177958_n() + 0.5, pos.func_177956_o(), pos.func_177952_p() + 0.5, 0.5F, 0.5F, (byte)2));
         }
      }

      return super.func_180639_a(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
   }

   public int func_180651_a(IBlockState state) {
      return this.func_176201_c(state);
   }

   public void func_149666_a(CreativeTabs itemIn, NonNullList<ItemStack> items) {
      for (BlockEvolutionLure.EnumType variant : BlockEvolutionLure.EnumType.values()) {
         items.add(new ItemStack(this, 1, variant.ordinal()));
      }
   }

   public IBlockState func_176203_a(int meta) {
      return this.func_176223_P().func_177226_a(VARIANT, BlockEvolutionLure.EnumType.values()[meta]);
   }

   public int func_176201_c(IBlockState state) {
      return ((BlockEvolutionLure.EnumType)state.func_177229_b(VARIANT)).ordinal();
   }

   public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
      return new ItemStack(Item.func_150898_a(this), 1, this.func_176201_c(world.func_180495_p(pos)));
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{VARIANT});
   }

   @Override
   public Enum[] getVariants() {
      return BlockEvolutionLure.EnumType.values();
   }

   @Override
   public ItemBlock getItemBlock() {
      return new ItemBlockVariant(this);
   }

   @Override
   public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
      this.func_176208_a(world, pos, state, player);
      return world.func_180501_a(pos, Blocks.field_150350_a.func_176223_P(), world.field_72995_K ? 11 : 3);
   }

   public void func_180650_b(World worldIn, BlockPos pos, IBlockState state, Random rand) {
   }

   public void func_190948_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
      IBlockState data = this.func_176203_a(stack.func_77960_j());
      int i = 0;
      switch ((BlockEvolutionLure.EnumType)data.func_177229_b(VARIANT)) {
         case EIGHT:
            i = SRPConfigSystems.luredValueEight;
            break;
         case FIVE:
            i = SRPConfigSystems.luredValueFive;
            break;
         case FOUR:
            i = SRPConfigSystems.luredValueFour;
            break;
         case ONE:
            i = SRPConfigSystems.luredValueOne;
            break;
         case SEVEN:
            i = SRPConfigSystems.luredValueSeven;
            break;
         case SIX:
            i = SRPConfigSystems.luredValueSix;
            break;
         case THREE:
            i = SRPConfigSystems.luredValueThree;
            break;
         case TWO:
            i = SRPConfigSystems.luredValueTwo;
            break;
         case NINE:
            i = SRPConfigSystems.luredValueEight;
            break;
         case TEN:
            i = SRPConfigSystems.luredValueEight;
      }

      tooltip.add(I18n.func_135052_a("tooltip." + this.func_149739_a(), new Object[]{i}));
   }

   private int getLevelByPhase(IBlockState state) {
      switch ((BlockEvolutionLure.EnumType)state.func_177229_b(VARIANT)) {
         case EIGHT:
            return SRPConfigSystems.eightLevelDeploy;
         case FIVE:
            return SRPConfigSystems.fiveLevelDeploy;
         case FOUR:
            return SRPConfigSystems.fourLevelDeploy;
         case ONE:
            return SRPConfigSystems.oneLevelDeploy;
         case SEVEN:
            return SRPConfigSystems.sevenLevelDeploy;
         case SIX:
            return SRPConfigSystems.sixLevelDeploy;
         case THREE:
            return SRPConfigSystems.threeLevelDeploy;
         case TWO:
            return SRPConfigSystems.twoLevelDeploy;
         case NINE:
            return SRPConfigSystems.nineLevelDeploy;
         case TEN:
            return SRPConfigSystems.tenLevelDeploy;
         default:
            return 0;
      }
   }

   private boolean checkBlocks(World worldIn, BlockPos pos, BlockEvolutionLure.EnumType t) {
      return worldIn.func_180495_p(pos.func_177964_d(3).func_177965_g(3)).func_177230_c() == SRPBlocks.evolutionLure
         && worldIn.func_180495_p(pos.func_177964_d(3).func_177985_f(3)).func_177230_c() == SRPBlocks.evolutionLure
         && worldIn.func_180495_p(pos.func_177970_e(3).func_177965_g(3)).func_177230_c() == SRPBlocks.evolutionLure
         && worldIn.func_180495_p(pos.func_177970_e(3).func_177985_f(3)).func_177230_c() == SRPBlocks.evolutionLure;
   }

   public static enum EnumType implements IStringSerializable {
      ONE,
      TWO,
      THREE,
      FOUR,
      FIVE,
      SIX,
      SEVEN,
      EIGHT,
      NINE,
      TEN;

      public String func_176610_l() {
         return this.name().toLowerCase();
      }

      @Override
      public String toString() {
         return this.func_176610_l();
      }
   }
}
