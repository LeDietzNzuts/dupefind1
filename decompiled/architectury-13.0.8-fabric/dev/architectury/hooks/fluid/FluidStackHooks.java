package dev.architectury.hooks.fluid;

import dev.architectury.fluid.FluidStack;
import dev.architectury.hooks.fluid.fabric.FluidStackHooksImpl;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import java.util.Optional;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_1058;
import net.minecraft.class_1920;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_2520;
import net.minecraft.class_2561;
import net.minecraft.class_3610;
import net.minecraft.class_3611;
import net.minecraft.class_9129;
import net.minecraft.class_7225.class_7874;
import org.jetbrains.annotations.Nullable;

public class FluidStackHooks {
   private FluidStackHooks() {
   }

   @ExpectPlatform
   @Transformed
   public static class_2561 getName(FluidStack stack) {
      return FluidStackHooksImpl.getName(stack);
   }

   @ExpectPlatform
   @Transformed
   public static String getTranslationKey(FluidStack stack) {
      return FluidStackHooksImpl.getTranslationKey(stack);
   }

   @ExpectPlatform
   @Transformed
   public static FluidStack read(class_9129 buf) {
      return FluidStackHooksImpl.read(buf);
   }

   @ExpectPlatform
   @Transformed
   public static void write(FluidStack stack, class_9129 buf) {
      FluidStackHooksImpl.write(stack, buf);
   }

   @ExpectPlatform
   @Transformed
   public static Optional<FluidStack> read(class_7874 provider, class_2520 tag) {
      return FluidStackHooksImpl.read(provider, tag);
   }

   @ExpectPlatform
   @Transformed
   public static FluidStack readOptional(class_7874 provider, class_2487 tag) {
      return FluidStackHooksImpl.readOptional(provider, tag);
   }

   @ExpectPlatform
   @Transformed
   public static class_2520 write(class_7874 provider, FluidStack stack, class_2520 tag) {
      return FluidStackHooksImpl.write(provider, stack, tag);
   }

   @ExpectPlatform
   @Transformed
   public static long bucketAmount() {
      return FluidStackHooksImpl.bucketAmount();
   }

   @ExpectPlatform
   @Environment(EnvType.CLIENT)
   @Nullable
   @Transformed
   public static class_1058 getStillTexture(@Nullable class_1920 level, @Nullable class_2338 pos, class_3610 state) {
      return FluidStackHooksImpl.getStillTexture(level, pos, state);
   }

   @ExpectPlatform
   @Environment(EnvType.CLIENT)
   @Nullable
   @Transformed
   public static class_1058 getStillTexture(FluidStack stack) {
      return FluidStackHooksImpl.getStillTexture(stack);
   }

   @ExpectPlatform
   @Environment(EnvType.CLIENT)
   @Nullable
   @Transformed
   public static class_1058 getStillTexture(class_3611 fluid) {
      return FluidStackHooksImpl.getStillTexture(fluid);
   }

   @ExpectPlatform
   @Environment(EnvType.CLIENT)
   @Nullable
   @Transformed
   public static class_1058 getFlowingTexture(@Nullable class_1920 level, @Nullable class_2338 pos, class_3610 state) {
      return FluidStackHooksImpl.getFlowingTexture(level, pos, state);
   }

   @ExpectPlatform
   @Environment(EnvType.CLIENT)
   @Nullable
   @Transformed
   public static class_1058 getFlowingTexture(FluidStack stack) {
      return FluidStackHooksImpl.getFlowingTexture(stack);
   }

   @ExpectPlatform
   @Environment(EnvType.CLIENT)
   @Nullable
   @Transformed
   public static class_1058 getFlowingTexture(class_3611 fluid) {
      return FluidStackHooksImpl.getFlowingTexture(fluid);
   }

   @ExpectPlatform
   @Environment(EnvType.CLIENT)
   @Transformed
   public static int getColor(@Nullable class_1920 level, @Nullable class_2338 pos, class_3610 state) {
      return FluidStackHooksImpl.getColor(level, pos, state);
   }

   @ExpectPlatform
   @Environment(EnvType.CLIENT)
   @Transformed
   public static int getColor(FluidStack stack) {
      return FluidStackHooksImpl.getColor(stack);
   }

   @ExpectPlatform
   @Environment(EnvType.CLIENT)
   @Transformed
   public static int getColor(class_3611 fluid) {
      return FluidStackHooksImpl.getColor(fluid);
   }

   @ExpectPlatform
   @Transformed
   public static int getLuminosity(FluidStack fluid, @Nullable class_1937 level, @Nullable class_2338 pos) {
      return FluidStackHooksImpl.getLuminosity(fluid, level, pos);
   }

   @ExpectPlatform
   @Transformed
   public static int getLuminosity(class_3611 fluid, @Nullable class_1937 level, @Nullable class_2338 pos) {
      return FluidStackHooksImpl.getLuminosity(fluid, level, pos);
   }

   @ExpectPlatform
   @Transformed
   public static int getTemperature(FluidStack fluid, @Nullable class_1937 level, @Nullable class_2338 pos) {
      return FluidStackHooksImpl.getTemperature(fluid, level, pos);
   }

   @ExpectPlatform
   @Transformed
   public static int getTemperature(class_3611 fluid, @Nullable class_1937 level, @Nullable class_2338 pos) {
      return FluidStackHooksImpl.getTemperature(fluid, level, pos);
   }

   @ExpectPlatform
   @Transformed
   public static int getViscosity(FluidStack fluid, @Nullable class_1937 level, @Nullable class_2338 pos) {
      return FluidStackHooksImpl.getViscosity(fluid, level, pos);
   }

   @ExpectPlatform
   @Transformed
   public static int getViscosity(class_3611 fluid, @Nullable class_1937 level, @Nullable class_2338 pos) {
      return FluidStackHooksImpl.getViscosity(fluid, level, pos);
   }
}
