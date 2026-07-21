package com.dhanantry.scapeandrunparasites.client.gui;

import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonImage;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.recipebook.GuiRecipeBook;
import net.minecraft.client.gui.recipebook.IRecipeShownListener;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GuiConsumedWorkbench extends GuiContainer implements IRecipeShownListener {
   private static final ResourceLocation CRAFTING_TEX = new ResourceLocation("textures/gui/container/crafting_table.png");
   private final GuiRecipeBook recipeBookGui = new GuiRecipeBook();
   private boolean widthTooNarrow;
   private final InventoryPlayer playerInv;
   private GuiButtonImage recipeButton;

   public void func_192043_J_() {
      this.recipeBookGui.func_193948_e();
   }

   public GuiConsumedWorkbench(InventoryPlayer playerInv, World world, BlockPos pos) {
      super(new ContainerConsumedWorkbench(playerInv, world, pos));
      this.playerInv = playerInv;
   }

   private ContainerConsumedWorkbench getCW() {
      return (ContainerConsumedWorkbench)this.field_147002_h;
   }

   public void func_73866_w_() {
      this.widthTooNarrow = this.field_146294_l < 379;
      super.func_73866_w_();
      this.recipeBookGui.func_194303_a(this.field_146294_l, this.field_146295_m, this.field_146297_k, this.widthTooNarrow, this.getCW().field_75162_e);
      this.field_147003_i = this.recipeBookGui.func_193011_a(this.widthTooNarrow, this.field_146294_l, this.field_146999_f);
      int btnX = this.field_147003_i + 5;
      int btnY = this.field_147009_r + 34;
      this.recipeButton = new GuiButtonImage(10, btnX, btnY, 20, 18, 0, 0, 0, CRAFTING_TEX) {
         public void func_191745_a(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
            if (this.field_146125_m) {
               this.field_146123_n = mouseX >= this.field_146128_h
                  && mouseY >= this.field_146129_i
                  && mouseX < this.field_146128_h + this.field_146120_f
                  && mouseY < this.field_146129_i + this.field_146121_g;
               mc.func_110434_K().func_110577_a(GuiConsumedWorkbench.CRAFTING_TEX);
               int u = 0;
               int v = this.field_146123_n ? 187 : 168;
               this.func_73729_b(this.field_146128_h, this.field_146129_i, 0, v, this.field_146120_f, this.field_146121_g);
            }
         }
      };
      this.field_146292_n.add(this.recipeButton);
   }

   public void func_73876_c() {
      super.func_73876_c();
      this.recipeBookGui.func_193957_d();
   }

   public void func_146281_b() {
      super.func_146281_b();
      this.recipeBookGui.func_191871_c();
   }

   protected void func_146284_a(GuiButton button) throws IOException {
      if (button.field_146127_k == 10) {
         this.recipeBookGui.func_193014_a(this.widthTooNarrow, this.getCW().field_75162_e);
         this.recipeBookGui.func_191866_a();
         this.field_147003_i = this.recipeBookGui.func_193011_a(this.widthTooNarrow, this.field_146294_l, this.field_146999_f);
         this.recipeButton.func_191746_c(this.field_147003_i + 5, this.field_147009_r + 34);
      } else {
         super.func_146284_a(button);
      }
   }

   protected void func_73869_a(char typedChar, int keyCode) throws IOException {
      if (!this.recipeBookGui.func_191859_a(typedChar, keyCode)) {
         super.func_73869_a(typedChar, keyCode);
      }
   }

   public void func_184098_a(Slot slotIn, int slotId, int mouseButton, ClickType type) {
      super.func_184098_a(slotIn, slotId, mouseButton, type);
      this.recipeBookGui.func_191874_a(slotIn);
   }

   public void func_73864_a(int mouseX, int mouseY, int mouseButton) throws IOException {
      if (!this.recipeBookGui.func_191862_a(mouseX, mouseY, mouseButton)) {
         super.func_73864_a(mouseX, mouseY, mouseButton);
      }
   }

   public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
      this.func_146276_q_();
      if (this.recipeBookGui.func_191878_b() && this.widthTooNarrow) {
         this.recipeBookGui.func_191861_a(mouseX, mouseY, partialTicks);
         super.func_73863_a(mouseX, mouseY, partialTicks);
      } else {
         super.func_73863_a(mouseX, mouseY, partialTicks);
         this.recipeBookGui.func_191861_a(mouseX, mouseY, partialTicks);
      }

      this.func_191948_b(mouseX, mouseY);
      this.recipeBookGui.func_191876_c(this.field_147003_i, this.field_147009_r, mouseX, mouseY);
   }

   protected void func_146979_b(int mouseX, int mouseY) {
      this.field_146289_q.func_78276_b(I18n.func_135052_a("container.crafting", new Object[0]), 28, 6, 4210752);
      this.field_146289_q.func_78276_b(this.playerInv.func_145748_c_().func_150260_c(), 8, this.field_147000_g - 96 + 2, 4210752);
   }

   protected void func_146976_a(float partialTicks, int mouseX, int mouseY) {
      Minecraft.func_71410_x().func_110434_K().func_110577_a(CRAFTING_TEX);
      int i = this.field_147003_i;
      int j = this.field_147009_r;
      this.func_73729_b(i, j, 0, 0, this.field_146999_f, this.field_147000_g);
   }

   public GuiRecipeBook func_194310_f() {
      return this.recipeBookGui;
   }
}
