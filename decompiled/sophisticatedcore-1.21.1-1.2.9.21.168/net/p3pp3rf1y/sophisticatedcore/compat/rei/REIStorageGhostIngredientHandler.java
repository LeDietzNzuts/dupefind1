package net.p3pp3rf1y.sophisticatedcore.compat.rei;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.drag.DraggableStack;
import me.shedaniel.rei.api.client.gui.drag.DraggableStackVisitor;
import me.shedaniel.rei.api.client.gui.drag.DraggedAcceptorResult;
import me.shedaniel.rei.api.client.gui.drag.DraggingContext;
import me.shedaniel.rei.api.client.gui.drag.DraggableStackVisitor.BoundsProvider;
import net.minecraft.class_1735;
import net.minecraft.class_1799;
import net.minecraft.class_238;
import net.minecraft.class_265;
import net.p3pp3rf1y.sophisticatedcore.client.gui.StorageScreenBase;
import net.p3pp3rf1y.sophisticatedcore.common.gui.IFilterSlot;
import net.p3pp3rf1y.sophisticatedcore.common.gui.StorageContainerMenuBase;
import net.p3pp3rf1y.sophisticatedcore.compat.jei.SetGhostSlotPayload;
import net.p3pp3rf1y.sophisticatedcore.network.PacketDistributor;

public abstract class REIStorageGhostIngredientHandler<S extends StorageScreenBase<?>> implements DraggableStackVisitor<S> {
   public DraggedAcceptorResult acceptDraggedStack(DraggingContext<S> context, DraggableStack stack) {
      Stream<BoundsProvider> bounds = this.getDraggableAcceptingBounds(context, stack);
      Point cursor = context.getCurrentPosition();
      if (cursor != null) {
         int x = cursor.getX();
         int y = cursor.getY();
         Optional<BoundsProvider> target = bounds.filter(b -> {
            class_238 box = b.bounds().method_1107();
            double minX = box.field_1323;
            double minY = box.field_1322;
            double maxX = box.field_1320;
            double maxY = box.field_1325;
            return x >= minX && x <= maxX && y >= minY && y <= maxY && b instanceof REIStorageGhostIngredientHandler.GhostTarget;
         }).findFirst();
         if (target.isPresent()) {
            REIStorageGhostIngredientHandler.GhostTarget<class_1799, ? extends StorageScreenBase<?>> ghost = (REIStorageGhostIngredientHandler.GhostTarget<class_1799, ? extends StorageScreenBase<?>>)target.get();
            if (stack.getStack().getValue() instanceof class_1799 item) {
               ghost.accept(item);
               return DraggedAcceptorResult.CONSUMED;
            }
         }
      }

      return super.acceptDraggedStack(context, stack);
   }

   public Stream<BoundsProvider> getDraggableAcceptingBounds(DraggingContext<S> context, DraggableStack stack) {
      List<BoundsProvider> targets = new ArrayList<>();
      StorageContainerMenuBase<?> screen = (StorageContainerMenuBase<?>)((StorageScreenBase)context.getScreen()).method_17577();
      if (stack.getStack().getValue() instanceof class_1799 ghostStack) {
         screen.getOpenContainer().ifPresent(c -> c.getSlots().forEach(s -> {
            if (s instanceof IFilterSlot && s.method_7680(ghostStack)) {
               targets.add(new REIStorageGhostIngredientHandler.GhostTarget((S)context.getScreen(), ghostStack, s));
            }
         }));
      }

      return targets.stream();
   }

   private static class GhostTarget<I, S extends StorageScreenBase<?>> implements BoundsProvider {
      private final Rectangle area;
      private final class_1735 slot;
      private final class_1799 stack;

      public GhostTarget(S screen, class_1799 stack, class_1735 slot) {
         this.slot = slot;
         this.stack = stack;
         this.area = new Rectangle(screen.sophisticatedCore_getGuiLeft() + slot.field_7873, screen.sophisticatedCore_getGuiTop() + slot.field_7872, 16, 16);
      }

      public void accept(I ingredient) {
         PacketDistributor.sendToServer(new SetGhostSlotPayload(this.stack, this.slot.field_7874));
      }

      public class_265 bounds() {
         return BoundsProvider.fromRectangle(this.area);
      }
   }
}
