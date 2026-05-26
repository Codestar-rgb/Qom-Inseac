/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.client.util.ITooltipFlag
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package com.subspaceparasite.item;

import com.subspaceparasite.SPMain;
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

public class ItemBeholderPearl
extends Item {
    private static final ResourceLocation ID_SIM = new ResourceLocation("subspaceparasite", "sim_enderman");
    private static final ResourceLocation ID_SIM_HEAD = new ResourceLocation("subspaceparasite", "sim_endermanhead");
    private static final ResourceLocation ID_FERAL = new ResourceLocation("subspaceparasite", "fer_enderman");
    private static final ResourceLocation ID_ASSIMARA = new ResourceLocation("subspaceparasite", "mar_enderman");
    private static final ResourceLocation KEY_PEARL_STATE = new ResourceLocation("subspaceparasite", "pearl_state");
    private static final double SCAN_RADIUS = 100.0;
    private static final int UPDATE_INTERVAL = 6;
    private static final Map<Integer, Cache> CACHE = new HashMap<Integer, Cache>();

    public ItemBeholderPearl() {
        this.setRegistryName("subspaceparasite", "pearl");
        this.func_77655_b("subspaceparasite.pearl");
        this.func_77625_d(64);
        this.func_77637_a(SPMain.SP_CREATIVETAB);
        this.func_185043_a(KEY_PEARL_STATE, (stack, worldIn, entityIn) -> {
            float out;
            boolean holding;
            if (worldIn == null || entityIn == null) {
                return 0.0f;
            }
            boolean bl = holding = !entityIn.func_184614_ca().func_190926_b() && entityIn.func_184614_ca().func_77973_b() == this || !entityIn.func_184592_cb().func_190926_b() && entityIn.func_184592_cb().func_77973_b() == this;
            if (!holding) {
                CACHE.remove(entityIn.func_145782_y());
                return 0.0f;
            }
            long now = worldIn.func_82737_E();
            Cache c = CACHE.get(entityIn.func_145782_y());
            if (c == null) {
                c = new Cache();
                CACHE.put(entityIn.func_145782_y(), c);
            }
            c.lastAccessTick = now;
            if (now < c.nextScanTick) {
                return c.lastVal;
            }
            c.nextScanTick = now + 6L;
            if ((now & 0xFFL) == 0L) {
                long cutoff = now - 600L;
                CACHE.entrySet().removeIf(e -> ((Cache)e.getValue()).lastAccessTick < cutoff);
            }
            double R = 100.0;
            AxisAlignedBB box = new AxisAlignedBB(entityIn.field_70165_t - 100.0, entityIn.field_70163_u - 100.0, entityIn.field_70161_v - 100.0, entityIn.field_70165_t + 100.0, entityIn.field_70163_u + 100.0, entityIn.field_70161_v + 100.0);
            List matches = worldIn.func_175647_a(Entity.class, box, e -> {
                if (e == null || e.field_70128_L || e == entityIn) {
                    return false;
                }
                ResourceLocation id = EntityList.func_191301_a((Entity)e);
                return id != null && (id.equals((Object)ID_ASSIMARA) || id.equals((Object)ID_FERAL) || id.equals((Object)ID_SIM) || id.equals((Object)ID_SIM_HEAD));
            });
            int tier = 0;
            double nearestSq = Double.POSITIVE_INFINITY;
            for (Entity e2 : matches) {
                int candidate;
                ResourceLocation id = EntityList.func_191301_a((Entity)e2);
                if (id == null) continue;
                int n = id.equals((Object)ID_ASSIMARA) ? 3 : (id.equals((Object)ID_FERAL) ? 2 : (candidate = id.equals((Object)ID_SIM) || id.equals((Object)ID_SIM_HEAD) ? 1 : 0));
                if (candidate == 0) continue;
                double d2 = e2.func_70068_e((Entity)entityIn);
                if (candidate <= tier && (candidate != tier || !(d2 < nearestSq))) continue;
                tier = candidate;
                nearestSq = d2;
                if (tier != 3) continue;
                break;
            }
            if (tier == 0) {
                out = 0.0f;
            } else {
                double dist = Math.sqrt(nearestSq);
                float proximity = (float)Math.max(0.0, Math.min(1.0, (100.0 - dist) / 100.0));
                long t = now + (long)entityIn.field_70173_aa;
                float freq = 1.5f + 1.0f * (float)tier + 4.0f * proximity;
                float omega = (float)((double)freq * 2.0 * Math.PI / 20.0);
                float osc = (float)Math.sin((float)t * omega);
                float amp = 0.12f + 0.15f * (float)tier + 0.25f * proximity;
                float base = tier;
                float val = base + 0.1f + amp * osc;
                out = val = Math.max(base + 0.05f, Math.min(base + 0.45f, val));
            }
            c.lastVal = out;
            return out;
        });
    }

    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.GRAY + I18n.func_135052_a((String)"tooltip.subspaceparasite.pearl.desc", (Object[])new Object[0]));
    }

    private static final class Cache {
        long nextScanTick;
        long lastAccessTick;
        float lastVal;

        private Cache() {
        }
    }
}

