package net.caffeinemc.mods.lithium.mixin.minimal_nonvanilla.world.expiring_chunk_tickets;

import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap.Entry;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.Collections;
import java.util.Iterator;
import net.caffeinemc.mods.lithium.common.util.collections.ChunkTicketSortedArraySet;
import net.minecraft.class_3204;
import net.minecraft.class_3228;
import net.minecraft.class_4706;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(class_3204.class)
public abstract class DistanceManagerMixin {
   @Shadow
   private long field_13894;
   @Shadow
   @Final
   Long2ObjectOpenHashMap<class_4706<class_3228<?>>> field_13895;
   private final Long2ObjectOpenHashMap<class_4706<class_3228<?>>> positionWithExpiringTicket = new Long2ObjectOpenHashMap();

   private static boolean canNoneExpire(class_4706<class_3228<?>> tickets) {
      if (!tickets.isEmpty()) {
         for (class_3228<?> ticket : tickets) {
            if (canExpire(ticket)) {
               return false;
            }
         }
      }

      return true;
   }

   @Redirect(
      method = {"method_14041(J)Lnet/minecraft/class_4706;", "lambda$getTickets$7"},
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_4706;method_23859(I)Lnet/minecraft/class_4706;")
   )
   private static class_4706<class_3228<?>> useLithiumSortedArraySet(int initialCapacity) {
      return new ChunkTicketSortedArraySet<>(initialCapacity);
   }

   private static boolean canExpire(class_3228<?> ticket) {
      return ticket.method_14281().method_20629() != 0L;
   }

   @Inject(
      method = "method_14042(JLnet/minecraft/class_3228;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_3228;method_23956(J)V"),
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   private void registerExpiringTicket(
      long position, class_3228<?> ticket, CallbackInfo ci, class_4706<class_3228<?>> ticketsAtPos, int i, class_3228<?> chunkTicket
   ) {
      if (canExpire(ticket)) {
         this.positionWithExpiringTicket.put(position, ticketsAtPos);
      }
   }

   @Inject(
      method = "method_17645(JLnet/minecraft/class_3228;)V",
      at = @At(value = "INVOKE", target = "Lnet/minecraft/class_3204$class_4077;method_14027(JIZ)V")
   )
   private void unregisterExpiringTicket(long pos, class_3228<?> ticket, CallbackInfo ci) {
      if (canExpire(ticket)) {
         class_4706<class_3228<?>> ticketsAtPos = (class_4706<class_3228<?>>)this.positionWithExpiringTicket.get(pos);
         if (canNoneExpire(ticketsAtPos)) {
            this.positionWithExpiringTicket.remove(pos);
         }
      }
   }

   @Inject(
      method = "method_14042(JLnet/minecraft/class_3228;)V",
      at = @At(value = "INVOKE", shift = Shift.BEFORE, target = "Lnet/minecraft/class_4706;method_23862(Ljava/lang/Object;)Ljava/lang/Object;"),
      locals = LocalCapture.CAPTURE_FAILHARD
   )
   private void updateSetMinExpiryTime(long position, class_3228<?> ticket, CallbackInfo ci, class_4706<?> sortedArraySet, int i) {
      if (canExpire(ticket) && sortedArraySet instanceof ChunkTicketSortedArraySet<?> chunkTickets) {
         chunkTickets.addExpireTime(this.field_13894 + ticket.method_14281().method_20629());
      }
   }

   @Redirect(
      method = "method_14045()V",
      at = @At(value = "FIELD", target = "Lnet/minecraft/class_3204;field_13895:Lit/unimi/dsi/fastutil/longs/Long2ObjectOpenHashMap;", ordinal = 0)
   )
   private Long2ObjectOpenHashMap<class_4706<class_3228<?>>> getExpiringTicketsByPosition(class_3204 chunkTicketManager) {
      return this.positionWithExpiringTicket;
   }

   @Redirect(method = "method_14045()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/SortedArraySet;isEmpty()Z"))
   private boolean retCanNoneExpire(class_4706<class_3228<?>> tickets) {
      return canNoneExpire(tickets);
   }

   @Inject(
      method = "method_14045()V",
      locals = LocalCapture.CAPTURE_FAILHARD,
      at = @At(value = "INVOKE", shift = Shift.BEFORE, target = "Lnet/minecraft/util/SortedArraySet;isEmpty()Z")
   )
   private void removeIfEmpty(CallbackInfo ci, ObjectIterator<?> objectIterator, Entry<class_4706<class_3228<?>>> entry) {
      class_4706<class_3228<?>> ticketsAtPos = (class_4706<class_3228<?>>)entry.getValue();
      if (ticketsAtPos.isEmpty()) {
         this.field_13895.remove(entry.getLongKey(), ticketsAtPos);
      }
   }

   @Redirect(method = "method_14045()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/class_4706;iterator()Ljava/util/Iterator;"))
   private Iterator<class_3228<?>> skipIfNotExpiringNow(class_4706<class_3228<?>> ticketsAtPos) {
      return ticketsAtPos instanceof ChunkTicketSortedArraySet<?> optimizedSet && optimizedSet.getMinExpireTime() > this.field_13894
         ? Collections.emptyIterator()
         : ticketsAtPos.iterator();
   }
}
