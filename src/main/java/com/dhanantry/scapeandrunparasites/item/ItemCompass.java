package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.network.SRPPacketCompass;
import com.dhanantry.scapeandrunparasites.world.SRPWorldData;
import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ItemCompass extends Item {
   protected byte type;

   public ItemCompass(String n, int t) {
      this.setRegistryName(n);
      this.func_77655_b("srparasites." + n);
      this.func_77637_a(SRPMain.SRP_CREATIVETAB);
      this.type = (byte)t;
      this.func_185043_a(
         new ResourceLocation("angle"),
         new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            double rotation;
            @SideOnly(Side.CLIENT)
            double rota;
            @SideOnly(Side.CLIENT)
            long lastUpdateTick;

            @SideOnly(Side.CLIENT)
            public float func_185085_a(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
               if (entityIn == null && !stack.func_82839_y()) {
                  return 0.0F;
               } else {
                  boolean flag = entityIn != null;
                  Entity entity = (Entity)(flag ? entityIn : stack.func_82836_z());
                  if (worldIn == null) {
                     worldIn = entity.field_70170_p;
                  }

                  double d0;
                  if (worldIn.field_73011_w.func_76569_d()) {
                     double d1 = flag ? entity.field_70177_z : this.getFrameRotation((EntityItemFrame)entity);
                     d1 = MathHelper.func_191273_b(d1 / 360.0, 1.0);
                     double d2 = this.getSpawnToAngle(worldIn, entity) / (Math.PI * 2);
                     d0 = 0.5 - (d1 - 0.25 - d2);
                  } else {
                     d0 = Math.random();
                  }

                  if (flag) {
                     d0 = this.wobble(worldIn, d0);
                  }

                  return MathHelper.func_188207_b((float)d0, 1.0F);
               }
            }

            @SideOnly(Side.CLIENT)
            private double wobble(World worldIn, double ddd) {
               if (worldIn.func_82737_E() != this.lastUpdateTick) {
                  this.lastUpdateTick = worldIn.func_82737_E();
                  double d0 = ddd - this.rotation;
                  d0 = MathHelper.func_191273_b(d0 + 0.5, 1.0) - 0.5;
                  this.rota += d0 * 0.1;
                  this.rota *= 0.8;
                  this.rotation = MathHelper.func_191273_b(this.rotation + this.rota, 1.0);
               }

               return this.rotation;
            }

            @SideOnly(Side.CLIENT)
            private double getFrameRotation(EntityItemFrame en) {
               return MathHelper.func_188209_b(180 + en.field_174860_b.func_176736_b() * 90);
            }

            @SideOnly(Side.CLIENT)
            private double getSpawnToAngle(World world, Entity en) {
               return ItemCompass.this.getOri() == null
                  ? world.field_73012_v.nextDouble()
                  : Math.atan2(ItemCompass.this.getOri().func_177952_p() - en.field_70161_v, ItemCompass.this.getOri().func_177958_n() - en.field_70165_t);
            }
         }
      );
   }

   public void func_77663_a(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
      if (!worldIn.field_72995_K && entityIn.field_70173_aa % 20 == 0) {
         SRPWorldData aaa = SRPWorldData.get(worldIn);
         BlockPos posss = this.getOrigin(entityIn, worldIn);
         if (posss != null) {
            SRPMain.network.sendToAll(new SRPPacketCompass(posss.func_177958_n(), posss.func_177956_o(), posss.func_177952_p(), this.type));
         } else {
            SRPMain.network
               .sendToAll(new SRPPacketCompass(worldIn.field_73012_v.nextInt(), worldIn.field_73012_v.nextInt(), worldIn.field_73012_v.nextInt(), this.type));
         }
      }
   }

   public abstract BlockPos getOrigin(Entity var1, World var2);

   public abstract BlockPos getOri();
}
