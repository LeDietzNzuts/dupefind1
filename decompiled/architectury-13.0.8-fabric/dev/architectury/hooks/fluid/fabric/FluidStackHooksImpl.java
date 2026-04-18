package dev.architectury.hooks.fluid.fabric;

import com.mojang.logging.LogUtils;
import dev.architectury.fluid.FluidStack;
import java.util.Optional;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.transfer.v1.client.fluid.FluidVariantRendering;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariantAttributes;
import net.minecraft.class_1058;
import net.minecraft.class_1920;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_2509;
import net.minecraft.class_2520;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_3610;
import net.minecraft.class_3611;
import net.minecraft.class_3612;
import net.minecraft.class_7923;
import net.minecraft.class_9129;
import net.minecraft.class_7225.class_7874;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

public class FluidStackHooksImpl {
   private static final Logger LOGGER = LogUtils.getLogger();

   public static class_2561 getName(FluidStack stack) {
      return FluidVariantAttributes.getName(FluidStackHooksFabric.toFabric(stack));
   }

   public static String getTranslationKey(FluidStack stack) {
      class_2960 id = class_7923.field_41173.method_10221(stack.getFluid());
      return "block." + id.method_12836() + "." + id.method_12832();
   }

   public static FluidStack read(class_9129 buf) {
      return (FluidStack)FluidStack.STREAM_CODEC.decode(buf);
   }

   public static void write(FluidStack stack, class_9129 buf) {
      FluidStack.STREAM_CODEC.encode(buf, stack);
   }

   public static Optional<FluidStack> read(class_7874 provider, class_2520 tag) {
      return FluidStack.CODEC
         .parse(provider.method_57093(class_2509.field_11560), tag)
         .resultOrPartial(string -> LOGGER.error("Tried to load invalid fluid stack: '{}'", string));
   }

   public static FluidStack readOptional(class_7874 provider, class_2487 tag) {
      return tag.method_33133() ? FluidStack.empty() : read(provider, tag).orElse(FluidStack.empty());
   }

   public static class_2520 write(class_7874 provider, FluidStack stack, class_2520 tag) {
      return (class_2520)FluidStack.CODEC.encode(stack, provider.method_57093(class_2509.field_11560), tag).getOrThrow(IllegalStateException::new);
   }

   public static long bucketAmount() {
      return 81000L;
   }

   @Environment(EnvType.CLIENT)
   @Nullable
   public static class_1058 getStillTexture(@Nullable class_1920 level, @Nullable class_2338 pos, class_3610 state) {
      if (state.method_15772() == class_3612.field_15906) {
         return null;
      } else {
         FluidRenderHandler handler = FluidRenderHandlerRegistry.INSTANCE.get(state.method_15772());
         if (handler == null) {
            return null;
         } else {
            class_1058[] sprites = handler.getFluidSprites(level, pos, state);
            return sprites == null ? null : sprites[0];
         }
      }
   }

   @Environment(EnvType.CLIENT)
   @Nullable
   public static class_1058 getStillTexture(FluidStack stack) {
      class_1058[] sprites = FluidVariantRendering.getSprites(FluidStackHooksFabric.toFabric(stack));
      return sprites == null ? null : sprites[0];
   }

   @Environment(EnvType.CLIENT)
   @Nullable
   public static class_1058 getStillTexture(class_3611 fluid) {
      class_1058[] sprites = FluidVariantRendering.getSprites(FluidVariant.of(fluid));
      return sprites == null ? null : sprites[0];
   }

   @Environment(EnvType.CLIENT)
   @Nullable
   public static class_1058 getFlowingTexture(@Nullable class_1920 level, @Nullable class_2338 pos, class_3610 state) {
      if (state.method_15772() == class_3612.field_15906) {
         return null;
      } else {
         FluidRenderHandler handler = FluidRenderHandlerRegistry.INSTANCE.get(state.method_15772());
         if (handler == null) {
            return null;
         } else {
            class_1058[] sprites = handler.getFluidSprites(level, pos, state);
            return sprites == null ? null : sprites[1];
         }
      }
   }

   @Environment(EnvType.CLIENT)
   @Nullable
   public static class_1058 getFlowingTexture(FluidStack stack) {
      class_1058[] sprites = FluidVariantRendering.getSprites(FluidStackHooksFabric.toFabric(stack));
      return sprites == null ? null : sprites[1];
   }

   @Environment(EnvType.CLIENT)
   @Nullable
   public static class_1058 getFlowingTexture(class_3611 fluid) {
      class_1058[] sprites = FluidVariantRendering.getSprites(FluidVariant.of(fluid));
      return sprites == null ? null : sprites[1];
   }

   @Environment(EnvType.CLIENT)
   public static int getColor(@Nullable class_1920 level, @Nullable class_2338 pos, class_3610 state) {
      if (state.method_15772() == class_3612.field_15906) {
         return -1;
      } else {
         FluidRenderHandler handler = FluidRenderHandlerRegistry.INSTANCE.get(state.method_15772());
         return handler == null ? -1 : handler.getFluidColor(level, pos, state);
      }
   }

   @Environment(EnvType.CLIENT)
   public static int getColor(FluidStack stack) {
      return FluidVariantRendering.getColor(FluidStackHooksFabric.toFabric(stack));
   }

   @Environment(EnvType.CLIENT)
   public static int getColor(class_3611 fluid) {
      if (fluid == class_3612.field_15906) {
         return -1;
      } else {
         FluidRenderHandler handler = FluidRenderHandlerRegistry.INSTANCE.get(fluid);
         return handler == null ? -1 : handler.getFluidColor(null, null, fluid.method_15785());
      }
   }

   public static int getLuminosity(FluidStack fluid, @Nullable class_1937 level, @Nullable class_2338 pos) {
      return FluidVariantAttributes.getLuminance(FluidStackHooksFabric.toFabric(fluid));
   }

   public static int getLuminosity(class_3611 fluid, @Nullable class_1937 level, @Nullable class_2338 pos) {
      return FluidVariantAttributes.getLuminance(FluidVariant.of(fluid));
   }

   public static int getTemperature(FluidStack fluid, @Nullable class_1937 level, @Nullable class_2338 pos) {
      return FluidVariantAttributes.getTemperature(FluidStackHooksFabric.toFabric(fluid));
   }

   public static int getTemperature(class_3611 fluid, @Nullable class_1937 level, @Nullable class_2338 pos) {
      return FluidVariantAttributes.getTemperature(FluidVariant.of(fluid));
   }

   public static int getViscosity(FluidStack fluid, @Nullable class_1937 level, @Nullable class_2338 pos) {
      return FluidVariantAttributes.getViscosity(FluidStackHooksFabric.toFabric(fluid), level);
   }

   public static int getViscosity(class_3611 fluid, @Nullable class_1937 level, @Nullable class_2338 pos) {
      return FluidVariantAttributes.getViscosity(FluidVariant.of(fluid), level);
   }
}
