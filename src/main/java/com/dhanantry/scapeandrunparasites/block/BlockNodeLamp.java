/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.properties.IProperty
 *  net.minecraft.block.properties.PropertyBool
 *  net.minecraft.block.properties.PropertyInteger
 *  net.minecraft.block.state.BlockStateContainer
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentTranslation
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.block.BlockBase;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityDod;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityDodSII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityDodSIII;
import com.dhanantry.scapeandrunparasites.entity.monster.deterrent.nexus.EntityDodSIV;
import com.dhanantry.scapeandrunparasites.util.LangHelper;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockNodeLamp
extends BlockBase {
    public static final PropertyBool POWERED = PropertyBool.func_177716_a((String)"powered");
    public static final PropertyInteger RANGE_LEVEL = PropertyInteger.func_177719_a((String)"range_level", (int)0, (int)5);

    public BlockNodeLamp() {
        super(Material.field_151591_t, "node_redstone_lamp", 0.3f, true, true);
        this.func_149672_a(SoundType.field_185853_f);
        this.func_180632_j(this.field_176227_L.func_177621_b().func_177226_a((IProperty)POWERED, (Comparable)Boolean.valueOf(false)).func_177226_a((IProperty)RANGE_LEVEL, (Comparable)Integer.valueOf(0)));
        this.func_149675_a(true);
    }

    protected BlockStateContainer func_180661_e() {
        return new BlockStateContainer((Block)this, new IProperty[]{POWERED, RANGE_LEVEL});
    }

    public IBlockState func_176203_a(int meta) {
        boolean powered = (meta & 8) != 0;
        int level = meta & 7;
        return this.func_176223_P().func_177226_a((IProperty)POWERED, (Comparable)Boolean.valueOf(powered)).func_177226_a((IProperty)RANGE_LEVEL, (Comparable)Integer.valueOf(level));
    }

    public int func_176201_c(IBlockState state) {
        int meta = (Integer)state.func_177229_b((IProperty)RANGE_LEVEL);
        if (((Boolean)state.func_177229_b((IProperty)POWERED)).booleanValue()) {
            meta |= 8;
        }
        return meta;
    }

    public boolean func_149744_f(IBlockState state) {
        return false;
    }

    public int func_180656_a(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        return 0;
    }

    public int func_176211_b(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing side) {
        return 0;
    }

    public boolean func_149740_M(IBlockState state) {
        return true;
    }

    public int func_180641_l(IBlockState state, World world, BlockPos pos) {
        return (Integer)state.func_177229_b((IProperty)RANGE_LEVEL) * 3;
    }

    public void func_180650_b(World world, BlockPos pos, IBlockState state, Random rand) {
        if (!world.field_72995_K) {
            this.updateLampState(world, pos, state);
            world.func_175684_a(pos, (Block)this, 100);
        }
    }

    public void func_176213_c(World world, BlockPos pos, IBlockState state) {
        super.func_176213_c(world, pos, state);
        if (!world.field_72995_K) {
            this.updateLampState(world, pos, state);
            world.func_175684_a(pos, (Block)this, 20);
        }
    }

    public void func_189540_a(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!world.field_72995_K) {
            this.updateLampState(world, pos, state);
        }
    }

    private boolean updateLampState(World world, BlockPos pos, IBlockState state) {
        boolean changed;
        int newLevel = 0;
        List entities = world.func_72872_a(Entity.class, new AxisAlignedBB(pos).func_186662_g(250.0));
        double nearestDistSq = Double.MAX_VALUE;
        for (Entity e : entities) {
            double distSq;
            if (e == null || !e.func_70089_S() || !(e instanceof EntityDod) && !(e instanceof EntityDodSII) && !(e instanceof EntityDodSIII) && !(e instanceof EntityDodSIV) || !((distSq = e.func_174818_b(pos)) < nearestDistSq)) continue;
            nearestDistSq = distSq;
        }
        if (nearestDistSq <= 2500.0) {
            newLevel = 5;
        } else if (nearestDistSq <= 5625.0) {
            newLevel = 4;
        } else if (nearestDistSq <= 10000.0) {
            newLevel = 3;
        } else if (nearestDistSq <= 22500.0) {
            newLevel = 2;
        } else if (nearestDistSq <= 40000.0) {
            newLevel = 1;
        } else if (nearestDistSq <= 62500.0) {
            newLevel = 0;
        }
        boolean powered = newLevel > 0;
        boolean bl = changed = powered != (Boolean)state.func_177229_b((IProperty)POWERED) || newLevel != (Integer)state.func_177229_b((IProperty)RANGE_LEVEL);
        if (changed) {
            world.func_180501_a(pos, state.func_177226_a((IProperty)POWERED, (Comparable)Boolean.valueOf(powered)).func_177226_a((IProperty)RANGE_LEVEL, (Comparable)Integer.valueOf(newLevel)), 3);
            world.func_175664_x(pos);
            world.func_175666_e(pos, (Block)this);
        }
        return changed;
    }

    private static String distanceColored(int displayTier) {
        switch (displayTier) {
            case 1: {
                return "\u00a7c1";
            }
            case 2: {
                return "\u00a762";
            }
            case 3: {
                return "\u00a7e3";
            }
            case 4: {
                return "\u00a7a4";
            }
            case 5: {
                return "\u00a725";
            }
        }
        return "\u00a770";
    }

    public boolean func_180639_a(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (world.field_72995_K) {
            return true;
        }
        boolean powered = (Boolean)state.func_177229_b((IProperty)POWERED);
        int maxStrength = 0;
        double nearestDistSq = Double.MAX_VALUE;
        List entities = world.func_72872_a(Entity.class, new AxisAlignedBB(pos).func_186662_g(250.0));
        for (Entity e : entities) {
            double d;
            if (e == null || !e.func_70089_S()) continue;
            int s = 0;
            if (e instanceof EntityDod) {
                s = 1;
            } else if (e instanceof EntityDodSII) {
                s = 2;
            } else if (e instanceof EntityDodSIII) {
                s = 3;
            } else if (e instanceof EntityDodSIV) {
                s = 4;
            }
            if (s <= 0) continue;
            if (s > maxStrength) {
                maxStrength = s;
            }
            if (!((d = e.func_174818_b(pos)) < nearestDistSq)) continue;
            nearestDistSq = d;
        }
        int distanceTierRaw = 0;
        if (nearestDistSq != Double.MAX_VALUE) {
            if (nearestDistSq <= 2500.0) {
                distanceTierRaw = 5;
            } else if (nearestDistSq <= 5625.0) {
                distanceTierRaw = 4;
            } else if (nearestDistSq <= 10000.0) {
                distanceTierRaw = 3;
            } else if (nearestDistSq <= 22500.0) {
                distanceTierRaw = 2;
            } else if (nearestDistSq <= 40000.0) {
                distanceTierRaw = 1;
            } else if (nearestDistSq <= 62500.0) {
                distanceTierRaw = 0;
            }
        }
        if (!powered) {
            maxStrength = 0;
            distanceTierRaw = 0;
        }
        int displayDistanceTier = distanceTierRaw == 0 ? 0 : 6 - distanceTierRaw;
        String strengthLabel = maxStrength == 0 ? "0" : LangHelper.toRoman(maxStrength);
        String distanceLabel = BlockNodeLamp.distanceColored(displayDistanceTier);
        player.func_145747_a((ITextComponent)new TextComponentTranslation("message.srparasites.node_lamp.strength", new Object[]{strengthLabel}));
        player.func_145747_a((ITextComponent)new TextComponentTranslation("message.srparasites.node_lamp.distance", new Object[]{distanceLabel}));
        return true;
    }
}

