package net.caffeinemc.mods.sodium.client.gui.options.binding.compat;

import net.caffeinemc.mods.sodium.client.gui.options.binding.OptionBinding;
import net.minecraft.class_315;
import net.minecraft.class_7172;

public class VanillaBooleanOptionBinding implements OptionBinding<class_315, Boolean> {
   private final class_7172<Boolean> option;

   public VanillaBooleanOptionBinding(class_7172<Boolean> option) {
      this.option = option;
   }

   public void setValue(class_315 storage, Boolean value) {
      this.option.method_41748(value);
   }

   public Boolean getValue(class_315 storage) {
      return (Boolean)this.option.method_41753();
   }
}
