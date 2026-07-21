package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.SRPMain;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBeholderPearl extends Item {
   private static final ResourceLocation ID_SIM = new ResourceLocation("srparasites", "sim_enderman");
   private static final ResourceLocation ID_SIM_HEAD = new ResourceLocation("srparasites", "sim_endermanhead");
   private static final ResourceLocation ID_FERAL = new ResourceLocation("srparasites", "fer_enderman");
   private static final ResourceLocation ID_ASSIMARA = new ResourceLocation("srparasites", "mar_enderman");
   private static final ResourceLocation KEY_PEARL_STATE = new ResourceLocation("srparasites", "pearl_state");
   private static final double SCAN_RADIUS = 100.0;
   private static final int UPDATE_INTERVAL = 6;
   private static final Map<Integer, ItemBeholderPearl.Cache> CACHE = new HashMap<>();

   public ItemBeholderPearl() {
      this.setRegistryName("srparasites", "pearl");
      this.func_77655_b("srparasites.pearl");
      this.func_77625_d(64);
      this.func_77637_a(SRPMain.SRP_CREATIVETAB);
      this.func_185043_a(
         KEY_PEARL_STATE,
         (stack, worldIn, entityIn) -> {
            if (worldIn != null && entityIn != null) {
               boolean holding = !entityIn.func_184614_ca().func_190926_b() && entityIn.func_184614_ca().func_77973_b() == this
                  || !entityIn.func_184592_cb().func_190926_b() && entityIn.func_184592_cb().func_77973_b() == this;
               if (!holding) {
                  CACHE.remove(entityIn.func_145782_y());
                  return 0.0F;
               } else {
                  long now = worldIn.func_82737_E();
                  ItemBeholderPearl.Cache c = CACHE.get(entityIn.func_145782_y());
                  if (c == null) {
                     c = new ItemBeholderPearl.Cache();
                     CACHE.put(entityIn.func_145782_y(), c);
                  }

                  c.lastAccessTick = now;
                  if (now < c.nextScanTick) {
                     return c.lastVal;
                  } else {
                     c.nextScanTick = now + 6L;
                     if ((now & 255L) == 0L) {
                        long cutoff = now - 600L;
                        CACHE.entrySet().removeIf(e -> e.getValue().lastAccessTick < cutoff);
                     }

                     double R = 100.0;
                     AxisAlignedBB box = new AxisAlignedBB(
                        entityIn.field_70165_t - 100.0,
                        entityIn.field_70163_u - 100.0,
                        entityIn.field_70161_v - 100.0,
                        entityIn.field_70165_t + 100.0,
                        entityIn.field_70163_u + 100.0,
                        entityIn.field_70161_v + 100.0
                     );
                     List<Entity> matches = worldIn.func_175647_a(Entity.class, box, e -> {
                        if (e != null && !e.field_70128_L && e != entityIn) {
                           ResourceLocation idx = EntityList.func_191301_a(e);
                           return idx != null && (idx.equals(ID_ASSIMARA) || idx.equals(ID_FERAL) || idx.equals(ID_SIM) || idx.equals(ID_SIM_HEAD));
                        } else {
                           return false;
                        }
                     });
                     int tier = 0;
                     double nearestSq = Double.POSITIVE_INFINITY;

                     for (Entity e : matches) {
                        ResourceLocation id = EntityList.func_191301_a(e);
                        if (id != null) {
                           int candidate = id.equals(ID_ASSIMARA) ? 3 : (id.equals(ID_FERAL) ? 2 : (!id.equals(ID_SIM) && !id.equals(ID_SIM_HEAD) ? 0 : 1));
                           if (candidate != 0) {
                              double d2 = e.func_70068_e(entityIn);
                              if (candidate > tier || candidate == tier && d2 < nearestSq) {
                                 tier = candidate;
                                 nearestSq = d2;
                                 if (candidate == 3) {
                                    break;
                                 }
                              }
                           }
                        }
                     }

                     float out;
                     if (tier == 0) {
                        out = 0.0F;
                     } else {
                        double dist = Math.sqrt(nearestSq);
                        float proximity = (float)Math.max(0.0, Math.min(1.0, (100.0 - dist) / 100.0));
                        long t = now + entityIn.field_70173_aa;
                        float freq = 1.5F + 1.0F * tier + 4.0F * proximity;
                        float omega = (float)(freq * 2.0 * Math.PI / 20.0);
                        float osc = (float)Math.sin((float)t * omega);
                        float amp = 0.12F + 0.15F * tier + 0.25F * proximity;
                        float base = tier;
                        float val = base + 0.1F + amp * osc;
                        val = Math.max(base + 0.05F, Math.min(base + 0.45F, val));
                        out = val;
                     }

                     c.lastVal = out;
                     return out;
                  }
               }
            } else {
               return 0.0F;
            }
         }
      );
   }

   @SideOnly(Side.CLIENT)
   public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
      tooltip.add(TextFormatting.GRAY + I18n.func_135052_a("tooltip.srparasites.pearl.desc", new Object[0]));
      tooltip.add("");
      tooltip.add(TextFormatting.LIGHT_PURPLE + I18n.func_135052_a("tooltip.srparasites.pearl.assimilated", new Object[0]));
      tooltip.add(TextFormatting.RED + I18n.func_135052_a("tooltip.srparasites.pearl.feral", new Object[0]));
      tooltip.add(TextFormatting.BLUE + I18n.func_135052_a("tooltip.srparasites.pearl.assimara", new Object[0]));
   }

   private static final class Cache {
      long nextScanTick;
      long lastAccessTick;
      float lastVal;

      private Cache() {
      }
   }
}
