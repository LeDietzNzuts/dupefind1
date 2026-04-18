package noobanidus.mods.lootr.common.advancement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Optional;
import net.minecraft.class_175;
import net.minecraft.class_3222;
import net.minecraft.class_4558;
import net.minecraft.class_5258;
import net.minecraft.class_2096.class_2100;
import net.minecraft.class_4558.class_8788;
import noobanidus.mods.lootr.common.api.advancement.ILootedStatTrigger;
import noobanidus.mods.lootr.common.api.registry.LootrRegistry;

public class LootedStatTrigger extends class_4558<LootedStatTrigger.TriggerInstance> implements ILootedStatTrigger {
   @Override
   public void trigger(class_3222 player) {
      this.method_22510(player, instance -> instance.test(player));
   }

   public Codec<LootedStatTrigger.TriggerInstance> method_54937() {
      return LootedStatTrigger.TriggerInstance.CODEC;
   }

   public static class_175<LootedStatTrigger.TriggerInstance> looted(int count) {
      return ((LootedStatTrigger)LootrRegistry.getStatTrigger().getTrigger())
         .method_53699(new LootedStatTrigger.TriggerInstance(Optional.empty(), class_2100.method_9058(count)));
   }

   public record TriggerInstance(Optional<class_5258> player, class_2100 score) implements class_8788 {
      public static final Codec<LootedStatTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
         codec -> codec.group(
               class_5258.field_47234.optionalFieldOf("player").forGetter(LootedStatTrigger.TriggerInstance::comp_2029),
               class_2100.field_45763.optionalFieldOf("score", class_2100.field_9708).forGetter(LootedStatTrigger.TriggerInstance::score)
            )
            .apply(codec, LootedStatTrigger.TriggerInstance::new)
      );

      public boolean test(class_3222 player) {
         return this.score.method_9054(player.method_14248().method_15025(LootrRegistry.getLootedStat()));
      }

      public Optional<class_5258> comp_2029() {
         return this.player;
      }
   }
}
