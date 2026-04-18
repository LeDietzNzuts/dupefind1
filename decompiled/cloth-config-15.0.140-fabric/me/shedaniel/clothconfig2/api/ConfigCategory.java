package me.shedaniel.clothconfig2.api;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_5348;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public interface ConfigCategory {
   class_2561 getCategoryKey();

   @Deprecated
   List<Object> getEntries();

   ConfigCategory addEntry(AbstractConfigListEntry var1);

   ConfigCategory setCategoryBackground(class_2960 var1);

   void setBackground(@Nullable class_2960 var1);

   @Nullable
   class_2960 getBackground();

   @Nullable
   Supplier<Optional<class_5348[]>> getDescription();

   void setDescription(@Nullable Supplier<Optional<class_5348[]>> var1);

   default void setDescription(@Nullable class_5348[] description) {
      this.setDescription(() -> Optional.ofNullable(description));
   }

   void removeCategory();
}
