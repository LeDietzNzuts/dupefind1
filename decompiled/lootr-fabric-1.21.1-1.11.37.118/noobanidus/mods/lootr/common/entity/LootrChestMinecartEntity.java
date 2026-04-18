package noobanidus.mods.lootr.common.entity;

import com.google.auto.service.AutoService;
import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import java.util.Set;
import java.util.UUID;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1282;
import net.minecraft.class_1299;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1693;
import net.minecraft.class_1703;
import net.minecraft.class_1707;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1937;
import net.minecraft.class_2281;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2371;
import net.minecraft.class_243;
import net.minecraft.class_2561;
import net.minecraft.class_2680;
import net.minecraft.class_3222;
import net.minecraft.class_52;
import net.minecraft.class_5321;
import net.minecraft.class_5712;
import net.minecraft.class_8103;
import net.minecraft.class_1297.class_5529;
import net.minecraft.class_1688.class_1689;
import noobanidus.mods.lootr.common.api.BuiltInLootrTypes;
import noobanidus.mods.lootr.common.api.ILootrEntityConverter;
import noobanidus.mods.lootr.common.api.ILootrType;
import noobanidus.mods.lootr.common.api.LootrAPI;
import noobanidus.mods.lootr.common.api.advancement.IContainerTrigger;
import noobanidus.mods.lootr.common.api.data.ILootrInfo;
import noobanidus.mods.lootr.common.api.data.LootrBlockType;
import noobanidus.mods.lootr.common.api.data.entity.ILootrEntity;
import noobanidus.mods.lootr.common.api.registry.LootrRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LootrChestMinecartEntity extends class_1693 implements ILootrEntity {
   private static class_2680 cartNormal = null;
   private final Set<UUID> clientOpeners = new ObjectLinkedOpenHashSet();
   private boolean hasBeenOpened = false;
   private boolean opened = false;
   private String cachedId;

   public LootrChestMinecartEntity(class_1299<LootrChestMinecartEntity> type, class_1937 world) {
      super(type, world);
   }

   public LootrChestMinecartEntity(class_1937 worldIn, double x, double y, double z) {
      super(LootrRegistry.getMinecart(), x, y, z, worldIn);
   }

   public class_1799 method_31480() {
      return new class_1799(class_1802.field_8388);
   }

   public void method_42291(@Nullable class_1657 p_219950_) {
   }

   @Nullable
   @Override
   public Set<UUID> getClientOpeners() {
      return this.clientOpeners;
   }

   @Override
   public boolean isClientOpened() {
      return this.opened;
   }

   @Override
   public void setClientOpened(boolean opened) {
      this.opened = opened;
   }

   public boolean method_5679(class_1282 source) {
      if (this.method_5655() && source.method_48789(class_8103.field_42242)) {
         return true;
      } else if (source.method_5529() instanceof class_1657 player) {
         if (LootrAPI.canDestroyOrBreak(player)) {
            return false;
         } else if (LootrAPI.isBreakDisabled()) {
            if (player.method_31549().field_7477) {
               if (!player.method_5715()) {
                  player.method_7353(class_2561.method_43471("lootr.message.cannot_break_sneak").method_10862(LootrAPI.getChatStyle()), false);
                  return true;
               } else {
                  return false;
               }
            } else {
               player.method_7353(class_2561.method_43471("lootr.message.cannot_break").method_10862(LootrAPI.getChatStyle()), false);
               return true;
            }
         } else if (!source.method_5529().method_5715()) {
            ((class_1657)source.method_5529())
               .method_7353(class_2561.method_43471("lootr.message.cart_should_sneak").method_10862(LootrAPI.getChatStyle()), false);
            ((class_1657)source.method_5529())
               .method_7353(class_2561.method_43471("lootr.message.cart_should_sneak2").method_10862(LootrAPI.getChatStyle()), false);
            return true;
         } else {
            return !source.method_5529().method_5715();
         }
      } else {
         return true;
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

   public int method_5439() {
      return 27;
   }

   public class_1689 method_7518() {
      return class_1689.field_7678;
   }

   public class_2680 method_7517() {
      if (cartNormal == null) {
         cartNormal = (class_2680)LootrRegistry.getChestBlock().method_9564().method_11657(class_2281.field_10768, class_2350.field_11043);
      }

      return cartNormal;
   }

   public int method_7526() {
      return 8;
   }

   public class_1703 method_17357(int id, class_1661 playerInventoryIn) {
      return class_1707.method_19245(id, playerInventoryIn, this);
   }

   public void method_5650(class_5529 reason) {
      this.method_31745(reason);
      if (reason == class_5529.field_26998) {
         this.method_32876(class_5712.field_37676);
      }
   }

   public class_1269 method_5688(class_1657 player, class_1268 hand) {
      if (!this.method_37908().method_8608() && !player.method_7325() && player instanceof class_3222 serverPlayer) {
         if (player.method_5715()) {
            LootrAPI.handleProviderSneak(this, serverPlayer);
         } else {
            LootrAPI.handleProviderOpen(this, serverPlayer);
         }

         return class_1269.field_5812;
      } else {
         return class_1269.field_21466;
      }
   }

   public void method_5435(class_1657 player) {
      if (!player.method_7325()) {
         if (!this.hasBeenOpened) {
            this.hasBeenOpened = true;
            this.markChanged();
         }

         this.performOpen((class_3222)player);
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

   @NotNull
   @Override
   public class_2338 getInfoPos() {
      return this.method_24515();
   }

   @Override
   public class_5321<class_52> getInfoLootTable() {
      return this.method_42276();
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
      return this.method_5439();
   }

   @Override
   public long getInfoLootSeed() {
      return this.method_42277();
   }

   @Nullable
   @Override
   public class_2371<class_1799> getInfoReferenceInventory() {
      return null;
   }

   @Override
   public boolean isInfoReferenceInventory() {
      return false;
   }

   @Override
   public class_1937 getInfoLevel() {
      return this.method_37908();
   }

   @Deprecated
   @Override
   public LootrBlockType getInfoBlockType() {
      return LootrBlockType.ENTITY;
   }

   @Override
   public ILootrType getInfoNewType() {
      return BuiltInLootrTypes.MINECART;
   }

   @NotNull
   @Override
   public class_243 getInfoVec() {
      return this.method_19538();
   }

   @NotNull
   @Override
   public UUID getInfoUUID() {
      return this.method_5667();
   }

   @Override
   public String getInfoKey() {
      if (this.cachedId == null) {
         this.cachedId = ILootrInfo.generateInfoKey(this.getInfoUUID());
      }

      return this.cachedId;
   }

   @Override
   public boolean hasBeenOpened() {
      return this.hasBeenOpened;
   }

   @Override
   public boolean isPhysicallyOpen() {
      return false;
   }

   @Override
   public void markChanged() {
      this.method_5431();
      this.markDataChanged();
   }

   @Override
   public double getParticleYOffset() {
      return 1.1;
   }

   @Override
   public double[] getParticleXBounds() {
      return new double[]{0.3, 0.7};
   }

   @Override
   public double[] getParticleZBounds() {
      return new double[]{0.3, 0.7};
   }

   @Nullable
   @Override
   public IContainerTrigger getTrigger() {
      return LootrRegistry.getCartTrigger();
   }

   protected void method_7525() {
      float f = 0.98F;
      if (this.method_5799()) {
         f *= 0.95F;
      }

      this.method_18799(this.method_18798().method_18805(f, 0.0, f));
   }

   protected class_1792 method_7557() {
      return class_1802.field_8388;
   }

   @AutoService(ILootrEntityConverter.class)
   public static class DefaultConverter implements ILootrEntityConverter<LootrChestMinecartEntity> {
      public ILootrEntity apply(LootrChestMinecartEntity entity) {
         return entity;
      }

      @Override
      public class_1299<?> getEntityType() {
         return LootrRegistry.getMinecart();
      }
   }
}
