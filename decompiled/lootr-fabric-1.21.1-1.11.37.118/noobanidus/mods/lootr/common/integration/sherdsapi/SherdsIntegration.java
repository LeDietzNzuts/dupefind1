package noobanidus.mods.lootr.common.integration.sherdsapi;

import net.minecraft.class_1799;
import net.minecraft.class_2586;
import net.minecraft.class_2960;
import net.minecraft.class_2586.class_9473;
import noobanidus.mods.lootr.common.api.PotDecorationsAdapter;
import noobanidus.mods.lootr.common.integration.sherdsapi.impl.SherdsIntegrationImpl;
import org.jetbrains.annotations.Nullable;

public class SherdsIntegration {
   @Nullable
   public static PotDecorationsAdapter getAdapterFrom(class_2586 blockEntity) {
      return SherdsIntegrationImpl.getAdapterFrom(blockEntity);
   }

   @Nullable
   public static PotDecorationsAdapter getAdapterFrom(class_9473 stack) {
      return SherdsIntegrationImpl.getAdapterFrom(stack);
   }

   @Nullable
   public static PotDecorationsAdapter getAdapterFrom(class_1799 stack) {
      return SherdsIntegrationImpl.getAdapterFrom(stack);
   }

   @Nullable
   public static class_2960 getCustomSideTexture(class_1799 item) {
      return SherdsIntegrationImpl.getCustomSideTexture(item);
   }
}
