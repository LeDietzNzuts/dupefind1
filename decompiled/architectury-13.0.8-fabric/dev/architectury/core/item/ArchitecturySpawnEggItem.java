package dev.architectury.core.item;

import dev.architectury.registry.registries.RegistrySupplier;
import java.util.Objects;
import net.minecraft.class_1299;
import net.minecraft.class_1308;
import net.minecraft.class_1799;
import net.minecraft.class_1826;
import net.minecraft.class_2315;
import net.minecraft.class_2342;
import net.minecraft.class_2347;
import net.minecraft.class_2350;
import net.minecraft.class_2357;
import net.minecraft.class_3730;
import net.minecraft.class_5712;
import net.minecraft.class_1792.class_1793;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

public class ArchitecturySpawnEggItem extends class_1826 {
   private static final Logger LOGGER = LogManager.getLogger(ArchitecturySpawnEggItem.class);
   private final RegistrySupplier<? extends class_1299<? extends class_1308>> entityType;

   protected static class_2357 createDispenseItemBehavior() {
      return new class_2347() {
         public class_1799 method_10135(class_2342 source, class_1799 stack) {
            class_2350 direction = (class_2350)source.comp_1969().method_11654(class_2315.field_10918);
            class_1299<?> entityType = ((class_1826)stack.method_7909()).method_8015(stack);

            try {
               entityType.method_5894(
                  source.comp_1967(),
                  stack,
                  null,
                  source.comp_1968().method_10093(direction),
                  class_3730.field_16470,
                  direction != class_2350.field_11036,
                  false
               );
            } catch (Exception var6) {
               field_34020.error("Error while dispensing spawn egg from dispenser at {}", source.comp_1968(), var6);
               return class_1799.field_8037;
            }

            stack.method_7934(1);
            source.comp_1967().method_33596(null, class_5712.field_28738, source.comp_1968());
            return stack;
         }
      };
   }

   public ArchitecturySpawnEggItem(
      RegistrySupplier<? extends class_1299<? extends class_1308>> entityType, int backgroundColor, int highlightColor, class_1793 properties
   ) {
      this(entityType, backgroundColor, highlightColor, properties, createDispenseItemBehavior());
   }

   public ArchitecturySpawnEggItem(
      RegistrySupplier<? extends class_1299<? extends class_1308>> entityType,
      int backgroundColor,
      int highlightColor,
      class_1793 properties,
      @Nullable class_2357 dispenseItemBehavior
   ) {
      super(null, backgroundColor, highlightColor, properties);
      this.entityType = Objects.requireNonNull(entityType, "entityType");
      class_1826.field_8914.remove(null);
      entityType.listen(type -> {
         LOGGER.debug("Registering spawn egg {} for {}", this.toString(), Objects.toString(type.arch$registryName()));
         class_1826.field_8914.put(type, this);
         this.field_8917 = type;
         if (dispenseItemBehavior != null) {
            class_2315.method_10009(this, dispenseItemBehavior);
         }
      });
   }

   public class_1299<?> method_8015(class_1799 itemStack) {
      class_1299<?> type = super.method_8015(itemStack);
      return (class_1299<?>)(type == null ? this.entityType.get() : type);
   }
}
