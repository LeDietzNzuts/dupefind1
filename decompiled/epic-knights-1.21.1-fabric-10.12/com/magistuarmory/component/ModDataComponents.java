package com.magistuarmory.component;

import com.mojang.serialization.Codec;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.class_7924;
import net.minecraft.class_9135;
import net.minecraft.class_9279;
import net.minecraft.class_9331;

public class ModDataComponents {
   public static final DeferredRegister<class_9331<?>> COMPONENT_TYPES = DeferredRegister.create("magistuarmory", class_7924.field_49659);
   public static final RegistrySupplier<class_9331<Integer>> TWO_HANDED_PENALTY = COMPONENT_TYPES.register(
      "two_handed_penalty", () -> class_9331.method_57873().method_57881(Codec.INT).method_57882(class_9135.field_49675).method_57880()
   );
   public static final RegistrySupplier<class_9331<Integer>> RAISED = COMPONENT_TYPES.register(
      "raised", () -> class_9331.method_57873().method_57881(Codec.intRange(0, 1)).method_57882(class_9135.field_49675).method_57880()
   );
   public static final RegistrySupplier<class_9331<Boolean>> DISMOUNT = COMPONENT_TYPES.register(
      "dismount", () -> class_9331.method_57873().method_57881(Codec.BOOL).method_57882(class_9135.field_48547).method_57880()
   );
   public static final RegistrySupplier<class_9331<Float>> RIDE_SPEED = COMPONENT_TYPES.register(
      "ride_speed", () -> class_9331.method_57873().method_57881(Codec.FLOAT).method_57882(class_9135.field_48552).method_57880()
   );
   public static final RegistrySupplier<class_9331<class_9279>> ARMOR_DECORATION = COMPONENT_TYPES.register(
      "armor_decoration", () -> class_9331.method_57873().method_57881(class_9279.field_49303).method_57882(class_9279.field_49305).method_57880()
   );

   public static void init() {
      COMPONENT_TYPES.register();
   }
}
