package vectorwing.farmersdelight.common.registry;

import com.mojang.serialization.Codec;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.class_173;
import net.minecraft.class_9135;
import net.minecraft.class_9331;
import net.minecraft.class_9698;
import net.minecraft.class_9723;
import vectorwing.farmersdelight.common.item.component.ItemStackWrapper;
import vectorwing.farmersdelight.refabricated.RegUtils;

public class ModDataComponents {
   public static final Supplier<class_9331<ItemStackWrapper>> MEAL = RegUtils.regComponent(
      "meal", builder -> builder.method_57881(ItemStackWrapper.CODEC).method_57882(ItemStackWrapper.STREAM_CODEC).method_59871()
   );
   public static final Supplier<class_9331<ItemStackWrapper>> CONTAINER = RegUtils.regComponent(
      "container", builder -> builder.method_57881(ItemStackWrapper.CODEC).method_57882(ItemStackWrapper.STREAM_CODEC).method_59871()
   );
   public static final Supplier<class_9331<Integer>> COOKING_TIME_LENGTH = RegUtils.regComponent(
      "cooking_time_length", builder -> builder.method_57881(Codec.INT).method_57882(class_9135.field_49675)
   );
   public static final Supplier<class_9331<ItemStackWrapper>> SKILLET_INGREDIENT = RegUtils.regComponent(
      "skillet_ingredient", builder -> builder.method_57881(ItemStackWrapper.CODEC).method_57882(ItemStackWrapper.STREAM_CODEC).method_59871()
   );
   public static final Supplier<class_9331<List<class_9698<class_9723>>>> BACKSTABBING = RegUtils.regEnchComponent(
      "backstabbing", builder -> builder.method_57881(class_9698.method_60004(class_9723.field_51709, class_173.field_51801).listOf())
   );
   public static final Supplier<class_9331<Long>> SKILLET_FLIP_TIMESTAMP = RegUtils.regComponent(
      "skillet_flip_timestamp", builder -> builder.method_57881(Codec.LONG).method_57882(class_9135.field_48551).method_59871()
   );
   public static final Supplier<class_9331<Boolean>> SKILLET_FLIPPED = RegUtils.regComponent(
      "skillet_flipped", builder -> builder.method_57881(Codec.BOOL).method_57882(class_9135.field_48547).method_59871()
   );

   public static void touch() {
   }
}
