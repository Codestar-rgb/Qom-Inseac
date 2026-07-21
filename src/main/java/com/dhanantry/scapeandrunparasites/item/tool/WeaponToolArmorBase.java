package com.dhanantry.scapeandrunparasites.item.tool;

import com.dhanantry.scapeandrunparasites.SRPMain;
import com.dhanantry.scapeandrunparasites.init.SRPItems;
import com.dhanantry.scapeandrunparasites.init.SRPPotions;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfig;
import com.dhanantry.scapeandrunparasites.util.config.SRPConfigSystems;
import com.dhanantry.scapeandrunparasites.world.SRPSaveData;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WeaponToolArmorBase extends ItemArmor {
   private byte effectHit;
   protected boolean calling;
   protected byte idTool;

   public WeaponToolArmorBase(ArmorMaterial material, String name, int renderI, boolean fear, byte id, EntityEquipmentSlot eq) {
      super(material, renderI, eq);
      this.setRegistryName("armor_" + name);
      this.func_77655_b("srparasites.armor_" + name);
      this.func_77637_a(SRPMain.SRP_CREATIVETAB);
      this.calling = fear;
      this.idTool = id;
      this.func_185043_a(new ResourceLocation("vinni"), new IItemPropertyGetter() {
         @SideOnly(Side.CLIENT)
         public float func_185085_a(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
            return 0.0F;
         }
      });
   }

   public void func_77663_a(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
      super.func_77663_a(stack, worldIn, entityIn, itemSlot, isSelected);
      if (!worldIn.field_72995_K
         && this.calling
         && SRPConfigSystems.useScent
         && worldIn.field_73012_v.nextInt(10) == 0
         && entityIn.field_70173_aa % 40 == 0
         && SRPSaveData.get(worldIn, 222).getDeveLevel() >= SRPConfigSystems.deveScentUse) {
         ((EntityLivingBase)entityIn).func_70690_d(new PotionEffect(SRPPotions.PREY_E, 1200, 0, false, false));
      }

      if (entityIn.field_70173_aa % 80 == 0) {
         int key = 0;
         NBTTagCompound compound = stack.func_77978_p();
         if (compound != null && this.getNext() != null) {
            if (compound.func_74764_b("srphits")) {
               key = compound.func_74762_e("srphits");
            }

            if (key >= SRPConfig.weapon_livingSentient_DAMAGE_needed) {
               compound.func_74768_a("srphits", 0);
               stack.func_190918_g(1);
               ItemStack stackW = new ItemStack(this.getNext(), 1);
               EntityItem entityitem = new EntityItem(worldIn, entityIn.field_70165_t, entityIn.field_70163_u, entityIn.field_70161_v, stackW);
               entityitem.func_174869_p();
               worldIn.func_72838_d(entityitem);
               if (SRPConfig.thunderEnable) {
                  worldIn.func_72942_c(new EntityLightningBolt(worldIn, entityIn.field_70165_t, entityIn.field_70163_u, entityIn.field_70161_v, true));
               }

               return;
            }
         }
      }
   }

   public boolean canCall() {
      return this.calling;
   }

   public Item getNext() {
      if (this == SRPItems.armor_helmet) {
         return SRPItems.armor_helmetSentient;
      } else if (this == SRPItems.armor_chest) {
         return SRPItems.armor_chestSentient;
      } else if (this == SRPItems.armor_pants) {
         return SRPItems.armor_pantsSentient;
      } else {
         return this == SRPItems.armor_boots ? SRPItems.armor_bootsSentient : null;
      }
   }

   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
      return super.getArmorModel(entityLiving, itemStack, armorSlot, _default);
   }

   public void func_77622_d(ItemStack stack, World worldIn, EntityPlayer playerIn) {
      super.func_77622_d(stack, worldIn, playerIn);
   }

   public EnumRarity func_77613_e(ItemStack stack) {
      return super.func_77613_e(stack);
   }

   public void func_77624_a(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
      ArrayList<String> resistanceS = new ArrayList<>();
      ArrayList<Integer> resistanceI = new ArrayList<>();
      NBTTagCompound compound = stack.func_77978_p();
      if (compound == null) {
         compound = new NBTTagCompound();
      }

      tooltip.add(TextFormatting.BLUE + "---> " + compound.func_74762_e("srphits"));
      tooltip.add(TextFormatting.BLUE + "  ");
      if (compound.func_74764_b("sprresistances")) {
         NBTTagList allResS = compound.func_150295_c("sprresistances", 10);
         NBTTagList allResI = compound.func_150295_c("sprresistancei", 10);
         if (allResS.func_74745_c() != allResI.func_74745_c()) {
            return;
         }

         for (int i = 0; i < allResS.func_74745_c(); i++) {
            NBTTagCompound resT = allResS.func_150305_b(i);
            String res = resT.func_74779_i("resistance" + i);
            resistanceS.add(i, res);
            NBTTagCompound resU = allResI.func_150305_b(i);
            int resi = resU.func_74762_e("resistance" + i);
            resistanceI.add(i, resi);
         }

         tooltip.add(TextFormatting.DARK_PURPLE + "Current Adaptation:");

         for (int i = 0; i < resistanceS.size(); i++) {
            double reduc = Math.min(resistanceI.get(i), this.calling ? SRPConfig.sentientPointCap : SRPConfig.livingPointCap);
            reduc *= this.calling ? SRPConfig.sentientPointReduction : SRPConfig.livingPointReduction;
            DecimalFormat decimalFormat = new DecimalFormat("##.##");
            decimalFormat.setRoundingMode(RoundingMode.DOWN);
            String formatResult = decimalFormat.format(reduc * 100.0);
            tooltip.add(TextFormatting.YELLOW + "-> " + resistanceS.get(i));
            tooltip.add(TextFormatting.YELLOW + " reduction: " + formatResult + "%");
         }
      }
   }
}
