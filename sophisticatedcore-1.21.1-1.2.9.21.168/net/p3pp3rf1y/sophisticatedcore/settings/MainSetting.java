package net.p3pp3rf1y.sophisticatedcore.settings;

import java.util.Optional;
import java.util.function.BiFunction;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.util.TriConsumer;

public class MainSetting<T> {
   private final String tagName;
   private final BiFunction<class_2487, String, Optional<T>> getValue;
   private final TriConsumer<class_2487, String, T> setValue;
   private final T defaultValue;

   public MainSetting(String tagName, BiFunction<class_2487, String, Optional<T>> getValue, TriConsumer<class_2487, String, T> setValue, T defaultValue) {
      this.tagName = tagName;
      this.getValue = getValue;
      this.setValue = setValue;
      this.defaultValue = defaultValue;
   }

   public String getName() {
      return this.tagName;
   }

   public void setValue(class_2487 tag, T value) {
      this.setValue.accept(tag, this.tagName, value);
   }

   public void removeFrom(class_2487 tag) {
      tag.method_10551(this.tagName);
   }

   public Optional<T> getValue(class_2487 tag) {
      return this.getValue.apply(tag, this.tagName);
   }

   public T getDefaultValue() {
      return this.defaultValue;
   }
}
