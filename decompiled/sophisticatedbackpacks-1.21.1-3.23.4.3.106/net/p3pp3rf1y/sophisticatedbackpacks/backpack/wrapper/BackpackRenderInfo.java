package net.p3pp3rf1y.sophisticatedbackpacks.backpack.wrapper;

import java.util.Optional;
import java.util.function.Supplier;
import net.minecraft.class_1799;
import net.minecraft.class_2487;
import net.minecraft.class_9279;
import net.p3pp3rf1y.sophisticatedcore.init.ModCoreDataComponents;
import net.p3pp3rf1y.sophisticatedcore.renderdata.RenderInfo;

public class BackpackRenderInfo extends RenderInfo {
   private final class_1799 backpack;

   public BackpackRenderInfo(class_1799 backpack, Supplier<Runnable> getSaveHandler) {
      super(getSaveHandler);
      this.backpack = backpack;
      this.deserialize();
   }

   protected void serializeRenderInfo(class_2487 renderInfo) {
      this.backpack.sophisticatedCore_set(ModCoreDataComponents.RENDER_INFO_TAG, class_9279.method_57456(renderInfo));
   }

   protected Optional<class_2487> getRenderInfoTag() {
      return Optional.ofNullable((class_9279)this.backpack.sophisticatedCore_get(ModCoreDataComponents.RENDER_INFO_TAG)).map(class_9279::method_57461);
   }
}
