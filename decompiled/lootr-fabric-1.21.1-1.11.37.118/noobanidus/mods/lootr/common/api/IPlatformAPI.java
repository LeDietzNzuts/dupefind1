package noobanidus.mods.lootr.common.api;

import net.minecraft.class_1297;
import net.minecraft.class_1693;
import net.minecraft.class_2586;
import net.minecraft.class_3222;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import noobanidus.mods.lootr.common.api.adapter.ILootrDataAdapter;
import noobanidus.mods.lootr.common.api.adapter.ILootrItemFrameAdapter;
import noobanidus.mods.lootr.common.api.data.blockentity.ILootrBlockEntity;
import noobanidus.mods.lootr.common.api.data.entity.ILootrCart;
import noobanidus.mods.lootr.common.api.data.entity.ILootrEntity;
import org.jetbrains.annotations.ApiStatus.Internal;

@Internal
public interface IPlatformAPI {
   void performEntityOpen(ILootrEntity var1, class_3222 var2);

   @Deprecated
   default void performCartOpen(ILootrCart cart, class_3222 player) {
      this.performEntityOpen(cart, player);
   }

   @Deprecated
   default void performCartOpen(ILootrCart cart) {
      this.performEntityOpen(cart);
   }

   void performEntityOpen(ILootrEntity var1);

   @Deprecated
   default void performCartClose(ILootrCart cart, class_3222 player) {
      this.performEntityClose(cart, player);
   }

   void performEntityClose(ILootrEntity var1, class_3222 var2);

   @Deprecated
   default void performCartClose(ILootrCart cart) {
      this.performEntityClose(cart);
   }

   void performEntityClose(ILootrEntity var1);

   void performBlockOpen(ILootrBlockEntity var1, class_3222 var2);

   void performBlockOpen(ILootrBlockEntity var1);

   void performBlockClose(ILootrBlockEntity var1, class_3222 var2);

   void performBlockClose(ILootrBlockEntity var1);

   DataToCopy copySpecificData(class_2586 var1);

   void restoreSpecificData(DataToCopy var1, class_2586 var2);

   @Deprecated
   default void copyEntityData(class_1693 entity1, class_1693 entity2) {
      entity2.method_36457(entity1.method_36455());
      entity2.method_36456(entity1.method_36454());
      entity2.method_5847(entity1.method_5791());
      if (entity1.method_42276() != null) {
         entity2.method_7562(entity1.method_42276(), entity1.method_42277());
      }
   }

   default void copyEntityData(ILootrDataAdapter<class_1297> adapter, class_1297 entity1, ILootrEntity entity3) {
      class_1297 entity2 = entity3.asEntity();
      entity2.method_36457(entity1.method_36455());
      entity2.method_36456(entity1.method_36454());
      entity2.method_5847(entity1.method_5791());
      class_5321<class_52> lootTable = adapter.getLootTable(entity1);
      long seed = adapter.getLootSeed(entity1);
      if (lootTable != null && entity2 instanceof class_1693 entity4) {
         entity4.method_7562(lootTable, seed);
      }
   }

   default void copyEntityData(ILootrItemFrameAdapter<class_1297> adapter, class_1297 entity1, ILootrEntity entity3) {
      class_1297 entity2 = entity3.asEntity();
      entity2.method_36457(entity1.method_36455());
      entity2.method_36456(entity1.method_36454());
      entity2.method_5847(entity1.method_5791());
   }

   void refreshPlayerSection(class_3222 var1);

   void performPotBreak(ILootrBlockEntity var1, class_3222 var2);

   boolean shouldDoInitialSave();
}
