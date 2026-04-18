package vectorwing.farmersdelight.common.crafting.ingredient;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import net.fabricmc.fabric.api.recipe.v1.ingredient.CustomIngredient;
import net.fabricmc.fabric.api.recipe.v1.ingredient.CustomIngredientSerializer;
import net.minecraft.class_1799;
import net.minecraft.class_2960;
import net.minecraft.class_6328;
import net.minecraft.class_7923;
import net.minecraft.class_9129;
import net.minecraft.class_9135;
import net.minecraft.class_9139;
import org.jetbrains.annotations.Nullable;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.refabricated.ItemAbility;

@class_6328
public class ItemAbilityIngredient implements CustomIngredient {
   public static final ItemAbilityIngredient.Serializer SERIALIZER = new ItemAbilityIngredient.Serializer();
   public static final class_2960 SERIALIZER_ID = FarmersDelight.res("item_ability");
   protected final ItemAbility itemAbility;
   protected List<class_1799> itemStacks;

   public ItemAbilityIngredient(ItemAbility itemAbility) {
      this.itemAbility = itemAbility;
   }

   public static void touch() {
      CustomIngredientSerializer.register(SERIALIZER);
   }

   protected void dissolve() {
      if (this.itemStacks == null) {
         this.itemStacks = class_7923.field_41178.method_10220().<class_1799>map(class_1799::new).filter(this.itemAbility::canPerformAction).toList();
      }
   }

   public boolean test(@Nullable class_1799 stack) {
      return stack != null && this.itemAbility.canPerformAction(stack);
   }

   public ItemAbility getItemAbility() {
      return this.itemAbility;
   }

   public List<class_1799> getMatchingStacks() {
      this.dissolve();
      return this.itemStacks;
   }

   public boolean requiresTesting() {
      return false;
   }

   public CustomIngredientSerializer<?> getSerializer() {
      return SERIALIZER;
   }

   public static class Serializer implements CustomIngredientSerializer<ItemAbilityIngredient> {
      public static final MapCodec<ItemAbilityIngredient> CODEC = RecordCodecBuilder.mapCodec(
         inst -> inst.group(ItemAbility.CODEC.fieldOf("action").forGetter(ItemAbilityIngredient::getItemAbility)).apply(inst, ItemAbilityIngredient::new)
      );
      public static final class_9139<class_9129, ItemAbilityIngredient> STREAM_CODEC = class_9135.method_56368(ItemAbility.CODEC)
         .method_56432(ItemAbilityIngredient::new, ItemAbilityIngredient::getItemAbility)
         .method_56430();

      public class_2960 getIdentifier() {
         return ItemAbilityIngredient.SERIALIZER_ID;
      }

      public MapCodec<ItemAbilityIngredient> getCodec(boolean allowEmpty) {
         return CODEC;
      }

      public class_9139<class_9129, ItemAbilityIngredient> getPacketCodec() {
         return STREAM_CODEC;
      }
   }
}
