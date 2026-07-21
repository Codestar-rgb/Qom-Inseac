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
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;

public class CommandHarlequinScatter extends CommandBase {
   private static final String[] ROCKS = new String[]{
      "harlequin_rock_01", "harlequin_rock_02", "harlequin_rock_03", "harlequin_rock_04", "harlequin_rock_05", "harlequin_rock_06", "harlequin_rock_07"
   };
   private static final String[] BUSHES = new String[]{"harlequin_bush_00", "harlequin_bush_01", "harlequin_bush_02"};
   private static final String[] TREES = new String[]{
      "harlequin_tree_01", "harlequin_tree_02", "harlequin_tree_03", "harlequin_tree_04", "harlequin_tree_05", "harlequin_tree_06", "harlequin_tree_07"
   };
   private static final String[] RUINS = new String[]{"harlequin_ruin_01", "harlequin_ruin_02", "harlequin_ruin_03"};

   private static boolean isGoodGround(WorldServer w, BlockPos pos) {
      Block b = w.func_180495_p(pos).func_177230_c();
      if (b != Blocks.field_150354_m && b != Blocks.field_150322_A && b != Blocks.field_180395_cM && b != Blocks.field_150349_c && b != Blocks.field_150346_d) {
         if (b.getRegistryName() != null) {
            String id = b.getRegistryName().toString();
            if (id.equals("srparasites:harlequinn_grass") || id.equals("srparasites:harleskinn_block")) {
               return true;
            }
         }

         return false;
      } else {
         return true;
      }
   }

   private static boolean isBadTop(WorldServer w, BlockPos pos) {
      IBlockState s = w.func_180495_p(pos);
      Block b = s.func_177230_c();
      return b != Blocks.field_150355_j && b != Blocks.field_150358_i && b != Blocks.field_150353_l && b != Blocks.field_150356_k
         ? b.isLeaves(s, w, pos)
         : true;
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
      } else {
         String type = args[0].toLowerCase();
         int tries = args.length >= 2 ? func_175764_a(args[1], 1, 4096) : 32;
         int radius = args.length >= 3 ? func_175764_a(args[2], 8, 512) : 32;
         EntityPlayerMP p = func_71521_c(sender);
         WorldServer w = p.func_71121_q();
         Random rand = w.field_73012_v;
         int placed = 0;

         for (int i = 0; i < tries; i++) {
            int dx = rand.nextInt(radius * 2 + 1) - radius;
            int dz = rand.nextInt(radius * 2 + 1) - radius;
            BlockPos xz = new BlockPos(p.field_70165_t + dx, 0.0, p.field_70161_v + dz);
            BlockPos surf = surfaceAt(w, xz);
            Biome b = w.func_180494_b(surf);
            if (b == SRPBiomes.biomeHarlequin && isGoodGround(w, surf.func_177977_b()) && !isBadTop(w, surf)) {
               switch (type) {
                  case "rock":
                     if (flatEnough(w, surf, 5, 3) && this.placeTemplate(w, surf.func_177979_c(rand.nextInt(2)), pick(ROCKS, rand), rand)) {
                        placed++;
                     }
                     break;
                  case "bush":
                     int c = 1 + rand.nextInt(3);

                     for (int k = 0; k < c; k++) {
                        BlockPos around = surf.func_177982_a(rand.nextInt(7) - 3, 0, rand.nextInt(7) - 3);
                        BlockPos s2 = surfaceAt(w, around);
                        if (w.func_180494_b(s2) == SRPBiomes.biomeHarlequin
                           && isGoodGround(w, s2.func_177977_b())
                           && !isBadTop(w, s2)
                           && flatEnough(w, s2, 3, 3)
                           && this.placeTemplate(w, s2, pick(BUSHES, rand), rand)) {
                           placed++;
                        }
                     }
                     break;
                  case "tree":
                     if (flatEnough(w, surf, 6, 3) && this.placeTemplate(w, surf, pick(TREES, rand), rand)) {
                        placed++;
                     }
                     break;
                  case "ruin":
                     if (flatEnough(w, surf, 10, 4) && this.placeTemplate(w, surf.func_177979_c(rand.nextBoolean() ? 1 : 0), pick(RUINS, rand), rand)) {
                        placed++;
                     }
                     break;
                  case "both":
                     if (flatEnough(w, surf, 5, 3)) {
                        if (this.placeTemplate(w, surf.func_177979_c(rand.nextInt(2)), pick(ROCKS, rand), rand)) {
                           placed++;
                        }

                        int bc = 1 + rand.nextInt(2);

                        for (int k2 = 0; k2 < bc; k2++) {
                           BlockPos around2 = surf.func_177982_a(rand.nextInt(7) - 3, 0, rand.nextInt(7) - 3);
                           BlockPos s3 = surfaceAt(w, around2);
                           if (w.func_180494_b(s3) == SRPBiomes.biomeHarlequin
                              && isGoodGround(w, s3.func_177977_b())
                              && !isBadTop(w, s3)
                              && flatEnough(w, s3, 3, 3)
                              && this.placeTemplate(w, s3, pick(BUSHES, rand), rand)) {
                              placed++;
                           }
                        }
                     }
                     break;
                  case "all":
                     if (flatEnough(w, surf, 5, 3) && this.placeTemplate(w, surf.func_177979_c(rand.nextInt(2)), pick(ROCKS, rand), rand)) {
                        placed++;
                     }

                     BlockPos tpos = surfaceAt(w, surf.func_177982_a(rand.nextInt(9) - 4, 0, rand.nextInt(9) - 4));
                     if (w.func_180494_b(tpos) == SRPBiomes.biomeHarlequin
                        && isGoodGround(w, tpos.func_177977_b())
                        && !isBadTop(w, tpos)
                        && flatEnough(w, tpos, 6, 3)
                        && this.placeTemplate(w, tpos, pick(TREES, rand), rand)) {
                        placed++;
                     }

                     BlockPos rpos = surfaceAt(w, surf.func_177982_a(rand.nextInt(17) - 8, 0, rand.nextInt(17) - 8));
                     if (w.func_180494_b(rpos) == SRPBiomes.biomeHarlequin
                        && isGoodGround(w, rpos.func_177977_b())
                        && !isBadTop(w, rpos)
                        && flatEnough(w, rpos, 10, 4)
                        && this.placeTemplate(w, rpos.func_177979_c(rand.nextBoolean() ? 1 : 0), pick(RUINS, rand), rand)) {
                        placed++;
                     }

                     int bc2 = 2 + rand.nextInt(3);

                     for (int k3 = 0; k3 < bc2; k3++) {
                        BlockPos bpos = surfaceAt(w, surf.func_177982_a(rand.nextInt(9) - 4, 0, rand.nextInt(9) - 4));
                        if (w.func_180494_b(bpos) == SRPBiomes.biomeHarlequin
                           && isGoodGround(w, bpos.func_177977_b())
                           && !isBadTop(w, bpos)
                           && flatEnough(w, bpos, 3, 3)
                           && this.placeTemplate(w, bpos, pick(BUSHES, rand), rand)) {
                           placed++;
                        }
                     }
                     break;
                  default:
                     throw new WrongUsageException(this.func_71518_a(sender), new Object[0]);
               }
            }
         }

         sender.func_145747_a(
            new TextComponentString("Harlequin scatter: placed ~" + placed + " (type=" + type + ", tries=" + tries + ", radius=" + radius + ")")
         );
      }
   }

   private boolean placeTemplate(WorldServer w, BlockPos at, String name, Random rand) {
      TemplateManager mgr = w.func_72860_G().func_186340_h();
      Template tpl = mgr.func_186237_a(w.func_73046_m(), new ResourceLocation("srparasites", name));
      if (tpl == null) {
         return false;
      } else {
         Rotation rot = Rotation.values()[rand.nextInt(Rotation.values().length)];
         Mirror mir = rand.nextBoolean() ? Mirror.NONE : Mirror.FRONT_BACK;
         PlacementSettings settings = new PlacementSettings().func_186220_a(rot).func_186214_a(mir).func_186222_a(true).func_186226_b(true);
         tpl.func_186253_b(w, at, settings);
         return true;
      }
   }

   private static String pick(String[] arr, Random r) {
      return arr[r.nextInt(arr.length)];
   }
}
