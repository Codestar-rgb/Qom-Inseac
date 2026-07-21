package com.dhanantry.scapeandrunparasites.item;

import com.dhanantry.scapeandrunparasites.SRPMain;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemModule extends Item {
   private final ItemModule.Kind kind;

   public ItemModule(String name, ItemModule.Kind kind) {
      this.kind = kind;
      this.setRegistryName(name);
      this.func_77655_b("srparasites." + name);
      this.func_77637_a(SRPMain.SRP_CREATIVETAB);
      this.func_77625_d(1);
   }

   public ItemModule() {
      this.kind = ItemModule.Kind.INBORN;
      this.func_77625_d(1);
   }

   public ItemModule.Kind getKind() {
      return this.kind;
   }

   public ItemModule.Kind getKind(ItemStack ignored) {
      return this.kind;
   }

   public void func_150895_a(CreativeTabs tab, NonNullList<ItemStack> items) {
      if (this.func_194125_a(tab)) {
         items.add(new ItemStack(this));
      }
   }

   @SideOnly(Side.CLIENT)
   public void func_77624_a(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
      if (this.getRegistryName() != null) {
         String key = "tooltip." + this.getRegistryName().func_110623_a();
         String translated = I18n.func_135052_a(key, new Object[0]);
         if (!translated.equals(key) && !translated.isEmpty()) {
            if (translated.contains("\\n")) {
               for (String line : translated.split("\\\\n")) {
                  tooltip.add(line);
               }
            } else {
               tooltip.add(translated);
            }
         }

         tooltip.add(I18n.func_135052_a("tooltip.module.profile", new Object[]{this.kind.name()}));
      }
   }

   public static enum Kind {
      INBORN,
      ASSIMILATED,
      HIJACKED,
      FERAL,
      CRUDE,
      PRIMITIVE,
      ADAPTED,
      NEXUS,
      DETERRENT,
      PURE,
      PREEMINENT,
      ANCIENT,
      DESMOID,
      ESCHAR,
      RESISTANCE,
      IDEAL,
      ORIGIN,
      ASSIMARA,
      DERIVED,
      VECTORS,
      PHASE,
      DISLODGEMENT;
   }
}
