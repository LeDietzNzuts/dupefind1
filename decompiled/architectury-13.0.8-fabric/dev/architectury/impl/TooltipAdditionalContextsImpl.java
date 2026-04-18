package dev.architectury.impl;

import dev.architectury.event.events.client.ClientTooltipEvent;
import net.minecraft.class_1799;
import org.jetbrains.annotations.Nullable;

public class TooltipAdditionalContextsImpl implements ClientTooltipEvent.AdditionalContexts {
   private static final ThreadLocal<TooltipAdditionalContextsImpl> INSTANCE_LOCAL = ThreadLocal.withInitial(TooltipAdditionalContextsImpl::new);
   @Nullable
   private class_1799 item;

   public static ClientTooltipEvent.AdditionalContexts get() {
      return INSTANCE_LOCAL.get();
   }

   @Nullable
   @Override
   public class_1799 getItem() {
      return this.item;
   }

   @Override
   public void setItem(@Nullable class_1799 item) {
      this.item = item;
   }
}
