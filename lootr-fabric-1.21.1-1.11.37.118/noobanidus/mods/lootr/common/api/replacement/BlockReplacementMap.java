package noobanidus.mods.lootr.common.api.replacement;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.class_2248;
import net.minecraft.class_2281;
import net.minecraft.class_2680;
import net.minecraft.class_2745;
import net.minecraft.class_2769;
import net.minecraft.class_6880.class_6883;
import noobanidus.mods.lootr.common.api.LootrTags;
import org.jetbrains.annotations.Nullable;

public class BlockReplacementMap {
   private final Set<class_2248> ignoredBlocks = new HashSet<>();
   private final Map<class_2248, class_2248> replacements = new Object2ObjectOpenHashMap();
   private final List<ILootrBlockReplacementProvider> allProviders = new ArrayList<>();

   public void register(ILootrBlockReplacementProvider provider) {
      this.allProviders.add(provider);
   }

   public void sort() {
      this.allProviders.sort(Comparator.comparingInt(ILootrBlockReplacementProvider::getPriority).reversed());
   }

   public void clear() {
      this.ignoredBlocks.clear();
      this.replacements.clear();
   }

   @Nullable
   public class_2680 getReplacement(class_2680 state) {
      class_2248 block = state.method_26204();
      if (this.ignoredBlocks.contains(block)) {
         return null;
      } else {
         class_6883<class_2248> holder = block.method_40142();
         if (holder.method_40220(LootrTags.Blocks.CONVERT_BLACKLIST)) {
            this.ignoredBlocks.add(block);
            return null;
         } else {
            class_2248 result = this.replacements.get(block);
            if (result == null) {
               for (ILootrBlockReplacementProvider provider : this.allProviders) {
                  class_2248 replacement = provider.apply(block);
                  if (replacement != null) {
                     result = replacement;
                     break;
                  }
               }
            }

            if (result == null) {
               this.ignoredBlocks.add(block);
               return null;
            } else {
               class_2680 replacement = result.method_9564();

               for (class_2769<?> prop : replacement.method_28501()) {
                  if (state.method_28498(prop)) {
                     replacement = safeReplace(replacement, state, prop);
                  }
               }

               return replacement;
            }
         }
      }
   }

   public static <V extends Comparable<V>> class_2680 safeReplace(class_2680 state, class_2680 original, class_2769<V> property) {
      if (property == class_2281.field_10770 && state.method_28498(property)) {
         return (class_2680)state.method_11657(class_2281.field_10770, class_2745.field_12569);
      } else {
         return original.method_28498(property) && state.method_28498(property)
            ? (class_2680)state.method_11657(property, original.method_11654(property))
            : state;
      }
   }
}
