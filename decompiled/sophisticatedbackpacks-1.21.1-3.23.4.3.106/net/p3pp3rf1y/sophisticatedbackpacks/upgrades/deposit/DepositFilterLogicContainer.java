package net.p3pp3rf1y.sophisticatedbackpacks.upgrades.deposit;

import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.class_1735;
import net.minecraft.class_2487;
import net.p3pp3rf1y.sophisticatedcore.common.gui.IServerUpdater;
import net.p3pp3rf1y.sophisticatedcore.upgrades.FilterLogicContainer;
import net.p3pp3rf1y.sophisticatedcore.util.NBTHelper;

public class DepositFilterLogicContainer extends FilterLogicContainer<DepositFilterLogic> {
   private static final String DATA_DEPOSIT_FILTER_TYPE = "depositFilterType";

   public DepositFilterLogicContainer(Supplier<DepositFilterLogic> filterLogic, IServerUpdater serverUpdater, Consumer<class_1735> addSlot) {
      super(filterLogic, serverUpdater, addSlot);
      if (this.getDepositFilterType() == DepositFilterType.INVENTORY) {
         this.getFilterSlots().forEach(s -> s.setEnabled(false));
      }
   }

   public void setDepositFilterType(DepositFilterType depositFilterType) {
      ((DepositFilterLogic)this.filterLogic.get()).setDepositFilterType(depositFilterType);
      this.serverUpdater.sendDataToServer(() -> NBTHelper.putEnumConstant(new class_2487(), "depositFilterType", depositFilterType));
   }

   public boolean handlePacket(class_2487 data) {
      if (data.method_10545("depositFilterType")) {
         this.setDepositFilterType(DepositFilterType.fromName(data.method_10558("depositFilterType")));
      }

      return super.handlePacket(data);
   }

   public DepositFilterType getDepositFilterType() {
      return ((DepositFilterLogic)this.filterLogic.get()).getDepositFilterType();
   }
}
