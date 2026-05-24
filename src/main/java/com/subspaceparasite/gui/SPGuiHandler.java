/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.common.network.IGuiHandler
 */
package com.subspaceparasite.gui;

import com.subspaceparasite.client.gui.ContainerConsumedWorkbench;
import com.subspaceparasite.client.gui.GuiConsumedWorkbench;
import com.subspaceparasite.client.gui.GuiInfuserFurnace;
import com.subspaceparasite.container.ContainerInfuserFurnace;
import com.subspaceparasite.container.ContainerParasiteLoot;
import com.subspaceparasite.container.ScannerContainer;
import com.subspaceparasite.gui.GuiParasiteLoot;
import com.subspaceparasite.gui.ScannerGui;
import com.subspaceparasite.tileentity.TileEntityInfuserFurnace;
import com.subspaceparasite.tileentity.TileEntityParasiteLoot;
import com.subspaceparasite.tileentity.TileEntityRelayController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class SPGuiHandler
implements IGuiHandler {
    public static final int SCANNER_GUI_ID = 0;
    public static final int PARASITE_LOOT_GUI_ID = 101;
    public static final int CONSUMED_WORKBENCH_GUI_ID = 102;
    public static final int INFUSER_FURNACE_GUI_ID = 7;

    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        if (id == 102) {
            return new ContainerConsumedWorkbench(player.field_71071_by, world, pos);
        }
        TileEntity te = world.func_175625_s(pos);
        if (te == null) {
            return null;
        }
        switch (id) {
            case 0: {
                if (te instanceof TileEntityRelayController) {
                    return new ScannerContainer(player.field_71071_by, (TileEntityRelayController)te);
                }
                return null;
            }
            case 101: {
                if (te instanceof TileEntityParasiteLoot) {
                    return new ContainerParasiteLoot(player.field_71071_by, (TileEntityParasiteLoot)te, player);
                }
                return null;
            }
            case 7: {
                if (te instanceof TileEntityInfuserFurnace) {
                    return new ContainerInfuserFurnace(player.field_71071_by, (TileEntityInfuserFurnace)te, player);
                }
                return null;
            }
        }
        return null;
    }

    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        if (id == 102) {
            return new GuiConsumedWorkbench(player.field_71071_by, world, pos);
        }
        TileEntity te = world.func_175625_s(pos);
        if (te == null) {
            return null;
        }
        switch (id) {
            case 0: {
                if (te instanceof TileEntityRelayController) {
                    return new ScannerGui(player.field_71071_by, (TileEntityRelayController)te);
                }
                return null;
            }
            case 101: {
                if (te instanceof TileEntityParasiteLoot) {
                    return new GuiParasiteLoot(player.field_71071_by, (TileEntityParasiteLoot)te);
                }
                return null;
            }
            case 7: {
                if (te instanceof TileEntityInfuserFurnace) {
                    return new GuiInfuserFurnace(player.field_71071_by, (TileEntityInfuserFurnace)te);
                }
                return null;
            }
        }
        return null;
    }
}

