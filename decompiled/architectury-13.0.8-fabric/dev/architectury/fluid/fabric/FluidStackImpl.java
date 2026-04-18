package dev.architectury.fluid.fabric;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.architectury.fluid.FluidStack;
import io.netty.buffer.ByteBuf;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.minecraft.class_3609;
import net.minecraft.class_3611;
import net.minecraft.class_3612;
import net.minecraft.class_7923;
import net.minecraft.class_7924;
import net.minecraft.class_9129;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import net.minecraft.class_9323;
import net.minecraft.class_9326;
import net.minecraft.class_9331;
import net.minecraft.class_9335;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Internal;

@Internal
public enum FluidStackImpl implements FluidStack.FluidStackAdapter<FluidStackImpl.Pair> {
   INSTANCE;

   public static Function<FluidStack, Object> toValue;
   public static Function<Object, FluidStack> fromValue;

   public static FluidStack.FluidStackAdapter<Object> adapt(Function<FluidStack, Object> toValue, Function<Object, FluidStack> fromValue) {
      FluidStackImpl.toValue = toValue;
      FluidStackImpl.fromValue = fromValue;
      return INSTANCE;
   }

   public FluidStackImpl.Pair create(Supplier<class_3611> fluid, long amount, @Nullable class_9326 patch) {
      class_3611 fluidType = Objects.requireNonNull(fluid).get();
      if (fluidType instanceof class_3609 flowingFluid) {
         fluidType = flowingFluid.method_15751();
      }

      return new FluidStackImpl.Pair(fluidType, patch, amount);
   }

   public Supplier<class_3611> getRawFluidSupplier(FluidStackImpl.Pair object) {
      return () -> object.fluid;
   }

   public class_3611 getFluid(FluidStackImpl.Pair object) {
      return object.fluid;
   }

   public long getAmount(FluidStackImpl.Pair object) {
      return object.amount;
   }

   public void setAmount(FluidStackImpl.Pair object, long amount) {
      object.amount = amount;
   }

   public class_9326 getPatch(FluidStackImpl.Pair value) {
      return value.getPatch();
   }

   public class_9335 getComponents(FluidStackImpl.Pair value) {
      return value.components;
   }

   public void applyComponents(FluidStackImpl.Pair value, class_9326 patch) {
      value.components.method_57936(patch);
   }

   public void applyComponents(FluidStackImpl.Pair value, class_9323 patch) {
      value.components.method_57933(patch);
   }

   @Nullable
   public <D> D set(FluidStackImpl.Pair value, class_9331<? super D> type, @Nullable D component) {
      return (D)value.components.method_57938(type, component);
   }

   @Nullable
   public <D> D remove(FluidStackImpl.Pair value, class_9331<? extends D> type) {
      return (D)value.components.method_57939(type);
   }

   @Nullable
   public <D> D update(FluidStackImpl.Pair value, class_9331<D> type, D component, UnaryOperator<D> updater) {
      return (D)value.components.method_57938(type, updater.apply((D)this.getComponents(value).method_57830(type, component)));
   }

   @Nullable
   public <D, U> D update(FluidStackImpl.Pair value, class_9331<D> type, D component, U updateContext, BiFunction<D, U, D> updater) {
      return (D)value.components.method_57938(type, updater.apply((D)this.getComponents(value).method_57830(type, component), updateContext));
   }

   public FluidStackImpl.Pair copy(FluidStackImpl.Pair value) {
      return new FluidStackImpl.Pair(value.fluid, value.components.method_57941(), value.amount);
   }

   public int hashCode(FluidStackImpl.Pair value) {
      int code = 1;
      code = 31 * code + value.fluid.hashCode();
      code = 31 * code + Long.hashCode(value.amount);
      return 31 * code + value.components.hashCode();
   }

   @Override
   public Codec<FluidStack> codec() {
      return RecordCodecBuilder.create(
         instance -> instance.group(
               class_7923.field_41173.method_40294().fieldOf("fluid").forGetter(stack -> stack.getFluid().method_40178()),
               Codec.LONG
                  .validate(
                     value -> value.compareTo(0L) >= 0 && value.compareTo(Long.MAX_VALUE) <= 0
                        ? DataResult.success(value)
                        : DataResult.error(() -> "Value must be non-negative: " + value)
                  )
                  .fieldOf("amount")
                  .forGetter(FluidStack::getAmount),
               class_9326.field_49589.optionalFieldOf("components", class_9326.field_49588).forGetter(FluidStack::getPatch)
            )
            .apply(instance, FluidStack::create)
      );
   }

   @Override
   public class_9139<class_9129, FluidStack> streamCodec() {
      return class_9139.method_56436(
         class_9135.method_56383(class_7924.field_41270),
         stack -> stack.getFluid().method_40178(),
         class_9139.method_56437(ByteBuf::writeLong, ByteBuf::readLong),
         FluidStack::getAmount,
         class_9326.field_49590,
         FluidStack::getPatch,
         FluidStack::create
      );
   }

   static {
      FluidStack.init();
   }

   public static class Pair {
      public class_3611 fluid;
      public class_9335 components;
      public long amount;

      public Pair(class_3611 fluid, @Nullable class_9326 patch, long amount) {
         this(fluid, patch == null ? new class_9335(class_9323.field_49584) : class_9335.method_57935(class_9323.field_49584, patch), amount);
      }

      public Pair(class_3611 fluid, class_9335 components, long amount) {
         this.fluid = fluid;
         this.components = components;
         this.amount = amount;
      }

      public FluidVariant toVariant() {
         return FluidVariant.of(this.fluid, this.getPatch());
      }

      public class_9326 getPatch() {
         return this.amount > 0L && this.fluid != class_3612.field_15906 ? class_9326.field_49588 : this.components.method_57940();
      }
   }
}
