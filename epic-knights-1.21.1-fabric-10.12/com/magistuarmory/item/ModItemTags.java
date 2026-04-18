package com.magistuarmory.item;

import com.magistuarmory.item.armor.MedievalArmorItem;
import dev.architectury.registry.registries.RegistrySupplier;
import java.util.concurrent.CompletableFuture;
import net.minecraft.class_1792;
import net.minecraft.class_2248;
import net.minecraft.class_2960;
import net.minecraft.class_6862;
import net.minecraft.class_7784;
import net.minecraft.class_7805;
import net.minecraft.class_7924;
import net.minecraft.class_2474.class_8211;
import net.minecraft.class_7225.class_7874;

public class ModItemTags extends class_7805 {
   class_6862<class_1792> INGOTS = tag("c", "ingots");
   class_6862<class_1792> NUGGETS = tag("c", "nuggets");
   class_6862<class_1792> SWORDS = tag("c", "swords");
   class_6862<class_1792> TOOLS = tag("c", "tools");
   class_6862<class_1792> TOOLS_BOWS = tag("c", "tools/bows");
   class_6862<class_1792> TOOLS_SHIELDS = tag("c", "tools/shields");
   class_6862<class_1792> ARMORS = tag("c", "armors");
   class_6862<class_1792> ARMORS_HELMETS = tag("c", "armors/helmets");
   class_6862<class_1792> ARMORS_CHESTPLATES = tag("c", "armors/chestplates");
   class_6862<class_1792> ARMORS_LEGGINGS = tag("c", "armors/leggings");
   class_6862<class_1792> ARMORS_BOOTS = tag("c", "armors/boots");

   public ModItemTags(class_7784 packOutput, CompletableFuture<class_7874> completableFuture, CompletableFuture<class_8211<class_2248>> completableFuture2) {
      super(packOutput, completableFuture, completableFuture2);
   }

   protected void method_10514(class_7874 provider) {
      this.method_46827(this.INGOTS).method_46829((class_1792)ModItems.STEEL_INGOT.get());
      this.method_46827(this.NUGGETS).method_46829((class_1792)ModItems.STEEL_NUGGET.get());

      for (RegistrySupplier<? extends MedievalWeaponItem> supplier : ModItems.INSTANCE.weaponItems) {
         MedievalWeaponItem weapon = (MedievalWeaponItem)supplier.get();
         this.method_46827(this.TOOLS).method_46829(weapon);
         this.method_46827(this.SWORDS).method_46829(weapon);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.INSTANCE.rangedWeaponItems) {
         class_1792 bow = (class_1792)supplier.get();
         this.method_46827(this.TOOLS).method_46829(bow);
         this.method_46827(this.TOOLS_BOWS).method_46829(bow);
      }

      for (RegistrySupplier<? extends class_1792> supplier : ModItems.INSTANCE.shieldItems) {
         class_1792 shield = (class_1792)supplier.get();
         this.method_46827(this.TOOLS).method_46829(shield);
         this.method_46827(this.TOOLS_SHIELDS).method_46829(shield);
      }

      for (RegistrySupplier<? extends MedievalArmorItem> supplier : ModItems.INSTANCE.armorItems) {
         MedievalArmorItem armor = (MedievalArmorItem)supplier.get();
         this.method_46827(this.ARMORS).method_46829(armor);
         switch (armor.method_7685()) {
            case field_6169:
               this.method_46827(this.ARMORS_HELMETS).method_46829(armor);
               break;
            case field_6174:
               this.method_46827(this.ARMORS_CHESTPLATES).method_46829(armor);
               break;
            case field_48824:
               this.method_46827(this.ARMORS_CHESTPLATES).method_46829(armor);
               break;
            case field_6172:
               this.method_46827(this.ARMORS_LEGGINGS).method_46829(armor);
               break;
            case field_6166:
               this.method_46827(this.ARMORS_BOOTS).method_46829(armor);
         }

         this.method_46827(this.ARMORS_HELMETS).method_46829((class_1792)supplier.get());
      }
   }

   private static class_6862<class_1792> tag(String namespace, String name) {
      return class_6862.method_40092(class_7924.field_41197, class_2960.method_60655(namespace, name));
   }
}
