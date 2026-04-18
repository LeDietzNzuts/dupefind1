package net.p3pp3rf1y.sophisticatedcore.compat.litematica;

import com.google.common.collect.Lists;
import fi.dy.masa.litematica.materials.MaterialListBase;
import fi.dy.masa.litematica.materials.MaterialListEntry;
import fi.dy.masa.litematica.materials.MaterialListUtils;
import fi.dy.masa.litematica.scheduler.tasks.TaskBase;
import fi.dy.masa.malilib.gui.Message.MessageType;
import fi.dy.masa.malilib.util.InfoUtils;
import java.util.List;

public class TaskCountReceivedContents extends TaskBase {
   private final MaterialListBase materialList;
   protected int requestedContents;
   protected int receivedContents;
   protected boolean oldFinished;

   public TaskCountReceivedContents(MaterialListBase materialList) {
      this.materialList = materialList;
      this.oldFinished = false;
   }

   public boolean execute() {
      InfoUtils.showGuiMessage(
         MessageType.INFO, 1000, "compat.litematica.gui.label.task.remaining_contents", new Object[]{this.receivedContents, this.requestedContents}
      );
      this.oldFinished = this.finished;
      this.finished = this.receivedContents >= this.requestedContents;
      return this.finished && this.oldFinished;
   }

   public void setRequested(int requested) {
      this.requestedContents = requested;
   }

   public void incrementReceived(int count) {
      this.receivedContents += count;
   }

   public void stop() {
      this.mc.execute(() -> {
         List<MaterialListEntry> materialListEntries = Lists.newArrayList(this.materialList.getMaterialsAll());
         MaterialListUtils.updateAvailableCounts(materialListEntries, this.mc.field_1724);
         this.materialList.setMaterialListEntries(materialListEntries);
      });
      this.notifyListener();
   }
}
