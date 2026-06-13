/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.CommandException
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.WrongUsageException
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.Mirror
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Rotation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraft.world.biome.Biome
 *  net.minecraft.world.gen.structure.template.PlacementSettings
 *  net.minecraft.world.gen.structure.template.Template
 *  net.minecraft.world.gen.structure.template.TemplateManager
 */
package com.dhanantry.scapeandrunparasites.network;

import com.dhanantry.scapeandrunparasites.init.SRPBiomes;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class CommandHarlequinScatter
extends CommandBase {
    private static final String[] ROCKS = new String[]{"harlequin_rock_01", "harlequin_rock_02", "harlequin_rock_03", "harlequin_rock_04", "harlequin_rock_05", "harlequin_rock_06", "harlequin_rock_07"};
    private static final String[] BUSHES = new String[]{"harlequin_bush_00", "harlequin_bush_01", "harlequin_bush_02"};
    private static final String[] TREES = new String[]{"harlequin_tree_01", "harlequin_tree_02", "harlequin_tree_03", "harlequin_tree_04", "harlequin_tree_05", "harlequin_tree_06", "harlequin_tree_07"};
    private static final String[] RUINS = new String[]{"harlequin_ruin_01", "harlequin_ruin_02", "harlequin_ruin_03"};

    private static boolean isGoodGround(WorldServer w, BlockPos pos) {
        String id;
        Block b = w.func_180495_p(pos).func_177230_c();
        if (b == Blocks.field_150354_m || b == Blocks.field_150322_A || b == Blocks.field_180395_cM || b == Blocks.field_150349_c || b == Blocks.field_150346_d) {
            return true;
        }
        return b.getRegistryName() != null && ((id = b.getRegistryName().toString()).equals("srparasites:harlequinn_grass") || id.equals("srparasites:harleskinn_block"));
    }

    private static boolean isBadTop(WorldServer w, BlockPos pos) {
        IBlockState s = w.func_180495_p(pos);
        Block b = s.func_177230_c();
        if (b == Blocks.field_150355_j || b == Blocks.field_150358_i || b == Blocks.field_150353_l || b == Blocks.field_150356_k) {
            return true;
        }
        return b.isLeaves(s, (IBlockAccess)w, pos);
    }

    private static boolean flatEnough(WorldServer w, BlockPos center, int radius, int maxDelta) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int dx = -radius; dx <= radius; dx += radius) {
            for (int dz = -radius; dz <= radius; dz += radius) {
                int y = w.func_175645_m(center.func_177982_a(dx, 0, dz)).func_177956_o();
                min = Math.min(min, y);
                max = Math.max(max, y);
            }
        }
        return max - min <= maxDelta;
    }

    private static BlockPos surfaceAt(WorldServer w, BlockPos xz) {
        int y = w.func_175645_m(xz).func_177956_o();
        return new BlockPos(xz.func_177958_n(), y, xz.func_177952_p());
    }

    public String func_71517_b() {
        return "harlequin_scatter";
    }

    public String func_71518_a(ICommandSender s) {
        return "/harlequin_scatter <rock|bush|tree|ruin|both|all> [tries=32] [radius=32]";
    }

    public int func_82362_a() {
        return 2;
    }

    public void func_184881_a(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length < 1) {
            throw new WrongUsageException(this.func_71518_a(sender), new Object[0]);
        }
        String type = args[0].toLowerCase();
        int tries = args.length >= 2 ? CommandHarlequinScatter.func_175764_a((String)args[1], (int)1, (int)4096) : 32;
        int radius = args.length >= 3 ? CommandHarlequinScatter.func_175764_a((String)args[2], (int)8, (int)512) : 32;
        EntityPlayerMP p = CommandHarlequinScatter.func_71521_c((ICommandSender)sender);
        WorldServer w = p.func_71121_q();
        Random rand = w.field_73012_v;
        int placed = 0;
        block16: for (int i = 0; i < tries; ++i) {
            int dz;
            int dx = rand.nextInt(radius * 2 + 1) - radius;
            BlockPos xz = new BlockPos(p.field_70165_t + (double)dx, 0.0, p.field_70161_v + (double)(dz = rand.nextInt(radius * 2 + 1) - radius));
            BlockPos surf = CommandHarlequinScatter.surfaceAt(w, xz);
            Biome b = w.func_180494_b(surf);
            if (b != SRPBiomes.biomeHarlequin || !CommandHarlequinScatter.isGoodGround(w, surf.func_177977_b()) || CommandHarlequinScatter.isBadTop(w, surf)) continue;
            switch (type) {
                case "rock": {
                    if (!CommandHarlequinScatter.flatEnough(w, surf, 5, 3) || !this.placeTemplate(w, surf.func_177979_c(rand.nextInt(2)), CommandHarlequinScatter.pick(ROCKS, rand), rand)) continue block16;
                    ++placed;
                    continue block16;
                }
                case "bush": {
                    int c = 1 + rand.nextInt(3);
                    for (int k = 0; k < c; ++k) {
                        BlockPos around = surf.func_177982_a(rand.nextInt(7) - 3, 0, rand.nextInt(7) - 3);
                        BlockPos s2 = CommandHarlequinScatter.surfaceAt(w, around);
                        if (w.func_180494_b(s2) != SRPBiomes.biomeHarlequin || !CommandHarlequinScatter.isGoodGround(w, s2.func_177977_b()) || CommandHarlequinScatter.isBadTop(w, s2) || !CommandHarlequinScatter.flatEnough(w, s2, 3, 3) || !this.placeTemplate(w, s2, CommandHarlequinScatter.pick(BUSHES, rand), rand)) continue;
                        ++placed;
                    }
                    continue block16;
                }
                case "tree": {
                    if (!CommandHarlequinScatter.flatEnough(w, surf, 6, 3) || !this.placeTemplate(w, surf, CommandHarlequinScatter.pick(TREES, rand), rand)) continue block16;
                    ++placed;
                    continue block16;
                }
                case "ruin": {
                    if (!CommandHarlequinScatter.flatEnough(w, surf, 10, 4) || !this.placeTemplate(w, surf.func_177979_c(rand.nextBoolean() ? 1 : 0), CommandHarlequinScatter.pick(RUINS, rand), rand)) continue block16;
                    ++placed;
                    continue block16;
                }
                case "both": {
                    if (!CommandHarlequinScatter.flatEnough(w, surf, 5, 3)) continue block16;
                    if (this.placeTemplate(w, surf.func_177979_c(rand.nextInt(2)), CommandHarlequinScatter.pick(ROCKS, rand), rand)) {
                        ++placed;
                    }
                    int bc = 1 + rand.nextInt(2);
                    for (int k2 = 0; k2 < bc; ++k2) {
                        BlockPos around2 = surf.func_177982_a(rand.nextInt(7) - 3, 0, rand.nextInt(7) - 3);
                        BlockPos s3 = CommandHarlequinScatter.surfaceAt(w, around2);
                        if (w.func_180494_b(s3) != SRPBiomes.biomeHarlequin || !CommandHarlequinScatter.isGoodGround(w, s3.func_177977_b()) || CommandHarlequinScatter.isBadTop(w, s3) || !CommandHarlequinScatter.flatEnough(w, s3, 3, 3) || !this.placeTemplate(w, s3, CommandHarlequinScatter.pick(BUSHES, rand), rand)) continue;
                        ++placed;
                    }
                    continue block16;
                }
                case "all": {
                    BlockPos rpos;
                    BlockPos tpos;
                    if (CommandHarlequinScatter.flatEnough(w, surf, 5, 3) && this.placeTemplate(w, surf.func_177979_c(rand.nextInt(2)), CommandHarlequinScatter.pick(ROCKS, rand), rand)) {
                        ++placed;
                    }
                    if (w.func_180494_b(tpos = CommandHarlequinScatter.surfaceAt(w, surf.func_177982_a(rand.nextInt(9) - 4, 0, rand.nextInt(9) - 4))) == SRPBiomes.biomeHarlequin && CommandHarlequinScatter.isGoodGround(w, tpos.func_177977_b()) && !CommandHarlequinScatter.isBadTop(w, tpos) && CommandHarlequinScatter.flatEnough(w, tpos, 6, 3) && this.placeTemplate(w, tpos, CommandHarlequinScatter.pick(TREES, rand), rand)) {
                        ++placed;
                    }
                    if (w.func_180494_b(rpos = CommandHarlequinScatter.surfaceAt(w, surf.func_177982_a(rand.nextInt(17) - 8, 0, rand.nextInt(17) - 8))) == SRPBiomes.biomeHarlequin && CommandHarlequinScatter.isGoodGround(w, rpos.func_177977_b()) && !CommandHarlequinScatter.isBadTop(w, rpos) && CommandHarlequinScatter.flatEnough(w, rpos, 10, 4) && this.placeTemplate(w, rpos.func_177979_c(rand.nextBoolean() ? 1 : 0), CommandHarlequinScatter.pick(RUINS, rand), rand)) {
                        ++placed;
                    }
                    int bc2 = 2 + rand.nextInt(3);
                    for (int k3 = 0; k3 < bc2; ++k3) {
                        BlockPos bpos = CommandHarlequinScatter.surfaceAt(w, surf.func_177982_a(rand.nextInt(9) - 4, 0, rand.nextInt(9) - 4));
                        if (w.func_180494_b(bpos) != SRPBiomes.biomeHarlequin || !CommandHarlequinScatter.isGoodGround(w, bpos.func_177977_b()) || CommandHarlequinScatter.isBadTop(w, bpos) || !CommandHarlequinScatter.flatEnough(w, bpos, 3, 3) || !this.placeTemplate(w, bpos, CommandHarlequinScatter.pick(BUSHES, rand), rand)) continue;
                        ++placed;
                    }
                    continue block16;
                }
                default: {
                    throw new WrongUsageException(this.func_71518_a(sender), new Object[0]);
                }
            }
        }
        sender.func_145747_a((ITextComponent)new TextComponentString("Harlequin scatter: placed ~" + placed + " (type=" + type + ", tries=" + tries + ", radius=" + radius + ")"));
    }

    private boolean placeTemplate(WorldServer w, BlockPos at, String name, Random rand) {
        TemplateManager mgr = w.func_72860_G().func_186340_h();
        Template tpl = mgr.func_186237_a(w.func_73046_m(), new ResourceLocation("srparasites", name));
        if (tpl == null) {
            return false;
        }
        Rotation rot = Rotation.values()[rand.nextInt(Rotation.values().length)];
        Mirror mir = rand.nextBoolean() ? Mirror.NONE : Mirror.FRONT_BACK;
        PlacementSettings settings = new PlacementSettings().func_186220_a(rot).func_186214_a(mir).func_186222_a(true).func_186226_b(true);
        tpl.func_186253_b((World)w, at, settings);
        return true;
    }

    private static String pick(String[] arr, Random r) {
        return arr[r.nextInt(arr.length)];
    }
}

