package net.caffeinemc.mods.sodium.mixin.core.model.colors;

import it.unimi.dsi.fastutil.objects.Reference2ReferenceMap;
import it.unimi.dsi.fastutil.objects.Reference2ReferenceOpenHashMap;
import net.caffeinemc.mods.sodium.client.model.color.interop.ItemColorsExtension;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_325;
import net.minecraft.class_326;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_325.class)
public class ItemColorsMixin implements ItemColorsExtension {
   @Unique
   private final Reference2ReferenceMap<class_1935, class_326> itemsToColor = new Reference2ReferenceOpenHashMap();

   @Inject(method = "method_1708(Lnet/minecraft/class_326;[Lnet/minecraft/class_1935;)V", at = @At("TAIL"))
   private void preRegisterColor(class_326 provider, class_1935[] items, CallbackInfo ci) {
      for (class_1935 convertible : items) {
         this.itemsToColor.put(convertible.method_8389(), provider);
      }
   }

   @Override
   public class_326 sodium$getColorProvider(class_1799 stack) {
      return (class_326)this.itemsToColor.get(stack.method_7909());
   }
}
