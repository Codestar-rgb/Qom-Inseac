package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.network.SRPPacketClock;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemClockDevelopment extends ItemBase {
   public static int level = 0;

   public ItemClockDevelopment(String name, int maxStack, byte v) {
      super(name, maxStack, v);
      this.func_185043_a(
         new ResourceLocation("level"),
         new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float func_185085_a(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
               if (entityIn == null) {
                  return 0.0F;
               } else {
                  ItemStack main = entityIn.func_184614_ca();
                  ItemStack off = entityIn.func_184592_cb();
                  return main != stack && off != stack && !ItemStack.func_77989_b(main, stack) && !ItemStack.func_77989_b(off, stack)
                     ? 0.0F
                     : ItemClockDevelopment.level;
               }
            }
         }
      );
   }

   public void func_77663_a(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
      if (!worldIn.field_72995_K && entityIn.field_70173_aa % 20 == 0) {
         SRPSaveData data = SRPSaveData.get(worldIn, -11);
         if (data != null) {
            SRPMain.network
               .sendToAll(
                  new SRPPacketClock(
                     data.getCooldown(worldIn, worldIn.field_73011_w.getDimension()),
                     data.getEvolutionPhase(worldIn.field_73011_w.getDimension()),
                     data.getDeveLevel(),
                     2
                  )
               );
         }
      }
   }
}
