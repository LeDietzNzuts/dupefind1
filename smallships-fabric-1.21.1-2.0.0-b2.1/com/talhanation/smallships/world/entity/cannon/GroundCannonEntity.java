package com.talhanation.smallships.world.entity.cannon;

import com.talhanation.smallships.network.ModPackets;
import com.talhanation.smallships.network.packet.ServerboundEnterCannonBarrelPacket;
import com.talhanation.smallships.network.packet.ServerboundShootGroundCannonPacket;
import com.talhanation.smallships.world.entity.IMixinEntity;
import com.talhanation.smallships.world.entity.ModEntityTypes;
import com.talhanation.smallships.world.entity.projectile.CannonBallEntity;
import com.talhanation.smallships.world.entity.projectile.ICannonProjectile;
import com.talhanation.smallships.world.item.CannonBallItem;
import com.talhanation.smallships.world.item.ModItems;
import com.talhanation.smallships.world.particles.ModParticleTypes;
import com.talhanation.smallships.world.particles.cannon.DyedCannonShootOptions;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1301;
import net.minecraft.class_1309;
import net.minecraft.class_1439;
import net.minecraft.class_1657;
import net.minecraft.class_1688;
import net.minecraft.class_1695;
import net.minecraft.class_1767;
import net.minecraft.class_1769;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1928;
import net.minecraft.class_1937;
import net.minecraft.class_2394;
import net.minecraft.class_243;
import net.minecraft.class_2487;
import net.minecraft.class_2940;
import net.minecraft.class_2943;
import net.minecraft.class_2945;
import net.minecraft.class_3414;
import net.minecraft.class_3481;
import net.minecraft.class_4048;
import net.minecraft.class_9279;
import net.minecraft.class_9334;
import net.minecraft.class_2945.class_9222;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3d;
import org.joml.Vector3f;

public class GroundCannonEntity extends class_1695 implements ICannon {
   public static final String ID = "ground_cannon";
   private static final class_2940<Optional<UUID>> UUID = class_2945.method_12791(GroundCannonEntity.class, class_2943.field_13313);
   private static final class_2940<String> DYE = class_2945.method_12791(GroundCannonEntity.class, class_2943.field_13326);
   private final Cannon cannon = new Cannon(this);
   private boolean drivenPrevTick = false;

   public GroundCannonEntity(class_1937 level, class_243 pos) {
      super(ModEntityTypes.GROUND_CANNON, level);
      this.method_33574(pos);
   }

   public GroundCannonEntity(class_1299<? extends GroundCannonEntity> entityType, class_1937 level) {
      super(entityType, level);
   }

   protected void method_5693(class_9222 builder) {
      super.method_5693(builder);
      builder.method_56912(UUID, Optional.empty());
      builder.method_56912(DYE, "");
   }

   public Optional<UUID> getEntityInBarrelUUID() {
      return (Optional<UUID>)this.field_6011.method_12789(UUID);
   }

   protected final void setEntityInBarrelUUID(UUID uuid) {
      this.field_6011.method_12778(UUID, Optional.ofNullable(uuid));
   }

   @Nullable
   public class_1767 getDye() {
      String dye = (String)this.field_6011.method_12789(DYE);
      return dye.isEmpty() ? null : class_1767.method_7793((String)this.field_6011.method_12789(DYE), null);
   }

   @Nullable
   protected final void setDye(@Nullable class_1767 dye) {
      this.field_6011.method_12778(DYE, dye != null ? dye.method_15434() : "");
   }

   public void method_5652(class_2487 tag) {
      super.method_5652(tag);
      class_1767 dye;
      if ((dye = this.getDye()) != null) {
         tag.method_10582("Dye", dye.method_15434());
      }

      this.getEntityInBarrelUUID().ifPresent(uuid -> tag.method_25927("EntityInBarrelUUID", uuid));
   }

   public void method_5749(class_2487 tag) {
      super.method_5749(tag);
      if (tag.method_10545("Dye")) {
         this.setDye(class_1767.method_7793(tag.method_10558("Dye"), null));
      }

      if (tag.method_10545("EntityInBarrelUUID")) {
         this.setEntityInBarrelUUID(tag.method_25926("EntityInBarrelUUID"));
      }
   }

   public Cannon getCannon() {
      return this.cannon;
   }

   public void method_5773() {
      float xRot = this.method_36455();
      float yRot = this.method_36454();
      super.method_5773();
      boolean isDriven = this.getPassengerDriver() != null;
      class_1297 driver = this.getPassengerDriver();
      boolean enteredCannon = !this.drivenPrevTick && isDriven;
      if (enteredCannon) {
         this.getPassengerDriver().method_36456(this.method_36454());
         this.getPassengerDriver().method_36457(this.method_36455());
      }

      this.drivenPrevTick = isDriven;
      if (isDriven) {
         xRot = driver.method_36455();
         yRot = driver.method_36454();
      }

      xRot = Math.clamp(xRot, -90.0F, 20.0F);
      this.method_36456(yRot);
      this.method_36457(xRot);
      this.cannon.tick(this.method_23317(), this.method_23318(), this.method_23321(), -yRot, xRot);
      this.testEntityIntersection();
   }

   protected void testEntityIntersection() {
      if (!this.method_37908().method_8608()) {
         List<class_1297> list = this.method_37908().method_8333(this, this.method_5829().method_1009(0.2F, 0.0, 0.2F), class_1301.method_5911(this));
         if (!list.isEmpty()) {
            for (class_1297 entity : list) {
               boolean isEntityTypeAllowed = !(entity instanceof class_1657) && !(entity instanceof class_1439) && !(entity instanceof class_1688);
               boolean isBarrelEmpty = this.getPassengerInBarrel() == null;
               if (isEntityTypeAllowed && isBarrelEmpty && !entity.method_5765()) {
                  this.tryPuttingIntoBarrel(entity);
               }
            }
         }
      }
   }

   public class_1269 method_5688(class_1657 player, class_1268 interactionHand) {
      if (this.itemInteraction(player, interactionHand)) {
         return class_1269.field_21466;
      } else if (player.method_21823()) {
         return class_1269.field_5811;
      } else if (this.method_5685().size() == 2) {
         return class_1269.field_5811;
      } else if (!this.method_37908().method_8608()) {
         return this.tryRiding(player) ? class_1269.field_21466 : class_1269.field_5811;
      } else {
         return class_1269.field_5812;
      }
   }

   protected boolean itemInteraction(class_1657 player, class_1268 interactionHand) {
      if (!this.method_37908().method_8608() && interactionHand == class_1268.field_5808) {
         class_1799 item = player.method_6047();
         if (item.method_7909() instanceof class_1769 dye) {
            if (!dye.method_7802().equals(this.getDye())) {
               item.method_7934(1);
               this.setDye(dye.method_7802());
            }

            return true;
         }

         if (item.method_31574(class_1802.field_8884)) {
            this.setDye(null);
            return true;
         }
      }

      return false;
   }

   protected boolean tryRiding(class_1297 entity) {
      if (this.method_37908().method_8608()) {
         return false;
      } else if (this.getPassengerInBarrel() == null && !this.method_5685().isEmpty() && this.method_5818(entity)) {
         return this.tryPuttingIntoBarrel(entity);
      } else {
         this.cleanEntityInBarrelUUID();
         return entity.method_5804(this);
      }
   }

   protected boolean tryPuttingIntoBarrel(class_1297 entity) {
      if (!this.method_37908().method_8608() && entity != null && !this.getCannon().isFuzing()) {
         class_1297 barrelEntity = this.getPassengerInBarrel();
         if (barrelEntity == entity) {
            return true;
         } else if (this.method_5685().size() == 2) {
            return false;
         } else if (barrelEntity != null) {
            return false;
         } else if (this.method_5685().contains(entity)) {
            this.setEntityInBarrelUUID(entity.method_5667());
            return true;
         } else if (entity.method_5804(this)) {
            this.setEntityInBarrelUUID(entity.method_5667());
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public void putEntityIntoBarrel(class_1297 entity) {
      if (this.method_37908().method_8608()) {
         ModPackets.clientSendPacket(new ServerboundEnterCannonBarrelPacket(this.method_5628(), entity.method_5628()));
      } else {
         this.tryPuttingIntoBarrel(entity);
      }
   }

   protected boolean method_5818(class_1297 entity) {
      return this.method_5685().size() < 2;
   }

   public void trigger(class_1297 triggeredBy) {
      if (this.method_37908().method_8608()) {
         ModPackets.clientSendPacket(new ServerboundShootGroundCannonPacket(false));
      } else {
         CannonBallItem cannonBallToShoot = this.getPassengerInBarrel() == null ? this.getCannonBallToShoot() : null;
         boolean canFuze = cannonBallToShoot != null || this.getPassengerInBarrel() != null;
         if (canFuze) {
            if (cannonBallToShoot != null) {
               this.consumeCannonBall();
            }

            this.cannon
               .triggerFuze(
                  triggeredBy,
                  () -> (ICannonProjectile)(cannonBallToShoot != null
                     ? new CannonBallEntity(this.method_37908())
                     : (ICannonProjectile)this.getPassengerInBarrel())
               );
         }
      }
   }

   protected class_243 method_52533(class_1297 entity, class_4048 entityDimensions, float f) {
      if (this.getPassengerInBarrel() == entity) {
         Vector3d endPoint = this.cannon.getBarrelEndPointLocal();
         return new class_243(endPoint.x, endPoint.y, endPoint.z);
      } else {
         return this.getBarrelPassengerAttachmentPoint();
      }
   }

   protected class_243 getBarrelPassengerAttachmentPoint() {
      Vector3f relativePoint = new Vector3f(0.0F, 0.0F, -0.5F).rotateAxis(-((float)Math.toRadians(this.method_36454())), 0.0F, 1.0F, 0.0F);
      return new class_243(relativePoint.x, relativePoint.y, relativePoint.z);
   }

   public void method_5644(class_1297 entity) {
      super.method_5644(entity);
      if (this.getPassengerDriver() == entity) {
         float prevXRot = ((IMixinEntity)entity).getPrevXRot();
         float prevYRot = ((IMixinEntity)entity).getPrevYRot();
         float yRotChange = Math.clamp(0.1F * (entity.method_36454() - prevYRot), -5.0F, 5.0F);
         float xRotChange = Math.clamp(0.1F * (entity.method_36455() - prevXRot), -5.0F, 5.0F);
         entity.method_36456(prevYRot + yRotChange);
         entity.method_5636(prevYRot + yRotChange);
         entity.field_6004 = prevXRot + xRotChange;
         entity.method_36457(Math.clamp(prevXRot + xRotChange, -90.0F, 20.0F));
      }
   }

   public void method_54298(class_1792 arg) {
      this.method_5768();
      if (this.method_37908().method_8450().method_8355(class_1928.field_19393)) {
         class_1799 itemStack = new class_1799(arg);
         itemStack.method_57379(class_9334.field_49631, this.method_5797());
         class_2487 tag = new class_2487();
         this.method_5652(tag);
         itemStack.method_57379(class_9334.field_49628, class_9279.method_57456(tag));
         this.method_5775(itemStack);
      }
   }

   public class_1792 method_7557() {
      return ModItems.CANNON;
   }

   public class_1799 method_31480() {
      return new class_1799(ModItems.CANNON);
   }

   public boolean method_5810() {
      return this.method_37908().method_8320(this.method_24515()).method_26164(class_3481.field_15463);
   }

   @Nullable
   public class_1297 getPassengerDriver() {
      for (class_1297 passenger : this.method_5685()) {
         if (passenger != this.getPassengerInBarrel()) {
            return passenger;
         }
      }

      return null;
   }

   @Nullable
   public class_1297 getPassengerInBarrel() {
      Optional<UUID> uuid = this.getEntityInBarrelUUID();
      if (uuid.isEmpty()) {
         return null;
      } else if (this.method_5685().isEmpty()) {
         return null;
      } else {
         for (class_1297 passenger : this.method_5685()) {
            if (passenger.method_5667().equals(uuid.get())) {
               return passenger;
            }
         }

         return null;
      }
   }

   protected void cleanEntityInBarrelUUID() {
      if (this.method_5685().isEmpty() || this.getPassengerInBarrel() == null) {
         this.setEntityInBarrelUUID(null);
      }
   }

   public static GroundCannonEntity factory(class_1299<? extends GroundCannonEntity> entityType, class_1937 level) {
      return new GroundCannonEntity(entityType, level);
   }

   @Override
   public void consumeCannonBall() {
      class_1297 driver = this.getPassengerDriver();
      if (driver != null && !(driver instanceof class_1309 livingDriver && livingDriver.method_56992())) {
         if (driver instanceof ICannonBallSource container) {
            container.consumeCannonBall();
         } else if (this.getPassengerDriver() instanceof class_1657 player) {
            for (class_1799 itemstack : player.method_31548().field_7547) {
               if (itemstack.method_31574(ModItems.CANNON_BALL)) {
                  itemstack.method_7934(1);
                  break;
               }
            }
         }
      }
   }

   @Override
   public class_2394 provideShootParticles() {
      return (class_2394)(this.getDye() != null ? new DyedCannonShootOptions(this.getDye()) : (class_2394)ModParticleTypes.CANNON_SHOOT.get());
   }

   @Override
   public void playSoundAt(class_3414 soundEvent, float volumeMultiplier, float pitch) {
      this.method_5783(soundEvent, volumeMultiplier, pitch);
   }

   @Override
   public class_1937 getLevel() {
      return this.method_37908();
   }

   @Override
   public CannonBallItem getCannonBallToShoot() {
      if (this.getPassengerDriver() == null) {
         return null;
      } else if (this.getPassengerDriver() instanceof ICannonBallSource container) {
         return container.getCannonBallToShoot();
      } else if (this.getPassengerDriver() instanceof class_1657 player) {
         return player.method_31548().field_7547.stream().anyMatch(itemStack -> itemStack.method_7909().equals(ModItems.CANNON_BALL))
            ? ModItems.CANNON_BALL
            : null;
      } else {
         return null;
      }
   }
}
