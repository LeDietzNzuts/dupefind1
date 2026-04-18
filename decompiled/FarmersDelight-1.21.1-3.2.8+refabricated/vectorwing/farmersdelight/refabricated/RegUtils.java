package vectorwing.farmersdelight.refabricated;

import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.class_1291;
import net.minecraft.class_1299;
import net.minecraft.class_1761;
import net.minecraft.class_179;
import net.minecraft.class_1792;
import net.minecraft.class_1865;
import net.minecraft.class_2248;
import net.minecraft.class_2378;
import net.minecraft.class_2396;
import net.minecraft.class_2591;
import net.minecraft.class_3031;
import net.minecraft.class_3414;
import net.minecraft.class_3917;
import net.minecraft.class_3956;
import net.minecraft.class_5339;
import net.minecraft.class_5342;
import net.minecraft.class_6798;
import net.minecraft.class_7923;
import net.minecraft.class_9331;
import net.minecraft.class_9331.class_9332;
import vectorwing.farmersdelight.FarmersDelight;

public class RegUtils {
   public static <R, T extends R> Supplier<T> register(String name, Supplier<T> supplier, class_2378<R> reg) {
      T object = supplier.get();
      class_2378.method_10230(reg, FarmersDelight.res(name), object);
      return () -> object;
   }

   public static <B extends class_1299<?>> Supplier<B> regEntity(String name, Supplier<B> supplier) {
      return register(name, supplier, class_7923.field_41177);
   }

   public static <B extends class_3917<?>> Supplier<B> regMenu(String name, Supplier<B> supplier) {
      return register(name, supplier, class_7923.field_41187);
   }

   public static <B extends class_6798<?>> Supplier<B> regPlacementMod(String name, Supplier<B> supplier) {
      return register(name, supplier, class_7923.field_41148);
   }

   public static <B extends class_1865<?>> Supplier<B> regRecipeSerializer(String name, Supplier<B> supplier) {
      return register(name, supplier, class_7923.field_41189);
   }

   public static <B extends class_3956<?>> Supplier<B> regRecipe(String name, Supplier<B> supplier) {
      return register(name, supplier, class_7923.field_41188);
   }

   public static <B extends class_2396<?>> Supplier<B> regParticle(String name, Supplier<B> supplier) {
      return register(name, supplier, class_7923.field_41180);
   }

   public static <B extends class_3414> Supplier<B> regSound(String name, Supplier<B> supplier) {
      return register(name, supplier, class_7923.field_41172);
   }

   public static <B extends class_5339<?>> Supplier<B> regLootFunction(String name, Supplier<B> supplier) {
      return register(name, supplier, class_7923.field_41134);
   }

   public static <B extends class_3031<?>> Supplier<B> regFeature(String name, Supplier<B> supplier) {
      return register(name, supplier, class_7923.field_41144);
   }

   public static <B extends class_2591<?>> Supplier<B> regBlockEntity(String name, Supplier<B> supplier) {
      return register(name, supplier, class_7923.field_41181);
   }

   public static <B extends class_1761> Supplier<B> regTab(String name, Supplier<B> supplier) {
      return register(name, supplier, class_7923.field_44687);
   }

   public static <B extends class_9331<?>> Supplier<B> regComponent(String name, Supplier<B> supplier) {
      return register(name, supplier, class_7923.field_49658);
   }

   public static <A> Supplier<class_9331<A>> regComponent(String name, Consumer<class_9332<A>> stuff) {
      class_9332<A> builder = class_9331.method_57873();
      stuff.accept(builder);
      return register(name, builder::method_57880, class_7923.field_49658);
   }

   public static <A> Supplier<class_9331<A>> regEnchComponent(String name, Consumer<class_9332<A>> stuff) {
      class_9332<A> builder = class_9331.method_57873();
      stuff.accept(builder);
      return register(name, builder::method_57880, class_7923.field_51832);
   }

   public static <B extends class_1291> Supplier<B> regEffect(String name, Supplier<B> supplier) {
      return register(name, supplier, class_7923.field_41174);
   }

   public static <B extends class_5339<?>> Supplier<B> regLootFunc(String name, Supplier<B> supplier) {
      return register(name, supplier, class_7923.field_41134);
   }

   public static <B extends class_1792> Supplier<B> regItem(String name, Supplier<B> supplier) {
      return register(name, supplier, class_7923.field_41178);
   }

   public static <B extends class_2248> Supplier<B> regBlock(String name, Supplier<B> supplier) {
      return register(name, supplier, class_7923.field_41175);
   }

   public static <B extends class_179<?>> Supplier<B> regTrigger(String name, Supplier<B> supplier) {
      return register(name, supplier, class_7923.field_47496);
   }

   public static <B extends class_5342> Supplier<B> regLootCond(String name, Supplier<B> supplier) {
      return register(name, supplier, class_7923.field_41135);
   }
}
