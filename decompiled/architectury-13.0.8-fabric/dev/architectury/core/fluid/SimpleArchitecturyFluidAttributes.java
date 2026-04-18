package dev.architectury.core.fluid;

import com.google.common.base.Suppliers;
import dev.architectury.fluid.FluidStack;
import dev.architectury.registry.registries.RegistrySupplier;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import net.minecraft.class_156;
import net.minecraft.class_1792;
import net.minecraft.class_1814;
import net.minecraft.class_1920;
import net.minecraft.class_2338;
import net.minecraft.class_2404;
import net.minecraft.class_2960;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_3610;
import net.minecraft.class_3611;
import net.minecraft.class_4538;
import org.jetbrains.annotations.Nullable;

public class SimpleArchitecturyFluidAttributes implements ArchitecturyFluidAttributes {
   private final Supplier<? extends class_3611> flowingFluid;
   private final Supplier<? extends class_3611> sourceFluid;
   private boolean canConvertToSource = false;
   private int slopeFindDistance = 4;
   private int dropOff = 1;
   private Supplier<? extends Optional<class_1792>> bucketItem = Optional::empty;
   private int tickDelay = 5;
   private float explosionResistance = 100.0F;
   private Supplier<? extends Optional<? extends class_2404>> block = Optional::empty;
   @Nullable
   private class_2960 sourceTexture;
   @Nullable
   private class_2960 flowingTexture;
   @Nullable
   private class_2960 overlayTexture;
   private int color = 16777215;
   private int luminosity = 0;
   private int density = 1000;
   private int temperature = 300;
   private int viscosity = 1000;
   private boolean lighterThanAir = false;
   private class_1814 rarity = class_1814.field_8906;
   @Nullable
   private class_3414 fillSound = class_3417.field_15126;
   @Nullable
   private class_3414 emptySound = class_3417.field_14834;
   private final Supplier<String> defaultTranslationKey = Suppliers.memoize(() -> class_156.method_646("fluid", this.getSourceFluid().arch$registryName()));

   public static SimpleArchitecturyFluidAttributes ofSupplier(
      Supplier<? extends Supplier<? extends class_3611>> flowingFluid, Supplier<? extends Supplier<? extends class_3611>> sourceFluid
   ) {
      return of(() -> flowingFluid.get().get(), () -> sourceFluid.get().get());
   }

   public static SimpleArchitecturyFluidAttributes of(Supplier<? extends class_3611> flowingFluid, Supplier<? extends class_3611> sourceFluid) {
      return new SimpleArchitecturyFluidAttributes(flowingFluid, sourceFluid);
   }

   protected SimpleArchitecturyFluidAttributes(Supplier<? extends class_3611> flowingFluid, Supplier<? extends class_3611> sourceFluid) {
      this.flowingFluid = flowingFluid;
      this.sourceFluid = sourceFluid;
   }

   public SimpleArchitecturyFluidAttributes convertToSource(boolean canConvertToSource) {
      this.canConvertToSource = canConvertToSource;
      return this;
   }

   public SimpleArchitecturyFluidAttributes slopeFindDistance(int slopeFindDistance) {
      this.slopeFindDistance = slopeFindDistance;
      return this;
   }

   public SimpleArchitecturyFluidAttributes dropOff(int dropOff) {
      this.dropOff = dropOff;
      return this;
   }

   public SimpleArchitecturyFluidAttributes bucketItemSupplier(Supplier<RegistrySupplier<class_1792>> bucketItem) {
      return this.bucketItem(() -> bucketItem.get().toOptional());
   }

   public SimpleArchitecturyFluidAttributes bucketItem(RegistrySupplier<class_1792> bucketItem) {
      return this.bucketItem(bucketItem::toOptional);
   }

   public SimpleArchitecturyFluidAttributes bucketItem(Supplier<? extends Optional<class_1792>> bucketItem) {
      this.bucketItem = Objects.requireNonNull(bucketItem);
      return this;
   }

   public SimpleArchitecturyFluidAttributes tickDelay(int tickDelay) {
      this.tickDelay = tickDelay;
      return this;
   }

   public SimpleArchitecturyFluidAttributes explosionResistance(float explosionResistance) {
      this.explosionResistance = explosionResistance;
      return this;
   }

   public SimpleArchitecturyFluidAttributes blockSupplier(Supplier<RegistrySupplier<? extends class_2404>> block) {
      return this.block(() -> block.get().toOptional());
   }

   public SimpleArchitecturyFluidAttributes block(RegistrySupplier<? extends class_2404> block) {
      return this.block(block::toOptional);
   }

   public SimpleArchitecturyFluidAttributes block(Supplier<? extends Optional<? extends class_2404>> block) {
      this.block = Objects.requireNonNull(block);
      return this;
   }

   public SimpleArchitecturyFluidAttributes sourceTexture(class_2960 sourceTexture) {
      this.sourceTexture = sourceTexture;
      return this;
   }

   public SimpleArchitecturyFluidAttributes flowingTexture(class_2960 flowingTexture) {
      this.flowingTexture = flowingTexture;
      return this;
   }

   public SimpleArchitecturyFluidAttributes overlayTexture(class_2960 overlayTexture) {
      this.overlayTexture = overlayTexture;
      return this;
   }

   public SimpleArchitecturyFluidAttributes color(int color) {
      this.color = color;
      return this;
   }

   public SimpleArchitecturyFluidAttributes luminosity(int luminosity) {
      this.luminosity = luminosity;
      return this;
   }

   public SimpleArchitecturyFluidAttributes density(int density) {
      this.density = density;
      return this;
   }

   public SimpleArchitecturyFluidAttributes temperature(int temperature) {
      this.temperature = temperature;
      return this;
   }

   public SimpleArchitecturyFluidAttributes viscosity(int viscosity) {
      this.viscosity = viscosity;
      return this;
   }

   public SimpleArchitecturyFluidAttributes lighterThanAir(boolean lighterThanAir) {
      this.lighterThanAir = lighterThanAir;
      return this;
   }

   public SimpleArchitecturyFluidAttributes rarity(class_1814 rarity) {
      this.rarity = rarity;
      return this;
   }

   public SimpleArchitecturyFluidAttributes fillSound(class_3414 fillSound) {
      this.fillSound = fillSound;
      return this;
   }

   public SimpleArchitecturyFluidAttributes emptySound(class_3414 emptySound) {
      this.emptySound = emptySound;
      return this;
   }

   @Nullable
   @Override
   public String getTranslationKey(@Nullable FluidStack stack) {
      return this.defaultTranslationKey.get();
   }

   @Override
   public final class_3611 getFlowingFluid() {
      return this.flowingFluid.get();
   }

   @Override
   public final class_3611 getSourceFluid() {
      return this.sourceFluid.get();
   }

   @Override
   public boolean canConvertToSource() {
      return this.canConvertToSource;
   }

   @Override
   public int getSlopeFindDistance(@Nullable class_4538 level) {
      return this.slopeFindDistance;
   }

   @Override
   public int getDropOff(@Nullable class_4538 level) {
      return this.dropOff;
   }

   @Nullable
   @Override
   public class_1792 getBucketItem() {
      return this.bucketItem.get().orElse(null);
   }

   @Override
   public int getTickDelay(@Nullable class_4538 level) {
      return this.tickDelay;
   }

   @Override
   public float getExplosionResistance() {
      return this.explosionResistance;
   }

   @Nullable
   @Override
   public class_2404 getBlock() {
      return this.block.get().orElse(null);
   }

   @Override
   public class_2960 getSourceTexture(@Nullable FluidStack stack, @Nullable class_1920 level, @Nullable class_2338 pos) {
      return this.sourceTexture;
   }

   @Override
   public class_2960 getFlowingTexture(@Nullable FluidStack stack, @Nullable class_1920 level, @Nullable class_2338 pos) {
      return this.flowingTexture;
   }

   @Override
   public class_2960 getOverlayTexture(@Nullable class_3610 state, @Nullable class_1920 level, @Nullable class_2338 pos) {
      return this.overlayTexture;
   }

   @Override
   public int getColor(@Nullable FluidStack stack, @Nullable class_1920 level, @Nullable class_2338 pos) {
      return this.color;
   }

   @Override
   public int getLuminosity(@Nullable FluidStack stack, @Nullable class_1920 level, @Nullable class_2338 pos) {
      return this.luminosity;
   }

   @Override
   public int getDensity(@Nullable FluidStack stack, @Nullable class_1920 level, @Nullable class_2338 pos) {
      return this.density;
   }

   @Override
   public int getTemperature(@Nullable FluidStack stack, @Nullable class_1920 level, @Nullable class_2338 pos) {
      return this.temperature;
   }

   @Override
   public int getViscosity(@Nullable FluidStack stack, @Nullable class_1920 level, @Nullable class_2338 pos) {
      return this.viscosity;
   }

   @Override
   public boolean isLighterThanAir(@Nullable FluidStack stack, @Nullable class_1920 level, @Nullable class_2338 pos) {
      return this.lighterThanAir;
   }

   @Override
   public class_1814 getRarity(@Nullable FluidStack stack, @Nullable class_1920 level, @Nullable class_2338 pos) {
      return this.rarity;
   }

   @Nullable
   @Override
   public class_3414 getFillSound(@Nullable FluidStack stack, @Nullable class_1920 level, @Nullable class_2338 pos) {
      return this.fillSound;
   }

   @Nullable
   @Override
   public class_3414 getEmptySound(@Nullable FluidStack stack, @Nullable class_1920 level, @Nullable class_2338 pos) {
      return this.emptySound;
   }
}
