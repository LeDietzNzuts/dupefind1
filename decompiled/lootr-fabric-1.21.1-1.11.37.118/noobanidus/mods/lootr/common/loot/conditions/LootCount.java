package noobanidus.mods.lootr.common.loot.conditions;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.PrimitiveCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import net.minecraft.class_169;
import net.minecraft.class_181;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_2586;
import net.minecraft.class_3542;
import net.minecraft.class_47;
import net.minecraft.class_5341;
import net.minecraft.class_5342;
import net.minecraft.class_3542.class_7292;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.common.api.registry.LootrRegistry;

public record LootCount(List<LootCount.Operation> operations) implements class_5341 {
   public static final MapCodec<LootCount> CODEC = RecordCodecBuilder.mapCodec(
      builder -> builder.group(
            LootCount.Operation.CODEC
               .listOf()
               .xmap(operationList -> operationList.stream().sorted(Comparator.comparingInt(LootCount.Operation::getPrecedence)).toList(), Function.identity())
               .fieldOf("operations")
               .forGetter(LootCount::operations)
         )
         .apply(builder, LootCount::new)
   );

   public class_5342 method_29325() {
      return LootrRegistry.getLootCount();
   }

   public boolean test(class_47 lootContext) {
      class_243 incomingPos = (class_243)lootContext.method_35508(class_181.field_24424);
      class_2338 position = new class_2338((int)incomingPos.field_1352, (int)incomingPos.field_1351, (int)incomingPos.field_1350);
      class_2586 blockEntity = lootContext.method_299().method_8321(position);
      ILootrBlockEntity ibe = LootrAPI.resolveBlockEntity(blockEntity);
      if (ibe != null && ibe.hasLootTable()) {
         Set<UUID> actualOpeners = ibe.getActualOpeners();
         if (actualOpeners == null) {
            return false;
         } else {
            int count = ibe.getActualOpeners().size() + 1;

            for (LootCount.Operation op : this.operations) {
               if (!op.test(count)) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }

   public Set<class_169<?>> method_293() {
      return ImmutableSet.of(class_181.field_24424);
   }

   public static enum Operand implements BiPredicate<Integer, Integer>, class_3542 {
      EQUALS(Integer::equals, 0),
      NOT_EQUALS((a, b) -> !a.equals(b), 0),
      LESS_THAN((a, b) -> a < b, 1),
      GREATER_THAN((a, b) -> a > b, 1),
      LESS_THAN_EQUALS((a, b) -> a <= b, 1),
      GREATER_THAN_EQUALS((a, b) -> a >= b, 1);

      public static final class_7292<LootCount.Operand> CODEC = class_3542.method_28140(LootCount.Operand::values);
      private final BiPredicate<Integer, Integer> predicate;
      private final int precedence;

      private Operand(BiPredicate<Integer, Integer> predicate, int precedence) {
         this.predicate = predicate;
         this.precedence = precedence;
      }

      public boolean test(Integer integer, Integer integer2) {
         return this.predicate.test(integer, integer2);
      }

      public int getPrecedence() {
         return this.precedence;
      }

      public String method_15434() {
         return this.name().toLowerCase(Locale.ROOT);
      }
   }

   public record Operation(LootCount.Operand operand, int value) implements Predicate<Integer> {
      public static final Codec<LootCount.Operation> CODEC = RecordCodecBuilder.create(
         instance -> instance.group(
               LootCount.Operand.CODEC.fieldOf("type").forGetter(LootCount.Operation::operand),
               PrimitiveCodec.INT.fieldOf("value").forGetter(LootCount.Operation::value)
            )
            .apply(instance, LootCount.Operation::new)
      );

      public int getPrecedence() {
         return this.operand.getPrecedence();
      }

      public boolean test(Integer integer) {
         return this.operand.test(integer, this.value);
      }
   }
}
