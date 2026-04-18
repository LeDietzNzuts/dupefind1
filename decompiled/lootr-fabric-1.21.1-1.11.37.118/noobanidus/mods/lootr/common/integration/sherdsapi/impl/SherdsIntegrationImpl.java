package noobanidus.mods.lootr.common.integration.sherdsapi.impl;

import dev.thomasglasser.sherdsapi.impl.StackPotDecorations;
import dev.thomasglasser.sherdsapi.impl.StackPotDecorationsHolder;
import net.minecraft.class_1799;
import net.minecraft.class_2586;
import net.minecraft.class_2960;
import net.minecraft.class_7923;
import net.minecraft.class_9331;
import net.minecraft.class_2586.class_9473;
import noobanidus.mods.lootr.common.api.LootrConstants;
import noobanidus.mods.lootr.common.api.PotDecorationsAdapter;
import org.jetbrains.annotations.Nullable;

public class SherdsIntegrationImpl {
   private static class_9331<StackPotDecorations> type = null;
   private static boolean checked = false;
   private static class_9331<class_2960> type2 = null;
   private static boolean checked2 = false;

   @Nullable
   private static class_9331<StackPotDecorations> getSherdsDecorationsComponent() {
      if (!checked) {
         checked = true;
         class_9331<?> comp = (class_9331<?>)class_7923.field_49658.method_10223(LootrConstants.SHERDSAPI_POT_DECORATIONS);
         if (comp == null) {
            return null;
         }

         type = (class_9331<StackPotDecorations>)comp;
      }

      return type;
   }

   @Nullable
   private static class_9331<class_2960> getSherdsTextureComponent() {
      if (!checked2) {
         checked2 = true;
         class_9331<?> comp = (class_9331<?>)class_7923.field_49658.method_10223(LootrConstants.SHERDSAPI_SHERD_PATTERN);
         if (comp == null) {
            return null;
         }

         type2 = (class_9331<class_2960>)comp;
      }

      return type2;
   }

   @Nullable
   public static PotDecorationsAdapter getAdapterFrom(class_9473 stack) {
      class_9331<StackPotDecorations> sherdsType = getSherdsDecorationsComponent();
      if (sherdsType == null) {
         return null;
      } else {
         StackPotDecorations decorations = (StackPotDecorations)stack.method_58694(sherdsType);
         return decorations == null ? null : new PotDecorationsAdapter(decorations.ordered());
      }
   }

   @Nullable
   public static PotDecorationsAdapter getAdapterFrom(class_1799 stack) {
      class_9331<StackPotDecorations> sherdsType = getSherdsDecorationsComponent();
      if (sherdsType == null) {
         return null;
      } else if (!stack.method_57826(sherdsType)) {
         return null;
      } else {
         StackPotDecorations decorations = (StackPotDecorations)stack.method_57824(sherdsType);
         return decorations == null ? null : new PotDecorationsAdapter(decorations.ordered());
      }
   }

   @Nullable
   public static PotDecorationsAdapter getAdapterFrom(class_2586 blockEntity) {
      if (blockEntity instanceof StackPotDecorationsHolder holderType) {
         StackPotDecorations decorations = holderType.sherdsapi$getDecorations();
         return decorations == null ? null : new PotDecorationsAdapter(decorations.ordered());
      } else {
         return null;
      }
   }

   @Nullable
   public static class_2960 getCustomSideTexture(class_1799 item) {
      class_9331<class_2960> textureType = getSherdsTextureComponent();
      if (textureType == null) {
         return null;
      } else {
         return !item.method_57826(textureType) ? null : (class_2960)item.method_57824(textureType);
      }
   }
}
