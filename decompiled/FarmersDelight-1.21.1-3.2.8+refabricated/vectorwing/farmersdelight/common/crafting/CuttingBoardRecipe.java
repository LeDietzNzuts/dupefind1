package vectorwing.farmersdelight.common.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import net.minecraft.class_1799;
import net.minecraft.class_1856;
import net.minecraft.class_1860;
import net.minecraft.class_1865;
import net.minecraft.class_1937;
import net.minecraft.class_2371;
import net.minecraft.class_3414;
import net.minecraft.class_3956;
import net.minecraft.class_5321;
import net.minecraft.class_5819;
import net.minecraft.class_7923;
import net.minecraft.class_7924;
import net.minecraft.class_9129;
import net.minecraft.class_9139;
import net.minecraft.class_6880.class_6883;
import net.minecraft.class_7225.class_7874;
import vectorwing.farmersdelight.common.crafting.ingredient.ChanceResult;
import vectorwing.farmersdelight.common.registry.ModRecipeSerializers;
import vectorwing.farmersdelight.common.registry.ModRecipeTypes;

public class CuttingBoardRecipe implements class_1860<CuttingBoardRecipeInput> {
   public static final int MAX_RESULTS = 4;
   private final String group;
   private final class_1856 input;
   private final class_1856 tool;
   private final class_2371<ChanceResult> results;
   private final Optional<class_3414> soundEvent;

   public CuttingBoardRecipe(String group, class_1856 input, class_1856 tool, class_2371<ChanceResult> results, Optional<class_3414> soundEvent) {
      this.group = group;
      this.input = input;
      this.tool = tool;
      this.results = results;
      this.soundEvent = soundEvent;
   }

   public boolean matches(CuttingBoardRecipeInput input, class_1937 level) {
      return this.input.method_8093(input.item()) && this.tool.method_8093(input.tool());
   }

   public class_1799 assemble(CuttingBoardRecipeInput inv, class_7874 provider) {
      return ((ChanceResult)this.results.getFirst()).stack().method_7972();
   }

   public boolean method_8118() {
      return true;
   }

   public String method_8112() {
      return this.group;
   }

   public class_2371<class_1856> method_8117() {
      class_2371<class_1856> nonnulllist = class_2371.method_10211();
      nonnulllist.add(this.input);
      return nonnulllist;
   }

   public class_1856 getTool() {
      return this.tool;
   }

   public class_1799 method_8110(class_7874 provider) {
      return ((ChanceResult)this.results.getFirst()).stack();
   }

   public List<class_1799> getResults() {
      return this.getRollableResults().stream().map(ChanceResult::stack).collect(Collectors.toList());
   }

   public class_2371<ChanceResult> getRollableResults() {
      return this.results;
   }

   public List<class_1799> rollResults(class_5819 rand, int fortuneLevel) {
      List<class_1799> results = new ArrayList<>();

      for (ChanceResult output : this.getRollableResults()) {
         class_1799 stack = output.rollOutput(rand, fortuneLevel);
         if (!stack.method_7960()) {
            results.add(stack);
         }
      }

      return results;
   }

   public Optional<class_3414> getSoundEvent() {
      return this.soundEvent;
   }

   protected int getMaxInputCount() {
      return 1;
   }

   public boolean method_8113(int width, int height) {
      return width * height >= this.getMaxInputCount();
   }

   public class_1865<?> method_8119() {
      return ModRecipeSerializers.CUTTING.get();
   }

   public class_3956<?> method_17716() {
      return ModRecipeTypes.CUTTING.get();
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      } else if (o != null && this.getClass() == o.getClass()) {
         CuttingBoardRecipe that = (CuttingBoardRecipe)o;
         if (!this.method_8112().equals(that.method_8112())) {
            return false;
         } else if (!this.input.equals(that.input)) {
            return false;
         } else if (!this.getTool().equals(that.getTool())) {
            return false;
         } else {
            return !this.getResults().equals(that.getResults()) ? false : Objects.equals(this.soundEvent, that.soundEvent);
         }
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      int result = this.method_8112() != null ? this.method_8112().hashCode() : 0;
      result = 31 * result + this.input.hashCode();
      result = 31 * result + this.getTool().hashCode();
      result = 31 * result + this.getResults().hashCode();
      return 31 * result + this.soundEvent.map(Object::hashCode).orElse(0);
   }

   public static class Serializer implements class_1865<CuttingBoardRecipe> {
      public static final class_9139<class_9129, CuttingBoardRecipe> STREAM_CODEC = class_9139.method_56437(
         CuttingBoardRecipe.Serializer::toNetwork, CuttingBoardRecipe.Serializer::fromNetwork
      );
      private static final MapCodec<CuttingBoardRecipe> CODEC = RecordCodecBuilder.mapCodec(
         inst -> inst.group(
               Codec.STRING.optionalFieldOf("group", "").forGetter(CuttingBoardRecipe::method_8112),
               class_1856.field_46096.listOf().fieldOf("ingredients").flatXmap(ingredients -> {
                  if (ingredients.isEmpty()) {
                     return DataResult.error(() -> "No ingredients for cutting recipe");
                  } else if (ingredients.size() > 1) {
                     return DataResult.error(() -> "Too many ingredients for cutting recipe! Please define only one ingredient");
                  } else {
                     class_2371<class_1856> nonNullList = class_2371.method_10211();
                     nonNullList.add((class_1856)ingredients.get(0));
                     return DataResult.success((class_1856)ingredients.get(0));
                  }
               }, ingredient -> {
                  class_2371<class_1856> nonNullList = class_2371.method_10211();
                  nonNullList.add(ingredient);
                  return DataResult.success(nonNullList);
               }).forGetter(cuttingBoardRecipe -> cuttingBoardRecipe.input),
               class_1856.field_46095.fieldOf("tool").forGetter(CuttingBoardRecipe::getTool),
               Codec.list(ChanceResult.CODEC).fieldOf("result").flatXmap(chanceResults -> {
                  if (chanceResults.size() > 4) {
                     return DataResult.error(() -> "Too many results for cutting recipe! The maximum quantity of unique results is 4");
                  } else {
                     class_2371<ChanceResult> nonNullList = class_2371.method_10211();
                     nonNullList.addAll(chanceResults);
                     return DataResult.success(nonNullList);
                  }
               }, DataResult::success).forGetter(CuttingBoardRecipe::getRollableResults),
               class_3414.field_41698.optionalFieldOf("sound").forGetter(CuttingBoardRecipe::getSoundEvent)
            )
            .apply(inst, CuttingBoardRecipe::new)
      );

      public static CuttingBoardRecipe fromNetwork(class_9129 buffer) {
         String groupIn = buffer.method_10800(32767);
         class_1856 inputItemIn = (class_1856)class_1856.field_48355.decode(buffer);
         class_1856 toolIn = (class_1856)class_1856.field_48355.decode(buffer);
         int i = buffer.method_10816();
         class_2371<ChanceResult> resultsIn = class_2371.method_10213(i, ChanceResult.EMPTY);
         resultsIn.replaceAll(ignored -> ChanceResult.read(buffer));
         Optional<class_3414> soundEventIn = Optional.empty();
         if (buffer.readBoolean()) {
            Optional<class_6883<class_3414>> holder = class_7923.field_41172.method_40264(buffer.method_44112(class_7924.field_41225));
            if (holder.isPresent() && holder.get().method_40227()) {
               soundEventIn = Optional.of((class_3414)holder.get().comp_349());
            }
         }

         return new CuttingBoardRecipe(groupIn, inputItemIn, toolIn, resultsIn, soundEventIn);
      }

      public static void toNetwork(class_9129 buffer, CuttingBoardRecipe recipe) {
         buffer.method_10814(recipe.group);
         class_1856.field_48355.encode(buffer, recipe.input);
         class_1856.field_48355.encode(buffer, recipe.tool);
         buffer.method_10804(recipe.results.size());

         for (ChanceResult result : recipe.results) {
            result.write(buffer);
         }

         if (recipe.getSoundEvent().isPresent()) {
            Optional<class_5321<class_3414>> resourceKey = class_7923.field_41172.method_29113(recipe.getSoundEvent().get());
            resourceKey.ifPresentOrElse(rk -> {
               buffer.method_52964(true);
               buffer.method_44116(rk);
            }, () -> buffer.method_52964(false));
         } else {
            buffer.method_52964(false);
         }
      }

      public MapCodec<CuttingBoardRecipe> method_53736() {
         return CODEC;
      }

      public class_9139<class_9129, CuttingBoardRecipe> method_56104() {
         return STREAM_CODEC;
      }
   }
}
