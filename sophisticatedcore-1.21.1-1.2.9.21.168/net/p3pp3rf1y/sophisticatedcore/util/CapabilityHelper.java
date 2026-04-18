package net.p3pp3rf1y.sophisticatedcore.util;

import io.github.fabricators_of_create.porting_lib.transfer.MutableContainerItemContext;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.annotation.Nullable;
import net.fabricmc.fabric.api.lookup.v1.block.BlockApiLookup;
import net.fabricmc.fabric.api.lookup.v1.entity.EntityApiLookup;
import net.fabricmc.fabric.api.lookup.v1.item.ItemApiLookup;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.p3pp3rf1y.sophisticatedcore.inventory.IInventoryHandlerHelper;

public class CapabilityHelper {
   public static void runOnItemHandler(class_1297 entity, Consumer<IInventoryHandlerHelper> run) {
      runOnCapability(entity, Capabilities.ItemHandler.ENTITY, null, run);
   }

   public static <T> T getFromItemHandler(class_1937 level, class_2338 pos, @Nullable class_2350 context, Function<Storage<ItemVariant>, T> get, T defaultValue) {
      return getFromCapability(level, pos, ItemStorage.SIDED, context, get, defaultValue);
   }

   public static <T> T getFromItemHandler(class_1937 level, class_2338 pos, Function<Storage<ItemVariant>, T> get, T defaultValue) {
      return getFromItemHandler(level, pos, null, get, defaultValue);
   }

   public static <T, C> void runOnCapability(class_1297 entity, EntityApiLookup<T, C> capability, @Nullable C context, Consumer<T> run) {
      runOnCapability(run, (T)capability.find(entity, context));
   }

   public static <T, C> void runOnCapability(class_1799 stack, ItemApiLookup<T, C> capability, @Nullable C context, Consumer<T> run) {
      runOnCapability(run, (T)capability.find(stack, context));
   }

   public static <T, C> void runOnCapability(class_1799 stack, ItemApiLookup<T, C> capability, @Nullable C context, BiConsumer<C, T> run) {
      runOnCapability(run, context, (T)capability.find(stack, context));
   }

   private static <T> void runOnCapability(Consumer<T> run, @Nullable T t) {
      if (t != null) {
         run.accept(t);
      }
   }

   private static <T, C> void runOnCapability(BiConsumer<C, T> run, @Nullable C context, @Nullable T t) {
      if (t != null) {
         run.accept(context, t);
      }
   }

   public static <U> U getFromCapability(
      ItemApiLookup<Storage<FluidVariant>, ContainerItemContext> capability,
      ContainerItemContext context,
      Function<Storage<FluidVariant>, U> get,
      U defaultValue
   ) {
      Storage<FluidVariant> fluidHandler = (Storage<FluidVariant>)context.find(capability);
      return fluidHandler == null ? defaultValue : get.apply(fluidHandler);
   }

   public static <T, C, U> U getFromCapability(
      class_1937 level, class_2338 pos, BlockApiLookup<T, C> capability, @Nullable C context, Function<T, U> get, U defaultValue
   ) {
      return getFromCapability(level, pos, null, null, capability, context, get, defaultValue);
   }

   public static <T, C, U> U getFromCapability(class_2586 blockEntity, BlockApiLookup<T, C> capability, @Nullable C context, Function<T, U> get, U defaultValue) {
      return blockEntity.method_10997() == null
         ? defaultValue
         : getFromCapability(
            blockEntity.method_10997(), blockEntity.method_11016(), blockEntity.method_11010(), blockEntity, capability, context, get, defaultValue
         );
   }

   public static <T, C, U> U getFromCapability(
      class_1937 level,
      class_2338 pos,
      @Nullable class_2680 state,
      @Nullable class_2586 blockEntity,
      BlockApiLookup<T, C> capability,
      @Nullable C context,
      Function<T, U> get,
      U defaultValue
   ) {
      T t = (T)capability.find(level, pos, context);
      return t == null ? defaultValue : get.apply(t);
   }

   public static <T> T getFromFluidHandler(class_2586 be, class_2350 side, Function<Storage<FluidVariant>, T> get, T defaultValue) {
      return getFromCapability(be, FluidStorage.SIDED, side, get, defaultValue);
   }

   public static <T> T getFromFluidHandler(class_1799 stack, Function<Storage<FluidVariant>, T> get, T defaultValue) {
      return getFromCapability(FluidStorage.ITEM, ContainerItemContext.withConstant(stack), get, defaultValue);
   }

   public static <T> T getFromFluidHandler(class_1657 player, class_1268 hand, Function<Storage<FluidVariant>, T> get, T defaultValue) {
      return getFromCapability(FluidStorage.ITEM, ContainerItemContext.forPlayerInteraction(player, hand), get, defaultValue);
   }

   public static void runOnFluidHandler(class_1799 stack, BiConsumer<ContainerItemContext, Storage<FluidVariant>> run) {
      runOnCapability(stack, FluidStorage.ITEM, new MutableContainerItemContext(stack), run);
   }
}
