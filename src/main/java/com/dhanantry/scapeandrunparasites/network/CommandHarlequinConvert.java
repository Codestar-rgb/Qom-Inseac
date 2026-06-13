/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockLeaves
 *  net.minecraft.block.BlockSand
 *  net.minecraft.block.BlockSandStone
 *  net.minecraft.block.material.Material
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.init.Blocks
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.BlockPos$MutableBlockPos
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.world.World
 */
package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.init.SRPBlocks;
import com.dhanantry.scapeandrunparasites.util.convert.HarlequinBlockConverter;
import java.util.HashSet;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSandStone;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class CommandHarlequinConvert
extends CommandBase {
    public String func_71517_b() {
        return "harlequin_convert";
    }

    public String func_71518_a(ICommandSender sender) {
        return "/harlequin_convert [radius] [sampleChance] [depth] [blotchChance] [blotchMinDiam] [blotchMaxDiam]";
    }

    public int func_82362_a() {
        return 2;
    }

    public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        int z;
        int x;
        int dz;
        int dx;
        World world = sender.func_130014_f_();
        if (world.field_72995_K) {
            sender.func_145747_a((ITextComponent)new TextComponentString("[HarlequinConvert] (client) \u2013 no changes made"));
            return;
        }
        int radius = args.length >= 1 ? CommandHarlequinConvert.func_175764_a((String)args[0], (int)1, (int)256) : 16;
        int sampleChance = args.length >= 2 ? CommandHarlequinConvert.func_175764_a((String)args[1], (int)1, (int)128) : 1;
        int depth = args.length >= 3 ? CommandHarlequinConvert.func_175764_a((String)args[2], (int)1, (int)256) : 32;
        int blotchChance = args.length >= 4 ? CommandHarlequinConvert.func_175764_a((String)args[3], (int)1, (int)256) : 18;
        int blotchMinDiam = args.length >= 5 ? CommandHarlequinConvert.func_175764_a((String)args[4], (int)1, (int)64) : 3;
        int blotchMaxDiam = args.length >= 6 ? CommandHarlequinConvert.func_175764_a((String)args[5], (int)1, (int)64) : 5;
        BlockPos center = sender.func_180425_c();
        Random rand = world.field_73012_v;
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(center);
        HarlequinBlockConverter.Config cfg = new HarlequinBlockConverter.Config(SRPBlocks.HarleskinnBlock, SRPBlocks.HarlequinnGrass, SRPBlocks.Alveoli, SRPBlocks.AlveoliGrowth, SRPBlocks.LipomaMass, SRPBlocks.TressesHair, SRPBlocks.HirsuteHair);
        int changed = 0;
        HashSet<Long> blotchXZ = new HashSet<Long>();
        int blotchesSeeded = 0;
        int blotchCellsMarked = 0;
        int blotchCellsConverted = 0;
        for (dx = -radius; dx <= radius; ++dx) {
            for (dz = -radius; dz <= radius; ++dz) {
                boolean seedThisColumn;
                x = center.func_177958_n() + dx;
                z = center.func_177952_p() + dz;
                boolean bl = seedThisColumn = blotchChance <= 1 || rand.nextInt(Math.max(2, blotchChance)) == 0;
                if (!seedThisColumn) continue;
                int minD = Math.min(blotchMinDiam, blotchMaxDiam);
                int maxD = Math.max(blotchMinDiam, blotchMaxDiam);
                int diameter = minD + rand.nextInt(maxD - minD + 1);
                int r = diameter / 2;
                boolean anyMarked = false;
                for (int ox = -r; ox <= r; ++ox) {
                    for (int oz = -r; oz <= r; ++oz) {
                        boolean wouldBecomeHarlequinn;
                        int sz;
                        int sx;
                        int ySurf;
                        if (ox * ox + oz * oz > r * r || (ySurf = world.func_175645_m(new BlockPos(sx = x + ox, 0, sz = z + oz)).func_177956_o() - 1) < 0) continue;
                        BlockPos top = new BlockPos(sx, ySurf, sz);
                        IBlockState sTop = world.func_180495_p(top);
                        Block topB = sTop.func_177230_c();
                        boolean bl2 = wouldBecomeHarlequinn = world.func_175678_i(top.func_177984_a()) && (sTop.func_177230_c() instanceof BlockSand || sTop.func_177230_c() instanceof BlockSandStone || topB == Blocks.field_180395_cM || topB == Blocks.field_150348_b);
                        if (topB != Blocks.field_150349_c && !wouldBecomeHarlequinn || !blotchXZ.add(CommandHarlequinConvert.keyXZ(sx, sz))) continue;
                        ++blotchCellsMarked;
                        anyMarked = true;
                    }
                }
                if (!anyMarked) continue;
                ++blotchesSeeded;
            }
        }
        for (dx = -radius; dx <= radius; ++dx) {
            for (dz = -radius; dz <= radius; ++dz) {
                x = center.func_177958_n() + dx;
                int yTop = world.func_175645_m(new BlockPos(x, 0, z = center.func_177952_p() + dz)).func_177956_o() - 1;
                if (yTop < 0) continue;
                for (int y = yTop; y >= Math.max(5, yTop - depth); --y) {
                    BlockPos below;
                    Block replacedWith;
                    Material mat;
                    pos.func_181079_c(x, y, z);
                    IBlockState state = world.func_180495_p((BlockPos)pos);
                    Block b = state.func_177230_c();
                    boolean blotchHere = blotchXZ.contains(CommandHarlequinConvert.keyXZ(x, z));
                    if (!blotchHere && !(b instanceof BlockLeaves) && rand.nextInt(Math.max(1, sampleChance)) != 0 || (mat = state.func_185904_a()) == Material.field_151579_a || mat == Material.field_151586_h || mat == Material.field_151587_i || (replacedWith = HarlequinBlockConverter.convert(world, (BlockPos)pos, state, blotchHere, rand, cfg)) == null) continue;
                    if (replacedWith != cfg.ALVEOLI && rand.nextInt(100) < 30 && world.func_175623_d(below = pos.func_177977_b())) {
                        world.func_180501_a(below, cfg.LIPOMA.func_176223_P(), 2);
                    }
                    ++changed;
                    if (!blotchHere || state.func_177230_c() != Blocks.field_150349_c || replacedWith != cfg.HARLESKINN) continue;
                    ++blotchCellsConverted;
                }
            }
        }
        sender.func_145747_a((ITextComponent)new TextComponentString("Harlequin convert: changed ~" + changed + " | blotches: " + blotchesSeeded + " | cells marked: " + blotchCellsMarked + " | cells converted: " + blotchCellsConverted + " | radius " + radius + " | 1-in-" + sampleChance + " | depth " + depth + " | blotchChance 1-in-" + Math.max(1, blotchChance) + " | blotchDiam " + Math.min(blotchMinDiam, blotchMaxDiam) + ".." + Math.max(blotchMinDiam, blotchMaxDiam)));
    }

    private static long keyXZ(int x, int z) {
        return (long)x << 32 ^ (long)z & 0xFFFFFFFFL;
    }
}

