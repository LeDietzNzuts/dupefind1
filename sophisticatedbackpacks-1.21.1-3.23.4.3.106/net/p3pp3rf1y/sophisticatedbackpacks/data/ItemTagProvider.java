package net.p3pp3rf1y.sophisticatedbackpacks.data;

import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.class_1792;
import net.minecraft.class_2960;
import net.minecraft.class_5321;
import net.minecraft.class_7923;
import net.minecraft.class_7225.class_7874;
import net.p3pp3rf1y.sophisticatedbackpacks.init.ModItems;
import net.p3pp3rf1y.sophisticatedcore.upgrades.UpgradeItemBase;

public class ItemTagProvider extends net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.ItemTagProvider {
   public ItemTagProvider(FabricDataOutput output, CompletableFuture<class_7874> completableFuture) {
      super(output, completableFuture);
   }

   protected void method_10514(class_7874 pProvider) {
      FabricTagProvider<class_1792>.FabricTagBuilder upgradeTag = this.getOrCreateTagBuilder(ModItems.BACKPACK_UPGRADE_TAG);
      class_7923.field_41178
         .method_29722()
         .stream()
         .filter(
            entry -> ((class_5321)entry.getKey()).method_29177().method_12836().equals("sophisticatedbackpacks") && entry.getValue() instanceof UpgradeItemBase
         )
         .map(Entry::getValue)
         .forEach(item -> {
            class_2960 location = class_7923.field_41178.method_10221(item);
            if (location.method_12832().contains("/")) {
               upgradeTag.addOptional(location);
            } else {
               upgradeTag.add(item);
            }
         });
   }
}
