package com.blamejared.clumps.mixin;

import com.blamejared.clumps.ClumpsCommon;
import com.blamejared.clumps.api.events.IRepairEvent;
import com.blamejared.clumps.api.events.IValueEvent;
import com.blamejared.clumps.helper.IClumpedOrb;
import com.blamejared.clumps.platform.Services;
import com.mojang.datafixers.util.Either;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1303;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1890;
import net.minecraft.class_1937;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_3218;
import net.minecraft.class_3222;
import net.minecraft.class_5575;
import net.minecraft.class_9699;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = class_1303.class, priority = 1001)
public abstract class MixinExperienceOrb extends class_1297 implements IClumpedOrb {
   @Shadow
   private int field_27009;
   @Shadow
   private int field_6164;
   @Shadow
   private int field_6159;
   @Unique
   public Map<Integer, Integer> clumps$clumpedMap;
   @Unique
   public Optional<class_9699> clumps$currentEntry;

   @Shadow
   protected abstract int method_35051(class_3222 var1, int var2);

   @Shadow
   private static boolean method_31495(class_1303 experienceOrb, int id, int value) {
      return false;
   }

   public MixinExperienceOrb(class_1299<?> entityType, class_1937 level) {
      super(entityType, level);
   }

   @Inject(method = "canMerge(Lnet/minecraft/world/entity/ExperienceOrb;)Z", at = @At("HEAD"), cancellable = true)
   private void canMerge(class_1303 experienceOrb, CallbackInfoReturnable<Boolean> cir) {
      cir.setReturnValue(experienceOrb.method_5805() && !this.method_5779(experienceOrb));
   }

   @Inject(method = "canMerge(Lnet/minecraft/world/entity/ExperienceOrb;II)Z", at = @At("HEAD"), cancellable = true)
   private static void canMerge(class_1303 experienceOrb, int i, int j, CallbackInfoReturnable<Boolean> cir) {
      cir.setReturnValue(experienceOrb.method_5805());
   }

   @Inject(method = "playerTouch(Lnet/minecraft/world/entity/player/Player;)V", at = @At("HEAD"), cancellable = true)
   public void playerTouch(class_1657 rawPlayer, CallbackInfo ci) {
      if (rawPlayer instanceof class_3222 player) {
         if (ClumpsCommon.pickupXPEvent.test(player, (class_1303)this)) {
            return;
         }

         player.field_7504 = 0;
         player.method_6103(this, 1);
         if (this.field_6159 != 0 || this.clumps$resolve()) {
            AtomicInteger toGive = new AtomicInteger();
            this.clumps$getClumpedMap().forEach((value, amount) -> {
               Either<IValueEvent, Integer> result = Services.EVENT.fireValueEvent(player, value);
               int actualValue = (Integer)result.map(IValueEvent::getValue, UnaryOperator.identity());

               for (int i = 0; i < amount; i++) {
                  int leftOver = (Integer)Services.EVENT.fireRepairEvent(player, actualValue).map(IRepairEvent::getValue, UnaryOperator.identity());
                  if (leftOver == actualValue) {
                     leftOver = this.method_35051(player, actualValue);
                  }

                  if (leftOver > 0) {
                     toGive.addAndGet(leftOver);
                  }
               }
            });
            if (toGive.get() > 0) {
               player.method_7255(toGive.get());
            }
         }

         this.method_31472();
         ci.cancel();
      }
   }

   @ModifyVariable(
      index = 3,
      method = "repairPlayerItems",
      at = @At(
         value = "INVOKE_ASSIGN",
         target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getRandomItemWith(Lnet/minecraft/core/component/DataComponentType;Lnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Predicate;)Ljava/util/Optional;"
      )
   )
   public Optional<class_9699> clumps$captureCurrentEntry(Optional<class_9699> entry) {
      this.clumps$currentEntry = entry;
      return entry;
   }

   @Inject(
      method = "repairPlayerItems",
      cancellable = true,
      at = @At(
         value = "INVOKE_ASSIGN",
         target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getRandomItemWith(Lnet/minecraft/core/component/DataComponentType;Lnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Predicate;)Ljava/util/Optional;"
      )
   )
   public void clumps$repairPlayerItems(class_3222 player, int actualValue, CallbackInfoReturnable<Integer> cir) {
      cir.setReturnValue(this.clumps$currentEntry.<Integer>map(foundItem -> {
         class_1799 itemstack = foundItem.comp_2682();
         int xpToRepair = class_1890.method_60168(player.method_51469(), itemstack, (int)(actualValue * Services.PLATFORM.getRepairRatio(itemstack)));
         int toRepair = Math.min(xpToRepair, itemstack.method_7919());
         itemstack.method_7974(itemstack.method_7919() - toRepair);
         if (toRepair > 0) {
            int used = actualValue - toRepair * actualValue / xpToRepair;
            if (used > 0) {
               return this.method_35051(player, used);
            }
         }

         return 0;
      }).orElse(actualValue));
   }

   @Inject(
      method = "merge(Lnet/minecraft/world/entity/ExperienceOrb;)V",
      at = @At(value = "INVOKE", target = "net/minecraft/world/entity/ExperienceOrb.discard()V", shift = Shift.BEFORE),
      cancellable = true
   )
   public void merge(class_1303 secondaryOrb, CallbackInfo ci) {
      Map<Integer, Integer> otherMap = ((IClumpedOrb)secondaryOrb).clumps$getClumpedMap();
      this.field_27009 = this.clumps$getClumpedMap().values().stream().reduce(Integer::sum).orElse(1);
      this.field_6164 = Math.min(this.field_6164, ((ExperienceOrbAccess)secondaryOrb).clumps$getAge());
      this.clumps$setClumpedMap(
         Stream.of(this.clumps$getClumpedMap(), otherMap)
            .flatMap(map -> map.entrySet().stream())
            .collect(Collectors.toMap(Entry::getKey, Entry::getValue, Integer::sum))
      );
      secondaryOrb.method_31472();
      ci.cancel();
   }

   @Inject(method = "tryMergeToExisting(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/phys/Vec3;I)Z", at = @At("HEAD"), cancellable = true)
   private static void tryMergeToExisting(class_3218 serverLevel, class_243 vec3, int value, CallbackInfoReturnable<Boolean> cir) {
      class_238 aABB = class_238.method_30048(vec3, 1.0, 1.0, 1.0);
      int id = serverLevel.method_8409().method_43048(40);
      List<class_1303> list = serverLevel.method_18023(
         class_5575.method_31795(class_1303.class), aABB, experienceOrbx -> method_31495(experienceOrbx, id, value)
      );
      if (!list.isEmpty()) {
         class_1303 experienceOrb = (class_1303)list.getFirst();
         Map<Integer, Integer> clumpedMap = ((IClumpedOrb)experienceOrb).clumps$getClumpedMap();
         ((IClumpedOrb)experienceOrb)
            .clumps$setClumpedMap(
               Stream.of(clumpedMap, Collections.singletonMap(value, 1))
                  .flatMap(map -> map.entrySet().stream())
                  .collect(Collectors.toMap(Entry::getKey, Entry::getValue, Integer::sum))
            );
         ((ExperienceOrbAccess)experienceOrb).clumps$setCount(clumpedMap.values().stream().reduce(Integer::sum).orElse(1));
         ((ExperienceOrbAccess)experienceOrb).clumps$setAge(0);
         cir.setReturnValue(true);
      } else {
         cir.setReturnValue(false);
      }
   }

   @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
   public void addAdditionalSaveData(class_2487 compoundTag, CallbackInfo ci) {
      if (this.clumps$clumpedMap != null) {
         class_2487 map = new class_2487();
         this.clumps$getClumpedMap().forEach((value, count) -> map.method_10569(String.valueOf(value), count));
         compoundTag.method_10566("clumpedMap", map);
      }
   }

   @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
   public void readAdditionalSaveData(class_2487 compoundTag, CallbackInfo ci) {
      Map<Integer, Integer> map = new HashMap<>();
      if (compoundTag.method_10545("clumpedMap")) {
         class_2487 clumpedMap = compoundTag.method_10562("clumpedMap");

         for (String s : clumpedMap.method_10541()) {
            map.put(Integer.parseInt(s), clumpedMap.method_10550(s));
         }
      } else {
         map.put(this.field_6159, this.field_27009);
      }

      this.clumps$setClumpedMap(map);
   }

   @Override
   public Map<Integer, Integer> clumps$getClumpedMap() {
      if (this.clumps$clumpedMap == null) {
         this.clumps$clumpedMap = new HashMap<>();
         this.clumps$clumpedMap.put(this.field_6159, 1);
      }

      return this.clumps$clumpedMap;
   }

   @Override
   public void clumps$setClumpedMap(Map<Integer, Integer> map) {
      this.clumps$clumpedMap = map;
      this.clumps$resolve();
   }

   @Override
   public boolean clumps$resolve() {
      this.field_6159 = this.clumps$getClumpedMap().entrySet().stream().map(entry -> entry.getKey() * entry.getValue()).reduce(Integer::sum).orElse(1);
      return this.field_6159 > 0;
   }
}
