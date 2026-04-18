package me.shedaniel.clothconfig2.gui.widget;

import java.util.List;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_364;
import net.minecraft.class_4069;
import net.minecraft.class_437;
import net.minecraft.class_6379;
import net.minecraft.class_6381;
import net.minecraft.class_6382;
import net.minecraft.class_8016;
import net.minecraft.class_8023;
import net.minecraft.class_8027;
import net.minecraft.class_8028;
import net.minecraft.class_437.class_6390;
import net.minecraft.class_6379.class_6380;
import net.minecraft.class_8023.class_8024;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public abstract class DynamicElementListWidget<E extends DynamicElementListWidget.ElementEntry<E>> extends DynamicSmoothScrollingEntryListWidget<E> {
   private static final class_2561 USAGE_NARRATION = class_2561.method_43471("narration.selection.usage");

   public DynamicElementListWidget(class_310 client, int width, int height, int top, int bottom, class_2960 backgroundLocation) {
      super(client, width, height, top, bottom, backgroundLocation);
   }

   @Nullable
   public class_8016 method_48205(class_8023 focusNavigationEvent) {
      if (this.getItemCount() == 0) {
         return null;
      } else if (!(focusNavigationEvent instanceof class_8024 arrowNavigation)) {
         return super.method_48205(focusNavigationEvent);
      } else {
         DynamicElementListWidget.ElementEntry entry = this.getFocused();
         if (arrowNavigation.comp_1191().method_48237() == class_8027.field_41822 && entry != null) {
            return class_8016.method_48192(this, entry.method_48205(focusNavigationEvent));
         } else {
            int i = -1;
            class_8028 screenDirection = arrowNavigation.comp_1191();
            if (entry != null) {
               i = entry.method_25396().indexOf(entry.method_25399());
            }

            if (i == -1) {
               switch (screenDirection) {
                  case field_41828:
                     i = Integer.MAX_VALUE;
                     screenDirection = class_8028.field_41827;
                     break;
                  case field_41829:
                     i = 0;
                     screenDirection = class_8028.field_41827;
                     break;
                  default:
                     i = 0;
               }
            }

            E entry2 = (E)entry;

            class_8016 componentPath;
            do {
               entry2 = this.nextEntry(screenDirection, entryx -> !entryx.method_25396().isEmpty(), entry2);
               if (entry2 == null) {
                  return null;
               }

               componentPath = entry2.focusPathAtIndex(arrowNavigation, i);
            } while (componentPath == null);

            return class_8016.method_48192(this, componentPath);
         }
      }
   }

   @Override
   public void method_37020(class_6382 narrationElementOutput) {
      E entry = this.hoveredItem;
      if (entry != null) {
         entry.method_37020(narrationElementOutput.method_37031());
         this.narrateListElementPosition(narrationElementOutput, entry);
      } else {
         E entry2 = this.getFocused();
         if (entry2 != null) {
            entry2.method_37020(narrationElementOutput.method_37031());
            this.narrateListElementPosition(narrationElementOutput, entry2);
         }
      }

      narrationElementOutput.method_37034(class_6381.field_33791, class_2561.method_43471("narration.component_list.usage"));
   }

   @Override
   public void method_25395(@Nullable class_364 guiEventListener) {
      super.method_25395(guiEventListener);
      if (guiEventListener == null) {
         this.selectItem(null);
      }
   }

   @Override
   public class_6380 method_37018() {
      return this.method_25370() ? class_6380.field_33786 : super.method_37018();
   }

   @Override
   protected boolean isSelected(int i) {
      return false;
   }

   @Environment(EnvType.CLIENT)
   public abstract static class ElementEntry<E extends DynamicElementListWidget.ElementEntry<E>>
      extends DynamicEntryListWidget.Entry<E>
      implements class_4069,
      class_6379 {
      @Nullable
      private class_364 focused;
      @Nullable
      private class_6379 lastNarratable;
      private boolean dragging;

      public boolean method_25397() {
         return this.dragging;
      }

      public void method_25398(boolean bl) {
         this.dragging = bl;
      }

      @Nullable
      public class_364 method_25399() {
         return this.focused;
      }

      public void method_25395(@Nullable class_364 guiEventListener) {
         if (this.focused != null) {
            this.focused.method_25365(false);
         }

         if (guiEventListener != null) {
            guiEventListener.method_25365(true);
         }

         this.focused = guiEventListener;
      }

      @Nullable
      public class_8016 focusPathAtIndex(class_8023 focusNavigationEvent, int i) {
         if (this.method_25396().isEmpty()) {
            return null;
         } else {
            class_8016 componentPath = ((class_364)this.method_25396().get(Math.min(i, this.method_25396().size() - 1))).method_48205(focusNavigationEvent);
            return class_8016.method_48192(this, componentPath);
         }
      }

      @Nullable
      public class_8016 method_48205(class_8023 focusNavigationEvent) {
         if (focusNavigationEvent instanceof class_8024 arrowNavigation) {
            int var10000 = switch (arrowNavigation.comp_1191()) {
               case field_41828 -> -1;
               case field_41829 -> 1;
               case field_41826, field_41827 -> 0;
               default -> throw new MatchException(null, null);
            };
            if (var10000 == 0) {
               return null;
            }

            int j = class_3532.method_15340(var10000 + this.method_25396().indexOf(this.method_25399()), 0, this.method_25396().size() - 1);

            for (int k = j; k >= 0 && k < this.method_25396().size(); k += var10000) {
               class_364 guiEventListener = (class_364)this.method_25396().get(k);
               class_8016 componentPath = guiEventListener.method_48205(focusNavigationEvent);
               if (componentPath != null) {
                  return class_8016.method_48192(this, componentPath);
               }
            }
         }

         return super.method_48205(focusNavigationEvent);
      }

      @Override
      public abstract List<? extends class_6379> narratables();

      @Override
      public void method_37020(class_6382 narrationElementOutput) {
         List<? extends class_6379> list = this.narratables();
         class_6390 narratableSearchResult = class_437.method_37061(list, this.lastNarratable);
         if (narratableSearchResult != null) {
            if (narratableSearchResult.field_33827.method_37028()) {
               this.lastNarratable = narratableSearchResult.field_33825;
            }

            if (list.size() > 1) {
               narrationElementOutput.method_37034(
                  class_6381.field_33789,
                  class_2561.method_43469("narrator.position.object_list", new Object[]{narratableSearchResult.field_33826 + 1, list.size()})
               );
               if (narratableSearchResult.field_33827 == class_6380.field_33786) {
                  narrationElementOutput.method_37034(class_6381.field_33791, class_2561.method_43471("narration.component_list.usage"));
               }
            }

            narratableSearchResult.field_33825.method_37020(narrationElementOutput.method_37031());
         }
      }

      public boolean method_37303() {
         return false;
      }

      public class_6380 method_37018() {
         return this.method_25370() ? class_6380.field_33786 : class_6380.field_33784;
      }

      public boolean method_25402(double d, double e, int i) {
         return !this.isEnabled() ? false : super.method_25402(d, e, i);
      }

      public boolean method_25406(double d, double e, int i) {
         return !this.isEnabled() ? false : super.method_25406(d, e, i);
      }

      public boolean method_25403(double d, double e, int i, double f, double g) {
         return !this.isEnabled() ? false : super.method_25403(d, e, i, f, g);
      }

      public boolean method_25401(double d, double e, double amountX, double amountY) {
         return !this.isEnabled() ? false : super.method_25401(d, e, amountX, amountY);
      }

      public boolean method_25404(int i, int j, int k) {
         return !this.isEnabled() ? false : super.method_25404(i, j, k);
      }

      public boolean method_16803(int i, int j, int k) {
         return !this.isEnabled() ? false : super.method_16803(i, j, k);
      }

      public boolean method_25400(char c, int i) {
         return !this.isEnabled() ? false : super.method_25400(c, i);
      }
   }
}
