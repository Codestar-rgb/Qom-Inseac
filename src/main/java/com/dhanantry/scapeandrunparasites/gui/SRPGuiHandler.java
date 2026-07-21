package com.dhanantry.scapeandrunparasites.gui;

import com.dhanantry.scapeandrunparasites.client.gui.ContainerConsumedWorkbench;
import com.dhanantry.scapeandrunparasites.client.gui.GuiConsumedWorkbench;
import com.dhanantry.scapeandrunparasites.client.gui.GuiInfuserFurnace;
import com.dhanantry.scapeandrunparasites.container.ContainerInfuserFurnace;
import com.dhanantry.scapeandrunparasites.container.ContainerParasiteLoot;
import com.dhanantry.scapeandrunparasites.container.ScannerContainer;
import com.dhanantry.scapeandrunparasites.tileentity.TileEntityInfuserFurnace;
import com.dhanantry.scapeandrunparasites.tileentity.TileEntityParasiteLoot;
import com.dhanantry.scapeandrunparasites.tileentity.TileEntityRelayController;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class SRPGuiHandler implements IGuiHandler {
   public static final int SCANNER_GUI_ID = 0;
   public static final int PARASITE_LOOT_GUI_ID = 101;
   public static final int CONSUMED_WORKBENCH_GUI_ID = 102;
   public static final int INFUSER_FURNACE_GUI_ID = 7;

   public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
      BlockPos pos = new BlockPos(x, y, z);
      if (id == 102) {
         return new ContainerConsumedWorkbench(player.field_71071_by, world, pos);
      } else {
         TileEntity te = world.func_175625_s(pos);
         if (te == null) {
            return null;
         } else {
            switch (id) {
               case 0:
                  if (te instanceof TileEntityRelayController) {
                     return new ScannerContainer(player.field_71071_by, (TileEntityRelayController)te);
                  }

                  return null;
               case 7:
                  if (te instanceof TileEntityInfuserFurnace) {
                     return new ContainerInfuserFurnace(player.field_71071_by, (TileEntityInfuserFurnace)te, player);
                  }

                  return null;
               case 101:
                  if (te instanceof TileEntityParasiteLoot) {
                     return new ContainerParasiteLoot(player.field_71071_by, (TileEntityParasiteLoot)te, player);
                  }

                  return null;
               default:
                  return null;
            }
         }
      }
   }

   public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
      BlockPos pos = new BlockPos(x, y, z);
      if (id == 102) {
         return new GuiConsumedWorkbench(player.field_71071_by, world, pos);
      } else {
         TileEntity te = world.func_175625_s(pos);
         if (te == null) {
            return null;
         } else {
            switch (id) {
               case 0:
                  if (te instanceof TileEntityRelayController) {
                     return new ScannerGui(player.field_71071_by, (TileEntityRelayController)te);
                  }

                  return null;
               case 7:
                  if (te instanceof TileEntityInfuserFurnace) {
                     return new GuiInfuserFurnace(player.field_71071_by, (TileEntityInfuserFurnace)te);
                  }

                  return null;
               case 101:
                  if (te instanceof TileEntityParasiteLoot) {
                     return new GuiParasiteLoot(player.field_71071_by, (TileEntityParasiteLoot)te);
                  }

                  return null;
               default:
                  return null;
            }
         }
      }
   }
}
