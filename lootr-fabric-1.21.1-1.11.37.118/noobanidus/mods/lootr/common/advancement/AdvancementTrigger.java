package noobanidus.mods.lootr.common.advancement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Optional;
import net.minecraft.class_175;
import net.minecraft.class_2960;
import net.minecraft.class_3222;
import net.minecraft.class_4558;
import net.minecraft.class_5258;
import net.minecraft.class_4558.class_8788;
import noobanidus.mods.lootr.common.api.advancement.IAdvancementTrigger;
import noobanidus.mods.lootr.common.api.registry.LootrRegistry;

public class AdvancementTrigger extends class_4558<AdvancementTrigger.TriggerInstance> implements IAdvancementTrigger {
   public Codec<AdvancementTrigger.TriggerInstance> method_54937() {
      return AdvancementTrigger.TriggerInstance.CODEC;
   }

   @Override
   public void trigger(class_3222 player, class_2960 advancementId) {
      this.method_22510(player, instance -> instance.test(advancementId));
   }

   public static class_175<AdvancementTrigger.TriggerInstance> completed(class_2960 advancementId) {
      return ((AdvancementTrigger)LootrRegistry.getAdvancementTrigger())
         .method_53699(new AdvancementTrigger.TriggerInstance(Optional.empty(), Optional.of(advancementId)));
   }

   public record TriggerInstance(Optional<class_5258> player, Optional<class_2960> advancement) implements class_8788 {
      public static final Codec<AdvancementTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
         codec -> codec.group(
               class_5258.field_47234.optionalFieldOf("player").forGetter(AdvancementTrigger.TriggerInstance::comp_2029),
               class_2960.field_25139.optionalFieldOf("advancement").forGetter(AdvancementTrigger.TriggerInstance::advancement)
            )
            .apply(codec, AdvancementTrigger.TriggerInstance::new)
      );

      public boolean test(class_2960 advancementId) {
         return this.advancement.isEmpty() || this.advancement.get().equals(advancementId);
      }

      public Optional<class_5258> comp_2029() {
         return this.player;
      }
   }
}
