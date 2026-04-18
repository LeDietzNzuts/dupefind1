package com.magistuarmory.item.crafting;

import com.magistuarmory.component.ModDataComponents;
import com.magistuarmory.item.ArmorDecoration;
import com.magistuarmory.item.DyeableArmorDecorationItem;
import com.magistuarmory.item.MedievalBagItem;
import com.magistuarmory.item.ModItems;
import com.magistuarmory.item.crafting.fabric.DecorationRemoveRecipeImpl;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1738;
import net.minecraft.class_1746;
import net.minecraft.class_1767;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1819;
import net.minecraft.class_1852;
import net.minecraft.class_1865;
import net.minecraft.class_1866;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_2215;
import net.minecraft.class_2487;
import net.minecraft.class_2499;
import net.minecraft.class_2960;
import net.minecraft.class_7710;
import net.minecraft.class_7923;
import net.minecraft.class_9279;
import net.minecraft.class_9307;
import net.minecraft.class_9331;
import net.minecraft.class_9334;
import net.minecraft.class_9694;
import net.minecraft.class_7225.class_7874;
import org.jetbrains.annotations.NotNull;

public class DecorationRemoveRecipe extends class_1852 {
   public static class_1865<DecorationRemoveRecipe> SERIALIZER = new class_1866(DecorationRemoveRecipe::new);

   public DecorationRemoveRecipe(class_7710 category) {
      super(category);
   }

   public boolean matches(class_9694 container, class_1937 level) {
      class_1799 stack = class_1799.field_8037;

      for (int i = 0; i < container.method_59983(); i++) {
         class_1799 stack2 = container.method_59984(i);
         if (!stack2.method_7960()) {
            if (!isDecorated(stack2)) {
               return false;
            }

            if (!stack.method_7960()) {
               return false;
            }

            stack = stack2;
         }
      }

      return !stack.method_7960();
   }

   @NotNull
   public class_1799 assemble(class_9694 container, @NotNull class_7874 access) {
      class_1799 stack = class_1799.field_8037;

      for (int i = 0; i < container.method_59983(); i++) {
         class_1799 stack2 = container.method_59984(i);
         if (!stack2.method_7960() && isDecorated(stack2)) {
            stack = stack2;
            break;
         }
      }

      if (!stack.method_7960()) {
         class_1799 bagstack = new class_1799((class_1935)ModItems.MEDIEVAL_BAG.get());
         List<class_1799> stacks = takeApart(stack);
         if (stacks.isEmpty()) {
            return class_1799.field_8037;
         } else {
            MedievalBagItem.setContents(bagstack, stacks);
            return bagstack;
         }
      } else {
         return class_1799.field_8037;
      }
   }

   public boolean method_8113(int i, int j) {
      return i * j >= 1;
   }

   public class_1865<?> method_8119() {
      return getSerializerInstance();
   }

   @ExpectPlatform
   @Transformed
   public static class_1865<DecorationRemoveRecipe> getSerializerInstance() {
      return DecorationRemoveRecipeImpl.getSerializerInstance();
   }

   static boolean mightBeDecorated(class_1792 item) {
      return item instanceof class_1738 || item instanceof class_1819;
   }

   static boolean isDecorated(class_1799 stack) {
      if (!mightBeDecorated(stack.method_7909())) {
         return false;
      } else {
         class_9279 decorationdata = (class_9279)stack.method_57824((class_9331)ModDataComponents.ARMOR_DECORATION.get());
         if (decorationdata != null) {
            class_2487 compoundtag = decorationdata.method_57461();
            class_2499 listtag = compoundtag.method_10554("Items", 10);
            if (!listtag.isEmpty()) {
               return true;
            }
         }

         return stack.method_57824(class_9334.field_49619) != null;
      }
   }

   static List<class_1799> takeApart(class_1799 stack) {
      List<class_1799> stacks = new ArrayList<>();
      class_1799 newstack = stack.method_7972();
      class_1767 basecolor = (class_1767)newstack.method_57824(class_9334.field_49620);
      if (basecolor != null) {
         class_1746 banner = (class_1746)class_2215.method_9398(basecolor).method_8389();
         class_1799 bannerstack = new class_1799(banner);
         class_9307 patterns = (class_9307)newstack.method_57824(class_9334.field_49619);
         if (patterns != null && !patterns.comp_2428().isEmpty()) {
            bannerstack.method_57379(class_9334.field_49619, patterns);
         }

         stacks.add(bannerstack);
      }

      class_9279 decorationdata = (class_9279)newstack.method_57824((class_9331)ModDataComponents.ARMOR_DECORATION.get());
      if (decorationdata != null) {
         class_2487 compoundtag = decorationdata.method_57461();
         class_2499 listtag = compoundtag.method_10554("Items", 10);

         while (!listtag.isEmpty()) {
            class_2487 tag = listtag.method_10602(listtag.size() - 1);
            String name = tag.method_10558("name");
            int color = tag.method_10550("color");
            ArmorDecoration decoration = (ArmorDecoration)class_7923.field_41178.method_10223(class_2960.method_60654(name + "_decoration"));
            class_1799 decorationstack = new class_1799(decoration);
            if (decoration instanceof DyeableArmorDecorationItem dyeabedecoration && dyeabedecoration.getColor(decorationstack) != color) {
               dyeabedecoration.setColor(decorationstack, color);
            }

            listtag.method_10536(listtag.size() - 1);
            compoundtag.method_10566("Items", listtag);
            stacks.add(decorationstack);
         }
      }

      newstack.method_57381((class_9331)ModDataComponents.ARMOR_DECORATION.get());
      newstack.method_57381(class_9334.field_49619);
      newstack.method_57381(class_9334.field_49620);
      newstack.method_57381(class_9334.field_49631);
      stacks.add(newstack);
      return stacks;
   }
}
