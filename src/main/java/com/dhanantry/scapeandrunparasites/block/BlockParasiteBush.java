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
import net.minecraft.item.ItemSword;
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

public class BlockParasiteBush extends BlockBush implements IMetaName, IShearable {
   public static final PropertyBool NODE = PropertyBool.func_177716_a("node");
   public static final PropertyBool END = PropertyBool.func_177716_a("end");
   protected static final AxisAlignedBB TALL_GRASS_AABB = new AxisAlignedBB(0.099999994F, 0.0, 0.099999994F, 0.9F, 0.8F, 0.9F);
   protected static final AxisAlignedBB REED_AABB = new AxisAlignedBB(0.125, 0.0, 0.125, 0.875, 1.0, 0.875);
   public static final PropertyEnum<BlockParasiteBush.EnumType> VARIANT = PropertyEnum.func_177709_a("variant", BlockParasiteBush.EnumType.class);
   private static final String[] RAW_MEAT_KEYS = new String[]{"listAllmeatraw", "listAllfishraw", "foodMeatRaw", "foodFishRaw"};

   public BlockParasiteBush(String name, float hardness) {
      super(Material.field_151582_l);
      this.setRegistryName(name);
      this.func_149663_c("srparasites." + name);
      this.func_149672_a(SoundType.field_185850_c);
      this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(VARIANT, BlockParasiteBush.EnumType.TENDRIL));
      this.func_149647_a(SRPMain.SRP_CREATIVETAB);
      this.func_149711_c(hardness);
      SRPBlocks.SRP_BLOCKS.add(this);
      Item itemBlock = this.getItemBlock();
      SRPItems.SRP_ITEMS.add(itemBlock.setRegistryName(Objects.requireNonNull(this.getRegistryName())));
   }

   public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) {
      BlockParasiteBush.EnumType variant = (BlockParasiteBush.EnumType)state.func_177229_b(VARIANT);
      return variant != BlockParasiteBush.EnumType.TENDRIL && variant != BlockParasiteBush.EnumType.BINE ? TALL_GRASS_AABB : REED_AABB;
   }

   public boolean func_180671_f(World worldIn, BlockPos pos, IBlockState state) {
      BlockParasiteBush.EnumType selfVariant = (BlockParasiteBush.EnumType)state.func_177229_b(VARIANT);
      if ((selfVariant == BlockParasiteBush.EnumType.BINE || selfVariant == BlockParasiteBush.EnumType.TENDRIL) && this.hasSRPCeilingSupport(worldIn, pos)) {
         return true;
      } else {
         BlockPos down = pos.func_177977_b();
         IBlockState below = worldIn.func_180495_p(down);
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

         if (below.func_177230_c() instanceof BlockParasiteBush
            && (
               "tendril".equals(variantName)
                  || "bine".equals(variantName)
                  || "thorn".equals(variantName)
                  || "vine".equals(variantName)
                  || "spine".equals(variantName)
            )) {
            BlockPos base = down;

            for (int guard = 0; guard < 64; guard++) {
               IBlockState cur = worldIn.func_180495_p(base);
               if (!(cur.func_177230_c() instanceof BlockParasiteBush)) {
                  break;
               }

               base = base.func_177977_b();
            }

            IBlockState sup = worldIn.func_180495_p(base);
            Block b = sup.func_177230_c();
            ResourceLocation id = b.getRegistryName();
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
         } else {
            IBlockState sup = worldIn.func_180495_p(down);
            Block b = sup.func_177230_c();
            ResourceLocation id = b.getRegistryName();
            if (id == null) {
               return false;
            } else {
               String domain = id.func_110624_b();
               String path = id.func_110623_a();
               if (!"srparasites".equals(domain)) {
                  return false;
               } else {
                  return !"bloodyice".equals(path) && !"ashen_glass".equals(path) ? sup.isSideSolid(worldIn, down, EnumFacing.UP) : false;
               }
            }
         }
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

   public boolean func_176196_c(World worldIn, BlockPos pos) {
      if (this.hasSRPCeilingSupport(worldIn, pos)) {
         return true;
      } else {
         IBlockState below = worldIn.func_180495_p(pos.func_177977_b());
         if (!(below.func_177230_c() instanceof BlockParasiteBush)) {
            IBlockState sup = worldIn.func_180495_p(pos.func_177977_b());
            Block b = sup.func_177230_c();
            ResourceLocation id = b.getRegistryName();
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
            BlockParasiteBush.EnumType v = (BlockParasiteBush.EnumType)below.func_177229_b(VARIANT);
            if (v != BlockParasiteBush.EnumType.TENDRIL && v != BlockParasiteBush.EnumType.BINE && v != BlockParasiteBush.EnumType.THORN) {
               return false;
            } else {
               BlockPos base = pos.func_177977_b();

               for (int guard = 0; worldIn.func_180495_p(base).func_177230_c() instanceof BlockParasiteBush && guard < 64; guard++) {
                  base = base.func_177977_b();
               }

               IBlockState sup = worldIn.func_180495_p(base);
               Block b = sup.func_177230_c();
               ResourceLocation id = b.getRegistryName();
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

   private boolean isValidSRPCeilingSupport(World world, BlockPos supportPosAbove) {
      IBlockState sup = world.func_180495_p(supportPosAbove);
      Block b = sup.func_177230_c();
      ResourceLocation id = b.getRegistryName();
      if (id == null) {
         return false;
      } else {
         String domain = id.func_110624_b();
         String path = id.func_110623_a();
         if (!"srparasites".equals(domain)) {
            return false;
         } else {
            return !"bloodyice".equals(path) && !"ashen_glass".equals(path) ? sup.isSideSolid(world, supportPosAbove, EnumFacing.DOWN) : false;
         }
      }
   }

   private boolean hasSRPCeilingSupport(World world, BlockPos pos) {
      BlockPos p = pos.func_177984_a();

      for (int guard = 0; world.func_180495_p(p).func_177230_c() instanceof BlockParasiteBush && guard < 64; guard++) {
         p = p.func_177984_a();
      }

      return this.isValidSRPCeilingSupport(world, p);
   }

   public void func_189540_a(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
      super.func_189540_a(state, worldIn, pos, blockIn, fromPos);
      if (!this.func_180671_f(worldIn, pos, state)) {
         worldIn.func_175655_b(pos, true);
      } else {
         if (fromPos != null && (fromPos.equals(pos.func_177977_b()) || fromPos.equals(pos.func_177984_a()))) {
            worldIn.func_184138_a(pos, state, state, 3);
            worldIn.func_175704_b(pos, pos);
         }
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
      for (BlockParasiteBush.EnumType variant : BlockParasiteBush.EnumType.values()) {
         items.add(new ItemStack(this, 1, variant.ordinal()));
      }
   }

   public IBlockState func_176203_a(int meta) {
      return this.func_176223_P().func_177226_a(VARIANT, BlockParasiteBush.EnumType.values()[meta]);
   }

   public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity) {
      if (!SRPConfigWorld.bushClimbingEnabled) {
         return false;
      } else if (!(entity instanceof EntityPlayer)) {
         return false;
      } else {
         BlockParasiteBush.EnumType type = (BlockParasiteBush.EnumType)state.func_177229_b(VARIANT);
         return type == BlockParasiteBush.EnumType.TENDRIL || type == BlockParasiteBush.EnumType.BINE;
      }
   }

   public int func_176201_c(IBlockState state) {
      return ((BlockParasiteBush.EnumType)state.func_177229_b(VARIANT)).ordinal();
   }

   public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
      return new ItemStack(Item.func_150898_a(this), 1, this.func_176201_c(world.func_180495_p(pos)));
   }

   public IBlockState func_176221_a(IBlockState state, IBlockAccess world, BlockPos pos) {
      boolean node = false;
      boolean end = false;
      if (this.isVariant(state, "tooh")) {
         node = this.isSpecialGround(world, pos.func_177977_b());
      }

      if (this.isVariant(state, "tendril")) {
         boolean bottom = world.func_175623_d(pos.func_177977_b());
         boolean top = !bottom && world.func_175623_d(pos.func_177984_a());
         end = top;
         node = bottom;
      }

      if (this.isVariant(state, "bine")) {
         boolean top = world.func_175623_d(pos.func_177984_a());
         boolean bottom = !top && world.func_175623_d(pos.func_177977_b());
         node = top;
         end = bottom;
      }

      return state.func_177226_a(NODE, node).func_177226_a(END, end);
   }

   private boolean isVariant(IBlockState state, String name) {
      Object v = state.func_177229_b(VARIANT);
      return v instanceof IStringSerializable && ((IStringSerializable)v).func_176610_l().equals(name);
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

   protected BlockStateContainer func_180661_e() {
      return new BlockStateContainer(this, new IProperty[]{END, NODE, VARIANT});
   }

   @Override
   public Enum[] getVariants() {
      return BlockParasiteBush.EnumType.values();
   }

   @Override
   public ItemBlock getItemBlock() {
      return new ItemBlockVariant(this);
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

   public boolean isToolEffective(String type, IBlockState state) {
      return "axe".equals(type) || "shears".equals(type);
   }

   public String getHarvestTool(IBlockState state) {
      return "axe";
   }

   public int getHarvestLevel(IBlockState state) {
      return 0;
   }

   public float func_176195_g(IBlockState blockState, World worldIn, BlockPos pos) {
      return 0.2F;
   }

   public float func_180647_a(IBlockState state, EntityPlayer player, World world, BlockPos pos) {
      ItemStack held = player.func_184614_ca();
      return !held.func_190926_b() && held.func_77973_b() instanceof ItemSword
         ? super.func_180647_a(state, player, world, pos) * 4.0F
         : super.func_180647_a(state, player, world, pos);
   }

   private boolean isSRPSolidTop(World world, BlockPos supportPos) {
      IBlockState sup = world.func_180495_p(supportPos);
      Block b = sup.func_177230_c();
      ResourceLocation id = b.getRegistryName();
      if (id == null) {
         return false;
      } else {
         String domain = id.func_110624_b();
         String path = id.func_110623_a();
         if (!"srparasites".equals(domain)) {
            return false;
         } else {
            return !"bloodyice".equals(path) && !"ashen_glass".equals(path) ? sup.isSideSolid(world, supportPos, EnumFacing.UP) : false;
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

   public boolean func_176198_a(World worldIn, BlockPos pos, EnumFacing side) {
      if (side != EnumFacing.UP) {
         return side == EnumFacing.DOWN ? this.hasSRPCeilingSupport(worldIn, pos) : false;
      } else {
         IBlockState below = worldIn.func_180495_p(pos.func_177977_b());
         if (!(below.func_177230_c() instanceof BlockParasiteBush)) {
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
            BlockParasiteBush.EnumType v = (BlockParasiteBush.EnumType)below.func_177229_b(VARIANT);
            if (v != BlockParasiteBush.EnumType.TENDRIL && v != BlockParasiteBush.EnumType.BINE && v != BlockParasiteBush.EnumType.THORN) {
               return false;
            } else {
               BlockPos base = pos.func_177977_b();

               for (int guard = 0; worldIn.func_180495_p(base).func_177230_c() instanceof BlockParasiteBush && guard < 64; guard++) {
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

   private boolean isValidSRPSupport(World world, BlockPos supportPos) {
      IBlockState sup = world.func_180495_p(supportPos);
      Block b = sup.func_177230_c();
      ResourceLocation id = b.getRegistryName();
      if (id == null) {
         return false;
      } else {
         String domain = id.func_110624_b();
         String path = id.func_110623_a();
         if (!"srparasites".equals(domain)) {
            return false;
         } else {
            return !"bloodyice".equals(path) && !"ashen_glass".equals(path) ? sup.isSideSolid(world, supportPos, EnumFacing.UP) : false;
         }
      }
   }

   private boolean hasSRPBaseSupport(World world, BlockPos posAboveSupport) {
      BlockPos p = posAboveSupport.func_177977_b();

      for (int guard = 0; world.func_180495_p(p).func_177230_c() instanceof BlockParasiteBush && guard < 64; guard++) {
         p = p.func_177977_b();
      }

      return this.isValidSRPSupport(world, p);
   }

   public static enum EnumType implements IStringSerializable {
      TENDRIL,
      BINE,
      POP,
      EYE,
      TOOH,
      FROSTG,
      FROSTGT,
      THORN;

      public String func_176610_l() {
         return this.name().toLowerCase();
      }

      @Override
      public String toString() {
         return this.func_176610_l();
      }
   }
}
