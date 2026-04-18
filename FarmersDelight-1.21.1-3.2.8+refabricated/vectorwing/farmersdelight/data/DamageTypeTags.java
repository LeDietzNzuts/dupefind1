package vectorwing.farmersdelight.data;

import java.util.concurrent.CompletableFuture;
import net.minecraft.class_2474;
import net.minecraft.class_7784;
import net.minecraft.class_7924;
import net.minecraft.class_8103;
import net.minecraft.class_8110;
import net.minecraft.class_7225.class_7874;
import vectorwing.farmersdelight.common.registry.ModDamageTypes;

public class DamageTypeTags extends class_2474<class_8110> {
   public DamageTypeTags(class_7784 output, CompletableFuture<class_7874> lookupProvider) {
      super(output, class_7924.field_42534, lookupProvider);
   }

   protected void method_10514(class_7874 provider) {
      this.method_10512(class_8103.field_42246).method_46835(ModDamageTypes.STOVE_BURN);
      this.method_10512(class_8103.field_45065).method_46835(ModDamageTypes.STOVE_BURN);
      this.method_10512(class_8103.field_51527).method_46835(ModDamageTypes.STOVE_BURN);
      this.method_10512(class_8103.field_51991).method_46835(ModDamageTypes.STOVE_BURN);
   }
}
