package noobanidus.mods.lootr.common.entity;

import com.google.auto.service.AutoService;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_1262;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1533;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1937;
import net.minecraft.class_2312;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2371;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2561;
import net.minecraft.class_2680;
import net.minecraft.class_3222;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_5630;
import net.minecraft.class_5712;
import net.minecraft.class_8103;
import net.minecraft.class_2350.class_2351;
import noobanidus.mods.lootr.common.api.BuiltInLootrTypes;
import noobanidus.mods.lootr.common.api.ILootrEntityConverter;
import noobanidus.mods.lootr.common.api.ILootrType;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.data.LootrBlockType;
import noobanidus.mods.lootr.common.api.data.SimpleLootrEntityInstance;
import noobanidus.mods.lootr.common.api.data.entity.ILootrEntity;
import noobanidus.mods.lootr.common.api.data.inventory.ILootrInventory;
import noobanidus.mods.lootr.common.api.registry.LootrRegistry;
import noobanidus.mods.lootr.common.mixin.accessor.AccessorMixinItemFrame;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LootrItemFrame extends class_1533 implements ILootrEntity {
   private final class_2371<class_1799> inventory = class_2371.method_10213(1, class_1799.field_8037);
   private final SimpleLootrEntityInstance instance = new SimpleLootrEntityInstance(this, this::getVisualOpeners, 1);

   public LootrItemFrame(class_1299<? extends class_1533> entityType, class_1937 level) {
      super(entityType, level);
   }

   public LootrItemFrame(class_1937 level, class_2338 pos, class_2350 facingDirection) {
      super(LootrRegistry.getItemFrame(), level, pos, facingDirection);
   }

   public void lootrSetItem(class_1799 stack) {
      stack = stack.method_46651(1);
      this.inventory.set(0, stack);
      if (!this.method_37908().method_8608()) {
         this.setItemInternal(stack);
      }
   }

   public void method_5837(class_3222 pPlayer) {
      super.method_5837(pPlayer);
      if (this.hasVisualOpened(pPlayer)) {
         this.performOpen(pPlayer);
      } else {
         this.performClose(pPlayer);
      }
   }

   public boolean method_6888() {
      if (LootrAPI.canItemFramesSelfSupport()) {
         return true;
      } else if (!this.method_37908().method_17892(this)) {
         return false;
      } else {
         class_2680 blockState = this.method_37908().method_8320(this.field_51589.method_10093(this.field_7099.method_10153()));
         return (blockState.method_51367() || this.field_7099.method_10166().method_10179() && class_2312.method_9999(blockState))
            && this.method_37908().method_8333(this, this.method_5829(), field_7098).isEmpty();
      }
   }

   public boolean method_5679(class_1282 source) {
      if (this.method_5655() && source.method_48789(class_8103.field_42242)) {
         return true;
      } else {
         if (source.method_5529() instanceof class_1657 player) {
            if (LootrAPI.canDestroyOrBreak(player)) {
               return false;
            }

            if (LootrAPI.isBreakDisabled()) {
               if (player.method_31549().field_7477) {
                  return !player.method_5715();
               }

               return true;
            }

            if (!source.method_5529().method_5715()) {
               return true;
            }

            if (source.method_5529().method_5715()) {
               return false;
            }
         }

         return true;
      }
   }

   private void maybeMessagePlayer(class_1282 source) {
      if (source.method_5529() instanceof class_1657 player && !this.method_37908().method_8608()) {
         if (LootrAPI.canDestroyOrBreak(player)) {
            return;
         }

         if (LootrAPI.isBreakDisabled()) {
            if (player.method_31549().field_7477) {
               if (!player.method_5715()) {
                  player.method_7353(class_2561.method_43471("lootr.message.cannot_break_sneak").method_10862(LootrAPI.getChatStyle()), false);
               }
            } else {
               player.method_7353(class_2561.method_43471("lootr.message.cannot_break").method_10862(LootrAPI.getChatStyle()), false);
            }
         } else if (!source.method_5529().method_5715()) {
            ((class_1657)source.method_5529())
               .method_7353(class_2561.method_43471("lootr.message.cart_should_sneak").method_10862(LootrAPI.getChatStyle()), false);
            ((class_1657)source.method_5529())
               .method_7353(class_2561.method_43471("lootr.message.cart_should_sneak2").method_10862(LootrAPI.getChatStyle()), false);
         }
      }
   }

   public boolean method_5643(class_1282 source, float amount) {
      boolean skipMessage = false;
      if (amount > 0.0F && source.method_5529() instanceof class_3222 player && this.actuallyDropItem(player)) {
         skipMessage = true;
      }

      if (amount > 0.0F && !skipMessage) {
         this.maybeMessagePlayer(source);
      }

      if (this.method_5679(source)) {
         return false;
      } else {
         if (!this.method_31481() && !this.method_37908().field_9236) {
            this.method_5768();
            this.method_5785();
         }

         return true;
      }
   }

   public void method_6889(@Nullable class_1297 entity) {
   }

   public void method_6933(class_1799 stack, boolean updateNeighbours) {
   }

   private boolean actuallyDropItem(class_3222 player) {
      if (this.method_37908().method_8608()) {
         return false;
      } else {
         ILootrInventory inventory = LootrAPI.getInventory(this, player);
         if (inventory == null) {
            return false;
         } else if (inventory.method_5438(0).method_7960()) {
            return false;
         } else {
            if (!this.hasServerOpened(player)) {
               player.method_7259(LootrRegistry.getLootedStat());
               LootrRegistry.getStatTrigger().trigger(player);
            }

            this.method_5783(this.method_34240(), 1.0F, 1.0F);
            inventory.method_5447(0, class_1799.field_8037);
            inventory.method_5431();
            class_1799 item = this.method_6940().method_7972();
            this.method_5775(item);
            this.performTrigger(player);
            if (this.addOpener(player)) {
               this.performOpen(player);
            }

            this.performUpdate(player);
            return true;
         }
      }
   }

   public class_1799 method_6940() {
      return (class_1799)this.method_5841().method_12789(AccessorMixinItemFrame.lootr$getDataItem());
   }

   public boolean method_43273() {
      return false;
   }

   public class_5630 method_32318(int slot) {
      return class_5630.field_27860;
   }

   public void method_5652(class_2487 compound) {
      super.method_5652(compound);
      compound.method_10566("customInventory", class_1262.method_5426(new class_2487(), this.inventory, this.method_37908().method_30349()));
   }

   public void method_5749(class_2487 compound) {
      super.method_5749(compound);
      class_1262.method_5429(compound.method_10562("customInventory"), this.inventory, this.method_37908().method_30349());
      this.setItemInternal((class_1799)this.inventory.getFirst());
   }

   private void setItemInternal(class_1799 stack) {
      if (stack.method_31574(class_1802.field_8204)) {
         LootrAPI.LOG.error("ItemFrames with maps are not supported by Lootr Item Frames due to technical limitations.");
      } else {
         if (!stack.method_7960()) {
            stack = stack.method_46651(1);
         }

         ((AccessorMixinItemFrame)this).lootr$onItemChanged(stack);
         this.method_5841().method_12778(AccessorMixinItemFrame.lootr$getDataItem(), stack);
      }
   }

   public void method_5773() {
      super.method_5773();
      if (!this.method_37908().method_8608()) {
         LootrAPI.handleProviderTick(this);
      } else {
         LootrAPI.handleProviderClientTick(this);
      }
   }

   public class_1269 method_5688(class_1657 player, class_1268 hand) {
      if (!this.method_37908().field_9236) {
         this.method_5783(this.method_34244(), 1.0F, 1.0F);
         this.method_6939(this.method_6934() + 1);
         this.method_32875(class_5712.field_28733, player);
         return class_1269.field_21466;
      } else {
         return class_1269.field_5812;
      }
   }

   public class_1799 method_31480() {
      return new class_1799(class_1802.field_8143);
   }

   protected class_1799 method_33340() {
      return new class_1799(class_1802.field_8143);
   }

   @Nullable
   @Override
   public Set<UUID> getClientOpeners() {
      return this.instance.getClientOpeners();
   }

   @Override
   public boolean isClientOpened() {
      return this.instance.isClientOpened();
   }

   @Override
   public void setClientOpened(boolean opened) {
      this.instance.setClientOpened(opened);
   }

   @Override
   public void markChanged() {
      this.markDataChanged();
   }

   @Deprecated
   @Override
   public LootrBlockType getInfoBlockType() {
      return null;
   }

   @Override
   public ILootrType getInfoNewType() {
      return BuiltInLootrTypes.ITEM_FRAME;
   }

   @NotNull
   @Override
   public UUID getInfoUUID() {
      return this.method_5667();
   }

   @Override
   public String getInfoKey() {
      return this.instance.getInfoKey();
   }

   @Override
   public boolean hasBeenOpened() {
      return this.instance.hasBeenOpened();
   }

   @Override
   public boolean isPhysicallyOpen() {
      return false;
   }

   @NotNull
   @Override
   public class_2338 getInfoPos() {
      return class_2338.method_49638(this.method_19538());
   }

   @Nullable
   @Override
   public class_2561 getInfoDisplayName() {
      return this.method_5476();
   }

   @NotNull
   @Override
   public class_5321<class_1937> getInfoDimension() {
      return this.method_37908().method_27983();
   }

   @Override
   public int getInfoContainerSize() {
      return 1;
   }

   @Nullable
   @Override
   public class_2371<class_1799> getInfoReferenceInventory() {
      return this.inventory;
   }

   @Override
   public boolean isInfoReferenceInventory() {
      return true;
   }

   @Nullable
   @Override
   public class_5321<class_52> getInfoLootTable() {
      return LootrAPI.ITEM_FRAME_EMPTY;
   }

   @Override
   public long getInfoLootSeed() {
      return 0L;
   }

   @Override
   public class_1937 getInfoLevel() {
      return this.method_37908();
   }

   @Override
   public class_243 getParticleCenter() {
      return this.method_19538();
   }

   @Override
   public double getParticleYOffset() {
      return 0.4F;
   }

   @Override
   public double[] getParticleXBounds() {
      if (this.field_7099.method_10166() == class_2351.field_11051) {
         return new double[]{-0.35, 0.35};
      } else {
         return this.field_7099 != class_2350.field_11035 && this.field_7099 != class_2350.field_11034 ? new double[]{-0.1, -0.05} : new double[]{0.05, 0.1};
      }
   }

   @Override
   public double[] getParticleZBounds() {
      if (this.field_7099.method_10166() == class_2351.field_11048) {
         return new double[]{-0.35, 0.35};
      } else {
         return this.field_7099 != class_2350.field_11035 && this.field_7099 != class_2350.field_11034 ? new double[]{-0.1, -0.05} : new double[]{0.05, 0.1};
      }
   }

   @AutoService(ILootrEntityConverter.class)
   public static class DefaultConverter implements ILootrEntityConverter<LootrItemFrame> {
      public ILootrEntity apply(LootrItemFrame entity) {
         return entity;
      }

      @Override
      public class_1299<?> getEntityType() {
         return LootrRegistry.getItemFrame();
      }
   }
}
