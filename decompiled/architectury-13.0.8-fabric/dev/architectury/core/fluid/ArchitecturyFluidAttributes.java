package dev.architectury.core.fluid;

import dev.architectury.fluid.FluidStack;
import net.minecraft.class_1792;
import net.minecraft.class_1814;
import net.minecraft.class_1920;
import net.minecraft.class_2338;
import net.minecraft.class_2404;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_3414;
import net.minecraft.class_3610;
import net.minecraft.class_3611;
import net.minecraft.class_4538;
import org.jetbrains.annotations.Nullable;

public interface ArchitecturyFluidAttributes {
   @Nullable
   String getTranslationKey(@Nullable FluidStack var1);

   @Nullable
   default String getTranslationKey() {
      return this.getTranslationKey(null);
   }

   default class_2561 getName(@Nullable FluidStack stack) {
      return class_2561.method_43471(this.getTranslationKey(stack));
   }

   default class_2561 getName() {
      return this.getName(null);
   }

   class_3611 getFlowingFluid();

   class_3611 getSourceFluid();

   boolean canConvertToSource();

   int getSlopeFindDistance(@Nullable class_4538 var1);

   default int getSlopeFindDistance() {
      return this.getSlopeFindDistance(null);
   }

   int getDropOff(@Nullable class_4538 var1);

   default int getDropOff() {
      return this.getDropOff(null);
   }

   @Nullable
   class_1792 getBucketItem();

   int getTickDelay(@Nullable class_4538 var1);

   default int getTickDelay() {
      return this.getTickDelay(null);
   }

   float getExplosionResistance();

   @Nullable
   class_2404 getBlock();

   @Deprecated(forRemoval = true)
   class_2960 getSourceTexture(@Nullable FluidStack var1, @Nullable class_1920 var2, @Nullable class_2338 var3);

   default class_2960 getSourceTexture(@Nullable class_3610 state, @Nullable class_1920 level, @Nullable class_2338 pos) {
      return this.getSourceTexture(state == null ? null : FluidStack.create(state.method_15772(), FluidStack.bucketAmount()), level, pos);
   }

   default class_2960 getSourceTexture(@Nullable FluidStack stack) {
      return this.getSourceTexture(stack, null, null);
   }

   default class_2960 getSourceTexture() {
      return this.getSourceTexture(null);
   }

   @Deprecated(forRemoval = true)
   class_2960 getFlowingTexture(@Nullable FluidStack var1, @Nullable class_1920 var2, @Nullable class_2338 var3);

   default class_2960 getFlowingTexture(@Nullable class_3610 state, @Nullable class_1920 level, @Nullable class_2338 pos) {
      return this.getFlowingTexture(state == null ? null : FluidStack.create(state.method_15772(), FluidStack.bucketAmount()), level, pos);
   }

   default class_2960 getFlowingTexture(@Nullable FluidStack stack) {
      return this.getFlowingTexture(stack, null, null);
   }

   default class_2960 getFlowingTexture() {
      return this.getFlowingTexture(null);
   }

   @Nullable
   default class_2960 getOverlayTexture(@Nullable class_3610 state, @Nullable class_1920 level, @Nullable class_2338 pos) {
      return null;
   }

   @Nullable
   default class_2960 getOverlayTexture(@Nullable FluidStack stack) {
      return null;
   }

   @Nullable
   default class_2960 getOverlayTexture() {
      return this.getOverlayTexture(null);
   }

   @Deprecated(forRemoval = true)
   int getColor(@Nullable FluidStack var1, @Nullable class_1920 var2, @Nullable class_2338 var3);

   default int getColor(@Nullable class_3610 state, @Nullable class_1920 level, @Nullable class_2338 pos) {
      return this.getColor(state == null ? null : FluidStack.create(state.method_15772(), FluidStack.bucketAmount()), level, pos);
   }

   default int getColor(@Nullable FluidStack stack) {
      return this.getColor(stack, null, null);
   }

   default int getColor() {
      return this.getColor(null);
   }

   int getLuminosity(@Nullable FluidStack var1, @Nullable class_1920 var2, @Nullable class_2338 var3);

   default int getLuminosity(@Nullable FluidStack stack) {
      return this.getLuminosity(stack, null, null);
   }

   default int getLuminosity() {
      return this.getLuminosity(null);
   }

   int getDensity(@Nullable FluidStack var1, @Nullable class_1920 var2, @Nullable class_2338 var3);

   default int getDensity(@Nullable FluidStack stack) {
      return this.getDensity(stack, null, null);
   }

   default int getDensity() {
      return this.getDensity(null);
   }

   int getTemperature(@Nullable FluidStack var1, @Nullable class_1920 var2, @Nullable class_2338 var3);

   default int getTemperature(@Nullable FluidStack stack) {
      return this.getTemperature(stack, null, null);
   }

   default int getTemperature() {
      return this.getTemperature(null);
   }

   int getViscosity(@Nullable FluidStack var1, @Nullable class_1920 var2, @Nullable class_2338 var3);

   default int getViscosity(@Nullable FluidStack stack) {
      return this.getViscosity(stack, null, null);
   }

   default int getViscosity() {
      return this.getViscosity(null);
   }

   boolean isLighterThanAir(@Nullable FluidStack var1, @Nullable class_1920 var2, @Nullable class_2338 var3);

   default boolean isLighterThanAir(@Nullable FluidStack stack) {
      return this.isLighterThanAir(stack, null, null);
   }

   default boolean isLighterThanAir() {
      return this.isLighterThanAir(null);
   }

   class_1814 getRarity(@Nullable FluidStack var1, @Nullable class_1920 var2, @Nullable class_2338 var3);

   default class_1814 getRarity(@Nullable FluidStack stack) {
      return this.getRarity(stack, null, null);
   }

   default class_1814 getRarity() {
      return this.getRarity(null);
   }

   @Nullable
   class_3414 getFillSound(@Nullable FluidStack var1, @Nullable class_1920 var2, @Nullable class_2338 var3);

   @Nullable
   default class_3414 getFillSound(@Nullable FluidStack stack) {
      return this.getFillSound(stack, null, null);
   }

   @Nullable
   default class_3414 getFillSound() {
      return this.getFillSound(null);
   }

   @Nullable
   class_3414 getEmptySound(@Nullable FluidStack var1, @Nullable class_1920 var2, @Nullable class_2338 var3);

   @Nullable
   default class_3414 getEmptySound(@Nullable FluidStack stack) {
      return this.getEmptySound(stack, null, null);
   }

   @Nullable
   default class_3414 getEmptySound() {
      return this.getEmptySound(null);
   }
}
