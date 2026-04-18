package noobanidus.mods.lootr.common.api.advancement;

import net.minecraft.class_179;
import org.apache.commons.lang3.NotImplementedException;

public interface ITrigger {
   default class_179<?> getTrigger() {
      if (this instanceof class_179<?> trigger) {
         return trigger;
      } else {
         throw new NotImplementedException("This trigger does not have a CriterionTrigger associated with it. That's bad!");
      }
   }
}
