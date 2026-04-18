package com.magistuarmory.item.crafting;

import com.magistuarmory.item.ArmorDecoration;
import com.magistuarmory.item.crafting.fabric.ArmorDecorationRecipeImpl;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import net.minecraft.class_1738;
import net.minecraft.class_1799;
import net.minecraft.class_1852;
import net.minecraft.class_1865;
import net.minecraft.class_1866;
import net.minecraft.class_1937;
import net.minecraft.class_7710;
import net.minecraft.class_9694;
import net.minecraft.class_7225.class_7874;
import org.jetbrains.annotations.NotNull;

public class ArmorDecorationRecipe extends class_1852 {
   public static class_1865<ArmorDecorationRecipe> SERIALIZER = new class_1866(ArmorDecorationRecipe::new);

   public ArmorDecorationRecipe(class_7710 category) {
      super(category);
   }

   public boolean matches(class_9694 container, class_1937 level) {
      class_1799 wearabledecorationstack = class_1799.field_8037;
      class_1799 armorstack = class_1799.field_8037;
      class_1799 decorationstack = class_1799.field_8037;

      for (int i = 0; i < container.method_59983(); i++) {
         class_1799 stack = container.method_59984(i);
         if (!stack.method_7960()) {
            if (stack.method_7909() instanceof class_1738 && stack.method_7909() instanceof ArmorDecoration) {
               if (!wearabledecorationstack.method_7960()) {
                  return false;
               }

               wearabledecorationstack = stack;
            } else if (stack.method_7909() instanceof ArmorDecoration) {
               if (!decorationstack.method_7960()) {
                  return false;
               }

               decorationstack = stack;
            } else {
               if (!(stack.method_7909() instanceof class_1738)) {
                  return false;
               }

               if (!armorstack.method_7960()) {
                  return false;
               }

               armorstack = stack;
            }
         }
      }

      if (!wearabledecorationstack.method_7960()) {
         if (!decorationstack.method_7960() && armorstack.method_7960()) {
            armorstack = wearabledecorationstack;
         } else {
            if (!decorationstack.method_7960() || armorstack.method_7960()) {
               return false;
            }

            decorationstack = wearabledecorationstack;
         }
      }

      return decorationstack.method_7909() instanceof ArmorDecoration decoration ? decoration.isApplicableForDecoration(armorstack) : false;
   }

   @NotNull
   public class_1799 assemble(class_9694 container, @NotNull class_7874 access) {
      class_1799 wearabledecorationstack = class_1799.field_8037;
      class_1799 armorstack = class_1799.field_8037;
      class_1799 decorationstack = class_1799.field_8037;

      for (int i = 0; i < container.method_59983(); i++) {
         class_1799 stack = container.method_59984(i);
         if (!stack.method_7960()) {
            if (stack.method_7909() instanceof ArmorDecoration && stack.method_7909() instanceof class_1738) {
               wearabledecorationstack = stack;
            } else if (stack.method_7909() instanceof ArmorDecoration) {
               decorationstack = stack;
            } else if (stack.method_7909() instanceof class_1738) {
               armorstack = stack.method_7972();
            }
         }
      }

      if (!wearabledecorationstack.method_7960()) {
         if (!decorationstack.method_7960() && armorstack.method_7960()) {
            armorstack = wearabledecorationstack.method_7972();
         } else {
            if (!decorationstack.method_7960() || armorstack.method_7960()) {
               return armorstack;
            }

            decorationstack = wearabledecorationstack;
         }
      }

      if (!armorstack.method_7960() && !decorationstack.method_7960() && decorationstack.method_7909() instanceof ArmorDecoration decoration) {
         decoration.decorate(armorstack, decorationstack);
      }

      return armorstack;
   }

   public boolean method_8113(int n, int m) {
      return n * m >= 2;
   }

   public class_1865<?> method_8119() {
      return getSerializerInstance();
   }

   @ExpectPlatform
   @Transformed
   public static class_1865<ArmorDecorationRecipe> getSerializerInstance() {
      return ArmorDecorationRecipeImpl.getSerializerInstance();
   }
}
