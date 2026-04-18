package vectorwing.farmersdelight.data;

import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.EntityTypeTagProvider;
import net.minecraft.class_1299;
import net.minecraft.class_7225.class_7874;
import vectorwing.farmersdelight.common.tag.ModTags;

public class EntityTags extends EntityTypeTagProvider {
   public EntityTags(FabricDataOutput output, CompletableFuture<class_7874> lookupProvider) {
      super(output, lookupProvider);
   }

   protected void method_10514(class_7874 provider) {
      this.getOrCreateTagBuilder(ModTags.DOG_FOOD_USERS).add(class_1299.field_6055);
      this.getOrCreateTagBuilder(ModTags.HORSE_FEED_USERS)
         .add(
            new class_1299[]{
               class_1299.field_6139, class_1299.field_6075, class_1299.field_6048, class_1299.field_6067, class_1299.field_6057, class_1299.field_6074
            }
         );
      this.getOrCreateTagBuilder(ModTags.HORSE_FEED_TEMPTED).add(new class_1299[]{class_1299.field_6139, class_1299.field_6067, class_1299.field_6057});
   }
}
