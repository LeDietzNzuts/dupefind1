package com.talhanation.smallships.world.particles;

import com.mojang.serialization.MapCodec;
import com.talhanation.smallships.world.particles.cannon.DyedCannonShootOptions;
import com.talhanation.smallships.world.particles.custom.CustomPoofParticleOptions;
import com.talhanation.smallships.world.particles.fabric.ModParticleTypesImpl;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import java.util.function.Supplier;
import net.minecraft.class_2394;
import net.minecraft.class_2396;
import net.minecraft.class_2400;
import net.minecraft.class_9129;
import net.minecraft.class_9139;
import org.jetbrains.annotations.NotNull;

public class ModParticleTypes {
   public static final Supplier<class_2400> CANNON_SHOOT = register("basic_cannon_shoot");
   public static final Supplier<class_2396<DyedCannonShootOptions>> DYED_CANNON_SHOOT = register(
      "dyed_cannon_shoot", DyedCannonShootOptions.MAP_CODEC, DyedCannonShootOptions.STREAM_CODEC
   );
   public static final Supplier<class_2400> CANNON_BALL_SHOOT = register("cannon_ball_shoot");
   public static final Supplier<class_2396<CustomPoofParticleOptions>> COLORED_POOF = register(
      "colored_poof", CustomPoofParticleOptions.MAP_CODEC, CustomPoofParticleOptions.STREAM_CODEC
   );

   public static Supplier<class_2400> register(String id) {
      return register(id, false);
   }

   public static Supplier<class_2400> register(String id, boolean overrideLimiter) {
      class_2400 type = new ModParticleTypes.SimpleParticleTypeImpl(overrideLimiter);
      return register(id, type);
   }

   public static <T extends class_2394> Supplier<class_2396<T>> register(
      String string, MapCodec<T> codecSupplier, class_9139<? super class_9129, T> streamCodecSupplier
   ) {
      class_2396<T> type = new class_2396<T>(false) {
         @NotNull
         public MapCodec<T> method_29138() {
            return codecSupplier;
         }

         @NotNull
         public class_9139<? super class_9129, T> method_56179() {
            return streamCodecSupplier;
         }
      };
      return register(string, type);
   }

   @ExpectPlatform
   @NotNull
   @Transformed
   public static <T extends class_2394> Supplier<class_2396<T>> register(String string, class_2396<T> particleType) {
      return ModParticleTypesImpl.register(string, particleType);
   }

   private static class SimpleParticleTypeImpl extends class_2400 {
      public SimpleParticleTypeImpl(boolean bl) {
         super(bl);
      }
   }
}
