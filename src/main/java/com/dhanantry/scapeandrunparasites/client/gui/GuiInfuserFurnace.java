package com.dhanantry.scapeandrunparasites.client.gui;

import com.dhanantry.scapeandrunparasites.container.ContainerInfuserFurnace;
import com.dhanantry.scapeandrunparasites.tileentity.TileEntityInfuserFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiInfuserFurnace extends GuiContainer {
   private static final ResourceLocation TEX_BG = new ResourceLocation("srparasites", "textures/gui/infuser_furnace.png");
   private final InventoryPlayer playerInv;
   private final TileEntityInfuserFurnace te;
   private static final int VANILLA_ARROW_X = 79;
   private static final int VANILLA_ARROW_Y = 35;
   private static final int ARROW_SHIFT_LEFT = 24;

   public GuiInfuserFurnace(InventoryPlayer playerInv, TileEntityInfuserFurnace te) {
      super(new ContainerInfuserFurnace(playerInv, te, playerInv.field_70458_d));
      this.playerInv = playerInv;
      this.te = te;
      this.field_146999_f = 176;
      this.field_147000_g = 166;
   }

   protected void func_146979_b(int mouseX, int mouseY) {
      this.field_146289_q.func_78276_b(I18n.func_135052_a(this.te.func_70005_c_(), new Object[0]), 8, 6, 4210752);
      this.field_146289_q.func_78276_b(I18n.func_135052_a("container.inventory", new Object[0]), 8, this.field_147000_g - 96 + 2, 4210752);
   }

   protected void func_146976_a(float partialTicks, int mouseX, int mouseY) {
      int x = (this.field_146294_l - this.field_146999_f) / 2;
      int y = (this.field_146295_m - this.field_147000_g) / 2;
      this.field_146297_k.func_110434_K().func_110577_a(TEX_BG);
      this.func_73729_b(x, y, 0, 0, 176, 166);
      this.field_146297_k.func_110434_K().func_110577_a(TEX_BG);
      int smelt = Math.min(this.getCookScaled(this.te.func_174887_a_(2), 24), 24);
      if (smelt > 0) {
         this.func_73729_b(x + 80, y + 35, 176, 14, smelt + 1, 16);
      }

      if (this.te.isBurning()) {
         int k = this.getBurnLeftScaled(13);
         this.func_73729_b(x + 56, y + 36 + 12 - k, 176, 12 - k, 14, k + 1);
      }

      int infuse = this.getCookScaled(this.te.func_174887_a_(3), 24);
      if (infuse > 0) {
         this.func_73729_b(x + 80, y + 35, 176, 14, infuse + 1, 16);
      }
   }

   private int getCookScaled(int cook, int pixels) {
      return cook <= 0 ? 0 : cook * pixels / 200;
   }

   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
      this.func_146276_q_();
      super.func_73863_a(mouseX, mouseY, partialTicks);
      this.func_191948_b(mouseX, mouseY);
   }

   private int getBurnLeftScaled(int pixels) {
      int cur = this.te.func_174887_a_(1);
      if (cur <= 0) {
         cur = 200;
      }

      int burn = this.te.func_174887_a_(0);
      return burn <= 0 ? 0 : burn * pixels / cur;
   }
}
