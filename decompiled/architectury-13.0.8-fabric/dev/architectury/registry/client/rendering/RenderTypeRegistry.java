package dev.architectury.registry.client.rendering;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import dev.architectury.registry.client.rendering.fabric.RenderTypeRegistryImpl;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1921;
import net.minecraft.class_2248;
import net.minecraft.class_3611;

@Environment(EnvType.CLIENT)
public final class RenderTypeRegistry {
   private RenderTypeRegistry() {
   }

   @ExpectPlatform
   @Transformed
   public static void register(class_1921 type, class_2248... blocks) {
      RenderTypeRegistryImpl.register(type, blocks);
   }

   @ExpectPlatform
   @Transformed
   public static void register(class_1921 type, class_3611... fluids) {
      RenderTypeRegistryImpl.register(type, fluids);
   }
}
