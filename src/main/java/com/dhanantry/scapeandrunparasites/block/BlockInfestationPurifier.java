package com.dhanantry.scapeandrunparasites.block;

import com.dhanantry.scapeandrunparasites.init.SRPSoundTypes;
import com.dhanantry.scapeandrunparasites.init.SRPSounds;
import com.dhanantry.scapeandrunparasites.network.MsgSpawnPureParticles;
import com.dhanantry.scapeandrunparasites.network.SRPNetwork;
import com.google.common.base.Predicate;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;

public class BlockInfestationPurifier extends BlockBase {
   private static final int ENTITY_SEARCH_RADIUS = 200;
   private static final int CONNECT_MAX_NODES = 40000;
   private static final int CONNECT_MAX_DISTANCE = 200;
   private static final String MAPPING_FILE = "srparasites_purify_mappings.txt";
   private static final ResourceLocation[] BECKON_IDS = new ResourceLocation[]{
      new ResourceLocation("srparasites", "beckon_si"),
      new ResourceLocation("srparasites", "beckon_sii"),
      new ResourceLocation("srparasites", "beckon_siii"),
      new ResourceLocation("srparasites", "beckon_siv")
   };

   public BlockInfestationPurifier(String name) {
      super(Material.field_151576_e, name, 2.0F, true, false);
      this.func_149675_a(false);
      this.func_149672_a(SRPSoundTypes.PURIFIER);
   }

   public boolean hasTileEntity(IBlockState state) {
      return true;
   }

   public TileEntity createTileEntity(World world, IBlockState state) {
      return new TileEntityInfestationPurifier();
   }

   public boolean func_180639_a(
      World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ
   ) {
      ItemStack held = player.func_184586_b(hand);
      boolean hasDiamond = !held.func_190926_b() && held.func_77973_b() == Items.field_151045_i;
      BlockPos below = pos.func_177977_b();
      IBlockState belowState = world.func_180495_p(below);
      PurifyMappings.ensureLoaded(world, "srparasites_purify_mappings.txt");
      if (!hasDiamond) {
         if (!world.field_72995_K) {
            player.func_146105_b(new TextComponentTranslation("message.srparasites.purifier_use_diamond", new Object[0]), true);
         }

         return true;
      } else if (!PurifyMappings.isSrp(belowState)) {
         if (!world.field_72995_K) {
            player.func_146105_b(new TextComponentTranslation("message.srparasites.purifier_not_over_srp", new Object[0]), true);
         }

         return true;
      } else if (world.field_72995_K) {
         return true;
      } else {
         int connectedCount = this.checkAndGlowConnectedBeckons(world, pos, below);
         if (connectedCount > 0) {
            player.func_146105_b(new TextComponentTranslation("message.srparasites.purifier_entities_marked", new Object[]{connectedCount}), true);
            return true;
         } else {
            TileEntity te = world.func_175625_s(pos);
            if (te instanceof TileEntityInfestationPurifier) {
               world.func_184133_a(null, pos, SRPSounds.PURIFIER_USE, SoundCategory.BLOCKS, 0.9F, 1.0F);
               double cx = pos.func_177958_n() + 0.5;
               double cy = pos.func_177956_o() + 0.9;
               double cz = pos.func_177952_p() + 0.5;
               SRPNetwork.CHANNEL
                  .sendToAllAround(new MsgSpawnPureParticles(cx, cy, cz, 24, 1), new TargetPoint(world.field_73011_w.getDimension(), cx, cy, cz, 64.0));
               this.spawnStartPulse(world, pos);
               ((TileEntityInfestationPurifier)te).startAt(below, player.func_110124_au());
               player.func_146105_b(new TextComponentTranslation("message.srparasites.purifier_started", new Object[0]), true);
            }

            return true;
         }
      }
   }

   private int checkAndGlowConnectedBeckons(final World world, BlockPos pos, BlockPos startSrp) {
      AxisAlignedBB box = new AxisAlignedBB(
         pos.func_177958_n() - 200,
         pos.func_177956_o() - 96,
         pos.func_177952_p() - 200,
         pos.func_177958_n() + 200 + 1,
         pos.func_177956_o() + 96,
         pos.func_177952_p() + 200 + 1
      );
      final Map<BlockPos, List<EntityLivingBase>> beckonsByFoot = new HashMap<>();
      List<Entity> _unused = world.func_175647_a(
         Entity.class,
         box,
         new Predicate<Entity>() {
            public boolean apply(Entity e) {
               if (!(e instanceof EntityLivingBase)) {
                  return false;
               } else {
                  ResourceLocation id = EntityList.func_191301_a(e);
                  if (id == null) {
                     return false;
                  } else {
                     boolean isBeckon = id.equals(new ResourceLocation("srparasites", "beckon_si"))
                        || id.equals(new ResourceLocation("srparasites", "beckon_sii"))
                        || id.equals(new ResourceLocation("srparasites", "beckon_siii"))
                        || id.equals(new ResourceLocation("srparasites", "beckon_siv"));
                     if (!isBeckon) {
                        return false;
                     } else {
                        BlockPos under = new BlockPos(
                           MathHelper.func_76128_c(e.field_70165_t), MathHelper.func_76128_c(e.field_70163_u) - 1, MathHelper.func_76128_c(e.field_70161_v)
                        );
                        if (!PurifyMappings.isSrp(world.func_180495_p(under))) {
                           return false;
                        } else {
                           beckonsByFoot.computeIfAbsent(under, k -> new ArrayList<>()).add((EntityLivingBase)e);
                           return true;
                        }
                     }
                  }
               }
            }
         }
      );
      return beckonsByFoot.isEmpty() ? 0 : this.bfsGlowConnected(world, startSrp, beckonsByFoot);
   }

   private int bfsGlowConnected(World world, BlockPos start, Map<BlockPos, List<EntityLivingBase>> beckonsByFoot) {
      ArrayDeque<BlockPos> q = new ArrayDeque<>();
      HashSet<Long> seen = new HashSet<>();
      int maxDistSq = 40000;
      q.add(start);
      seen.add(start.func_177986_g());
      HashSet<Long> targets = new HashSet<>();

      for (BlockPos bp : beckonsByFoot.keySet()) {
         targets.add(bp.func_177986_g());
      }

      int nodes = 0;
      int connected = 0;

      while (!q.isEmpty() && nodes < 40000 && !targets.isEmpty()) {
         BlockPos cur = q.poll();
         nodes++;
         long curKey = cur.func_177986_g();
         if (targets.contains(curKey)) {
            List<EntityLivingBase> here = beckonsByFoot.get(cur);
            if (here != null) {
               for (EntityLivingBase elb : here) {
                  elb.func_70690_d(new PotionEffect(MobEffects.field_188423_x, 600, 0, false, true));
               }

               connected += here.size();
            }

            targets.remove(curKey);
         }

         for (EnumFacing f : EnumFacing.field_82609_l) {
            BlockPos nxt = cur.func_177972_a(f);
            long k = nxt.func_177986_g();
            if (seen.add(k) && !(start.func_177951_i(nxt) > maxDistSq) && PurifyMappings.isSrp(world.func_180495_p(nxt))) {
               q.add(nxt);
            }
         }
      }

      return connected;
   }

   static IBlockState tryCopyCommonProps(IBlockState from, IBlockState to) {
      IBlockState out = to;

      for (IProperty<?> propTo : to.func_177227_a()) {
         if (from.func_177227_a().contains(propTo)) {
            try {
               Object val = from.func_177229_b(propTo);
               if (val != null && propTo.func_177700_c().contains(val)) {
                  out = out.func_177226_a(propTo, (Comparable)val);
               }
            } catch (Exception var7) {
            }
         }
      }

      return out;
   }

   private void spawnStartPulse(World world, BlockPos pos) {
      if (world instanceof WorldServer) {
         WorldServer ws = (WorldServer)world;
         double cx = pos.func_177958_n() + 0.5;
         double cy = pos.func_177956_o() + 0.8;
         double cz = pos.func_177952_p() + 0.5;

         for (int i = 0; i < 28; i++) {
            double angle = (Math.PI * 2) * i / 28.0;
            double radius = 0.9;
            double x = cx + Math.cos(angle) * radius;
            double z = cz + Math.sin(angle) * radius;
            ws.func_175739_a(EnumParticleTypes.REDSTONE, x, cy, z, 0, 0.1, 0.35, 1.0, 1.0, new int[0]);
            ws.func_175739_a(EnumParticleTypes.PORTAL, x, cy + 0.05, z, 1, 0.0, 0.01, 0.0, 0.0, new int[0]);
         }
      }
   }
}
