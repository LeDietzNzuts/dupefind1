package vectorwing.farmersdelight.data;

import java.util.concurrent.CompletableFuture;
import net.minecraft.class_7784;
import net.minecraft.class_9636;
import net.minecraft.class_9674;
import net.minecraft.class_7225.class_7874;

public class EnchantmentTags extends class_9674 {
   public EnchantmentTags(class_7784 output, CompletableFuture<class_7874> lookupProvider) {
      super(output, lookupProvider);
   }

   protected void method_10514(class_7874 provider) {
      this.method_10512(class_9636.field_51558).method_46835(ModEnchantments.BACKSTABBING);
   }
}
