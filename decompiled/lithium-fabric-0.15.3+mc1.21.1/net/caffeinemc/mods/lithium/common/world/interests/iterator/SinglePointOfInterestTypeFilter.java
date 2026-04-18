package net.caffeinemc.mods.lithium.common.world.interests.iterator;

import java.util.function.Predicate;
import net.minecraft.class_4158;
import net.minecraft.class_6880;

public record SinglePointOfInterestTypeFilter(class_6880<class_4158> type) implements Predicate<class_6880<class_4158>> {
   public boolean test(class_6880<class_4158> other) {
      return this.type == other;
   }

   public class_6880<class_4158> getType() {
      return this.type;
   }
}
