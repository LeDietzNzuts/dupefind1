package net.p3pp3rf1y.sophisticatedcore.crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.class_1767;
import net.minecraft.class_1769;
import net.minecraft.class_1799;
import net.minecraft.class_1852;
import net.minecraft.class_1937;
import net.minecraft.class_2960;
import net.minecraft.class_3545;
import net.minecraft.class_6862;
import net.minecraft.class_7710;
import net.minecraft.class_7924;
import net.minecraft.class_9694;
import net.minecraft.class_7225.class_7874;

public abstract class StorageDyeRecipeBase extends class_1852 {
   protected StorageDyeRecipeBase(class_7710 category) {
      super(category);
   }

   public boolean matches(class_9694 inv, class_1937 worldIn) {
      boolean storagePresent = false;
      boolean dyePresent = false;

      for (int slot = 0; slot < inv.method_59983(); slot++) {
         class_1799 slotStack = inv.method_59984(slot);
         if (!slotStack.method_7960()) {
            if (this.isDyeableStorageItem(slotStack)) {
               if (storagePresent) {
                  return false;
               }

               storagePresent = true;
            } else {
               if (!slotStack.method_31573(ConventionalItemTags.DYES)) {
                  return false;
               }

               dyePresent = true;
            }
         }
      }

      return storagePresent && dyePresent;
   }

   public class_1799 assemble(class_9694 inv, class_7874 registries) {
      Map<Integer, List<class_1767>> columnDyes = new HashMap<>();
      class_3545<Integer, class_1799> columnStorage = null;

      for (int slot = 0; slot < inv.method_59983(); slot++) {
         class_1799 slotStack = inv.method_59984(slot);
         if (!slotStack.method_7960()) {
            int column = slot % inv.method_59991();
            if (this.isDyeableStorageItem(slotStack)) {
               if (columnStorage != null) {
                  return class_1799.field_8037;
               }

               columnStorage = new class_3545(column, slotStack);
            } else {
               if (!slotStack.method_31573(ConventionalItemTags.DYES)) {
                  return class_1799.field_8037;
               }

               class_1767 dyeColor = getColorFromStack(slotStack);
               if (dyeColor == null) {
                  return class_1799.field_8037;
               }

               columnDyes.computeIfAbsent(column, c -> new ArrayList<>()).add(dyeColor);
            }
         }
      }

      if (columnStorage == null) {
         return class_1799.field_8037;
      } else {
         class_1799 coloredStorage = ((class_1799)columnStorage.method_15441()).method_7972();
         coloredStorage.method_7939(1);
         int storageColumn = (Integer)columnStorage.method_15442();
         this.applyTintColors(columnDyes, coloredStorage, storageColumn);
         return coloredStorage;
      }
   }

   protected abstract boolean isDyeableStorageItem(class_1799 var1);

   private void applyTintColors(Map<Integer, List<class_1767>> columnDyes, class_1799 coloredStorage, int storageColumn) {
      List<class_1767> mainDyes = new ArrayList<>();
      List<class_1767> trimDyes = new ArrayList<>();

      for (Entry<Integer, List<class_1767>> entry : columnDyes.entrySet()) {
         if (entry.getKey() <= storageColumn) {
            mainDyes.addAll(entry.getValue());
         }

         if (entry.getKey() >= storageColumn) {
            trimDyes.addAll(entry.getValue());
         }
      }

      this.applyColors(coloredStorage, mainDyes, trimDyes);
   }

   protected abstract void applyColors(class_1799 var1, List<class_1767> var2, List<class_1767> var3);

   public boolean method_8113(int width, int height) {
      return width >= 2 && height >= 1;
   }

   @Nullable
   public static class_1767 getColorFromStack(class_1799 stack) {
      if (stack.method_7909() instanceof class_1769 dyeItem) {
         return dyeItem.method_7802();
      } else {
         for (class_1767 color : class_1767.values()) {
            if (stack.method_31573(class_6862.method_40092(class_7924.field_41197, class_2960.method_60655("c", color.method_7792() + "_dyes")))) {
               return color;
            }
         }

         return null;
      }
   }
}
