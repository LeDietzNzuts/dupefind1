package dev.architectury.fluid;

import com.mojang.serialization.Codec;
import dev.architectury.fluid.fabric.FluidStackImpl;
import dev.architectury.hooks.fluid.FluidStackHooks;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import net.minecraft.class_2520;
import net.minecraft.class_2561;
import net.minecraft.class_3611;
import net.minecraft.class_3612;
import net.minecraft.class_6880;
import net.minecraft.class_9129;
import net.minecraft.class_9139;
import net.minecraft.class_9322;
import net.minecraft.class_9323;
import net.minecraft.class_9326;
import net.minecraft.class_9331;
import net.minecraft.class_9335;
import net.minecraft.class_7225.class_7874;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.ApiStatus.Internal;

public final class FluidStack implements class_9322 {
   private static final FluidStack.FluidStackAdapter<Object> ADAPTER = adapt(FluidStack::getValue, FluidStack::new);
   private static final FluidStack EMPTY = new FluidStack(() -> class_3612.field_15906, 0L, class_9326.field_49588);
   public static final Codec<FluidStack> CODEC = ADAPTER.codec();
   public static final class_9139<class_9129, FluidStack> STREAM_CODEC = ADAPTER.streamCodec();
   private final Object value;

   private FluidStack(Supplier<class_3611> fluid, long amount, class_9326 patch) {
      this(ADAPTER.create(fluid, amount, patch));
   }

   private FluidStack(Object value) {
      this.value = Objects.requireNonNull(value);
   }

   private Object getValue() {
      return this.value;
   }

   @ExpectPlatform
   @Transformed
   private static FluidStack.FluidStackAdapter<Object> adapt(Function<FluidStack, Object> toValue, Function<Object, FluidStack> fromValue) {
      return FluidStackImpl.adapt(toValue, fromValue);
   }

   public static FluidStack empty() {
      return EMPTY;
   }

   public static FluidStack create(class_3611 fluid, long amount, class_9326 patch) {
      return fluid != class_3612.field_15906 && amount > 0L ? create((Supplier<class_3611>)(() -> fluid), amount, patch) : empty();
   }

   public static FluidStack create(class_3611 fluid, long amount) {
      return create(fluid, amount, class_9326.field_49588);
   }

   public static FluidStack create(Supplier<class_3611> fluid, long amount, class_9326 patch) {
      return amount <= 0L ? empty() : new FluidStack(fluid, amount, patch);
   }

   public static FluidStack create(Supplier<class_3611> fluid, long amount) {
      return create(fluid, amount, class_9326.field_49588);
   }

   public static FluidStack create(class_6880<class_3611> fluid, long amount, class_9326 patch) {
      return create((class_3611)fluid.comp_349(), amount, patch);
   }

   public static FluidStack create(class_6880<class_3611> fluid, long amount) {
      return create((class_3611)fluid.comp_349(), amount, class_9326.field_49588);
   }

   public static FluidStack create(FluidStack stack, long amount) {
      return create(stack.getRawFluidSupplier(), amount, stack.getPatch());
   }

   public static long bucketAmount() {
      return FluidStackHooks.bucketAmount();
   }

   public class_3611 getFluid() {
      return this.isEmpty() ? class_3612.field_15906 : this.getRawFluid();
   }

   @Nullable
   public class_3611 getRawFluid() {
      return ADAPTER.getFluid(this.value);
   }

   public Supplier<class_3611> getRawFluidSupplier() {
      return ADAPTER.getRawFluidSupplier(this.value);
   }

   public boolean isEmpty() {
      return this.getRawFluid() == class_3612.field_15906 || ADAPTER.getAmount(this.value) <= 0L;
   }

   public long getAmount() {
      return this.isEmpty() ? 0L : ADAPTER.getAmount(this.value);
   }

   public void setAmount(long amount) {
      ADAPTER.setAmount(this.value, amount);
   }

   public void grow(long amount) {
      this.setAmount(this.getAmount() + amount);
   }

   public void shrink(long amount) {
      this.setAmount(this.getAmount() - amount);
   }

   public class_9326 getPatch() {
      return ADAPTER.getPatch(this.value);
   }

   public class_9335 getComponents() {
      return ADAPTER.getComponents(this.value);
   }

   public void applyComponents(class_9326 patch) {
      ADAPTER.applyComponents(this.value, patch);
   }

   public void applyComponents(class_9323 patch) {
      ADAPTER.applyComponents(this.value, patch);
   }

   @Nullable
   public <T> T set(class_9331<? super T> type, @Nullable T component) {
      return ADAPTER.set(this.value, type, component);
   }

   @Nullable
   public <T> T remove(class_9331<? extends T> type) {
      return ADAPTER.remove(this.value, type);
   }

   @Nullable
   public <T> T update(class_9331<T> type, T component, UnaryOperator<T> updater) {
      return ADAPTER.update(this.value, type, component, updater);
   }

   @Nullable
   public <T, U> T update(class_9331<T> type, T component, U updateContext, BiFunction<T, U, T> updater) {
      return ADAPTER.update(this.value, type, component, updateContext, updater);
   }

   public class_2561 getName() {
      return FluidStackHooks.getName(this);
   }

   public String getTranslationKey() {
      return FluidStackHooks.getTranslationKey(this);
   }

   public FluidStack copy() {
      return new FluidStack(ADAPTER.copy(this.value));
   }

   @Override
   public int hashCode() {
      return ADAPTER.hashCode(this.value);
   }

   @Override
   public boolean equals(Object o) {
      return !(o instanceof FluidStack) ? false : this.isFluidStackEqual((FluidStack)o);
   }

   public boolean isFluidStackEqual(FluidStack other) {
      return this.getFluid() == other.getFluid() && this.getAmount() == other.getAmount() && this.isComponentEqual(other);
   }

   public boolean isFluidEqual(FluidStack other) {
      return this.getFluid() == other.getFluid();
   }

   public boolean isComponentEqual(FluidStack other) {
      class_9326 patch = this.getPatch();
      class_9326 otherPatch = other.getPatch();
      return Objects.equals(patch, otherPatch);
   }

   public static FluidStack read(class_9129 buf) {
      return FluidStackHooks.read(buf);
   }

   public static Optional<FluidStack> read(class_7874 provider, class_2520 tag) {
      return FluidStackHooks.read(provider, tag);
   }

   public void write(class_9129 buf) {
      FluidStackHooks.write(this, buf);
   }

   public class_2520 write(class_7874 provider, class_2520 tag) {
      return FluidStackHooks.write(provider, this, tag);
   }

   public FluidStack copyWithAmount(long amount) {
      return this.isEmpty() ? this : new FluidStack(this.getRawFluidSupplier(), amount, this.getPatch());
   }

   @Internal
   public static void init() {
   }

   @Internal
   public interface FluidStackAdapter<T> {
      T create(Supplier<class_3611> var1, long var2, @Nullable class_9326 var4);

      Supplier<class_3611> getRawFluidSupplier(T var1);

      class_3611 getFluid(T var1);

      long getAmount(T var1);

      void setAmount(T var1, long var2);

      class_9326 getPatch(T var1);

      class_9335 getComponents(T var1);

      void applyComponents(T var1, class_9326 var2);

      void applyComponents(T var1, class_9323 var2);

      @Nullable
      <D> D set(T var1, class_9331<? super D> var2, @Nullable D var3);

      @Nullable
      <D> D remove(T var1, class_9331<? extends D> var2);

      @Nullable
      <D> D update(T var1, class_9331<D> var2, D var3, UnaryOperator<D> var4);

      @Nullable
      <D, U> D update(T var1, class_9331<D> var2, D var3, U var4, BiFunction<D, U, D> var5);

      T copy(T var1);

      int hashCode(T var1);

      Codec<FluidStack> codec();

      class_9139<class_9129, FluidStack> streamCodec();
   }
}
