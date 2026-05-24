/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBush
 *  net.minecraft.block.SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.properties.PropertyBool
 *  net.minecraft.block.properties.PropertyEnum
 *  net.minecraft.block.state.BlockStateContainer
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.IStringSerializable
 *  net.minecraft.util.NonNullList
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IShearable
 *  net.minecraftforge.oredict.OreDictionary
 */
package com.subspaceparasite.block;

import com.subspaceparasite.SPMain;
import com.subspaceparasite.block.IMetaName;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.init.SPItems;
import com.subspaceparasite.item.ItemBlockVariant;
import com.subspaceparasite.util.config.SPConfigWorld;
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

public class BlockParasiteBush
extends BlockBush
implements IMetaName,
IShearable {
    public static final PropertyBool NODE = PropertyBool.func_177716_a((String)"node");
    public static final PropertyBool END = PropertyBool.func_177716_a((String)"end");
    protected static final AxisAlignedBB TALL_GRASS_AABB = new AxisAlignedBB(0.09999999403953552, 0.0, 0.09999999403953552, (double)0.9f, (double)0.8f, (double)0.9f);
    protected static final AxisAlignedBB REED_AABB = new AxisAlignedBB(0.125, 0.0, 0.125, 0.875, 1.0, 0.875);
    public static final PropertyEnum<EnumType> VARIANT = PropertyEnum.func_177709_a((String)"variant", EnumType.class);
    private static final String[] RAW_MEAT_KEYS = new String[]{"listAllmeatraw", "listAllfishraw", "foodMeatRaw", "foodFishRaw"};

    public BlockParasiteBush(String name, float hardness) {
        super(Material.field_151582_l);
        this.setRegistryName(name);
        this.func_149663_c("subspaceparasite." + name);
        this.func_149672_a(SoundType.field_185850_c);
        this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a(VARIANT, (Comparable)((Object)EnumType.TENDRIL)));
        this.func_149647_a(SPMain.SP_CREATIVETAB);
        this.func_149711_c(hardness);
        SPBlocks.SP_BLOCKS.add((Block)this);
        ItemBlock itemBlock = this.getItemBlock();
        SPItems.SP_ITEMS.add((Item)itemBlock.setRegistryName(Objects.requireNonNull(this.getRegistryName())));
    }

    public AxisAlignedBB func_185496_a(IBlockState state, IBlockAccess source, BlockPos pos) {
        EnumType variant = (EnumType)((Object)state.func_177229_b(VARIANT));
        if (variant == EnumType.TENDRIL || variant == EnumType.BINE) {
            return REED_AABB;
        }
        return TALL_GRASS_AABB;
    }

    public boolean func_180671_f(World worldIn, BlockPos pos, IBlockState state) {
        EnumType selfVariant = (EnumType)((Object)state.func_177229_b(VARIANT));
        if ((selfVariant == EnumType.BINE || selfVariant == EnumType.TENDRIL) && this.hasSPCeilingSupport(worldIn, pos)) {
            return true;
        }
        BlockPos down = pos.func_177977_b();
        IBlockState below = worldIn.func_180495_p(down);
        String variantName = null;
        for (IProperty p : below.func_177227_a()) {
            if (!"variant".equals(p.func_177701_a())) continue;
            IProperty raw = p;
            Comparable val = below.func_177229_b(raw);
            if (val instanceof IStringSerializable) {
                variantName = ((IStringSerializable)val).func_176610_l();
                break;
            }
            if (val == null) break;
            variantName = String.valueOf(val);
            break;
        }
        if (below.func_177230_c() instanceof BlockParasiteBush && ("tendril".equals(variantName) || "bine".equals(variantName) || "thorn".equals(variantName) || "vine".equals(variantName) || "spine".equals(variantName))) {
            IBlockState cur;
            BlockPos base = down;
            for (int guard = 0; guard < 64 && (cur = worldIn.func_180495_p(base)).func_177230_c() instanceof BlockParasiteBush; ++guard) {
                base = base.func_177977_b();
            }
            IBlockState sup = worldIn.func_180495_p(base);
            Block b = sup.func_177230_c();
            ResourceLocation id = b.getRegistryName();
            if (id == null) {
                return false;
            }
            String domain = id.func_110624_b();
            String path = id.func_110623_a();
            if (!"subspaceparasite".equals(domain)) {
                return false;
            }
            if ("bloodyice".equals(path) || "ashen_glass".equals(path)) {
                return false;
            }
            return sup.isSideSolid((IBlockAccess)worldIn, base, EnumFacing.UP);
        }
        IBlockState sup = worldIn.func_180495_p(down);
        Block b = sup.func_177230_c();
        ResourceLocation id = b.getRegistryName();
        if (id == null) {
            return false;
        }
        String domain = id.func_110624_b();
        String path = id.func_110623_a();
        if (!"subspaceparasite".equals(domain)) {
            return false;
        }
        if ("bloodyice".equals(path) || "ashen_glass".equals(path)) {
            return false;
        }
        return sup.isSideSolid((IBlockAccess)worldIn, down, EnumFacing.UP);
    }

    public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack held = player.func_184586_b(hand);
        if (BlockParasiteBush.isRawMeat(held)) {
            if (!world.field_72995_K) {
                int meta = this.func_176201_c(state);
                Item itemBlock = Item.func_150898_a((Block)this);
                if (itemBlock != null) {
                    BlockParasiteBush.func_180635_a((World)world, (BlockPos)pos.func_177984_a(), (ItemStack)new ItemStack(itemBlock, 1, meta));
                }
                if (!player.field_71075_bZ.field_75098_d) {
                    held.func_190918_g(1);
                }
                world.func_175718_b(2005, pos, 0);
                player.func_184811_cZ().func_185145_a(held.func_77973_b(), 10);
            }
            return true;
        }
        return false;
    }

    public boolean func_176196_c(World worldIn, BlockPos pos) {
        if (this.hasSPCeilingSupport(worldIn, pos)) {
            return true;
        }
        IBlockState below = worldIn.func_180495_p(pos.func_177977_b());
        if (below.func_177230_c() instanceof BlockParasiteBush) {
            EnumType v = (EnumType)((Object)below.func_177229_b(VARIANT));
            if (v == EnumType.TENDRIL || v == EnumType.BINE || v == EnumType.THORN) {
                BlockPos base = pos.func_177977_b();
                for (int guard = 0; worldIn.func_180495_p(base).func_177230_c() instanceof BlockParasiteBush && guard < 64; ++guard) {
                    base = base.func_177977_b();
                }
                IBlockState sup = worldIn.func_180495_p(base);
                Block b = sup.func_177230_c();
                ResourceLocation id = b.getRegistryName();
                if (id == null) {
                    return false;
                }
                String domain = id.func_110624_b();
                String path = id.func_110623_a();
                if (!"subspaceparasite".equals(domain)) {
                    return false;
                }
                if ("bloodyice".equals(path) || "ashen_glass".equals(path)) {
                    return false;
                }
                return sup.isSideSolid((IBlockAccess)worldIn, base, EnumFacing.UP);
            }
            return false;
        }
        IBlockState sup = worldIn.func_180495_p(pos.func_177977_b());
        Block b = sup.func_177230_c();
        ResourceLocation id = b.getRegistryName();
        if (id == null) {
            return false;
        }
        String domain = id.func_110624_b();
        String path = id.func_110623_a();
        if (!"subspaceparasite".equals(domain)) {
            return false;
        }
        if ("bloodyice".equals(path) || "ashen_glass".equals(path)) {
            return false;
        }
        return sup.isSideSolid((IBlockAccess)worldIn, pos.func_177977_b(), EnumFacing.UP);
    }

    private boolean isValidSPCeilingSupport(World world, BlockPos supportPosAbove) {
        IBlockState sup = world.func_180495_p(supportPosAbove);
        Block b = sup.func_177230_c();
        ResourceLocation id = b.getRegistryName();
        if (id == null) {
            return false;
        }
        String domain = id.func_110624_b();
        String path = id.func_110623_a();
        if (!"subspaceparasite".equals(domain)) {
            return false;
        }
        if ("bloodyice".equals(path) || "ashen_glass".equals(path)) {
            return false;
        }
        return sup.isSideSolid((IBlockAccess)world, supportPosAbove, EnumFacing.DOWN);
    }

    private boolean hasSPCeilingSupport(World world, BlockPos pos) {
        BlockPos p = pos.func_177984_a();
        for (int guard = 0; world.func_180495_p(p).func_177230_c() instanceof BlockParasiteBush && guard < 64; ++guard) {
            p = p.func_177984_a();
        }
        return this.isValidSPCeilingSupport(world, p);
    }

    public void func_189540_a(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        super.func_189540_a(state, worldIn, pos, blockIn, fromPos);
        if (!this.func_180671_f(worldIn, pos, state)) {
            worldIn.func_175655_b(pos, true);
            return;
        }
        if (fromPos != null && (fromPos.equals((Object)pos.func_177977_b()) || fromPos.equals((Object)pos.func_177984_a()))) {
            worldIn.func_184138_a(pos, state, state, 3);
            worldIn.func_175704_b(pos, pos);
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
        for (EnumType variant : EnumType.values()) {
            items.add((Object)new ItemStack((Block)this, 1, variant.ordinal()));
        }
    }

    public IBlockState func_176203_a(int meta) {
        return this.func_176223_P().func_177226_a(VARIANT, (Comparable)((Object)EnumType.values()[meta]));
    }

    public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity) {
        if (!SPConfigWorld.bushClimbingEnabled) {
            return false;
        }
        if (entity instanceof EntityPlayer) {
            EnumType type = (EnumType)((Object)state.func_177229_b(VARIANT));
            return type == EnumType.TENDRIL || type == EnumType.BINE;
        }
        return false;
    }

    public int func_176201_c(IBlockState state) {
        return ((EnumType)((Object)state.func_177229_b(VARIANT))).ordinal();
    }

    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(Item.func_150898_a((Block)this), 1, this.func_176201_c(world.func_180495_p(pos)));
    }

    public IBlockState func_176221_a(IBlockState state, IBlockAccess world, BlockPos pos) {
        boolean node = false;
        boolean end = false;
        if (this.isVariant(state, "tooh")) {
            node = this.isSpecialGround(world, pos.func_177977_b());
        }
        if (this.isVariant(state, "tendril")) {
            boolean top;
            boolean bottom = world.func_175623_d(pos.func_177977_b());
            end = top = !bottom && world.func_175623_d(pos.func_177984_a());
            node = bottom;
        }
        if (this.isVariant(state, "bine")) {
            boolean top = world.func_175623_d(pos.func_177984_a());
            boolean bottom = !top && world.func_175623_d(pos.func_177977_b());
            node = top;
            end = bottom;
        }
        return state.func_177226_a((IProperty)NODE, (Comparable)Boolean.valueOf(node)).func_177226_a((IProperty)END, (Comparable)Boolean.valueOf(end));
    }

    private boolean isVariant(IBlockState state, String name) {
        Comparable v = state.func_177229_b(VARIANT);
        return v instanceof IStringSerializable && ((IStringSerializable)v).func_176610_l().equals(name);
    }

    private boolean isSpecialGround(IBlockAccess world, BlockPos groundPos) {
        IBlockState below = world.func_180495_p(groundPos);
        Block b = below.func_177230_c();
        ResourceLocation id = b.getRegistryName();
        if (id == null || !"subspaceparasite".equals(id.func_110624_b())) {
            return false;
        }
        String path = id.func_110623_a();
        if ("parasitestain".equals(path)) {
            return true;
        }
        if ("parasiterubble".equals(path)) {
            return b.func_176201_c(below) == 2;
        }
        return false;
    }

    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer((Block)this, new IProperty[]{END, NODE, VARIANT});
    }

    @Override
    public Enum[] getVariants() {
        return EnumType.values();
    }

    @Override
    public ItemBlock getItemBlock() {
        return new ItemBlockVariant((Block)this);
    }

    public boolean isShearable(ItemStack item, IBlockAccess world, BlockPos pos) {
        return true;
    }

    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        IBlockState state = world.func_180495_p(pos);
        int meta = this.func_176201_c(state);
        Item itemBlock = Item.func_150898_a((Block)this);
        if (itemBlock == null) {
            return Collections.emptyList();
        }
        return Collections.singletonList(new ItemStack(itemBlock, 1, meta));
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
        return 0.2f;
    }

    public float func_180647_a(IBlockState state, EntityPlayer player, World world, BlockPos pos) {
        ItemStack held = player.func_184614_ca();
        if (!held.func_190926_b() && held.func_77973_b() instanceof ItemSword) {
            return super.func_180647_a(state, player, world, pos) * 4.0f;
        }
        return super.func_180647_a(state, player, world, pos);
    }

    private boolean isSPSolidTop(World world, BlockPos supportPos) {
        IBlockState sup = world.func_180495_p(supportPos);
        Block b = sup.func_177230_c();
        ResourceLocation id = b.getRegistryName();
        if (id == null) {
            return false;
        }
        String domain = id.func_110624_b();
        String path = id.func_110623_a();
        if (!"subspaceparasite".equals(domain)) {
            return false;
        }
        if ("bloodyice".equals(path) || "ashen_glass".equals(path)) {
            return false;
        }
        return sup.isSideSolid((IBlockAccess)world, supportPos, EnumFacing.UP);
    }

    private static boolean isRawMeat(ItemStack stack) {
        int[] ids;
        if (stack == null || stack.func_190926_b()) {
            return false;
        }
        Item it = stack.func_77973_b();
        if (it == Items.field_151082_bd || it == Items.field_151147_al || it == Items.field_151076_bf || it == Items.field_179561_bm || it == Items.field_179558_bo || it == Items.field_151115_aP) {
            return true;
        }
        for (int id : ids = OreDictionary.getOreIDs((ItemStack)stack)) {
            String name = OreDictionary.getOreName((int)id);
            for (String key : RAW_MEAT_KEYS) {
                if (!key.equalsIgnoreCase(name)) continue;
                return true;
            }
        }
        return false;
    }

    public boolean func_176198_a(World worldIn, BlockPos pos, EnumFacing side) {
        if (side == EnumFacing.UP) {
            IBlockState below = worldIn.func_180495_p(pos.func_177977_b());
            if (below.func_177230_c() instanceof BlockParasiteBush) {
                EnumType v = (EnumType)((Object)below.func_177229_b(VARIANT));
                if (v == EnumType.TENDRIL || v == EnumType.BINE || v == EnumType.THORN) {
                    BlockPos base = pos.func_177977_b();
                    for (int guard = 0; worldIn.func_180495_p(base).func_177230_c() instanceof BlockParasiteBush && guard < 64; ++guard) {
                        base = base.func_177977_b();
                    }
                    IBlockState sup = worldIn.func_180495_p(base);
                    ResourceLocation id = sup.func_177230_c().getRegistryName();
                    if (id == null) {
                        return false;
                    }
                    String domain = id.func_110624_b();
                    String path = id.func_110623_a();
                    if (!"subspaceparasite".equals(domain)) {
                        return false;
                    }
                    if ("bloodyice".equals(path) || "ashen_glass".equals(path)) {
                        return false;
                    }
                    return sup.isSideSolid((IBlockAccess)worldIn, base, EnumFacing.UP);
                }
                return false;
            }
            IBlockState sup = worldIn.func_180495_p(pos.func_177977_b());
            ResourceLocation id = sup.func_177230_c().getRegistryName();
            if (id == null) {
                return false;
            }
            String domain = id.func_110624_b();
            String path = id.func_110623_a();
            if (!"subspaceparasite".equals(domain)) {
                return false;
            }
            if ("bloodyice".equals(path) || "ashen_glass".equals(path)) {
                return false;
            }
            return sup.isSideSolid((IBlockAccess)worldIn, pos.func_177977_b(), EnumFacing.UP);
        }
        if (side == EnumFacing.DOWN) {
            return this.hasSPCeilingSupport(worldIn, pos);
        }
        return false;
    }

    protected boolean func_185514_i(IBlockState state) {
        ResourceLocation id = state.func_177230_c().getRegistryName();
        if (id == null) {
            return false;
        }
        String domain = id.func_110624_b();
        String path = id.func_110623_a();
        if (!"subspaceparasite".equals(domain)) {
            return false;
        }
        if ("bloodyice".equals(path) || "ashen_glass".equals(path)) {
            return false;
        }
        return state.func_185914_p();
    }

    private boolean isValidSPSupport(World world, BlockPos supportPos) {
        IBlockState sup = world.func_180495_p(supportPos);
        Block b = sup.func_177230_c();
        ResourceLocation id = b.getRegistryName();
        if (id == null) {
            return false;
        }
        String domain = id.func_110624_b();
        String path = id.func_110623_a();
        if (!"subspaceparasite".equals(domain)) {
            return false;
        }
        if ("bloodyice".equals(path) || "ashen_glass".equals(path)) {
            return false;
        }
        return sup.isSideSolid((IBlockAccess)world, supportPos, EnumFacing.UP);
    }

    private boolean hasSPBaseSupport(World world, BlockPos posAboveSupport) {
        BlockPos p = posAboveSupport.func_177977_b();
        for (int guard = 0; world.func_180495_p(p).func_177230_c() instanceof BlockParasiteBush && guard < 64; ++guard) {
            p = p.func_177977_b();
        }
        return this.isValidSPSupport(world, p);
    }

    public static enum EnumType implements IStringSerializable
    {
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

        public String toString() {
            return this.func_176610_l();
        }
    }
}

