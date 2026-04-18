package noobanidus.mods.lootr.common.advancement;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.class_175;
import net.minecraft.class_3222;
import net.minecraft.class_4558;
import net.minecraft.class_5258;
import net.minecraft.class_4558.class_8788;
import noobanidus.mods.lootr.common.api.advancement.IContainerTrigger;
import noobanidus.mods.lootr.common.api.advancement.ITrigger;

public class ContainerTrigger extends class_4558<ContainerTrigger.TriggerInstance> implements IContainerTrigger {
   @Override
   public void trigger(class_3222 player, UUID condition) {
      this.method_22510(player, ContainerTrigger.TriggerInstance::test);
   }

   public Codec<ContainerTrigger.TriggerInstance> method_54937() {
      return ContainerTrigger.TriggerInstance.CODEC;
   }

   public static class_175<ContainerTrigger.TriggerInstance> looted(ITrigger trigger) {
      return ((ContainerTrigger)trigger.getTrigger()).method_53699(new ContainerTrigger.TriggerInstance(Optional.empty()));
   }

   public record TriggerInstance(Optional<class_5258> player) implements class_8788 {
      public static final Codec<ContainerTrigger.TriggerInstance> CODEC = RecordCodecBuilder.create(
         codec -> codec.group(class_5258.field_47234.optionalFieldOf("player").forGetter(ContainerTrigger.TriggerInstance::comp_2029))
            .apply(codec, ContainerTrigger.TriggerInstance::new)
      );

      public boolean test() {
         return true;
      }

      public Optional<class_5258> comp_2029() {
         return this.player;
      }
   }
}
