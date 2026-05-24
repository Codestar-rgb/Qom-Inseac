/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 */
package com.subspaceparasite.client.renderer;

import com.subspaceparasite.block.BlockEntityTrophy;
import com.subspaceparasite.entity.tile.TileEntityTrophy;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RenderTrophyTESR
extends TileEntitySpecialRenderer<TileEntityTrophy> {
    public void render(TileEntityTrophy te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        World world = te.func_145831_w();
        if (world == null) {
            return;
        }
        BlockPos pos = te.func_174877_v();
        IBlockState state = world.func_180495_p(pos);
        if (!(state.func_177230_c() instanceof BlockEntityTrophy)) {
            return;
        }
        BlockEntityTrophy trophy = (BlockEntityTrophy)state.func_177230_c();
        Entity entity = this.getOrCreateEntity(te, trophy.getMobId());
        if (entity == null) {
            return;
        }
        entity.func_174810_b(true);
        entity.func_189654_d(true);
        if (!trophy.isAnimate()) {
            entity.field_70173_aa = 0;
        } else {
            long t = Minecraft.func_71410_x().field_71441_e.func_82737_E();
            entity.field_70173_aa = (int)(t % 100000L);
        }
        float yaw = Minecraft.func_71410_x().field_71439_g != null ? Minecraft.func_71410_x().field_71439_g.field_70177_z : 0.0f;
        entity.field_70177_z = -yaw;
        entity.field_70126_B = -yaw;
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b((float)((float)x + 0.5f), (float)((float)y + 1.0f + trophy.getYOffset()), (float)((float)z + 0.5f));
        float s = trophy.getScale();
        GlStateManager.func_179152_a((float)s, (float)s, (float)s);
        if (trophy.getTextureMode() == BlockEntityTrophy.TrophyTextureMode.STONE) {
            GlStateManager.func_179131_c((float)0.75f, (float)0.75f, (float)0.75f, (float)1.0f);
        } else {
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
        Minecraft.func_71410_x().func_175598_ae().func_188391_a(entity, 0.0, 0.0, 0.0, 0.0f, partialTicks, false);
        GlStateManager.func_179121_F();
    }

    private Entity getOrCreateEntity(TileEntityTrophy te, ResourceLocation mobId) {
        if (te.cachedRenderEntity != null) {
            return te.cachedRenderEntity;
        }
        WorldClient w = Minecraft.func_71410_x().field_71441_e;
        if (w == null) {
            return null;
        }
        Entity e = EntityList.func_188429_b((ResourceLocation)mobId, (World)w);
        if (e == null) {
            return null;
        }
        if (e instanceof EntityLiving) {
            ((EntityLiving)e).func_94061_f(true);
        }
        te.cachedRenderEntity = e;
        return e;
    }
}

