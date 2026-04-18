package me.shedaniel.clothconfig2.api;

import java.util.Iterator;
import java.util.function.Consumer;
import net.minecraft.class_2960;
import net.minecraft.class_437;
import org.jetbrains.annotations.Nullable;

public interface ConfigScreen {
   void setSavingRunnable(@Nullable Runnable var1);

   void setAfterInitConsumer(@Nullable Consumer<class_437> var1);

   class_2960 getBackgroundLocation();

   boolean isRequiresRestart();

   boolean isEdited();

   void saveAll(boolean var1);

   void addTooltip(Tooltip var1);

   boolean matchesSearch(Iterator<String> var1);
}
