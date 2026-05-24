/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBush
 *  net.minecraft.block.BlockFence
 *  net.minecraft.block.BlockFenceGate
 *  net.minecraft.block.BlockFurnace
 *  net.minecraft.block.BlockGlass
 *  net.minecraft.block.BlockGrassPath
 *  net.minecraft.block.BlockLiquid
 *  net.minecraft.block.BlockPane
 *  net.minecraft.block.BlockPlanks
 *  net.minecraft.block.BlockStairs
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.EnumSkyBlock
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package com.subspaceparasite.util.convert;

import com.subspaceparasite.block.BlockBase;
import com.subspaceparasite.block.BlockInfestedBush;
import com.subspaceparasite.block.BlockParasiteSpreading;
import com.subspaceparasite.block.IMetaName;
import com.subspaceparasite.block.IStagedBlock;
import com.subspaceparasite.entity.ai.misc.EntityPBeckon;
import com.subspaceparasite.entity.ai.misc.EntityParasiteBase;
import com.subspaceparasite.entity.monster.deterrent.nexus.EntityVenkrol;
import com.subspaceparasite.init.SPBlocks;
import com.subspaceparasite.init.SPPotions;
import com.subspaceparasite.util.ParasiteEventEntity;
import com.subspaceparasite.util.ParasiteEventWorld;
import com.subspaceparasite.util.config.SPConfig;
import com.subspaceparasite.util.config.SPConfigMobs;
import com.subspaceparasite.util.config.SPConfigSystems;
import com.subspaceparasite.util.config.SPConfigWorld;
import com.subspaceparasite.world.SPSaveData;
import com.subspaceparasite.world.gen.feature.WorldGenParasiteBush;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.BlockGrassPath;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BeckonBlockInfestation {
    private static final Logger INFEST_LOG = LogManager.getLogger((String)"SRP-Infest");
    private static final boolean DEBUG = false;
    public static int blockInfestedCount;
    private static final HashMap<Material, IBlockState> conversionCache;
    private static final HashMap<BlockMetaKey, IBlockState> customConvertCache;
    private static int customConvertHash;

    public static void beckonInfestation(World worldIn, BlockPos pos, Random rand, int stage, boolean fromVenkrol) {
        int rangeY;
        int range;
        if (blockInfestedCount > SPConfig.BlockInfestedLimit) {
            return;
        }
        if (worldIn.func_175659_aa() == EnumDifficulty.PEACEFUL) {
            return;
        }
        int pivot = 0;
        switch (stage) {
            case 1: {
                range = SPConfigMobs.venkrolRange - 1;
                rangeY = SPConfigMobs.venkrolRangeY;
                break;
            }
            case 2: {
                range = SPConfigMobs.venkrolsiiRange - 1;
                rangeY = SPConfigMobs.venkrolsiiRangeY;
                break;
            }
            default: {
                range = SPConfigMobs.venkrolsiiiRange - 1;
                rangeY = SPConfigMobs.venkrolsiiiRangeY;
            }
        }
        AxisAlignedBB axisalignedbb = new AxisAlignedBB((double)pos.func_177958_n(), (double)pos.func_177956_o(), (double)pos.func_177952_p(), (double)(pos.func_177958_n() + 1), (double)(pos.func_177956_o() + 1), (double)(pos.func_177952_p() + 1)).func_72314_b((double)range, (double)rangeY, (double)range);
        List moblist = worldIn.func_72872_a(EntityParasiteBase.class, axisalignedbb);
        if (fromVenkrol) {
            if (moblist.isEmpty() && stage < 4) {
                BeckonBlockInfestation.spawnBeckonFromInfestation(worldIn, pos, stage);
                return;
            }
            if (!moblist.isEmpty() && stage < 4) {
                int flagV = 0;
                for (EntityParasiteBase mob : moblist) {
                    if (flagV == 2) break;
                    if (mob.func_70644_a(SPPotions.PIVOT_E)) {
                        pivot = Math.max(mob.func_70660_b(SPPotions.PIVOT_E).func_76458_c() + 1, pivot);
                    }
                    flagV = 0;
                    BlockPos venPos = mob.func_180425_c();
                    if (worldIn.func_175724_o(venPos.func_177984_a()) < (float)SPConfigSystems.rsBlockLight / 15.0f) {
                        ++flagV;
                    }
                    if (worldIn.func_175642_b(EnumSkyBlock.BLOCK, venPos.func_177984_a()) >= SPConfigSystems.rsBlockLight) continue;
                    ++flagV;
                }
                if (flagV < 2) {
                    return;
                }
            }
        }
        int covertedBlocks = BeckonBlockInfestation.blockConversions(worldIn, pos, rand, stage);
        if (stage >= 4) {
            if (worldIn.func_180495_p(pos).func_177230_c() == SPBlocks.InfestedStain) {
                worldIn.func_175656_a(pos, SPBlocks.optionalDirt);
            } else if (worldIn.func_180495_p(pos).func_177230_c() == SPBlocks.InfestedRubble) {
                worldIn.func_175656_a(pos, SPBlocks.optionalRub);
            }
            if (worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() == Blocks.field_150350_a || worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() == SPBlocks.ParasiteBush) {
                worldIn.func_175656_a(pos.func_177984_a(), SPBlocks.InfestRemain.func_176223_P());
            }
        } else if (covertedBlocks != 0) {
            blockInfestedCount += covertedBlocks;
            if (SPConfigSystems.useEvolution) {
                if (pivot != 0) {
                    SPSaveData.get(worldIn, 75).setTotalKills(worldIn.field_73011_w.getDimension(), SPConfigSystems.valueBlock * covertedBlocks * (SPConfigSystems.pivotPointMultiplier * pivot), true, worldIn, true, 53);
                } else {
                    SPSaveData.get(worldIn, 74).setTotalKills(worldIn.field_73011_w.getDimension(), SPConfigSystems.valueBlock * covertedBlocks, true, worldIn, true, 54);
                }
            }
        }
    }

    private static void generateInfestationFeatures(World worldIn, BlockPos pos, Random rand) {
        BeckonBlockInfestation.spawnGenFeatureInfested(worldIn, pos.func_177984_a(), rand);
        BeckonBlockInfestation.spawnGenRoofInfested(worldIn, pos.func_177977_b(), rand);
    }

    public static void spawnGenFeatureInfested(World worldIn, BlockPos pos, Random rand) {
        WorldGenParasiteBush gen;
        double bonus = 0.0;
        if (worldIn.func_180495_p(pos).func_177230_c() != Blocks.field_150350_a && worldIn.func_180495_p(pos).func_185904_a() == Material.field_151586_h) {
            pos = ParasiteEventEntity.getFloor(worldIn, pos, 10);
            bonus = 3.0E-5;
            if (pos == null) {
                return;
            }
        }
        if (rand.nextInt(20) == 0) {
            gen = new WorldGenParasiteBush(false, BlockInfestedBush.EnumType.SPINE, 4);
            gen.func_180709_b(worldIn, rand, pos);
        }
        if (rand.nextInt(20) == 0) {
            if (rand.nextInt(2) == 0) {
                gen = new WorldGenParasiteBush(false, BlockInfestedBush.EnumType.ARC, 2);
                gen.func_180709_b(worldIn, rand, pos);
            } else {
                gen = new WorldGenParasiteBush(false, BlockInfestedBush.EnumType.GRASS1, 1);
                gen.func_180709_b(worldIn, rand, pos);
            }
        }
    }

    public static void spawnGenRoofInfested(World worldIn, BlockPos pos, Random rand) {
        if (worldIn.func_180495_p(pos).func_177230_c() != Blocks.field_150350_a) {
            return;
        }
        if (rand.nextInt(5) == 0) {
            worldIn.func_175656_a(pos, SPBlocks.InfestedBush.func_176223_P().func_177226_a(BlockInfestedBush.VARIANT, (Comparable)((Object)BlockInfestedBush.EnumType.VINE)));
            pos = pos.func_177977_b();
            if (rand.nextInt(4) == 0) {
                if (worldIn.func_180495_p(pos).func_177230_c() != Blocks.field_150350_a) {
                    return;
                }
                worldIn.func_175656_a(pos, SPBlocks.InfestedBush.func_176223_P().func_177226_a(BlockInfestedBush.VARIANT, (Comparable)((Object)BlockInfestedBush.EnumType.VINE)));
                pos = pos.func_177977_b();
                if (rand.nextInt(3) == 0) {
                    if (worldIn.func_180495_p(pos).func_177230_c() != Blocks.field_150350_a) {
                        return;
                    }
                    worldIn.func_175656_a(pos, SPBlocks.InfestedBush.func_176223_P().func_177226_a(BlockInfestedBush.VARIANT, (Comparable)((Object)BlockInfestedBush.EnumType.VINE)));
                }
            }
        }
    }

    private static void rebuildCustomConvertCacheIfNeeded() {
        String[] list = SPConfigWorld.infestationConvertBlocks;
        int h = 1;
        if (list != null) {
            for (String s : list) {
                if (s == null) continue;
                h = 31 * h + s.hashCode();
            }
        }
        if (h == customConvertHash) {
            return;
        }
        customConvertHash = h;
        customConvertCache.clear();
        if (list == null) {
            return;
        }
        for (String entry : list) {
            String[] parts;
            if (entry == null || (entry = entry.trim()).isEmpty() || (parts = entry.split(";", 2)).length != 2) continue;
            String left = parts[0].trim();
            String right = parts[1].trim();
            ParsedState src = BeckonBlockInfestation.parseStateToken(left);
            ParsedState dst = BeckonBlockInfestation.parseStateToken(right);
            if (src == null || dst == null || dst.block == Blocks.field_150350_a && !"minecraft:air".equals(right)) continue;
            customConvertCache.put(new BlockMetaKey(src.block, src.meta), dst.state);
        }
    }

    private static ParsedState parseStateToken(String token) {
        ResourceLocation rl;
        if (token == null || token.isEmpty()) {
            return null;
        }
        String id = token.trim();
        int meta = -1;
        String[] split = id.split(":");
        if (split.length == 3) {
            id = split[0] + ":" + split[1];
            try {
                meta = Integer.parseInt(split[2]);
            }
            catch (Exception e) {
                meta = -1;
            }
        }
        try {
            rl = new ResourceLocation(id);
        }
        catch (Exception e) {
            return null;
        }
        if (!Block.field_149771_c.func_148741_d((Object)rl)) {
            return null;
        }
        Block b = (Block)Block.field_149771_c.func_82594_a((Object)rl);
        if (b == null) {
            return null;
        }
        if (b == Blocks.field_150350_a && !"minecraft:air".equals(id)) {
            return null;
        }
        if (meta < 0 && b instanceof BlockLiquid) {
            meta = 0;
        }
        IBlockState st = meta >= 0 ? b.func_176203_a(meta) : b.func_176223_P();
        return new ParsedState(b, meta, st);
    }

    private static IBlockState getCustomConvertedState(Block lookingBlock, IBlockState lookingState, int stage) {
        if (stage >= 4) {
            return null;
        }
        BeckonBlockInfestation.rebuildCustomConvertCacheIfNeeded();
        int meta = lookingBlock.func_176201_c(lookingState);
        IBlockState dst = customConvertCache.get(new BlockMetaKey(lookingBlock, meta));
        if (dst == null) {
            dst = customConvertCache.get(new BlockMetaKey(lookingBlock, -1));
        }
        if (dst == null) {
            return null;
        }
        return BeckonBlockInfestation.createStagedState(dst, stage);
    }

    private static IBlockState createStagedState(IBlockState baseState, int stage) {
        if (stage < 0) {
            stage = 0;
        }
        if (stage > 5) {
            stage = 5;
        }
        if (baseState.func_177230_c() instanceof IStagedBlock) {
            return ((IStagedBlock)baseState.func_177230_c()).withStage(baseState, stage);
        }
        return baseState;
    }

    private static int blockConversions(World worldIn, BlockPos pos, Random rand, int stage) {
        int convertedBlocks = 0;
        for (int dir = 0; dir <= 5; ++dir) {
            IBlockState custom;
            BlockPos helper = BlockParasiteSpreading.directionToSpread(pos, dir);
            IBlockState lookingState = worldIn.func_180495_p(helper);
            Block lookingBlock = lookingState.func_177230_c();
            Material mat = lookingState.func_185904_a();
            if (lookingBlock instanceof IMetaName || BeckonBlockInfestation.isSrpInfestedBlock(lookingBlock)) continue;
            if (stage < 4 && !BeckonBlockInfestation.isSrpBlock(lookingBlock) && (custom = BeckonBlockInfestation.getCustomConvertedState(lookingBlock, lookingState, stage)) != null) {
                worldIn.func_180501_a(helper, custom, 3);
                ++convertedBlocks;
                BeckonBlockInfestation.generateInfestationFeatures(worldIn, helper, rand);
                continue;
            }
            if (stage <= 3) {
                IBlockState copy;
                IBlockState dst;
                if (stage <= 3 && BeckonBlockInfestation.isCobbleBlock(lookingBlock, lookingState)) {
                    ++convertedBlocks;
                    worldIn.func_180501_a(helper, SPBlocks.InfestedCobblestone.func_176223_P(), 3);
                    continue;
                }
                if (BeckonBlockInfestation.isPathBlock(lookingBlock)) {
                    ++convertedBlocks;
                    worldIn.func_180501_a(helper, SPBlocks.InfestedSand.func_176223_P(), 3);
                    continue;
                }
                if (BeckonBlockInfestation.isFenceBlock(lookingBlock)) {
                    ++convertedBlocks;
                    worldIn.func_180501_a(helper, SPBlocks.InfestedFence.func_176223_P(), 3);
                    continue;
                }
                if (BeckonBlockInfestation.isAnyPlanks(lookingBlock, lookingState)) {
                    ++convertedBlocks;
                    worldIn.func_180501_a(helper, SPBlocks.InfestedPlanks.func_176223_P(), 3);
                    continue;
                }
                if (BeckonBlockInfestation.isGlassBlock(lookingBlock, lookingState)) {
                    ++convertedBlocks;
                    worldIn.func_180501_a(helper, SPBlocks.InfestedGlass.func_176223_P(), 3);
                    continue;
                }
                if (BeckonBlockInfestation.isGlassPaneBlock(lookingBlock)) {
                    ++convertedBlocks;
                    worldIn.func_180501_a(helper, SPBlocks.INFESTED_GLASS_PANE.func_176223_P(), 3);
                    continue;
                }
                if (BeckonBlockInfestation.isFurnaceBlock(lookingBlock)) {
                    ++convertedBlocks;
                    worldIn.func_180501_a(helper, SPBlocks.INFESTED_FURNACE.func_176223_P(), 3);
                    continue;
                }
                if (BeckonBlockInfestation.isStoneStairs(lookingBlock, lookingState)) {
                    dst = SPBlocks.InfestedStoneStairs.func_176223_P();
                    copy = BeckonBlockInfestation.copyCommonProps(lookingState, dst);
                    ++convertedBlocks;
                    worldIn.func_180501_a(helper, copy, 3);
                    continue;
                }
                if (BeckonBlockInfestation.isWoodStairs(lookingBlock, lookingState)) {
                    dst = SPBlocks.InfestedPlanksStairs.func_176223_P();
                    copy = BeckonBlockInfestation.copyCommonProps(lookingState, dst);
                    ++convertedBlocks;
                    worldIn.func_180501_a(helper, copy, 3);
                    continue;
                }
                if (lookingBlock.isWood((IBlockAccess)worldIn, helper)) {
                    ++convertedBlocks;
                    worldIn.func_180501_a(helper, SPBlocks.InfestedTrunk.func_176223_P(), 3);
                    continue;
                }
            }
            if (ParasiteEventWorld.blockException(worldIn, helper, lookingBlock, lookingState, SPConfigSystems.blockBList, SPConfigSystems.blockBListWhite, SPConfigSystems.rsBlockIMaxH) && stage <= 3) continue;
            if (lookingBlock instanceof BlockBase) {
                if (lookingBlock == SPBlocks.BiomeHeart || lookingBlock == SPBlocks.ColonyHeart || BeckonBlockInfestation.isSrpInfestedBlock(lookingBlock)) continue;
                int lookM = lookingBlock.func_176201_c(lookingState);
                if (!conversionCache.containsKey(mat)) continue;
                IBlockState newState = BeckonBlockInfestation.createStagedState(conversionCache.get(mat), stage);
                if (stage >= 4 && (lookM <= 1 || stage == 5)) {
                    worldIn.func_175656_a(helper, newState);
                    worldIn.func_175654_a(helper, newState.func_177230_c(), 40, 5);
                    continue;
                }
                if (lookM >= stage || stage >= 4) continue;
                worldIn.func_175656_a(helper, newState);
                continue;
            }
            if (stage >= 4 || !conversionCache.containsKey(mat) || BeckonBlockInfestation.isSrpBlock(lookingBlock)) continue;
            IBlockState infestedState = BeckonBlockInfestation.createStagedState(conversionCache.get(mat), stage);
            worldIn.func_175656_a(helper, infestedState);
            ++convertedBlocks;
            BeckonBlockInfestation.generateInfestationFeatures(worldIn, helper, rand);
        }
        return convertedBlocks;
    }

    private static void spawnBeckonFromInfestation(World worldIn, BlockPos pos, int stage) {
        if (worldIn.field_73012_v.nextDouble() < SPConfigSystems.rsVenkrolEmpty && stage != 1 && (worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() == Blocks.field_150350_a || worldIn.func_180495_p(pos.func_177984_a()).func_177230_c() instanceof BlockBush)) {
            if (worldIn.func_175724_o(pos.func_177984_a()) > 0.46666667f || worldIn.func_175642_b(EnumSkyBlock.BLOCK, pos.func_177984_a()) > 7) {
                return;
            }
            List serverList = worldIn.field_72996_f;
            int count = 0;
            for (Entity entity : serverList) {
                if (!(entity instanceof EntityPBeckon) || ++count <= SPConfig.nexusVenkrolCap && !(BeckonBlockInfestation.getDistanceSq(pos, entity) < (double)(SPConfig.nexusVenkrolDis * SPConfig.nexusVenkrolDis))) continue;
                return;
            }
            EntityVenkrol out = new EntityVenkrol(worldIn);
            out.func_70012_b((double)pos.func_177958_n() + 0.5, pos.func_177956_o() + 1, (double)pos.func_177952_p() + 0.5, 0.0f, 0.0f);
            worldIn.func_72838_d((Entity)out);
            out.cannotDespawn(true);
        }
    }

    private static double getDistanceSq(BlockPos pos, Entity entityIn) {
        double d0 = (double)pos.func_177958_n() - entityIn.field_70165_t;
        double d1 = (double)pos.func_177956_o() - entityIn.field_70163_u;
        double d2 = (double)pos.func_177952_p() - entityIn.field_70161_v;
        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    private static boolean isSrpInfestedBlock(Block b) {
        ResourceLocation rl = b.getRegistryName();
        if (rl == null) {
            return false;
        }
        if (!"subspaceparasite".equals(rl.func_110624_b())) {
            return false;
        }
        String path = rl.func_110623_a().toLowerCase(Locale.ROOT);
        return path.contains("infest");
    }

    private static boolean isSrpBlock(Block b) {
        ResourceLocation rl = b.getRegistryName();
        return rl != null && "subspaceparasite".equals(rl.func_110624_b());
    }

    private static boolean isPathBlock(Block b) {
        if (b == Blocks.field_185774_da || b instanceof BlockGrassPath) {
            return true;
        }
        ResourceLocation rl = b.getRegistryName();
        if (rl == null) {
            return false;
        }
        String path = rl.func_110623_a().toLowerCase(Locale.ROOT);
        return path.contains("path");
    }

    private static boolean isFenceBlock(Block b) {
        if (b instanceof BlockFence) {
            return true;
        }
        if (b instanceof BlockFenceGate) {
            return false;
        }
        ResourceLocation rl = b.getRegistryName();
        if (rl == null) {
            return false;
        }
        String path = rl.func_110623_a().toLowerCase(Locale.ROOT);
        return path.contains("fence") && !path.contains("gate");
    }

    private static boolean isAnyPlanks(Block b, IBlockState s) {
        if (b instanceof BlockPlanks) {
            return true;
        }
        ResourceLocation rl = b.getRegistryName();
        if (rl == null) {
            return false;
        }
        String path = rl.func_110623_a().toLowerCase(Locale.ROOT);
        return path.contains("planks") || path.endsWith("_plank") || path.contains("wood_plank");
    }

    private static boolean isGlassBlock(Block b, IBlockState s) {
        if (b instanceof BlockGlass) {
            return true;
        }
        if (s.func_185904_a() == Material.field_151592_s && !(b instanceof BlockPane)) {
            return true;
        }
        ResourceLocation rl = b.getRegistryName();
        if (rl == null) {
            return false;
        }
        String path = rl.func_110623_a().toLowerCase(Locale.ROOT);
        return path.contains("glass") && !path.contains("pane");
    }

    private static boolean isGlassPaneBlock(Block b) {
        if (b instanceof BlockPane) {
            return true;
        }
        ResourceLocation rl = b.getRegistryName();
        if (rl == null) {
            return false;
        }
        String path = rl.func_110623_a().toLowerCase(Locale.ROOT);
        return path.contains("pane");
    }

    private static boolean isFurnaceBlock(Block b) {
        return b == Blocks.field_150460_al || b == Blocks.field_150470_am || b instanceof BlockFurnace;
    }

    private static boolean isStoneStairs(Block b, IBlockState s) {
        if (b instanceof BlockStairs && s.func_185904_a() == Material.field_151576_e) {
            return true;
        }
        ResourceLocation rl = b.getRegistryName();
        if (rl == null) {
            return false;
        }
        String path = rl.func_110623_a().toLowerCase(Locale.ROOT);
        return path.contains("stairs") && (path.contains("stone") || path.contains("brick") || path.contains("sandstone"));
    }

    private static boolean isWoodStairs(Block b, IBlockState s) {
        if (b instanceof BlockStairs && s.func_185904_a() == Material.field_151575_d) {
            return true;
        }
        ResourceLocation rl = b.getRegistryName();
        if (rl == null) {
            return false;
        }
        String path = rl.func_110623_a().toLowerCase(Locale.ROOT);
        return path.contains("stairs") && (path.contains("oak") || path.contains("spruce") || path.contains("birch") || path.contains("jungle") || path.contains("acacia") || path.contains("dark_oak") || path.contains("wood"));
    }

    private static IBlockState copyCommonProps(IBlockState from, IBlockState to) {
        IBlockState out = to;
        for (IProperty propTo : to.func_177227_a()) {
            if (!from.func_177227_a().contains(propTo)) continue;
            try {
                IProperty raw = propTo;
                Comparable val = from.func_177229_b(raw);
                if (val == null || !raw.func_177700_c().contains(val)) continue;
                out = out.func_177226_a(raw, val);
            }
            catch (Exception exception) {}
        }
        return out;
    }

    private static boolean isCobbleBlock(Block b, IBlockState s) {
        if (b == Blocks.field_150347_e) {
            return true;
        }
        try {
            if (b == Blocks.field_150341_Y) {
                return true;
            }
        }
        catch (NoSuchFieldError noSuchFieldError) {
            // empty catch block
        }
        ResourceLocation rl = b.getRegistryName();
        if (rl == null) {
            return false;
        }
        String path = rl.func_110623_a().toLowerCase(Locale.ROOT);
        return path.contains("cobblestone") && !path.contains("wall") && !path.contains("stairs");
    }

    static {
        conversionCache = new HashMap();
        conversionCache.put(Material.field_151578_c, SPBlocks.InfestedStain.func_176223_P());
        conversionCache.put(Material.field_151577_b, SPBlocks.InfestedStain.func_176223_P());
        conversionCache.put(Material.field_151595_p, SPBlocks.InfestedSand.func_176223_P());
        conversionCache.put(Material.field_151576_e, SPBlocks.InfestedRubble.func_176223_P());
        customConvertCache = new HashMap();
        customConvertHash = 0;
    }

    private static class ParsedState {
        private final Block block;
        private final int meta;
        private final IBlockState state;

        private ParsedState(Block block, int meta, IBlockState state) {
            this.block = block;
            this.meta = meta;
            this.state = state;
        }
    }

    private static class BlockMetaKey {
        private final Block block;
        private final int meta;

        private BlockMetaKey(Block block, int meta) {
            this.block = block;
            this.meta = meta;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BlockMetaKey)) {
                return false;
            }
            BlockMetaKey other = (BlockMetaKey)obj;
            return this.block == other.block && this.meta == other.meta;
        }

        public int hashCode() {
            return System.identityHashCode(this.block) * 31 + this.meta;
        }
    }
}

