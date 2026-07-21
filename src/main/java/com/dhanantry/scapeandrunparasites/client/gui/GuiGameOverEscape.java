package com.dhanantry.scapeandrunparasites.client.gui;

import com.dhanantry.scapeandrunparasites.client.EscapeClientState;
import com.dhanantry.scapeandrunparasites.network.SRPNetwork;
import com.dhanantry.scapeandrunparasites.network.msg.C2SRequestEscape;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigWorld;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiGameOverEscape extends GuiGameOver {
   private static final int BTN_ESCAPE = 911;
   private GuiButton escapeBtn;

   public GuiGameOverEscape() {
      super(null);
   }

   public void func_73866_w_() {
      super.func_73866_w_();
      if (EscapeClientState.OFFER) {
         int min = Math.max(0, SRPConfigWorld.escapeMinDistance);
         int max = Math.max(min, SRPConfigWorld.escapeMaxDistance);
         String label = I18n.func_74838_a("gui.srparasites.escape_button") + min + "-" + max + " " + I18n.func_74838_a("gui.srparasites.escape_button_blocks");
         int x = this.field_146294_l / 2 - 100;
         int bottom = 0;

         for (GuiButton b : this.field_146292_n) {
            bottom = Math.max(bottom, b.field_146129_i + b.field_146121_g);
         }

         int y = bottom + 6;
         this.escapeBtn = new GuiButton(911, x, y, 200, 20, label);
         this.field_146292_n.add(this.escapeBtn);
      }
   }

   protected void func_146284_a(GuiButton button) throws IOException {
      if (button.field_146127_k == 911) {
         SRPNetwork.CHANNEL.sendToServer(new C2SRequestEscape());
         Minecraft.func_71410_x().field_71439_g.func_71004_bE();
      } else {
         super.func_146284_a(button);
      }
   }

   @SideOnly(Side.CLIENT)
   public static class OpenHook {
      @SubscribeEvent
      public void onOpen(GuiOpenEvent e) {
         if (e.getGui() instanceof GuiGameOver && EscapeClientState.OFFER && !(e.getGui() instanceof GuiGameOverEscape)) {
            e.setGui(new GuiGameOverEscape());
         }
      }
   }
}
