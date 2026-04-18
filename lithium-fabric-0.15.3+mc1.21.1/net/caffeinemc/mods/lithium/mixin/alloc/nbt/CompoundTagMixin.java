package net.caffeinemc.mods.lithium.mixin.alloc.nbt;

import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_2487;
import net.minecraft.class_2520;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_2487.class)
public class CompoundTagMixin {
   @Shadow
   @Final
   private Map<String, class_2520> field_11515;

   @ModifyArg(method = "<init>()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_2487;<init>(Ljava/util/Map;)V"))
   private static Map<String, class_2520> useFasterCollection(Map<String, class_2520> oldMap) {
      return new Object2ObjectOpenHashMap();
   }

   @Redirect(method = "<init>()V", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/Maps;newHashMap()Ljava/util/HashMap;", remap = false))
   private static HashMap<?, ?> removeOldMapAlloc() {
      return null;
   }

   @Overwrite
   public class_2487 method_10553() {
      Object2ObjectOpenHashMap<String, class_2520> map = new Object2ObjectOpenHashMap(Maps.transformValues(this.field_11515, class_2520::method_10707));
      return new class_2487(map);
   }

   @Mixin(targets = "net/minecraft/class_2487$1")
   static class Type {
      @ModifyVariable(
         method = "method_53889(Ljava/io/DataInput;Lnet/minecraft/class_2505;)Lnet/minecraft/class_2487;",
         at = @At(value = "INVOKE_ASSIGN", target = "Lcom/google/common/collect/Maps;newHashMap()Ljava/util/HashMap;", remap = false)
      )
      private static Map<String, class_2520> useFasterCollection(Map<String, class_2520> map) {
         return new Object2ObjectOpenHashMap();
      }

      @Redirect(
         method = "method_53889(Ljava/io/DataInput;Lnet/minecraft/class_2505;)Lnet/minecraft/class_2487;",
         at = @At(value = "INVOKE", target = "Lcom/google/common/collect/Maps;newHashMap()Ljava/util/HashMap;", remap = false)
      )
      private static HashMap<?, ?> removeOldMapAlloc() {
         return null;
      }
   }
}
