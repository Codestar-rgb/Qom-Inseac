package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.item.ItemBlockVariant;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.oredict.OreDictionary;

public class BlockInfestedBush extends BlockBush implements IMetaName, IShearable {
   protected static final AxisAlignedBB TALL_GRASS_AABB = new AxisAlignedBB(0.099999994F, 0.0, 0.099999994F, 0.9F, 0.8F, 0.9F);
   protected static final AxisAlignedBB REED_AABB = new AxisAlignedBB(0.125, 0.0, 0.125, 0.875, 1.0, 0.875);
   public static final PropertyBool NODE = PropertyBool.func_177716_a("node");
   public static final PropertyBool END = PropertyBool.func_177716_a("end");
   public static final PropertyEnum<BlockInfestedBush.EnumType> VARIANT = PropertyEnum.func_177709_a("variant", BlockInfestedBush.EnumType.class);
   private static final String[] RAW_MEAT_KEYS = new String[]{"listAllmeatraw", "listAllfishraw", "foodMeatRaw", "foodFishRaw"};

   public BlockInfestedBush(String name, float hardness) {
      super(Material.field_151582_l);
      this.setRegistryName(name);
      this.func_149663_c("srparasites." + name);
      this.func_149672_a(SoundType.field_185850_c);
      this.func_149647_a(SRPMain.SRP_CREATIVETAB);
      this.func_149711_c(hardness);
      this.func_180632_j(
         this.field_176227_L
            .func_177621_b()
            .func_177226_a(VARIANT, BlockInfestedBush.EnumType.INFECTED)
            .func_177226_a(NODE, Boolean.FALSE)
            .func_177226_a(END, Boolean.FALSE)
      );
      SRPBlocks.SRP_BLOCKS.add(this);
      Item itemBlock = this.getItemBlock();
      SRPItems.SRP_ITEMS.add(itemBlock.setRegistryName(Objects.requireNonNull(this.getRegistryName())));
   }

   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) {
      BlockInfestedBush.EnumType variant = (BlockInfestedBush.EnumType)state.func_177229_b(VARIANT);
      return variant != BlockInfestedBush.EnumType.SPINE && variant != BlockInfestedBush.EnumType.VINE ? TALL_GRASS_AABB : REED_AABB;
   }

   public boolean func_180671_f(World worldIn, BlockPos pos, IBlockState state) {
      BlockPos down = pos.func_177977_b();
      IBlockState below = worldIn.func_180495_p(down);
      Block belowBlock = below.func_177230_c();
      String variantName = null;

      for (IProperty<?> p : below.func_177227_a()) {
         if ("variant".equals(p.func_177701_a())) {
            Object val = below.func_177229_b(p);
            if (val instanceof IStringSerializable) {
               variantName = ((IStringSerializable)val).func_176610_l();
            } else if (val != null) {
               variantName = String.valueOf(val);
            }
            break;
         }
      }

      if ((belowBlock instanceof BlockInfestedBush || belowBlock instanceof BlockParasiteBush) && ("spine".equals(variantName) || "vine".equals(variantName))) {
         BlockPos base = down;

         for (int guard = 0; guard < 64; guard++) {
            IBlockState cur = worldIn.func_180495_p(base);
            Block curBlock = cur.func_177230_c();
            if (!(curBlock instanceof BlockInfestedBush) && !(curBlock instanceof BlockParasiteBush)) {
               break;
            }

            base = base.func_177977_b();
         }

         IBlockState support = worldIn.func_180495_p(base);
         Block supportBlock = support.func_177230_c();
         ResourceLocation id = supportBlock.getRegistryName();
         if (id == null) {
            return false;
         } else {
            String domain = id.func_110624_b();
            String path = id.func_110623_a();
            if (!"srparasites".equals(domain)) {
               return false;
            } else {
               return !"bloodyice".equals(path) && !"ashen_glass".equals(path) ? support.isSideSolid(worldIn, base, EnumFacing.UP) : false;
            }
         }
      } else {
         IBlockState support = worldIn.func_180495_p(down);
         Block supportBlock = support.func_177230_c();
         ResourceLocation id = supportBlock.getRegistryName();
         if (id == null) {
            return false;
         } else {
            String domain = id.func_110624_b();
            String path = id.func_110623_a();
            if (!"srparasites".equals(domain)) {
               return false;
            } else {
               return !"bloodyice".equals(path) && !"ashen_glass".equals(path) ? support.isSideSolid(worldIn, down, EnumFacing.UP) : false;
            }
         }
      }
   }

   private static boolean isRawMeat(ItemStack stack) {
      if (stack != null && !stack.func_190926_b()) {
         Item it = stack.func_77973_b();
         if (it != Items.field_151082_bd
            && it != Items.field_151147_al
            && it != Items.field_151076_bf
            && it != Items.field_179561_bm
            && it != Items.field_179558_bo
            && it != Items.field_151115_aP) {
            int[] ids = OreDictionary.getOreIDs(stack);

            for (int id : ids) {
               String name = OreDictionary.getOreName(id);

               for (String key : RAW_MEAT_KEYS) {
                  if (key.equalsIgnoreCase(name)) {
                     return true;
                  }
               }
            }

            return false;
         } else {
            return true;
         }
      } else {
         return false;
      }
   }

   public boolean func_180639_a(
      World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ
   ) {
      ItemStack held = player.func_184586_b(hand);
      if (isRawMeat(held)) {
         if (!world.field_72995_K) {
            int meta = this.func_176201_c(state);
            Item itemBlock = Item.func_150898_a(this);
            if (itemBlock != null) {
               func_180635_a(world, pos.func_177984_a(), new ItemStack(itemBlock, 1, meta));
            }

            if (!player.field_71075_bZ.field_75098_d) {
               held.func_190918_g(1);
            }

            world.func_175718_b(2005, pos, 0);
            player.func_184811_cZ().func_185145_a(held.func_77973_b(), 10);
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity) {
      if (!SRPConfigWorld.bushClimbingEnabled) {
         return false;
      } else if (entity instanceof EntityPlayer) {
         BlockInfestedBush.EnumType type = (BlockInfestedBush.EnumType)state.func_177229_b(VARIANT);
         return type == BlockInfestedBush.EnumType.SPINE;
      } else {
         return false;
      }
   }

   public boolean func_176196_c(World worldIn, BlockPos pos) {
      IBlockState below = worldIn.func_180495_p(pos.func_177977_b());
      if (!(below.func_177230_c() instanceof BlockInfestedBush) && !(below.func_177230_c() instanceof BlockParasiteBush)) {
         IBlockState sup = worldIn.func_180495_p(pos.func_177977_b());
         ResourceLocation id = sup.func_177230_c().getRegistryName();
         if (id == null) {
            return false;
         } else {
            String domain = id.func_110624_b();
            String path = id.func_110623_a();
            if (!"srparasites".equals(domain)) {
               return false;
            } else {
               return !"bloodyice".equals(path) && !"ashen_glass".equals(path) ? sup.isSideSolid(worldIn, pos.func_177977_b(), EnumFacing.UP) : false;
            }
         }
      } else {
         BlockInfestedBush.EnumType v = (BlockInfestedBush.EnumType)below.func_177229_b(VARIANT);
         if (v != BlockInfestedBush.EnumType.SPINE && v != BlockInfestedBush.EnumType.VINE) {
            return false;
         } else {
            BlockPos base = pos.func_177977_b();

            for (int guard = 0;
               (
                     worldIn.func_180495_p(base).func_177230_c() instanceof BlockInfestedBush
                        || worldIn.func_180495_p(base).func_177230_c() instanceof BlockParasiteBush
                  )
                  && guard < 64;
               guard++
            ) {
               base = base.func_177977_b();
            }

            IBlockState sup = worldIn.func_180495_p(base);
            ResourceLocation id = sup.func_177230_c().getRegistryName();
            if (id == null) {
               return false;
            } else {
               String domain = id.func_110624_b();
               String path = id.func_110623_a();
               if (!"srparasites".equals(domain)) {
                  return false;
               } else {
                  return !"bloodyice".equals(path) && !"ashen_glass".equals(path) ? sup.isSideSolid(worldIn, base, EnumFacing.UP) : false;
               }
            }
         }
      }
   }

   public boolean func_176198_a(World worldIn, BlockPos pos, EnumFacing side) {
      if (side != EnumFacing.UP) {
         return false;
      } else {
         IBlockState below = worldIn.func_180495_p(pos.func_177977_b());
         if (!(below.func_177230_c() instanceof BlockInfestedBush) && !(below.func_177230_c() instanceof BlockParasiteBush)) {
            IBlockState sup = worldIn.func_180495_p(pos.func_177977_b());
            ResourceLocation id = sup.func_177230_c().getRegistryName();
            if (id == null) {
               return false;
            } else {
               String domain = id.func_110624_b();
               String path = id.func_110623_a();
               if (!"srparasites".equals(domain)) {
                  return false;
               } else {
                  return !"bloodyice".equals(path) && !"ashen_glass".equals(path) ? sup.isSideSolid(worldIn, pos.func_177977_b(), EnumFacing.UP) : false;
               }
            }
         } else {
            BlockInfestedBush.EnumType v = (BlockInfestedBush.EnumType)below.func_177229_b(VARIANT);
            if (v != BlockInfestedBush.EnumType.SPINE && v != BlockInfestedBush.EnumType.VINE) {
               return false;
            } else {
               BlockPos base = pos.func_177977_b();

               for (int guard = 0;
                  (
                        worldIn.func_180495_p(base).func_177230_c() instanceof BlockInfestedBush
                           || worldIn.func_180495_p(base).func_177230_c() instanceof BlockParasiteBush
                     )
                     && guard < 64;
                  guard++
               ) {
                  base = base.func_177977_b();
               }

               IBlockState sup = worldIn.func_180495_p(base);
               ResourceLocation id = sup.func_177230_c().getRegistryName();
               if (id == null) {
                  return false;
               } else {
                  String domain = id.func_110624_b();
                  String path = id.func_110623_a();
                  if (!"srparasites".equals(domain)) {
                     return false;
                  } else {
                     return !"bloodyice".equals(path) && !"ashen_glass".equals(path) ? sup.isSideSolid(worldIn, base, EnumFacing.UP) : false;
                  }
               }
            }
         }
      }
   }

   protected boolean func_185514_i(IBlockState state) {
      ResourceLocation id = state.func_177230_c().getRegistryName();
      if (id == null) {
         return false;
      } else {
         String domain = id.func_110624_b();
         String path = id.func_110623_a();
         if (!"srparasites".equals(domain)) {
            return false;
         } else {
            return !"bloodyice".equals(path) && !"ashen_glass".equals(path) ? state.func_185914_p() : false;
         }
      }
   }

   protected boolean checkBush(IBlockState state) {
      return !(state.func_177230_c() instanceof BlockBase) ? false : state.func_185917_h();
   }

   protected boolean checkBushV(IBlockState state) {
      if (state.func_185917_h() && state.func_177230_c() instanceof BlockBase) {
         return true;
      } else {
         return state.func_177230_c() != this
            ? false
            : state.func_177229_b(VARIANT) == BlockInfestedBush.EnumType.SPINE || state.func_177229_b(VARIANT) == BlockInfestedBush.EnumType.VINE;
      }
   }

   public void func_189540_a(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
      super.func_189540_a(state, world, pos, blockIn, fromPos);
      if (fromPos.equals(pos.func_177977_b()) || this.isVariant(state, "spine")) {
         world.func_184138_a(pos, state, state, 3);
         world.func_175704_b(pos, pos);
      }
   }

   protected void func_176475_e(World worldIn, BlockPos pos, IBlockState state) {
      if (!this.func_180671_f(worldIn, pos, state)) {
         this.func_176226_b(worldIn, pos, state, 0);
         worldIn.func_180501_a(pos, Blocks.field_150350_a.func_176223_P(), 3);
      }
   }

   public void func_180634_a(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
      super.func_180634_a(worldIn, pos, state, entityIn);
   }

   public void func_176199_a(World worldIn, BlockPos pos, Entity entityIn) {
      super.func_176199_a(worldIn, pos, entityIn);
   }

   public boolean func_176200_f(IBlockAccess worldIn, BlockPos pos) {
      return false;
   }

   public Item func_180660_a(IBlockState state, Random rand, int fortune) {
      return null;
   }

   public void func_180657_a(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
      super.func_180657_a(worldIn, player, pos, state, te, stack);
   }

   public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
      return false;
   }

   public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
      return false;
   }

   public int func_180651_a(IBlockState state) {
      return this.func_176201_c(state);
   }

   public void func_149666_a(CreativeTabs itemIn, NonNullList<ItemStack> items) {
      for (BlockInfestedBush.EnumType variant : BlockInfestedBush.EnumType.values()) {
         items.add(new ItemStack(this, 1, variant.ordinal()));
      }
   }

   public IBlockState func_176203_a(int meta) {
      return this.func_176223_P().func_177226_a(VARIANT, BlockInfestedBush.EnumType.values()[meta]);
   }

   public int func_176201_c(IBlockState state) {
      return ((BlockInfestedBush.EnumType)state.func_177229_b(VARIANT)).ordinal();
   }

   public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
      return new ItemStack(Item.func_150898_a(this), 1, this.func_176201_c(world.func_180495_p(pos)));
   }

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{END, NODE, VARIANT});
   }

   private boolean isSameVariantAbove(IBlockAccess world, BlockPos pos, IBlockState here, String variantName) {
      IBlockState above = world.func_180495_p(pos.func_177984_a());
      if (above.func_177230_c() != this) {
         return false;
      } else {
         Object v = above.func_177229_b(VARIANT);
         return v != null && v.toString().equals(variantName);
      }
   }

   @Override
   public Enum[] getVariants() {
      return BlockInfestedBush.EnumType.values();
   }

   @Override
   public ItemBlock getItemBlock() {
      return new ItemBlockVariant(this);
   }

   public IBlockState func_176221_a(IBlockState state, IBlockAccess world, BlockPos pos) {
      boolean node = false;
      boolean end = false;
      if (this.isVariant(state, "grass1")) {
         node = this.isSpecialGround(world, pos.func_177977_b());
      } else if (this.isVariant(state, "spine")) {
         BlockPos scan = pos;

         while (true) {
            BlockPos down = scan.func_177977_b();
            IBlockState s = world.func_180495_p(down);
            if (s.func_177230_c() != this || !this.isVariant(s, "spine")) {
               node = this.isSpecialGround(world, scan.func_177977_b());
               end = !this.isSameVariantAbove(world, pos, state, "spine");
               break;
            }

            scan = down;
         }
      }

      return state.func_177226_a(NODE, node).func_177226_a(END, end);
   }

   private boolean isSpecialGround(IBlockAccess world, BlockPos groundPos) {
      IBlockState below = world.func_180495_p(groundPos);
      Block b = below.func_177230_c();
      ResourceLocation id = b.getRegistryName();
      if (id != null && "srparasites".equals(id.func_110624_b())) {
         String path = id.func_110623_a();
         if ("parasitestain".equals(path)) {
            return true;
         } else {
            return "parasiterubble".equals(path) ? b.func_176201_c(below) == 2 : false;
         }
      } else {
         return false;
      }
   }

   private boolean isVariant(IBlockState state, String name) {
      Object v = state.func_177229_b(VARIANT);
      return v instanceof IStringSerializable && ((IStringSerializable)v).func_176610_l().equals(name);
   }

   public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
      return true;
   }

   public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
      IBlockState state = world.func_180495_p(pos);
      int meta = this.func_176201_c(state);
      Item itemBlock = Item.func_150898_a(this);
      return itemBlock == null ? Collections.emptyList() : Collections.singletonList(new ItemStack(itemBlock, 1, meta));
   }

   public static enum EnumType implements IStringSerializable {
      INFECTED,
      GRASS1,
      FLOWER1,
      SPINE,
      VINE,
      ARC;

      public String func_176610_l() {
         return this.name().toLowerCase();
      }

      @Override
      public String toString() {
         return this.func_176610_l();
      }
   }
}
