package dev.architectury.registry.level.entity.trade;

import java.util.Optional;
import net.minecraft.class_1297;
import net.minecraft.class_1799;
import net.minecraft.class_1914;
import net.minecraft.class_5819;
import net.minecraft.class_9306;
import net.minecraft.class_3853.class_1652;
import org.jetbrains.annotations.Nullable;

public record SimpleTrade(
   class_9306 primaryPrice, Optional<class_9306> secondaryPrice, class_1799 sale, int maxTrades, int experiencePoints, float priceMultiplier
) implements class_1652 {
   @Nullable
   public class_1914 method_7246(class_1297 entity, class_5819 random) {
      return new class_1914(this.primaryPrice, this.secondaryPrice, this.sale, this.maxTrades, this.experiencePoints, this.priceMultiplier);
   }
}
