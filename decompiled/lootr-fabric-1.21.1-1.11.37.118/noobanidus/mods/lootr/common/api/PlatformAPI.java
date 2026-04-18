package noobanidus.mods.lootr.common.api;

import net.minecraft.class_1297;
import net.minecraft.class_1693;
import net.minecraft.class_2586;
import net.minecraft.class_3222;
import noobanidus.mods.lootr.common.api.adapter.ILootrDataAdapter;
import noobanidus.mods.lootr.common.api.adapter.ILootrItemFrameAdapter;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.common.api.data.entity.ILootrCart;
import noobanidus.mods.lootr.common.api.data.entity.ILootrEntity;

public class PlatformAPI {
   public static IPlatformAPI INSTANCE;

   public static void performEntityOpen(ILootrEntity entity, class_3222 player) {
      INSTANCE.performEntityOpen(entity, player);
   }

   @Deprecated
   public static void performCartOpen(ILootrCart cart, class_3222 player) {
      INSTANCE.performCartOpen(cart, player);
   }

   public static void performEntityOpen(ILootrEntity entity) {
      INSTANCE.performEntityOpen(entity);
   }

   @Deprecated
   public static void performCartOpen(ILootrCart cart) {
      INSTANCE.performCartOpen(cart);
   }

   public static void performEntityClose(ILootrEntity entity, class_3222 player) {
      INSTANCE.performEntityClose(entity, player);
   }

   @Deprecated
   public static void performCartClose(ILootrCart cart, class_3222 player) {
      INSTANCE.performCartClose(cart, player);
   }

   public static void performEntityClose(ILootrEntity entity) {
      INSTANCE.performEntityClose(entity);
   }

   @Deprecated
   public static void performCartClose(ILootrCart cart) {
      INSTANCE.performCartClose(cart);
   }

   public static void performBlockOpen(ILootrBlockEntity blockEntity, class_3222 player) {
      INSTANCE.performBlockOpen(blockEntity, player);
   }

   public static void performBlockOpen(ILootrBlockEntity blockEntity) {
      INSTANCE.performBlockOpen(blockEntity);
   }

   public static void performBlockClose(ILootrBlockEntity blockEntity, class_3222 player) {
      INSTANCE.performBlockClose(blockEntity, player);
   }

   public static void performBlockClose(ILootrBlockEntity blockEntity) {
      INSTANCE.performBlockClose(blockEntity);
   }

   public static DataToCopy copySpecificData(class_2586 oldBlockEntity) {
      return INSTANCE.copySpecificData(oldBlockEntity);
   }

   public static void restoreSpecificData(DataToCopy data, class_2586 newBlockEntity) {
      INSTANCE.restoreSpecificData(data, newBlockEntity);
   }

   @Deprecated
   public static void copyEntityData(class_1693 entity1, class_1693 entity2) {
      INSTANCE.copyEntityData(entity1, entity2);
   }

   public static void copyEntityData(ILootrDataAdapter<class_1297> adapter, class_1297 entity1, ILootrEntity entity2) {
      INSTANCE.copyEntityData(adapter, entity1, entity2);
   }

   public static void copyEntityData(ILootrItemFrameAdapter<class_1297> adapter, class_1297 entity1, ILootrEntity entity2) {
      INSTANCE.copyEntityData(adapter, entity1, entity2);
   }

   public static void refreshPlayerSection(class_3222 player) {
      INSTANCE.refreshPlayerSection(player);
   }

   public static void performPotBreak(ILootrBlockEntity lootrDecoratedPotBlockEntity, class_3222 player) {
      INSTANCE.performPotBreak(lootrDecoratedPotBlockEntity, player);
   }

   public static boolean shouldDoInitialSave() {
      return INSTANCE.shouldDoInitialSave();
   }
}
