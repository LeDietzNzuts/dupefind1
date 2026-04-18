package net.p3pp3rf1y.sophisticatedcore.upgrades;

import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.class_1735;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.common.gui.IServerUpdater;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;

public class ContentsFilterLogicContainer extends FilterLogicContainer<ContentsFilterLogic> {
   private static final String DATA_CONTENTS_FILTER_TYPE = "contentsFilterType";

   public ContentsFilterLogicContainer(Supplier<ContentsFilterLogic> filterLogic, IServerUpdater serverUpdater, Consumer<class_1735> addSlot) {
      super(filterLogic, serverUpdater, addSlot);
      if (this.getFilterType() == ContentsFilterType.STORAGE) {
         this.getFilterSlots().forEach(s -> s.setEnabled(false));
      }
   }

   public void setFilterType(ContentsFilterType depositFilterType) {
      this.filterLogic.get().setDepositFilterType(depositFilterType);
      this.sendDataToServer(() -> NBTHelper.putEnumConstant(new class_2487(), "contentsFilterType", depositFilterType));
   }

   @Override
   public boolean handlePacket(class_2487 data) {
      if (this.isDifferentFilterLogicsData(data)) {
         return false;
      } else {
         if (data.method_10545("contentsFilterType")) {
            this.setFilterType(ContentsFilterType.fromName(data.method_10558("contentsFilterType")));
         }

         return super.handlePacket(data);
      }
   }

   public ContentsFilterType getFilterType() {
      return this.filterLogic.get().getFilterType();
   }
}
