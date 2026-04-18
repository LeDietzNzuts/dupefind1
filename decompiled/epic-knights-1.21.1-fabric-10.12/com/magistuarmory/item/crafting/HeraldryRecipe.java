package com.magistuarmory.item.crafting;

import com.magistuarmory.EpicKnights;
import com.magistuarmory.item.MedievalShieldItem;
import com.magistuarmory.item.armor.ISurcoat;
import com.magistuarmory.item.crafting.fabric.HeraldryRecipeImpl;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import net.minecraft.class_1304;
import net.minecraft.class_1738;
import net.minecraft.class_1746;
import net.minecraft.class_1767;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1852;
import net.minecraft.class_1865;
import net.minecraft.class_1866;
import net.minecraft.class_1937;
import net.minecraft.class_2561;
import net.minecraft.class_4059;
import net.minecraft.class_7710;
import net.minecraft.class_9307;
import net.minecraft.class_9334;
import net.minecraft.class_9694;
import net.minecraft.class_4059.class_9076;
import net.minecraft.class_7225.class_7874;
import org.jetbrains.annotations.NotNull;

public class HeraldryRecipe extends class_1852 {
   public static class_1865<HeraldryRecipe> SERIALIZER = new class_1866(HeraldryRecipe::new);

   public HeraldryRecipe(class_7710 category) {
      super(category);
   }

   public boolean matches(class_9694 container, class_1937 level) {
      class_1799 stack = class_1799.field_8037;
      class_1799 stack2 = class_1799.field_8037;

      for (int i = 0; i < container.method_59983(); i++) {
         class_1799 stack3 = container.method_59984(i);
         if (!stack3.method_7960()) {
            if (stack3.method_7909() instanceof class_1746) {
               if (!stack2.method_7960()) {
                  return false;
               }

               stack2 = stack3;
            } else {
               if (!isApplicableForBanner(stack3.method_7909())) {
                  return false;
               }

               if (!stack.method_7960()) {
                  return false;
               }

               class_9307 patterns = (class_9307)stack3.method_57824(class_9334.field_49619);
               if (patterns != null && !patterns.comp_2428().isEmpty()) {
                  return false;
               }

               stack = stack3;
            }
         }
      }

      return !stack.method_7960() && !stack2.method_7960();
   }

   @NotNull
   public class_1799 assemble(class_9694 container, @NotNull class_7874 access) {
      class_1799 stack = class_1799.field_8037;
      class_1799 stack1 = class_1799.field_8037;

      for (int i = 0; i < container.method_59983(); i++) {
         class_1799 stack2 = container.method_59984(i);
         if (!stack2.method_7960()) {
            if (stack2.method_7909() instanceof class_1746) {
               stack = stack2;
            } else if (isApplicableForBanner(stack2.method_7909())) {
               stack1 = stack2.method_7972();
            }
         }
      }

      if (!stack1.method_7960()) {
         class_9307 patterns = (class_9307)stack.method_57824(class_9334.field_49619);
         class_1767 color = ((class_1746)stack.method_7909()).method_7706();
         if (wornWithSurcoat(stack1.method_7909())) {
            stack1.method_57379(
               class_9334.field_49631,
               class_2561.method_43469("magistuarmory.withsurcoat." + color.method_7792(), new Object[]{stack1.method_7964().getString()})
            );
         } else if (wornWithCaparison(stack1.method_7909())) {
            stack1.method_57379(
               class_9334.field_49631,
               class_2561.method_43469("magistuarmory.withcaparison." + color.method_7792(), new Object[]{stack1.method_7964().getString()})
            );
         }

         if (patterns != null) {
            stack1.method_57379(class_9334.field_49619, patterns);
         }

         stack1.method_57379(class_9334.field_49620, color);
      }

      return stack1;
   }

   public boolean method_8113(int p_44298_, int p_44299_) {
      return p_44298_ * p_44299_ >= 2;
   }

   @NotNull
   public class_1865<?> method_8119() {
      return getSerializerInstance();
   }

   @ExpectPlatform
   @Transformed
   public static class_1865<HeraldryRecipe> getSerializerInstance() {
      return HeraldryRecipeImpl.getSerializerInstance();
   }

   static boolean isPaintableShield(class_1792 item) {
      return item instanceof MedievalShieldItem && ((MedievalShieldItem)item).isPaintable();
   }

   static boolean wornWithCaparison(class_1792 item) {
      return item instanceof class_4059 animalarmor && animalarmor.method_55756().equals(class_9076.field_47825);
   }

   static boolean wornWithSurcoat(class_1792 item) {
      return item instanceof class_1738
         && (EpicKnights.GENERAL_CONFIG.enableSurcoatRecipeForAllArmor || item instanceof ISurcoat)
         && ((class_1738)item).method_48398().method_48399().equals(class_1304.field_6174);
   }

   static boolean isApplicableForBanner(class_1792 item) {
      return isPaintableShield(item) || wornWithCaparison(item) || wornWithSurcoat(item);
   }
}
