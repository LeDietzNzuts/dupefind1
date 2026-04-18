package net.caffeinemc.mods.lithium.mixin.experimental.entity.item_entity_merging;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import net.caffeinemc.mods.lithium.common.entity.TypeFilterableListInternalAccess;
import net.caffeinemc.mods.lithium.common.entity.item.ItemEntityLazyIterationConsumer;
import net.caffeinemc.mods.lithium.common.entity.item.ItemEntityList;
import net.caffeinemc.mods.lithium.common.world.WorldHelper;
import net.caffeinemc.mods.lithium.mixin.util.accessors.EntitySectionAccessor;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1542;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import net.minecraft.class_3509;
import net.minecraft.class_5573;
import net.minecraft.class_7927.class_7928;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(class_1542.class)
public abstract class ItemEntityMixin extends class_1297 {
   public ItemEntityMixin(class_1299<?> type, class_1937 world) {
      super(type, world);
   }

   @Redirect(
      method = "method_6973()V",
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_1937;method_8390(Ljava/lang/Class;Lnet/minecraft/class_238;Ljava/util/function/Predicate;)Ljava/util/List;"
      )
   )
   private List<class_1542> getItems(class_1937 world, Class<class_1542> itemEntityClass, class_238 box, Predicate<class_1542> predicate) {
      class_5573<class_1297> cache = WorldHelper.getEntityCacheOrNull(world);
      return (List<class_1542>)(cache != null
         ? consumeItemEntitiesForMerge(cache, (class_1542)this, box, predicate)
         : world.method_8390(itemEntityClass, box, predicate));
   }

   @Unique
   private static ArrayList<class_1542> consumeItemEntitiesForMerge(
      class_5573<class_1297> cache, class_1542 searchingItemEntity, class_238 box, Predicate<class_1542> predicate
   ) {
      ItemEntityLazyIterationConsumer itemEntityConsumer = new ItemEntityLazyIterationConsumer(searchingItemEntity, box, predicate);
      cache.method_31777(
         box,
         section -> {
            class_3509<class_1297> allEntities = ((EntitySectionAccessor)section).getCollection();
            TypeFilterableListInternalAccess<class_1297> internalEntityList = (TypeFilterableListInternalAccess<class_1297>)allEntities;
            List<class_1542> itemEntities = internalEntityList.lithium$getOrCreateAllOfTypeRaw(class_1542.class);
            class_7928 next = class_7928.field_41283;
            if (itemEntities instanceof ItemEntityList itemEntityList) {
               next = itemEntityList.consumeForEntityStacking(searchingItemEntity, itemEntityConsumer);
            } else if (itemEntities.size() > 10 && itemEntities instanceof ArrayList) {
               ItemEntityList itemEntityList = (ItemEntityList)internalEntityList.<class_1542>lithium$replaceCollectionAndGet(
                  class_1542.class, ItemEntityList::new
               );
               next = itemEntityList.consumeForEntityStacking(searchingItemEntity, itemEntityConsumer);
            } else {
               for (int i = 0; next != class_7928.field_41284 && i < itemEntities.size(); i++) {
                  class_1542 entity = itemEntities.get(i);
                  next = itemEntityConsumer.accept(entity);
               }
            }

            return next;
         }
      );
      return itemEntityConsumer.getMergeEntities();
   }
}
